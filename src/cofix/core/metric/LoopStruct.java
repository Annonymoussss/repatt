
package cofix.core.metric;

import cofix.core.parser.node.Node;

/**
 * @author anonymous
 * 
 */
public class LoopStruct extends Feature {

  public enum KIND {
    FOR,
    EFOR,
    WHILE,
    DO
  }

  private KIND _kind = null;

  public LoopStruct(Node node, KIND kind) {
    super(node);
    _kind = kind;
  }
}
