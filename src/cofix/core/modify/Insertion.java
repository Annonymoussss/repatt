
package cofix.core.modify;

import cofix.core.parser.node.Node;
import cofix.core.parser.node.Node.TYPE;

/**
 * @author anonymous
 * 
 */
public class Insertion extends Modification {

  public Insertion(Node node, int srcID, String target, TYPE changeNodeType) {
    super(node, srcID, target, changeNodeType, 1);
    //  OLDO Auto-generated constructor stub
  }

  @Override
  public String toString() {
    return "[INS | " + _nodeType + " | " + _sourceID + "]" + _target;
  }
}
