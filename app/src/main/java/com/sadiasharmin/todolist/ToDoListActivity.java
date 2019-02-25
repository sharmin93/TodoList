package com.sadiasharmin.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.sadiasharmin.todolist.util.FireBaseUtil;
import com.sadiasharmin.todolist.util.ToDoObject;

public class ToDoListActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTodoText;
    Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        etTodoText = findViewById(R.id.etTodo);
        btSave = findViewById(R.id.btSave);

        btSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String todoText = etTodoText.getText().toString();

        ToDoObject toDoObject = new ToDoObject();
        toDoObject.setMessage(todoText);
        toDoObject.setStatus("A");
        toDoObject.setDone(false);
        FireBaseUtil.writeNewDataToDatabase(toDoObject);
        Toast.makeText(getApplicationContext(), "Saved Successfully",Toast.LENGTH_LONG).show();

        Intent intent= new Intent(ToDoListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
