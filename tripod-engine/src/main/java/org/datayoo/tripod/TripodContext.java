package org.datayoo.tripod;

import java.util.List;
import java.util.Set;

public interface TripodContext {

  FieldMetadata getDefaultField();

  List<FieldMetadata> getAllFields();

  int getBoost(String field);

  boolean isScoring();

  double getIdf(String term);

  void calcIdf(Set<String> terms);

  IdfCounter getIdfCounter();

}
