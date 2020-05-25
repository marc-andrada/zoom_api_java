package service;

import java.util.HashMap;

public class Request {

    private String channelName;
    private IHandler handler;
    private HashMap<String, Message> newMessageMap = new HashMap<>();
    private HashMap<String, Message> updatedMessageMap = new HashMap<>();
    private HashMap<String, Member> newMembersMap = new HashMap<>();

    public Request(String channelName, IHandler handler){

        this.channelName = channelName;
        this.handler = handler;

    };

    String getChannelName(){return channelName;}
    IHandler getIHandler(){return handler;}
    HashMap<String, Message> getNewMessageMap(){return newMessageMap;}
    HashMap<String, Message> getUpdatedMessageMap(){return updatedMessageMap;}
    HashMap<String, Member> getNewMembersMap(){return newMembersMap;}

}
