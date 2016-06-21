package com.keller23.java.beatmapeditor.cli;

import com.keller23.java.beatmapeditor.lib.Strings;

import java.util.Scanner;

/***
 * Class for dealing with the CLI
 */
public class CLI {

    /***
     * Returns the string to print to the screen after starting the app.
     * @return
     */
    public static String printHeader() {
        return System.lineSeparator() + "--- " + Strings.APP_NAME  + ", v" + Strings.APP_VERSION + " ---" + System.lineSeparator();
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
        Scanner sc = new Scanner(System.in);
        String tmp;

        printMenu();
        tmp = sc.nextLine();

        switch (Integer.valueOf(tmp)) {
            case 1:
                //asdf
                break;
            case 2:
                //asdf
                break;
            case 3:
                //asdf
                break;
            case 4:
                //asdf
                break;
            case 99:
                System.out.println(System.lineSeparator() + "Goodbye!");
                break; // Do we need to use System.exit(0) here?
            default:
                System.out.println("Please select one of the uncompleted options.");
                break;
        }
    }

    /***
     * Print interactive menu
     */
    private static void printMenu() {
        clearScreen();
        System.out.println(printHeader());
        System.out.println("1. Single / Multiple OSUs.");
        System.out.println("2. Path to OSU(s):");
        System.out.println("3. ** OTHER OPTIONS.");
        System.out.println("4. Process OSU changes.");
        System.out.println("99. Exit.");
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
