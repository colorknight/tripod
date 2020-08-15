package org.datayoo.tripod;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface DocumentEntity extends Serializable {

  List<TermEntity> findTerm(String field, String term);

  TermEntity[] getTermEntities(String field);

  Set<String> getTerms(String field);

  Set<String> getAllTerms();

  double getTermFrequency(String term);

  int getTermsCount();
}
