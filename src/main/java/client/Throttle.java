package client;

public class Throttle {

    private Boolean wait = false;

    public Throttle(){}

    public boolean check(int code) throws InterruptedException {

        //System.out.println("Code: " + code);
        if(code == 429){
            //System.out.println("Code: " + code + " APPEARED! WAITING 3 SECONDS...");
            Thread.sleep(3000);
            //System.out.println("RESENDING RESPONSE...SLOW DOWN NERD ;]");

            return true;
        }
        return false;
    }



}
