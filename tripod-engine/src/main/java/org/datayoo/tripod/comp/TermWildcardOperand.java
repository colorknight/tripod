package org.datayoo.tripod.comp;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.WildcardMetadata;
import org.datayoo.tripod.operand.AtomOperand;
import org.datayoo.tripod.utils.WildcardMatcher;

import java.util.List;
import java.util.Map;

public class TermWildcardOperand extends AtomOperand {

  protected WildcardMatcher wildcardMatcher;

  public TermWildcardOperand(String field, WildcardMetadata wildcardMetadata,
      TripodContext context) {
    super(field, wildcardMetadata, context);
    wildcardMatcher = new WildcardMatcher(wildcardMetadata.getWildcard());
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    double score = 0;
    List<HitToken> hits = getHits(hitMap, field);
    for (TermEntity termEntity : documentEntity.getTermEntities(field)) {
      if (wildcardMatcher.isMatch(termEntity.getTerm())) {
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
