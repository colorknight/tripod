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
package org.datayoo.tripod.engine;

import org.apache.commons.lang3.Validate;
import org.datayoo.tripod.FieldMetadata;
import org.datayoo.tripod.IdfCounter;
import org.datayoo.tripod.IdfCounterImpl;
import org.datayoo.tripod.TripodContext;

import java.util.List;
import java.util.Set;

public class TripodContextImpl implements TripodContext {

  protected List<FieldMetadata> allFields;

  protected FieldMetadata defaultFieldMetadata;

  protected boolean scoring = false;

  protected IdfCounter idfCounter;

  public TripodContextImpl(List<FieldMetadata> allFields,
      FieldMetadata defaultFieldMetadata, IdfCounter idfCounter) {
    Validate.notEmpty(allFields, "allFields is empty!");
    Validate.notNull(defaultFieldMetadata, "defaultFieldMetadata is null!");
    Validate.notNull(idfCounter, "idfCounter is null!");

    this.allFields = allFields;
    this.defaultFieldMetadata = defaultFieldMetadata;
    this.idfCounter = idfCounter;
  }

  @Override
  public FieldMetadata getDefaultField() {
    return defaultFieldMetadata;
  }

  @Override
  public List<FieldMetadata> getAllFields() {
    return allFields;
  }

  @Override
  public int getBoost(String field) {
    for (FieldMetadata fm : allFields) {
      if (fm.getField().equals(field))
        return fm.getBoost();
    }
    return 1;
  }

  @Override
  public boolean isScoring() {
    return scoring;
  }

  @Override
  public double getIdf(String term) {
    if (scoring)
      return idfCounter.idf(term);
    return 0;
  }

  public void setScoring(boolean scoring) {
    this.scoring = scoring;
  }

  public void calcIdf(Set<String> terms) {
    if (scoring)
      idfCounter.count(terms);
  }

  @Override
  public IdfCounter getIdfCounter() {
    return idfCounter;
  }
}
