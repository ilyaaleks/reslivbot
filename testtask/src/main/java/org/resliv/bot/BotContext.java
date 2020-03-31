package org.resliv.bot;

import org.resliv.model.City;
import org.resliv.model.User;
import org.resliv.service.CityService;

public class BotContext {
    private final ChatBot bot;
    private final User user;
    private final String input;
    private final CityService cityService;
public static BotContext of(ChatBot chatBot, User user, String text,CityService cityService){
    return new BotContext(chatBot,user,text,cityService);
}

    public BotContext(ChatBot bot, User user, String input, CityService cityService) {
        this.bot = bot;
        this.user = user;
        this.input = input;
        this.cityService = cityService;
    }

    public ChatBot getBot() {
        return bot;
    }

    public User getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public CityService getCityService() {
        return cityService;
    }
}
