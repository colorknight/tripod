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
