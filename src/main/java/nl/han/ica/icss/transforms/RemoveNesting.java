package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import java.util.ArrayList;
import java.util.List;

public class RemoveNesting implements Transform {

    private List<ASTNode> addToRoot;

    @Override
    public void apply(AST ast) {
        removeNesting(ast);
    }

    private void removeNesting(AST ast) {
        addToRoot = new ArrayList<>();
        addParentSelector(ast.root.getChildren());
        removeNestedRules(ast, null, ast.root.getChildren());
        for (ASTNode node : addToRoot) {
            ast.root.addChild(node);
        }
    }

    private void addParentSelector(List<ASTNode> astNodes) {
        for (ASTNode node : astNodes) {
            if (node instanceof Stylerule) {
                if (node.getChildren() != null) {
                    for (ASTNode childNode :
                            node.getChildren()) {
                        if (childNode instanceof Stylerule) {
                            for (int i = 0; i < ((Stylerule) node).selectors.size(); i++) {
                                childNode.addChild(((Stylerule) node).selectors.get(i));
                            }
                            addParentSelector(node.getChildren());
                        }
                    }
                }
            }
        }
    }

    private void removeNestedRules(AST ast, ASTNode parent, List<ASTNode> astNodes) {
        for (ASTNode node : astNodes) {
            if (node instanceof Stylerule) {
                if (!ast.root.getChildren().contains(node)) {
                    addToRoot.add(node);
                    parent.removeChild(node);
                }
                if (node.getChildren() != null) {
                    removeNestedRules(ast, node, node.getChildren());
                }
            }
        }
    }
}
