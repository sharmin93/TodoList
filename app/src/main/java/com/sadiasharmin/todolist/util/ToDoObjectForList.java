package com.sadiasharmin.todolist.util;

public class ToDoObjectForList {
    private int indexOfDatabase;
    private String message;
    private boolean done;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIndexOfDatabase() {
        return indexOfDatabase;
    }

    public void setIndexOfDatabase(int indexOfDatabase) {
        this.indexOfDatabase = indexOfDatabase;
    }
}
