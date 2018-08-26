// $ANTLR 3.3 Nov 30, 2010 12:50:56 resources/Clang/CPreprocessorLL.g 2014-07-10 11:47:32
 package org.tamedragon.compilers.clang.preprocessor;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CPreprocessorLLLexer extends Lexer {
    public static final int EOF=-1;
    public static final int HASH=4;
    public static final int DOUBLE_HASH=5;
    public static final int INCLUDE=6;
    public static final int DEFINE=7;
    public static final int DEFINED=8;
    public static final int UNDEF=9;
    public static final int LINE=10;
    public static final int ERROR=11;
    public static final int WARNING=12;
    public static final int PRAGMA=13;
    public static final int IF=14;
    public static final int IFDEF=15;
    public static final int IFNDEF=16;
    public static final int ELIF=17;
    public static final int ELSE=18;
    public static final int ENDIF=19;
    public static final int SEMICOLON=20;
    public static final int COMMA=21;
    public static final int ASSIGN=22;
    public static final int PLUS=23;
    public static final int MINUS=24;
    public static final int COLON=25;
    public static final int DOT=26;
    public static final int LPAREN=27;
    public static final int RPAREN=28;
    public static final int LCURLY=29;
    public static final int RCURLY=30;
    public static final int LBRACK=31;
    public static final int RBRACK=32;
    public static final int PIPE=33;
    public static final int QUESTION=34;
    public static final int NOT=35;
    public static final int EQUALS=36;
    public static final int NOT_EQUALS=37;
    public static final int DEREFERENCE=38;
    public static final int AMPERSAND=39;
    public static final int INCREMENT=40;
    public static final int DECREMENT=41;
    public static final int MULTIPLY_ASSIGN=42;
    public static final int DIVIDE_ASSIGN=43;
    public static final int ADD_ASSIGN=44;
    public static final int MINUS_ASSIGN=45;
    public static final int MODULO_ASSIGN=46;
    public static final int BITWISE_AND_ASSIGN=47;
    public static final int BITWISE_XOR_ASSIGN=48;
    public static final int BITWISE_OR_ASSIGN=49;
    public static final int LEFT_SHIFT_ASSIGN=50;
    public static final int RIGHT_SHIFT_ASSIGN=51;
    public static final int LESSER_THAN=52;
    public static final int GREATER_THAN=53;
    public static final int LESSER_THAN_OR_EQUAL_TO=54;
    public static final int GREATER_THAN_OR_EQUAL_TO=55;
    public static final int LEFT_SHIFT=56;
    public static final int RIGHT_SHIFT=57;
    public static final int TILDE=58;
    public static final int CARET=59;
    public static final int OR=60;
    public static final int AND=61;
    public static final int STAR=62;
    public static final int DIVIDE=63;
    public static final int MODULO=64;
    public static final int ELLIPSES=65;
    public static final int IntegerTypeSuffix=66;
    public static final int DECIMAL_LITERAL=67;
    public static final int OCTAL_LITERAL=68;
    public static final int HexDigit=69;
    public static final int HEX_LITERAL=70;
    public static final int Exponent=71;
    public static final int FloatTypeSuffix=72;
    public static final int FLOATING_LITERAL=73;
    public static final int EscapeSequence=74;
    public static final int CHAR_LITERAL=75;
    public static final int NEWLINE=76;
    public static final int WS=77;
    public static final int COMMENT=78;
    public static final int LINE_COMMENT=79;
    public static final int LETTER=80;
    public static final int ID=81;
    public static final int FILENAME=82;
    public static final int STRING_LITERAL=83;
    public static final int OctalEscape=84;

    		public void skipIfRequired(){
    			CurrentProductionSingleton cps = CurrentProductionSingleton.getInstance();
    			if(!cps.wsRequired()) 
    				skip();			
    		} 
    	

    // delegates
    // delegators

    public CPreprocessorLLLexer() {;} 
    public CPreprocessorLLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CPreprocessorLLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "resources/Clang/CPreprocessorLL.g"; }

    // $ANTLR start "HASH"
    public final void mHASH() throws RecognitionException {
        try {
            int _type = HASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:205:5: ( '#' )
            // resources/Clang/CPreprocessorLL.g:205:7: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HASH"

    // $ANTLR start "DOUBLE_HASH"
    public final void mDOUBLE_HASH() throws RecognitionException {
        try {
            int _type = DOUBLE_HASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:206:12: ( '##' )
            // resources/Clang/CPreprocessorLL.g:206:14: '##'
            {
            match("##"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_HASH"

    // $ANTLR start "INCLUDE"
    public final void mINCLUDE() throws RecognitionException {
        try {
            int _type = INCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:207:8: ( 'include' )
            // resources/Clang/CPreprocessorLL.g:207:10: 'include'
            {
            match("include"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INCLUDE"

    // $ANTLR start "DEFINE"
    public final void mDEFINE() throws RecognitionException {
        try {
            int _type = DEFINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:208:7: ( 'define' )
            // resources/Clang/CPreprocessorLL.g:208:9: 'define'
            {
            match("define"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFINE"

    // $ANTLR start "DEFINED"
    public final void mDEFINED() throws RecognitionException {
        try {
            int _type = DEFINED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:209:8: ( 'defined' )
            // resources/Clang/CPreprocessorLL.g:209:10: 'defined'
            {
            match("defined"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFINED"

    // $ANTLR start "UNDEF"
    public final void mUNDEF() throws RecognitionException {
        try {
            int _type = UNDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:210:6: ( 'undef' )
            // resources/Clang/CPreprocessorLL.g:210:8: 'undef'
            {
            match("undef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNDEF"

    // $ANTLR start "LINE"
    public final void mLINE() throws RecognitionException {
        try {
            int _type = LINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:211:5: ( 'line' )
            // resources/Clang/CPreprocessorLL.g:211:7: 'line'
            {
            match("line"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE"

    // $ANTLR start "ERROR"
    public final void mERROR() throws RecognitionException {
        try {
            int _type = ERROR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:212:6: ( 'error' )
            // resources/Clang/CPreprocessorLL.g:212:8: 'error'
            {
            match("error"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ERROR"

    // $ANTLR start "WARNING"
    public final void mWARNING() throws RecognitionException {
        try {
            int _type = WARNING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:213:8: ( 'warning' )
            // resources/Clang/CPreprocessorLL.g:213:9: 'warning'
            {
            match("warning"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WARNING"

    // $ANTLR start "PRAGMA"
    public final void mPRAGMA() throws RecognitionException {
        try {
            int _type = PRAGMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:214:7: ( 'pragma' )
            // resources/Clang/CPreprocessorLL.g:214:9: 'pragma'
            {
            match("pragma"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRAGMA"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:215:3: ( 'if' )
            // resources/Clang/CPreprocessorLL.g:215:5: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "IFDEF"
    public final void mIFDEF() throws RecognitionException {
        try {
            int _type = IFDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:216:6: ( 'ifdef' )
            // resources/Clang/CPreprocessorLL.g:216:8: 'ifdef'
            {
            match("ifdef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IFDEF"

    // $ANTLR start "IFNDEF"
    public final void mIFNDEF() throws RecognitionException {
        try {
            int _type = IFNDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:217:7: ( 'ifndef' )
            // resources/Clang/CPreprocessorLL.g:217:9: 'ifndef'
            {
            match("ifndef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IFNDEF"

    // $ANTLR start "ELIF"
    public final void mELIF() throws RecognitionException {
        try {
            int _type = ELIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:218:5: ( 'elif' )
            // resources/Clang/CPreprocessorLL.g:218:7: 'elif'
            {
            match("elif"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELIF"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:219:5: ( 'else' )
            // resources/Clang/CPreprocessorLL.g:219:7: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "ENDIF"
    public final void mENDIF() throws RecognitionException {
        try {
            int _type = ENDIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:220:6: ( 'endif' )
            // resources/Clang/CPreprocessorLL.g:220:8: 'endif'
            {
            match("endif"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENDIF"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:221:11: ( ';' )
            // resources/Clang/CPreprocessorLL.g:221:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:222:7: ( ',' )
            // resources/Clang/CPreprocessorLL.g:222:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:223:8: ( '=' )
            // resources/Clang/CPreprocessorLL.g:223:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:224:6: ( '+' )
            // resources/Clang/CPreprocessorLL.g:224:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:225:8: ( '-' )
            // resources/Clang/CPreprocessorLL.g:225:10: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:226:7: ( ':' )
            // resources/Clang/CPreprocessorLL.g:226:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:227:5: ( '.' )
            // resources/Clang/CPreprocessorLL.g:227:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:228:8: ( '(' )
            // resources/Clang/CPreprocessorLL.g:228:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:229:8: ( ')' )
            // resources/Clang/CPreprocessorLL.g:229:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:230:8: ( '{' )
            // resources/Clang/CPreprocessorLL.g:230:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:231:8: ( '}' )
            // resources/Clang/CPreprocessorLL.g:231:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:232:8: ( '[' )
            // resources/Clang/CPreprocessorLL.g:232:10: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:233:8: ( ']' )
            // resources/Clang/CPreprocessorLL.g:233:10: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "PIPE"
    public final void mPIPE() throws RecognitionException {
        try {
            int _type = PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:234:7: ( '|' )
            // resources/Clang/CPreprocessorLL.g:234:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PIPE"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:235:9: ( '?' )
            // resources/Clang/CPreprocessorLL.g:235:13: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:236:5: ( '!' )
            // resources/Clang/CPreprocessorLL.g:236:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:237:8: ( '==' )
            // resources/Clang/CPreprocessorLL.g:237:10: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "NOT_EQUALS"
    public final void mNOT_EQUALS() throws RecognitionException {
        try {
            int _type = NOT_EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:238:12: ( '!=' )
            // resources/Clang/CPreprocessorLL.g:238:14: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT_EQUALS"

    // $ANTLR start "DEREFERENCE"
    public final void mDEREFERENCE() throws RecognitionException {
        try {
            int _type = DEREFERENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:239:13: ( '->' )
            // resources/Clang/CPreprocessorLL.g:239:15: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEREFERENCE"

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:240:11: ( '&' )
            // resources/Clang/CPreprocessorLL.g:240:13: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "INCREMENT"
    public final void mINCREMENT() throws RecognitionException {
        try {
            int _type = INCREMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:241:11: ( '++' )
            // resources/Clang/CPreprocessorLL.g:241:13: '++'
            {
            match("++"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INCREMENT"

    // $ANTLR start "DECREMENT"
    public final void mDECREMENT() throws RecognitionException {
        try {
            int _type = DECREMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:242:11: ( '--' )
            // resources/Clang/CPreprocessorLL.g:242:13: '--'
            {
            match("--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECREMENT"

    // $ANTLR start "MULTIPLY_ASSIGN"
    public final void mMULTIPLY_ASSIGN() throws RecognitionException {
        try {
            int _type = MULTIPLY_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:243:17: ( '*=' )
            // resources/Clang/CPreprocessorLL.g:243:19: '*='
            {
            match("*="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULTIPLY_ASSIGN"

    // $ANTLR start "DIVIDE_ASSIGN"
    public final void mDIVIDE_ASSIGN() throws RecognitionException {
        try {
            int _type = DIVIDE_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:244:15: ( '/=' )
            // resources/Clang/CPreprocessorLL.g:244:17: '/='
            {
            match("/="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIVIDE_ASSIGN"

    // $ANTLR start "ADD_ASSIGN"
    public final void mADD_ASSIGN() throws RecognitionException {
        try {
            int _type = ADD_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:245:12: ( '+=' )
            // resources/Clang/CPreprocessorLL.g:245:14: '+='
            {
            match("+="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ADD_ASSIGN"

    // $ANTLR start "MINUS_ASSIGN"
    public final void mMINUS_ASSIGN() throws RecognitionException {
        try {
            int _type = MINUS_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:246:14: ( '-=' )
            // resources/Clang/CPreprocessorLL.g:246:16: '-='
            {
            match("-="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS_ASSIGN"

    // $ANTLR start "MODULO_ASSIGN"
    public final void mMODULO_ASSIGN() throws RecognitionException {
        try {
            int _type = MODULO_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:247:15: ( '%=' )
            // resources/Clang/CPreprocessorLL.g:247:17: '%='
            {
            match("%="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODULO_ASSIGN"

    // $ANTLR start "BITWISE_AND_ASSIGN"
    public final void mBITWISE_AND_ASSIGN() throws RecognitionException {
        try {
            int _type = BITWISE_AND_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:248:20: ( '&=' )
            // resources/Clang/CPreprocessorLL.g:248:22: '&='
            {
            match("&="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_AND_ASSIGN"

    // $ANTLR start "BITWISE_XOR_ASSIGN"
    public final void mBITWISE_XOR_ASSIGN() throws RecognitionException {
        try {
            int _type = BITWISE_XOR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:249:20: ( '^=' )
            // resources/Clang/CPreprocessorLL.g:249:22: '^='
            {
            match("^="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_XOR_ASSIGN"

    // $ANTLR start "BITWISE_OR_ASSIGN"
    public final void mBITWISE_OR_ASSIGN() throws RecognitionException {
        try {
            int _type = BITWISE_OR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:250:19: ( '|=' )
            // resources/Clang/CPreprocessorLL.g:250:21: '|='
            {
            match("|="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_OR_ASSIGN"

    // $ANTLR start "LEFT_SHIFT_ASSIGN"
    public final void mLEFT_SHIFT_ASSIGN() throws RecognitionException {
        try {
            int _type = LEFT_SHIFT_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:251:19: ( '<<=' )
            // resources/Clang/CPreprocessorLL.g:251:21: '<<='
            {
            match("<<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEFT_SHIFT_ASSIGN"

    // $ANTLR start "RIGHT_SHIFT_ASSIGN"
    public final void mRIGHT_SHIFT_ASSIGN() throws RecognitionException {
        try {
            int _type = RIGHT_SHIFT_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:252:20: ( '>>=' )
            // resources/Clang/CPreprocessorLL.g:252:22: '>>='
            {
            match(">>="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RIGHT_SHIFT_ASSIGN"

    // $ANTLR start "LESSER_THAN"
    public final void mLESSER_THAN() throws RecognitionException {
        try {
            int _type = LESSER_THAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:253:14: ( '<' )
            // resources/Clang/CPreprocessorLL.g:253:16: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESSER_THAN"

    // $ANTLR start "GREATER_THAN"
    public final void mGREATER_THAN() throws RecognitionException {
        try {
            int _type = GREATER_THAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:254:15: ( '>' )
            // resources/Clang/CPreprocessorLL.g:254:17: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_THAN"

    // $ANTLR start "LESSER_THAN_OR_EQUAL_TO"
    public final void mLESSER_THAN_OR_EQUAL_TO() throws RecognitionException {
        try {
            int _type = LESSER_THAN_OR_EQUAL_TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:255:26: ( '<=' )
            // resources/Clang/CPreprocessorLL.g:255:28: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESSER_THAN_OR_EQUAL_TO"

    // $ANTLR start "GREATER_THAN_OR_EQUAL_TO"
    public final void mGREATER_THAN_OR_EQUAL_TO() throws RecognitionException {
        try {
            int _type = GREATER_THAN_OR_EQUAL_TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:256:27: ( '>=' )
            // resources/Clang/CPreprocessorLL.g:256:29: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_THAN_OR_EQUAL_TO"

    // $ANTLR start "LEFT_SHIFT"
    public final void mLEFT_SHIFT() throws RecognitionException {
        try {
            int _type = LEFT_SHIFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:257:13: ( '<<' )
            // resources/Clang/CPreprocessorLL.g:257:15: '<<'
            {
            match("<<"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEFT_SHIFT"

    // $ANTLR start "RIGHT_SHIFT"
    public final void mRIGHT_SHIFT() throws RecognitionException {
        try {
            int _type = RIGHT_SHIFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:258:14: ( '>>' )
            // resources/Clang/CPreprocessorLL.g:258:16: '>>'
            {
            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RIGHT_SHIFT"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:259:7: ( '~' )
            // resources/Clang/CPreprocessorLL.g:259:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "CARET"
    public final void mCARET() throws RecognitionException {
        try {
            int _type = CARET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:260:7: ( '^' )
            // resources/Clang/CPreprocessorLL.g:260:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CARET"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:261:4: ( '||' )
            // resources/Clang/CPreprocessorLL.g:261:6: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:262:5: ( '&&' )
            // resources/Clang/CPreprocessorLL.g:262:7: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:263:6: ( '*' )
            // resources/Clang/CPreprocessorLL.g:263:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:264:8: ( '/' )
            // resources/Clang/CPreprocessorLL.g:264:10: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "MODULO"
    public final void mMODULO() throws RecognitionException {
        try {
            int _type = MODULO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:265:8: ( '%' )
            // resources/Clang/CPreprocessorLL.g:265:10: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODULO"

    // $ANTLR start "ELLIPSES"
    public final void mELLIPSES() throws RecognitionException {
        try {
            int _type = ELLIPSES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:266:9: ( '...' )
            // resources/Clang/CPreprocessorLL.g:266:11: '...'
            {
            match("..."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELLIPSES"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            int _type = DECIMAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:267:17: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // resources/Clang/CPreprocessorLL.g:267:19: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // resources/Clang/CPreprocessorLL.g:267:19: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='0') ) {
                alt2=1;
            }
            else if ( ((LA2_0>='1' && LA2_0<='9')) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:267:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:267:26: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // resources/Clang/CPreprocessorLL.g:267:35: ( '0' .. '9' )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:267:35: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:267:46: ( IntegerTypeSuffix )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='L'||LA3_0=='U'||LA3_0=='l'||LA3_0=='u') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:267:46: IntegerTypeSuffix
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
        }
    }
    // $ANTLR end "DECIMAL_LITERAL"

    // $ANTLR start "OCTAL_LITERAL"
    public final void mOCTAL_LITERAL() throws RecognitionException {
        try {
            int _type = OCTAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:268:15: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // resources/Clang/CPreprocessorLL.g:268:17: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            // resources/Clang/CPreprocessorLL.g:268:21: ( '0' .. '7' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='7')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:268:22: '0' .. '7'
            	    {
            	    matchRange('0','7'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // resources/Clang/CPreprocessorLL.g:268:33: ( IntegerTypeSuffix )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='L'||LA5_0=='U'||LA5_0=='l'||LA5_0=='u') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:268:33: IntegerTypeSuffix
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
        }
    }
    // $ANTLR end "OCTAL_LITERAL"

    // $ANTLR start "HEX_LITERAL"
    public final void mHEX_LITERAL() throws RecognitionException {
        try {
            int _type = HEX_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:269:13: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // resources/Clang/CPreprocessorLL.g:269:15: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // resources/Clang/CPreprocessorLL.g:269:29: ( HexDigit )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='F')||(LA6_0>='a' && LA6_0<='f')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:269:29: HexDigit
            	    {
            	    mHexDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            // resources/Clang/CPreprocessorLL.g:269:39: ( IntegerTypeSuffix )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='L'||LA7_0=='U'||LA7_0=='l'||LA7_0=='u') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:269:39: IntegerTypeSuffix
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
        }
    }
    // $ANTLR end "HEX_LITERAL"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:271:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // resources/Clang/CPreprocessorLL.g:271:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "IntegerTypeSuffix"
    public final void mIntegerTypeSuffix() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:274:2: ( ( 'u' | 'U' )? ( 'l' | 'L' ) | ( 'u' | 'U' ) ( 'l' | 'L' )? )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='U'||LA10_0=='u') ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1=='L'||LA10_1=='l') ) {
                    alt10=1;
                }
                else {
                    alt10=2;}
            }
            else if ( (LA10_0=='L'||LA10_0=='l') ) {
                alt10=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:274:4: ( 'u' | 'U' )? ( 'l' | 'L' )
                    {
                    // resources/Clang/CPreprocessorLL.g:274:4: ( 'u' | 'U' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='U'||LA8_0=='u') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:
                            {
                            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:275:4: ( 'u' | 'U' ) ( 'l' | 'L' )?
                    {
                    if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // resources/Clang/CPreprocessorLL.g:275:15: ( 'l' | 'L' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='L'||LA9_0=='l') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:
                            {
                            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "IntegerTypeSuffix"

    // $ANTLR start "FLOATING_LITERAL"
    public final void mFLOATING_LITERAL() throws RecognitionException {
        try {
            int _type = FLOATING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:278:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix )
            int alt22=4;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:278:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                    {
                    // resources/Clang/CPreprocessorLL.g:278:9: ( '0' .. '9' )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:278:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);

                    match('.'); 
                    // resources/Clang/CPreprocessorLL.g:278:25: ( '0' .. '9' )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:278:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:278:37: ( Exponent )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:278:37: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // resources/Clang/CPreprocessorLL.g:278:47: ( FloatTypeSuffix )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='D'||LA14_0=='F'||LA14_0=='d'||LA14_0=='f') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:278:47: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:279:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                    {
                    match('.'); 
                    // resources/Clang/CPreprocessorLL.g:279:13: ( '0' .. '9' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:279:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:279:25: ( Exponent )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='E'||LA16_0=='e') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:279:25: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // resources/Clang/CPreprocessorLL.g:279:35: ( FloatTypeSuffix )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='D'||LA17_0=='F'||LA17_0=='d'||LA17_0=='f') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:279:35: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:280:9: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
                    {
                    // resources/Clang/CPreprocessorLL.g:280:9: ( '0' .. '9' )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='0' && LA18_0<='9')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:280:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);

                    mExponent(); 
                    // resources/Clang/CPreprocessorLL.g:280:30: ( FloatTypeSuffix )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0=='D'||LA19_0=='F'||LA19_0=='d'||LA19_0=='f') ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:280:30: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // resources/Clang/CPreprocessorLL.g:281:9: ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix
                    {
                    // resources/Clang/CPreprocessorLL.g:281:9: ( '0' .. '9' )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( ((LA20_0>='0' && LA20_0<='9')) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:281:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt20 >= 1 ) break loop20;
                                EarlyExitException eee =
                                    new EarlyExitException(20, input);
                                throw eee;
                        }
                        cnt20++;
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:281:21: ( Exponent )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0=='E'||LA21_0=='e') ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:281:21: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    mFloatTypeSuffix(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOATING_LITERAL"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:284:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // resources/Clang/CPreprocessorLL.g:284:13: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // resources/Clang/CPreprocessorLL.g:284:23: ( '+' | '-' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='+'||LA23_0=='-') ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:284:34: ( '0' .. '9' )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>='0' && LA24_0<='9')) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:284:35: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "FloatTypeSuffix"
    public final void mFloatTypeSuffix() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:286:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // resources/Clang/CPreprocessorLL.g:286:19: ( 'f' | 'F' | 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "FloatTypeSuffix"

    // $ANTLR start "CHAR_LITERAL"
    public final void mCHAR_LITERAL() throws RecognitionException {
        try {
            int _type = CHAR_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:287:17: ( '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // resources/Clang/CPreprocessorLL.g:287:21: '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // resources/Clang/CPreprocessorLL.g:287:26: ( EscapeSequence | ~ ( '\\'' | '\\\\' ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='\\') ) {
                alt25=1;
            }
            else if ( ((LA25_0>='\u0000' && LA25_0<='&')||(LA25_0>='(' && LA25_0<='[')||(LA25_0>=']' && LA25_0<='\uFFFF')) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:287:28: EscapeSequence
                    {
                    mEscapeSequence(); 

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:287:45: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR_LITERAL"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:288:9: ( ( '\\r' )? '\\n' )
            // resources/Clang/CPreprocessorLL.g:288:11: ( '\\r' )? '\\n'
            {
            // resources/Clang/CPreprocessorLL.g:288:11: ( '\\r' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='\r') ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:288:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:289:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // resources/Clang/CPreprocessorLL.g:289:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:290:9: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // resources/Clang/CPreprocessorLL.g:290:13: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // resources/Clang/CPreprocessorLL.g:290:18: ( options {greedy=false; } : . )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0=='*') ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1=='/') ) {
                        alt27=2;
                    }
                    else if ( ((LA27_1>='\u0000' && LA27_1<='.')||(LA27_1>='0' && LA27_1<='\uFFFF')) ) {
                        alt27=1;
                    }


                }
                else if ( ((LA27_0>='\u0000' && LA27_0<=')')||(LA27_0>='+' && LA27_0<='\uFFFF')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:290:46: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:291:14: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // resources/Clang/CPreprocessorLL.g:291:16: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // resources/Clang/CPreprocessorLL.g:291:21: (~ ( '\\n' | '\\r' ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>='\u0000' && LA28_0<='\t')||(LA28_0>='\u000B' && LA28_0<='\f')||(LA28_0>='\u000E' && LA28_0<='\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:291:21: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:291:35: ( '\\r' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0=='\r') ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:291:35: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:292:4: ( LETTER ( LETTER | '0' .. '9' )* )
            // resources/Clang/CPreprocessorLL.g:292:6: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 
            // resources/Clang/CPreprocessorLL.g:292:13: ( LETTER | '0' .. '9' )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0=='$'||(LA30_0>='0' && LA30_0<='9')||(LA30_0>='A' && LA30_0<='Z')||LA30_0=='_'||(LA30_0>='a' && LA30_0<='z')) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "FILENAME"
    public final void mFILENAME() throws RecognitionException {
        try {
            int _type = FILENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:293:10: ( LETTER ( LETTER | '0' .. '9' | MINUS )* )
            // resources/Clang/CPreprocessorLL.g:293:12: LETTER ( LETTER | '0' .. '9' | MINUS )*
            {
            mLETTER(); 
            // resources/Clang/CPreprocessorLL.g:293:19: ( LETTER | '0' .. '9' | MINUS )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0=='$'||LA31_0=='-'||(LA31_0>='0' && LA31_0<='9')||(LA31_0>='A' && LA31_0<='Z')||LA31_0=='_'||(LA31_0>='a' && LA31_0<='z')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:
            	    {
            	    if ( input.LA(1)=='$'||input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FILENAME"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/CPreprocessorLL.g:294:16: ( '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"' )
            // resources/Clang/CPreprocessorLL.g:294:18: '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // resources/Clang/CPreprocessorLL.g:294:22: ( EscapeSequence | ~ ( '\\\\' | '\"' ) )*
            loop32:
            do {
                int alt32=3;
                int LA32_0 = input.LA(1);

                if ( (LA32_0=='\\') ) {
                    alt32=1;
                }
                else if ( ((LA32_0>='\u0000' && LA32_0<='!')||(LA32_0>='#' && LA32_0<='[')||(LA32_0>=']' && LA32_0<='\uFFFF')) ) {
                    alt32=2;
                }


                switch (alt32) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:294:24: EscapeSequence
            	    {
            	    mEscapeSequence(); 

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/CPreprocessorLL.g:294:41: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:297:5: ( '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OctalEscape )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0=='\\') ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1=='\"'||LA33_1=='\''||LA33_1=='\\'||(LA33_1>='a' && LA33_1<='b')||LA33_1=='f'||LA33_1=='n'||LA33_1=='r'||LA33_1=='t') ) {
                    alt33=1;
                }
                else if ( ((LA33_1>='0' && LA33_1<='7')) ) {
                    alt33=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:297:9: '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||(input.LA(1)>='a' && input.LA(1)<='b')||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:298:9: OctalEscape
                    {
                    mOctalEscape(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:301:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0=='\\') ) {
                int LA34_1 = input.LA(2);

                if ( ((LA34_1>='0' && LA34_1<='3')) ) {
                    int LA34_2 = input.LA(3);

                    if ( ((LA34_2>='0' && LA34_2<='7')) ) {
                        int LA34_4 = input.LA(4);

                        if ( ((LA34_4>='0' && LA34_4<='7')) ) {
                            alt34=1;
                        }
                        else {
                            alt34=2;}
                    }
                    else {
                        alt34=3;}
                }
                else if ( ((LA34_1>='4' && LA34_1<='7')) ) {
                    int LA34_3 = input.LA(3);

                    if ( ((LA34_3>='0' && LA34_3<='7')) ) {
                        alt34=2;
                    }
                    else {
                        alt34=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:301:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // resources/Clang/CPreprocessorLL.g:301:14: ( '0' .. '3' )
                    // resources/Clang/CPreprocessorLL.g:301:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // resources/Clang/CPreprocessorLL.g:301:25: ( '0' .. '7' )
                    // resources/Clang/CPreprocessorLL.g:301:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // resources/Clang/CPreprocessorLL.g:301:36: ( '0' .. '7' )
                    // resources/Clang/CPreprocessorLL.g:301:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:302:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // resources/Clang/CPreprocessorLL.g:302:14: ( '0' .. '7' )
                    // resources/Clang/CPreprocessorLL.g:302:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // resources/Clang/CPreprocessorLL.g:302:25: ( '0' .. '7' )
                    // resources/Clang/CPreprocessorLL.g:302:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:303:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // resources/Clang/CPreprocessorLL.g:303:14: ( '0' .. '7' )
                    // resources/Clang/CPreprocessorLL.g:303:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // resources/Clang/CPreprocessorLL.g:307:2: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // resources/Clang/CPreprocessorLL.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    public void mTokens() throws RecognitionException {
        // resources/Clang/CPreprocessorLL.g:1:8: ( HASH | DOUBLE_HASH | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | WARNING | PRAGMA | IF | IFDEF | IFNDEF | ELIF | ELSE | ENDIF | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | QUESTION | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | ID | FILENAME | STRING_LITERAL )
        int alt35=74;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:1:10: HASH
                {
                mHASH(); 

                }
                break;
            case 2 :
                // resources/Clang/CPreprocessorLL.g:1:15: DOUBLE_HASH
                {
                mDOUBLE_HASH(); 

                }
                break;
            case 3 :
                // resources/Clang/CPreprocessorLL.g:1:27: INCLUDE
                {
                mINCLUDE(); 

                }
                break;
            case 4 :
                // resources/Clang/CPreprocessorLL.g:1:35: DEFINE
                {
                mDEFINE(); 

                }
                break;
            case 5 :
                // resources/Clang/CPreprocessorLL.g:1:42: DEFINED
                {
                mDEFINED(); 

                }
                break;
            case 6 :
                // resources/Clang/CPreprocessorLL.g:1:50: UNDEF
                {
                mUNDEF(); 

                }
                break;
            case 7 :
                // resources/Clang/CPreprocessorLL.g:1:56: LINE
                {
                mLINE(); 

                }
                break;
            case 8 :
                // resources/Clang/CPreprocessorLL.g:1:61: ERROR
                {
                mERROR(); 

                }
                break;
            case 9 :
                // resources/Clang/CPreprocessorLL.g:1:67: WARNING
                {
                mWARNING(); 

                }
                break;
            case 10 :
                // resources/Clang/CPreprocessorLL.g:1:75: PRAGMA
                {
                mPRAGMA(); 

                }
                break;
            case 11 :
                // resources/Clang/CPreprocessorLL.g:1:82: IF
                {
                mIF(); 

                }
                break;
            case 12 :
                // resources/Clang/CPreprocessorLL.g:1:85: IFDEF
                {
                mIFDEF(); 

                }
                break;
            case 13 :
                // resources/Clang/CPreprocessorLL.g:1:91: IFNDEF
                {
                mIFNDEF(); 

                }
                break;
            case 14 :
                // resources/Clang/CPreprocessorLL.g:1:98: ELIF
                {
                mELIF(); 

                }
                break;
            case 15 :
                // resources/Clang/CPreprocessorLL.g:1:103: ELSE
                {
                mELSE(); 

                }
                break;
            case 16 :
                // resources/Clang/CPreprocessorLL.g:1:108: ENDIF
                {
                mENDIF(); 

                }
                break;
            case 17 :
                // resources/Clang/CPreprocessorLL.g:1:114: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 18 :
                // resources/Clang/CPreprocessorLL.g:1:124: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 19 :
                // resources/Clang/CPreprocessorLL.g:1:130: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 20 :
                // resources/Clang/CPreprocessorLL.g:1:137: PLUS
                {
                mPLUS(); 

                }
                break;
            case 21 :
                // resources/Clang/CPreprocessorLL.g:1:142: MINUS
                {
                mMINUS(); 

                }
                break;
            case 22 :
                // resources/Clang/CPreprocessorLL.g:1:148: COLON
                {
                mCOLON(); 

                }
                break;
            case 23 :
                // resources/Clang/CPreprocessorLL.g:1:154: DOT
                {
                mDOT(); 

                }
                break;
            case 24 :
                // resources/Clang/CPreprocessorLL.g:1:158: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 25 :
                // resources/Clang/CPreprocessorLL.g:1:165: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 26 :
                // resources/Clang/CPreprocessorLL.g:1:172: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 27 :
                // resources/Clang/CPreprocessorLL.g:1:179: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 28 :
                // resources/Clang/CPreprocessorLL.g:1:186: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 29 :
                // resources/Clang/CPreprocessorLL.g:1:193: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 30 :
                // resources/Clang/CPreprocessorLL.g:1:200: PIPE
                {
                mPIPE(); 

                }
                break;
            case 31 :
                // resources/Clang/CPreprocessorLL.g:1:205: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 32 :
                // resources/Clang/CPreprocessorLL.g:1:214: NOT
                {
                mNOT(); 

                }
                break;
            case 33 :
                // resources/Clang/CPreprocessorLL.g:1:218: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 34 :
                // resources/Clang/CPreprocessorLL.g:1:225: NOT_EQUALS
                {
                mNOT_EQUALS(); 

                }
                break;
            case 35 :
                // resources/Clang/CPreprocessorLL.g:1:236: DEREFERENCE
                {
                mDEREFERENCE(); 

                }
                break;
            case 36 :
                // resources/Clang/CPreprocessorLL.g:1:248: AMPERSAND
                {
                mAMPERSAND(); 

                }
                break;
            case 37 :
                // resources/Clang/CPreprocessorLL.g:1:258: INCREMENT
                {
                mINCREMENT(); 

                }
                break;
            case 38 :
                // resources/Clang/CPreprocessorLL.g:1:268: DECREMENT
                {
                mDECREMENT(); 

                }
                break;
            case 39 :
                // resources/Clang/CPreprocessorLL.g:1:278: MULTIPLY_ASSIGN
                {
                mMULTIPLY_ASSIGN(); 

                }
                break;
            case 40 :
                // resources/Clang/CPreprocessorLL.g:1:294: DIVIDE_ASSIGN
                {
                mDIVIDE_ASSIGN(); 

                }
                break;
            case 41 :
                // resources/Clang/CPreprocessorLL.g:1:308: ADD_ASSIGN
                {
                mADD_ASSIGN(); 

                }
                break;
            case 42 :
                // resources/Clang/CPreprocessorLL.g:1:319: MINUS_ASSIGN
                {
                mMINUS_ASSIGN(); 

                }
                break;
            case 43 :
                // resources/Clang/CPreprocessorLL.g:1:332: MODULO_ASSIGN
                {
                mMODULO_ASSIGN(); 

                }
                break;
            case 44 :
                // resources/Clang/CPreprocessorLL.g:1:346: BITWISE_AND_ASSIGN
                {
                mBITWISE_AND_ASSIGN(); 

                }
                break;
            case 45 :
                // resources/Clang/CPreprocessorLL.g:1:365: BITWISE_XOR_ASSIGN
                {
                mBITWISE_XOR_ASSIGN(); 

                }
                break;
            case 46 :
                // resources/Clang/CPreprocessorLL.g:1:384: BITWISE_OR_ASSIGN
                {
                mBITWISE_OR_ASSIGN(); 

                }
                break;
            case 47 :
                // resources/Clang/CPreprocessorLL.g:1:402: LEFT_SHIFT_ASSIGN
                {
                mLEFT_SHIFT_ASSIGN(); 

                }
                break;
            case 48 :
                // resources/Clang/CPreprocessorLL.g:1:420: RIGHT_SHIFT_ASSIGN
                {
                mRIGHT_SHIFT_ASSIGN(); 

                }
                break;
            case 49 :
                // resources/Clang/CPreprocessorLL.g:1:439: LESSER_THAN
                {
                mLESSER_THAN(); 

                }
                break;
            case 50 :
                // resources/Clang/CPreprocessorLL.g:1:451: GREATER_THAN
                {
                mGREATER_THAN(); 

                }
                break;
            case 51 :
                // resources/Clang/CPreprocessorLL.g:1:464: LESSER_THAN_OR_EQUAL_TO
                {
                mLESSER_THAN_OR_EQUAL_TO(); 

                }
                break;
            case 52 :
                // resources/Clang/CPreprocessorLL.g:1:488: GREATER_THAN_OR_EQUAL_TO
                {
                mGREATER_THAN_OR_EQUAL_TO(); 

                }
                break;
            case 53 :
                // resources/Clang/CPreprocessorLL.g:1:513: LEFT_SHIFT
                {
                mLEFT_SHIFT(); 

                }
                break;
            case 54 :
                // resources/Clang/CPreprocessorLL.g:1:524: RIGHT_SHIFT
                {
                mRIGHT_SHIFT(); 

                }
                break;
            case 55 :
                // resources/Clang/CPreprocessorLL.g:1:536: TILDE
                {
                mTILDE(); 

                }
                break;
            case 56 :
                // resources/Clang/CPreprocessorLL.g:1:542: CARET
                {
                mCARET(); 

                }
                break;
            case 57 :
                // resources/Clang/CPreprocessorLL.g:1:548: OR
                {
                mOR(); 

                }
                break;
            case 58 :
                // resources/Clang/CPreprocessorLL.g:1:551: AND
                {
                mAND(); 

                }
                break;
            case 59 :
                // resources/Clang/CPreprocessorLL.g:1:555: STAR
                {
                mSTAR(); 

                }
                break;
            case 60 :
                // resources/Clang/CPreprocessorLL.g:1:560: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 61 :
                // resources/Clang/CPreprocessorLL.g:1:567: MODULO
                {
                mMODULO(); 

                }
                break;
            case 62 :
                // resources/Clang/CPreprocessorLL.g:1:574: ELLIPSES
                {
                mELLIPSES(); 

                }
                break;
            case 63 :
                // resources/Clang/CPreprocessorLL.g:1:583: DECIMAL_LITERAL
                {
                mDECIMAL_LITERAL(); 

                }
                break;
            case 64 :
                // resources/Clang/CPreprocessorLL.g:1:599: OCTAL_LITERAL
                {
                mOCTAL_LITERAL(); 

                }
                break;
            case 65 :
                // resources/Clang/CPreprocessorLL.g:1:613: HEX_LITERAL
                {
                mHEX_LITERAL(); 

                }
                break;
            case 66 :
                // resources/Clang/CPreprocessorLL.g:1:625: FLOATING_LITERAL
                {
                mFLOATING_LITERAL(); 

                }
                break;
            case 67 :
                // resources/Clang/CPreprocessorLL.g:1:642: CHAR_LITERAL
                {
                mCHAR_LITERAL(); 

                }
                break;
            case 68 :
                // resources/Clang/CPreprocessorLL.g:1:655: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 69 :
                // resources/Clang/CPreprocessorLL.g:1:663: WS
                {
                mWS(); 

                }
                break;
            case 70 :
                // resources/Clang/CPreprocessorLL.g:1:666: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 71 :
                // resources/Clang/CPreprocessorLL.g:1:674: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 72 :
                // resources/Clang/CPreprocessorLL.g:1:687: ID
                {
                mID(); 

                }
                break;
            case 73 :
                // resources/Clang/CPreprocessorLL.g:1:690: FILENAME
                {
                mFILENAME(); 

                }
                break;
            case 74 :
                // resources/Clang/CPreprocessorLL.g:1:699: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;

        }

    }


    protected DFA22 dfa22 = new DFA22(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA22_eotS =
        "\7\uffff\1\10\2\uffff";
    static final String DFA22_eofS =
        "\12\uffff";
    static final String DFA22_minS =
        "\2\56\2\uffff\1\53\1\uffff\2\60\2\uffff";
    static final String DFA22_maxS =
        "\1\71\1\146\2\uffff\1\71\1\uffff\1\71\1\146\2\uffff";
    static final String DFA22_acceptS =
        "\2\uffff\1\2\1\1\1\uffff\1\4\2\uffff\2\3";
    static final String DFA22_specialS =
        "\12\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\12\uffff\1\5\1\4\1\5\35\uffff\1\5\1\4\1"+
            "\5",
            "",
            "",
            "\1\6\1\uffff\1\6\2\uffff\12\7",
            "",
            "\12\7",
            "\12\7\12\uffff\1\11\1\uffff\1\11\35\uffff\1\11\1\uffff\1\11",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "277:1: FLOATING_LITERAL : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix );";
        }
    }
    static final String DFA35_eotS =
        "\1\uffff\1\52\7\55\2\uffff\1\71\1\74\1\100\1\uffff\1\102\6\uffff"+
        "\1\106\1\uffff\1\110\1\113\1\115\1\121\1\123\1\125\1\130\1\133\1"+
        "\uffff\2\135\1\uffff\1\46\2\uffff\1\55\3\uffff\1\55\1\144\1\uffff"+
        "\1\55\1\uffff\10\55\36\uffff\1\157\2\uffff\1\161\4\uffff\1\162\1"+
        "\135\1\uffff\3\55\1\uffff\11\55\5\uffff\5\55\1\u0084\1\55\1\u0086"+
        "\1\u0087\4\55\1\u008c\2\55\1\u008f\1\uffff\1\u0090\2\uffff\1\u0091"+
        "\3\55\1\uffff\1\u0095\1\u0097\3\uffff\1\55\1\u0099\1\u009a\1\uffff"+
        "\1\u009b\1\uffff\1\u009c\4\uffff";
    static final String DFA35_eofS =
        "\u009d\uffff";
    static final String DFA35_minS =
        "\1\11\1\43\7\44\2\uffff\1\75\1\53\1\55\1\uffff\1\56\6\uffff\1\75"+
        "\1\uffff\1\75\1\46\1\75\1\52\2\75\1\74\1\75\1\uffff\2\56\1\uffff"+
        "\1\12\2\uffff\1\44\3\uffff\2\44\1\uffff\1\44\1\uffff\10\44\36\uffff"+
        "\1\75\2\uffff\1\75\4\uffff\2\56\1\uffff\3\44\1\uffff\11\44\5\uffff"+
        "\21\44\1\uffff\1\44\2\uffff\4\44\1\uffff\2\44\3\uffff\3\44\1\uffff"+
        "\1\44\1\uffff\1\44\4\uffff";
    static final String DFA35_maxS =
        "\1\176\1\43\7\172\2\uffff\2\75\1\76\1\uffff\1\71\6\uffff\1\174"+
        "\1\uffff\7\75\1\76\1\uffff\1\170\1\146\1\uffff\1\12\2\uffff\1\172"+
        "\3\uffff\2\172\1\uffff\1\172\1\uffff\10\172\36\uffff\1\75\2\uffff"+
        "\1\75\4\uffff\2\146\1\uffff\3\172\1\uffff\11\172\5\uffff\21\172"+
        "\1\uffff\1\172\2\uffff\4\172\1\uffff\2\172\3\uffff\3\172\1\uffff"+
        "\1\172\1\uffff\1\172\4\uffff";
    static final String DFA35_acceptS =
        "\11\uffff\1\21\1\22\3\uffff\1\26\1\uffff\1\30\1\31\1\32\1\33\1"+
        "\34\1\35\1\uffff\1\37\10\uffff\1\67\2\uffff\1\103\1\uffff\1\104"+
        "\1\105\1\uffff\1\112\1\2\1\1\2\uffff\1\110\1\uffff\1\111\10\uffff"+
        "\1\41\1\23\1\45\1\51\1\24\1\43\1\46\1\52\1\25\1\76\1\27\1\102\1"+
        "\56\1\71\1\36\1\42\1\40\1\54\1\72\1\44\1\47\1\73\1\50\1\106\1\107"+
        "\1\74\1\53\1\75\1\55\1\70\1\uffff\1\63\1\61\1\uffff\1\64\1\62\1"+
        "\101\1\77\2\uffff\1\104\3\uffff\1\13\11\uffff\1\57\1\65\1\60\1\66"+
        "\1\100\21\uffff\1\7\1\uffff\1\16\1\17\4\uffff\1\14\2\uffff\1\6\1"+
        "\10\1\20\3\uffff\1\15\1\uffff\1\4\1\uffff\1\12\1\3\1\5\1\11";
    static final String DFA35_specialS =
        "\u009d\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\46\1\45\1\uffff\1\46\1\44\22\uffff\1\46\1\30\1\50\1\1\1"+
            "\47\1\34\1\31\1\43\1\20\1\21\1\32\1\14\1\12\1\15\1\17\1\33\1"+
            "\41\11\42\1\16\1\11\1\36\1\13\1\37\1\27\1\uffff\32\47\1\24\1"+
            "\uffff\1\25\1\35\1\47\1\uffff\3\47\1\3\1\6\3\47\1\2\2\47\1\5"+
            "\3\47\1\10\4\47\1\4\1\47\1\7\3\47\1\22\1\26\1\23\1\40",
            "\1\51",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\54\7\56\1\53\14\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\60\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\15\56\1\61\14\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\10\56\1\62\21\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\13\56\1\64\1\56\1\65\3\56\1\63\10\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\1\66\31\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\21\56\1\67\10\56",
            "",
            "",
            "\1\70",
            "\1\72\21\uffff\1\73",
            "\1\76\17\uffff\1\77\1\75",
            "",
            "\1\101\1\uffff\12\103",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\104\76\uffff\1\105",
            "",
            "\1\107",
            "\1\112\26\uffff\1\111",
            "\1\114",
            "\1\117\4\uffff\1\120\15\uffff\1\116",
            "\1\122",
            "\1\124",
            "\1\126\1\127",
            "\1\132\1\131",
            "",
            "\1\103\1\uffff\10\136\2\103\12\uffff\3\103\21\uffff\1\134"+
            "\13\uffff\3\103\21\uffff\1\134",
            "\1\103\1\uffff\12\137\12\uffff\3\103\35\uffff\3\103",
            "",
            "\1\140",
            "",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\2\56\1\141\27\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\3\56\1\142\11\56\1\143\14\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\145\24\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\3\56\1\146\26\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\15\56\1\147\14\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\21\56\1\150\10\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\10\56\1\151\11\56\1\152\7\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\3\56\1\153\26\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\21\56\1\154\10\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\1\155\31\56",
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
            "",
            "",
            "",
            "",
            "\1\156",
            "",
            "",
            "\1\160",
            "",
            "",
            "",
            "",
            "\1\103\1\uffff\10\136\2\103\12\uffff\3\103\35\uffff\3\103",
            "\1\103\1\uffff\12\137\12\uffff\3\103\35\uffff\3\103",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\13\56\1\163\16\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\164\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\3\56\1\165\26\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\10\56\1\166\21\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\167\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\170\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\16\56\1\171\13\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\172\24\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\173\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\10\56\1\174\21\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\15\56\1\175\14\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\6\56\1\176\23\56",
            "",
            "",
            "",
            "",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\24\56\1\177\5\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\u0080\24\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\u0081\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\15\56\1\u0082\14\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\u0083\24\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\21\56\1\u0085\10\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\u0088\24\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\10\56\1\u0089\21\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\14\56\1\u008a\15\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\3\56\1\u008b\26\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\5\56\1\u008d\24\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\u008e\25\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\15\56\1\u0092\14\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\1\u0093\31\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\4\56\1\u0094\25\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\3\56\1\u0096\26\56",
            "",
            "",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\6\56\1\u0098\23\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "\1\56\10\uffff\1\57\2\uffff\12\56\7\uffff\32\56\4\uffff\1"+
            "\56\1\uffff\32\56",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( HASH | DOUBLE_HASH | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | WARNING | PRAGMA | IF | IFDEF | IFNDEF | ELIF | ELSE | ENDIF | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | QUESTION | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | ID | FILENAME | STRING_LITERAL );";
        }
    }
 

}