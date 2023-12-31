
package cofix.core.parser.node.expr;

import cofix.common.util.Pair;
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
public class SuperFieldAcc extends Expr {

  private Label _name = null;
  private SName _identifier = null;

  /** SuperFieldAccess: [ ClassName . ] super . Identifier */
  public SuperFieldAcc(int startLine, int endLine, ASTNode node) {
    super(startLine, endLine, node);
    _nodeType = TYPE.SFIELDACC;
  }

  public void setName(Label name) {
    _name = name;
  }

  public void setIdentifier(SName identifier) {
    _identifier = identifier;
  }

  @Override
  public boolean match(
      Node node,
      Map<String, String> varTrans,
      Map<String, Type> allUsableVariables,
      List<Modification> modifications) {
    boolean match = false;
    if (node instanceof SuperFieldAcc) {
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
    StringBuffer stringBuffer = new StringBuffer();
    if (_name != null) {
      stringBuffer.append(_name);
      stringBuffer.append(".");
    }
    stringBuffer.append("super.");
    stringBuffer.append(_identifier.toSrcString());
    return stringBuffer;
  }

  @Override
  public List<Literal> getLiterals() {
    return new LinkedList<>();
  }

  @Override
  public List<SVariable> getVariables() {
    List<SVariable> list = new LinkedList<>();
    if (_name != null) {
      list.addAll(_name.getVariables());
    }
    list.addAll(_identifier.getVariables());
    return list;
  }

  @Override
  public void computeFeatureVector() {
    _fVector = new NewFVector();
    if (_name != null) {
      _fVector.combineFeature(_name.getFeatureVector());
    }
    _fVector.combineFeature(_identifier.getFeatureVector());
  }

  @Override
  public List<Node> getChildren() {
    return new ArrayList<>();
  }

  @Override
  public String simplify(Map<String, String> varTrans, Map<String, Type> allUsableVariables) {
    Map<SName, Pair<String, String>> record =
        NodeUtils.tryReplaceAllVariables(this, varTrans, allUsableVariables);
    if (record == null) {
      return null;
    }
    NodeUtils.replaceVariable(record);
    String string = toSrcString().toString();
    NodeUtils.restoreVariables(record);
    return string;
  }
}
