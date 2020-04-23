package components;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Meeting extends Base{

    public Meeting(String baseUri, int timeout, String token){ super(baseUri, timeout, token); }

    public String list(String userID) throws Exception {

        HttpResponse response = super.getRequest("/users/"+userID+"/meetings", null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();

    }

    public void create(String topic, Integer type, String startTime, Integer duration, String timeZone, String password, String agenda,
    String field, String value, Integer recurrenceType) throws Exception {

        //Overall Object
        HashMap<String, Object> data = new HashMap<String, Object>();
        //TrackingFields
        ArrayList<HashMap<String,String>> trackingFields = new ArrayList<>();
        HashMap<String, String> tfField = new HashMap<String,String>();
        tfField.put("field", field);
        HashMap<String, String> tfValue = new HashMap<String,String>();
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
        data.put("tracking_fields",trackingFields);
        data.put("recurrence", recurrence);

        HttpResponse response = super.postRequest("/users", null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public String get(String meetingID) throws Exception {

        HttpResponse response = super.getRequest("/meetings"+meetingID, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();

    }

    public void update(String scheduleFor, String topic, Integer type, String startTime, Integer duration, String timeZone, String password, String agenda,
                       String field, String value, Integer recurrenceType) throws Exception {

        //Overall Object
        HashMap<String, Object> data = new HashMap<String, Object>();
        //TrackingFields
        ArrayList<HashMap<String,String>> trackingFields = new ArrayList<>();
        HashMap<String, String> tfField = new HashMap<String,String>();
        tfField.put("field", field);
        HashMap<String, String> tfValue = new HashMap<String,String>();
        tfField.put("value", value);
        trackingFields.add(tfField);
        trackingFields.add(tfValue);
        //Recurrence Object
        HashMap<String, Object> recurrence = new HashMap<String, Object>();
        recurrence.put("type", recurrenceType);
        //Add to data object
        data.put("schedule_for", scheduleFor);
        data.put("topic", topic);
        data.put("type", type);
        data.put("start_time", startTime);
        data.put("duration", duration);
        data.put("timezone", timeZone);
        data.put("password", password);
        data.put("agenda", agenda);
        data.put("tracking_fields",trackingFields);
        data.put("recurrence", recurrence);

        HttpResponse response = super.patchRequest("/users", null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void delete(String meetingID) throws Exception {

        HttpResponse response = super.deleteRequest("/meetings/"+meetingID, null, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }



}
