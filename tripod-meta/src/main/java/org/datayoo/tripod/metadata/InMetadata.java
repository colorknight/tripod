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

import java.util.List;

public class InMetadata extends AbstractExpressionMetadata {

  protected List<ExpressionMetadata> expressions;

  public InMetadata(List<ExpressionMetadata> expressions) {
    super(ExpressionType.IN);
    this.expressions = expressions;
  }

  public List<ExpressionMetadata> getExpressions() {
    return expressions;
  }

  public String toString() {
    StringBuilder sbud = new StringBuilder();
    sbud.append('[');
    for (ExpressionMetadata expression : expressions) {
      if (sbud.length() > 1)
        sbud.append(' ');
      sbud.append(expression.toString());
    }
    sbud.append(']');
    if (boost != 1) {
      sbud.append('^');
      sbud.append(boost);
    }
    return sbud.toString();
  }
}
