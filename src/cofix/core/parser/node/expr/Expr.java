
package cofix.core.parser.node.expr;

import cofix.core.metric.CondStruct;
import cofix.core.metric.LoopStruct;
import cofix.core.metric.MethodCall;
import cofix.core.metric.Operator;
import cofix.core.metric.OtherStruct;
import cofix.core.metric.SVariable.USE_TYPE;
import cofix.core.parser.node.CodeBlock;
import cofix.core.parser.node.Node;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Type;

/**
 * @author anonymous
 * 
 */
public abstract class Expr extends Node {

  protected Type _exprType = null;

  protected Expr(int startLine, int endLine, ASTNode node) {
    super(startLine, endLine, node, null);
    AST ast = AST.newAST(AST.JLS8);
    _exprType = ast.newWildcardType();
  }

  public void setType(Type exprType) {
    if (exprType != null) {
      _exprType = exprType;
    }
  }

  public Type getType() {
    return _exprType;
  }

  @Override
  public List<LoopStruct> getLoopStruct() {
    return new LinkedList<>();
  }

  @Override
  public List<CondStruct> getCondStruct() {
    return new LinkedList<>();
  }

  @Override
  public List<Operator> getOperators() {
    return new LinkedList<>();
  }

  @Override
  public List<MethodCall> getMethodCalls() {
    return new LinkedList<>();
  }

  public List<OtherStruct> getOtherStruct() {
    return new LinkedList<>();
  }

  @Override
  public USE_TYPE getUseType(Node child) {
    return _parent.getUseType(this);
  }

  @Override
  public List<CodeBlock> reduce() {
    return new LinkedList<>();
  }
}
