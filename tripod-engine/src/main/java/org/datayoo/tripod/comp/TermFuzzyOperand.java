package org.datayoo.tripod.comp;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.ExpressionType;
import org.datayoo.tripod.metadata.SuffixMetadata;
import org.datayoo.tripod.metadata.TermMetadata;
import org.datayoo.tripod.operand.AtomOperand;
import org.datayoo.tripod.utils.LevDistance;

import java.util.List;
import java.util.Map;

public class TermFuzzyOperand extends AtomOperand {

  protected char[] termChars;

  protected double similarity;

  public TermFuzzyOperand(String field, SuffixMetadata suffixMetadata,
      TripodContext context) {
    super(field, suffixMetadata, context);
    if (suffixMetadata.getExpressionType() != ExpressionType.FUZZY)
      throw new IllegalArgumentException(
          "suffixMetadata is not a fuzzy expression!");
    TermMetadata termMetadata = (TermMetadata) suffixMetadata.getExpr();
    termChars = termMetadata.getTerm().toCharArray();
    boost = termMetadata.getBoost();
    similarity = suffixMetadata.getNumber().doubleValue();
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double score = 0;
    List<HitToken> hits = getHits(hitMap, field);
    for (TermEntity termEntity : documentEntity.getTermEntities(field)) {
      char[] dst = termEntity.getTerm().toCharArray();
      int d = LevDistance.lev(termChars, dst);
      double sim = 1 - (d * 1.0 / Math.min(termChars.length, dst.length));
      if (sim >= similarity) {
        if (hits == null)
          return score(documentEntity, termEntity);
        else {
          score += score(documentEntity, termEntity);
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
}
