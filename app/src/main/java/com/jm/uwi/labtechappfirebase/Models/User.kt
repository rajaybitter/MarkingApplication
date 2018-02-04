package com.jm.uwi.labtechappfirebase.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by rajay on 10/3/17.
 */

open class User(
        @PrimaryKey var idNumber:String = "",
        var name:String = "",
        var password:String = "",
        var type:String = ""
) : RealmObject(){}