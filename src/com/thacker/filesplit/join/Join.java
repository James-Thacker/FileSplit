package com.thacker.filesplit.join;

import com.thacker.filesplit.split.FileInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Rejoins all chunk files into the original source file
 */
public class Join {

    private static FileInputStream fis;
    private static FileOutputStream fos;

    /**
     * Join chunks to recreate original file
     * @param fi    data about the original file
     * @param path  folder to recreate the original file in
     * @throws IOException
     */
    public static void join(FileInfo fi, String path) throws IOException {

        //Create link to file that will be the recreated file
        File f = new File(path + fi.getName());

        //Setup output stream
        fos = new FileOutputStream(f);

        //Restore the original file's data
        for(int x = 0; x < fi.getTotalChunks(); x++){
            //Load a chunk file
            File chunk = ChunkLoad.load(path + "file" + (x + 1) + ".fs");

            //Setup input stream to the chunk file
            fis = new FileInputStream(chunk);

            //Read all data in the chunk file and write it to the restored file
            fos.write(fis.readAllBytes());

            //Close the input stream
            fis.close();

            //Delete the chunk
            chunk.delete();
        }

        //Close the output stream
        fos.close();

        //Load the 0th chunk file to delete it
        File zero = ChunkLoad.load(path + "file0.fs");
        zero.delete();

    }
}
