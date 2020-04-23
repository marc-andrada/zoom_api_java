package components;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatMessages extends Base{

    public ChatMessages(String baseUri, int timeout, String token){
        super(baseUri, timeout, token);
    }

    public String list(String userID, String toContact, String channelID) throws Exception {

        List<NameValuePair> nvp = new ArrayList<>();

        if(toContact == null & channelID == null){
            System.out.println("Need to provide email of to contact or channel ID to list messages");
        }
        if(toContact!=null){
            NameValuePair toContactInfo = new BasicNameValuePair("to_contact", toContact);
            nvp.add(toContactInfo);
        }
        if(channelID!=null){
            NameValuePair toChannelInfo = new BasicNameValuePair("to_channel", channelID);
            nvp.add(toChannelInfo);
        }

        HttpResponse response = super.getRequest("/chat/users/"+userID+"/messages", nvp);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

    public void send(String message, String toContact, String channelID) throws InterruptedException, IOException, URISyntaxException {

        HashMap<String, Object> data = new HashMap<>();
        data.put("message", message);
        data.put("to_contact", toContact);
        data.put("to_channel", channelID);

        HttpResponse response = super.postRequest("/chat/users/me/messages", null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void update(String messageID, String message, String toContact, String channelID) throws InterruptedException, IOException, URISyntaxException {

        HashMap<String, Object> data = new HashMap<>();
        data.put("message", message);
        data.put("to_contact", toContact);
        data.put("to_channel", channelID);

        HttpResponse response = super.putRequest("/chat/users/me/messages/"+messageID, null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void delete(String messageID, String toContact, String channelID) throws InterruptedException, IOException, URISyntaxException {

        List<NameValuePair> nvp = new ArrayList<>();

        if(toContact == null & channelID == null){
            System.out.println("Need to provide email of to contact or channel ID to list messages");
        }
        if(toContact!=null){
            System.out.println("Adding to_contact");
            NameValuePair toContactInfo = new BasicNameValuePair("to_contact", toContact);
            nvp.add(toContactInfo);
        }
        if(channelID!=null){
            System.out.println("Adding to_channel");
            NameValuePair toChannelInfo = new BasicNameValuePair("to_channel", channelID);
            nvp.add(toChannelInfo);
        }

        HttpResponse response = super.deleteRequest("/chat/users/me/messages/"+messageID, nvp, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }


}
