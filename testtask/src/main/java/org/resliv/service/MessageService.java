package org.resliv.service;

import org.resliv.bot.BotContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class MessageService {
    public void sendMessage(BotContext context, String text)
    {
        SendMessage message=new SendMessage(context.getUser().getChatId(),text);
        try{
            context.getBot().execute(message);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

}
