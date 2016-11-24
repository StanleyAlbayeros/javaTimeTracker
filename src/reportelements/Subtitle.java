package reportelements;

import reports.Formatting;

public class Subtitle implements ElementInterface {
  
  private String content;
  
  public Subtitle(String content) {
    assert (content != null);
    this.content = content;
  }
  
  public final String getContent() {
    return content;
  }
  
  public final void setContent(String content) {
    this.content = content;
  }

  @Override
  public void accept(Formatting formatting) {
    formatting.visit(this);
  }

}
