package seng1.rockpapertoe.Remote;

/**
 * Created by kevin on 18.06.16.
 */
public class SessionResponse {

    int sessionId = -1;
    String userName = "";

    public SessionResponse(int sessionId, String userName) {
        this.sessionId = sessionId;
        this.userName = userName;
    }

    public boolean isValidSession() {
        return sessionId >= 0;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getUserName() {
        return userName;
    }
}
