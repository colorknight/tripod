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
package org.datayoo.tripod;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Objects;

public class TermEntityImpl implements TermEntity {

  protected int index;

  protected String term;

  protected String partOfSpeech;

  protected int offset;

  public TermEntityImpl(String term, int offset) {
    this(0, term, offset);
  }

  public TermEntityImpl(int index, String term, int offset) {
    Validate.isTrue(index > -1, "index is less than 0!");
    Validate.notEmpty(term, "term is empty!");
    Validate.isTrue(offset > -1, "index is less than 0!");

    this.index = index;
    this.term = term;
    this.offset = offset;
  }

  public int getIndex() {
    return index;
  }

  public String getTerm() {
    return term;
  }

  public int getOffset() {
    return offset;
  }

  public String getPartOfSpeech() {
    return partOfSpeech;
  }

  public void setPartOfSpeech(String partOfSpeech) {
    this.partOfSpeech = partOfSpeech;
  }

  @Override
  public int hashCode() {
    return term.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TermEntity))
      return false;
    TermEntityImpl te = (TermEntityImpl) obj;
    if (te.index != index)
      return false;
    if (te.offset != offset)
      return false;
    if (!Objects.equals(te.partOfSpeech, partOfSpeech))
      return false;
    return te.term.equals(term);
  }

  @Override
  public String toString() {
    return "TermEntity{" + "index=" + index + ", term='" + term + '\''
        + ", partOfSpeech='" + partOfSpeech + '\'' + ", offset=" + offset + '}';
  }
}
