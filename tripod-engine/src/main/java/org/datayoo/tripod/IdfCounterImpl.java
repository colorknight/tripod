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
