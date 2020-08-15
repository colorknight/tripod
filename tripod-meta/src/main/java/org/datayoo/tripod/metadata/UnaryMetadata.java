package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

public class UnaryMetadata extends AbstractExpressionMetadata {

  protected ExpressionMetadata expr;

  public UnaryMetadata(ExpressionType expressionType, ExpressionMetadata expr) {
    super(expressionType);
    if (!(expressionType == ExpressionType.MUST
        || expressionType == ExpressionType.NOT
        || expressionType == ExpressionType.MINUS
        || expressionType == ExpressionType.PAREN))
      throw new IllegalArgumentException("Invalid expression type!");
    Validate.notNull(expr, "expr is null!");
    this.expr = expr;
  }

  public ExpressionMetadata getExpr() {
    return expr;
  }

  public String toString() {
    String op = "+";
    if (expressionType == ExpressionType.NOT) {
      op = "!";
    } else if (expressionType == ExpressionType.MINUS) {
      op = "-";
    } else if (expressionType == ExpressionType.PAREN) {
      if (boost == 1)
        return String.format("(%s)", expr.toString());
      else
        return String.format("(%s)^%d", expr.toString(), boost);
    }
    if (boost == 1)
      return String.format("%s%s", op, expr.toString());
    else
      return String.format("%s%s^%d", op, expr.toString(), boost);
  }
}
