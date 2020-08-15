package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

public class TermMetadata extends AbstractExpressionMetadata {

  protected String term;

  public TermMetadata(String term) {
    super(ExpressionType.TERM);
    Validate.notEmpty(term, "Term is empty!");
    this.term = term;
  }

  public String getTerm() {
    return term;
  }

  public String toString() {
    if (boost == 1)
      return term;
    return String.format("%s^%d", term, boost);
  }
}
