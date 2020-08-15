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

public class TermMetadata extends AbstractExpressionMetadata {

  protected String term;

  public TermMetadata(String term) {
    super(ExpressionType.TERM);
    Validate.notEmpty(term, "Term is empty!");
    this.term = term;
  }

  public String getTerm() {
    return term;
  }

  public String toString() {
    if (boost == 1)
      return term;
    return String.format("%s^%d", term, boost);
  }
}
