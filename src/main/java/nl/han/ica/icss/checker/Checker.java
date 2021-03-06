package nl.han.ica.icss.checker;

import java.util.*;
import java.util.stream.Collectors;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.*;

public class Checker {
    //niet gebruikt
    //private LinkedList<HashMap<String, ExpressionType>> variableTypes;
    private LinkedList<HashMap<String, Expression>> linkHashmap;

    public void check(AST ast) {
        //variableTypes = new LinkedList<>();
        linkHashmap = new LinkedList<>();
        //checkAll(ast, ast.root.getChildren());
        addExpressionsToList(ast.root.getChildren(), 0);
    }

    private void checkAll(AST ast, List<ASTNode> children) {
        for (ASTNode node :
                children) {
            if (node instanceof VariableAssignment) {
                checkVarAssign((VariableAssignment) node);
            }
            if (node instanceof Stylerule) {
                //checkStylerule((Stylerule) node);
            }
            if (node instanceof Declaration) {
                checkDeclaration((Declaration) node);
            }
        }
    }

    private void checkVarAssign(VariableAssignment varAsign) {
        if (varAsign.expression instanceof Literal) {

        } else if (varAsign.expression instanceof Operation) {
            if (!checkOperation((Operation) varAsign.expression)) {
                varAsign.expression.setError("something went wrong");
            }
        } else if (varAsign.expression instanceof VariableReference) {
            if (!checkVarExist((VariableReference) varAsign.expression)) {
                varAsign.setError("Var reference does not exist");
            }
        }
    }

    private boolean checkOperation(Operation operation) {
        if (operation.lhs instanceof Operation) {
            if (!checkOperation((Operation) operation.lhs)) {
                operation.lhs.setError("operation error");
            }
        }
        if (operation.rhs instanceof MultiplyOperation) {
            if (!checkOperation((MultiplyOperation) operation.rhs)) {
                operation.rhs.setError("operation error");
            }
        }

        if (operation instanceof MultiplyOperation) {
            return operation.lhs instanceof ScalarLiteral;
        } else if (operation instanceof SubtractOperation) {
            if (!(operation.rhs instanceof MultiplyOperation)) {
                return checkRefLiteralType(operation.rhs) == checkRefLiteralType(operation.lhs);
            }
        } else if (operation instanceof AddOperation) {
            if (!(operation.rhs instanceof MultiplyOperation)) {
                return checkRefLiteralType(operation.rhs) == checkRefLiteralType(operation.lhs);
            }
        }
        return false;
    }

    //kijkt of de reference bestaat.
    private boolean checkVarExist(VariableReference node) {
        for (HashMap map :
                linkHashmap) {
            if (map.get(node.name) != null) {

                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addExpressionsToList(List<ASTNode> children, int level) {
        int currentlevel = level;
        linkHashmap.addFirst(new HashMap<>());
        linkHashmap.peekFirst().put(Integer.toString(level), null);
        List<ASTNode> stylerules = new ArrayList<>();

        for (ASTNode node :
                children) {
            if (node instanceof VariableAssignment) {
                linkHashmap.peekFirst().put(((VariableAssignment) node).name.name, ((VariableAssignment) node).expression);
            } else if (node instanceof Declaration) {
                linkHashmap.peekFirst().put(((Declaration) node).property.name, ((Declaration) node).expression);
                if (node.getChildren().get(1) instanceof VariableReference) {
                    //checkUndefVar(node);
                    if (!checkVarExist((VariableReference) node.getChildren().get(1))) {
                        node.setError("undef var");
                    }
                }
                if (node.getChildren().get(1) instanceof Operation) {
                    checkOperationType(node.getChildren().get(1));
                }
                checkDeclaration(node);

            } else if (node instanceof Stylerule) {
                stylerules.add(node);
                addExpressionsToList(node.getChildren(), level);
            }
            //linkHashmap.removeFirst(); //het het mis gaat ligt het hieraan
            level++;
        }
    }

    //ch04
    private void checkDeclaration(ASTNode node) {
        Declaration dec = (Declaration) node;
        String name = dec.property.name;
        if (!(dec.expression instanceof Operation)) {
            if (name.equalsIgnoreCase("width") || name.equalsIgnoreCase("height")) {
                if (dec.expression instanceof VariableReference) {
                    if (checkRefLiteralType(getVarRefInMap(dec.expression)) != ExpressionType.PIXEL) {
                        dec.setError("Declaration value not valid.");
                    }
                } else if (!(dec.expression instanceof PixelLiteral)) {
                    dec.setError("Declaration value not valid.");
                }
            } else if (name.equalsIgnoreCase("color") || name.equalsIgnoreCase("background-color")) {
                if (dec.expression instanceof VariableReference) {
                    if (checkRefLiteralType(getVarRefInMap(dec.expression)) != ExpressionType.COLOR) {
                        dec.setError("Declaration value not valid.");
                    }
                } else if (!(dec.expression instanceof ColorLiteral)) {
                    dec.setError("Declaration value not valid.");
                }
            }
        }
    }

    //ch02 en ch03
    private ASTNode checkOperationType(ASTNode operation) {
        if (operation instanceof AddOperation || operation instanceof SubtractOperation) {
            checkAddSub(operation);
        } else if (operation instanceof MultiplyOperation) {
            checkMul(operation);
        }
        return operation;
    }

    private ExpressionType checkAddSub(ASTNode operationNode) {
        ExpressionType litType = null;
        for (ASTNode child :
                operationNode.getChildren()) {
            if (child instanceof Operation) {
                //checkOperationType(child); -- hoeft niet te checken wat voor een type het is.
                ExpressionType type = checkAddSub(child);
                if (litType == null) {
                    litType = type;
                } else if (type == ExpressionType.SCALAR || type == ExpressionType.PIXEL) {
                    //dit mag
                } else if (litType != type) {
                    child.setError("Add and Sub operation can only be used for equal types.");
                }
            } else if (child instanceof Literal) {
                ExpressionType type = checkRefLiteralType(child);
                if (litType == null) {
                    litType = type;
                } else if (type == ExpressionType.SCALAR || type == ExpressionType.PIXEL) {
                    //dit mag
                } else if (litType != type) {
                    child.setError("Add and Sub operation can only be used for equal types.");
                }
            } else if (child instanceof VariableReference) {
                ExpressionType type = checkRefLiteralType(getVarRefInMap(child));
                if (litType == null) {
                    litType = type;
                } else if (type == ExpressionType.SCALAR || type == ExpressionType.PIXEL) {
                    //dit mag
                } else if (litType != type) {
                    child.setError("Add and Sub operation can only be used for equal types.");
                }
            }
        }
        if (litType == ExpressionType.COLOR) {
            operationNode.setError("Add and Sub operations can not be used for colour codes");
        }
        return litType;
    }

    private void checkMul(ASTNode operationNode) {
        for (ASTNode child :
                operationNode.getChildren()) {
            if (child instanceof Operation) {
                checkOperationType(child);
            } else if (child instanceof VariableReference) {
                if (!checkVarRefScalarInMap(child)) {
                    child.setError("Variable reference is not a Scalar value");
                }
            } else if (child instanceof Literal) {
                if (!(child instanceof ScalarLiteral)) {
                    child.setError("Only scalar literals are allowed for multiplication");
                }
            }
        }
    }

    private ExpressionType checkRefLiteralType(ASTNode ref) {
        if (ref instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        } else if (ref instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        } else if (ref instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        } else if (ref instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        } else {
            return ExpressionType.UNDEFINED;
        }
    }

    private Literal getVarRefInMap(ASTNode ref) {
        VariableReference varRef = (VariableReference) ref;
        Literal lit;
        for (HashMap map :
                linkHashmap) {
            lit = (Literal) map.get(varRef.name);
            if (lit != null) {
                return lit;
            }
        }
        return null;
    }

    private boolean checkVarRefScalarInMap(ASTNode ref) {
        VariableReference varRef = (VariableReference) ref;
        for (HashMap map :
                linkHashmap) {
            if (map.get(varRef.name) instanceof ScalarLiteral) {
                return true;
            }
        }
        return false;
    }

    //ch01
    private void checkUndefVar(ASTNode node) {
        int linklevel = 0; //van linkmap
        int level = -1; //in hashmap
        HashMap<Integer, Integer> linkmap = new HashMap<>();
        boolean saveme = false;
        while (linklevel < linkHashmap.size() && !saveme) {
            HashMap map = linkHashmap.get(linklevel);
            //Dat gevoel wanneer je van een keyset naar array naar string naar int gaat...
            level = Integer.valueOf(getKeysByValue(map, null).toArray()[0].toString());

            if (map.containsKey(((VariableReference) node.getChildren().get(1)).name) && !linkmap.containsKey(level)) {
                saveme = true;
            } else if (level != 0) {
                linkmap.put(level, level);
                linklevel++;
            } else {
                node.getChildren().get(1).setError("var bestaat niet");
                saveme = true;
            }
        }
    }

    /**
     * https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
     * dank u
     */
    public static <String, Expression> Set<String> getKeysByValue(Map<String, Expression> map, Expression value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    //voor test doeleinden
    private void printstructure() {
        System.out.println("head");
        for (int i = 0; i < linkHashmap.size(); i++) {
            HashMap a = linkHashmap.get(i);
            System.out.println("dit is de huidige it van i" + i);
            a.forEach((key, value) -> System.out.println(key + " = " + value));
            System.out.println("DOOT");

        }
        System.out.println("tails");
    }

    //voor test doeleinden
    private void printmap(HashMap map) {
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
    //wtfman
}
