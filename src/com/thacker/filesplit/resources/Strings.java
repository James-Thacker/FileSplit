package com.thacker.filesplit.resources;

public class Strings {
    //PROMPT STRINGS
    public static String defaultPrompt = ": ";

    //MENU STRINGS
    public static String title = "FileSplit file split/join utility";
    public static String version = "0.1";
    public static String mainMenuInstruction = "Please select an option";
    public static String mainMenuItem1 = "1: Split a file";
    public static String mainMenuItem2 = "2: Join files";
    public static String mainMenuItem3 = "3: About FileSplit";
    public static String mainMenuItem4 = "4: Quit";
    public static String aboutString1 = "FileSplit is a simple command line file splitter/joiner";
    public static String copyrightNotice = "Copyright \u00A9 2020 - 2021 James Thacker";

    //ERROR STRINGS
    public static String sourceNotFound = "Source file not found";
    public static String fileNotCreated = "Can not create chunk file";
    public static String chunkWriteError = "READ/WRITE ERROR writeChunks";
    public static String invalidOption = "Invalid option";
    public static String commandLineArgumentError = "Not enough command line arguments";
    public static String wholeNumberInputOnly = "Please only input a whole number";

    //SPLIT STINGS
    public static String sizeOfSplitFiles = "How large to make each chunk, in MB: ";
}
