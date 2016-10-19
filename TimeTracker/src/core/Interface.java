package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;


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
    
    public static void menuScreen() {
    	
    	System.out.println("hola");
    	
    }
}