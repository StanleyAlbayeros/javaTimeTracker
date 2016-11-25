package reports;

import paginaweb.PaginaWeb;
import reports.elements.Separator;
import reports.elements.Subtitle;
import reports.elements.Table;
import reports.elements.Text;
import reports.elements.Title;

import java.io.FileNotFoundException;
import java.io.PrintWriter;



public class HtmlFormatting implements Formatting {

  protected PrintWriter fileOut;
  protected PaginaWeb reportContent;


  /**
   * Generates an html formatting object with specialized visitor methods to import a simple text
   * layout file to html.
   * 
   * @param filename File's name.
   * @throws FileNotFoundException The file could not be found or could not be created
   */
  public HtmlFormatting(String filename) throws FileNotFoundException {
    assert (reportFilenameCheck(filename)) : "Filename not valid";
    fileOut = new PrintWriter(filename);
    reportContent = new PaginaWeb();
  }

  private boolean reportFilenameCheck(String filename) {
    boolean validFilename = false;
    if (filename != null) {
      validFilename = true;
    }
    return validFilename;
  }

  @Override
  public void visit(Separator separator) {
    assert (separator != null) : "Separator is null";
    reportContent.afegeixLiniaSeparacio();
  }

  @Override
  public void visit(Subtitle subtitle) {
    assert (subtitle != null) : "Subtitle is null";
    reportContent.afegeixHeader(subtitle.getContent(), 2, false);
  }

  @Override
  public void visit(Title title) {
    assert (title != null) : "Title is null";
    reportContent.afegeixHeader(title.getContent(), 1, true);
  }

  @Override
  public void visit(Table table) {
    assert (table != null) : "Table is null";
    reportContent.afegeixTaula(table.getTable(), true, true);
  }

  @Override
  public void visit(Text text) {
    assert (text != null) : "Text is null";
    reportContent.afegeixTextNormal(text.getContent());
  }

  @Override
  public void closeReport() {
    fileOut.close();
  }


}
