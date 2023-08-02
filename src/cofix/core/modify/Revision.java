
package cofix.core.modify;

import cofix.core.parser.node.Node;
import cofix.core.parser.node.Node.TYPE;

/**
 * @author anonymous
 * 
 */
public class Revision extends Modification {

  public Revision(Node node, int srcID, String target, TYPE changeNodeType) {
    super(node, srcID, target, changeNodeType, 0);
    //  OLDO Auto-generated constructor stub
  }

  @Override
  public String toString() {
    return "[REV | "
        + _nodeType
        + " | "
        + _sourceID
        + "]"
        + _node.toString().replace("\n", " ")
        + "=>"
        + _target.replace("\n", " ");
  }
}
