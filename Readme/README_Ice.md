**Method test: TicTacToeBoardPrinterMixedMarksTest**

- ชื่อ Test: TicTacToeBoardPrinterMixedMarksTest
- จุดประสงค์เพื่อตรวจสอบการพิมพ์กระดาน TicTacToe ที่มีเครื่องหมายผสม เช่น X, O, และช่องว่าง (null)
1. Identify testable functions => printGameBoard()
   - ฟังก์ชันที่ทดสอบคือ printGameBoard ซึ่งรับ GameBoard และพิมพ์ค่าออกมา
2. Parameters => GameBoard board
   - board: กระดานเกมที่มีเครื่องหมายของผู้เล่นในแต่ละช่อง
3. Return type => void
   - ฟังก์ชันนี้ไม่มีค่าที่ส่งกลับ แต่จะพิมพ์ค่าลงใน PrintStream
4. Return value => no return value
   - ฟังก์ชันนี้จะไม่ส่งกลับค่าใดๆ แต่จะแสดงผลการพิมพ์ออกมาแทน
5. Exceptional behavior => invalid GameBoard configuration
   - หาก GameBoard มีการกำหนดค่าที่ไม่ถูกต้อง (เช่น มีเครื่องหมายที่ไม่ใช่ Player) จะทำให้เกิดผลลัพธ์ที่ไม่ถูกต้องในการพิมพ์
6. Model input domain => GameBoard state
   - กระดานเกมควรมีเครื่องหมาย Player ที่ถูกต้องในช่วง 0-2 ของแถวและคอลัมน์

**Interface Test**

Interface Test จะช่วยให้เราตรวจสอบว่าฟังก์ชันสามารถจัดการกับค่าต่างๆ ได้อย่างถูกต้องในกรณีที่ข้อมูลที่ได้รับเข้ามาเป็นค่าที่ว่าง (null) หรือไม่ ในที่นี้เราทดสอบกระดานที่ว่างเปล่าด้วยการใช้การทดสอบในเมธอด printGameBoard_EmptyBoard_PrintsEmptyGrid

- ในการทดสอบนี้ เราสร้างกระดานที่ทุกช่องเป็น null และเรียกใช้ printGameBoard ซึ่งควรจะแสดงกระดานว่างออกมา
- มีการตรวจสอบลำดับการพิมพ์ด้วย InOrder ของ Mockito เพื่อให้แน่ใจว่าการพิมพ์เกิดขึ้นในลำดับที่ถูกต้อง

**Function Test**

Function Test จะทดสอบฟังก์ชันการทำงานของ printGameBoard กับกระดานที่มีเครื่องหมายผสม (X, O, null) เช่น ในเมธอด printGameBoardMixedMarks
- เราทดสอบว่าฟังก์ชันสามารถพิมพ์กระดานที่มีค่า X และ O รวมถึงช่องว่างได้ถูกต้องตามลำดับ
- การตรวจสอบจะทำโดยการใช้ InOrder เช่นกัน เพื่อตรวจสอบว่าฟังก์ชันพิมพ์ค่าในลำดับที่ถูกต้อง

**Each Choice Coverage (ECC)**

- Each Choice Coverage (ECC) จะตรวจสอบว่าการทำงานของฟังก์ชันไม่ให้ผลลัพธ์เป็น null สำหรับกรณีการพิมพ์กระดานด้วยเครื่องหมายผสม ซึ่งทดสอบในเมธอด checkPrintedOutputNotNull
- เราสร้างกระดานที่มีค่า X, O และช่องว่าง จากนั้นเรียกใช้ printGameBoard และตรวจสอบว่า printStream มีค่าไม่เป็น null
- ECC เป็นการทดสอบว่าฟังก์ชันได้ตรวจสอบค่าทุกค่าอย่างถูกต้องและทำงานได้ตามที่คาดหวัง

**Method test: markToString**

- ชื่อ Test: TicTacToeBoardPrinterMarkToStringTest
- จุดประสงค์: เพื่อตรวจสอบการแปลงค่าที่ Player เป็น String ในฟังก์ชัน markToString
1. Identify testable functions: markToString()
   - ฟังก์ชันที่ทดสอบคือ markToString ซึ่งรับค่าผู้เล่นเพื่อแปลงเป็น String
2. Parameters: Player player
   - player: ค่าที่เป็นประเภท Player ซึ่งสามารถเป็น X, O หรือ null
3. Return type: String
   - ฟังก์ชันนี้จะส่งกลับค่า String ที่แทนผู้เล่นตามที่ระบุ
4. Return value: "X", "O", " "
   - ค่าที่สามารถส่งกลับได้คือ "X", "O" สำหรับผู้เล่น X และ O ตามลำดับ หรือ " " หากเป็น null
5. Exceptional behavior: no exceptional behavior
   - ฟังก์ชันนี้ไม่ควรเกิดข้อผิดพลาด เนื่องจากค่าที่ส่งเข้ามาเป็นประเภท Player
6. Model input domain: Player types: X, O, null
   - ค่าที่ถูกต้องสำหรับ player คือ X, O หรือ null

**Interface Test**

Interface Test สำหรับ markToString จะตรวจสอบว่าเมธอดสามารถจัดการกับค่าต่างๆ ได้อย่างถูกต้อง โดยเฉพาะในกรณีที่ค่าที่ได้รับเข้ามาเป็น null หรือค่าที่ไม่คาดคิด:

**ตัวอย่างการทดสอบ:**

Test Case: markToString_Null
- Input: null
- Expected Result: " "
- คำอธิบาย: ทดสอบว่าฟังก์ชันสามารถจัดการกับค่าที่เป็น null และส่งคืน " " ได้หรือไม่

Test Case: markToString_InvalidPlayer
- Input: ค่าที่ไม่ถูกต้อง (เช่น ค่าที่ไม่ใช่ Player)
- Expected Result: ควรมีการจัดการข้อผิดพลาด (ซึ่งอาจไม่เกิดขึ้นในกรณีนี้ แต่สามารถเพิ่มการตรวจสอบเพื่อให้มั่นใจว่ามีการจัดการค่าผิดปกติ)

**Function test**

Function Test: markToString
ชื่อ Test: TicTacToeBoardPrinterMarkToStringFunctionTest

จุดประสงค์: เพื่อตรวจสอบการทำงานของฟังก์ชัน markToString ในกรณีที่มีค่าผู้เล่นที่แตกต่างกัน

**Test Cases**

1. Test Case: markToString_X

   - Input: Player.X
   - Expected Result: "X"
   - คำอธิบาย: ทดสอบว่าฟังก์ชันส่งคืน "X" เมื่อค่าผู้เล่นเป็น Player.X
2. Test Case: markToString_O

   - Input: Player.O
   - Expected Result: "O"
   - คำอธิบาย: ทดสอบว่าฟังก์ชันส่งคืน "O" เมื่อค่าผู้เล่นเป็น Player.O
3. Test Case: markToString_Null

   - Input: null
   - Expected Result: " "
   - คำอธิบาย: ทดสอบว่าฟังก์ชันสามารถจัดการกับค่าที่เป็น null และส่งคืน " " ได้หรือไม่
4. Test Case: markToString_Invalid

   - Input: ค่าที่ไม่ถูกต้อง (เช่น ค่าที่ไม่ใช่ Player)
   - Expected Result: ควรมีการจัดการข้อผิดพลาด (อาจจะ throw exception หรือ return ค่าที่เหมาะสม)
   - คำอธิบาย: ทดสอบว่าฟังก์ชันมีการจัดการกับค่าที่ไม่ถูกต้องอย่างไร

**คำอธิบาย Function Test**
- Function Test นี้จะใช้การตรวจสอบว่าเมธอด markToString สามารถคืนค่าที่ถูกต้องตาม input ที่กำหนดได้หรือไม่
- ใช้ assertions (เช่น assertEquals) ในการเปรียบเทียบค่าที่ส่งกลับจากฟังก์ชันกับค่าที่คาดหวังสำหรับแต่ละ test case
- ในกรณีของค่าที่ไม่ถูกต้อง อาจต้องการทำให้แน่ใจว่าเมธอดสามารถจัดการข้อผิดพลาดได้อย่างเหมาะสม

**ตัวอย่างโค้ดการทดสอบ**
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TicTacToeBoardPrinterMarkToStringFunctionTest {

    @Test
    public void markToString_X() {
        assertEquals("X", TicTacToeBoardPrinter.markToString(Player.X));
    }

    @Test
    public void markToString_O() {
        assertEquals("O", TicTacToeBoardPrinter.markToString(Player.O));
    }

    @Test
    public void markToString_Null() {
        assertEquals(" ", TicTacToeBoardPrinter.markToString(null));
    }

    @Test
    public void markToString_Invalid() {
        // Assuming an InvalidPlayerException is thrown for invalid input
        assertThrows(InvalidPlayerException.class, () -> {
            TicTacToeBoardPrinter.markToString(new InvalidPlayer());
        });
    }
}


**Each Choice Coverage (ECC)**

Each Choice Coverage (ECC) สำหรับ markToString จะทดสอบว่าเมธอดสามารถแสดงผลตามค่าที่เป็นไปได้ทั้งหมด รวมถึงการจัดการกับค่าที่เป็น null:

ECC Test Cases:
Test Case             | Input    | Expected Result

markToString_X        | Player.X | "X"

markToString_O        | Player.O | "O"

markToString_Null    | null     | " "

markToString_Invalid | Invalid Value | Exception (or error handling)

- คำอธิบาย ECC: การทดสอบ ECC นี้จะช่วยให้แน่ใจว่าเมธอด markToString สามารถรับค่าได้ทุกกรณีและแสดงผลตามที่คาดหวัง รวมถึงการจัดการข้อผิดพลาดในกรณีที่มีค่าที่ไม่คาดคิด


