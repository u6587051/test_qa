/* Copyright (C) 2024 Tutchakorn Gomonkongyou - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */
package ttsu.game.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class testIsOver_TicTacToeGameState {

    // ชื่อ class IsOverTest
    // จุดประสงค์คือการ test method isOver() เพื่อตัดสินว่าเกม TicTacToe จะสามารถจบเกมได้แบบใดบ้าง
    // Identify testable functions => isOver()
    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters(State): Game Board State, ผู้เล่นปัจจุบัน, ลำดับในการเล่นที่ทำให้เกิดการจัดวางหมากในกระดานปัจจุบัน
    // Return type: boolean
    // Return value: True (เกมจบลงแล้ว), False (เกมยังไม่จบ)
    // Exceptional behavior: ไม่ควรโยนข้อยกเว้น
    // Model input domain: สถานะของเกม (ตำแหน่งของเครื่องหมาย, ผู้เล่นคนล่าสุด)

    // All Combinations of Coverage (ACoC): ไม่สามารถ Test ได้ครบ 100% Coverage เนื่องจากตัวเลขจำนวน test cases ที่มากเกินไป (200k+)
    // ครอบคลุมการทดสอบที่เป็นการจบเกมได้ครบทั้ง 4 แบบได้แก่ จบเกมแบบชนะ Row, ชนะ Column, ชนะ Diagonal และ Draw

    // Interface Test: มุ่งเน้นไปที่การตรวจสอบว่าเมธอดสามารถใช้งานได้โดยไม่เกิดข้อผิดพลาดภายใต้เงื่อนไขที่ถูกต้องตามปกติ
    // Function Test: มุ่งเน้นไปที่การตรวจสอบว่าเมธอดให้ผลลัพธ์ที่ถูกต้องตามความคาดหวังภายใต้เงื่อนไขต่างๆ

    private TicTacToeGameState game;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        game = new TicTacToeGameState();
    }


    // Interface Test: testIsOver_NoException() สามารถยืนยันได้ว่าไม่สามารถจบเกมได้หากเป็น Board ว่างๆ ถึงแม้จะใช้ method isOver()
    @Test
    public void testIsOver_NoException() {
        game = new TicTacToeGameState();
        game.isOver();
    }

    // Board ว่างไม่สามารถทำให้เกมจบได้
    @Test
    public void testIsOver_EmptyBoard() {
        TicTacToeGameState game = new TicTacToeGameState();

        assertFalse(game.isOver());
    }

    // กำลังเล่นอยู่ ไม่สามารถจบเกมได้
    @Test
    public void testIsOver_GameInProgress() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.play(0, 0); // X starts
        game.play(1, 1);

        assertFalse(game.isOver());
    }
    
    // X Win Row,Column and Diagonal
    // Function Test: Verify X wins
    @Test
    public void testIsOver_XWinsRow() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.play(0, 0); // X starts
        game.play(0, 1);
        game.play(0, 2);

        assertTrue(game.isOver()); // X wins in a row
        assertTrue(game.hasWin(Player.X));
    }

    // Function Test: Verify X wins
    @Test
    public void testIsOver_XWinsColumn() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.play(0, 0); // X starts
        game.play(1, 0);
        game.play(2, 0);

        assertTrue(game.isOver()); // X wins in a column
        assertTrue(game.hasWin(Player.X));
    }

    // Function Test: Verify X wins
    @Test
    public void testIsOver_XWinsDiagonal() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.play(0, 0); // X starts
        game.play(1, 1);
        game.play(2, 2);

        assertTrue(game.isOver()); // X wins in a diagonal
        assertTrue(game.hasWin(Player.X));
    }

    // Function Test: Verify O wins
    @Test
    public void testIsOver_OWinsRow() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.switchPlayer(); // Switch to O
        game.play(1, 0);
        game.play(1, 1);
        game.play(1, 2);

        assertTrue(game.isOver()); // O wins in a row
        assertTrue(game.hasWin(Player.O));
    }

    // Function Test: Verify O wins
    @Test
    public void testIsOver_OWinsColumn() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.switchPlayer(); // Switch to O
        game.play(0, 1);
        game.play(1, 1);
        game.play(2, 1);

        assertTrue(game.isOver()); // O wins in a column
        assertTrue(game.hasWin(Player.O));
    }

    // Function Test: Verify O wins
    @Test
    public void testIsOver_OWinsDiagonal() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.switchPlayer(); // Switch to O
        game.play(0, 2);
        game.play(1, 1);
        game.play(2, 0);

        assertTrue(game.isOver()); // O wins in a diagonal
        assertTrue(game.hasWin(Player.O));
    }

    // Board full without any winner
    @Test
    public void testIsOver_Draw() {
        TicTacToeGameState game = new TicTacToeGameState();
        game.play(0, 0); // X starts
        game.switchPlayer();
        game.play(0, 1); // O
        game.switchPlayer();
        game.play(0, 2); // X
        game.switchPlayer();
        game.play(1, 1); // O
        game.switchPlayer();
        game.play(2, 1); // X
        game.switchPlayer();
        game.play(2, 0); // O
        game.switchPlayer();
        game.play(1, 0); // X
        game.switchPlayer();
        game.play(1, 2); // O
        game.switchPlayer();
        game.play(2, 2); // X

        assertTrue(game.isOver()); // Game end with a draw
    }
}