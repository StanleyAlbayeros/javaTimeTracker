package core;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class TimedTask extends SpecialTask{
	
	private int timeLimit;

	private static Logger log = (Logger) LoggerFactory.getLogger(TimedTask.class);
	
	public TimedTask(String name, String description, Project father, ArrayList<Activity> root, int ntimeLimit) {
		super(name, description, father, root);
		timeLimit = ntimeLimit;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
