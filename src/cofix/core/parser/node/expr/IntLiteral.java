
package cofix.core.parser.node.expr;

import cofix.core.modify.Modification;
import cofix.core.modify.Revision;
import cofix.core.parser.NodeUtils;
import cofix.core.parser.node.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Type;

/**
 * @author anonymous
 * 
 */
public class IntLiteral extends NumLiteral {

  private int _value = 0;

  private String _replace = null;

  private final int EXPRID = 0;

  public IntLiteral(int startLine, int endLine, ASTNode node) {
    super(startLine, endLine, node);
    _nodeType = TYPE.INTLITERAL;
  }

  public void setValue(int value) {
    _value = value;
  }

  public int getValue() {
    return _value;
  }

  @Override
  public boolean match(
      Node node,
      Map<String, String> varTrans,
      Map<String, Type> allUsableVariables,
      List<Modification> modifications) {
    boolean match = false;
    if (node instanceof IntLiteral) {
      match = true;
      IntLiteral other = (IntLiteral) node;
      if (_value != other._value) {
        if (!NodeUtils.isBoundaryValue(this)) {
          Revision revision = new Revision(this, EXPRID, other.toSrcString().toString(), _nodeType);
          modifications.add(revision);
        }
      }
    } else if (node instanceof SName || node instanceof QName) {
      Label label = (Label) node;
      if (label.getType().toString().equals("int")) {
        match = true;
        String target = node.simplify(varTrans, allUsableVariables);
        if (target != null && target.length() > 1) {
          Revision revision = new Revision(this, EXPRID, target, _nodeType);
          modifications.add(revision);
        }
      }
    } else {
      List<Modification> tmp = new ArrayList<>();
      if (node instanceof ConditionalExpr) {
        ConditionalExpr conditionalExpr = (ConditionalExpr) node;
        if (NodeUtils.conditionalMatch(
            this, EXPRID, conditionalExpr, varTrans, allUsableVariables, tmp)) {
          match = true;
          modifications.addAll(tmp);
        }
      } else {
        List<Node> children = node.getChildren();
        if (NodeUtils.nodeMatchList(this, children, varTrans, allUsableVariables, tmp)) {
          match = true;
          modifications.addAll(tmp);
        }
      }
    }
    return match;
  }

  @Override
  public boolean adapt(Modification modification) {
    if (modification.getSourceID() == EXPRID) {
      _replace = modification.getTargetString();
      return true;
    }
    return false;
  }

  @Override
  public boolean restore(Modification modification) {
    if (modification.getSourceID() == EXPRID) {
      _replace = null;
      return true;
    }
    return false;
  }

  @Override
  public boolean backup(Modification modification) {
    //  OLDO Auto-generated method stub
    return false;
  }

  @Override
  public StringBuffer toSrcString() {
    if (_replace != null) {
      return new StringBuffer(_replace);
    }
    return new StringBuffer(String.valueOf(_value));
  }
}
