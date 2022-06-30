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
import org.datayoo.tripod.seg.TripodSegment;
import org.datayoo.tripod.seg.ansj.AnsjSegmenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TripodTest extends TestCase {

  public void test1() {
    Tripod tripod = createTripod();
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    System.out.println(tripod.match(dataMap, true));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void test2() {
    Tripod tripod = createTripod2();
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
    System.out.println(tripod.match(dataMap, true));
    tripod = createTripod3();
    System.out.println(tripod.match(dataMap, true));
    tripod = createTripod4();
    System.out.println(tripod.match(dataMap, true));
  }

  protected static Tripod createTripod() {
    List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
    FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
    fieldMetadatas.add(fieldMetadata);
    fieldMetadata = new FieldMetadata("content", 1);
    fieldMetadatas.add(fieldMetadata);
    Tripod tripod = new Tripod("test1", fieldMetadatas, fieldMetadata,
        new IdfCounterImpl(), "\"第5代 领导\" 任命 形式主义");
    return tripod;
  }

  protected static Tripod createTripod2() {
    List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
    FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
    fieldMetadatas.add(fieldMetadata);
    fieldMetadata = new FieldMetadata("content", 1);
    fieldMetadatas.add(fieldMetadata);
    Tripod tripod = new Tripod("test1", fieldMetadatas, fieldMetadata,
        new IdfCounterImpl(), "\"第5代 领导\" +[任命 形式主义]");
    return tripod;
  }

  protected static Tripod createTripod3() {
    List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
    FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
    fieldMetadatas.add(fieldMetadata);
    fieldMetadata = new FieldMetadata("content", 1);
    fieldMetadatas.add(fieldMetadata);
    Tripod tripod = new Tripod("test1", fieldMetadatas, fieldMetadata,
        new IdfCounterImpl(), "\"第5代 领导\" +[任命 形式主义] +[中办 形而上学]");
    return tripod;
  }

  protected static Tripod createTripod4() {
    List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
    FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
    fieldMetadatas.add(fieldMetadata);
    fieldMetadata = new FieldMetadata("content", 1);
    fieldMetadatas.add(fieldMetadata);
    Tripod tripod = new Tripod("test1", fieldMetadatas, fieldMetadata,
        new IdfCounterImpl(), "\"第5代 领导\" +[任命 形式主义] +[中办 形而上学] +[测试 测试2]");
    return tripod;
  }

}
