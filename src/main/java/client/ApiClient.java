package client;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.*;
import java.util.Map;


public class ApiClient {

    private String BASE_URI;
    private int TIMEOUT;
    private String token;

    public ApiClient(String baseURI, int timeout)
    {
        BASE_URI = baseURI;
        TIMEOUT = timeout;

    };

    public HttpResponse postRequest(String endpoint, List<NameValuePair> params, Map<String, Object> data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        URIBuilder uriBuilder = new URIBuilder(BASE_URI + endpoint);
        if(params != null){
            uriBuilder.addParameters(params);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriBuilder.toString()))
                .setHeader("Authorization", "Bearer " + this.token)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(data)))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

}
