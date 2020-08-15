package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

public class FieldMetadata extends AbstractExpressionMetadata {

  protected String field;

  protected ExpressionMetadata expr;

  public FieldMetadata(String field, ExpressionMetadata expr) {
    super(ExpressionType.FIELD);
    Validate.notNull(expr, "expr is null!");
    this.field = field;
    this.expr = expr;
  }

  public String getField() {
    return field;
  }

  public ExpressionMetadata getExpr() {
    return expr;
  }

  public String toString() {
    if (field == null)
      return expr.toString();
    else {
      return String.format("%s:%s", field, expr.toString());
    }
  }
}
