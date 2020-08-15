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
