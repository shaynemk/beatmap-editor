package com.keller23.java.beatmapeditor.ops;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OSU {

    //private Log log;

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
    //@Deprecated
    //Map<String, Object> metadata;

    Map<String, String> properties = new HashMap<String, String>();

    //List<String> allProps;


    /***
     * Configure the class upon instantiation
     * @param filePath Location of the OSU file.
     */
    public OSU(final String filePath) {
        //log.debug("OSU Constructor; given: " + filePath);
        //System.out.println("OSU Constructor; given: " + filePath);
        // Check the file exists, then open it and start extracting info.
        if (Files.exists(Paths.get(filePath))) {
            //log.debug("File exists: " + filePath);
            //System.out.println("File exists: " + filePath);

            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                //take 2
                /*allProps =*/ stream.filter(line -> line.contains(":")
                        || line.startsWith("osu file format v"))
                        .collect(Collectors.toList())//;
                        .forEach(e -> {
                            if (e.contains(":")) {
                                readProperties(e);
                            } else if (e.startsWith("osu file format v")) {
                                readVersionS(e);
                            }
                        });
                /*allProps.forEach(line -> {
                    if (line.contains(":")) {
                        readProperties(line);
                    } else if (line.startsWith("osu file format v")) {
                        readVersionS(line);
                    }
                });*/

/*            // Feed the String[] directly into readVersion to avoid using another variable.
                readVersionL(stream
                        .filter(line -> line.startsWith("osu file format"))
                        .collect(Collectors.toList()));

                //(OLD) Read Metadata props in
                List<String> tmpMetadata = stream
                        .filter(line -> line.startsWith("Title:") || line.startsWith("Artist:") || line.startsWith("Creator:") || line.startsWith("Version"))
                        .collect(Collectors.toList());
                //readMetadata(tmpMetadata);
                //tmpMetadata.clear();

                //(NEW) Read in OSU File properties
                List<String> tmpProps = stream
                        .filter(line -> line.contains(":") && !line.contains(","))
                        .collect(Collectors.toList());
                readProperties(tmpProps);*/


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("OSU Instantiation failed: No file given in instantiation argument.");
        }
    }

    /***
     * Dedicated string manipulation to extract the version from the version line.
     * @param version contains the versions listed in OSU file
     */
    private void readVersionL(final List<String> version) throws IOException {
        //log.debug("readVersion('" + version + "')");
        //System.out.println("readVersion('" + version + "')");

        // Make sure there is only one entry, as there should only be one version line in the OSU.
        if (version.size() == 1) {
            //log.debug("version.size() == 1");
            //System.out.println("version.size() == 1");
            this.fileVersion = version.get(0).substring(version.get(0).length() - 1);
            //log.debug(this.fileVersion);
            //System.out.println(this.fileVersion);
        } else {
            //log.debug("version.size !=
            System.out.println("version.size != 1");
            throw new IOException("OSU File either doesn't have or has too many file format definitions.");
        }
    }

    /***
     * Take 2
     * @param version
     */
    private void readVersionS(final String version) {
        this.fileVersion = version.substring(version.length() - 1);
    }

    /***
     * readMetadata reads metadata from the metadata list given.
     * @param metadata String List of metadata values.
     */
    @Deprecated
    private void readMetadata(final List<String> metadata) {
        for (String md : metadata) {
            //this.metadata.put(md.split(":")[0], md.split(":")[0]);
        }
    }

    /***
     * readProperties - Reads properties from List of Strings into the properties map.
     * @param prop
     */
    private void readProperties(final String prop) {
        String[] splitProp = prop.split(":");
        this.properties.putIfAbsent(splitProp[0].trim(), splitProp[1].trim());
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
