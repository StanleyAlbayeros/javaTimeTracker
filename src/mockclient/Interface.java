package mockclient;

import core.Activity;
import core.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class Interface implements Observer {

  private static String division =
      "_____________________________________________________________"
      + "________________________________\n";


  /* 
   * Updates the observable.
   * 
   * 
   */
  public void update(Observable observable, Object date) {
    System.out.println("\n");
    System.out.println("Name\t" + "\tStart Date\t" + "\t\tEnd Date\t" + "\t\tLength\t");
    System.out.println(division);
    printTable(Client.root);
  }

  /**
   * Outputs the current project tree and it's details to the console
   * 
   * @param root project tree root.
   */
  public static void printTable(Project root) {

    ArrayList<Activity> hoja = root.getActivityList();
    Iterator<Activity> iter = hoja.iterator();

    while (iter.hasNext()) {
      Activity tempA = iter.next();
      if (tempA.getLength() == 0) {
        System.out.println(tempA.getName() + "\t\t" + "Not yet started" + "\t\t\t"
            + "Not yet started" + "\t\t\t\t");
      } else {
        System.out.println(tempA.getName() + "\t" + tempA.getStartDate() + "\t" + tempA.getEndDate()
            + "\t\t" + tempA.getLength() / 1000 + "seg");
      }

      if (tempA.getClass() == Project.class) {
        printTable((Project) tempA);
      }
    }
  }

  /**
   * Prints the main menu screen and asks for an option input from the console.
   * 
   * @param scanner single scanner to read console input
   * @return selected option
   * @throws IOException Input exception
   */
  public static String menuScreen(Scanner scanner) throws IOException {
    System.out.println("********MENU***********");
    System.out.println("1 - Load saved state.");
    System.out.println("2 - Run test A1.");
    System.out.println("3 - Run test A2.");
    System.out.println("4 - Run Decorator test: Programmed Event.");
    System.out.println("5 - Run Decorator test: Task Sequence.");
    System.out.println("6 - Run Decorator test: Timed Task.");
    System.out.println("7 - Print previous state.");
    System.out.println("8 - Generate simplified TXT report.");
    System.out.println("9 - Exit.");
    System.out.println(division);


    String option = scanner.nextLine();
    return option;

  }

  /**
   * Prints the exit screen.
   */
  public static void exitScreen() {
    System.out.println(division);
    System.out.println(division);
    System.out.println("\t\t\t" + "--------------Goodbye--------------");
    System.out.println(division);
    System.out.println(division);

  }

  /**
   * Transitions between the main menu and an option execution.
   * 
   * @param option option to print.
   */
  public static void menuTransition(String option) {
    switch (option) {
      case "1":

        System.out.println(division);
        System.out.println("Loading file");
        System.out.println(division);
        break;

      case "2":

        System.out.println(division);
        System.out.println("Starting test A1");
        System.out.println(division);
        break;

      case "3":

        System.out.println(division);
        System.out.println("Starting test A2");
        System.out.println(division);
        break;

      case "4":

        System.out.println(division);
        System.out.println("Starting Decorator test: Programmed Event");
        System.out.println(division);
        break;

      case "5":

        System.out.println(division);
        System.out.println("Starting Decorator test: Task Sequence");
        System.out.println(division);
        break;

      case "6":

        System.out.println(division);
        System.out.println("StartingDecorator test: Timed Task");
        System.out.println(division);
        break;

      case "7":
        System.out.println(division);
        System.out.println("Loading last known state");
        System.out.println(division);
        break;

      case "8":
        System.out.println(division);
        System.out.println("Generating simplified TXT file");
        System.out.println(division);
        return;

      case "9":
        System.out.println(division);
        System.out.println("Goodbye");
        System.out.println(division);
        return;

      default:
        break;
    }
  }
}
