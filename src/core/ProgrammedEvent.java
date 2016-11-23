package core;

//dfernandez@cvc.uab.es

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ProgrammedEvent extends SpecialTask implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date startingDate;

  private Date endingDate;

  /**
   * Programmed event constructor.
   * 
   * @param root Project root object.
   * @param name Task instance name.
   * @param description Task instance descrpition.
   * @param father Father activity of the new task.
   * @param nstartingDate Used to start the task in a future date.
   */
  public ProgrammedEvent(String name, String description, Project father, ArrayList<Activity> root,
      Date nstartingDate, Date nendingDate) {
    super(name, description, father, root, "ProgrammedEvent");
    startingDate = nstartingDate;
    endingDate = nendingDate;
  }

  /**
   * No arg constructor used to serialize.
   */
  public ProgrammedEvent() {

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

  public Date getEndingDate() {
    return endingDate;
  }

  public void setEndingDate(Date endingDate) {
    this.endingDate = endingDate;
  }
}
