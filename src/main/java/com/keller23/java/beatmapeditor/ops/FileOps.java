package com.keller23.java.beatmapeditor.ops;

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

    //private CompositeConfiguration config = new CompositeConfiguration();

    /***
     * List all files in current/sub dir.
     * @throws IOException
     */
    public static void listFilesInDir() throws IOException {
        File dir = new File(".");

        System.out.println("Getting all files in " + dir.getPath() + "including subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
            //file.
        }
    }

    /***
     * Lists all files in given directory.
     * @param _dir Directory to search in.
     * @throws IOException
     */
    public static void listFilesInDir(String _dir) throws IOException {
        File dir = new File(_dir);

        System.out.println("Getting all files under: " + dir.getPath());
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    /***
     * Gets all files with given extensions in current/sub directory.
     * @param _extensions String[] array of _extensions to filter inclusively.
     * @throws IOException
     */
    public static void getExtFilesInDir(String[] _extensions) throws IOException {
        File dir = new File(".");
        //String[] OSU = new String[] {"osu"};
        System.out.print("Getting all {");
        for (String ext : _extensions) {
            System.out.print("'" + ext + "', ");
        }
        System.out.println("} files in " + dir.getPath());
        List<File> files = (List<File>) FileUtils.listFiles(dir, _extensions, true);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    /***
     * Get all OSU files in given/sub directories.
     * @param _dir This is the root directory of the search for OSU files.
     * @throws IOException
     */
    public static void getOSUFilesInDir(String _dir) throws IOException {
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

    /***
     * Print all the OSU Files in Directory and Subdirectories.
     * @throws IOException
     */
    public static void printOSUFilesInDir() throws IOException {
        File dir = new File(".");
        String[] OSU = new String[] {"osu"};

        System.out.println("Searching for all *.osu under " + dir.getPath() + "...");

        List<File> files = (List<File>) FileUtils.listFiles(dir, OSU/*_extensions*/, true);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    /***
     * Read information from individual OSU files.
     * @param _filePath Input path to the .OSU that is to be read
     */
    public static void readOSUVersion(String _filePath) {
        List<String> osuVersion = new ArrayList<>();
        List<String> difficultyOptions = new ArrayList<>();

        // get the OSU Version
        try (Stream<String> stream = Files.lines(Paths.get(_filePath))) {
            //stream.forEach(System.out::println);
            osuVersion = stream
                    .filter(line -> line.startsWith("osu file format"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the Difficulty options we want to track
        try (Stream<String> stream = Files.lines(Paths.get(_filePath))) {
            difficultyOptions = stream
                    .filter(line -> line.startsWith("OverallDifficulty") || line.startsWith("ApproachRate"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n---- " + _filePath.split("\\\\")[_filePath.split("\\\\").length-1] + ":");
        osuVersion.forEach(System.out::println);
        difficultyOptions.forEach(System.out::println);
    }

    /***
     * Searches given directory for OSU files, sends each to readOSUVersion.
     * @param _dir Directory to search.
     */
    public static void getAllInPath(String _dir) {
        File dir = new File(_dir);
        String[] OSUFilter = new String[] {"osu"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, OSUFilter, true);

        for (File file : files) {
            //System.out.println("file: " + file.getPath());
            readOSUVersion(file.getPath());
        }
    }
}
