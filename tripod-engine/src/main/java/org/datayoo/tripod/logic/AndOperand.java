package org.datayoo.tripod.logic;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.operand.DecratorOperand;

import java.util.List;
import java.util.Map;

public class AndOperand extends DecratorOperand {

  protected Operand lOperand;

  protected Operand rOperand;

  public AndOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext context) {
    super(operands, expressionMetadata, context);
    if (operands.size() != 2)
      throw new IllegalArgumentException("Invalid and operand!");
    lOperand = operands.get(0);
    rOperand = operands.get(1);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double lScore = lOperand.operate(documentEntity, termDistance, hitMap);
    if (lScore < 0)
      return -1;
    double rScore = rOperand.operate(documentEntity, termDistance, hitMap);
    if (rScore < 0)
      return -1;
    if (context.isScoring())
      return (lScore + rScore) * boost;
    return 1;
  }
}
