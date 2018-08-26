// $ANTLR 3.3 Nov 30, 2010 12:50:56 resources/Clang/ClangLL.g 2014-07-10 11:47:34
 package org.tamedragon.compilers.clang.abssyntree;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ClangLLLexer extends Lexer {
    public static final int EOF=-1;
    public static final int HASH=4;
    public static final int SEMICOLON=5;
    public static final int COMMA=6;
    public static final int ASSIGN=7;
    public static final int INT=8;
    public static final int DOUBLE=9;
    public static final int PLUS=10;
    public static final int MINUS=11;
    public static final int COLON=12;
    public static final int DOT=13;
    public static final int EXTERN=14;
    public static final int AUTO=15;
    public static final int STATIC=16;
    public static final int REGISTER=17;
    public static final int CHAR=18;
    public static final int SHORT=19;
    public static final int LONG=20;
    public static final int FLOAT=21;
    public static final int SIGNED=22;
    public static final int UNSIGNED=23;
    public static final int TYPEDEF=24;
    public static final int UNION=25;
    public static final int STRUCT=26;
    public static final int CONST=27;
    public static final int VOLATILE=28;
    public static final int ENUM=29;
    public static final int LPAREN=30;
    public static final int RPAREN=31;
    public static final int VOID=32;
    public static final int LCURLY=33;
    public static final int RCURLY=34;
    public static final int LBRACK=35;
    public static final int RBRACK=36;
    public static final int IF=37;
    public static final int ELSE=38;
    public static final int CASE=39;
    public static final int DEFAULT=40;
    public static final int SWITCH=41;
    public static final int WHILE=42;
    public static final int DO=43;
    public static final int FOR=44;
    public static final int GOTO=45;
    public static final int CONTINUE=46;
    public static final int BREAK=47;
    public static final int RETURN=48;
    public static final int PIPE=49;
    public static final int NOT=50;
    public static final int EQUALS=51;
    public static final int NOT_EQUALS=52;
    public static final int DEREFERENCE=53;
    public static final int AMPERSAND=54;
    public static final int INCREMENT=55;
    public static final int DECREMENT=56;
    public static final int SIZEOF=57;
    public static final int TYPEOF=58;
    public static final int MULTIPLY_ASSIGN=59;
    public static final int DIVIDE_ASSIGN=60;
    public static final int ADD_ASSIGN=61;
    public static final int MINUS_ASSIGN=62;
    public static final int MODULO_ASSIGN=63;
    public static final int BITWISE_AND_ASSIGN=64;
    public static final int BITWISE_XOR_ASSIGN=65;
    public static final int BITWISE_OR_ASSIGN=66;
    public static final int LEFT_SHIFT_ASSIGN=67;
    public static final int RIGHT_SHIFT_ASSIGN=68;
    public static final int LESSER_THAN=69;
    public static final int GREATER_THAN=70;
    public static final int LESSER_THAN_OR_EQUAL_TO=71;
    public static final int GREATER_THAN_OR_EQUAL_TO=72;
    public static final int LEFT_SHIFT=73;
    public static final int RIGHT_SHIFT=74;
    public static final int TILDE=75;
    public static final int CARET=76;
    public static final int OR=77;
    public static final int AND=78;
    public static final int QUESTION=79;
    public static final int STAR=80;
    public static final int DIVIDE=81;
    public static final int MODULO=82;
    public static final int ELLIPSES=83;
    public static final int IntegerTypeSuffix=84;
    public static final int DECIMAL_LITERAL=85;
    public static final int OCTAL_LITERAL=86;
    public static final int HexDigit=87;
    public static final int HEX_LITERAL=88;
    public static final int Exponent=89;
    public static final int FloatTypeSuffix=90;
    public static final int FLOATING_LITERAL=91;
    public static final int EscapeSequence=92;
    public static final int CHAR_LITERAL=93;
    public static final int NEWLINE=94;
    public static final int WS=95;
    public static final int COMMENT=96;
    public static final int LINE_COMMENT=97;
    public static final int LETTER=98;
    public static final int ID=99;
    public static final int STRING_LITERAL=100;
    public static final int OctalEscape=101;

    // delegates
    // delegators

    public ClangLLLexer() {;} 
    public ClangLLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ClangLLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "resources/Clang/ClangLL.g"; }

    // $ANTLR start "HASH"
    public final void mHASH() throws RecognitionException {
        try {
            int _type = HASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1134:5: ( '#' )
            // resources/Clang/ClangLL.g:1134:7: '#'
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

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1135:11: ( ';' )
            // resources/Clang/ClangLL.g:1135:13: ';'
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
            // resources/Clang/ClangLL.g:1136:7: ( ',' )
            // resources/Clang/ClangLL.g:1136:9: ','
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
            // resources/Clang/ClangLL.g:1137:8: ( '=' )
            // resources/Clang/ClangLL.g:1137:10: '='
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

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1138:5: ( 'int' )
            // resources/Clang/ClangLL.g:1138:7: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "DOUBLE"
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1139:8: ( 'double' )
            // resources/Clang/ClangLL.g:1139:10: 'double'
            {
            match("double"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1140:6: ( '+' )
            // resources/Clang/ClangLL.g:1140:8: '+'
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
            // resources/Clang/ClangLL.g:1141:8: ( '-' )
            // resources/Clang/ClangLL.g:1141:10: '-'
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
            // resources/Clang/ClangLL.g:1142:7: ( ':' )
            // resources/Clang/ClangLL.g:1142:9: ':'
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
            // resources/Clang/ClangLL.g:1143:5: ( '.' )
            // resources/Clang/ClangLL.g:1143:7: '.'
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

    // $ANTLR start "EXTERN"
    public final void mEXTERN() throws RecognitionException {
        try {
            int _type = EXTERN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1144:8: ( 'extern' )
            // resources/Clang/ClangLL.g:1144:10: 'extern'
            {
            match("extern"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXTERN"

    // $ANTLR start "AUTO"
    public final void mAUTO() throws RecognitionException {
        try {
            int _type = AUTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1145:6: ( 'auto' )
            // resources/Clang/ClangLL.g:1145:8: 'auto'
            {
            match("auto"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AUTO"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1146:8: ( 'static' )
            // resources/Clang/ClangLL.g:1146:10: 'static'
            {
            match("static"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "REGISTER"
    public final void mREGISTER() throws RecognitionException {
        try {
            int _type = REGISTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1147:10: ( 'register' )
            // resources/Clang/ClangLL.g:1147:11: 'register'
            {
            match("register"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REGISTER"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1148:6: ( 'char' )
            // resources/Clang/ClangLL.g:1148:8: 'char'
            {
            match("char"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "SHORT"
    public final void mSHORT() throws RecognitionException {
        try {
            int _type = SHORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1149:7: ( 'short' )
            // resources/Clang/ClangLL.g:1149:9: 'short'
            {
            match("short"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHORT"

    // $ANTLR start "LONG"
    public final void mLONG() throws RecognitionException {
        try {
            int _type = LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1150:6: ( 'long' )
            // resources/Clang/ClangLL.g:1150:8: 'long'
            {
            match("long"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LONG"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1151:7: ( 'float' )
            // resources/Clang/ClangLL.g:1151:9: 'float'
            {
            match("float"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "SIGNED"
    public final void mSIGNED() throws RecognitionException {
        try {
            int _type = SIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1152:8: ( 'signed' )
            // resources/Clang/ClangLL.g:1152:10: 'signed'
            {
            match("signed"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIGNED"

    // $ANTLR start "UNSIGNED"
    public final void mUNSIGNED() throws RecognitionException {
        try {
            int _type = UNSIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1153:9: ( 'unsigned' )
            // resources/Clang/ClangLL.g:1153:11: 'unsigned'
            {
            match("unsigned"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNSIGNED"

    // $ANTLR start "TYPEDEF"
    public final void mTYPEDEF() throws RecognitionException {
        try {
            int _type = TYPEDEF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1154:9: ( 'typedef' )
            // resources/Clang/ClangLL.g:1154:11: 'typedef'
            {
            match("typedef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TYPEDEF"

    // $ANTLR start "UNION"
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1155:7: ( 'union' )
            // resources/Clang/ClangLL.g:1155:9: 'union'
            {
            match("union"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNION"

    // $ANTLR start "STRUCT"
    public final void mSTRUCT() throws RecognitionException {
        try {
            int _type = STRUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1156:8: ( 'struct' )
            // resources/Clang/ClangLL.g:1156:10: 'struct'
            {
            match("struct"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRUCT"

    // $ANTLR start "CONST"
    public final void mCONST() throws RecognitionException {
        try {
            int _type = CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1157:7: ( 'const' )
            // resources/Clang/ClangLL.g:1157:9: 'const'
            {
            match("const"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONST"

    // $ANTLR start "VOLATILE"
    public final void mVOLATILE() throws RecognitionException {
        try {
            int _type = VOLATILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1158:9: ( 'volatile' )
            // resources/Clang/ClangLL.g:1158:11: 'volatile'
            {
            match("volatile"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOLATILE"

    // $ANTLR start "ENUM"
    public final void mENUM() throws RecognitionException {
        try {
            int _type = ENUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1159:6: ( 'enum' )
            // resources/Clang/ClangLL.g:1159:8: 'enum'
            {
            match("enum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENUM"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1160:8: ( '(' )
            // resources/Clang/ClangLL.g:1160:10: '('
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
            // resources/Clang/ClangLL.g:1161:8: ( ')' )
            // resources/Clang/ClangLL.g:1161:10: ')'
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

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1162:6: ( 'void' )
            // resources/Clang/ClangLL.g:1162:8: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1163:8: ( '{' )
            // resources/Clang/ClangLL.g:1163:10: '{'
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
            // resources/Clang/ClangLL.g:1164:8: ( '}' )
            // resources/Clang/ClangLL.g:1164:10: '}'
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
            // resources/Clang/ClangLL.g:1165:8: ( '[' )
            // resources/Clang/ClangLL.g:1165:10: '['
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
            // resources/Clang/ClangLL.g:1166:8: ( ']' )
            // resources/Clang/ClangLL.g:1166:10: ']'
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

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1167:4: ( 'if' )
            // resources/Clang/ClangLL.g:1167:6: 'if'
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

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1168:6: ( 'else' )
            // resources/Clang/ClangLL.g:1168:8: 'else'
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

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1169:6: ( 'case' )
            // resources/Clang/ClangLL.g:1169:8: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1170:9: ( 'default' )
            // resources/Clang/ClangLL.g:1170:11: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "SWITCH"
    public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1171:9: ( 'switch' )
            // resources/Clang/ClangLL.g:1171:11: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SWITCH"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1172:7: ( 'while' )
            // resources/Clang/ClangLL.g:1172:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1173:5: ( 'do' )
            // resources/Clang/ClangLL.g:1173:7: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1174:5: ( 'for' )
            // resources/Clang/ClangLL.g:1174:7: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "GOTO"
    public final void mGOTO() throws RecognitionException {
        try {
            int _type = GOTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1175:6: ( 'goto' )
            // resources/Clang/ClangLL.g:1175:8: 'goto'
            {
            match("goto"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GOTO"

    // $ANTLR start "CONTINUE"
    public final void mCONTINUE() throws RecognitionException {
        try {
            int _type = CONTINUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1176:9: ( 'continue' )
            // resources/Clang/ClangLL.g:1176:11: 'continue'
            {
            match("continue"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTINUE"

    // $ANTLR start "BREAK"
    public final void mBREAK() throws RecognitionException {
        try {
            int _type = BREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1177:7: ( 'break' )
            // resources/Clang/ClangLL.g:1177:9: 'break'
            {
            match("break"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BREAK"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1178:8: ( 'return' )
            // resources/Clang/ClangLL.g:1178:10: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "PIPE"
    public final void mPIPE() throws RecognitionException {
        try {
            int _type = PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1179:7: ( '|' )
            // resources/Clang/ClangLL.g:1179:9: '|'
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

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1180:5: ( '!' )
            // resources/Clang/ClangLL.g:1180:7: '!'
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
            // resources/Clang/ClangLL.g:1181:8: ( '==' )
            // resources/Clang/ClangLL.g:1181:10: '=='
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
            // resources/Clang/ClangLL.g:1182:12: ( '!=' )
            // resources/Clang/ClangLL.g:1182:14: '!='
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
            // resources/Clang/ClangLL.g:1183:13: ( '->' )
            // resources/Clang/ClangLL.g:1183:15: '->'
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
            // resources/Clang/ClangLL.g:1184:11: ( '&' )
            // resources/Clang/ClangLL.g:1184:13: '&'
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
            // resources/Clang/ClangLL.g:1185:11: ( '++' )
            // resources/Clang/ClangLL.g:1185:13: '++'
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
            // resources/Clang/ClangLL.g:1186:11: ( '--' )
            // resources/Clang/ClangLL.g:1186:13: '--'
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

    // $ANTLR start "SIZEOF"
    public final void mSIZEOF() throws RecognitionException {
        try {
            int _type = SIZEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1187:8: ( 'sizeof' )
            // resources/Clang/ClangLL.g:1187:11: 'sizeof'
            {
            match("sizeof"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIZEOF"

    // $ANTLR start "TYPEOF"
    public final void mTYPEOF() throws RecognitionException {
        try {
            int _type = TYPEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1188:8: ( ( 'typeof' | '_typeof_' | '_typeof' ) )
            // resources/Clang/ClangLL.g:1188:10: ( 'typeof' | '_typeof_' | '_typeof' )
            {
            // resources/Clang/ClangLL.g:1188:10: ( 'typeof' | '_typeof_' | '_typeof' )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // resources/Clang/ClangLL.g:1188:11: 'typeof'
                    {
                    match("typeof"); 


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1188:22: '_typeof_'
                    {
                    match("_typeof_"); 


                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1188:35: '_typeof'
                    {
                    match("_typeof"); 


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
    // $ANTLR end "TYPEOF"

    // $ANTLR start "MULTIPLY_ASSIGN"
    public final void mMULTIPLY_ASSIGN() throws RecognitionException {
        try {
            int _type = MULTIPLY_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1189:17: ( '*=' )
            // resources/Clang/ClangLL.g:1189:19: '*='
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
            // resources/Clang/ClangLL.g:1190:15: ( '/=' )
            // resources/Clang/ClangLL.g:1190:17: '/='
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
            // resources/Clang/ClangLL.g:1191:12: ( '+=' )
            // resources/Clang/ClangLL.g:1191:14: '+='
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
            // resources/Clang/ClangLL.g:1192:14: ( '-=' )
            // resources/Clang/ClangLL.g:1192:16: '-='
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
            // resources/Clang/ClangLL.g:1193:15: ( '%=' )
            // resources/Clang/ClangLL.g:1193:17: '%='
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
            // resources/Clang/ClangLL.g:1194:20: ( '&=' )
            // resources/Clang/ClangLL.g:1194:22: '&='
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
            // resources/Clang/ClangLL.g:1195:20: ( '^=' )
            // resources/Clang/ClangLL.g:1195:22: '^='
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
            // resources/Clang/ClangLL.g:1196:19: ( '|=' )
            // resources/Clang/ClangLL.g:1196:21: '|='
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
            // resources/Clang/ClangLL.g:1197:19: ( '<<=' )
            // resources/Clang/ClangLL.g:1197:21: '<<='
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
            // resources/Clang/ClangLL.g:1198:20: ( '>>=' )
            // resources/Clang/ClangLL.g:1198:22: '>>='
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
            // resources/Clang/ClangLL.g:1199:14: ( '<' )
            // resources/Clang/ClangLL.g:1199:16: '<'
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
            // resources/Clang/ClangLL.g:1200:15: ( '>' )
            // resources/Clang/ClangLL.g:1200:17: '>'
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
            // resources/Clang/ClangLL.g:1201:26: ( '<=' )
            // resources/Clang/ClangLL.g:1201:28: '<='
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
            // resources/Clang/ClangLL.g:1202:27: ( '>=' )
            // resources/Clang/ClangLL.g:1202:29: '>='
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
            // resources/Clang/ClangLL.g:1203:13: ( '<<' )
            // resources/Clang/ClangLL.g:1203:15: '<<'
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
            // resources/Clang/ClangLL.g:1204:14: ( '>>' )
            // resources/Clang/ClangLL.g:1204:16: '>>'
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
            // resources/Clang/ClangLL.g:1205:7: ( '~' )
            // resources/Clang/ClangLL.g:1205:9: '~'
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
            // resources/Clang/ClangLL.g:1206:7: ( '^' )
            // resources/Clang/ClangLL.g:1206:9: '^'
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
            // resources/Clang/ClangLL.g:1207:4: ( '||' )
            // resources/Clang/ClangLL.g:1207:6: '||'
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
            // resources/Clang/ClangLL.g:1208:5: ( '&&' )
            // resources/Clang/ClangLL.g:1208:7: '&&'
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

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1209:10: ( '?' )
            // resources/Clang/ClangLL.g:1209:12: '?'
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

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1210:6: ( '*' )
            // resources/Clang/ClangLL.g:1210:8: '*'
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
            // resources/Clang/ClangLL.g:1211:8: ( '/' )
            // resources/Clang/ClangLL.g:1211:10: '/'
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
            // resources/Clang/ClangLL.g:1212:8: ( '%' )
            // resources/Clang/ClangLL.g:1212:10: '%'
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
            // resources/Clang/ClangLL.g:1213:9: ( '...' )
            // resources/Clang/ClangLL.g:1213:11: '...'
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
            // resources/Clang/ClangLL.g:1214:17: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // resources/Clang/ClangLL.g:1214:19: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // resources/Clang/ClangLL.g:1214:19: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='0') ) {
                alt3=1;
            }
            else if ( ((LA3_0>='1' && LA3_0<='9')) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // resources/Clang/ClangLL.g:1214:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1214:26: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // resources/Clang/ClangLL.g:1214:35: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:1214:35: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }

            // resources/Clang/ClangLL.g:1214:46: ( IntegerTypeSuffix )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='L'||LA4_0=='U'||LA4_0=='l'||LA4_0=='u') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // resources/Clang/ClangLL.g:1214:46: IntegerTypeSuffix
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
            // resources/Clang/ClangLL.g:1215:15: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // resources/Clang/ClangLL.g:1215:17: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            // resources/Clang/ClangLL.g:1215:21: ( '0' .. '7' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='7')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1215:22: '0' .. '7'
            	    {
            	    matchRange('0','7'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            // resources/Clang/ClangLL.g:1215:33: ( IntegerTypeSuffix )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='L'||LA6_0=='U'||LA6_0=='l'||LA6_0=='u') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // resources/Clang/ClangLL.g:1215:33: IntegerTypeSuffix
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
            // resources/Clang/ClangLL.g:1216:13: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // resources/Clang/ClangLL.g:1216:15: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // resources/Clang/ClangLL.g:1216:29: ( HexDigit )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='F')||(LA7_0>='a' && LA7_0<='f')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1216:29: HexDigit
            	    {
            	    mHexDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            // resources/Clang/ClangLL.g:1216:39: ( IntegerTypeSuffix )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='L'||LA8_0=='U'||LA8_0=='l'||LA8_0=='u') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // resources/Clang/ClangLL.g:1216:39: IntegerTypeSuffix
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
            // resources/Clang/ClangLL.g:1218:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // resources/Clang/ClangLL.g:1218:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // resources/Clang/ClangLL.g:1221:2: ( ( 'u' | 'U' )? ( 'l' | 'L' ) | ( 'u' | 'U' ) ( 'l' | 'L' )? )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='U'||LA11_0=='u') ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1=='L'||LA11_1=='l') ) {
                    alt11=1;
                }
                else {
                    alt11=2;}
            }
            else if ( (LA11_0=='L'||LA11_0=='l') ) {
                alt11=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // resources/Clang/ClangLL.g:1221:4: ( 'u' | 'U' )? ( 'l' | 'L' )
                    {
                    // resources/Clang/ClangLL.g:1221:4: ( 'u' | 'U' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='U'||LA9_0=='u') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // resources/Clang/ClangLL.g:
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
                    // resources/Clang/ClangLL.g:1222:4: ( 'u' | 'U' ) ( 'l' | 'L' )?
                    {
                    if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // resources/Clang/ClangLL.g:1222:15: ( 'l' | 'L' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='L'||LA10_0=='l') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // resources/Clang/ClangLL.g:
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
            // resources/Clang/ClangLL.g:1225:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix )
            int alt23=4;
            alt23 = dfa23.predict(input);
            switch (alt23) {
                case 1 :
                    // resources/Clang/ClangLL.g:1225:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                    {
                    // resources/Clang/ClangLL.g:1225:9: ( '0' .. '9' )+
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
                    	    // resources/Clang/ClangLL.g:1225:10: '0' .. '9'
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

                    match('.'); 
                    // resources/Clang/ClangLL.g:1225:25: ( '0' .. '9' )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:1225:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    // resources/Clang/ClangLL.g:1225:37: ( Exponent )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='E'||LA14_0=='e') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1225:37: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // resources/Clang/ClangLL.g:1225:47: ( FloatTypeSuffix )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='D'||LA15_0=='F'||LA15_0=='d'||LA15_0=='f') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1225:47: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1226:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                    {
                    match('.'); 
                    // resources/Clang/ClangLL.g:1226:13: ( '0' .. '9' )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:1226:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

                    // resources/Clang/ClangLL.g:1226:25: ( Exponent )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='E'||LA17_0=='e') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1226:25: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // resources/Clang/ClangLL.g:1226:35: ( FloatTypeSuffix )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='D'||LA18_0=='F'||LA18_0=='d'||LA18_0=='f') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1226:35: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1227:9: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
                    {
                    // resources/Clang/ClangLL.g:1227:9: ( '0' .. '9' )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:1227:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);

                    mExponent(); 
                    // resources/Clang/ClangLL.g:1227:30: ( FloatTypeSuffix )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='D'||LA20_0=='F'||LA20_0=='d'||LA20_0=='f') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1227:30: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1228:9: ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix
                    {
                    // resources/Clang/ClangLL.g:1228:9: ( '0' .. '9' )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:1228:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);

                    // resources/Clang/ClangLL.g:1228:21: ( Exponent )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0=='E'||LA22_0=='e') ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1228:21: Exponent
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
            // resources/Clang/ClangLL.g:1231:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // resources/Clang/ClangLL.g:1231:13: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // resources/Clang/ClangLL.g:1231:23: ( '+' | '-' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='+'||LA24_0=='-') ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // resources/Clang/ClangLL.g:
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

            // resources/Clang/ClangLL.g:1231:34: ( '0' .. '9' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1231:35: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
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
            // resources/Clang/ClangLL.g:1233:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // resources/Clang/ClangLL.g:1233:19: ( 'f' | 'F' | 'd' | 'D' )
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
            // resources/Clang/ClangLL.g:1234:17: ( '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // resources/Clang/ClangLL.g:1234:21: '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // resources/Clang/ClangLL.g:1234:26: ( EscapeSequence | ~ ( '\\'' | '\\\\' ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='\\') ) {
                alt26=1;
            }
            else if ( ((LA26_0>='\u0000' && LA26_0<='&')||(LA26_0>='(' && LA26_0<='[')||(LA26_0>=']' && LA26_0<='\uFFFF')) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // resources/Clang/ClangLL.g:1234:28: EscapeSequence
                    {
                    mEscapeSequence(); 

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1234:45: ~ ( '\\'' | '\\\\' )
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
            // resources/Clang/ClangLL.g:1235:9: ( ( '\\r' )? '\\n' )
            // resources/Clang/ClangLL.g:1235:11: ( '\\r' )? '\\n'
            {
            // resources/Clang/ClangLL.g:1235:11: ( '\\r' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0=='\r') ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // resources/Clang/ClangLL.g:1235:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            skip();

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
            // resources/Clang/ClangLL.g:1236:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // resources/Clang/ClangLL.g:1236:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

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
            // resources/Clang/ClangLL.g:1237:9: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // resources/Clang/ClangLL.g:1237:13: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // resources/Clang/ClangLL.g:1237:18: ( options {greedy=false; } : . )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0=='*') ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1=='/') ) {
                        alt28=2;
                    }
                    else if ( ((LA28_1>='\u0000' && LA28_1<='.')||(LA28_1>='0' && LA28_1<='\uFFFF')) ) {
                        alt28=1;
                    }


                }
                else if ( ((LA28_0>='\u0000' && LA28_0<=')')||(LA28_0>='+' && LA28_0<='\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1237:46: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            match("*/"); 

            _channel=HIDDEN;

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
            // resources/Clang/ClangLL.g:1238:14: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // resources/Clang/ClangLL.g:1238:16: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // resources/Clang/ClangLL.g:1238:21: (~ ( '\\n' | '\\r' ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>='\u0000' && LA29_0<='\t')||(LA29_0>='\u000B' && LA29_0<='\f')||(LA29_0>='\u000E' && LA29_0<='\uFFFF')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1238:21: ~ ( '\\n' | '\\r' )
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
            	    break loop29;
                }
            } while (true);

            // resources/Clang/ClangLL.g:1238:35: ( '\\r' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='\r') ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // resources/Clang/ClangLL.g:1238:35: '\\r'
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

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1239:4: ( LETTER ( LETTER | '0' .. '9' )* )
            // resources/Clang/ClangLL.g:1239:6: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 
            // resources/Clang/ClangLL.g:1239:13: ( LETTER | '0' .. '9' )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0=='$'||(LA31_0>='0' && LA31_0<='9')||(LA31_0>='A' && LA31_0<='Z')||LA31_0=='_'||(LA31_0>='a' && LA31_0<='z')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:
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
    // $ANTLR end "ID"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // resources/Clang/ClangLL.g:1240:16: ( '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"' )
            // resources/Clang/ClangLL.g:1240:18: '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // resources/Clang/ClangLL.g:1240:22: ( EscapeSequence | ~ ( '\\\\' | '\"' ) )*
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
            	    // resources/Clang/ClangLL.g:1240:24: EscapeSequence
            	    {
            	    mEscapeSequence(); 

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/ClangLL.g:1240:41: ~ ( '\\\\' | '\"' )
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
            // resources/Clang/ClangLL.g:1243:5: ( '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OctalEscape )
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
                    // resources/Clang/ClangLL.g:1243:9: '\\\\' ( 'a' | 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // resources/Clang/ClangLL.g:1244:9: OctalEscape
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
            // resources/Clang/ClangLL.g:1247:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
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
                    // resources/Clang/ClangLL.g:1247:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // resources/Clang/ClangLL.g:1247:14: ( '0' .. '3' )
                    // resources/Clang/ClangLL.g:1247:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // resources/Clang/ClangLL.g:1247:25: ( '0' .. '7' )
                    // resources/Clang/ClangLL.g:1247:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // resources/Clang/ClangLL.g:1247:36: ( '0' .. '7' )
                    // resources/Clang/ClangLL.g:1247:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1248:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // resources/Clang/ClangLL.g:1248:14: ( '0' .. '7' )
                    // resources/Clang/ClangLL.g:1248:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // resources/Clang/ClangLL.g:1248:25: ( '0' .. '7' )
                    // resources/Clang/ClangLL.g:1248:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1249:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // resources/Clang/ClangLL.g:1249:14: ( '0' .. '7' )
                    // resources/Clang/ClangLL.g:1249:15: '0' .. '7'
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
            // resources/Clang/ClangLL.g:1253:2: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // resources/Clang/ClangLL.g:
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
        // resources/Clang/ClangLL.g:1:8: ( HASH | SEMICOLON | COMMA | ASSIGN | INT | DOUBLE | PLUS | MINUS | COLON | DOT | EXTERN | AUTO | STATIC | REGISTER | CHAR | SHORT | LONG | FLOAT | SIGNED | UNSIGNED | TYPEDEF | UNION | STRUCT | CONST | VOLATILE | ENUM | LPAREN | RPAREN | VOID | LCURLY | RCURLY | LBRACK | RBRACK | IF | ELSE | CASE | DEFAULT | SWITCH | WHILE | DO | FOR | GOTO | CONTINUE | BREAK | RETURN | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | SIZEOF | TYPEOF | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | QUESTION | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | ID | STRING_LITERAL )
        int alt35=91;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // resources/Clang/ClangLL.g:1:10: HASH
                {
                mHASH(); 

                }
                break;
            case 2 :
                // resources/Clang/ClangLL.g:1:15: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 3 :
                // resources/Clang/ClangLL.g:1:25: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 4 :
                // resources/Clang/ClangLL.g:1:31: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 5 :
                // resources/Clang/ClangLL.g:1:38: INT
                {
                mINT(); 

                }
                break;
            case 6 :
                // resources/Clang/ClangLL.g:1:42: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 7 :
                // resources/Clang/ClangLL.g:1:49: PLUS
                {
                mPLUS(); 

                }
                break;
            case 8 :
                // resources/Clang/ClangLL.g:1:54: MINUS
                {
                mMINUS(); 

                }
                break;
            case 9 :
                // resources/Clang/ClangLL.g:1:60: COLON
                {
                mCOLON(); 

                }
                break;
            case 10 :
                // resources/Clang/ClangLL.g:1:66: DOT
                {
                mDOT(); 

                }
                break;
            case 11 :
                // resources/Clang/ClangLL.g:1:70: EXTERN
                {
                mEXTERN(); 

                }
                break;
            case 12 :
                // resources/Clang/ClangLL.g:1:77: AUTO
                {
                mAUTO(); 

                }
                break;
            case 13 :
                // resources/Clang/ClangLL.g:1:82: STATIC
                {
                mSTATIC(); 

                }
                break;
            case 14 :
                // resources/Clang/ClangLL.g:1:89: REGISTER
                {
                mREGISTER(); 

                }
                break;
            case 15 :
                // resources/Clang/ClangLL.g:1:98: CHAR
                {
                mCHAR(); 

                }
                break;
            case 16 :
                // resources/Clang/ClangLL.g:1:103: SHORT
                {
                mSHORT(); 

                }
                break;
            case 17 :
                // resources/Clang/ClangLL.g:1:109: LONG
                {
                mLONG(); 

                }
                break;
            case 18 :
                // resources/Clang/ClangLL.g:1:114: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 19 :
                // resources/Clang/ClangLL.g:1:120: SIGNED
                {
                mSIGNED(); 

                }
                break;
            case 20 :
                // resources/Clang/ClangLL.g:1:127: UNSIGNED
                {
                mUNSIGNED(); 

                }
                break;
            case 21 :
                // resources/Clang/ClangLL.g:1:136: TYPEDEF
                {
                mTYPEDEF(); 

                }
                break;
            case 22 :
                // resources/Clang/ClangLL.g:1:144: UNION
                {
                mUNION(); 

                }
                break;
            case 23 :
                // resources/Clang/ClangLL.g:1:150: STRUCT
                {
                mSTRUCT(); 

                }
                break;
            case 24 :
                // resources/Clang/ClangLL.g:1:157: CONST
                {
                mCONST(); 

                }
                break;
            case 25 :
                // resources/Clang/ClangLL.g:1:163: VOLATILE
                {
                mVOLATILE(); 

                }
                break;
            case 26 :
                // resources/Clang/ClangLL.g:1:172: ENUM
                {
                mENUM(); 

                }
                break;
            case 27 :
                // resources/Clang/ClangLL.g:1:177: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 28 :
                // resources/Clang/ClangLL.g:1:184: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 29 :
                // resources/Clang/ClangLL.g:1:191: VOID
                {
                mVOID(); 

                }
                break;
            case 30 :
                // resources/Clang/ClangLL.g:1:196: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 31 :
                // resources/Clang/ClangLL.g:1:203: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 32 :
                // resources/Clang/ClangLL.g:1:210: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 33 :
                // resources/Clang/ClangLL.g:1:217: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 34 :
                // resources/Clang/ClangLL.g:1:224: IF
                {
                mIF(); 

                }
                break;
            case 35 :
                // resources/Clang/ClangLL.g:1:227: ELSE
                {
                mELSE(); 

                }
                break;
            case 36 :
                // resources/Clang/ClangLL.g:1:232: CASE
                {
                mCASE(); 

                }
                break;
            case 37 :
                // resources/Clang/ClangLL.g:1:237: DEFAULT
                {
                mDEFAULT(); 

                }
                break;
            case 38 :
                // resources/Clang/ClangLL.g:1:245: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 39 :
                // resources/Clang/ClangLL.g:1:252: WHILE
                {
                mWHILE(); 

                }
                break;
            case 40 :
                // resources/Clang/ClangLL.g:1:258: DO
                {
                mDO(); 

                }
                break;
            case 41 :
                // resources/Clang/ClangLL.g:1:261: FOR
                {
                mFOR(); 

                }
                break;
            case 42 :
                // resources/Clang/ClangLL.g:1:265: GOTO
                {
                mGOTO(); 

                }
                break;
            case 43 :
                // resources/Clang/ClangLL.g:1:270: CONTINUE
                {
                mCONTINUE(); 

                }
                break;
            case 44 :
                // resources/Clang/ClangLL.g:1:279: BREAK
                {
                mBREAK(); 

                }
                break;
            case 45 :
                // resources/Clang/ClangLL.g:1:285: RETURN
                {
                mRETURN(); 

                }
                break;
            case 46 :
                // resources/Clang/ClangLL.g:1:292: PIPE
                {
                mPIPE(); 

                }
                break;
            case 47 :
                // resources/Clang/ClangLL.g:1:297: NOT
                {
                mNOT(); 

                }
                break;
            case 48 :
                // resources/Clang/ClangLL.g:1:301: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 49 :
                // resources/Clang/ClangLL.g:1:308: NOT_EQUALS
                {
                mNOT_EQUALS(); 

                }
                break;
            case 50 :
                // resources/Clang/ClangLL.g:1:319: DEREFERENCE
                {
                mDEREFERENCE(); 

                }
                break;
            case 51 :
                // resources/Clang/ClangLL.g:1:331: AMPERSAND
                {
                mAMPERSAND(); 

                }
                break;
            case 52 :
                // resources/Clang/ClangLL.g:1:341: INCREMENT
                {
                mINCREMENT(); 

                }
                break;
            case 53 :
                // resources/Clang/ClangLL.g:1:351: DECREMENT
                {
                mDECREMENT(); 

                }
                break;
            case 54 :
                // resources/Clang/ClangLL.g:1:361: SIZEOF
                {
                mSIZEOF(); 

                }
                break;
            case 55 :
                // resources/Clang/ClangLL.g:1:368: TYPEOF
                {
                mTYPEOF(); 

                }
                break;
            case 56 :
                // resources/Clang/ClangLL.g:1:375: MULTIPLY_ASSIGN
                {
                mMULTIPLY_ASSIGN(); 

                }
                break;
            case 57 :
                // resources/Clang/ClangLL.g:1:391: DIVIDE_ASSIGN
                {
                mDIVIDE_ASSIGN(); 

                }
                break;
            case 58 :
                // resources/Clang/ClangLL.g:1:405: ADD_ASSIGN
                {
                mADD_ASSIGN(); 

                }
                break;
            case 59 :
                // resources/Clang/ClangLL.g:1:416: MINUS_ASSIGN
                {
                mMINUS_ASSIGN(); 

                }
                break;
            case 60 :
                // resources/Clang/ClangLL.g:1:429: MODULO_ASSIGN
                {
                mMODULO_ASSIGN(); 

                }
                break;
            case 61 :
                // resources/Clang/ClangLL.g:1:443: BITWISE_AND_ASSIGN
                {
                mBITWISE_AND_ASSIGN(); 

                }
                break;
            case 62 :
                // resources/Clang/ClangLL.g:1:462: BITWISE_XOR_ASSIGN
                {
                mBITWISE_XOR_ASSIGN(); 

                }
                break;
            case 63 :
                // resources/Clang/ClangLL.g:1:481: BITWISE_OR_ASSIGN
                {
                mBITWISE_OR_ASSIGN(); 

                }
                break;
            case 64 :
                // resources/Clang/ClangLL.g:1:499: LEFT_SHIFT_ASSIGN
                {
                mLEFT_SHIFT_ASSIGN(); 

                }
                break;
            case 65 :
                // resources/Clang/ClangLL.g:1:517: RIGHT_SHIFT_ASSIGN
                {
                mRIGHT_SHIFT_ASSIGN(); 

                }
                break;
            case 66 :
                // resources/Clang/ClangLL.g:1:536: LESSER_THAN
                {
                mLESSER_THAN(); 

                }
                break;
            case 67 :
                // resources/Clang/ClangLL.g:1:548: GREATER_THAN
                {
                mGREATER_THAN(); 

                }
                break;
            case 68 :
                // resources/Clang/ClangLL.g:1:561: LESSER_THAN_OR_EQUAL_TO
                {
                mLESSER_THAN_OR_EQUAL_TO(); 

                }
                break;
            case 69 :
                // resources/Clang/ClangLL.g:1:585: GREATER_THAN_OR_EQUAL_TO
                {
                mGREATER_THAN_OR_EQUAL_TO(); 

                }
                break;
            case 70 :
                // resources/Clang/ClangLL.g:1:610: LEFT_SHIFT
                {
                mLEFT_SHIFT(); 

                }
                break;
            case 71 :
                // resources/Clang/ClangLL.g:1:621: RIGHT_SHIFT
                {
                mRIGHT_SHIFT(); 

                }
                break;
            case 72 :
                // resources/Clang/ClangLL.g:1:633: TILDE
                {
                mTILDE(); 

                }
                break;
            case 73 :
                // resources/Clang/ClangLL.g:1:639: CARET
                {
                mCARET(); 

                }
                break;
            case 74 :
                // resources/Clang/ClangLL.g:1:645: OR
                {
                mOR(); 

                }
                break;
            case 75 :
                // resources/Clang/ClangLL.g:1:648: AND
                {
                mAND(); 

                }
                break;
            case 76 :
                // resources/Clang/ClangLL.g:1:652: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 77 :
                // resources/Clang/ClangLL.g:1:661: STAR
                {
                mSTAR(); 

                }
                break;
            case 78 :
                // resources/Clang/ClangLL.g:1:666: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 79 :
                // resources/Clang/ClangLL.g:1:673: MODULO
                {
                mMODULO(); 

                }
                break;
            case 80 :
                // resources/Clang/ClangLL.g:1:680: ELLIPSES
                {
                mELLIPSES(); 

                }
                break;
            case 81 :
                // resources/Clang/ClangLL.g:1:689: DECIMAL_LITERAL
                {
                mDECIMAL_LITERAL(); 

                }
                break;
            case 82 :
                // resources/Clang/ClangLL.g:1:705: OCTAL_LITERAL
                {
                mOCTAL_LITERAL(); 

                }
                break;
            case 83 :
                // resources/Clang/ClangLL.g:1:719: HEX_LITERAL
                {
                mHEX_LITERAL(); 

                }
                break;
            case 84 :
                // resources/Clang/ClangLL.g:1:731: FLOATING_LITERAL
                {
                mFLOATING_LITERAL(); 

                }
                break;
            case 85 :
                // resources/Clang/ClangLL.g:1:748: CHAR_LITERAL
                {
                mCHAR_LITERAL(); 

                }
                break;
            case 86 :
                // resources/Clang/ClangLL.g:1:761: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 87 :
                // resources/Clang/ClangLL.g:1:769: WS
                {
                mWS(); 

                }
                break;
            case 88 :
                // resources/Clang/ClangLL.g:1:772: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 89 :
                // resources/Clang/ClangLL.g:1:780: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 90 :
                // resources/Clang/ClangLL.g:1:793: ID
                {
                mID(); 

                }
                break;
            case 91 :
                // resources/Clang/ClangLL.g:1:796: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA1_eotS =
        "\10\uffff\1\12\2\uffff";
    static final String DFA1_eofS =
        "\13\uffff";
    static final String DFA1_minS =
        "\1\137\1\uffff\1\164\1\171\1\160\1\145\1\157\1\146\1\137\2\uffff";
    static final String DFA1_maxS =
        "\1\164\1\uffff\1\164\1\171\1\160\1\145\1\157\1\146\1\137\2\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\7\uffff\1\2\1\3";
    static final String DFA1_specialS =
        "\13\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\2\24\uffff\1\1",
            "",
            "\1\3",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
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
            return "1188:10: ( 'typeof' | '_typeof_' | '_typeof' )";
        }
    }
    static final String DFA23_eotS =
        "\7\uffff\1\10\2\uffff";
    static final String DFA23_eofS =
        "\12\uffff";
    static final String DFA23_minS =
        "\2\56\2\uffff\1\53\1\uffff\2\60\2\uffff";
    static final String DFA23_maxS =
        "\1\71\1\146\2\uffff\1\71\1\uffff\1\71\1\146\2\uffff";
    static final String DFA23_acceptS =
        "\2\uffff\1\2\1\1\1\uffff\1\4\2\uffff\2\3";
    static final String DFA23_specialS =
        "\12\uffff}>";
    static final String[] DFA23_transitionS = {
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

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "1224:1: FLOATING_LITERAL : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ ( Exponent )? FloatTypeSuffix );";
        }
    }
    static final String DFA35_eotS =
        "\4\uffff\1\63\2\60\1\72\1\76\1\uffff\1\100\12\60\6\uffff\3\60\1"+
        "\131\1\133\1\136\1\60\1\141\1\145\1\147\1\151\1\154\1\157\2\uffff"+
        "\2\161\1\uffff\1\57\6\uffff\1\60\1\166\1\170\1\60\12\uffff\25\60"+
        "\10\uffff\1\60\12\uffff\1\u0096\2\uffff\1\u0098\4\uffff\1\u0099"+
        "\1\161\1\uffff\1\u009a\1\uffff\1\60\1\uffff\22\60\1\u00af\11\60"+
        "\6\uffff\3\60\1\u00bc\1\u00bd\1\u00be\10\60\1\u00c7\2\60\1\u00ca"+
        "\1\u00cb\1\60\1\uffff\4\60\1\u00d2\1\60\1\u00d4\5\60\3\uffff\2\60"+
        "\1\u00dc\5\60\1\uffff\1\u00e2\1\60\2\uffff\1\u00e4\1\60\1\u00e6"+
        "\3\60\1\uffff\1\u00ea\1\uffff\1\u00eb\1\60\1\u00ed\1\60\1\u00ef"+
        "\1\u00f0\1\u00f1\1\uffff\1\u00f2\1\u00f3\1\u00f4\1\60\1\u00f6\1"+
        "\uffff\1\60\1\uffff\1\60\1\uffff\1\60\1\u00fa\1\60\2\uffff\1\60"+
        "\1\uffff\1\u00fd\6\uffff\1\60\1\uffff\2\60\1\u0101\1\uffff\1\60"+
        "\1\u00fa\1\uffff\1\u0104\1\u0105\1\u0106\1\uffff\1\u0107\1\u00fa"+
        "\4\uffff";
    static final String DFA35_eofS =
        "\u0108\uffff";
    static final String DFA35_minS =
        "\1\11\3\uffff\1\75\1\146\1\145\1\53\1\55\1\uffff\1\56\1\154\1\165"+
        "\1\150\1\145\1\141\1\157\1\154\1\156\1\171\1\157\6\uffff\1\150\1"+
        "\157\1\162\2\75\1\46\1\164\1\75\1\52\2\75\1\74\1\75\2\uffff\2\56"+
        "\1\uffff\1\12\6\uffff\1\164\2\44\1\146\12\uffff\1\164\1\165\1\163"+
        "\1\164\1\141\1\157\1\147\1\151\1\147\1\141\1\156\1\163\1\156\1\157"+
        "\1\162\1\151\1\160\2\151\1\164\1\145\10\uffff\1\171\12\uffff\1\75"+
        "\2\uffff\1\75\4\uffff\2\56\1\uffff\1\44\1\uffff\1\142\1\uffff\1"+
        "\141\1\145\1\155\1\145\1\157\1\164\1\165\1\162\1\156\1\145\1\164"+
        "\1\151\1\165\1\162\1\163\1\145\1\147\1\141\1\44\1\151\1\157\1\145"+
        "\1\141\1\144\1\154\1\157\1\141\1\160\6\uffff\1\154\1\165\1\162\3"+
        "\44\1\151\1\143\1\164\1\145\1\157\1\143\1\163\1\162\1\44\1\164\1"+
        "\151\2\44\1\164\1\uffff\1\147\1\156\1\144\1\164\1\44\1\145\1\44"+
        "\1\153\2\145\1\154\1\156\3\uffff\1\143\1\164\1\44\1\144\1\146\1"+
        "\150\1\164\1\156\1\uffff\1\44\1\156\2\uffff\1\44\1\156\1\44\1\145"+
        "\1\146\1\151\1\uffff\1\44\1\uffff\1\44\1\157\1\44\1\164\3\44\1\uffff"+
        "\3\44\1\145\1\44\1\uffff\1\165\1\uffff\1\145\1\uffff\1\146\1\44"+
        "\1\154\2\uffff\1\146\1\uffff\1\44\6\uffff\1\162\1\uffff\1\145\1"+
        "\144\1\44\1\uffff\1\145\1\44\1\uffff\3\44\1\uffff\2\44\4\uffff";
    static final String DFA35_maxS =
        "\1\176\3\uffff\1\75\1\156\1\157\1\75\1\76\1\uffff\1\71\1\170\1"+
        "\165\1\167\1\145\3\157\1\156\1\171\1\157\6\uffff\1\150\1\157\1\162"+
        "\1\174\2\75\1\164\5\75\1\76\2\uffff\1\170\1\146\1\uffff\1\12\6\uffff"+
        "\1\164\2\172\1\146\12\uffff\1\164\1\165\1\163\1\164\1\162\1\157"+
        "\1\172\1\151\1\164\1\141\1\156\1\163\1\156\1\157\1\162\1\163\1\160"+
        "\1\154\1\151\1\164\1\145\10\uffff\1\171\12\uffff\1\75\2\uffff\1"+
        "\75\4\uffff\2\146\1\uffff\1\172\1\uffff\1\142\1\uffff\1\141\1\145"+
        "\1\155\1\145\1\157\1\164\1\165\1\162\1\156\1\145\1\164\1\151\1\165"+
        "\1\162\1\164\1\145\1\147\1\141\1\172\1\151\1\157\1\145\1\141\1\144"+
        "\1\154\1\157\1\141\1\160\6\uffff\1\154\1\165\1\162\3\172\1\151\1"+
        "\143\1\164\1\145\1\157\1\143\1\163\1\162\1\172\1\164\1\151\2\172"+
        "\1\164\1\uffff\1\147\1\156\1\157\1\164\1\172\1\145\1\172\1\153\2"+
        "\145\1\154\1\156\3\uffff\1\143\1\164\1\172\1\144\1\146\1\150\1\164"+
        "\1\156\1\uffff\1\172\1\156\2\uffff\1\172\1\156\1\172\1\145\1\146"+
        "\1\151\1\uffff\1\172\1\uffff\1\172\1\157\1\172\1\164\3\172\1\uffff"+
        "\3\172\1\145\1\172\1\uffff\1\165\1\uffff\1\145\1\uffff\1\146\1\172"+
        "\1\154\2\uffff\1\146\1\uffff\1\172\6\uffff\1\162\1\uffff\1\145\1"+
        "\144\1\172\1\uffff\1\145\1\172\1\uffff\3\172\1\uffff\2\172\4\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\1\2\1\3\5\uffff\1\11\13\uffff\1\33\1\34\1\36\1\37"+
        "\1\40\1\41\15\uffff\1\110\1\114\2\uffff\1\125\1\uffff\1\126\1\127"+
        "\1\132\1\133\1\60\1\4\4\uffff\1\64\1\72\1\7\1\62\1\65\1\73\1\10"+
        "\1\120\1\12\1\124\25\uffff\1\77\1\112\1\56\1\61\1\57\1\75\1\113"+
        "\1\63\1\uffff\1\70\1\115\1\71\1\130\1\131\1\116\1\74\1\117\1\76"+
        "\1\111\1\uffff\1\104\1\102\1\uffff\1\105\1\103\1\123\1\121\2\uffff"+
        "\1\126\1\uffff\1\42\1\uffff\1\50\34\uffff\1\100\1\106\1\101\1\107"+
        "\1\122\1\5\24\uffff\1\51\14\uffff\1\32\1\43\1\14\10\uffff\1\17\2"+
        "\uffff\1\44\1\21\6\uffff\1\35\1\uffff\1\52\7\uffff\1\20\5\uffff"+
        "\1\30\1\uffff\1\22\1\uffff\1\26\3\uffff\1\47\1\54\1\uffff\1\6\1"+
        "\uffff\1\13\1\15\1\27\1\23\1\66\1\46\1\uffff\1\55\3\uffff\1\67\2"+
        "\uffff\1\45\3\uffff\1\25\2\uffff\1\16\1\53\1\24\1\31";
    static final String DFA35_specialS =
        "\u0108\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\57\1\56\1\uffff\1\57\1\55\22\uffff\1\57\1\37\1\61\1\1\1"+
            "\60\1\44\1\40\1\54\1\25\1\26\1\42\1\7\1\3\1\10\1\12\1\43\1\52"+
            "\11\53\1\11\1\2\1\46\1\4\1\47\1\51\1\uffff\32\60\1\31\1\uffff"+
            "\1\32\1\45\1\41\1\uffff\1\14\1\35\1\17\1\6\1\13\1\21\1\34\1"+
            "\60\1\5\2\60\1\20\5\60\1\16\1\15\1\23\1\22\1\24\1\33\3\60\1"+
            "\27\1\36\1\30\1\50",
            "",
            "",
            "",
            "\1\62",
            "\1\65\7\uffff\1\64",
            "\1\67\11\uffff\1\66",
            "\1\70\21\uffff\1\71",
            "\1\74\17\uffff\1\75\1\73",
            "",
            "\1\77\1\uffff\12\101",
            "\1\104\1\uffff\1\103\11\uffff\1\102",
            "\1\105",
            "\1\107\1\110\12\uffff\1\106\2\uffff\1\111",
            "\1\112",
            "\1\115\6\uffff\1\113\6\uffff\1\114",
            "\1\116",
            "\1\117\2\uffff\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127\76\uffff\1\130",
            "\1\132",
            "\1\135\26\uffff\1\134",
            "\1\137",
            "\1\140",
            "\1\143\4\uffff\1\144\15\uffff\1\142",
            "\1\146",
            "\1\150",
            "\1\152\1\153",
            "\1\156\1\155",
            "",
            "",
            "\1\101\1\uffff\10\162\2\101\12\uffff\3\101\21\uffff\1\160"+
            "\13\uffff\3\101\21\uffff\1\160",
            "\1\101\1\uffff\12\163\12\uffff\3\101\35\uffff\3\101",
            "",
            "\1\164",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\165",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\24"+
            "\60\1\167\5\60",
            "\1\171",
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
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176\20\uffff\1\177",
            "\1\u0080",
            "\1\u0081\22\uffff\1\u0082",
            "\1\u0083",
            "\1\u0084\14\uffff\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008d\11\uffff\1\u008c",
            "\1\u008e",
            "\1\u0090\2\uffff\1\u008f",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0094",
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
            "\1\u0095",
            "",
            "",
            "\1\u0097",
            "",
            "",
            "",
            "",
            "\1\101\1\uffff\10\162\2\101\12\uffff\3\101\35\uffff\3\101",
            "\1\101\1\uffff\12\163\12\uffff\3\101\35\uffff\3\101",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\u009b",
            "",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00c8",
            "\1\u00c9",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00cc",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf\12\uffff\1\u00d0",
            "\1\u00d1",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00d3",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "",
            "",
            "",
            "\1\u00da",
            "\1\u00db",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00e3",
            "",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00e5",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00ec",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00ee",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00f5",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\u00f7",
            "",
            "\1\u00f8",
            "",
            "\1\u00f9",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00fb",
            "",
            "",
            "\1\u00fc",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00fe",
            "",
            "\1\u00ff",
            "\1\u0100",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\u0102",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\u0103\1\uffff"+
            "\32\60",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\60\13\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32"+
            "\60",
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
            return "1:1: Tokens : ( HASH | SEMICOLON | COMMA | ASSIGN | INT | DOUBLE | PLUS | MINUS | COLON | DOT | EXTERN | AUTO | STATIC | REGISTER | CHAR | SHORT | LONG | FLOAT | SIGNED | UNSIGNED | TYPEDEF | UNION | STRUCT | CONST | VOLATILE | ENUM | LPAREN | RPAREN | VOID | LCURLY | RCURLY | LBRACK | RBRACK | IF | ELSE | CASE | DEFAULT | SWITCH | WHILE | DO | FOR | GOTO | CONTINUE | BREAK | RETURN | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | SIZEOF | TYPEOF | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | QUESTION | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | ID | STRING_LITERAL );";
        }
    }
 

}