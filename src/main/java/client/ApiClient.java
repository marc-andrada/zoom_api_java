package client;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;

public class ApiClient {

    private String BASE_URI;
    private int TIMEOUT;
    private Throttle throttle;

    public String TOKEN;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public ApiClient(String baseURI, int timeout)
    {
        BASE_URI = baseURI;
        TIMEOUT = timeout;
        throttle = new Throttle();
    };

    //Get and Set BaseURI
    private String getBaseURI(){return BASE_URI;}
    private void setBaseURI(String uri){BASE_URI = uri;}

    //Get and Set Timeout
    private int getTimeout(){return TIMEOUT;}
    private void setTimeout(int timeout){TIMEOUT = timeout;}

    public void setToken(String token){
        TOKEN = token;
    }

    private String urlFor(String endpoint){

        if(!endpoint.startsWith("/")){
            endpoint = endpoint.format("/%s", endpoint);
        }
        if(endpoint.endsWith("/")){
            endpoint = endpoint.substring(0, endpoint.length()-1);
        }
        return this.getBaseURI() + endpoint;

    }


    public HttpResponse getRequest(String endpoint, List<NameValuePair> params) throws Exception {

        URIBuilder uriBuilder = new URIBuilder(BASE_URI + endpoint);
        if(params != null){
            uriBuilder.addParameters(params);
        }

        //System.out.println("Token in superclass: " + TOKEN);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uriBuilder.toString()))
                .setHeader("Authorization", "Bearer " + TOKEN)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(throttle.check(response.statusCode())){
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }

        //System.out.println("Sending GET request: " + request.toString());
        return response;
    }

    public HttpResponse postRequest(String endpoint, List<NameValuePair> params, Object data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        Gson gson = new Gson();

        URIBuilder uriBuilder = new URIBuilder(BASE_URI + endpoint);
        if(params != null){
            uriBuilder.addParameters(params);
        }

        //System.out.println(gson.toJson(data));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriBuilder.toString()))
                .setHeader("Authorization", "Bearer " + TOKEN)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(data)))
                .setHeader("Accept", "application/json")
                .setHeader("Content-type", "application/json")
                .build();

        //System.out.println("Headers: " + request.headers());
        //System.out.println("Sending POST request: " + request.toString());

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(throttle.check(response.statusCode())){
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return response;

    }

    public HttpResponse deleteRequest(String endpoint, List<NameValuePair> params, Map<String, Object> data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        URIBuilder uriBuilder = new URIBuilder(BASE_URI + endpoint);
        if(params != null){
            uriBuilder.addParameters(params);
        }

        //System.out.println("Data: " + gson.toJson(data));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .setHeader("Authorization", "Bearer " + TOKEN)
                .DELETE()
                .build();

        //System.out.println("Headers: " + request.headers());
        //System.out.println("Sending DELETE request: " + request.toString());
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(throttle.check(response.statusCode())){
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return response;

    }

    public HttpResponse patchRequest(String endpoint, List<NameValuePair> params, Map<String, Object> data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        URIBuilder uriBuilder = new URIBuilder(BASE_URI + endpoint);
        if(params != null){
            uriBuilder.addParameters(params);
        }

        //System.out.println("Data: " + gson.toJson(data));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .setHeader("Authorization", "Bearer " + TOKEN)
                .setHeader("Accept", "application/json")
                .setHeader("Content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(gson.toJson(data)))
                .build();

        //System.out.println("Headers: " + request.headers());
        //System.out.println("Sending PATCH request: " + request.toString());

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(throttle.check(response.statusCode())){
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return response;

    }

    public HttpResponse putRequest(String endpoint, List<NameValuePair> params, Map<String, Object> data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        URIBuilder uriBuilder = new URIBuilder(BASE_URI + endpoint);
        if(params != null){
            uriBuilder.addParameters(params);
        }

        //System.out.println("Data: " + gson.toJson(data));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .setHeader("Accept", "application/json")
                .setHeader("Content-type", "application/json")
                .setHeader("Authorization", "Bearer " + TOKEN)
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(data)))
                .build();

        //System.out.println("Headers: " + request.headers());
        //System.out.println("Sending PUT request: " + request.toString());

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(throttle.check(response.statusCode())){
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return response;

    }

    public Date stringToDate(String dateString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date = formatter.parse(dateString);
        return date;
    }

    public String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }


}
