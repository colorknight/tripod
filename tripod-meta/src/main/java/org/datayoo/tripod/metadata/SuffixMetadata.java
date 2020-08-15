package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

public class SuffixMetadata extends AbstractExpressionMetadata {

  protected ExpressionMetadata expr;

  protected Number number;

  public SuffixMetadata(ExpressionType expressionType, Number number) {
    super(expressionType);
    if (expressionType == ExpressionType.PROXIMITY) {
      if (number != null && !(number instanceof Integer)) {
        throw new IllegalArgumentException(
            "Invalid format! Number should be unsigned integer!");
      }
      if (number.intValue() < 0)
        throw new IllegalArgumentException(
            "Invalid format! Number should be bigger than 1!");
    } else if (expressionType == ExpressionType.FUZZY) {
      if (number != null && (number.doubleValue() <= 0
          || number.doubleValue() > 1)) {
        throw new IllegalArgumentException(
            "Invalid format! Number should be (0,1]!");
      }
    } else {
      throw new IllegalArgumentException("Invalid expression type!");
    }

    this.number = number;
  }

  public ExpressionMetadata getExpr() {
    return expr;
  }

  public void setExpr(ExpressionMetadata expr) {
    Validate.notNull(expr, "expr is null!");
    this.expr = expr;
  }

  public Number getNumber() {
    return number;
  }

  public String toString() {
    String op = "~";
    StringBuffer sbuf = new StringBuffer();
    if (expr != null)
      sbuf.append(expr.toString());
    sbuf.append(op);
    if (number != null)
      sbuf.append(number.toString());
    if (boost != 1) {
      sbuf.append("^");
      sbuf.append(boost);
    }
    return sbuf.toString();
  }
}
