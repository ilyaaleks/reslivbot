package org.resliv.bot;

import org.resliv.model.City;
import org.resliv.service.CityService;
import org.resliv.service.MessageService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public enum BotState {
    START{
        @Override
        public void enter(BotContext context) {
            messageService.sendMessage(context,"Hello, my name is CityBot.");
        }

        @Override
        public BotState nextState() {
            return ENTERCITY;
        }
    },
    ENTERCITY{
        @Override
        public void handleInput(BotContext context) {
            String cityName=context.getInput();
            City city=context.getCityService().findCity(cityName);
            if(city!=null) {
                String cityInfo = city.getName() + " - " + city.getDescription();
                sendCityInfo(city,context);
            }
            else{
                messageService.sendMessage(context,"City not found");
            }
        }
        private void sendCityInfo(City city, BotContext botContext)
        {
            if(city!=null) {
                String cityInfo = city.getName() + " - " + city.getDescription();
                messageService.sendMessage(botContext,cityInfo);
            }
            else{
                messageService.sendMessage(botContext,"City not found");
            }
        }
        @Override
        public void enter(BotContext context) {
            messageService.sendMessage(context,"Enter your city");
        }

        @Override
        public BotState nextState() {
            return FINISH;
        }
    },
    FINISH(false){
        @Override
        public void enter(BotContext context) {
            messageService.sendMessage(context,"That's what i managed to find");
        }

        @Override
        public BotState nextState() {
            return ENTERCITY;
        }
    };
    @Autowired
    private CityService service;
    MessageService messageService=new MessageService();
    private static BotState[] states;
    private final boolean inputNeeded;

    BotState() {
        inputNeeded=true;
    }

    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;
    }
    public static BotState getInitialState()
    {
        return byId(0);
    }

    public static BotState byId(int id) {
        if(states==null)
        {
            states=BotState.values();
        }
        return states[id];
    }

    public boolean isInputNeeded() {
        return inputNeeded;
    }

    public void handleInput(BotContext context){

    }
    public abstract void enter(BotContext context);
    public abstract BotState nextState();
}

