package com.sadiasharmin.todolist.util;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FireBaseUtil {
    public static ArrayList<String> dataToWrite;
    public static boolean updatedFromDatabase =false;
    public static DatabaseReference myRef;

    public static void initFireBase(Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailId = CommonTask.getPreference(context, "EMAIL_ACCOUNT");
        myRef = database.getReference(emailId);
    }

    public static boolean writeNewDataToDatabase(ToDoObject toDoObject) {
        if(updatedFromDatabase == false){
            return false;
        }else {
            if (dataToWrite == null) {
                dataToWrite = new ArrayList<>();
            }
            dataToWrite.add(GsonUtil.getString(toDoObject));
            myRef.child("data").setValue(dataToWrite);
            return true;
        }

    }

    public static void doneAData(int position, boolean done) {
        if (dataToWrite == null) {
            dataToWrite = new ArrayList<>();
        }
        String dataToDone = dataToWrite.get(position);
        ToDoObject toDoObject = GsonUtil.getObject(dataToDone);
        toDoObject.setDone(done);
        String newDataToSave = GsonUtil.getString(toDoObject);
        dataToWrite.set(position, newDataToSave);
        myRef.child("data").setValue(dataToWrite);
    }

    public static void deleteAData(int position) {
        if (dataToWrite == null) {
            dataToWrite = new ArrayList<>();
        }
        String dataToDelete = dataToWrite.get(position);
        ToDoObject toDoObject = GsonUtil.getObject(dataToDelete);
        toDoObject.setStatus("D");
        String newDataToSave = GsonUtil.getString(toDoObject);
        dataToWrite.set(position, newDataToSave);
        myRef.child("data").setValue(dataToWrite);
    }

    public static void deleteAllCompleted() {
        if( dataToWrite == null){
            return;
        }
        for (int i = 0; i < dataToWrite.size(); i++) {
            String dataString = dataToWrite.get(i);
            ToDoObject toDoObject = GsonUtil.getObject(dataString);
            if(toDoObject.isDone() ==true){
                toDoObject.setStatus("D");
            }
            String updatedData= GsonUtil.getString(toDoObject);
            dataToWrite.set(i,updatedData);
        }

        myRef.child("data").setValue(dataToWrite);
    }
}
