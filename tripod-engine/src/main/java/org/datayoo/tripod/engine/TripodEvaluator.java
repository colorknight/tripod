package org.datayoo.tripod.engine;

import org.datayoo.tripod.metadata.*;
import org.datayoo.tripod.parser.TripodExpressionParser;
import org.datayoo.tripod.utils.DoubleFormater;

public class TripodEvaluator {

  protected double unit = 1;

  protected double mustCoefficent = 1;

  protected double shouldCoefficent = 1.2;

  protected double phraseCoefficent = 8;

  protected double rangeCoefficent = 1.1;

  protected double fuzzyCoefficent = 4;

  protected double wildcardCoefficent = 8;

  protected double parenCoefficent = 1;

  protected double orCoefficent = 1;

  protected double andCoefficent = 1.2;

  protected double notCoefficent = 1;

  public double complexity(String rule) {
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(rule);
    return complexity(boolMetadata);
  }

  public double complexity(BoolMetadata boolMetadata) {
    double score = 0;
    for (ExpressionMetadata expressionMetadata : boolMetadata.getBoolExprs()) {
      if (expressionMetadata.getExpressionType() == ExpressionType.MUST
          || expressionMetadata.getExpressionType() == ExpressionType.MINUS)
        score += complexity(expressionMetadata);
      else {
        score += complexity(expressionMetadata) * shouldCoefficent;
      }
    }
    return DoubleFormater.format(score, 2);
  }

  protected double complexity(ExpressionMetadata expressionMetadata) {
    if (expressionMetadata.getExpressionType() == ExpressionType.SPACE_OR) {
      return complexity((BoolMetadata) expressionMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.AND) {
      return complexityOfAnd((BinaryMetadata) expressionMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.OR) {
      return complexityOfOr((BinaryMetadata) expressionMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.NOT) {
      return complexityOfNot((UnaryMetadata) expressionMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.MUST
        || expressionMetadata.getExpressionType() == ExpressionType.MINUS) {
      return complexityOfMust((UnaryMetadata) expressionMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.FIELD) {
      FieldMetadata fieldMetadata = (FieldMetadata) expressionMetadata;
      return complexity(fieldMetadata.getField());
    } else if (expressionMetadata.getExpressionType() == ExpressionType.PAREN) {
      UnaryMetadata unaryMetadata = (UnaryMetadata) expressionMetadata;
      return complexityOfParen(unaryMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.RANGE) {
      RangeMetadata rangeMetadata = (RangeMetadata) expressionMetadata;
      return complexity(rangeMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.PHRASE
        || expressionMetadata.getExpressionType() == ExpressionType.PROXIMITY) {
      return complexityOfPhrase(expressionMetadata);
    } else if (expressionMetadata.getExpressionType()
        == ExpressionType.WILDCARD) {
      WildcardMetadata wildcardMetadata = (WildcardMetadata) expressionMetadata;
      return complexity(wildcardMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.FUZZY) {
      SuffixMetadata suffixMetadata = (SuffixMetadata) expressionMetadata;
      return complexity(suffixMetadata);
    } else if (expressionMetadata.getExpressionType() == ExpressionType.TERM) {
      TermMetadata termMetadata = (TermMetadata) expressionMetadata;
      return complexity(termMetadata);
    } else {
      throw new IllegalArgumentException(String
          .format("Invalid expression type '%s'!",
              expressionMetadata.getExpressionType()));
    }
  }

  protected double complexityOfAnd(BinaryMetadata andMetadata) {
    double score = 0;
    score = complexity(andMetadata.getlExpr());
    score += complexity(andMetadata.getrExpr());
    return score * andCoefficent;
  }

  protected double complexityOfOr(BinaryMetadata orMetadata) {
    double score = 0;
    score = complexity(orMetadata.getlExpr());
    score += complexity(orMetadata.getrExpr());
    return score * orCoefficent;
  }

  protected double complexityOfNot(UnaryMetadata unaryMetadata) {
    double score = 0;
    score = complexity(unaryMetadata.getExpr());
    return score * notCoefficent;
  }

  protected double complexityOfParen(UnaryMetadata unaryMetadata) {
    double score = 0;
    score = complexity(unaryMetadata.getExpr());
    return score * parenCoefficent;
  }

  protected double complexityOfMust(UnaryMetadata unaryMetadata) {
    double score = 0;
    score = complexity(unaryMetadata.getExpr());
    return score * mustCoefficent;
  }

  protected double complexity(TermMetadata termMetadata) {
    return unit;
  }

  protected double complexity(WildcardMetadata wildcardMetadata) {
    return unit * wildcardCoefficent;
  }

  protected double complexity(SuffixMetadata suffixMetadata) {
    return unit * fuzzyCoefficent;
  }

  protected double complexityOfPhrase(ExpressionMetadata expressionMetadata) {
    if (expressionMetadata instanceof SuffixMetadata) {
      expressionMetadata = ((SuffixMetadata) expressionMetadata).getExpr();
    }
    PhraseMetadata phraseMetadata = (PhraseMetadata) expressionMetadata;
    String[] terms = phraseMetadata.getPhrase().split(" ");
    return terms.length * unit * phraseCoefficent;
  }

  protected double createRangeOperand(RangeMetadata rangeMetadata) {
    return unit * rangeCoefficent;
  }

  public TripodEvaluator setUnit(double unit) {
    this.unit = unit;
    return this;
  }

  public TripodEvaluator setMustCoefficent(double mustCoefficent) {
    this.mustCoefficent = mustCoefficent;
    return this;
  }

  public TripodEvaluator setShouldCoefficent(double shouldCoefficent) {
    this.shouldCoefficent = shouldCoefficent;
    return this;
  }

  public TripodEvaluator setPhraseCoefficent(double distanceCoefficent) {
    this.phraseCoefficent = phraseCoefficent;
    return this;
  }

  public TripodEvaluator setRangeCoefficent(double rangeCoefficent) {
    this.rangeCoefficent = rangeCoefficent;
    return this;
  }

  public TripodEvaluator setFuzzyCoefficent(double fuzzyCoefficent) {
    this.fuzzyCoefficent = fuzzyCoefficent;
    return this;
  }

  public TripodEvaluator setWildcardCoefficent(double wildcardCoefficent) {
    this.wildcardCoefficent = wildcardCoefficent;
    return this;
  }

  public TripodEvaluator setParenCoefficent(double parenCoefficent) {
    this.parenCoefficent = parenCoefficent;
    return this;
  }

  public TripodEvaluator setOrCoefficent(double orCoefficent) {
    this.orCoefficent = orCoefficent;
    return this;
  }

  public TripodEvaluator setAndCoefficent(double andCoefficent) {
    this.andCoefficent = andCoefficent;
    return this;
  }

  public TripodEvaluator setNotCoefficent(double notCoefficent) {
    this.notCoefficent = notCoefficent;
    return this;
  }
}
