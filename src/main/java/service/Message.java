package service;

import org.json.JSONObject;

public class Message {

    private JSONObject message;

    public Message(Object message){

        this.message = ((JSONObject) message);

    }

    public String getMessage(){
        return message.getString("message");
    }

    public String getSender(){
        return message.getString("sender");
    }

    public String getDateSent(){
        return message.getString("date_time");
    }

}
