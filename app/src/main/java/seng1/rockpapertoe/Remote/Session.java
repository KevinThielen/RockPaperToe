package seng1.rockpapertoe.Remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kevin on 18.06.16.
 */
public class Session {

    private SharedPreferences prefs;

    public Session(Context context) {

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserName(String username) {
        prefs.edit().putString("userName", username).apply();
    }

    public String getUserName() {
        String username = prefs.getString("username","");
        return username;
    }

    public void setSessionId(int id) {
        prefs.edit().putInt("sessionId", id).apply();
    }

    public int getSessionId() {
        int sessionId = prefs.getInt("sessionId", -1);
        return sessionId;
    }
}