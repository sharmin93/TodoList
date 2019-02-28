package com.sadiasharmin.todolist.util;

import com.google.gson.Gson;

public class GsonUtil {
    public static String getString(Object toDoObject){
        Gson gson = new Gson();
        return gson.toJson(toDoObject);
    }


    public static String getString(ToDoObjectForList toDoObjectForList){
        Gson gson = new Gson();
        return gson.toJson(toDoObjectForList);
    }

    public static ToDoObject getObject(String json){
        Gson gson = new Gson();
        ToDoObject toDoObject = gson.fromJson(json, ToDoObject.class);
        return toDoObject;
    }
    public static ToDoObjectForList getObjectForList(String json){
        Gson gson = new Gson();
        ToDoObjectForList toDoObjectForList = gson.fromJson(json, ToDoObjectForList.class);
        return toDoObjectForList;
    }
}
