
package cofix.core.metric;

import cofix.core.parser.node.Node;

/**
 * @author anonymous
 * 
 */
public class OtherStruct extends Feature {

  public enum KIND {
    BREAK,
    CONTINUE,
    RETURN,
    THROW
  }

  private KIND _kind = null;

  public OtherStruct(Node node, KIND kind) {
    super(node);
    _kind = kind;
  }

  public KIND getKind() {
    return _kind;
  }
}
