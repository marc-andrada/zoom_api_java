package components;
import client.*;

public class Base extends ApiClient {

    public Base(String baseURI, int timeout, String token){

        super(baseURI, timeout);

        super.setToken(token);
    }


}
