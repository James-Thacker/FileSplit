package com.thacker.filesplit.join;

import com.thacker.filesplit.split.FileInfo;

import java.io.*;

public class ChunkZeroVerify {
    private static ObjectInputStream ois;

    /**
     * Verifies that the 0th chunk file isn't corrupt and if not, returns the data of that file
     * @param zero  File object referring to the 0th chunk file
     * @return  object containing the data stored in the 0th chunk file
     */
    public static FileInfo verify(File zero){
        try{
            //Setup input stream
            ois = new ObjectInputStream(new FileInputStream(zero));

            //Deserialize object containing info about the original split file
            FileInfo fi = (FileInfo) ois.readObject();
            ois.close();

            //Verify integrity, not implemented yet
            if(fi.getChecksum().equals("DEADBEEF001")){

                //If the file is valid return the data
                return fi;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //If the file is not valid return null
        return null;
    }
}
