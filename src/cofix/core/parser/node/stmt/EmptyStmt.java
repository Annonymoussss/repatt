
package cofix.core.parser.node.stmt;

import cofix.core.metric.NewFVector;
import cofix.core.modify.Modification;
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
public class EmptyStmt extends Stmt {

  /** EmptyStatement: ; */
  public EmptyStmt(int startLine, int endLine, ASTNode node) {
    this(startLine, endLine, node, null);
  }

  public EmptyStmt(int startLine, int endLine, ASTNode node, Node parent) {
    super(startLine, endLine, node, parent);
  }

  @Override
  public boolean match(
      Node node,
      Map<String, String> varTrans,
      Map<String, Type> allUsableVariables,
      List<Modification> modifications) {
    //  OLDO Auto-generated method stub
    return false;
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
    return new StringBuffer();
  }

  @Override
  public void computeFeatureVector() {
    _fVector = new NewFVector();
  }

  @Override
  public List<Node> getChildren() {
    return new ArrayList<>();
  }

  @Override
  public String simplify(Map<String, String> varTrans, Map<String, Type> allUsableVariables) {
    return "";
  }
}
