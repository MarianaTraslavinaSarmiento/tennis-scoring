# Tennis Scoring - Home Task

Github link: https://github.com/MarianaTraslavinaSarmiento/tennis-scoring

A Kotlin project to implement tennis scoring logic.  
This project is configured with Gradle and Kotlin DSL

## Project Setup

### Requirements
- **JDK**: Amazon Corretto 21.0.6 (aarch64)
- **Build System**: Gradle (Wrapper)
- **Gradle Version**: 8.8 
- **Gradle DSL**: Kotlin
- **IDE**: IntelliJ IDEA (recommended for best Kotlin support)

### Group and Artifact
- **Group ID**: `com.tennis.scoring`
- **Artifact ID**: `tennis-scoring`


## Features

- Players start at **Love-Love** (0–0).
- Score progression: **Love → 15 → 30 → 40 → Game**.
- Special rules:
  - **Deuce**: when both players reach 40.
  - **Advantage**: the player who wins the point after Deuce.
  - **Back to Deuce**: if the other player wins the next point.
  - **Win**: a player must win **two consecutive points** from Deuce to finish the game.
- Human-readable score format:
    - `"15 - 30"`, `"Deuce"`, `"Player has advantage" `, `"Results: The winner is Player 1 / Player 2"`.
- Ability to **reset** the game to start again.

## Running the Project

The `Main.kt` file is set up to simulate a sample match.


### Steps:

1. **Create players**  
   You have the option to give your players custom names, or you can use the default `"Player 1"` and `"Player 2"`:

   ```kotlin
   val player1 = Player("Player 1")
   val player2 = Player("Player 2")

2. **Start a game**
    
    ```kotlin
   val game = Game(player1, player2)
    val scoreService = ScoreService()

3. **Add points to players**

    Use `pointWonBy()` with the player’s name:

    ```kotlin
   scoreService.pointWonBy(game, "Player 1")
    println(scoreService.getScore(game)) //This prints the current score of the game to the console.
   ```
    If the other player scores:
    ````kotlin
   scoreService.pointWonBy(game, "Player 2")
    println(scoreService.getScore(game))

4. **Reset the game (optional)**

   You can restart the game anytime:
   ````kotlin
   scoreService.resetGame(game)
   println(scoreService.getScore(game))

## Running the Tests

This project uses JUnit for testing.

To run the tests:
1. Go to `src/test/kotlin/com/tennis/scoring/GameTest.kt`
2. Inside the file, you will see the test functions (e.g., `testGameStartsAtLoveAll`).
3. To run all tests in `GameTest`, click the **green play arrow** next to the class name `GameTest`.
4. To run a single test, click the **green play arrow** next to the specific test function.
5. IntelliJ will execute the tests, and the results will be shown in the **Run window** at the bottom of the IDE.  


