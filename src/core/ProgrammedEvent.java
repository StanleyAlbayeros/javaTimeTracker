package core;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ProgrammedEvent extends SpecialTask implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startingDate;
	
	public ProgrammedEvent(String name, String description, Project father, ArrayList<Activity> root, Date nstartingDate){
		super(name, description, father, root, "ProgrammedEvent");

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
