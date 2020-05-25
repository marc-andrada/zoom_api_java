package client;

import components.*;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import service.*;
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

    //Downloader Service
    private Downloader downloader;

    //EventFramework
    //private EventFramework eventFramework;

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

        //Component
        user = new User("https://api.zoom.us/v2", 15, zoacToken);
        chatChannels = new ChatChannels("https://api.zoom.us/v2", 15, zoacToken);
        chatMessages = new ChatMessages("https://api.zoom.us/v2", 15, zoacToken);
        recording = new Recording("https://api.zoom.us/v2", 15, zoacToken);
        report = new Report("https://api.zoom.us/v2", 15, zoacToken);
        meeting = new Meeting("https://api.zoom.us/v2", 15, zoacToken);
        webinar = new Webinar("https://api.zoom.us/v2", 15, zoacToken);

        //Service
        downloader = new Downloader(this);
    }

    private void refreshToken() throws OAuthSystemException, OAuthProblemException, IOException {
        TokenHandler handler = new TokenHandler();
        zoacToken = handler.getOAuthToken(clientID, clientSecret, redirectURI);
    }

    public void NewMessageEvent(String channelName, IHandler handler) throws Exception {

        if(downloader == null){
            downloader = new Downloader(this);
        }
        downloader.addEvent("New_Message_Event", new Request(channelName, handler));

    }

    public void UpdatedMessageEvent(String channelName, IHandler handler) throws Exception {

        if(downloader == null){
            downloader = new Downloader(this);
        }
        downloader.addEvent("Updated_Message_Event", new Request(channelName, handler));

    }

    public void NewMemberEvent(String channelName, IHandler handler) throws Exception {

        if(downloader == null){
            downloader = new Downloader(this);
        }
        downloader.addEvent("New_Member_Event", new Request(channelName, handler));

    }

    public void startDownloader(){downloader.run();}

}
