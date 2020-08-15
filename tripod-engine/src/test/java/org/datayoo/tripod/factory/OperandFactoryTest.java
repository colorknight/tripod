package org.datayoo.tripod.operand.factory;

import junit.framework.TestCase;
import org.datayoo.tripod.*;
import org.datayoo.tripod.engine.TripodContextImpl;
import org.datayoo.tripod.factory.OperandFactory;
import org.datayoo.tripod.metadata.BoolMetadata;
import org.datayoo.tripod.operand.TripodTestHelper;
import org.datayoo.tripod.parser.TripodExpressionParser;

import java.util.Map;

public class OperandFactoryTest extends TestCase {

  public void testOperand() {
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap, true);
    TripodContextImpl tripodContext = TripodTestHelper
        .createTripodContext(true);
    tripodContext.calcIdf(documentEntity.getAllTerms());
    testOperand(createOperand1(tripodContext), documentEntity, true);
    testOperand(createOperand2(tripodContext), documentEntity, true);
    testOperand(createOperand3(tripodContext), documentEntity, false);
    testOperand(createOperand4(tripodContext), documentEntity, true);
    testOperand(createOperand5(tripodContext), documentEntity, true);
    testOperand(createOperand6(tripodContext), documentEntity, true);
    testOperand(createOperand7(tripodContext), documentEntity, true);
    testOperand(createOperand8(tripodContext), documentEntity, false);
  }

  protected void testOperand(Operand operand, DocumentEntity documentEntity,
      boolean success) {
    double d = operand.operate(documentEntity, true);
    if (success)
      assertTrue(d > 0);
    else
      assertTrue(d < 0);
    System.out
        .println(String.format("%s : %f", operand.getMetadata().toString(), d));
  }

  protected Operand createOperand1(TripodContext tripodContext) {
    String expr = "中办 任命 形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand2(TripodContext tripodContext) {
    String expr = "title:中办 任命 形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand3(TripodContext tripodContext) {
    String expr = "title:中办 +任命 形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand4(TripodContext tripodContext) {
    String expr = "(中办&title:中办)^2 任命 形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand5(TripodContext tripodContext) {
    String expr = "(中办8|title:中办)^2 任命 形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand6(TripodContext tripodContext) {
    String expr = "(中办8|title:中办)^2 任命 +形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand7(TripodContext tripodContext) {
    String expr = "(中办8|title:中办)^2 !任命 +形式主义";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

  protected Operand createOperand8(TripodContext tripodContext) {
    String expr = "title:(男足 男篮) && title:(愤怒 被喷 垃圾 碾压)^2";
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(expr);
    return OperandFactory.createOperand(boolMetadata, tripodContext);
  }

}
