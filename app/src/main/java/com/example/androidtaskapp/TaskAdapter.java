package com.example.androidtaskapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>  {

    private ArrayList<ListItems> listItems;

    // Pass in the contact array into the constructor
    TaskAdapter(ArrayList<ListItems> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout
        View listView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_items, parent, false);

        // Return a new holder instance
        return new ViewHolder(listView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItems listItem = listItems.get(position);

        // Set item views based on your views and data model
        holder.itemid.setText(listItem.getId().toString());
        holder.listid.setText(listItem.getListId().toString());
        holder.name.setText(listItem.getName());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView itemid, listid, name;
        public final View mView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mView = itemView;

            itemid = itemView.findViewById(R.id.itemId);
            listid = itemView.findViewById(R.id.listId);
            name = itemView.findViewById(R.id.name);
        }
    }

}
