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

    boolean isValidSession() {
        return sessionId > 0;
    }

    int getSessionId() {
        return sessionId;
    }

    String getUserName() {
        return userName;
    }
}
