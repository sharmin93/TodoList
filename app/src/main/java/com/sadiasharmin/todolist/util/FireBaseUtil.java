package com.sadiasharmin.todolist.util;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FireBaseUtil {
    public static ArrayList<String> dataToWrite;
    public static DatabaseReference myRef;

    public static void initFireBase(Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailId = CommonTask.getPreference(context,"EMAIL_ACCOUNT");
        myRef = database.getReference(emailId).push();
    }

    public static void writeNewDataToDatabase(ToDoObject toDoObject){
        if(dataToWrite== null){
            dataToWrite = new ArrayList<>();
        }
        dataToWrite.add(GsonUtil.getString(toDoObject));
        myRef.setValue(dataToWrite);

    }

}
