package org.datayoo.tripod.metadata;

import java.util.List;

public class InMetadata extends AbstractExpressionMetadata {

  protected List<ExpressionMetadata> expressions;

  public InMetadata(List<ExpressionMetadata> expressions) {
    super(ExpressionType.IN);
    this.expressions = expressions;
  }

  public List<ExpressionMetadata> getExpressions() {
    return expressions;
  }

  public String toString() {
    StringBuilder sbud = new StringBuilder();
    sbud.append('[');
    for (ExpressionMetadata expression : expressions) {
      if (sbud.length() > 1)
        sbud.append(' ');
      sbud.append(expression.toString());
    }
    sbud.append(']');
    if (boost != 1) {
      sbud.append('^');
      sbud.append(boost);
    }
    return sbud.toString();
  }
}
