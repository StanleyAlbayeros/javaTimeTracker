package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BasicTask extends Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BasicTask(String name, String description, Project father, ArrayList<Activity> root) {
		super(name, description, father, root, "BasicTask");
	}

	public BasicTask() {

	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public Date getTaskStartDate() {
		return null;
	}

	@Override
	public int getTimeLimit() {
		return 0;
	}


}
