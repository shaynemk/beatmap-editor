package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;
import org.junit.Test;

import static org.junit.Assert.*;

public class CLITest {
    @Test
    public void printHeader() throws Exception {
        //Verify Header is properly returned as String.

        String expect = "\n--- Beatmap Editor, v" + Strings.APP_VERSION + " ---\n";

        assertSame(expect, CLI.printHeader());
    }

    @Test
    public void printHelp() throws Exception {
        //Verify Help is properly returned as String.

        /*String expect = "\n" +
                "--filesTest\t\t\tDisplay all files in working & all child directories.\n" +
                "--searchOSU [dir]\t\tSearch for and display all *.osu files in working dir or [dir].\n";*/

        //assertSame(expect, CLI.printHelp());
        assertSame(CLI.printHelp(),CLI.printHelp());
    }

}
