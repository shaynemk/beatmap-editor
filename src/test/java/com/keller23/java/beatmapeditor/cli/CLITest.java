package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CLITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void printHeader() throws Exception {
        String expect = System.lineSeparator() + "--- Beatmap Editor, v" + Strings.APP_VERSION + " ---" + System.lineSeparator();

        System.out.print(CLI.printHeader());

        Assert.assertEquals("Header output to System.out does not match expected.", expect, outContent.toString());

        assertEquals("Actual/Expected strings don't match.", expect, CLI.printHeader());
    }

    @Test
    public void printHelp() throws Exception {
        //Verify Help is properly returned as String.

        String expect =   "--help\t\t\t\t\tThis page, also default." + System.lineSeparator()
                        + "--readVersion \"path/to/OSU\"\t\tOutput version of OSU." + System.lineSeparator()
                        + "--read \"path/to/OSU\"\t\t\tPrint all properties of OSU file that we are reading in.";

        System.out.print(CLI.printHelp());

        Assert.assertEquals("Actual/Expected Help output doesn't match.", expect, outContent.toString());

        assertEquals("Actual/Expected Help strings don't match.", expect, CLI.printHelp());
    }

}
