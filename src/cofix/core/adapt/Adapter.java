
package cofix.core.adapt;

import cofix.core.modify.Modification;

/**
 * @author anonymous
 * 
 */
public interface Adapter {
  boolean adapt(Modification modification);

  boolean restore(Modification modification);

  boolean backup(Modification modification);
}
