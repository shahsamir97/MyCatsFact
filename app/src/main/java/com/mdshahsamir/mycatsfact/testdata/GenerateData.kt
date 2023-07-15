package com.mdshahsamir.mycatsfact.testdata

import com.mdshahsamir.mycatsfact.model.Cat

fun generateCatData(size: Int, offset: Int) : ArrayList<Cat> {
    val cats = ArrayList<Cat>()

    for (i in offset until offset + size) {
        val cat = Cat()
        cat.name = if (i%2 == 0) "Luna" else "Max"
        cat.fact = "Cats are believed to be the only mammals who don’t taste sweetness."
        cat.imageLink = "https://cataas.com/cat/says/$i"
        cats.add(cat)
    }

    return cats
}
