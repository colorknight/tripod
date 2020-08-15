package org.datayoo.tripod.logic;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.operand.DecratorOperand;

import java.util.List;
import java.util.Map;

public class OrOperand extends DecratorOperand {

  protected Operand lOperand;

  protected Operand rOperand;

  public OrOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext context) {
    super(operands, expressionMetadata, context);
    if (operands.size() != 2)
      throw new IllegalArgumentException("Invalid or operand!");
    lOperand = operands.get(0);
    rOperand = operands.get(1);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double score = lOperand.operate(documentEntity, termDistance, hitMap);
    if (score > 0) {
      if (context.isScoring())
        return score * boost;
      return 1;
    }
    score = rOperand.operate(documentEntity, termDistance, hitMap);
    if (score > 0) {
      if (context.isScoring())
        return score * boost;
      return 1;
    }
    return -1;
  }
}
