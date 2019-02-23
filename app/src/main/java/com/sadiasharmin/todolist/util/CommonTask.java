package com.sadiasharmin.todolist.util;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonTask {
    public static void savePreference(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_APP_SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getPreference(Context context, String key){
        SharedPreferences sharedPreferences =context.getSharedPreferences("MY_APP_SHARED_PREF",  Context.MODE_PRIVATE );
        String  value = sharedPreferences.getString(key, "");
        return value;
    }
}
