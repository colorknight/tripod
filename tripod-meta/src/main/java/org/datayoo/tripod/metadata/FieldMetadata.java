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

public class FieldMetadata extends AbstractExpressionMetadata {

  protected String field;

  protected ExpressionMetadata expr;

  public FieldMetadata(String field, ExpressionMetadata expr) {
    super(ExpressionType.FIELD);
    Validate.notNull(expr, "expr is null!");
    this.field = field;
    this.expr = expr;
  }

  public String getField() {
    return field;
  }

  public ExpressionMetadata getExpr() {
    return expr;
  }

  public String toString() {
    if (field == null)
      return expr.toString();
    else {
      return String.format("%s:%s", field, expr.toString());
    }
  }
}
