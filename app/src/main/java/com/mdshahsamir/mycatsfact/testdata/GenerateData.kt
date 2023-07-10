package com.mdshahsamir.mycatsfact.testdata

import com.mdshahsamir.mycatsfact.model.Cat

fun generateCatData() : ArrayList<Cat> {
    val cats = ArrayList<Cat>()

    for (i in 0..20) {
        val cat = Cat()
        cat.name = if (i%2 == 0) "Luna" else "Max"
        cat.fact = "Cats are believed to be the only mammals who don’t taste sweetness."
        cat.imageLink = "https://cataas.com/cat/says/$i"
        cats.add(cat)
    }

    return cats
}