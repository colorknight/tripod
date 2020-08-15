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

public class RangeMetadata extends AbstractExpressionMetadata {

  protected boolean lInclude = false;

  protected boolean rInclude = false;

  protected ExpressionMetadata lExpr;

  protected ExpressionMetadata rExpr;

  public RangeMetadata(boolean lInclude, ExpressionMetadata lExpr,
      ExpressionMetadata rExpr, boolean rInclude) {
    super(ExpressionType.RANGE);
    Validate.notNull(lExpr, "lExpr is null!");
    Validate.notNull(rExpr, "rExpr is null!");

    this.lExpr = lExpr;
    this.rExpr = rExpr;
  }

  public boolean islInclude() {
    return lInclude;
  }

  public boolean isrInclude() {
    return rInclude;
  }

  public ExpressionMetadata getlExpr() {
    return lExpr;
  }

  public ExpressionMetadata getrExpr() {
    return rExpr;
  }

  public String toString() {
    String l = "[";
    String r = "]";
    if (!lInclude) {
      l = "{";
    }
    if (rInclude) {
      r = "}";
    }
    if (boost == 1)
      return String
          .format("%s%s,%s%s", l, lExpr.toString(), rExpr.toString(), r);
    else {
      return String
          .format("%s%s,%s%s^%d", l, lExpr.toString(), rExpr.toString(), r,
              boost);
    }
  }
}
