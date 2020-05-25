package components;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatChannels extends Base {

    public ChatChannels(String baseUri, int timeout, String token){
        super(baseUri, timeout, token);
    }

    public String list() throws Exception {

        HttpResponse response = super.getRequest("/chat/users/me/channels", null);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

    public void create(Map<String, Object> data) throws Exception {

        HttpResponse response = super.postRequest("/chat/users/me/channels", null, data);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

    }

    public String get(String channelID) throws Exception {

        HttpResponse response = super.getRequest("/chat/channels/"+channelID, null);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

        return response.body().toString();

    }

    public void update(String channelID, String newName) throws InterruptedException, IOException, URISyntaxException {

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", newName);

        HttpResponse response = super.patchRequest("/chat/channels/"+channelID, null, data);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());
    }

    public void delete(String channelID) throws InterruptedException, IOException, URISyntaxException {

        HttpResponse response = super.deleteRequest("/chat/channels/"+channelID, null, null);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

    }


    public String listMembers(String channelID, String nextPageToken) throws Exception {

        List<NameValuePair> nvp = new ArrayList<>();

        NameValuePair nextPageTokenParam = new BasicNameValuePair("next_page_token", nextPageToken);
        nvp.add(nextPageTokenParam);

        HttpResponse response = super.getRequest("/chat/channels/"+channelID+"/members", nvp);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

    public void inviteMembers(String channelID, HashMap<String, Object> emailList) throws InterruptedException, IOException, URISyntaxException {

        HttpResponse response = super.postRequest("/chat/channels/"+channelID+"/members", null, emailList);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

    }

    public void join(String channelID) throws InterruptedException, IOException, URISyntaxException {

        HttpResponse response = super.postRequest("/chat/channels/"+channelID+"/members/me", null, null);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());

    }

    public void leave(String channelID) throws InterruptedException, IOException, URISyntaxException {

        HttpResponse response = super.deleteRequest("/chat/channels/"+channelID, null, null);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());
    }

    public void removeMember(String channelID, String memberID) throws InterruptedException, IOException, URISyntaxException {
        HttpResponse response = super.deleteRequest("/chat/channels/"+channelID+"/members/"+memberID, null, null);

        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.body());
    }

}
