package com.keller23.java.beatmapeditor;


import com.keller23.java.beatmapeditor.cli.CLI;
import com.keller23.java.beatmapeditor.ops.FileOps;

public class Main {

    private static final Boolean DEBUG = false;

    public static void main(final String[] args) {
        // TODO: 5/30/16 Clean this up, being real logic.
        System.out.println(CLI.printHeader());

        if (args.length > 1) switch (args[0]) {
            case "--read":
                if (!args[1].isEmpty()) {
                    FileOps.readFile(args[1]);
                } else {
                    System.out.println("Command needs filename given to read.");
                    System.out.println(CLI.printHelp());
                }
                break;
            case "--readVersion":
                FileOps.readFileVersion(args[1]);
                break;
            default:
                //System.out.println("First argument is unknown.");
                System.out.println(CLI.printHelp());
                break;
        } else {
            System.out.println(CLI.printHelp());
        }


        /*  Old code -- to be removed at a later time. */

        /*if (DEBUG) {
            if (args.length > 0) {
                System.out.println("Args Given (" + args.length + "):");
                for (String arg : args) {
                    System.out.println(arg);
                }
            } else {
                System.out.println("No arguments given");
            }
        } else*/

        /*if (args.length > 0) {
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
                        FileOps.printOSUFilesInDir();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (args[0].equals("--readVersion") && args.length > 1) {
                FileOps.readFileVersion(args[1]);
            } else if (args[0].equals("--readAllVersions") && args.length > 1) {
                FileOps.getAllInPath(args[1]);
            } else if (args[0].equals("--read") && args.length > 1) {

                FileOps.readFile(args[1]);
            } else {
                System.out.println(CLI.printHelp());
            }
        /*} else {
            System.out.println(CLI.printHelp());
        }*/
    }
}
