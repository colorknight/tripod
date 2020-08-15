package org.datayoo.tripod.comp;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.metadata.PhraseMetadata;
import org.datayoo.tripod.metadata.RangeMetadata;
import org.datayoo.tripod.metadata.TermMetadata;
import org.datayoo.tripod.operand.AtomOperand;

import java.util.List;
import java.util.Map;

public class RangeOperand extends AtomOperand {

  protected boolean lInclude;

  protected String lStr;

  protected boolean rInclude;

  protected String rStr;

  public RangeOperand(String field, RangeMetadata rangeMetadata,
      TripodContext tripodContext) {
    super(field, rangeMetadata, tripodContext);
    lInclude = rangeMetadata.islInclude();
    rInclude = rangeMetadata.isrInclude();
    lStr = getStr(rangeMetadata.getlExpr());
    rStr = getStr(rangeMetadata.getrExpr());
  }

  protected String getStr(ExpressionMetadata expressionMetadata) {
    if (expressionMetadata instanceof TermMetadata) {
      TermMetadata tm = (TermMetadata) expressionMetadata;
      return tm.getTerm();
    } else if (expressionMetadata instanceof PhraseMetadata) {
      PhraseMetadata pm = (PhraseMetadata) expressionMetadata;
      return pm.getPhrase();
    } else {
      throw new IllegalArgumentException(String
          .format("Unsupported expression type %s!",
              expressionMetadata.getExpressionType()));
    }
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double score = 0;
    List<HitToken> hits = getHits(hitMap, field);
    for (TermEntity termEntity : documentEntity.getTermEntities(field)) {
      if (inRange(termEntity.getTerm())) {
        if (hits == null)
          return score(documentEntity, termEntity) * boost;
        else {
          score += score(documentEntity, termEntity) * boost;
          HitToken hit = new HitToken(termEntity.getOffset(),
              termEntity.getOffset() + termEntity.getTerm().length());
          hit.setToken(termEntity.getTerm());
          hits.add(hit);
        }
      }
    }
    if (score == 0)
      return -1;
    return score;
  }

  protected boolean inRange(String term) {
    int comp = term.compareTo(lStr);
    if (lInclude && comp == 0)
      return true;
    if (comp < 0)
      return false;
    comp = term.compareTo(rStr);
    if (rInclude && comp == 0)
      return true;
    if (comp > 0)
      return false;
    return true;
  }
}
