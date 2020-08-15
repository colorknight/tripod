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
