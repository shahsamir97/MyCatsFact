package com.mdshahsamir.mycatsfact.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Animal(
     var breed : String = "Persian",
     var age : Int = 2,
     var weight : Double = 4.0,
    ) : Parcelable, AnimalActivities {
     override fun animalSound(): String = "sound"

     override fun animalFavoriteFood(): String = "food"

     override fun animalSleepCycle(): String = "sleep"
}