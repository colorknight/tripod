/*
 * Copyright 2020 Taiding Tang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
