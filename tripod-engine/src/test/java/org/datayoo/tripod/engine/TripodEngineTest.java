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

import junit.framework.TestCase;
import org.datayoo.tripod.FieldMetadata;
import org.datayoo.tripod.IdfCounterImpl;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.operand.TripodTestHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TripodEngineTest extends TestCase {

  public void test1() {
    // 创建TripodEngine
    TripodEngine tripodEngine = createTripodEngine();
    // 构造测试数据文档
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    // 匹配文档
    tripodEngine.match(dataMap, true);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void test2() {
    TripodEngine tripodEngine = createTripodEngine();
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    tripodEngine.match(dataMap, true, true);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected static TripodEngine createTripodEngine() {
    List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
    FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
    fieldMetadatas.add(fieldMetadata);
    fieldMetadata = new FieldMetadata("content", 1);
    fieldMetadatas.add(fieldMetadata);
    /*
    * 初始化TripodEngine，传入待处理的文档对象的字段信息，缺省字段及Idf计算辅助接口
    * */
    TripodEngine tripodEngine = new TripodEngine(fieldMetadatas, fieldMetadata,
        new IdfCounterImpl());
    // 设置引擎在匹配时计算相关度
    tripodEngine.setScoring(true);
    // 文档匹配监听器，当规则匹配文档后，通过该接口回调传回匹配结果
    TripodListener tripodListener = new TripodPrintListener();
    //    yoolerEngine
    //        .addYoolerRule("test", "(中办&title:中办)^2 任命 形式主义", yoolerListener);
    // 向引擎添加匹配规则
    tripodEngine.addTripodRule("test1", "\"第5代 领导\" 任命 形式主义", tripodListener);
    return tripodEngine;
  }

}
