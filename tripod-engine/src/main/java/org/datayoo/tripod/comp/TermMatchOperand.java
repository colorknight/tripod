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
