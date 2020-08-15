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
import org.datayoo.tripod.*;
import org.datayoo.tripod.bool.BoolOperand;
import org.datayoo.tripod.factory.OperandFactory;
import org.datayoo.tripod.metadata.BoolMetadata;
import org.datayoo.tripod.parser.TripodExpressionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TripodEngine {

  protected static final Logger logger = LoggerFactory
      .getLogger(TripodEngine.class);

  protected TripodContextImpl tripodContext;

  protected Map<String, TripodRule> ruleMap = new HashMap<String, TripodRule>();

  protected ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

  public TripodEngine(List<FieldMetadata> allFields,
      FieldMetadata defaultFieldMetadata, IdfCounter idfCounter) {
    tripodContext = new TripodContextImpl(allFields, defaultFieldMetadata,
        idfCounter);
  }

  public TripodEngine setScoring(boolean scoring) {
    tripodContext.setScoring(scoring);
    return this;
  }

  public void addTripodRule(String name, String rule,
      TripodListener tripodListener) {
    Validate.notEmpty(name, "name is empty!");
    Validate.notEmpty(rule, "rule is empty!");
    Validate.notNull(tripodListener, "tripodListener is null!");
    rwLock.writeLock().lock();
    try {
      if (ruleMap.get(name) != null)
        throw new IllegalArgumentException(
            String.format("Name is conflicted!"));
      BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(rule);
      BoolOperand boolOperand = OperandFactory
          .createOperand(boolMetadata, tripodContext);
      TripodRule tripodRule = new TripodRule(name, rule);
      tripodRule.boolOperand = boolOperand;
      tripodRule.tripodListener = tripodListener;
      ruleMap.put(name, tripodRule);
    } finally {
      rwLock.writeLock().unlock();
    }
  }

  public String removeTripodRule(String name) {
    Validate.notEmpty(name, "name is empty!");
    rwLock.writeLock().lock();
    try {
      TripodRule tripodRule = ruleMap.remove(name);
      if (tripodRule != null)
        return name;
      return null;
    } finally {
      rwLock.writeLock().unlock();
    }
  }

  public void match(Map<String, TermEntity[]> dataMap) {
    match(dataMap, true, false);
  }

  public void match(Map<String, TermEntity[]> dataMap, boolean termDistance) {
    match(dataMap, termDistance, false);
  }

  public void match(Map<String, TermEntity[]> dataMap, boolean termDistance,
      boolean retHit) {
    Validate.notEmpty(dataMap, "dataMap is empty!");
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap,
        tripodContext.isScoring());
    tripodContext.getIdfCounter().count(documentEntity.getAllTerms());
    rwLock.readLock().lock();
    try {
      for (TripodRule tripodRule : ruleMap.values()) {
        Map<String, List<HitToken>> hitMap = null;
        if (retHit) {
          hitMap = new HashMap<>();
        }
        try {
          double score = tripodRule.boolOperand
              .operate(documentEntity, termDistance, hitMap);
          if (score > 0)
            tripodRule.tripodListener
                .onHit(tripodRule.name, dataMap, score, hitMap);
        } catch (Throwable t) {
          logger.info(String
              .format("tripod rule '%s' execute failed!", tripodRule.name));
        }
      }
    } finally {
      rwLock.readLock().unlock();
    }
  }

  protected static class TripodRule {

    protected String name;

    protected String rule;

    protected BoolOperand boolOperand;

    protected TripodListener tripodListener;

    public TripodRule(String name, String rule) {
      this.name = name;
      this.rule = rule;
    }
  }

}
