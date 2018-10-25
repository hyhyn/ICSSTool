package nl.han.ica.icss.parser;

import java.util.Stack;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

    //Accumulator attributes:
    private AST ast;

    //Use this to keep track of the parent nodes when recursively traversing the ast
    private Stack<ASTNode> currentContainer;

    public ASTListener() {
        ast = new AST();
        currentContainer = new Stack<>();
    }

    public AST getAST() {
        return ast;
    }

    @Override
    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        currentContainer.push(new Stylesheet());
    }

    @Override
    public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        ast.setRoot((Stylesheet) currentContainer.pop());
    }

    //level 0
    @Override
    public void enterStylerule(ICSSParser.StyleruleContext ctx) {
        currentContainer.push(new Stylerule());
    }

    @Override
    public void exitStylerule(ICSSParser.StyleruleContext ctx) {
        ASTNode temp = currentContainer.pop(); // pak de declaration
        currentContainer.peek().addChild(temp); // stop het in de stylesheet
    }

    //stylerule declaration
    @Override
    public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
        currentContainer.push(new Declaration());
    }

    @Override
    public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
        ASTNode temp = currentContainer.pop(); // pak de declaration
        currentContainer.peek().addChild(temp); // stop het in de stylesheet
    }

    @Override
    public void enterProperty(ICSSParser.PropertyContext ctx) {
        currentContainer.peek().addChild(new PropertyName(ctx.getChild(0).getText()));
    }

    //selectors
    @Override
    public void enterLOW(ICSSParser.LOWContext ctx) {
        currentContainer.peek().addChild(new TagSelector(ctx.getChild(0).getText()));
    }

    @Override
    public void enterID(ICSSParser.IDContext ctx) {
        currentContainer.peek().addChild(new IdSelector(ctx.getChild(0).getText()));
    }

    @Override
    public void enterCLASS(ICSSParser.CLASSContext ctx) {
        currentContainer.peek().addChild(new ClassSelector(ctx.getChild(0).getText()));
    }

    //level 1 -variables
    @Override
    public void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
        ASTNode temp = currentContainer.pop(); //temo is de variable assignement
        currentContainer.peek().addChild(temp); //peek is de stylesheet
    }

    @Override
    public void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
        currentContainer.push(new VariableAssignment());
    }

    @Override
    public void enterVariable_reference(ICSSParser.Variable_referenceContext ctx) {
        currentContainer.peek().addChild(new VariableReference(ctx.getChild(0).getText()));
    }

    //literals
    @Override
    public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
        currentContainer.peek().addChild(new ColorLiteral(ctx.getChild(0).getText()));
    }

    @Override
    public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
        currentContainer.peek().addChild(new ScalarLiteral(ctx.getChild(0).getText()));
    }

    @Override
    public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
        currentContainer.peek().addChild(new PercentageLiteral(ctx.getChild(0).getText()));
    }

    @Override
    public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
        currentContainer.peek().addChild(new PixelLiteral(ctx.getChild(0).getText()));
    }

    //level 2 operations
    @Override
    public void enterPlusoperation(ICSSParser.PlusoperationContext ctx) {
        currentContainer.push(new AddOperation());
    }

    @Override
    public void exitPlusoperation(ICSSParser.PlusoperationContext ctx) {
        ASTNode temp = currentContainer.pop();
        currentContainer.peek().addChild(temp);
    }

    @Override
    public void enterMuloperation(ICSSParser.MuloperationContext ctx) {
        currentContainer.push(new MultiplyOperation());
    }

    @Override
    public void exitMuloperation(ICSSParser.MuloperationContext ctx) {
        ASTNode temp = currentContainer.pop();
        currentContainer.peek().addChild(temp);
    }

    @Override
    public void enterMinexpression(ICSSParser.MinexpressionContext ctx) {
        currentContainer.push(new SubtractOperation());
    }

    @Override
    public void exitMinexpression(ICSSParser.MinexpressionContext ctx) {
        ASTNode temp = currentContainer.pop();
        currentContainer.peek().addChild(temp);
    }
}
