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

import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;

import java.util.List;
import java.util.Map;

/**
 * TripodEngine的匹配结果监听器
 */
public interface TripodListener {
  /**
   * 当输入的文档数据被命中时，TripodEngine调用此接口返回结果
   * @param ruleName 命中文档数据的规则的名字
   * @param dataMap 文档数据
   * @param score 当前规则命中文档的相关度值
   * @param hitMap 当前规则命中的当前文档的所有标记，标记可能是词，短语或一段文本的起止
   *               位置。由于文档数据已被做过分词，文字空间上不再连续，当规则中带有距离
   *               计算时，无法复原文本串，故只给出其起止位置。
   */
  void onHit(String ruleName, Map<String, TermEntity[]> dataMap, double score,
      Map<String, List<HitToken>> hitMap);
}
