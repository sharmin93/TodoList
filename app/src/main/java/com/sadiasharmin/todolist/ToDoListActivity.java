package com.sadiasharmin.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sadiasharmin.todolist.util.CommonTask;
import com.sadiasharmin.todolist.util.FireBaseUtil;
import com.sadiasharmin.todolist.util.ToDoObject;

public class ToDoListActivity extends AppCompatActivity implements View.OnClickListener {
    Button btSave;
    EditText etTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        // Write a message to the database
        FirebaseApp.initializeApp(getApplicationContext());
        FireBaseUtil.initFireBase(getApplicationContext());
        btSave = findViewById(R.id.btSave);
        etTodo = findViewById(R.id.etTodo);
        btSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String message = etTodo.getText().toString();
        ToDoObject toDoObject = new ToDoObject();
        toDoObject.setMessage(message);
        toDoObject.setStatus("A");
        toDoObject.setDone(false);
        FireBaseUtil.writeToDatabase(toDoObject);
    }
}
