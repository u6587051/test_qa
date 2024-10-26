/* Copyright (C) 2024 Suntipap Chotimanont - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */


package ttsu.game.tictactoe;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class GetMarkTest {

    // ชื่อ GetMarkTest
    // จุดประสงค์เพื่อตรวจสอบการรับค่าที่ mark บนกระดานเช่น X,O จากตำแหน่งที่ได้รับ

    // Identify testable functions => getMark()
    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters => row, col
    // Return type => Player
    // Return value => Player.X, Player.O
    // Exceptional behavior => invalid input type that not int
    // Model input domain => row: 0-2, col: 0-2

    // มีการเช็คทั้งหมด 2 parameters ว่ามีค่าที่ได้รับมาหรือไม่ => int row และ int col

    private GameBoard board;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        board = new GameBoard();
    }

    // เช็คว่าไม่มีหรือไม่เป็น null  True(T) ไม่เป็น null หรือ False(F) เป็น null
    // row (T,F) = a,b
    // col (T,F) = y,z
    // (T,T),(T,F)   ==>  (a,y),(a,z)
    // (F,T),(F,F)        (b,y),(b,z)

    //เช็ค parameter ของ getMark ว่า parameter ต่างๆว่างได้ไหม (Interface-based characteristic)

    // Test values
    // Test แบบ Base choice เริ่มจาก (a,y) == (T,T) => (b,y),(a,z) => (F,T),(T,F)

    // Expected
    // Test แบบ Base choice ควรได้ค่าดังนี้ (null) => (null),(null)
    // null คือ null error

    //(b,y) => (F,T)
    @Test
    public void checkParamNull1() {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("null");
        int test = Integer.parseInt(null);
        board.getMark(test,1);
    }

    //(a,z) => (T,F)
    @Test
    public void checkParamNull2() {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("null");
        int test = Integer.parseInt(null);
        board.getMark(0, test);
    }

    // ตำแหน่งในกระดานของ tictactoe
    // 00,01,02
    // 10,11,12
    // 20,21,22
    // ผู้เล่น: X, O

    // row = 0,1,2 => (a,b,c)
    // col = 0,1,2 => (x,y,z)
    // (a,x),(a,y),(a,z)
    // (b,x),(b,y),(b,z)
    // (c,x),(c,y),(c,z)

    // เช็คการทำงานของ getMark ว่าใส่ตำแหน่งไปแล้วสามารถรับค่าที่อยู่บนกระดานได้ถูกต้องไหม (X,O) (Functionality-based characteristic)
    // เช็คการทำงานจาก input ที่สามารถ getMark จากกระดานได้
    // int อื่นมีการ test แล้วว่าไม่สามารถเกินขอบเขตที่กำหนดได้

    // ตำแหน่งในกระดานของที่ทำการ mark แล้ว
    // O,"",""
    // O,"",""
    // X,X,X

    // Test values
    // Test แบบ Base choice เริ่มจาก (c,x)  => (a,x),(b,x),(c,y),(c,z)
    //                            (2,0)     (0,0),(1,0),(2,1),(2,2)

    // Expected
    // Test แบบ Base choice ควรได้ค่าดังนี้ (X) => (O),(O),(X),(X)
    // O,X คือ ผู้เล่นที่ลงในกระดานแล้วในตำแหน่งที่กำหนด

    @Test
    public void Base_Position_getMark() {

        // (c,x) => (2,0)
        board.mark(2,0, Player.X);
        assertEquals(Player.X, board.getMark(2,0));

        // (a,x) => (0,0)
        board.mark(0,0, Player.O);
        assertEquals(Player.O, board.getMark(0,0));

        // (b,x) => (1,0)
        board.mark(1,0, Player.O);
        assertEquals(Player.O, board.getMark(1,0));

        // (c,y) => (2,1)
        board.mark(2,1, Player.X);
        assertEquals(Player.X, board.getMark(2,1));

        // (c,z) => (2,2)
        board.mark(2,2, Player.X);
        assertEquals(Player.X, board.getMark(2,2));
    }

    // mark อีกจุดแล้วเช็คอีกจุดนึง
    @Test
    public void CheckWrongMark(){
        board.mark(2,1, Player.O);
        assertEquals(null, board.getMark(2,2));
    }
}