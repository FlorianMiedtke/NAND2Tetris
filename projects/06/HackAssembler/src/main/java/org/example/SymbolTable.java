package org.example;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, Integer> symbolTable;

    private int variableCounter = 16;

    SymbolTable() {
        symbolTable = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            symbolTable.put("R" + i, i);
        }
        symbolTable.put("SCREEN", 16384);
        symbolTable.put("SP", 0);
        symbolTable.put("LCL", 1);
        symbolTable.put("ARG", 2);
        symbolTable.put("THIS", 3);
        symbolTable.put("THAT", 4);
    }

    public void addLabel(String label, int pc) {
        symbolTable.put(label, pc);
    }

    public int addVariable(String value) {
        symbolTable.put(value, variableCounter);
        int addedCounterValue = variableCounter;
        variableCounter++;
        return addedCounterValue;
    }

    public Integer getEntry(String aInstructionValue) {
        return symbolTable.get(aInstructionValue);
    }
}
