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

import java.util.LinkedList;
import java.util.List;

public class BoolMetadata extends AbstractExpressionMetadata {

  protected List<ExpressionMetadata> boolExprs = new LinkedList<ExpressionMetadata>();

  public BoolMetadata() {
    super(ExpressionType.SPACE_OR);
  }

  public List<ExpressionMetadata> getBoolExprs() {
    return boolExprs;
  }

  @Override
  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    for (ExpressionMetadata expr : boolExprs) {
      if (sbuf.length() != 0)
        sbuf.append(' ');
      sbuf.append(expr.toString());
    }
    return sbuf.toString();
  }
}
