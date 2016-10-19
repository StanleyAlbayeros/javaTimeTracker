package core;
import java.io.Serializable;
import java.util.ArrayList;

public class BasicTask extends Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicTask(String name, String description, Project father, ArrayList<Activity> root) {
		super(name, description, father, root);
	}
}
