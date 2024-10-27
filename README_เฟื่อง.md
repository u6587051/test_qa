# Simple TicTacToe Game #

**Getting started**

**Prerequisites**

- JRE 1.6
- Maven 2 or Gradle

**Build and Run**

**Maven**

	mvn package

	java -jar target/tictactoe-1.0.jar

**Gradle**

	gradle installApp
	./build/install/tictactoe-java/bin/tictactoe-java

**Detail of board**

	ตำแหน่งในกระดานของ tictactoe

	00,01,02

	10,11,12

	20,21,22

	ผู้เล่น: X, O

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
