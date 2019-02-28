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
import com.sadiasharmin.todolist.util.GsonUtil;
import com.sadiasharmin.todolist.util.ToDoObject;
import com.sadiasharmin.todolist.util.ToDoObjectForList;

public class ToDoListActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTodoText;
    Button btSave;
    boolean newData = false;
    ToDoObjectForList toDoObjectForList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        Intent intent = getIntent();
        etTodoText = findViewById(R.id.etTodo);
        btSave = findViewById(R.id.btSave);
        String getData = intent.getStringExtra("DATA_TO_EDIT");
        if (getData == null){
            newData = true;
        }else{
            newData =false;
            toDoObjectForList = GsonUtil.getObjectForList(getData);
            etTodoText.setText(toDoObjectForList.getMessage());
            btSave.setText("EDIT");
        }




        btSave.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ToDoListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(newData) {
            String todoText = etTodoText.getText().toString();

            ToDoObject toDoObject = new ToDoObject();
            toDoObject.setMessage(todoText);
            toDoObject.setStatus("A");
            toDoObject.setDone(false);
            boolean isSaved = FireBaseUtil.writeNewDataToDatabase(toDoObject);
            if (isSaved == true) {
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong, Please try again later", Toast.LENGTH_LONG).show();
            }
        }else{
            String todoText = etTodoText.getText().toString();
            toDoObjectForList.setMessage(todoText);
            boolean isEdited = FireBaseUtil.editAData(toDoObjectForList);
            if (isEdited == true) {
                Toast.makeText(getApplicationContext(), "Edited Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong, Please try again later", Toast.LENGTH_LONG).show();
            }
        }

        Intent intent= new Intent(ToDoListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
