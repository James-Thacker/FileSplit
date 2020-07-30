package com.thacker.filesplit.split;

import java.io.*;

public class Split {
    private static FileInputStream fis;
    private static ObjectOutputStream oos;
    private static FileOutputStream fos;

    /**
     * Split the source file into smaller chunk files
     * @param file  the file to be split
     * @param fi    the data about the file being split
     * @throws FileNotFoundException    if the file to be split is not found
     */
    public static void split(File file, FileInfo fi) throws FileNotFoundException {
        //Write the initial file
        writeZeroChunk(file, fi);

        //Write the chunk files containing the data of the source file
        writeChunks(file, fi);
    }

    /**
     * Creates the 0th chunk file which contains data about the source file
     * @param file  file to be split, needed to get the folder the file resides in
     * @param fi    all of the information about the source file, used in joining the chunk files
     * @throws FileNotFoundException
     */
    private static void writeZeroChunk(File file, FileInfo fi) throws FileNotFoundException {

        //Create a link to the 0th chunk file
        File fZero = new File(file.getParent() + "/file0.fs");

        try{
            //Actually create the 0th chunk file
            fZero.createNewFile();

            //Setup output stream
            oos = new ObjectOutputStream(new FileOutputStream(fZero));

            //Write data to 0th chunk file and close output stream
            oos.writeObject(fi);
            oos.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Can not create chunk file");   //FIX
        }
    }

    /**
     * Writes the data chunk files
     * @param file  File being split
     * @param fi    data about the file being split
     */
    private static void writeChunks(File file, FileInfo fi){
        File chunk;
        long chunkNo = 0;
        try {
            //Setup input stream, used to get folder that the source file resides in
            fis = new FileInputStream(file);

            //Write all of the chunk files
            while (chunkNo < fi.getTotalChunks()) {
                //Create link to a chunk file to create
                chunk = new File(file.getParent() + "/file" + (chunkNo + 1) + ".fs");

                //Create chunk file
                chunk.createNewFile();

                //Setup output stream to write chunk file
                fos = new FileOutputStream(chunk);

                //Read data from source file and write it to chunk file
                fos.write(fis.readNBytes(fi.getChunkSize()));

                //Close output stream and increment chunk number
                fos.close();
                chunkNo++;
            }
        } catch (IOException e) {
            System.out.println("READ/WRITE ERROR writeChunks");
            e.printStackTrace();
        }
    }
}
