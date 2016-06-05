package com.keller23.java.beatmapeditor.ops;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileOps {

    private CompositeConfiguration config = new CompositeConfiguration();

    public static void listFilesInDir() throws IOException {
        File dir = new File(".");

        System.out.println("Getting all files in " + dir.getPath() + "including subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
            //file.
        }
    }

    public static void listFilesInDir(String argDIR) throws IOException {
        File dir = new File(argDIR);

        System.out.println("Getting all files under: " + dir.getPath());
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    public static void getExtFilesInDir(String[] extensions) throws IOException {
        File dir = new File(".");
        //String[] OSU = new String[] {"osu"};
        System.out.print("Getting all {");
        for (String ext : extensions) {
            System.out.print("'" + ext + "', ");
        }
        System.out.println("} files in " + dir.getPath());
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    public static void getOSUFilesInDir(/*String[] _extensions, */String _dir) throws IOException {
        File dir = new File(_dir);
        String[] OSU = new String[] {"osu"};
        /*System.out.print("Getting all {");
        for (String ext : _extensions) {
            System.out.print(ext + ", ");
        }
        System.out.println("} files in " + dir.getPath());*/

        System.out.println("Searching for all *.osu under " + dir.getPath() + "...");

        List<File> files = (List<File>) FileUtils.listFiles(dir, OSU/*_extensions*/, true);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    public static void getOSUFilesInDir() throws IOException {
        File dir = new File(".");
        String[] OSU = new String[] {"osu"};
        /*System.out.print("Getting all {");
        for (String ext : _extensions) {
            System.out.print(ext + ", ");
        }
        System.out.println("} files in " + dir.getPath());*/

        System.out.println("Searching for all *.osu under " + dir.getPath() + "...");

        List<File> files = (List<File>) FileUtils.listFiles(dir, OSU/*_extensions*/, true);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    /*public static void populateMaps() {

    }*/

    /***
     * Read in config from OSU -- testing
     * @param filePath - Input path to the .OSU that is to be read
     */
    public static void readOSUVersion(String filePath) {
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

        System.out.println("---- " + filePath.split("\\\\")[filePath.split("\\\\").length-1] + ":");
        osuVersion.forEach(System.out::println);
        difficultyOptions.forEach(System.out::println);
    }

    public static void readOSUVersions(String _dir) {
        File dir = new File(_dir);
        String[] OSUFilter = new String[] {"osu"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, OSUFilter, true);

        for (File file : files) {
            //System.out.println("file: " + file.getPath());
            readOSUVersion(file.getPath());
        }
    }
}
