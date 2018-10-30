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

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;
    static private int count = 0;
    //hashmap( hashmap ( (hashmap)) map met expressions
//    private HashMap<String, HashMap<String, Expression>> hashashmap;
//    private HashMap<String, Expression> varAssignmentsMap;
    private LinkedList<HashMap<String, Expression>> linkHashmap;

    public void check(AST ast) {
        variableTypes = new LinkedList<>();
        linkHashmap = new LinkedList<>();

        addExpressionsToList(ast.root.getChildren(), 0);

        System.out.println("einde");
        //printstructure();
    }

    //return error
    private void addExpressionsToList(List<ASTNode> children, int level) {
        int currentlevel = level;
        linkHashmap.addFirst(new HashMap<>());
        linkHashmap.peekFirst().put(Integer.toString(level), null);
        List<ASTNode> stylerules = new ArrayList<>();

        for (ASTNode node :
                children) {
            if (node instanceof VariableAssignment) {
                linkHashmap.peekFirst().put(((VariableAssignment) node).name.name, ((VariableAssignment) node).expression);
                //addToVariableTypes(node);
            } else if (node instanceof Declaration) {
                linkHashmap.peekFirst().put(((Declaration) node).property.name, ((Declaration) node).expression);
                if (node.getChildren().get(1) instanceof VariableReference) {
                    checkUndefVar(node);
                }
                if (node.getChildren().get(1) instanceof Operation) {
                    checkOperationType(node.getChildren().get(1));
                }
                checkDeclaration(node);

            } else if (node instanceof Stylerule) {
                stylerules.add(node);
            }
        }
        currentlevel++;
        children.retainAll(stylerules);
        if (!children.isEmpty()) {
            for (ASTNode node :
                    children) {
                addExpressionsToList(node.getChildren(), currentlevel);
            }
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
                } else if (litType != type) {
                    child.setError("Add and Sub operation can only be used for equal types.");
                }
            } else if (child instanceof Literal) {
                ExpressionType type = checkRefLiteralType(child);
                if (litType == null) {
                    litType = type;
                } else if (litType != type) {
                    child.setError("Add and Sub operation can only be used for equal types.");
                }
            } else if (child instanceof VariableReference) {
                ExpressionType type = checkRefLiteralType(getVarRefInMap(child));
                if (litType == null) {
                    litType = type;
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
        //check each child of the node if
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
        ListIterator a = linkHashmap.listIterator();//werkt niet
        int linklevel = 0; //van linkmap
        int level = -1; //in hashmap
        HashMap<Integer, Integer> linkmap = new HashMap<>();
        boolean saveme = false;
        while (linklevel < linkHashmap.size() && !saveme) {
            HashMap map = linkHashmap.get(linklevel);
            level = Integer.valueOf(getKeysByValue(map, null).toArray()[0].toString()); //Dat gevoel wanneer je van een keyset naar array naar string naar int gaat...

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

    private void printmap(HashMap map) {
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
