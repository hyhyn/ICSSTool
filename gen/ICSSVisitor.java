// Generated from /Users/h.hnguyen/Desktop/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ID}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitID(ICSSParser.IDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CLASS}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCLASS(ICSSParser.CLASSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LOW}
	 * labeled alternative in {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOW(ICSSParser.LOWContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ICSSParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variable_reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_reference(ICSSParser.Variable_referenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PixelLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PercentageLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ScalarLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ColorLiteral}
	 * labeled alternative in {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#calc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalc(ICSSParser.CalcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eoperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEoperation(ICSSParser.EoperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code muloperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuloperation(ICSSParser.MuloperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minexpression}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinexpression(ICSSParser.MinexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusoperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusoperation(ICSSParser.PlusoperationContext ctx);
}