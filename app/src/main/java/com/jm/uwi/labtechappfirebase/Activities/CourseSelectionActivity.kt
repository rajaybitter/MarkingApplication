package com.jm.uwi.labtechappfirebase.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

import kotlinx.android.synthetic.main.activity_course_selection.*;

class CourseSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_selection)

        java_button.setOnClickListener{
            Utils.saveStringValue(this, Constants.COURSE_KEY, "COMP1161")
            startActivity(Intent(this, GradingActivity::class.java))
        }

        python_button.setOnClickListener{
            Utils.saveStringValue(this, Constants.COURSE_KEY, "COMP1126")
            startActivity(Intent(this, GradingActivity::class.java))
        }

        second_python_button.setOnClickListener{
            Utils.saveStringValue(this, Constants.COURSE_KEY, "COMP1127")
            startActivity(Intent(this, GradingActivity::class.java))
        }

        var is_admin = Utils.getStringValue(this, Constants.ADMIN_STATUS_KEY);
        if(is_admin == "true"){
            view_grades_button.visibility = View.VISIBLE
            view_users_button.visibility = View.VISIBLE
        }

        view_grades_button.setOnClickListener{
            startActivity(Intent(this, GradeListActivity::class.java))
        }

        view_users_button.setOnClickListener{
            startActivity(Intent(this, UserListActivity::class.java))
        }

        logout_button.setOnClickListener{
            Utils.saveStringValue(this, Constants.LOGGED_IN_KEY, "false")
            Utils.saveStringValue(this, Constants.ADMIN_STATUS_KEY, "false")
            startActivity(Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
    }
}
