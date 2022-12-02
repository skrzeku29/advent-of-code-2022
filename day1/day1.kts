#!/usr/bin/env kscript
/*The application is written in kotlin and is expected to be executed with kscript. Commands to install required depedencies are.
brew install kotlin
sdk install kscript
first system argument can be supplied that is a filepath string leading to a file containing the data required to solve the problem
to run the application run
kscript day1.kts <optional filepath parameter | default is a file called test in the same directory as this file>
or
./day1.kts*/

import java.io.File

val filepath = if (args.isEmpty()) "test" else args[0]
val caloriesPerElf = retrieveSortedArrayOfCaloriesPerElf(filepath)

println("Answer to task1: ${retrieveTask1Answer(caloriesPerElf)}")
println("Answer to task2: ${retrieveTask2Answer(caloriesPerElf)}")

fun retrieveTask1Answer(caloriesPerElf: List<Int>): Int {
    // Retrieve first item in sorted array of calories per elf
    return caloriesPerElf[0]
}

fun retrieveTask2Answer(caloriesPerElf: List<Int>): Int {
    // Retrieve the sum of the calories carried by 3 elfs that have the most calories in the list
    return caloriesPerElf.take(3).sum()
}

fun retrieveSortedArrayOfCaloriesPerElf(filepath: String): List<Int> {
    return File(filepath).readText()
                .split("\n\n")
                .map { elfCalories -> elfCalories.split("\n").sumOf { calorie -> calorie.toInt() } }
                .sortedDescending()
}
