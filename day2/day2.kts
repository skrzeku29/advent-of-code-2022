#!/usr/bin/env kscript
/*The application is written in kotlin and is expected to be executed with kscript. Commands to install required depedencies are.
brew install kotlin
brew install kscript
first system argument can be supplied that is a filepath string leading to a file containing the data required to solve the problem
to run the application run
kscript day1.kts <optional filepath parameter | default is a file called test in the same directory as this file>
or
./day1.kts*/

import java.io.File
//xyz me
//abc opponent
// a rock x
//b paper y
//c  scissors z
//0 if you lost, 3 if the round was a draw, and 6 if you won


val filepath = if (args.isEmpty()) "test" else args[0]
val caloriesPerElf = retrieveSortedArrayOfCaloriesPerElf(filepath)

println("Answer to task1: ${retrieveTask1Answer(caloriesPerElf)}")

fun retrieveTask1Answer(caloriesPerElf: List<Int>): Int {
    // Retrieve first item in sorted array of calories per elf
    return caloriesPerElf.sum()
}

fun retrieveSortedArrayOfCaloriesPerElf(filepath: String): List<Int> {
    val file = File(filepath).readLines().map { line -> line.replace("X", "A").replace("Y", "B").replace("Z", "C") }
    val games = ArrayList<Int>()
    file.forEach() {
        game -> games.add(getScoreFromArray(game))
    }


    return games
}

fun getScoreFromArray(game: String): Int {
    val split = game.split(" ")
    if (split[0] == split[1])
        return 3 + shapePlayed(split[1])
    if (split[0] == "A" && split[1] == "B")
        return 6+2
    if (split[0] == "B" && split[1] == "C")
        return 6+3
    if (split[0] == "C" && split[1] == "A")
        return 6+1
    return 0 + shapePlayed(split[1])
}

fun shapePlayed(shape: String): Int {
    if (shape == "A")
        return 1
    if (shape == "B")
        return 2
    return 3
}
