package org.datayoo.tripod.comp;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.InMetadata;
import org.datayoo.tripod.operand.AtomOperand;

import java.util.List;
import java.util.Map;

public class InOperand extends AtomOperand {

  protected List<Operand> operands;

  public InOperand(List<Operand> operands, InMetadata inMetadata,
      TripodContext context) {
    super(inMetadata, context);
    this.operands = operands;
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    for (Operand operand : operands) {
      double d = operand.operate(documentEntity, termDistance, hitMap);
      if (d > 0)
        return d;
    }
    return -1;
  }
}
