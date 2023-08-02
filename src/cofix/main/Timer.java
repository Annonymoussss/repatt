
package cofix.main;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author anonymous
 * 
 */
public class Timer {
  private long _start = 0;
  private long _timeout = 0;

  public Timer(int hour, int min) {
    _timeout += TimeUnit.HOURS.toMillis(hour);
    _timeout += TimeUnit.MINUTES.toMillis(min);
    System.out.println("TIMEOUT : " + _timeout);
  }

  public String start() {
    _start = System.currentTimeMillis();
    return new Date(_start).toString();
  }

  public boolean timeout() {
    return (System.currentTimeMillis() - _start) > _timeout;
  }
}
