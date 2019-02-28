package com.sadiasharmin.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sadiasharmin.todolist.util.FireBaseUtil;
import com.sadiasharmin.todolist.util.GsonUtil;
import com.sadiasharmin.todolist.util.ToDoObject;
import com.sadiasharmin.todolist.util.ToDoObjectForList;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<ToDoObjectForList> mDataset;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvTodo;
        public CheckBox cbDone;
        public View myView;

        public MyViewHolder(View v) {
            super(v);
            myView = v;
            tvTodo = v.findViewById(R.id.tvTodo);
            cbDone = v.findViewById(R.id.cbDone);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> dataFromFirebase, Context c) {
        this.context = c;
        mDataset = new ArrayList<>();
        for (int i = 0; i < dataFromFirebase.size(); i++) {

            ToDoObject toDoObject = GsonUtil.getObject(dataFromFirebase.get(i));
            if (toDoObject.getStatus().equals("A")) {
                ToDoObjectForList toDoObjectForList = new ToDoObjectForList();
                toDoObjectForList.setDone(toDoObject.isDone());
                toDoObjectForList.setMessage(toDoObject.getMessage());
                toDoObjectForList.setStatus(toDoObject.getStatus());
                toDoObjectForList.setIndexOfDatabase(i);
                mDataset.add(toDoObjectForList);
            }

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_todo_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ToDoObjectForList toDoObjectForList = mDataset.get(position);
        String message = toDoObjectForList.getMessage();
        boolean checked = toDoObjectForList.isDone();

        if(checked) {
            holder.cbDone.setChecked(checked);
            holder.tvTodo.setText(message);
            holder.tvTodo.setPaintFlags(holder.tvTodo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.cbDone.setChecked(checked);
            holder.tvTodo.setText(message);
            holder.tvTodo.setPaintFlags(0);
        }

        holder.tvTodo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context).setTitle("Delete or Edit entry").setMessage("What do you want to do?")
                        .setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, ToDoListActivity.class);
                                intent.putExtra("DATA_TO_EDIT", GsonUtil.getString(toDoObjectForList));
                                context.startActivity(intent);
                            }
                        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FireBaseUtil.deleteAData(toDoObjectForList.getIndexOfDatabase());
                    }
                })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return false;
            }
        });

        holder.cbDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                FireBaseUtil.doneAData(toDoObjectForList.getIndexOfDatabase(), checkBox.isChecked());
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}