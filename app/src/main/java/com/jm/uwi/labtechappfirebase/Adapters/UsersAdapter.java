package com.jm.uwi.labtechappfirebase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jm.uwi.labtechappfirebase.Models.User;
import com.jm.uwi.labtechappfirebase.R;

import java.util.ArrayList;

/**
 * Created by rajay on 10/3/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Holder> {

    private ArrayList<User> users;

    public UsersAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generic_text_view_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show full user info, with option to edit or delete user
            }
        });
        return new Holder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        User currentItem = users.get(position);
        holder.name.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public TextView name;

        public Holder(View itemView) {
            super(itemView);
            this.name = (TextView)itemView.findViewById(R.id.username);
        }
    }


}
