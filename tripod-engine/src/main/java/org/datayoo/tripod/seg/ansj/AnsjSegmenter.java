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
package org.datayoo.tripod.seg.ansj;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.TermEntityImpl;
import org.datayoo.tripod.seg.TripodSegment;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;

import java.util.LinkedList;
import java.util.List;

public class AnsjSegmenter implements TripodSegment {

  protected AnsjSegmentMode segmentMode = AnsjSegmentMode.ACCURATE;

  protected Forest[] forests;

  public AnsjSegmenter() {
  }

  public AnsjSegmenter(AnsjSegmentMode segmentMode) {
    if (segmentMode == null)
      return;
    this.segmentMode = segmentMode;
  }

  public AnsjSegmenter(AnsjSegmentMode segmentMode, String[] libPaths)
      throws Exception {
    this(segmentMode);
    if (libPaths != null) {
      forests = new Forest[libPaths.length];
      for (int i = 0; i < libPaths.length; i++) {
        forests[i] = Library.makeForest(libPaths[i]);
      }
    }
  }

  @Override
  public TermEntity[] segment(String text) {
    if (text == null || text.isEmpty())
      return new TermEntity[0];
    Result result;
    if (segmentMode == AnsjSegmentMode.ACCURATE) {
      result = ToAnalysis.parse(text, forests);
    } else if (segmentMode == AnsjSegmentMode.BASE) {
      result = BaseAnalysis.parse(text);
    } else if (segmentMode == AnsjSegmentMode.NLP) {
      result = NlpAnalysis.parse(text, forests);
    } else {
      result = IndexAnalysis.parse(text, forests);
    }
    return toTermEntities(result);
  }

  protected TermEntity[] toTermEntities(Result result) {
    TermEntity[] termEntities = new TermEntity[result.size()];
    int i = 0;
    for (Term term : result) {
      TermEntityImpl te = new TermEntityImpl(term.getName(), term.getOffe());
      te.setPartOfSpeech(term.getNatureStr());
      termEntities[i++] = te;
    }
    return termEntities;
  }

}
