package reports;

import core.Project;
import reports.elements.ElementInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public abstract class Report {
  
  protected Project project;
  protected ArrayList<ElementInterface> reportElements;
  
  /** Constructor for the report class.
   * 
   * @param project Project used to generate the report.
   */
  public Report(Project project) {
    assert (project != null);
    this.project = project;
    reportElements = new ArrayList<ElementInterface>();
    
  }

  
  public void writeReport(Formatting formatting) {
    for (ElementInterface currentElement : reportElements){
      currentElement.accept(formatting);
    }
    formatting.closeReport();
  }
}
