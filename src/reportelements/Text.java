package reportelements;

import java.util.ArrayList;
import java.util.LinkedList;

import reports.Formatting;

public class Text implements ElementInterface {
  
  private String content = " ";  
  private String underscoreLine = "____________________________________________"
      + "________________________";
  private String horizontalLine = "-----------------------------------";
  private String verticalLine = "|";
  
  public String getContent() {
    return content;
  }
  
  public void addContent(String newText) {
    assert ( newText != null );
    content = content + newText;
  }
  
  public void addLineBreak() {
    String lineBreak = System.getProperty("line.separator");
    content = content + lineBreak;
  }
  
  public void addUnderscoreLine() {  
    addLineBreak();
    content = content + underscoreLine;
    addLineBreak();
  }
  
  /** Adds "num" whitespaces after the current element, places a new string, 
   * and places "num" whitespaces after the new string.
   * 
   * @param newText new text.
   * @param num number of whitespaces.
   */
  public void centerElement(String newText, int num) {
    assert ( newText != null );
    for (int i = 0 ; i < num ; i++) {
      content = content + " ";
    }
    content = content + newText;
    for ( int i = 0 ; i < num ; i++) {
      content = content + " ";
    }
  }
  
  public void addTitle(String newText, boolean centered){
    if (centered){
      centerElement(newText, 50);
    } else {
      content = content + newText;
    }
    addUnderscoreLine();
  }
  
  public void addSubtitle(String newText, boolean centered) {
    if (centered){
      centerElement(newText, 50);
    } else {
      content = content + newText;
    }
    addLineBreak();
  }
  
  public void addTable (ArrayList<LinkedList<String>> newTable) {
        
    int tableColumns = newTable.get(0).size();
    int tableRows = newTable.size();
    
    for (int i = 0 ; i < tableRows ; i++) {
      for (String currentColumm : newTable.get(i)) {
        content = content + horizontalLine;
      }
      addLineBreak();
      content = content + verticalLine;
      addLineBreak();
      for (String currentColumm2 : newTable.get(i)) {
        centerElement(currentColumm2, 100);
      }
      content = content + verticalLine;
      addLineBreak();
    }
    for (int j = 0 ; j < tableColumns ; j++) {
      content = content + horizontalLine;
    }
    addLineBreak();
  }
  
  @Override
  public void accept(Formatting formatting) {
    assert ( formatting != null );

  }

}
