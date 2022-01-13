package com.example.remotelinq;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefStore {

    private static final String file_key = "com.example.remotelinq.PREFERENCE_FILE_KEY";

    private Context context;
    //private int selected_notification;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public PrefStore(Context context) {
        this.context = context;
        //this.selected_notification = sharedPref.getInt("notification", 0);
        sharedPref = context.getSharedPreferences(file_key, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

/*
    public PrefStore(Context context) {
        this.context = context;
    }
*/

    public int getNotification() {
        return sharedPref.getInt("notification", 0);
    }

    public void setNotification(int notification) {
        editor.putInt("notification", notification);
        editor.apply();
    }

    public String getPcgroup() {
        return sharedPref.getString("pcgroup", "default");
    }

    public void setPcgroup(String pcgroup) {
        editor.putString("pcgroup", pcgroup);
        editor.apply();
    }

    public String getUsername() {
        return sharedPref.getString("username", "default");
    }

    public void setUsername(String username) {
        editor.putString("username", username);
        editor.apply();
    }
}
