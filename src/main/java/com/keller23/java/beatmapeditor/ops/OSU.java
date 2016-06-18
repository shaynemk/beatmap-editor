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
    private String Version;

    /***
     * An attempt at creating a Properties storage container.
     */
    private Map<String, String> Properties = new HashMap<String, String>();


    /***
     * OSU object constructor.
     * @param filePath Location of the OSU file.
     */
    OSU(final String filePath) {
        //log.debug("OSU Constructor; given: " + filePath);
        //System.out.println("OSU Constructor; given: " + filePath);
        // Check the file exists, then open it and start extracting info.
        if (Files.exists(Paths.get(filePath))) {
            //log.debug("File exists: " + filePath);
            //System.out.println("File exists: " + filePath);

            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                stream.filter(line -> !line.contains("|")
                        && (line.contains(":") || line.startsWith("osu file format v")))
                        .collect(Collectors.toList())
                        .forEach(e -> {
                            if (e.contains(":")) {
                                readProperties(e);
                            } else if (e.startsWith("osu file format v")) {
                                readVersionS(e);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("OSU Instantiation failed: File given doesn't exist.");
        }
    }

    /***
     * getVersion() returns the Version of the OSU.
     * @return Version Version of the OSU file.
     */
    public final String getVersion() {
        return Version;
    }

    /***
     * Setting the file Version is disabled.
     * @param version Version of the OSU file.
     */
    public final void setVersion(final String version) {
        //this.Version = Version;
    }

    /***
     * getProperties() returns all the currently known Properties.
     * @return Properties
     */
    public Map<String, String> getProperties() {
        return Properties;
    }

    /***
     * Setting the properties externally has been disabled.
     * @param properties of the form Map(String,String), contains properties from OSU file.
     */
    public void setProperties(final Map<String, String> properties) {
        //this.Properties = properties;
    }

    /***
     * Dedicated string manipulation to extract the Version from the Version line.
     * @param version contains the versions listed in OSU file
     * @throws IOException
     */
    @Deprecated
    private void readVersionL(final List<String> version) throws IOException {
        //log.debug("readVersion('" + Version + "')");
        //System.out.println("readVersion('" + Version + "')");

        // Make sure there is only one entry, as there should only be one Version line in the OSU.
        if (version.size() == 1) {
            //log.debug("Version.size() == 1");
            //System.out.println("Version.size() == 1");
            this.Version = version.get(0).substring(version.get(0).length() - 1);
            //log.debug(this.Version);
            //System.out.println(this.Version);
        } else {
            //log.debug("Version.size !=
            System.out.println("Version.size != 1");
            throw new IOException("OSU File either doesn't have or has too many file format definitions.");
        }
    }

    /***
     * Take 2
     * @param version
     */
    private void readVersionS(final String version) {
        this.Version = version.substring(version.length() - 1);
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
     * readProperties - Reads Properties from List of Strings into the Properties map.
     * @param prop Property string given to OSU object to parse and insert.
     */
    private void readProperties(final String prop) {
        String[] splitProp = prop.split(":");
        this.Properties.putIfAbsent(splitProp[0].trim(), splitProp[1].trim());
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
