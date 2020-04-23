package client;

import components.*;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.IOException;
import java.net.URISyntaxException;

public class JWTZoomClient extends ZoomClient{

    private ChatChannels CHAT_CHANNELS;
    private ChatMessages CHAT_MESSAGES;
    private User USER;
    private Recording RECORDING;
    private Report REPORT;
    private Meeting MEETING;
    private Webinar WEBINAR;

    private String API_KEY;
    private String API_SECRET;
    private String JWTZ_TOKEN;

    private JWTZoomClient(int timeout, String apiKey, String apiSecret, String dataType) throws OAuthSystemException, OAuthProblemException, IOException, URISyntaxException {
        super(timeout, apiKey, apiSecret);

        API_KEY = apiKey;
        API_SECRET = apiSecret;

        //Component Access
        USER = new User("https://api.zoom.us/v2", 15, JWTZ_TOKEN);
        CHAT_CHANNELS = new ChatChannels("https://api.zoom.us/v2", 15, JWTZ_TOKEN);
        CHAT_MESSAGES = new ChatMessages("https://api.zoom.us/v2", 15, JWTZ_TOKEN);
        RECORDING = new Recording("https://api.zoom.us/v2", 15, JWTZ_TOKEN);
        REPORT = new Report("https://api.zoom.us/v2", 15, JWTZ_TOKEN);
        MEETING = new Meeting("https://api.zoom.us/v2", 15, JWTZ_TOKEN);
        WEBINAR = new Webinar("https://api.zoom.us/v2", 15, JWTZ_TOKEN);

    }

    //Refresh JWT Token
    private void refreshToken(){
        TokenHandler handler = new TokenHandler();
        JWTZ_TOKEN = handler.generateJWT(this.API_KEY, this.API_SECRET);
    }

    //Api Key
    private String getApiKey(){return API_KEY;}
    private void setApiKey(String key){API_KEY = key;}

    //Api Secret
    private String getApiSecret(){return API_SECRET;}
    private void setApiSecret(String secret){API_SECRET = secret;}

    //Component Getters
    private ChatChannels getChatChannels(){return CHAT_CHANNELS;}
    private ChatMessages getChatMessages(){return CHAT_MESSAGES;}
    private Meeting getMEETING(){return MEETING;}
    private Report getREPORT(){return REPORT;}
    private User getUSER(){return USER;}
    private Webinar getWEBINAR(){return WEBINAR;}
    private Recording getRECORDING(){return RECORDING;}
}
