
package cofix.common.junit.runner;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author anonymous
 * 
 */
public class OutStream extends OutputStream {

  private final Set<Integer> _out = new HashSet<>();

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    String message = new String(b, off, len, StandardCharsets.UTF_8).trim();
    Integer id = null;
    if (message.length() > 0) {
      try {
        id = Integer.parseInt(message);
      } catch (Exception e) {
        return;
      }
      _out.add(id);
    }
  }

  @Override
  public void write(int b) throws IOException {}

  /** @return the _out */
  public Set<Integer> getOut() {
    return _out;
  }
}
