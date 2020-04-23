package components;

import java.net.http.HttpResponse;

public class Recording extends Base{

    public Recording(String baseUri, int timeout, String token){ super(baseUri, timeout, token); }

    public String list(String userID) throws Exception {

        HttpResponse response = super.getRequest("/users/"+userID+"/recordings", null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

    public String get(String meetingID) throws Exception {

        HttpResponse response = super.getRequest("/users/"+meetingID+"/recordings", null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

    public void delete(String meetingID) throws Exception {

        HttpResponse response = super.deleteRequest("/users/"+meetingID+"/recordings", null, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }



}


