package com.keller23.java.beatmapeditor;


import com.keller23.java.beatmapeditor.cli.CLI;
import com.keller23.java.beatmapeditor.ops.FileOps;

import java.io.IOException;

public class Main {

    private static final Boolean DEBUG = false;

    public static void main(String[] args) {
        // TODO: 5/30/16 Clean this up, being real logic.
        System.out.println(CLI.printHeader());

        if(DEBUG) {
            if (args.length > 0) {
                System.out.println("Args Given (" + args.length + "):");
                for (String arg : args) {
                    System.out.println(arg);
                }
            } else {
                System.out.println("No arguments given");
            }
        } else if (args.length > 0) {
            if (args[0].equals("--filesTest")) {
                try {
                    FileOps.listFilesInDir();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (args[0].equals("--searchOSU")) {
                if (args.length > 1) {
                    try {
                        FileOps.getOSUFilesInDir(args[1]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        FileOps.getOSUFilesInDir();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (args[0].equals("--read")) {
                if(args.length > 1) {
                    FileOps.readOSU(args[1]);
                }
            }else {
                System.out.print(CLI.printHelp());
            }
        } else {
            System.out.print(CLI.printHelp());
        }
    }
}
