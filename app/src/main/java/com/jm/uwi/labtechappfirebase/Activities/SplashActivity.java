package com.jm.uwi.labtechappfirebase.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jm.uwi.labtechappfirebase.R;
import com.jm.uwi.labtechappfirebase.Utilities.Utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private boolean isVisible = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Completable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::startNewActivity);
    }

    public void startNewActivity(){
        //RealmUtils.addOrUpdateUser(User(idNumber = "1", password = "1", type = "admin", name = "John Jones"))
        //RealmUtils.addOrUpdateUser(User(idNumber = "2", password = "2", type = "tech", name = "Peter Parker"))
        //RealmUtils.addOrUpdateUser(User(idNumber = "3", password = "3", type = "tech", name = "Aster Brown"))

        boolean logged_in = Utils.isLoggedIn(this);
        if (isVisible) {
            if (logged_in) {
                Intent intent = new Intent(SplashActivity.this, CourseSelectionActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.isVisible = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.isVisible = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isVisible) {
            this.isVisible = true;
            startNewActivity();
        }
    }
}
