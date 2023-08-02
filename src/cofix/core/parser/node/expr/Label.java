
package cofix.core.parser.node.expr;

import org.eclipse.jdt.core.dom.ASTNode;

/**
 * @author anonymous
 * 
 */
public abstract class Label extends Expr {

  /** Name: SimpleName QualifiedName */
  public Label(int startLine, int endLine, ASTNode node) {
    super(startLine, endLine, node);
  }
}
