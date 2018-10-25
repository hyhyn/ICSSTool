// Generated from /Users/h.hnguyen/Desktop/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PIXELSIZE=1, PERCENTAGE=2, SCALAR=3, COLOR=4, ID_IDENT=5, CLASS_IDENT=6, 
		LOWER_IDENT=7, CAPITAL_IDENT=8, WS=9, OPEN_BRACE=10, CLOSE_BRACE=11, SEMICOLON=12, 
		COLON=13, PLUS=14, MIN=15, MUL=16, ASSIGNMENT_OPERATOR=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
		"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
		"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "'{'", "'}'", 
		"';'", "':'", "'+'", "'-'", "'*'", "':='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
		"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
		"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
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


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\6\2\'\n\2\r\2\16\2(\3\2\3\2\3\2\3\3\6\3/\n\3\r\3\16\3\60\3\3\3\3"+
		"\3\4\6\4\66\n\4\r\4\16\4\67\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\6"+
		"\6D\n\6\r\6\16\6E\3\7\3\7\6\7J\n\7\r\7\16\7K\3\b\6\bO\n\b\r\b\16\bP\3"+
		"\t\3\t\7\tU\n\t\f\t\16\tX\13\t\3\n\6\n[\n\n\r\n\16\n\\\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23\3\2\b\3\2\62;\4\2\62;ch\5\2//\62;c|\3\2C\\"+
		"\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\2x\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3&\3\2\2\2\5.\3\2\2\2\7\65\3"+
		"\2\2\2\t9\3\2\2\2\13A\3\2\2\2\rG\3\2\2\2\17N\3\2\2\2\21R\3\2\2\2\23Z\3"+
		"\2\2\2\25`\3\2\2\2\27b\3\2\2\2\31d\3\2\2\2\33f\3\2\2\2\35h\3\2\2\2\37"+
		"j\3\2\2\2!l\3\2\2\2#n\3\2\2\2%\'\t\2\2\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2"+
		"\2()\3\2\2\2)*\3\2\2\2*+\7r\2\2+,\7z\2\2,\4\3\2\2\2-/\t\2\2\2.-\3\2\2"+
		"\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62\63\7\'\2\2\63"+
		"\6\3\2\2\2\64\66\t\2\2\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\67"+
		"8\3\2\2\28\b\3\2\2\29:\7%\2\2:;\t\3\2\2;<\t\3\2\2<=\t\3\2\2=>\t\3\2\2"+
		">?\t\3\2\2?@\t\3\2\2@\n\3\2\2\2AC\7%\2\2BD\t\4\2\2CB\3\2\2\2DE\3\2\2\2"+
		"EC\3\2\2\2EF\3\2\2\2F\f\3\2\2\2GI\7\60\2\2HJ\t\4\2\2IH\3\2\2\2JK\3\2\2"+
		"\2KI\3\2\2\2KL\3\2\2\2L\16\3\2\2\2MO\t\4\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2"+
		"\2\2PQ\3\2\2\2Q\20\3\2\2\2RV\t\5\2\2SU\t\6\2\2TS\3\2\2\2UX\3\2\2\2VT\3"+
		"\2\2\2VW\3\2\2\2W\22\3\2\2\2XV\3\2\2\2Y[\t\7\2\2ZY\3\2\2\2[\\\3\2\2\2"+
		"\\Z\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\b\n\2\2_\24\3\2\2\2`a\7}\2\2a\26\3"+
		"\2\2\2bc\7\177\2\2c\30\3\2\2\2de\7=\2\2e\32\3\2\2\2fg\7<\2\2g\34\3\2\2"+
		"\2hi\7-\2\2i\36\3\2\2\2jk\7/\2\2k \3\2\2\2lm\7,\2\2m\"\3\2\2\2no\7<\2"+
		"\2op\7?\2\2p$\3\2\2\2\13\2(\60\67EKPV\\\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}