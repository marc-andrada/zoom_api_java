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
        if(message.has("sender")){
            return message.getString("sender");
        }
        else{ return ""; }

    }

    public String getDateSent(){
        if(message.has("date_time")){
            return message.getString("date_time");
        }
        else{ return ""; }

    }

    public String getMessageID(){
        if(message.has("id")){
            return message.getString("id");
        }
        else{ return ""; }
    }

    public String printMessage(){

        String messageInfo =
            "\nMessage: \"" + this.getMessage() + "\"" +
            "\nSender: " + this.getSender() +
            "\nDate Sent: " + this.getDateSent();

        return messageInfo;
    }


}
