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

import java.util.Map;
import java.util.Set;

/**
 * 用于记录词与文档的数目，方便计算IDF
 */
public interface IdfCounter {
  /**
   * 对集合中的词进行记数
   * @param terms 词集合，一般认为一个set集合为一篇文档中出现的所有去重后的词
   */
  void count(Set<String> terms);
  /**
   * 获得指定词的idf值
   * @param term 词
   * @return idf分值
   */
  double idf(String term);
  /**
   * 获得全部的文档数
   * @return 全部文档数
   */
  int getTotalDocs();
  /**
   * 获得词语存在于多少个文档中的Map集合
   * @return 词语与文档数的Map集合
   */
  Map<String, Integer> getTermDocs();
}
