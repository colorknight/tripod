package org.datayoo.tripod;

import org.apache.commons.lang3.Validate;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class IdfCounterImpl implements IdfCounter {

  protected int totalDocs;

  protected Map<String, Integer> termDocs = new TreeMap<String, Integer>();

  public IdfCounterImpl() {
  }

  public IdfCounterImpl(int totalDocs, Map<String, Integer> termDocs) {
    this.totalDocs = totalDocs;
    this.termDocs = termDocs;
  }

  public void count(Set<String> terms) {
    totalDocs++;
    for (String term : terms) {
      Integer i = termDocs.get(term);
      if (i == null) {
        termDocs.put(term, 1);
      } else {
        termDocs.put(term, i + 1);
      }
    }
  }

  public double idf(String term) {
    Integer num = termDocs.get(term);
    if (num == null)
      return 0;
    return Math.log(totalDocs / (double) (num + 1)) + 1.0;
  }

  public int getTotalDocs() {
    return totalDocs;
  }

  public Map<String, Integer> getTermDocs() {
    return termDocs;
  }

  public void load(String fileName) throws IOException, ClassNotFoundException {
    Validate.notEmpty(fileName, "fileName is empty!");
    ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(fileName));
    try {
      totalDocs = ois.readInt();
      termDocs = (Map<String, Integer>) ois.readObject();
    } finally {
      ois.close();
    }
  }

  public void save(String fileName) throws IOException {
    Validate.notEmpty(fileName, "fileName is empty!");
    IdfCounterImpl idfCounter = new IdfCounterImpl();

    ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(fileName));
    try {
      oos.writeInt(totalDocs);
      oos.writeObject(termDocs);
    } finally {
      oos.close();
    }

  }
}
