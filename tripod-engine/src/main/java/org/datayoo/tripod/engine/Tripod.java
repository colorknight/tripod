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

import java.util.List;
import java.util.Map;

/**
 *
 */
public class Tripod {

  protected static final Logger logger = LoggerFactory.getLogger(Tripod.class);

  protected String name;

  protected String rule;

  protected TripodContext context;
  // 若context为私有，则tripod自己维护context中的词计数信息
  protected boolean privateContext = false;

  protected BoolOperand boolOperand;

  public Tripod(String name, List<FieldMetadata> allFields,
      FieldMetadata defaultFieldMetadata, IdfCounter idfCounter, String rule) {
    Validate.notEmpty(name, "name is empty!");
    this.name = name;
    this.rule = rule;
    context = new TripodContextImpl(allFields, defaultFieldMetadata,
        idfCounter);
    ((TripodContextImpl) context).setScoring(true);
    privateContext = true;
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(rule);
    boolOperand = OperandFactory.createOperand(boolMetadata, context);

  }

  public Tripod(String name, TripodContext context, String rule) {
    Validate.notEmpty(name, "name is empty!");
    this.name = name;
    this.rule = rule;
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(rule);
    boolOperand = OperandFactory.createOperand(boolMetadata, context);
    this.context = context;
  }

  public double match(Map<String, TermEntity[]> dataMap) {
    return match(dataMap, true, null);
  }

  public double match(Map<String, TermEntity[]> dataMap, boolean termDistance) {
    return match(dataMap, termDistance, null);
  }

  public double match(Map<String, TermEntity[]> dataMap, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    Validate.notEmpty(dataMap, "dataMap is empty!");
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap,
        context.isScoring());
    return match(documentEntity, termDistance, hitMap);
  }

  public double match(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    if (privateContext)
      context.calcIdf(documentEntity.getAllTerms());
    try {
      return boolOperand.operate(documentEntity, termDistance, hitMap);
    } catch (Throwable t) {
      logger.info(String.format("yooler '%s' execute failed!", name));
    }
    return 0;
  }

  public String getName() {
    return name;
  }

  public String getRule() {
    return rule;
  }

  public TripodContext getContext() {
    return context;
  }
}
