
package cofix.core.metric;

import cofix.core.parser.node.Node;

/**
 * @author anonymous
 * 
 */
public class CondStruct extends Feature {

  public enum KIND {
    IF, // if-else
    SC, // switch-case
    CE // conditional-expression
  }

  private KIND _kind = null;

  public CondStruct(Node node, KIND kind) {
    super(node);
    _kind = kind;
  }

  public KIND getKind() {
    return _kind;
  }
}
