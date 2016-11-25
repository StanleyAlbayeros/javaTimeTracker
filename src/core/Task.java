package core;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;


public abstract class Task extends Activity implements Serializable {

  private static final long serialVersionUID = 1L;

  private static Logger log = (Logger) LoggerFactory.getLogger(Task.class);

  private String taskType;

  private ArrayList<Interval> intervalList = new ArrayList<Interval>();

  private ArrayDeque<Interval> pausedIntervals = new ArrayDeque<Interval>();
  
  private boolean isTaskInitialized = false;

  /**
   * Abstract task constructor.
   * 
   * @param name Task name.
   * @param description Task description.
   * @param father This task's father task.
   * @param root Project root.
   * @param ntaskType Sets the task type (basic, sequence, programmed, timed) to avoid getclass()
   *        calls
   */
  public Task(String name, String description, Project father, ArrayList<Activity> root,
      String ntaskType) {
    super(name, description, father, root);
    assert ((name != null) && (description != null) && (father != null));
    setTaskType(ntaskType);
    this.setStartDate(new Date());

    log.info("Task: " + name + ". Type: " + taskType + " created.");
  }

  /**
   * No arg constructor used to serialize.
   */
  public Task() {

  }
  
  @Override
  public boolean isTaskInitialized() {
    return isTaskInitialized;
  }

  /**
   * Starts a new task interval.
   * 
   * @param name interval name
   * @param description interval description
   */
  public void startTaskInterval(String name, String description) {
    log.info("Starting the task: " + name + " with description: " + description);
    Date tempDate = new Date();
    if ((!this.isTaskInitialized) || (this.getStartDate() == null)) {
      this.setStartDate(tempDate);
      isTaskInitialized = true;
    }

    Project tempProject = this.getFather();
    while (tempProject != null) {
      if (tempProject.getStartDate() == null) {
        tempProject.setStartDate(tempDate);
      }
      tempProject = tempProject.getFather();
    }
    Clock clock = Clock.getInstance();
    Interval tempInterval = new Interval(name, description, this);
    clock.addObserver(tempInterval);
    log.info("Task: " + name + ". Type: " + taskType + " started succesfully");
  }

  /**
   * Stops this task's lest running interval and updates the project tree accordingly.
   * 
   */
  public void stopTaskInterval() {

    Clock clock = Clock.getInstance();
    log.info("Stopping the task: " + getName() + " with description: " + getDescription());
    int intervalIndex = 0;
    intervalIndex = getIntervalList().size() - 1;
    clock.deleteObserver(getIntervalList().get(intervalIndex));
    Project tempProject = this.getFather();

    while (tempProject != null) {
      SerializeData.saveData(tempProject, "tempState");
      tempProject = tempProject.getFather();
    }
    log.info("Task: " + getName() + " stopped");
  }

  /**
   * Pauses this task's latest running interval, and saves its position within intervalList so we
   * can resume it later.
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

  /**
   * Resumes the last paused task interval.
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

  /**
   * Implemented by decorator SpecialTask subclass TaskSequence,
   * 
   * @return queued task.
   */
  public abstract Task getNextTask();

  /**
   * Implemented by decorator SpecialTask subclass ProgrammedEvent,
   * 
   * @return date in which the task will start.
   */
  public abstract Date getTaskStartDate();

  /**
   * Implemented by decorator Specialtask subclass ProgrammedEvent,
   * 
   * @return date in which the task will end.
   */
  public abstract Date getEndingDate();

  /**
   * Implemented by decorator SpecialTask subclass TimedTask.
   * 
   * @return maximum TimedTask duration.
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
