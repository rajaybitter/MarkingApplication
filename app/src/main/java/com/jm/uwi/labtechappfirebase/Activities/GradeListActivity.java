package com.jm.uwi.labtechappfirebase.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jm.uwi.labtechappfirebase.Adapters.GradesAdapter;
import com.jm.uwi.labtechappfirebase.Adapters.UsersAdapter;
import com.jm.uwi.labtechappfirebase.Models.Grade;
import com.jm.uwi.labtechappfirebase.Models.User;
import com.jm.uwi.labtechappfirebase.R;
import com.jm.uwi.labtechappfirebase.Utilities.RealmUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by rajay on 10/5/17.
 */

public class GradeListActivity  extends AppCompatActivity {
    private ArrayList<Grade> grades = new ArrayList<>();
    private RecyclerView gradesRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_user_button);
        fab.setVisibility(View.GONE);

        grades = RealmUtils.getGrades();
        gradesRecyclerView = (RecyclerView)findViewById(R.id.user_list);
        layoutManager = new LinearLayoutManager(this);
        gradesRecyclerView.setLayoutManager(layoutManager);

        Collections.sort(grades, new Comparator<Grade>() {
            @Override
            public int compare(Grade o1, Grade o2) {
                int result = o1.getId().compareTo(o2.getId());
                return result;
            }
        });

        adapter = new GradesAdapter(grades);
        gradesRecyclerView.setAdapter(adapter);
    }
}
