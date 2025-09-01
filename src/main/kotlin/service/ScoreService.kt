package com.tennis.scoring.service

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.GameState
import kotlin.math.abs

class ScoreService {

    fun pointWonBy(game: Game, playerName: String) {
        if (game.state == GameState.FINISHED) return

        val player = when (playerName) {
            game.player1.name -> game.player1
            game.player2.name -> game.player2
            else -> null
        }

        if (player != null) {
            player.score++
            updateGameState(game)
        } else {
            println("It does not existe a player with that name: $playerName")
        }

    }

    fun getScore(game: Game): String {
        return when (game.state) {
            GameState.LOVE_LOVE -> "Love-Love"
            GameState.REGULAR -> ""
            GameState.DEUCE -> "Deuce"
            GameState.ADVANTAGE_PLAYER1 -> "Advantage ${game.player1.name}"
            GameState.ADVANTAGE_PLAYER2 -> "Advantage ${game.player2.name}"
            GameState.FINISHED -> "Results: Winner is ${winner(game)}"
        }
    }

    private fun updateGameState(game: Game) {
        val p1 = game.player1.score
        val p2 = game.player2.score

        if (p1 == 0 && p2 == 0) {
            game.state = GameState.LOVE_LOVE
            return
        }

        if (p1 == 3 && p2 == 3) {
            game.state = GameState.DEUCE
            return
        }

        if (p1 >= 4 || p2 >= 4) {
            val diff = p1 - p2
            game.state = when {
                diff == 0 -> GameState.DEUCE
                diff == 1 -> GameState.ADVANTAGE_PLAYER1
                diff == -1 -> GameState.ADVANTAGE_PLAYER2
                abs(diff) >= 2 -> GameState.FINISHED
                else -> GameState.REGULAR
            }
        } else {
            game.state = GameState.REGULAR
        }
    }

    private fun winner(game: Game): String {
        return if (game.player1.score > game.player2.score) game.player1.name else game.player2.name
    }



}