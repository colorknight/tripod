package org.datayoo.tripod;

import java.util.*;

public class DocumentEntityImpl implements DocumentEntity {

  protected Map<String, TermEntity[]> entityMap;

  protected Map<String, Map<String, List<TermEntity>>> entityTermMap = new HashMap<String, Map<String, List<TermEntity>>>();

  protected Map<String, Double> tfs = new TreeMap<String, Double>();

  public DocumentEntityImpl(Map<String, TermEntity[]> entityMap,
      boolean scoring) {
    this.entityMap = entityMap;
    initEntityTermMap();
    if (scoring)
      caculateTfs();
  }

  protected void caculateTfs() {
    fillTfs();
    for (Map.Entry<String, Double> entry : tfs.entrySet()) {
      entry.setValue(Math.sqrt(entry.getValue()));
    }
  }

  protected void initEntityTermMap() {
    for (Map.Entry<String, TermEntity[]> entry : entityMap.entrySet()) {
      Map<String, List<TermEntity>> termMap = entityTermMap.get(entry.getKey());
      if (termMap == null) {
        termMap = new TreeMap<>();
        entityTermMap.put(entry.getKey(), termMap);
      }
      initTermMap(termMap, entry.getValue());
    }
  }

  protected void initTermMap(Map<String, List<TermEntity>> termMap,
      TermEntity[] termEntities) {
    for (int i = 0; i < termEntities.length; i++) {
      List<TermEntity> entities = termMap.get(termEntities[i].getTerm());
      if (entities == null) {
        entities = new LinkedList<TermEntity>();
        termMap.put(termEntities[i].getTerm(), entities);
      }
      entities.add(termEntities[i]);
    }
  }

  protected void fillTfs() {
    for (TermEntity[] termEntities : entityMap.values()) {
      for (int i = 0; i < termEntities.length; i++) {
        String term = termEntities[i].getTerm();
        Double d = tfs.get(term);
        if (d == null) {
          tfs.put(term, 1.0);
        } else {
          tfs.put(term, d + 1.0);
        }
      }
    }
  }

  @Override
  public List<TermEntity> findTerm(String field, String term) {
    Map<String, List<TermEntity>> termMap = entityTermMap.get(field);
    if (termMap == null)
      return null;
    return termMap.get(term);
  }

  @Override
  public double getTermFrequency(String term) {
    if (tfs.size() == 0)
      return 0;
    Double d = tfs.get(term);
    if (d == null)
      return 0;
    return d;
  }

  @Override
  public int getTermsCount() {
    return tfs.size();
  }

  public TermEntity[] getTermEntities(String field) {
    return entityMap.get(field);
  }

  @Override
  public Set<String> getTerms(String field) {
    Map<String, List<TermEntity>> termMap = entityTermMap.get(field);
    if (termMap != null)
      return termMap.keySet();
    return null;
  }

  public Set<String> getAllTerms() {
    return tfs.keySet();
  }

}
