package org.datayoo.tripod.factory;

import org.apache.commons.lang3.Validate;
import org.datayoo.tripod.Operand;
import org.datayoo.tripod.Paren.ParenOperand;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.bool.BoolOperand;
import org.datayoo.tripod.bool.MustNotOperand;
import org.datayoo.tripod.bool.MustOperand;
import org.datayoo.tripod.comp.*;
import org.datayoo.tripod.logic.AndOperand;
import org.datayoo.tripod.logic.NotOperand;
import org.datayoo.tripod.logic.OrOperand;
import org.datayoo.tripod.metadata.*;

import java.util.LinkedList;
import java.util.List;

public abstract class OperandFactory {

  public static BoolOperand createOperand(BoolMetadata boolMetadata,
      TripodContext tripodContext) {
    Validate.notNull(boolMetadata, "boolMetadata is null!");
    Validate.notNull(tripodContext, "tripodContext is null!");
    return (BoolOperand) createOperand(null, boolMetadata, tripodContext);
  }

  protected static Operand createOperand(String field,
      ExpressionMetadata expressionMetadata, TripodContext tripodContext) {
    if (expressionMetadata.getExpressionType() == ExpressionType.SPACE_OR) {
      return createBoolOperand(field, (BoolMetadata) expressionMetadata,
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.AND) {
      return createAndOperand(field, (BinaryMetadata) expressionMetadata,
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.OR) {
      return createOrOperand(field, (BinaryMetadata) expressionMetadata,
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.NOT) {
      return createNotOperand(field, (UnaryMetadata) expressionMetadata,
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.MUST) {
      return createMustOperand(field, (UnaryMetadata) expressionMetadata,
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.MINUS) {
      return createMustNotOperand(field, (UnaryMetadata) expressionMetadata,
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.FIELD) {
      FieldMetadata fieldMetadata = (FieldMetadata) expressionMetadata;
      return createOperand(fieldMetadata.getField(), fieldMetadata.getExpr(),
          tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.PAREN) {
      UnaryMetadata unaryMetadata = (UnaryMetadata) expressionMetadata;
      return createParenOperand(field, unaryMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.RANGE) {
      RangeMetadata rangeMetadata = (RangeMetadata) expressionMetadata;
      return createRangeOperand(field, rangeMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.IN) {
      InMetadata inMetadata = (InMetadata) expressionMetadata;
      return createInOperand(field, inMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType()
        == ExpressionType.PHRASE) {
      return createPhraseOperand(field, expressionMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType()
        == ExpressionType.PROXIMITY) {
      return createPhraseOperand(field, expressionMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType()
        == ExpressionType.WILDCARD) {
      WildcardMetadata wildcardMetadata = (WildcardMetadata) expressionMetadata;
      return createTermWildcardOperand(field, wildcardMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.FUZZY) {
      SuffixMetadata suffixMetadata = (SuffixMetadata) expressionMetadata;
      return createTermFuzzyOperand(field, suffixMetadata, tripodContext);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.TERM) {
      TermMetadata termMetadata = (TermMetadata) expressionMetadata;
      return createTermMatchOperand(field, termMetadata, tripodContext);
    } else {
      throw new IllegalArgumentException(String
          .format("Invalid expression type '%s'!",
              expressionMetadata.getExpressionType()));
    }
  }

  protected static BoolOperand createBoolOperand(String field,
      BoolMetadata boolMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    for (ExpressionMetadata expressionMetadata : boolMetadata.getBoolExprs()) {
      // 当布尔表达式在括号内时有可能引起权重值的变化
      if (boolMetadata.getBoost() != 1)
        expressionMetadata.setBoost(boolMetadata.getBoost());
      Operand operand = createOperand(field, expressionMetadata, tripodContext);
      operands.add(operand);
    }
    return new BoolOperand(operands, boolMetadata, tripodContext);
  }

  protected static AndOperand createAndOperand(String field,
      BinaryMetadata andMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    Operand operand = createOperand(field, andMetadata.getlExpr(),
        tripodContext);
    operands.add(operand);
    operand = createOperand(field, andMetadata.getrExpr(), tripodContext);
    operands.add(operand);
    return new AndOperand(operands, andMetadata, tripodContext);
  }

  protected static OrOperand createOrOperand(String field,
      BinaryMetadata orMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    Operand operand = createOperand(field, orMetadata.getlExpr(),
        tripodContext);
    operands.add(operand);
    operand = createOperand(field, orMetadata.getrExpr(), tripodContext);
    operands.add(operand);
    return new OrOperand(operands, orMetadata, tripodContext);
  }

  protected static NotOperand createNotOperand(String field,
      UnaryMetadata unaryMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    Operand operand = createOperand(field, unaryMetadata.getExpr(),
        tripodContext);
    operands.add(operand);
    return new NotOperand(operands, unaryMetadata, tripodContext);
  }

  protected static ParenOperand createParenOperand(String field,
      UnaryMetadata unaryMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    Operand operand = createOperand(field, unaryMetadata.getExpr(),
        tripodContext);
    operands.add(operand);
    return new ParenOperand(operands, unaryMetadata, tripodContext);
  }

  protected static MustOperand createMustOperand(String field,
      UnaryMetadata unaryMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    Operand operand = createOperand(field, unaryMetadata.getExpr(),
        tripodContext);
    operands.add(operand);
    return new MustOperand(operands, unaryMetadata, tripodContext);
  }

  protected static MustNotOperand createMustNotOperand(String field,
      UnaryMetadata unaryMetadata, TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    Operand operand = createOperand(field, unaryMetadata.getExpr(),
        tripodContext);
    operands.add(operand);
    return new MustNotOperand(operands, unaryMetadata, tripodContext);
  }

  protected static Operand createTermMatchOperand(String field,
      TermMetadata termMetadata, TripodContext tripodContext) {
    return new TermMatchOperand(field, termMetadata, tripodContext);
  }

  protected static Operand createTermWildcardOperand(String field,
      WildcardMetadata wildcardMetadata, TripodContext tripodContext) {
    return new TermWildcardOperand(field, wildcardMetadata, tripodContext);
  }

  protected static Operand createTermFuzzyOperand(String field,
      SuffixMetadata suffixMetadata, TripodContext tripodContext) {
    return new TermFuzzyOperand(field, suffixMetadata, tripodContext);
  }

  protected static Operand createPhraseOperand(String field,
      ExpressionMetadata expressionMetadata, TripodContext tripodContext) {
    return new PhraseOperand(field, expressionMetadata, tripodContext);
  }

  protected static Operand createRangeOperand(String field,
      RangeMetadata rangeMetadata, TripodContext tripodContext) {
    return new RangeOperand(field, rangeMetadata, tripodContext);
  }

  protected static Operand createInOperand(String field, InMetadata inMetadata,
      TripodContext tripodContext) {
    List<Operand> operands = new LinkedList<Operand>();
    for (ExpressionMetadata expressionMetadata : inMetadata.getExpressions()) {
      Operand operand = createOperand(field, expressionMetadata, tripodContext);
      operands.add(operand);
    }
    return new InOperand(operands, inMetadata, tripodContext);
  }

}
