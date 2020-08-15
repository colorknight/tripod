package org.datayoo.tripod.operand;

import org.apache.commons.lang3.Validate;
import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;

public abstract class AbstractOperand implements Operand {

  protected int boost;

  protected ExpressionMetadata expressionMetadata;

  protected TripodContext context;

  public AbstractOperand(ExpressionMetadata expressionMetadata,
      TripodContext context) {
    Validate.notNull(context, "context is null!");
    Validate.notNull(expressionMetadata, "expressionMetadata is null!");
    this.context = context;
    this.boost = expressionMetadata.getBoost();
    this.expressionMetadata = expressionMetadata;
  }

  @Override
  public ExpressionMetadata getMetadata() {
    return expressionMetadata;
  }

  @Override
  public double operate(DocumentEntity documentEntity) {
    return operate(documentEntity, true, null);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance) {
    return operate(documentEntity, termDistance, null);
  }
}
