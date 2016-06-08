package com.keller23.java.beatmapeditor.ops;

import org.junit.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OSUTest {

    private final String tmpOSULocation = "./tmp.osu";

    public OSU osuInfo;
    public File osuFile;

    @Before
    public void setUp() throws Exception {
        // See if the temp OSU exists for some reason, if so delete it.
        Files.deleteIfExists(Paths.get(tmpOSULocation));

        // Create new temp OSU file in working directory (aka, same dir as build.gradle).
        osuFile = new File(tmpOSULocation);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(osuFile), System.getProperty("file.encoding"));
        osw.write(demoOSU);
        osw.close();
    }

    @After
    public void tearDown() throws Exception {
        // Delete the temp OSU file as we are done with it for now.
        Files.deleteIfExists(Paths.get(tmpOSULocation));
    }

    @Test
    public void testInstantiation() throws Exception {
        osuInfo = new OSU(tmpOSULocation);
        Assert.assertNotNull(osuInfo);
        osuInfo = null;
    }

    @Ignore
    @Test
    public void testVersion() throws Exception {
        final String expectedVersion = "5";

        osuInfo = new OSU(tmpOSULocation);

        Assert.assertNotNull(/*"OSU.fileVersion has evaluated to null.", */osuInfo.fileVersion);
        Assert.assertSame("Expected and Actual OSU.fileVersion are not the same.",
                expectedVersion, osuInfo.fileVersion);
    }

    private final String demoOSU = "osu file format v5\n" +
            "\n" +
            "[General]\n" +
            "AudioFilename: chatmonchy - 02 - Make Up! Make Up!.mp3\n" +
            "AudioLeadIn: 0\n" +
            "AudioHash: 6c9ddb3ad3f4b8559d27a25dfdc8bb8a\n" +
            "PreviewTime: -1\n" +
            "Countdown: 0\n" +
            "SampleSet: Normal\n" +
            "EditorBookmarks: 41229,84086\n" +
            "\n" +
            "[Metadata]\n" +
            "Title:Make Up! Make Up!\n" +
            "Artist:Chatmonchy\n" +
            "Creator:peppy\n" +
            "Version:Hard\n" +
            "\n" +
            "[Difficulty]\n" +
            "HPDrainRate:5\n" +
            "CircleSize:5\n" +
            "OverallDifficulty:5\n" +
            "SliderMultiplier:1.4\n" +
            "SliderTickRate:2\n" +
            "\n" +
            "[Events]\n" +
            "//SECTION EXCLUDED//\n" +
            "\n" +
            "[TimingPoints]\n" +
            "//SECTION EXCLUDED//\n" +
            "\n" +
            "[Colours]\n" +
            "//SECTION EXCLUDED//\n" +
            "\n" +
            "[HitObjects]\n" +
            "//SECTION EXCLUDED//\n";
}