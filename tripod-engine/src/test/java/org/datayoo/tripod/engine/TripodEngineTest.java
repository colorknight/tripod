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
    TripodEngine tripodEngine = createTripodEngine();
    Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
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
    TripodEngine tripodEngine = new TripodEngine(fieldMetadatas, fieldMetadata,
        new IdfCounterImpl());
    tripodEngine.setScoring(true);
    TripodListener tripodListener = new TripodPrintListener();
    //    yoolerEngine
    //        .addYoolerRule("test", "(中办&title:中办)^2 任命 形式主义", yoolerListener);
    tripodEngine.addTripodRule("test1", "\"第5代 领导\" 任命 形式主义", tripodListener);
    return tripodEngine;
  }

}
