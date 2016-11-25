package core;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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
   * @param description Activity instance descrpition.
   * @param name Activity instance name.
   * @param father Father activity of the new activity.
   * @param root Root object.
   * 
   */
  public Activity(String name, String description, Project father, ArrayList<Activity> root) {
    
    this.father = father;
    this.name = name;
    this.description = description;
    root.add(this);
    this.father.setActivityList(root);
    assert ((name != null) && (description != null) && (father != null));
    log.info("Created activity: " + name + " with the following description: " + description);
  }

  /**
   * No arg constructor used to serialize.
   */
  public Activity() {

  }

  public Project getFather() {
    return father;
  }

  public void setFather(Project father) {
    assert ( father != null  );
    this.father = father;
  }

  public Date getStartDate() {
    return startDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String ndescription) {
    assert (ndescription != null);
    this.description = ndescription;
  }

  public void setStartDate(Date nstartDate) {
    assert (nstartDate != null);
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
    assert (name != null);
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
    assert (nendDate != null);
    this.endDate = nendDate;
  }

  public boolean isTaskInitialized() {
    return false;
  }

}

