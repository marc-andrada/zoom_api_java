package client;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

public class TokenHandler {

    private String code;

    private void receiveAuthorization(int port) throws IOException {

        //Open HTTP Port
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Read HttpResponse for Auth Code and Split Code Value
        String data = reader.readLine();
        System.out.println("Data: " + data);
        String[] response = data.split(" ");
        for(String s : response){
            System.out.println(s);
        }
        code = response[1].split("=")[1];

        //Send Dummy Response Message so (as Prof put it) you don't "slam the door on the http port" and get 502 Error
        String dummyResponse = "HTTP/1.1 200 OK\r\n\r\n";
        socket.getOutputStream().write(dummyResponse.getBytes("UTF-8"));
        socket.getOutputStream().flush();

        //Close HTTP Port
        serverSocket.close();
    }

    public String getOAuthToken(String clientID, String clientSecret, String redirectURI) throws IOException, OAuthSystemException, OAuthProblemException{

        //Authorization Request
        OAuthClientRequest authorizationRequest = OAuthClientRequest
                .authorizationLocation("https://zoom.us/oauth/authorize")
                .setResponseType("code")
                .setClientId(clientID)
                .setRedirectURI(redirectURI)
                .buildQueryMessage();


        System.out.println("Authorization request URI: " + authorizationRequest.getLocationUri());

        //Open desktop browser
        URI uri = URI.create(authorizationRequest.getLocationUri());
        Desktop.getDesktop().browse(uri);


        //Get the Auth Code
        receiveAuthorization(4001);

        OAuthClientRequest tokenRequest = OAuthClientRequest
                .tokenLocation("https://zoom.us/oauth/token")
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .setRedirectURI(redirectURI)
                .setCode(code)
                .setParameter("response_type", ResponseType.CODE.toString())
                .buildQueryMessage();

        OAuthClient client = new OAuthClient(new URLConnectionClient());

        //return the token
        return client.accessToken(tokenRequest, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class).getAccessToken();

    }

    public String generateJWT(String apiKey, String apiSecret){

        return "Dummy Token";

    }

}
