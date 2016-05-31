package com.keller23.java.beatmapeditor;


import com.keller23.java.beatmapeditor.cli.CLI;

public class Main {

    public static void main(String[] args) {
        // TODO: 5/30/16 Clean this up, being real logic.
        if(false) {
            if (args.length > 0) {
                System.out.println("Args Given (" + args.length + "):");
                for (String arg : args) {
                    System.out.println(arg);
                }
            } else {
                System.out.println("No arguments given");
            }
        } else {
            System.out.println(CLI.printHeader());
            System.out.println(CLI.printHelp());
        }
    }
}
