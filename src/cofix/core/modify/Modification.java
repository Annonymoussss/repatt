
package cofix.core.modify;

import cofix.core.parser.node.Node;
import cofix.core.parser.node.Node.TYPE;
import java.util.Map;
import org.eclipse.jdt.core.dom.Type;

/**
 * @author anonymous
 * 
 */
public abstract class Modification {

  protected Node _node = null;
  protected TYPE _nodeType = TYPE.UNKNOWN;
  protected int _sourceID = -1;
  protected String _target = null;
  protected int _type = 0;

  public Modification(Node node, int srcId, String target, TYPE changeNodeType, int typeID) {
    _node = node;
    _sourceID = srcId;
    _target = target;
    if (_target == null) {
      _target = "";
    }
    _nodeType = changeNodeType;
    _type = typeID;
  }

  public int getRevisionTypeID() {
    return _type;
  }

  public TYPE getNodeType() {
    return _nodeType;
  }

  public Node getSrcNode() {
    return _node;
  }

  public int getSourceID() {
    return _sourceID;
  }

  public String getTargetString() {
    return _target;
  }

  public boolean apply(Map<String, Type> usableVars) {
    return _node.adapt(this);
  }

  public boolean backup() {
    return _node.backup(this);
  }

  public boolean restore() {
    return _node.restore(this);
  }

  public boolean compatible(Modification modification) {
    if (this instanceof Insertion || modification instanceof Insertion) {
      return !this.getTargetString().equals(modification.getTargetString());
    }
    if (this instanceof Deletion) {
      Node node = modification.getSrcNode();
      while (node != null) {
        if (node == _node) {
          return false;
        }
        node = node.getParent();
      }
    }
    if (modification instanceof Deletion) {
      Node node = _node;
      while (node != null) {
        if (node == modification.getSrcNode()) {
          return false;
        }
        node = node.getParent();
      }
    }
    return _node != modification._node || _sourceID != modification._sourceID;
  }
}
