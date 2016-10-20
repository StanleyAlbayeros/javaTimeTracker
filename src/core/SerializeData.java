package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeData {
	
	public static void saveData (Project root, String name){
		
		String filename = name;
		filename = filename + ".bin";
		File file = new File(filename);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try{
			
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(root);
			oos.close();
			fos.close();			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static Project loadData (String name){		
		String filename = name;
		filename = filename + ".bin";
		File file = new File(filename);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Project root = null;
		try {
			
			FileInputStream fis = new FileInputStream(file);
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
