// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.


//Pseudo code:

//Loop
//R2 = 0
//i = 0
//(LOOP)
//	if(i > R0) GOTO END
//
//	sum = sum + R1
//
//	i++
//
//	JUMP LOOP
//	
//(END)
//	JUMP END

@R2
M=0
@i
M=0

(LOOP)
	@R0
	D=M
	@i
	A=M
	D=D-A
	@END
	D;JLE
	
	@R1
	D=M
	@R2
	M=D+M
	
	@i
	M=M+1
	
	@LOOP
	0;JMP
	
(END)
	@END
	0;JMP