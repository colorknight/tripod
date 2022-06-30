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

import org.ansj.domain.Term;
import org.apache.commons.lang3.Validate;
import org.datayoo.tripod.*;
import org.datayoo.tripod.bool.BoolOperand;
import org.datayoo.tripod.factory.OperandFactory;
import org.datayoo.tripod.metadata.BoolMetadata;
import org.datayoo.tripod.parser.TripodExpressionParser;
import org.datayoo.tripod.seg.TripodSegment;

import java.util.*;

public class SegmentableTripodEngine {

  protected TripodEngine tripodEngine;

  protected TripodSegment tripodSegment;

  protected Set<String> segmentFields = new HashSet<>();

  /**
   * TripodEngine构造器
   *
   * @param allFields            待处理的文档的所有字段的相关描述
   * @param defaultFieldMetadata 缺省字段，即当表达式不指定title时，表明是此字段
   * @param idfCounter           用于计算Idf的工具接口
   * @param tripodSegment        分词接口
   */
  public SegmentableTripodEngine(List<FieldMetadata> allFields,
      FieldMetadata defaultFieldMetadata, IdfCounter idfCounter,
      TripodSegment tripodSegment) {
    tripodEngine = new TripodEngine(allFields, defaultFieldMetadata,
        idfCounter);
    Validate.notNull(tripodSegment, "tripodSegment is null!");
    this.tripodSegment = tripodSegment;
    segmentFields.add(defaultFieldMetadata.getField());
  }

  /**
   * 设置匹配时，是否计算相关度
   *
   * @param scoring true表示计算相关度,false表示不计算相关度
   * @return TripodEngine
   */
  public SegmentableTripodEngine setScoring(boolean scoring) {
    tripodEngine.setScoring(scoring);
    return this;
  }

  /**
   * 添加一个以lucene语法规则描述的匹配规则
   *
   * @param name           规则名
   * @param rule           由lucene语法描述的匹配规则
   * @param tripodListener 命中结果监听器，当匹配命中时，调用词监听器返回结果数据
   */
  public void addTripodRule(String name, String rule,
      TripodListener tripodListener) {
    tripodEngine.addTripodRule(name, rule, tripodListener);
    synchronized (segmentFields) {
      segmentFields.addAll(TripodExpressionParser.extractSegmentFields(rule));
    }
  }

  /**
   * 删除以lucene语法规则描述的匹配规则
   *
   * @param name 规则的名字
   * @return 由lucene语法描述的匹配规则
   */
  public String removeTripodRule(String name) {
    return tripodEngine.removeTripodRule(name);
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   *
   * @param dataMap 待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
   *                进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
   *                转换为DocumentEntity对象。
   */
  public void match(Map<String, Object> dataMap) {
    Map<String, TermEntity[]> segmentedMap = segmentFields(dataMap);
    tripodEngine.match(segmentedMap, true, false);
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   *
   * @param dataMap      待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
   *                     进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
   *                     转换为DocumentEntity对象。
   * @param termDistance 是否以词距离进行匹配，缺省为true；若该值为false，表示以字
   *                     符距离进行距离计算。
   */
  public void match(Map<String, Object> dataMap, boolean termDistance) {
    Map<String, TermEntity[]> segmentedMap = segmentFields(dataMap);
    tripodEngine.match(segmentedMap, termDistance, false);
  }

  /**
   * 对map格式描述的文档数据进行匹配。
   *
   * @param dataMap      待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
   *                     进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
   *                     转换为DocumentEntity对象。
   * @param termDistance 是否以词距离进行匹配，缺省为true；若该值为false，表示以字
   *                     符距离进行距离计算。
   * @param retHit       是否返回规则命中的标记信息，该值缺省为false；若该值为true时，表示
   *                     返回命中的标记，标记可以是词，短语或一段文本的起止位置。
   */
  public void match(Map<String, Object> dataMap, boolean termDistance,
      boolean retHit) {
    Map<String, TermEntity[]> segmentedMap = segmentFields(dataMap);
    tripodEngine.match(segmentedMap, true, retHit);
  }

  protected Map<String, TermEntity[]> segmentFields(
      Map<String, Object> dataMap) {
    Map<String, TermEntity[]> segmentedMap = new HashMap<>();
    synchronized (segmentFields) {
      for (String field : segmentFields) {
        Object v = dataMap.get(field);
        if (v == null)
          continue;
        if (v instanceof TermEntity[])
          segmentedMap.put(field, (TermEntity[]) v);
        else
          segmentedMap.put(field, tripodSegment.segment(v.toString()));
      }
    }
    return segmentedMap;
  }

}
