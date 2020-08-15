package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class AbstractExpressionMetadata
    implements ExpressionMetadata, Serializable {

  protected ExpressionType expressionType;

  protected int boost = 1;

  public AbstractExpressionMetadata(ExpressionType expressionType) {
    Validate.notNull(expressionType, "expressionType is null!");
    this.expressionType = expressionType;
  }

  @Override
  public ExpressionType getExpressionType() {
    return expressionType;
  }

  public int getBoost() {
    return boost;
  }

  public void setBoost(int boost) {
    this.boost = boost;
  }
}
