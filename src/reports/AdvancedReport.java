package reports;

import core.Activity;
import core.Project;
import core.Task;
import reports.elements.Separator;
import reports.elements.Subtitle;
import reports.elements.Table;
import reports.elements.Title;

import java.util.ArrayList;

public class AdvancedReport extends Report {

  protected String reportTitle;
  final Table table;

  /**
   * Generates a simplified report object.
   * 
   * @param project Root project.
   * @param reportTitle Report object's title.
   */
  public AdvancedReport(Project project, String reportTitle, ArrayList<Activity> projectTree) {
    super(project);
    assert (reportTitle != null) : "Report title is null";
    this.reportTitle = reportTitle;
    table = new Table();
    reportElements.add(new Separator());
    reportElements.add(new Title(reportTitle));
    ArrayList<String> dateRow = new ArrayList<String>();
    dateRow.add("");
    dateRow.add("Date");
    ArrayList<String> dateSubRow = new ArrayList<String>();
    dateSubRow.add("From: ");
    dateSubRow.add(project.getStartDate().toString());
    ArrayList<String> dateSubRow2 = new ArrayList<String>();
    dateSubRow2.add("To: ");
    dateSubRow2.add(project.getEndDate().toString());
    table.addTableRow(dateRow);
    table.addTableRow(dateSubRow);
    table.addTableRow(dateSubRow2);
    reportElements.add(table);
    
    Table projectTable = generateProjectTable(project);
    reportElements.add(projectTable);
    
    reportElements.add(new Subtitle("Tasks"));
    ArrayList<String> row = new ArrayList<>();
    ArrayList<String> singleTask = new ArrayList<>();
    row.add("Tasks");
    row.add("Start Date: ");
    row.add("End Date: ");
    Table taskTable = new Table();
    row.add("Total Length");
    taskTable.addTableRow(row);
    ArrayList<String> taskRow = generateTaskTable(project);
    int rowCount = 0;
    for (String taskAtHand : taskRow) {
      rowCount++;
      singleTask.add(taskAtHand);
      if ( rowCount == 4 ) {
        taskTable.addTableRow(singleTask);
        singleTask = new ArrayList<String>();
        rowCount = 0;
      }
    }      
    reportElements.add(taskTable);
  }

  /**
   * Generates a table with project elements.
   * 
   * @param project project from where we will look for more projects.
   */
  private Table generateProjectTable(Project project) {
    Table table = new Table();
    ArrayList<String> descriptionRow = new ArrayList<String>();
    descriptionRow.add("Project name");
    descriptionRow.add("Project start date");
    descriptionRow.add("Project end date");
    descriptionRow.add("Total time length");
    table.addTableRow(descriptionRow);
    ArrayList<Project> activityList = project.getProjectTree();

    for (Activity currentActivity : activityList) {

      ArrayList<String> newRow = new ArrayList<>();

      if (currentActivity.getClass() == Project.class) {

        newRow.add(currentActivity.getName());
        newRow.add(currentActivity.getStartDate().toString());
        newRow.add(currentActivity.getEndDate().toString());

        long currentProjectLength = currentActivity.getLength() / 1000;
        newRow.add(Long.toString(currentProjectLength));
        newRow.add(currentActivity.getName());
        table.addTableRow(newRow);

      } else {

        if ((!((Task) currentActivity).isTaskInitialized()) || currentActivity == null) {
          continue;
        }
        newRow.add(currentActivity.getName() + " Task");
        newRow.add(currentActivity.getStartDate().toString());
        newRow.add(currentActivity.getEndDate().toString());
        long currentProjectLength = currentActivity.getLength() / 1000;
        newRow.add(Long.toString(currentProjectLength));
        newRow.add(currentActivity.getName());
        table.addTableRow(newRow);

      }
    }
    return table;
  }
  
  /**
   * Generates a list with a project's tasks.
   * 
   * @param project root project.
   */
  private ArrayList<String> generateTaskTable(Project project) {

    ArrayList<String> taskList = new ArrayList<String>();
    ArrayList<Project> activityList = project.getProjectTree();

    if (activityList != null) {
      for (Activity currentActivity : activityList) {
        ArrayList<String> newRow = new ArrayList<>();
        if (currentActivity.getClass() == Project.class) {
          ArrayList<Task> tmpList = ((Project) currentActivity).getTaskList();
          if (tmpList != null) {
            for (Task currentTask : tmpList) {
              taskList.add(currentTask.getName()+ "." + currentTask.getFather().getName());
              taskList.add(currentActivity.getStartDate().toString());
              taskList.add(currentActivity.getEndDate().toString());long currentProjectLength = currentTask.getLength() / 1000;
              taskList.add(Long.toString(currentProjectLength));
            }
          }
        }
      } 
    }
    return taskList;
  }

}
