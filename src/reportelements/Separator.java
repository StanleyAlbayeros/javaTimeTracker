package reportelements;

import reports.Formatting;

public class Separator implements ElementInterface {

  @Override
  public void accept(Formatting formatting) {
    formatting.visit(this);
  }

}
