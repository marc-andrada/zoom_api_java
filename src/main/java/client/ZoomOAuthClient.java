package client;

import components.*;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import java.io.IOException;
import java.net.URISyntaxException;

public class ZoomOAuthClient extends ZoomClient{

    //Class Components
    public ChatChannels CHAT_CHANNELS;
    public ChatMessages CHAT_MESSAGES;
    public User USER;
    public Recording RECORDING;
    public Report REPORT;
    public Meeting MEETING;
    public Webinar WEBINAR;


    //Fields
    private String CLIENT_ID;
    private String CLIENT_SECRET;
    private int PORT;
    private String REDIRECT_URI;
    private String BROWSER_PATH;
    private String ZOAC_TOKEN;

    public ZoomOAuthClient(int timeout, String apiKey, String apiSecret, int port, String redirectURI, String browserPath) throws OAuthSystemException, OAuthProblemException, IOException, URISyntaxException {
        super(timeout, apiKey, apiSecret);

        CLIENT_ID = apiKey;
        CLIENT_SECRET = apiSecret;
        TokenHandler tokenHandler = new TokenHandler();
        ZOAC_TOKEN = tokenHandler.getOAuthToken(apiKey, apiSecret, redirectURI);
        super.setApiClientToken(ZOAC_TOKEN);
        PORT = port;
        REDIRECT_URI = redirectURI;
        BROWSER_PATH = browserPath;

        //Component Access
        USER = new User("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
        CHAT_CHANNELS = new ChatChannels("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
        CHAT_MESSAGES = new ChatMessages("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
        RECORDING = new Recording("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
        REPORT = new Report("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
        MEETING = new Meeting("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
        WEBINAR = new Webinar("https://api.zoom.us/v2", 15, ZOAC_TOKEN);
    }

    private void refreshToken() throws OAuthSystemException, OAuthProblemException, IOException {
        TokenHandler handler = new TokenHandler();
        ZOAC_TOKEN = handler.getOAuthToken(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
    }




}
