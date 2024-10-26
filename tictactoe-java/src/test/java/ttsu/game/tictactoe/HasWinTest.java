/* Copyright (C) 2024 Salute Fuangvut - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package ttsu.game.tictactoe;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class HasWinTest {

    // ชื่อ HasWinTest
    // จุดประสงค์เพื่อตรวจสอบการชนะของผู้เล่น (X หรือ O) ในรูปแบบต่างๆ ว่าสามารถชนะได้ในแบบแนวนอน แนวตั้ง และแนวทแยงหรือไม่

    // Identify testable functions => hasWin()
    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters => player
    // Return type => boolean
    // Return value => true (ชนะ), false (ไม่ชนะ)
    // Exceptional behavior => invalid input type that not Player or null
    // Model input domain => player: X,O,null

// มีการเช็คทั้งหมด 1 parameter ว่ามีค่าที่ได้รับมาหรือไม่ => Player player

    // Interface-based characteristic (parameter validation):
    // A: Player type - checks input parameter validity
    //    A1: Player.X (base)
    //    A2: Player.O
    //    A3: null

    // Functionality-based characteristics (game logic):
    // B: Win pattern - checks different winning combinations
    //    B1: Row (base)
    //    B2: Column
    //    B3: Diagonal

    // C: Board state - checks game state outcomes
    //    C1: Complete win (base)
    //    C2: No win

    // Multiple Base Choice Coverage: (แบบOriginal)
    // Base Choices:
    // 1. A1,B1,C1 - X wins with row
    // 2. A1,B2,C1 - X wins with column
    // 3. A1,B3,C1 - X wins with diagonal

    // Variations จาก Characteristic A (Player):
    // 1. A2,B1,C1 - O wins with row
    // 2. A2,B2,C1 - O wins with column
    // 3. A2,B3,C1 - O wins with diagonal
    // 4. A3,B1,C1 - null player with row
    // 5. A3,B2,C1 - null player with column
    // 6. A3,B3,C1 - null player with diagonal

    // Variations จาก Characteristic C (Board state):
    // 7. A1,B1,C2 - X no win with row
    // 8. A1,B2,C2 - X no win with column
    // 9. A1,B3,C2 - X no win with diagonal

    // Multiple Base Choice Coverage: (แบบที่ตัดตัวซ้ำออกไปแล้ว)
    // Base Choices:
    // 1. A1,B1,C1 - X wins with row
    // 2. A1,B2,C1 - X wins with column
    // 3. A1,B3,C1 - X wins with diagonal

    // Variations:
    // 1. A2,B1,C1 - O wins with row (vary interface parameter)
    // 2. A3,B1,C1 - null player (vary interface parameter)
    // 3. A1,B1,C2 - X no win (vary functionality outcome)

    //ที่เราตัดออกเพราะ:
    // 1. บาง cases ซ้ำซ้อนและไม่จำเป็น (เช่น null player กับทุกแบบของ win pattern)
    // 2. บาง cases ไม่สมเหตุสมผล (เช่น invalid inputs กับ occupied position)
    // 3. ต้องการ test cases ที่มีความหมายและครอบคลุมการทำงานหลัก

    private TicTacToeGameState gameState;

    @Before
    public void setup() {
        gameState = new TicTacToeGameState();
    }

    @Test
    public void testXWinsRow() {
        // Test A1,B1,C1: Base test - valid parameter (X) with row win
        gameState.play(0, 0); // X
        gameState.play(0, 1); // X
        gameState.play(0, 2); // X

        assertThat(gameState.hasWin(Player.X)).isTrue();
    }

    @Test
    public void testXWinsColumn() {
        // Test A1,B2,C1: Base test - valid parameter (X) with column win
        gameState.play(0, 0); // X
        gameState.play(1, 0); // X
        gameState.play(2, 0); // X

        assertThat(gameState.hasWin(Player.X)).isTrue();
    }

    @Test
    public void testXWinsDiagonal() {
        // Test A1,B3,C1: Base test - valid parameter (X) with diagonal win
        gameState.play(0, 0); // X
        gameState.play(1, 1); // X
        gameState.play(2, 2); // X

        assertThat(gameState.hasWin(Player.X)).isTrue();
    }

    @Test
    public void testOWinsRow() {
        // Variation A2,B1,C1: Interface variation - different valid player (O)
        gameState.switchPlayer(); // Switch to O
        gameState.play(0, 0); // O
        gameState.play(0, 1); // O
        gameState.play(0, 2); // O

        assertThat(gameState.hasWin(Player.O)).isTrue();
    }

    @Test
    public void testNullPlayer() {
        // Variation A3,B1,C1: Interface variation - invalid parameter (null)
        assertThat(gameState.hasWin(null)).isTrue();
    }

    @Test
    public void testNoWin() {
        // Variation A1,B1,C2: Functionality variation - incomplete win condition
        gameState.play(0, 0); // X
        gameState.play(0, 1); // X

        assertThat(gameState.hasWin(Player.X)).isFalse();
    }
}
