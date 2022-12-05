#!/usr/bin/env kscript

import java.io.File
import kotlin.reflect.typeOf

val filepath = if (args.isEmpty()) "test" else args[0]
val pairsOfElves = File(filepath).readLines()

println("Answer for task1: ${retrieveAnswerForTask1(pairsOfElves).joinToString().replace(", ", "")}")
//println("Answer for task2: ${retrieveAnswerForTask2(pairsOfElves).sum()}")

fun retrieveAnswerForTask1(cargo: List<String>): List<String> {
    var index = cargo.indexOf("")
//    cargo.forEach { item -> if (item.contains("move")) index = cargo.indexOf(item).toInt() }
    println(index)
    var cargoArr = cargo.subList(0, index)
    val instructions = cargo.subList(index+1, cargo.size)
//    every 4 characters
    var stacks = MutableList(cargoArr[cargoArr.size-1].split("   ").size) { mutableListOf<Char>() }
    println(stacks.toString())
    for (i in 0..cargoArr.size-2) {
        var l = 1
//        for (var j in 1..cargo[i].length-1) {
//            println(cargo[i])
//            j+=4
//        }
        for (stack in stacks) {
//            println(cargo[i])
            if (l < cargo[i].length && cargo[i][l].isLetter()) {
                stack.add(cargo[i][l])
            }
            l += 4
        }
    }
    println(stacks.toString())

    for (instruction in instructions) {
        val chunks = instruction.split(" ")
        val times = chunks[1].toInt()
        for (i in 0..times-1) {
//            println(chunks)
//            println(stacks.get(chunks[5].toInt()-1))
//            println(chunks[3].toInt()-1)
//            println(stacks.get(chunks[3].toInt()-1).first())
            stacks.get(chunks[5].toInt()-1).add(0, stacks.get(chunks[3].toInt()-1)[times-1-i])
            stacks.get(chunks[3].toInt()-1).removeAt(times-1-i)
        }
        println(stacks)
    }

    println(stacks.toString())
    return stacks.map { stack -> stack.first().toString() }


//    1 5 9
//    println(instructions)




//    return pairOfElves.map { pair -> matchWholeSections(pair.split(","))}
}

//fun retrieveAnswerForTask2(pairOfElves: List<String>): List<Int> {
//    return pairOfElves.map { pair -> matchSubsections(pair.split(","))}
//}
