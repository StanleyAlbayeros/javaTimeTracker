package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class Interface implements Observer {

    public void update(Observable observable, Object date) {
    	System.out.println("\n");
    	System.out.println("Name\t" + "\tStart Date\t" + "\t\tEnd Date\t" + "\t\tLength\t");
    	System.out.println("_____________________________________________________________________________________________\n");
    	printTable(Client.root);
    }

    public static void printTable(Project root){
        
        ArrayList<Activity> hoja = root.getActivityList();
        Iterator<Activity> iter = hoja.iterator();
       
        while(iter.hasNext()) {
        	Activity TempA = iter.next();
        	System.out.println(TempA.getName()+"\t"+TempA.getStartDate()+"\t"+TempA.getEndDate()+"\t\t"+TempA.getLength()/1000+"seg");
        	
            if (TempA.getClass() == Project.class) {
                printTable((Project)TempA);
            }
        }
    }
    
    public String  menuScreen() throws IOException {
    	System.out.println("********MENU***********");
    	System.out.println("1 - Load saved state");
    	System.out.println("2 - Run test A1");
    	System.out.println("3 - Run test A2");
    	System.out.println("4 - Load previous state");
    	System.out.println("5 - Exit ");
    	System.out.println("***********************");
    	
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        scanner.close();
    	return option;
    	
    }
    public static void menuTransition(String option){
    	switch (option) {
			case "1":

		    	System.out.println("***********************");
		   	 	System.out.println("Loading file");
		    	System.out.println("***********************");
				break;
				
			case "2":

		    	System.out.println("***********************");
		   	 	System.out.println("Starting test A1");
		    	System.out.println("***********************");
				
				break;
				
			case "3":

		    	System.out.println("***********************");
		   	 	System.out.println("Starting test A2");
		    	System.out.println("***********************");
				break;
				
			case "4":
		    	System.out.println("***********************");
		   	 	System.out.println("Goodbye");
		    	System.out.println("***********************");
				return;

			default:
				break;	
		}
    }
}