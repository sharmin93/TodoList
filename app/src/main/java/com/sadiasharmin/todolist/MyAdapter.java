package com.sadiasharmin.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sadiasharmin.todolist.util.GsonUtil;
import com.sadiasharmin.todolist.util.ToDoObject;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvTodo;
        public CheckBox cbDone;
        public MyViewHolder(View v) {
            super(v);
            tvTodo = v.findViewById(R.id.tvTodo);
            cbDone = v.findViewById(R.id.cbDone);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_todo_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ToDoObject toDoObject = GsonUtil.getObject(mDataset.get(position));
        String message = toDoObject.getMessage();
        boolean checked = toDoObject.isDone();
        holder.tvTodo.setText(message);
        holder.cbDone.setChecked(checked);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}