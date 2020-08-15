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
package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class AbstractExpressionMetadata
    implements ExpressionMetadata, Serializable {

  protected ExpressionType expressionType;

  protected int boost = 1;

  public AbstractExpressionMetadata(ExpressionType expressionType) {
    Validate.notNull(expressionType, "expressionType is null!");
    this.expressionType = expressionType;
  }

  @Override
  public ExpressionType getExpressionType() {
    return expressionType;
  }

  public int getBoost() {
    return boost;
  }

  public void setBoost(int boost) {
    this.boost = boost;
  }
}
