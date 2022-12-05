#!/usr/bin/env kscript

import java.io.File
import kotlin.reflect.typeOf

val filepath = if (args.isEmpty()) "test" else args[0]
val rucksacks = File(filepath).readLines()
//A = 65 - 38 ~ a = 97 - 96 to score
println("Answer for task1: ${retrieveAnswerForTask1(rucksacks).sum()}")
println("Answer for task2: ${retrieveAnswerForTask2(rucksacks).sum()}")

fun retrieveAnswerForTask1(rucksacks: List<String>): List<Int> {
    return rucksacks.map { rucksack ->
        calculateScore(findCommonItem(rucksack.splitAtIndex(rucksack.length/2)))
    }
}

fun retrieveAnswerForTask2(rucksacks: List<String>): List<Int> {
    return rucksacks.chunked(3).map { rucksacks ->
        calculateScore(
            findCommonBadge(
                rucksacks
            )
        )
    }
}

fun calculateScore(commonItem: Char): Int {
//    println(commonItem)
//    println(commonItem.code)
    if (commonItem.isUpperCase()) {
//        println(commonItem.code - 26)
        return commonItem.code - 38
    }
    else {
//        println(commonItem.code - 26)
        return commonItem.code - 96
    }
}

//fun findCommonBadge(compartments: List<String>): Char {
//
//}

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