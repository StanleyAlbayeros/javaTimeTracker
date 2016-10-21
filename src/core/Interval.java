package core;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import mockClient.Client;

/**
 * la clase intervalo es la clase que observa el clock segun el patron de dise�o Observer durante la
 * conometracion de la tarea en la variable duracion se guarda el intervalo que es la diferencia
 * entre la fecha inicial de la tarea y la fecha final.
 */
public class Interval implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Client.class);
	
	private Task fatherTask = null;

	private String name = new java.lang.String();

	private Date endDate;

	private Date startDate;

	private long length = 0;

	private String description = new java.lang.String();

	/** Constructs an interval
	 * @param name
	 * @param description
	 * @param fatherTask: the new interval belongs to this task
	 */
	public Interval(String name, String description, Task fatherTask) {
		this.fatherTask = fatherTask;
		this.name = name;
		this.description = description;
		startDate = new Date();
		this.fatherTask.getIntervalList().add(this);
		log.info("Created an interval: " + name + " for activity: " + fatherTask.getName());
	}

	/**
	 * No arg constructor used to serialize
	 */
	public Interval() {

	}

	/** Updates this interval depending on the type of task that contains it.
	 *  
	 *  This method is called whenever the observable is updated.
	 *  
	 */

	public void update(Observable observable, Object date) {
		
		switch (fatherTask.getTaskType()){
			case "BasicTask":
				normalUpdate();
				break;
			case "ProgramedEvent":
				Date currentDate = Clock.getCurrentDate();
				if (fatherTask.getEndingDate().before(currentDate)){
					fatherTask.stopTaskInterval();
					break;
				}
				if (fatherTask.getTaskStartDate().before(currentDate)){
					normalUpdate();
				}
				break;
			case "TaskSequence":
				normalUpdate();
				break;
			case "TimedTask":
				if (fatherTask.getTimeLimit()<fatherTask.getLength()){
					normalUpdate();
				} else {
					fatherTask.stopTaskInterval();
				}
				break;
			default:
				normalUpdate();
				break;

		}
		
	}
	
	/** Simply updates an interval
	 * 
	 */
	public void normalUpdate() {
		this.length = Clock.getIntervalLength();
		Activity tempA = this.fatherTask;
		while (tempA != null) {
			Date currentDate = Clock.getCurrentDate();
			this.setEndDate(currentDate );
			tempA.setEndDate(this.endDate);
			tempA.addLength(2000);
			tempA = tempA.getFather();
		}
	}

	public Task getFatherTask() {
		return fatherTask;
	}

	public void setFatherTask(Task nfatherTask) {
		this.fatherTask = nfatherTask;
	}

	public String getName() {
		return name;
	}

	public void setName(String nname) {
		this.name = nname;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date nendDate) {
		this.endDate = nendDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date nstartDate) {
		this.startDate = nstartDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String ndescription) {
		this.description = ndescription;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long nlength) {
		this.length = nlength;
	}
}
