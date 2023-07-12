package com.mdshahsamir.mycatsfact.model

class Cat: Animal(){
    override fun animalSound(): String = "Meawww"

    override fun animalFavoriteFood(): String = "Fish"

    override fun animalSleepCycle(): String = "79 out of every 104 minutes"
}