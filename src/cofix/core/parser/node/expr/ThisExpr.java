
package cofix.core.parser.node.expr;

import cofix.core.metric.Literal;
import cofix.core.metric.NewFVector;
import cofix.core.metric.SVariable;
import cofix.core.modify.Modification;
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
public class ThisExpr extends Expr {

  /** ThisExpression: [ ClassName . ] this */
  public ThisExpr(int startLine, int endLine, ASTNode node) {
    super(startLine, endLine, node);
    _nodeType = TYPE.THIS;
  }

  @Override
  public boolean match(
      Node node,
      Map<String, String> varTrans,
      Map<String, Type> allUsableVariables,
      List<Modification> modifications) {
    boolean match = false;
    if (node instanceof ThisExpr) {
      match = true;
      //  OLDO : to finish
    } else {
      List<Node> children = node.getChildren();
      List<Modification> tmp = new ArrayList<>();
      if (NodeUtils.nodeMatchList(this, children, varTrans, allUsableVariables, tmp)) {
        match = true;
        modifications.addAll(tmp);
      }
    }
    return match;
  }

  @Override
  public boolean adapt(Modification modification) {
    //  OLDO Auto-generated method stub
    return false;
  }

  @Override
  public boolean restore(Modification modification) {
    //  OLDO Auto-generated method stub
    return false;
  }

  @Override
  public boolean backup(Modification modification) {
    //  OLDO Auto-generated method stub
    return false;
  }

  @Override
  public StringBuffer toSrcString() {
    return new StringBuffer("this");
  }

  @Override
  public List<Literal> getLiterals() {
    return new LinkedList<>();
  }

  @Override
  public List<SVariable> getVariables() {
    List<SVariable> list = new LinkedList<>();
    //		Variable variable = new Variable(this, "this", _exprType);
    //		list.add(variable);
    return list;
  }

  @Override
  public void computeFeatureVector() {
    _fVector = new NewFVector();
    //		_fVector.inc(NewFVector.INDEX_VAR);
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
