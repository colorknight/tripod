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
package org.datayoo.yooler.engine;

import junit.framework.TestCase;
import org.datayoo.tripod.engine.TripodEvaluator;

public class TripodEvaluatorTest extends TestCase {

  public void testEvaluator() {
    TripodEvaluator evaluator = new TripodEvaluator();
    System.out.println(evaluator.complexity("(中办&title:中办)^2 任命 形式主义"));
    System.out.println(evaluator.complexity("(中办&title:中办)^2 任命"));
    System.out.println(evaluator.complexity("w1 +w2 !w3 \"w4 w5\" w6~0.8"));
  }

  public void testEvaluator2() {
    TripodEvaluator evaluator = new TripodEvaluator();
    System.out.println(evaluator.complexity("游行 静坐 示威 抗议 罢工 罢市 骚乱 拉横幅 上访 请愿 非法集会 聚众 暴乱 聚集维权 灭门 行凶 杀人 强奸 轮奸 抢劫 劫持 绑架 走私 叛国 贩毒 贩卖毒品 投毒 放火 纵火 私藏枪支 卖淫 空袭 恐怖活动 恐袭 重大案件 刑事案件 挤兑 非法集资 邪教 自杀式袭击 自杀袭击 -旅游"));
    Double d = 0.0;
  }
}
