package com.tennis.scoring.model

sealed class GameState {
    data object LoveLove : GameState()
    data object Regular : GameState()
    data object Deuce : GameState()
    data class Advantage(val player: Player) : GameState()
    data class Finished(val winner: Player) : GameState()
}
