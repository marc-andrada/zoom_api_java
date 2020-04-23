package client;
import components.*;

public class ZoomClient extends ApiClient {


    //Class Components
    private ChatChannels CHAT_CHANNELS;
    private ChatMessages CHAT_MESSAGES;
    private Meeting MEETING;
    private Report REPORT;
    private User USER;
    private Webinar WEBINAR;
    private Recording RECORDING;

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
    private ChatChannels getChatChannels(){return CHAT_CHANNELS;}
    private ChatMessages getChatMessages(){return CHAT_MESSAGES;}
    private Meeting getMEETING(){return MEETING;}
    private Report getREPORT(){return REPORT;}
    private User getUSER(){return USER;}
    private Webinar getWEBINAR(){return WEBINAR;}
    private Recording getRECORDING(){return RECORDING;}

}
