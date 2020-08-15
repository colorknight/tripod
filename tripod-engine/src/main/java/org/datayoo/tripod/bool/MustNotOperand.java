package org.datayoo.tripod.bool;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.operand.DecratorOperand;

import java.util.List;
import java.util.Map;

public class MustNotOperand extends DecratorOperand {

  protected Operand mustNotOperand;

  public MustNotOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext tripodContext) {
    super(operands, expressionMetadata, tripodContext);
    if (operands.size() != 1)
      throw new IllegalArgumentException("Invalid must not operand!");
    mustNotOperand = operands.get(0);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double d = mustNotOperand.operate(documentEntity, termDistance, hitMap);
    if (d > 0) {
      return -1;
    } else {
      if (context.isScoring())
        return 1 / Math.sqrt(documentEntity.getTermsCount());
      else
        return 1;
    }
  }
}
