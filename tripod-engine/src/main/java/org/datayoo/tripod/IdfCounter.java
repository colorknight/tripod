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

  void count(Set<String> terms);

  double idf(String term);

  int getTotalDocs();

  Map<String, Integer> getTermDocs();

}
