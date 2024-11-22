
# Simple TicTacToe Game
by Tim Tsu

---

## Getting Started

---

## Project Structure
- **Recommended Configuration:**
  - SDK: Corretto-11
  - Language Level: 11 - Local variable
- **Project Setup:**
  - You might need to load Maven and Gradle for the project.
  - Link Gradle to the project via `build.gradle` if it's not already configured.

---

## Build and Run

### Maven
```sh
mvn package
java -jar target/tictactoe-1.0.jar
```

### Gradle
```sh
gradle installApp
./build/install/tictactoe-java/bin/tictactoe-java
```

---

### Prerequisites
- **JUnit:** 4.13.2
- **Mockito-core:** 5.4.0
- **Fest-Assert:** 1.4

---

## Board Details

### TicTacToe Board Layout
```
00, 01, 02
10, 11, 12
20, 21, 22
```
- Players: **X**, **O**

---

# Test Suite

## Method Test: `testEquals_GameBoard`
- **Test Name:** testEquals_GameBoard
- **Objective:** Verify if two `GameBoard` instances are equal by comparing the positions of marks (X or O) on the board.

### Testable Functions
- **Function:** `equals(Object obj)`
  - **Input Parameter:** `Object obj` - The `GameBoard` object to compare with the current board.
  - **Return Type:** `boolean`
    - **`true`:** Boards are identical in every position.
    - **`false`:** At least one position differs.
  - **Exception Behavior:** Invalid object type returns `false`.
  - **Model Input Domain:** Valid `GameBoard` state.

### Interface Test
- Compare `GameBoard` to non-`GameBoard` objects (expect `false`).
- Test with `null` (ensure no errors, result `false`).

### Function Test
- Compare boards with mixed marks (X, O, null).
- Validate results for identical and differing boards.

### Pairwise Coverage
- Test pairs of boards with matching and differing marks to cover all scenarios.

---

## Method Test: `testGetMark_GameBoard`
- **Test Name:** testGetMark_GameBoard
- **Objective:** Verify correct retrieval of marks (X or O) from specified positions.

### Testable Functions
- **Function:** `getMark(row, col)`
  - **Parameters:** `row`, `col` (int)
  - **Return Type:** `Player` (`Player.X`, `Player.O`, or `null`)
  - **Exception Behavior:** Invalid input type returns error.
  - **Model Input Domain:** Valid range for `row` and `col` (0-2).

### Characteristics
1. **Interface-Based:**
   - Ensure parameters are non-`null`.
2. **Functionality-Based:**
   - Test marks on all board positions.

---

## Method Test: `testHashCode_GameBoard`
- **Test Name:** testHashCode_GameBoard
- **Objective:** Compare `hashCode` for boards in different scenarios.

### Testable Functions
- **Function:** `hashCode()`
  - **Input Domain:** Board state, current player, and play sequence.
  - **Return Type:** `int` (hash value).
  - **Exception Behavior:** Unexpected changes in internal data.

### Coverage
- **All Combinations Coverage (ACoC):** Validate hash values for various scenarios:
  1. Empty boards have identical `hashCode`.
  2. Boards with different marks have distinct `hashCode`.

---

## Method Test: `testHasWin_TicTacToeGameState`
- **Test Name:** testHasWin_TicTacToeGameState
- **Objective:** Verify win conditions for horizontal, vertical, and diagonal patterns.

### Testable Functions
- **Function:** `hasWin(player)`
  - **Parameters:** `player` (`Player.X`, `Player.O`, or `null`)
  - **Return Type:** `boolean` (`true` for win, `false` otherwise).
  - **Exception Behavior:** Invalid player type returns `false`.

---

## Method Test: `testToString_GameBoard`
- **Test Name:** testToString_GameBoard
- **Objective:** Validate the string representation of the `GameBoard`.

### Testable Functions
- **Function:** `toString()`
  - **Output:** Board layout as a `String`.

---

## Additional Methods
- **`testPlay_TicTacToeGameState`:** Validate play actions.
- **`testMark_GameBoard`:** Test marking positions.
- **`testMixedMarks_TicTacToeBoardPrinter`:** Validate board printing with mixed marks.
- **`testMarkToString_TicTacToeBoardPrinter`:** Ensure correct conversion of marks to strings.

---
