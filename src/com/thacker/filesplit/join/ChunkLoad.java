package com.thacker.filesplit.join;

import java.io.File;

/**
 * Loads a chunk file into a File object and returns the File object to the caller
 */
public class ChunkLoad {

    /**
     * Returns a File object of a chunk file
     * @param chunkName name of the chunk file requested
     * @return  File object referring to the chunk file
     */
    public static File load(String chunkName){
        File chunk = new File(chunkName);
        if(chunk.exists() && chunk.isFile() && chunk.canRead()){
            return chunk;
        }
        return null;
    }
}
