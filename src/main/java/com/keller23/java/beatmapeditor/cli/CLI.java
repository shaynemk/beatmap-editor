package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.*;

public class CLI {

    public static String printHeader() {
        return "\n--- " + Strings.APP_NAME  + ", v" + Strings.APP_VERSION + " ---\n"; //+ ", v" + Strings.APP_VERSION;
    }

    public static String printHelp() {
        // TODO: 5/30/16 Build up the help menu.

        //return "- Help Menu -" + "\nSorry, I'm only a useless WIP placeholder for the time being.\nTry running with filesTest argument.";

        return "--help\t\t\t\t\tThis page. Page is also default for unknown commands.\n" +
                "--filesTest\t\t\t\tDisplay all files in working & all child directories.\n" +
                "--searchOSU [dir]\t\t\tSearch for and display all *.osu files in working dir or [dir].\n" +
                "--readVersion \"path/to/OSU\"\t\tOutput version of OSU.\n" +
                "--readAllVersions \"path/of/OSUs\"\tOutput version of all OSU's in path given.";
    }
}
