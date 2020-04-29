package components;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Report extends Base{

    public Report(String baseUri, int timeout, String token){ super(baseUri, timeout, token); }

    public String getUserReport(String userID, String fromDate, String toDate) throws Exception {

        List<NameValuePair> nvp = new ArrayList<>();

        NameValuePair from = new BasicNameValuePair("from", fromDate);
        NameValuePair to = new BasicNameValuePair("to", toDate);
        nvp.add(from);
        nvp.add(to);

        HttpResponse response = super.getRequest("/report/users/"+userID+"/recordings", nvp);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();

    }

    public String getAccountReport(String fromDate, String toDate) throws Exception {

        List<NameValuePair> nvp = new ArrayList<>();

        NameValuePair from = new BasicNameValuePair("from", fromDate);
        NameValuePair to = new BasicNameValuePair("to", toDate);
        nvp.add(from);
        nvp.add(to);

        HttpResponse response = super.getRequest("/report/users", nvp);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        return response.body().toString();
    }
}
