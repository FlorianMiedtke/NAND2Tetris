package org.example;

public class Parser {

    private static final String WHITE_SPACES_REGEX = "\\s+";
    private static final String SPLIT_REGEX_BOTH = "[=;]";
    private static final String SPLIT_REGEX_EQ = "=";
    private static final String SPLIT_REGEX_SEMICOL = ";";

    public String getAInstructionValue(String currentLine) {
        return currentLine.substring(1);
    }

    public String getDest(String currentLine) {
        if(currentLine.contains("=")) {
            return removeWhiteSpaces(currentLine).split(SPLIT_REGEX_EQ)[0];
        }

        return null;
    }

    public String getComp(String currentLine) {
        String[] splitString = removeWhiteSpaces(currentLine).split(SPLIT_REGEX_BOTH);

        if(currentLine.contains("=") && currentLine.contains(";")) {
            return splitString[1];

        } else if(currentLine.contains("=")) {
            return splitString[1];

        } else if(currentLine.contains(";")) {
            return splitString[0];
        }

        return null;
    }

    public String getJump(String currentLine) {
        if(currentLine.contains(";")) {
            return removeWhiteSpaces(currentLine).split(SPLIT_REGEX_SEMICOL)[1];
        }

        return null;
    }

    private String removeWhiteSpaces(String string) {
        return string.replaceAll(WHITE_SPACES_REGEX, "");
    }
}
