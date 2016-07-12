package com.keller23.java.beatmapeditor.ops;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/***
 * FileOps - Class containing some methods for file operations.
 */
public class FileOps {

    /***
     * List all files in current/sub dir.
     * @throws IOException Explanation
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
     * @throws IOException Explanation
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
     * @throws IOException Explanation
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
     * @throws IOException Explanation
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
     * @throws IOException Explanation
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
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getLocalizedMessage().split("\n")[0]); // try to get only first line //FIXME
            // System.exit(1); // non zero to indicate error.
        }

        //System.out.println("\n---- " + filePath.split("\\\\")[filePath.split("\\\\").length - 1] + ":");

        System.out.println("\n---- Found OSU: " + filePath.split("\\\\")[filePath.split("\\\\").length - 1] + ":");

        //osuVersion.forEach(System.out::println);
        //difficultyOptions.forEach(System.out::println);
    }

    /***
     * New method for reading the OSU given
     * @param filePath Path to OSU
     */
    public static void readFile(final String filePath) {
        // Create OSU container, parse .osu file.
        OSU osuFile = new OSU(filePath);
        printFileBits(osuFile);
    }

    /***
     * Print out the properties extracted from the OSU file.
     * @param osuFile OSU File object
     */
    private static void printFileBits(final OSU osuFile) {
        // Print file version
        if (osuFile.getVersion() != null && !osuFile.getVersion().isEmpty()) {
            System.out.println("OSU File Version: " + osuFile.getVersion());
        } else {
            System.out.println("OSU File Version is not stored as it should be.");
        }

        // Print properties
        osuFile.getProperties().forEach((k, v) -> {
            if (!k.startsWith("osu file version v")) {
                System.out.println(k + ": " + v);
            }
        });
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

    /***
     * Walk path and modify all OSU files.
     * @param rootDir Directory to walk
     */
    public static void walkPath(final String rootDir) {
        try {
            Files.walk(Paths.get(rootDir))
            .filter(f -> f.toFile().isFile() && f.toFile().getAbsolutePath().endsWith(".osu"))
            .forEach(file -> {

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Parses the batchOptions.
     * @param options Map of batchOptions.
     * @return true if batch mode is enabled, otherwise false.
     */
    private static boolean checkOptions(final Map<String, String> options) {
        for (String option : options.values()) {
            if (!option.equals("0")) return true;
        }

        return false;
    }

    /**
     * Get all the non empty lines from all the files with the specific extension, recursively.
     *
     * @param path      the path to start recursion
     * @param batchOptions stores batch mode options
     */
    public static /*List<String>*/ void readAllLineFromAllFilesRecursively(final String path, final Map<String, String> batchOptions/*, final String extension*/) {
        final List<String> lines = new ArrayList<>();
        try (final Stream<Path> pathStream = Files.walk(Paths.get(path), FileVisitOption.FOLLOW_LINKS)) {
            pathStream
                    .filter((p) -> !p.toFile().isDirectory() && !p.toFile().getAbsolutePath().endsWith(".tmp.osu") && p.toFile().getAbsolutePath().endsWith(".osu" /*extension*/))
                    .forEach(p -> fileLinesToList(p, lines, batchOptions));
        } catch (final IOException e) {
            //LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        //return lines;
    }

    /***
     * some other method
     * @param file the file
     * @param lines file lines
     * @param batchOptions map of options for batch mode
     */
    private static void fileLinesToList(final Path file, final List<String> lines, final Map<String, String> batchOptions) {
        System.out.println("--- Processing " + file.toString());
        final String tFile = file.toFile().getAbsolutePath().substring(0, file.toFile().getAbsolutePath().length() - 4) + ".tmp.osu";
        //System.out.println(tFile);
        try (Stream<String> stream = Files.lines(file, Charset.defaultCharset())) {
            stream
                    /*.map(String::trim)*/
                    /*.filter(s -> !s.isEmpty())*/
                    .forEach(lines::add);

            if (!lines.isEmpty()) { // If is was not an empty file...
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(tFile, false));

                    String tmpLine, tmpInput, tmpOption, newOption;
                    String[] tSplit;
                    Scanner sc = new Scanner(System.in);
                    for (int i = 0; i < lines.size(); i++) {
                        tmpLine = lines.get(i);
                        if (tmpLine.contains("HPDrainRate") || tmpLine.contains("CircleSize") || tmpLine.contains("OverallDifficulty") || tmpLine.contains("ApproachRate")) {
                            if (checkOptions(batchOptions)) {
                                tSplit = tmpLine.split(":");
                                tmpOption = batchOptions.get(tSplit[0]);
                                newOption = (Integer.parseInt(tmpOption) > 0) ? tmpOption : tSplit[1];
                            } else {
                                System.out.println(System.lineSeparator() + tmpLine);
                                tSplit = tmpLine.split(":");
                                System.out.print("Found option '" + tSplit[0] + "'. Enter new setting? [y/N] ");
                                tmpInput = sc.nextLine();
                                switch (tmpInput.toLowerCase()) {
                                    case "y":
                                        System.out.print(System.lineSeparator() + "Enter new setting: ");
                                        newOption = sc.nextLine();
                                        if (Integer.parseInt(newOption) < 1 || Integer.parseInt(newOption) > 10) {
                                            System.out.println("ERROR: Option is out of bounds. (1 - 10)" + System.lineSeparator()
                                                    + "Resetting option to original. (" + tSplit[1] + ")");
                                            newOption = tSplit[1];
                                        }
                                        break;
                                    case "n": // "n" is the default option, so just let it bleed down to default since it will be the same outcome.
                                    default:
                                        newOption = tSplit[1];
                                }
                            }

                            pw.println(tSplit[0] + ":" + newOption);
                        } else {
                            pw.println(tmpLine);
                        }
                    }

                    pw.close();
                    lines.clear();
                    System.out.println();

                    // Move original to .bak
                    Files.move(file, file.resolveSibling(file.toFile().getName() + ".bak"), REPLACE_EXISTING);

                    // Move new file into production
                    Path tmpPath = Paths.get(tFile);
                    Files.move(tmpPath, tmpPath.resolveSibling(file.toFile().getName()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File (" + file + ") was empty.");
            }
        } catch (final Exception e) {
            //LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
