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

public class WildcardMetadata extends AbstractExpressionMetadata {

  public static final char ESCAPE_CH = '\\';

  public static final char ANY_CH = '?';

  public static final char MULTI_ANY_CH = '*';

  protected String wildcard;

  public WildcardMetadata(String wildcard) {
    super(ExpressionType.WILDCARD);
    Validate.notEmpty(wildcard, "wildcard is empty!");
    this.wildcard = wildcard;
  }

  public String getWildcard() {
    return wildcard;
  }

  public String toString() {
    if (boost == 1)
      return wildcard;
    else
      return String.format("%s^%d", wildcard, boost);
  }

  public static boolean isWildcard(String text) {
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) == ANY_CH || text.charAt(i) == MULTI_ANY_CH) {
        if (i > 0) {
          if (text.charAt(i - 1) != ESCAPE_CH) {
            return true;
          }
        } else {
          return true;
        }
      }
    }
    return false;
  }

}
