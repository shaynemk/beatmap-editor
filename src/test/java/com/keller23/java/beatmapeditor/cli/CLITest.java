package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;
import org.junit.Test;

import static org.junit.Assert.*;

public class CLITest {
    @Test
    public void printHeader() throws Exception {
        //Verify Header is properly returned as String.

        String expect = "\n-- Beatmap Editor, v" + Strings.APP_VERSION + " --";
        String actual = CLI.printHeader();

        assertSame(expect,actual);
    }

    @Test
    public void printHelp() throws Exception {
        //TODO Verify Help is properly returned as String.
    }

}
