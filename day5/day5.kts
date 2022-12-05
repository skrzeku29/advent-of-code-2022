#!/usr/bin/env kscript

import java.io.File
import kotlin.reflect.typeOf

val filepath = if (args.isEmpty()) "test" else args[0]
val cargoAndInstructions = File(filepath).readLines()

var indexOfCargoAndInstructionSplit = cargoAndInstructions.indexOf("")
val unsanitizedCargoInput = cargoAndInstructions.subList(0, indexOfCargoAndInstructionSplit)
val instructions = cargoAndInstructions.subList(indexOfCargoAndInstructionSplit + 1, cargoAndInstructions.size)

println("Answer for task1: ${retrieveAnswerForTask1(unsanitizedCargoInput, instructions)}")
println("Answer for task2: ${retrieveAnswerForTask2(unsanitizedCargoInput, instructions)}")

fun listOfMutableArrayCharactersToString(strings: List<String>): String {
    return strings.joinToString().replace(", ", "")
}

fun retrieveAnswerForTask1(unsanitizedCargoInput: List<String>, instructions: List<String>): String {
    val stacks = mapCargoToListOfMutableLists(unsanitizedCargoInput)

    instructions.forEach { instruction: String ->
        val chunkedInstruction = instruction.split(" ")
        val timesToExecute = chunkedInstruction[1].toInt()
        val appendIndex = chunkedInstruction[5].toInt() - 1
        val removeIndex = chunkedInstruction[3].toInt() - 1

        for (timesIndex in 0..timesToExecute - 1) {
            stacks.get(appendIndex).add(0, stacks.get(removeIndex).first())
            stacks.get(removeIndex).removeFirst()
        }
    }

    return listOfMutableArrayCharactersToString(stacks.map { stack: List<Char> -> stack.first().toString() })
}

fun retrieveAnswerForTask2(unsanitizedCargoInput: List<String>, instructions: List<String>): String {
    val stacks = mapCargoToListOfMutableLists(unsanitizedCargoInput)

    instructions.forEach { instruction: String ->
        val chunkedInstruction = instruction.split(" ")
        val timesToExecute = chunkedInstruction[1].toInt()
        val appendIndex = chunkedInstruction[5].toInt() - 1
        val removeIndex = chunkedInstruction[3].toInt() - 1

        for (timesIndex in 0..timesToExecute - 1) {
            val iterativeExecution = timesToExecute - 1 - timesIndex
            stacks.get(appendIndex).add(0, stacks.get(removeIndex)[iterativeExecution])
            stacks.get(removeIndex).removeAt(iterativeExecution)
        }
    }

    return listOfMutableArrayCharactersToString(stacks.map { stack: List<Char> -> stack.first().toString() })
}

fun mapCargoToListOfMutableLists(unsortedCargo: List<String>): List<MutableList<Char>> {
    var stacks = MutableList(unsortedCargo[unsortedCargo.size-1].split("   ").size) { mutableListOf<Char>() }
    unsortedCargo.forEach { cargoLine: String ->
        for (cargoLineIndex in 1..cargoLine.length - 1 step 4) {
            if (cargoLineIndex < cargoLine.length && cargoLine[cargoLineIndex].isLetter()) {
                stacks.get((cargoLineIndex-1)/4).add(cargoLine[cargoLineIndex])
            }
        }
    }
    return stacks
}
