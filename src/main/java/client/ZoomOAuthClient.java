package client;

import components.*;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import java.io.IOException;
import java.net.URISyntaxException;

public class ZoomOAuthClient extends ZoomClient{

    //Class Components
    public ChatChannels chatChannels;
    public ChatMessages chatMessages;
    public User user;
    public Recording recording;
    public Report report;
    public Meeting meeting;
    public Webinar webinar;


    //Fields
    private String clientID;
    private String clientSecret;
    private int PORT;
    private String redirectURI;
    private String BROWSER_PATH;
    private String zoacToken;

    public ZoomOAuthClient(int timeout, String apiKey, String apiSecret, int port, String redirectURI, String browserPath) throws OAuthSystemException, OAuthProblemException, IOException, URISyntaxException {
        super(timeout, apiKey, apiSecret);

        clientID = apiKey;
        clientSecret = apiSecret;
        TokenHandler tokenHandler = new TokenHandler();
        zoacToken = tokenHandler.getOAuthToken(apiKey, apiSecret, redirectURI);
        super.setApiClientToken(zoacToken);
        PORT = port;
        this.redirectURI = redirectURI;
        BROWSER_PATH = browserPath;

        //Component Access
        user = new User("https://api.zoom.us/v2", 15, zoacToken);
        chatChannels = new ChatChannels("https://api.zoom.us/v2", 15, zoacToken);
        chatMessages = new ChatMessages("https://api.zoom.us/v2", 15, zoacToken);
        recording = new Recording("https://api.zoom.us/v2", 15, zoacToken);
        report = new Report("https://api.zoom.us/v2", 15, zoacToken);
        meeting = new Meeting("https://api.zoom.us/v2", 15, zoacToken);
        webinar = new Webinar("https://api.zoom.us/v2", 15, zoacToken);
    }

    private void refreshToken() throws OAuthSystemException, OAuthProblemException, IOException {
        TokenHandler handler = new TokenHandler();
        zoacToken = handler.getOAuthToken(clientID, clientSecret, redirectURI);
    }




}
