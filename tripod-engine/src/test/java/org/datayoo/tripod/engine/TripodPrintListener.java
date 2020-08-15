package org.datayoo.tripod.engine;

import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;

import java.util.List;
import java.util.Map;

public class TripodPrintListener implements TripodListener {

  @Override
  public void onHit(String ruleName, Map<String, TermEntity[]> dataMap,
      double score, Map<String, List<HitToken>> hitMap) {
    System.out.println(String.format("%s : %f", ruleName, score));
    if (hitMap != null) {
      System.out.println("---------------------");
      for (Map.Entry<String, List<HitToken>> entry : hitMap.entrySet()) {
        System.out.println(String.format("----field:%s", entry.getKey()));
        for (HitToken hitToken : entry.getValue()) {
          System.out.println(String
              .format("begin:%d;end:%d;%s", hitToken.getBegin(),
                  hitToken.getEnd(), hitToken.getToken()));
        }
      }
      System.out.println("---------------------");
    }
  }
}
