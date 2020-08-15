package org.datayoo.tripod.bool;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.operand.DecratorOperand;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoolOperand extends DecratorOperand {

  protected List<Operand> mustOperands = new LinkedList<Operand>();

  protected List<Operand> shouldOperands = new LinkedList<Operand>();

  public BoolOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext tripodContext) {
    super(operands, expressionMetadata, tripodContext);
    for (Operand operand : operands) {
      if (operand instanceof MustOperand || operand instanceof MustNotOperand) {
        mustOperands.add(operand);
      } else {
        shouldOperands.add(operand);
      }
    }
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double score = mustOperate(documentEntity, termDistance, hitMap);
    if (score < 0)
      return score;
    if (score > 0 && !context.isScoring())
      return score;
    int overlap = mustOperands.size();
    for (Operand operand : shouldOperands) {
      double d = operand.operate(documentEntity, termDistance, hitMap);
      if (d > 0) {
        if (context.isScoring()) {
          score += d;
          overlap++;
        } else {
          return 1;
        }
      }
    }
    if (score == 0)
      return -1;
    if (context.isScoring())
      return coord(overlap) * score;
    else
      return 1;
  }

  protected double mustOperate(DocumentEntity documentEntity,
      boolean termDistance, Map<String, List<HitToken>> hitMap) {
    double scoreSum = 0;
    for (Operand operand : mustOperands) {
      double d = operand.operate(documentEntity, termDistance, hitMap);
      if (d < 0)
        return d;
      scoreSum += d;
    }
    return scoreSum;
  }

  protected double coord(int overlap) {
    return overlap * 1.0 / (mustOperands.size() + shouldOperands.size());
  }

}
