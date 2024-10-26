/* Copyright (C) 2024 Suntipap Chotimanont - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */


package ttsu.game.tictactoe;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ttsu.game.tictactoe.TicTacToeGameState.Player;


public class MarkTest {

    // ชื่อ MarkTest
    // จุดประสงค์เพื่อตรวจสอบการ mark บนกระดานเช่น X,O จากตำแหน่งที่ได้รับว่าลงได้ไหม ถูกต้องหรือไม่ทั้งในด้านของตำแหน่งและผู้เล่น

    // Identify testable functions => mark()
    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters => row, col, player
    // Return type => boolean
    // Return value => true,false
    // Exceptional behavior => invalid input type that not int and obj player
    // Model input domain => row: 0-2, col: 0-2, Player: X,O

    // มีการเช็คทั้งหมด 3 parameters ว่ามีค่าที่ได้รับมาหรือไม่ => int row, int col และ player

    private GameBoard board;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        board = new GameBoard();
    }

    // เช็คว่าไม่เป็น null ใช่ไหม True(T) ไม่เป็น null หรือ False(F) เป็น null
    // row (T,F) = a,b
    // col (T,F) = y,z
    // player (T,F) = 1,2
    // (T,T,T),(T,T,F),(T,F,T),(T,F,F),   ==>  (a,y,1),(a,y,2),(a,z,1),(a,z,2)
    // (F,T,T),(F,T,F),(F,F,T),(F,F,F)         (b,y,1),(b,y,2),(b,z,1),(b,z,2)

    // เช็ค parameter ของ Mark ว่า parameter ต่างๆว่างได้ไหม (Interface-based characteristic)

    // Test values
    // Test แบบ Base choice เริ่มจาก (a,y,1) == (T,T,T) => (b,y,1),(a,z,1),(a,y,2) => (F,T,T),(T,F,T),(T,T,F)

    // Expected
    // Test แบบ Base choice ควรได้ค่าดังนี้ (mark ค่าได้ตามตำแหน่งและผู้เล่น) => (null),(null),(cannot mark null player)
    // null คือ null error

    //(b,y,1) => (F,T,T)
    @Test
    public void checkParamNull1() {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("null");
        int test = Integer.parseInt(null);
        board.mark(test, 1, Player.O);
    }

    //(a,z,1) => (T,F,T)
    @Test
    public void checkParamNull2() {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("null");
        int test = Integer.parseInt(null);
        board.mark(0, test, Player.X);
    }

    //(a,y,2) => (T,T,F)
    @Test
    public void checkParamNull3() {
        // ลักษณะตรงกับ test markNull()
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("cannot mark null player");
        board.mark(0, 0, null);
    }

    // ตำแหน่งในกระดานของ tictactoe
    // 00,01,02
    // 10,11,12
    // 20,21,22
    // ผู้เล่น: X, O

    // row = 0,1,2 => (a,b,c)
    // col = 0,1,2 => (x,y,z)
    // Player = Player.X, Player.O => (1,2)
    // (a,x,1),(a,x,2),(b,x,1),(b,x,2),(c,x,1),(c,x,2)
    // (a,y,1),(a,y,2),(b,y,1),(b,y,2),(c,y,1),(c,y,2)
    // (a,z,1),(a,z,2),(b,z,1),(b,z,2),(c,z,1),(c,z,2)

    // เช็คการทำงานของ Mark ว่าทุกตำแหน่งในกระดานสามารถลงได้ไหมโดยการลงทีละตำแหน่ง (Functionality-based characteristic)
    // เช็คการทำงานจาก input ที่สามารถ mark ลงบนกระดานได้
    // int และ Player อื่นมีการ test แล้วว่าไม่สามารถเกินขอบเขตที่กำหนดได้

    // Test values
    // Test แบบ Base choice เริ่มจาก (b,y,2)  => (a,y,2),(c,y,2),(b,x,2),(b,z,2),(b,y,1)
    //                            (1,1,O)     (0,1,O),(2,1,O),(1,0,O),(1,2,O),(1,1,X)

    // Expected
    // Test แบบ Base choice ควรได้ค่าดังนี้ (True) => (True),(True),(True),(True),(True)
    // True คือ สามารถ mark ได้ถูกต้องบนตำแหน่งและผู้เล่นดังกล่าว

    @Test
    public void Base_Position_Mark() {

        // (b,y,2) => (1,1,O)
        boolean pos01 = board.mark(1, 1, Player.O);
        assertThat(pos01).isTrue();

        // (a,y,2) => (0,1,O)
        boolean pos02 = board.mark(0, 1, Player.O);
        assertThat(pos02).isTrue();

        // (c,y,2) => (2,1,O)
        boolean pos03 = board.mark(2, 1, Player.O);
        assertThat(pos03).isTrue();

        // (b,x,2) => (1,0,O)
        boolean pos04 = board.mark(1, 0, Player.O);
        assertTrue(pos04);

        // (b,z,2) => (1,2,O)
        boolean pos05 = board.mark(1, 2, Player.O);
        assertTrue(pos05);

        // (b,y,1) => (1,1,X)
        // มีการ mark ตำแหน่งซ้ำ ถ้าหาก mark ไม่ได้แปลว่าทำการ mark แล้วแต่ซ้ำจึงเช็คเป็น False แทน
        boolean pos06 = board.mark(1, 1, Player.X);
        assertFalse(pos06);
    }

}