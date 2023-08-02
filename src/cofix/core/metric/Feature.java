
package cofix.core.metric;

import cofix.core.parser.node.Node;

/**
 * @author anonymous
 * 
 */
public abstract class Feature {

  protected Node _node = null;

  protected Feature(Node node) {
    _node = node;
  }

  public Node getNode() {
    return _node;
  }
}
