package org.datayoo.tripod.operand;

import org.datayoo.tripod.FieldMetadata;
import org.datayoo.tripod.IdfCounterImpl;
import org.datayoo.tripod.TermEntity;
import org.datayoo.tripod.TermEntityImpl;
import org.datayoo.tripod.engine.TripodContextImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class TripodTestHelper {

  public static Map<String, TermEntity[]> createDataMap() {
    Map<String, TermEntity[]> dataMap = new HashMap<String, TermEntity[]>();
    dataMap.put("title", getTitleTerms());
    dataMap.put("content", getContentTerms());
    return dataMap;
  }

  // 中办发任命通告
  public static TermEntity[] getTitleTerms() {
    TermEntity[] termEntities = new TermEntity[2];
    termEntities[0] = new TermEntityImpl(0, "中办", 0);
    termEntities[1] = new TermEntityImpl(1, "中办发", 0);
    return termEntities;
  }

  // 这是中办发发布的关于第5代领导人的任命通知。杜绝形式主义，形而上学的言论。测试American Academy of Political and Social Science
  public static TermEntity[] getContentTerms() {
    TermEntity[] termEntities = new TermEntity[8];
    termEntities[0] = new TermEntityImpl(0, "中办", 0);
    termEntities[1] = new TermEntityImpl(1, "中办发", 0);
    termEntities[2] = new TermEntityImpl(2, "第5代", 10);
    termEntities[3] = new TermEntityImpl(3, "领导", 13);
    termEntities[4] = new TermEntityImpl(4, "第5代领导", 10);
    termEntities[5] = new TermEntityImpl(5, "形式主义", 24);
    termEntities[6] = new TermEntityImpl(6, "形而上学", 29);
    termEntities[7] = new TermEntityImpl(7, "American Academy of Political and Social Science", 39);
    return termEntities;
  }

  public static TripodContextImpl createTripodContext(boolean scoring) {
    List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
    FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
    fieldMetadatas.add(fieldMetadata);
    fieldMetadata = new FieldMetadata("content", 1);
    fieldMetadatas.add(fieldMetadata);
    TripodContextImpl tripodContext = new TripodContextImpl(fieldMetadatas,
        fieldMetadata, new IdfCounterImpl());
    tripodContext.setScoring(scoring);
    return tripodContext;
  }
}
