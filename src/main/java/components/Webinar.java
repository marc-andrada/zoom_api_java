package components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class Webinar extends Base {

    public Webinar(String baseUri, int timeout, String token) {
        super(baseUri, timeout, token);
    }

    public String list(String userID) throws Exception {

        HttpResponse response = super.getRequest("/users/" + userID + "/webinars", null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();

    }

    public void create(String userID, String topic, Integer type, String startTime, Integer duration, String timeZone, String password, String agenda,
                       String field, String value, Integer recurrenceType) throws Exception {

        //Overall Object
        HashMap<String, Object> data = new HashMap<String, Object>();
        //TrackingFields
        ArrayList<HashMap<String, String>> trackingFields = new ArrayList<>();
        HashMap<String, String> tfField = new HashMap<String, String>();
        tfField.put("field", field);
        HashMap<String, String> tfValue = new HashMap<String, String>();
        tfField.put("value", value);
        trackingFields.add(tfField);
        trackingFields.add(tfValue);
        //Recurrence Object
        HashMap<String, Object> recurrence = new HashMap<String, Object>();
        recurrence.put("type", recurrenceType);
        //Add to data object
        data.put("topic", topic);
        data.put("type", type);
        data.put("start_time", startTime);
        data.put("duration", duration);
        data.put("timezone", timeZone);
        data.put("password", password);
        data.put("agenda", agenda);
        data.put("tracking_fields", trackingFields);
        data.put("recurrence", recurrence);

        HttpResponse response = super.postRequest("/users/" + userID + "/webinars", null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void update(String webinarID, String topic, Integer type, String startTime, Integer duration, String timeZone, String password, String agenda,
                       String field, String value, Integer recurrenceType) throws Exception {

        //Overall Object
        HashMap<String, Object> data = new HashMap<String, Object>();
        //TrackingFields
        ArrayList<HashMap<String, String>> trackingFields = new ArrayList<>();
        HashMap<String, String> tfField = new HashMap<String, String>();
        tfField.put("field", field);
        HashMap<String, String> tfValue = new HashMap<String, String>();
        tfField.put("value", value);
        trackingFields.add(tfField);
        trackingFields.add(tfValue);
        //Recurrence Object
        HashMap<String, Object> recurrence = new HashMap<String, Object>();
        recurrence.put("type", recurrenceType);
        //Add to data object
        data.put("topic", topic);
        data.put("type", type);
        data.put("start_time", startTime);
        data.put("duration", duration);
        data.put("timezone", timeZone);
        data.put("password", password);
        data.put("agenda", agenda);
        data.put("tracking_fields", trackingFields);
        data.put("recurrence", recurrence);

        HttpResponse response = super.patchRequest("/webinars/" + webinarID, null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void delete(String webinarID) throws Exception {

        HttpResponse response = super.deleteRequest("/webinars/" + webinarID, null, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());


    }

    //How do they want the webinarID to be represented as int64? a string of it?
    public void end(String webinarID) throws InterruptedException, IOException, URISyntaxException {

        HttpResponse response = super.putRequest("/webinars/"+webinarID+"/status", null, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public String get(String webinarID) throws Exception {

        HttpResponse response = super.getRequest("/users/"+webinarID, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();

    }

    public void register(String webinarID, String email, String firstName, String lastName) throws InterruptedException, IOException, URISyntaxException {

        HashMap<String, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("first_name", firstName);
        data.put("last_name", lastName);

        HttpResponse response = super.postRequest("/webinars/"+webinarID+"/registrants", null, data);

    }

}
