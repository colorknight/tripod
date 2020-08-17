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

public class HitToken implements Serializable {
  // 命中标记的起始位置
  protected int begin;
  // 命中标记的终止位置
  protected int end;
  // 标记文本
  protected String token;

  public HitToken(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  /**
   * 获得标记在文本中的起始位置
   *
   * @return 返回起始位置信息
   */
  public int getBegin() {
    return begin;
  }

  /**
   * 获得标记在文本中的终止位置
   *
   * @return 返回终止位置信息
   */
  public int getEnd() {
    return end;
  }

  /**
   * 获得标记文本
   *
   * @return 标记文本
   */
  public String getToken() {
    return token;
  }

  /**
   * 设置标记文本
   *
   * @param token 标记文本
   */
  public void setToken(String token) {
    this.token = token;
  }
}
