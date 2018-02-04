package com.jm.uwi.labtechappfirebase.Utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jm.uwi.labtechappfirebase.Models.Grade;
import com.jm.uwi.labtechappfirebase.Models.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by rajay on 10/3/17.
 */

public class RealmUtils {

    public static void addOrUpdateUser(final User user){
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(user);
                }
            });
        }catch (RealmException exception){
            Log.e("Save user", "Shit happened bruv");
        }finally {
            realm.close();
        }
    }

    public static ArrayList<User> getUsers(){
        Realm realm = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            realm = Realm.getDefaultInstance();
            RealmResults<User> userResults = realm.where(User.class).findAll();
            users = ((ArrayList<User>)(realm.copyFromRealm(userResults)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            realm.close();
        }

        return users;
    }

    public static void removeUser(final User user){
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    user.deleteFromRealm();
                }
            });
        }catch (RealmException exception){
            Log.e("Remove User", "Shit happened bruv");
        }finally {
            realm.close();
        }
    }

    public static void loginUser(final Context context, final String idNumber, final String password){
        Realm realm = null;
        User user = null;

        try{
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user;
                    user = realm.where(User.class).equalTo("idNumber", idNumber).findFirst();
                    if(user == null){
                        Utils.saveStringValue((Activity) context, Constants.LOGGED_IN_KEY, "false");
                        Utils.saveStringValue((Activity) context, Constants.ADMIN_STATUS_KEY, "false");
                    }else if(user.getPassword().equals(password)){
                        Utils.saveStringValue((Activity) context, Constants.LOGGED_IN_KEY, "true");
                        if(user.getType().equals("admin")){
                            Utils.saveStringValue((Activity) context, Constants.ADMIN_STATUS_KEY, "true");
                        }else{
                            Utils.saveStringValue((Activity) context, Constants.ADMIN_STATUS_KEY, "false");
                        }
                    }
                }
            });
        }catch (Exception e){
            Log.e("Find User", "Shit happened bruv");
        }
    }

    public static void addOrUpdateGrade(final Grade grade){
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(grade);
                }
            });
        }catch (RealmException exception){
            Log.e("Save user", "Shit happened bruv");
        }finally {
            realm.close();
        }
    }

    public static void removeGrade(final Grade grade){
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    grade.deleteFromRealm();
                }
            });
        }catch (RealmException exception){
            Log.e("Remove Grade", "Shit happened bruv");
        }finally {
            realm.close();
        }
    }

    public static ArrayList<Grade> getGrades(){
        Realm realm = null;
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            realm = Realm.getDefaultInstance();
            RealmResults<Grade> gradeResults = realm.where(Grade.class).findAll();
            grades = ((ArrayList<Grade>)(realm.copyFromRealm(gradeResults)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            realm.close();
        }
        return grades;
    }

    public static void removeUsers(){
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(User.class);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Delete Users Failed", e.getStackTrace().toString());
        } finally {
            realm.close();
        }
    }
}
