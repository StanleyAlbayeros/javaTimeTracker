package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class SpecialTask extends Task implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Special task decorator constructor, delegates the implementation to Task.
   * 
   * @param name Task name.
   * @param description Task description.
   * @param father This task's father task.
   * @param root Project root.
   * @param taskType The type of this special task.
   */
  public SpecialTask(String name, String description, Project father, ArrayList<Activity> root,
      String taskType) {
    super(name, description, father, root, taskType);
    assert ((name != null) && (description != null) && (father != null));
  }

  /**
   * No arg constructor used to serialize.
   */
  public SpecialTask() {

  }

  public abstract Task getNextTask();

  public abstract Date getTaskStartDate();

  public abstract int getTimeLimit();
}
