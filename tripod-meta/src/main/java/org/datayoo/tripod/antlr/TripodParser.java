// Generated from org/datayoo/tripod/antlr/TripodParser.g4 by ANTLR 4.7.1
package org.datayoo.tripod.antlr;

import java.util.LinkedList;
import java.util.BitSet;
import org.datayoo.tripod.metadata.*;
import org.datayoo.tripod.exception.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TripodParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMBER=1, TERM=2, Q_PHRASE=3, DQ_PHRASE=4, LPAREN=5, RPAREN=6, LBRACE=7, 
		RBRACE=8, LBRACK=9, RBRACK=10, COMMA=11, NOT=12, COLON=13, AND=14, OR=15, 
		MUST=16, MINUS=17, STAR=18, CARET=19, SWANGDASH=20, QMARK=21, WS=22, COMMENT=23, 
		LINE_COMMENT=24, IDENTIFIER=25;
	public static final int
		RULE_tripodExpression = 0, RULE_orExpression = 1, RULE_andExpression = 2, 
		RULE_notExpression = 3, RULE_basicExpression = 4, RULE_primary = 5, RULE_parenExpression = 6, 
		RULE_rangeTerm = 7, RULE_rangeValue = 8, RULE_inTerm = 9, RULE_term = 10, 
		RULE_phrase = 11, RULE_fuzzyExpression = 12, RULE_proximityExpression = 13;
	public static final String[] ruleNames = {
		"tripodExpression", "orExpression", "andExpression", "notExpression", 
		"basicExpression", "primary", "parenExpression", "rangeTerm", "rangeValue", 
		"inTerm", "term", "phrase", "fuzzyExpression", "proximityExpression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'('", "')'", "'{'", "'}'", "'['", "']'", 
		"','", "'!'", "':'", null, null, "'+'", "'-'", "'*'", "'^'", "'~'", "'?'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NUMBER", "TERM", "Q_PHRASE", "DQ_PHRASE", "LPAREN", "RPAREN", "LBRACE", 
		"RBRACE", "LBRACK", "RBRACK", "COMMA", "NOT", "COLON", "AND", "OR", "MUST", 
		"MINUS", "STAR", "CARET", "SWANGDASH", "QMARK", "WS", "COMMENT", "LINE_COMMENT", 
		"IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TripodParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	protected void mismatch(IntStream input, int ttype, BitSet follow)
	throws RecognitionException
	{
	String message = String.format("'%c' is invalid!", input.LA(ttype));
	throw new TripodRuntimeException(message);
	}

	public void recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
	throws RecognitionException
	{
	throw e;
	}

	public TripodParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TripodExpressionContext extends ParserRuleContext {
		public BoolMetadata boolMetadata;
		public OrExpressionContext lExpr;
		public OrExpressionContext rExpr;
		public List<OrExpressionContext> orExpression() {
			return getRuleContexts(OrExpressionContext.class);
		}
		public OrExpressionContext orExpression(int i) {
			return getRuleContext(OrExpressionContext.class,i);
		}
		public TripodExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tripodExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterTripodExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitTripodExpression(this);
		}
	}

	public final TripodExpressionContext tripodExpression() throws RecognitionException {
		TripodExpressionContext _localctx = new TripodExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tripodExpression);

		    ((TripodExpressionContext)_localctx).boolMetadata =  new BoolMetadata();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			((TripodExpressionContext)_localctx).lExpr = orExpression();

			    _localctx.boolMetadata.getBoolExprs().add(((TripodExpressionContext)_localctx).lExpr.expressionMetadata);
			  
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << TERM) | (1L << Q_PHRASE) | (1L << DQ_PHRASE) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << MUST) | (1L << MINUS) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(30);
				((TripodExpressionContext)_localctx).rExpr = orExpression();

				    _localctx.boolMetadata.getBoolExprs().add(((TripodExpressionContext)_localctx).rExpr.expressionMetadata);
				  
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public AndExpressionContext lExpr;
		public AndExpressionContext rExpr;
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(TripodParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(TripodParser.OR, i);
		}
		public OrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitOrExpression(this);
		}
	}

	public final OrExpressionContext orExpression() throws RecognitionException {
		OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_orExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((OrExpressionContext)_localctx).lExpr = andExpression();

			    ((OrExpressionContext)_localctx).expressionMetadata =  ((OrExpressionContext)_localctx).lExpr.expressionMetadata;
			  
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(40);
				match(OR);
				setState(41);
				((OrExpressionContext)_localctx).rExpr = andExpression();

				    ((OrExpressionContext)_localctx).expressionMetadata =  new BinaryMetadata(ExpressionType.OR, _localctx.expressionMetadata, ((OrExpressionContext)_localctx).rExpr.expressionMetadata);
				  
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public NotExpressionContext lExpr;
		public NotExpressionContext rExpr;
		public List<NotExpressionContext> notExpression() {
			return getRuleContexts(NotExpressionContext.class);
		}
		public NotExpressionContext notExpression(int i) {
			return getRuleContext(NotExpressionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(TripodParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(TripodParser.AND, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitAndExpression(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			((AndExpressionContext)_localctx).lExpr = notExpression();

			    ((AndExpressionContext)_localctx).expressionMetadata =  ((AndExpressionContext)_localctx).lExpr.expressionMetadata;
			  
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(51);
				match(AND);
				setState(52);
				((AndExpressionContext)_localctx).rExpr = notExpression();

				    ((AndExpressionContext)_localctx).expressionMetadata =  new BinaryMetadata(ExpressionType.AND, _localctx.expressionMetadata, ((AndExpressionContext)_localctx).rExpr.expressionMetadata);
				  
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public BasicExpressionContext exprCtx;
		public BasicExpressionContext basicExpression() {
			return getRuleContext(BasicExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(TripodParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(TripodParser.MINUS, 0); }
		public NotExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitNotExpression(this);
		}
	}

	public final NotExpressionContext notExpression() throws RecognitionException {
		NotExpressionContext _localctx = new NotExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_notExpression);

		    ExpressionType expressionType = null;

		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				{
				setState(60);
				match(NOT);
				expressionType = ExpressionType.NOT;
				}
				break;
			case MINUS:
				{
				setState(62);
				match(MINUS);
				expressionType = ExpressionType.MINUS;
				}
				break;
			case NUMBER:
			case TERM:
			case Q_PHRASE:
			case DQ_PHRASE:
			case LPAREN:
			case LBRACE:
			case LBRACK:
			case MUST:
			case IDENTIFIER:
				break;
			default:
				break;
			}
			setState(66);
			((NotExpressionContext)_localctx).exprCtx = basicExpression();

			    if (expressionType == null)
			        ((NotExpressionContext)_localctx).expressionMetadata =  ((NotExpressionContext)_localctx).exprCtx.expressionMetadata;
			    else {
			        ((NotExpressionContext)_localctx).expressionMetadata =  new UnaryMetadata(expressionType, ((NotExpressionContext)_localctx).exprCtx.expressionMetadata);
			    }
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasicExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public Token t;
		public PrimaryContext primaryCtx;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalNode MUST() { return getToken(TripodParser.MUST, 0); }
		public TerminalNode COLON() { return getToken(TripodParser.COLON, 0); }
		public TerminalNode CARET() { return getToken(TripodParser.CARET, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TripodParser.IDENTIFIER, 0); }
		public TerminalNode NUMBER() { return getToken(TripodParser.NUMBER, 0); }
		public BasicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterBasicExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitBasicExpression(this);
		}
	}

	public final BasicExpressionContext basicExpression() throws RecognitionException {
		BasicExpressionContext _localctx = new BasicExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_basicExpression);

		    boolean must = false;
		    String field = null;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MUST) {
				{
				setState(69);
				match(MUST);
				must = true;
				}
			}

			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(73);
				((BasicExpressionContext)_localctx).t = match(IDENTIFIER);
				setState(74);
				match(COLON);

				        field = ((BasicExpressionContext)_localctx).t.getText();
				    
				}
			}

			setState(78);
			((BasicExpressionContext)_localctx).primaryCtx = primary();

			        ((BasicExpressionContext)_localctx).expressionMetadata =  ((BasicExpressionContext)_localctx).primaryCtx.expressionMetadata;
			    
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CARET) {
				{
				setState(80);
				match(CARET);
				setState(82);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(81);
					((BasicExpressionContext)_localctx).t = match(NUMBER);
					}
					break;
				}

				      if (((BasicExpressionContext)_localctx).t != null) {
				          _localctx.expressionMetadata.setBoost(Integer.valueOf(((BasicExpressionContext)_localctx).t.getText()));
				      }
				    
				}
			}

			}
			_ctx.stop = _input.LT(-1);

			    if (field != null) {
			        ((BasicExpressionContext)_localctx).expressionMetadata =  new FieldMetadata(field, _localctx.expressionMetadata);
			    }
			    if (must) {
			        ((BasicExpressionContext)_localctx).expressionMetadata =  new UnaryMetadata(ExpressionType.MUST, _localctx.expressionMetadata);
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public RangeTermContext rangeCtx;
		public InTermContext inCtx;
		public ParenExpressionContext parenCtx;
		public TermContext termCtx;
		public FuzzyExpressionContext fuzzyCtx;
		public PhraseContext phraseCtx;
		public ProximityExpressionContext proximityCtx;
		public RangeTermContext rangeTerm() {
			return getRuleContext(RangeTermContext.class,0);
		}
		public InTermContext inTerm() {
			return getRuleContext(InTermContext.class,0);
		}
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FuzzyExpressionContext fuzzyExpression() {
			return getRuleContext(FuzzyExpressionContext.class,0);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public ProximityExpressionContext proximityExpression() {
			return getRuleContext(ProximityExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_primary);
		int _la;
		try {
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				((PrimaryContext)_localctx).rangeCtx = rangeTerm();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).rangeCtx.rangeMetadata;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				((PrimaryContext)_localctx).inCtx = inTerm();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).inCtx.inMetadata;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				((PrimaryContext)_localctx).parenCtx = parenExpression();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).parenCtx.parenMetadata;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(96);
				((PrimaryContext)_localctx).termCtx = term();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).termCtx.expressionMetadata;
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SWANGDASH) {
					{
					setState(98);
					((PrimaryContext)_localctx).fuzzyCtx = fuzzyExpression();

					     ((PrimaryContext)_localctx).fuzzyCtx.fuzzyMetadata.setExpr(_localctx.expressionMetadata);
					     ((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).fuzzyCtx.fuzzyMetadata;
					  
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(103);
				((PrimaryContext)_localctx).phraseCtx = phrase();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).phraseCtx.phraseMetadata;
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SWANGDASH) {
					{
					setState(105);
					((PrimaryContext)_localctx).proximityCtx = proximityExpression();

					    ((PrimaryContext)_localctx).proximityCtx.proximityMetadata.setExpr(_localctx.expressionMetadata);
					    ((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).proximityCtx.proximityMetadata;
					  
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenExpressionContext extends ParserRuleContext {
		public UnaryMetadata parenMetadata;
		public TripodExpressionContext exprCtx;
		public TerminalNode LPAREN() { return getToken(TripodParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TripodParser.RPAREN, 0); }
		public TripodExpressionContext tripodExpression() {
			return getRuleContext(TripodExpressionContext.class,0);
		}
		public ParenExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitParenExpression(this);
		}
	}

	public final ParenExpressionContext parenExpression() throws RecognitionException {
		ParenExpressionContext _localctx = new ParenExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parenExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(LPAREN);
			setState(113);
			((ParenExpressionContext)_localctx).exprCtx = tripodExpression();

			    ((ParenExpressionContext)_localctx).parenMetadata =  new UnaryMetadata(ExpressionType.PAREN, ((ParenExpressionContext)_localctx).exprCtx.boolMetadata);
			  
			setState(115);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeTermContext extends ParserRuleContext {
		public RangeMetadata rangeMetadata;
		public RangeValueContext lExpr;
		public RangeValueContext rExpr;
		public TerminalNode COMMA() { return getToken(TripodParser.COMMA, 0); }
		public List<RangeValueContext> rangeValue() {
			return getRuleContexts(RangeValueContext.class);
		}
		public RangeValueContext rangeValue(int i) {
			return getRuleContext(RangeValueContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(TripodParser.LBRACK, 0); }
		public TerminalNode LBRACE() { return getToken(TripodParser.LBRACE, 0); }
		public TerminalNode RBRACK() { return getToken(TripodParser.RBRACK, 0); }
		public TerminalNode RBRACE() { return getToken(TripodParser.RBRACE, 0); }
		public RangeTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterRangeTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitRangeTerm(this);
		}
	}

	public final RangeTermContext rangeTerm() throws RecognitionException {
		RangeTermContext _localctx = new RangeTermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_rangeTerm);

		  boolean lInclude = false;
		  boolean rInclude = false;

		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				{
				setState(117);
				match(LBRACK);
				lInclude = true;
				}
				break;
			case LBRACE:
				{
				setState(119);
				match(LBRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(122);
			((RangeTermContext)_localctx).lExpr = rangeValue();
			setState(123);
			match(COMMA);
			setState(124);
			((RangeTermContext)_localctx).rExpr = rangeValue();
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RBRACK:
				{
				setState(125);
				match(RBRACK);
				rInclude = true;
				}
				break;
			case RBRACE:
				{
				setState(127);
				match(RBRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			    ((RangeTermContext)_localctx).rangeMetadata =  new RangeMetadata(lInclude, ((RangeTermContext)_localctx).lExpr.expressionMetadata, ((RangeTermContext)_localctx).rExpr.expressionMetadata, rInclude);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeValueContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public PhraseContext phraseCtx;
		public TermContext termCtx;
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public RangeValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterRangeValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitRangeValue(this);
		}
	}

	public final RangeValueContext rangeValue() throws RecognitionException {
		RangeValueContext _localctx = new RangeValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rangeValue);
		try {
			setState(138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Q_PHRASE:
			case DQ_PHRASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				((RangeValueContext)_localctx).phraseCtx = phrase();
				((RangeValueContext)_localctx).expressionMetadata =  ((RangeValueContext)_localctx).phraseCtx.phraseMetadata;
				}
				break;
			case NUMBER:
			case TERM:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				((RangeValueContext)_localctx).termCtx = term();
				((RangeValueContext)_localctx).expressionMetadata =  ((RangeValueContext)_localctx).termCtx.expressionMetadata;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InTermContext extends ParserRuleContext {
		public InMetadata inMetadata;
		public RangeValueContext expr;
		public TerminalNode LBRACK() { return getToken(TripodParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(TripodParser.RBRACK, 0); }
		public List<RangeValueContext> rangeValue() {
			return getRuleContexts(RangeValueContext.class);
		}
		public RangeValueContext rangeValue(int i) {
			return getRuleContext(RangeValueContext.class,i);
		}
		public InTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterInTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitInTerm(this);
		}
	}

	public final InTermContext inTerm() throws RecognitionException {
		InTermContext _localctx = new InTermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_inTerm);

		  List<ExpressionMetadata> expressions = new LinkedList<ExpressionMetadata>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(LBRACK);
			setState(141);
			((InTermContext)_localctx).expr = rangeValue();
			expressions.add(((InTermContext)_localctx).expr.expressionMetadata);
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << TERM) | (1L << Q_PHRASE) | (1L << DQ_PHRASE))) != 0)) {
				{
				{
				setState(143);
				((InTermContext)_localctx).expr = rangeValue();
				expressions.add(((InTermContext)_localctx).expr.expressionMetadata);
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151);
			match(RBRACK);

			    ((InTermContext)_localctx).inMetadata =  new InMetadata(expressions);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public Token t;
		public TerminalNode TERM() { return getToken(TripodParser.TERM, 0); }
		public TerminalNode NUMBER() { return getToken(TripodParser.NUMBER, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_term);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TERM:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				((TermContext)_localctx).t = match(TERM);

				    if (WildcardMetadata.isWildcard(((TermContext)_localctx).t.getText())) {
				        ((TermContext)_localctx).expressionMetadata =  new WildcardMetadata(((TermContext)_localctx).t.getText());
				    } else {
				        ((TermContext)_localctx).expressionMetadata =  new TermMetadata(((TermContext)_localctx).t.getText());
				    }
				  
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				((TermContext)_localctx).t = match(NUMBER);

				    ((TermContext)_localctx).expressionMetadata =  new TermMetadata(((TermContext)_localctx).t.getText());
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PhraseContext extends ParserRuleContext {
		public PhraseMetadata phraseMetadata;
		public Token t;
		public TerminalNode Q_PHRASE() { return getToken(TripodParser.Q_PHRASE, 0); }
		public TerminalNode DQ_PHRASE() { return getToken(TripodParser.DQ_PHRASE, 0); }
		public PhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitPhrase(this);
		}
	}

	public final PhraseContext phrase() throws RecognitionException {
		PhraseContext _localctx = new PhraseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_phrase);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Q_PHRASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				((PhraseContext)_localctx).t = match(Q_PHRASE);
				((PhraseContext)_localctx).phraseMetadata =  new PhraseMetadata(((PhraseContext)_localctx).t.getText());
				}
				break;
			case DQ_PHRASE:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				((PhraseContext)_localctx).t = match(DQ_PHRASE);
				((PhraseContext)_localctx).phraseMetadata =  new PhraseMetadata(((PhraseContext)_localctx).t.getText());
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuzzyExpressionContext extends ParserRuleContext {
		public SuffixMetadata fuzzyMetadata;
		public Token t;
		public TerminalNode SWANGDASH() { return getToken(TripodParser.SWANGDASH, 0); }
		public TerminalNode NUMBER() { return getToken(TripodParser.NUMBER, 0); }
		public FuzzyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fuzzyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterFuzzyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitFuzzyExpression(this);
		}
	}

	public final FuzzyExpressionContext fuzzyExpression() throws RecognitionException {
		FuzzyExpressionContext _localctx = new FuzzyExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fuzzyExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(SWANGDASH);
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(167);
				((FuzzyExpressionContext)_localctx).t = match(NUMBER);
				}
				break;
			}

			    if (((FuzzyExpressionContext)_localctx).t != null) {
			        if (((FuzzyExpressionContext)_localctx).t.getText().indexOf('.') == -1) {
			            ((FuzzyExpressionContext)_localctx).fuzzyMetadata =  new SuffixMetadata(ExpressionType.FUZZY, Integer.valueOf(((FuzzyExpressionContext)_localctx).t.getText()));
			        } else {
			            ((FuzzyExpressionContext)_localctx).fuzzyMetadata =  new SuffixMetadata(ExpressionType.FUZZY, Double.valueOf(((FuzzyExpressionContext)_localctx).t.getText()));
			        }
			    } else {
			        ((FuzzyExpressionContext)_localctx).fuzzyMetadata =  new SuffixMetadata(ExpressionType.FUZZY, null);
			    }
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProximityExpressionContext extends ParserRuleContext {
		public SuffixMetadata proximityMetadata;
		public Token t;
		public TerminalNode SWANGDASH() { return getToken(TripodParser.SWANGDASH, 0); }
		public TerminalNode NUMBER() { return getToken(TripodParser.NUMBER, 0); }
		public ProximityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proximityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).enterProximityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TripodParserListener ) ((TripodParserListener)listener).exitProximityExpression(this);
		}
	}

	public final ProximityExpressionContext proximityExpression() throws RecognitionException {
		ProximityExpressionContext _localctx = new ProximityExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_proximityExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(SWANGDASH);
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(173);
				((ProximityExpressionContext)_localctx).t = match(NUMBER);
				}
				break;
			}

			    if (((ProximityExpressionContext)_localctx).t != null) {
			        if (((ProximityExpressionContext)_localctx).t.getText().indexOf('.') == -1) {
			            ((ProximityExpressionContext)_localctx).proximityMetadata =  new SuffixMetadata(ExpressionType.PROXIMITY, Integer.valueOf(((ProximityExpressionContext)_localctx).t.getText()));
			        } else {
			            ((ProximityExpressionContext)_localctx).proximityMetadata =  new SuffixMetadata(ExpressionType.PROXIMITY, Double.valueOf(((ProximityExpressionContext)_localctx).t.getText()));
			        }
			    } else {
			        ((ProximityExpressionContext)_localctx).proximityMetadata =  new SuffixMetadata(ExpressionType.PROXIMITY, null);
			    }
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00b5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\7\2$\n\2"+
		"\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3/\n\3\f\3\16\3\62\13\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\5\3\5\3\5\3\5\5\5C\n\5"+
		"\3\5\3\5\3\5\3\6\3\6\5\6J\n\6\3\6\3\6\3\6\5\6O\n\6\3\6\3\6\3\6\3\6\5\6"+
		"U\n\6\3\6\5\6X\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7h\n\7\3\7\3\7\3\7\3\7\3\7\5\7o\n\7\5\7q\n\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\5\t{\n\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0083\n\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u008d\n\n\3\13\3\13\3\13\3\13\3\13\3\13\7"+
		"\13\u0095\n\13\f\13\16\13\u0098\13\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5"+
		"\f\u00a1\n\f\3\r\3\r\3\r\3\r\5\r\u00a7\n\r\3\16\3\16\5\16\u00ab\n\16\3"+
		"\16\3\16\3\17\3\17\5\17\u00b1\n\17\3\17\3\17\3\17\2\2\20\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\2\2\2\u00bd\2\36\3\2\2\2\4(\3\2\2\2\6\63\3\2\2\2"+
		"\bB\3\2\2\2\nI\3\2\2\2\fp\3\2\2\2\16r\3\2\2\2\20z\3\2\2\2\22\u008c\3\2"+
		"\2\2\24\u008e\3\2\2\2\26\u00a0\3\2\2\2\30\u00a6\3\2\2\2\32\u00a8\3\2\2"+
		"\2\34\u00ae\3\2\2\2\36\37\5\4\3\2\37%\b\2\1\2 !\5\4\3\2!\"\b\2\1\2\"$"+
		"\3\2\2\2# \3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2"+
		"\2()\5\6\4\2)\60\b\3\1\2*+\7\21\2\2+,\5\6\4\2,-\b\3\1\2-/\3\2\2\2.*\3"+
		"\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\5\3\2\2\2\62\60\3\2\2"+
		"\2\63\64\5\b\5\2\64;\b\4\1\2\65\66\7\20\2\2\66\67\5\b\5\2\678\b\4\1\2"+
		"8:\3\2\2\29\65\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\7\3\2\2\2=;\3\2"+
		"\2\2>?\7\16\2\2?C\b\5\1\2@A\7\23\2\2AC\b\5\1\2B>\3\2\2\2B@\3\2\2\2BC\3"+
		"\2\2\2CD\3\2\2\2DE\5\n\6\2EF\b\5\1\2F\t\3\2\2\2GH\7\22\2\2HJ\b\6\1\2I"+
		"G\3\2\2\2IJ\3\2\2\2JN\3\2\2\2KL\7\33\2\2LM\7\17\2\2MO\b\6\1\2NK\3\2\2"+
		"\2NO\3\2\2\2OP\3\2\2\2PQ\5\f\7\2QW\b\6\1\2RT\7\25\2\2SU\7\3\2\2TS\3\2"+
		"\2\2TU\3\2\2\2UV\3\2\2\2VX\b\6\1\2WR\3\2\2\2WX\3\2\2\2X\13\3\2\2\2YZ\5"+
		"\20\t\2Z[\b\7\1\2[q\3\2\2\2\\]\5\24\13\2]^\b\7\1\2^q\3\2\2\2_`\5\16\b"+
		"\2`a\b\7\1\2aq\3\2\2\2bc\5\26\f\2cg\b\7\1\2de\5\32\16\2ef\b\7\1\2fh\3"+
		"\2\2\2gd\3\2\2\2gh\3\2\2\2hq\3\2\2\2ij\5\30\r\2jn\b\7\1\2kl\5\34\17\2"+
		"lm\b\7\1\2mo\3\2\2\2nk\3\2\2\2no\3\2\2\2oq\3\2\2\2pY\3\2\2\2p\\\3\2\2"+
		"\2p_\3\2\2\2pb\3\2\2\2pi\3\2\2\2q\r\3\2\2\2rs\7\7\2\2st\5\2\2\2tu\b\b"+
		"\1\2uv\7\b\2\2v\17\3\2\2\2wx\7\13\2\2x{\b\t\1\2y{\7\t\2\2zw\3\2\2\2zy"+
		"\3\2\2\2{|\3\2\2\2|}\5\22\n\2}~\7\r\2\2~\u0082\5\22\n\2\177\u0080\7\f"+
		"\2\2\u0080\u0083\b\t\1\2\u0081\u0083\7\n\2\2\u0082\177\3\2\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\t\1\2\u0085\21\3\2\2\2\u0086"+
		"\u0087\5\30\r\2\u0087\u0088\b\n\1\2\u0088\u008d\3\2\2\2\u0089\u008a\5"+
		"\26\f\2\u008a\u008b\b\n\1\2\u008b\u008d\3\2\2\2\u008c\u0086\3\2\2\2\u008c"+
		"\u0089\3\2\2\2\u008d\23\3\2\2\2\u008e\u008f\7\13\2\2\u008f\u0090\5\22"+
		"\n\2\u0090\u0096\b\13\1\2\u0091\u0092\5\22\n\2\u0092\u0093\b\13\1\2\u0093"+
		"\u0095\3\2\2\2\u0094\u0091\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"\u009a\7\f\2\2\u009a\u009b\b\13\1\2\u009b\25\3\2\2\2\u009c\u009d\7\4\2"+
		"\2\u009d\u00a1\b\f\1\2\u009e\u009f\7\3\2\2\u009f\u00a1\b\f\1\2\u00a0\u009c"+
		"\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\27\3\2\2\2\u00a2\u00a3\7\5\2\2\u00a3"+
		"\u00a7\b\r\1\2\u00a4\u00a5\7\6\2\2\u00a5\u00a7\b\r\1\2\u00a6\u00a2\3\2"+
		"\2\2\u00a6\u00a4\3\2\2\2\u00a7\31\3\2\2\2\u00a8\u00aa\7\26\2\2\u00a9\u00ab"+
		"\7\3\2\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\u00ad\b\16\1\2\u00ad\33\3\2\2\2\u00ae\u00b0\7\26\2\2\u00af\u00b1\7\3"+
		"\2\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b3\b\17\1\2\u00b3\35\3\2\2\2\25%\60;BINTWgnpz\u0082\u008c\u0096\u00a0"+
		"\u00a6\u00aa\u00b0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}