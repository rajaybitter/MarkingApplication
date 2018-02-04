package com.jm.uwi.labtechappfirebase.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.jm.uwi.labtechappfirebase.Models.Grade

import com.jm.uwi.labtechappfirebase.R
import com.jm.uwi.labtechappfirebase.Utilities.Constants
import com.jm.uwi.labtechappfirebase.Utilities.RealmUtils
import com.jm.uwi.labtechappfirebase.Utilities.Utils
import kotlinx.android.synthetic.main.activity_grading.*

class GradingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grading)

        var course = Utils.getStringValue(this, Constants.COURSE_KEY)

        if (course == "COMP1126"){
            lab_selector.minValue = 0
            lab_selector.maxValue = 4
            lab_grade_selector.minValue = 0
            lab_grade_selector.maxValue = 5
        } else if (course == "COMP1127"){
            lab_selector.minValue = 0
            lab_selector.maxValue = 5
            lab_grade_selector.minValue = 0
            lab_grade_selector.maxValue = 5
        } else if (course == "COMP1161"){
            lab_selector.minValue = 0
            lab_selector.maxValue = 12
            lab_grade_selector.minValue = 0
            lab_grade_selector.maxValue = 10
        }

        submit_grade.setOnClickListener{
            if(student_id_input.text.isEmpty()){
                Toast.makeText(this, "Please enter student ID number", Toast.LENGTH_LONG).show()
            }else {
                var xlabNumber = lab_selector.value
                var xlabGrade = lab_grade_selector.value
                var xstudentID = student_id_input.text.toString().trim()
                var xmarkerID = Utils.getStringValue(this, Constants.MARKER_ID_KEY)
                var xcourseCode = Utils.getStringValue(this, Constants.COURSE_KEY)
                var xtime = System.currentTimeMillis()
                var xid = xstudentID + ", " + xcourseCode + " - Lab " + xlabNumber

                var grade = Grade(labGrade = xlabGrade,labNumber = xlabNumber,
                        markerID = xmarkerID, studentID = xstudentID,
                        courseCode = xcourseCode, time = xtime, id = xid)
                RealmUtils.addOrUpdateGrade(grade)
                saveGrade(grade)
                //TODO send to Ourvle
                startActivity(Intent(this, CourseSelectionActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }
        }
    }

    fun saveGrade(grade:Grade){
        val database = FirebaseDatabase.getInstance()
        val ref = database.reference

        var course = Utils.getStringValue(this, Constants.COURSE_KEY)
        ref.child("grades").child(course).child(grade.id).setValue(grade)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@GradingActivity, "Grade successfully saved", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@GradingActivity, "Saving grade failed", Toast.LENGTH_SHORT).show()
                        Log.e("Add User", "onComplete: " + task.exception!!.message)
                    }
                }
    }
}
