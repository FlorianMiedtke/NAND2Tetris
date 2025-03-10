package org.example;

import java.io.*;

/**
 * Handles the process of the assembler
 */
public class Process {

    BinaryTranslator binaryTranslator = new BinaryTranslator();
    Parser parser = new Parser();
    public void run(String[] args) {
        //read file
        //loop over file rows
            //check if A or C intruction
                //if A
                    //translate to binary
                //if C
                    //parse and translate parts accordingly
                    //put back into one string
            //write to output file

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        PrintWriter printWriter = new PrintWriter("out.hack")) {
            //Workaround to avoid blank line
            boolean firstIteration = true;
            String translatedLine;
            String currentLine = bufferedReader.readLine();

            while (currentLine != null) {
                if(!isCommentLine(currentLine) && !isEmptyLine(currentLine)) {
                    firstIteration = emptyLineWorkaround(firstIteration, printWriter);

                    if(isAInstruction(currentLine)) {
                        translatedLine = binaryTranslator.translateAInstructionValue(parser.getAInstructionValue(currentLine));

                    } else {
                        translatedLine = binaryTranslator.translateCInstruction(parser.getDest(currentLine),
                                parser.getComp(currentLine),
                                parser.getJump(currentLine));
                    }

                    printWriter.print(translatedLine);
                }
                currentLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean emptyLineWorkaround(boolean firstIteration, PrintWriter printWriter) {
        if (!firstIteration) {
            printWriter.println();
        }
        return false;
    }

    private boolean isAInstruction(String instruction) {
        return instruction.startsWith("@");
    }

    private boolean isCommentLine(String line) {
        return line.startsWith("//");
    }

    private boolean isEmptyLine(String line) {
        return line.isBlank();
    }
}
