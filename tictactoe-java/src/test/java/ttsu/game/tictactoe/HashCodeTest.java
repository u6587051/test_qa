/* Copyright (C) 2024 Tutchakorn Gomonkongyou - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */
package ttsu.game.tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class HashCodeTest {

    // ชื่อ class HashCodeTest
    // จุดประสงค์ เพื่อทดสอบเปรียบเทียบ hashCode ว่าในแต่ละ Scenarios hashCode ของ Board เหมือนกันหรือแตกต่างกันหรือไม่
    // Identify testable functions => hashCode()
    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters(State): Game Board State, ผู้เล่นปัจจุบัน, ลำดับในการเล่นที่ทำให้เกิดการจัดวางหมากในกระดานปัจจุบัน
    // Return type: int
    // Return value: ค่า hash ของกระดาน
    // Exceptional behavior: อาจเกิดพฤติกรรมไม่คาดคิดหากมีการเปลี่ยนแปลงโครงสร้างข้อมูลภายในระหว่างการเรียกใช้
    // Model input domain: สถานะของกระดานเกม (ตำแหน่งของเครื่องหมาย, ขนาดของกระดาน)

    // All Combinations of Coverage (ACoC): ไม่สามารถ Test ได้ครบ 100% Coverage เนื่องจากการเปรียบเทียบ hashCode ของ Board ที่แตกต่างกับทุกๆแบบที่เป็นไปได้นั้นมีมากเกินไป
    // ครอบคลุมการทดสอบที่เป็นการพิสูจน์ความต่างและความเหมือนของ hashCode กับ Board ในแต่ละกรณีได้ครบ ซึ่งมีทั้งหมด 5 scenarios ได้แก่:
    // 1. ทดสอบว่ากระดานว่างเปล่าสองอันมีค่า hashCode เท่ากัน
    // 2. ทดสอบว่ากระดานที่มีเครื่องหมายต่างกันมีค่า hashCode ต่างกัน
    // 3. ทดสอบว่ากระดานที่มีเครื่องหมายเหมือนกันแต่ตำแหน่งต่างกันมีค่า hashCode ต่างกัน
    // 4. ทดสอบคุณสมบัติ Transitivity ของ hashCode โดยที่ A, B, C คือกระดานเกม
    // 5. ทดสอบ hashCode ใน Large set of boards ในที่นี้ให้เป็น 1000 random boards โดยใช้ function generateRandomBoard() เพิ่อ generate และให้ print ค่า Collisions ของ hashCode

    // Interface Test: ทำหน้าที่ตรวจสอบว่า hashCode ไม่โยน Exception ภายใต้เงื่อนไขต่างๆ
    // Function Test: เจาะจงไปที่การทดสอบผลลัพธ์ที่คาดหวังของ hashCode สำหรับอินพุตที่แตกต่างกัน

    private GameBoard board;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        board = new GameBoard();

    }

    // Interface Test: Calling hashCode() on an empty board should not throw any exceptions.
    @Test
    public void testHashCode_Interface() {
        board = new GameBoard();
        board.hashCode();
    }

    // Interface Test: Calling hashCode() on a board with some marks should not throw any exceptions.
    @Test
    public void testHashCode_InterfaceNonEmptyBoard() {
        GameBoard board = new GameBoard();
        board.mark(0, 0, Player.X);
        board.hashCode();
    }

    // Function Test: ทดสอบฟังก์ชันการทำงานของ hashCode สำหรับกระดานว่างเปล่า
    @Test
    public void testHashCode_EmptyBoards() {
        // Two empty boards should have the same hash code
        GameBoard board1 = new GameBoard();
        GameBoard board2 = new GameBoard();

        assertEquals(board1.hashCode(), board2.hashCode());
    }

    // Function Test: ทดสอบฟังก์ชันการทำงานของ hashCode สำหรับกระดานที่มีเครื่องหมายแตกต่างกัน
    @Test
    public void testHashCode_DifferentBoards() {
        // Boards with different marks should have different hash codes
        GameBoard board1 = new GameBoard();
        GameBoard board2 = new GameBoard();
        board1.mark(0, 0, Player.X);

        assertTrue(board1.hashCode() != board2.hashCode());
    }

    // Function Test: ทดสอบฟังก์ชันการทำงานของ hashCode สำหรับกระดานที่มีเครื่องหมายเหมือนกันแต่ตำแหน่งต่างกัน
    @Test
    public void testHashCode_SameMarksDifferentPositions() {
        // Boards with the same marks in different positions should have different hash codes
        GameBoard board1 = new GameBoard();
        GameBoard board2 = new GameBoard();
        board1.mark(0, 0, Player.X);
        board2.mark(1, 1, Player.X);

        assertTrue(board1.hashCode() != board2.hashCode());
    }

    // Function Test: ตรวจสอบคุณสมบัติ Transitivity ของฟังก์ชัน hashCode(): คุณสมบัติ Transitivity หมายความว่าหาก A เท่ากับ B และ B เท่ากับ C แล้ว A ก็ต้องเท่ากับ C
    @Test
    public void testHashCode_Transitivity() {
        // If A has the same hash code as B and B has the same hash code as C, then A should have the same hash code as C
        GameBoard board1 = new GameBoard();
        GameBoard board2 = new GameBoard();
        GameBoard board3 = new GameBoard();
        board1.mark(0, 0, Player.X);
        board2.mark(0, 0, Player.X);
        board3.mark(0, 0, Player.X);

        assertEquals(board1.hashCode(), board2.hashCode());
        assertEquals(board2.hashCode(), board3.hashCode());
        assertEquals(board1.hashCode(), board3.hashCode());
    }

    @Test
    public void testHashCode_LargeSet() {
        // Test hash code distribution with a large set of random boards
        int numBoards = 1000;
        List<GameBoard> boards = new ArrayList<>();
        for (int i = 0; i < numBoards; i++) {
            boards.add(generateRandomBoard());
        }

        // Check for collisions (same hash code for different boards)
        int collisions = 0;
        for (int i = 0; i < numBoards; i++) {
            for (int j = i + 1; j < numBoards; j++) {
                if (boards.get(i).hashCode() == boards.get(j).hashCode()) {
                    collisions++;
                }
            }
        }

        // Report the number of collisions (should be a low percentage)
        System.out.println("Collisions in " + numBoards + " random boards: " + collisions);
    }

    private GameBoard generateRandomBoard() {
        GameBoard board = new GameBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Math.random() > 0.5) {
                    board.mark(i, j, Player.X);
                }
            }
        }
        return board;
    }
}