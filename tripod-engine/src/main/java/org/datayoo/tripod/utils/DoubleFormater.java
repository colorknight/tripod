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
package org.datayoo.tripod.utils;

public abstract class DoubleFormater {

  public static final double format(double value, int precision) {
    if (precision < 1) {
      return (long) value;
    }
    double divisor = 1.0;
    for (int i = 0; i < precision; i++) {
      value *= 10;
      divisor *= 10;
    }
    return ((long) value) / divisor;
  }
}
