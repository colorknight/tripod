package org.datayoo.tripod.operand;

import org.apache.commons.lang3.Validate;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;

import java.util.List;

public abstract class DecratorOperand extends AbstractOperand {

  protected List<Operand> operands;

  public DecratorOperand(ExpressionMetadata expressionMetadata,
      TripodContext context) {
    super(expressionMetadata, context);
  }

  public DecratorOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext tripodContext) {
    super(expressionMetadata, tripodContext);
    Validate.notEmpty(operands, "operands is null!");
    this.operands = operands;
  }

}
