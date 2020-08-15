package org.datayoo.tripod.Paren;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.operand.DecratorOperand;

import java.util.List;
import java.util.Map;

public class ParenOperand extends DecratorOperand {

  protected Operand innerOperand;

  public ParenOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext context) {
    super(operands, expressionMetadata, context);
    if (operands.size() != 1)
      throw new IllegalArgumentException("Invalid paren operand!");
    innerOperand = operands.get(0);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double score = innerOperand.operate(documentEntity, termDistance, hitMap);
    if (score < 0)
      return -1;
    if (context.isScoring())
      return score * boost;
    return 1;
  }
}
