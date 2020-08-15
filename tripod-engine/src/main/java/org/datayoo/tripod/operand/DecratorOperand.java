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
