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
        String expect = System.lineSeparator() + "--- Beatmap Editor v" + Strings.APP_VERSION + " ---" + System.lineSeparator();

        System.out.print(CLI.getHeader());

        Assert.assertEquals("Header output to System.out does not match expected.", expect, outContent.toString());

        assertEquals("Actual/Expected strings don't match.", expect, CLI.getHeader());
    }

    @Test
    public void getHelp() throws Exception {
        //Verify Help is properly returned as String.

        String expect =   "--help\t\t\t\t\tThis page, also default." + System.lineSeparator()
                + "--readVersion \"path/to/OSU\"\t\tOutput version of OSU." + System.lineSeparator()
                + "--read \"path/to/OSU\"\t\t\tPrint all properties of OSU file that we are reading in." + System.lineSeparator()
                + "--auto\t\t\t\tAutomatic mode will scan all sub dirs and asks if you want to change every difficulty option in every OSU."
                + "Automatic mode will autosave the original OSU with a .bak extension before writing the new OSU." + System.lineSeparator()
                + "\t--path path/to/directory\t\t\tRequired for Automatic mode, no directory assumptions made." + System.lineSeparator()
                + "\t[--batch]\t\tIf passed, Automatic mode will ask for one set of options to change all OSU's to. "
                + "Only use if you want every OSU under given path to have the same options.";

        System.out.print(CLI.getHelp());

        assertEquals("Actual/Expected Help output doesn't match.", expect, outContent.toString());

        assertEquals("Actual/Expected Help strings don't match.", expect, CLI.getHelp());
    }

    @Test
    public void printCredits() throws Exception {
        String expect = "Idea/Graphics: Alex Hornish" + System.lineSeparator()
                + "Code Impl/Jenkins: Shayne Keller";

        System.out.print(CLI.getCredits());

        assertEquals("Actual/Expected Credits string do not match", expect, CLI.getCredits());
        assertEquals("Actual/Expected Credits output do not match.", expect, outContent.toString());
    }

    /*@Test // TODO: Figure out if/how to test the method.
    public void interactiveMode() throws Exception {

    }*/

}
