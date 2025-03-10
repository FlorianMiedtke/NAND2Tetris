package org.example;

import java.util.HashMap;
import java.util.Map;

public class TranslationTables {

    private final Map<String, String> compBitsTable;
    private final Map<String, String> destBitsTable;
    private final Map<String, String> jumpBitsTable;

    TranslationTables() {
        compBitsTable = new HashMap<>();
        destBitsTable = new HashMap<>();
        jumpBitsTable = new HashMap<>();

        compBitsTable.put("0", "0101010");
        compBitsTable.put("1", "0111111");
        compBitsTable.put("-1", "0111010");
        compBitsTable.put("D", "0001100");
        compBitsTable.put("A", "0110000");
        compBitsTable.put("!D", "0001101");
        compBitsTable.put("!A", "0110001");
        compBitsTable.put("-D", "0001111");
        compBitsTable.put("-A", "0110011");
        compBitsTable.put("D+1", "0011111");
        compBitsTable.put("A+1", "0110111");
        compBitsTable.put("D-1", "0001110");
        compBitsTable.put("A-1", "0110010");
        compBitsTable.put("D+A", "0000010");
        compBitsTable.put("D-A", "0010011");
        compBitsTable.put("A-D", "0000111");
        compBitsTable.put("D&A", "0000000");
        compBitsTable.put("D|A", "0010101");
        compBitsTable.put("M", "1110000");
        compBitsTable.put("!M", "1110001");
        compBitsTable.put("-M", "1110011");
        compBitsTable.put("M+1", "1110111");
        compBitsTable.put("M-1", "1110010");
        compBitsTable.put("D+M", "1000010");
        compBitsTable.put("D-M", "1010011");
        compBitsTable.put("M-D", "1000111");
        compBitsTable.put("D&M", "1000000");
        compBitsTable.put("D|M", "1010101");

        destBitsTable.put(null, "000");
        destBitsTable.put("M", "001");
        destBitsTable.put("D", "010");
        destBitsTable.put("DM", "011");
        destBitsTable.put("A", "100");
        destBitsTable.put("AM", "101");
        destBitsTable.put("AD", "110");
        destBitsTable.put("ADM", "111");

        jumpBitsTable.put(null, "000");
        jumpBitsTable.put("JGT", "001");
        jumpBitsTable.put("JEQ", "010");
        jumpBitsTable.put("JGE", "011");
        jumpBitsTable.put("JLT", "100");
        jumpBitsTable.put("JNE", "101");
        jumpBitsTable.put("JLE", "110");
        jumpBitsTable.put("JMP", "111");
    }

    public String getCompBits(String comp) {
        return compBitsTable.get(comp);
    }

    public String getDestBits(String dest) {
        return destBitsTable.get(dest);
    }

    public String getJumpBits(String jump) {
        return jumpBitsTable.get(jump);
    }
}
