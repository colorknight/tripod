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

public class SuffixMetadata extends AbstractExpressionMetadata {

  protected ExpressionMetadata expr;

  protected Number number;

  public SuffixMetadata(ExpressionType expressionType, Number number) {
    super(expressionType);
    if (expressionType == ExpressionType.PROXIMITY) {
      if (number != null && !(number instanceof Integer)) {
        throw new IllegalArgumentException(
            "Invalid format! Number should be unsigned integer!");
      }
      if (number.intValue() < 0)
        throw new IllegalArgumentException(
            "Invalid format! Number should be bigger than 1!");
    } else if (expressionType == ExpressionType.FUZZY) {
      if (number != null && (number.doubleValue() <= 0
          || number.doubleValue() > 1)) {
        throw new IllegalArgumentException(
            "Invalid format! Number should be (0,1]!");
      }
    } else {
      throw new IllegalArgumentException("Invalid expression type!");
    }

    this.number = number;
  }

  public ExpressionMetadata getExpr() {
    return expr;
  }

  public void setExpr(ExpressionMetadata expr) {
    Validate.notNull(expr, "expr is null!");
    this.expr = expr;
  }

  public Number getNumber() {
    return number;
  }

  public String toString() {
    String op = "~";
    StringBuffer sbuf = new StringBuffer();
    if (expr != null)
      sbuf.append(expr.toString());
    sbuf.append(op);
    if (number != null)
      sbuf.append(number.toString());
    if (boost != 1) {
      sbuf.append("^");
      sbuf.append(boost);
    }
    return sbuf.toString();
  }
}
