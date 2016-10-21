package core;


import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class ProgrammedEvent extends SpecialTask{

	private static final long serialVersionUID = 1L;

	private Date startingDate;

	private static Logger log = (Logger) LoggerFactory.getLogger(ProgrammedEvent.class);
	
	public ProgrammedEvent(String name, String description, Project father, ArrayList<Activity> root, Date nstartingDate){
		super(name, description, father, root);
		startingDate = nstartingDate;
	}
	
	
	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public Date getTaskStartDate() {
		return startingDate;
	}

	@Override
	public int getTimeLimit() {
		return 0;
	}	
}
