package org.datayoo.tripod.engine;

import org.datayoo.tripod.HitToken;
import org.datayoo.tripod.TermEntity;

import java.util.List;
import java.util.Map;

public interface TripodListener {

  void onHit(String ruleName, Map<String, TermEntity[]> dataMap, double score,
      Map<String, List<HitToken>> hitMap);
}
