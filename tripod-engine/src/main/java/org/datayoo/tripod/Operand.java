/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.datayoo.tripod;

import org.datayoo.tripod.metadata.ExpressionMetadata;

import java.util.List;
import java.util.Map;

public interface Operand {

  ExpressionMetadata getMetadata();

  double operate(DocumentEntity documentEntity);

  double operate(DocumentEntity documentEntity, boolean termDistance);

  /**
   * @param documentEntity
   * @param termDistance   是否采用词距离，如不采用词距离则采用字符距离
   * @param hitMap         算子命中的文本的列表，若为null表示不返回命中列表
   * @return
   */
  double operate(DocumentEntity documentEntity, boolean termDistance,
      Map<String, List<HitToken>> hitMap);

}
