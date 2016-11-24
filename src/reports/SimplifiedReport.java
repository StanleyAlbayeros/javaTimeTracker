package reports;

import core.Project;
import reports.elements.Separator;
import reports.elements.Subtitle;
import reports.elements.Table;
import reports.elements.Title;

import java.util.ArrayList;
import java.util.List;

public class SimplifiedReport extends Report {
  
  protected String reportTitle;
  final Table table;
  

  public SimplifiedReport(Project project, String reportTitle) {
    super( project);
    assert ( reportTitle != null) : "Report title is null";
    this.reportTitle = reportTitle;
    table = new Table();
   //reportElements.add(new Separator());
    reportElements.add(new Title(reportTitle));
    //reportElements.add(new Separator());
    reportElements.add(new Subtitle("Time Period"));
    ArrayList<String> dateRow = new ArrayList<String>();
    dateRow.add("");
    dateRow.add("Date");
    ArrayList<String> dateSubRow = new ArrayList<String>();
    dateSubRow.add("From: ");
    dateSubRow.add("");
    ArrayList<String> dateSubRow2 = new ArrayList<String>();
    dateSubRow2.add("To: ");
    dateSubRow2.add("");
    table.addTableRow(dateRow);
    table.addTableRow(dateSubRow);
    table.addTableRow(dateSubRow2);
    ArrayList<String> descriptionRow = new ArrayList<String>();
    descriptionRow.add("Project name");
    descriptionRow.add("Project start date");
    descriptionRow.add("Project end date");
    descriptionRow.add("Total time length");
    table.addTableRow(descriptionRow);
    ArrayList<Project> projectTree = project.getProjectTree();
    for (Project currentProject : projectTree) {
      ArrayList<String> newRow = new ArrayList<>();
      newRow.add(currentProject.getName());
      newRow.add(currentProject.getStartDate().toString());
      newRow.add(currentProject.getEndDate().toString());
      long currentProjectLength = currentProject.getLength();
      newRow.add(Long.toString(currentProjectLength));
      newRow.add(currentProject.getName());
      table.addTableRow(newRow);
    }
    
    reportElements.add(table);
    
  }

}
