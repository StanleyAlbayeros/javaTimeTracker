package core;

import java.util.ArrayList;
import java.util.Date;

public class ProgramedTasks extends SpecialTask{

	private static final long serialVersionUID = 1L;

	private Date startingDate;

	public ProgramedTasks(String name, String description, Project father, ArrayList<Activity> root, Date nstartingDate){
		super(name, description, father, root);
		startingDate = nstartingDate;
	}
	
	@Override
	public String getDescription(){
		String myDescription = super.getDescription() + " programmed task.";
		return myDescription;
	}

	@Override
	public void startTask(String name, String description, Clock clock) {
		super.startTask(name, description, clock);
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
