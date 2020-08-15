package org.datayoo.tripod;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class FieldMetadata implements Serializable {

  protected String field;

  protected int boost;

  public FieldMetadata(String field, int boost) {
    Validate.notEmpty(field, "field is empty!");
    Validate.isTrue(boost > 0, "boost is less than 0!");

    this.field = field;
    this.boost = boost;
  }

  public String getField() {
    return field;
  }

  public int getBoost() {
    return boost;
  }

  @Override
  public int hashCode() {
    return field.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof FieldMetadata))
      return false;
    FieldMetadata fm = (FieldMetadata) obj;
    if (!fm.getField().equals(field))
      return false;
    return fm.getBoost() == boost;
  }
}
