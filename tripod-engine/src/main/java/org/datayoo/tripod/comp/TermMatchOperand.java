package org.datayoo.tripod.comp;

import org.datayoo.tripod.DocumentEntity;
import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.TripodContext;
import org.datayoo.tripod.metadata.TermMetadata;
import org.datayoo.tripod.operand.AtomOperand;

import java.util.List;
import java.util.Map;

public class TermMatchOperand extends AtomOperand {

  protected String term;

  public TermMatchOperand(String field, TermMetadata termMetadata,
      TripodContext context) {
    super(field, termMetadata, context);
    term = termMetadata.getTerm();
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    List<HitToken> hits = getHits(hitMap, field);
    List<TermEntity> termEntities = documentEntity.findTerm(field, term);
    if (termEntities == null || termEntities.size() == 0)
      return -1;
    double score = score(documentEntity, termEntities.get(0));
    if (score > 0 && hits != null) {
      for (TermEntity termEntity : termEntities) {
        HitToken hit = new HitToken(termEntity.getOffset(),
            termEntity.getOffset() + termEntity.getTerm().length());
        hit.setToken(termEntity.getTerm());
        hits.add(hit);
      }
    }
    return score;
  }
}
