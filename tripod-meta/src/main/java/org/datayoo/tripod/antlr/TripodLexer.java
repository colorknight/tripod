// Generated from org/datayoo/tripod/antlr/TripodLexer.g4 by ANTLR 4.7.1
package org.datayoo.tripod.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TripodLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMBER=1, TERM=2, IDENTIFIER=3, Q_PHRASE=4, DQ_PHRASE=5, LPAREN=6, RPAREN=7, 
		LBRACE=8, RBRACE=9, LBRACK=10, RBRACK=11, COMMA=12, NOT=13, COLON=14, 
		AND=15, OR=16, MUST=17, MINUS=18, STAR=19, CARET=20, SWANGDASH=21, QMARK=22, 
		WS=23, COMMENT=24, LINE_COMMENT=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NUMBER", "TERM", "IDENTIFIER", "Q_PHRASE", "DQ_PHRASE", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "LBRACK", "RBRACK", "COMMA", "NOT", "COLON", "AND", 
		"OR", "MUST", "MINUS", "STAR", "CARET", "SWANGDASH", "QMARK", "WS", "COMMENT", 
		"LINE_COMMENT", "EscapeSequence", "Digits", "TermChar", "LetterOrDigit", 
		"Letter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, "'('", "')'", "'{'", "'}'", "'['", 
		"']'", "','", "'!'", "':'", null, null, "'+'", "'-'", "'*'", "'^'", "'~'", 
		"'?'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NUMBER", "TERM", "IDENTIFIER", "Q_PHRASE", "DQ_PHRASE", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "COMMA", "NOT", "COLON", 
		"AND", "OR", "MUST", "MINUS", "STAR", "CARET", "SWANGDASH", "QMARK", "WS", 
		"COMMENT", "LINE_COMMENT"
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


	public TripodLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TripodLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00de\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\5\2C\n\2\3\3\7\3F\n\3\f\3\16\3I\13\3\3\3\3\3\7\3M\n\3\f\3\16"+
		"\3P\13\3\3\4\3\4\7\4T\n\4\f\4\16\4W\13\4\3\4\3\4\3\4\7\4\\\n\4\f\4\16"+
		"\4_\13\4\7\4a\n\4\f\4\16\4d\13\4\3\5\3\5\3\5\3\5\3\5\7\5k\n\5\f\5\16\5"+
		"n\13\5\3\5\3\5\3\6\3\6\3\6\3\6\7\6v\n\6\f\6\16\6y\13\6\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\20\5\20\u0092\n\20\3\21\3\21\3\21\5\21\u0097\n\21\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\6\30\u00a6"+
		"\n\30\r\30\16\30\u00a7\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00b0\n\31\f"+
		"\31\16\31\u00b3\13\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32"+
		"\u00be\n\32\f\32\16\32\u00c1\13\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34"+
		"\7\34\u00ca\n\34\f\34\16\34\u00cd\13\34\5\34\u00cf\n\34\3\35\3\35\5\35"+
		"\u00d3\n\35\3\36\3\36\5\36\u00d7\n\36\3\37\3\37\3\37\3\37\5\37\u00dd\n"+
		"\37\3\u00b1\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\2\67"+
		"\29\2;\2=\2\3\2\r\6\2\f\f\17\17))^^\6\2\f\f\17\17$$^^\5\2\13\f\16\17\""+
		"\"\4\2\f\f\17\17\n\2$$))^^ddhhppttvv\3\2\62;\4\2,,AA\6\2&&C\\aac|\4\2"+
		"\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\2\u00ef\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\3?\3\2\2\2\5G\3\2\2\2\7Q\3\2\2\2\te\3\2\2\2\13q\3\2"+
		"\2\2\r|\3\2\2\2\17~\3\2\2\2\21\u0080\3\2\2\2\23\u0082\3\2\2\2\25\u0084"+
		"\3\2\2\2\27\u0086\3\2\2\2\31\u0088\3\2\2\2\33\u008a\3\2\2\2\35\u008c\3"+
		"\2\2\2\37\u0091\3\2\2\2!\u0096\3\2\2\2#\u0098\3\2\2\2%\u009a\3\2\2\2\'"+
		"\u009c\3\2\2\2)\u009e\3\2\2\2+\u00a0\3\2\2\2-\u00a2\3\2\2\2/\u00a5\3\2"+
		"\2\2\61\u00ab\3\2\2\2\63\u00b9\3\2\2\2\65\u00c4\3\2\2\2\67\u00c7\3\2\2"+
		"\29\u00d2\3\2\2\2;\u00d6\3\2\2\2=\u00dc\3\2\2\2?B\5\67\34\2@A\7\60\2\2"+
		"AC\5\67\34\2B@\3\2\2\2BC\3\2\2\2C\4\3\2\2\2DF\59\35\2ED\3\2\2\2FI\3\2"+
		"\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JN\5;\36\2KM\59\35\2LK\3\2"+
		"\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\6\3\2\2\2PN\3\2\2\2QU\5=\37\2RT\5"+
		";\36\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2Vb\3\2\2\2WU\3\2\2\2XY\7"+
		"\60\2\2Y]\5=\37\2Z\\\5;\36\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2"+
		"^a\3\2\2\2_]\3\2\2\2`X\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\b\3\2\2"+
		"\2db\3\2\2\2el\7)\2\2fk\n\2\2\2gk\5\65\33\2hi\7^\2\2ik\7)\2\2jf\3\2\2"+
		"\2jg\3\2\2\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2"+
		"\2op\7)\2\2p\n\3\2\2\2qw\7$\2\2rv\n\3\2\2sv\5\65\33\2tv\7$\2\2ur\3\2\2"+
		"\2us\3\2\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2"+
		"\2z{\7$\2\2{\f\3\2\2\2|}\7*\2\2}\16\3\2\2\2~\177\7+\2\2\177\20\3\2\2\2"+
		"\u0080\u0081\7}\2\2\u0081\22\3\2\2\2\u0082\u0083\7\177\2\2\u0083\24\3"+
		"\2\2\2\u0084\u0085\7]\2\2\u0085\26\3\2\2\2\u0086\u0087\7_\2\2\u0087\30"+
		"\3\2\2\2\u0088\u0089\7.\2\2\u0089\32\3\2\2\2\u008a\u008b\7#\2\2\u008b"+
		"\34\3\2\2\2\u008c\u008d\7<\2\2\u008d\36\3\2\2\2\u008e\u0092\7(\2\2\u008f"+
		"\u0090\7(\2\2\u0090\u0092\7(\2\2\u0091\u008e\3\2\2\2\u0091\u008f\3\2\2"+
		"\2\u0092 \3\2\2\2\u0093\u0097\7~\2\2\u0094\u0095\7~\2\2\u0095\u0097\7"+
		"~\2\2\u0096\u0093\3\2\2\2\u0096\u0094\3\2\2\2\u0097\"\3\2\2\2\u0098\u0099"+
		"\7-\2\2\u0099$\3\2\2\2\u009a\u009b\7/\2\2\u009b&\3\2\2\2\u009c\u009d\7"+
		",\2\2\u009d(\3\2\2\2\u009e\u009f\7`\2\2\u009f*\3\2\2\2\u00a0\u00a1\7\u0080"+
		"\2\2\u00a1,\3\2\2\2\u00a2\u00a3\7A\2\2\u00a3.\3\2\2\2\u00a4\u00a6\t\4"+
		"\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\30\2\2\u00aa\60\3\2\2"+
		"\2\u00ab\u00ac\7\61\2\2\u00ac\u00ad\7,\2\2\u00ad\u00b1\3\2\2\2\u00ae\u00b0"+
		"\13\2\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00b2\3\2\2\2"+
		"\u00b1\u00af\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5"+
		"\7,\2\2\u00b5\u00b6\7\61\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\b\31\2\2"+
		"\u00b8\62\3\2\2\2\u00b9\u00ba\7\61\2\2\u00ba\u00bb\7\61\2\2\u00bb\u00bf"+
		"\3\2\2\2\u00bc\u00be\n\5\2\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\u00c3\b\32\2\2\u00c3\64\3\2\2\2\u00c4\u00c5\7^\2\2\u00c5\u00c6"+
		"\t\6\2\2\u00c6\66\3\2\2\2\u00c7\u00ce\t\7\2\2\u00c8\u00ca\t\7\2\2\u00c9"+
		"\u00c8\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2"+
		"\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cb\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf8\3\2\2\2\u00d0\u00d3\5;\36\2\u00d1\u00d3\t\b\2\2"+
		"\u00d2\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3:\3\2\2\2\u00d4\u00d7\5"+
		"=\37\2\u00d5\u00d7\t\7\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7"+
		"<\3\2\2\2\u00d8\u00dd\t\t\2\2\u00d9\u00dd\n\n\2\2\u00da\u00db\t\13\2\2"+
		"\u00db\u00dd\t\f\2\2\u00dc\u00d8\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dc\u00da"+
		"\3\2\2\2\u00dd>\3\2\2\2\27\2BGNU]bjluw\u0091\u0096\u00a7\u00b1\u00bf\u00cb"+
		"\u00ce\u00d2\u00d6\u00dc\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}