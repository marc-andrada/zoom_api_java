package client;

public class Throttle {

    private Boolean wait = false;

    public Throttle(){}

    public boolean check(int code) throws InterruptedException {

        //System.out.println("Code: " + code);
        if(code == 429){
            System.out.println("Code: " + code + " APPEARED! WAITING 2.5 SECONDS...");
            Thread.sleep(2500);
            System.out.println("RESENDING RESPONSE...SLOW DOWN NERD ;]");

            return true;
        }
        return false;
    }



}
