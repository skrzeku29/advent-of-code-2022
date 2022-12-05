#!/usr/bin/env kscript

import java.io.File
import kotlin.reflect.typeOf

val filepath = if (args.isEmpty()) "test" else args[0]
val pairsOfElves = File(filepath).readLines()

println("Answer for task1: ${retrieveAnswerForTask1(pairsOfElves).sum()}")
println("Answer for task2: ${retrieveAnswerForTask2(pairsOfElves).sum()}")

fun retrieveAnswerForTask1(pairOfElves: List<String>): List<Int> {
    return pairOfElves.map { pair -> matchWholeSections(pair.split(","))}
}

fun retrieveAnswerForTask2(pairOfElves: List<String>): List<Int> {
    return pairOfElves.map { pair -> matchSubsections(pair.split(","))}
}

fun matchWholeSections(sections: List<String>): Int {
    val elf1Sections = convertRangeToListOfSections(sections[0].split("-"))
    val elf2Sections = convertRangeToListOfSections(sections[1].split("-"))
    if (elf1Sections.containsAll(elf2Sections) || elf2Sections.containsAll(elf1Sections))
        return 1
    return 0
}

fun matchSubsections(sections: List<String>): Int {
    val elf1Sections = convertRangeToListOfSections(sections[0].split("-"))
    val elf2Sections = convertRangeToListOfSections(sections[1].split("-"))
    elf1Sections.forEach { value -> if (elf2Sections.contains(value)) return 1 }
    return 0
}

fun toIntArrayRange(first: Int, last: Int): List<Int> = (first..last).toList()

fun convertRangeToListOfSections(range: List<String>): List<Int> {
    return toIntArrayRange(range[0].toInt(), range[1].toInt())
}