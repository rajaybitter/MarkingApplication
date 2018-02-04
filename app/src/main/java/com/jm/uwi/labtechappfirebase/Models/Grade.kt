package com.jm.uwi.labtechappfirebase.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by rajay on 10/1/17.
 */

open class Grade(
        @PrimaryKey var id:String = "",
        var markerID:String = "",
        var studentID:String = "",
        var courseCode:String = "",
        var labNumber:Int = 0,
        var labGrade:Int = 0,
        var time:Long = 0
) : RealmObject(){}