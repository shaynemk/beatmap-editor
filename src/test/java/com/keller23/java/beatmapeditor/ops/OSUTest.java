package com.keller23.java.beatmapeditor.ops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OSUTest {

    private final String tmpOSULocation = "./tmp.osu";
    private Map<String, String> expectedProperties;

    private OSU osuInfo;
    private File osuFile;

    @Before
    public void setUp() throws Exception {
        // See if the temp OSU exists for some reason, if so delete it.
        Files.deleteIfExists(Paths.get(tmpOSULocation));

        // Create new temp OSU file in working directory (aka, same dir as build.gradle).
        osuFile = new File(tmpOSULocation);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(osuFile), System.getProperty("file.encoding"));
        osw.write(demoOSU);
        osw.close();

        // Write expected properties map.
        expectedProperties = createPropertiesMap();
    }

    @After
    public void tearDown() throws Exception {
        // Delete the temp OSU file as we are done with it for now.
        Files.deleteIfExists(Paths.get(tmpOSULocation));
    }

    @Test
    public void testInstantiation() throws Exception {
        osuInfo = new OSU(tmpOSULocation);
        Assert.assertNotNull("osuInfo is null after instantiation.",osuInfo);
        osuInfo = null; //todo gotta remember why this statement is here when we use osuInfo later on...
    }

    @Test
    public void testVersion() throws Exception {
        final String expectedVersion = "5";

        if (osuInfo == null) {
            osuInfo = new OSU(tmpOSULocation);
        }

        Assert.assertNotNull("OSU.getVersion() gave us a null. Why, damnit, why!?", osuInfo.getVersion());
        Assert.assertEquals("Expected and Actual OSU.getVersion() results are not the same.",
                expectedVersion, osuInfo.getVersion());
    }

    @Test
    public void testPropertiesGetter() throws Exception {
        if (osuInfo == null) {
            osuInfo = new OSU(tmpOSULocation);
        }

        Assert.assertEquals("Actual and Expected Properties Maps are not equal.", expectedProperties, osuInfo.getProperties());
        if (!expectedProperties.equals(osuInfo.getProperties())) {
            System.out.println("Expected: " + expectedProperties.toString());
            System.out.println("Actual: " + osuInfo.getProperties().toString());
        }
    }


    private Map<String, String> createPropertiesMap() {
        Map<String, String> result = new HashMap<String, String>();
        String[] splitt;
        for (String prop : expectedPropertiesRaw) {
            splitt = prop.split(":");
            result.putIfAbsent(splitt[0].trim(), splitt[1].trim());
        }
        return Collections.unmodifiableMap(result);
    }

    private final String[] expectedPropertiesRaw = {"AudioFilename: chatmonchy - 02 - Make Up! Make Up!.mp3",
            "AudioLeadIn: 0",
            "AudioHash: 6c9ddb3ad3f4b8559d27a25dfdc8bb8a",
            "PreviewTime: -1",
            "Countdown: 0",
            "SampleSet: Normal",
            "EditorBookmarks: 41229,84086",
            "Title: Make Up! Make Up!",
            "Artist: Chatmonchy",
            "Creator: peppy",
            "Version: Hard",
            "HPDrainRate: 5",
            "CircleSize: 5",
            "OverallDifficulty: 5",
            "SliderMultiplier: 1.4",
            "SliderTickRate: 2"};

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