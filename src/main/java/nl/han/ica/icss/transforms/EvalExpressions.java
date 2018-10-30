package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EvalExpressions implements Transform {

    private LinkedList<HashMap<String, Literal>> variableValues;

    public EvalExpressions() {
        variableValues = new LinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new LinkedList<>();
        evaluate(ast.root.getChildren());

        removeAllVarAssignments(ast.root);
        printstructure(variableValues);
    }

    private void evaluate(List<ASTNode> children) {
        List<ASTNode> styleRules = new ArrayList<>();
        HashMap map = new HashMap();
        for (ASTNode node :
                children) {
            if (node instanceof VariableAssignment) {
                writeToMap((VariableAssignment) node, map);
            } else if (node instanceof Declaration) {

            } else if (node instanceof Stylerule) {
                styleRules.add(node);
            }
        }
        children.retainAll(styleRules);
        if (!styleRules.isEmpty()) {
            if (!children.isEmpty()) {
                for (ASTNode node :
                        children) {
                    evaluate(node.getChildren());
                }
            }
        }
    }

    private HashMap writeToMap(VariableAssignment varAssign, HashMap map) {
        System.out.println("writetomap");
        if (varAssign.expression instanceof Literal) {
            map.put(varAssign.name, varAssign.expression);
        } else if (varAssign.expression instanceof VariableReference) {
            map.put(varAssign.name, travelMap((VariableReference) varAssign.expression));
        } else if (varAssign.expression instanceof Operation) {
            map.put(varAssign.name, calcOperation((Operation) varAssign.expression));
        }
        return map;
    }

    private Literal calcOperation(Operation operation) {
        System.out.println("calcOperation");
        Literal literal = null;
        Literal lhs = null;
        Literal rhs = null;
        for (ASTNode child :
                operation.getChildren()) {
            if (child instanceof Operation) {
                lhs = calcOperation(operation);
            }
        }

        if (operation.lhs instanceof Operation) {
            lhs = calcOperation((Operation) operation.lhs);
        }

        if (operation.rhs instanceof Operation) {
            rhs = calcOperation((Operation) operation.rhs);
        }

        if (operation instanceof AddOperation) {
            lhs = ((Literal) operation.getChildren().get(0));
            rhs = ((Literal) operation.getChildren().get(1));
            if (operation.rhs instanceof PixelLiteral) {
                literal = new PixelLiteral(((PixelLiteral) lhs).value +
                        ((PixelLiteral) rhs).value);
            }
            if (operation.rhs instanceof ScalarLiteral) {
                literal = new ScalarLiteral(((ScalarLiteral) lhs).value +
                        ((ScalarLiteral) rhs).value);
            }

        } else if (operation instanceof SubtractOperation) {
            lhs = ((Literal) operation.getChildren().get(0));
            rhs = ((Literal) operation.getChildren().get(1));
            if (operation.rhs instanceof PixelLiteral) {
                literal = new PixelLiteral(((PixelLiteral) lhs).value -
                        ((PixelLiteral) rhs).value);
            }
            if (operation.rhs instanceof ScalarLiteral) {
                literal = new ScalarLiteral(((ScalarLiteral) lhs).value -
                        ((ScalarLiteral) rhs).value);
            }

        } else if (operation instanceof MultiplyOperation) {
            lhs = ((Literal) operation.getChildren().get(0));
            rhs = ((Literal) operation.getChildren().get(1));
            if (operation.rhs instanceof PixelLiteral) {
                literal = new PixelLiteral(((PixelLiteral) lhs).value *
                        ((PixelLiteral) rhs).value);
            }
            if (operation.rhs instanceof ScalarLiteral) {
                literal = new ScalarLiteral(((ScalarLiteral) lhs).value *
                        ((ScalarLiteral) rhs).value);
            }
        }

        return literal; // lhs + rhs
    }

    private Literal travelMap(VariableReference reference) {
        for (HashMap map :
                variableValues) {
            return (Literal) map.get(reference.name);

        }
        return null;
    }


    private void removeAllVarAssignments(Stylesheet stylesheet) {

    }

    private void printstructure(LinkedList<HashMap<String, Literal>> map) {
        System.out.println("head");
        for (int i = 0; i < variableValues.size(); i++) {
            HashMap a = variableValues.get(i);
            a.forEach((key, value) -> System.out.println(key + " = " + value));
            System.out.println("DOOT");
        }
        System.out.println("tails");
    }
}