package com.sadiasharmin.todolist.util;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseUtil {
    public static DatabaseReference myRef;

    public static void initFireBase(Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailId = CommonTask.getPreference(context,"EMAIL_ACCOUNT");
        myRef = database.getReference(emailId).push();
    }

    public static void writeToDatabase(ToDoObject toDoObject){
        myRef.setValue(toDoObject);

    }

}
