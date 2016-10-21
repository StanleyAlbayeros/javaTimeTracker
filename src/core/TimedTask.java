package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TimedTask extends SpecialTask implements Serializable {
	
	private int timeLimit;
	
	public TimedTask(String name, String description, Project father, ArrayList<Activity> root, int ntimeLimit) {
		super(name, description, father, root, "TimedTask");

		timeLimit = ntimeLimit;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName(){
		String myName = super.getName() + " timed task.";
		return myName;
	}
	
	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getTaskStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTimeLimit() {
		
		return timeLimit;
	}

}
