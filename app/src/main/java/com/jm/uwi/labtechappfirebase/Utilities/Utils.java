package com.jm.uwi.labtechappfirebase.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rajay on 10/1/17.
 */

public class Utils {
    public static void saveStringValue(Activity context, String key, String value){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringValue(Activity context, String key){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString(key, "not set");
    }

    public static boolean isLoggedIn(Activity context){
        String status = getStringValue(context, Constants.LOGGED_IN_KEY);
        if(status.equals("true")){
            return true;
        }
        return false;
    }

    public static boolean isAdmin(Activity context){
        String status = getStringValue(context, Constants.ADMIN_STATUS_KEY);
        if(status.equals("true")){
            return true;
        }
        return false;
    }

    public static void saveLog(String id, String message){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
    }
}
