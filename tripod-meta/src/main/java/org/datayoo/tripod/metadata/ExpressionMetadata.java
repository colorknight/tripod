package org.datayoo.tripod.metadata;

public interface ExpressionMetadata {

  ExpressionType getExpressionType();

  int getBoost();

  void setBoost(int boost);
}
