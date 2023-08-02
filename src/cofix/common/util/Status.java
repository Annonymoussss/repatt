
package cofix.common.util;

/**
 * @author anonymous
 * 
 */
public enum Status {
  SUCCESS("Successfully repair!"),
  FAILED("Failed to repair!"),
  TIMEOUT("Timeout!");

  private String _msg = null;

  Status(String message) {
    _msg = message;
  }

  @Override
  public String toString() {
    return _msg;
  }
}
