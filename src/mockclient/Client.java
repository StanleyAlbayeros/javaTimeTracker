package mockclient;

import ch.qos.logback.classic.Logger;
import core.BasicTask;
import core.Clock;
import core.ProgrammedEvent;
import core.Project;
import core.SerializeData;
import core.Task;
import core.TaskSequence;
import core.TimedTask;
import reports.Formatting;
import reports.Report;
import reports.SimplifiedReport;
import reports.TxtFormatting;

import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Observer;
import java.util.Scanner;



public class Client {

  private static Logger log = (Logger) LoggerFactory.getLogger(Client.class);
  public static Scanner scanner = new Scanner(System.in);

  /**
   * Main method that imitates an actual user.
   * 
   * <p>
   * * This simulates a user clicking through an interface and using the implemented methods.
   * 
   */
  public static void main(String[] args) throws InterruptedException, IOException {


    log.info("Starting Main");
    String option = Interface.menuScreen(scanner);
    log.info("Read option: " + option);

    if ((option == "2") || (option == "3")) {
      Project newRoot = new Project();
      setRoot(newRoot);
    }

    Interface.menuTransition(option);
    switch (option) {
      case "1":

        log.info("Loading saved state");
        setRoot(loadFile(scanner));
        Interface.printTable(root);
        return;

      case "2":

        log.info("Running test A1");
        Interface inter1 = new Interface();
        testA1(inter1);

        break;

      case "3":
        log.info("Running test A2");
        Interface inter2 = new Interface();
        testA2(inter2);

        break;
      case "4":
        log.info("Running Decorator test: Programmed Event");
        Interface inter3 = new Interface();
        testProgrammedEvent(inter3);

        break;
      case "5":
        log.info("Running Decorator test: Task Sequence");
        Interface inter4 = new Interface();
        testTaskSequence(inter4);

        break;
      case "6":
        log.info("Running Decorator test: Timed Task");
        Interface inter5 = new Interface();
        testTimedTask(inter5);

        break;

      case "7":

        log.info("Printing last State");
        printLastState();

        return;

      case "8":
        log.info("Generating simple txt report");
        String filename = "";
        System.out.println(
            "Write the filename you wish to save: (press Enter for the default simpleTXTreport file"
            + "name)");
        filename = scanner.nextLine();
        System.out.println(
            "Write the saved state you want to load: (press Enter to load the default saved "
            + "A1 test.)");
        String loadBin = "";
        loadBin = scanner.nextLine();
        generateSimpleTxtReport(filename, loadBin);

        return;

      case "9":
        log.info("Exiting the program");
        Interface.exitScreen();
        return;

      default:
        log.info("Invalid character.");
        Interface.exitScreen();
        return;
    }

    Interface.exitScreen();
    SerializeData.saveData(root, "lastCompletedState");
    scanner.close();
    return;
  }



  /**
   * 
   */
  private static void printLastState() {
    Project loadedState = new Project();
    loadedState = SerializeData.loadData("tempState");
    setRoot(loadedState);
    System.out.println("\n");
    System.out.println("Name\t" + "\tStart Date\t" + "\t\tEnd Date\t" + "\t\tLength\t");
    System.out
        .println("_______________________________________________________________________________"
            + "______________\n");
    Interface.printTable(root);
  }



  /**
   * Calls the necessary methods to generate a simple txt format report.
   * 
   * @throws FileNotFoundException The specified file was not found.
   */
  private static void generateSimpleTxtReport(String filename, String loadBin)
      throws FileNotFoundException {
    if (loadBin.isEmpty()) {
      loadBin = "A1FinalState";
    }
    Project a1Project = SerializeData.loadData(loadBin);
    setRoot(a1Project);
    if (filename.isEmpty()) {
      filename = "simpleTXTreport.txt";
    }
    Formatting txt = new TxtFormatting(filename);
    String reportTitle = "A1 project report";
    Report txtReport = new SimplifiedReport(root, reportTitle, root.getActivityList());
    txtReport.writeReport(txt);
    log.info("Exiting the program");
    Interface.exitScreen();
  }



  private static Project loadFile(Scanner scanner) throws IOException {
    System.out.println("********Enter the filename.bin********");
    String filename = "";
    filename = scanner.nextLine();
    Project loadedRoot = SerializeData.loadData(filename);
    return loadedRoot;
  }

  private static void testA1(Interface testA1Interface) {
    Project p1 = new Project("p1", "project root", root, root.getActivityList());

    Task t3 = new BasicTask("t3", "root project task", p1, p1.getActivityList());

    Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());

    @SuppressWarnings("unused") // remove later
    Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());

    Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());

    try {
      Clock clock = Clock.getInstance();

      clock.addObserver(testA1Interface);

      clock.start();

      t3.startTaskInterval("interval", "task 3 interval");
      Thread.sleep(3000);
      t3.stopTaskInterval();
      Thread.sleep(2000);
      Thread.sleep(7000);
      t2.startTaskInterval("interval", "task 2 interval");
      Thread.sleep(10000);
      t2.stopTaskInterval();
      t3.startTaskInterval("interval", "task 3 interval");
      Thread.sleep(2000);
      t3.stopTaskInterval();
      clock.stop();
    } catch (Exception exception) {
      // TODO: handle exception
    }
  }

  private static void testA2(Interface testA2Interface) {
    Project p1 = new Project("p1", "project root", root, root.getActivityList());

    Task t3 = new BasicTask("t3", "root project task", p1, p1.getActivityList());

    Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());


    Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());

    Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());

    try {
      Clock clock = Clock.getInstance();

      clock.addObserver(testA2Interface);

      clock.start();

      t3.startTaskInterval("interval", "task 3 interval");
      Thread.sleep(4000);
      t2.startTaskInterval("interval", "task 2 interval");
      Thread.sleep(2000);
      t3.stopTaskInterval();
      Thread.sleep(2000);
      t1.startTaskInterval("interval", "task 1 interval");
      Thread.sleep(4000);
      t1.stopTaskInterval();
      t2.stopTaskInterval();
      Thread.sleep(4000);
      t3.startTaskInterval("interval", "task 3 interval");
      Thread.sleep(2000);
      t3.stopTaskInterval();
      clock.stop();
    } catch (Exception exception) {
      // TODO: handle exception
    }
  }

  private static void testTimedTask(Interface inter5) {
    Project p1 = new Project("p1", "project root", root, root.getActivityList());

    Task t3 = new TimedTask("t3", "root project task", p1, p1.getActivityList(), 8000);

    Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());


    Task t1 = new TimedTask("t1", "p2 task", p2, p2.getActivityList(), 4000);

    Task t2 = new TimedTask("t2", "p2 task", p2, p2.getActivityList(), 12000);

    try {
      Clock clock = Clock.getInstance();

      clock.addObserver(inter5);

      clock.start();

      t1.startTaskInterval("interval", "task 1 interval");
      t2.startTaskInterval("interval", "task 2 interval");
      t3.startTaskInterval("interval", "task 3 interval");
      Thread.sleep(26000);
      clock.stop();
    } catch (Exception exception) {
      // TODO: handle exception
    }

  }

  private static void testTaskSequence(Interface t5) {
    Project p1 = new Project("p1", "project root", root, root.getActivityList());
    Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());
    Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());
    Task t1 = new TaskSequence("t1", "root project task", p1, p1.getActivityList(), t2);


    try {
      Clock clock = Clock.getInstance();

      clock.addObserver(t5);

      clock.start();

      t1.startTaskInterval("interval", "task 1 interval");
      Thread.sleep(80000);
      t1.stopTaskInterval();
      Thread.sleep(5000);
      t1.stopTaskInterval();
      clock.stop();
    } catch (Exception exception) {
      // TODO: handle exception
    }
  }

  @SuppressWarnings("deprecation")
  private static void testProgrammedEvent(Interface interface1) {
    int timedifference = 2100;
    Date futureDate = new Date();
    futureDate.setSeconds(futureDate.getSeconds() + timedifference);
    Date t1StartDate = futureDate;

    Date futureDate2 = new Date();
    futureDate2.setSeconds(futureDate.getSeconds() + timedifference);
    Date t1EndDate = futureDate2;

    Project p1 = new Project("p1", "project root", root, root.getActivityList());


    Task t1 = new ProgrammedEvent("t3", "root project task", p1, p1.getActivityList(), t1StartDate,
        t1EndDate);

    try {
      Clock clock = Clock.getInstance();

      clock.addObserver(interface1);

      clock.start();

      t1.startTaskInterval("interval", "task 3 interval");
      Thread.sleep(8000);
      t1.stopTaskInterval();
      clock.stop();
    } catch (Exception exception) {
      // TODO: handle exception
    }
  }

  public static Project root = new Project();

  public Project getRoot() {
    return root;
  }

  public static void setRoot(Project nroot) {
    Client.root = nroot;
  }
}



/*
 * ///////////////////////////DEBUG REPORTS///////////////////////////////////////////// Project p1
 * = new Project("p1", "project root", root, root.getActivityList());
 * 
 * Task t3 = new BasicTask("t3", "root project task", p1, p1.getActivityList());
 * 
 * Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());
 * 
 * @SuppressWarnings("unused") // remove later Task t1 = new BasicTask("t1", "p2 task", p2,
 * p2.getActivityList());
 * 
 * 
 * Clock clock = Clock.getInstance();
 * 
 * Interface testA1Interface = new Interface(); clock.addObserver(testA1Interface);
 * 
 * clock.start();
 * 
 * t3.startTaskInterval("interval", "task 3 interval"); Thread.sleep(3000); t3.stopTaskInterval();
 * Thread.sleep(7000); Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());
 * t2.startTaskInterval("interval", "task 2 interval"); Thread.sleep(5000); t2.stopTaskInterval();
 * t3.startTaskInterval("interval", "task 3 interval"); Thread.sleep(2000); t3.stopTaskInterval();
 * clock.stop();
 * 
 * //////////////////////////////////////////////////////////////////////////////
 */

