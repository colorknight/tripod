package org.datayoo.tripod;

import java.io.Serializable;

public interface TermEntity extends Serializable {

  int getIndex();

  String getTerm();

  int getOffset();

  String getPartOfSpeech();
}
