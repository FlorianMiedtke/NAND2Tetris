// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

	//Constant
	//Screen has 8191 registers from SCREEN onwards
	@8191
	D=A
	@screenlength
	M=D

(LOOP)
	//if KBD != 0 GOTO BLACK
	//else GOTO WHITE
	@KBD
	D=M
	@BLACK
	D;JNE
	@WHITE
	D;JEQ
	@LOOP
	0;JMP
	
(BLACK)
	@color
	M=-1
	@COLORSCREEN
	0;JMP

(WHITE)
	@color
	M=0
	@COLORSCREEN
	0;JMP
	
(COLORSCREEN)
	@i
	M=0
	
	@SCREEN
	D=A
	@currentscreenadrr
	M=D
	
(COLORLOOP)
	//if i > screenlength GOTO LOOP
	@screenlength
	D=M
	@i
	D=D-M
	@LOOP
	D;JLT
	
	@color
	D=M
	//RAM[currentscreenadrr] = color
	@currentscreenadrr
	A=M
	M=D
	//currentscreenadrr++
	@currentscreenadrr
	M=M+1
	
	//i++
	@i
	M=M+1
	@COLORLOOP
	0;JMP