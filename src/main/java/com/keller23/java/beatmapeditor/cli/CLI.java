package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/***
 * Class for dealing with the CLI
 */
public class CLI {

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
     * @return Returns string containing the header, including app name and version.
     */
    public static String printHeader() { // TODO: Figure out how to burn the full version + build into the code at compile.
        return System.lineSeparator() + "--- " + Strings.APP_NAME  + " v" + Strings.APP_VERSION + " ---" + System.lineSeparator();
    }

    /***
     * Print credits due to authors of content
     */
    public static String printCredits() {
        return "Idea/Graphics: Alex Hornish" + System.lineSeparator()
                + "Code Impl/Jenkins: Shayne Keller";
    }

    /***
     * Returns the string to print that should be printed to the console.
     * @return Help Menu
     */
    public static String printHelp() {
        return    "--help\t\t\t\t\tThis page, also default." + System.lineSeparator()
                + "--readVersion \"path/to/OSU\"\t\tOutput version of OSU." + System.lineSeparator()
                + "--read \"path/to/OSU\"\t\t\tPrint all properties of OSU file that we are reading in.";
    }

    /***
     * Interactive CLI Mode
     */
    public static void interactiveMode() {
        // Get things initialized
        Boolean inMenu = true;
        Scanner sc = new Scanner(System.in);
        String tmp;
        initOptions();

        while (inMenu) { // Keep displaying the menu until we want to get out.
            // Clear screen, print menu, get input, blah blah blah
            printMenu();
            tmp = sc.nextLine();

            // figure out what the user entered and wants to do.
            switch (Integer.valueOf(tmp)) {
                case MENU_NUM_NUMOSU:
                    if (options.get("NUMOSU").equals("single")) {
                        options.put("NUMOSU", "multiple");
                        options.put("OSUNAME", "*.osu");
                    } else {
                        options.put("NUMOSU", "single");
                    }
                    break;
                case MENU_NUM_OSUPATH:
                    // set osu path and add to MENU_STR_OSUPATH
                    break;
                case MENU_NUM_OSUNAME:
                    // set osuname option
                    break;
                case MENU_NUM_PROCESS:
                    // process changes desired.
                    break;
                case MENU_NUM_EXIT:
                    System.out.println(System.lineSeparator() + "Goodbye!");
                    inMenu = false;
                    break; // Should we use System.exit(0) here?
                default:
                    System.out.println("Please select one of the uncompleted options.");
                    break;
            }
        }
    }

    /***
     * Init the interactive options map
     */
    private static void initOptions() {
        options.put("NUMOSU", "single");
        options.put("OSUPATH", "./");
        options.put("OSUNAME", "");
    }

    /***
     * Fix the Option strings.
     */
    private static void fixOptions() {
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
        MENU_STR_OSUNAME = "3. OSU Filename: [" + options.get("OSUNAME") + "]";
    }

    /***
     * Print interactive menu
     */
    private static void printMenu() {
        fixOptions();
        clearScreen();
        System.out.println(printHeader());
        System.out.println(MENU_STR_NUMOSU);
        System.out.println(MENU_STR_OSUPATH);
        System.out.println(MENU_STR_OSUNAME);
        System.out.println(MENU_STR_OTHER);
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
}
