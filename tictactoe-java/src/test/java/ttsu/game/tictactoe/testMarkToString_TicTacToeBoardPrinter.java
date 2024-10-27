/* Copyright (C) 2024 Napaphat Napatsanan - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package ttsu.game.tictactoe;

import static org.junit.Assert.assertEquals;
import static ttsu.game.tictactoe.TicTacToeGameState.Player.X;
import static ttsu.game.tictactoe.TicTacToeGameState.Player.O;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Tests the markToString method of TicTacToeBoardPrinter.
 */
public class testMarkToString_TicTacToeBoardPrinter {

    // ชื่อ TicTacToeBoardPrinterMarkToStringTest
    // จุดประสงค์เพื่อตรวจสอบการแปลงค่าที่ Player เป็น String ในฟังก์ชัน markToString

    // Identify testable functions => markToString()
    // ฟังก์ชันที่ทดสอบคือ markToString ซึ่งรับค่าผู้เล่นเพื่อแปลงเป็น String

    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters => Player player
    // - player: ค่าที่เป็นประเภท Player ซึ่งสามารถเป็น X, O หรือ null

    // Return type => String
    // - ฟังก์ชันนี้จะส่งกลับค่า String ที่แทนผู้เล่นตามที่ระบุ

    // Return value => "X", "O", " "
    // - ค่าที่สามารถส่งกลับได้คือ "X", "O" สำหรับผู้เล่น X และ O ตามลำดับ หรือ " " หากเป็น null

    // Exceptional behavior => no exceptional behavior
    // - ฟังก์ชันนี้ไม่ควรเกิดข้อผิดพลาด เนื่องจากค่าที่ส่งเข้ามาเป็นประเภท Player

    // Model input domain => Player types: X, O, null
    // - ค่าที่ถูกต้องสำหรับ player คือ X, O หรือ null

    // Function Test:
    // ทดสอบการทำงานของฟังก์ชัน markToString ในกรณีที่ Player เป็น X
    @Test
    public void testMarkToStringX() throws Exception {
        Method method = TicTacToeBoardPrinter.class.getDeclaredMethod("markToString", TicTacToeGameState.Player.class);
        method.setAccessible(true); // ทำให้เข้าถึงเมธอด private ได้
        String result = (String) method.invoke(null, X);
        assertEquals("X", result); // Function Test: ทดสอบการส่งกลับของฟังก์ชันเมื่อ Player เป็น X
    }

    // Function Test:
    // ทดสอบการทำงานของฟังก์ชัน markToString ในกรณีที่ Player เป็น O
    @Test
    public void testMarkToStringO() throws Exception {
        Method method = TicTacToeBoardPrinter.class.getDeclaredMethod("markToString", TicTacToeGameState.Player.class);
        method.setAccessible(true);
        String result = (String) method.invoke(null, O);
        assertEquals("O", result); // Function Test: ทดสอบการส่งกลับของฟังก์ชันเมื่อ Player เป็น O
    }

    // Function Test / Interface Test:
    // ทดสอบการทำงานของฟังก์ชัน markToString ในกรณีที่ Player เป็น null
    @Test
    public void testMarkToStringNull() throws Exception {
        Method method = TicTacToeBoardPrinter.class.getDeclaredMethod("markToString", TicTacToeGameState.Player.class);
        method.setAccessible(true);
        String result = (String) method.invoke(null, (Object) null);
        assertEquals(" ", result); // ECC: ทดสอบการส่งกลับของฟังก์ชันเมื่อ Player เป็น null
    }
}

/*

Function Test: การทดสอบที่ตรวจสอบการทำงานของฟังก์ชันในกรณีต่าง ๆ ที่ระบุ เช่น testMarkToStringX และ testMarkToStringO ตรวจสอบการส่งค่าที่ถูกต้องเมื่อ Player เป็น X หรือ O

Interface Test: ใช้ในการตรวจสอบการทำงานของฟังก์ชันในกรณีที่ Player เป็น null ว่าฟังก์ชันสามารถจัดการกับกรณีนี้ได้อย่างถูกต้องหรือไม่

Each Choice Coverage (ECC): ทุกการทดสอบจะครอบคลุมทุกทางเลือกที่ฟังก์ชันสามารถจัดการได้ โดยจะตรวจสอบผลลัพธ์สำหรับ X, O, และ null

*/