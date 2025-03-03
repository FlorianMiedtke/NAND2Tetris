// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.


//Pseudo code:

//if(R0 < R1){
//	factorsmall = R0
//  factorbig = R1
//} else {
//	factorsmall = R1
//  factorbig = R0
//}
//
//Loop
//i = 0
//(LOOP)
//	if(i > factorsmall) GOTO END
//
//	sum = sum + factorbig
//
//	i++
//
//	JUMP LOOP
//	
//(END)
//	JUMP END

//Determine which factor is bigger
	@R0
	D=M
	@R1
	A=M
	D=D-A

//if R1 < R0 JMP
	@R1SMALLER
	D;JGT

//else
	@R0
	D=M
	@factorsmall
	M=D
	@R1
	D=M
	@factorbig
	M=D

	@LOOPINIT
	0;JMP

(R1SMALLER)
	@R1
	D=M
	@factorsmall
	M=D
	@R0
	D=M
	@factorbig
	M=D

(LOOPINIT)
	@i
	M=0

(LOOP)
	@factorsmall
	D=M
	@i
	A=M
	D=D-A
	@END
	D;JLE
	
	@factorbig
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