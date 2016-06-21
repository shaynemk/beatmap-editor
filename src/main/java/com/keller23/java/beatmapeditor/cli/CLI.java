package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;

import java.util.Scanner;

/***
 * Class for dealing with the CLI
 */
public class CLI {

    private static final int MENU_NUM_NUMOSU = 1;
    private static final int MENU_NUM_OSUPATH = 2;
    private static final int MENU_NUM_PROCESS = 5;
    private static final int MENU_NUM_EXIT = 99;

    private static String MENU_STR_NUMOSU = "1. [Single] / Multiple OSUs.";
    private static String MENU_STR_OSUPATH = "2. Path to OSU(s): [./]"; //todo Get the system local path separator instead of using only '/'.
    private static String MENU_STR_OTHER = "3. ** OTHER OPTIONS.";
    private static String MENU_STR_PROCESS = "5. Process OSU changes.";
    private static String MENU_STR_EXIT = "99. Discard and Exit.";

    /***
     * Returns the string to print to the screen after starting the app.
     * @return
     */
    public static String printHeader() {
        return System.lineSeparator() + "--- " + Strings.APP_NAME  + " v" + Strings.APP_VERSION + " ---" + System.lineSeparator();
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
        Boolean inMenu = true;
        Scanner sc = new Scanner(System.in);
        String tmp;

        printMenu();
        tmp = sc.nextLine();

        while (inMenu) {
            switch (Integer.valueOf(tmp)) {
                case MENU_NUM_NUMOSU:
                    // toggle single and multiple OSU mode
                    break;
                case MENU_NUM_OSUPATH:
                    // set osu path and add to MENU_STR_OSUPATH
                    break;
                case MENU_NUM_PROCESS:
                    // process changes desired.
                    break;
                case MENU_NUM_EXIT:
                    System.out.println(System.lineSeparator() + "Goodbye!");
                    inMenu = false;
                    break; // Do we need to use System.exit(0) here?
                default:
                    System.out.println("Please select one of the uncompleted options.");
                    break;
            }
        }
    }

    /***
     * Print interactive menu
     */
    private static void printMenu() {
        clearScreen();
        System.out.println(printHeader());
        System.out.println(MENU_STR_NUMOSU);
        System.out.println(MENU_STR_OSUPATH);
        System.out.println(MENU_STR_OTHER);
        System.out.println(MENU_STR_PROCESS);
        System.out.println(MENU_STR_EXIT);
        System.out.println(System.lineSeparator());
        System.out.print("Enter #: ");
    }

    /***
     * Screen clear helper
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
