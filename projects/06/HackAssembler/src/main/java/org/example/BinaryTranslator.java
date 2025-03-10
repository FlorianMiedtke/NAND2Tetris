package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Translates instructions to a HACK compatible binary format.
 */
public class BinaryTranslator {

    private final TranslationTables translationTables = new TranslationTables();

    public String translateAInstructionValue(String aInstructionValue) {
        int integerValue = Integer.parseInt(aInstructionValue);
        String binaryValueString = Integer.toBinaryString(integerValue);

        StringBuilder stringBuilder = new StringBuilder(binaryValueString);
        while (stringBuilder.length() < 16) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }

    public String translateCInstruction(String dest, String comp, String jump) {
        return "111" + translationTables.getCompBits(comp) +
                translationTables.getDestBits(dest) +
                translationTables.getJumpBits(jump);
    }
}
