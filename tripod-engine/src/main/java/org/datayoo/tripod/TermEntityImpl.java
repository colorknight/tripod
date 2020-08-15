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
}
