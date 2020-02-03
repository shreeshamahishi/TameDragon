// $ANTLR 3.5.2 llvmGrammar.g 2020-02-03 11:59:33
 package org.tamedragon.common.llvmir.utils; 
                import org.tamedragon.common.llvmir.types.*;
                import org.tamedragon.common.llvmir.semanticanalysis.*;
               

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class llvmGrammarLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int T__63=63;
	public static final int T__64=64;
	public static final int T__65=65;
	public static final int T__66=66;
	public static final int T__67=67;
	public static final int T__68=68;
	public static final int T__69=69;
	public static final int T__70=70;
	public static final int T__71=71;
	public static final int T__72=72;
	public static final int T__73=73;
	public static final int T__74=74;
	public static final int T__75=75;
	public static final int T__76=76;
	public static final int T__77=77;
	public static final int T__78=78;
	public static final int T__79=79;
	public static final int T__80=80;
	public static final int T__81=81;
	public static final int T__82=82;
	public static final int T__83=83;
	public static final int T__84=84;
	public static final int T__85=85;
	public static final int ALIGN=4;
	public static final int ARGUMENT_ATTRIBUTE=5;
	public static final int ATOMIC_ORDERING=6;
	public static final int BIN_OPR_FLAG=7;
	public static final int BIN_OPR_STR=8;
	public static final int CALLING_CONV=9;
	public static final int CAST_TYPE=10;
	public static final int COMMA=11;
	public static final int CONDITION=12;
	public static final int DECIMAL_LITERAL=13;
	public static final int DOT=14;
	public static final int END_ANGULAR=15;
	public static final int END_CURLY=16;
	public static final int END_PARANTHESIS=17;
	public static final int END_SQUARE_BR=18;
	public static final int EQUAL=19;
	public static final int EscapeSequence=20;
	public static final int Exponent=21;
	public static final int FALSE=22;
	public static final int FLOATING_LITERAL=23;
	public static final int FUNCTION_ATTRIBUTE=24;
	public static final int FloatTypeSuffix=25;
	public static final int GLOBAL_PREFIX=26;
	public static final int HEX_LITERAL=27;
	public static final int HexDigit=28;
	public static final int ID=29;
	public static final int IntegerTypeSuffix=30;
	public static final int LETTER=31;
	public static final int LINE_COMMENT=32;
	public static final int LINKAGE_TYPE=33;
	public static final int LOCAL_PREFIX=34;
	public static final int MUL_OPERATOR=35;
	public static final int NULL_CHAR=36;
	public static final int NUMBER=37;
	public static final int OCTAL_LITERAL=38;
	public static final int OctalEscape=39;
	public static final int PARAMETER_ATTRIBUTE=40;
	public static final int PHI=41;
	public static final int PRIMITIVE_DATA_TYPE=42;
	public static final int RETURN_ATTR=43;
	public static final int STAR=44;
	public static final int START_ANGULAR=45;
	public static final int START_CURLY=46;
	public static final int START_PARANTHESIS=47;
	public static final int START_SQUARE_BR=48;
	public static final int STRING_LITERAL=49;
	public static final int TRUE=50;
	public static final int UNREACHABLE=51;
	public static final int WHITESPACE=52;
	public static final int XOR=53;
	public static final int ZEROINITIALIZER=54;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public llvmGrammarLexer() {} 
	public llvmGrammarLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public llvmGrammarLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "llvmGrammar.g"; }

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:12:7: ( ' \"' )
			// llvmGrammar.g:12:9: ' \"'
			{
			match(" \""); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:13:7: ( ' alloca ' )
			// llvmGrammar.g:13:9: ' alloca '
			{
			match(" alloca "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:14:7: ( '\"' )
			// llvmGrammar.g:14:9: '\"'
			{
			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:15:7: ( '-' )
			// llvmGrammar.g:15:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:16:7: ( '-p:' )
			// llvmGrammar.g:16:9: '-p:'
			{
			match("-p:"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:17:7: ( '...' )
			// llvmGrammar.g:17:9: '...'
			{
			match("..."); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:18:7: ( ':' )
			// llvmGrammar.g:18:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:19:7: ( 'S32\"' )
			// llvmGrammar.g:19:9: 'S32\"'
			{
			match("S32\""); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:20:7: ( 'br' )
			// llvmGrammar.g:20:9: 'br'
			{
			match("br"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:21:7: ( 'call' )
			// llvmGrammar.g:21:9: 'call'
			{
			match("call"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:22:7: ( 'constant' )
			// llvmGrammar.g:22:9: 'constant'
			{
			match("constant"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:23:7: ( 'declare' )
			// llvmGrammar.g:23:9: 'declare'
			{
			match("declare"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:24:7: ( 'define' )
			// llvmGrammar.g:24:9: 'define'
			{
			match("define"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:25:7: ( 'fcmp' )
			// llvmGrammar.g:25:9: 'fcmp'
			{
			match("fcmp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:26:7: ( 'getelementptr' )
			// llvmGrammar.g:26:9: 'getelementptr'
			{
			match("getelementptr"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:27:7: ( 'global' )
			// llvmGrammar.g:27:9: 'global'
			{
			match("global"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:28:7: ( 'icmp' )
			// llvmGrammar.g:28:9: 'icmp'
			{
			match("icmp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:29:7: ( 'inbounds' )
			// llvmGrammar.g:29:9: 'inbounds'
			{
			match("inbounds"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:30:7: ( 'label' )
			// llvmGrammar.g:30:9: 'label'
			{
			match("label"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:31:7: ( 'load' )
			// llvmGrammar.g:31:9: 'load'
			{
			match("load"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:32:7: ( 'ret' )
			// llvmGrammar.g:32:9: 'ret'
			{
			match("ret"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:33:7: ( 'select' )
			// llvmGrammar.g:33:9: 'select'
			{
			match("select"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:34:7: ( 'store' )
			// llvmGrammar.g:34:9: 'store'
			{
			match("store"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:35:7: ( 'switch' )
			// llvmGrammar.g:35:9: 'switch'
			{
			match("switch"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:36:7: ( 'tail' )
			// llvmGrammar.g:36:9: 'tail'
			{
			match("tail"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:37:7: ( 'target datalayout' )
			// llvmGrammar.g:37:9: 'target datalayout'
			{
			match("target datalayout"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:38:7: ( 'to' )
			// llvmGrammar.g:38:9: 'to'
			{
			match("to"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:39:7: ( 'type {' )
			// llvmGrammar.g:39:9: 'type {'
			{
			match("type {"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:40:7: ( 'undef' )
			// llvmGrammar.g:40:9: 'undef'
			{
			match("undef"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:41:7: ( 'unnamed_addr' )
			// llvmGrammar.g:41:9: 'unnamed_addr'
			{
			match("unnamed_addr"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:42:7: ( 'volatile' )
			// llvmGrammar.g:42:9: 'volatile'
			{
			match("volatile"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__85"

	// $ANTLR start "XOR"
	public final void mXOR() throws RecognitionException {
		try {
			// llvmGrammar.g:341:5: ( 'xor' )
			// llvmGrammar.g:341:7: 'xor'
			{
			match("xor"); 

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "XOR"

	// $ANTLR start "PHI"
	public final void mPHI() throws RecognitionException {
		try {
			int _type = PHI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:341:5: ( 'phi' )
			// llvmGrammar.g:341:7: 'phi'
			{
			match("phi"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PHI"

	// $ANTLR start "UNREACHABLE"
	public final void mUNREACHABLE() throws RecognitionException {
		try {
			int _type = UNREACHABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:343:13: ( 'unreachable' )
			// llvmGrammar.g:343:15: 'unreachable'
			{
			match("unreachable"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNREACHABLE"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:344:6: ( 'true' )
			// llvmGrammar.g:344:8: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:345:7: ( 'false' )
			// llvmGrammar.g:345:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "ALIGN"
	public final void mALIGN() throws RecognitionException {
		try {
			int _type = ALIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:346:7: ( ' align ' )
			// llvmGrammar.g:346:9: ' align '
			{
			match(" align "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALIGN"

	// $ANTLR start "LINKAGE_TYPE"
	public final void mLINKAGE_TYPE() throws RecognitionException {
		try {
			int _type = LINKAGE_TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:347:14: ( 'private' | 'common' | 'extern' | 'internal' | 'external' )
			int alt1=5;
			switch ( input.LA(1) ) {
			case 'p':
				{
				alt1=1;
				}
				break;
			case 'c':
				{
				alt1=2;
				}
				break;
			case 'e':
				{
				int LA1_3 = input.LA(2);
				if ( (LA1_3=='x') ) {
					int LA1_5 = input.LA(3);
					if ( (LA1_5=='t') ) {
						int LA1_6 = input.LA(4);
						if ( (LA1_6=='e') ) {
							int LA1_7 = input.LA(5);
							if ( (LA1_7=='r') ) {
								int LA1_8 = input.LA(6);
								if ( (LA1_8=='n') ) {
									int LA1_9 = input.LA(7);
									if ( (LA1_9=='a') ) {
										alt1=5;
									}

									else {
										alt1=3;
									}

								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 1, 8, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 1, 7, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'i':
				{
				alt1=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// llvmGrammar.g:347:16: 'private'
					{
					match("private"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:347:26: 'common'
					{
					match("common"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:347:35: 'extern'
					{
					match("extern"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:347:44: 'internal'
					{
					match("internal"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:347:55: 'external'
					{
					match("external"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LINKAGE_TYPE"

	// $ANTLR start "RETURN_ATTR"
	public final void mRETURN_ATTR() throws RecognitionException {
		try {
			int _type = RETURN_ATTR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:348:13: ( 'noalias' )
			// llvmGrammar.g:348:15: 'noalias'
			{
			match("noalias"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN_ATTR"

	// $ANTLR start "ZEROINITIALIZER"
	public final void mZEROINITIALIZER() throws RecognitionException {
		try {
			int _type = ZEROINITIALIZER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:349:17: ( 'zeroinitializer' )
			// llvmGrammar.g:349:19: 'zeroinitializer'
			{
			match("zeroinitializer"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ZEROINITIALIZER"

	// $ANTLR start "CAST_TYPE"
	public final void mCAST_TYPE() throws RecognitionException {
		try {
			int _type = CAST_TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:350:11: ( 'trunc' | 'zext' | 'sext' | 'fptrunc' | 'fpext' | 'fptoui' | 'fptosi' | 'uitofp' | 'sitofp' | 'ptrtoint' | 'inttoptr' | 'bitcast' )
			int alt2=12;
			switch ( input.LA(1) ) {
			case 't':
				{
				alt2=1;
				}
				break;
			case 'z':
				{
				alt2=2;
				}
				break;
			case 's':
				{
				int LA2_3 = input.LA(2);
				if ( (LA2_3=='e') ) {
					alt2=3;
				}
				else if ( (LA2_3=='i') ) {
					alt2=9;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'f':
				{
				int LA2_4 = input.LA(2);
				if ( (LA2_4=='p') ) {
					int LA2_11 = input.LA(3);
					if ( (LA2_11=='t') ) {
						int LA2_12 = input.LA(4);
						if ( (LA2_12=='r') ) {
							alt2=4;
						}
						else if ( (LA2_12=='o') ) {
							int LA2_15 = input.LA(5);
							if ( (LA2_15=='u') ) {
								alt2=6;
							}
							else if ( (LA2_15=='s') ) {
								alt2=7;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 2, 15, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 2, 12, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA2_11=='e') ) {
						alt2=5;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 2, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'u':
				{
				alt2=8;
				}
				break;
			case 'p':
				{
				alt2=10;
				}
				break;
			case 'i':
				{
				alt2=11;
				}
				break;
			case 'b':
				{
				alt2=12;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// llvmGrammar.g:350:13: 'trunc'
					{
					match("trunc"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:350:23: 'zext'
					{
					match("zext"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:350:32: 'sext'
					{
					match("sext"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:350:41: 'fptrunc'
					{
					match("fptrunc"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:350:53: 'fpext'
					{
					match("fpext"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:350:63: 'fptoui'
					{
					match("fptoui"); 

					}
					break;
				case 7 :
					// llvmGrammar.g:350:74: 'fptosi'
					{
					match("fptosi"); 

					}
					break;
				case 8 :
					// llvmGrammar.g:350:85: 'uitofp'
					{
					match("uitofp"); 

					}
					break;
				case 9 :
					// llvmGrammar.g:350:96: 'sitofp'
					{
					match("sitofp"); 

					}
					break;
				case 10 :
					// llvmGrammar.g:350:106: 'ptrtoint'
					{
					match("ptrtoint"); 

					}
					break;
				case 11 :
					// llvmGrammar.g:350:119: 'inttoptr'
					{
					match("inttoptr"); 

					}
					break;
				case 12 :
					// llvmGrammar.g:350:132: 'bitcast'
					{
					match("bitcast"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CAST_TYPE"

	// $ANTLR start "ARGUMENT_ATTRIBUTE"
	public final void mARGUMENT_ATTRIBUTE() throws RecognitionException {
		try {
			int _type = ARGUMENT_ATTRIBUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:351:20: ( 'byval' | 'nest' | 'structret' | 'nocapture' )
			int alt3=4;
			switch ( input.LA(1) ) {
			case 'b':
				{
				alt3=1;
				}
				break;
			case 'n':
				{
				int LA3_2 = input.LA(2);
				if ( (LA3_2=='e') ) {
					alt3=2;
				}
				else if ( (LA3_2=='o') ) {
					alt3=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 's':
				{
				alt3=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// llvmGrammar.g:351:21: 'byval'
					{
					match("byval"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:351:30: 'nest'
					{
					match("nest"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:351:38: 'structret'
					{
					match("structret"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:351:50: 'nocapture'
					{
					match("nocapture"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARGUMENT_ATTRIBUTE"

	// $ANTLR start "PARAMETER_ATTRIBUTE"
	public final void mPARAMETER_ATTRIBUTE() throws RecognitionException {
		try {
			int _type = PARAMETER_ATTRIBUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:352:21: ( 'zeroext' | 'signext' | 'inreg' | 'byval' | 'sret' | 'nocapture' | 'nest' )
			int alt4=7;
			switch ( input.LA(1) ) {
			case 'z':
				{
				alt4=1;
				}
				break;
			case 's':
				{
				int LA4_2 = input.LA(2);
				if ( (LA4_2=='i') ) {
					alt4=2;
				}
				else if ( (LA4_2=='r') ) {
					alt4=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'i':
				{
				alt4=3;
				}
				break;
			case 'b':
				{
				alt4=4;
				}
				break;
			case 'n':
				{
				int LA4_5 = input.LA(2);
				if ( (LA4_5=='o') ) {
					alt4=6;
				}
				else if ( (LA4_5=='e') ) {
					alt4=7;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// llvmGrammar.g:352:23: 'zeroext'
					{
					match("zeroext"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:352:35: 'signext'
					{
					match("signext"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:352:47: 'inreg'
					{
					match("inreg"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:352:57: 'byval'
					{
					match("byval"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:352:67: 'sret'
					{
					match("sret"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:352:76: 'nocapture'
					{
					match("nocapture"); 

					}
					break;
				case 7 :
					// llvmGrammar.g:352:90: 'nest'
					{
					match("nest"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARAMETER_ATTRIBUTE"

	// $ANTLR start "FUNCTION_ATTRIBUTE"
	public final void mFUNCTION_ATTRIBUTE() throws RecognitionException {
		try {
			int _type = FUNCTION_ATTRIBUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:353:20: ( 'nounwind' | 'uwtable' | 'noreturn' | 'readonly' | 'ssp' | 'optsize' )
			int alt5=6;
			switch ( input.LA(1) ) {
			case 'n':
				{
				int LA5_1 = input.LA(2);
				if ( (LA5_1=='o') ) {
					int LA5_6 = input.LA(3);
					if ( (LA5_6=='u') ) {
						alt5=1;
					}
					else if ( (LA5_6=='r') ) {
						alt5=3;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 5, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'u':
				{
				alt5=2;
				}
				break;
			case 'r':
				{
				alt5=4;
				}
				break;
			case 's':
				{
				alt5=5;
				}
				break;
			case 'o':
				{
				alt5=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// llvmGrammar.g:353:22: 'nounwind'
					{
					match("nounwind"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:353:35: 'uwtable'
					{
					match("uwtable"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:353:46: 'noreturn'
					{
					match("noreturn"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:353:59: 'readonly'
					{
					match("readonly"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:353:72: 'ssp'
					{
					match("ssp"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:353:79: 'optsize'
					{
					match("optsize"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FUNCTION_ATTRIBUTE"

	// $ANTLR start "CALLING_CONV"
	public final void mCALLING_CONV() throws RecognitionException {
		try {
			int _type = CALLING_CONV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:354:14: ( 'ccc' | 'fastcc' | 'coldcc' | 'cc 10' | 'cc 11' )
			int alt6=5;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='c') ) {
				int LA6_1 = input.LA(2);
				if ( (LA6_1=='c') ) {
					int LA6_3 = input.LA(3);
					if ( (LA6_3=='c') ) {
						alt6=1;
					}
					else if ( (LA6_3==' ') ) {
						int LA6_6 = input.LA(4);
						if ( (LA6_6=='1') ) {
							int LA6_7 = input.LA(5);
							if ( (LA6_7=='0') ) {
								alt6=4;
							}
							else if ( (LA6_7=='1') ) {
								alt6=5;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 6, 7, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 6, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 6, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA6_1=='o') ) {
					alt6=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 6, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA6_0=='f') ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// llvmGrammar.g:354:16: 'ccc'
					{
					match("ccc"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:354:24: 'fastcc'
					{
					match("fastcc"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:354:35: 'coldcc'
					{
					match("coldcc"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:354:46: 'cc 10'
					{
					match("cc 10"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:354:56: 'cc 11'
					{
					match("cc 11"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CALLING_CONV"

	// $ANTLR start "CONDITION"
	public final void mCONDITION() throws RecognitionException {
		try {
			int _type = CONDITION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:355:11: ( 'eq' | 'ne' | 'ugt' | 'uge' | 'ult' | 'ule' | 'sgt' | 'sge' | 'slt' | 'sle' | 'olt' | 'ogt' | 'oeq' | 'one' | 'oge' )
			int alt7=15;
			switch ( input.LA(1) ) {
			case 'e':
				{
				alt7=1;
				}
				break;
			case 'n':
				{
				alt7=2;
				}
				break;
			case 'u':
				{
				int LA7_3 = input.LA(2);
				if ( (LA7_3=='g') ) {
					int LA7_6 = input.LA(3);
					if ( (LA7_6=='t') ) {
						alt7=3;
					}
					else if ( (LA7_6=='e') ) {
						alt7=4;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA7_3=='l') ) {
					int LA7_7 = input.LA(3);
					if ( (LA7_7=='t') ) {
						alt7=5;
					}
					else if ( (LA7_7=='e') ) {
						alt7=6;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 's':
				{
				int LA7_4 = input.LA(2);
				if ( (LA7_4=='g') ) {
					int LA7_8 = input.LA(3);
					if ( (LA7_8=='t') ) {
						alt7=7;
					}
					else if ( (LA7_8=='e') ) {
						alt7=8;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA7_4=='l') ) {
					int LA7_9 = input.LA(3);
					if ( (LA7_9=='t') ) {
						alt7=9;
					}
					else if ( (LA7_9=='e') ) {
						alt7=10;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'o':
				{
				switch ( input.LA(2) ) {
				case 'l':
					{
					alt7=11;
					}
					break;
				case 'g':
					{
					int LA7_11 = input.LA(3);
					if ( (LA7_11=='t') ) {
						alt7=12;
					}
					else if ( (LA7_11=='e') ) {
						alt7=15;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case 'e':
					{
					alt7=13;
					}
					break;
				case 'n':
					{
					alt7=14;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// llvmGrammar.g:355:13: 'eq'
					{
					match("eq"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:355:20: 'ne'
					{
					match("ne"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:355:27: 'ugt'
					{
					match("ugt"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:355:35: 'uge'
					{
					match("uge"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:355:43: 'ult'
					{
					match("ult"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:355:51: 'ule'
					{
					match("ule"); 

					}
					break;
				case 7 :
					// llvmGrammar.g:355:59: 'sgt'
					{
					match("sgt"); 

					}
					break;
				case 8 :
					// llvmGrammar.g:355:67: 'sge'
					{
					match("sge"); 

					}
					break;
				case 9 :
					// llvmGrammar.g:355:75: 'slt'
					{
					match("slt"); 

					}
					break;
				case 10 :
					// llvmGrammar.g:355:82: 'sle'
					{
					match("sle"); 

					}
					break;
				case 11 :
					// llvmGrammar.g:355:89: 'olt'
					{
					match("olt"); 

					}
					break;
				case 12 :
					// llvmGrammar.g:355:96: 'ogt'
					{
					match("ogt"); 

					}
					break;
				case 13 :
					// llvmGrammar.g:355:103: 'oeq'
					{
					match("oeq"); 

					}
					break;
				case 14 :
					// llvmGrammar.g:355:110: 'one'
					{
					match("one"); 

					}
					break;
				case 15 :
					// llvmGrammar.g:355:117: 'oge'
					{
					match("oge"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONDITION"

	// $ANTLR start "NULL_CHAR"
	public final void mNULL_CHAR() throws RecognitionException {
		try {
			int _type = NULL_CHAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:356:11: ( '\\\\00' )
			// llvmGrammar.g:356:13: '\\\\00'
			{
			match("\\00"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULL_CHAR"

	// $ANTLR start "BIN_OPR_STR"
	public final void mBIN_OPR_STR() throws RecognitionException {
		try {
			int _type = BIN_OPR_STR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:357:13: ( 'add' | 'fadd' | 'sub' | 'fsub' | 'mul' | 'fmul' | 'udiv' | 'sdiv' | 'fdiv' | 'urem' | 'srem' | 'frem' | ' xor ' | 'shl' | 'shr' | 'lshr' | 'rshr' | 'ashr' | 'and' | 'or' )
			int alt8=20;
			switch ( input.LA(1) ) {
			case 'a':
				{
				switch ( input.LA(2) ) {
				case 'd':
					{
					alt8=1;
					}
					break;
				case 's':
					{
					alt8=18;
					}
					break;
				case 'n':
					{
					alt8=19;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 'f':
				{
				switch ( input.LA(2) ) {
				case 'a':
					{
					alt8=2;
					}
					break;
				case 's':
					{
					alt8=4;
					}
					break;
				case 'm':
					{
					alt8=6;
					}
					break;
				case 'd':
					{
					alt8=9;
					}
					break;
				case 'r':
					{
					alt8=12;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 's':
				{
				switch ( input.LA(2) ) {
				case 'u':
					{
					alt8=3;
					}
					break;
				case 'd':
					{
					alt8=8;
					}
					break;
				case 'r':
					{
					alt8=11;
					}
					break;
				case 'h':
					{
					int LA8_21 = input.LA(3);
					if ( (LA8_21=='l') ) {
						alt8=14;
					}
					else if ( (LA8_21=='r') ) {
						alt8=15;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 21, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 'm':
				{
				alt8=5;
				}
				break;
			case 'u':
				{
				int LA8_5 = input.LA(2);
				if ( (LA8_5=='d') ) {
					alt8=7;
				}
				else if ( (LA8_5=='r') ) {
					alt8=10;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ' ':
				{
				alt8=13;
				}
				break;
			case 'l':
				{
				alt8=16;
				}
				break;
			case 'r':
				{
				alt8=17;
				}
				break;
			case 'o':
				{
				alt8=20;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// llvmGrammar.g:357:15: 'add'
					{
					match("add"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:357:23: 'fadd'
					{
					match("fadd"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:357:32: 'sub'
					{
					match("sub"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:357:40: 'fsub'
					{
					match("fsub"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:357:48: 'mul'
					{
					match("mul"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:357:56: 'fmul'
					{
					match("fmul"); 

					}
					break;
				case 7 :
					// llvmGrammar.g:357:64: 'udiv'
					{
					match("udiv"); 

					}
					break;
				case 8 :
					// llvmGrammar.g:357:71: 'sdiv'
					{
					match("sdiv"); 

					}
					break;
				case 9 :
					// llvmGrammar.g:357:78: 'fdiv'
					{
					match("fdiv"); 

					}
					break;
				case 10 :
					// llvmGrammar.g:357:86: 'urem'
					{
					match("urem"); 

					}
					break;
				case 11 :
					// llvmGrammar.g:357:93: 'srem'
					{
					match("srem"); 

					}
					break;
				case 12 :
					// llvmGrammar.g:357:100: 'frem'
					{
					match("frem"); 

					}
					break;
				case 13 :
					// llvmGrammar.g:357:109: ' xor '
					{
					match(" xor "); 

					}
					break;
				case 14 :
					// llvmGrammar.g:357:119: 'shl'
					{
					match("shl"); 

					}
					break;
				case 15 :
					// llvmGrammar.g:357:127: 'shr'
					{
					match("shr"); 

					}
					break;
				case 16 :
					// llvmGrammar.g:357:135: 'lshr'
					{
					match("lshr"); 

					}
					break;
				case 17 :
					// llvmGrammar.g:357:142: 'rshr'
					{
					match("rshr"); 

					}
					break;
				case 18 :
					// llvmGrammar.g:357:149: 'ashr'
					{
					match("ashr"); 

					}
					break;
				case 19 :
					// llvmGrammar.g:357:157: 'and'
					{
					match("and"); 

					}
					break;
				case 20 :
					// llvmGrammar.g:357:165: 'or'
					{
					match("or"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BIN_OPR_STR"

	// $ANTLR start "BIN_OPR_FLAG"
	public final void mBIN_OPR_FLAG() throws RecognitionException {
		try {
			int _type = BIN_OPR_FLAG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:358:14: ( 'nsw' | 'nuw' | 'exact' )
			int alt9=3;
			int LA9_0 = input.LA(1);
			if ( (LA9_0=='n') ) {
				int LA9_1 = input.LA(2);
				if ( (LA9_1=='s') ) {
					alt9=1;
				}
				else if ( (LA9_1=='u') ) {
					alt9=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA9_0=='e') ) {
				alt9=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// llvmGrammar.g:358:16: 'nsw'
					{
					match("nsw"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:358:24: 'nuw'
					{
					match("nuw"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:358:32: 'exact'
					{
					match("exact"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BIN_OPR_FLAG"

	// $ANTLR start "ATOMIC_ORDERING"
	public final void mATOMIC_ORDERING() throws RecognitionException {
		try {
			int _type = ATOMIC_ORDERING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:359:17: ( 'unordered' | 'monotonic' | 'acquire' | 'release' | 'acq_rel' | 'seq_cst' )
			int alt10=6;
			switch ( input.LA(1) ) {
			case 'u':
				{
				alt10=1;
				}
				break;
			case 'm':
				{
				alt10=2;
				}
				break;
			case 'a':
				{
				int LA10_3 = input.LA(2);
				if ( (LA10_3=='c') ) {
					int LA10_6 = input.LA(3);
					if ( (LA10_6=='q') ) {
						int LA10_7 = input.LA(4);
						if ( (LA10_7=='u') ) {
							alt10=3;
						}
						else if ( (LA10_7=='_') ) {
							alt10=5;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 10, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 10, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 'r':
				{
				alt10=4;
				}
				break;
			case 's':
				{
				alt10=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// llvmGrammar.g:359:19: 'unordered'
					{
					match("unordered"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:359:31: 'monotonic'
					{
					match("monotonic"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:359:43: 'acquire'
					{
					match("acquire"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:359:53: 'release'
					{
					match("release"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:359:63: 'acq_rel'
					{
					match("acq_rel"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:359:73: 'seq_cst'
					{
					match("seq_cst"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ATOMIC_ORDERING"

	// $ANTLR start "PRIMITIVE_DATA_TYPE"
	public final void mPRIMITIVE_DATA_TYPE() throws RecognitionException {
		try {
			int _type = PRIMITIVE_DATA_TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:361:21: ( ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' ) )
			// llvmGrammar.g:361:23: ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' )
			{
			// llvmGrammar.g:361:23: ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' )
			int alt11=16;
			switch ( input.LA(1) ) {
			case 'i':
				{
				switch ( input.LA(2) ) {
				case '1':
					{
					int LA11_8 = input.LA(3);
					if ( (LA11_8=='6') ) {
						alt11=3;
					}

					else {
						alt11=1;
					}

					}
					break;
				case '8':
					{
					alt11=2;
					}
					break;
				case '3':
					{
					alt11=4;
					}
					break;
				case '6':
					{
					alt11=5;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 'f':
				{
				switch ( input.LA(2) ) {
				case 'l':
					{
					alt11=6;
					}
					break;
				case '3':
					{
					alt11=10;
					}
					break;
				case '6':
					{
					alt11=11;
					}
					break;
				case '8':
					{
					alt11=12;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 'd':
				{
				alt11=7;
				}
				break;
			case 'v':
				{
				switch ( input.LA(2) ) {
				case 'o':
					{
					alt11=8;
					}
					break;
				case '6':
					{
					alt11=13;
					}
					break;
				case '1':
					{
					alt11=14;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 'l':
				{
				alt11=9;
				}
				break;
			case 'a':
				{
				alt11=15;
				}
				break;
			case 'n':
				{
				alt11=16;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// llvmGrammar.g:361:25: 'i1'
					{
					match("i1"); 

					}
					break;
				case 2 :
					// llvmGrammar.g:361:32: 'i8'
					{
					match("i8"); 

					}
					break;
				case 3 :
					// llvmGrammar.g:361:39: 'i16'
					{
					match("i16"); 

					}
					break;
				case 4 :
					// llvmGrammar.g:361:47: 'i32'
					{
					match("i32"); 

					}
					break;
				case 5 :
					// llvmGrammar.g:361:55: 'i64'
					{
					match("i64"); 

					}
					break;
				case 6 :
					// llvmGrammar.g:361:63: 'float'
					{
					match("float"); 

					}
					break;
				case 7 :
					// llvmGrammar.g:361:73: 'double'
					{
					match("double"); 

					}
					break;
				case 8 :
					// llvmGrammar.g:361:84: 'void'
					{
					match("void"); 

					}
					break;
				case 9 :
					// llvmGrammar.g:362:9: 'label'
					{
					match("label"); 

					}
					break;
				case 10 :
					// llvmGrammar.g:362:18: 'f32'
					{
					match("f32"); 

					}
					break;
				case 11 :
					// llvmGrammar.g:362:25: 'f64'
					{
					match("f64"); 

					}
					break;
				case 12 :
					// llvmGrammar.g:362:31: 'f80'
					{
					match("f80"); 

					}
					break;
				case 13 :
					// llvmGrammar.g:362:37: 'v64'
					{
					match("v64"); 

					}
					break;
				case 14 :
					// llvmGrammar.g:362:43: 'v128'
					{
					match("v128"); 

					}
					break;
				case 15 :
					// llvmGrammar.g:362:50: 'a0'
					{
					match("a0"); 

					}
					break;
				case 16 :
					// llvmGrammar.g:362:55: 'n8'
					{
					match("n8"); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIMITIVE_DATA_TYPE"

	// $ANTLR start "EQUAL"
	public final void mEQUAL() throws RecognitionException {
		try {
			int _type = EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:364:7: ( '=' )
			// llvmGrammar.g:364:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:365:7: ( ',' )
			// llvmGrammar.g:365:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "GLOBAL_PREFIX"
	public final void mGLOBAL_PREFIX() throws RecognitionException {
		try {
			int _type = GLOBAL_PREFIX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:366:15: ( '@' )
			// llvmGrammar.g:366:17: '@'
			{
			match('@'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GLOBAL_PREFIX"

	// $ANTLR start "LOCAL_PREFIX"
	public final void mLOCAL_PREFIX() throws RecognitionException {
		try {
			int _type = LOCAL_PREFIX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:367:14: ( '%' )
			// llvmGrammar.g:367:16: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOCAL_PREFIX"

	// $ANTLR start "STAR"
	public final void mSTAR() throws RecognitionException {
		try {
			int _type = STAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:368:6: ( '*' )
			// llvmGrammar.g:368:8: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAR"

	// $ANTLR start "START_PARANTHESIS"
	public final void mSTART_PARANTHESIS() throws RecognitionException {
		try {
			int _type = START_PARANTHESIS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:369:19: ( '(' )
			// llvmGrammar.g:369:21: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "START_PARANTHESIS"

	// $ANTLR start "END_PARANTHESIS"
	public final void mEND_PARANTHESIS() throws RecognitionException {
		try {
			int _type = END_PARANTHESIS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:370:17: ( ')' )
			// llvmGrammar.g:370:19: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "END_PARANTHESIS"

	// $ANTLR start "START_CURLY"
	public final void mSTART_CURLY() throws RecognitionException {
		try {
			int _type = START_CURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:371:13: ( '{' )
			// llvmGrammar.g:371:15: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "START_CURLY"

	// $ANTLR start "END_CURLY"
	public final void mEND_CURLY() throws RecognitionException {
		try {
			int _type = END_CURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:372:11: ( '}' )
			// llvmGrammar.g:372:13: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "END_CURLY"

	// $ANTLR start "START_ANGULAR"
	public final void mSTART_ANGULAR() throws RecognitionException {
		try {
			int _type = START_ANGULAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:373:15: ( '<' )
			// llvmGrammar.g:373:17: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "START_ANGULAR"

	// $ANTLR start "END_ANGULAR"
	public final void mEND_ANGULAR() throws RecognitionException {
		try {
			int _type = END_ANGULAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:374:13: ( '>' )
			// llvmGrammar.g:374:15: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "END_ANGULAR"

	// $ANTLR start "START_SQUARE_BR"
	public final void mSTART_SQUARE_BR() throws RecognitionException {
		try {
			int _type = START_SQUARE_BR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:375:17: ( '[' )
			// llvmGrammar.g:375:19: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "START_SQUARE_BR"

	// $ANTLR start "END_SQUARE_BR"
	public final void mEND_SQUARE_BR() throws RecognitionException {
		try {
			int _type = END_SQUARE_BR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:376:15: ( ']' )
			// llvmGrammar.g:376:17: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "END_SQUARE_BR"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:377:6: ( '.' )
			// llvmGrammar.g:377:8: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "MUL_OPERATOR"
	public final void mMUL_OPERATOR() throws RecognitionException {
		try {
			int _type = MUL_OPERATOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:378:14: ( ' x ' )
			// llvmGrammar.g:378:16: ' x '
			{
			match(" x "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MUL_OPERATOR"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:379:9: ( ( '+' | '-' )? ( '0' .. '9' )+ )
			// llvmGrammar.g:379:11: ( '+' | '-' )? ( '0' .. '9' )+
			{
			// llvmGrammar.g:379:11: ( '+' | '-' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='+'||LA12_0=='-') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// llvmGrammar.g:379:22: ( '0' .. '9' )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "DECIMAL_LITERAL"
	public final void mDECIMAL_LITERAL() throws RecognitionException {
		try {
			int _type = DECIMAL_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:380:17: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
			// llvmGrammar.g:380:19: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
			{
			// llvmGrammar.g:380:19: ( '0' | '1' .. '9' ( '0' .. '9' )* )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0=='0') ) {
				alt15=1;
			}
			else if ( ((LA15_0 >= '1' && LA15_0 <= '9')) ) {
				alt15=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}

			switch (alt15) {
				case 1 :
					// llvmGrammar.g:380:20: '0'
					{
					match('0'); 
					}
					break;
				case 2 :
					// llvmGrammar.g:380:26: '1' .. '9' ( '0' .. '9' )*
					{
					matchRange('1','9'); 
					// llvmGrammar.g:380:35: ( '0' .. '9' )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop14;
						}
					}

					}
					break;

			}

			// llvmGrammar.g:380:46: ( IntegerTypeSuffix )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0=='L'||LA16_0=='U'||LA16_0=='l'||LA16_0=='u') ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// llvmGrammar.g:380:46: IntegerTypeSuffix
					{
					mIntegerTypeSuffix(); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DECIMAL_LITERAL"

	// $ANTLR start "OCTAL_LITERAL"
	public final void mOCTAL_LITERAL() throws RecognitionException {
		try {
			// llvmGrammar.g:384:15: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
			// llvmGrammar.g:384:17: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
			{
			match('0'); 
			// llvmGrammar.g:384:21: ( '0' .. '7' )+
			int cnt17=0;
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( ((LA17_0 >= '0' && LA17_0 <= '7')) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt17 >= 1 ) break loop17;
					EarlyExitException eee = new EarlyExitException(17, input);
					throw eee;
				}
				cnt17++;
			}

			// llvmGrammar.g:384:33: ( IntegerTypeSuffix )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0=='L'||LA18_0=='U'||LA18_0=='l'||LA18_0=='u') ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// llvmGrammar.g:384:33: IntegerTypeSuffix
					{
					mIntegerTypeSuffix(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OCTAL_LITERAL"

	// $ANTLR start "HEX_LITERAL"
	public final void mHEX_LITERAL() throws RecognitionException {
		try {
			// llvmGrammar.g:387:13: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
			// llvmGrammar.g:387:15: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
			{
			match('0'); 
			if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// llvmGrammar.g:387:29: ( HexDigit )+
			int cnt19=0;
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( ((LA19_0 >= '0' && LA19_0 <= '9')||(LA19_0 >= 'A' && LA19_0 <= 'F')||(LA19_0 >= 'a' && LA19_0 <= 'f')) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt19 >= 1 ) break loop19;
					EarlyExitException eee = new EarlyExitException(19, input);
					throw eee;
				}
				cnt19++;
			}

			// llvmGrammar.g:387:39: ( IntegerTypeSuffix )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0=='L'||LA20_0=='U'||LA20_0=='l'||LA20_0=='u') ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// llvmGrammar.g:387:39: IntegerTypeSuffix
					{
					mIntegerTypeSuffix(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEX_LITERAL"

	// $ANTLR start "HexDigit"
	public final void mHexDigit() throws RecognitionException {
		try {
			// llvmGrammar.g:390:10: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
			// llvmGrammar.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexDigit"

	// $ANTLR start "IntegerTypeSuffix"
	public final void mIntegerTypeSuffix() throws RecognitionException {
		try {
			// llvmGrammar.g:394:2: ( ( 'u' | 'U' )? ( 'l' | 'L' ) | ( 'u' | 'U' ) ( 'l' | 'L' )? )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0=='U'||LA23_0=='u') ) {
				int LA23_1 = input.LA(2);
				if ( (LA23_1=='L'||LA23_1=='l') ) {
					alt23=1;
				}

				else {
					alt23=2;
				}

			}
			else if ( (LA23_0=='L'||LA23_0=='l') ) {
				alt23=1;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// llvmGrammar.g:394:4: ( 'u' | 'U' )? ( 'l' | 'L' )
					{
					// llvmGrammar.g:394:4: ( 'u' | 'U' )?
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0=='U'||LA21_0=='u') ) {
						alt21=1;
					}
					switch (alt21) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// llvmGrammar.g:395:4: ( 'u' | 'U' ) ( 'l' | 'L' )?
					{
					if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// llvmGrammar.g:395:15: ( 'l' | 'L' )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0=='L'||LA22_0=='l') ) {
						alt22=1;
					}
					switch (alt22) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IntegerTypeSuffix"

	// $ANTLR start "FLOATING_LITERAL"
	public final void mFLOATING_LITERAL() throws RecognitionException {
		try {
			int _type = FLOATING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:398:5: ( ( '+' | '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix | HEX_LITERAL )
			int alt36=5;
			alt36 = dfa36.predict(input);
			switch (alt36) {
				case 1 :
					// llvmGrammar.g:398:8: ( '+' | '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
					{
					// llvmGrammar.g:398:8: ( '+' | '-' )?
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0=='+'||LA24_0=='-') ) {
						alt24=1;
					}
					switch (alt24) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					// llvmGrammar.g:398:20: ( '0' .. '9' )+
					int cnt25=0;
					loop25:
					while (true) {
						int alt25=2;
						int LA25_0 = input.LA(1);
						if ( ((LA25_0 >= '0' && LA25_0 <= '9')) ) {
							alt25=1;
						}

						switch (alt25) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt25 >= 1 ) break loop25;
							EarlyExitException eee = new EarlyExitException(25, input);
							throw eee;
						}
						cnt25++;
					}

					match('.'); 
					// llvmGrammar.g:398:36: ( '0' .. '9' )*
					loop26:
					while (true) {
						int alt26=2;
						int LA26_0 = input.LA(1);
						if ( ((LA26_0 >= '0' && LA26_0 <= '9')) ) {
							alt26=1;
						}

						switch (alt26) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop26;
						}
					}

					// llvmGrammar.g:398:48: ( Exponent )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0=='E'||LA27_0=='e') ) {
						alt27=1;
					}
					switch (alt27) {
						case 1 :
							// llvmGrammar.g:398:48: Exponent
							{
							mExponent(); 

							}
							break;

					}

					// llvmGrammar.g:398:58: ( FloatTypeSuffix )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0=='D'||LA28_0=='F'||LA28_0=='d'||LA28_0=='f') ) {
						alt28=1;
					}
					switch (alt28) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 2 :
					// llvmGrammar.g:399:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
					{
					match('.'); 
					// llvmGrammar.g:399:13: ( '0' .. '9' )+
					int cnt29=0;
					loop29:
					while (true) {
						int alt29=2;
						int LA29_0 = input.LA(1);
						if ( ((LA29_0 >= '0' && LA29_0 <= '9')) ) {
							alt29=1;
						}

						switch (alt29) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt29 >= 1 ) break loop29;
							EarlyExitException eee = new EarlyExitException(29, input);
							throw eee;
						}
						cnt29++;
					}

					// llvmGrammar.g:399:25: ( Exponent )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0=='E'||LA30_0=='e') ) {
						alt30=1;
					}
					switch (alt30) {
						case 1 :
							// llvmGrammar.g:399:25: Exponent
							{
							mExponent(); 

							}
							break;

					}

					// llvmGrammar.g:399:35: ( FloatTypeSuffix )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0=='D'||LA31_0=='F'||LA31_0=='d'||LA31_0=='f') ) {
						alt31=1;
					}
					switch (alt31) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 3 :
					// llvmGrammar.g:400:9: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
					{
					// llvmGrammar.g:400:9: ( '0' .. '9' )+
					int cnt32=0;
					loop32:
					while (true) {
						int alt32=2;
						int LA32_0 = input.LA(1);
						if ( ((LA32_0 >= '0' && LA32_0 <= '9')) ) {
							alt32=1;
						}

						switch (alt32) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt32 >= 1 ) break loop32;
							EarlyExitException eee = new EarlyExitException(32, input);
							throw eee;
						}
						cnt32++;
					}

					mExponent(); 

					// llvmGrammar.g:400:30: ( FloatTypeSuffix )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0=='D'||LA33_0=='F'||LA33_0=='d'||LA33_0=='f') ) {
						alt33=1;
					}
					switch (alt33) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 4 :
					// llvmGrammar.g:401:9: ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix
					{
					// llvmGrammar.g:401:9: ( '0' .. '9' )+
					int cnt34=0;
					loop34:
					while (true) {
						int alt34=2;
						int LA34_0 = input.LA(1);
						if ( ((LA34_0 >= '0' && LA34_0 <= '9')) ) {
							alt34=1;
						}

						switch (alt34) {
						case 1 :
							// llvmGrammar.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt34 >= 1 ) break loop34;
							EarlyExitException eee = new EarlyExitException(34, input);
							throw eee;
						}
						cnt34++;
					}

					// llvmGrammar.g:401:21: ( Exponent )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0=='E'||LA35_0=='e') ) {
						alt35=1;
					}
					switch (alt35) {
						case 1 :
							// llvmGrammar.g:401:21: Exponent
							{
							mExponent(); 

							}
							break;

					}

					mFloatTypeSuffix(); 

					}
					break;
				case 5 :
					// llvmGrammar.g:402:6: HEX_LITERAL
					{
					mHEX_LITERAL(); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOATING_LITERAL"

	// $ANTLR start "Exponent"
	public final void mExponent() throws RecognitionException {
		try {
			// llvmGrammar.g:407:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
			// llvmGrammar.g:407:13: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
			{
			if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// llvmGrammar.g:407:23: ( '+' | '-' )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0=='+'||LA37_0=='-') ) {
				alt37=1;
			}
			switch (alt37) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// llvmGrammar.g:407:34: ( '0' .. '9' )+
			int cnt38=0;
			loop38:
			while (true) {
				int alt38=2;
				int LA38_0 = input.LA(1);
				if ( ((LA38_0 >= '0' && LA38_0 <= '9')) ) {
					alt38=1;
				}

				switch (alt38) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt38 >= 1 ) break loop38;
					EarlyExitException eee = new EarlyExitException(38, input);
					throw eee;
				}
				cnt38++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Exponent"

	// $ANTLR start "FloatTypeSuffix"
	public final void mFloatTypeSuffix() throws RecognitionException {
		try {
			// llvmGrammar.g:410:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
			// llvmGrammar.g:
			{
			if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FloatTypeSuffix"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:411:16: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
			// llvmGrammar.g:411:19: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
			{
			match('\"'); 
			// llvmGrammar.g:411:23: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
			loop39:
			while (true) {
				int alt39=3;
				int LA39_0 = input.LA(1);
				if ( (LA39_0=='\\') ) {
					alt39=1;
				}
				else if ( ((LA39_0 >= '\u0000' && LA39_0 <= '!')||(LA39_0 >= '#' && LA39_0 <= '[')||(LA39_0 >= ']' && LA39_0 <= '\uFFFF')) ) {
					alt39=2;
				}

				switch (alt39) {
				case 1 :
					// llvmGrammar.g:411:25: EscapeSequence
					{
					mEscapeSequence(); 

					}
					break;
				case 2 :
					// llvmGrammar.g:411:42: ~ ( '\\\\' | '\"' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop39;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "EscapeSequence"
	public final void mEscapeSequence() throws RecognitionException {
		try {
			// llvmGrammar.g:415:16: ( '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OctalEscape )
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0=='\\') ) {
				int LA40_1 = input.LA(2);
				if ( (LA40_1=='\"'||LA40_1=='\''||LA40_1=='\\'||(LA40_1 >= 'a' && LA40_1 <= 'b')||LA40_1=='f'||LA40_1=='n'||LA40_1=='r'||LA40_1=='t') ) {
					alt40=1;
				}
				else if ( ((LA40_1 >= '0' && LA40_1 <= '7')) ) {
					alt40=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 40, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}

			switch (alt40) {
				case 1 :
					// llvmGrammar.g:415:20: '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
					{
					match('\\'); 
					if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||(input.LA(1) >= 'a' && input.LA(1) <= 'b')||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// llvmGrammar.g:416:14: OctalEscape
					{
					mOctalEscape(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EscapeSequence"

	// $ANTLR start "OctalEscape"
	public final void mOctalEscape() throws RecognitionException {
		try {
			// llvmGrammar.g:420:13: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
			int alt41=3;
			int LA41_0 = input.LA(1);
			if ( (LA41_0=='\\') ) {
				int LA41_1 = input.LA(2);
				if ( ((LA41_1 >= '0' && LA41_1 <= '3')) ) {
					int LA41_2 = input.LA(3);
					if ( ((LA41_2 >= '0' && LA41_2 <= '7')) ) {
						int LA41_4 = input.LA(4);
						if ( ((LA41_4 >= '0' && LA41_4 <= '7')) ) {
							alt41=1;
						}

						else {
							alt41=2;
						}

					}

					else {
						alt41=3;
					}

				}
				else if ( ((LA41_1 >= '4' && LA41_1 <= '7')) ) {
					int LA41_3 = input.LA(3);
					if ( ((LA41_3 >= '0' && LA41_3 <= '7')) ) {
						alt41=2;
					}

					else {
						alt41=3;
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 41, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}

			switch (alt41) {
				case 1 :
					// llvmGrammar.g:420:17: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
					{
					match('\\'); 
					if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// llvmGrammar.g:421:11: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
					{
					match('\\'); 
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 3 :
					// llvmGrammar.g:422:11: '\\\\' ( '0' .. '7' )
					{
					match('\\'); 
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OctalEscape"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// llvmGrammar.g:427:2: ( ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )+ )
			// llvmGrammar.g:427:3: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )+
			{
			// llvmGrammar.g:427:3: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )+
			int cnt42=0;
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0=='$'||(LA42_0 >= 'A' && LA42_0 <= 'Z')||LA42_0=='_'||(LA42_0 >= 'a' && LA42_0 <= 'z')) ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt42 >= 1 ) break loop42;
					EarlyExitException eee = new EarlyExitException(42, input);
					throw eee;
				}
				cnt42++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:432:4: ( LETTER ( LETTER | '0' .. '9' | '.' )* )
			// llvmGrammar.g:432:5: LETTER ( LETTER | '0' .. '9' | '.' )*
			{
			mLETTER(); 

			// llvmGrammar.g:432:12: ( LETTER | '0' .. '9' | '.' )*
			loop43:
			while (true) {
				int alt43=4;
				switch ( input.LA(1) ) {
				case '$':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case '_':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					alt43=1;
					}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					{
					alt43=2;
					}
					break;
				case '.':
					{
					alt43=3;
					}
					break;
				}
				switch (alt43) {
				case 1 :
					// llvmGrammar.g:432:13: LETTER
					{
					mLETTER(); 

					}
					break;
				case 2 :
					// llvmGrammar.g:432:20: '0' .. '9'
					{
					matchRange('0','9'); 
					}
					break;
				case 3 :
					// llvmGrammar.g:432:29: '.'
					{
					match('.'); 
					}
					break;

				default :
					break loop43;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "WHITESPACE"
	public final void mWHITESPACE() throws RecognitionException {
		try {
			int _type = WHITESPACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:434:12: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
			// llvmGrammar.g:434:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			{
			// llvmGrammar.g:434:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			int cnt44=0;
			loop44:
			while (true) {
				int alt44=2;
				int LA44_0 = input.LA(1);
				if ( ((LA44_0 >= '\t' && LA44_0 <= '\n')||(LA44_0 >= '\f' && LA44_0 <= '\r')||LA44_0==' ') ) {
					alt44=1;
				}

				switch (alt44) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt44 >= 1 ) break loop44;
					EarlyExitException eee = new EarlyExitException(44, input);
					throw eee;
				}
				cnt44++;
			}

			 _channel = HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHITESPACE"

	// $ANTLR start "LINE_COMMENT"
	public final void mLINE_COMMENT() throws RecognitionException {
		try {
			int _type = LINE_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// llvmGrammar.g:436:14: ( ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// llvmGrammar.g:436:16: ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match(';'); 
			// llvmGrammar.g:436:20: (~ ( '\\n' | '\\r' ) )*
			loop45:
			while (true) {
				int alt45=2;
				int LA45_0 = input.LA(1);
				if ( ((LA45_0 >= '\u0000' && LA45_0 <= '\t')||(LA45_0 >= '\u000B' && LA45_0 <= '\f')||(LA45_0 >= '\u000E' && LA45_0 <= '\uFFFF')) ) {
					alt45=1;
				}

				switch (alt45) {
				case 1 :
					// llvmGrammar.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop45;
				}
			}

			// llvmGrammar.g:436:34: ( '\\r' )?
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0=='\r') ) {
				alt46=1;
			}
			switch (alt46) {
				case 1 :
					// llvmGrammar.g:436:34: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LINE_COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// llvmGrammar.g:1:8: ( T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | PHI | UNREACHABLE | TRUE | FALSE | ALIGN | LINKAGE_TYPE | RETURN_ATTR | ZEROINITIALIZER | CAST_TYPE | ARGUMENT_ATTRIBUTE | PARAMETER_ATTRIBUTE | FUNCTION_ATTRIBUTE | CALLING_CONV | CONDITION | NULL_CHAR | BIN_OPR_STR | BIN_OPR_FLAG | ATOMIC_ORDERING | PRIMITIVE_DATA_TYPE | EQUAL | COMMA | GLOBAL_PREFIX | LOCAL_PREFIX | STAR | START_PARANTHESIS | END_PARANTHESIS | START_CURLY | END_CURLY | START_ANGULAR | END_ANGULAR | START_SQUARE_BR | END_SQUARE_BR | DOT | MUL_OPERATOR | NUMBER | DECIMAL_LITERAL | FLOATING_LITERAL | STRING_LITERAL | ID | WHITESPACE | LINE_COMMENT )
		int alt47=72;
		alt47 = dfa47.predict(input);
		switch (alt47) {
			case 1 :
				// llvmGrammar.g:1:10: T__55
				{
				mT__55(); 

				}
				break;
			case 2 :
				// llvmGrammar.g:1:16: T__56
				{
				mT__56(); 

				}
				break;
			case 3 :
				// llvmGrammar.g:1:22: T__57
				{
				mT__57(); 

				}
				break;
			case 4 :
				// llvmGrammar.g:1:28: T__58
				{
				mT__58(); 

				}
				break;
			case 5 :
				// llvmGrammar.g:1:34: T__59
				{
				mT__59(); 

				}
				break;
			case 6 :
				// llvmGrammar.g:1:40: T__60
				{
				mT__60(); 

				}
				break;
			case 7 :
				// llvmGrammar.g:1:46: T__61
				{
				mT__61(); 

				}
				break;
			case 8 :
				// llvmGrammar.g:1:52: T__62
				{
				mT__62(); 

				}
				break;
			case 9 :
				// llvmGrammar.g:1:58: T__63
				{
				mT__63(); 

				}
				break;
			case 10 :
				// llvmGrammar.g:1:64: T__64
				{
				mT__64(); 

				}
				break;
			case 11 :
				// llvmGrammar.g:1:70: T__65
				{
				mT__65(); 

				}
				break;
			case 12 :
				// llvmGrammar.g:1:76: T__66
				{
				mT__66(); 

				}
				break;
			case 13 :
				// llvmGrammar.g:1:82: T__67
				{
				mT__67(); 

				}
				break;
			case 14 :
				// llvmGrammar.g:1:88: T__68
				{
				mT__68(); 

				}
				break;
			case 15 :
				// llvmGrammar.g:1:94: T__69
				{
				mT__69(); 

				}
				break;
			case 16 :
				// llvmGrammar.g:1:100: T__70
				{
				mT__70(); 

				}
				break;
			case 17 :
				// llvmGrammar.g:1:106: T__71
				{
				mT__71(); 

				}
				break;
			case 18 :
				// llvmGrammar.g:1:112: T__72
				{
				mT__72(); 

				}
				break;
			case 19 :
				// llvmGrammar.g:1:118: T__73
				{
				mT__73(); 

				}
				break;
			case 20 :
				// llvmGrammar.g:1:124: T__74
				{
				mT__74(); 

				}
				break;
			case 21 :
				// llvmGrammar.g:1:130: T__75
				{
				mT__75(); 

				}
				break;
			case 22 :
				// llvmGrammar.g:1:136: T__76
				{
				mT__76(); 

				}
				break;
			case 23 :
				// llvmGrammar.g:1:142: T__77
				{
				mT__77(); 

				}
				break;
			case 24 :
				// llvmGrammar.g:1:148: T__78
				{
				mT__78(); 

				}
				break;
			case 25 :
				// llvmGrammar.g:1:154: T__79
				{
				mT__79(); 

				}
				break;
			case 26 :
				// llvmGrammar.g:1:160: T__80
				{
				mT__80(); 

				}
				break;
			case 27 :
				// llvmGrammar.g:1:166: T__81
				{
				mT__81(); 

				}
				break;
			case 28 :
				// llvmGrammar.g:1:172: T__82
				{
				mT__82(); 

				}
				break;
			case 29 :
				// llvmGrammar.g:1:178: T__83
				{
				mT__83(); 

				}
				break;
			case 30 :
				// llvmGrammar.g:1:184: T__84
				{
				mT__84(); 

				}
				break;
			case 31 :
				// llvmGrammar.g:1:190: T__85
				{
				mT__85(); 

				}
				break;
			case 32 :
				// llvmGrammar.g:1:196: PHI
				{
				mPHI(); 

				}
				break;
			case 33 :
				// llvmGrammar.g:1:200: UNREACHABLE
				{
				mUNREACHABLE(); 

				}
				break;
			case 34 :
				// llvmGrammar.g:1:212: TRUE
				{
				mTRUE(); 

				}
				break;
			case 35 :
				// llvmGrammar.g:1:217: FALSE
				{
				mFALSE(); 

				}
				break;
			case 36 :
				// llvmGrammar.g:1:223: ALIGN
				{
				mALIGN(); 

				}
				break;
			case 37 :
				// llvmGrammar.g:1:229: LINKAGE_TYPE
				{
				mLINKAGE_TYPE(); 

				}
				break;
			case 38 :
				// llvmGrammar.g:1:242: RETURN_ATTR
				{
				mRETURN_ATTR(); 

				}
				break;
			case 39 :
				// llvmGrammar.g:1:254: ZEROINITIALIZER
				{
				mZEROINITIALIZER(); 

				}
				break;
			case 40 :
				// llvmGrammar.g:1:270: CAST_TYPE
				{
				mCAST_TYPE(); 

				}
				break;
			case 41 :
				// llvmGrammar.g:1:280: ARGUMENT_ATTRIBUTE
				{
				mARGUMENT_ATTRIBUTE(); 

				}
				break;
			case 42 :
				// llvmGrammar.g:1:299: PARAMETER_ATTRIBUTE
				{
				mPARAMETER_ATTRIBUTE(); 

				}
				break;
			case 43 :
				// llvmGrammar.g:1:319: FUNCTION_ATTRIBUTE
				{
				mFUNCTION_ATTRIBUTE(); 

				}
				break;
			case 44 :
				// llvmGrammar.g:1:338: CALLING_CONV
				{
				mCALLING_CONV(); 

				}
				break;
			case 45 :
				// llvmGrammar.g:1:351: CONDITION
				{
				mCONDITION(); 

				}
				break;
			case 46 :
				// llvmGrammar.g:1:361: NULL_CHAR
				{
				mNULL_CHAR(); 

				}
				break;
			case 47 :
				// llvmGrammar.g:1:371: BIN_OPR_STR
				{
				mBIN_OPR_STR(); 

				}
				break;
			case 48 :
				// llvmGrammar.g:1:383: BIN_OPR_FLAG
				{
				mBIN_OPR_FLAG(); 

				}
				break;
			case 49 :
				// llvmGrammar.g:1:396: ATOMIC_ORDERING
				{
				mATOMIC_ORDERING(); 

				}
				break;
			case 50 :
				// llvmGrammar.g:1:412: PRIMITIVE_DATA_TYPE
				{
				mPRIMITIVE_DATA_TYPE(); 

				}
				break;
			case 51 :
				// llvmGrammar.g:1:432: EQUAL
				{
				mEQUAL(); 

				}
				break;
			case 52 :
				// llvmGrammar.g:1:438: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 53 :
				// llvmGrammar.g:1:444: GLOBAL_PREFIX
				{
				mGLOBAL_PREFIX(); 

				}
				break;
			case 54 :
				// llvmGrammar.g:1:458: LOCAL_PREFIX
				{
				mLOCAL_PREFIX(); 

				}
				break;
			case 55 :
				// llvmGrammar.g:1:471: STAR
				{
				mSTAR(); 

				}
				break;
			case 56 :
				// llvmGrammar.g:1:476: START_PARANTHESIS
				{
				mSTART_PARANTHESIS(); 

				}
				break;
			case 57 :
				// llvmGrammar.g:1:494: END_PARANTHESIS
				{
				mEND_PARANTHESIS(); 

				}
				break;
			case 58 :
				// llvmGrammar.g:1:510: START_CURLY
				{
				mSTART_CURLY(); 

				}
				break;
			case 59 :
				// llvmGrammar.g:1:522: END_CURLY
				{
				mEND_CURLY(); 

				}
				break;
			case 60 :
				// llvmGrammar.g:1:532: START_ANGULAR
				{
				mSTART_ANGULAR(); 

				}
				break;
			case 61 :
				// llvmGrammar.g:1:546: END_ANGULAR
				{
				mEND_ANGULAR(); 

				}
				break;
			case 62 :
				// llvmGrammar.g:1:558: START_SQUARE_BR
				{
				mSTART_SQUARE_BR(); 

				}
				break;
			case 63 :
				// llvmGrammar.g:1:574: END_SQUARE_BR
				{
				mEND_SQUARE_BR(); 

				}
				break;
			case 64 :
				// llvmGrammar.g:1:588: DOT
				{
				mDOT(); 

				}
				break;
			case 65 :
				// llvmGrammar.g:1:592: MUL_OPERATOR
				{
				mMUL_OPERATOR(); 

				}
				break;
			case 66 :
				// llvmGrammar.g:1:605: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 67 :
				// llvmGrammar.g:1:612: DECIMAL_LITERAL
				{
				mDECIMAL_LITERAL(); 

				}
				break;
			case 68 :
				// llvmGrammar.g:1:628: FLOATING_LITERAL
				{
				mFLOATING_LITERAL(); 

				}
				break;
			case 69 :
				// llvmGrammar.g:1:645: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 70 :
				// llvmGrammar.g:1:660: ID
				{
				mID(); 

				}
				break;
			case 71 :
				// llvmGrammar.g:1:663: WHITESPACE
				{
				mWHITESPACE(); 

				}
				break;
			case 72 :
				// llvmGrammar.g:1:674: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA36 dfa36 = new DFA36(this);
	protected DFA47 dfa47 = new DFA47(this);
	static final String DFA36_eotS =
		"\11\uffff\1\12\2\uffff";
	static final String DFA36_eofS =
		"\14\uffff";
	static final String DFA36_minS =
		"\1\53\1\uffff\1\56\1\uffff\1\56\1\uffff\1\53\1\uffff\2\60\2\uffff";
	static final String DFA36_maxS =
		"\1\71\1\uffff\1\170\1\uffff\1\146\1\uffff\1\71\1\uffff\1\71\1\146\2\uffff";
	static final String DFA36_acceptS =
		"\1\uffff\1\1\1\uffff\1\2\1\uffff\1\5\1\uffff\1\4\2\uffff\2\3";
	static final String DFA36_specialS =
		"\14\uffff}>";
	static final String[] DFA36_transitionS = {
			"\1\1\1\uffff\1\1\1\3\1\uffff\1\2\11\4",
			"",
			"\1\1\1\uffff\12\4\12\uffff\1\7\1\6\1\7\21\uffff\1\5\13\uffff\1\7\1\6"+
			"\1\7\21\uffff\1\5",
			"",
			"\1\1\1\uffff\12\4\12\uffff\1\7\1\6\1\7\35\uffff\1\7\1\6\1\7",
			"",
			"\1\10\1\uffff\1\10\2\uffff\12\11",
			"",
			"\12\11",
			"\12\11\12\uffff\1\13\1\uffff\1\13\35\uffff\1\13\1\uffff\1\13",
			"",
			""
	};

	static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
	static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
	static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
	static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
	static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
	static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
	static final short[][] DFA36_transition;

	static {
		int numStates = DFA36_transitionS.length;
		DFA36_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
		}
	}

	protected class DFA36 extends DFA {

		public DFA36(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 36;
			this.eot = DFA36_eot;
			this.eof = DFA36_eof;
			this.min = DFA36_min;
			this.max = DFA36_max;
			this.accept = DFA36_accept;
			this.special = DFA36_special;
			this.transition = DFA36_transition;
		}
		@Override
		public String getDescription() {
			return "397:1: FLOATING_LITERAL : ( ( '+' | '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix | HEX_LITERAL );";
		}
	}

	static final String DFA47_eotS =
		"\1\uffff\1\54\1\61\1\64\1\67\1\uffff\22\53\1\uffff\2\53\16\uffff\2\u008b"+
		"\12\uffff\1\u008b\3\uffff\1\53\1\u0093\26\53\2\u00b4\23\53\1\u00d2\20"+
		"\53\1\u00ea\1\53\1\u00ea\2\53\1\u00b4\6\53\1\u0090\4\53\1\u00b4\2\53\1"+
		"\uffff\1\u008b\1\uffff\1\u008b\3\uffff\1\53\1\uffff\6\53\1\u009b\1\uffff"+
		"\16\53\3\u00b4\6\53\1\u00b4\1\uffff\2\u00b4\3\53\1\u0122\14\53\1\u0130"+
		"\4\u00ea\1\u0090\1\53\2\u0090\2\53\1\uffff\10\53\4\u00ea\4\53\1\u00b4"+
		"\1\53\1\u0142\4\53\1\uffff\5\53\2\u014c\3\53\5\u00ea\1\u0090\1\53\1\u0090"+
		"\1\53\1\u0090\1\53\3\uffff\2\53\1\u0156\6\53\1\u015d\2\53\1\u0090\3\53"+
		"\4\u0090\3\53\1\u0167\5\53\1\u016d\1\u0090\1\uffff\2\53\1\u0090\1\53\1"+
		"\u0171\6\53\1\u0178\1\u0090\1\uffff\1\u0090\1\u0179\2\53\1\u017c\7\53"+
		"\2\u0090\1\53\2\u00b4\1\uffff\10\53\1\u018d\1\uffff\1\53\1\u0171\1\53"+
		"\1\u0090\4\53\1\u018d\1\uffff\6\53\1\uffff\1\u019b\4\53\1\u0171\1\u00b4"+
		"\2\53\1\uffff\3\53\1\u0178\1\u01a5\1\uffff\3\53\1\uffff\1\53\1\u01aa\4"+
		"\53\2\uffff\1\53\2\uffff\1\u0171\1\u01b0\11\53\1\u014c\4\53\1\uffff\10"+
		"\53\1\u01c6\1\u009b\1\53\1\u01c8\1\u00b4\1\uffff\1\u009b\1\53\2\u0171"+
		"\1\53\1\u01cb\3\53\1\uffff\2\53\1\u01d1\1\53\1\uffff\1\53\1\u01d4\1\u0171"+
		"\2\53\1\uffff\3\53\1\u0171\4\53\1\u01c6\12\53\1\u0171\1\53\1\uffff\1\u01ea"+
		"\1\uffff\1\u0171\1\53\1\uffff\4\53\1\u01f0\1\uffff\1\u01f0\1\53\1\uffff"+
		"\1\u0178\1\uffff\3\53\1\u0130\1\53\1\u01c6\2\53\1\u01f8\4\53\1\u0178\1"+
		"\u0130\2\u01f0\1\53\1\u01fe\1\uffff\1\53\1\u0200\1\u01c6\1\u0171\1\u0130"+
		"\1\uffff\4\53\1\u0205\1\u0171\1\u01c6\1\uffff\1\53\2\u0130\2\53\1\uffff"+
		"\1\53\1\uffff\1\u018d\2\53\1\u01f0\1\uffff\1\u018d\1\53\1\u01f0\6\53\1"+
		"\u0213\2\53\1\u0216\1\uffff\1\53\1\u0218\1\uffff\1\53\1\uffff\1\53\1\u021b"+
		"\1\uffff";
	static final String DFA47_eofS =
		"\u021c\uffff";
	static final String DFA47_minS =
		"\1\11\1\42\1\0\1\60\1\56\1\uffff\1\63\1\151\1\141\1\145\1\63\1\145\1\61"+
		"\1\141\1\145\1\144\1\141\1\144\1\61\1\150\1\161\1\70\2\145\1\uffff\1\60"+
		"\1\157\15\uffff\1\60\2\56\4\uffff\1\154\1\40\4\uffff\1\56\3\uffff\1\62"+
		"\1\44\1\164\1\166\2\154\1\40\1\143\1\165\1\155\1\144\1\145\2\165\1\151"+
		"\1\145\1\157\1\62\1\64\1\60\1\164\1\157\1\155\1\142\2\44\1\62\1\64\1\142"+
		"\1\141\1\150\1\141\1\150\1\154\1\157\1\151\1\147\1\145\1\160\2\145\1\142"+
		"\1\151\1\154\1\151\1\44\1\160\1\165\1\144\2\164\2\145\1\151\1\145\1\151"+
		"\1\64\1\62\2\151\1\162\1\141\1\44\1\141\1\44\2\167\1\44\1\162\2\164\1"+
		"\145\1\161\1\145\1\44\1\144\1\150\1\144\1\161\1\44\1\154\1\156\1\uffff"+
		"\1\56\1\uffff\1\56\1\151\2\uffff\1\42\1\uffff\1\143\1\141\1\154\1\163"+
		"\1\155\1\144\1\44\1\uffff\1\154\1\151\1\142\1\160\1\163\1\164\1\144\1"+
		"\157\1\170\1\142\1\154\1\166\1\155\1\141\3\44\1\145\1\142\1\160\1\157"+
		"\2\145\1\44\1\uffff\2\44\1\145\1\144\1\162\1\44\1\144\1\145\1\162\1\145"+
		"\1\164\1\137\1\162\1\165\1\164\1\157\1\156\1\155\6\44\1\166\2\44\1\154"+
		"\1\147\1\uffff\3\145\1\141\1\145\1\162\1\157\1\141\4\44\1\166\1\155\1"+
		"\141\1\144\1\44\1\70\1\44\1\166\1\164\1\145\1\143\1\uffff\1\154\1\141"+
		"\1\156\1\145\1\164\2\44\1\157\1\164\1\163\6\44\1\162\1\44\1\137\1\44\1"+
		"\157\3\uffff\1\141\1\154\1\44\1\164\1\157\1\143\1\141\1\156\1\154\1\44"+
		"\1\145\1\143\1\44\1\165\1\163\1\164\4\44\1\164\1\154\1\141\1\44\1\165"+
		"\1\162\1\157\1\147\1\154\2\44\1\uffff\1\157\1\141\1\44\1\143\1\44\1\143"+
		"\1\145\2\143\1\146\1\145\2\44\1\uffff\2\44\1\145\1\40\1\44\1\143\1\146"+
		"\1\155\1\141\1\144\1\146\1\142\2\44\1\164\2\44\1\uffff\1\141\1\157\1\162"+
		"\1\164\1\151\1\160\1\167\1\164\1\44\1\uffff\1\145\1\44\1\151\1\44\1\151"+
		"\1\162\1\164\1\163\1\44\1\uffff\1\141\1\156\1\143\1\162\2\145\1\uffff"+
		"\1\44\1\143\1\156\2\151\2\44\1\145\1\154\1\uffff\2\156\1\160\2\44\1\uffff"+
		"\1\156\1\163\1\164\1\uffff\1\163\1\44\1\164\1\150\1\160\1\170\2\uffff"+
		"\1\164\2\uffff\2\44\1\145\1\143\1\145\1\160\1\154\1\151\1\164\1\151\1"+
		"\156\1\44\1\141\1\164\1\151\1\165\1\uffff\1\156\1\170\1\172\1\162\1\145"+
		"\1\157\1\164\1\156\2\44\1\145\2\44\1\uffff\1\44\1\143\2\44\1\155\1\44"+
		"\1\144\1\141\1\164\1\uffff\1\154\1\145\1\44\1\164\1\uffff\1\162\2\44\1"+
		"\164\1\40\1\uffff\1\144\1\150\1\162\1\44\1\145\1\154\1\145\1\156\1\44"+
		"\1\163\1\165\1\156\1\162\1\151\1\164\2\145\1\154\1\156\1\44\1\164\1\uffff"+
		"\1\44\1\uffff\1\44\1\145\1\uffff\1\163\1\154\1\162\1\171\1\44\1\uffff"+
		"\1\44\1\145\1\uffff\1\44\1\uffff\1\137\1\141\1\145\1\44\1\145\1\44\1\164"+
		"\1\154\1\44\1\162\1\144\1\156\1\164\4\44\1\151\1\44\1\uffff\1\156\4\44"+
		"\1\uffff\1\164\1\141\1\142\1\144\3\44\1\uffff\1\145\2\44\1\151\1\143\1"+
		"\uffff\1\164\1\uffff\1\44\1\144\1\154\1\44\1\uffff\1\44\1\141\1\44\1\160"+
		"\1\144\1\145\1\154\1\164\1\162\1\44\1\151\1\162\1\44\1\uffff\1\172\1\44"+
		"\1\uffff\1\145\1\uffff\1\162\1\44\1\uffff";
	static final String DFA47_maxS =
		"\1\175\1\170\1\uffff\1\160\1\71\1\uffff\1\63\1\171\2\157\1\163\1\154\1"+
		"\156\2\163\1\167\1\171\1\167\1\157\1\164\1\170\1\165\1\145\1\162\1\uffff"+
		"\1\163\1\165\15\uffff\1\71\1\170\1\165\4\uffff\1\154\1\157\4\uffff\1\71"+
		"\3\uffff\1\62\1\172\1\164\1\166\1\154\1\156\1\143\1\146\1\165\1\155\1"+
		"\163\1\164\2\165\1\151\1\145\1\157\1\62\1\64\1\60\1\164\1\157\1\155\1"+
		"\164\2\172\1\62\1\64\1\142\1\141\1\150\1\164\1\150\1\170\1\162\1\151\1"+
		"\164\1\145\1\160\2\164\1\142\1\151\2\162\1\172\1\160\1\165\1\162\4\164"+
		"\1\151\1\145\1\154\1\64\1\62\2\151\1\162\1\164\1\172\1\165\1\172\2\167"+
		"\1\172\1\170\3\164\1\161\1\145\1\172\1\144\1\150\1\144\1\161\1\172\1\154"+
		"\1\156\1\uffff\1\146\1\uffff\1\165\1\154\2\uffff\1\42\1\uffff\1\143\1"+
		"\141\1\154\1\163\1\155\1\144\1\172\1\uffff\1\154\1\151\1\142\1\160\1\163"+
		"\1\164\1\144\1\162\1\170\1\142\1\154\1\166\1\155\1\141\3\172\1\145\1\142"+
		"\1\160\1\157\1\164\1\145\1\172\1\uffff\2\172\1\145\1\144\1\162\1\172\1"+
		"\144\1\145\1\162\1\145\1\164\1\137\1\162\1\165\1\164\1\157\1\156\1\164"+
		"\6\172\1\166\2\172\1\154\1\147\1\uffff\1\145\1\156\1\145\1\141\1\145\1"+
		"\162\1\157\1\141\4\172\1\166\1\155\1\141\1\144\1\172\1\70\1\172\1\166"+
		"\1\164\1\145\1\143\1\uffff\1\154\1\141\1\156\1\145\1\164\2\172\1\157\1"+
		"\164\1\163\6\172\1\162\1\172\1\165\1\172\1\157\3\uffff\1\141\1\154\1\172"+
		"\1\164\1\157\1\143\1\141\1\156\1\154\1\172\1\145\1\143\1\172\2\165\1\164"+
		"\4\172\1\164\1\154\1\141\1\172\1\165\1\162\1\157\1\147\1\154\2\172\1\uffff"+
		"\1\157\1\141\1\172\1\143\1\172\1\143\1\145\2\143\1\146\1\145\2\172\1\uffff"+
		"\2\172\1\145\1\40\1\172\1\143\1\146\1\155\1\141\1\144\1\146\1\142\2\172"+
		"\1\164\2\172\1\uffff\1\141\1\157\1\162\1\164\1\151\1\160\1\167\1\164\1"+
		"\172\1\uffff\1\151\1\172\1\151\1\172\1\151\1\162\1\164\1\163\1\172\1\uffff"+
		"\1\141\1\156\1\143\1\162\2\145\1\uffff\1\172\1\143\1\156\2\151\2\172\1"+
		"\145\1\154\1\uffff\2\156\1\160\2\172\1\uffff\1\156\1\163\1\164\1\uffff"+
		"\1\163\1\172\1\164\1\150\1\160\1\170\2\uffff\1\164\2\uffff\2\172\1\145"+
		"\1\143\1\145\1\160\1\154\1\151\1\164\1\151\1\156\1\172\1\141\1\164\1\151"+
		"\1\165\1\uffff\1\156\1\170\1\172\1\162\1\145\1\157\1\164\1\156\2\172\1"+
		"\145\2\172\1\uffff\1\172\1\143\2\172\1\155\1\172\1\144\1\141\1\164\1\uffff"+
		"\1\154\1\145\1\172\1\164\1\uffff\1\162\2\172\1\164\1\40\1\uffff\1\144"+
		"\1\150\1\162\1\172\1\145\1\154\1\145\1\156\1\172\1\163\1\165\1\156\1\162"+
		"\1\151\1\164\2\145\1\154\1\156\1\172\1\164\1\uffff\1\172\1\uffff\1\172"+
		"\1\145\1\uffff\1\163\1\154\1\162\1\171\1\172\1\uffff\1\172\1\145\1\uffff"+
		"\1\172\1\uffff\1\137\1\141\1\145\1\172\1\145\1\172\1\164\1\154\1\172\1"+
		"\162\1\144\1\156\1\164\4\172\1\151\1\172\1\uffff\1\156\4\172\1\uffff\1"+
		"\164\1\141\1\142\1\144\3\172\1\uffff\1\145\2\172\1\151\1\143\1\uffff\1"+
		"\164\1\uffff\1\172\1\144\1\154\1\172\1\uffff\1\172\1\141\1\172\1\160\1"+
		"\144\1\145\1\154\1\164\1\162\1\172\1\151\1\162\1\172\1\uffff\2\172\1\uffff"+
		"\1\145\1\uffff\1\162\1\172\1\uffff";
	static final String DFA47_acceptS =
		"\5\uffff\1\7\22\uffff\1\56\2\uffff\1\63\1\64\1\65\1\66\1\67\1\70\1\71"+
		"\1\72\1\73\1\74\1\75\1\76\1\77\3\uffff\1\106\1\107\1\110\1\1\2\uffff\1"+
		"\3\1\105\1\5\1\4\1\uffff\1\6\1\100\1\104\122\uffff\1\102\1\uffff\1\103"+
		"\2\uffff\1\57\1\101\1\uffff\1\11\7\uffff\1\54\30\uffff\1\62\35\uffff\1"+
		"\33\27\uffff\1\55\25\uffff\1\2\1\44\1\10\37\uffff\1\25\15\uffff\1\53\21"+
		"\uffff\1\40\11\uffff\1\60\11\uffff\1\12\6\uffff\1\16\11\uffff\1\21\5\uffff"+
		"\1\24\3\uffff\1\50\6\uffff\1\52\1\31\1\uffff\1\34\1\42\20\uffff\1\51\15"+
		"\uffff\1\43\11\uffff\1\23\4\uffff\1\27\5\uffff\1\35\25\uffff\1\45\1\uffff"+
		"\1\15\2\uffff\1\20\5\uffff\1\26\2\uffff\1\30\1\uffff\1\32\23\uffff\1\14"+
		"\5\uffff\1\61\7\uffff\1\46\5\uffff\1\13\1\uffff\1\22\4\uffff\1\37\15\uffff"+
		"\1\41\2\uffff\1\36\1\uffff\1\17\2\uffff\1\47";
	static final String DFA47_specialS =
		"\2\uffff\1\0\u0219\uffff}>";
	static final String[] DFA47_transitionS = {
			"\2\54\1\uffff\2\54\22\uffff\1\1\1\uffff\1\2\1\uffff\1\53\1\36\2\uffff"+
			"\1\40\1\41\1\37\1\50\1\34\1\3\1\4\1\uffff\1\51\11\52\1\5\1\55\1\44\1"+
			"\33\1\45\1\uffff\1\35\22\53\1\6\7\53\1\46\1\30\1\47\1\uffff\1\53\1\uffff"+
			"\1\31\1\7\1\10\1\11\1\24\1\12\1\13\1\53\1\14\2\53\1\15\1\32\1\25\1\27"+
			"\1\23\1\53\1\16\1\17\1\20\1\21\1\22\3\53\1\26\1\42\1\uffff\1\43",
			"\1\56\76\uffff\1\57\26\uffff\1\60",
			"\0\62",
			"\12\65\66\uffff\1\63",
			"\1\66\1\uffff\12\70",
			"",
			"\1\71",
			"\1\73\10\uffff\1\72\6\uffff\1\74",
			"\1\75\1\uffff\1\77\13\uffff\1\76",
			"\1\100\11\uffff\1\101",
			"\1\112\2\uffff\1\113\1\uffff\1\114\50\uffff\1\103\1\uffff\1\102\1\107"+
			"\7\uffff\1\111\1\106\2\uffff\1\104\1\uffff\1\110\1\105",
			"\1\115\6\uffff\1\116",
			"\1\121\1\uffff\1\123\2\uffff\1\124\1\uffff\1\122\52\uffff\1\117\12\uffff"+
			"\1\120",
			"\1\125\15\uffff\1\126\3\uffff\1\127",
			"\1\130\15\uffff\1\131",
			"\1\143\1\132\1\uffff\1\140\1\144\1\135\2\uffff\1\141\5\uffff\1\136\1"+
			"\137\1\133\1\142\1\uffff\1\134",
			"\1\145\15\uffff\1\146\2\uffff\1\150\6\uffff\1\147",
			"\1\156\2\uffff\1\154\1\uffff\1\152\2\uffff\1\155\1\uffff\1\151\3\uffff"+
			"\1\157\4\uffff\1\153",
			"\1\162\4\uffff\1\161\70\uffff\1\160",
			"\1\163\11\uffff\1\164\1\uffff\1\165",
			"\1\167\6\uffff\1\166",
			"\1\174\54\uffff\1\171\11\uffff\1\170\3\uffff\1\172\1\uffff\1\173",
			"\1\175",
			"\1\u0081\1\uffff\1\u0080\4\uffff\1\177\1\uffff\1\u0082\1\uffff\1\176"+
			"\1\uffff\1\u0083",
			"",
			"\1\u0088\62\uffff\1\u0087\1\u0084\11\uffff\1\u0086\4\uffff\1\u0085",
			"\1\u008a\5\uffff\1\u0089",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\65",
			"\1\70\1\uffff\12\u008c\12\uffff\3\70\5\uffff\1\u008d\10\uffff\1\u008d"+
			"\2\uffff\1\70\13\uffff\3\70\5\uffff\1\u008d\10\uffff\1\u008d\2\uffff"+
			"\1\70",
			"\1\70\1\uffff\12\u008e\12\uffff\3\70\5\uffff\1\u008d\10\uffff\1\u008d"+
			"\16\uffff\3\70\5\uffff\1\u008d\10\uffff\1\u008d",
			"",
			"",
			"",
			"",
			"\1\u008f",
			"\1\u0091\116\uffff\1\u0090",
			"",
			"",
			"",
			"",
			"\1\70\1\uffff\12\65",
			"",
			"",
			"",
			"\1\u0092",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\u0099\1\u0098\1\u0097",
			"\1\u009b\102\uffff\1\u009a",
			"\1\u009c\2\uffff\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u00a2\7\uffff\1\u00a0\6\uffff\1\u00a1",
			"\1\u00a4\16\uffff\1\u00a3",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"\1\u00ae",
			"\1\u00af",
			"\1\u00b0\17\uffff\1\u00b2\1\uffff\1\u00b1",
			"\1\53\11\uffff\1\53\1\uffff\6\53\1\u00b3\3\53\7\uffff\32\53\4\uffff"+
			"\1\53\1\uffff\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u00b5",
			"\1\u00b6",
			"\1\u00b7",
			"\1\u00b8",
			"\1\u00b9",
			"\1\u00bb\12\uffff\1\u00bc\7\uffff\1\u00ba",
			"\1\u00bd",
			"\1\u00be\4\uffff\1\u00c0\6\uffff\1\u00bf",
			"\1\u00c1\2\uffff\1\u00c2",
			"\1\u00c3",
			"\1\u00c5\14\uffff\1\u00c4",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c9\16\uffff\1\u00c8",
			"\1\u00cb\16\uffff\1\u00ca",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce\5\uffff\1\u00cf",
			"\1\u00d0\10\uffff\1\u00d1",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u00d3",
			"\1\u00d4",
			"\1\u00d5\11\uffff\1\u00d6\1\u00d8\2\uffff\1\u00d7",
			"\1\u00d9",
			"\1\u00da",
			"\1\u00dc\16\uffff\1\u00db",
			"\1\u00de\16\uffff\1\u00dd",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e2\2\uffff\1\u00e1",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e9\22\uffff\1\u00e8",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u00eb\1\uffff\1\u00ec\16\uffff\1\u00ee\2\uffff\1\u00ed",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\22\53\1\u00ef\7\53",
			"\1\u00f0",
			"\1\u00f1",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u00f2\5\uffff\1\u00f3",
			"\1\u00f4",
			"\1\u00f5",
			"\1\u00f7\16\uffff\1\u00f6",
			"\1\u00f8",
			"\1\u00f9",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u00fa",
			"\1\u00fb",
			"\1\u00fc",
			"\1\u00fd",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u00fe",
			"\1\u00ff",
			"",
			"\1\70\1\uffff\12\u008c\12\uffff\3\70\35\uffff\3\70",
			"",
			"\1\70\1\uffff\12\u008e\12\uffff\3\70\5\uffff\1\u008d\10\uffff\1\u008d"+
			"\16\uffff\3\70\5\uffff\1\u008d\10\uffff\1\u008d",
			"\1\u0101\2\uffff\1\u0100",
			"",
			"",
			"\1\u0102",
			"",
			"\1\u0103",
			"\1\u0104",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0109",
			"\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"\1\u010d",
			"\1\u010e",
			"\1\u010f",
			"\1\u0111\2\uffff\1\u0110",
			"\1\u0112",
			"\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\1\u0116",
			"\1\u0117",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0118",
			"\1\u0119",
			"\1\u011a",
			"\1\u011b",
			"\1\u011c\16\uffff\1\u011d",
			"\1\u011e",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u011f",
			"\1\u0120",
			"\1\u0121",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127",
			"\1\u0128",
			"\1\u0129",
			"\1\u012a",
			"\1\u012b",
			"\1\u012c",
			"\1\u012d",
			"\1\u012f\6\uffff\1\u012e",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0131",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0132",
			"\1\u0133",
			"",
			"\1\u0134",
			"\1\u0135\10\uffff\1\u0136",
			"\1\u0137",
			"\1\u0138",
			"\1\u0139",
			"\1\u013a",
			"\1\u013b",
			"\1\u013c",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u013d",
			"\1\u013e",
			"\1\u013f",
			"\1\u0140",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0141",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0143",
			"\1\u0144",
			"\1\u0145",
			"\1\u0146",
			"",
			"\1\u0147",
			"\1\u0148",
			"\1\u0149",
			"\1\u014a",
			"\1\u014b",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u014d",
			"\1\u014e",
			"\1\u014f",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0150",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0152\25\uffff\1\u0151",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0153",
			"",
			"",
			"",
			"\1\u0154",
			"\1\u0155",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0157",
			"\1\u0158",
			"\1\u0159",
			"\1\u015a",
			"\1\u015b",
			"\1\u015c",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u015e",
			"\1\u015f",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0160",
			"\1\u0162\1\uffff\1\u0161",
			"\1\u0163",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0164",
			"\1\u0165",
			"\1\u0166",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0168",
			"\1\u0169",
			"\1\u016a",
			"\1\u016b",
			"\1\u016c",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u016e",
			"\1\u016f",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0170",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0172",
			"\1\u0173",
			"\1\u0174",
			"\1\u0175",
			"\1\u0176",
			"\1\u0177",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u017a",
			"\1\u017b",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u017d",
			"\1\u017e",
			"\1\u017f",
			"\1\u0180",
			"\1\u0181",
			"\1\u0182",
			"\1\u0183",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0184",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0185",
			"\1\u0186",
			"\1\u0187",
			"\1\u0188",
			"\1\u0189",
			"\1\u018a",
			"\1\u018b",
			"\1\u018c",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u018f\3\uffff\1\u018e",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0190",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0191",
			"\1\u0192",
			"\1\u0193",
			"\1\u0194",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0195",
			"\1\u0196",
			"\1\u0197",
			"\1\u0198",
			"\1\u0199",
			"\1\u019a",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u019c",
			"\1\u019d",
			"\1\u019e",
			"\1\u019f",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01a0",
			"\1\u01a1",
			"",
			"\1\u01a2",
			"\1\u01a3",
			"\1\u01a4",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u01a6",
			"\1\u01a7",
			"\1\u01a8",
			"",
			"\1\u01a9",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01ab",
			"\1\u01ac",
			"\1\u01ad",
			"\1\u01ae",
			"",
			"",
			"\1\u01af",
			"",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01b1",
			"\1\u01b2",
			"\1\u01b3",
			"\1\u01b4",
			"\1\u01b5",
			"\1\u01b6",
			"\1\u01b7",
			"\1\u01b8",
			"\1\u01b9",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01ba",
			"\1\u01bb",
			"\1\u01bc",
			"\1\u01bd",
			"",
			"\1\u01be",
			"\1\u01bf",
			"\1\u01c0",
			"\1\u01c1",
			"\1\u01c2",
			"\1\u01c3",
			"\1\u01c4",
			"\1\u01c5",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01c7",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01c9",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01ca",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01cc",
			"\1\u01cd",
			"\1\u01ce",
			"",
			"\1\u01cf",
			"\1\u01d0",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01d2",
			"",
			"\1\u01d3",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01d5",
			"\1\u01d6",
			"",
			"\1\u01d7",
			"\1\u01d8",
			"\1\u01d9",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01da",
			"\1\u01db",
			"\1\u01dc",
			"\1\u01dd",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\1\u01de\31\53",
			"\1\u01df",
			"\1\u01e0",
			"\1\u01e1",
			"\1\u01e2",
			"\1\u01e3",
			"\1\u01e4",
			"\1\u01e5",
			"\1\u01e6",
			"\1\u01e7",
			"\1\u01e8",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01e9",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01eb",
			"",
			"\1\u01ec",
			"\1\u01ed",
			"\1\u01ee",
			"\1\u01ef",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01f1",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u01f2",
			"\1\u01f3",
			"\1\u01f4",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01f5",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01f6",
			"\1\u01f7",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01f9",
			"\1\u01fa",
			"\1\u01fb",
			"\1\u01fc",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u01fd",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u01ff",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0201",
			"\1\u0202",
			"\1\u0203",
			"\1\u0204",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0206",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0207",
			"\1\u0208",
			"",
			"\1\u0209",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u020a",
			"\1\u020b",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u020c",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u020d",
			"\1\u020e",
			"\1\u020f",
			"\1\u0210",
			"\1\u0211",
			"\1\u0212",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"\1\u0214",
			"\1\u0215",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0217",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			"",
			"\1\u0219",
			"",
			"\1\u021a",
			"\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff"+
			"\32\53",
			""
	};

	static final short[] DFA47_eot = DFA.unpackEncodedString(DFA47_eotS);
	static final short[] DFA47_eof = DFA.unpackEncodedString(DFA47_eofS);
	static final char[] DFA47_min = DFA.unpackEncodedStringToUnsignedChars(DFA47_minS);
	static final char[] DFA47_max = DFA.unpackEncodedStringToUnsignedChars(DFA47_maxS);
	static final short[] DFA47_accept = DFA.unpackEncodedString(DFA47_acceptS);
	static final short[] DFA47_special = DFA.unpackEncodedString(DFA47_specialS);
	static final short[][] DFA47_transition;

	static {
		int numStates = DFA47_transitionS.length;
		DFA47_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA47_transition[i] = DFA.unpackEncodedString(DFA47_transitionS[i]);
		}
	}

	protected class DFA47 extends DFA {

		public DFA47(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 47;
			this.eot = DFA47_eot;
			this.eof = DFA47_eof;
			this.min = DFA47_min;
			this.max = DFA47_max;
			this.accept = DFA47_accept;
			this.special = DFA47_special;
			this.transition = DFA47_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | PHI | UNREACHABLE | TRUE | FALSE | ALIGN | LINKAGE_TYPE | RETURN_ATTR | ZEROINITIALIZER | CAST_TYPE | ARGUMENT_ATTRIBUTE | PARAMETER_ATTRIBUTE | FUNCTION_ATTRIBUTE | CALLING_CONV | CONDITION | NULL_CHAR | BIN_OPR_STR | BIN_OPR_FLAG | ATOMIC_ORDERING | PRIMITIVE_DATA_TYPE | EQUAL | COMMA | GLOBAL_PREFIX | LOCAL_PREFIX | STAR | START_PARANTHESIS | END_PARANTHESIS | START_CURLY | END_CURLY | START_ANGULAR | END_ANGULAR | START_SQUARE_BR | END_SQUARE_BR | DOT | MUL_OPERATOR | NUMBER | DECIMAL_LITERAL | FLOATING_LITERAL | STRING_LITERAL | ID | WHITESPACE | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA47_2 = input.LA(1);
						s = -1;
						if ( ((LA47_2 >= '\u0000' && LA47_2 <= '\uFFFF')) ) {s = 50;}
						else s = 49;
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 47, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
