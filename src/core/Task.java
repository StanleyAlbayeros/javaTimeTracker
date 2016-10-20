package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Task extends Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Interval> intervalList = new java.util.ArrayList<Interval>();

	public Task(String name, String description, Project father, ArrayList<Activity> root) {
		super(name, description, father, root);
	}

	public Task() {}

	public void startTask(String name, String description, Clock clock) {
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
	}

	public void stopTask(Clock clock) {
		int i = 0;
		i = intervalList.size() - 1;
		clock.deleteObserver(intervalList.get(i));
	}

	public void pauseTask(Clock clock) {
		clock.stop();
	}

	public ArrayList<Interval> getIntervalList() {
		return intervalList;
	}

	public void setIntervalList(ArrayList<Interval> nintervalList) {
		this.intervalList = nintervalList;
	}

}
