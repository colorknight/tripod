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
import java.util.List;
import java.util.Set;

public interface DocumentEntity extends Serializable {

  List<TermEntity> findTerm(String field, String term);

  TermEntity[] getTermEntities(String field);

  Set<String> getTerms(String field);

  Set<String> getAllTerms();

  double getTermFrequency(String term);

  int getTermsCount();
}
