package org.datayoo.tripod.utils;

// levenshtein 编辑距离
public class LevDistance {

  public static int lev(byte[] a, byte[] b) {
    int[] lev = new int[b.length + 1];
    for (int i = 0; i < lev.length; i++) {
      lev[i] = i;
    }
    int t1, t2;
    for (int i = 1; i <= a.length; i++) {
      t1 = lev[0]++;
      for (int j = 1; j <= b.length; j++) {
        t2 = lev[j];
        if (a[i - 1] == b[j - 1])
          lev[j] = t1;
        else
          lev[j] = min(t1, min(lev[j - 1], lev[j])) + 1;
        t1 = t2;
      }
    }
    return lev[b.length];
  }

  protected static int min(int a, int b) {
    if (a <= b)
      return a;
    return b;
  }

  public static int lev(char[] a, char[] b) {
    int[] lev = new int[b.length + 1];
    for (int i = 0; i < lev.length; i++) {
      lev[i] = i;
    }
    int t1, t2;
    for (int i = 1; i <= a.length; i++) {
      t1 = lev[0]++;
      for (int j = 1; j <= b.length; j++) {
        t2 = lev[j];
        if (a[i - 1] == b[j - 1])
          lev[j] = t1;
        else
          lev[j] = min(t1, min(lev[j - 1], lev[j])) + 1;
        t1 = t2;
      }
    }
    return lev[b.length];
  }

}
