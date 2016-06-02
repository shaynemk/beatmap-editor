package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.*;

public class CLI {

    public static String printHeader() {
        // TODO: 5/30/16 Finish the header. Integrate version?
        return "\n-- " + Strings.APP_NAME  + ", v" + Strings.APP_VERSION + " --"; //+ ", v" + Strings.APP_VERSION;
    }

    public static String printHelp() {
        // TODO: 5/30/16 Build up the help menu.

        //return "- Help Menu -" + "\nSorry, I'm only a useless WIP placeholder for the time being.\nTry running with filesTest argument.";

        return "\n" +
                "--filesTest\tDisplay all files in working/child directories.\n" +
                "--searchOSU\tSearch for and display all *.osu files in current/child directories.\n";
    }
}
