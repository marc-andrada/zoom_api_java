package bots;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import client.*;
import service.*;

public class TestBot2 {

    public TestBot2(){}

    public static void main(String[] args) throws UnirestException {
        //.ini parser
        Ini ini;

        {
            try {

                //Get Oauth, ClientID, Client Secret, Port from ini file
                ini = new Ini(new File("src/main/java/bots/bots.ini"));
                System.out.println(ini.get("OAuth", "client_id"));
                String cid = ini.get("OAuth", "client_id");
                String csecret = ini.get("OAuth", "client_secret");
                int port = Integer.parseInt(ini.get("OAuth", "port"));
                String browserPath = ini.get("OAuth", "browser_path");
                //open ngrok tunnel return url

                //NgrokTunnel tunnel = new NgrokTunnel(Integer.parseInt(ini.get("OAuth", "port")));
                NgrokTunnel tunnel = new NgrokTunnel("http://127.0.0.1:4040", 4001);
                System.out.println("ngrok url: " + tunnel.url());
                String redirectURL = tunnel.url();

                //Make a call
                ZoomOAuthClient client = new ZoomOAuthClient(15, cid, csecret, port, redirectURL, browserPath);

                //Service
                Chat chat = new Chat(client);

                //Bot Functionality
                Scanner in = new Scanner(System.in);

                while(true){
                    System.out.println("Enter channel name or 'exit' to stop bot: ");
                    String chanName = in.nextLine();
                    if(chanName.equals("exit")){
                        break;
                    }
                    else{
                        //Send message to particular channel
                        System.out.println("Enter message to send to channel, " + chanName);
                        String input = in.nextLine();
                        System.out.println("Sending '" + input + "' to channel: " + chanName);
                        chat.sendMessage(chanName, input);
                        //History
                        System.out.println("Getting this channel's history...");
                        chat.history(chanName, "2020-04-26", "2020-04-29");
                        //Search
                        //Adjust the sender email to get the desired sender you are looking for
                        //Or change .getSender() method to .getMessage() and enter desired message you are looking for
                        chat.search(chanName, "2020-04-26", "2020-04-29",  (Message message) -> (message.getSender().equals("zoombotma@gmail.com")));
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
