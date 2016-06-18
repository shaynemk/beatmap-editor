package com.keller23.java.beatmapeditor.ops;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FileOpsTest {

    private final String songDir = ".\\spec\\mockSongs\\";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Ignore
    @Test
    public void readOSUVersion() throws Exception {
        String testOSU = songDir + "Chatmonchy - Make Up! Make Up!\\Chatmonchy - Make Up! Make Up! (peppy) [Hard].osu";
        FileOps.readFileVersion(testOSU);
        //TODO Do something about this test not actually testing anything....
    }
}

/** -- This is supposedly one way to set up the tests for reading System.out
private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

@Before
public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
}

@After
public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
}
sample test cases:

@Test
public void out() {
    System.out.print("hello");
    assertEquals("hello", outContent.toString());
}

@Test
public void err() {
    System.err.print("hello again");
    assertEquals("hello again", errContent.toString());
}*/