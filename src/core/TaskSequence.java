package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class TaskSequence extends SpecialTask  implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger log = (Logger) LoggerFactory.getLogger(TaskSequence.class);
	
	private Task nextTask;

	public TaskSequence(String name, String description, Project father, ArrayList<Activity> root, Task nnextTask){
		super(name, description, father, root, "TaskSequence");

		nextTask = nnextTask;
	}
	
	@Override
	public String getName(){
		String myName = super.getName() + " task sequence.";
		return myName;
	}
	
	@Override
	public void stopTask() {
		if (nextTask == null){
			super.stopTask();
		} else {
			nextTask.startTask(nextTask.getName(), nextTask.getDescription());
			super.stopTask();
			log.info("Task: " + getName() + " stopped");
		}
	}
	
	@Override
	public Task getNextTask() {
		return nextTask;
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
