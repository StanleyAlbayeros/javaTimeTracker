package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TimedTask extends SpecialTask implements Serializable {

  private static final long serialVersionUID = 1L;
  private int timeLimit;

  /**
   * Timed task constructor.
   * 
   * @param root root object
   * @param name task instance name
   * @param description task instance descrpition
   * @param father father activity of the new task
   * @param ntimeLimit maximum task length
   */
  public TimedTask(String name, String description, Project father, ArrayList<Activity> root,
      int ntimeLimit) {
    super(name, description, father, root, "TimedTask");
    assert ((name != null) && (description != null) && (father != null));

    timeLimit = ntimeLimit;
  }

  /**
   * No arg constructor used to serialize.
   */
  public TimedTask() {

  }

  @Override
  public Task getNextTask() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Date getTaskStartDate() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getTimeLimit() {

    return timeLimit;
  }

  @Override
  public Date getEndingDate() {
    // TODO Auto-generated method stub
    return null;
  }

}
