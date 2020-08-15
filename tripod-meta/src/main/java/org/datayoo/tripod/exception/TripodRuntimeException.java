package org.datayoo.tripod.exception;

public class TripodRuntimeException extends RuntimeException {
  public TripodRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public TripodRuntimeException(String message) {
    super(message);
  }

  public TripodRuntimeException(Throwable cause) {
    super(cause);
  }
}
