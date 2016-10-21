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

	/** Task sequence constructor
	 * 
	 * @param root: root object
	 * @param name: task instance name
	 * @param description: task instance descrpition
	 * @param father: father activity of the new task
	 * @param nnextTask: queued task
	 */
	public TaskSequence(String name, String description, Project father, ArrayList<Activity> root, Task nnextTask){
		super(name, description, father, root, "TaskSequence");

		nextTask = nnextTask;
	}
	/**
	 * No arg constructor used to serialize
	 */
	public TaskSequence(){
		
	}
	
	@Override
	public void stopTaskInterval() {
		if (nextTask == null){
			super.stopTaskInterval();
		} else {
			nextTask.startTaskInterval(nextTask.getName(), nextTask.getDescription());
			super.stopTaskInterval();
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
	@Override
	public Date getEndingDate() {
		// TODO Auto-generated method stub
		return null;
	}
}
