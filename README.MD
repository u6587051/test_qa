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

**Method test: testEqualsGameBoard**
- ชื่อ Test: testEqualsGameBoard
- จุดประสงค์: เพื่อตรวจสอบว่า GameBoard สองตัวมีค่าเท่ากันหรือไม่ โดยเปรียบเทียบตำแหน่งของเครื่องหมาย (X หรือ O) ในทุกช่องของกระดาน
1. Identify testable functions => equals(Object obj)
	- ฟังก์ชันที่ทดสอบคือ equals ซึ่งรับ Object ของ GameBoard เพื่อเปรียบเทียบกับ this ว่ามีตำแหน่งของเครื่องหมายบนกระดานที่ตรงกันหรือไม่
2. Parameters => Object obj
	- obj: ออบเจกต์ GameBoard ที่จะนำมาเปรียบเทียบกับกระดานปัจจุบัน
3. Return type => boolean
	- ฟังก์ชันนี้ส่งกลับค่า true หากทั้งสองกระดานมีค่าที่เท่ากันในทุกช่อง และ false หากมีค่าใดต่างกัน
4. Return value => no return value
	- หากกระดานเหมือนกันในทุกตำแหน่ง จะส่งกลับ true
	- หากมีความแตกต่างกันในตำแหน่งใด จะส่งกลับ false
5. Exceptional behavior => Invalid object type
	- หากออบเจกต์ที่ส่งเข้ามาไม่ใช่ประเภท GameBoard จะส่งกลับ false
6. Model input domain => GameBoard state
	- GameBoard ที่ใช้ในการเปรียบเทียบควรมีขนาดเดียวกันและข้อมูลในช่วงที่ถูกต้อง (0-2 ของแถวและคอลัมน์)
**Interface Test**
Interface Test จะช่วยให้มั่นใจว่าฟังก์ชัน equals สามารถจัดการกับข้อมูลที่เป็นประเภทต่างๆ ได้อย่างเหมาะสม:
- ในการทดสอบนี้ เราทำการเปรียบเทียบ GameBoard กับออบเจกต์ที่ไม่ใช่ GameBoard เพื่อดูว่าผลลัพธ์จะเป็น false ตามที่คาดหวัง
- นอกจากนี้ ยังมีการทดสอบด้วย null ซึ่งต้องทำให้แน่ใจว่าผลลัพธ์ไม่ก่อให้เกิดข้อผิดพลาด และฟังก์ชันจะส่งกลับ false
**Function Test**
Function Test จะทดสอบฟังก์ชันการทำงานของ printGameBoard กับกระดานที่มีเครื่องหมายผสม (X, O, null) เช่น ในเมธอด printGameBoardMixedMarks
- เราทดสอบการเปรียบเทียบระหว่างกระดานที่มีค่าเท่ากันและกระดานที่มีค่าต่างกันในบางตำแหน่ง
- การทดสอบจะครอบคลุมกระดานที่มี X, O, และตำแหน่งที่ว่าง (null) เพื่อให้มั่นใจว่าการเปรียบเทียบเกิดขึ้นถูกต้องในทุกสถานะของกระดาน
**Pair-wise Coverage**
- เราทดสอบด้วยคู่กระดานที่มีเครื่องหมายที่เหมือนกันและคู่ที่มีเครื่องหมายต่างกันบางตำแหน่งเพื่อครอบคลุมค่าที่เป็นไปได้ในทุกคู่
- นอกจากนี้ยังครอบคลุมการทดสอบว่าค่าที่ส่งกลับต้องเป็น true เมื่อกระดานทั้งสองมีค่าเท่ากันและ false เมื่อมีความแตกต่าง

**Method test: GetMarkTest**
- ชื่อ Test: GetMarkTest
- จุดประสงค์เพื่อตรวจสอบการรับค่าที่ mark บนกระดานเช่น X,O จากตำแหน่งที่ได้รับ

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

***Method test: HashCodeTest**

* ชื่อ class HashCodeTest
* จุดประสงค์ เพื่อทดสอบเปรียบเทียบ hashCode ว่าในแต่ละ Scenarios hashCode ของ Board เหมือนกันหรือแตกต่างกันหรือไม่

1. Identify testable functions => hashCode()
2. Parameters(State): Game Board State, ผู้เล่นปัจจุบัน, ลำดับในการเล่นที่ทำให้เกิดการจัดวางหมากในกระดานปัจจุบัน
3. Return type: int
4. Return value: ค่า hash ของกระดาน
5. Exceptional behavior: อาจเกิดพฤติกรรมไม่คาดคิดหากมีการเปลี่ยนแปลงโครงสร้างข้อมูลภายในระหว่างการเรียกใช้
6. Model input domain: สถานะของกระดานเกม (ตำแหน่งของเครื่องหมาย, ขนาดของกระดาน)

**Interface Test**

Interface Test ทำหน้าที่ตรวจสอบว่า hashCode ไม่โยน Exception ภายใต้เงื่อนไขต่างๆ
- testHashCode_Interface() ทดสอบ call hashCode() เพื่อเช็คว่า empty board ไม่ควร throw exception ใดๆ
- testHashCode_InterfaceNonEmptyBoard() ทดสอบ call hashCode() เพื่อเช็คว่า บอร์ดที่กำลังเล่นอยู่ ไม่ควร throw exception ใดๆ

**Function Test**

Function Test เจาะจงไปที่การทดสอบผลลัพธ์ที่คาดหวังของ hashCode สำหรับอินพุตที่แตกต่างกัน
- ทดสอบฟังก์ชันการทำงานของ hashCode สำหรับกระดานว่างเปล่า
- ทดสอบฟังก์ชันการทำงานของ hashCode สำหรับกระดานที่มีเครื่องหมายแตกต่างกัน
- ทดสอบฟังก์ชันการทำงานของ hashCode สำหรับกระดานที่มีเครื่องหมายเหมือนกันแต่ตำแหน่งต่างกัน
- ตรวจสอบคุณสมบัติ Transitivity ของฟังก์ชัน hashCode(): คุณสมบัติ Transitivity หมายความว่าหาก A เท่ากับ B และ B เท่ากับ C แล้ว A ก็ต้องเท่ากับ C

**All Combinations of Coverage (ACoC)**

- ไม่สามารถ Test ได้ครบ 100% Coverage เนื่องจากการเปรียบเทียบ hashCode ของ Board ที่แตกต่างกับทุกๆแบบที่เป็นไปได้นั้นมีมากเกินไป
- ครอบคลุมการทดสอบที่เป็นการพิสูจน์ความต่างและความเหมือนของ hashCode กับ Board ในแต่ละกรณีได้ครบ ซึ่งมีทั้งหมด 5 scenarios ได้แก่:
  1. ทดสอบว่ากระดานว่างเปล่าสองอันมีค่า hashCode เท่ากัน
  2. ทดสอบว่ากระดานที่มีเครื่องหมายต่างกันมีค่า hashCode ต่างกัน
  3. ทดสอบว่ากระดานที่มีเครื่องหมายเหมือนกันแต่ตำแหน่งต่างกันมีค่า hashCode ต่างกัน
  4. ทดสอบคุณสมบัติ Transitivity ของ hashCode โดยที่ A, B, C คือกระดานเกม
  5. ทดสอบ hashCode ใน Large set of boards ในที่นี้ให้เป็น 1000 random boards โดยใช้ function generateRandomBoard()
     เพิ่อ generate และให้ print ค่า Collisions ของ hashCode
  
**Test values & Expected values**

*Test Value (สถานะกระดาน) 
*Expected Value (ค่า hashCode) 

1. testHashCode_EmptyBoards() 
   Test value: สองกระดานว่างเปล่า
   Expected: ค่า hashCode เท่ากัน
  
1. testHashCode_DifferentBoards() 
   Test value: กระดานหนึ่งว่างเปล่า อีกกระดานหนึ่งมีเครื่องหมาย
   Expected: ค่า hashCode แตกต่างกัน
   
1. testHashCode_SameMarksDifferentPositions()  
   Test value: สองกระดานมีเครื่องหมายเหมือนกันแต่ตำแหน่งต่างกัน
   Expected: ค่า hashCode แตกต่างกัน
   
1. testHashCode_Transitivity() 
   Test value: สามกระดานที่มีสถานะเหมือนกัน
   Expected: ค่า ค่า hashCode เท่ากันทั้งหมด

1. testHashCode_LargeSet() 
   Test value: จำนวนมากของกระดานสุ่ม
   Expected: ค่า ค่า hashCode กระจายตัวดี (น้อยมากที่จะมีการชนกัน) 
   
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
	
**Method test: isOverTest**

* ชื่อ class IsOverTest
* จุดประสงค์คือการ test method isOver() เพื่อตัดสินว่าเกม TicTacToe จะสามารถจบเกมได้แบบใดบ้าง

1. Identify testable functions => isOver()
2. Parameters(State): Game Board State, ผู้เล่นปัจจุบัน, ลำดับในการเล่นที่ทำให้เกิดการจัดวางหมากในกระดานปัจจุบัน
3. Return type: boolean
4. Return value: True (เกมจบลงแล้ว), False (เกมยังไม่จบ)
5. Exceptional behavior: ไม่ควรโยนข้อยกเว้น
6. Model input domain: สถานะของเกม (ตำแหน่งของเครื่องหมาย, ผู้เล่นคนล่าสุด)

**Interface Test**

Interface Test จะมุ่งเน้นที่การตรวจสอบว่าเมธอดสามารถใช้งานได้โดยไม่เกิดข้อผิดพลาดภายใต้เงื่อนไขที่ถูกต้องตามปกติ
- testIsOver_NoException() สามารถยืนยันได้ว่าไม่สามารถจบเกมได้หากเป็น Board ว่างๆ ถึงแม้จะใช้ method isOver()

**Function Test**

Function Test มุ่งเน้นไปที่การตรวจสอบว่าเมธอดให้ผลลัพธ์ที่ถูกต้องตามความคาดหวังภายใต้เงื่อนไขต่างๆ
- ทุก Test cases มีการยืนยันผลการชนะด้วย hasWin ยกเว้น Draw Scenario เนื่องจากไม่จำเป็นต้องยืนยันผู้ชนะ

**All Combinations of Coverage (ACoC)**

- ไม่สามารถ Test ได้ครบ 100% Coverage เนื่องจากตัวเลขจำนวน test cases ที่มากเกินไป (200k+)
- ครอบคลุมการทดสอบที่เป็นการจบเกมได้ครบทั้ง 4 แบบได้แก่ จบเกมแบบชนะ Row, ชนะ Column, ชนะ Diagonal และ Draw

**Test values**
- คือสถานะต่างๆ ของกระดานเกม เช่น กระดานว่าง, กระดานที่มีเครื่องหมายบางส่วน, กระดานที่มีผู้เล่นชนะ เป็นต้น

*Test value ตัวอย่าง  Test Case: testIsOver_XWinsRow
*สถานะของเกม  ผู้เล่น  X: (0,0),(1,0),(2,0)

**Expected values**
- คือค่า true หรือ false ที่แสดงถึงผลลัพธ์ที่คาดหวังจากการเรียกใช้ฟังก์ชัน isOver() กับ Test Values นั้นๆ

*Expected values: True (X ชนะ)

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
	
**Method test: testToString_GameBoard**

- ชื่อ Test: testToString_GameBoard
- จุดประสงค์: ตรวจสอบการทำงานของเมธอด toString ในคลาส GameBoard เพื่อดูว่าฟังก์ชันสามารถแสดงผลกระดาน TicTacToe ในรูปแบบที่ถูกต้องตามตำแหน่งที่มีเครื่องหมาย X, O หรือช่องว่างได้หรือไม่
1. Identify testable functions => toString()
	- ฟังก์ชันที่ทดสอบคือ toString ซึ่งรับ GameBoard และแสดงผลกระดานเป็น String รูปแบบ TicTacToe
2. Parameters => 
3. Return type => String
	- ฟังก์ชันนี้ส่งค่ากลับเป็น String ซึ่งเป็นการแสดงสถานะของกระดานเกมในรูปแบบ TicTacToe
4. Return value => Board layout in String format
	- ค่าที่ส่งกลับคือ String ที่แสดงสถานะของกระดาน TicTacToe โดยมีเครื่องหมาย X, O, หรือช่องว่างตามตำแหน่งของ Player.X, Player.O และ null ในกระดาน
5. Exceptional behavior =>
6. Model input domain => GameBoard state
	- กระดานเกมควรมีข้อมูลที่ถูกต้องของ Player.X, Player.O, หรือ null เท่านั้นในแต่ละช่อง เพื่อให้การแสดงผลเป็นไปตามที่คาดหวัง

**Interface Test**
Interface Test ทดสอบกรณีต่างๆ ของกระดานที่มีเครื่องหมาย X และ O ในรูปแบบที่ผสมกัน:
- ทดสอบกรณีที่กระดาน TicTacToe ทั้งหมดเป็นช่องว่าง (ค่าทั้งหมดเป็น null) โดยใช้ testToString_emptyBoard
- ตรวจสอบว่าฟังก์ชัน toString() สามารถแสดงกระดานที่ไม่มีเครื่องหมายใดๆ ได้ถูกต้องตามรูปแบบที่กำหนด

**Function Test**
Function Test จะทดสอบการทำงานของ toString ในสถานะต่างๆ ของกระดาน:
- testToString_fullBoard: ทดสอบกระดานที่เต็มไปด้วย Player.X และ Player.O
- testToString_partiallyFilledBoard: ทดสอบกระดานที่มีช่องว่างผสมอยู่กับ X และ O
- testToString_winningLine: ทดสอบกระดานที่มีเครื่องหมาย X หรือ O เรียงกันในแถวใดแถวหนึ่ง

**Pair-wise Coverage**
- Pair-wise Coverage ถูกนำมาใช้เพื่อทดสอบการทำงานของ toString() โดยให้ครอบคลุมทุกกรณีการจัดวาง X, O, และช่องว่างในกระดาน