package bots;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.NameValuePair;
import org.ini4j.Ini;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import client.*;

public class TestBot {



    public TestBot(){}

    public static void main(String[] args) throws UnirestException {
        TestBot t = new TestBot();

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

                //INSTANTIATE SOME CHANNELS
                System.out.println("Okay Zoomer! Today we'll be running some zoom methods");
                System.out.println("First I'm going to create a few test channels...");
                Map<String, Object> testChannels = new HashMap<String, Object>();
                ArrayList<NameValuePair> testEmails = new ArrayList<>();
                testChannels.put("members", testEmails);
                testChannels.put("type", 1);
                testChannels.put("name", "channel 1");
                client.chatChannels.create(testChannels);
                //Thread.sleep(2000);
                testChannels.replace("name", "channel 2");
                client.chatChannels.create(testChannels);
                //Thread.sleep(2000);
                testChannels.replace("name", "channel 3");
                client.chatChannels.create(testChannels);
                //Thread.sleep(2000);
                System.out.println("Here are your current channels: ");
                client.chatChannels.list();

                System.out.println("Okay we're all set. Look for the most recent [NEXT] to get your next task");

                //CREATE CHANNEL
                System.out.println("Now let's make our own channel: ");
                System.out.println("[NEXT] Enter a channel name: ");
                Scanner in = new Scanner(System.in);
                String name = in.nextLine();
                Map<String, Object> data = new HashMap<String, Object>();
                ArrayList<NameValuePair> memberEmails = new ArrayList<>();
                data.put("members", memberEmails);
                data.put("name", name);
                data.put("type", 1);
                client.chatChannels.create(data);
                //Thread.sleep(2000);

                //LIST CHANNELS
                System.out.println("Here is a list of channels including yours: ");
                System.out.println(client.chatChannels.list());
                //Thread.sleep(2000);

                //GET, RENAME, AND DELETE CHANNEL
                System.out.println("[NEXT] Enter the id of the channel from the list (above) you want to get. Make sure it's correct!");
                String channelID = in.nextLine();
                System.out.println("Getting id: " + channelID);
                client.chatChannels.get(channelID);
                //Thread.sleep(2000);
                System.out.println("[NEXT] Enter new name for this channel: ");
                String newChanName = in.nextLine();
                client.chatChannels.update(channelID, newChanName);
                //Thread.sleep(2000);
                System.out.println("Now we will delete the channel: ");
                //Thread.sleep(2000);
                client.chatChannels.delete(channelID);
                //Thread.sleep(2000);


                //LIST CHANNEL MEMBERS
                System.out.println("[NEXT] Select an id of the channel whose members you want to see from this list:");
                System.out.println(client.chatChannels.list());
                //Thread.sleep(2000);
                channelID = in.nextLine();
                System.out.println("Here are the members: ");
                client.chatChannels.listMembers(channelID);
                //Thread.sleep(2000);

                //INVITE THEN REMOVE MEMBERS
                System.out.println("[NEXT] Now we'll invite my friend to this channel...");
                String email = "andradam@uci.edu";
                System.out.println("Invite sent to: " + email);
                ArrayList<HashMap<String, String>> emailList = new ArrayList<>();
                HashMap<String, String> emailMap = new HashMap<>();
                emailMap.put("email", email);
                emailList.add(emailMap);
                HashMap<String, Object> memberList = new HashMap<>();
                memberList.put("members", emailList);
                client.chatChannels.inviteMembers(channelID, memberList);
                //Thread.sleep(2000);
                System.out.println("[NEXT] Turns out this member is a spy!! Enter this member's ID (above) so we can remove them!");
                String memberID = in.nextLine();
                client.chatChannels.removeMember(channelID, memberID);
                //Thread.sleep(2000);

                //LEAVE AND JOIN CHANNEL
                System.out.println("[NEXT] Enter channel ID of channel you want to leave, then rejoin: (see channel list below)");
                System.out.println(client.chatChannels.list());
                //Thread.sleep(2000);
                String idLeaveRejoin = in.nextLine();
                System.out.println("Leaving...");
                client.chatChannels.leave(idLeaveRejoin);
                //Thread.sleep(2000);
                System.out.println("Re-joining");
                client.chatChannels.join(idLeaveRejoin);
                //Thread.sleep(2000);

                //LIST CHAT MESSAGES
                System.out.println("[NEXT] Enter channel ID or to contact email of channel you want to list messages of (see channel list below)");
                System.out.println(client.chatChannels.list());
                //Thread.sleep(2000);
                channelID = in.nextLine();
                client.chatMessages.list("me", null, channelID, null, null);
                //Thread.sleep(2000);

                //SEND MESSAGE
                System.out.println("[NEXT] Enter a message to send: ");
                String message = in.nextLine();
                client.chatMessages.send(message, null, channelID);
                //Thread.sleep(2000);

                //UPDATE MESSAGE
                System.out.println("[NEXT] From this list (above) Enter ID of message you want to update: ");
                String messageID = in.nextLine();
                System.out.println("[NEXT] Enter the updated message: ");
                String updatedMessage = in.nextLine();
                System.out.println("Updating...");
                client.chatMessages.update(messageID, updatedMessage, null, channelID);
                //Thread.sleep(2000);

                //DELETE MESSAGE
                System.out.println("Now we'll delete this message...");
                client.chatMessages.delete(messageID, null, channelID);
                //Thread.sleep(2000);

                System.out.println("Well, that was fun! Goodbye.");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    //Open ngrok tunnel ****

    //Make HTTP Call

}
