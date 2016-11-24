package reportelements;

import reports.Formatting;

import java.util.ArrayList;
import java.util.LinkedList;


public class Table implements ElementInterface {
  
  private ArrayList<LinkedList<String>> table = new ArrayList<LinkedList<String>>();
  
  public Table(){
  }
  
  public Table(ArrayList<LinkedList<String>> table) {
    assert (table != null);
    this.table = table;
  }
  
  public void setTable(ArrayList<LinkedList<String>> table) {
    assert (table != null);
    this.table = table;
  }
  
  ArrayList<LinkedList<String>> getTable() {
    return table;
  }
  
  public void addTableRow(LinkedList<String> row) {
    table.add(row);
  }
  
  @Override
  public void accept(Formatting formatting) {
    // TODO Auto-generated method stub

  }

}
