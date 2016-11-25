package core;

import core.Activity;
import core.Project;

import java.io.Serializable;
import java.util.ArrayList;



public class Project extends Activity implements Serializable {

  private static final long serialVersionUID = 1L;


  private ArrayList<Activity> activityList = new ArrayList<Activity>();

  public Project() {
    this.activityList = new ArrayList<Activity>();

  }

  public Project(String name, String description, Project father, ArrayList<Activity> root) {
    super(name, description, father, root);
    assert ((name != null) && (description != null) && (father != null));
  }

  public ArrayList<Activity> getActivityList() {
    return this.activityList;
  }

  public void setActivityList(ArrayList<Activity> activityList) {
    this.activityList = activityList;
  }
  

  public void addToActivityList(Activity activity) {
    this.activityList.add(activity);
  }

  /**
   * Generates an arraylist with all the projects in the tree.
   * 
   * @return the generated arraylist.
   */
  public ArrayList<Project> getProjectTree() {
    ArrayList<Project> tree = new ArrayList<Project>();
    for (Activity currentActivity : this.getActivityList()) {
      if (currentActivity.getClass() == Project.class) {
        // if (currentActivity.father == this) {
        tree.add((Project) currentActivity);
        tree.addAll(((Project) currentActivity).getProjectTree());
        // }
      }
    }
    return tree;
  }
}

