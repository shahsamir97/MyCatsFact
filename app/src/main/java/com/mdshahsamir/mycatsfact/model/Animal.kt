package com.mdshahsamir.mycatsfact.model

abstract class Animal() {
    protected var name : String = ""
    protected var breed : String = ""
    protected var age : Int = 0
    protected var weight : Double = 0.0

    abstract fun animalSound()
    abstract fun animalFavoriteFood()
    abstract fun animalSleepCycle()
}