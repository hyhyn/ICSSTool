package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

public class Generator {

    public String generate(AST ast) {
        StringBuilder builder = new StringBuilder();

        for (ASTNode node : ast.root.getChildren()) {
            if (node instanceof Stylerule) {
                addStyleRule((Stylerule) node, builder);
            }
        }
        return builder.toString();
    }

    private void addStyleRule(Stylerule stylerule, StringBuilder builder) {
        if (stylerule.selectors.size() == 2) {
            builder.append(stylerule.selectors.get(1))
                    .append(" ")
                    .append(stylerule.selectors.get(0));
        } else {
            builder.append(stylerule.selectors.get(0));
        }
        builder.append(" {")
                .append(System.lineSeparator());

        for (ASTNode node : stylerule.getChildren()) {
            if (node instanceof Declaration) {
                addDeclaration((Declaration) node, builder);
            }
        }
        builder.append('}')
                .append(System.lineSeparator());
    }

    private void addDeclaration(Declaration declaration, StringBuilder builder) {
        builder.append(" \t")
                .append(declaration.property.name)
                .append(": ")
                .append(getExpressionValue(declaration.expression))
                .append(";")
                .append(System.lineSeparator());
    }

    private String getExpressionValue(Expression expression) {
        if (expression instanceof ColorLiteral) {
            return ((ColorLiteral) expression).value;
        } else if (expression instanceof PercentageLiteral) {
            return ((PercentageLiteral) expression).value + "%";
        } else if (expression instanceof PixelLiteral) {
            return ((PixelLiteral) expression).value + "px";
        } else if (expression instanceof ScalarLiteral) {
            return Integer.toString(((ScalarLiteral) expression).value);
        }
        return expression.toString();
    }
}
