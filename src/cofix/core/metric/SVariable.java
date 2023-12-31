
package cofix.core.metric;

import cofix.core.parser.node.Node;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Type;

/**
 * @author anonymous
 * 
 */
public class SVariable extends Feature {

  public enum USE_TYPE {
    USE_METHOD_EXP,
    USE_METHOD_PARAM,
    USE_INFIX_EXP,
    USE_POSTFIX_EXP,
    USE_PREFIX_EXP,
    USE_ARR_ACC,
    USE_VAR_DECL,
    USE_ASSIGN_LHS,
    USE_ASSIGN_RHS,
    USE_CONDITIONAL,
    USE_LOOP,
    USE_IF,
    USE_RETURN,
    USE_SWCASE,
    USE_SWSTMT,
    USE_SYNC,
    USE_THROW,
    USE_TRY,
    USE_UNKNOWN
  }

  private String _name = null;
  private Type _type = null;
  private USE_TYPE _kind = null;

  public SVariable(Node node, String name, Type type) {
    super(node);
    _name = name;
    _type = type;
    if (_type == null) {
      AST ast = AST.newAST(AST.JLS8);
      _type = ast.newWildcardType();
    }
    if (node != null) {
      _kind = node.getUseType(node);
    } else {
      _kind = USE_TYPE.USE_UNKNOWN;
    }
  }

  public Type getType() {
    return _type;
  }

  public String getName() {
    return _name;
  }

  public USE_TYPE getUseType() {
    return _kind;
  }

  @Override
  public int hashCode() {
    return _name.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof SVariable)) {
      return false;
    }
    SVariable other = (SVariable) obj;
    if (!_name.equals(other._name)) {
      return false;
    }
    if (_type == other._type) {
      return true;
    }
    if (_type == null || other._type == null) {
      return true;
    }
    String thisTypeStr = _type.toString();
    String otherTypeStr = other._type.toString();
    if (thisTypeStr.equals("Object") || otherTypeStr.equals("Object")) {
      return true;
    }
    return thisTypeStr.equals(otherTypeStr);
  }

  @Override
  public String toString() {
    return _name;
  }
}
