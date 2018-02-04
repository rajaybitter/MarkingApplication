package com.jm.uwi.labtechappfirebase.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jm.uwi.labtechappfirebase.Models.User

import com.jm.uwi.labtechappfirebase.R
import com.jm.uwi.labtechappfirebase.Utilities.Constants
import com.jm.uwi.labtechappfirebase.Utilities.RealmUtils
import com.jm.uwi.labtechappfirebase.Utilities.Utils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getUsers()
        login_button.setOnClickListener{
            var id = login_id.text.toString().trim()
            var password = login_password.text.toString().trim()
            var valid = checkInput(id = id, password = password)
            if(valid){
                RealmUtils.loginUser(this, id, password)
                var correct = Utils.getStringValue(this, Constants.LOGGED_IN_KEY)
                if (correct == "true"){
                    startActivity(Intent(this, CourseSelectionActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }else{
                    login_error.text = "ID number or Password incorrect"
                }
            }
        }
    }

    fun checkInput(id:String, password:String):Boolean{
        if(id == ""){
            login_error.text = "Please enter your ID number"
            return false
        }
        if(password == ""){
            login_error.text = "Please enter your password"
            return false
        }
        return true
    }

    fun getUsers() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.reference
        ref.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                RealmUtils.removeUsers()
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(User::class.java)
                    RealmUtils.addOrUpdateUser(user)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.message)
                Toast.makeText(applicationContext, "Failed to get updated list of users", Toast.LENGTH_LONG).show()
            }
        })
    }
}
