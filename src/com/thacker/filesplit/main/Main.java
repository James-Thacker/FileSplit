package com.thacker.filesplit.main;

import com.thacker.filesplit.join.ChunkZeroVerify;
import com.thacker.filesplit.join.Join;
import com.thacker.filesplit.split.FileInfo;
import com.thacker.filesplit.split.FileLoad;
import com.thacker.filesplit.split.Split;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static int defaultChunkSize = 1_048_576;   //1 MB

    public static void main(String[] args) throws IOException {

        if(args.length > 0){
            if(args[0].toLowerCase().equals("-s")){
                split(args[1]);
            }else if(args[0].toLowerCase().equals("-j")){
                join(args[1]);
            }
        }
    }

    public static void split(String path) throws FileNotFoundException {
        System.out.println("How large to make each chunk, in MB: ");
        int chunkSize;
        Scanner in = new Scanner(System.in);
        chunkSize = defaultChunkSize * in.nextInt();               //Add check
        File f = FileLoad.load(path);
        FileInfo fi = new FileInfo(f, chunkSize);
        Split.split(f, fi);
    }

    public static void join(String path) throws IOException {
        FileInfo fi = ChunkZeroVerify.verify(new File(path + "file0.fs"));
        Join.join(fi, path);
    }
}
