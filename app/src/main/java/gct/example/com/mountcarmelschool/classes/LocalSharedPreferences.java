package gct.example.com.mountcarmelschool.classes;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GCT on 11/29/2017.
 */

public class LocalSharedPreferences {

    private static final String strSharedPrefName = "AppPrefrence";

    public static void saveEventId(Context context, String gallery_event_id) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("gallery_event_id", gallery_event_id);
        editor.commit();
    }

    public static String getEventId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        String gallery_event_id = pref.getString("gallery_event_id", "");
        return gallery_event_id;
    }

    public static void saveUserName(Context context, String name) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name);
        editor.commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        String gallery_event_id = pref.getString("name", "");
        return gallery_event_id;
    }

    public static void saveUserEmail(Context context, String email) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", email);
        editor.commit();
    }

    public static String getUserEmail(Context context) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        String gallery_event_id = pref.getString("email", "");
        return gallery_event_id;
    }

    public static String getWishMessage(Context context){
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        String wish = pref.getString("message", "");
        return wish;
    }

    public static void saveWishMessage(Context context, String chr) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("message", chr);
        editor.commit();
    }





    public static String getReplyMessage(Context context){

        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        String reply = pref.getString("reply", "");
        return reply;
    }

    public static void saveReplyMessage(Context context, String chr) {
        SharedPreferences pref = context.getSharedPreferences(strSharedPrefName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("reply", chr);
        editor.commit();
    }

}
//adapter kha h gallery adapter k name se hok