package com.thacker.filesplit.main;

import com.thacker.filesplit.join.ChunkZeroVerify;
import com.thacker.filesplit.join.Join;
import com.thacker.filesplit.resources.Strings;
import com.thacker.filesplit.split.FileInfo;
import com.thacker.filesplit.split.FileLoad;
import com.thacker.filesplit.split.Split;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int defaultChunkSize = 1_048_576;   //1 MB
    private static String zeroFileName = "file0.fs";
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        if(args.length > 1){
            if(args[0].toLowerCase().equals("-s")){
                split(args[1]);
            }else if(args[0].toLowerCase().equals("-j")){
                join(args[1]);
            }else{
                System.out.println(Strings.invalidOption);
                return;
            }
        }else if(args.length == 0){
            int option;

            while(true) {
                System.out.println(Strings.title + "     Version " + Strings.version);
                System.out.println(Strings.mainMenuInstruction + '\n');
                System.out.println(Strings.mainMenuItem1);
                System.out.println(Strings.mainMenuItem2);
                System.out.println(Strings.mainMenuItem3);
                System.out.println(Strings.mainMenuItem4);
                System.out.println();
                System.out.print(Strings.defaultPrompt);

                try {
                    option = in.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(Strings.invalidOption);
                    in.nextLine();
                    continue;
                }

                if (option < 1 && option > 4) {
                    System.out.println(Strings.invalidOption);
                    continue;
                }

                in.nextLine();

                switch (option) {
                    case 1:
                        split();
                        break;
                    case 2:
//                    join();
                        break;
                    case 3:
                        about();
                        break;
                    case 4:
                        return;
                }

                in.nextLine();
            }
        }else{
            System.out.println(Strings.commandLineArgumentError);
            return;
        }
    }

    public static void about(){
        System.out.println(Strings.aboutString1);
        System.out.println("Version: " + Strings.version);
        System.out.println(Strings.copyrightNotice);
    }

    public static void split(){
        String filePath = in.nextLine();

        try {
            split(filePath);
        }catch(FileNotFoundException e){
            System.out.println(Strings.sourceNotFound);
        }
    }

    private static void split(String path) throws FileNotFoundException {
        int chunkSize;

        System.out.println(Strings.sizeOfSplitFiles);

        while(true) {
            try {
                chunkSize = defaultChunkSize * in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println(Strings.wholeNumberInputOnly);
                in.next();
                continue;
            }
        }
        in.nextLine();

        File f = FileLoad.load(path);
        FileInfo fi = new FileInfo(f, chunkSize);
        Split.split(f, fi);
    }

    public static void join(String path) throws IOException {
        if((path.charAt(path.length() - 1)) != '/'){
            path = path + "/";
        }

        FileInfo fi = ChunkZeroVerify.verify(new File(path + zeroFileName));
        Join.join(fi, path);
    }
}
