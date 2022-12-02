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
println("Answer to task2: ${retrieveTask2Answer(strategyGuide(filepath))}")

fun retrieveTask1Answer(caloriesPerElf: List<Int>): Int {
    // Retrieve first item in sorted array of calories per elf
    return caloriesPerElf.sum()
}

fun retrieveTask2Answer(caloriesPerElf: List<Int>): Int {
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

fun strategyGuide(filepath: String): List<Int> {
    val file = File(filepath).readLines()
//        .map { line -> line
//        .replace("X", "A").replace("Y", "B").replace("Z", "C") }
    val games = ArrayList<Int>()
    file.forEach() {
            game -> games.add(getScoreFromArray(game))
    }


    return games
}
//x lose Y draw z win
fun getCorrectPlayOnScenario(play: String, theirPlay: String): String {
    if (play == "X")
        return playLose(theirPlay)
    if (play == "Z")
        return playWinnable(theirPlay)
    if (play == "Y")
        return theirPlay
    return play
}
//9177
fun playWinnable(play: String): String {
    if (play == "A")
        return "B"
    if (play == "B" )
        return "C"
//    if (play == "C")
    return "A"
}

fun playLose(play: String): String {
    if (play == "A")
        return "C"
    if (play == "B" )
        return "A"
//    if (play == "C")
    return "B"
}

fun getScoreFromArray(game: String): Int {
    val split = game.split(" ")
    val correctPlay = getCorrectPlayOnScenario(split[1], split[0])
    if (split[0] == correctPlay)
        return 3 + shapePlayed(correctPlay)
    if (split[0] == "A" && correctPlay == "B")
        return 6+2
    if (split[0] == "B" && correctPlay == "C")
        return 6+3
    if (split[0] == "C" && correctPlay == "A")
        return 6+1
    return 0 + shapePlayed(correctPlay)
}

fun shapePlayed(shape: String): Int {
    if (shape == "A")
        return 1
    if (shape == "B")
        return 2
    return 3
}
