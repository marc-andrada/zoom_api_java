package service;

import client.ZoomOAuthClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Channel {

    private ZoomOAuthClient client;

    public Channel(ZoomOAuthClient client) {

        this.client = client;

    }

    public ArrayList<Member> getMembers(String channelName) throws Exception {

        //Check Range before performing history method

        //Our messages
        ArrayList<Member> memberList = new ArrayList<>();

        //get channel id
        String chanID = getChannelID(channelName);


        //obtain message list per dates in range


        String channelMembersString = client.chatChannels.listMembers(chanID, null);

        JSONObject root = new JSONObject(channelMembersString);
        JSONArray channelMembersJSON = root.getJSONArray("members");

        for (Object c : channelMembersJSON) {
            memberList.add(new Member(c));
        }

        String nextPageToken = root.getString("next_page_token");


        while (!nextPageToken.isEmpty()) {
            channelMembersString = client.chatChannels.listMembers(chanID, nextPageToken);

            root = new JSONObject(channelMembersString);
            channelMembersJSON = root.getJSONArray("members");

            for (Object c : channelMembersJSON) {
                memberList.add(new Member(c));
            }

            nextPageToken = root.getString("next_page_token");
        }

        return memberList;
    }

    public String getChannelID(String channelName) throws Exception {

        String jsonString = client.chatChannels.list();
        JSONObject root = new JSONObject(jsonString);
        JSONArray channels = root.getJSONArray("channels");

        String chanID = "";
        for (Object c : channels) {

            if (((JSONObject) c).getString("name").equals(channelName)) {
                //System.out.println("Found channel " + channelName);
                chanID = ((JSONObject) c).getString("id");
            } else {
                //System.out.println("Wasn't found...");
            }
        }

        return chanID;

    }


}
