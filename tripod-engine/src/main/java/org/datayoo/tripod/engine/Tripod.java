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
 * 每个由lucene语法写成的查询语句被视为一个匹配规则，每个查询规则的执行态对象为一个Tripod。
 */
public class Tripod {

  protected static final Logger logger = LoggerFactory.getLogger(Tripod.class);

  protected String name;

  protected String rule;

  protected TripodContext context;
  // 若context为私有，则tripod自己维护context中的词计数信息
  protected boolean privateContext = false;

  protected BoolOperand boolOperand;

  /**
   * 初始化
   * @param name tripod的名字
   * @param allFields 在lucene语法中可能出现的所有field
   * @param defaultFieldMetadata 在lucene语法中不指定field时，缺省的字段名
   * @param idfCounter 用于记录文档与词关系的记数对象，用于计算IDF
   * @param rule lucene语法写成的匹配规则
   */
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

  /**
   * 初始化
   * @param name tripod的名字
   * @param context tripod上下文对象
   * @param rule lucene语法写成的匹配规则
   */
  public Tripod(String name, TripodContext context, String rule) {
    Validate.notEmpty(name, "name is empty!");
    this.name = name;
    this.rule = rule;
    BoolMetadata boolMetadata = TripodExpressionParser.parseFromString(rule);
    boolOperand = OperandFactory.createOperand(boolMetadata, context);
    this.context = context;
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   * @param dataMap 待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
   *                进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
   *                转换为DocumentEntity对象。
   * @return 匹配的分值，若该值为负表示没有匹配
   */
  public double match(Map<String, TermEntity[]> dataMap) {
    return match(dataMap, true, null);
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   * @param dataMap 待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
   *                进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
   *                转换为DocumentEntity对象。
   * @param termDistance 是否以词距离进行匹配，缺省为true；若该值为false，表示以字
   *                     符距离进行距离计算。
   * @return 匹配的分值，若该值为负表示没有匹配
   */
  public double match(Map<String, TermEntity[]> dataMap, boolean termDistance) {
    return match(dataMap, termDistance, null);
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   * @param dataMap 待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
   *                进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
   *                转换为DocumentEntity对象。
   * @param termDistance 是否以词距离进行匹配，缺省为true；若该值为false，表示以字
   *                     符距离进行距离计算。
   * @param hitMap 该对象是一个返出结果对象，它会被填充所有由规则命中的词、短语或一段
   *               文本的起止位置。由于文档数据已被做过分词，文字空间上不再连续，当规
   *               则中带有距离计算时，无法复原文本串，故只给出其起止位置。key值与
   *               dataMap的key值含义相同。
   * @return 匹配的分值，若该值为负表示没有匹配
   */
  public double match(Map<String, TermEntity[]> dataMap, boolean termDistance,
      Map<String, List<HitToken>> hitMap) {
    Validate.notEmpty(dataMap, "dataMap is empty!");
    DocumentEntity documentEntity = new DocumentEntityImpl(dataMap,
        context.isScoring());
    return match(documentEntity, termDistance, hitMap);
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   * @param documentEntity 文档实体对象
   * @param termDistance 是否以词距离进行匹配，缺省为true；若该值为false，表示以字
   *                     符距离进行距离计算。
   * @param hitMap 该对象是一个返出结果对象，它会被填充所有由规则命中的词、短语或一段
   *               文本的起止位置。由于文档数据已被做过分词，文字空间上不再连续，当规
   *               则中带有距离计算时，无法复原文本串，故只给出其起止位置。key值与
   *               dataMap的key值含义相同。
   * @return 匹配的分值，若该值为负表示没有匹配
   */
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
