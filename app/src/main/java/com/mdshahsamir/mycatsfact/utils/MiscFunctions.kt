package com.mdshahsamir.mycatsfact.utils

fun getDottedText(list: List<String>): String {
    var dottedString = ""
    for ((i, item) in list.withIndex()) {
        dottedString += if (i == list.size - 1) item else ("$item • ")
    }
    return dottedString
}