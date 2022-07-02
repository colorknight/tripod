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

import junit.framework.TestCase;
import org.datayoo.tripod.parser.TripodExpressionParser;

public class ParserTest extends TestCase {

  public void testParse() {

    String expr1 = "test 中文 'this is phrase1'&\"this is phrase2\"";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "\"this is phrase2\"~2^ title:(a1 b2) | c3~0.8 & te??";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "ted* [a2,b6} -list !ge";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "+list~0.8";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "test (test~0.8)^2 'test'~3";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse2() {
    String expr1 = "-list";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());

  }

  public void testParse3() {
    String expr1 = "title:a1 b2";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "title:(a1~0.2 b2^2)";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "title:(a1^2)^2";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "+title:a1~0.2^2";
    expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
    expr1 = "+title:(a1~0.8)";
    try {
      expressionMetadata = TripodExpressionParser.parseFromString(expr1);
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  public void testParse4() {
    String expr1 = "test test^2 'test'~3";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse5() {
    String expr1 = "test & (test^2 | 'test')";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse6() {
    String expr1 = "title:(男足 男篮) AND title:(愤怒 被喷 垃圾 碾压)^2";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse7() {
    String expr1 = "+777 -23.2 AND title:(愤怒 被喷 垃圾 碾压)^2";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse8() {
    String expr1 = "+[2,5] & -{4,5.5} title:(愤怒 被喷 垃圾 碾压)^2";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse9() {
    String expr1 = "title:[愤怒 被喷 垃圾 碾压]^2";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

  public void testParse10() {
    String expr1 = "a.title:[愤怒 被喷 垃圾 碾压]^2 a.b.c:[士兵]";
    ExpressionMetadata expressionMetadata = TripodExpressionParser
        .parseFromString(expr1);
    System.out.println(expressionMetadata.toString());
  }

}
