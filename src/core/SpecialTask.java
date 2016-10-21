package core;

import java.util.ArrayList;
import java.util.Date;

public abstract class SpecialTask extends Task{

	private static final long serialVersionUID = 1L;

	public SpecialTask(String name, String description, Project father, ArrayList<Activity> root) {
		super(name, description, father, root);
	}
	public abstract Task getNextTask();
	public abstract Date getTaskStartDate();
	public abstract int getTimeLimit();
}
