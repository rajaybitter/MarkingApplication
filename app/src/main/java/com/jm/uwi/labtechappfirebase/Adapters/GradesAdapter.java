package com.jm.uwi.labtechappfirebase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jm.uwi.labtechappfirebase.Models.Grade;
import com.jm.uwi.labtechappfirebase.R;

import java.util.ArrayList;

/**
 * Created by rajay on 10/5/17.
 */

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.Holder> {

    private ArrayList<Grade> grades;

    public GradesAdapter(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generic_text_view_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show full grade info, with option to delete user
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
        Grade currentItem = grades.get(position);
        holder.name.setText(currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public TextView name;

        public Holder(View itemView) {
            super(itemView);
            this.name = (TextView)itemView.findViewById(R.id.username);
        }
    }
}
