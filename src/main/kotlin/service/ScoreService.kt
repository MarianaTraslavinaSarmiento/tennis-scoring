package com.tennis.scoring.service

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.GameState

class ScoreService {

    fun pointWonBy(game: Game, playerName: String) {
        if (game.state == GameState.FINISHED) return

        val player = when (playerName) {
            game.player1.name -> game.player1
            game.player2.name -> game.player2
            else -> null
        }

    }

}