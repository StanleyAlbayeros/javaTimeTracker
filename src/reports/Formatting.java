package reports;

import reportelements.Separator;
import reportelements.Subtitle;
import reportelements.Title;

/** Visitor interface.
 * 
 * @author Vernon
 *
 */
public interface Formatting {
  
  void closeReport();
  
  void visit(Separator separator);
  
  void visit(Subtitle subtitle);  

  void visit(Title title);
  
}
