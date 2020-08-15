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
package org.datayoo.yooler.engine;

import junit.framework.TestCase;
import org.datayoo.tripod.engine.TripodEvaluator;

public class TripodEvaluatorTest extends TestCase {

  public void testEvaluator() {
    TripodEvaluator evaluator = new TripodEvaluator();
    System.out.println(evaluator.complexity("(中办&title:中办)^2 任命 形式主义"));
    System.out.println(evaluator.complexity("(中办&title:中办)^2 任命"));
    System.out.println(evaluator.complexity("w1 +w2 !w3 \"w4 w5\" w6~0.8"));
  }
}
