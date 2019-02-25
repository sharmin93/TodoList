package com.sadiasharmin.todolist.util;

import com.google.gson.Gson;

public class GsonUtil {
    public static String getString(ToDoObject toDoObject){
        Gson gson = new Gson();
        return gson.toJson(toDoObject);
    }

    public static ToDoObject getObject(String json){
        Gson gson = new Gson();
        ToDoObject toDoObject = gson.fromJson(json, ToDoObject.class);
        return toDoObject;
    }
}
