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
import org.datayoo.tripod.metadata.ExpressionMetadata;
import org.datayoo.tripod.metadata.ExpressionType;
import org.datayoo.tripod.metadata.PhraseMetadata;
import org.datayoo.tripod.metadata.SuffixMetadata;
import org.datayoo.tripod.operand.AtomOperand;

import java.util.List;
import java.util.Map;

public class PhraseOperand extends AtomOperand {

  protected String[] terms;

  protected int distance = 1; // 两个相连的TERM其距离缺省为1，这与距离计算的方式有关。

  public PhraseOperand(String field, ExpressionMetadata expressionMetadata,
      TripodContext context) {
    super(field, expressionMetadata, context);
    int distance = 1;
    if (expressionMetadata.getExpressionType() == ExpressionType.PROXIMITY) {
      SuffixMetadata suffixMetadata = (SuffixMetadata) expressionMetadata;
      expressionMetadata = suffixMetadata.getExpr();
      distance = suffixMetadata.getNumber().intValue() + 1;
    }
    if (expressionMetadata.getExpressionType() == ExpressionType.PHRASE) {
      PhraseMetadata phraseMetadata = (PhraseMetadata) expressionMetadata;
      init(phraseMetadata.getPhrase(), distance);
    } else {
      throw new IllegalArgumentException("Invalid expression type!");
    }
  }

  protected void init(String phrase, int distance) {
    terms = phrase.split(" ");
    if (terms.length < 2)
      throw new IllegalArgumentException("It's not a phrase!");
    this.distance = distance;
  }

  @Override
  public double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    DoubleRef dr = new DoubleRef();
    int[][] posAries = findPos(documentEntity, termDistance, dr);
    if (posAries == null)
      return -1;
    int[] posAry = new int[terms.length];
    List<HitToken> hits = getHits(hitMap, field);
    if (calcDistance(posAries, 0, posAry, documentEntity, termDistance, hits)) {
      if (context.isScoring())
        return dr.d;
      return 1;
    }
    return -1;
  }

  /**
   * 获得短语中每个词出现的所有位置
   *
   * @return 按短语中的词序组织的词出现位置的位置数组
   */
  protected int[][] findPos(DocumentEntity documentEntity, boolean termDistance,
      DoubleRef score) {
    int[][] posAries = new int[terms.length][];
    for (int i = 0; i < terms.length; i++) {
      List<TermEntity> termEntities = documentEntity.findTerm(field, terms[i]);
      if (termEntities == null || termEntities.size() == 0)
        return null;
      int[] posAry = new int[termEntities.size()];
      posAries[i] = posAry;
      int j = 0;
      for (TermEntity termEntity : termEntities) {
        if (termDistance)
          posAry[j] = termEntity.getIndex();
        else
          posAry[j] = termEntity.getOffset();
        j++;
      }
      if (context.isScoring()) {
        score.d += score(documentEntity, termEntities.get(0));
      }
    }
    return posAries;
  }

  protected boolean calcDistance(int[][] posAries, int level, int[] posAry,
      DocumentEntity documentEntity, boolean termDistance,
      List<HitToken> hits) {
    int hitSize = hits == null ? 0 : hits.size();
    for (int i = 0; i < posAries[level].length; i++) {
      posAry[level] = posAries[level][i];
      if (level == terms.length - 1) {
        if (termDistance) {
          if (calcTermDistance(posAry)) {
            if (hits == null)
              return true;
            else {
              fillHits(posAry, documentEntity, termDistance, hits);
            }
          }
        } else {
          if (calcCharDistance(posAry)) {
            if (hits == null)
              return true;
            else {
              fillHits(posAry, documentEntity, termDistance, hits);
            }
          }
        }
      } else {
        if (calcDistance(posAries, level + 1, posAry, documentEntity,
            termDistance, hits)) {
          if (hits == null)
            return true;
        }
      }
    }
    //
    if (hits != null && hits.size() != hitSize)
      return true;
    return false;
  }

  protected void fillHits(int[] posAry, DocumentEntity documentEntity,
      boolean termDistance, List<HitToken> hits) {
    int bt = 0; // begin term
    int et = 0; // end term
    int min = Integer.MAX_VALUE, max = 0;
    for (int i = 0; i < posAry.length; i++) {
      if (posAry[i] > max) {
        max = posAry[i];
        et = i;
      }
      if (posAry[i] < min) {
        min = posAry[i];
        bt = i;
      }
    }
    int begin = 0, end = 0;
    TermEntity[] entities = documentEntity.getTermEntities(field);
    if (termDistance) {
      begin = entities[min].getOffset();
      end = entities[max].getOffset() + entities[max].getTerm().length();
    } else {
      begin = min;
      end = max + terms[et].length();
    }
    HitToken hit = new HitToken(begin, end);
    hit.setToken(extractPhrase(begin, end, entities));
    hits.add(hit);
  }

  protected String extractPhrase(int begin, int end,
      TermEntity[] termEntities) {
    StringBuilder sbud = new StringBuilder();
    int pos = -1;
    for (int i = 0; i <= termEntities.length; i++) {
      if (termEntities[i].getOffset() < begin)
        continue;
      if (termEntities[i].getOffset() > end)
        break;
      if (termEntities[i].getOffset() < pos)
        continue;
      if (pos > 0 && termEntities[i].getOffset() - pos > 0) {
        stuff(sbud, termEntities[i].getOffset() - pos);
      }
      sbud.append(termEntities[i].getTerm());
      pos = termEntities[i].getOffset() + termEntities[i].getTerm().length();
    }
    return sbud.toString();
  }

  protected void stuff(StringBuilder sbud, int length) {
    for (int i = 0; i < length; i++) {
      sbud.append('.');
    }
  }

  protected boolean calcTermDistance(int[] findPos) {
    boolean[] bAry = new boolean[findPos.length];
    // init
    for (int i = 0; i < bAry.length; i++)
      bAry[i] = false;
    // calc
    for (int i = 0; i < findPos.length; i++) {
      int dist = Integer.MAX_VALUE;
      int inx = 0;
      for (int j = i + 1; j < findPos.length; j++) {
        int tmp1 = Math.abs(findPos[i] + terms[i].length() - findPos[j]);
        int tmp2 = Math.abs(findPos[i] - findPos[j] + terms[j].length());
        tmp1 = Math.min(tmp1, tmp2);
        dist = Math.min(dist, tmp1);
        if (tmp1 == dist)
          inx = j;
      }
      if (dist <= distance) {
        bAry[i] = true;
        bAry[inx] = true;
      }
    }
    for (int i = 0; i < bAry.length; i++)
      if (!bAry[i])
        return false;
    return true;
  }

  protected boolean calcCharDistance(int[] findPos) {
    boolean[] bAry = new boolean[findPos.length];
    // init
    for (int i = 0; i < bAry.length; i++)
      bAry[i] = false;
    // calc
    for (int i = 0; i < findPos.length; i++) {
      int dist = Integer.MAX_VALUE;
      int inx = 0;
      for (int j = i + 1; j < findPos.length; j++) {
        int tmp = Math.abs(findPos[i] - findPos[j]);
        dist = Math.min(dist, tmp);
        if (tmp == dist)
          inx = j;
      }
      if (dist <= distance) {
        bAry[i] = true;
        bAry[inx] = true;
      }
    }
    for (int i = 0; i < bAry.length; i++)
      if (!bAry[i])
        return false;
    return true;
  }

  protected class DoubleRef {
    double d = 0;
  }

}
