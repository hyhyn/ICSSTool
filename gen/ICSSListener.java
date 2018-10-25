// Generated from /Users/h.hnguyen/Desktop/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ID}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterID(ICSSParser.IDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ID}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitID(ICSSParser.IDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CLASS}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterCLASS(ICSSParser.CLASSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CLASS}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitCLASS(ICSSParser.CLASSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LOW}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterLOW(ICSSParser.LOWContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LOW}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitLOW(ICSSParser.LOWContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ICSSParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ICSSParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variable_reference}.
	 * @param ctx the parse tree
	 */
	void enterVariable_reference(ICSSParser.Variable_referenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variable_reference}.
	 * @param ctx the parse tree
	 */
	void exitVariable_reference(ICSSParser.Variable_referenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PixelLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PixelLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PercentageLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PercentageLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ScalarLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ScalarLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ColorLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ColorLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#calc}.
	 * @param ctx the parse tree
	 */
	void enterCalc(ICSSParser.CalcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#calc}.
	 * @param ctx the parse tree
	 */
	void exitCalc(ICSSParser.CalcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eoperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterEoperation(ICSSParser.EoperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eoperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitEoperation(ICSSParser.EoperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code muloperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterMuloperation(ICSSParser.MuloperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code muloperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitMuloperation(ICSSParser.MuloperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minexpression}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterMinexpression(ICSSParser.MinexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minexpression}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitMinexpression(ICSSParser.MinexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusoperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterPlusoperation(ICSSParser.PlusoperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusoperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitPlusoperation(ICSSParser.PlusoperationContext ctx);
}