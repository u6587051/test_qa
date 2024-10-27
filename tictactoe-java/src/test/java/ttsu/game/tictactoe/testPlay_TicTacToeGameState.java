/* Copyright (C) 2024 Salute Fuangvut - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package ttsu.game.tictactoe;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class testPlay_TicTacToeGameState {

    // ชื่อ PlayTest
    // จุดประสงค์เพื่อตรวจสอบการเล่นในตำแหน่งต่างๆว่าสามารถเล่นได้หรือไม่ ทั้งในด้านความถูกต้องของตำแหน่งและสถานะของตำแหน่งนั้นๆ

    // Identify testable functions => play()
    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters => row, col
    // Return type => boolean
    // Return value => true (เล่นสำเร็จ), false (เล่นไม่สำเร็จ)
    // Exceptional behavior => invalid input type that not int or out of bounds (0-2)
    // Model input domain => row: 0-2, col: 0-2

    // มีการเช็คทั้งหมด 2 parameters ว่ามีค่าที่ได้รับมาหรือไม่ => int row, int col

    // Interface-based characteristics (parameter validation):
    // A: Row value - checks row parameter bounds
    //    A1: Valid (0-2) (base)
    //    A2: Invalid (<0)
    //    A3: Invalid (>2)

    // B: Column value - checks column parameter bounds
    //    B1: Valid (0-2) (base)
    //    B2: Invalid (<0)
    //    B3: Invalid (>2)

    // Functionality-based characteristic (game logic):
    // C: Position state - checks game board position status
    //    C1: Empty (base)
    //    C2: Occupied

    // Multiple Base Choice Coverage (แบบOriginal):
    // Base Choice:
    // 1. A1,B1,C1 - Valid, Valid, Empty
    // 2. A1,B1,C2 - Valid, Valid, Occupied
    // 3. A2,B1,C1 - Invalid(<0), Valid, Empty

    // Variations:
    // 1. A3,B1,C1 - Invalid(>2), Valid, Empty
    // 2. A3,B1,C2 - Invalid(>2), Valid, Occ
    // 3. A1,B2,C1 - Valid, Invalid(<0), Empty
    // 4. A1,B2,C2 - Valid, Invalid(<0), Occ
    // 5. A1,B3,C1 - Valid, Invalid(>2), Empty
    // 6. A1,B3,C2 - Valid, Invalid(>2), Occ

    // Multiple Base Choice Coverage (แบบที่ตัดตัวซ้ำออกไปแล้ว):
    // Base Choice:
    // 1. A1,B1,C1 - Valid, Valid, Empty
    // 2. A1,B1,C2 - Valid, Valid, Occupied
    // 3. A2,B1,C1 - Invalid(<0), Valid, Empty

    // Variations:
    // 1. A3,B1,C1 - Invalid(>2), Valid, Empty
    // 2. A1,B2,C1 - Valid, Invalid(<0), Empty
    // 3. A1,B3,C1 - Valid, Invalid(>2), Empty

    //ที่เราตัดออกเพราะ:
    // 1. บาง cases ซ้ำซ้อนและไม่จำเป็น (เช่น null player กับทุกแบบของ win pattern)
    // 2. บาง cases ไม่สมเหตุสมผล (เช่น invalid inputs กับ occupied position)
    // 3. ต้องการ test cases ที่มีความหมายและครอบคลุมการทำงานหลัก

    private TicTacToeGameState gameState;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        gameState = new TicTacToeGameState();
    }

    @Test
    public void testValidEmptyPosition() {
        // Test A1,B1,C1: Base test - all valid parameters with empty position
        boolean result = gameState.play(1, 1);
        assertThat(result).isTrue();
        assertThat(gameState.getGameBoard().getMark(1, 1)).isEqualTo(Player.X);
    }

    @Test
    public void testValidOccupiedPosition() {
        // Test A1,B1,C2: Functionality variation - valid parameters but occupied position
        gameState.play(1, 1);  // First move
        boolean result = gameState.play(1, 1);  // Same position
        assertThat(result).isFalse();
    }

    @Test
    public void testInvalidRowNegative() {
        // Test A2,B1,C1: Interface variation - invalid row parameter (<0)
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("is off the board");
        gameState.play(-1, 1);
    }

    @Test
    public void testInvalidRowLarge() {
        // Variation A3,B1,C1: Interface variation - different invalid row (>2)
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("is off the board");
        gameState.play(3, 1);
    }

    @Test
    public void testInvalidColumnNegative() {
        // Variation A1,B2,C1: Interface variation - invalid column parameter (<0)
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("is off the board");
        gameState.play(1, -1);
    }

    @Test
    public void testInvalidColumnLarge() {
        // Variation A1,B3,C1: Interface variation - different invalid column (>2)
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("is off the board");
        gameState.play(1, 3);
    }
}
