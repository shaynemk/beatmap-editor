package com.keller23.java.beatmapeditor.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class CLITest {
    @Test
    public void printHeader() throws Exception {
        //Verify Header is properly returned as String.

        String expect = "Beatmap Editor\nWork in Progress";
        String actual = CLI.printHeader();

        assertSame(expect,actual);
    }

    @Test
    public void printHelp() throws Exception {
        //TODO Verify Help is properly returned as String.
    }

}