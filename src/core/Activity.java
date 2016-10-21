package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public abstract class Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Activity.class);

	private String description = new java.lang.String();

	private Date startDate;

	private Date endDate = null;

	private long length = 0;

	protected Project father = null;

	private String name = new java.lang.String();

	/**
	 * Task and project constructor
	 * 
	 * @param root: root object
	 * @param name: Activity instance name
	 * @param description: Activity instance descrpition
	 * @param father: father activity of the new activity
	 * 
	 */
	public Activity(String name, String description, Project nFather, ArrayList<Activity> root) {
		this.father = nFather;
		this.name = name;
		this.description = description;
		root.add(this);
		father.setActivityList(root);
		log.info("Created activity: " + name + " with the following description: " + description);
	}
	/**
	 * No arg constructor used to serialize
	 */
	public Activity() {
		
	}

	public Project getFather() {
		return father;
	}

	public void setFather(Project nFather) {
		this.father = nFather;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String ndescription) {
		this.description = ndescription;
	}

	public void setStartDate(Date nstartDate) {
		this.startDate = nstartDate;
	}

	public long getLength() {
		return length;
	}

	public void addLength(long more) {
		this.length = this.length + more;
	}

	public String getName() {
		return name;
	}

	public void setName(String nname) {
		this.name = nname;
	}

	public Project getProject() {
		return father;
	}

	public void setProject(Project nfather) {
		this.father = nfather;
	}


	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date nendDate) {
		this.endDate = nendDate;
	}

}

