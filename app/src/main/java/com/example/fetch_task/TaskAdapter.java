package com.example.fetch_task;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.application.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>  {

    private ArrayList<ListItems> listItems;

    TaskAdapter(ArrayList<ListItems> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_items, parent, false);

        return new ViewHolder(listView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItems listItem = listItems.get(position);

        holder.itemid.setText(listItem.getId().toString());
        holder.listid.setText(listItem.getListId().toString());
        holder.name.setText(listItem.getName());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemid, listid, name;
        public final View mView;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            itemid = itemView.findViewById(R.id.itemId);
            listid = itemView.findViewById(R.id.listId);
            name = itemView.findViewById(R.id.name);
        }
    }

}
