package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;
import com.keller23.java.beatmapeditor.ops.FileOps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/***
 * Class for dealing with the CLI
 */
public class CLI {

    private static final int defaultSleep = 2000;

    private static final int MENU_NUM_NUMOSU = 1;
    private static final int MENU_NUM_OSUPATH = 2;
    private static final int MENU_NUM_OSUNAME = 3;
    private static final int MENU_NUM_PROCESS = 9;
    private static final int MENU_NUM_EXIT = 99;

    private static String MENU_STR_NUMOSU = "1. [Single] / Multiple OSUs.";
    private static String MENU_STR_OSUPATH = "2. Path to OSU(s): [./]"; //todo Get the system local path separator instead of using only '/'.
    private static String MENU_STR_OSUNAME = "3. OSU Filename: []";
    private static String MENU_STR_OTHER = "4. ** OTHER OPTIONS **";
    private static String MENU_STR_PROCESS = "9. Process OSU changes.";
    private static String MENU_STR_EXIT = "99. Discard and Exit.";

    private static Map<String, String> options = new HashMap<>();

    /***
     * Returns the string to print to the screen after starting the app.
     * @return Returns string containing the header.
     */
    public static String getHeader() {
        // TODO: Figure out how to include BUILD_NUMBER at compile.
        return System.lineSeparator() + "--- " + Strings.APP_NAME  + " v"
                + Strings.APP_VERSION + " ---" + System.lineSeparator();
    }

    public static void printHeader() {
        System.out.println(getHeader());
    }

    /***
     * Print credits due to authors of content
     */
    public static String getCredits() {
        return "Idea/Graphics: Alex Hornish" + System.lineSeparator()
                + "Code Impl/Jenkins: Shayne Keller";
    }

    public static void printCredits() {
        System.out.println(getCredits());
    }

    /***
     * Returns the string to print that should be printed to the console.
     * @return Help Menu
     */
    public static String getHelp() {
        return    "--help\t\t\t\t\tThis page, also default." + System.lineSeparator()
                + "--readVersion \"path/to/OSU\"\t\tOutput version of OSU." + System.lineSeparator()
                + "--read \"path/to/OSU\"\t\t\tPrint all properties of OSU file that we are reading in." + System.lineSeparator()
                + "--auto\t\t\t\tAutomatic mode will scan all sub dirs and asks if you want to change every difficulty option in every OSU."
                + "Automatic mode will autosave the original OSU with a .bak extension before writing the new OSU." + System.lineSeparator()
                + "\t--path path/to/directory\t\t\tRequired for Automatic mode, no directory assumptions made." + System.lineSeparator()
                + "\t[--batch]\t\tIf passed, Automatic mode will ask for one set of options to change all OSU's to. "
                + "Only use if you want every OSU under given path to have the same options.";
    }

    public static void printHelp() {
        System.out.println(getHelp());
    }

    /***
     * Automatic Mode
     * @param args Arguments from command line.
     */
    public static void automaticMode(final String[] args) {
        boolean batchMode = false;
        String path = "nope", tmp;
        Scanner sc = new Scanner(System.in);
        Map<String, String> batchOptions = new HashMap<String, String>();
        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--path":
                    i += 1;
                    if (i < args.length) {
                        path = args[i];
                    } else {
                        System.out.println("ERROR: --path given, but not specified. Not enough arguments.");
                    }
                    break;
                case "--batch":
                    batchMode = true;
                    System.out.println("Batch mode activated.");
                    System.out.println("Please enter new options when prompted. If you wish to keep a setting as it was, enter '0'." + System.lineSeparator());

                    batchOptions.put("HPDrainRate", "0");
                    batchOptions.put("CircleSize", "0");
                    batchOptions.put("OverallDifficulty", "0");
                    batchOptions.put("ApproachRate", "0");

                    for (String option : batchOptions.keySet()) {
                        System.out.print(option + ": ");
                        tmp = sc.nextLine();
                        while (Integer.parseInt(tmp) > 10 || Integer.parseInt(tmp) < 0) {
                            System.out.println("ERROR: Entry out of bounds (Entry: " + tmp + ", Bounds: 0-10). New Option: ");
                            tmp = sc.nextLine();
                        }
                        batchOptions.put(option, tmp);
                    }
                    break;
                default:
                    System.out.println("Unknown argument given. (" + args[i] + ")");
            }
        }
        if (!path.equals("nope")) {
            FileOps.readAllLineFromAllFilesRecursively(path, batchOptions);
        } else {
            System.out.println("ERROR: Could not resolve path. No path given.");
        }
    }

    /***
     * Interactive CLI Mode
     */
    public static void interactiveMode() {
        // Get things initialized and ready to go
        Boolean inMenu = true;
        Scanner sc = new Scanner(System.in);
        String tmp;
        initOptions();

        // Keep displaying the menu until we want to get out.
        while (inMenu) {
            // Clear screen, print menu, get input, blah blah blah
            printMenu();
            tmp = sc.nextLine();

            // figure out what the user entered and wants to do.
            try {
                switch (Integer.valueOf(tmp)) {
                    case MENU_NUM_NUMOSU:
                        if (options.get("NUMOSU").equals("single")) {
                            printWIP();
                            // Use the following code after enabling Multi mode.
                            /*options.put("NUMOSU", "multiple");
                            options.put("OSUNAME", "*.osu");*/
                        } else {
                            options.put("NUMOSU", "single");
                        }
                        break;
                    case MENU_NUM_OSUPATH:
                        // set osu path and add to MENU_STR_OSUPATH
                        System.out.print("Please enter the path to the OSU(s): ");
                        options.put("OSUPATH", sc.nextLine());
                        if (options.get("OSUPATH").contains("*")) {
                            options.put("OSUPATH", "./");
                            System.out.println("OSU Path cannot contain a wildcard.");
                        }
                        break;
                    case MENU_NUM_OSUNAME:
                        if (options.get("NUMOSU").equals("single")) {
                            System.out.print("Please enter name of OSU file to open: ");
                            tmp = sc.nextLine();
                            if (!tmp.contains("*")) {
                                options.put("OSUNAME", tmp);
                            } else {
                                System.out.println(System.lineSeparator()
                                        + "Filename cannot contain a wildcard.");
                                sleep();
                            }
                        } else {
                            System.out.println("OSU Name is locked to *.osu "
                                    + "when in Multiple mode"
                                    + "so that we grab all OSUs in the (sub)folders.");
                            sleep();
                        }
                        break;
                    case MENU_NUM_PROCESS:
                        // process changes desired, but make sure required info has been given first.
                        if (!options.get("OSUNAME").isEmpty() || options.get("NUMOSU").equals("multiple")) {
                            getOSU();
                            inMenu = false;
                        } else {
                            System.out.println(System.lineSeparator()
                                    + "Please enter something for the OSU name.");
                            sleep();
                        }
                        break;
                    case MENU_NUM_EXIT:
                        System.out.println(System.lineSeparator() + "Goodbye!");
                        inMenu = false;
                        break; // Should we use System.exit(0) here?
                    default:
                        System.out.println("Please select one of the uncompleted options.");
                        sleep();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(System.lineSeparator()
                        + "Please enter a number corresponding the options listed.");
            }
        }
    }

    /***
     * Get the desired OSU file.
     */
    private static void getOSU() {
        //printWIP();
        String osuPath;

        osuPath = options.get("OSUPATH");
        if (!osuPath.endsWith("/") && !osuPath.endsWith("\\")) {
            //osuPath = osuPath + "/";
            if (osuPath.contains("\\")) {
                osuPath = osuPath + "\\";
            } else if (osuPath.contains("/")) {
                osuPath = osuPath + "/";
            }
        }
        osuPath = osuPath + options.get("OSUNAME");

        //System.out.println("===== Looking for: " + osuPath+ " =====");
        FileOps.readFileVersion(osuPath);

        //FIXME Change this to to use an OSU object.
    }

    /***
     * Init the interactive options map
     */
    private static void initOptions() {
        options.put("NUMOSU", "single");
        options.put("OSUPATH", "./");
        options.put("OSUNAME", "");
        //options.put("SHOWPROCESS", "false");
    }

    /***
     * Fix the Option strings.
     */
    private static void fixOptionStrings() {
        // NUMOSU
        if (options.get("NUMOSU").equals("multiple")) {
            MENU_STR_NUMOSU = "1. Single / [Multiple] OSUs.";
        } else {
            MENU_STR_NUMOSU = "1. [Single] / Multiple OSUs.";
        }

        // OSUPATH
        if (options.get("OSUPATH").equals("./")) {
            if (options.get("NUMOSU").equals("multiple")) {
                MENU_STR_OSUPATH = "2. Path to OSU(s): [./**/]";
            } else {
                MENU_STR_OSUPATH = "2. Path to OSU(s): [./]";
            }
        } else {
            MENU_STR_OSUPATH = "2. Path to OSU(s): [" + options.get("OSUPATH") + "]";
        }

        // OSUNAME
        if (!options.get("OSUNAME").isEmpty() && !options.get("OSUNAME").toLowerCase().endsWith("osu")) {
            // OSUNAME does not have 'osu' at the end.
            if (!options.get("OSUNAME").endsWith(".")) {
                // OSUNAME does not have a '.' at the end.
                options.put("OSUNAME", options.get("OSUNAME") + ".");
            }
            options.put("OSUNAME", options.get("OSUNAME") + "osu");
        } else if (options.get("NUMOSU").equals("single") && options.get("OSUNAME").equals("*.osu")) {
            options.put("OSUNAME", "");
        }
        MENU_STR_OSUNAME = "3. OSU Filename: [" + options.get("OSUNAME") + "]";
    }

    /***
     * Print interactive menu
     */
    private static void printMenu() {
        fixOptionStrings();
        clearScreen();
        System.out.println(getHeader());
        System.out.println(MENU_STR_NUMOSU);
        System.out.println(MENU_STR_OSUPATH);
        System.out.println(MENU_STR_OSUNAME);
        //System.out.println(MENU_STR_OTHER);
        System.out.println(MENU_STR_PROCESS);
        System.out.println(MENU_STR_EXIT);
        System.out.println(System.lineSeparator());
        System.out.print("Enter #: ");
    }

    /***
     * Screen clear helper -- Should be cross platform.
     */
    private static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                //Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Helper to print out a WIP message.
     */
    private static void printWIP() {
        System.out.println(System.lineSeparator() + "Not implemented quite yet, perhaps poke around elsewhere or exit for now? Sorry!");
        sleep();
    }

    /***
     * Delay helper function.
     * @param millis Milliseconds to sleep.
     */
    private static void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /***
     * Sleep overload to allow for using a default length.
     */
    private static void sleep() {
        try {
            Thread.sleep(defaultSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
