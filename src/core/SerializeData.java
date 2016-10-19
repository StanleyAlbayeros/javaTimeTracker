package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeData {
	
	public static void saveData (Project root, String name){
		
		String filename = name.toLowerCase();
		filename = filename + ".bin";
		
		try{
			
			FileOutputStream fos = new FileOutputStream(name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(root);
			oos.close();
			fos.close();			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static Project loadData (String name){		
		String filename = name.toLowerCase();
		filename = filename + ".bin";
		Project root = null;
		try {
			
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			root = (Project) ois.readObject();
			
			ois.close();
			fis.close();
			
		} catch (IOException ioe){
			//TODO
//			System.out.print("File not found, creating a new project");
			root = new Project();
			
		} catch (ClassNotFoundException ce) {
			
			ce.printStackTrace();
			
		}
		
	return root;	
	
	}
	
}
