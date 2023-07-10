package com.mdshahsamir.mycatsfact.model

import android.graphics.Bitmap

open class Animal(
     var name : String = "",
     var breed : String = "",
     var age : Int = 0,
     var weight : Double = 0.0,
     var image : Bitmap? = null,
     var fact : String = ""
    )