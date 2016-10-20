package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {
    	
   	 	Interface t = new Interface();
   	 	
   	 	String option = t.menuScreen();
 
   	 	//TODO
//   	 	System.out.println("\n option pre case: " + option);
   	 	
		switch (option) {
			case "1":

		   	 	System.out.println("case 1");
				loadFile();
				break;

			case "2":

				System.out.println("case 2");
				testA1(t);

				break;

			case "3":
				testA2(t);

				break;

			case "4":
				root = SerializeData.loadData("lastState");
				Interface.printTable(root);
				System.out.println("Printing last State");
				
				break;
				
			case "5":
				
				return;

			default:

				System.out.println("Invalid character.");
				break;
		}

   	 	Interface.printTable(root);  
   	 	SerializeData.saveData(root, "lastState");
   	 	return;
   	 	
    }

	private static void loadFile() throws IOException {
		System.out.println("********Enter the filename.bin********");
		 Scanner scanner = new Scanner(System.in);
	        String filename = scanner.nextLine();
	        scanner.close();	    	    
		root = SerializeData.loadData(filename);
	}

	private static void testA1(Interface t) {
		Project p1 = new Project("p1", "project root", root, root.getActivityList());
        
        Task t3 = new BasicTask ("t3", "root project task", p1, p1.getActivityList());
        
        Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());
        
        @SuppressWarnings("unused") //remove later
		Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());
        
        Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());
	
   	 	try {
			Clock clock = new Clock();
			
			clock.addObserver(t);
			
			clock.start();
			
			t3.stopwatch("interval", "task 3 interval", clock);
			Thread.sleep(3000);
			t3.stopTask(clock);
			Thread.sleep(2000);
			Thread.sleep(7000);
			t2.stopwatch("interval", "task 2 interval", clock);
			Thread.sleep(10000);
			t2.stopTask(clock);
			t3.stopwatch("interval", "task 3 interval", clock);
			Thread.sleep(2000);
			t3.stopTask(clock);
			clock.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}  	 	
	}

	private static void testA2(Interface t) {
		Project p1 = new Project("p1", "project root", root, root.getActivityList());
        
        Task t3 = new BasicTask ("t3", "root project task", p1, p1.getActivityList());
        
        Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());
        
        @SuppressWarnings("unused") //remove later
		Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());
        
        Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());
	
   	 	try {
			Clock clock = new Clock();
			
			clock.addObserver(t);
			
			clock.start();
			
			t3.stopwatch("interval", "task 3 interval", clock);
			Thread.sleep(3000);
			t3.stopTask(clock);
			Thread.sleep(2000);
			Thread.sleep(7000);
			t2.stopwatch("interval", "task 2 interval", clock);
			Thread.sleep(10000);
			t2.stopTask(clock);
			t3.stopwatch("interval", "task 3 interval", clock);
			Thread.sleep(2000);
			t3.stopTask(clock);
			clock.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}	 	
	}
    
	public static Project root = SerializeData.loadData("lastState");
	
	public Project getRoot() {
		return root;
	}

	public static void setRoot(Project nroot) {
		Client.root = nroot;
	}
}

