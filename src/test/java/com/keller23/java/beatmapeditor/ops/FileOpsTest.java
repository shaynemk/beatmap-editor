package com.keller23.java.beatmapeditor.ops;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by under on 6/5/2016.
 */
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
    }

}