package org.datayoo.tripod.operand;

import org.datayoo.tripod.*;
import org.datayoo.tripod.metadata.ExpressionMetadata;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AtomOperand extends AbstractOperand {

  protected String field;

  public AtomOperand(ExpressionMetadata expressionMetadata,
      TripodContext context) {
    super(expressionMetadata, context);
  }

  public AtomOperand(String field, ExpressionMetadata expressionMetadata,
      TripodContext context) {
    this(expressionMetadata, context);
    if (field != null) {
      this.field = field;
      // 缺省值
      if (expressionMetadata.getBoost() == 1)
        boost = context.getBoost(field);
    } else {
      FieldMetadata fieldMetadata = context.getDefaultField();
      this.field = fieldMetadata.getField();
      this.boost = fieldMetadata.getBoost();
    }
  }

  protected double score(DocumentEntity documentEntity, TermEntity termEntity) {
    if (!context.isScoring())
      return 1;
    double tf = getTf(documentEntity, termEntity);
    double idf = context.getIdf(termEntity.getTerm());
    double lengthNorm = lengthNorm(documentEntity);
    return tf * idf * idf * boost * lengthNorm;
  }

  protected double getTf(DocumentEntity documentEntity, TermEntity termEntity) {
    return documentEntity.getTermFrequency(termEntity.getTerm());
  }

  protected double lengthNorm(DocumentEntity documentEntity) {
    return boost * (1.0 / Math.sqrt(documentEntity.getTermsCount()));
  }

  protected List<HitToken> getHits(Map<String, List<HitToken>> hitMap,
      String field) {
    if (hitMap == null)
      return null;
    List<HitToken> hits = hitMap.get(field);
    if (hits == null) {
      hits = new LinkedList<>();
      hitMap.put(field, hits);
    }
    return hits;
  }

  @Override
  public ExpressionMetadata getMetadata() {
    return expressionMetadata;
  }

}
