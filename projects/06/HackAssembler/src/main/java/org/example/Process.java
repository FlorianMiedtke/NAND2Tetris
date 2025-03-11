package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles the process of the assembler
 */
public class Process {

    private final BinaryTranslator binaryTranslator = new BinaryTranslator();
    private final Parser parser = new Parser();
    private final SymbolTable symbolTable = new SymbolTable();

    public void run(String[] args) {
        firstPassThrough(args);
        secondPassThrough(args);
    }

    /**
     * Adds labels to symbol table.
     *
     * @param args from main method
     */
    private void firstPassThrough(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            String currentLine = bufferedReader.readLine();
            int pc = 0;

            while (currentLine != null) {
                if(!isCommentLine(currentLine) && !isEmptyLine(currentLine)) {
                    if (containsLabel(currentLine)) {
                        String label = parser.getLabelDeclarationName(currentLine);
                        symbolTable.addLabel(label, pc);

                    } else {
                        pc++;
                    }
                }

                currentLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles translation of A and C instructions.
     *
     * @param args from main method
     */
    private void secondPassThrough(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(getFileOutputName(args[0]))) {
            //Workaround to avoid blank line
            boolean firstIteration = true;
            String translatedLine;
            String currentLine = bufferedReader.readLine();

            while (currentLine != null) {
                if (!isCommentLine(currentLine) && !isEmptyLine(currentLine) && !containsLabel(currentLine)) {
                    firstIteration = emptyLineWorkaround(firstIteration, printWriter);

                    //A-Instruction handling
                    if (isAInstruction(currentLine)) {
                        String aInstructionValue = parser.getAInstructionValue(currentLine);
                        boolean isNumeric = aInstructionValue.chars().allMatch(Character::isDigit);

                        if (isNumeric) {
                            translatedLine = binaryTranslator.translateAInstructionValue(Integer.parseInt(aInstructionValue));

                        } else {
                            Integer symbolTableEntry = symbolTable.getEntry(aInstructionValue);

                            if(symbolTableEntry == null) {
                                symbolTableEntry = symbolTable.addVariable(aInstructionValue);
                            }
                            translatedLine = binaryTranslator.translateAInstructionValue(symbolTableEntry);
                        }
                        printWriter.print(translatedLine);

                    } else {
                        //C-Instruction handling
                        translatedLine = binaryTranslator.translateCInstruction(parser.getDest(currentLine),
                                parser.getComp(currentLine),
                                parser.getJump(currentLine));

                        printWriter.print(translatedLine);
                    }

                }
                currentLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileOutputName(String fileNameWithEnding) {
        return fileNameWithEnding.split("\\.")[0] + ".hack";
    }

    private boolean emptyLineWorkaround(boolean firstIteration, PrintWriter printWriter) {
        if (!firstIteration) {
            printWriter.println();
        }
        return false;
    }

    private boolean isAInstruction(String instruction) {
        return instruction.contains("@");
    }

    private boolean isCommentLine(String line) {
        return line.contains("//");
    }

    private boolean isEmptyLine(String line) {
        return line.isBlank();
    }

    private boolean containsLabel(String currentLine) {
        return currentLine.contains("(");
    }
}
