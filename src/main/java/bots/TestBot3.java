package bots;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import client.*;
import service.*;

public class TestBot3 {

    public TestBot3(){}

    public static void main(String[] args) throws UnirestException {
        //.ini parser
        Ini ini;

        {
            try {

                //Get Oauth, ClientID, Client Secret, Port from ini file
                ini = new Ini(new File("src/main/java/bots/bots.ini"));
                //System.out.println(ini.get("OAuth", "client_id"));
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

                //Bot Functionality
                Scanner in = new Scanner(System.in);

                //Start Bot

                System.out.println("\nHello, for this demo, TestBot3 will take 2 different channel names (make sure they are the correctly entered) and then monitor" +
                        "the channels for new messages, updated messages, and newly joined members. \nPlease follow the instructions below to continue:\n");


                //Get two channel names
                System.out.println("Enter first channel name:");
                String chan1 = in.nextLine();
                System.out.println("Enter second channel name:");
                String chan2 = in.nextLine();

                //Service

                //listeners for first channel for new message, updated message, new member
                client.NewMessageEvent(chan1, (Object o1, Object o2) ->
                        System.out.println("______________________\n[New Message Received]"
                                + "\nChannel: " + chan1
                                + ((Message) o1).printMessage()));

                client.UpdatedMessageEvent(chan1, (Object o1, Object o2) ->
                        System.out.println("______________________\n[Updated Message Transaction]"
                                + "\nChannel: " + chan1
                                + "\nMessage ID: " + ((Message) o1).getMessageID()
                                //o1 will be the old message and o2 will be the new message
                                + "\n" + ((Message) o1).getMessage() + " -> " + ((Message) o2).getMessage()));

                client.NewMemberEvent(chan1, (Object o1, Object o2) ->
                        System.out.println("______________________\n[New Member Joined]"
                                + "\nChannel: " + chan1
                                + ((Member) o1).printMember()));

                //listeners for second channel for new message, updated message, new member

                client.NewMessageEvent(chan2, (Object o1, Object o2) ->
                        System.out.println("______________________\n[New Message Received]"
                                + "\nChannel: " + chan2
                                + ((Message) o1).printMessage()));

                client.UpdatedMessageEvent(chan2, (Object o1, Object o2) ->
                        System.out.println("______________________\n[Updated Message Transaction]"
                                + "\nChannel: " + chan2
                                + "\nMessage ID: " + ((Message) o1).getMessageID()
                                //o1 will be the old message and o2 will be the new message
                                + "\n" + ((Message) o1).getMessage() + " -> " + ((Message) o2).getMessage()));

                client.NewMemberEvent(chan2, (Object o1, Object o2) ->
                        System.out.println("______________________\n[New Member Joined]"
                                + "\nChannel: " + chan2
                                + ((Member) o1).printMember()));

                //start
                client.startDownloader();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
