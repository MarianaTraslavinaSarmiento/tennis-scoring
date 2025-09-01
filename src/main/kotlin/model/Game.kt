package com.tennis.scoring.model

data class Game(
    val player1: Player,
    val player2: Player,
    var state: GameState = GameState.LOVE_LOVE
)
