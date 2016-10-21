package core;

import java.io.Serializable;
import java.util.*;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public abstract class Task extends Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Task.class);
	
	private String taskType;

	private ArrayList<Interval> intervalList = new ArrayList<Interval>();
	
	private ArrayDeque<Interval> pausedIntervals = new ArrayDeque<Interval>();

	/** Abstract task constructor
	 * @param name
	 * @param description
	 * @param father
	 * @param root
	 * @param ntaskType Sets the task type (basic, sequence, programmed, timed) to avoid getclass() calls
	 */
	public Task(String name, String description, Project father, ArrayList<Activity> root, String ntaskType) {
		super(name, description, father, root);
		setTaskType(ntaskType);

		log.info("Task: " + name +". Type: "+ taskType +" created.");
	}
	
	/**
	 * No arg constructor used to serialize
	 */
	public Task() {
		
	}

	/** Starts a new task interval
	 * @param name interval name
	 * @param description interval description
	 */
	public void startTaskInterval(String name, String description) {

		Clock clock = Clock.getInstance();
		log.info("Starting the task: " + name + " with description: " + description);
		Date d = new Date();
		if (this.getStartDate() == null) {
			this.setStartDate(d);
		}

		Project p = this.getFather();
		while (p != null) {
			if (p.getStartDate() == null) {
				p.setStartDate(d);
			}
			p = p.getFather();
		}
		Interval i = new Interval(name, description, this);
		clock.addObserver(i);
		log.info("Task: " + name +". Type: "+ taskType +" started succesfully");
	}

	/** Stops this task's lest running interval and updates the project tree accordingly.
	 * 
	 */
	public void stopTaskInterval() {

			Clock clock = Clock.getInstance();
			log.info("Stopping the task: " + getName() + " with description: " + getDescription());
			int intervalIndex = 0;
			intervalIndex = getIntervalList().size() - 1;
			clock.deleteObserver(getIntervalList().get(intervalIndex));
			Project p = this.getFather();
			
			while (p!=null){
				SerializeData.saveData(p, "tempState");
				p = p.getFather();
			}
			log.info("Task: " + getName() + " stopped");
		}

	/** Pauses this task's latest running interval, and saves its position within intervalList so we can resume it later
	 * 
	 */
	public void pauseTaskInterval() {

		Clock clock = Clock.getInstance();
		int pausedIntervalIndex = 0;
		pausedIntervalIndex = getIntervalList().size() - 1;
		Interval pausedInterval = getIntervalList().get(pausedIntervalIndex);
		pausedIntervals.push(pausedInterval);
		clock.deleteObserver(pausedInterval);
		log.info("Task: " + getName() + " paused");
	}	
	
	/** Resumes the last paused task interval.
	 * 
	 */
	public void resumeTaskInterval() {

		if (!pausedIntervals.isEmpty()) {
			Clock clock = Clock.getInstance();
			int pausedIntervalIndex = 0;
			pausedIntervalIndex = getIntervalList().size() - 1;
			Interval pausedInterval = getIntervalList().get(pausedIntervalIndex);
			pausedIntervals.push(pausedInterval);
			clock.addObserver(pausedInterval);
			log.info("Task: " + getName() + " resumed");
		}
	}
	
	/** Implemented by decorator SpecialTask subclass TaskSequence
	 * 
	 * @return queued task
	 */
	public abstract Task getNextTask();
	
	/** Implemented by decorator SpecialTask subclass ProgrammedEvent
	 * 
	 * @return date in which the task will start
	 */
	public abstract Date getTaskStartDate();
	
	/** Implemented by decorator Specialtask subclass ProgrammedEvent
	 * 
	 * @return date in which the task will end
	 */
	public abstract Date getEndingDate();
	/** Implemented by decorator SpecialTask subclass TimedTask
	 * 
	 * @return maximum TimedTask duration
	 */
	public abstract int getTimeLimit();
	
	public ArrayList<Interval> getIntervalList() {
		return intervalList;
	}

	public void setIntervalList(ArrayList<Interval> nintervalList) {
		this.intervalList = nintervalList;
	}	
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String ntaskType) {
		taskType = ntaskType;
	}

	


}
