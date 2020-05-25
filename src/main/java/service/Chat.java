package service;

import client.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Chat {

    private ZoomOAuthClient client;
    long MAX_DAY_RANGE = 5;

    public Chat(ZoomOAuthClient client) {

        this.client = client;

    }

    public void sendMessage(String channelName, String message) throws Exception {

        String chanID = getChannelID(channelName);
        client.chatMessages.send(message, null, chanID);

    }

    public ArrayList<Message> history(String channelName, String dateFrom, String dateTo) throws Exception {

        //Check Range before performing history method
        if (withinDateRangeConstraint(dateFrom, dateTo, MAX_DAY_RANGE)) {
            //Our messages
            ArrayList<Message> messageList = new ArrayList<>();

            //get channel id
            String chanID = getChannelID(channelName);

            //Establish range of dates
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = dateFrom;
            //set up end date
            LocalDate endDate = LocalDate.parse(dateTo, fmt);
            dateTo = endDate.plusDays(1).toString();

            //obtain message list per dates in range
            while (!date.equals(dateTo)) {


                String channelMessagesString = client.chatMessages.list("me", null, chanID, date, null);

                JSONObject root = new JSONObject(channelMessagesString);
                JSONArray channelMessagesJSON = root.getJSONArray("messages");

                for (Object c : channelMessagesJSON) {
                    messageList.add(new Message(c));
                }

                String nextPageToken = root.getString("next_page_token");


                while (!nextPageToken.isEmpty()) {
                    channelMessagesString = client.chatMessages.list("me", null, chanID, date, nextPageToken);

                    root = new JSONObject(channelMessagesString);
                    channelMessagesJSON = root.getJSONArray("messages");

                    for (Object c : channelMessagesJSON) {
                        messageList.add(new Message(c));
                    }

                    nextPageToken = root.getString("next_page_token");
                }
                //}

                //increment date
                LocalDate currDate = LocalDate.parse(date, fmt);
                date = currDate.plusDays(1).toString();

            }

            return messageList;
        } else {
            System.out.println("Dates are not within the maximum range of " + MAX_DAY_RANGE);
            return null;
        }

    }

    public ArrayList<Message> search(String channelName, String fromDate, String toDate, ConditionInterface condition) throws Exception {

        ArrayList<Message> messageListAll = history(channelName, fromDate, toDate);
        ArrayList<Message> matches = new ArrayList<>();

        for (Message m : messageListAll) {

            if (condition.check(m)) {
                matches.add(m);
            }

        }

        return matches;

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

    public Boolean withinDateRangeConstraint(String dateFrom, String dateTo, long range) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateFrom, fmt);
        LocalDate endDate = LocalDate.parse(dateTo, fmt);

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        if (daysBetween <= range) {
            return true;
        } else {
            return false;
        }


    }


}
