package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    Parser instanceUnderTest = new Parser();

    @Test
    public void testGetAInstructionValue() {
        String testString1 = "@1";
        String expectedString1 = "1";

        String testString2 = "  @1";
        String expectedString2 = "1";

        String result1 = instanceUnderTest.getAInstructionValue(testString1);
        String result2 = instanceUnderTest.getAInstructionValue(testString2);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
    }

    @Test
    public void testGetDest() {
        String testString1 = "D=A";
        String expectedString1 = "D";

        String testString2 = "D=D+A";
        String expectedString2 = "D";

        String testString3 = "D=D+A;JMP";
        String expectedString3 = "D";

        String testString4 = "D  = D + A ; JMP";
        String expectedString4 = "D";

        String testString5 = "D+A";
        String expectedString5 = null;

        String testString6 = "DM=A";
        String expectedString6 = "DM";

        String testString7 = "D;JGT";
        String expectedString7 = null;

        String result1 = instanceUnderTest.getDest(testString1);
        String result2 = instanceUnderTest.getDest(testString2);
        String result3 = instanceUnderTest.getDest(testString3);
        String result4 = instanceUnderTest.getDest(testString4);
        String result5 = instanceUnderTest.getDest(testString5);
        String result6 = instanceUnderTest.getDest(testString6);
        String result7 = instanceUnderTest.getDest(testString7);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
        assertEquals(expectedString3, result3);
        assertEquals(expectedString4, result4);
        assertEquals(expectedString5, result5);
        assertEquals(expectedString6, result6);
        assertEquals(expectedString7, result7);
    }

    @Test
    public void testGetComp() {
        String testString1 = "D=A";
        String expectedString1 = "A";

        String testString2 = "D=D+A";
        String expectedString2 = "D+A";

        String testString3 = "D=D+A;JMP";
        String expectedString3 = "D+A";

        String testString4 = "D  = D + A ; JMP";
        String expectedString4 = "D+A";

        String testString5 = "D=!D";
        String expectedString5 = "!D";

        String testString6 = "D=-1";
        String expectedString6 = "-1";

        String testString7 = "D;JGT";
        String expectedString7 = "D";

        String result1 = instanceUnderTest.getComp(testString1);
        String result2 = instanceUnderTest.getComp(testString2);
        String result3 = instanceUnderTest.getComp(testString3);
        String result4 = instanceUnderTest.getComp(testString4);
        String result5 = instanceUnderTest.getComp(testString5);
        String result6 = instanceUnderTest.getComp(testString6);
        String result7 = instanceUnderTest.getComp(testString7);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
        assertEquals(expectedString3, result3);
        assertEquals(expectedString4, result4);
        assertEquals(expectedString5, result5);
        assertEquals(expectedString6, result6);
        assertEquals(expectedString7, result7);
    }

    @Test
    public void testGetJump() {
        String testString1 = "D=A";
        String expectedString1 = null;

        String testString2 = "D=D+A";
        String expectedString2 = null;

        String testString3 = "D=D+A;JMP";
        String expectedString3 = "JMP";

        String testString4 = "D  = D + A ; JMP";
        String expectedString4 = "JMP";

        String testString5 = "D;JGT";
        String expectedString5 = "JGT";

        String result1 = instanceUnderTest.getJump(testString1);
        String result2 = instanceUnderTest.getJump(testString2);
        String result3 = instanceUnderTest.getJump(testString3);
        String result4 = instanceUnderTest.getJump(testString4);
        String result5 = instanceUnderTest.getJump(testString5);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
        assertEquals(expectedString3, result3);
        assertEquals(expectedString4, result4);
        assertEquals(expectedString5, result5);
    }

    @Test
    public void testGetLabelDeclarationName() {
        String testString1 = "(test)";
        String expectedString1 = "test";

        String testString2 = "   (test)";
        String expectedString2 = "test";

        String testString3 = "(test)   ";
        String expectedString3 = "test";

        String result1 = instanceUnderTest.getLabelDeclarationName(testString1);
        String result2 = instanceUnderTest.getLabelDeclarationName(testString2);
        String result3 = instanceUnderTest.getLabelDeclarationName(testString3);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
        assertEquals(expectedString3, result3);
    }
}