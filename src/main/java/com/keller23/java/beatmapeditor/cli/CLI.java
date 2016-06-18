package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.*;

public class CLI {

    public static String printHeader() {
        return "\n--- " + Strings.APP_NAME  + ", v" + Strings.APP_VERSION + " ---\n"; //+ ", v" + Strings.APP_VERSION;
    }

    public static String printHelp() {
        // TODO: 5/30/16 Build up the help menu.

        //return "- Help Menu -" + "\nSorry, I'm only a useless WIP placeholder for the time being.\nTry running with filesTest argument.";

        return "--help\t\t\t\t\tThis page, also default.\n" +
                "--readVersion \"path/to/OSU\"\t\tOutput version of OSU.\n" +
                "--read \"path/to/OSU\"\t\t\tPrint all properties of OSU file that we are reading in.";
    }
}
