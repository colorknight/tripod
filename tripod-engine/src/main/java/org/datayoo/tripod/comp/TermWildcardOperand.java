/*
 * Copyright 2020 Taiding Tang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
