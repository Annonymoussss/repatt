
package cofix.core.metric;

import cofix.core.parser.node.Node;

/**
 * @author anonymous
 * 
 */
public class Operator extends Feature {

  public enum KIND {
    INFIX,
    POSTFIX,
    PREFIX,
    ACC,
    INS
  }

  private KIND _kind = null;

  public Operator(Node node, KIND kind) {
    super(node);
    _kind = kind;
  }
}
