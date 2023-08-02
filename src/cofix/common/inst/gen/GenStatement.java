
package cofix.common.inst.gen;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;

/**
 * @author anonymous
 * 
 */
public class GenStatement {

  private static final AST ast = AST.newAST(AST.JLS8);

  private static Statement genPrinter(Expression expression) {
    MethodInvocation methodInvocation = ast.newMethodInvocation();
    methodInvocation.setExpression(ast.newName("System.out"));
    methodInvocation.setName(ast.newSimpleName("println"));
    methodInvocation.arguments().add(expression);
    ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
    return expressionStatement;
  }

  public static Statement genPrinter(String message) {
    StringLiteral stringLiteral = ast.newStringLiteral();
    stringLiteral.setLiteralValue(message);
    return genPrinter(stringLiteral);
  }
}
