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

/***
 * FileOps - Class containing some methods for file operations.
 */
public class FileOps {

    //private Map<String> configs = null;

    /***
     * List all files in current/sub dir.
     * @throws IOException
     */
    public static void listFilesInDir() throws IOException {
        File dir = new File(".");

        System.out.println("Getting all files in " + dir.getPath() + "including subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(dir,
                TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
            //file.
        }
    }

    /***
     * Lists all files in given directory.
     * @param dir Directory to search in.
     * @throws IOException
     */
    public static void listFilesInDir(final String dir) throws IOException {
        File directory = new File(dir);

        System.out.println("Getting all files under: " + directory.getPath());
        List<File> files = (List<File>) FileUtils.listFiles(directory, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getPath());
        }
    }

    /***
     * Gets all files with given extensions in current/sub directory.
     * @param extensions String[] array of _extensions to filter inclusively.
     * @throws IOException
     */
    public static void getExtFilesInDir(final String[] extensions) throws IOException {
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

    /***
     * Get all OSU files in given/sub directories.
     * @param dir This is the root directory of the search for OSU files.
     * @throws IOException
     */
    public static void getOSUFilesInDir(final String dir) throws IOException {
        File file = new File(dir);
        String[] OSU = new String[] {"osu"};
        /*System.out.print("Getting all {");
        for (String ext : _extensions) {
            System.out.print(ext + ", ");
        }
        System.out.println("} files in " + dir.getPath());*/

        System.out.println("Searching for all *.osu under " + file.getPath() + "...");

        List<File> files = (List<File>) FileUtils.listFiles(file, OSU/*_extensions*/, true);
        for (File tmpFile : files) {
            System.out.println("file: " + tmpFile.getPath());
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
     * @param filePath Input path to the .OSU that is to be read
     */
    public static void readFileVersion(final String filePath) {
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

        System.out.println("\n---- " + filePath.split("\\\\")[filePath.split("\\\\").length - 1] + ":");
        osuVersion.forEach(System.out::println);
        difficultyOptions.forEach(System.out::println);
    }

    /***
     * Searches given directory for OSU files, sends each to readFileVersion.
     * @param dir Directory to search.
     */
    public static void getAllInPath(final String dir) {
        File directory = new File(dir);
        String[] OSUFilter = new String[] {"osu"};
        List<File> files = (List<File>) FileUtils.listFiles(directory, OSUFilter, true);

        for (File file : files) {
            //System.out.println("file: " + file.getPath());
            readFileVersion(file.getPath());
        }
    }
}
