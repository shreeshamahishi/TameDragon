// $ANTLR 3.3 Nov 30, 2010 12:50:56 Resources/LLVM/llvmGrammar.g 2014-07-15 15:17:44
 package org.tamedragon.common.llvmir.utils; 
                               



import org.antlr.runtime.*;
import org.tamedragon.common.llvmir.semanticanalysis.*;
import org.tamedragon.common.llvmir.types.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class llvmGrammarLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__54=54;
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
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int XOR=4;
    public static final int PHI=5;
    public static final int UNREACHABLE=6;
    public static final int TRUE=7;
    public static final int FALSE=8;
    public static final int ALIGN=9;
    public static final int LINKAGE_TYPE=10;
    public static final int RETURN_ATTR=11;
    public static final int ZEROINITIALIZER=12;
    public static final int CAST_TYPE=13;
    public static final int ARGUMENT_ATTRIBUTE=14;
    public static final int PARAMETER_ATTRIBUTE=15;
    public static final int FUNCTION_ATTRIBUTE=16;
    public static final int CALLING_CONV=17;
    public static final int CONDITION=18;
    public static final int NULL_CHAR=19;
    public static final int BIN_OPR_STR=20;
    public static final int ATOMIC_ORDERING=21;
    public static final int ID=22;
    public static final int PRIMITIVE_DATA_TYPE=23;
    public static final int GLOBAL_PREFIX=24;
    public static final int LOCAL_PREFIX=25;
    public static final int EQUAL=26;
    public static final int COMMA=27;
    public static final int STAR=28;
    public static final int START_PARANTHESIS=29;
    public static final int END_PARANTHESIS=30;
    public static final int START_CURLY=31;
    public static final int END_CURLY=32;
    public static final int START_ANGULAR=33;
    public static final int END_ANGULAR=34;
    public static final int START_SQUARE_BR=35;
    public static final int END_SQUARE_BR=36;
    public static final int DOT=37;
    public static final int MUL_OPERATOR=38;
    public static final int NUMBER=39;
    public static final int IntegerTypeSuffix=40;
    public static final int DECIMAL_LITERAL=41;
    public static final int OCTAL_LITERAL=42;
    public static final int HexDigit=43;
    public static final int HEX_LITERAL=44;
    public static final int Exponent=45;
    public static final int FloatTypeSuffix=46;
    public static final int FLOATING_LITERAL=47;
    public static final int EscapeSequence=48;
    public static final int STRING_LITERAL=49;
    public static final int OctalEscape=50;
    public static final int LETTER=51;
    public static final int WHITESPACE=52;
    public static final int LINE_COMMENT=53;

    // delegates
    // delegators

    public llvmGrammarLexer() {;} 
    public llvmGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public llvmGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Resources/LLVM/llvmGrammar.g"; }

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:12:7: ( 'target datalayout' )
            // Resources/LLVM/llvmGrammar.g:12:9: 'target datalayout'
            {
            match("target datalayout"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:13:7: ( ' \"' )
            // Resources/LLVM/llvmGrammar.g:13:9: ' \"'
            {
            match(" \""); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:14:7: ( '-p:' )
            // Resources/LLVM/llvmGrammar.g:14:9: '-p:'
            {
            match("-p:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:15:7: ( ':' )
            // Resources/LLVM/llvmGrammar.g:15:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:16:7: ( '-' )
            // Resources/LLVM/llvmGrammar.g:16:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:17:7: ( 'S32\"' )
            // Resources/LLVM/llvmGrammar.g:17:9: 'S32\"'
            {
            match("S32\""); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:18:7: ( '\"' )
            // Resources/LLVM/llvmGrammar.g:18:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:19:7: ( 'declare' )
            // Resources/LLVM/llvmGrammar.g:19:9: 'declare'
            {
            match("declare"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:20:7: ( '...' )
            // Resources/LLVM/llvmGrammar.g:20:9: '...'
            {
            match("..."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:21:7: ( 'define' )
            // Resources/LLVM/llvmGrammar.g:21:9: 'define'
            {
            match("define"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:22:7: ( 'nsw' )
            // Resources/LLVM/llvmGrammar.g:22:9: 'nsw'
            {
            match("nsw"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:23:7: ( 'nuw' )
            // Resources/LLVM/llvmGrammar.g:23:9: 'nuw'
            {
            match("nuw"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:24:7: ( 'exact' )
            // Resources/LLVM/llvmGrammar.g:24:9: 'exact'
            {
            match("exact"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:25:7: ( ' alloca ' )
            // Resources/LLVM/llvmGrammar.g:25:9: ' alloca '
            {
            match(" alloca "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:26:7: ( 'load' )
            // Resources/LLVM/llvmGrammar.g:26:9: 'load'
            {
            match("load"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:27:7: ( 'store' )
            // Resources/LLVM/llvmGrammar.g:27:9: 'store'
            {
            match("store"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:28:7: ( 'volatile' )
            // Resources/LLVM/llvmGrammar.g:28:9: 'volatile'
            {
            match("volatile"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:29:7: ( 'getelementptr' )
            // Resources/LLVM/llvmGrammar.g:29:9: 'getelementptr'
            {
            match("getelementptr"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:30:7: ( 'inbounds' )
            // Resources/LLVM/llvmGrammar.g:30:9: 'inbounds'
            {
            match("inbounds"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:31:7: ( 'undef' )
            // Resources/LLVM/llvmGrammar.g:31:9: 'undef'
            {
            match("undef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:32:7: ( 'ret' )
            // Resources/LLVM/llvmGrammar.g:32:9: 'ret'
            {
            match("ret"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:33:7: ( 'br' )
            // Resources/LLVM/llvmGrammar.g:33:9: 'br'
            {
            match("br"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:34:7: ( 'label' )
            // Resources/LLVM/llvmGrammar.g:34:9: 'label'
            {
            match("label"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:35:7: ( 'switch' )
            // Resources/LLVM/llvmGrammar.g:35:9: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:36:7: ( 'icmp' )
            // Resources/LLVM/llvmGrammar.g:36:9: 'icmp'
            {
            match("icmp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:37:7: ( 'fcmp' )
            // Resources/LLVM/llvmGrammar.g:37:9: 'fcmp'
            {
            match("fcmp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:38:7: ( 'tail' )
            // Resources/LLVM/llvmGrammar.g:38:9: 'tail'
            {
            match("tail"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:39:7: ( 'call' )
            // Resources/LLVM/llvmGrammar.g:39:9: 'call'
            {
            match("call"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:40:7: ( 'select' )
            // Resources/LLVM/llvmGrammar.g:40:9: 'select'
            {
            match("select"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:41:7: ( 'to' )
            // Resources/LLVM/llvmGrammar.g:41:9: 'to'
            {
            match("to"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:42:7: ( 'global' )
            // Resources/LLVM/llvmGrammar.g:42:9: 'global'
            {
            match("global"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:43:7: ( 'unnamed_addr' )
            // Resources/LLVM/llvmGrammar.g:43:9: 'unnamed_addr'
            {
            match("unnamed_addr"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:44:7: ( 'constant' )
            // Resources/LLVM/llvmGrammar.g:44:9: 'constant'
            {
            match("constant"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:45:7: ( 'type {' )
            // Resources/LLVM/llvmGrammar.g:45:9: 'type {'
            {
            match("type {"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            // Resources/LLVM/llvmGrammar.g:338:5: ( 'xor' )
            // Resources/LLVM/llvmGrammar.g:338:7: 'xor'
            {
            match("xor"); 


            }

        }
        finally {
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "PHI"
    public final void mPHI() throws RecognitionException {
        try {
            int _type = PHI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:339:5: ( 'phi' )
            // Resources/LLVM/llvmGrammar.g:339:7: 'phi'
            {
            match("phi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PHI"

    // $ANTLR start "UNREACHABLE"
    public final void mUNREACHABLE() throws RecognitionException {
        try {
            int _type = UNREACHABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:341:13: ( 'unreachable' )
            // Resources/LLVM/llvmGrammar.g:341:15: 'unreachable'
            {
            match("unreachable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNREACHABLE"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:342:6: ( 'true' )
            // Resources/LLVM/llvmGrammar.g:342:8: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:343:7: ( 'false' )
            // Resources/LLVM/llvmGrammar.g:343:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "ALIGN"
    public final void mALIGN() throws RecognitionException {
        try {
            int _type = ALIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:344:7: ( ' align ' )
            // Resources/LLVM/llvmGrammar.g:344:9: ' align '
            {
            match(" align "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALIGN"

    // $ANTLR start "LINKAGE_TYPE"
    public final void mLINKAGE_TYPE() throws RecognitionException {
        try {
            int _type = LINKAGE_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:345:14: ( 'private' | 'common' | 'extern' | 'internal' | 'external' )
            int alt1=5;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:345:16: 'private'
                    {
                    match("private"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:345:26: 'common'
                    {
                    match("common"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:345:35: 'extern'
                    {
                    match("extern"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:345:44: 'internal'
                    {
                    match("internal"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:345:55: 'external'
                    {
                    match("external"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINKAGE_TYPE"

    // $ANTLR start "RETURN_ATTR"
    public final void mRETURN_ATTR() throws RecognitionException {
        try {
            int _type = RETURN_ATTR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:346:13: ( 'noalias' )
            // Resources/LLVM/llvmGrammar.g:346:15: 'noalias'
            {
            match("noalias"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN_ATTR"

    // $ANTLR start "ZEROINITIALIZER"
    public final void mZEROINITIALIZER() throws RecognitionException {
        try {
            int _type = ZEROINITIALIZER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:347:17: ( 'zeroinitializer' )
            // Resources/LLVM/llvmGrammar.g:347:19: 'zeroinitializer'
            {
            match("zeroinitializer"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ZEROINITIALIZER"

    // $ANTLR start "CAST_TYPE"
    public final void mCAST_TYPE() throws RecognitionException {
        try {
            int _type = CAST_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:348:11: ( 'trunc' | 'zext' | 'sext' | 'fptrunc' | 'fpext' | 'fptoui' | 'fptosi' | 'uitofp' | 'sitofp' | 'ptrtoint' | 'inttoptr' | 'bitcast' )
            int alt2=12;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:348:13: 'trunc'
                    {
                    match("trunc"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:348:23: 'zext'
                    {
                    match("zext"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:348:32: 'sext'
                    {
                    match("sext"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:348:41: 'fptrunc'
                    {
                    match("fptrunc"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:348:53: 'fpext'
                    {
                    match("fpext"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:348:63: 'fptoui'
                    {
                    match("fptoui"); 


                    }
                    break;
                case 7 :
                    // Resources/LLVM/llvmGrammar.g:348:74: 'fptosi'
                    {
                    match("fptosi"); 


                    }
                    break;
                case 8 :
                    // Resources/LLVM/llvmGrammar.g:348:85: 'uitofp'
                    {
                    match("uitofp"); 


                    }
                    break;
                case 9 :
                    // Resources/LLVM/llvmGrammar.g:348:96: 'sitofp'
                    {
                    match("sitofp"); 


                    }
                    break;
                case 10 :
                    // Resources/LLVM/llvmGrammar.g:348:106: 'ptrtoint'
                    {
                    match("ptrtoint"); 


                    }
                    break;
                case 11 :
                    // Resources/LLVM/llvmGrammar.g:348:119: 'inttoptr'
                    {
                    match("inttoptr"); 


                    }
                    break;
                case 12 :
                    // Resources/LLVM/llvmGrammar.g:348:132: 'bitcast'
                    {
                    match("bitcast"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CAST_TYPE"

    // $ANTLR start "ARGUMENT_ATTRIBUTE"
    public final void mARGUMENT_ATTRIBUTE() throws RecognitionException {
        try {
            int _type = ARGUMENT_ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:349:20: ( 'byval' | 'nest' | 'structret' | 'nocapture' )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:349:21: 'byval'
                    {
                    match("byval"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:349:30: 'nest'
                    {
                    match("nest"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:349:38: 'structret'
                    {
                    match("structret"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:349:50: 'nocapture'
                    {
                    match("nocapture"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARGUMENT_ATTRIBUTE"

    // $ANTLR start "PARAMETER_ATTRIBUTE"
    public final void mPARAMETER_ATTRIBUTE() throws RecognitionException {
        try {
            int _type = PARAMETER_ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:350:21: ( 'zeroext' | 'signext' | 'inreg' | 'byval' | 'sret' | 'nocapture' | 'nest' )
            int alt4=7;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:350:23: 'zeroext'
                    {
                    match("zeroext"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:350:35: 'signext'
                    {
                    match("signext"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:350:47: 'inreg'
                    {
                    match("inreg"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:350:57: 'byval'
                    {
                    match("byval"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:350:67: 'sret'
                    {
                    match("sret"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:350:76: 'nocapture'
                    {
                    match("nocapture"); 


                    }
                    break;
                case 7 :
                    // Resources/LLVM/llvmGrammar.g:350:90: 'nest'
                    {
                    match("nest"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARAMETER_ATTRIBUTE"

    // $ANTLR start "FUNCTION_ATTRIBUTE"
    public final void mFUNCTION_ATTRIBUTE() throws RecognitionException {
        try {
            int _type = FUNCTION_ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:351:20: ( 'nounwind' | 'uwtable' | 'noreturn' | 'readonly' | 'ssp' | 'optsize' )
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
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 6, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:351:22: 'nounwind'
                    {
                    match("nounwind"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:351:35: 'uwtable'
                    {
                    match("uwtable"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:351:46: 'noreturn'
                    {
                    match("noreturn"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:351:59: 'readonly'
                    {
                    match("readonly"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:351:72: 'ssp'
                    {
                    match("ssp"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:351:79: 'optsize'
                    {
                    match("optsize"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FUNCTION_ATTRIBUTE"

    // $ANTLR start "CALLING_CONV"
    public final void mCALLING_CONV() throws RecognitionException {
        try {
            int _type = CALLING_CONV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:352:14: ( 'ccc' | 'fastcc' | 'coldcc' | 'cc 10' | 'cc 11' )
            int alt6=5;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:352:16: 'ccc'
                    {
                    match("ccc"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:352:24: 'fastcc'
                    {
                    match("fastcc"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:352:35: 'coldcc'
                    {
                    match("coldcc"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:352:46: 'cc 10'
                    {
                    match("cc 10"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:352:56: 'cc 11'
                    {
                    match("cc 11"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CALLING_CONV"

    // $ANTLR start "CONDITION"
    public final void mCONDITION() throws RecognitionException {
        try {
            int _type = CONDITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:353:11: ( 'eq' | 'ne' | 'ugt' | 'uge' | 'ult' | 'ule' | 'sgt' | 'sge' | 'slt' | 'sle' | 'olt' | 'ogt' | 'oeq' | 'one' | 'oge' )
            int alt7=15;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:353:13: 'eq'
                    {
                    match("eq"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:353:20: 'ne'
                    {
                    match("ne"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:353:27: 'ugt'
                    {
                    match("ugt"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:353:35: 'uge'
                    {
                    match("uge"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:353:43: 'ult'
                    {
                    match("ult"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:353:51: 'ule'
                    {
                    match("ule"); 


                    }
                    break;
                case 7 :
                    // Resources/LLVM/llvmGrammar.g:353:59: 'sgt'
                    {
                    match("sgt"); 


                    }
                    break;
                case 8 :
                    // Resources/LLVM/llvmGrammar.g:353:67: 'sge'
                    {
                    match("sge"); 


                    }
                    break;
                case 9 :
                    // Resources/LLVM/llvmGrammar.g:353:75: 'slt'
                    {
                    match("slt"); 


                    }
                    break;
                case 10 :
                    // Resources/LLVM/llvmGrammar.g:353:82: 'sle'
                    {
                    match("sle"); 


                    }
                    break;
                case 11 :
                    // Resources/LLVM/llvmGrammar.g:353:89: 'olt'
                    {
                    match("olt"); 


                    }
                    break;
                case 12 :
                    // Resources/LLVM/llvmGrammar.g:353:96: 'ogt'
                    {
                    match("ogt"); 


                    }
                    break;
                case 13 :
                    // Resources/LLVM/llvmGrammar.g:353:103: 'oeq'
                    {
                    match("oeq"); 


                    }
                    break;
                case 14 :
                    // Resources/LLVM/llvmGrammar.g:353:110: 'one'
                    {
                    match("one"); 


                    }
                    break;
                case 15 :
                    // Resources/LLVM/llvmGrammar.g:353:117: 'oge'
                    {
                    match("oge"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONDITION"

    // $ANTLR start "NULL_CHAR"
    public final void mNULL_CHAR() throws RecognitionException {
        try {
            int _type = NULL_CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:354:11: ( '\\\\00' )
            // Resources/LLVM/llvmGrammar.g:354:13: '\\\\00'
            {
            match("\\00"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL_CHAR"

    // $ANTLR start "BIN_OPR_STR"
    public final void mBIN_OPR_STR() throws RecognitionException {
        try {
            int _type = BIN_OPR_STR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:355:13: ( ' add ' | 'fadd' | 'sub' | 'fsub' | 'mul' | 'fmul' | 'udiv' | 'sdiv' | 'fdiv' | 'urem' | 'srem' | 'frem' | ' xor ' | 'shl' | 'shr' | 'lshr' | 'rshr' | 'ashr' | 'and' | 'or' )
            int alt8=20;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:355:15: ' add '
                    {
                    match(" add "); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:355:25: 'fadd'
                    {
                    match("fadd"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:355:34: 'sub'
                    {
                    match("sub"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:355:42: 'fsub'
                    {
                    match("fsub"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:355:50: 'mul'
                    {
                    match("mul"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:355:58: 'fmul'
                    {
                    match("fmul"); 


                    }
                    break;
                case 7 :
                    // Resources/LLVM/llvmGrammar.g:355:66: 'udiv'
                    {
                    match("udiv"); 


                    }
                    break;
                case 8 :
                    // Resources/LLVM/llvmGrammar.g:355:73: 'sdiv'
                    {
                    match("sdiv"); 


                    }
                    break;
                case 9 :
                    // Resources/LLVM/llvmGrammar.g:355:80: 'fdiv'
                    {
                    match("fdiv"); 


                    }
                    break;
                case 10 :
                    // Resources/LLVM/llvmGrammar.g:355:88: 'urem'
                    {
                    match("urem"); 


                    }
                    break;
                case 11 :
                    // Resources/LLVM/llvmGrammar.g:355:95: 'srem'
                    {
                    match("srem"); 


                    }
                    break;
                case 12 :
                    // Resources/LLVM/llvmGrammar.g:355:102: 'frem'
                    {
                    match("frem"); 


                    }
                    break;
                case 13 :
                    // Resources/LLVM/llvmGrammar.g:355:111: ' xor '
                    {
                    match(" xor "); 


                    }
                    break;
                case 14 :
                    // Resources/LLVM/llvmGrammar.g:355:121: 'shl'
                    {
                    match("shl"); 


                    }
                    break;
                case 15 :
                    // Resources/LLVM/llvmGrammar.g:355:129: 'shr'
                    {
                    match("shr"); 


                    }
                    break;
                case 16 :
                    // Resources/LLVM/llvmGrammar.g:355:137: 'lshr'
                    {
                    match("lshr"); 


                    }
                    break;
                case 17 :
                    // Resources/LLVM/llvmGrammar.g:355:144: 'rshr'
                    {
                    match("rshr"); 


                    }
                    break;
                case 18 :
                    // Resources/LLVM/llvmGrammar.g:355:151: 'ashr'
                    {
                    match("ashr"); 


                    }
                    break;
                case 19 :
                    // Resources/LLVM/llvmGrammar.g:355:159: 'and'
                    {
                    match("and"); 


                    }
                    break;
                case 20 :
                    // Resources/LLVM/llvmGrammar.g:355:167: 'or'
                    {
                    match("or"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIN_OPR_STR"

    // $ANTLR start "ATOMIC_ORDERING"
    public final void mATOMIC_ORDERING() throws RecognitionException {
        try {
            int _type = ATOMIC_ORDERING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:356:17: ( 'unordered' | 'monotonic' | 'acquire' | 'release' | 'acq_rel' | 'seq_cst' )
            int alt9=6;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:356:19: 'unordered'
                    {
                    match("unordered"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:356:31: 'monotonic'
                    {
                    match("monotonic"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:356:43: 'acquire'
                    {
                    match("acquire"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:356:53: 'release'
                    {
                    match("release"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:356:63: 'acq_rel'
                    {
                    match("acq_rel"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:356:73: 'seq_cst'
                    {
                    match("seq_cst"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ATOMIC_ORDERING"

    // $ANTLR start "PRIMITIVE_DATA_TYPE"
    public final void mPRIMITIVE_DATA_TYPE() throws RecognitionException {
        try {
            int _type = PRIMITIVE_DATA_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:358:21: ( ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' ) )
            // Resources/LLVM/llvmGrammar.g:358:23: ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' )
            {
            // Resources/LLVM/llvmGrammar.g:358:23: ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' )
            int alt10=16;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:358:25: 'i1'
                    {
                    match("i1"); 


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:358:32: 'i8'
                    {
                    match("i8"); 


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:358:39: 'i16'
                    {
                    match("i16"); 


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:358:47: 'i32'
                    {
                    match("i32"); 


                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:358:55: 'i64'
                    {
                    match("i64"); 


                    }
                    break;
                case 6 :
                    // Resources/LLVM/llvmGrammar.g:358:63: 'float'
                    {
                    match("float"); 


                    }
                    break;
                case 7 :
                    // Resources/LLVM/llvmGrammar.g:358:73: 'double'
                    {
                    match("double"); 


                    }
                    break;
                case 8 :
                    // Resources/LLVM/llvmGrammar.g:358:84: 'void'
                    {
                    match("void"); 


                    }
                    break;
                case 9 :
                    // Resources/LLVM/llvmGrammar.g:359:9: 'label'
                    {
                    match("label"); 


                    }
                    break;
                case 10 :
                    // Resources/LLVM/llvmGrammar.g:359:18: 'f32'
                    {
                    match("f32"); 


                    }
                    break;
                case 11 :
                    // Resources/LLVM/llvmGrammar.g:359:25: 'f64'
                    {
                    match("f64"); 


                    }
                    break;
                case 12 :
                    // Resources/LLVM/llvmGrammar.g:359:31: 'f80'
                    {
                    match("f80"); 


                    }
                    break;
                case 13 :
                    // Resources/LLVM/llvmGrammar.g:359:37: 'v64'
                    {
                    match("v64"); 


                    }
                    break;
                case 14 :
                    // Resources/LLVM/llvmGrammar.g:359:43: 'v128'
                    {
                    match("v128"); 


                    }
                    break;
                case 15 :
                    // Resources/LLVM/llvmGrammar.g:359:50: 'a0'
                    {
                    match("a0"); 


                    }
                    break;
                case 16 :
                    // Resources/LLVM/llvmGrammar.g:359:55: 'n8'
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
        }
    }
    // $ANTLR end "PRIMITIVE_DATA_TYPE"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:361:7: ( '=' )
            // Resources/LLVM/llvmGrammar.g:361:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:362:7: ( ',' )
            // Resources/LLVM/llvmGrammar.g:362:9: ','
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

    // $ANTLR start "GLOBAL_PREFIX"
    public final void mGLOBAL_PREFIX() throws RecognitionException {
        try {
            int _type = GLOBAL_PREFIX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:363:15: ( '@' )
            // Resources/LLVM/llvmGrammar.g:363:17: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GLOBAL_PREFIX"

    // $ANTLR start "LOCAL_PREFIX"
    public final void mLOCAL_PREFIX() throws RecognitionException {
        try {
            int _type = LOCAL_PREFIX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:364:14: ( '%' )
            // Resources/LLVM/llvmGrammar.g:364:16: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOCAL_PREFIX"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:365:6: ( '*' )
            // Resources/LLVM/llvmGrammar.g:365:8: '*'
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

    // $ANTLR start "START_PARANTHESIS"
    public final void mSTART_PARANTHESIS() throws RecognitionException {
        try {
            int _type = START_PARANTHESIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:366:19: ( '(' )
            // Resources/LLVM/llvmGrammar.g:366:21: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START_PARANTHESIS"

    // $ANTLR start "END_PARANTHESIS"
    public final void mEND_PARANTHESIS() throws RecognitionException {
        try {
            int _type = END_PARANTHESIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:367:17: ( ')' )
            // Resources/LLVM/llvmGrammar.g:367:19: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_PARANTHESIS"

    // $ANTLR start "START_CURLY"
    public final void mSTART_CURLY() throws RecognitionException {
        try {
            int _type = START_CURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:368:13: ( '{' )
            // Resources/LLVM/llvmGrammar.g:368:15: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START_CURLY"

    // $ANTLR start "END_CURLY"
    public final void mEND_CURLY() throws RecognitionException {
        try {
            int _type = END_CURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:369:11: ( '}' )
            // Resources/LLVM/llvmGrammar.g:369:13: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_CURLY"

    // $ANTLR start "START_ANGULAR"
    public final void mSTART_ANGULAR() throws RecognitionException {
        try {
            int _type = START_ANGULAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:370:15: ( '<' )
            // Resources/LLVM/llvmGrammar.g:370:17: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START_ANGULAR"

    // $ANTLR start "END_ANGULAR"
    public final void mEND_ANGULAR() throws RecognitionException {
        try {
            int _type = END_ANGULAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:371:13: ( '>' )
            // Resources/LLVM/llvmGrammar.g:371:15: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_ANGULAR"

    // $ANTLR start "START_SQUARE_BR"
    public final void mSTART_SQUARE_BR() throws RecognitionException {
        try {
            int _type = START_SQUARE_BR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:372:17: ( '[' )
            // Resources/LLVM/llvmGrammar.g:372:19: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START_SQUARE_BR"

    // $ANTLR start "END_SQUARE_BR"
    public final void mEND_SQUARE_BR() throws RecognitionException {
        try {
            int _type = END_SQUARE_BR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:373:15: ( ']' )
            // Resources/LLVM/llvmGrammar.g:373:17: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_SQUARE_BR"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:374:6: ( '.' )
            // Resources/LLVM/llvmGrammar.g:374:8: '.'
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

    // $ANTLR start "MUL_OPERATOR"
    public final void mMUL_OPERATOR() throws RecognitionException {
        try {
            int _type = MUL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:375:14: ( ' x ' )
            // Resources/LLVM/llvmGrammar.g:375:16: ' x '
            {
            match(" x "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MUL_OPERATOR"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:376:9: ( ( '+' | '-' )? ( '0' .. '9' )+ )
            // Resources/LLVM/llvmGrammar.g:376:11: ( '+' | '-' )? ( '0' .. '9' )+
            {
            // Resources/LLVM/llvmGrammar.g:376:11: ( '+' | '-' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='+'||LA11_0=='-') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:
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

            // Resources/LLVM/llvmGrammar.g:376:22: ( '0' .. '9' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:376:22: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            int _type = DECIMAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:377:17: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // Resources/LLVM/llvmGrammar.g:377:19: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // Resources/LLVM/llvmGrammar.g:377:19: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='0') ) {
                alt14=1;
            }
            else if ( ((LA14_0>='1' && LA14_0<='9')) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:377:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:377:26: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // Resources/LLVM/llvmGrammar.g:377:35: ( '0' .. '9' )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:377:35: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:377:46: ( IntegerTypeSuffix )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='L'||LA15_0=='U'||LA15_0=='l'||LA15_0=='u') ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:377:46: IntegerTypeSuffix
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
            // Resources/LLVM/llvmGrammar.g:380:15: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // Resources/LLVM/llvmGrammar.g:380:17: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            // Resources/LLVM/llvmGrammar.g:380:21: ( '0' .. '7' )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='0' && LA16_0<='7')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:380:22: '0' .. '7'
            	    {
            	    matchRange('0','7'); 

            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);

            // Resources/LLVM/llvmGrammar.g:380:33: ( IntegerTypeSuffix )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='L'||LA17_0=='U'||LA17_0=='l'||LA17_0=='u') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:380:33: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_LITERAL"

    // $ANTLR start "HEX_LITERAL"
    public final void mHEX_LITERAL() throws RecognitionException {
        try {
            // Resources/LLVM/llvmGrammar.g:383:13: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // Resources/LLVM/llvmGrammar.g:383:15: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Resources/LLVM/llvmGrammar.g:383:29: ( HexDigit )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='0' && LA18_0<='9')||(LA18_0>='A' && LA18_0<='F')||(LA18_0>='a' && LA18_0<='f')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:383:29: HexDigit
            	    {
            	    mHexDigit(); 

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

            // Resources/LLVM/llvmGrammar.g:383:39: ( IntegerTypeSuffix )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='L'||LA19_0=='U'||LA19_0=='l'||LA19_0=='u') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:383:39: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_LITERAL"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // Resources/LLVM/llvmGrammar.g:386:10: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // Resources/LLVM/llvmGrammar.g:386:12: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // Resources/LLVM/llvmGrammar.g:390:2: ( ( 'u' | 'U' )? ( 'l' | 'L' ) | ( 'u' | 'U' ) ( 'l' | 'L' )? )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='U'||LA22_0=='u') ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1=='L'||LA22_1=='l') ) {
                    alt22=1;
                }
                else {
                    alt22=2;}
            }
            else if ( (LA22_0=='L'||LA22_0=='l') ) {
                alt22=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:390:4: ( 'u' | 'U' )? ( 'l' | 'L' )
                    {
                    // Resources/LLVM/llvmGrammar.g:390:4: ( 'u' | 'U' )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='U'||LA20_0=='u') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:
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
                    // Resources/LLVM/llvmGrammar.g:391:4: ( 'u' | 'U' ) ( 'l' | 'L' )?
                    {
                    if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // Resources/LLVM/llvmGrammar.g:391:15: ( 'l' | 'L' )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0=='L'||LA21_0=='l') ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:
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
            // Resources/LLVM/llvmGrammar.g:395:5: ( ( '+' | '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix | HEX_LITERAL )
            int alt35=5;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:395:8: ( '+' | '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                    {
                    // Resources/LLVM/llvmGrammar.g:395:8: ( '+' | '-' )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0=='+'||LA23_0=='-') ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:
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

                    // Resources/LLVM/llvmGrammar.g:395:20: ( '0' .. '9' )+
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
                    	    // Resources/LLVM/llvmGrammar.g:395:21: '0' .. '9'
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

                    match('.'); 
                    // Resources/LLVM/llvmGrammar.g:395:36: ( '0' .. '9' )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:395:37: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    // Resources/LLVM/llvmGrammar.g:395:48: ( Exponent )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0=='E'||LA26_0=='e') ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:395:48: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // Resources/LLVM/llvmGrammar.g:395:58: ( FloatTypeSuffix )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0=='D'||LA27_0=='F'||LA27_0=='d'||LA27_0=='f') ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:395:58: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:396:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                    {
                    match('.'); 
                    // Resources/LLVM/llvmGrammar.g:396:13: ( '0' .. '9' )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( ((LA28_0>='0' && LA28_0<='9')) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:396:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);

                    // Resources/LLVM/llvmGrammar.g:396:25: ( Exponent )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0=='E'||LA29_0=='e') ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:396:25: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // Resources/LLVM/llvmGrammar.g:396:35: ( FloatTypeSuffix )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0=='D'||LA30_0=='F'||LA30_0=='d'||LA30_0=='f') ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:396:35: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:397:9: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
                    {
                    // Resources/LLVM/llvmGrammar.g:397:9: ( '0' .. '9' )+
                    int cnt31=0;
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( ((LA31_0>='0' && LA31_0<='9')) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:397:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt31 >= 1 ) break loop31;
                                EarlyExitException eee =
                                    new EarlyExitException(31, input);
                                throw eee;
                        }
                        cnt31++;
                    } while (true);

                    mExponent(); 
                    // Resources/LLVM/llvmGrammar.g:397:30: ( FloatTypeSuffix )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0=='D'||LA32_0=='F'||LA32_0=='d'||LA32_0=='f') ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:397:30: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:398:9: ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix
                    {
                    // Resources/LLVM/llvmGrammar.g:398:9: ( '0' .. '9' )+
                    int cnt33=0;
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( ((LA33_0>='0' && LA33_0<='9')) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:398:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt33 >= 1 ) break loop33;
                                EarlyExitException eee =
                                    new EarlyExitException(33, input);
                                throw eee;
                        }
                        cnt33++;
                    } while (true);

                    // Resources/LLVM/llvmGrammar.g:398:21: ( Exponent )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0=='E'||LA34_0=='e') ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:398:21: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    mFloatTypeSuffix(); 

                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:399:6: HEX_LITERAL
                    {
                    mHEX_LITERAL(); 

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
            // Resources/LLVM/llvmGrammar.g:403:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // Resources/LLVM/llvmGrammar.g:403:13: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Resources/LLVM/llvmGrammar.g:403:23: ( '+' | '-' )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0=='+'||LA36_0=='-') ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:
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

            // Resources/LLVM/llvmGrammar.g:403:34: ( '0' .. '9' )+
            int cnt37=0;
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( ((LA37_0>='0' && LA37_0<='9')) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:403:35: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt37 >= 1 ) break loop37;
                        EarlyExitException eee =
                            new EarlyExitException(37, input);
                        throw eee;
                }
                cnt37++;
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
            // Resources/LLVM/llvmGrammar.g:406:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // Resources/LLVM/llvmGrammar.g:406:19: ( 'f' | 'F' | 'd' | 'D' )
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

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:408:16: ( '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"' )
            // Resources/LLVM/llvmGrammar.g:408:19: '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // Resources/LLVM/llvmGrammar.g:408:23: ( EscapeSequence | ~ ( '\\\\' | '\"' ) )*
            loop38:
            do {
                int alt38=3;
                int LA38_0 = input.LA(1);

                if ( (LA38_0=='\\') ) {
                    alt38=1;
                }
                else if ( ((LA38_0>='\u0000' && LA38_0<='!')||(LA38_0>='#' && LA38_0<='[')||(LA38_0>=']' && LA38_0<='\uFFFF')) ) {
                    alt38=2;
                }


                switch (alt38) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:408:25: EscapeSequence
            	    {
            	    mEscapeSequence(); 

            	    }
            	    break;
            	case 2 :
            	    // Resources/LLVM/llvmGrammar.g:408:42: ~ ( '\\\\' | '\"' )
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
            	    break loop38;
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
            // Resources/LLVM/llvmGrammar.g:411:16: ( '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OctalEscape )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0=='\\') ) {
                int LA39_1 = input.LA(2);

                if ( (LA39_1=='\"'||LA39_1=='\''||LA39_1=='\\'||(LA39_1>='a' && LA39_1<='b')||LA39_1=='f'||LA39_1=='n'||LA39_1=='r'||LA39_1=='t') ) {
                    alt39=1;
                }
                else if ( ((LA39_1>='0' && LA39_1<='7')) ) {
                    alt39=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:411:20: '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // Resources/LLVM/llvmGrammar.g:412:14: OctalEscape
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
            // Resources/LLVM/llvmGrammar.g:416:13: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt40=3;
            int LA40_0 = input.LA(1);

            if ( (LA40_0=='\\') ) {
                int LA40_1 = input.LA(2);

                if ( ((LA40_1>='0' && LA40_1<='3')) ) {
                    int LA40_2 = input.LA(3);

                    if ( ((LA40_2>='0' && LA40_2<='7')) ) {
                        int LA40_4 = input.LA(4);

                        if ( ((LA40_4>='0' && LA40_4<='7')) ) {
                            alt40=1;
                        }
                        else {
                            alt40=2;}
                    }
                    else {
                        alt40=3;}
                }
                else if ( ((LA40_1>='4' && LA40_1<='7')) ) {
                    int LA40_3 = input.LA(3);

                    if ( ((LA40_3>='0' && LA40_3<='7')) ) {
                        alt40=2;
                    }
                    else {
                        alt40=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:416:17: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // Resources/LLVM/llvmGrammar.g:416:22: ( '0' .. '3' )
                    // Resources/LLVM/llvmGrammar.g:416:23: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // Resources/LLVM/llvmGrammar.g:416:33: ( '0' .. '7' )
                    // Resources/LLVM/llvmGrammar.g:416:34: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // Resources/LLVM/llvmGrammar.g:416:44: ( '0' .. '7' )
                    // Resources/LLVM/llvmGrammar.g:416:45: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:417:11: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // Resources/LLVM/llvmGrammar.g:417:16: ( '0' .. '7' )
                    // Resources/LLVM/llvmGrammar.g:417:17: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // Resources/LLVM/llvmGrammar.g:417:27: ( '0' .. '7' )
                    // Resources/LLVM/llvmGrammar.g:417:28: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:418:11: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // Resources/LLVM/llvmGrammar.g:418:16: ( '0' .. '7' )
                    // Resources/LLVM/llvmGrammar.g:418:17: '0' .. '7'
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
            // Resources/LLVM/llvmGrammar.g:423:2: ( ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )+ )
            // Resources/LLVM/llvmGrammar.g:423:3: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )+
            {
            // Resources/LLVM/llvmGrammar.g:423:3: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )+
            int cnt41=0;
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0=='$'||(LA41_0>='A' && LA41_0<='Z')||LA41_0=='_'||(LA41_0>='a' && LA41_0<='z')) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt41 >= 1 ) break loop41;
                        EarlyExitException eee =
                            new EarlyExitException(41, input);
                        throw eee;
                }
                cnt41++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:429:4: ( LETTER ( LETTER | '0' .. '9' | '.' )* )
            // Resources/LLVM/llvmGrammar.g:429:5: LETTER ( LETTER | '0' .. '9' | '.' )*
            {
            mLETTER(); 
            // Resources/LLVM/llvmGrammar.g:429:12: ( LETTER | '0' .. '9' | '.' )*
            loop42:
            do {
                int alt42=4;
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
                    alt42=1;
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
                    alt42=2;
                    }
                    break;
                case '.':
                    {
                    alt42=3;
                    }
                    break;

                }

                switch (alt42) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:429:13: LETTER
            	    {
            	    mLETTER(); 

            	    }
            	    break;
            	case 2 :
            	    // Resources/LLVM/llvmGrammar.g:429:20: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;
            	case 3 :
            	    // Resources/LLVM/llvmGrammar.g:429:29: '.'
            	    {
            	    match('.'); 

            	    }
            	    break;

            	default :
            	    break loop42;
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

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:431:12: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Resources/LLVM/llvmGrammar.g:431:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Resources/LLVM/llvmGrammar.g:431:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt43=0;
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>='\t' && LA43_0<='\n')||(LA43_0>='\f' && LA43_0<='\r')||LA43_0==' ') ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt43 >= 1 ) break loop43;
                        EarlyExitException eee =
                            new EarlyExitException(43, input);
                        throw eee;
                }
                cnt43++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Resources/LLVM/llvmGrammar.g:433:14: ( ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // Resources/LLVM/llvmGrammar.g:433:16: ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match(';'); 
            // Resources/LLVM/llvmGrammar.g:433:20: (~ ( '\\n' | '\\r' ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0>='\u0000' && LA44_0<='\t')||(LA44_0>='\u000B' && LA44_0<='\f')||(LA44_0>='\u000E' && LA44_0<='\uFFFF')) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:433:20: ~ ( '\\n' | '\\r' )
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
            	    break loop44;
                }
            } while (true);

            // Resources/LLVM/llvmGrammar.g:433:34: ( '\\r' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0=='\r') ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:433:34: '\\r'
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
        }
    }
    // $ANTLR end "LINE_COMMENT"

    public void mTokens() throws RecognitionException {
        // Resources/LLVM/llvmGrammar.g:1:8: ( T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | PHI | UNREACHABLE | TRUE | FALSE | ALIGN | LINKAGE_TYPE | RETURN_ATTR | ZEROINITIALIZER | CAST_TYPE | ARGUMENT_ATTRIBUTE | PARAMETER_ATTRIBUTE | FUNCTION_ATTRIBUTE | CALLING_CONV | CONDITION | NULL_CHAR | BIN_OPR_STR | ATOMIC_ORDERING | PRIMITIVE_DATA_TYPE | EQUAL | COMMA | GLOBAL_PREFIX | LOCAL_PREFIX | STAR | START_PARANTHESIS | END_PARANTHESIS | START_CURLY | END_CURLY | START_ANGULAR | END_ANGULAR | START_SQUARE_BR | END_SQUARE_BR | DOT | MUL_OPERATOR | NUMBER | DECIMAL_LITERAL | FLOATING_LITERAL | STRING_LITERAL | ID | WHITESPACE | LINE_COMMENT )
        int alt46=74;
        alt46 = dfa46.predict(input);
        switch (alt46) {
            case 1 :
                // Resources/LLVM/llvmGrammar.g:1:10: T__54
                {
                mT__54(); 

                }
                break;
            case 2 :
                // Resources/LLVM/llvmGrammar.g:1:16: T__55
                {
                mT__55(); 

                }
                break;
            case 3 :
                // Resources/LLVM/llvmGrammar.g:1:22: T__56
                {
                mT__56(); 

                }
                break;
            case 4 :
                // Resources/LLVM/llvmGrammar.g:1:28: T__57
                {
                mT__57(); 

                }
                break;
            case 5 :
                // Resources/LLVM/llvmGrammar.g:1:34: T__58
                {
                mT__58(); 

                }
                break;
            case 6 :
                // Resources/LLVM/llvmGrammar.g:1:40: T__59
                {
                mT__59(); 

                }
                break;
            case 7 :
                // Resources/LLVM/llvmGrammar.g:1:46: T__60
                {
                mT__60(); 

                }
                break;
            case 8 :
                // Resources/LLVM/llvmGrammar.g:1:52: T__61
                {
                mT__61(); 

                }
                break;
            case 9 :
                // Resources/LLVM/llvmGrammar.g:1:58: T__62
                {
                mT__62(); 

                }
                break;
            case 10 :
                // Resources/LLVM/llvmGrammar.g:1:64: T__63
                {
                mT__63(); 

                }
                break;
            case 11 :
                // Resources/LLVM/llvmGrammar.g:1:70: T__64
                {
                mT__64(); 

                }
                break;
            case 12 :
                // Resources/LLVM/llvmGrammar.g:1:76: T__65
                {
                mT__65(); 

                }
                break;
            case 13 :
                // Resources/LLVM/llvmGrammar.g:1:82: T__66
                {
                mT__66(); 

                }
                break;
            case 14 :
                // Resources/LLVM/llvmGrammar.g:1:88: T__67
                {
                mT__67(); 

                }
                break;
            case 15 :
                // Resources/LLVM/llvmGrammar.g:1:94: T__68
                {
                mT__68(); 

                }
                break;
            case 16 :
                // Resources/LLVM/llvmGrammar.g:1:100: T__69
                {
                mT__69(); 

                }
                break;
            case 17 :
                // Resources/LLVM/llvmGrammar.g:1:106: T__70
                {
                mT__70(); 

                }
                break;
            case 18 :
                // Resources/LLVM/llvmGrammar.g:1:112: T__71
                {
                mT__71(); 

                }
                break;
            case 19 :
                // Resources/LLVM/llvmGrammar.g:1:118: T__72
                {
                mT__72(); 

                }
                break;
            case 20 :
                // Resources/LLVM/llvmGrammar.g:1:124: T__73
                {
                mT__73(); 

                }
                break;
            case 21 :
                // Resources/LLVM/llvmGrammar.g:1:130: T__74
                {
                mT__74(); 

                }
                break;
            case 22 :
                // Resources/LLVM/llvmGrammar.g:1:136: T__75
                {
                mT__75(); 

                }
                break;
            case 23 :
                // Resources/LLVM/llvmGrammar.g:1:142: T__76
                {
                mT__76(); 

                }
                break;
            case 24 :
                // Resources/LLVM/llvmGrammar.g:1:148: T__77
                {
                mT__77(); 

                }
                break;
            case 25 :
                // Resources/LLVM/llvmGrammar.g:1:154: T__78
                {
                mT__78(); 

                }
                break;
            case 26 :
                // Resources/LLVM/llvmGrammar.g:1:160: T__79
                {
                mT__79(); 

                }
                break;
            case 27 :
                // Resources/LLVM/llvmGrammar.g:1:166: T__80
                {
                mT__80(); 

                }
                break;
            case 28 :
                // Resources/LLVM/llvmGrammar.g:1:172: T__81
                {
                mT__81(); 

                }
                break;
            case 29 :
                // Resources/LLVM/llvmGrammar.g:1:178: T__82
                {
                mT__82(); 

                }
                break;
            case 30 :
                // Resources/LLVM/llvmGrammar.g:1:184: T__83
                {
                mT__83(); 

                }
                break;
            case 31 :
                // Resources/LLVM/llvmGrammar.g:1:190: T__84
                {
                mT__84(); 

                }
                break;
            case 32 :
                // Resources/LLVM/llvmGrammar.g:1:196: T__85
                {
                mT__85(); 

                }
                break;
            case 33 :
                // Resources/LLVM/llvmGrammar.g:1:202: T__86
                {
                mT__86(); 

                }
                break;
            case 34 :
                // Resources/LLVM/llvmGrammar.g:1:208: T__87
                {
                mT__87(); 

                }
                break;
            case 35 :
                // Resources/LLVM/llvmGrammar.g:1:214: PHI
                {
                mPHI(); 

                }
                break;
            case 36 :
                // Resources/LLVM/llvmGrammar.g:1:218: UNREACHABLE
                {
                mUNREACHABLE(); 

                }
                break;
            case 37 :
                // Resources/LLVM/llvmGrammar.g:1:230: TRUE
                {
                mTRUE(); 

                }
                break;
            case 38 :
                // Resources/LLVM/llvmGrammar.g:1:235: FALSE
                {
                mFALSE(); 

                }
                break;
            case 39 :
                // Resources/LLVM/llvmGrammar.g:1:241: ALIGN
                {
                mALIGN(); 

                }
                break;
            case 40 :
                // Resources/LLVM/llvmGrammar.g:1:247: LINKAGE_TYPE
                {
                mLINKAGE_TYPE(); 

                }
                break;
            case 41 :
                // Resources/LLVM/llvmGrammar.g:1:260: RETURN_ATTR
                {
                mRETURN_ATTR(); 

                }
                break;
            case 42 :
                // Resources/LLVM/llvmGrammar.g:1:272: ZEROINITIALIZER
                {
                mZEROINITIALIZER(); 

                }
                break;
            case 43 :
                // Resources/LLVM/llvmGrammar.g:1:288: CAST_TYPE
                {
                mCAST_TYPE(); 

                }
                break;
            case 44 :
                // Resources/LLVM/llvmGrammar.g:1:298: ARGUMENT_ATTRIBUTE
                {
                mARGUMENT_ATTRIBUTE(); 

                }
                break;
            case 45 :
                // Resources/LLVM/llvmGrammar.g:1:317: PARAMETER_ATTRIBUTE
                {
                mPARAMETER_ATTRIBUTE(); 

                }
                break;
            case 46 :
                // Resources/LLVM/llvmGrammar.g:1:337: FUNCTION_ATTRIBUTE
                {
                mFUNCTION_ATTRIBUTE(); 

                }
                break;
            case 47 :
                // Resources/LLVM/llvmGrammar.g:1:356: CALLING_CONV
                {
                mCALLING_CONV(); 

                }
                break;
            case 48 :
                // Resources/LLVM/llvmGrammar.g:1:369: CONDITION
                {
                mCONDITION(); 

                }
                break;
            case 49 :
                // Resources/LLVM/llvmGrammar.g:1:379: NULL_CHAR
                {
                mNULL_CHAR(); 

                }
                break;
            case 50 :
                // Resources/LLVM/llvmGrammar.g:1:389: BIN_OPR_STR
                {
                mBIN_OPR_STR(); 

                }
                break;
            case 51 :
                // Resources/LLVM/llvmGrammar.g:1:401: ATOMIC_ORDERING
                {
                mATOMIC_ORDERING(); 

                }
                break;
            case 52 :
                // Resources/LLVM/llvmGrammar.g:1:417: PRIMITIVE_DATA_TYPE
                {
                mPRIMITIVE_DATA_TYPE(); 

                }
                break;
            case 53 :
                // Resources/LLVM/llvmGrammar.g:1:437: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 54 :
                // Resources/LLVM/llvmGrammar.g:1:443: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 55 :
                // Resources/LLVM/llvmGrammar.g:1:449: GLOBAL_PREFIX
                {
                mGLOBAL_PREFIX(); 

                }
                break;
            case 56 :
                // Resources/LLVM/llvmGrammar.g:1:463: LOCAL_PREFIX
                {
                mLOCAL_PREFIX(); 

                }
                break;
            case 57 :
                // Resources/LLVM/llvmGrammar.g:1:476: STAR
                {
                mSTAR(); 

                }
                break;
            case 58 :
                // Resources/LLVM/llvmGrammar.g:1:481: START_PARANTHESIS
                {
                mSTART_PARANTHESIS(); 

                }
                break;
            case 59 :
                // Resources/LLVM/llvmGrammar.g:1:499: END_PARANTHESIS
                {
                mEND_PARANTHESIS(); 

                }
                break;
            case 60 :
                // Resources/LLVM/llvmGrammar.g:1:515: START_CURLY
                {
                mSTART_CURLY(); 

                }
                break;
            case 61 :
                // Resources/LLVM/llvmGrammar.g:1:527: END_CURLY
                {
                mEND_CURLY(); 

                }
                break;
            case 62 :
                // Resources/LLVM/llvmGrammar.g:1:537: START_ANGULAR
                {
                mSTART_ANGULAR(); 

                }
                break;
            case 63 :
                // Resources/LLVM/llvmGrammar.g:1:551: END_ANGULAR
                {
                mEND_ANGULAR(); 

                }
                break;
            case 64 :
                // Resources/LLVM/llvmGrammar.g:1:563: START_SQUARE_BR
                {
                mSTART_SQUARE_BR(); 

                }
                break;
            case 65 :
                // Resources/LLVM/llvmGrammar.g:1:579: END_SQUARE_BR
                {
                mEND_SQUARE_BR(); 

                }
                break;
            case 66 :
                // Resources/LLVM/llvmGrammar.g:1:593: DOT
                {
                mDOT(); 

                }
                break;
            case 67 :
                // Resources/LLVM/llvmGrammar.g:1:597: MUL_OPERATOR
                {
                mMUL_OPERATOR(); 

                }
                break;
            case 68 :
                // Resources/LLVM/llvmGrammar.g:1:610: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 69 :
                // Resources/LLVM/llvmGrammar.g:1:617: DECIMAL_LITERAL
                {
                mDECIMAL_LITERAL(); 

                }
                break;
            case 70 :
                // Resources/LLVM/llvmGrammar.g:1:633: FLOATING_LITERAL
                {
                mFLOATING_LITERAL(); 

                }
                break;
            case 71 :
                // Resources/LLVM/llvmGrammar.g:1:650: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;
            case 72 :
                // Resources/LLVM/llvmGrammar.g:1:665: ID
                {
                mID(); 

                }
                break;
            case 73 :
                // Resources/LLVM/llvmGrammar.g:1:668: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 74 :
                // Resources/LLVM/llvmGrammar.g:1:679: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA2 dfa2 = new DFA2(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA46 dfa46 = new DFA46(this);
    static final String DFA1_eotS =
        "\11\uffff\1\13\2\uffff";
    static final String DFA1_eofS =
        "\14\uffff";
    static final String DFA1_minS =
        "\1\143\2\uffff\1\170\1\uffff\1\164\1\145\1\162\1\156\1\141\2\uffff";
    static final String DFA1_maxS =
        "\1\160\2\uffff\1\170\1\uffff\1\164\1\145\1\162\1\156\1\141\2\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\5\uffff\1\5\1\3";
    static final String DFA1_specialS =
        "\14\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\2\1\uffff\1\3\3\uffff\1\4\6\uffff\1\1",
            "",
            "",
            "\1\5",
            "",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "345:1: LINKAGE_TYPE : ( 'private' | 'common' | 'extern' | 'internal' | 'external' );";
        }
    }
    static final String DFA2_eotS =
        "\22\uffff";
    static final String DFA2_eofS =
        "\22\uffff";
    static final String DFA2_minS =
        "\1\142\2\uffff\1\145\1\160\6\uffff\1\145\1\157\2\uffff\1\163\2"+
        "\uffff";
    static final String DFA2_maxS =
        "\1\172\2\uffff\1\151\1\160\6\uffff\1\164\1\162\2\uffff\1\165\2"+
        "\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\1\2\2\uffff\1\10\1\12\1\13\1\14\1\3\1\11\2\uffff\1"+
        "\5\1\4\1\uffff\1\6\1\7";
    static final String DFA2_specialS =
        "\22\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\10\3\uffff\1\4\2\uffff\1\7\6\uffff\1\6\2\uffff\1\3\1\1\1"+
            "\5\4\uffff\1\2",
            "",
            "",
            "\1\11\3\uffff\1\12",
            "\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\15\16\uffff\1\14",
            "\1\17\2\uffff\1\16",
            "",
            "",
            "\1\21\1\uffff\1\20",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "348:1: CAST_TYPE : ( 'trunc' | 'zext' | 'sext' | 'fptrunc' | 'fpext' | 'fptoui' | 'fptosi' | 'uitofp' | 'sitofp' | 'ptrtoint' | 'inttoptr' | 'bitcast' );";
        }
    }
    static final String DFA4_eotS =
        "\12\uffff";
    static final String DFA4_eofS =
        "\12\uffff";
    static final String DFA4_minS =
        "\1\142\1\uffff\1\151\2\uffff\1\145\4\uffff";
    static final String DFA4_maxS =
        "\1\172\1\uffff\1\162\2\uffff\1\157\4\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\1\uffff\1\2\1\5\1\6\1\7";
    static final String DFA4_specialS =
        "\12\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\4\6\uffff\1\3\4\uffff\1\5\4\uffff\1\2\6\uffff\1\1",
            "",
            "\1\6\10\uffff\1\7",
            "",
            "",
            "\1\11\11\uffff\1\10",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "350:1: PARAMETER_ATTRIBUTE : ( 'zeroext' | 'signext' | 'inreg' | 'byval' | 'sret' | 'nocapture' | 'nest' );";
        }
    }
    static final String DFA6_eotS =
        "\12\uffff";
    static final String DFA6_eofS =
        "\12\uffff";
    static final String DFA6_minS =
        "\2\143\1\uffff\1\40\2\uffff\1\61\1\60\2\uffff";
    static final String DFA6_maxS =
        "\1\146\1\157\1\uffff\1\143\2\uffff\2\61\2\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\2\1\uffff\1\3\1\1\2\uffff\1\4\1\5";
    static final String DFA6_specialS =
        "\12\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\2\uffff\1\2",
            "\1\3\13\uffff\1\4",
            "",
            "\1\6\102\uffff\1\5",
            "",
            "",
            "\1\7",
            "\1\10\1\11",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "352:1: CALLING_CONV : ( 'ccc' | 'fastcc' | 'coldcc' | 'cc 10' | 'cc 11' );";
        }
    }
    static final String DFA7_eotS =
        "\30\uffff";
    static final String DFA7_eofS =
        "\30\uffff";
    static final String DFA7_minS =
        "\1\145\2\uffff\2\147\5\145\1\uffff\1\145\14\uffff";
    static final String DFA7_maxS =
        "\1\165\2\uffff\2\154\1\156\4\164\1\uffff\1\164\14\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\1\2\7\uffff\1\13\1\uffff\1\15\1\16\1\3\1\4\1\5\1\6"+
        "\1\7\1\10\1\11\1\12\1\14\1\17";
    static final String DFA7_specialS =
        "\30\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\10\uffff\1\2\1\5\3\uffff\1\4\1\uffff\1\3",
            "",
            "",
            "\1\6\4\uffff\1\7",
            "\1\10\4\uffff\1\11",
            "\1\14\1\uffff\1\13\4\uffff\1\12\1\uffff\1\15",
            "\1\17\16\uffff\1\16",
            "\1\21\16\uffff\1\20",
            "\1\23\16\uffff\1\22",
            "\1\25\16\uffff\1\24",
            "",
            "\1\27\16\uffff\1\26",
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
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "353:1: CONDITION : ( 'eq' | 'ne' | 'ugt' | 'uge' | 'ult' | 'ule' | 'sgt' | 'sge' | 'slt' | 'sle' | 'olt' | 'ogt' | 'oeq' | 'one' | 'oge' );";
        }
    }
    static final String DFA8_eotS =
        "\33\uffff";
    static final String DFA8_eofS =
        "\33\uffff";
    static final String DFA8_minS =
        "\1\40\2\141\1\144\1\uffff\1\144\2\uffff\1\156\13\uffff\1\154\6"+
        "\uffff";
    static final String DFA8_maxS =
        "\1\165\1\170\1\163\1\165\1\uffff\1\162\2\uffff\1\163\13\uffff\1"+
        "\162\6\uffff";
    static final String DFA8_acceptS =
        "\4\uffff\1\5\1\uffff\1\20\1\21\1\uffff\1\24\1\1\1\15\1\2\1\4\1"+
        "\6\1\11\1\14\1\3\1\10\1\13\1\uffff\1\7\1\12\1\22\1\23\1\16\1\17";
    static final String DFA8_specialS =
        "\33\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\1\100\uffff\1\10\4\uffff\1\2\5\uffff\1\6\1\4\1\uffff\1\11"+
            "\2\uffff\1\7\1\3\1\uffff\1\5",
            "\1\12\26\uffff\1\13",
            "\1\14\2\uffff\1\17\10\uffff\1\16\4\uffff\1\20\1\15",
            "\1\22\3\uffff\1\24\11\uffff\1\23\2\uffff\1\21",
            "",
            "\1\25\15\uffff\1\26",
            "",
            "",
            "\1\30\4\uffff\1\27",
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
            "\1\31\5\uffff\1\32",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "355:1: BIN_OPR_STR : ( ' add ' | 'fadd' | 'sub' | 'fsub' | 'mul' | 'fmul' | 'udiv' | 'sdiv' | 'fdiv' | 'urem' | 'srem' | 'frem' | ' xor ' | 'shl' | 'shr' | 'lshr' | 'rshr' | 'ashr' | 'and' | 'or' );";
        }
    }
    static final String DFA9_eotS =
        "\12\uffff";
    static final String DFA9_eofS =
        "\12\uffff";
    static final String DFA9_minS =
        "\1\141\2\uffff\1\143\2\uffff\1\161\1\137\2\uffff";
    static final String DFA9_maxS =
        "\1\165\2\uffff\1\143\2\uffff\1\161\1\165\2\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\1\6\2\uffff\1\3\1\5";
    static final String DFA9_specialS =
        "\12\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\3\13\uffff\1\2\4\uffff\1\4\1\5\1\uffff\1\1",
            "",
            "",
            "\1\6",
            "",
            "",
            "\1\7",
            "\1\11\25\uffff\1\10",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "356:1: ATOMIC_ORDERING : ( 'unordered' | 'monotonic' | 'acquire' | 'release' | 'acq_rel' | 'seq_cst' );";
        }
    }
    static final String DFA10_eotS =
        "\10\uffff\1\24\14\uffff";
    static final String DFA10_eofS =
        "\25\uffff";
    static final String DFA10_minS =
        "\1\141\1\61\1\63\1\uffff\1\61\3\uffff\1\66\14\uffff";
    static final String DFA10_maxS =
        "\1\166\1\70\1\154\1\uffff\1\157\3\uffff\1\66\14\uffff";
    static final String DFA10_acceptS =
        "\3\uffff\1\7\1\uffff\1\11\1\17\1\20\1\uffff\1\2\1\4\1\5\1\6\1\12"+
        "\1\13\1\14\1\10\1\15\1\16\1\3\1\1";
    static final String DFA10_specialS =
        "\25\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\6\2\uffff\1\3\1\uffff\1\2\2\uffff\1\1\2\uffff\1\5\1\uffff"+
            "\1\7\7\uffff\1\4",
            "\1\10\1\uffff\1\12\2\uffff\1\13\1\uffff\1\11",
            "\1\15\2\uffff\1\16\1\uffff\1\17\63\uffff\1\14",
            "",
            "\1\22\4\uffff\1\21\70\uffff\1\20",
            "",
            "",
            "",
            "\1\23",
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
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "358:23: ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void' | 'label' | 'f32' | 'f64' | 'f80' | 'v64' | 'v128' | 'a0' | 'n8' )";
        }
    }
    static final String DFA35_eotS =
        "\11\uffff\1\12\2\uffff";
    static final String DFA35_eofS =
        "\14\uffff";
    static final String DFA35_minS =
        "\1\53\1\uffff\1\56\1\uffff\1\56\1\uffff\1\53\1\uffff\2\60\2\uffff";
    static final String DFA35_maxS =
        "\1\71\1\uffff\1\170\1\uffff\1\146\1\uffff\1\71\1\uffff\1\71\1\146"+
        "\2\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\1\uffff\1\5\1\uffff\1\4\2\uffff\2\3";
    static final String DFA35_specialS =
        "\14\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\1\uffff\1\1\1\3\1\uffff\1\2\11\4",
            "",
            "\1\1\1\uffff\12\4\12\uffff\1\7\1\6\1\7\21\uffff\1\5\13\uffff"+
            "\1\7\1\6\1\7\21\uffff\1\5",
            "",
            "\1\1\1\uffff\12\4\12\uffff\1\7\1\6\1\7\35\uffff\1\7\1\6\1"+
            "\7",
            "",
            "\1\10\1\uffff\1\10\2\uffff\12\11",
            "",
            "\12\11",
            "\12\11\12\uffff\1\13\1\uffff\1\13\35\uffff\1\13\1\uffff\1"+
            "\13",
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
            return "394:1: FLOATING_LITERAL : ( ( '+' | '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix | HEX_LITERAL );";
        }
    }
    static final String DFA46_eotS =
        "\1\uffff\1\53\1\54\1\66\1\uffff\1\53\1\71\1\53\1\76\17\53\1\uffff"+
        "\2\53\16\uffff\2\u008a\3\uffff\1\53\1\u0090\2\53\5\uffff\1\u008a"+
        "\1\53\2\uffff\2\53\3\uffff\3\53\1\u00a1\1\u00a2\1\53\1\u00a1\25"+
        "\53\2\u00a2\13\53\1\u00d7\31\53\1\u0094\5\53\1\u00a2\1\uffff\1\u008a"+
        "\1\uffff\1\u008a\2\53\1\uffff\2\53\3\uffff\4\53\1\u0109\1\u010a"+
        "\5\53\2\uffff\16\53\1\u011f\4\u00a1\1\u0094\1\53\2\u0094\2\53\1"+
        "\u00a2\7\53\3\u00a2\6\53\4\u00a1\2\53\1\u0133\3\53\1\uffff\15\53"+
        "\3\u00a2\4\53\1\u00ed\1\uffff\1\u0149\5\53\5\u00a1\1\u0094\2\53"+
        "\1\u0094\2\53\1\u0154\1\53\1\u0156\1\53\3\uffff\3\53\2\uffff\4\53"+
        "\1\u015f\2\53\1\u0162\1\53\1\u0094\4\53\1\u0168\3\53\1\u016c\1\u0094"+
        "\1\uffff\1\u0094\1\53\2\u00a2\6\53\1\u0174\6\53\2\u0094\1\uffff"+
        "\2\53\1\u0094\2\53\1\u017f\2\53\1\u0094\3\53\4\u0094\1\53\1\u0187"+
        "\3\53\1\uffff\3\53\1\u0168\2\53\1\u0094\3\53\3\uffff\1\u0168\7\53"+
        "\1\uffff\1\u019b\1\53\1\uffff\1\u019d\1\u019e\3\53\1\uffff\3\53"+
        "\1\uffff\6\53\1\u016c\1\uffff\1\u01ab\10\53\1\u015f\1\uffff\1\u01b4"+
        "\4\53\1\u0168\1\u00a2\1\uffff\15\53\1\u01c6\1\u00a2\4\53\1\uffff"+
        "\1\u01cc\2\uffff\1\53\1\u01ce\1\u01cf\1\53\1\u0168\3\53\1\u01d4"+
        "\3\53\1\uffff\3\53\1\u0168\4\53\1\uffff\1\u00ed\1\53\2\u0168\1\53"+
        "\1\u01cc\1\u00ed\10\53\1\uffff\1\u01e9\1\uffff\1\u01ea\4\53\1\uffff"+
        "\1\53\2\uffff\1\u01f0\1\u016c\2\53\1\uffff\6\53\1\u011f\1\53\1\u01f0"+
        "\2\u0168\1\53\1\u01cc\2\53\1\u016c\1\u011f\1\53\2\u01f0\2\uffff"+
        "\1\53\2\u011f\1\u01cc\1\53\1\uffff\1\u0200\1\53\1\u0202\1\u01cc"+
        "\1\u0168\3\53\1\u011f\1\u0206\1\u0168\2\53\2\u015f\1\uffff\1\53"+
        "\1\uffff\2\53\1\u01f0\1\uffff\1\53\1\u01f0\6\53\1\u0213\2\53\1\u0216"+
        "\1\uffff\1\53\1\u0218\1\uffff\1\53\1\uffff\1\53\1\u021b\1\uffff";
    static final String DFA46_eofS =
        "\u021c\uffff";
    static final String DFA46_minS =
        "\1\11\1\141\1\42\1\60\1\uffff\1\63\1\0\1\145\1\56\1\70\1\161\1"+
        "\141\1\144\1\61\1\145\1\61\1\144\1\145\1\151\1\63\1\141\1\150\2"+
        "\145\1\uffff\1\157\1\60\15\uffff\1\60\2\56\3\uffff\1\151\1\44\1"+
        "\160\1\165\1\uffff\1\144\1\40\2\uffff\1\56\1\62\2\uffff\1\143\1"+
        "\165\3\uffff\2\167\1\141\2\44\1\141\1\44\1\141\1\142\1\150\1\157"+
        "\1\151\1\154\1\147\1\145\1\160\2\145\1\142\1\151\1\154\1\151\1\64"+
        "\1\62\1\164\1\157\1\142\1\155\2\44\1\62\1\64\1\144\2\164\2\145\1"+
        "\151\1\145\1\141\1\150\1\44\1\164\1\166\1\155\1\144\1\145\2\165"+
        "\1\151\1\145\1\157\1\62\1\64\1\60\2\154\1\40\2\151\2\162\2\164\1"+
        "\145\1\161\1\145\1\44\1\154\1\156\1\150\1\144\1\161\1\44\1\uffff"+
        "\1\56\1\uffff\1\56\1\147\1\154\1\uffff\2\145\1\151\2\uffff\1\42"+
        "\1\154\1\151\1\142\2\44\1\154\1\141\1\156\1\145\1\164\2\uffff\1"+
        "\143\1\145\1\144\1\145\2\162\1\165\1\164\1\145\1\164\1\137\1\157"+
        "\1\156\1\155\6\44\1\166\2\44\1\141\1\144\1\44\1\70\1\145\1\142\1"+
        "\157\2\145\1\160\3\44\1\145\1\141\1\145\1\162\1\157\1\141\4\44\1"+
        "\166\1\155\1\44\1\144\1\145\1\162\1\uffff\1\143\1\141\1\160\1\163"+
        "\1\164\1\144\1\157\1\170\1\142\1\154\1\166\1\155\1\141\3\44\1\154"+
        "\1\163\1\155\1\144\1\44\1\uffff\1\44\1\166\1\164\1\157\1\164\1\163"+
        "\6\44\1\157\1\162\1\44\1\137\1\145\1\44\1\40\1\44\1\143\3\uffff"+
        "\1\141\1\156\1\154\2\uffff\1\151\1\160\1\167\1\164\1\44\1\164\1"+
        "\162\1\44\1\154\1\44\1\145\3\143\1\44\1\143\1\146\1\145\2\44\1\uffff"+
        "\1\44\1\164\2\44\1\154\1\141\1\165\1\162\1\157\1\147\1\44\1\146"+
        "\1\155\1\141\1\144\1\146\1\142\2\44\1\uffff\1\157\1\141\1\44\1\141"+
        "\1\154\1\44\1\145\1\143\1\44\1\165\1\163\1\164\4\44\1\164\1\44\1"+
        "\164\1\157\1\143\1\uffff\1\141\1\157\1\145\1\44\1\151\1\164\1\44"+
        "\1\151\1\162\1\164\3\uffff\1\44\1\162\2\145\1\141\1\164\1\151\1"+
        "\165\1\uffff\1\44\1\156\1\uffff\2\44\1\164\1\150\1\164\1\uffff\1"+
        "\163\1\160\1\170\1\uffff\1\151\1\145\1\154\2\156\1\160\1\44\1\uffff"+
        "\1\44\1\145\1\143\1\145\1\160\1\154\1\156\2\163\1\44\1\uffff\1\44"+
        "\1\143\1\156\2\151\2\44\1\uffff\1\141\1\156\1\143\1\164\1\151\1"+
        "\156\1\170\1\172\1\157\1\162\1\145\1\40\1\145\2\44\1\163\1\165\1"+
        "\156\1\162\1\uffff\1\44\2\uffff\1\162\2\44\1\164\1\44\1\164\1\154"+
        "\1\155\1\44\1\144\1\141\1\164\1\uffff\1\144\1\150\1\162\1\44\1\145"+
        "\1\154\1\145\1\164\1\uffff\1\44\1\143\2\44\1\156\2\44\1\145\1\156"+
        "\1\151\1\164\1\145\1\156\1\145\1\154\1\uffff\1\44\1\uffff\1\44\1"+
        "\162\1\144\1\156\1\154\1\uffff\1\145\2\uffff\2\44\2\145\1\uffff"+
        "\1\163\1\154\1\162\1\137\1\141\1\145\1\44\1\171\3\44\1\164\1\44"+
        "\2\164\2\44\1\151\2\44\2\uffff\1\145\3\44\1\164\1\uffff\1\44\1\156"+
        "\3\44\1\141\1\142\1\144\3\44\1\151\1\143\2\44\1\uffff\1\164\1\uffff"+
        "\1\144\1\154\1\44\1\uffff\1\141\1\44\1\160\1\144\1\145\1\154\1\164"+
        "\1\162\1\44\1\151\1\162\1\44\1\uffff\1\172\1\44\1\uffff\1\145\1"+
        "\uffff\1\162\1\44\1\uffff";
    static final String DFA46_maxS =
        "\1\175\1\171\1\170\1\160\1\uffff\1\63\1\uffff\1\157\1\71\1\165"+
        "\1\170\1\163\1\167\1\157\1\154\1\156\1\167\1\163\1\171\1\163\1\157"+
        "\1\164\1\145\1\162\1\uffff\1\165\1\163\15\uffff\1\71\1\170\1\165"+
        "\3\uffff\1\162\1\172\1\160\1\165\1\uffff\1\154\1\157\2\uffff\1\71"+
        "\1\62\2\uffff\1\146\1\165\3\uffff\2\167\1\165\2\172\1\164\1\172"+
        "\1\141\1\142\1\150\1\162\1\151\1\170\1\164\1\145\1\160\2\164\1\142"+
        "\1\151\1\162\1\154\1\64\1\62\1\164\1\157\1\164\1\155\2\172\1\62"+
        "\1\64\1\162\4\164\1\151\1\145\1\164\1\150\1\172\1\164\1\166\1\155"+
        "\1\163\1\164\2\165\1\151\1\145\1\157\1\62\1\64\1\60\1\154\1\156"+
        "\1\143\2\151\1\162\1\170\3\164\1\161\1\145\1\172\1\154\1\156\1\150"+
        "\1\144\1\161\1\172\1\uffff\1\146\1\uffff\1\165\1\147\1\154\1\uffff"+
        "\1\145\1\156\1\154\2\uffff\1\42\1\154\1\151\1\142\2\172\1\154\1"+
        "\141\1\156\1\145\1\164\2\uffff\1\143\1\145\1\144\1\145\2\162\1\165"+
        "\1\164\1\145\1\164\1\137\1\157\1\156\1\164\6\172\1\166\2\172\1\141"+
        "\1\144\1\172\1\70\1\145\1\142\1\157\1\164\1\145\1\160\3\172\1\145"+
        "\1\141\1\145\1\162\1\157\1\141\4\172\1\166\1\155\1\172\1\144\1\145"+
        "\1\162\1\uffff\1\143\1\141\1\160\1\163\1\164\1\144\1\162\1\170\1"+
        "\142\1\154\1\166\1\155\1\141\3\172\1\154\1\163\1\155\1\144\1\172"+
        "\1\uffff\1\172\1\166\1\164\1\157\1\164\1\163\6\172\1\157\1\162\1"+
        "\172\1\165\1\145\1\172\1\40\1\172\1\143\3\uffff\1\141\1\156\1\154"+
        "\2\uffff\1\151\1\160\1\167\1\164\1\172\1\164\1\162\1\172\1\154\1"+
        "\172\1\145\3\143\1\172\1\143\1\146\1\145\2\172\1\uffff\1\172\1\164"+
        "\2\172\1\154\1\141\1\165\1\162\1\157\1\147\1\172\1\146\1\155\1\141"+
        "\1\144\1\146\1\142\2\172\1\uffff\1\157\1\141\1\172\1\141\1\154\1"+
        "\172\1\145\1\143\1\172\2\165\1\164\4\172\1\164\1\172\1\164\1\157"+
        "\1\143\1\uffff\1\141\1\157\1\151\1\172\1\151\1\164\1\172\1\151\1"+
        "\162\1\164\3\uffff\1\172\1\162\2\145\1\141\1\164\1\151\1\165\1\uffff"+
        "\1\172\1\156\1\uffff\2\172\1\164\1\150\1\164\1\uffff\1\163\1\160"+
        "\1\170\1\uffff\1\151\1\145\1\154\2\156\1\160\1\172\1\uffff\1\172"+
        "\1\145\1\143\1\145\1\160\1\154\1\156\2\163\1\172\1\uffff\1\172\1"+
        "\143\1\156\2\151\2\172\1\uffff\1\141\1\156\1\143\1\164\1\151\1\156"+
        "\1\170\1\172\1\157\1\162\1\145\1\40\1\145\2\172\1\163\1\165\1\156"+
        "\1\162\1\uffff\1\172\2\uffff\1\162\2\172\1\164\1\172\1\164\1\154"+
        "\1\155\1\172\1\144\1\141\1\164\1\uffff\1\144\1\150\1\162\1\172\1"+
        "\145\1\154\1\145\1\164\1\uffff\1\172\1\143\2\172\1\156\2\172\1\145"+
        "\1\156\1\151\1\164\1\145\1\156\1\145\1\154\1\uffff\1\172\1\uffff"+
        "\1\172\1\162\1\144\1\156\1\154\1\uffff\1\145\2\uffff\2\172\2\145"+
        "\1\uffff\1\163\1\154\1\162\1\137\1\141\1\145\1\172\1\171\3\172\1"+
        "\164\1\172\2\164\2\172\1\151\2\172\2\uffff\1\145\3\172\1\164\1\uffff"+
        "\1\172\1\156\3\172\1\141\1\142\1\144\3\172\1\151\1\143\2\172\1\uffff"+
        "\1\164\1\uffff\1\144\1\154\1\172\1\uffff\1\141\1\172\1\160\1\144"+
        "\1\145\1\154\1\164\1\162\1\172\1\151\1\162\1\172\1\uffff\2\172\1"+
        "\uffff\1\145\1\uffff\1\162\1\172\1\uffff";
    static final String DFA46_acceptS =
        "\4\uffff\1\4\23\uffff\1\61\2\uffff\1\65\1\66\1\67\1\70\1\71\1\72"+
        "\1\73\1\74\1\75\1\76\1\77\1\100\1\101\3\uffff\1\110\1\111\1\112"+
        "\4\uffff\1\2\2\uffff\1\3\1\5\2\uffff\1\7\1\107\2\uffff\1\11\1\102"+
        "\1\106\112\uffff\1\104\1\uffff\1\105\3\uffff\1\36\3\uffff\1\62\1"+
        "\103\13\uffff\1\60\1\64\64\uffff\1\26\25\uffff\1\57\25\uffff\1\16"+
        "\1\47\1\6\3\uffff\1\13\1\14\24\uffff\1\56\23\uffff\1\25\25\uffff"+
        "\1\43\12\uffff\1\33\1\42\1\45\10\uffff\1\54\2\uffff\1\17\5\uffff"+
        "\1\53\3\uffff\1\55\7\uffff\1\31\12\uffff\1\32\7\uffff\1\34\23\uffff"+
        "\1\15\1\uffff\1\27\1\20\14\uffff\1\24\10\uffff\1\46\17\uffff\1\1"+
        "\1\uffff\1\12\5\uffff\1\50\1\uffff\1\30\1\35\4\uffff\1\37\24\uffff"+
        "\1\10\1\51\5\uffff\1\63\17\uffff\1\21\1\uffff\1\23\3\uffff\1\41"+
        "\14\uffff\1\44\2\uffff\1\40\1\uffff\1\22\2\uffff\1\52";
    static final String DFA46_specialS =
        "\6\uffff\1\0\u0215\uffff}>";
    static final String[] DFA46_transitionS = {
            "\2\54\1\uffff\2\54\22\uffff\1\2\1\uffff\1\6\1\uffff\1\53\1"+
            "\36\2\uffff\1\40\1\41\1\37\1\50\1\34\1\3\1\10\1\uffff\1\51\11"+
            "\52\1\4\1\55\1\44\1\33\1\45\1\uffff\1\35\22\53\1\5\7\53\1\46"+
            "\1\30\1\47\1\uffff\1\53\1\uffff\1\32\1\22\1\24\1\7\1\12\1\23"+
            "\1\16\1\53\1\17\2\53\1\13\1\31\1\11\1\27\1\25\1\53\1\21\1\14"+
            "\1\1\1\20\1\15\3\53\1\26\1\42\1\uffff\1\43",
            "\1\56\15\uffff\1\57\2\uffff\1\61\6\uffff\1\60",
            "\1\62\76\uffff\1\63\26\uffff\1\64",
            "\12\67\66\uffff\1\65",
            "",
            "\1\70",
            "\0\72",
            "\1\73\11\uffff\1\74",
            "\1\75\1\uffff\12\77",
            "\1\104\54\uffff\1\103\11\uffff\1\102\3\uffff\1\100\1\uffff"+
            "\1\101",
            "\1\106\6\uffff\1\105",
            "\1\110\15\uffff\1\107\3\uffff\1\111",
            "\1\123\1\114\1\uffff\1\120\1\124\1\115\2\uffff\1\121\5\uffff"+
            "\1\116\1\117\1\112\1\122\1\uffff\1\113",
            "\1\127\4\uffff\1\126\70\uffff\1\125",
            "\1\130\6\uffff\1\131",
            "\1\134\1\uffff\1\136\2\uffff\1\137\1\uffff\1\135\52\uffff"+
            "\1\133\12\uffff\1\132",
            "\1\145\2\uffff\1\143\1\uffff\1\141\2\uffff\1\144\1\uffff\1"+
            "\140\3\uffff\1\146\4\uffff\1\142",
            "\1\147\15\uffff\1\150",
            "\1\152\10\uffff\1\151\6\uffff\1\153",
            "\1\164\2\uffff\1\165\1\uffff\1\166\50\uffff\1\155\1\uffff"+
            "\1\154\1\161\7\uffff\1\163\1\160\2\uffff\1\156\1\uffff\1\162"+
            "\1\157",
            "\1\167\1\uffff\1\171\13\uffff\1\170",
            "\1\172\11\uffff\1\173\1\uffff\1\174",
            "\1\175",
            "\1\u0081\1\uffff\1\u0080\4\uffff\1\177\1\uffff\1\u0082\1\uffff"+
            "\1\176\1\uffff\1\u0083",
            "",
            "\1\u0085\5\uffff\1\u0084",
            "\1\u0089\62\uffff\1\u0088\12\uffff\1\u0087\4\uffff\1\u0086",
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
            "\12\67",
            "\1\77\1\uffff\12\u008b\12\uffff\3\77\5\uffff\1\u008c\10\uffff"+
            "\1\u008c\2\uffff\1\77\13\uffff\3\77\5\uffff\1\u008c\10\uffff"+
            "\1\u008c\2\uffff\1\77",
            "\1\77\1\uffff\12\u008d\12\uffff\3\77\5\uffff\1\u008c\10\uffff"+
            "\1\u008c\16\uffff\3\77\5\uffff\1\u008c\10\uffff\1\u008c",
            "",
            "",
            "",
            "\1\u008f\10\uffff\1\u008e",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0091",
            "\1\u0092",
            "",
            "\1\u0094\7\uffff\1\u0093",
            "\1\u0095\116\uffff\1\u0094",
            "",
            "",
            "\1\77\1\uffff\12\67",
            "\1\u0096",
            "",
            "",
            "\1\u0097\2\uffff\1\u0098",
            "\1\u0099",
            "",
            "",
            "",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c\1\uffff\1\u009d\16\uffff\1\u009f\2\uffff\1\u009e",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\22\53\1\u00a0\7\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u00a3\22\uffff\1\u00a4",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8\2\uffff\1\u00a9",
            "\1\u00aa",
            "\1\u00ab\4\uffff\1\u00ad\6\uffff\1\u00ac",
            "\1\u00af\14\uffff\1\u00ae",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b3\16\uffff\1\u00b2",
            "\1\u00b5\16\uffff\1\u00b4",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8\5\uffff\1\u00b9",
            "\1\u00bb\2\uffff\1\u00ba",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0\17\uffff\1\u00c2\1\uffff\1\u00c1",
            "\1\u00c3",
            "\1\53\11\uffff\1\53\1\uffff\6\53\1\u00c4\3\53\7\uffff\32\53"+
            "\4\uffff\1\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7\11\uffff\1\u00c8\1\u00ca\2\uffff\1\u00c9",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00ce\16\uffff\1\u00cd",
            "\1\u00d0\16\uffff\1\u00cf",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d4\12\uffff\1\u00d5\7\uffff\1\u00d3",
            "\1\u00d6",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00dd\7\uffff\1\u00db\6\uffff\1\u00dc",
            "\1\u00df\16\uffff\1\u00de",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00eb\1\u00ea\1\u00e9",
            "\1\u00ed\102\uffff\1\u00ec",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1\5\uffff\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f6\16\uffff\1\u00f5",
            "\1\u00f7",
            "\1\u00f8",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\77\1\uffff\12\u008b\12\uffff\3\77\35\uffff\3\77",
            "",
            "\1\77\1\uffff\12\u008d\12\uffff\3\77\5\uffff\1\u008c\10\uffff"+
            "\1\u008c\16\uffff\3\77\5\uffff\1\u008c\10\uffff\1\u008c",
            "\1\u00fe",
            "\1\u00ff",
            "",
            "\1\u0100",
            "\1\u0101\10\uffff\1\u0102",
            "\1\u0104\2\uffff\1\u0103",
            "",
            "",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "",
            "",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\u011c",
            "\1\u011e\6\uffff\1\u011d",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0120",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0121",
            "\1\u0122",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127\16\uffff\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0131",
            "\1\u0132",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "",
            "\1\u0137",
            "\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\u013c",
            "\1\u013e\2\uffff\1\u013d",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "\1\u014e",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u014f",
            "\1\u0150",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0152\25\uffff\1\u0151",
            "\1\u0153",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0155",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0157",
            "",
            "",
            "",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "",
            "",
            "\1\u015b",
            "\1\u015c",
            "\1\u015d",
            "\1\u015e",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0160",
            "\1\u0161",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0163",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0164",
            "\1\u0165",
            "\1\u0166",
            "\1\u0167",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u016d",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u016e",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0175",
            "\1\u0176",
            "\1\u0177",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\u017b",
            "\1\u017c",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u017d",
            "\1\u017e",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0180",
            "\1\u0181",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0182",
            "\1\u0184\1\uffff\1\u0183",
            "\1\u0185",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0186",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0188",
            "\1\u0189",
            "\1\u018a",
            "",
            "\1\u018b",
            "\1\u018c",
            "\1\u018e\3\uffff\1\u018d",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u018f",
            "\1\u0190",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "",
            "",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0194",
            "\1\u0195",
            "\1\u0196",
            "\1\u0197",
            "\1\u0198",
            "\1\u0199",
            "\1\u019a",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u019c",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u019f",
            "\1\u01a0",
            "\1\u01a1",
            "",
            "\1\u01a2",
            "\1\u01a3",
            "\1\u01a4",
            "",
            "\1\u01a5",
            "\1\u01a6",
            "\1\u01a7",
            "\1\u01a8",
            "\1\u01a9",
            "\1\u01aa",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01ac",
            "\1\u01ad",
            "\1\u01ae",
            "\1\u01af",
            "\1\u01b0",
            "\1\u01b1",
            "\1\u01b2",
            "\1\u01b3",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01b5",
            "\1\u01b6",
            "\1\u01b7",
            "\1\u01b8",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\u01b9",
            "\1\u01ba",
            "\1\u01bb",
            "\1\u01bc",
            "\1\u01bd",
            "\1\u01be",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\u01c4",
            "\1\u01c5",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01c7",
            "\1\u01c8",
            "\1\u01c9",
            "\1\u01ca",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\1\u01cb\31\53",
            "",
            "",
            "\1\u01cd",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01d0",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01d1",
            "\1\u01d2",
            "\1\u01d3",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01d5",
            "\1\u01d6",
            "\1\u01d7",
            "",
            "\1\u01d8",
            "\1\u01d9",
            "\1\u01da",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01db",
            "\1\u01dc",
            "\1\u01dd",
            "\1\u01de",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01df",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01e0",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01e1",
            "\1\u01e2",
            "\1\u01e3",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u01e6",
            "\1\u01e7",
            "\1\u01e8",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01eb",
            "\1\u01ec",
            "\1\u01ed",
            "\1\u01ee",
            "",
            "\1\u01ef",
            "",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01f1",
            "\1\u01f2",
            "",
            "\1\u01f3",
            "\1\u01f4",
            "\1\u01f5",
            "\1\u01f6",
            "\1\u01f7",
            "\1\u01f8",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01f9",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01fa",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01fb",
            "\1\u01fc",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01fd",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "",
            "\1\u01fe",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u01ff",
            "",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0201",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0203",
            "\1\u0204",
            "\1\u0205",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0207",
            "\1\u0208",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\u0209",
            "",
            "\1\u020a",
            "\1\u020b",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\u020c",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u020d",
            "\1\u020e",
            "\1\u020f",
            "\1\u0210",
            "\1\u0211",
            "\1\u0212",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "\1\u0214",
            "\1\u0215",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\u0217",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            "",
            "\1\u0219",
            "",
            "\1\u021a",
            "\1\53\11\uffff\1\53\1\uffff\12\53\7\uffff\32\53\4\uffff\1"+
            "\53\1\uffff\32\53",
            ""
    };

    static final short[] DFA46_eot = DFA.unpackEncodedString(DFA46_eotS);
    static final short[] DFA46_eof = DFA.unpackEncodedString(DFA46_eofS);
    static final char[] DFA46_min = DFA.unpackEncodedStringToUnsignedChars(DFA46_minS);
    static final char[] DFA46_max = DFA.unpackEncodedStringToUnsignedChars(DFA46_maxS);
    static final short[] DFA46_accept = DFA.unpackEncodedString(DFA46_acceptS);
    static final short[] DFA46_special = DFA.unpackEncodedString(DFA46_specialS);
    static final short[][] DFA46_transition;

    static {
        int numStates = DFA46_transitionS.length;
        DFA46_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA46_transition[i] = DFA.unpackEncodedString(DFA46_transitionS[i]);
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = DFA46_eot;
            this.eof = DFA46_eof;
            this.min = DFA46_min;
            this.max = DFA46_max;
            this.accept = DFA46_accept;
            this.special = DFA46_special;
            this.transition = DFA46_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | PHI | UNREACHABLE | TRUE | FALSE | ALIGN | LINKAGE_TYPE | RETURN_ATTR | ZEROINITIALIZER | CAST_TYPE | ARGUMENT_ATTRIBUTE | PARAMETER_ATTRIBUTE | FUNCTION_ATTRIBUTE | CALLING_CONV | CONDITION | NULL_CHAR | BIN_OPR_STR | ATOMIC_ORDERING | PRIMITIVE_DATA_TYPE | EQUAL | COMMA | GLOBAL_PREFIX | LOCAL_PREFIX | STAR | START_PARANTHESIS | END_PARANTHESIS | START_CURLY | END_CURLY | START_ANGULAR | END_ANGULAR | START_SQUARE_BR | END_SQUARE_BR | DOT | MUL_OPERATOR | NUMBER | DECIMAL_LITERAL | FLOATING_LITERAL | STRING_LITERAL | ID | WHITESPACE | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA46_6 = input.LA(1);

                        s = -1;
                        if ( ((LA46_6>='\u0000' && LA46_6<='\uFFFF')) ) {s = 58;}

                        else s = 57;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 46, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}