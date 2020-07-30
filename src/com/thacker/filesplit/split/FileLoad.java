package com.thacker.filesplit.split;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class to load the source file to be split
 */
public class FileLoad {
    /**
     * Finds and returns a File object representing the specified source file
     * @param path  pathname of the source file
     * @return  the File object referencing the source file
     * @throws FileNotFoundException    source file wasn't found
     */
    public static File load(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException("Source file not found");
        }
        return file;
    }
}
