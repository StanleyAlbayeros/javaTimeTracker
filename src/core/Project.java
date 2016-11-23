package core;

import core.Activity;
import core.Project;

import java.io.Serializable;
import java.util.ArrayList;



public class Project extends Activity implements Serializable {
  
  private static final long serialVersionUID = 1L;


  private ArrayList<Activity> activityList = new java.util.ArrayList<Activity>();

  public Project() {
    this.activityList = new ArrayList<Activity>();

  }

  public Project(String name, String description, Project father, ArrayList<Activity> root) {
    super(name, description, father, root);
  }

  public ArrayList<Activity> getActivityList() {
    return activityList;
  }

  public void setActivityList(ArrayList<Activity> nactivityList) {
    this.activityList = nactivityList;
  }
}

