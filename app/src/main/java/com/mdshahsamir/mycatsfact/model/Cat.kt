package com.mdshahsamir.mycatsfact.model

class Cat(
    var name: String = "",
    var fact: String = "",
    var imageLink: String = "",
) : Animal() {
    override fun animalSound(): String = "Meawww"

    override fun animalFavoriteFood(): String = "Fish"

    override fun animalSleepCycle(): String = "79 out of every 104 minutes"
}