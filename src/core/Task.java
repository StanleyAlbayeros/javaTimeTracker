package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public abstract class Task extends Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Task.class);

	private ArrayList<Interval> intervalList = new java.util.ArrayList<Interval>();

	public Task(String name, String description, Project father, ArrayList<Activity> root) {
		super(name, description, father, root);
	}

	public Task() {}

	public void startTask(String name, String description) {

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
		log.info("Task: " + name + " started succesfully");
	}

	/**
	 * @param clock
	 */	
	public void stopTask() {

			Clock clock = Clock.getInstance();
			log.info("Stopping the task: " + getName() + " with description: " + getDescription());
			int i = 0;
			i = getIntervalList().size() - 1;
			clock.deleteObserver(getIntervalList().get(i));
			Project p = this.getFather();
			
			while (p!=null){
				SerializeData.saveData(p, "tempState");
				p = p.getFather();
			}
			log.info("Task: " + getName() + " stopped");
		}

	public void pauseTask(Clock clock) {
		clock.stop();
		log.info("Task: " + getName() + " paused");
	}

	public ArrayList<Interval> getIntervalList() {
		return intervalList;
	}

	public void setIntervalList(ArrayList<Interval> nintervalList) {
		this.intervalList = nintervalList;
	}	
	
	public abstract Task getNextTask();
	public abstract Date getTaskStartDate();
	public abstract int getTimeLimit();


}
