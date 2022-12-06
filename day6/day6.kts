#!/usr/bin/env kscript

import java.io.File
import kotlin.reflect.typeOf

val filepath = if (args.isEmpty()) "test" else args[0]
val signal = File(filepath).readText()

println("Answer for task1: ${retrieveAnswerForTask1(signal)}")
//println("Answer for task2: ${retrieveAnswerForTask2(unsanitizedCargoInput, instructions)}")

fun retrieveAnswerForTask1(signal: String): Int {
    var marker = 1
    var soFar = mutableListOf<String>()
    for (character in signal) {
//        print(soFar)
//        print(character)
        val str = character.toString()
        if (soFar.contains(str)) {
            val index = soFar.indexOf(str)
            for (i in 0..index) {
                soFar.removeAt(index-i)
            }
        }
        soFar.add(str)
        if (soFar.size == 4) {
            return marker
        }
        marker+=1
    }
    return -1
}