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
