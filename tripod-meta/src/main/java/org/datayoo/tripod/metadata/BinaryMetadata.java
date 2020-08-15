package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

public class BinaryMetadata extends AbstractExpressionMetadata {

  protected ExpressionMetadata lExpr;

  protected ExpressionMetadata rExpr;

  public BinaryMetadata(ExpressionType expressionType, ExpressionMetadata lExpr,
      ExpressionMetadata rExpr) {
    super(expressionType);
    if (!(expressionType == ExpressionType.AND
        || expressionType == ExpressionType.OR))
      throw new IllegalArgumentException("Invalid expression type!");
    Validate.notNull(lExpr, "lExpr is null!");
    Validate.notNull(rExpr, "rExpr is null!");
    this.lExpr = lExpr;
    this.rExpr = rExpr;
  }

  public ExpressionMetadata getlExpr() {
    return lExpr;
  }

  public ExpressionMetadata getrExpr() {
    return rExpr;
  }

  public String toString() {
    String op = " | ";
    if (expressionType == ExpressionType.AND) {
      op = " & ";
    }
    if (boost == 1) {
      return String.format("%s%s%s", lExpr.toString(), op, rExpr.toString());
    } else {
      return String.format("%s%s%s^%d", lExpr.toString(), op, rExpr.toString(), boost);
    }
  }
}
