package org.datayoo.tripod;

import java.util.Map;
import java.util.Set;

/**
 * 用于记录词与文档的数目，方便计算IDF
 */
public interface IdfCounter {

  void count(Set<String> terms);

  double idf(String term);

  int getTotalDocs();

  Map<String, Integer> getTermDocs();

}
