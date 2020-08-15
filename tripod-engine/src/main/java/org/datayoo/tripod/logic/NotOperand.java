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

public class NotOperand extends DecratorOperand {

  protected Operand notOperand;

  public NotOperand(List<Operand> operands,
      ExpressionMetadata expressionMetadata, TripodContext context) {
    super(operands, expressionMetadata, context);
    if (operands.size() != 1)
      throw new IllegalArgumentException("Invalid must not operand!");
    notOperand = operands.get(0);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double d = notOperand.operate(documentEntity, termDistance, hitMap);
    if (d > 0) {
      return -1;
    } else {
      if (context.isScoring())
        return 1 / Math.sqrt(documentEntity.getTermsCount());
      return 1;
    }
  }
}
