package com.keller23.java.beatmapeditor.ops;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OSU {

    /***
     * OSU File Version
     */
    String fileVersion;

    /***
     * [Metadata]
     * Title: *
     * Artist: *
     * Creator: *
     * Version: *
     */
    //String title, artist, creator, version;
    Map<String,Object> metadata;


    /***
     * Configure the class upon instantiation
     * @param filePath
     */
    public OSU(final String filePath) {
        // Check the file exists, then open it and start extracting info.
        if (Files.exists(Paths.get(filePath))) {
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                // Feed the String[] directly into readVersion to avoid using another variable.
                readVersion(stream
                        .filter(line -> line.startsWith("osu file format"))
                        .collect(Collectors.toList()));


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("OSU Instantiation failed: No file given in instantiation argument.");
        }
    }

    /***
     * Dedicated string manipulation to extract the version from the version line.
     * @param version
     */
    private void readVersion(final List<String> version) throws IOException {
        // Make sure there is only one entry, as there should only be one version line in the OSU.
        if (version.size() == 1) {
            this.fileVersion = version.get(0).substring(version.get(0).length() - 1);
        } else {
            throw new IOException("OSU File either doesn't have or has too many file format definitions.");
        }
    }

    /*public static void readFileVersion(final String filePath) {
        List<String> osuVersion = new ArrayList<>();
        List<String> difficultyOptions = new ArrayList<>();

        // get the OSU Version
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            //stream.forEach(System.out::println);
            osuVersion = stream
                    .filter(line -> line.startsWith("osu file format"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the Difficulty options we want to track
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            difficultyOptions = stream
                    .filter(line -> line.startsWith("OverallDifficulty") || line.startsWith("ApproachRate"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n---- " + filePath.split("\\\\")[filePath.split("\\\\").length-1] + ":");
        osuVersion.forEach(System.out::println);
        difficultyOptions.forEach(System.out::println);
    }*/
}
