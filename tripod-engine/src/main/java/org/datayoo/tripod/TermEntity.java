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

import java.io.Serializable;

/**
 * 词实体接口
 */
public interface TermEntity extends Serializable {
  // 词在文档中的序号，以词作为单位进行临近距离匹配时，依赖此属性
  int getIndex();
  // 词文本
  String getTerm();
  // 词在文档中的起始字符偏移，以字符为单位进行临近距离匹配时，依赖此属性
  int getOffset();
  // 词性(可选，计算时并不需要)
  String getPartOfSpeech();
}
