package org.datayoo.tripod;

import java.io.Serializable;

public class HitToken implements Serializable {

  protected int begin;

  protected int end;

  protected String token;

  public HitToken(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
