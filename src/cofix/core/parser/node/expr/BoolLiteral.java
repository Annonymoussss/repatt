
package cofix.core.parser.node.expr;

import cofix.core.metric.Literal;
import cofix.core.metric.NewFVector;
import cofix.core.metric.SVariable;
import cofix.core.modify.Modification;
import cofix.core.modify.Revision;
import cofix.core.parser.NodeUtils;
import cofix.core.parser.node.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Type;

/**
 * @author anonymous
 * 
 */
public class BoolLiteral extends Expr {

  private boolean _value = false;
  private String _replace = null;

  private final int EXPRID = 0;
  /** BooleanLiteral: true false */
  public BoolLiteral(int startLine, int endLine, ASTNode node) {
    super(startLine, endLine, node);
    _nodeType = TYPE.BLITERAL;
  }

  public void setValue(boolean value) {
    _value = value;
  }

  @Override
  public boolean match(
      Node node,
      Map<String, String> varTrans,
      Map<String, Type> allUsableVariables,
      List<Modification> modifications) {
    boolean match = false;
    if (node instanceof BoolLiteral) {
      match = true;
      BoolLiteral other = (BoolLiteral) node;
      if (_value != other._value) {
        Revision revision = new Revision(this, EXPRID, other.toSrcString().toString(), _nodeType);
        modifications.add(revision);
      }
    } else if (node instanceof SName || node instanceof QName) {
      Label label = (Label) node;
      if (label.getType().toString().equals("boolean")) {
        match = true;
        String target = node.simplify(varTrans, allUsableVariables);
        if (target != null) {
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
    return true;
  }

  @Override
  public StringBuffer toSrcString() {
    if (_replace != null) {
      return new StringBuffer(_replace);
    } else {
      return new StringBuffer(String.valueOf(_value));
    }
  }

  @Override
  public List<Literal> getLiterals() {
    List<Literal> list = new LinkedList<>();
    Literal literal = new Literal(this);
    list.add(literal);
    return list;
  }

  @Override
  public List<SVariable> getVariables() {
    List<SVariable> list = new LinkedList<>();
    return list;
  }

  @Override
  public void computeFeatureVector() {
    _fVector = new NewFVector();
    _fVector.inc(NewFVector.INDEX_LITERAL);
  }

  @Override
  public List<Node> getChildren() {
    return new ArrayList<>();
  }

  @Override
  public String simplify(Map<String, String> varTrans, Map<String, Type> allUsableVariables) {
    return toSrcString().toString();
  }
}
