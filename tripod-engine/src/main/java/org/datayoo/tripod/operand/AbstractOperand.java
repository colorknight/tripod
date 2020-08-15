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
import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;

public abstract class AbstractOperand implements Operand {

  protected int boost;

  protected ExpressionMetadata expressionMetadata;

  protected TripodContext context;

  public AbstractOperand(ExpressionMetadata expressionMetadata,
      TripodContext context) {
    Validate.notNull(context, "context is null!");
    Validate.notNull(expressionMetadata, "expressionMetadata is null!");
    this.context = context;
    this.boost = expressionMetadata.getBoost();
    this.expressionMetadata = expressionMetadata;
  }

  @Override
  public ExpressionMetadata getMetadata() {
    return expressionMetadata;
  }

  @Override
  public double operate(DocumentEntity documentEntity) {
    return operate(documentEntity, true, null);
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance) {
    return operate(documentEntity, termDistance, null);
  }
}
