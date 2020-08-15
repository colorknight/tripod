parser grammar TripodParser;

options {
language = Java;
//backtrack=true;
tokenVocab=TripodLexer;
}

//tokens {
//}

@header {
import java.util.LinkedList;
import java.util.BitSet;
import org.datayoo.tripod.metadata.*;
import org.datayoo.tripod.exception.*;
}
@lexer::header {
package org.datayoo.tripod.antlr;

}
@members {
protected void mismatch(IntStream input, int ttype, BitSet follow)
throws RecognitionException
{
String message = String.format("'%c' is invalid!", input.LA(ttype));
throw new TripodRuntimeException(message);
}

public void recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
throws RecognitionException
{
throw e;
}
}

tripodExpression returns[BoolMetadata boolMetadata]
@init {
    $boolMetadata = new BoolMetadata();
}
  :
  //m:(a b AND c OR d OR e)
  // without duplicating the rules (but it allows recursion)
  lExpr = orExpression
  {
    $boolMetadata.getBoolExprs().add($lExpr.expressionMetadata);
  }
  ( rExpr = orExpression
  {
    $boolMetadata.getBoolExprs().add($rExpr.expressionMetadata);
  }
  )*
  ;

orExpression returns[ExpressionMetadata expressionMetadata]
  : lExpr = andExpression
  {
    $expressionMetadata = $lExpr.expressionMetadata;
  }
  (OR rExpr = andExpression
  {
    $expressionMetadata = new BinaryMetadata(ExpressionType.OR, $expressionMetadata, $rExpr.expressionMetadata);
  }
  )*
  ;

andExpression returns[ExpressionMetadata expressionMetadata]
  : lExpr = notExpression
  {
    $expressionMetadata = $lExpr.expressionMetadata;
  }
  (AND rExpr = notExpression
  {
    $expressionMetadata = new BinaryMetadata(ExpressionType.AND, $expressionMetadata, $rExpr.expressionMetadata);
  }
  )*
  ;

notExpression returns[ExpressionMetadata expressionMetadata]
@init {
    ExpressionType expressionType = null;
}
  :
  (NOT {expressionType = ExpressionType.NOT;} | MINUS {expressionType = ExpressionType.MINUS;})?
  exprCtx = basicExpression
  {
    if (expressionType == null)
        $expressionMetadata = $exprCtx.expressionMetadata;
    else {
        $expressionMetadata = new UnaryMetadata(expressionType, $exprCtx.expressionMetadata);
    }
  }
  ;

basicExpression returns[ExpressionMetadata expressionMetadata]
@init {
    boolean must = false;
    String field = null;
}
@after {
    if (field != null) {
        $expressionMetadata = new FieldMetadata(field, $expressionMetadata);
    }
    if (must) {
        $expressionMetadata = new UnaryMetadata(ExpressionType.MUST, $expressionMetadata);
    }
}
  : (MUST {must = true;})? ( t = TERM COLON
    {
        field = $t.getText();
    })?
    primaryCtx = primary
    {
        $expressionMetadata = $primaryCtx.expressionMetadata;
    }
    (CARET t = NUMBER?
    {
      if ($t != null) {
          $expressionMetadata.setBoost(Integer.valueOf($t.getText()));
      }
    })?
  ;

primary returns[ExpressionMetadata expressionMetadata]
  :
  rangeCtx = rangeTerm {$expressionMetadata = $rangeCtx.rangeMetadata;}
  | inCtx = inTerm {$expressionMetadata = $inCtx.inMetadata;}
  | parenCtx = parenExpression {$expressionMetadata = $parenCtx.parenMetadata;}
  | termCtx = term {$expressionMetadata = $termCtx.expressionMetadata;} (fuzzyCtx = fuzzyExpression
  {
     $fuzzyCtx.fuzzyMetadata.setExpr($expressionMetadata);
     $expressionMetadata = $fuzzyCtx.fuzzyMetadata;
  })?
  | phraseCtx = phrase {$expressionMetadata = $phraseCtx.phraseMetadata;} (proximityCtx = proximityExpression
  {
    $proximityCtx.proximityMetadata.setExpr($expressionMetadata);
    $expressionMetadata = $proximityCtx.proximityMetadata;
  })?
  ;

parenExpression returns[UnaryMetadata parenMetadata]
  :
  LPAREN exprCtx = tripodExpression
  {
    $parenMetadata = new UnaryMetadata(ExpressionType.PAREN, $exprCtx.boolMetadata);
  }
  RPAREN
  ;

rangeTerm returns[RangeMetadata rangeMetadata]
@init {
  boolean lInclude = false;
  boolean rInclude = false;
}
  :
  (LBRACK {lInclude = true;}|LBRACE)
  lExpr = rangeValue COMMA rExpr = rangeValue
  (RBRACK {rInclude = true;}|RBRACE)
  {
    $rangeMetadata = new RangeMetadata(lInclude, $lExpr.expressionMetadata, $rExpr.expressionMetadata, rInclude);
  }
  ;

rangeValue returns[ExpressionMetadata expressionMetadata]
  :
    phraseCtx = phrase {$expressionMetadata = $phraseCtx.phraseMetadata;}
  | termCtx = term {$expressionMetadata = $termCtx.expressionMetadata;}
  ;

inTerm returns[InMetadata inMetadata]
@init {
  List<ExpressionMetadata> expressions = new LinkedList<ExpressionMetadata>();
}
  :
  LBRACK
  expr = rangeValue {expressions.add($expr.expressionMetadata);}
  (expr  = rangeValue {expressions.add($expr.expressionMetadata);})*
  RBRACK
  {
    $inMetadata = new InMetadata(expressions);
  }
  ;

term returns[ExpressionMetadata expressionMetadata]:
  t = TERM  {
    if (WildcardMetadata.isWildcard($t.getText())) {
        $expressionMetadata = new WildcardMetadata($t.getText());
    } else {
        $expressionMetadata = new TermMetadata($t.getText());
    }
  }
  | t = NUMBER {
    $expressionMetadata = new TermMetadata($t.getText());
  }
  ;

phrase returns[PhraseMetadata phraseMetadata] :
  t = Q_PHRASE {$phraseMetadata = new PhraseMetadata($t.getText());}
  | t = DQ_PHRASE {$phraseMetadata = new PhraseMetadata($t.getText());}
  ;

fuzzyExpression returns[SuffixMetadata fuzzyMetadata]:
  SWANGDASH t = NUMBER?
  {
    if ($t != null) {
        if ($t.getText().indexOf('.') == -1) {
            $fuzzyMetadata = new SuffixMetadata(ExpressionType.FUZZY, Integer.valueOf($t.getText()));
        } else {
            $fuzzyMetadata = new SuffixMetadata(ExpressionType.FUZZY, Double.valueOf($t.getText()));
        }
    } else {
        $fuzzyMetadata = new SuffixMetadata(ExpressionType.FUZZY, null);
    }
  }
  ;
proximityExpression returns[SuffixMetadata proximityMetadata]:
  SWANGDASH t = NUMBER?
  {
    if ($t != null) {
        if ($t.getText().indexOf('.') == -1) {
            $proximityMetadata = new SuffixMetadata(ExpressionType.PROXIMITY, Integer.valueOf($t.getText()));
        } else {
            $proximityMetadata = new SuffixMetadata(ExpressionType.PROXIMITY, Double.valueOf($t.getText()));
        }
    } else {
        $proximityMetadata = new SuffixMetadata(ExpressionType.PROXIMITY, null);
    }
  }
  ;