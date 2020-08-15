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
package org.datayoo.tripod.utils;

import org.apache.commons.lang3.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WildcardMatcher {

  public static final char ESCAPE_CH = '\\';

  public static final char ANY_CH = '?';

  public static final char MULTI_ANY_CH = '*';

  public static final char[] escapeChars = new char[] { '$', '(', ')', '+', '.',
      '[', ']', '^', '{', '}', '|', '\\', '?', '*'
  };

  protected Pattern pattern;

  public WildcardMatcher(String fuzzyExp) {
    Validate.notEmpty(fuzzyExp, fuzzyExp);
    String regExp = trans2RegExp(fuzzyExp);
    pattern = Pattern.compile(regExp);
  }

  protected String trans2RegExp(String fuzzyExp) {
    StringBuffer sbuf = new StringBuffer();
    boolean escape = false;
    for (int i = 0; i < fuzzyExp.length(); i++) {
      if (fuzzyExp.charAt(i) == ESCAPE_CH && !escape) {
        escape = true;
      } else if (fuzzyExp.charAt(i) == ANY_CH && !escape) {
        sbuf.append('.');
      } else if (fuzzyExp.charAt(i) == MULTI_ANY_CH && !escape) {
        sbuf.append(".*");
      } else if (isEscapeChar(fuzzyExp.charAt(i))) {
        sbuf.append("\\");
        sbuf.append(fuzzyExp.charAt(i));
        escape = false;
      } else {
        if (escape)
          escape = false;
        sbuf.append(fuzzyExp.charAt(i));
      }
    }
    return sbuf.toString();
  }

  public boolean isMatch(String data) {
    Matcher matcher = pattern.matcher(data);
    return matcher.matches();
  }

  protected static boolean isEscapeChar(char ch) {
    for (int i = 0; i < escapeChars.length; i++) {
      if (escapeChars[i] == ch)
        return true;
    }
    return false;
  }

}
