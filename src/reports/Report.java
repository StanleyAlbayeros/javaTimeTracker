package reports;

import core.Project;
import reportelements.ElementInterface;

import java.util.LinkedList;


public abstract class Report {
  
  protected TimePeriod timePeriod;
  protected Project project;
  protected LinkedList<ElementInterface> reportElements;
  
  /** Constructor for the report class.
   * 
   * @param timePeriod Desired time period for the project report.
   * @param project Project used to generate the report.
   */
  public Report(TimePeriod timePeriod, Project project) {
    assert ((timePeriod != null) && (project != null));
    this.timePeriod = timePeriod;
    this.project = project;
    reportElements = new LinkedList<ElementInterface>();
    
  }

  
  public void writeReport(Formatting formatting) {
    for (ElementInterface currentElement : reportElements){
      currentElement.accept(formatting);
    }
    formatting.closeReport();
  }
}
