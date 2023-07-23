package com.mdshahsamir.mycatsfact

import com.mdshahsamir.mycatsfact.testdata.generateCatData
import com.mdshahsamir.mycatsfact.utils.getDottedText
import com.mdshahsamir.mycatsfact.utils.getRandomCatName
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getCatNameValidator() {
        assertTrue(getRandomCatName().isNotEmpty())
    }

    @Test
    fun getCatDataValidator() {
        assertEquals(generateCatData(10, 0).size, 10)
        assertEquals(generateCatData(0, 0).size, 0)
        assertEquals(generateCatData(0, 10).size, 0)
    }

    @Test
    fun getDottedText_showsAListOfString_withDotsBetweenThem() {
        assertEquals(getDottedText(listOf("Md", "shah", "samir")), "Md • shah • samir")
        assertEquals(getDottedText(listOf("Md")), "Md")
        assertEquals(getDottedText(listOf("")), "")
        assertEquals(getDottedText(listOf()), "")
    }
}
