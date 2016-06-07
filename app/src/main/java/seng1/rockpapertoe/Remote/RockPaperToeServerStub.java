package seng1.rockpapertoe.Remote;


import org.ksoap2.serialization.SoapObject;

public class RockPaperToeServerStub {
    private RemoteManager remote;

    public RockPaperToeServerStub() {
        remote = new RemoteManager();
    }

    /*example usage*/
    boolean login(String name, String pw) {
        //calls method with name "login" with 2 string params on the server
        SoapObject response = remote.executeSoapAction("login", name, pw);

        //if return code in soap response == 0, success
        return 0 == Integer.parseInt(response.getPrimitivePropertySafelyAsString("returnCode"));
    }

}
