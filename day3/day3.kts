#!/usr/bin/env kscript

import java.io.File

val filepath = if (args.isEmpty()) "test" else args[0]
val rucksacks = File(filepath).readLines()
//A = 65 - 38 ~ a = 97 - 96 to score
val UPPERCASE_SCORE_SUBTRACT = 96
val LOWERCASE_SCORE_SUBTRACT = 38
println("Answer for task1: ${retrieveAnswerForTask1(rucksacks).sum()}")
println("Answer for task2: ${retrieveAnswerForTask2(rucksacks).sum()}")

fun retrieveAnswerForTask1(rucksacks: List<String>): List<Int> {
    return rucksacks.map { rucksack ->
        calculateScore(findCommonItem(rucksack.splitAtIndex(rucksack.length/2)))
    }
}

fun retrieveAnswerForTask2(rucksacks: List<String>): List<Int> {
    return rucksacks.chunked(3).map { rucksacks ->
        calculateScore(findCommonBadge(rucksacks))
    }
}

fun calculateScore(commonItem: Char): Int {
    if (commonItem.isUpperCase()) {
        return commonItem.code - LOWERCASE_SCORE_SUBTRACT
    }
    return commonItem.code - UPPERCASE_SCORE_SUBTRACT
}

fun findCommonItem(compartments: List<String>): Char {
    return compartments.reduce { compartment1, compartment2 -> getCharacter(compartment1, compartment2) }.first()
}

fun findCommonBadge(rucksacks: List<String>): Char {
    return getCharacterBadge(rucksacks[0], rucksacks[1], rucksacks[2]).first()
}

fun getCharacter(compartment1: String, compartment2: String): String {
    compartment1.forEach { compartment ->
        if (compartment2.contains(compartment))
            return compartment2[compartment2.indexOf(compartment)].toString()
    }
    return ""
}

fun getCharacterBadge(rucksack1: String, rucksack2: String, rucksack3: String): String {
    rucksack1.forEach { item ->
        if (rucksack2.contains(item) && rucksack3.contains(item))
            return item.toString()
    }
    return ""
}

fun String.splitAtIndex(index: Int): List<String> = (take(index) to substring(index)).toList()