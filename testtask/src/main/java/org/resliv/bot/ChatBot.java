package org.resliv.bot;

import org.resliv.model.City;
import org.resliv.model.User;
import org.resliv.repo.CityRepo;
import org.resliv.service.CityService;
import org.resliv.service.MessageService;
import org.resliv.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.validation.Valid;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    private final UserService userService;
    private final CityService cityService;
    MessageService messageService=new MessageService();
    public ChatBot(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText())
            return;
        final String text = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();
        User user = userService.findByChatId(chatId);
        BotContext botContext;
        BotState state;
        if (user == null) {
            state=BotState.getInitialState();
            user=new User(chatId,state.ordinal());
            userService.addUser(user);
            botContext=BotContext.of(this,user,text,cityService);
            state.enter(botContext);
        }
        else{
            botContext=BotContext.of(this,user,text,cityService);
            state=BotState.byId(user.getStateId());
        }
        state.handleInput(botContext);
        do{
            state=state.nextState();
            state.enter(botContext);
        } while(!state.isInputNeeded());
        user.setStateId(state.ordinal());
        userService.updateUser(user);

    }

}
