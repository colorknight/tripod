package org.datayoo.yooler.operand.comp;

import junit.framework.TestCase;
import org.datayoo.tripod.*;
import org.datayoo.tripod.bool.MustNotOperand;
import org.datayoo.tripod.bool.MustOperand;
import org.datayoo.tripod.comp.*;
import org.datayoo.tripod.engine.Tripod;
import org.datayoo.tripod.engine.TripodContextImpl;
import org.datayoo.tripod.metadata.*;
import org.datayoo.tripod.operand.TripodTestHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OperandTest extends TestCase {

  public void testPhrase() {
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap, true);
    TripodContextImpl yoolerContext = TripodTestHelper
        .createTripodContext(true);
    PhraseOperand phraseOperand = createPhraseOperand(yoolerContext, 1);
    yoolerContext.calcIdf(documentEntity.getAllTerms());
    double d = phraseOperand.operate(documentEntity, true);
    System.out.println(d);
    d = phraseOperand.operate(documentEntity, false);
    System.out.println(d);
    phraseOperand = createPhraseOperand(yoolerContext, 13);
    d = phraseOperand.operate(documentEntity, false);
    System.out.println(d);
  }

  protected PhraseOperand createPhraseOperand(TripodContext context,
      int distance) {
    PhraseMetadata phraseMetadata = new PhraseMetadata("\"中办发 第5代领导 形而上学\"");
    if (distance > 0) {
      SuffixMetadata fuzzyMetadata = new SuffixMetadata(
          ExpressionType.PROXIMITY, distance);
      fuzzyMetadata.setExpr(phraseMetadata);
      return new PhraseOperand(null, fuzzyMetadata, context);
    }
    return new PhraseOperand(null, phraseMetadata, context);
  }

  public void testTerm() {
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap, true);
    TripodContextImpl tripodContext = TripodTestHelper
        .createTripodContext(true);
    Operand operand = createTermOperand(tripodContext, 0);
    tripodContext.calcIdf(documentEntity.getAllTerms());
    double d = operand.operate(documentEntity, true);
    System.out.println(d);
    operand = createTermOperand(tripodContext, 0.5);
    d = operand.operate(documentEntity, true);
    System.out.println(d);
    operand = createWildcardOperand(tripodContext);
    d = operand.operate(documentEntity, true);
    System.out.println(d);
  }

  protected Operand createTermOperand(TripodContext tripodContext,
      double distance) {
    if (distance == 0) {
      TermMetadata termMetadata = new TermMetadata("中发");
      return new TermMatchOperand(null, termMetadata, tripodContext);
    } else {
      TermMetadata termMetadata = new TermMetadata("中办发");
      SuffixMetadata fuzzyMetadata = new SuffixMetadata(ExpressionType.FUZZY,
          distance);
      fuzzyMetadata.setExpr(termMetadata);
      return new TermFuzzyOperand(null, fuzzyMetadata, tripodContext);
    }
  }

  protected Operand createWildcardOperand(TripodContext tripodContext) {
    WildcardMetadata wildcardMetadata = new WildcardMetadata("第?代*");
    return new TermWildcardOperand(null, wildcardMetadata, tripodContext);
  }

  public void testRange() {
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap, true);
    TripodContextImpl tripodContext = TripodTestHelper
        .createTripodContext(true);
    Operand operand = createRangeOperand(tripodContext);
    tripodContext.calcIdf(documentEntity.getAllTerms());
    double d = operand.operate(documentEntity, true);
    System.out.println(d);
  }

  protected Operand createRangeOperand(TripodContext context) {
    RangeMetadata rangeMetadata = new RangeMetadata(false,
        new TermMetadata("中办"), new TermMetadata("中办发展"), false);
    return new RangeOperand(null, rangeMetadata, context);
  }

  public void testMustOperand() {
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap, true);
    TripodContextImpl tripodContext = TripodTestHelper
        .createTripodContext(true);
    Operand operand = createMustOperand(tripodContext, false);
    tripodContext.calcIdf(documentEntity.getAllTerms());
    double d = operand.operate(documentEntity, true);
    System.out.println(d);
    operand = createMustOperand(tripodContext, true);
    tripodContext.calcIdf(documentEntity.getAllTerms());
    d = operand.operate(documentEntity, true);
    System.out.println(d);
  }

  protected Operand createMustOperand(TripodContext context, boolean not) {
    WildcardMetadata wildcardMetadata = new WildcardMetadata("第?代*");
    LinkedList<Operand> operands = new LinkedList<Operand>();
    TermWildcardOperand operand = new TermWildcardOperand(null,
        wildcardMetadata, context);
    operands.add(operand);
    UnaryMetadata unaryMetadata;
    if (!not) {
      unaryMetadata = new UnaryMetadata(ExpressionType.MUST, wildcardMetadata);
      return new MustOperand(operands, unaryMetadata, context);
    } else {
      unaryMetadata = new UnaryMetadata(ExpressionType.MINUS, wildcardMetadata);
      return new MustNotOperand(operands, unaryMetadata, context);
    }
  }

}
