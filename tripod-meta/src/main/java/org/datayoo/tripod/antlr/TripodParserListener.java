// Generated from org/datayoo/tripod/antlr/TripodParser.g4 by ANTLR 4.7.1
package org.datayoo.tripod.antlr;

import java.util.LinkedList;
import java.util.BitSet;
import org.datayoo.tripod.metadata.*;
import org.datayoo.tripod.exception.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TripodParser}.
 */
public interface TripodParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TripodParser#tripodExpression}.
	 * @param ctx the parse tree
	 */
	void enterTripodExpression(TripodParser.TripodExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#tripodExpression}.
	 * @param ctx the parse tree
	 */
	void exitTripodExpression(TripodParser.TripodExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(TripodParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(TripodParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(TripodParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(TripodParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(TripodParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(TripodParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void enterBasicExpression(TripodParser.BasicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void exitBasicExpression(TripodParser.BasicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(TripodParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(TripodParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(TripodParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(TripodParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#rangeTerm}.
	 * @param ctx the parse tree
	 */
	void enterRangeTerm(TripodParser.RangeTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#rangeTerm}.
	 * @param ctx the parse tree
	 */
	void exitRangeTerm(TripodParser.RangeTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#rangeValue}.
	 * @param ctx the parse tree
	 */
	void enterRangeValue(TripodParser.RangeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#rangeValue}.
	 * @param ctx the parse tree
	 */
	void exitRangeValue(TripodParser.RangeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#inTerm}.
	 * @param ctx the parse tree
	 */
	void enterInTerm(TripodParser.InTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#inTerm}.
	 * @param ctx the parse tree
	 */
	void exitInTerm(TripodParser.InTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TripodParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TripodParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#phrase}.
	 * @param ctx the parse tree
	 */
	void enterPhrase(TripodParser.PhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#phrase}.
	 * @param ctx the parse tree
	 */
	void exitPhrase(TripodParser.PhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#fuzzyExpression}.
	 * @param ctx the parse tree
	 */
	void enterFuzzyExpression(TripodParser.FuzzyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#fuzzyExpression}.
	 * @param ctx the parse tree
	 */
	void exitFuzzyExpression(TripodParser.FuzzyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TripodParser#proximityExpression}.
	 * @param ctx the parse tree
	 */
	void enterProximityExpression(TripodParser.ProximityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TripodParser#proximityExpression}.
	 * @param ctx the parse tree
	 */
	void exitProximityExpression(TripodParser.ProximityExpressionContext ctx);
}