package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class SerializeData {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Client.class);
	
	public static void saveData(Project root, String name) {
		String filename = name;
		filename = filename + ".bin";
		File file = new File(filename);
		log.info("Trying to save the program's state in: " + filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Failed to create the file: " + filename);
				e.printStackTrace();
				
			}
		}

		log.info("File " + filename + " created succesfully, saving");
		try {

			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(root);
			oos.close();
			fos.close();

		} catch (IOException e) {
			log.error("Failed to save the program's state in: " + filename);
			e.printStackTrace();

		}

		log.info("Program's state successfully saved in: " + filename);
	}

	public static Project loadData(String name) {
		String filename = name;
		filename = filename + ".bin";
		File file = new File(filename);

		log.info("Trying to load the program's state from: " + filename);
		if (!file.exists()) {

			log.error("Failed read the file: " + filename + ", it does not exist");
		}
		Project root = null;
		try {

			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			root = (Project) ois.readObject();

			ois.close();
			fis.close();

		} catch (IOException ioe) {
			// TODO
			// System.out.print("File not found, creating a new project");
			root = new Project();

		} catch (ClassNotFoundException ce) {

			log.error("Class not found ");
			ce.printStackTrace();

		}
		log.error("Program's state successfully loaded from: " + filename);

		return root;

	}

}
