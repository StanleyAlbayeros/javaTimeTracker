package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class BasicTask extends Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = (Logger) LoggerFactory.getLogger(BasicTask.class);
	
	public BasicTask(String name, String description, Project father, ArrayList<Activity> root) {
		super(name, description, father, root);
	}

	public BasicTask() {

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
		// TODO Auto-generated method stub
		return 0;
	}


}
