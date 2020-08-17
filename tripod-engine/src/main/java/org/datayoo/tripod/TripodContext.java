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

import java.util.List;
import java.util.Set;

public interface TripodContext {

  /**
   * 获得lucene语法中缺省字段的字段信息
   *
   * @return 字段信息
   */
  FieldMetadata getDefaultField();

  /**
   * 获得lucene语法中，可能出现的全部字段的字段信息
   *
   * @return 字段信息列表
   */
  List<FieldMetadata> getAllFields();

  /**
   * 获得指定字段的权重值
   *
   * @param field 字段名
   * @return 字段权重
   */
  int getBoost(String field);

  /**
   * tripod在进行匹配时，是否计算相关度值
   *
   * @return true表示计算相关度，false表示不计算相关度
   */
  boolean isScoring();

  /**
   * 获得指定词的idf值
   *
   * @param term 词
   * @return idf分值
   */
  double getIdf(String term);

  /**
   * 计算词组的idf值
   *
   * @param terms 词集合，一般认为一个set集合为一篇文档中出现的所有去重后的词
   */
  void calcIdf(Set<String> terms);

  IdfCounter getIdfCounter();

}
