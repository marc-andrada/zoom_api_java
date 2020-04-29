package client;
import components.*;

public class ZoomClient extends ApiClient {


    //Class Components
    private ChatChannels chatChannels;
    private ChatMessages chatMessages;
    private Meeting meeting;
    private Report report;
    private User user;
    private Webinar webinar;
    private Recording recording;

    //Fields
    private String API_KEY;
    private String API_SECRET;
    private String ZOOM_BASE_URI;

    public ZoomClient(int timeout, String apiKey, String apiSecret){

        super("https://api.zoom.us/v2", timeout);
        ZOOM_BASE_URI = "https://api.zoom.us/v2";
        API_KEY = apiKey;
        API_SECRET = apiSecret;

    }

    //sets token to ApiClient object for HttpRequest Authentication
    public void setApiClientToken(String token){
        super.setToken(token);
    }

    //Field Getters
    private String getApiKey(){return API_KEY;}
    private String getApiSecret(){return API_SECRET;}

    //Component Getters
    private ChatChannels getChatChannels(){return chatChannels;}
    private ChatMessages getChatMessages(){return chatMessages;}
    private Meeting getMEETING(){return meeting;}
    private Report getREPORT(){return report;}
    private User getUSER(){return user;}
    private Webinar getWEBINAR(){return webinar;}
    private Recording getRECORDING(){return recording;}

}
