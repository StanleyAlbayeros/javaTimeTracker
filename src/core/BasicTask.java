package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BasicTask extends Task implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * BasicTask constructor.
   * 
   * @param name Task name.
   * @param description Task description.
   * @param father Father task.
   * @param root Project tree root.
   */
  public BasicTask(String name, String description, Project father, ArrayList<Activity> root) {
    super(name, description, father, root, "BasicTask");
  }

  /**
   * No arg constructor used to serialize.
   * 
   */
  public BasicTask() {

  }

  @Override
  public Task getNextTask() {
    return null;
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
    return null;
  }


}
