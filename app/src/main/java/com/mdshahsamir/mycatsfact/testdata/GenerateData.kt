package com.mdshahsamir.mycatsfact.testdata

import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.utils.getRandomCatName

fun generateCatData(size: Int, offset: Int) : ArrayList<Cat> {
    val cats = ArrayList<Cat>()

    for (i in offset until offset + size) {
        val cat = Cat()
        cat.name = getRandomCatName()
        cat.breed = if (i % 10 == 0) "Birman" else "Persian"
        cat.fact = "Cats are believed to be the only mammals who don’t taste sweetness."
        cat.imageLink = "https://cataas.com/cat/says/$i"
        cats.add(cat)
    }

    return cats
}
