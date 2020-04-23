package components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class User extends Base {


    public User(String baseUri, int timeout, String token){
        super(baseUri, timeout, token);
    }

    public String list() throws Exception {

        HttpResponse response = super.getRequest("/users", null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

    public void create(String action, String email, Integer type, String firstName, String lastName, String password) throws Exception {

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("action", action);

        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        data.put("email", email);
        data.put("type", type);
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("password", password);

        data.put("user_info", userInfo);

        HttpResponse response = super.postRequest("/users", null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void update(String userID, String firstName, String lastName, Integer type, Integer pmi, String timeZone, String language, String department, String vanityName,
                       String hostKey, String cmsUserID, String jobTitle, String company, String location, String phoneNumber, String phoneCountry) throws InterruptedException, IOException, URISyntaxException {

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("type", type);
        data.put("pmi", pmi);
        data.put("timezone", timeZone);
        data.put("language", language);
        data.put("dept", department);
        data.put("vanity_name", vanityName);
        data.put("host_key", hostKey);
        data.put("cms_user_id", cmsUserID);
        data.put("job_title", jobTitle);
        data.put("company", company);
        data.put("location", location);
        data.put("phone_number", phoneNumber);
        data.put("phone_country", phoneCountry);

        HttpResponse response = super.patchRequest("/users/"+userID, null, data);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public void delete(String userID) throws InterruptedException, IOException, URISyntaxException {

        HttpResponse response = super.deleteRequest("/users/"+userID, null, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }

    public String get(String id) throws Exception {

        HttpResponse response = super.getRequest("/users/"+id, null);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }

}
