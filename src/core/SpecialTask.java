package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class SpecialTask extends Task implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Special task decorator constructor, delegates the implementation to Task
	 * 
	 * @param name
	 * @param description
	 * @param father
	 * @param root
	 * @param taskType
	 */
	public SpecialTask(String name, String description, Project father, ArrayList<Activity> root, String taskType) {
		super(name, description, father, root, taskType);
	}
	
	/**
	 * No arg constructor used to serialize
	 */
	public SpecialTask(){
		
	}
	
	public abstract Task getNextTask();
	public abstract Date getTaskStartDate();
	public abstract int getTimeLimit();
}
