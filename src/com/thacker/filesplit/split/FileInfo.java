package com.thacker.filesplit.split;

import java.io.File;
import java.io.Serializable;

/**
 * Stores information about the source file and its chunks
 * Will be written to the Zero file
 */
public class FileInfo implements Serializable {

    private String srcName;
    private int chunkSize;
    private long totalChunks;
    private String checksum;

    /**
     * Create the object which stores information about the source and chunk files
     * @param file  the source file to be split/joined
     * @param chunkSize how large each chunk file is in bytes
     */
    public FileInfo(File file, int chunkSize){
        this.setName(file);
        this.setChunkSize(chunkSize);
        this.setTotalChunks(file);
        this.setChecksum(file);
    }

    /**
     * Sets the name of the source file
     * @param file  the source file to be split/joined
     */
    private void setName(File file){
        this.srcName = file.getName();
    }

    /**
     * Sets the specified chunk file size in bytes
     * @param chunkSize the specified size for each chunk in bytes
     */
    private void setChunkSize(int chunkSize){
        this.chunkSize = chunkSize;
    }

    /**
     * Calculates and sets the total number of chunk files for the source file
     * @param file  the source file to be split/joined
     */
    private void setTotalChunks(File file){
        this.totalChunks = file.length() / this.chunkSize;

        //If the file can not be evenly split add another chunk file for the remainder of the data to be split
        if(file.length() % this.chunkSize > 0){
            this.totalChunks++;
        }
    }

    /**
     * Calculate and set the checksum for the source file
     * @param file  the source file to be split/joined
     */
    private void setChecksum(File file){
        this.checksum = "DEADBEEF001";  //DUMMY CHECKSUM
    }

    /**
     * Returns the source file's name
     * @return  the source file's name
     */
    public String getName(){
        return this.srcName;
    }

    /**
     * Returns the source file's checksum
     * @return  the source file's checksum
     */
    public String getChecksum(){
        return this.checksum;
    }

    /**
     * Returns the source file's chunk file size
     * @return  the source file's chunk file size
     */
    public int getChunkSize(){
        return this.chunkSize;
    }

    /**
     * Returns the number of chunk files to join to rebuild the source file
     * @return  the number of chunk files
     */
    public long getTotalChunks(){
        return this.totalChunks;
    }

}
