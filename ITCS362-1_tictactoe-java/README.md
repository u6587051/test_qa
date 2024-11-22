# Simple TicTacToe Game #
by Tim Tsu

## Getting started ##

### Build and Run ###
#### Maven ####
    mvn package
    java -jar target/tictactoe-1.0.jar
#### Gradle ####
    gradle installApp
    ./build/install/tictactoe-java/bin/tictactoe-java

-----------------------------------------
## Note: Maybe need to install this!!
* junit:4.13.2!!
* mockito-core:5.4.0
* fest-assert:1.4

## Detail of board
### ตำแหน่งในกระดานของ tictactoe <br>
    00,01,02
    10,11,12
    20,21,22
    ผู้เล่น: X, O

## Method test: MarkTest ##
* ชื่อ Test: MarkTest
* จุดประสงค์เพื่อตรวจสอบการ mark บนกระดานเช่น X,O จากตำแหน่งที่ได้รับว่าลงได้ไหม ถูกต้องหรือไม่ทั้งในด้านของตำแหน่งและผู้เล่น

1. Identify testable functions => `mark()`
2. Parameters => row, col, player 
3. Return type => boolean 
4. Return value => true,false 
5. Exceptional behavior => invalid input type that not int and obj player 
6. Model input domain => row: 0-2, col: 0-2, Player: X,O

มีการเช็คทั้งหมด 3 parameters ว่ามีค่าที่ได้รับมาหรือไม่ => int row, int col และ player

### เช็ค parameter ของ Mark ว่า parameter ต่างๆว่างได้ไหม (Interface-based characteristic)
เช็คว่าไม่เป็น null ใช่ไหม True(T) ไม่เป็น null หรือ False(F) เป็น null <br>
row (T,F) = a,b <br>
col (T,F) = y,z <br>
player (T,F) = 1,2

(T,T,T),(T,T,F),(T,F,T),(T,F,F),   ==>  <br>
(F,T,T),(F,T,F),(F,F,T),(F,F,F)  

(a,y,1),(a,y,2),(a,z,1),(a,z,2),(b,y,1),(b,y,2),(b,z,1),(b,z,2) 

* Test values <br>
Test แบบ Base choice เริ่มจาก (a,y,1) == (T,T,T) => (b,y,1),(a,z,1),(a,y,2) => (F,T,T),(T,F,T),(T,T,F) <br>
* Expected <br>
Test แบบ Base choice ควรได้ค่าดังนี้ (mark ค่าได้ตามตำแหน่งและผู้เล่น) => (null),(null),(cannot mark null player) <br>
* null คือ null error <br>

### เช็คการทำงานของ Mark ว่าทุกตำแหน่งในกระดานสามารถลงได้ไหมโดยการลงทีละตำแหน่ง (Functionality-based characteristic)
row = 0,1,2 => (a,b,c) <br>
col = 0,1,2 => (x,y,z) <br>
Player = Player.X, Player.O => (1,2) <br>

(a,x,1),(a,x,2),(b,x,1),(b,x,2),(c,x,1),(c,x,2) <br>
(a,y,1),(a,y,2),(b,y,1),(b,y,2),(c,y,1),(c,y,2) <br>
(a,z,1),(a,z,2),(b,z,1),(b,z,2),(c,z,1),(c,z,2) <br>

เช็คการทำงานจาก input ที่สามารถ mark ลงบนกระดานได้ <br>
int และ Player อื่นมีการ test แล้วว่าไม่สามารถเกินขอบเขตที่กำหนดได้

* Test values
Test แบบ Base choice เริ่มจาก (b,y,2)  => (a,y,2),(c,y,2),(b,x,2),(b,z,2),(b,y,1) <br>
หรือ (1,1,O) => (0,1,O),(2,1,O),(1,0,O),(1,2,O),(1,1,X)

* Expected
* Test แบบ Base choice ควรได้ค่าดังนี้ (True) => (True),(True),(True),(True),(True)
* True คือ สามารถ mark ได้ถูกต้องบนตำแหน่งและผู้เล่นดังกล่าว

## Method test: GetMarkTest ##
* ชื่อ Test: GetMarkTest
* จุดประสงค์เพื่อตรวจสอบการรับค่าที่ mark บนกระดานเช่น X,O จากตำแหน่งที่ได้รับ

1. Identify testable functions => `getMark()`
2. Parameters => row, col 
3. Return type => Player 
4. Return value => Player.X, Player.O 
5. Exceptional behavior => invalid input type that not int 
6. Model input domain => row: 0-2, col: 0-2

มีการเช็คทั้งหมด 2 parameters ว่ามีค่าที่ได้รับมาหรือไม่ => int row และ int col

### เช็ค parameter ของ getMark ว่า parameter ต่างๆว่างได้ไหม (Interface-based characteristic)
เช็คว่าไม่มีหรือไม่เป็น null  True(T) ไม่เป็น null หรือ False(F) เป็น null <br>
row (T,F) = a,b <br>
col (T,F) = y,z 

(T,T),(T,F)   ==> <br>
(F,T),(F,F) <br>

(a,y),(a,z),(b,y),(b,z)

### Test cases using Base Choice
* Test values
Test แบบ Base choice เริ่มจาก (a,y) == (T,T) => (b,y),(a,z) => (F,T),(T,F)

* Expected
Test แบบ Base choice ควรได้ค่าดังนี้ (null) => (null),(null)
* null คือ null error

### เช็คการทำงานของ getMark ว่าใส่ตำแหน่งไปแล้วสามารถรับค่าที่อยู่บนกระดานได้ถูกต้องไหม (X,O) (Functionality-based characteristic)
row = 0,1,2 => (a,b,c) <br>
col = 0,1,2 => (x,y,z)

(a,x),(a,y),(a,z) <br>
(b,x),(b,y),(b,z) <br>
(c,x),(c,y),(c,z) <br>

เช็คการทำงานจาก input ที่สามารถ getMark จากกระดานได้
* int อื่นมีการ test แล้วว่าไม่สามารถเกินขอบเขตที่กำหนดได้

### ตำแหน่งในกระดานของที่ทำการ mark แล้ว
    O,"",""
    O,"",""
    X,X,X

### Test cases using Base Choice
* Test values
Test แบบ Base choice เริ่มจาก (c,x)  => (a,x),(b,x),(c,y),(c,z) <br>
หรือ (2,0) => (0,0),(1,0),(2,1),(2,2)

* Expected
Test แบบ Base choice ควรได้ค่าดังนี้ (X) => (O),(O),(X),(X)
* O,X คือ ผู้เล่นที่ลงในกระดานแล้วในตำแหน่งที่กำหนด

## Method test: HasWinTest
* ชื่อ HasWinTest
ชุดการทดสอบการตรวจสอบการชนะ

* จุดประสงค์:เพื่อตรวจสอบการชนะของผู้เล่น (X หรือ O) ในรูปแบบต่างๆ ว่าสามารถชนะได้ในแบบแนวนอน แนวตั้ง และแนวทแยงหรือไม่

### Input Domain Modeling (การจำลองขอบเขตข้อมูลนำเข้า)
1. Testable function (ฟังก์ชันที่จะทดสอบ) => `hasWin()`
2. Parameters (พารามิเตอร์) => player: Player (X,O,null) - ผู้เล่นที่ต้องการตรวจสอบการชนะ
3. Return type (ชนิดข้อมูลที่ส่งคืน) => boolean
4. Return value (ค่าที่ส่งคืน) => true (กรณีชนะ), false (กรณีไม่ชนะ)
5. Exceptional behavior (กรณีข้อผิดพลาด) => เมื่อใส่ค่าที่ไม่ใช่ Player หรือ null
6. Model input domain (ขอบเขตข้อมูลที่รับ) => player สามารถเป็นได้แค่ X, O หรือ null เท่านั้น

### Characteristics (ลักษณะที่ต้องทดสอบ):
1. Interface-based characteristic (การทดสอบด้านการรับข้อมูล):
```
    A: Player type - ตรวจสอบความถูกต้องของผู้เล่นที่รับเข้ามา
        - A1: Player.X (ค่าพื้นฐาน)
        - A2: Player.O (ทดสอบกับผู้เล่น O)
        - A3: null (ทดสอบกรณีไม่มีผู้เล่น)
```

2. Functionality-based characteristics (การทดสอบด้านการทำงาน):
```
    B: Win pattern - ตรวจสอบรูปแบบการชนะแบบต่างๆ
        - B1: Row (แนวนอน - ค่าพื้นฐาน)
        - B2: Column (แนวตั้ง)
        - B3: Diagonal (แนวทแยง)
    C: Board state - ตรวจสอบสถานะของกระดาน
        - C1: Complete win (ชนะสมบูรณ์ - ค่าพื้นฐาน)
        - C2: No win (ยังไม่ชนะ)
```
### Test Cases Using MBCC (การทดสอบแบบ Multiple Base Choice):
1. Base Tests (การทดสอบพื้นฐาน):
    ```
    A1,B1,C1 - X ชนะในแนวนอน (กรณีพื้นฐาน)
    A1,B2,C1 - X ชนะในแนวตั้ง (กรณีพื้นฐาน)
    A1,B3,C1 - X ชนะในแนวทแยง (กรณีพื้นฐาน)
    ```
2. Variations (การทดสอบแบบแปรผัน):
    ```
    A2,B1,C1 - O ชนะในแนวนอน (เปลี่ยนผู้เล่น) 
    A3,B1,C1 - ทดสอบกับ null (ทดสอบค่า null) 
    A1,B1,C2 - X ยังไม่ชนะ (ทดสอบกรณีไม่ชนะ)
    ```
### Test Values & Expected Results (ค่าทดสอบและผลลัพธ์ที่คาดหวัง):
1. testXWinsRow():
    - Input: ผู้เล่น X, ชนะแนวนอน
    - Expected: true (คาดว่าจะชนะ)

2. testXWinsColumn():
    - Input: ผู้เล่น X, ชนะแนวตั้ง
    - Expected: true (คาดว่าจะชนะ)

3. testXWinsDiagonal():
    - Input: ผู้เล่น X, ชนะแนวทแยง
    - Expected: true (คาดว่าจะชนะ)

4. testOWinsRow():
    - Input: ผู้เล่น O, ชนะแนวนอน
    - Expected: true (คาดว่าจะชนะ)

5. testNullPlayer():
    - Input: null
    - Expected: true (คาดว่าจะได้ true)

6. testNoWin():
    - Input: ผู้เล่น X, ยังเล่นไม่จบ
    - Expected: false (คาดว่าจะไม่ชนะ)

## Method Test: PlayTest

* ชื่อ Test: PlayTest ชุดการทดสอบการเล่นในตำแหน่งต่างๆ
* จุดประสงค์เพื่อตรวจสอบการเล่นในตำแหน่งต่างๆว่าสามารถเล่นได้หรือไม่ ทั้งในด้านความถูกต้องของตำแหน่งและสถานะของตำแหน่งนั้นๆ

### Input Domain Modeling (การจำลองขอบเขตข้อมูลนำเข้า)
1. Testable function (ฟังก์ชันที่จะทดสอบ) => `play()`
2. Parameters (พารามิเตอร์) => row: int (แถว), col: int (คอลัมน์)
3. Return type (ชนิดข้อมูลที่ส่งคืน) => boolean
4. Return value (ค่าที่ส่งคืน) => true (เล่นสำเร็จ), false (เล่นไม่สำเร็จ)
5. Exceptional behavior (กรณีข้อผิดพลาด) => เมื่อใส่ค่าที่ไม่ใช่ตัวเลข หรือ เกินขอบเขต (0-2)
6. Model input domain (ขอบเขตข้อมูลที่รับ) => row และ col ต้องอยู่ในช่วง 0-2 เท่านั้น

### Characteristics (ลักษณะที่ต้องทดสอบ):
1. Interface-based characteristics (การทดสอบด้านการรับข้อมูล):
    ```
        A: Row value - ตรวจสอบค่าแถวที่รับเข้ามา
            - A1: Valid (0-2) (ค่าที่ถูกต้อง - ค่าพื้นฐาน)
            - A2: Invalid (<0) (ค่าติดลบ)
            - A3: Invalid (>2) (ค่าเกิน 2)
        B: Column value - ตรวจสอบค่าคอลัมน์ที่รับเข้ามา
            - B1: Valid (0-2) (ค่าที่ถูกต้อง - ค่าพื้นฐาน)
            - B2: Invalid (<0) (ค่าติดลบ)
            - B3: Invalid (>2) (ค่าเกิน 2)
    ```
2. Functionality-based characteristic (การทดสอบด้านการทำงาน):
    ```
        C: Position state - ตรวจสอบสถานะของตำแหน่งที่จะเล่น
            - C1: Empty (ว่าง - ค่าพื้นฐาน)
            - C2: Occupied (มีคนเล่นแล้ว)
    ```

### Test Cases Using MBCC (การทดสอบแบบ Multiple Base Choice):
1. Base Tests (การทดสอบพื้นฐาน):
   ```
   A1,B1,C1 - ตำแหน่งถูกต้องและว่าง (กรณีพื้นฐาน)
   A1,B1,C2 - ตำแหน่งถูกต้องแต่มีคนเล่นแล้ว
   A2,B1,C1 - แถวไม่ถูกต้อง (ติดลบ)
   ```

2. Variations (การทดสอบแบบแปรผัน):
   ```
   A3,B1,C1 - แถวไม่ถูกต้อง (เกิน 2)
   A1,B2,C1 - คอลัมน์ไม่ถูกต้อง (ติดลบ)
   A1,B3,C1 - คอลัมน์ไม่ถูกต้อง (เกิน 2)
   ```

### Test Values & Expected Results (ค่าทดสอบและผลลัพธ์ที่คาดหวัง):
1. testValidEmptyPosition():
    - Input: แถว=1, คอลัมน์=1 (ตำแหน่งว่าง)
    - Expected: true (คาดว่าจะเล่นได้)

2. testValidOccupiedPosition():
    - Input: แถว=1, คอลัมน์=1 (ตำแหน่งที่มีคนเล่นแล้ว)
    - Expected: false (คาดว่าจะเล่นไม่ได้)

3. testInvalidRowNegative():
    - Input: แถว=-1, คอลัมน์=1
    - Expected: IllegalArgumentException (คาดว่าจะเกิดข้อผิดพลาด)

4. testInvalidRowLarge():
    - Input: แถว=3, คอลัมน์=1
    - Expected: IllegalArgumentException (คาดว่าจะเกิดข้อผิดพลาด)

5. testInvalidColumnNegative():
    - Input: แถว=1, คอลัมน์=-1
    - Expected: IllegalArgumentException (คาดว่าจะเกิดข้อผิดพลาด)

6. testInvalidColumnLarge():
    - Input: แถว=1, คอลัมน์=3
    - Expected: IllegalArgumentException (คาดว่าจะเกิดข้อผิดพลาด)