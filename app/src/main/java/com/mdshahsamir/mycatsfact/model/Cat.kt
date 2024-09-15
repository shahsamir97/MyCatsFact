package com.mdshahsamir.mycatsfact.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cat(
    var name: String = "",
    @PrimaryKey var fact: String = "",
    @ColumnInfo(name ="image_link") var imageLink: String = "",
) : Animal() {

    override fun uniqueKey(): String {
        return fact
    }

    override fun animalSound(): String = "Meawww"

    override fun animalFavoriteFood(): String = "Fish"

    override fun animalSleepCycle(): String = "79 out of every 104 minutes"
}
