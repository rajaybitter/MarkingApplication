package com.jm.uwi.labtechappfirebase.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jm.uwi.labtechappfirebase.Adapters.UsersAdapter;
import com.jm.uwi.labtechappfirebase.Models.User;
import com.jm.uwi.labtechappfirebase.R;
import com.jm.uwi.labtechappfirebase.Utilities.RealmUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserListActivity extends AppCompatActivity {

    private ArrayList<User> users = new ArrayList<>();
    private RecyclerView usersRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        usersRecyclerView = (RecyclerView)findViewById(R.id.user_list);
        layoutManager = new LinearLayoutManager(this);
        usersRecyclerView.setLayoutManager(layoutManager);

        users = RealmUtils.getUsers();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result;
            }
        });

        adapter = new UsersAdapter(users);
        usersRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_user_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(UserListActivity.this);
                LayoutInflater inflater = UserListActivity.this.getLayoutInflater();
                View mView = inflater.inflate(R.layout.add_user_layout, null);

                alert.setTitle("Enter new user info");
                alert.setView(mView);
                final TextInputEditText nameField = (TextInputEditText)mView.findViewById(R.id.full_name_input);
                final TextInputEditText idField = (TextInputEditText)mView.findViewById(R.id.id_input);
                final TextInputEditText passwordField = (TextInputEditText)mView.findViewById(R.id.password_input);
                final RadioButton adminField = (RadioButton) mView.findViewById(R.id.radio_admin);
                final RadioButton techField = (RadioButton) mView.findViewById(R.id.radio_tech);
                alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameField.getText().toString().trim();
                        String id = idField.getText().toString().trim();
                        String password = passwordField.getText().toString().trim();
                        String type = "";
                        if(adminField.isChecked()){
                            type = "admin";
                        }else if(techField.isChecked()){
                            type = "tech";
                        }

                        User user = new User(id, name, password,type);
                        RealmUtils.addOrUpdateUser(user);
                        users.add(user);
                        adapter.notifyDataSetChanged();
                        saveUser(user);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
            }
        });
    }

    public void saveUser(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child("users").child(user.getIdNumber()).setValue(user)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(UserListActivity.this, "User successfully saved", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(UserListActivity.this, "Saving user failed", Toast.LENGTH_SHORT).show();
                        Log.e("Add User", "onComplete: " + task.getException().getMessage());
                    }
                }
            });
    }
}
