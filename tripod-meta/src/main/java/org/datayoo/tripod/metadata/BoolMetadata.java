package org.datayoo.tripod.metadata;

import java.util.LinkedList;
import java.util.List;

public class BoolMetadata extends AbstractExpressionMetadata {

  protected List<ExpressionMetadata> boolExprs = new LinkedList<ExpressionMetadata>();

  public BoolMetadata() {
    super(ExpressionType.SPACE_OR);
  }

  public List<ExpressionMetadata> getBoolExprs() {
    return boolExprs;
  }

  @Override
  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    for (ExpressionMetadata expr : boolExprs) {
      if (sbuf.length() != 0)
        sbuf.append(' ');
      sbuf.append(expr.toString());
    }
    return sbuf.toString();
  }
}
