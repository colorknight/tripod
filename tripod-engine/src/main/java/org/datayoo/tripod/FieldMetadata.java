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
