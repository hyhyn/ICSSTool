package nl.han.ica.icss.checker;

import java.util.*;
import java.util.stream.Collectors;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
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
                //checkUndefVar(node);
            } else if (node instanceof Declaration) {
                linkHashmap.peekFirst().put(((Declaration) node).property.name, ((Declaration) node).expression);
                if (node.getChildren().get(1) instanceof VariableReference) {
                    checkUndefVar(node);
                }

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

    private void checkUndefVar(ASTNode node) {
        ListIterator a = linkHashmap.listIterator();//werkt niet
        int linklevel = 0; //van linkmap
        int level = -1; //in hashmap
        HashMap<Integer, Integer> linkmap = new HashMap<>();

        boolean saveme = false;
        while (linklevel < linkHashmap.size() && !saveme) {
            System.out.println("GET ON MY LEVEL " + level);
            HashMap map = linkHashmap.get(linklevel);
            level = Integer.valueOf(getKeysByValue(map, null).toArray()[0].toString()); //Dat gevoel wanneer je van een keyset naar array naar string naar int gaat...

            if (map.containsKey(((VariableReference) node.getChildren().get(1)).name) && !linkmap.containsKey(level)) {
                System.out.println("node");
                System.out.println(((VariableReference) node.getChildren().get(1)).name);
                System.out.println("map");
                System.out.println(map.get(((VariableReference) node.getChildren().get(1)).name).toString());
                saveme = true;
            } else if (level != 0) {
                linkmap.put(level, level);
                linklevel++;
            } else {
                node.setError("variable is niet set");
                saveme = true;
            }

        }
    }


    /**
     * https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
     * dank u
     *
     * @param map
     * @param value
     * @param <String>
     * @param <Expression>
     * @return
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
