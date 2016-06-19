package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;

/***
 * Class for dealing with the CLI
 */
public class CLI {

    /***
     * Returns the string to print to the screen after starting the app.
     * @return
     */
    public static String printHeader() {
        return "\n--- " + Strings.APP_NAME  + ", v" + Strings.APP_VERSION + " ---\n"; //+ ", v" + Strings.APP_VERSION;
    }

    /***
     * Returns the string to print that should be printed to the console.
     * @return Help Menu
     */
    public static String printHelp() {
        return    "--help\t\t\t\t\tThis page, also default.\n"
                + "--readVersion \"path/to/OSU\"\t\tOutput version of OSU.\n"
                + "--read \"path/to/OSU\"\t\t\tPrint all properties of OSU file that we are reading in.";
    }
}
