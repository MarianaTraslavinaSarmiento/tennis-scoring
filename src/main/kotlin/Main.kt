package com.tennis.scoring

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.Player
import com.tennis.scoring.service.ScoreService

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val player1 = Player("Player 1")
    val player2 = Player("Player 2")
    val game = Game(player1, player2)
    val scoreService = ScoreService()

    println("Game started: ${scoreService.getScore(game)}") //Love all


    scoreService.pointWonBy(game, "Player 1")
    println(scoreService.getScore(game))

    scoreService.pointWonBy(game, "Player 2")
    println(scoreService.getScore(game))

    scoreService.pointWonBy(game, "Player 2")
    println(scoreService.getScore(game))

    scoreService.pointWonBy(game, "Player 1")
    println(scoreService.getScore(game))

    println("Reseting the game...")
    scoreService.resetGame(game)
    println(scoreService.getScore(game))

}