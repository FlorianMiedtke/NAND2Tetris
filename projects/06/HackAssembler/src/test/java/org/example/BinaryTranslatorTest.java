package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTranslatorTest {
    BinaryTranslator instanceUnderTest = new BinaryTranslator();

    @Test
    public void testTranslateAInstructionValue() {
        Integer testInteger1 = 1;
        String expectedString1 = "0000000000000001";

        Integer testInteger2 = 0;
        String expectedString2 = "0000000000000000";

        Integer testInteger3 = 32767;
        String expectedString3 = "0111111111111111";

        String result1 = instanceUnderTest.translateAInstructionValue(testInteger1);
        String result2 = instanceUnderTest.translateAInstructionValue(testInteger2);
        String result3 = instanceUnderTest.translateAInstructionValue(testInteger3);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
        assertEquals(expectedString3, result3);
    }

    @Test
    public void testTranslateCInstruction() {
        String testStringDest1 = "D";
        String testStringComp1 = "D-M";
        String testStringJump1 = null;
        String expectedString1 = "1111010011010000";

        String testStringDest2 = null;
        String testStringComp2 = "D";
        String testStringJump2 = "JGT";
        String expectedString2 = "1110001100000001";

        String result1 = instanceUnderTest.translateCInstruction(testStringDest1, testStringComp1, testStringJump1);
        String result2 = instanceUnderTest.translateCInstruction(testStringDest2, testStringComp2, testStringJump2);

        assertEquals(expectedString1, result1);
        assertEquals(expectedString2, result2);
    }

}