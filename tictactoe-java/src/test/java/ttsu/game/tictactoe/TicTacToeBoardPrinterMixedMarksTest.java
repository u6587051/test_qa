/* Copyright (C) 2024 Napaphat Napatsanan - All Rights Reserved
* You may use, distribute and modify this code under the terms of the MIT license.
*/

package ttsu.game.tictactoe;

import static org.mockito.Mockito.inOrder;
import static ttsu.game.tictactoe.TicTacToeGameState.Player.O;
import static ttsu.game.tictactoe.TicTacToeGameState.Player.X;
import static org.junit.Assert.assertNotNull;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

// ใช้ @RunWith เพื่อเรียกใช้งาน MockitoJUnitRunner
@RunWith(MockitoJUnitRunner.class)
public class TicTacToeBoardPrinterMixedMarksTest {

    // ชื่อ TicTacToeBoardPrinterMixedMarksTest
    // จุดประสงค์เพื่อตรวจสอบการพิมพ์กระดาน TicTacToe ที่มีเครื่องหมายผสม

    // Identify testable functions => printGameBoard()
    // ฟังก์ชันที่ทดสอบคือ printGameBoard ซึ่งรับ GameBoard และพิมพ์ค่าออกมา

    // Identify parameters, return types, return values, and exceptional behavior
    // Parameters => GameBoard board
    // - board: กระดานเกมที่มีเครื่องหมายของผู้เล่นในแต่ละช่อง

    // Return type => void
    // - ฟังก์ชันนี้ไม่มีค่าที่ส่งกลับ แต่จะพิมพ์ค่าลงใน PrintStream

    // Return value => no return value
    // - ฟังก์ชันนี้จะไม่ส่งกลับค่าใดๆ แต่จะแสดงผลการพิมพ์ออกมาแทน

    // Exceptional behavior => invalid GameBoard configuration
    // - หาก GameBoard มีการกำหนดค่าที่ไม่ถูกต้อง (เช่น มีเครื่องหมายที่ไม่ใช่ Player) จะทำให้เกิดผลลัพธ์ที่ไม่ถูกต้องในการพิมพ์

    // Model input domain => GameBoard state
    // - กระดานเกมควรมีเครื่องหมาย Player ที่ถูกต้องในช่วง 0-2 ของแถวและคอลัมน์


    private TicTacToeBoardPrinter printer;

    // สร้าง Mock ของ PrintStream เพื่อทดสอบโดยไม่พิมพ์จริงลงคอนโซล
    @Mock
    private PrintStream printStream;

    // กำหนด Rule สำหรับตรวจสอบ Exception ที่คาดว่าจะเกิดขึ้น
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // เมธอด setup() จะถูกเรียกก่อนการทดสอบแต่ละเคส เพื่อเตรียมสภาพแวดล้อมทดสอบ
    @Before
    public void setup() {
        printer = new TicTacToeBoardPrinter(printStream);
    }

    // **Interface Test**
    // ทดสอบการพิมพ์กระดานที่เป็นกระดานเปล่า (ทุกช่องเป็น null) เพื่อทดสอบว่าระบบสามารถจัดการค่า null ได้หรือไม่
    @Test
    public void printGameBoard_EmptyBoard_PrintsEmptyGrid() {
        // กำหนดกระดานที่มีค่าทุกช่องเป็น null เพื่อจำลองกระดานเปล่า
        GameBoard board = new GameBoard(new TicTacToeGameState.Player[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
        });

        // เรียกใช้งานเมธอด printGameBoard เพื่อพิมพ์กระดาน
        printer.printGameBoard(board);

        // ตรวจสอบผลลัพธ์ที่พิมพ์ออกมาว่าเป็นกระดานเปล่า
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
    }

    // **Function Test**
    // ทดสอบการพิมพ์กระดานที่มีเครื่องหมายผสมระหว่าง X, O และช่องว่าง (null)
    @Test
    public void printGameBoardMixedMarks() {
        // กำหนดกระดานที่มีค่า X, O และ null ผสมกัน
        GameBoard board = new GameBoard(new TicTacToeGameState.Player[][] {
                {X, O, null},
                {null, X, O},
                {O, null, X}
        });

        // เรียกใช้งานเมธอด printGameBoard เพื่อพิมพ์กระดาน
        printer.printGameBoard(board);

        // ตรวจสอบว่าลำดับการพิมพ์ตรงกับที่กำหนดไว้
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "O", " ");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", "X", "O");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "O", " ", "X");
    }

    // **Each Choice Coverage (ECC)**
    // ทดสอบว่าการพิมพ์กระดานด้วยเครื่องหมายผสมไม่ให้ผลลัพธ์เป็น null
    @Test
    public void checkPrintedOutputNotNull() {
        // สร้างกระดานที่มี X, O และ null ผสมกัน
        GameBoard board = new GameBoard(new TicTacToeGameState.Player[][] {
                {X, O, null},
                {null, X, O},
                {O, null, X}
        });

        // เรียกใช้งานเมธอด printGameBoard เพื่อพิมพ์กระดาน
        printer.printGameBoard(board);

        // ตรวจสอบว่า printStream มีค่าไม่เป็น null
        assertNotNull(printStream);
    }

    // **Each Choice Coverage (ECC)**
    // ทดสอบการพิมพ์กระดานที่มีเครื่องหมาย X ทั้งหมดในทุกช่อง
    @Test
    public void printGameBoard_AllXMarks() {
        // สร้างกระดานที่มีค่า X ในทุกช่อง
        GameBoard board = new GameBoard(new TicTacToeGameState.Player[][] {
                {X, X, X},
                {X, X, X},
                {X, X, X}
        });

        // เรียกใช้งานเมธอด printGameBoard เพื่อพิมพ์กระดาน
        printer.printGameBoard(board);

        // ตรวจสอบว่าลำดับการพิมพ์ตรงกับที่กำหนดไว้
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "X", "X");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "X", "X");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "X", "X");
    }

    // **Function Test**
    // กรณีที่มีเครื่องหมาย X และ O ผสมกันในทุกช่อง
    @Test
    public void printGameBoard_MixedMarks_AllPositions() {
        // สร้างกระดานที่มีค่า X, O ในทุกช่อง
        GameBoard board = new GameBoard(new TicTacToeGameState.Player[][] {
                {X, O, X},
                {O, X, O},
                {X, O, X}
        });

        // เรียกใช้งานเมธอด printGameBoard เพื่อพิมพ์กระดาน
        printer.printGameBoard(board);

        // ตรวจสอบว่าลำดับการพิมพ์ตรงกับที่กำหนดไว้
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "O", "X");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "O", "X", "O");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", "O", "X");
    }

    // **Function Test**
    // กรณีที่มีช่องว่าง (null) ควบคู่กับเครื่องหมาย X และ O
    @Test
    public void printGameBoard_MixedMarks_WithSpaces() {
        // สร้างกระดานที่มีเครื่องหมาย X, O และช่องว่าง
        GameBoard board = new GameBoard(new TicTacToeGameState.Player[][] {
                {X, null, O},
                {X, null, O},
                {null, X, X}
        });

        // เรียกใช้งานเมธอด printGameBoard เพื่อพิมพ์กระดาน
        printer.printGameBoard(board);

        // ตรวจสอบว่าลำดับการพิมพ์ตรงกับที่กำหนดไว้
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", " ", "O");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", "X", " ", "O");
        inOrder.verify(printStream).println("-+-+-");
        inOrder.verify(printStream).printf("%s|%s|%s\n", " ", "X", "X");
    }
}

/*
Interface Test
- printGameBoard_EmptyBoard_PrintsEmptyGrid(): ทดสอบว่าระบบสามารถจัดการกับกระดานที่เป็น null (ว่างเปล่า) ได้อย่างถูกต้อง โดยการตรวจสอบว่ากระดานที่พิมพ์ออกมาเป็นรูปแบบว่างเปล่า

Function Test
- printGameBoardMixedMarks(): ทดสอบการพิมพ์กระดานที่มีเครื่องหมาย X และ O ผสมกัน เพื่อให้แน่ใจว่าการพิมพ์ทำงานถูกต้อง
- printGameBoard_AllXMarks(): ทดสอบการพิมพ์กระดานที่มี X ในทุกช่อง
- printGameBoard_MixedMarks_AllPositions(): ทดสอบการพิมพ์กระดานที่มีเครื่องหมาย X และ O ผสมกันในทุกช่อง
- printGameBoard_MixedMarks_WithSpaces(): ทดสอบการพิมพ์กระดานที่มีช่องว่าง (null) ควบคู่กับเครื่องหมาย X และ O

Each Choice Coverage (ECC)
- checkPrintedOutputNotNull(): ทดสอบว่าผลลัพธ์การพิมพ์ไม่เป็น null
- เพิ่มการทดสอบที่ครอบคลุมทุกสถานการณ์ที่เป็นไปได้ในกระดาน
*/