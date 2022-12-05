#!/usr/bin/env kscript

import java.io.File

val ROCK_SCORE = 1
val PAPER_SCORE = 2
val SCISSORS_SCORE = 3

val LOSE_SCORE = 0
val DRAW_SCORE = 3
val WIN_SCORE = 6

enum class Play(val play: String) {
    ROCK("A"),
    PAPER("B"),
    SCRISSORS("C");

    companion object {
        fun create(play: String): Play {
            return when (play) {
                "A" -> ROCK
                "B" -> PAPER
                "C" -> SCRISSORS
                else -> throw IllegalStateException()
            }
        }
    }
}

enum class Result(val result: String) {
    LOSE("X"),
    DRAW("Y"),
    WIN("Z");

    companion object {
        fun create(result: String): Result {
            return when (result) {
                "X" -> LOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw IllegalStateException()
            }
        }
    }
}

val filepath = if (args.isEmpty()) "test" else args[0]
val games = File(filepath).readLines()

println("Answer to task1: ${calculateTheScoreOfTheMatchByPlay(games).sum()}")
println("Answer to task2: ${calculateTheScoreOfTheMatchByResult(games).sum()}")


fun calculateTheScoreOfTheMatchByPlay(games: List<String>): List<Int> {
   return games.map {
        game -> scoreCalculator(game
                    .split(" ")
                    .reduce { opponent, you -> getGameByPlay(opponent, you) })
    }
}

fun calculateTheScoreOfTheMatchByResult(games: List<String>): List<Int> {
    return games.map {
            game -> scoreCalculator(game
                        .split(" ")
                        .reduce { opponent, you -> getGameByResult(opponent, you) })
    }
}

fun getGameByPlay(opponent: String, you: String): String {
    return "${opponent} ${toPlayConverter(you)}"
}

fun getGameByResult(opponent: String, you: String): String {
    return "${opponent} ${getCorrectPlayOnScenario(opponent, you)}"
}

fun toPlayConverter(play: String): String {
    return when(play) {
        "X" -> Play.ROCK.play
        "Y" -> Play.PAPER.play
        "Z" -> Play.SCRISSORS.play
        else -> play
    }
}

fun scoreCalculator(game: String): Int {
    return when(game) {
        "A A" -> ROCK_SCORE + DRAW_SCORE
        "A B" -> PAPER_SCORE + WIN_SCORE
        "A C" -> SCISSORS_SCORE + LOSE_SCORE
        "B A" -> ROCK_SCORE + LOSE_SCORE
        "B B" -> PAPER_SCORE + DRAW_SCORE
        "B C" -> SCISSORS_SCORE + WIN_SCORE
        "C A" -> ROCK_SCORE + WIN_SCORE
        "C B" -> PAPER_SCORE + LOSE_SCORE
        "C C" -> SCISSORS_SCORE + DRAW_SCORE
        else -> throw IllegalStateException()
    }
}

fun getScoreBasedOnPlay(play: String): Int {
    return when(Play.create(play)) {
        Play.ROCK -> ROCK_SCORE
        Play.PAPER -> PAPER_SCORE
        Play.SCRISSORS -> SCISSORS_SCORE
    }
}

fun getCorrectPlayOnScenario(opponentPlay: String, yourResult: String): String {
    return when(Result.create(yourResult)) {
        Result.LOSE -> playToLose(opponentPlay)
        Result.DRAW -> opponentPlay
        Result.WIN -> playToWin(opponentPlay)
    }
}

fun playToWin(play: String): String {
    return when(Play.create(play)) {
        Play.ROCK -> Play.PAPER.play
        Play.PAPER -> Play.SCRISSORS.play
        Play.SCRISSORS -> Play.ROCK.play
    }
}

fun playToLose(play: String): String {
    return when (Play.create(play)) {
        Play.ROCK -> Play.SCRISSORS.play
        Play.PAPER  -> Play.ROCK.play
        Play.SCRISSORS -> Play.PAPER.play
    }
}
