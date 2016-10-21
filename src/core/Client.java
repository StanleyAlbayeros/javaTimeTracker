package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;



public class Client {

	private static Logger log = (Logger) LoggerFactory.getLogger(Client.class);
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {


		log.info("Starting Main");
		String option = Interface.menuScreen(scanner);
		log.info("Read option: " + option);

		if ((option == "2") || (option == "3")) {
			Project newRoot = new Project();
			setRoot(newRoot);
		}


		switch (option) {
			case "1":

				log.info("Loading saved state");
				setRoot(loadFile(scanner));
				Interface.printTable(root);
				return;

			case "2":

				log.info("Running test A1");
				Interface t2 = new Interface();
				testA1(t2);

				break;

			case "3":
				log.info("Running test A2");
				Interface t3 = new Interface();
				testA2(t3);

				break;

			case "4":

				log.info("Printing last State");
				Project loadedState = new Project();
				loadedState = SerializeData.loadData("tempState");
				setRoot(loadedState);		System.out.println("\n");
				System.out.println("Name\t" + "\tStart Date\t" + "\t\tEnd Date\t" + "\t\tLength\t");
				System.out.println(
						"_____________________________________________________________________________________________\n");
				Interface.printTable(root);

				return;

			case "5":
				log.info("Exiting the program");
				return;

			default:
				log.info("Invalid character.");
				return;
		}
		
		SerializeData.saveData(root, "lastCompletedState");
		scanner.close();
		return;
	}

	private static Project loadFile(Scanner scanner) throws IOException {
		System.out.println("********Enter the filename.bin********");
		String filename = "";
		filename = scanner.nextLine();
		Project loadedRoot = SerializeData.loadData(filename);
		return loadedRoot;
	}

	private static void testA1(Interface t) {
		Project p1 = new Project("p1", "project root", root, root.getActivityList());

		Task t3 = new BasicTask("t3", "root project task", p1, p1.getActivityList());

		Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());

		@SuppressWarnings("unused") // remove later
		Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());

		Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());

		try {
			Clock clock = Clock.getInstance();

			clock.addObserver(t);

			clock.start();

			t3.startTask("interval", "task 3 interval");
			Thread.sleep(3000);
			t3.stopTask();
			Thread.sleep(2000);
			Thread.sleep(7000);
			t2.startTask("interval", "task 2 interval");
			Thread.sleep(10000);
			t2.stopTask();
			t3.startTask("interval", "task 3 interval");
			Thread.sleep(2000);
			t3.stopTask();
			clock.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void testA2(Interface t) {
		Project p1 = new Project("p1", "project root", root, root.getActivityList());

		Task t3 = new BasicTask("t3", "root project task", p1, p1.getActivityList());

		Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());


		Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());

		Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());

		try {
			Clock clock = Clock.getInstance();

			clock.addObserver(t);

			clock.start();

			t3.startTask("interval", "task 3 interval");
			Thread.sleep(4000);
			t2.startTask("interval", "task 2 interval");
			Thread.sleep(2000);
			t3.stopTask();
			Thread.sleep(2000);
			t1.startTask("interval", "task 1 interval");
			Thread.sleep(4000);
			t1.stopTask();
			t2.stopTask();
			Thread.sleep(4000);
			t3.startTask("interval", "task 3 interval");
			Thread.sleep(2000);
			t3.stopTask();
			clock.stop();
		} catch (Exception e) {
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

