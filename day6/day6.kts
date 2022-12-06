#!/usr/bin/env kscript

import java.io.File

val filepath = if (args.isEmpty()) "test" else args[0]
val signal = File(filepath).readText()
val TASK1_LIMIT = 4
val TASK2_LIMIT = 14

println("Answer for task1: ${getUniqueCharacterMarkerByLimit(signal, TASK1_LIMIT)}")
println("Answer for task2: ${getUniqueCharacterMarkerByLimit(signal, TASK2_LIMIT)}")

fun getUniqueCharacterMarkerByLimit(signal: String, uniqueCharacterMakerLimit: Int): Int {
    var marker = 1
    var uniqueCharacters = mutableListOf<String>()
    for (character in signal) {
        if (uniqueCharacters.contains(character.toString())) {
            val index = uniqueCharacters.indexOf(character.toString())
            for (i in 0..index) {
                uniqueCharacters.removeAt(index-i)
            }
        }
        uniqueCharacters.add(character.toString())
        if (uniqueCharacters.size == uniqueCharacterMakerLimit) {
            return marker
        }
        marker+=1
    }
    return -1
}