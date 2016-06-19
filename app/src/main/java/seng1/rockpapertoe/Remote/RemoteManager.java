package seng1.rockpapertoe.Remote;

import java.util.List;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class RemoteManager {
    private static final String NAMESPACE = "http://Server.RockPaperToe.de/";
    //private static final String URL = "http://192.168.178.84:8080/UnoServer/RockPaperToeServer";
    private static final String URL = "http://10.0.2.2:8080/RockPaperToeServer/RockPaperToeServer";
    private static final String TAG = RockPaperToeServerStub.class.getName();

    public SoapObject executeSoapAction(String methodName, Object... args) {
        Object result = null;

        SoapObject request = new SoapObject(NAMESPACE, methodName);
        for(int i = 0; i<args.length; i++)
            request.addProperty("arg"+i, args[i]);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            List<HeaderProperty> reqHeaders = null;

            @SuppressWarnings({"unused", "unchecked"})
            List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope, reqHeaders);

            result = envelope.getResponse();

            if(result instanceof SoapFault)
                throw( (SoapFault) result);
        }
        catch(SoapFault e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return (SoapObject)result;
    }
}
