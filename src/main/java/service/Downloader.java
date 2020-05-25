package service;

import client.ZoomOAuthClient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Downloader {

    private ZoomOAuthClient client;
    private Chat chat;
    private Channel channel;

    //private HashMap<String, IHandler> requests = new HashMap<>();
    private HashMap<String, ArrayList<Request>> requests;

    //change to downloader
    public Downloader(ZoomOAuthClient client) {

        this.client = client;
        chat = new Chat(client);
        channel = new Channel(client);

        requests = new HashMap<>();
        requests.put("New_Message_Event", new ArrayList<>());
        requests.put("Updated_Message_Event", new ArrayList<>());
        requests.put("New_Member_Event", new ArrayList<>());

    }

    public void addEvent(String eventType, Request request){
        requests.get(eventType).add(request);
    }


    public void run(){


        while(true){

            System.out.println("Hello! Monitoring channels...");
            if(requests.containsKey("New_Message_Event")){

                //for each request under this event type do the following:
                for(Request request : requests.get("New_Message_Event")){
                    //System.out.println("Found New_Message_Event");

                    IHandler handler = request.getIHandler();

                    //get current date
                    LocalDate currentDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                    //get startDate as currentDate - 1 day and endDate as currentDate +1 day to establish query range
                    String dateFrom  = currentDate.minusDays(1).toString();
                    String dateTo = currentDate.plusDays(1).toString();

                    List<Message> newMessages = null;
                    try {
                        newMessages = chat.history(request.getChannelName(), dateFrom, dateTo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //assert newMessages != null;
                    if(newMessages != null){
                        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("New Message(s) for channel: " + request.getChannelName());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        for(Message m : newMessages){

                            if(!request.getNewMessageMap().containsKey(m.getMessageID())){
                                request.getNewMessageMap().put(m.getMessageID(), m);
                                handler.handle(m, null);
                            }

                        }
                    }

                }


            }
            if(requests.containsKey("Updated_Message_Event")){
                for(Request request : requests.get("Updated_Message_Event")){
                    //System.out.println("Found Updated_Message_Event");

                    IHandler handler = request.getIHandler();

                    //get current date
                    LocalDate currentDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                    //get startDate as currentDate - 1 day and endDate as currentDate +1 day to establish query range
                    String dateFrom  = currentDate.minusDays(1).toString();
                    String dateTo = currentDate.plusDays(1).toString();

                    List<Message> newMessages = null;
                    try {
                        newMessages = chat.history(request.getChannelName(), dateFrom, dateTo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //assert newMessages != null;
                    if(newMessages != null){
                        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Updated Message(s) for channel: " + request.getChannelName());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        for(Message m : newMessages){
                            if(!request.getUpdatedMessageMap().containsKey(m.getMessageID())){
                                request.getUpdatedMessageMap().put(m.getMessageID(), m);

                            }
                            if(!((request.getUpdatedMessageMap().get(m.getMessageID()).getMessage()).equals(m.getMessage()))){

                                handler.handle(request.getUpdatedMessageMap().get(m.getMessageID()), m);
                                request.getUpdatedMessageMap().replace(m.getMessageID(), m);

                            }
                        }
                    }

                }

            }
            if(requests.containsKey("New_Member_Event")){
                for(Request request : requests.get("New_Member_Event")){
                   // System.out.println("Found New_Member_Event");

                    IHandler handler = request.getIHandler();

                    List<Member> newMembers = null;
                    try {
                        newMembers = channel.getMembers(request.getChannelName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //assert newMembers != null;
                    if(newMembers != null){

                        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("New Member(s) for channel: " + request.getChannelName());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        for(Member m : newMembers){

                            if(!request.getNewMembersMap().containsKey(m.getID())){
                                request.getNewMembersMap().put(m.getID(), m);
                                handler.handle(m, null);

                            }

                        }
                    }

                }

            }
            System.out.println("\nDownloader sleepy...goodbye");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
