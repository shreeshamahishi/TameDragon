// $ANTLR 3.3 Nov 30, 2010 12:50:56 resources/Clang/CPreprocessorLL.g 2014-07-10 11:47:31

package org.tamedragon.compilers.clang.preprocessor;
import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class CPreprocessorLLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "HASH", "DOUBLE_HASH", "INCLUDE", "DEFINE", "DEFINED", "UNDEF", "LINE", "ERROR", "WARNING", "PRAGMA", "IF", "IFDEF", "IFNDEF", "ELIF", "ELSE", "ENDIF", "SEMICOLON", "COMMA", "ASSIGN", "PLUS", "MINUS", "COLON", "DOT", "LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACK", "RBRACK", "PIPE", "QUESTION", "NOT", "EQUALS", "NOT_EQUALS", "DEREFERENCE", "AMPERSAND", "INCREMENT", "DECREMENT", "MULTIPLY_ASSIGN", "DIVIDE_ASSIGN", "ADD_ASSIGN", "MINUS_ASSIGN", "MODULO_ASSIGN", "BITWISE_AND_ASSIGN", "BITWISE_XOR_ASSIGN", "BITWISE_OR_ASSIGN", "LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", "LESSER_THAN", "GREATER_THAN", "LESSER_THAN_OR_EQUAL_TO", "GREATER_THAN_OR_EQUAL_TO", "LEFT_SHIFT", "RIGHT_SHIFT", "TILDE", "CARET", "OR", "AND", "STAR", "DIVIDE", "MODULO", "ELLIPSES", "IntegerTypeSuffix", "DECIMAL_LITERAL", "OCTAL_LITERAL", "HexDigit", "HEX_LITERAL", "Exponent", "FloatTypeSuffix", "FLOATING_LITERAL", "EscapeSequence", "CHAR_LITERAL", "NEWLINE", "WS", "COMMENT", "LINE_COMMENT", "LETTER", "ID", "FILENAME", "STRING_LITERAL", "OctalEscape"
    };
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

    // delegates
    // delegators


        public CPreprocessorLLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CPreprocessorLLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[392+1];
             
             
        }
        

    public String[] getTokenNames() { return CPreprocessorLLParser.tokenNames; }
    public String getGrammarFileName() { return "resources/Clang/CPreprocessorLL.g"; }

    		
    		
        	private PreprocessorSegments addUnitToPreprocessorSegments(PreprocessorSegments segments, 
    							PreprocessorUnit preProcessorUnit){
    			PreprocessorSegments retValue = segments;
        		if(segments == null){
        			retValue = new PreprocessorSegments();    			
        		}
        		retValue.addUnit(preProcessorUnit);
        		
        		return retValue;
        	}
        	
        	private ProgramCode addToken(ProgramCode pc, int lineNum, String token){
        		
        		if(pc == null){    			
        			pc = new ProgramCode();
        			pc.setLineNum(lineNum);    		
        		}
        		
        		pc.addToken(token);
        		
        		return pc;
        	}
        	
        	private TokenSequence addToken(TokenSequence ts, int lineNum, String token){
        		if(ts == null){
        			ts = new TokenSequence();
        			ts.setLineNum(lineNum);
        		}    		
        		
        		ts.addToken(token);
        		
        		return ts;
        	}	  
        	
        	private ProgramCode addTokenUsingParamListStart(ProgramCode pc, int lineNum, String paramListStartToken){
    			String token = "";
    			if(paramListStartToken == null){
    				return pc;
    			}
    			else{
    				if(paramListStartToken.endsWith("(")){
    					token = paramListStartToken.substring(0, paramListStartToken.length() -1);
    				}
    				else{
    					return pc;
    				}
    			}    		
    			
    			return addToken(pc, lineNum, token);
    		
    		}	
    		
    		private TokenSequence addTokenUsingParamListStart(TokenSequence ts, int lineNum, String paramListStartToken){
    			String token = "";
    			if(paramListStartToken == null){
    				return ts;
    			}
    			else{
    				if(paramListStartToken.endsWith("(")){
    					token = paramListStartToken.substring(0, paramListStartToken.length() -1);
    				}
    				else{
    					return ts;
    				}
    			}    		
    			
    			return addToken(ts, lineNum, token);
    		
    		}	
    		
    		private void setLineNumAndPos(Absyn preprocessorNode, int line, int pos){
    			preprocessorNode.setLineNum(line);
    			preprocessorNode.setPos(pos);
    		}
    		
    		private IfLine constructIfLine(int lineNum, String notText, String id1,
    					String id2){
    			int defType = 0;
    			
    		   if(notText != null)
    		   		defType = IfLine.IFNDEF;
    		   else 
    		   		defType = IfLine.IFDEF;
    		   		
    		   String id = null;
    		   if(id1 == null)
    		   		id = id2;
    		   	else
    		   		id = id1;
    			 
    			IfLine ifl = new IfLine(lineNum, defType, id);
    			return ifl;
    		}
    		
    		private Defined constructDefined(String notText, String id){
    			boolean isNot = false;
    			
    		   if(notText != null)
    		   		isNot = true;
    		   		
    			Defined defined = new Defined(id, isNot);
    			return defined;
    		}
    		
    		private Elif constructElifLine(int lineNum, String notText, String id1,
    					String id2){
    			int defType = 0;
    			
    		   if(notText != null)
    		   		defType = IfLine.IFNDEF;
    		   else 
    		   		defType = IfLine.IFDEF;
    		   		
    		   String id = null;
    		   if(id1 == null)
    		   		id = id2;
    		   	else
    		   		id = id1;
    			 
    			Elif elif = new Elif(lineNum, defType, id);
    			return elif;
    		}
    				
    		private int getIdForSymbol(String symbol, Object obj){
            		int id = -1;
            		
            		if(obj instanceof EqualityExpr){
            			if("==".equals(symbol))
            				id = EqualityExpr.EQUALS;
            			else if("!=".equals(symbol))
            				id = EqualityExpr.NOT_EQUALS;
            			else
            				id = -1;
            			
            		}
            		else if(obj instanceof RelationalExpr){
            			if("<".equals(symbol))
            				id = RelationalExpr.LESSER_THAN;
            			else if(">".equals(symbol))
            				id = RelationalExpr.GREATER_THAN;
            			else if("<=".equals(symbol))
            				id = RelationalExpr.LESSER_THAN_OR_EQUAL_TO;
            			else if(">=".equals(symbol))
            				id = RelationalExpr.GREATER_THAN_OR_EQUAL_TO;
            			else
            				id = -1;
            		}
            		else if(obj instanceof ShiftExpr){
            			if("<<".equals(symbol))
            				id = ShiftExpr.LEFT_SHIFT;
            			else if(">>".equals(symbol))
            				id = ShiftExpr.RIGHT_SHIFT;
            			else
            				id = -1;
            		}
            		else if(obj instanceof AdditiveExpr){
            			if("+".equals(symbol))
            				id = AdditiveExpr.ADD;
            			else if("-".equals(symbol))
            				id = AdditiveExpr.SUBTRACT;
            			else
            				id = -1;
            		}
            		else if(obj instanceof MultiplicativeExpr){
            			if("*".equals(symbol))
            				id = MultiplicativeExpr.MULTIPLY;
            			else if("/".equals(symbol))
            				id = MultiplicativeExpr.DIVIDE;
           				else if("%".equals(symbol))
            				id = MultiplicativeExpr.MODULO;
            			else
            				id = -1;
            		}
            		else
            			id = -1; // Invalid
            		
            		return id;
            	}		



    // $ANTLR start "translation_unit"
    // resources/Clang/CPreprocessorLL.g:313:1: translation_unit returns [PreprocessorSegments value] : (e1= preprocessor_directive | e2= program_code )+ ;
    public final PreprocessorSegments translation_unit() throws RecognitionException {
        PreprocessorSegments value = null;
        int translation_unit_StartIndex = input.index();
        PreprocessorDirective e1 = null;

        ProgramCode e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:313:55: ( (e1= preprocessor_directive | e2= program_code )+ )
            // resources/Clang/CPreprocessorLL.g:314:3: (e1= preprocessor_directive | e2= program_code )+
            {
            // resources/Clang/CPreprocessorLL.g:314:3: (e1= preprocessor_directive | e2= program_code )+
            int cnt1=0;
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==HASH) ) {
                    alt1=1;
                }
                else if ( ((LA1_0>=INCLUDE && LA1_0<=ERROR)||(LA1_0>=PRAGMA && LA1_0<=IF)||LA1_0==ELSE||(LA1_0>=SEMICOLON && LA1_0<=ELLIPSES)||(LA1_0>=DECIMAL_LITERAL && LA1_0<=OCTAL_LITERAL)||LA1_0==HEX_LITERAL||LA1_0==FLOATING_LITERAL||(LA1_0>=CHAR_LITERAL && LA1_0<=LINE_COMMENT)||(LA1_0>=ID && LA1_0<=STRING_LITERAL)) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:314:5: e1= preprocessor_directive
            	    {
            	    pushFollow(FOLLOW_preprocessor_directive_in_translation_unit1102);
            	    e1=preprocessor_directive();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = addUnitToPreprocessorSegments(value, e1);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/CPreprocessorLL.g:315:5: e2= program_code
            	    {
            	    pushFollow(FOLLOW_program_code_in_translation_unit1113);
            	    e2=program_code();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = addUnitToPreprocessorSegments(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, translation_unit_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "translation_unit"


    // $ANTLR start "preprocessor_directive"
    // resources/Clang/CPreprocessorLL.g:317:1: preprocessor_directive returns [PreprocessorDirective value] : (e1= artifact_include | e2= definition | e3= line | e4= pp_error | e5= pp_warning | e6= pp_pragma | e7= conditional | e8= null_directive );
    public final PreprocessorDirective preprocessor_directive() throws RecognitionException {
        PreprocessorDirective value = null;
        int preprocessor_directive_StartIndex = input.index();
        IncludeDirective e1 = null;

        Definition e2 = null;

        Line e3 = null;

        Error e4 = null;

        Warning e5 = null;

        Pragma e6 = null;

        Conditional e7 = null;

        NullDirective e8 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:317:62: (e1= artifact_include | e2= definition | e3= line | e4= pp_error | e5= pp_warning | e6= pp_pragma | e7= conditional | e8= null_directive )
            int alt2=8;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:318:4: e1= artifact_include
                    {
                    pushFollow(FOLLOW_artifact_include_in_preprocessor_directive1136);
                    e1=artifact_include();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e1;
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:319:5: e2= definition
                    {
                    pushFollow(FOLLOW_definition_in_preprocessor_directive1147);
                    e2=definition();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e2;
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:320:5: e3= line
                    {
                    pushFollow(FOLLOW_line_in_preprocessor_directive1158);
                    e3=line();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e3;
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/CPreprocessorLL.g:321:5: e4= pp_error
                    {
                    pushFollow(FOLLOW_pp_error_in_preprocessor_directive1169);
                    e4=pp_error();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                        value = e4;
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/CPreprocessorLL.g:322:5: e5= pp_warning
                    {
                    pushFollow(FOLLOW_pp_warning_in_preprocessor_directive1180);
                    e5=pp_warning();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                        value = e5;
                    }

                    }
                    break;
                case 6 :
                    // resources/Clang/CPreprocessorLL.g:323:5: e6= pp_pragma
                    {
                    pushFollow(FOLLOW_pp_pragma_in_preprocessor_directive1191);
                    e6=pp_pragma();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e6;
                    }

                    }
                    break;
                case 7 :
                    // resources/Clang/CPreprocessorLL.g:324:5: e7= conditional
                    {
                    pushFollow(FOLLOW_conditional_in_preprocessor_directive1202);
                    e7=conditional();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e7;
                    }

                    }
                    break;
                case 8 :
                    // resources/Clang/CPreprocessorLL.g:325:5: e8= null_directive
                    {
                    pushFollow(FOLLOW_null_directive_in_preprocessor_directive1214);
                    e8=null_directive();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e8;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, preprocessor_directive_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "preprocessor_directive"


    // $ANTLR start "artifact_include"
    // resources/Clang/CPreprocessorLL.g:327:1: artifact_include returns [IncludeDirective value] : (e0= HASH ( ( WS )* ) INCLUDE ( ( WS )* ) LESSER_THAN ( ( WS )* ) e1= filename_lib ( ( WS )* ) GREATER_THAN ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | HASH ( ( WS )* ) INCLUDE ( ( WS )* ) e2= STRING_LITERAL ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE );
    public final IncludeDirective artifact_include() throws RecognitionException {
        IncludeDirective value = null;
        int artifact_include_StartIndex = input.index();
        Token e0=null;
        Token e2=null;
        FileNameLib e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:327:50: (e0= HASH ( ( WS )* ) INCLUDE ( ( WS )* ) LESSER_THAN ( ( WS )* ) e1= filename_lib ( ( WS )* ) GREATER_THAN ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | HASH ( ( WS )* ) INCLUDE ( ( WS )* ) e2= STRING_LITERAL ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            int alt16=2;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:328:4: e0= HASH ( ( WS )* ) INCLUDE ( ( WS )* ) LESSER_THAN ( ( WS )* ) e1= filename_lib ( ( WS )* ) GREATER_THAN ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
                    {
                    e0=(Token)match(input,HASH,FOLLOW_HASH_in_artifact_include1235); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:328:12: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:328:13: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:328:13: ( WS )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==WS) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1238); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }

                    match(input,INCLUDE,FOLLOW_INCLUDE_in_artifact_include1242); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:328:27: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:328:28: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:328:28: ( WS )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==WS) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1246); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }

                    match(input,LESSER_THAN,FOLLOW_LESSER_THAN_in_artifact_include1254); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:329:16: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:329:17: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:329:17: ( WS )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==WS) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1258); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }

                    pushFollow(FOLLOW_filename_lib_in_artifact_include1265);
                    e1=filename_lib();

                    state._fsp--;
                    if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:329:40: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:329:41: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:329:41: ( WS )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==WS) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1269); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }

                    if ( state.backtracking==0 ) {
                      value = new IncludeDirective((e0!=null?e0.getLine():0), e1, IncludeDirective.LIB);
                    }
                    match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_artifact_include1281); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:331:17: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:331:18: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:331:18: ( WS )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==WS) ) {
                            int LA7_2 = input.LA(2);

                            if ( (synpred14_CPreprocessorLL()) ) {
                                alt7=1;
                            }


                        }


                        switch (alt7) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1285); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:331:23: ( COMMENT )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMENT) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
                    	    {
                    	    match(input,COMMENT,FOLLOW_COMMENT_in_artifact_include1289); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:331:32: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:331:33: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:331:33: ( WS )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==WS) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1293); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_artifact_include1297); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:333:3: HASH ( ( WS )* ) INCLUDE ( ( WS )* ) e2= STRING_LITERAL ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
                    {
                    match(input,HASH,FOLLOW_HASH_in_artifact_include1305); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:333:8: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:333:9: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:333:9: ( WS )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==WS) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1308); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }

                    match(input,INCLUDE,FOLLOW_INCLUDE_in_artifact_include1312); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:333:23: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:333:24: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:333:24: ( WS )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==WS) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1316); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }

                    e2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_artifact_include1322); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:333:48: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:333:49: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:333:49: ( WS )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==WS) ) {
                            int LA12_1 = input.LA(2);

                            if ( (synpred20_CPreprocessorLL()) ) {
                                alt12=1;
                            }


                        }


                        switch (alt12) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1326); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }

                    if ( state.backtracking==0 ) {
                      value = new IncludeDirective((e2!=null?e2.getLine():0), (e2!=null?e2.getText():null), IncludeDirective.LOCAL);
                    }
                    // resources/Clang/CPreprocessorLL.g:334:80: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:334:81: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:334:81: ( WS )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==WS) ) {
                            int LA13_2 = input.LA(2);

                            if ( (synpred21_CPreprocessorLL()) ) {
                                alt13=1;
                            }


                        }


                        switch (alt13) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1335); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:334:86: ( COMMENT )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==COMMENT) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
                    	    {
                    	    match(input,COMMENT,FOLLOW_COMMENT_in_artifact_include1339); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:334:95: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:334:96: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:334:96: ( WS )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==WS) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_artifact_include1343); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_artifact_include1347); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, artifact_include_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "artifact_include"


    // $ANTLR start "definition"
    // resources/Clang/CPreprocessorLL.g:336:1: definition returns [Definition value] : ( HASH ( ( WS )* ) DEFINE ( ( WS )+ ) e1= ID ( ( ( WS )+ ) e6= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | HASH ( ( WS )* ) DEFINE ( ( WS )+ ) id1= ID LPAREN ( ( WS )* ) (e2= ID ( ( WS )* ) ( COMMA ( ( WS )* ) e3= ID )* )? ( ( WS )* ) RPAREN ( ( ( WS )+ ) e4= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | ( HASH ( ( WS )* ) UNDEF ( ( WS )+ ) e5= ID ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE );
    public final Definition definition() throws RecognitionException {
        Definition value = null;
        int definition_StartIndex = input.index();
        Token e1=null;
        Token id1=null;
        Token e2=null;
        Token e3=null;
        Token e5=null;
        TokenSequence e6 = null;

        TokenSequence e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:336:39: ( HASH ( ( WS )* ) DEFINE ( ( WS )+ ) e1= ID ( ( ( WS )+ ) e6= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | HASH ( ( WS )* ) DEFINE ( ( WS )+ ) id1= ID LPAREN ( ( WS )* ) (e2= ID ( ( WS )* ) ( COMMA ( ( WS )* ) e3= ID )* )? ( ( WS )* ) RPAREN ( ( ( WS )+ ) e4= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | ( HASH ( ( WS )* ) UNDEF ( ( WS )+ ) e5= ID ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            int alt43=3;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:337:2: HASH ( ( WS )* ) DEFINE ( ( WS )+ ) e1= ID ( ( ( WS )+ ) e6= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
                    {
                    match(input,HASH,FOLLOW_HASH_in_definition1361); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:337:7: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:337:8: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:337:8: ( WS )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==WS) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1364); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }

                    match(input,DEFINE,FOLLOW_DEFINE_in_definition1368); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:337:21: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:337:22: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:337:22: ( WS )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==WS) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1372); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);


                    }

                    e1=(Token)match(input,ID,FOLLOW_ID_in_definition1378); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Definition((e1!=null?e1.getLine():0), (e1!=null?e1.getText():null), Definition.ID_DEFINITION);
                    }
                    // resources/Clang/CPreprocessorLL.g:338:4: ( ( ( WS )+ ) e6= token_sequence )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==WS) ) {
                        switch ( input.LA(2) ) {
                            case WS:
                                {
                                int LA20_3 = input.LA(3);

                                if ( (synpred27_CPreprocessorLL()) ) {
                                    alt20=1;
                                }
                                }
                                break;
                            case COMMENT:
                                {
                                int LA20_4 = input.LA(3);

                                if ( (synpred27_CPreprocessorLL()) ) {
                                    alt20=1;
                                }
                                }
                                break;
                            case HASH:
                            case DOUBLE_HASH:
                            case INCLUDE:
                            case DEFINE:
                            case DEFINED:
                            case UNDEF:
                            case LINE:
                            case ERROR:
                            case PRAGMA:
                            case IF:
                            case ELSE:
                            case SEMICOLON:
                            case COMMA:
                            case ASSIGN:
                            case PLUS:
                            case MINUS:
                            case COLON:
                            case DOT:
                            case LPAREN:
                            case RPAREN:
                            case LCURLY:
                            case RCURLY:
                            case LBRACK:
                            case RBRACK:
                            case PIPE:
                            case QUESTION:
                            case NOT:
                            case EQUALS:
                            case NOT_EQUALS:
                            case DEREFERENCE:
                            case AMPERSAND:
                            case INCREMENT:
                            case DECREMENT:
                            case MULTIPLY_ASSIGN:
                            case DIVIDE_ASSIGN:
                            case ADD_ASSIGN:
                            case MINUS_ASSIGN:
                            case MODULO_ASSIGN:
                            case BITWISE_AND_ASSIGN:
                            case BITWISE_XOR_ASSIGN:
                            case BITWISE_OR_ASSIGN:
                            case LEFT_SHIFT_ASSIGN:
                            case RIGHT_SHIFT_ASSIGN:
                            case LESSER_THAN:
                            case GREATER_THAN:
                            case LESSER_THAN_OR_EQUAL_TO:
                            case GREATER_THAN_OR_EQUAL_TO:
                            case LEFT_SHIFT:
                            case RIGHT_SHIFT:
                            case TILDE:
                            case CARET:
                            case OR:
                            case AND:
                            case STAR:
                            case DIVIDE:
                            case MODULO:
                            case ELLIPSES:
                            case DECIMAL_LITERAL:
                            case OCTAL_LITERAL:
                            case HEX_LITERAL:
                            case FLOATING_LITERAL:
                            case CHAR_LITERAL:
                            case LINE_COMMENT:
                            case ID:
                            case STRING_LITERAL:
                                {
                                alt20=1;
                                }
                                break;
                        }

                    }
                    switch (alt20) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:338:5: ( ( WS )+ ) e6= token_sequence
                            {
                            // resources/Clang/CPreprocessorLL.g:338:5: ( ( WS )+ )
                            // resources/Clang/CPreprocessorLL.g:338:6: ( WS )+
                            {
                            // resources/Clang/CPreprocessorLL.g:338:6: ( WS )+
                            int cnt19=0;
                            loop19:
                            do {
                                int alt19=2;
                                int LA19_0 = input.LA(1);

                                if ( (LA19_0==WS) ) {
                                    int LA19_1 = input.LA(2);

                                    if ( (synpred26_CPreprocessorLL()) ) {
                                        alt19=1;
                                    }


                                }


                                switch (alt19) {
                            	case 1 :
                            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                            	    {
                            	    match(input,WS,FOLLOW_WS_in_definition1393); if (state.failed) return value;

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt19 >= 1 ) break loop19;
                            	    if (state.backtracking>0) {state.failed=true; return value;}
                                        EarlyExitException eee =
                                            new EarlyExitException(19, input);
                                        throw eee;
                                }
                                cnt19++;
                            } while (true);


                            }

                            pushFollow(FOLLOW_token_sequence_in_definition1399);
                            e6=token_sequence();

                            state._fsp--;
                            if (state.failed) return value;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      value.setReplacementTokenSequence(e6);
                    }
                    // resources/Clang/CPreprocessorLL.g:339:55: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:339:56: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:339:56: ( WS )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==WS) ) {
                            int LA21_2 = input.LA(2);

                            if ( (synpred28_CPreprocessorLL()) ) {
                                alt21=1;
                            }


                        }


                        switch (alt21) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1413); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:339:61: ( COMMENT )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==COMMENT) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
                    	    {
                    	    match(input,COMMENT,FOLLOW_COMMENT_in_definition1417); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:339:70: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:339:71: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:339:71: ( WS )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==WS) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1421); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_definition1425); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:340:5: HASH ( ( WS )* ) DEFINE ( ( WS )+ ) id1= ID LPAREN ( ( WS )* ) (e2= ID ( ( WS )* ) ( COMMA ( ( WS )* ) e3= ID )* )? ( ( WS )* ) RPAREN ( ( ( WS )+ ) e4= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
                    {
                    match(input,HASH,FOLLOW_HASH_in_definition1431); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:340:11: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:340:12: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:340:12: ( WS )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==WS) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1435); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    }

                    match(input,DEFINE,FOLLOW_DEFINE_in_definition1439); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:340:25: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:340:26: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:340:26: ( WS )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==WS) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1443); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);


                    }

                    id1=(Token)match(input,ID,FOLLOW_ID_in_definition1449); if (state.failed) return value;
                    match(input,LPAREN,FOLLOW_LPAREN_in_definition1451); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:340:46: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:340:47: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:340:47: ( WS )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==WS) ) {
                            int LA26_2 = input.LA(2);

                            if ( (synpred34_CPreprocessorLL()) ) {
                                alt26=1;
                            }


                        }


                        switch (alt26) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1455); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);


                    }

                    if ( state.backtracking==0 ) {
                      value = new Definition((id1!=null?id1.getLine():0), (id1!=null?id1.getText():null), Definition.MACRO_DEFINITION);
                    }
                    // resources/Clang/CPreprocessorLL.g:341:7: (e2= ID ( ( WS )* ) ( COMMA ( ( WS )* ) e3= ID )* )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==ID) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:341:8: e2= ID ( ( WS )* ) ( COMMA ( ( WS )* ) e3= ID )*
                            {
                            e2=(Token)match(input,ID,FOLLOW_ID_in_definition1471); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.addToMacroList((e2!=null?e2.getText():null));
                            }
                            // resources/Clang/CPreprocessorLL.g:341:50: ( ( WS )* )
                            // resources/Clang/CPreprocessorLL.g:341:51: ( WS )*
                            {
                            // resources/Clang/CPreprocessorLL.g:341:51: ( WS )*
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( (LA27_0==WS) ) {
                                    int LA27_2 = input.LA(2);

                                    if ( (synpred35_CPreprocessorLL()) ) {
                                        alt27=1;
                                    }


                                }


                                switch (alt27) {
                            	case 1 :
                            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                            	    {
                            	    match(input,WS,FOLLOW_WS_in_definition1477); if (state.failed) return value;

                            	    }
                            	    break;

                            	default :
                            	    break loop27;
                                }
                            } while (true);


                            }

                            // resources/Clang/CPreprocessorLL.g:341:56: ( COMMA ( ( WS )* ) e3= ID )*
                            loop29:
                            do {
                                int alt29=2;
                                int LA29_0 = input.LA(1);

                                if ( (LA29_0==COMMA) ) {
                                    alt29=1;
                                }


                                switch (alt29) {
                            	case 1 :
                            	    // resources/Clang/CPreprocessorLL.g:341:57: COMMA ( ( WS )* ) e3= ID
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_definition1482); if (state.failed) return value;
                            	    // resources/Clang/CPreprocessorLL.g:341:64: ( ( WS )* )
                            	    // resources/Clang/CPreprocessorLL.g:341:65: ( WS )*
                            	    {
                            	    // resources/Clang/CPreprocessorLL.g:341:65: ( WS )*
                            	    loop28:
                            	    do {
                            	        int alt28=2;
                            	        int LA28_0 = input.LA(1);

                            	        if ( (LA28_0==WS) ) {
                            	            alt28=1;
                            	        }


                            	        switch (alt28) {
                            	    	case 1 :
                            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                            	    	    {
                            	    	    match(input,WS,FOLLOW_WS_in_definition1486); if (state.failed) return value;

                            	    	    }
                            	    	    break;

                            	    	default :
                            	    	    break loop28;
                            	        }
                            	    } while (true);


                            	    }

                            	    e3=(Token)match(input,ID,FOLLOW_ID_in_definition1492); if (state.failed) return value;
                            	    if ( state.backtracking==0 ) {
                            	      value.addToMacroList((e3!=null?e3.getText():null));
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop29;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // resources/Clang/CPreprocessorLL.g:341:116: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:341:117: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:341:117: ( WS )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==WS) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1502); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);


                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_definition1506); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:342:8: ( ( ( WS )+ ) e4= token_sequence )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==WS) ) {
                        switch ( input.LA(2) ) {
                            case WS:
                                {
                                int LA33_3 = input.LA(3);

                                if ( (synpred41_CPreprocessorLL()) ) {
                                    alt33=1;
                                }
                                }
                                break;
                            case COMMENT:
                                {
                                int LA33_4 = input.LA(3);

                                if ( (synpred41_CPreprocessorLL()) ) {
                                    alt33=1;
                                }
                                }
                                break;
                            case HASH:
                            case DOUBLE_HASH:
                            case INCLUDE:
                            case DEFINE:
                            case DEFINED:
                            case UNDEF:
                            case LINE:
                            case ERROR:
                            case PRAGMA:
                            case IF:
                            case ELSE:
                            case SEMICOLON:
                            case COMMA:
                            case ASSIGN:
                            case PLUS:
                            case MINUS:
                            case COLON:
                            case DOT:
                            case LPAREN:
                            case RPAREN:
                            case LCURLY:
                            case RCURLY:
                            case LBRACK:
                            case RBRACK:
                            case PIPE:
                            case QUESTION:
                            case NOT:
                            case EQUALS:
                            case NOT_EQUALS:
                            case DEREFERENCE:
                            case AMPERSAND:
                            case INCREMENT:
                            case DECREMENT:
                            case MULTIPLY_ASSIGN:
                            case DIVIDE_ASSIGN:
                            case ADD_ASSIGN:
                            case MINUS_ASSIGN:
                            case MODULO_ASSIGN:
                            case BITWISE_AND_ASSIGN:
                            case BITWISE_XOR_ASSIGN:
                            case BITWISE_OR_ASSIGN:
                            case LEFT_SHIFT_ASSIGN:
                            case RIGHT_SHIFT_ASSIGN:
                            case LESSER_THAN:
                            case GREATER_THAN:
                            case LESSER_THAN_OR_EQUAL_TO:
                            case GREATER_THAN_OR_EQUAL_TO:
                            case LEFT_SHIFT:
                            case RIGHT_SHIFT:
                            case TILDE:
                            case CARET:
                            case OR:
                            case AND:
                            case STAR:
                            case DIVIDE:
                            case MODULO:
                            case ELLIPSES:
                            case DECIMAL_LITERAL:
                            case OCTAL_LITERAL:
                            case HEX_LITERAL:
                            case FLOATING_LITERAL:
                            case CHAR_LITERAL:
                            case LINE_COMMENT:
                            case ID:
                            case STRING_LITERAL:
                                {
                                alt33=1;
                                }
                                break;
                        }

                    }
                    switch (alt33) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:342:9: ( ( WS )+ ) e4= token_sequence
                            {
                            // resources/Clang/CPreprocessorLL.g:342:9: ( ( WS )+ )
                            // resources/Clang/CPreprocessorLL.g:342:10: ( WS )+
                            {
                            // resources/Clang/CPreprocessorLL.g:342:10: ( WS )+
                            int cnt32=0;
                            loop32:
                            do {
                                int alt32=2;
                                int LA32_0 = input.LA(1);

                                if ( (LA32_0==WS) ) {
                                    int LA32_1 = input.LA(2);

                                    if ( (synpred40_CPreprocessorLL()) ) {
                                        alt32=1;
                                    }


                                }


                                switch (alt32) {
                            	case 1 :
                            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                            	    {
                            	    match(input,WS,FOLLOW_WS_in_definition1517); if (state.failed) return value;

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt32 >= 1 ) break loop32;
                            	    if (state.backtracking>0) {state.failed=true; return value;}
                                        EarlyExitException eee =
                                            new EarlyExitException(32, input);
                                        throw eee;
                                }
                                cnt32++;
                            } while (true);


                            }

                            pushFollow(FOLLOW_token_sequence_in_definition1524);
                            e4=token_sequence();

                            state._fsp--;
                            if (state.failed) return value;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      value.setReplacementTokenSequence(e4);
                    }
                    // resources/Clang/CPreprocessorLL.g:342:87: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:342:88: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:342:88: ( WS )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==WS) ) {
                            int LA34_2 = input.LA(2);

                            if ( (synpred42_CPreprocessorLL()) ) {
                                alt34=1;
                            }


                        }


                        switch (alt34) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1533); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:342:93: ( COMMENT )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==COMMENT) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
                    	    {
                    	    match(input,COMMENT,FOLLOW_COMMENT_in_definition1537); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:342:102: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:342:103: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:342:103: ( WS )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==WS) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1541); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_definition1545); if (state.failed) return value;

                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:343:7: ( HASH ( ( WS )* ) UNDEF ( ( WS )+ ) e5= ID ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
                    {
                    // resources/Clang/CPreprocessorLL.g:343:7: ( HASH ( ( WS )* ) UNDEF ( ( WS )+ ) e5= ID ( ( WS )* ) )
                    // resources/Clang/CPreprocessorLL.g:343:8: HASH ( ( WS )* ) UNDEF ( ( WS )+ ) e5= ID ( ( WS )* )
                    {
                    match(input,HASH,FOLLOW_HASH_in_definition1554); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:343:14: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:343:15: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:343:15: ( WS )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==WS) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1558); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);


                    }

                    match(input,UNDEF,FOLLOW_UNDEF_in_definition1562); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:343:27: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:343:28: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:343:28: ( WS )+
                    int cnt38=0;
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==WS) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1566); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt38 >= 1 ) break loop38;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(38, input);
                                throw eee;
                        }
                        cnt38++;
                    } while (true);


                    }

                    e5=(Token)match(input,ID,FOLLOW_ID_in_definition1572); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:343:40: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:343:41: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:343:41: ( WS )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==WS) ) {
                            int LA39_1 = input.LA(2);

                            if ( (synpred48_CPreprocessorLL()) ) {
                                alt39=1;
                            }


                        }


                        switch (alt39) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1576); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    }

                    if ( state.backtracking==0 ) {
                      value = new Definition((e5!=null?e5.getLine():0), (e5!=null?e5.getText():null), Definition.UNDEFINITION);
                    }

                    }

                    // resources/Clang/CPreprocessorLL.g:343:121: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:343:122: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:343:122: ( WS )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==WS) ) {
                            int LA40_2 = input.LA(2);

                            if ( (synpred49_CPreprocessorLL()) ) {
                                alt40=1;
                            }


                        }


                        switch (alt40) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1586); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:343:127: ( COMMENT )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==COMMENT) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
                    	    {
                    	    match(input,COMMENT,FOLLOW_COMMENT_in_definition1590); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);

                    // resources/Clang/CPreprocessorLL.g:343:136: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:343:137: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:343:137: ( WS )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==WS) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_definition1594); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);


                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_definition1598); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, definition_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "definition"


    // $ANTLR start "line"
    // resources/Clang/CPreprocessorLL.g:345:1: line returns [Line value] : ( HASH ( ( WS )* ) LINE ( ( WS )+ ) e1= DECIMAL_LITERAL ( ( WS )* ) (e2= STRING_LITERAL )? ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ;
    public final Line line() throws RecognitionException {
        Line value = null;
        int line_StartIndex = input.index();
        Token e1=null;
        Token e2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:345:27: ( ( HASH ( ( WS )* ) LINE ( ( WS )+ ) e1= DECIMAL_LITERAL ( ( WS )* ) (e2= STRING_LITERAL )? ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            // resources/Clang/CPreprocessorLL.g:346:2: ( HASH ( ( WS )* ) LINE ( ( WS )+ ) e1= DECIMAL_LITERAL ( ( WS )* ) (e2= STRING_LITERAL )? ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
            {
            // resources/Clang/CPreprocessorLL.g:346:2: ( HASH ( ( WS )* ) LINE ( ( WS )+ ) e1= DECIMAL_LITERAL ( ( WS )* ) (e2= STRING_LITERAL )? )
            // resources/Clang/CPreprocessorLL.g:346:3: HASH ( ( WS )* ) LINE ( ( WS )+ ) e1= DECIMAL_LITERAL ( ( WS )* ) (e2= STRING_LITERAL )?
            {
            match(input,HASH,FOLLOW_HASH_in_line1613); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:346:9: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:346:10: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:346:10: ( WS )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==WS) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_line1617); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }

            match(input,LINE,FOLLOW_LINE_in_line1621); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:346:21: ( ( WS )+ )
            // resources/Clang/CPreprocessorLL.g:346:22: ( WS )+
            {
            // resources/Clang/CPreprocessorLL.g:346:22: ( WS )+
            int cnt45=0;
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==WS) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_line1625); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    if ( cnt45 >= 1 ) break loop45;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(45, input);
                        throw eee;
                }
                cnt45++;
            } while (true);


            }

            e1=(Token)match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_line1631); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:346:47: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:346:48: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:346:48: ( WS )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==WS) ) {
                    int LA46_2 = input.LA(2);

                    if ( (synpred54_CPreprocessorLL()) ) {
                        alt46=1;
                    }


                }


                switch (alt46) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_line1635); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
              value = new Line((e1!=null?e1.getLine():0), (e1!=null?e1.getText():null));
            }
            // resources/Clang/CPreprocessorLL.g:346:94: (e2= STRING_LITERAL )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==STRING_LITERAL) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:346:95: e2= STRING_LITERAL
                    {
                    e2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_line1644); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setFileName((e2!=null?e2.getText():null));
                    }

                    }
                    break;

            }


            }

            // resources/Clang/CPreprocessorLL.g:346:148: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:346:149: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:346:149: ( WS )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==WS) ) {
                    int LA48_2 = input.LA(2);

                    if ( (synpred56_CPreprocessorLL()) ) {
                        alt48=1;
                    }


                }


                switch (alt48) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_line1652); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:346:154: ( COMMENT )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==COMMENT) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_line1656); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:346:163: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:346:164: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:346:164: ( WS )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==WS) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_line1660); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_line1664); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, line_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "line"


    // $ANTLR start "pp_pragma"
    // resources/Clang/CPreprocessorLL.g:348:1: pp_pragma returns [Pragma value] : ( HASH ( ( WS )* ) PRAGMA ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ;
    public final Pragma pp_pragma() throws RecognitionException {
        Pragma value = null;
        int pp_pragma_StartIndex = input.index();
        TokenSequence e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:348:34: ( ( HASH ( ( WS )* ) PRAGMA ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            // resources/Clang/CPreprocessorLL.g:349:2: ( HASH ( ( WS )* ) PRAGMA ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
            {
            // resources/Clang/CPreprocessorLL.g:349:2: ( HASH ( ( WS )* ) PRAGMA ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) )
            // resources/Clang/CPreprocessorLL.g:349:3: HASH ( ( WS )* ) PRAGMA ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* )
            {
            match(input,HASH,FOLLOW_HASH_in_pp_pragma1679); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:349:8: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:349:9: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:349:9: ( WS )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==WS) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_pragma1682); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);


            }

            match(input,PRAGMA,FOLLOW_PRAGMA_in_pp_pragma1686); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:349:22: ( ( WS )+ )
            // resources/Clang/CPreprocessorLL.g:349:23: ( WS )+
            {
            // resources/Clang/CPreprocessorLL.g:349:23: ( WS )+
            int cnt52=0;
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==WS) ) {
                    int LA52_1 = input.LA(2);

                    if ( (synpred60_CPreprocessorLL()) ) {
                        alt52=1;
                    }


                }


                switch (alt52) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_pragma1690); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    if ( cnt52 >= 1 ) break loop52;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(52, input);
                        throw eee;
                }
                cnt52++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
              value = new Pragma();
            }
            // resources/Clang/CPreprocessorLL.g:349:52: (e1= token_sequence )?
            int alt53=2;
            switch ( input.LA(1) ) {
                case WS:
                    {
                    int LA53_1 = input.LA(2);

                    if ( (synpred61_CPreprocessorLL()) ) {
                        alt53=1;
                    }
                    }
                    break;
                case COMMENT:
                    {
                    int LA53_2 = input.LA(2);

                    if ( (synpred61_CPreprocessorLL()) ) {
                        alt53=1;
                    }
                    }
                    break;
                case HASH:
                case DOUBLE_HASH:
                case INCLUDE:
                case DEFINE:
                case DEFINED:
                case UNDEF:
                case LINE:
                case ERROR:
                case PRAGMA:
                case IF:
                case ELSE:
                case SEMICOLON:
                case COMMA:
                case ASSIGN:
                case PLUS:
                case MINUS:
                case COLON:
                case DOT:
                case LPAREN:
                case RPAREN:
                case LCURLY:
                case RCURLY:
                case LBRACK:
                case RBRACK:
                case PIPE:
                case QUESTION:
                case NOT:
                case EQUALS:
                case NOT_EQUALS:
                case DEREFERENCE:
                case AMPERSAND:
                case INCREMENT:
                case DECREMENT:
                case MULTIPLY_ASSIGN:
                case DIVIDE_ASSIGN:
                case ADD_ASSIGN:
                case MINUS_ASSIGN:
                case MODULO_ASSIGN:
                case BITWISE_AND_ASSIGN:
                case BITWISE_XOR_ASSIGN:
                case BITWISE_OR_ASSIGN:
                case LEFT_SHIFT_ASSIGN:
                case RIGHT_SHIFT_ASSIGN:
                case LESSER_THAN:
                case GREATER_THAN:
                case LESSER_THAN_OR_EQUAL_TO:
                case GREATER_THAN_OR_EQUAL_TO:
                case LEFT_SHIFT:
                case RIGHT_SHIFT:
                case TILDE:
                case CARET:
                case OR:
                case AND:
                case STAR:
                case DIVIDE:
                case MODULO:
                case ELLIPSES:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case HEX_LITERAL:
                case FLOATING_LITERAL:
                case CHAR_LITERAL:
                case LINE_COMMENT:
                case ID:
                case STRING_LITERAL:
                    {
                    alt53=1;
                    }
                    break;
            }

            switch (alt53) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:349:53: e1= token_sequence
                    {
                    pushFollow(FOLLOW_token_sequence_in_pp_pragma1698);
                    e1=token_sequence();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setTokenSequence(e1);
                    }

                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:349:112: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:349:113: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:349:113: ( WS )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==WS) ) {
                    int LA54_1 = input.LA(2);

                    if ( (synpred62_CPreprocessorLL()) ) {
                        alt54=1;
                    }


                }


                switch (alt54) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_pragma1706); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);


            }


            }

            // resources/Clang/CPreprocessorLL.g:349:120: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:349:121: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:349:121: ( WS )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==WS) ) {
                    int LA55_2 = input.LA(2);

                    if ( (synpred63_CPreprocessorLL()) ) {
                        alt55=1;
                    }


                }


                switch (alt55) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_pragma1713); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:349:126: ( COMMENT )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==COMMENT) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_pp_pragma1717); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:349:135: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:349:136: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:349:136: ( WS )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==WS) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_pragma1721); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_pp_pragma1725); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, pp_pragma_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "pp_pragma"


    // $ANTLR start "pp_error"
    // resources/Clang/CPreprocessorLL.g:351:1: pp_error returns [Error value] : ( HASH ( ( WS )* ) ERROR ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ;
    public final Error pp_error() throws RecognitionException {
        Error value = null;
        int pp_error_StartIndex = input.index();
        TokenSequence e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:351:31: ( ( HASH ( ( WS )* ) ERROR ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            // resources/Clang/CPreprocessorLL.g:352:2: ( HASH ( ( WS )* ) ERROR ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
            {
            // resources/Clang/CPreprocessorLL.g:352:2: ( HASH ( ( WS )* ) ERROR ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) )
            // resources/Clang/CPreprocessorLL.g:352:3: HASH ( ( WS )* ) ERROR ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* )
            {
            match(input,HASH,FOLLOW_HASH_in_pp_error1739); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:352:8: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:352:9: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:352:9: ( WS )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==WS) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_error1742); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);


            }

            match(input,ERROR,FOLLOW_ERROR_in_pp_error1746); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:352:20: ( ( WS )+ )
            // resources/Clang/CPreprocessorLL.g:352:21: ( WS )+
            {
            // resources/Clang/CPreprocessorLL.g:352:21: ( WS )+
            int cnt59=0;
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==WS) ) {
                    int LA59_1 = input.LA(2);

                    if ( (synpred67_CPreprocessorLL()) ) {
                        alt59=1;
                    }


                }


                switch (alt59) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_error1749); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    if ( cnt59 >= 1 ) break loop59;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(59, input);
                        throw eee;
                }
                cnt59++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
              value = new Error();
            }
            // resources/Clang/CPreprocessorLL.g:352:50: (e1= token_sequence )?
            int alt60=2;
            switch ( input.LA(1) ) {
                case WS:
                    {
                    int LA60_1 = input.LA(2);

                    if ( (synpred68_CPreprocessorLL()) ) {
                        alt60=1;
                    }
                    }
                    break;
                case COMMENT:
                    {
                    int LA60_2 = input.LA(2);

                    if ( (synpred68_CPreprocessorLL()) ) {
                        alt60=1;
                    }
                    }
                    break;
                case HASH:
                case DOUBLE_HASH:
                case INCLUDE:
                case DEFINE:
                case DEFINED:
                case UNDEF:
                case LINE:
                case ERROR:
                case PRAGMA:
                case IF:
                case ELSE:
                case SEMICOLON:
                case COMMA:
                case ASSIGN:
                case PLUS:
                case MINUS:
                case COLON:
                case DOT:
                case LPAREN:
                case RPAREN:
                case LCURLY:
                case RCURLY:
                case LBRACK:
                case RBRACK:
                case PIPE:
                case QUESTION:
                case NOT:
                case EQUALS:
                case NOT_EQUALS:
                case DEREFERENCE:
                case AMPERSAND:
                case INCREMENT:
                case DECREMENT:
                case MULTIPLY_ASSIGN:
                case DIVIDE_ASSIGN:
                case ADD_ASSIGN:
                case MINUS_ASSIGN:
                case MODULO_ASSIGN:
                case BITWISE_AND_ASSIGN:
                case BITWISE_XOR_ASSIGN:
                case BITWISE_OR_ASSIGN:
                case LEFT_SHIFT_ASSIGN:
                case RIGHT_SHIFT_ASSIGN:
                case LESSER_THAN:
                case GREATER_THAN:
                case LESSER_THAN_OR_EQUAL_TO:
                case GREATER_THAN_OR_EQUAL_TO:
                case LEFT_SHIFT:
                case RIGHT_SHIFT:
                case TILDE:
                case CARET:
                case OR:
                case AND:
                case STAR:
                case DIVIDE:
                case MODULO:
                case ELLIPSES:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case HEX_LITERAL:
                case FLOATING_LITERAL:
                case CHAR_LITERAL:
                case LINE_COMMENT:
                case ID:
                case STRING_LITERAL:
                    {
                    alt60=1;
                    }
                    break;
            }

            switch (alt60) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:352:51: e1= token_sequence
                    {
                    pushFollow(FOLLOW_token_sequence_in_pp_error1758);
                    e1=token_sequence();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setTokenSequence(e1);
                    }

                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:352:110: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:352:111: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:352:111: ( WS )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==WS) ) {
                    int LA61_1 = input.LA(2);

                    if ( (synpred69_CPreprocessorLL()) ) {
                        alt61=1;
                    }


                }


                switch (alt61) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_error1766); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);


            }


            }

            // resources/Clang/CPreprocessorLL.g:352:118: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:352:119: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:352:119: ( WS )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==WS) ) {
                    int LA62_2 = input.LA(2);

                    if ( (synpred70_CPreprocessorLL()) ) {
                        alt62=1;
                    }


                }


                switch (alt62) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_error1773); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:352:124: ( COMMENT )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==COMMENT) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_pp_error1777); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:352:133: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:352:134: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:352:134: ( WS )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==WS) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_error1781); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_pp_error1785); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, pp_error_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "pp_error"


    // $ANTLR start "pp_warning"
    // resources/Clang/CPreprocessorLL.g:355:1: pp_warning returns [Warning value] : ( HASH ( ( WS )* ) WARNING ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ) ;
    public final Warning pp_warning() throws RecognitionException {
        Warning value = null;
        int pp_warning_StartIndex = input.index();
        TokenSequence e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:355:35: ( ( HASH ( ( WS )* ) WARNING ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ) )
            // resources/Clang/CPreprocessorLL.g:356:2: ( HASH ( ( WS )* ) WARNING ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            {
            // resources/Clang/CPreprocessorLL.g:356:2: ( HASH ( ( WS )* ) WARNING ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            // resources/Clang/CPreprocessorLL.g:356:3: HASH ( ( WS )* ) WARNING ( ( WS )+ ) (e1= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
            {
            match(input,HASH,FOLLOW_HASH_in_pp_warning1801); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:356:8: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:356:9: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:356:9: ( WS )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==WS) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_warning1804); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);


            }

            match(input,WARNING,FOLLOW_WARNING_in_pp_warning1808); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:356:22: ( ( WS )+ )
            // resources/Clang/CPreprocessorLL.g:356:23: ( WS )+
            {
            // resources/Clang/CPreprocessorLL.g:356:23: ( WS )+
            int cnt66=0;
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==WS) ) {
                    int LA66_1 = input.LA(2);

                    if ( (synpred74_CPreprocessorLL()) ) {
                        alt66=1;
                    }


                }


                switch (alt66) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_warning1811); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    if ( cnt66 >= 1 ) break loop66;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(66, input);
                        throw eee;
                }
                cnt66++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
              value = new Warning();
            }
            // resources/Clang/CPreprocessorLL.g:356:54: (e1= token_sequence )?
            int alt67=2;
            switch ( input.LA(1) ) {
                case WS:
                    {
                    int LA67_1 = input.LA(2);

                    if ( (synpred75_CPreprocessorLL()) ) {
                        alt67=1;
                    }
                    }
                    break;
                case COMMENT:
                    {
                    int LA67_2 = input.LA(2);

                    if ( (synpred75_CPreprocessorLL()) ) {
                        alt67=1;
                    }
                    }
                    break;
                case HASH:
                case DOUBLE_HASH:
                case INCLUDE:
                case DEFINE:
                case DEFINED:
                case UNDEF:
                case LINE:
                case ERROR:
                case PRAGMA:
                case IF:
                case ELSE:
                case SEMICOLON:
                case COMMA:
                case ASSIGN:
                case PLUS:
                case MINUS:
                case COLON:
                case DOT:
                case LPAREN:
                case RPAREN:
                case LCURLY:
                case RCURLY:
                case LBRACK:
                case RBRACK:
                case PIPE:
                case QUESTION:
                case NOT:
                case EQUALS:
                case NOT_EQUALS:
                case DEREFERENCE:
                case AMPERSAND:
                case INCREMENT:
                case DECREMENT:
                case MULTIPLY_ASSIGN:
                case DIVIDE_ASSIGN:
                case ADD_ASSIGN:
                case MINUS_ASSIGN:
                case MODULO_ASSIGN:
                case BITWISE_AND_ASSIGN:
                case BITWISE_XOR_ASSIGN:
                case BITWISE_OR_ASSIGN:
                case LEFT_SHIFT_ASSIGN:
                case RIGHT_SHIFT_ASSIGN:
                case LESSER_THAN:
                case GREATER_THAN:
                case LESSER_THAN_OR_EQUAL_TO:
                case GREATER_THAN_OR_EQUAL_TO:
                case LEFT_SHIFT:
                case RIGHT_SHIFT:
                case TILDE:
                case CARET:
                case OR:
                case AND:
                case STAR:
                case DIVIDE:
                case MODULO:
                case ELLIPSES:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case HEX_LITERAL:
                case FLOATING_LITERAL:
                case CHAR_LITERAL:
                case LINE_COMMENT:
                case ID:
                case STRING_LITERAL:
                    {
                    alt67=1;
                    }
                    break;
            }

            switch (alt67) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:356:55: e1= token_sequence
                    {
                    pushFollow(FOLLOW_token_sequence_in_pp_warning1820);
                    e1=token_sequence();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setTokenSequence(e1);
                    }

                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:356:114: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:356:115: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:356:115: ( WS )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==WS) ) {
                    int LA68_2 = input.LA(2);

                    if ( (synpred76_CPreprocessorLL()) ) {
                        alt68=1;
                    }


                }


                switch (alt68) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_warning1828); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:356:120: ( COMMENT )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==COMMENT) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_pp_warning1832); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:356:129: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:356:130: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:356:130: ( WS )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==WS) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_pp_warning1836); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_pp_warning1840); if (state.failed) return value;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, pp_warning_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "pp_warning"


    // $ANTLR start "null_directive"
    // resources/Clang/CPreprocessorLL.g:358:1: null_directive returns [NullDirective value] : HASH ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ;
    public final NullDirective null_directive() throws RecognitionException {
        NullDirective value = null;
        int null_directive_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:358:46: ( HASH ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            // resources/Clang/CPreprocessorLL.g:359:3: HASH ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
            {
            match(input,HASH,FOLLOW_HASH_in_null_directive1857); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:359:8: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:359:9: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:359:9: ( WS )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==WS) ) {
                    int LA71_1 = input.LA(2);

                    if ( (synpred79_CPreprocessorLL()) ) {
                        alt71=1;
                    }


                }


                switch (alt71) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_null_directive1860); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
              value = new NullDirective();
            }
            // resources/Clang/CPreprocessorLL.g:359:47: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:359:48: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:359:48: ( WS )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==WS) ) {
                    int LA72_2 = input.LA(2);

                    if ( (synpred80_CPreprocessorLL()) ) {
                        alt72=1;
                    }


                }


                switch (alt72) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_null_directive1868); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:359:53: ( COMMENT )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==COMMENT) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_null_directive1872); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:359:62: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:359:63: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:359:63: ( WS )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==WS) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_null_directive1876); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_null_directive1880); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, null_directive_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "null_directive"


    // $ANTLR start "conditional"
    // resources/Clang/CPreprocessorLL.g:361:1: conditional returns [Conditional value] : (e1= ifline ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE (e2= program_code | e5= preprocessor_directive )* (e3= elif_part )* (e4= else_part )? endcond= HASH ( ( WS )* ) ENDIF ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ) ;
    public final Conditional conditional() throws RecognitionException {
        Conditional value = null;
        int conditional_StartIndex = input.index();
        Token endcond=null;
        IfLine e1 = null;

        ProgramCode e2 = null;

        PreprocessorDirective e5 = null;

        ElifPart e3 = null;

        ElsePart e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:361:41: ( (e1= ifline ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE (e2= program_code | e5= preprocessor_directive )* (e3= elif_part )* (e4= else_part )? endcond= HASH ( ( WS )* ) ENDIF ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE ) )
            // resources/Clang/CPreprocessorLL.g:362:3: (e1= ifline ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE (e2= program_code | e5= preprocessor_directive )* (e3= elif_part )* (e4= else_part )? endcond= HASH ( ( WS )* ) ENDIF ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            {
            // resources/Clang/CPreprocessorLL.g:362:3: (e1= ifline ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE (e2= program_code | e5= preprocessor_directive )* (e3= elif_part )* (e4= else_part )? endcond= HASH ( ( WS )* ) ENDIF ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE )
            // resources/Clang/CPreprocessorLL.g:362:4: e1= ifline ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE (e2= program_code | e5= preprocessor_directive )* (e3= elif_part )* (e4= else_part )? endcond= HASH ( ( WS )* ) ENDIF ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE
            {
            pushFollow(FOLLOW_ifline_in_conditional1898);
            e1=ifline();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new Conditional(e1.getLineNum(), e1);
            }
            // resources/Clang/CPreprocessorLL.g:362:77: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:362:78: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:362:78: ( WS )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==WS) ) {
                    int LA75_2 = input.LA(2);

                    if ( (synpred83_CPreprocessorLL()) ) {
                        alt75=1;
                    }


                }


                switch (alt75) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_conditional1903); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop75;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:362:83: ( COMMENT )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==COMMENT) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_conditional1907); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:362:92: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:362:93: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:362:93: ( WS )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==WS) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_conditional1911); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_conditional1915); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:363:5: (e2= program_code | e5= preprocessor_directive )*
            loop78:
            do {
                int alt78=3;
                alt78 = dfa78.predict(input);
                switch (alt78) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:363:7: e2= program_code
            	    {
            	    pushFollow(FOLLOW_program_code_in_conditional1925);
            	    e2=program_code();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value.addPreprocessorUnit(e2);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/CPreprocessorLL.g:364:7: e5= preprocessor_directive
            	    {
            	    pushFollow(FOLLOW_preprocessor_directive_in_conditional1937);
            	    e5=preprocessor_directive();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value.addPreprocessorUnit(e5);
            	    }

            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:366:4: (e3= elif_part )*
            loop79:
            do {
                int alt79=2;
                alt79 = dfa79.predict(input);
                switch (alt79) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:366:5: e3= elif_part
            	    {
            	    pushFollow(FOLLOW_elif_part_in_conditional1956);
            	    e3=elif_part();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.addElifPart(e3);
            	    }

            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:367:4: (e4= else_part )?
            int alt80=2;
            alt80 = dfa80.predict(input);
            switch (alt80) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:367:5: e4= else_part
                    {
                    pushFollow(FOLLOW_else_part_in_conditional1969);
                    e4=else_part();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setElsePart(e4);
                    }

                    }
                    break;

            }

            endcond=(Token)match(input,HASH,FOLLOW_HASH_in_conditional1986); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:369:16: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:369:17: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:369:17: ( WS )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==WS) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_conditional1989); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


            }

            match(input,ENDIF,FOLLOW_ENDIF_in_conditional1993); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:369:28: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:369:29: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:369:29: ( WS )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==WS) ) {
                    int LA82_2 = input.LA(2);

                    if ( (synpred91_CPreprocessorLL()) ) {
                        alt82=1;
                    }


                }


                switch (alt82) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_conditional1996); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:369:34: ( COMMENT )*
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( (LA83_0==COMMENT) ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_conditional2000); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop83;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:369:43: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:369:44: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:369:44: ( WS )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==WS) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_conditional2004); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);


            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_conditional2008); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value.setEndConditionalLineNum((endcond!=null?endcond.getLine():0));
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, conditional_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "conditional"


    // $ANTLR start "ifline"
    // resources/Clang/CPreprocessorLL.g:371:1: ifline returns [IfLine value] : ( (e0= HASH ( ( WS )* ) IF ( ( WS )+ ) e1= const_expr ) | (h1= HASH ( ( WS )* ) IFDEF ( ( WS )+ ) e2= ID ) | (h2= HASH ( ( WS )* ) IFNDEF ( ( WS )+ ) e3= ID ) );
    public final IfLine ifline() throws RecognitionException {
        IfLine value = null;
        int ifline_StartIndex = input.index();
        Token e0=null;
        Token h1=null;
        Token e2=null;
        Token h2=null;
        Token e3=null;
        ConstExpr e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:371:31: ( (e0= HASH ( ( WS )* ) IF ( ( WS )+ ) e1= const_expr ) | (h1= HASH ( ( WS )* ) IFDEF ( ( WS )+ ) e2= ID ) | (h2= HASH ( ( WS )* ) IFNDEF ( ( WS )+ ) e3= ID ) )
            int alt91=3;
            alt91 = dfa91.predict(input);
            switch (alt91) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:372:4: (e0= HASH ( ( WS )* ) IF ( ( WS )+ ) e1= const_expr )
                    {
                    // resources/Clang/CPreprocessorLL.g:372:4: (e0= HASH ( ( WS )* ) IF ( ( WS )+ ) e1= const_expr )
                    // resources/Clang/CPreprocessorLL.g:372:5: e0= HASH ( ( WS )* ) IF ( ( WS )+ ) e1= const_expr
                    {
                    e0=(Token)match(input,HASH,FOLLOW_HASH_in_ifline2031); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:372:14: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:372:15: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:372:15: ( WS )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==WS) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_ifline2035); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop85;
                        }
                    } while (true);


                    }

                    match(input,IF,FOLLOW_IF_in_ifline2039); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:372:23: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:372:24: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:372:24: ( WS )+
                    int cnt86=0;
                    loop86:
                    do {
                        int alt86=2;
                        int LA86_0 = input.LA(1);

                        if ( (LA86_0==WS) ) {
                            int LA86_2 = input.LA(2);

                            if ( (synpred95_CPreprocessorLL()) ) {
                                alt86=1;
                            }


                        }


                        switch (alt86) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_ifline2042); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt86 >= 1 ) break loop86;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(86, input);
                                throw eee;
                        }
                        cnt86++;
                    } while (true);


                    }

                    pushFollow(FOLLOW_const_expr_in_ifline2048);
                    e1=const_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new IfLine((e0!=null?e0.getLine():0), IfLine.CONST_EXPR, e1);
                    }

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:373:4: (h1= HASH ( ( WS )* ) IFDEF ( ( WS )+ ) e2= ID )
                    {
                    // resources/Clang/CPreprocessorLL.g:373:4: (h1= HASH ( ( WS )* ) IFDEF ( ( WS )+ ) e2= ID )
                    // resources/Clang/CPreprocessorLL.g:373:5: h1= HASH ( ( WS )* ) IFDEF ( ( WS )+ ) e2= ID
                    {
                    h1=(Token)match(input,HASH,FOLLOW_HASH_in_ifline2060); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:373:13: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:373:14: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:373:14: ( WS )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==WS) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_ifline2063); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop87;
                        }
                    } while (true);


                    }

                    match(input,IFDEF,FOLLOW_IFDEF_in_ifline2067); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:373:26: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:373:27: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:373:27: ( WS )+
                    int cnt88=0;
                    loop88:
                    do {
                        int alt88=2;
                        int LA88_0 = input.LA(1);

                        if ( (LA88_0==WS) ) {
                            alt88=1;
                        }


                        switch (alt88) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_ifline2071); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt88 >= 1 ) break loop88;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(88, input);
                                throw eee;
                        }
                        cnt88++;
                    } while (true);


                    }

                    e2=(Token)match(input,ID,FOLLOW_ID_in_ifline2077); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new IfLine((h1!=null?h1.getLine():0), IfLine.IFDEF, (e2!=null?e2.getText():null));
                    }

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:374:4: (h2= HASH ( ( WS )* ) IFNDEF ( ( WS )+ ) e3= ID )
                    {
                    // resources/Clang/CPreprocessorLL.g:374:4: (h2= HASH ( ( WS )* ) IFNDEF ( ( WS )+ ) e3= ID )
                    // resources/Clang/CPreprocessorLL.g:374:5: h2= HASH ( ( WS )* ) IFNDEF ( ( WS )+ ) e3= ID
                    {
                    h2=(Token)match(input,HASH,FOLLOW_HASH_in_ifline2088); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:374:13: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:374:14: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:374:14: ( WS )*
                    loop89:
                    do {
                        int alt89=2;
                        int LA89_0 = input.LA(1);

                        if ( (LA89_0==WS) ) {
                            alt89=1;
                        }


                        switch (alt89) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_ifline2091); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);


                    }

                    match(input,IFNDEF,FOLLOW_IFNDEF_in_ifline2095); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:374:26: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:374:27: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:374:27: ( WS )+
                    int cnt90=0;
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==WS) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_ifline2098); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt90 >= 1 ) break loop90;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(90, input);
                                throw eee;
                        }
                        cnt90++;
                    } while (true);


                    }

                    e3=(Token)match(input,ID,FOLLOW_ID_in_ifline2104); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new IfLine((h2!=null?h2.getLine():0), IfLine.IFNDEF, (e3!=null?e3.getText():null));
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, ifline_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "ifline"


    // $ANTLR start "elif"
    // resources/Clang/CPreprocessorLL.g:376:1: elif returns [Elif value] : ( (e1= HASH ( ( WS )* ) IF ( ( WS )+ ) (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) ) ) | e2= HASH ( ( WS )* ) ELIF ( ( WS )+ ) e3= const_expr );
    public final Elif elif() throws RecognitionException {
        Elif value = null;
        int elif_StartIndex = input.index();
        Token e1=null;
        Token not=null;
        Token def=null;
        Token id1=null;
        Token id2=null;
        Token e2=null;
        ConstExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:376:27: ( (e1= HASH ( ( WS )* ) IF ( ( WS )+ ) (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) ) ) | e2= HASH ( ( WS )* ) ELIF ( ( WS )+ ) e3= const_expr )
            int alt102=2;
            alt102 = dfa102.predict(input);
            switch (alt102) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:377:2: (e1= HASH ( ( WS )* ) IF ( ( WS )+ ) (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) ) )
                    {
                    // resources/Clang/CPreprocessorLL.g:377:2: (e1= HASH ( ( WS )* ) IF ( ( WS )+ ) (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) ) )
                    // resources/Clang/CPreprocessorLL.g:377:3: e1= HASH ( ( WS )* ) IF ( ( WS )+ ) (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) )
                    {
                    e1=(Token)match(input,HASH,FOLLOW_HASH_in_elif2124); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:377:12: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:377:13: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:377:13: ( WS )*
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==WS) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_elif2128); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop92;
                        }
                    } while (true);


                    }

                    match(input,IF,FOLLOW_IF_in_elif2132); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:377:22: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:377:23: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:377:23: ( WS )+
                    int cnt93=0;
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==WS) ) {
                            int LA93_2 = input.LA(2);

                            if ( (synpred103_CPreprocessorLL()) ) {
                                alt93=1;
                            }


                        }


                        switch (alt93) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_elif2136); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt93 >= 1 ) break loop93;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(93, input);
                                throw eee;
                        }
                        cnt93++;
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:377:28: (not= NOT )?
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==NOT) ) {
                        alt94=1;
                    }
                    switch (alt94) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:377:29: not= NOT
                            {
                            not=(Token)match(input,NOT,FOLLOW_NOT_in_elif2143); if (state.failed) return value;

                            }
                            break;

                    }

                    // resources/Clang/CPreprocessorLL.g:377:39: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:377:40: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:377:40: ( WS )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==WS) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_elif2148); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop95;
                        }
                    } while (true);


                    }

                    def=(Token)match(input,DEFINED,FOLLOW_DEFINED_in_elif2154); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:377:57: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:377:58: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:377:58: ( WS )*
                    loop96:
                    do {
                        int alt96=2;
                        int LA96_0 = input.LA(1);

                        if ( (LA96_0==WS) ) {
                            alt96=1;
                        }


                        switch (alt96) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_elif2157); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop96;
                        }
                    } while (true);


                    }

                    // resources/Clang/CPreprocessorLL.g:378:5: ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) )
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==LPAREN) ) {
                        alt99=1;
                    }
                    else if ( (LA99_0==ID) ) {
                        alt99=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 99, 0, input);

                        throw nvae;
                    }
                    switch (alt99) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:378:6: ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN )
                            {
                            // resources/Clang/CPreprocessorLL.g:378:6: ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN )
                            // resources/Clang/CPreprocessorLL.g:378:7: LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_elif2168); if (state.failed) return value;
                            // resources/Clang/CPreprocessorLL.g:378:14: ( ( WS )* )
                            // resources/Clang/CPreprocessorLL.g:378:15: ( WS )*
                            {
                            // resources/Clang/CPreprocessorLL.g:378:15: ( WS )*
                            loop97:
                            do {
                                int alt97=2;
                                int LA97_0 = input.LA(1);

                                if ( (LA97_0==WS) ) {
                                    alt97=1;
                                }


                                switch (alt97) {
                            	case 1 :
                            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                            	    {
                            	    match(input,WS,FOLLOW_WS_in_elif2171); if (state.failed) return value;

                            	    }
                            	    break;

                            	default :
                            	    break loop97;
                                }
                            } while (true);


                            }

                            id1=(Token)match(input,ID,FOLLOW_ID_in_elif2177); if (state.failed) return value;
                            // resources/Clang/CPreprocessorLL.g:378:27: ( ( WS )* )
                            // resources/Clang/CPreprocessorLL.g:378:28: ( WS )*
                            {
                            // resources/Clang/CPreprocessorLL.g:378:28: ( WS )*
                            loop98:
                            do {
                                int alt98=2;
                                int LA98_0 = input.LA(1);

                                if ( (LA98_0==WS) ) {
                                    alt98=1;
                                }


                                switch (alt98) {
                            	case 1 :
                            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                            	    {
                            	    match(input,WS,FOLLOW_WS_in_elif2180); if (state.failed) return value;

                            	    }
                            	    break;

                            	default :
                            	    break loop98;
                                }
                            } while (true);


                            }

                            match(input,RPAREN,FOLLOW_RPAREN_in_elif2184); if (state.failed) return value;

                            }


                            }
                            break;
                        case 2 :
                            // resources/Clang/CPreprocessorLL.g:378:42: (id2= ID )
                            {
                            // resources/Clang/CPreprocessorLL.g:378:42: (id2= ID )
                            // resources/Clang/CPreprocessorLL.g:378:43: id2= ID
                            {
                            id2=(Token)match(input,ID,FOLLOW_ID_in_elif2191); if (state.failed) return value;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      value = constructElifLine((e1!=null?e1.getLine():0), (not!=null?not.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null));
                    }

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:381:5: e2= HASH ( ( WS )* ) ELIF ( ( WS )+ ) e3= const_expr
                    {
                    e2=(Token)match(input,HASH,FOLLOW_HASH_in_elif2215); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:381:13: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:381:14: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:381:14: ( WS )*
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==WS) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_elif2218); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop100;
                        }
                    } while (true);


                    }

                    match(input,ELIF,FOLLOW_ELIF_in_elif2222); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:381:24: ( ( WS )+ )
                    // resources/Clang/CPreprocessorLL.g:381:25: ( WS )+
                    {
                    // resources/Clang/CPreprocessorLL.g:381:25: ( WS )+
                    int cnt101=0;
                    loop101:
                    do {
                        int alt101=2;
                        int LA101_0 = input.LA(1);

                        if ( (LA101_0==WS) ) {
                            int LA101_2 = input.LA(2);

                            if ( (synpred112_CPreprocessorLL()) ) {
                                alt101=1;
                            }


                        }


                        switch (alt101) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_elif2225); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt101 >= 1 ) break loop101;
                    	    if (state.backtracking>0) {state.failed=true; return value;}
                                EarlyExitException eee =
                                    new EarlyExitException(101, input);
                                throw eee;
                        }
                        cnt101++;
                    } while (true);


                    }

                    pushFollow(FOLLOW_const_expr_in_elif2231);
                    e3=const_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new Elif((e2!=null?e2.getLine():0), IfLine.CONST_EXPR, e3);
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, elif_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "elif"


    // $ANTLR start "elif_part"
    // resources/Clang/CPreprocessorLL.g:383:1: elif_part returns [ElifPart value] : e1= elif ( ( WS )* ) ( COMMENT )* NEWLINE (e2= program_code | e3= preprocessor_directive )* ;
    public final ElifPart elif_part() throws RecognitionException {
        ElifPart value = null;
        int elif_part_StartIndex = input.index();
        Elif e1 = null;

        ProgramCode e2 = null;

        PreprocessorDirective e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:383:36: (e1= elif ( ( WS )* ) ( COMMENT )* NEWLINE (e2= program_code | e3= preprocessor_directive )* )
            // resources/Clang/CPreprocessorLL.g:384:3: e1= elif ( ( WS )* ) ( COMMENT )* NEWLINE (e2= program_code | e3= preprocessor_directive )*
            {
            pushFollow(FOLLOW_elif_in_elif_part2251);
            e1=elif();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ElifPart(e1.getLineNum(), e1);
            }
            // resources/Clang/CPreprocessorLL.g:384:71: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:384:72: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:384:72: ( WS )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==WS) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_elif_part2256); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:384:77: ( COMMENT )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==COMMENT) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_elif_part2260); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);

            match(input,NEWLINE,FOLLOW_NEWLINE_in_elif_part2263); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:385:5: (e2= program_code | e3= preprocessor_directive )*
            loop105:
            do {
                int alt105=3;
                alt105 = dfa105.predict(input);
                switch (alt105) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:385:6: e2= program_code
            	    {
            	    pushFollow(FOLLOW_program_code_in_elif_part2272);
            	    e2=program_code();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.addPreprocessorUnit(e2);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/CPreprocessorLL.g:386:6: e3= preprocessor_directive
            	    {
            	    pushFollow(FOLLOW_preprocessor_directive_in_elif_part2283);
            	    e3=preprocessor_directive();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.addPreprocessorUnit(e3);
            	    }

            	    }
            	    break;

            	default :
            	    break loop105;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, elif_part_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "elif_part"


    // $ANTLR start "else_part"
    // resources/Clang/CPreprocessorLL.g:389:1: else_part returns [ElsePart value] : e0= HASH ( ( WS )* ) ELSE ( ( WS )* ) ( COMMENT )* NEWLINE (e1= program_code | e2= preprocessor_directive )* ;
    public final ElsePart else_part() throws RecognitionException {
        ElsePart value = null;
        int else_part_StartIndex = input.index();
        Token e0=null;
        ProgramCode e1 = null;

        PreprocessorDirective e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:389:36: (e0= HASH ( ( WS )* ) ELSE ( ( WS )* ) ( COMMENT )* NEWLINE (e1= program_code | e2= preprocessor_directive )* )
            // resources/Clang/CPreprocessorLL.g:390:4: e0= HASH ( ( WS )* ) ELSE ( ( WS )* ) ( COMMENT )* NEWLINE (e1= program_code | e2= preprocessor_directive )*
            {
            e0=(Token)match(input,HASH,FOLLOW_HASH_in_else_part2310); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:390:12: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:390:13: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:390:13: ( WS )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==WS) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_else_part2313); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);


            }

            match(input,ELSE,FOLLOW_ELSE_in_else_part2317); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:390:23: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:390:24: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:390:24: ( WS )*
            loop107:
            do {
                int alt107=2;
                int LA107_0 = input.LA(1);

                if ( (LA107_0==WS) ) {
                    alt107=1;
                }


                switch (alt107) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_else_part2320); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop107;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:390:29: ( COMMENT )*
            loop108:
            do {
                int alt108=2;
                int LA108_0 = input.LA(1);

                if ( (LA108_0==COMMENT) ) {
                    alt108=1;
                }


                switch (alt108) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: COMMENT
            	    {
            	    match(input,COMMENT,FOLLOW_COMMENT_in_else_part2324); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop108;
                }
            } while (true);

            match(input,NEWLINE,FOLLOW_NEWLINE_in_else_part2327); if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = new ElsePart((e0!=null?e0.getLine():0));
            }
            // resources/Clang/CPreprocessorLL.g:391:8: (e1= program_code | e2= preprocessor_directive )*
            loop109:
            do {
                int alt109=3;
                alt109 = dfa109.predict(input);
                switch (alt109) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:391:9: e1= program_code
            	    {
            	    pushFollow(FOLLOW_program_code_in_else_part2341);
            	    e1=program_code();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.addPreprocessorUnit(e1);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/CPreprocessorLL.g:392:9: e2= preprocessor_directive
            	    {
            	    pushFollow(FOLLOW_preprocessor_directive_in_else_part2355);
            	    e2=preprocessor_directive();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.addPreprocessorUnit(e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop109;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, else_part_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "else_part"


    // $ANTLR start "token_sequence"
    // resources/Clang/CPreprocessorLL.g:395:1: token_sequence returns [TokenSequence value] : (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA ) )+ ;
    public final TokenSequence token_sequence() throws RecognitionException {
        TokenSequence value = null;
        int token_sequence_StartIndex = input.index();
        Token e1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:395:45: ( (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA ) )+ )
            // resources/Clang/CPreprocessorLL.g:396:3: (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA ) )+
            {
            // resources/Clang/CPreprocessorLL.g:396:3: (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA ) )+
            int cnt110=0;
            loop110:
            do {
                int alt110=2;
                switch ( input.LA(1) ) {
                case WS:
                    {
                    int LA110_1 = input.LA(2);

                    if ( (synpred188_CPreprocessorLL()) ) {
                        alt110=1;
                    }


                    }
                    break;
                case COMMENT:
                    {
                    int LA110_2 = input.LA(2);

                    if ( (synpred188_CPreprocessorLL()) ) {
                        alt110=1;
                    }


                    }
                    break;
                case HASH:
                case DOUBLE_HASH:
                case INCLUDE:
                case DEFINE:
                case DEFINED:
                case UNDEF:
                case LINE:
                case ERROR:
                case PRAGMA:
                case IF:
                case ELSE:
                case SEMICOLON:
                case COMMA:
                case ASSIGN:
                case PLUS:
                case MINUS:
                case COLON:
                case DOT:
                case LPAREN:
                case RPAREN:
                case LCURLY:
                case RCURLY:
                case LBRACK:
                case RBRACK:
                case PIPE:
                case QUESTION:
                case NOT:
                case EQUALS:
                case NOT_EQUALS:
                case DEREFERENCE:
                case AMPERSAND:
                case INCREMENT:
                case DECREMENT:
                case MULTIPLY_ASSIGN:
                case DIVIDE_ASSIGN:
                case ADD_ASSIGN:
                case MINUS_ASSIGN:
                case MODULO_ASSIGN:
                case BITWISE_AND_ASSIGN:
                case BITWISE_XOR_ASSIGN:
                case BITWISE_OR_ASSIGN:
                case LEFT_SHIFT_ASSIGN:
                case RIGHT_SHIFT_ASSIGN:
                case LESSER_THAN:
                case GREATER_THAN:
                case LESSER_THAN_OR_EQUAL_TO:
                case GREATER_THAN_OR_EQUAL_TO:
                case LEFT_SHIFT:
                case RIGHT_SHIFT:
                case TILDE:
                case CARET:
                case OR:
                case AND:
                case STAR:
                case DIVIDE:
                case MODULO:
                case ELLIPSES:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case HEX_LITERAL:
                case FLOATING_LITERAL:
                case CHAR_LITERAL:
                case LINE_COMMENT:
                case ID:
                case STRING_LITERAL:
                    {
                    alt110=1;
                    }
                    break;

                }

                switch (alt110) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:396:4: e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA )
            	    {
            	    e1=(Token)input.LT(1);
            	    if ( (input.LA(1)>=HASH && input.LA(1)<=ERROR)||(input.LA(1)>=PRAGMA && input.LA(1)<=IF)||input.LA(1)==ELSE||(input.LA(1)>=SEMICOLON && input.LA(1)<=ELLIPSES)||(input.LA(1)>=DECIMAL_LITERAL && input.LA(1)<=OCTAL_LITERAL)||input.LA(1)==HEX_LITERAL||input.LA(1)==FLOATING_LITERAL||input.LA(1)==CHAR_LITERAL||(input.LA(1)>=WS && input.LA(1)<=LINE_COMMENT)||input.LA(1)==ID||input.LA(1)==STRING_LITERAL ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	       value = addToken(value, (e1!=null?e1.getLine():0), (e1!=null?e1.getText():null));
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt110 >= 1 ) break loop110;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(110, input);
                        throw eee;
                }
                cnt110++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, token_sequence_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "token_sequence"


    // $ANTLR start "program_code"
    // resources/Clang/CPreprocessorLL.g:406:1: program_code returns [ProgramCode value] : (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME ) )+ ;
    public final ProgramCode program_code() throws RecognitionException {
        ProgramCode value = null;
        int program_code_StartIndex = input.index();
        Token e1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:406:41: ( (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME ) )+ )
            // resources/Clang/CPreprocessorLL.g:407:3: (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME ) )+
            {
            // resources/Clang/CPreprocessorLL.g:407:3: (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME ) )+
            int cnt111=0;
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( ((LA111_0>=INCLUDE && LA111_0<=ERROR)||(LA111_0>=PRAGMA && LA111_0<=IF)||LA111_0==ELSE||(LA111_0>=SEMICOLON && LA111_0<=ELLIPSES)||(LA111_0>=DECIMAL_LITERAL && LA111_0<=OCTAL_LITERAL)||LA111_0==HEX_LITERAL||LA111_0==FLOATING_LITERAL||(LA111_0>=CHAR_LITERAL && LA111_0<=LINE_COMMENT)||(LA111_0>=ID && LA111_0<=STRING_LITERAL)) ) {
                    int LA111_2 = input.LA(2);

                    if ( (synpred255_CPreprocessorLL()) ) {
                        alt111=1;
                    }


                }


                switch (alt111) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:407:4: e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME )
            	    {
            	    e1=(Token)input.LT(1);
            	    if ( (input.LA(1)>=INCLUDE && input.LA(1)<=ERROR)||(input.LA(1)>=PRAGMA && input.LA(1)<=IF)||input.LA(1)==ELSE||(input.LA(1)>=SEMICOLON && input.LA(1)<=ELLIPSES)||(input.LA(1)>=DECIMAL_LITERAL && input.LA(1)<=OCTAL_LITERAL)||input.LA(1)==HEX_LITERAL||input.LA(1)==FLOATING_LITERAL||(input.LA(1)>=CHAR_LITERAL && input.LA(1)<=LINE_COMMENT)||(input.LA(1)>=ID && input.LA(1)<=STRING_LITERAL) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	       value = addToken(value, (e1!=null?e1.getLine():0), (e1!=null?e1.getText():null));
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt111 >= 1 ) break loop111;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(111, input);
                        throw eee;
                }
                cnt111++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, program_code_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "program_code"


    // $ANTLR start "filename_lib"
    // resources/Clang/CPreprocessorLL.g:417:1: filename_lib returns [FileNameLib value] : e= ( ID | FILENAME ) ( (d1= DOT e1= ID ) )* (d= DIVIDE e3= ( ID | FILENAME ) ( (d2= DOT e2= ID ) )* )* ;
    public final FileNameLib filename_lib() throws RecognitionException {
        FileNameLib value = null;
        int filename_lib_StartIndex = input.index();
        Token e=null;
        Token d1=null;
        Token e1=null;
        Token d=null;
        Token e3=null;
        Token d2=null;
        Token e2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:417:42: (e= ( ID | FILENAME ) ( (d1= DOT e1= ID ) )* (d= DIVIDE e3= ( ID | FILENAME ) ( (d2= DOT e2= ID ) )* )* )
            // resources/Clang/CPreprocessorLL.g:418:4: e= ( ID | FILENAME ) ( (d1= DOT e1= ID ) )* (d= DIVIDE e3= ( ID | FILENAME ) ( (d2= DOT e2= ID ) )* )*
            {
            e=(Token)input.LT(1);
            if ( (input.LA(1)>=ID && input.LA(1)<=FILENAME) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
              value = new FileNameLib((e!=null?e.getLine():0), (e!=null?e.getText():null));
            }
            // resources/Clang/CPreprocessorLL.g:418:66: ( (d1= DOT e1= ID ) )*
            loop112:
            do {
                int alt112=2;
                int LA112_0 = input.LA(1);

                if ( (LA112_0==DOT) ) {
                    alt112=1;
                }


                switch (alt112) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:418:67: (d1= DOT e1= ID )
            	    {
            	    // resources/Clang/CPreprocessorLL.g:418:67: (d1= DOT e1= ID )
            	    // resources/Clang/CPreprocessorLL.g:418:68: d1= DOT e1= ID
            	    {
            	    d1=(Token)match(input,DOT,FOLLOW_DOT_in_filename_lib3018); if (state.failed) return value;
            	    e1=(Token)match(input,ID,FOLLOW_ID_in_filename_lib3022); if (state.failed) return value;

            	    }

            	    if ( state.backtracking==0 ) {
            	      if((d1!=null?d1.getText():null) != null && (e1!=null?e1.getText():null) != null) value.addToFileName("." +(e1!=null?e1.getText():null));
            	    }

            	    }
            	    break;

            	default :
            	    break loop112;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:418:164: (d= DIVIDE e3= ( ID | FILENAME ) ( (d2= DOT e2= ID ) )* )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( (LA114_0==DIVIDE) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:418:165: d= DIVIDE e3= ( ID | FILENAME ) ( (d2= DOT e2= ID ) )*
            	    {
            	    d=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_filename_lib3032); if (state.failed) return value;
            	    e3=(Token)input.LT(1);
            	    if ( (input.LA(1)>=ID && input.LA(1)<=FILENAME) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      if((e3!=null?e3.getText():null) != null) value.addToFileName("/" +(e3!=null?e3.getText():null));
            	    }
            	    // resources/Clang/CPreprocessorLL.g:418:251: ( (d2= DOT e2= ID ) )*
            	    loop113:
            	    do {
            	        int alt113=2;
            	        int LA113_0 = input.LA(1);

            	        if ( (LA113_0==DOT) ) {
            	            alt113=1;
            	        }


            	        switch (alt113) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:418:252: (d2= DOT e2= ID )
            	    	    {
            	    	    // resources/Clang/CPreprocessorLL.g:418:252: (d2= DOT e2= ID )
            	    	    // resources/Clang/CPreprocessorLL.g:418:253: d2= DOT e2= ID
            	    	    {
            	    	    d2=(Token)match(input,DOT,FOLLOW_DOT_in_filename_lib3048); if (state.failed) return value;
            	    	    e2=(Token)match(input,ID,FOLLOW_ID_in_filename_lib3052); if (state.failed) return value;

            	    	    }

            	    	    if ( state.backtracking==0 ) {
            	    	      if((d2!=null?d2.getText():null) != null && (e2!=null?e2.getText():null) != null) value.addToFileName("." +(e2!=null?e2.getText():null));
            	    	    }

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop113;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop114;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 17, filename_lib_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "filename_lib"


    // $ANTLR start "const_expr"
    // resources/Clang/CPreprocessorLL.g:425:1: const_expr returns [ConstExpr value] : ( LPAREN )? e= conditional_expression ( RPAREN )? ;
    public final ConstExpr const_expr() throws RecognitionException {
        ConstExpr value = null;
        int const_expr_StartIndex = input.index();
        ConditionalExpr e = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:426:2: ( ( LPAREN )? e= conditional_expression ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:426:4: ( LPAREN )? e= conditional_expression ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:426:4: ( LPAREN )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==LPAREN) ) {
                int LA115_1 = input.LA(2);

                if ( (synpred261_CPreprocessorLL()) ) {
                    alt115=1;
                }
            }
            switch (alt115) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_const_expr3077); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_conditional_expression_in_const_expr3082);
            e=conditional_expression();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ConstExpr(); value.setConditionalExpr(e); 
              			setLineNumAndPos(value, e.getLineNum(), e.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:427:73: ( RPAREN )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==RPAREN) ) {
                int LA116_1 = input.LA(2);

                if ( (synpred262_CPreprocessorLL()) ) {
                    alt116=1;
                }
            }
            switch (alt116) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_const_expr3086); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 18, const_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "const_expr"


    // $ANTLR start "conditional_expression"
    // resources/Clang/CPreprocessorLL.g:429:1: conditional_expression returns [ConditionalExpr value] : ( LPAREN )? e1= logical_or_expr ( ( WS )* ) ( QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression )? ( RPAREN )? ;
    public final ConditionalExpr conditional_expression() throws RecognitionException {
        ConditionalExpr value = null;
        int conditional_expression_StartIndex = input.index();
        LogicalOrExpr e1 = null;

        ConstExpr e2 = null;

        ConditionalExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:430:2: ( ( LPAREN )? e1= logical_or_expr ( ( WS )* ) ( QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression )? ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:430:4: ( LPAREN )? e1= logical_or_expr ( ( WS )* ) ( QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression )? ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:430:4: ( LPAREN )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==LPAREN) ) {
                int LA117_1 = input.LA(2);

                if ( (synpred263_CPreprocessorLL()) ) {
                    alt117=1;
                }
            }
            switch (alt117) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditional_expression3101); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_logical_or_expr_in_conditional_expression3106);
            e1=logical_or_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ConditionalExpr(); value.setLogicalOrExpr(e1); 
              			setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:432:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:432:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:432:4: ( WS )*
            loop118:
            do {
                int alt118=2;
                int LA118_0 = input.LA(1);

                if ( (LA118_0==WS) ) {
                    int LA118_2 = input.LA(2);

                    if ( (synpred264_CPreprocessorLL()) ) {
                        alt118=1;
                    }


                }


                switch (alt118) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_conditional_expression3113); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop118;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:432:9: ( QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==QUESTION) ) {
                int LA122_1 = input.LA(2);

                if ( (synpred268_CPreprocessorLL()) ) {
                    alt122=1;
                }
            }
            switch (alt122) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:432:10: QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_conditional_expression3118); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:432:19: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:432:20: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:432:20: ( WS )*
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==WS) ) {
                            int LA119_2 = input.LA(2);

                            if ( (synpred265_CPreprocessorLL()) ) {
                                alt119=1;
                            }


                        }


                        switch (alt119) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_conditional_expression3121); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop119;
                        }
                    } while (true);


                    }

                    pushFollow(FOLLOW_const_expr_in_conditional_expression3128);
                    e2=const_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setTrueInConditional(e2);
                    }
                    // resources/Clang/CPreprocessorLL.g:433:4: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:433:5: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:433:5: ( WS )*
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==WS) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_conditional_expression3137); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop120;
                        }
                    } while (true);


                    }

                    match(input,COLON,FOLLOW_COLON_in_conditional_expression3141); if (state.failed) return value;
                    // resources/Clang/CPreprocessorLL.g:433:16: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:433:17: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:433:17: ( WS )*
                    loop121:
                    do {
                        int alt121=2;
                        int LA121_0 = input.LA(1);

                        if ( (LA121_0==WS) ) {
                            int LA121_2 = input.LA(2);

                            if ( (synpred267_CPreprocessorLL()) ) {
                                alt121=1;
                            }


                        }


                        switch (alt121) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_conditional_expression3144); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop121;
                        }
                    } while (true);


                    }

                    pushFollow(FOLLOW_conditional_expression_in_conditional_expression3151);
                    e3=conditional_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setFalseInConditional(e3);
                    }

                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:433:94: ( RPAREN )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==RPAREN) ) {
                int LA123_1 = input.LA(2);

                if ( (synpred269_CPreprocessorLL()) ) {
                    alt123=1;
                }
            }
            switch (alt123) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_conditional_expression3157); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 19, conditional_expression_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "conditional_expression"


    // $ANTLR start "logical_or_expr"
    // resources/Clang/CPreprocessorLL.g:435:1: logical_or_expr returns [LogicalOrExpr value] : ( LPAREN )? e1= logical_and_expr ( ( WS )* ) ( OR ( ( WS )* ) e2= logical_and_expr )* ( RPAREN )? ;
    public final LogicalOrExpr logical_or_expr() throws RecognitionException {
        LogicalOrExpr value = null;
        int logical_or_expr_StartIndex = input.index();
        LogicalAndExpr e1 = null;

        LogicalAndExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:436:2: ( ( LPAREN )? e1= logical_and_expr ( ( WS )* ) ( OR ( ( WS )* ) e2= logical_and_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:436:4: ( LPAREN )? e1= logical_and_expr ( ( WS )* ) ( OR ( ( WS )* ) e2= logical_and_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:436:4: ( LPAREN )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==LPAREN) ) {
                int LA124_1 = input.LA(2);

                if ( (synpred270_CPreprocessorLL()) ) {
                    alt124=1;
                }
            }
            switch (alt124) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_logical_or_expr3172); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_logical_and_expr_in_logical_or_expr3177);
            e1=logical_and_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new LogicalOrExpr(null, e1); 
              				setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:438:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:438:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:438:4: ( WS )*
            loop125:
            do {
                int alt125=2;
                int LA125_0 = input.LA(1);

                if ( (LA125_0==WS) ) {
                    int LA125_2 = input.LA(2);

                    if ( (synpred271_CPreprocessorLL()) ) {
                        alt125=1;
                    }


                }


                switch (alt125) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_logical_or_expr3185); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop125;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:438:9: ( OR ( ( WS )* ) e2= logical_and_expr )*
            loop127:
            do {
                int alt127=2;
                int LA127_0 = input.LA(1);

                if ( (LA127_0==OR) ) {
                    int LA127_2 = input.LA(2);

                    if ( (synpred273_CPreprocessorLL()) ) {
                        alt127=1;
                    }


                }


                switch (alt127) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:438:10: OR ( ( WS )* ) e2= logical_and_expr
            	    {
            	    match(input,OR,FOLLOW_OR_in_logical_or_expr3190); if (state.failed) return value;
            	    // resources/Clang/CPreprocessorLL.g:438:13: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:438:14: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:438:14: ( WS )*
            	    loop126:
            	    do {
            	        int alt126=2;
            	        int LA126_0 = input.LA(1);

            	        if ( (LA126_0==WS) ) {
            	            int LA126_2 = input.LA(2);

            	            if ( (synpred272_CPreprocessorLL()) ) {
            	                alt126=1;
            	            }


            	        }


            	        switch (alt126) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_logical_or_expr3193); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop126;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_logical_and_expr_in_logical_or_expr3199);
            	    e2=logical_and_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new LogicalOrExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop127;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:438:90: ( RPAREN )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==RPAREN) ) {
                int LA128_1 = input.LA(2);

                if ( (synpred274_CPreprocessorLL()) ) {
                    alt128=1;
                }
            }
            switch (alt128) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_logical_or_expr3205); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 20, logical_or_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "logical_or_expr"


    // $ANTLR start "logical_and_expr"
    // resources/Clang/CPreprocessorLL.g:440:1: logical_and_expr returns [LogicalAndExpr value] : ( LPAREN )? e1= inclusive_or_expr ( ( WS )* ) ( AND ( ( WS )* ) e2= inclusive_or_expr )* ( RPAREN )? ;
    public final LogicalAndExpr logical_and_expr() throws RecognitionException {
        LogicalAndExpr value = null;
        int logical_and_expr_StartIndex = input.index();
        InclusiveOrExpr e1 = null;

        InclusiveOrExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:441:2: ( ( LPAREN )? e1= inclusive_or_expr ( ( WS )* ) ( AND ( ( WS )* ) e2= inclusive_or_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:441:4: ( LPAREN )? e1= inclusive_or_expr ( ( WS )* ) ( AND ( ( WS )* ) e2= inclusive_or_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:441:4: ( LPAREN )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==LPAREN) ) {
                int LA129_1 = input.LA(2);

                if ( (synpred275_CPreprocessorLL()) ) {
                    alt129=1;
                }
            }
            switch (alt129) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_logical_and_expr3220); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_inclusive_or_expr_in_logical_and_expr3225);
            e1=inclusive_or_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new LogicalAndExpr(null, e1); 
              					setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:443:4: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:443:5: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:443:5: ( WS )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( (LA130_0==WS) ) {
                    int LA130_2 = input.LA(2);

                    if ( (synpred276_CPreprocessorLL()) ) {
                        alt130=1;
                    }


                }


                switch (alt130) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_logical_and_expr3234); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop130;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:443:10: ( AND ( ( WS )* ) e2= inclusive_or_expr )*
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==AND) ) {
                    int LA132_2 = input.LA(2);

                    if ( (synpred278_CPreprocessorLL()) ) {
                        alt132=1;
                    }


                }


                switch (alt132) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:443:11: AND ( ( WS )* ) e2= inclusive_or_expr
            	    {
            	    match(input,AND,FOLLOW_AND_in_logical_and_expr3239); if (state.failed) return value;
            	    // resources/Clang/CPreprocessorLL.g:443:15: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:443:16: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:443:16: ( WS )*
            	    loop131:
            	    do {
            	        int alt131=2;
            	        int LA131_0 = input.LA(1);

            	        if ( (LA131_0==WS) ) {
            	            int LA131_2 = input.LA(2);

            	            if ( (synpred277_CPreprocessorLL()) ) {
            	                alt131=1;
            	            }


            	        }


            	        switch (alt131) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_logical_and_expr3242); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop131;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_inclusive_or_expr_in_logical_and_expr3248);
            	    e2=inclusive_or_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new LogicalAndExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop132;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:443:94: ( RPAREN )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==RPAREN) ) {
                int LA133_1 = input.LA(2);

                if ( (synpred279_CPreprocessorLL()) ) {
                    alt133=1;
                }
            }
            switch (alt133) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_logical_and_expr3254); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 21, logical_and_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "logical_and_expr"


    // $ANTLR start "inclusive_or_expr"
    // resources/Clang/CPreprocessorLL.g:445:1: inclusive_or_expr returns [InclusiveOrExpr value] : ( LPAREN )? e1= exclusive_or_expr ( ( WS )* ) ( PIPE ( ( WS )* ) e2= exclusive_or_expr )* ( RPAREN )? ;
    public final InclusiveOrExpr inclusive_or_expr() throws RecognitionException {
        InclusiveOrExpr value = null;
        int inclusive_or_expr_StartIndex = input.index();
        ExclusiveOrExpr e1 = null;

        ExclusiveOrExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:446:2: ( ( LPAREN )? e1= exclusive_or_expr ( ( WS )* ) ( PIPE ( ( WS )* ) e2= exclusive_or_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:446:4: ( LPAREN )? e1= exclusive_or_expr ( ( WS )* ) ( PIPE ( ( WS )* ) e2= exclusive_or_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:446:4: ( LPAREN )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==LPAREN) ) {
                int LA134_1 = input.LA(2);

                if ( (synpred280_CPreprocessorLL()) ) {
                    alt134=1;
                }
            }
            switch (alt134) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_inclusive_or_expr3269); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_exclusive_or_expr_in_inclusive_or_expr3274);
            e1=exclusive_or_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new InclusiveOrExpr(null, e1); setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:447:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:447:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:447:4: ( WS )*
            loop135:
            do {
                int alt135=2;
                int LA135_0 = input.LA(1);

                if ( (LA135_0==WS) ) {
                    int LA135_2 = input.LA(2);

                    if ( (synpred281_CPreprocessorLL()) ) {
                        alt135=1;
                    }


                }


                switch (alt135) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_inclusive_or_expr3283); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop135;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:447:9: ( PIPE ( ( WS )* ) e2= exclusive_or_expr )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==PIPE) ) {
                    int LA137_2 = input.LA(2);

                    if ( (synpred283_CPreprocessorLL()) ) {
                        alt137=1;
                    }


                }


                switch (alt137) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:447:10: PIPE ( ( WS )* ) e2= exclusive_or_expr
            	    {
            	    match(input,PIPE,FOLLOW_PIPE_in_inclusive_or_expr3288); if (state.failed) return value;
            	    // resources/Clang/CPreprocessorLL.g:447:15: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:447:16: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:447:16: ( WS )*
            	    loop136:
            	    do {
            	        int alt136=2;
            	        int LA136_0 = input.LA(1);

            	        if ( (LA136_0==WS) ) {
            	            int LA136_2 = input.LA(2);

            	            if ( (synpred282_CPreprocessorLL()) ) {
            	                alt136=1;
            	            }


            	        }


            	        switch (alt136) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_inclusive_or_expr3291); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop136;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_exclusive_or_expr_in_inclusive_or_expr3297);
            	    e2=exclusive_or_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new InclusiveOrExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop137;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:448:56: ( RPAREN )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==RPAREN) ) {
                int LA138_1 = input.LA(2);

                if ( (synpred284_CPreprocessorLL()) ) {
                    alt138=1;
                }
            }
            switch (alt138) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_inclusive_or_expr3306); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 22, inclusive_or_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "inclusive_or_expr"


    // $ANTLR start "exclusive_or_expr"
    // resources/Clang/CPreprocessorLL.g:450:1: exclusive_or_expr returns [ExclusiveOrExpr value] : ( LPAREN )? e1= and_expr ( ( WS )* ) ( (sign= CARET ) ( ( WS )* ) e2= and_expr )* ( RPAREN )? ;
    public final ExclusiveOrExpr exclusive_or_expr() throws RecognitionException {
        ExclusiveOrExpr value = null;
        int exclusive_or_expr_StartIndex = input.index();
        Token sign=null;
        AndExpr e1 = null;

        AndExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:451:2: ( ( LPAREN )? e1= and_expr ( ( WS )* ) ( (sign= CARET ) ( ( WS )* ) e2= and_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:451:4: ( LPAREN )? e1= and_expr ( ( WS )* ) ( (sign= CARET ) ( ( WS )* ) e2= and_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:451:4: ( LPAREN )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==LPAREN) ) {
                int LA139_1 = input.LA(2);

                if ( (synpred285_CPreprocessorLL()) ) {
                    alt139=1;
                }
            }
            switch (alt139) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_exclusive_or_expr3321); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_and_expr_in_exclusive_or_expr3326);
            e1=and_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ExclusiveOrExpr(null, e1); setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:452:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:452:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:452:4: ( WS )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0==WS) ) {
                    int LA140_2 = input.LA(2);

                    if ( (synpred286_CPreprocessorLL()) ) {
                        alt140=1;
                    }


                }


                switch (alt140) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_exclusive_or_expr3334); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop140;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:452:8: ( (sign= CARET ) ( ( WS )* ) e2= and_expr )*
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0==CARET) ) {
                    int LA142_2 = input.LA(2);

                    if ( (synpred288_CPreprocessorLL()) ) {
                        alt142=1;
                    }


                }


                switch (alt142) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:452:9: (sign= CARET ) ( ( WS )* ) e2= and_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:452:9: (sign= CARET )
            	    // resources/Clang/CPreprocessorLL.g:452:10: sign= CARET
            	    {
            	    sign=(Token)match(input,CARET,FOLLOW_CARET_in_exclusive_or_expr3341); if (state.failed) return value;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:452:22: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:452:23: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:452:23: ( WS )*
            	    loop141:
            	    do {
            	        int alt141=2;
            	        int LA141_0 = input.LA(1);

            	        if ( (LA141_0==WS) ) {
            	            int LA141_2 = input.LA(2);

            	            if ( (synpred287_CPreprocessorLL()) ) {
            	                alt141=1;
            	            }


            	        }


            	        switch (alt141) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_exclusive_or_expr3345); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop141;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_and_expr_in_exclusive_or_expr3351);
            	    e2=and_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new ExclusiveOrExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop142;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:452:93: ( RPAREN )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==RPAREN) ) {
                int LA143_1 = input.LA(2);

                if ( (synpred289_CPreprocessorLL()) ) {
                    alt143=1;
                }
            }
            switch (alt143) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_exclusive_or_expr3357); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 23, exclusive_or_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "exclusive_or_expr"


    // $ANTLR start "and_expr"
    // resources/Clang/CPreprocessorLL.g:454:1: and_expr returns [AndExpr value] : ( LPAREN )? e1= equality_expr ( ( WS )* ) ( (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr )* ( RPAREN )? ;
    public final AndExpr and_expr() throws RecognitionException {
        AndExpr value = null;
        int and_expr_StartIndex = input.index();
        Token sign=null;
        EqualityExpr e1 = null;

        EqualityExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:455:2: ( ( LPAREN )? e1= equality_expr ( ( WS )* ) ( (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:455:4: ( LPAREN )? e1= equality_expr ( ( WS )* ) ( (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:455:4: ( LPAREN )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==LPAREN) ) {
                int LA144_1 = input.LA(2);

                if ( (synpred290_CPreprocessorLL()) ) {
                    alt144=1;
                }
            }
            switch (alt144) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_and_expr3372); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_equality_expr_in_and_expr3377);
            e1=equality_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new AndExpr(null, e1); setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:456:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:456:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:456:4: ( WS )*
            loop145:
            do {
                int alt145=2;
                int LA145_0 = input.LA(1);

                if ( (LA145_0==WS) ) {
                    int LA145_2 = input.LA(2);

                    if ( (synpred291_CPreprocessorLL()) ) {
                        alt145=1;
                    }


                }


                switch (alt145) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_and_expr3386); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop145;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:456:9: ( (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr )*
            loop147:
            do {
                int alt147=2;
                int LA147_0 = input.LA(1);

                if ( (LA147_0==AMPERSAND) ) {
                    int LA147_2 = input.LA(2);

                    if ( (synpred293_CPreprocessorLL()) ) {
                        alt147=1;
                    }


                }


                switch (alt147) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:456:10: (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:456:10: (sign= AMPERSAND )
            	    // resources/Clang/CPreprocessorLL.g:456:11: sign= AMPERSAND
            	    {
            	    sign=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_and_expr3394); if (state.failed) return value;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:456:27: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:456:28: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:456:28: ( WS )*
            	    loop146:
            	    do {
            	        int alt146=2;
            	        int LA146_0 = input.LA(1);

            	        if ( (LA146_0==WS) ) {
            	            int LA146_2 = input.LA(2);

            	            if ( (synpred292_CPreprocessorLL()) ) {
            	                alt146=1;
            	            }


            	        }


            	        switch (alt146) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_and_expr3398); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop146;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_equality_expr_in_and_expr3404);
            	    e2=equality_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new AndExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop147;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:456:95: ( RPAREN )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==RPAREN) ) {
                int LA148_1 = input.LA(2);

                if ( (synpred294_CPreprocessorLL()) ) {
                    alt148=1;
                }
            }
            switch (alt148) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_and_expr3410); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 24, and_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "and_expr"


    // $ANTLR start "equality_expr"
    // resources/Clang/CPreprocessorLL.g:458:1: equality_expr returns [EqualityExpr value] : ( LPAREN )? e1= relational_expr ( ( WS )* ) ( (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr )* ( RPAREN )? ;
    public final EqualityExpr equality_expr() throws RecognitionException {
        EqualityExpr value = null;
        int equality_expr_StartIndex = input.index();
        Token sign=null;
        RelationalExpr e1 = null;

        RelationalExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:459:2: ( ( LPAREN )? e1= relational_expr ( ( WS )* ) ( (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:459:4: ( LPAREN )? e1= relational_expr ( ( WS )* ) ( (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:459:4: ( LPAREN )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==LPAREN) ) {
                int LA149_1 = input.LA(2);

                if ( (synpred295_CPreprocessorLL()) ) {
                    alt149=1;
                }
            }
            switch (alt149) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_equality_expr3425); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_relational_expr_in_equality_expr3430);
            e1=relational_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new EqualityExpr(null, e1, -1); 
              					setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:461:4: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:461:5: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:461:5: ( WS )*
            loop150:
            do {
                int alt150=2;
                int LA150_0 = input.LA(1);

                if ( (LA150_0==WS) ) {
                    int LA150_2 = input.LA(2);

                    if ( (synpred296_CPreprocessorLL()) ) {
                        alt150=1;
                    }


                }


                switch (alt150) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_equality_expr3439); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop150;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:461:10: ( (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr )*
            loop153:
            do {
                int alt153=2;
                int LA153_0 = input.LA(1);

                if ( (LA153_0==EQUALS) ) {
                    int LA153_2 = input.LA(2);

                    if ( (synpred299_CPreprocessorLL()) ) {
                        alt153=1;
                    }


                }
                else if ( (LA153_0==NOT_EQUALS) ) {
                    int LA153_3 = input.LA(2);

                    if ( (synpred299_CPreprocessorLL()) ) {
                        alt153=1;
                    }


                }


                switch (alt153) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:461:11: (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:461:11: (sign= EQUALS | sign= NOT_EQUALS )
            	    int alt151=2;
            	    int LA151_0 = input.LA(1);

            	    if ( (LA151_0==EQUALS) ) {
            	        alt151=1;
            	    }
            	    else if ( (LA151_0==NOT_EQUALS) ) {
            	        alt151=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 151, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt151) {
            	        case 1 :
            	            // resources/Clang/CPreprocessorLL.g:461:12: sign= EQUALS
            	            {
            	            sign=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_equality_expr3447); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/CPreprocessorLL.g:461:26: sign= NOT_EQUALS
            	            {
            	            sign=(Token)match(input,NOT_EQUALS,FOLLOW_NOT_EQUALS_in_equality_expr3453); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:461:44: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:461:45: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:461:45: ( WS )*
            	    loop152:
            	    do {
            	        int alt152=2;
            	        int LA152_0 = input.LA(1);

            	        if ( (LA152_0==WS) ) {
            	            int LA152_2 = input.LA(2);

            	            if ( (synpred298_CPreprocessorLL()) ) {
            	                alt152=1;
            	            }


            	        }


            	        switch (alt152) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_equality_expr3458); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop152;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_relational_expr_in_equality_expr3468);
            	    e2=relational_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new EqualityExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop153;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:462:111: ( RPAREN )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==RPAREN) ) {
                int LA154_1 = input.LA(2);

                if ( (synpred300_CPreprocessorLL()) ) {
                    alt154=1;
                }
            }
            switch (alt154) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_equality_expr3475); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 25, equality_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "equality_expr"


    // $ANTLR start "relational_expr"
    // resources/Clang/CPreprocessorLL.g:464:1: relational_expr returns [RelationalExpr value] : ( LPAREN )? e1= shift_expr ( ( WS )* ) ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr )* ( RPAREN )? ;
    public final RelationalExpr relational_expr() throws RecognitionException {
        RelationalExpr value = null;
        int relational_expr_StartIndex = input.index();
        Token sign=null;
        ShiftExpr e1 = null;

        ShiftExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:465:2: ( ( LPAREN )? e1= shift_expr ( ( WS )* ) ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:465:4: ( LPAREN )? e1= shift_expr ( ( WS )* ) ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:465:4: ( LPAREN )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==LPAREN) ) {
                int LA155_1 = input.LA(2);

                if ( (synpred301_CPreprocessorLL()) ) {
                    alt155=1;
                }
            }
            switch (alt155) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_relational_expr3490); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_shift_expr_in_relational_expr3495);
            e1=shift_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new RelationalExpr(null, e1, -1); 
              					setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:467:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:467:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:467:4: ( WS )*
            loop156:
            do {
                int alt156=2;
                int LA156_0 = input.LA(1);

                if ( (LA156_0==WS) ) {
                    int LA156_2 = input.LA(2);

                    if ( (synpred302_CPreprocessorLL()) ) {
                        alt156=1;
                    }


                }


                switch (alt156) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_relational_expr3504); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop156;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:467:9: ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr )*
            loop159:
            do {
                int alt159=2;
                switch ( input.LA(1) ) {
                case LESSER_THAN:
                    {
                    int LA159_2 = input.LA(2);

                    if ( (synpred307_CPreprocessorLL()) ) {
                        alt159=1;
                    }


                    }
                    break;
                case GREATER_THAN:
                    {
                    int LA159_3 = input.LA(2);

                    if ( (synpred307_CPreprocessorLL()) ) {
                        alt159=1;
                    }


                    }
                    break;
                case LESSER_THAN_OR_EQUAL_TO:
                    {
                    int LA159_4 = input.LA(2);

                    if ( (synpred307_CPreprocessorLL()) ) {
                        alt159=1;
                    }


                    }
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    {
                    int LA159_5 = input.LA(2);

                    if ( (synpred307_CPreprocessorLL()) ) {
                        alt159=1;
                    }


                    }
                    break;

                }

                switch (alt159) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:467:10: (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:467:10: (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO )
            	    int alt157=4;
            	    switch ( input.LA(1) ) {
            	    case LESSER_THAN:
            	        {
            	        alt157=1;
            	        }
            	        break;
            	    case GREATER_THAN:
            	        {
            	        alt157=2;
            	        }
            	        break;
            	    case LESSER_THAN_OR_EQUAL_TO:
            	        {
            	        alt157=3;
            	        }
            	        break;
            	    case GREATER_THAN_OR_EQUAL_TO:
            	        {
            	        alt157=4;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 157, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt157) {
            	        case 1 :
            	            // resources/Clang/CPreprocessorLL.g:467:12: sign= LESSER_THAN
            	            {
            	            sign=(Token)match(input,LESSER_THAN,FOLLOW_LESSER_THAN_in_relational_expr3513); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/CPreprocessorLL.g:467:31: sign= GREATER_THAN
            	            {
            	            sign=(Token)match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_relational_expr3519); if (state.failed) return value;

            	            }
            	            break;
            	        case 3 :
            	            // resources/Clang/CPreprocessorLL.g:467:51: sign= LESSER_THAN_OR_EQUAL_TO
            	            {
            	            sign=(Token)match(input,LESSER_THAN_OR_EQUAL_TO,FOLLOW_LESSER_THAN_OR_EQUAL_TO_in_relational_expr3525); if (state.failed) return value;

            	            }
            	            break;
            	        case 4 :
            	            // resources/Clang/CPreprocessorLL.g:467:82: sign= GREATER_THAN_OR_EQUAL_TO
            	            {
            	            sign=(Token)match(input,GREATER_THAN_OR_EQUAL_TO,FOLLOW_GREATER_THAN_OR_EQUAL_TO_in_relational_expr3531); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:467:113: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:467:114: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:467:114: ( WS )*
            	    loop158:
            	    do {
            	        int alt158=2;
            	        int LA158_0 = input.LA(1);

            	        if ( (LA158_0==WS) ) {
            	            int LA158_2 = input.LA(2);

            	            if ( (synpred306_CPreprocessorLL()) ) {
            	                alt158=1;
            	            }


            	        }


            	        switch (alt158) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_relational_expr3535); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop158;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_shift_expr_in_relational_expr3544);
            	    e2=shift_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new RelationalExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop159;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:468:106: ( RPAREN )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==RPAREN) ) {
                int LA160_1 = input.LA(2);

                if ( (synpred308_CPreprocessorLL()) ) {
                    alt160=1;
                }
            }
            switch (alt160) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_relational_expr3550); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 26, relational_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "relational_expr"


    // $ANTLR start "shift_expr"
    // resources/Clang/CPreprocessorLL.g:470:1: shift_expr returns [ShiftExpr value] : ( LPAREN )? e1= additive_expr ( ( WS )* ) ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr )* ( RPAREN )? ;
    public final ShiftExpr shift_expr() throws RecognitionException {
        ShiftExpr value = null;
        int shift_expr_StartIndex = input.index();
        Token sign=null;
        AdditiveExpr e1 = null;

        AdditiveExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:471:2: ( ( LPAREN )? e1= additive_expr ( ( WS )* ) ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:471:4: ( LPAREN )? e1= additive_expr ( ( WS )* ) ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:471:4: ( LPAREN )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==LPAREN) ) {
                int LA161_1 = input.LA(2);

                if ( (synpred309_CPreprocessorLL()) ) {
                    alt161=1;
                }
            }
            switch (alt161) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_shift_expr3565); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_additive_expr_in_shift_expr3570);
            e1=additive_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ShiftExpr(null, e1, -1); 
              			setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:473:4: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:473:5: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:473:5: ( WS )*
            loop162:
            do {
                int alt162=2;
                int LA162_0 = input.LA(1);

                if ( (LA162_0==WS) ) {
                    int LA162_2 = input.LA(2);

                    if ( (synpred310_CPreprocessorLL()) ) {
                        alt162=1;
                    }


                }


                switch (alt162) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_shift_expr3579); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop162;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:473:10: ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr )*
            loop165:
            do {
                int alt165=2;
                int LA165_0 = input.LA(1);

                if ( (LA165_0==LEFT_SHIFT) ) {
                    int LA165_2 = input.LA(2);

                    if ( (synpred313_CPreprocessorLL()) ) {
                        alt165=1;
                    }


                }
                else if ( (LA165_0==RIGHT_SHIFT) ) {
                    int LA165_3 = input.LA(2);

                    if ( (synpred313_CPreprocessorLL()) ) {
                        alt165=1;
                    }


                }


                switch (alt165) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:473:11: (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:473:11: (sign= LEFT_SHIFT | sign= RIGHT_SHIFT )
            	    int alt163=2;
            	    int LA163_0 = input.LA(1);

            	    if ( (LA163_0==LEFT_SHIFT) ) {
            	        alt163=1;
            	    }
            	    else if ( (LA163_0==RIGHT_SHIFT) ) {
            	        alt163=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 163, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt163) {
            	        case 1 :
            	            // resources/Clang/CPreprocessorLL.g:473:12: sign= LEFT_SHIFT
            	            {
            	            sign=(Token)match(input,LEFT_SHIFT,FOLLOW_LEFT_SHIFT_in_shift_expr3587); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/CPreprocessorLL.g:473:30: sign= RIGHT_SHIFT
            	            {
            	            sign=(Token)match(input,RIGHT_SHIFT,FOLLOW_RIGHT_SHIFT_in_shift_expr3593); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:473:48: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:473:49: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:473:49: ( WS )*
            	    loop164:
            	    do {
            	        int alt164=2;
            	        int LA164_0 = input.LA(1);

            	        if ( (LA164_0==WS) ) {
            	            int LA164_2 = input.LA(2);

            	            if ( (synpred312_CPreprocessorLL()) ) {
            	                alt164=1;
            	            }


            	        }


            	        switch (alt164) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_shift_expr3597); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop164;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_additive_expr_in_shift_expr3607);
            	    e2=additive_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new ShiftExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop165;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:474:105: ( RPAREN )?
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==RPAREN) ) {
                int LA166_1 = input.LA(2);

                if ( (synpred314_CPreprocessorLL()) ) {
                    alt166=1;
                }
            }
            switch (alt166) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_shift_expr3613); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 27, shift_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "shift_expr"


    // $ANTLR start "additive_expr"
    // resources/Clang/CPreprocessorLL.g:476:1: additive_expr returns [AdditiveExpr value] : ( LPAREN )? e1= multiplicative_expr ( ( WS )* ) ( (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr )* ( RPAREN )? ;
    public final AdditiveExpr additive_expr() throws RecognitionException {
        AdditiveExpr value = null;
        int additive_expr_StartIndex = input.index();
        Token sign=null;
        MultiplicativeExpr e1 = null;

        MultiplicativeExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:477:2: ( ( LPAREN )? e1= multiplicative_expr ( ( WS )* ) ( (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:477:4: ( LPAREN )? e1= multiplicative_expr ( ( WS )* ) ( (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:477:4: ( LPAREN )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==LPAREN) ) {
                int LA167_1 = input.LA(2);

                if ( (synpred315_CPreprocessorLL()) ) {
                    alt167=1;
                }
            }
            switch (alt167) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_additive_expr3628); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_multiplicative_expr_in_additive_expr3633);
            e1=multiplicative_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new AdditiveExpr(null, e1, -1); 
              				setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:479:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:479:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:479:4: ( WS )*
            loop168:
            do {
                int alt168=2;
                int LA168_0 = input.LA(1);

                if ( (LA168_0==WS) ) {
                    int LA168_2 = input.LA(2);

                    if ( (synpred316_CPreprocessorLL()) ) {
                        alt168=1;
                    }


                }


                switch (alt168) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_additive_expr3641); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop168;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:479:9: ( (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr )*
            loop171:
            do {
                int alt171=2;
                int LA171_0 = input.LA(1);

                if ( (LA171_0==PLUS) ) {
                    int LA171_2 = input.LA(2);

                    if ( (synpred319_CPreprocessorLL()) ) {
                        alt171=1;
                    }


                }
                else if ( (LA171_0==MINUS) ) {
                    int LA171_3 = input.LA(2);

                    if ( (synpred319_CPreprocessorLL()) ) {
                        alt171=1;
                    }


                }


                switch (alt171) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:479:10: (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:479:10: (sign= PLUS | sign= MINUS )
            	    int alt169=2;
            	    int LA169_0 = input.LA(1);

            	    if ( (LA169_0==PLUS) ) {
            	        alt169=1;
            	    }
            	    else if ( (LA169_0==MINUS) ) {
            	        alt169=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 169, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt169) {
            	        case 1 :
            	            // resources/Clang/CPreprocessorLL.g:479:12: sign= PLUS
            	            {
            	            sign=(Token)match(input,PLUS,FOLLOW_PLUS_in_additive_expr3650); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/CPreprocessorLL.g:479:25: sign= MINUS
            	            {
            	            sign=(Token)match(input,MINUS,FOLLOW_MINUS_in_additive_expr3657); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:479:37: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:479:38: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:479:38: ( WS )*
            	    loop170:
            	    do {
            	        int alt170=2;
            	        int LA170_0 = input.LA(1);

            	        if ( (LA170_0==WS) ) {
            	            int LA170_2 = input.LA(2);

            	            if ( (synpred318_CPreprocessorLL()) ) {
            	                alt170=1;
            	            }


            	        }


            	        switch (alt170) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_additive_expr3661); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop170;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_multiplicative_expr_in_additive_expr3669);
            	    e2=multiplicative_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new AdditiveExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop171;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:480:112: ( RPAREN )?
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( (LA172_0==RPAREN) ) {
                int LA172_1 = input.LA(2);

                if ( (synpred320_CPreprocessorLL()) ) {
                    alt172=1;
                }
            }
            switch (alt172) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_additive_expr3675); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 28, additive_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "additive_expr"


    // $ANTLR start "multiplicative_expr"
    // resources/Clang/CPreprocessorLL.g:482:1: multiplicative_expr returns [MultiplicativeExpr value] : ( LPAREN )? e1= unary_expr ( ( WS )* ) ( (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr )* ( RPAREN )? ;
    public final MultiplicativeExpr multiplicative_expr() throws RecognitionException {
        MultiplicativeExpr value = null;
        int multiplicative_expr_StartIndex = input.index();
        Token sign=null;
        UnaryExpr e1 = null;

        UnaryExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:483:2: ( ( LPAREN )? e1= unary_expr ( ( WS )* ) ( (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:483:4: ( LPAREN )? e1= unary_expr ( ( WS )* ) ( (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr )* ( RPAREN )?
            {
            // resources/Clang/CPreprocessorLL.g:483:4: ( LPAREN )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==LPAREN) ) {
                int LA173_1 = input.LA(2);

                if ( (synpred321_CPreprocessorLL()) ) {
                    alt173=1;
                }
            }
            switch (alt173) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_multiplicative_expr3690); if (state.failed) return value;

                    }
                    break;

            }

            pushFollow(FOLLOW_unary_expr_in_multiplicative_expr3695);
            e1=unary_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new MultiplicativeExpr( e1, null, -1);
              			 setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
            }
            // resources/Clang/CPreprocessorLL.g:485:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:485:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:485:4: ( WS )*
            loop174:
            do {
                int alt174=2;
                int LA174_0 = input.LA(1);

                if ( (LA174_0==WS) ) {
                    int LA174_2 = input.LA(2);

                    if ( (synpred322_CPreprocessorLL()) ) {
                        alt174=1;
                    }


                }


                switch (alt174) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_multiplicative_expr3703); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop174;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:485:9: ( (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr )*
            loop177:
            do {
                int alt177=2;
                switch ( input.LA(1) ) {
                case STAR:
                    {
                    int LA177_2 = input.LA(2);

                    if ( (synpred326_CPreprocessorLL()) ) {
                        alt177=1;
                    }


                    }
                    break;
                case DIVIDE:
                    {
                    int LA177_3 = input.LA(2);

                    if ( (synpred326_CPreprocessorLL()) ) {
                        alt177=1;
                    }


                    }
                    break;
                case MODULO:
                    {
                    int LA177_4 = input.LA(2);

                    if ( (synpred326_CPreprocessorLL()) ) {
                        alt177=1;
                    }


                    }
                    break;

                }

                switch (alt177) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:485:10: (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr
            	    {
            	    // resources/Clang/CPreprocessorLL.g:485:10: (sign= STAR | sign= DIVIDE | sign= MODULO )
            	    int alt175=3;
            	    switch ( input.LA(1) ) {
            	    case STAR:
            	        {
            	        alt175=1;
            	        }
            	        break;
            	    case DIVIDE:
            	        {
            	        alt175=2;
            	        }
            	        break;
            	    case MODULO:
            	        {
            	        alt175=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 175, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt175) {
            	        case 1 :
            	            // resources/Clang/CPreprocessorLL.g:485:12: sign= STAR
            	            {
            	            sign=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicative_expr3712); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/CPreprocessorLL.g:485:24: sign= DIVIDE
            	            {
            	            sign=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_multiplicative_expr3718); if (state.failed) return value;

            	            }
            	            break;
            	        case 3 :
            	            // resources/Clang/CPreprocessorLL.g:485:38: sign= MODULO
            	            {
            	            sign=(Token)match(input,MODULO,FOLLOW_MODULO_in_multiplicative_expr3724); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    // resources/Clang/CPreprocessorLL.g:485:52: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:485:53: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:485:53: ( WS )*
            	    loop176:
            	    do {
            	        int alt176=2;
            	        int LA176_0 = input.LA(1);

            	        if ( (LA176_0==WS) ) {
            	            int LA176_2 = input.LA(2);

            	            if ( (synpred325_CPreprocessorLL()) ) {
            	                alt176=1;
            	            }


            	        }


            	        switch (alt176) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_multiplicative_expr3729); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop176;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_unary_expr_in_multiplicative_expr3737);
            	    e2=unary_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new MultiplicativeExpr(e2, value, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop177;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:486:109: ( RPAREN )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==RPAREN) ) {
                int LA178_1 = input.LA(2);

                if ( (synpred327_CPreprocessorLL()) ) {
                    alt178=1;
                }
            }
            switch (alt178) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_multiplicative_expr3743); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 29, multiplicative_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "multiplicative_expr"


    // $ANTLR start "unary_expr"
    // resources/Clang/CPreprocessorLL.g:488:1: unary_expr returns [UnaryExpr value] : ( ( LPAREN )? e= primary_expr ( ( WS )* ) | (a1= PLUS | a2= MINUS | a3= TILDE | a4= NOT ) ( ( WS )* ) e3= multiplicative_expr ( RPAREN )? );
    public final UnaryExpr unary_expr() throws RecognitionException {
        UnaryExpr value = null;
        int unary_expr_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        PrimaryExpr e = null;

        MultiplicativeExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:489:2: ( ( LPAREN )? e= primary_expr ( ( WS )* ) | (a1= PLUS | a2= MINUS | a3= TILDE | a4= NOT ) ( ( WS )* ) e3= multiplicative_expr ( RPAREN )? )
            int alt184=2;
            switch ( input.LA(1) ) {
            case DEFINED:
            case LPAREN:
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case HEX_LITERAL:
            case CHAR_LITERAL:
            case WS:
            case ID:
            case STRING_LITERAL:
                {
                alt184=1;
                }
                break;
            case NOT:
                {
                int LA184_2 = input.LA(2);

                if ( (synpred330_CPreprocessorLL()) ) {
                    alt184=1;
                }
                else if ( (true) ) {
                    alt184=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 184, 2, input);

                    throw nvae;
                }
                }
                break;
            case PLUS:
            case MINUS:
            case TILDE:
                {
                alt184=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 184, 0, input);

                throw nvae;
            }

            switch (alt184) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:489:4: ( LPAREN )? e= primary_expr ( ( WS )* )
                    {
                    // resources/Clang/CPreprocessorLL.g:489:4: ( LPAREN )?
                    int alt179=2;
                    int LA179_0 = input.LA(1);

                    if ( (LA179_0==LPAREN) ) {
                        int LA179_1 = input.LA(2);

                        if ( (synpred328_CPreprocessorLL()) ) {
                            alt179=1;
                        }
                    }
                    switch (alt179) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_unary_expr3758); if (state.failed) return value;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_primary_expr_in_unary_expr3763);
                    e=primary_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new UnaryExpr(); value.setPrimaryExpr(e); 
                      					setLineNumAndPos(value, e.getLineNum(), e.getPos());
                    }
                    // resources/Clang/CPreprocessorLL.g:490:75: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:490:76: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:490:76: ( WS )*
                    loop180:
                    do {
                        int alt180=2;
                        int LA180_0 = input.LA(1);

                        if ( (LA180_0==WS) ) {
                            int LA180_1 = input.LA(2);

                            if ( (synpred329_CPreprocessorLL()) ) {
                                alt180=1;
                            }


                        }


                        switch (alt180) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_unary_expr3768); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop180;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:491:4: (a1= PLUS | a2= MINUS | a3= TILDE | a4= NOT ) ( ( WS )* ) e3= multiplicative_expr ( RPAREN )?
                    {
                    // resources/Clang/CPreprocessorLL.g:491:4: (a1= PLUS | a2= MINUS | a3= TILDE | a4= NOT )
                    int alt181=4;
                    switch ( input.LA(1) ) {
                    case PLUS:
                        {
                        alt181=1;
                        }
                        break;
                    case MINUS:
                        {
                        alt181=2;
                        }
                        break;
                    case TILDE:
                        {
                        alt181=3;
                        }
                        break;
                    case NOT:
                        {
                        alt181=4;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 181, 0, input);

                        throw nvae;
                    }

                    switch (alt181) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:491:7: a1= PLUS
                            {
                            a1=(Token)match(input,PLUS,FOLLOW_PLUS_in_unary_expr3781); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.PLUS); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 2 :
                            // resources/Clang/CPreprocessorLL.g:492:6: a2= MINUS
                            {
                            a2=(Token)match(input,MINUS,FOLLOW_MINUS_in_unary_expr3793); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.MINUS); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 3 :
                            // resources/Clang/CPreprocessorLL.g:493:6: a3= TILDE
                            {
                            a3=(Token)match(input,TILDE,FOLLOW_TILDE_in_unary_expr3805); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.TILDE); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 4 :
                            // resources/Clang/CPreprocessorLL.g:494:6: a4= NOT
                            {
                            a4=(Token)match(input,NOT,FOLLOW_NOT_in_unary_expr3817); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.NOT); setLineNumAndPos(value, (a4!=null?a4.getLine():0), (a4!=null?a4.getCharPositionInLine():0));
                            }

                            }
                            break;

                    }

                    // resources/Clang/CPreprocessorLL.g:495:5: ( ( WS )* )
                    // resources/Clang/CPreprocessorLL.g:495:6: ( WS )*
                    {
                    // resources/Clang/CPreprocessorLL.g:495:6: ( WS )*
                    loop182:
                    do {
                        int alt182=2;
                        int LA182_0 = input.LA(1);

                        if ( (LA182_0==WS) ) {
                            int LA182_2 = input.LA(2);

                            if ( (synpred334_CPreprocessorLL()) ) {
                                alt182=1;
                            }


                        }


                        switch (alt182) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_unary_expr3827); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop182;
                        }
                    } while (true);


                    }

                    pushFollow(FOLLOW_multiplicative_expr_in_unary_expr3833);
                    e3=multiplicative_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setMultiplicativeExpr(e3);
                    }
                    // resources/Clang/CPreprocessorLL.g:495:77: ( RPAREN )?
                    int alt183=2;
                    int LA183_0 = input.LA(1);

                    if ( (LA183_0==RPAREN) ) {
                        int LA183_1 = input.LA(2);

                        if ( (synpred335_CPreprocessorLL()) ) {
                            alt183=1;
                        }
                    }
                    switch (alt183) {
                        case 1 :
                            // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                            {
                            match(input,RPAREN,FOLLOW_RPAREN_in_unary_expr3837); if (state.failed) return value;

                            }
                            break;

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 30, unary_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "unary_expr"


    // $ANTLR start "primary_expr"
    // resources/Clang/CPreprocessorLL.g:497:1: primary_expr returns [PrimaryExpr value] : (fc= func_call_expr | ( LPAREN )* e= ID ( RPAREN )* | e1= constant | e2= STRING_LITERAL | d1= defined_expr );
    public final PrimaryExpr primary_expr() throws RecognitionException {
        PrimaryExpr value = null;
        int primary_expr_StartIndex = input.index();
        Token e=null;
        Token e2=null;
        FuncCallExpr fc = null;

        Constant e1 = null;

        Defined d1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:498:2: (fc= func_call_expr | ( LPAREN )* e= ID ( RPAREN )* | e1= constant | e2= STRING_LITERAL | d1= defined_expr )
            int alt187=5;
            alt187 = dfa187.predict(input);
            switch (alt187) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:498:4: fc= func_call_expr
                    {
                    pushFollow(FOLLOW_func_call_expr_in_primary_expr3855);
                    fc=func_call_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(fc);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:499:4: ( LPAREN )* e= ID ( RPAREN )*
                    {
                    // resources/Clang/CPreprocessorLL.g:499:4: ( LPAREN )*
                    loop185:
                    do {
                        int alt185=2;
                        int LA185_0 = input.LA(1);

                        if ( (LA185_0==LPAREN) ) {
                            alt185=1;
                        }


                        switch (alt185) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    	    {
                    	    match(input,LPAREN,FOLLOW_LPAREN_in_primary_expr3863); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop185;
                        }
                    } while (true);

                    e=(Token)match(input,ID,FOLLOW_ID_in_primary_expr3868); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.IDENTIFIER_TYPE); value.setIdentifier((e!=null?e.getText():null)); setLineNumAndPos(value, (e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0)); 
                    }
                    // resources/Clang/CPreprocessorLL.g:499:149: ( RPAREN )*
                    loop186:
                    do {
                        int alt186=2;
                        int LA186_0 = input.LA(1);

                        if ( (LA186_0==RPAREN) ) {
                            int LA186_2 = input.LA(2);

                            if ( (synpred338_CPreprocessorLL()) ) {
                                alt186=1;
                            }


                        }


                        switch (alt186) {
                    	case 1 :
                    	    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    	    {
                    	    match(input,RPAREN,FOLLOW_RPAREN_in_primary_expr3872); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop186;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:500:4: e1= constant
                    {
                    pushFollow(FOLLOW_constant_in_primary_expr3880);
                    e1=constant();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.CONSTANT_TYPE); value.setConstant(e1); setLineNumAndPos(value, e1.getLineNum(), e1.getPos());
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/CPreprocessorLL.g:501:4: e2= STRING_LITERAL
                    {
                    e2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_primary_expr3889); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.STRING_TYPE); value.setString((e2!=null?e2.getText():null)); setLineNumAndPos(value, (e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/CPreprocessorLL.g:502:6: d1= defined_expr
                    {
                    pushFollow(FOLLOW_defined_expr_in_primary_expr3902);
                    d1=defined_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(d1);
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 31, primary_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "primary_expr"


    // $ANTLR start "constant"
    // resources/Clang/CPreprocessorLL.g:504:1: constant returns [Constant value] : (a1= DECIMAL_LITERAL | a2= OCTAL_LITERAL | a3= HEX_LITERAL | a5= CHAR_LITERAL );
    public final Constant constant() throws RecognitionException {
        Constant value = null;
        int constant_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a5=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:505:2: (a1= DECIMAL_LITERAL | a2= OCTAL_LITERAL | a3= HEX_LITERAL | a5= CHAR_LITERAL )
            int alt188=4;
            switch ( input.LA(1) ) {
            case DECIMAL_LITERAL:
                {
                alt188=1;
                }
                break;
            case OCTAL_LITERAL:
                {
                alt188=2;
                }
                break;
            case HEX_LITERAL:
                {
                alt188=3;
                }
                break;
            case CHAR_LITERAL:
                {
                alt188=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 188, 0, input);

                throw nvae;
            }

            switch (alt188) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:505:4: a1= DECIMAL_LITERAL
                    {
                    a1=(Token)match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_constant3920); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setIntConstant((a1!=null?a1.getText():null)); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/CPreprocessorLL.g:506:4: a2= OCTAL_LITERAL
                    {
                    a2=(Token)match(input,OCTAL_LITERAL,FOLLOW_OCTAL_LITERAL_in_constant3930); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setIntConstant((a2!=null?a2.getText():null)); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/CPreprocessorLL.g:507:4: a3= HEX_LITERAL
                    {
                    a3=(Token)match(input,HEX_LITERAL,FOLLOW_HEX_LITERAL_in_constant3940); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setIntConstant((a3!=null?a3.getText():null)); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/CPreprocessorLL.g:508:4: a5= CHAR_LITERAL
                    {
                    a5=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_constant3950); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setCharConstant((a5!=null?a5.getText():null)); setLineNumAndPos(value, (a5!=null?a5.getLine():0), (a5!=null?a5.getCharPositionInLine():0));
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 32, constant_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "constant"


    // $ANTLR start "defined_expr"
    // resources/Clang/CPreprocessorLL.g:510:1: defined_expr returns [Defined value] : ( LPAREN )* (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( LPAREN )? ( ( WS )* ) id1= ID ( ( WS )* ) ( RPAREN )* ;
    public final Defined defined_expr() throws RecognitionException {
        Defined value = null;
        int defined_expr_StartIndex = input.index();
        Token not=null;
        Token def=null;
        Token id1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:510:38: ( ( LPAREN )* (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( LPAREN )? ( ( WS )* ) id1= ID ( ( WS )* ) ( RPAREN )* )
            // resources/Clang/CPreprocessorLL.g:511:2: ( LPAREN )* (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( LPAREN )? ( ( WS )* ) id1= ID ( ( WS )* ) ( RPAREN )*
            {
            // resources/Clang/CPreprocessorLL.g:511:2: ( LPAREN )*
            loop189:
            do {
                int alt189=2;
                int LA189_0 = input.LA(1);

                if ( (LA189_0==LPAREN) ) {
                    alt189=1;
                }


                switch (alt189) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
            	    {
            	    match(input,LPAREN,FOLLOW_LPAREN_in_defined_expr3966); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop189;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:511:9: (not= NOT )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==NOT) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:511:10: not= NOT
                    {
                    not=(Token)match(input,NOT,FOLLOW_NOT_in_defined_expr3971); if (state.failed) return value;

                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:511:20: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:511:21: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:511:21: ( WS )*
            loop191:
            do {
                int alt191=2;
                int LA191_0 = input.LA(1);

                if ( (LA191_0==WS) ) {
                    alt191=1;
                }


                switch (alt191) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_defined_expr3976); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop191;
                }
            } while (true);


            }

            def=(Token)match(input,DEFINED,FOLLOW_DEFINED_in_defined_expr3982); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:511:38: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:511:39: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:511:39: ( WS )*
            loop192:
            do {
                int alt192=2;
                int LA192_0 = input.LA(1);

                if ( (LA192_0==WS) ) {
                    int LA192_2 = input.LA(2);

                    if ( (synpred348_CPreprocessorLL()) ) {
                        alt192=1;
                    }


                }


                switch (alt192) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_defined_expr3985); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop192;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:511:44: ( LPAREN )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==LPAREN) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_defined_expr3989); if (state.failed) return value;

                    }
                    break;

            }

            // resources/Clang/CPreprocessorLL.g:511:52: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:511:53: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:511:53: ( WS )*
            loop194:
            do {
                int alt194=2;
                int LA194_0 = input.LA(1);

                if ( (LA194_0==WS) ) {
                    alt194=1;
                }


                switch (alt194) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_defined_expr3993); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop194;
                }
            } while (true);


            }

            id1=(Token)match(input,ID,FOLLOW_ID_in_defined_expr3999); if (state.failed) return value;
            // resources/Clang/CPreprocessorLL.g:511:65: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:511:66: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:511:66: ( WS )*
            loop195:
            do {
                int alt195=2;
                int LA195_0 = input.LA(1);

                if ( (LA195_0==WS) ) {
                    int LA195_2 = input.LA(2);

                    if ( (synpred351_CPreprocessorLL()) ) {
                        alt195=1;
                    }


                }


                switch (alt195) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_defined_expr4002); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop195;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:511:71: ( RPAREN )*
            loop196:
            do {
                int alt196=2;
                int LA196_0 = input.LA(1);

                if ( (LA196_0==RPAREN) ) {
                    int LA196_2 = input.LA(2);

                    if ( (synpred352_CPreprocessorLL()) ) {
                        alt196=1;
                    }


                }


                switch (alt196) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
            	    {
            	    match(input,RPAREN,FOLLOW_RPAREN_in_defined_expr4006); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop196;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              value = constructDefined((not!=null?not.getText():null), (id1!=null?id1.getText():null));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 33, defined_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "defined_expr"


    // $ANTLR start "func_call_expr"
    // resources/Clang/CPreprocessorLL.g:514:1: func_call_expr returns [FuncCallExpr value] : f= ID ( ( WS )* ) LPAREN e3= const_expr ( ( WS )* ) ( COMMA ( ( WS )* ) e4= const_expr ( ( WS )* ) )* ( RPAREN )? ;
    public final FuncCallExpr func_call_expr() throws RecognitionException {
        FuncCallExpr value = null;
        int func_call_expr_StartIndex = input.index();
        Token f=null;
        ConstExpr e3 = null;

        ConstExpr e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return value; }
            // resources/Clang/CPreprocessorLL.g:515:2: (f= ID ( ( WS )* ) LPAREN e3= const_expr ( ( WS )* ) ( COMMA ( ( WS )* ) e4= const_expr ( ( WS )* ) )* ( RPAREN )? )
            // resources/Clang/CPreprocessorLL.g:515:4: f= ID ( ( WS )* ) LPAREN e3= const_expr ( ( WS )* ) ( COMMA ( ( WS )* ) e4= const_expr ( ( WS )* ) )* ( RPAREN )?
            {
            f=(Token)match(input,ID,FOLLOW_ID_in_func_call_expr4027); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new FuncCallExpr((f!=null?f.getText():null));
            }
            // resources/Clang/CPreprocessorLL.g:515:47: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:515:48: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:515:48: ( WS )*
            loop197:
            do {
                int alt197=2;
                int LA197_0 = input.LA(1);

                if ( (LA197_0==WS) ) {
                    alt197=1;
                }


                switch (alt197) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_func_call_expr4032); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop197;
                }
            } while (true);


            }

            match(input,LPAREN,FOLLOW_LPAREN_in_func_call_expr4036); if (state.failed) return value;
            pushFollow(FOLLOW_const_expr_in_func_call_expr4040);
            e3=const_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value.addParameters(e3);
            }
            // resources/Clang/CPreprocessorLL.g:516:3: ( ( WS )* )
            // resources/Clang/CPreprocessorLL.g:516:4: ( WS )*
            {
            // resources/Clang/CPreprocessorLL.g:516:4: ( WS )*
            loop198:
            do {
                int alt198=2;
                int LA198_0 = input.LA(1);

                if ( (LA198_0==WS) ) {
                    int LA198_2 = input.LA(2);

                    if ( (synpred354_CPreprocessorLL()) ) {
                        alt198=1;
                    }


                }


                switch (alt198) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_func_call_expr4048); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop198;
                }
            } while (true);


            }

            // resources/Clang/CPreprocessorLL.g:516:9: ( COMMA ( ( WS )* ) e4= const_expr ( ( WS )* ) )*
            loop201:
            do {
                int alt201=2;
                int LA201_0 = input.LA(1);

                if ( (LA201_0==COMMA) ) {
                    int LA201_2 = input.LA(2);

                    if ( (synpred357_CPreprocessorLL()) ) {
                        alt201=1;
                    }


                }


                switch (alt201) {
            	case 1 :
            	    // resources/Clang/CPreprocessorLL.g:516:10: COMMA ( ( WS )* ) e4= const_expr ( ( WS )* )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_func_call_expr4053); if (state.failed) return value;
            	    // resources/Clang/CPreprocessorLL.g:516:17: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:516:18: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:516:18: ( WS )*
            	    loop199:
            	    do {
            	        int alt199=2;
            	        int LA199_0 = input.LA(1);

            	        if ( (LA199_0==WS) ) {
            	            int LA199_2 = input.LA(2);

            	            if ( (synpred355_CPreprocessorLL()) ) {
            	                alt199=1;
            	            }


            	        }


            	        switch (alt199) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_func_call_expr4057); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop199;
            	        }
            	    } while (true);


            	    }

            	    pushFollow(FOLLOW_const_expr_in_func_call_expr4063);
            	    e4=const_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.addParameters(e4);
            	    }
            	    // resources/Clang/CPreprocessorLL.g:516:72: ( ( WS )* )
            	    // resources/Clang/CPreprocessorLL.g:516:73: ( WS )*
            	    {
            	    // resources/Clang/CPreprocessorLL.g:516:73: ( WS )*
            	    loop200:
            	    do {
            	        int alt200=2;
            	        int LA200_0 = input.LA(1);

            	        if ( (LA200_0==WS) ) {
            	            int LA200_2 = input.LA(2);

            	            if ( (synpred356_CPreprocessorLL()) ) {
            	                alt200=1;
            	            }


            	        }


            	        switch (alt200) {
            	    	case 1 :
            	    	    // resources/Clang/CPreprocessorLL.g:0:0: WS
            	    	    {
            	    	    match(input,WS,FOLLOW_WS_in_func_call_expr4068); if (state.failed) return value;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop200;
            	        }
            	    } while (true);


            	    }


            	    }
            	    break;

            	default :
            	    break loop201;
                }
            } while (true);

            // resources/Clang/CPreprocessorLL.g:516:80: ( RPAREN )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==RPAREN) ) {
                int LA202_1 = input.LA(2);

                if ( (synpred358_CPreprocessorLL()) ) {
                    alt202=1;
                }
            }
            switch (alt202) {
                case 1 :
                    // resources/Clang/CPreprocessorLL.g:0:0: RPAREN
                    {
                    match(input,RPAREN,FOLLOW_RPAREN_in_func_call_expr4074); if (state.failed) return value;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 34, func_call_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "func_call_expr"

    // $ANTLR start synpred14_CPreprocessorLL
    public final void synpred14_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:331:18: ( WS )
        // resources/Clang/CPreprocessorLL.g:331:18: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred14_CPreprocessorLL1285); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_CPreprocessorLL

    // $ANTLR start synpred20_CPreprocessorLL
    public final void synpred20_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:333:49: ( WS )
        // resources/Clang/CPreprocessorLL.g:333:49: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred20_CPreprocessorLL1326); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred20_CPreprocessorLL

    // $ANTLR start synpred21_CPreprocessorLL
    public final void synpred21_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:334:81: ( WS )
        // resources/Clang/CPreprocessorLL.g:334:81: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred21_CPreprocessorLL1335); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred21_CPreprocessorLL

    // $ANTLR start synpred26_CPreprocessorLL
    public final void synpred26_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:338:6: ( WS )
        // resources/Clang/CPreprocessorLL.g:338:6: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred26_CPreprocessorLL1393); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred26_CPreprocessorLL

    // $ANTLR start synpred27_CPreprocessorLL
    public final void synpred27_CPreprocessorLL_fragment() throws RecognitionException {   
        TokenSequence e6 = null;


        // resources/Clang/CPreprocessorLL.g:338:5: ( ( ( WS )+ ) e6= token_sequence )
        // resources/Clang/CPreprocessorLL.g:338:5: ( ( WS )+ ) e6= token_sequence
        {
        // resources/Clang/CPreprocessorLL.g:338:5: ( ( WS )+ )
        // resources/Clang/CPreprocessorLL.g:338:6: ( WS )+
        {
        // resources/Clang/CPreprocessorLL.g:338:6: ( WS )+
        int cnt210=0;
        loop210:
        do {
            int alt210=2;
            int LA210_0 = input.LA(1);

            if ( (LA210_0==WS) ) {
                int LA210_1 = input.LA(2);

                if ( (synpred26_CPreprocessorLL()) ) {
                    alt210=1;
                }


            }


            switch (alt210) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred27_CPreprocessorLL1393); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt210 >= 1 ) break loop210;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(210, input);
                    throw eee;
            }
            cnt210++;
        } while (true);


        }

        pushFollow(FOLLOW_token_sequence_in_synpred27_CPreprocessorLL1399);
        e6=token_sequence();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_CPreprocessorLL

    // $ANTLR start synpred28_CPreprocessorLL
    public final void synpred28_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:339:56: ( WS )
        // resources/Clang/CPreprocessorLL.g:339:56: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred28_CPreprocessorLL1413); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred28_CPreprocessorLL

    // $ANTLR start synpred34_CPreprocessorLL
    public final void synpred34_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:340:47: ( WS )
        // resources/Clang/CPreprocessorLL.g:340:47: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred34_CPreprocessorLL1455); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_CPreprocessorLL

    // $ANTLR start synpred35_CPreprocessorLL
    public final void synpred35_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:341:51: ( WS )
        // resources/Clang/CPreprocessorLL.g:341:51: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred35_CPreprocessorLL1477); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_CPreprocessorLL

    // $ANTLR start synpred40_CPreprocessorLL
    public final void synpred40_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:342:10: ( WS )
        // resources/Clang/CPreprocessorLL.g:342:10: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred40_CPreprocessorLL1517); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred40_CPreprocessorLL

    // $ANTLR start synpred41_CPreprocessorLL
    public final void synpred41_CPreprocessorLL_fragment() throws RecognitionException {   
        TokenSequence e4 = null;


        // resources/Clang/CPreprocessorLL.g:342:9: ( ( ( WS )+ ) e4= token_sequence )
        // resources/Clang/CPreprocessorLL.g:342:9: ( ( WS )+ ) e4= token_sequence
        {
        // resources/Clang/CPreprocessorLL.g:342:9: ( ( WS )+ )
        // resources/Clang/CPreprocessorLL.g:342:10: ( WS )+
        {
        // resources/Clang/CPreprocessorLL.g:342:10: ( WS )+
        int cnt222=0;
        loop222:
        do {
            int alt222=2;
            int LA222_0 = input.LA(1);

            if ( (LA222_0==WS) ) {
                int LA222_1 = input.LA(2);

                if ( (synpred40_CPreprocessorLL()) ) {
                    alt222=1;
                }


            }


            switch (alt222) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred41_CPreprocessorLL1517); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt222 >= 1 ) break loop222;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(222, input);
                    throw eee;
            }
            cnt222++;
        } while (true);


        }

        pushFollow(FOLLOW_token_sequence_in_synpred41_CPreprocessorLL1524);
        e4=token_sequence();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_CPreprocessorLL

    // $ANTLR start synpred42_CPreprocessorLL
    public final void synpred42_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:342:88: ( WS )
        // resources/Clang/CPreprocessorLL.g:342:88: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred42_CPreprocessorLL1533); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred42_CPreprocessorLL

    // $ANTLR start synpred48_CPreprocessorLL
    public final void synpred48_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:343:41: ( WS )
        // resources/Clang/CPreprocessorLL.g:343:41: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred48_CPreprocessorLL1576); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_CPreprocessorLL

    // $ANTLR start synpred49_CPreprocessorLL
    public final void synpred49_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:343:122: ( WS )
        // resources/Clang/CPreprocessorLL.g:343:122: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred49_CPreprocessorLL1586); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_CPreprocessorLL

    // $ANTLR start synpred54_CPreprocessorLL
    public final void synpred54_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:346:48: ( WS )
        // resources/Clang/CPreprocessorLL.g:346:48: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred54_CPreprocessorLL1635); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred54_CPreprocessorLL

    // $ANTLR start synpred56_CPreprocessorLL
    public final void synpred56_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:346:149: ( WS )
        // resources/Clang/CPreprocessorLL.g:346:149: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred56_CPreprocessorLL1652); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred56_CPreprocessorLL

    // $ANTLR start synpred60_CPreprocessorLL
    public final void synpred60_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:349:23: ( WS )
        // resources/Clang/CPreprocessorLL.g:349:23: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred60_CPreprocessorLL1690); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred60_CPreprocessorLL

    // $ANTLR start synpred61_CPreprocessorLL
    public final void synpred61_CPreprocessorLL_fragment() throws RecognitionException {   
        TokenSequence e1 = null;


        // resources/Clang/CPreprocessorLL.g:349:53: (e1= token_sequence )
        // resources/Clang/CPreprocessorLL.g:349:53: e1= token_sequence
        {
        pushFollow(FOLLOW_token_sequence_in_synpred61_CPreprocessorLL1698);
        e1=token_sequence();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred61_CPreprocessorLL

    // $ANTLR start synpred62_CPreprocessorLL
    public final void synpred62_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:349:113: ( WS )
        // resources/Clang/CPreprocessorLL.g:349:113: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred62_CPreprocessorLL1706); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred62_CPreprocessorLL

    // $ANTLR start synpred63_CPreprocessorLL
    public final void synpred63_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:349:121: ( WS )
        // resources/Clang/CPreprocessorLL.g:349:121: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred63_CPreprocessorLL1713); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred63_CPreprocessorLL

    // $ANTLR start synpred67_CPreprocessorLL
    public final void synpred67_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:352:21: ( WS )
        // resources/Clang/CPreprocessorLL.g:352:21: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred67_CPreprocessorLL1749); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred67_CPreprocessorLL

    // $ANTLR start synpred68_CPreprocessorLL
    public final void synpred68_CPreprocessorLL_fragment() throws RecognitionException {   
        TokenSequence e1 = null;


        // resources/Clang/CPreprocessorLL.g:352:51: (e1= token_sequence )
        // resources/Clang/CPreprocessorLL.g:352:51: e1= token_sequence
        {
        pushFollow(FOLLOW_token_sequence_in_synpred68_CPreprocessorLL1758);
        e1=token_sequence();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred68_CPreprocessorLL

    // $ANTLR start synpred69_CPreprocessorLL
    public final void synpred69_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:352:111: ( WS )
        // resources/Clang/CPreprocessorLL.g:352:111: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred69_CPreprocessorLL1766); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred69_CPreprocessorLL

    // $ANTLR start synpred70_CPreprocessorLL
    public final void synpred70_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:352:119: ( WS )
        // resources/Clang/CPreprocessorLL.g:352:119: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred70_CPreprocessorLL1773); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred70_CPreprocessorLL

    // $ANTLR start synpred74_CPreprocessorLL
    public final void synpred74_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:356:23: ( WS )
        // resources/Clang/CPreprocessorLL.g:356:23: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred74_CPreprocessorLL1811); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred74_CPreprocessorLL

    // $ANTLR start synpred75_CPreprocessorLL
    public final void synpred75_CPreprocessorLL_fragment() throws RecognitionException {   
        TokenSequence e1 = null;


        // resources/Clang/CPreprocessorLL.g:356:55: (e1= token_sequence )
        // resources/Clang/CPreprocessorLL.g:356:55: e1= token_sequence
        {
        pushFollow(FOLLOW_token_sequence_in_synpred75_CPreprocessorLL1820);
        e1=token_sequence();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred75_CPreprocessorLL

    // $ANTLR start synpred76_CPreprocessorLL
    public final void synpred76_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:356:115: ( WS )
        // resources/Clang/CPreprocessorLL.g:356:115: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred76_CPreprocessorLL1828); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred76_CPreprocessorLL

    // $ANTLR start synpred79_CPreprocessorLL
    public final void synpred79_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:359:9: ( WS )
        // resources/Clang/CPreprocessorLL.g:359:9: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred79_CPreprocessorLL1860); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred79_CPreprocessorLL

    // $ANTLR start synpred80_CPreprocessorLL
    public final void synpred80_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:359:48: ( WS )
        // resources/Clang/CPreprocessorLL.g:359:48: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred80_CPreprocessorLL1868); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred80_CPreprocessorLL

    // $ANTLR start synpred83_CPreprocessorLL
    public final void synpred83_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:362:78: ( WS )
        // resources/Clang/CPreprocessorLL.g:362:78: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred83_CPreprocessorLL1903); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred83_CPreprocessorLL

    // $ANTLR start synpred87_CPreprocessorLL
    public final void synpred87_CPreprocessorLL_fragment() throws RecognitionException {   
        PreprocessorDirective e5 = null;


        // resources/Clang/CPreprocessorLL.g:364:7: (e5= preprocessor_directive )
        // resources/Clang/CPreprocessorLL.g:364:7: e5= preprocessor_directive
        {
        pushFollow(FOLLOW_preprocessor_directive_in_synpred87_CPreprocessorLL1937);
        e5=preprocessor_directive();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred87_CPreprocessorLL

    // $ANTLR start synpred91_CPreprocessorLL
    public final void synpred91_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:369:29: ( WS )
        // resources/Clang/CPreprocessorLL.g:369:29: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred91_CPreprocessorLL1996); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred91_CPreprocessorLL

    // $ANTLR start synpred95_CPreprocessorLL
    public final void synpred95_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:372:24: ( WS )
        // resources/Clang/CPreprocessorLL.g:372:24: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred95_CPreprocessorLL2042); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred95_CPreprocessorLL

    // $ANTLR start synpred103_CPreprocessorLL
    public final void synpred103_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:377:23: ( WS )
        // resources/Clang/CPreprocessorLL.g:377:23: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred103_CPreprocessorLL2136); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred103_CPreprocessorLL

    // $ANTLR start synpred112_CPreprocessorLL
    public final void synpred112_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:381:25: ( WS )
        // resources/Clang/CPreprocessorLL.g:381:25: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred112_CPreprocessorLL2225); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred112_CPreprocessorLL

    // $ANTLR start synpred116_CPreprocessorLL
    public final void synpred116_CPreprocessorLL_fragment() throws RecognitionException {   
        PreprocessorDirective e3 = null;


        // resources/Clang/CPreprocessorLL.g:386:6: (e3= preprocessor_directive )
        // resources/Clang/CPreprocessorLL.g:386:6: e3= preprocessor_directive
        {
        pushFollow(FOLLOW_preprocessor_directive_in_synpred116_CPreprocessorLL2283);
        e3=preprocessor_directive();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred116_CPreprocessorLL

    // $ANTLR start synpred188_CPreprocessorLL
    public final void synpred188_CPreprocessorLL_fragment() throws RecognitionException {   
        Token e1=null;

        // resources/Clang/CPreprocessorLL.g:396:4: (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA ) )
        // resources/Clang/CPreprocessorLL.g:396:4: e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | WS | QUESTION | COMMENT | LINE_COMMENT | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA )
        {
        e1=(Token)input.LT(1);
        if ( (input.LA(1)>=HASH && input.LA(1)<=ERROR)||(input.LA(1)>=PRAGMA && input.LA(1)<=IF)||input.LA(1)==ELSE||(input.LA(1)>=SEMICOLON && input.LA(1)<=ELLIPSES)||(input.LA(1)>=DECIMAL_LITERAL && input.LA(1)<=OCTAL_LITERAL)||input.LA(1)==HEX_LITERAL||input.LA(1)==FLOATING_LITERAL||input.LA(1)==CHAR_LITERAL||(input.LA(1)>=WS && input.LA(1)<=LINE_COMMENT)||input.LA(1)==ID||input.LA(1)==STRING_LITERAL ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }
    }
    // $ANTLR end synpred188_CPreprocessorLL

    // $ANTLR start synpred255_CPreprocessorLL
    public final void synpred255_CPreprocessorLL_fragment() throws RecognitionException {   
        Token e1=null;

        // resources/Clang/CPreprocessorLL.g:407:4: (e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME ) )
        // resources/Clang/CPreprocessorLL.g:407:4: e1= ( IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN | RPAREN | LCURLY | RCURLY | LBRACK | RBRACK | PIPE | NOT | EQUALS | NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT | DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN | BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN | LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE | CARET | OR | AND | STAR | DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE | WS | COMMENT | LINE_COMMENT | QUESTION | ID | STRING_LITERAL | INCLUDE | DEFINE | DEFINED | UNDEF | LINE | ERROR | PRAGMA | FILENAME )
        {
        e1=(Token)input.LT(1);
        if ( (input.LA(1)>=INCLUDE && input.LA(1)<=ERROR)||(input.LA(1)>=PRAGMA && input.LA(1)<=IF)||input.LA(1)==ELSE||(input.LA(1)>=SEMICOLON && input.LA(1)<=ELLIPSES)||(input.LA(1)>=DECIMAL_LITERAL && input.LA(1)<=OCTAL_LITERAL)||input.LA(1)==HEX_LITERAL||input.LA(1)==FLOATING_LITERAL||(input.LA(1)>=CHAR_LITERAL && input.LA(1)<=LINE_COMMENT)||(input.LA(1)>=ID && input.LA(1)<=STRING_LITERAL) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }
    }
    // $ANTLR end synpred255_CPreprocessorLL

    // $ANTLR start synpred261_CPreprocessorLL
    public final void synpred261_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:426:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:426:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred261_CPreprocessorLL3077); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred261_CPreprocessorLL

    // $ANTLR start synpred262_CPreprocessorLL
    public final void synpred262_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:427:73: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:427:73: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred262_CPreprocessorLL3086); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred262_CPreprocessorLL

    // $ANTLR start synpred263_CPreprocessorLL
    public final void synpred263_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:430:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:430:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred263_CPreprocessorLL3101); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred263_CPreprocessorLL

    // $ANTLR start synpred264_CPreprocessorLL
    public final void synpred264_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:432:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:432:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred264_CPreprocessorLL3113); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred264_CPreprocessorLL

    // $ANTLR start synpred265_CPreprocessorLL
    public final void synpred265_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:432:20: ( WS )
        // resources/Clang/CPreprocessorLL.g:432:20: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred265_CPreprocessorLL3121); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred265_CPreprocessorLL

    // $ANTLR start synpred267_CPreprocessorLL
    public final void synpred267_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:433:17: ( WS )
        // resources/Clang/CPreprocessorLL.g:433:17: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred267_CPreprocessorLL3144); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred267_CPreprocessorLL

    // $ANTLR start synpred268_CPreprocessorLL
    public final void synpred268_CPreprocessorLL_fragment() throws RecognitionException {   
        ConstExpr e2 = null;

        ConditionalExpr e3 = null;


        // resources/Clang/CPreprocessorLL.g:432:10: ( QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression )
        // resources/Clang/CPreprocessorLL.g:432:10: QUESTION ( ( WS )* ) e2= const_expr ( ( WS )* ) COLON ( ( WS )* ) e3= conditional_expression
        {
        match(input,QUESTION,FOLLOW_QUESTION_in_synpred268_CPreprocessorLL3118); if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:432:19: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:432:20: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:432:20: ( WS )*
        loop251:
        do {
            int alt251=2;
            int LA251_0 = input.LA(1);

            if ( (LA251_0==WS) ) {
                int LA251_2 = input.LA(2);

                if ( (synpred265_CPreprocessorLL()) ) {
                    alt251=1;
                }


            }


            switch (alt251) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred268_CPreprocessorLL3121); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop251;
            }
        } while (true);


        }

        pushFollow(FOLLOW_const_expr_in_synpred268_CPreprocessorLL3128);
        e2=const_expr();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:433:4: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:433:5: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:433:5: ( WS )*
        loop252:
        do {
            int alt252=2;
            int LA252_0 = input.LA(1);

            if ( (LA252_0==WS) ) {
                alt252=1;
            }


            switch (alt252) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred268_CPreprocessorLL3137); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop252;
            }
        } while (true);


        }

        match(input,COLON,FOLLOW_COLON_in_synpred268_CPreprocessorLL3141); if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:433:16: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:433:17: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:433:17: ( WS )*
        loop253:
        do {
            int alt253=2;
            int LA253_0 = input.LA(1);

            if ( (LA253_0==WS) ) {
                int LA253_2 = input.LA(2);

                if ( (synpred267_CPreprocessorLL()) ) {
                    alt253=1;
                }


            }


            switch (alt253) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred268_CPreprocessorLL3144); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop253;
            }
        } while (true);


        }

        pushFollow(FOLLOW_conditional_expression_in_synpred268_CPreprocessorLL3151);
        e3=conditional_expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred268_CPreprocessorLL

    // $ANTLR start synpred269_CPreprocessorLL
    public final void synpred269_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:433:94: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:433:94: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred269_CPreprocessorLL3157); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred269_CPreprocessorLL

    // $ANTLR start synpred270_CPreprocessorLL
    public final void synpred270_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:436:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:436:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred270_CPreprocessorLL3172); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred270_CPreprocessorLL

    // $ANTLR start synpred271_CPreprocessorLL
    public final void synpred271_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:438:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:438:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred271_CPreprocessorLL3185); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred271_CPreprocessorLL

    // $ANTLR start synpred272_CPreprocessorLL
    public final void synpred272_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:438:14: ( WS )
        // resources/Clang/CPreprocessorLL.g:438:14: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred272_CPreprocessorLL3193); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred272_CPreprocessorLL

    // $ANTLR start synpred273_CPreprocessorLL
    public final void synpred273_CPreprocessorLL_fragment() throws RecognitionException {   
        LogicalAndExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:438:10: ( OR ( ( WS )* ) e2= logical_and_expr )
        // resources/Clang/CPreprocessorLL.g:438:10: OR ( ( WS )* ) e2= logical_and_expr
        {
        match(input,OR,FOLLOW_OR_in_synpred273_CPreprocessorLL3190); if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:438:13: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:438:14: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:438:14: ( WS )*
        loop254:
        do {
            int alt254=2;
            int LA254_0 = input.LA(1);

            if ( (LA254_0==WS) ) {
                int LA254_2 = input.LA(2);

                if ( (synpred272_CPreprocessorLL()) ) {
                    alt254=1;
                }


            }


            switch (alt254) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred273_CPreprocessorLL3193); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop254;
            }
        } while (true);


        }

        pushFollow(FOLLOW_logical_and_expr_in_synpred273_CPreprocessorLL3199);
        e2=logical_and_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred273_CPreprocessorLL

    // $ANTLR start synpred274_CPreprocessorLL
    public final void synpred274_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:438:90: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:438:90: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred274_CPreprocessorLL3205); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred274_CPreprocessorLL

    // $ANTLR start synpred275_CPreprocessorLL
    public final void synpred275_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:441:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:441:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred275_CPreprocessorLL3220); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred275_CPreprocessorLL

    // $ANTLR start synpred276_CPreprocessorLL
    public final void synpred276_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:443:5: ( WS )
        // resources/Clang/CPreprocessorLL.g:443:5: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred276_CPreprocessorLL3234); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred276_CPreprocessorLL

    // $ANTLR start synpred277_CPreprocessorLL
    public final void synpred277_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:443:16: ( WS )
        // resources/Clang/CPreprocessorLL.g:443:16: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred277_CPreprocessorLL3242); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred277_CPreprocessorLL

    // $ANTLR start synpred278_CPreprocessorLL
    public final void synpred278_CPreprocessorLL_fragment() throws RecognitionException {   
        InclusiveOrExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:443:11: ( AND ( ( WS )* ) e2= inclusive_or_expr )
        // resources/Clang/CPreprocessorLL.g:443:11: AND ( ( WS )* ) e2= inclusive_or_expr
        {
        match(input,AND,FOLLOW_AND_in_synpred278_CPreprocessorLL3239); if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:443:15: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:443:16: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:443:16: ( WS )*
        loop255:
        do {
            int alt255=2;
            int LA255_0 = input.LA(1);

            if ( (LA255_0==WS) ) {
                int LA255_2 = input.LA(2);

                if ( (synpred277_CPreprocessorLL()) ) {
                    alt255=1;
                }


            }


            switch (alt255) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred278_CPreprocessorLL3242); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop255;
            }
        } while (true);


        }

        pushFollow(FOLLOW_inclusive_or_expr_in_synpred278_CPreprocessorLL3248);
        e2=inclusive_or_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred278_CPreprocessorLL

    // $ANTLR start synpred279_CPreprocessorLL
    public final void synpred279_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:443:94: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:443:94: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred279_CPreprocessorLL3254); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred279_CPreprocessorLL

    // $ANTLR start synpred280_CPreprocessorLL
    public final void synpred280_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:446:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:446:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred280_CPreprocessorLL3269); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred280_CPreprocessorLL

    // $ANTLR start synpred281_CPreprocessorLL
    public final void synpred281_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:447:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:447:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred281_CPreprocessorLL3283); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred281_CPreprocessorLL

    // $ANTLR start synpred282_CPreprocessorLL
    public final void synpred282_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:447:16: ( WS )
        // resources/Clang/CPreprocessorLL.g:447:16: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred282_CPreprocessorLL3291); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred282_CPreprocessorLL

    // $ANTLR start synpred283_CPreprocessorLL
    public final void synpred283_CPreprocessorLL_fragment() throws RecognitionException {   
        ExclusiveOrExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:447:10: ( PIPE ( ( WS )* ) e2= exclusive_or_expr )
        // resources/Clang/CPreprocessorLL.g:447:10: PIPE ( ( WS )* ) e2= exclusive_or_expr
        {
        match(input,PIPE,FOLLOW_PIPE_in_synpred283_CPreprocessorLL3288); if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:447:15: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:447:16: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:447:16: ( WS )*
        loop256:
        do {
            int alt256=2;
            int LA256_0 = input.LA(1);

            if ( (LA256_0==WS) ) {
                int LA256_2 = input.LA(2);

                if ( (synpred282_CPreprocessorLL()) ) {
                    alt256=1;
                }


            }


            switch (alt256) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred283_CPreprocessorLL3291); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop256;
            }
        } while (true);


        }

        pushFollow(FOLLOW_exclusive_or_expr_in_synpred283_CPreprocessorLL3297);
        e2=exclusive_or_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred283_CPreprocessorLL

    // $ANTLR start synpred284_CPreprocessorLL
    public final void synpred284_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:448:56: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:448:56: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred284_CPreprocessorLL3306); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred284_CPreprocessorLL

    // $ANTLR start synpred285_CPreprocessorLL
    public final void synpred285_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:451:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:451:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred285_CPreprocessorLL3321); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred285_CPreprocessorLL

    // $ANTLR start synpred286_CPreprocessorLL
    public final void synpred286_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:452:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:452:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred286_CPreprocessorLL3334); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred286_CPreprocessorLL

    // $ANTLR start synpred287_CPreprocessorLL
    public final void synpred287_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:452:23: ( WS )
        // resources/Clang/CPreprocessorLL.g:452:23: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred287_CPreprocessorLL3345); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred287_CPreprocessorLL

    // $ANTLR start synpred288_CPreprocessorLL
    public final void synpred288_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        AndExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:452:9: ( (sign= CARET ) ( ( WS )* ) e2= and_expr )
        // resources/Clang/CPreprocessorLL.g:452:9: (sign= CARET ) ( ( WS )* ) e2= and_expr
        {
        // resources/Clang/CPreprocessorLL.g:452:9: (sign= CARET )
        // resources/Clang/CPreprocessorLL.g:452:10: sign= CARET
        {
        sign=(Token)match(input,CARET,FOLLOW_CARET_in_synpred288_CPreprocessorLL3341); if (state.failed) return ;

        }

        // resources/Clang/CPreprocessorLL.g:452:22: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:452:23: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:452:23: ( WS )*
        loop257:
        do {
            int alt257=2;
            int LA257_0 = input.LA(1);

            if ( (LA257_0==WS) ) {
                int LA257_2 = input.LA(2);

                if ( (synpred287_CPreprocessorLL()) ) {
                    alt257=1;
                }


            }


            switch (alt257) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred288_CPreprocessorLL3345); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop257;
            }
        } while (true);


        }

        pushFollow(FOLLOW_and_expr_in_synpred288_CPreprocessorLL3351);
        e2=and_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred288_CPreprocessorLL

    // $ANTLR start synpred289_CPreprocessorLL
    public final void synpred289_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:452:93: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:452:93: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred289_CPreprocessorLL3357); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred289_CPreprocessorLL

    // $ANTLR start synpred290_CPreprocessorLL
    public final void synpred290_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:455:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:455:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred290_CPreprocessorLL3372); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred290_CPreprocessorLL

    // $ANTLR start synpred291_CPreprocessorLL
    public final void synpred291_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:456:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:456:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred291_CPreprocessorLL3386); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred291_CPreprocessorLL

    // $ANTLR start synpred292_CPreprocessorLL
    public final void synpred292_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:456:28: ( WS )
        // resources/Clang/CPreprocessorLL.g:456:28: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred292_CPreprocessorLL3398); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred292_CPreprocessorLL

    // $ANTLR start synpred293_CPreprocessorLL
    public final void synpred293_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        EqualityExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:456:10: ( (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr )
        // resources/Clang/CPreprocessorLL.g:456:10: (sign= AMPERSAND ) ( ( WS )* ) e2= equality_expr
        {
        // resources/Clang/CPreprocessorLL.g:456:10: (sign= AMPERSAND )
        // resources/Clang/CPreprocessorLL.g:456:11: sign= AMPERSAND
        {
        sign=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_synpred293_CPreprocessorLL3394); if (state.failed) return ;

        }

        // resources/Clang/CPreprocessorLL.g:456:27: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:456:28: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:456:28: ( WS )*
        loop258:
        do {
            int alt258=2;
            int LA258_0 = input.LA(1);

            if ( (LA258_0==WS) ) {
                int LA258_2 = input.LA(2);

                if ( (synpred292_CPreprocessorLL()) ) {
                    alt258=1;
                }


            }


            switch (alt258) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred293_CPreprocessorLL3398); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop258;
            }
        } while (true);


        }

        pushFollow(FOLLOW_equality_expr_in_synpred293_CPreprocessorLL3404);
        e2=equality_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred293_CPreprocessorLL

    // $ANTLR start synpred294_CPreprocessorLL
    public final void synpred294_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:456:95: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:456:95: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred294_CPreprocessorLL3410); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred294_CPreprocessorLL

    // $ANTLR start synpred295_CPreprocessorLL
    public final void synpred295_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:459:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:459:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred295_CPreprocessorLL3425); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred295_CPreprocessorLL

    // $ANTLR start synpred296_CPreprocessorLL
    public final void synpred296_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:461:5: ( WS )
        // resources/Clang/CPreprocessorLL.g:461:5: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred296_CPreprocessorLL3439); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred296_CPreprocessorLL

    // $ANTLR start synpred298_CPreprocessorLL
    public final void synpred298_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:461:45: ( WS )
        // resources/Clang/CPreprocessorLL.g:461:45: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred298_CPreprocessorLL3458); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred298_CPreprocessorLL

    // $ANTLR start synpred299_CPreprocessorLL
    public final void synpred299_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        RelationalExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:461:11: ( (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr )
        // resources/Clang/CPreprocessorLL.g:461:11: (sign= EQUALS | sign= NOT_EQUALS ) ( ( WS )* ) e2= relational_expr
        {
        // resources/Clang/CPreprocessorLL.g:461:11: (sign= EQUALS | sign= NOT_EQUALS )
        int alt259=2;
        int LA259_0 = input.LA(1);

        if ( (LA259_0==EQUALS) ) {
            alt259=1;
        }
        else if ( (LA259_0==NOT_EQUALS) ) {
            alt259=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 259, 0, input);

            throw nvae;
        }
        switch (alt259) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:461:12: sign= EQUALS
                {
                sign=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_synpred299_CPreprocessorLL3447); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/CPreprocessorLL.g:461:26: sign= NOT_EQUALS
                {
                sign=(Token)match(input,NOT_EQUALS,FOLLOW_NOT_EQUALS_in_synpred299_CPreprocessorLL3453); if (state.failed) return ;

                }
                break;

        }

        // resources/Clang/CPreprocessorLL.g:461:44: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:461:45: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:461:45: ( WS )*
        loop260:
        do {
            int alt260=2;
            int LA260_0 = input.LA(1);

            if ( (LA260_0==WS) ) {
                int LA260_2 = input.LA(2);

                if ( (synpred298_CPreprocessorLL()) ) {
                    alt260=1;
                }


            }


            switch (alt260) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred299_CPreprocessorLL3458); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop260;
            }
        } while (true);


        }

        pushFollow(FOLLOW_relational_expr_in_synpred299_CPreprocessorLL3468);
        e2=relational_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred299_CPreprocessorLL

    // $ANTLR start synpred300_CPreprocessorLL
    public final void synpred300_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:462:111: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:462:111: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred300_CPreprocessorLL3475); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred300_CPreprocessorLL

    // $ANTLR start synpred301_CPreprocessorLL
    public final void synpred301_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:465:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:465:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred301_CPreprocessorLL3490); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred301_CPreprocessorLL

    // $ANTLR start synpred302_CPreprocessorLL
    public final void synpred302_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:467:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:467:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred302_CPreprocessorLL3504); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred302_CPreprocessorLL

    // $ANTLR start synpred306_CPreprocessorLL
    public final void synpred306_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:467:114: ( WS )
        // resources/Clang/CPreprocessorLL.g:467:114: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred306_CPreprocessorLL3535); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred306_CPreprocessorLL

    // $ANTLR start synpred307_CPreprocessorLL
    public final void synpred307_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        ShiftExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:467:10: ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr )
        // resources/Clang/CPreprocessorLL.g:467:10: (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) ( ( WS )* ) e2= shift_expr
        {
        // resources/Clang/CPreprocessorLL.g:467:10: (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO )
        int alt261=4;
        switch ( input.LA(1) ) {
        case LESSER_THAN:
            {
            alt261=1;
            }
            break;
        case GREATER_THAN:
            {
            alt261=2;
            }
            break;
        case LESSER_THAN_OR_EQUAL_TO:
            {
            alt261=3;
            }
            break;
        case GREATER_THAN_OR_EQUAL_TO:
            {
            alt261=4;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 261, 0, input);

            throw nvae;
        }

        switch (alt261) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:467:12: sign= LESSER_THAN
                {
                sign=(Token)match(input,LESSER_THAN,FOLLOW_LESSER_THAN_in_synpred307_CPreprocessorLL3513); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/CPreprocessorLL.g:467:31: sign= GREATER_THAN
                {
                sign=(Token)match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_synpred307_CPreprocessorLL3519); if (state.failed) return ;

                }
                break;
            case 3 :
                // resources/Clang/CPreprocessorLL.g:467:51: sign= LESSER_THAN_OR_EQUAL_TO
                {
                sign=(Token)match(input,LESSER_THAN_OR_EQUAL_TO,FOLLOW_LESSER_THAN_OR_EQUAL_TO_in_synpred307_CPreprocessorLL3525); if (state.failed) return ;

                }
                break;
            case 4 :
                // resources/Clang/CPreprocessorLL.g:467:82: sign= GREATER_THAN_OR_EQUAL_TO
                {
                sign=(Token)match(input,GREATER_THAN_OR_EQUAL_TO,FOLLOW_GREATER_THAN_OR_EQUAL_TO_in_synpred307_CPreprocessorLL3531); if (state.failed) return ;

                }
                break;

        }

        // resources/Clang/CPreprocessorLL.g:467:113: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:467:114: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:467:114: ( WS )*
        loop262:
        do {
            int alt262=2;
            int LA262_0 = input.LA(1);

            if ( (LA262_0==WS) ) {
                int LA262_2 = input.LA(2);

                if ( (synpred306_CPreprocessorLL()) ) {
                    alt262=1;
                }


            }


            switch (alt262) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred307_CPreprocessorLL3535); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop262;
            }
        } while (true);


        }

        pushFollow(FOLLOW_shift_expr_in_synpred307_CPreprocessorLL3544);
        e2=shift_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred307_CPreprocessorLL

    // $ANTLR start synpred308_CPreprocessorLL
    public final void synpred308_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:468:106: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:468:106: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred308_CPreprocessorLL3550); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred308_CPreprocessorLL

    // $ANTLR start synpred309_CPreprocessorLL
    public final void synpred309_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:471:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:471:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred309_CPreprocessorLL3565); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred309_CPreprocessorLL

    // $ANTLR start synpred310_CPreprocessorLL
    public final void synpred310_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:473:5: ( WS )
        // resources/Clang/CPreprocessorLL.g:473:5: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred310_CPreprocessorLL3579); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred310_CPreprocessorLL

    // $ANTLR start synpred312_CPreprocessorLL
    public final void synpred312_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:473:49: ( WS )
        // resources/Clang/CPreprocessorLL.g:473:49: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred312_CPreprocessorLL3597); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred312_CPreprocessorLL

    // $ANTLR start synpred313_CPreprocessorLL
    public final void synpred313_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        AdditiveExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:473:11: ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr )
        // resources/Clang/CPreprocessorLL.g:473:11: (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) ( ( WS )* ) e2= additive_expr
        {
        // resources/Clang/CPreprocessorLL.g:473:11: (sign= LEFT_SHIFT | sign= RIGHT_SHIFT )
        int alt263=2;
        int LA263_0 = input.LA(1);

        if ( (LA263_0==LEFT_SHIFT) ) {
            alt263=1;
        }
        else if ( (LA263_0==RIGHT_SHIFT) ) {
            alt263=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 263, 0, input);

            throw nvae;
        }
        switch (alt263) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:473:12: sign= LEFT_SHIFT
                {
                sign=(Token)match(input,LEFT_SHIFT,FOLLOW_LEFT_SHIFT_in_synpred313_CPreprocessorLL3587); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/CPreprocessorLL.g:473:30: sign= RIGHT_SHIFT
                {
                sign=(Token)match(input,RIGHT_SHIFT,FOLLOW_RIGHT_SHIFT_in_synpred313_CPreprocessorLL3593); if (state.failed) return ;

                }
                break;

        }

        // resources/Clang/CPreprocessorLL.g:473:48: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:473:49: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:473:49: ( WS )*
        loop264:
        do {
            int alt264=2;
            int LA264_0 = input.LA(1);

            if ( (LA264_0==WS) ) {
                int LA264_2 = input.LA(2);

                if ( (synpred312_CPreprocessorLL()) ) {
                    alt264=1;
                }


            }


            switch (alt264) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred313_CPreprocessorLL3597); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop264;
            }
        } while (true);


        }

        pushFollow(FOLLOW_additive_expr_in_synpred313_CPreprocessorLL3607);
        e2=additive_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred313_CPreprocessorLL

    // $ANTLR start synpred314_CPreprocessorLL
    public final void synpred314_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:474:105: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:474:105: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred314_CPreprocessorLL3613); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred314_CPreprocessorLL

    // $ANTLR start synpred315_CPreprocessorLL
    public final void synpred315_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:477:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:477:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred315_CPreprocessorLL3628); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred315_CPreprocessorLL

    // $ANTLR start synpred316_CPreprocessorLL
    public final void synpred316_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:479:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:479:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred316_CPreprocessorLL3641); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred316_CPreprocessorLL

    // $ANTLR start synpred318_CPreprocessorLL
    public final void synpred318_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:479:38: ( WS )
        // resources/Clang/CPreprocessorLL.g:479:38: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred318_CPreprocessorLL3661); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred318_CPreprocessorLL

    // $ANTLR start synpred319_CPreprocessorLL
    public final void synpred319_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        MultiplicativeExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:479:10: ( (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr )
        // resources/Clang/CPreprocessorLL.g:479:10: (sign= PLUS | sign= MINUS ) ( ( WS )* ) e2= multiplicative_expr
        {
        // resources/Clang/CPreprocessorLL.g:479:10: (sign= PLUS | sign= MINUS )
        int alt265=2;
        int LA265_0 = input.LA(1);

        if ( (LA265_0==PLUS) ) {
            alt265=1;
        }
        else if ( (LA265_0==MINUS) ) {
            alt265=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 265, 0, input);

            throw nvae;
        }
        switch (alt265) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:479:12: sign= PLUS
                {
                sign=(Token)match(input,PLUS,FOLLOW_PLUS_in_synpred319_CPreprocessorLL3650); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/CPreprocessorLL.g:479:25: sign= MINUS
                {
                sign=(Token)match(input,MINUS,FOLLOW_MINUS_in_synpred319_CPreprocessorLL3657); if (state.failed) return ;

                }
                break;

        }

        // resources/Clang/CPreprocessorLL.g:479:37: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:479:38: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:479:38: ( WS )*
        loop266:
        do {
            int alt266=2;
            int LA266_0 = input.LA(1);

            if ( (LA266_0==WS) ) {
                int LA266_2 = input.LA(2);

                if ( (synpred318_CPreprocessorLL()) ) {
                    alt266=1;
                }


            }


            switch (alt266) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred319_CPreprocessorLL3661); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop266;
            }
        } while (true);


        }

        pushFollow(FOLLOW_multiplicative_expr_in_synpred319_CPreprocessorLL3669);
        e2=multiplicative_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred319_CPreprocessorLL

    // $ANTLR start synpred320_CPreprocessorLL
    public final void synpred320_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:480:112: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:480:112: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred320_CPreprocessorLL3675); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred320_CPreprocessorLL

    // $ANTLR start synpred321_CPreprocessorLL
    public final void synpred321_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:483:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:483:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred321_CPreprocessorLL3690); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred321_CPreprocessorLL

    // $ANTLR start synpred322_CPreprocessorLL
    public final void synpred322_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:485:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:485:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred322_CPreprocessorLL3703); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred322_CPreprocessorLL

    // $ANTLR start synpred325_CPreprocessorLL
    public final void synpred325_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:485:53: ( WS )
        // resources/Clang/CPreprocessorLL.g:485:53: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred325_CPreprocessorLL3729); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred325_CPreprocessorLL

    // $ANTLR start synpred326_CPreprocessorLL
    public final void synpred326_CPreprocessorLL_fragment() throws RecognitionException {   
        Token sign=null;
        UnaryExpr e2 = null;


        // resources/Clang/CPreprocessorLL.g:485:10: ( (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr )
        // resources/Clang/CPreprocessorLL.g:485:10: (sign= STAR | sign= DIVIDE | sign= MODULO ) ( ( WS )* ) e2= unary_expr
        {
        // resources/Clang/CPreprocessorLL.g:485:10: (sign= STAR | sign= DIVIDE | sign= MODULO )
        int alt267=3;
        switch ( input.LA(1) ) {
        case STAR:
            {
            alt267=1;
            }
            break;
        case DIVIDE:
            {
            alt267=2;
            }
            break;
        case MODULO:
            {
            alt267=3;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 267, 0, input);

            throw nvae;
        }

        switch (alt267) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:485:12: sign= STAR
                {
                sign=(Token)match(input,STAR,FOLLOW_STAR_in_synpred326_CPreprocessorLL3712); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/CPreprocessorLL.g:485:24: sign= DIVIDE
                {
                sign=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_synpred326_CPreprocessorLL3718); if (state.failed) return ;

                }
                break;
            case 3 :
                // resources/Clang/CPreprocessorLL.g:485:38: sign= MODULO
                {
                sign=(Token)match(input,MODULO,FOLLOW_MODULO_in_synpred326_CPreprocessorLL3724); if (state.failed) return ;

                }
                break;

        }

        // resources/Clang/CPreprocessorLL.g:485:52: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:485:53: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:485:53: ( WS )*
        loop268:
        do {
            int alt268=2;
            int LA268_0 = input.LA(1);

            if ( (LA268_0==WS) ) {
                int LA268_2 = input.LA(2);

                if ( (synpred325_CPreprocessorLL()) ) {
                    alt268=1;
                }


            }


            switch (alt268) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred326_CPreprocessorLL3729); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop268;
            }
        } while (true);


        }

        pushFollow(FOLLOW_unary_expr_in_synpred326_CPreprocessorLL3737);
        e2=unary_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred326_CPreprocessorLL

    // $ANTLR start synpred327_CPreprocessorLL
    public final void synpred327_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:486:109: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:486:109: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred327_CPreprocessorLL3743); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred327_CPreprocessorLL

    // $ANTLR start synpred328_CPreprocessorLL
    public final void synpred328_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:489:4: ( LPAREN )
        // resources/Clang/CPreprocessorLL.g:489:4: LPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred328_CPreprocessorLL3758); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred328_CPreprocessorLL

    // $ANTLR start synpred329_CPreprocessorLL
    public final void synpred329_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:490:76: ( WS )
        // resources/Clang/CPreprocessorLL.g:490:76: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred329_CPreprocessorLL3768); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred329_CPreprocessorLL

    // $ANTLR start synpred330_CPreprocessorLL
    public final void synpred330_CPreprocessorLL_fragment() throws RecognitionException {   
        PrimaryExpr e = null;


        // resources/Clang/CPreprocessorLL.g:489:4: ( ( LPAREN )? e= primary_expr ( ( WS )* ) )
        // resources/Clang/CPreprocessorLL.g:489:4: ( LPAREN )? e= primary_expr ( ( WS )* )
        {
        // resources/Clang/CPreprocessorLL.g:489:4: ( LPAREN )?
        int alt269=2;
        int LA269_0 = input.LA(1);

        if ( (LA269_0==LPAREN) ) {
            int LA269_1 = input.LA(2);

            if ( (synpred328_CPreprocessorLL()) ) {
                alt269=1;
            }
        }
        switch (alt269) {
            case 1 :
                // resources/Clang/CPreprocessorLL.g:0:0: LPAREN
                {
                match(input,LPAREN,FOLLOW_LPAREN_in_synpred330_CPreprocessorLL3758); if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_primary_expr_in_synpred330_CPreprocessorLL3763);
        e=primary_expr();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:490:75: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:490:76: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:490:76: ( WS )*
        loop270:
        do {
            int alt270=2;
            int LA270_0 = input.LA(1);

            if ( (LA270_0==WS) ) {
                alt270=1;
            }


            switch (alt270) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred330_CPreprocessorLL3768); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop270;
            }
        } while (true);


        }


        }
    }
    // $ANTLR end synpred330_CPreprocessorLL

    // $ANTLR start synpred334_CPreprocessorLL
    public final void synpred334_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:495:6: ( WS )
        // resources/Clang/CPreprocessorLL.g:495:6: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred334_CPreprocessorLL3827); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred334_CPreprocessorLL

    // $ANTLR start synpred335_CPreprocessorLL
    public final void synpred335_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:495:77: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:495:77: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred335_CPreprocessorLL3837); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred335_CPreprocessorLL

    // $ANTLR start synpred338_CPreprocessorLL
    public final void synpred338_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:499:149: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:499:149: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred338_CPreprocessorLL3872); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred338_CPreprocessorLL

    // $ANTLR start synpred348_CPreprocessorLL
    public final void synpred348_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:511:39: ( WS )
        // resources/Clang/CPreprocessorLL.g:511:39: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred348_CPreprocessorLL3985); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred348_CPreprocessorLL

    // $ANTLR start synpred351_CPreprocessorLL
    public final void synpred351_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:511:66: ( WS )
        // resources/Clang/CPreprocessorLL.g:511:66: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred351_CPreprocessorLL4002); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred351_CPreprocessorLL

    // $ANTLR start synpred352_CPreprocessorLL
    public final void synpred352_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:511:71: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:511:71: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred352_CPreprocessorLL4006); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred352_CPreprocessorLL

    // $ANTLR start synpred354_CPreprocessorLL
    public final void synpred354_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:516:4: ( WS )
        // resources/Clang/CPreprocessorLL.g:516:4: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred354_CPreprocessorLL4048); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred354_CPreprocessorLL

    // $ANTLR start synpred355_CPreprocessorLL
    public final void synpred355_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:516:18: ( WS )
        // resources/Clang/CPreprocessorLL.g:516:18: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred355_CPreprocessorLL4057); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred355_CPreprocessorLL

    // $ANTLR start synpred356_CPreprocessorLL
    public final void synpred356_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:516:73: ( WS )
        // resources/Clang/CPreprocessorLL.g:516:73: WS
        {
        match(input,WS,FOLLOW_WS_in_synpred356_CPreprocessorLL4068); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred356_CPreprocessorLL

    // $ANTLR start synpred357_CPreprocessorLL
    public final void synpred357_CPreprocessorLL_fragment() throws RecognitionException {   
        ConstExpr e4 = null;


        // resources/Clang/CPreprocessorLL.g:516:10: ( COMMA ( ( WS )* ) e4= const_expr ( ( WS )* ) )
        // resources/Clang/CPreprocessorLL.g:516:10: COMMA ( ( WS )* ) e4= const_expr ( ( WS )* )
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred357_CPreprocessorLL4053); if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:516:17: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:516:18: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:516:18: ( WS )*
        loop273:
        do {
            int alt273=2;
            int LA273_0 = input.LA(1);

            if ( (LA273_0==WS) ) {
                int LA273_2 = input.LA(2);

                if ( (synpred355_CPreprocessorLL()) ) {
                    alt273=1;
                }


            }


            switch (alt273) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred357_CPreprocessorLL4057); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop273;
            }
        } while (true);


        }

        pushFollow(FOLLOW_const_expr_in_synpred357_CPreprocessorLL4063);
        e4=const_expr();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/CPreprocessorLL.g:516:72: ( ( WS )* )
        // resources/Clang/CPreprocessorLL.g:516:73: ( WS )*
        {
        // resources/Clang/CPreprocessorLL.g:516:73: ( WS )*
        loop274:
        do {
            int alt274=2;
            int LA274_0 = input.LA(1);

            if ( (LA274_0==WS) ) {
                alt274=1;
            }


            switch (alt274) {
        	case 1 :
        	    // resources/Clang/CPreprocessorLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred357_CPreprocessorLL4068); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop274;
            }
        } while (true);


        }


        }
    }
    // $ANTLR end synpred357_CPreprocessorLL

    // $ANTLR start synpred358_CPreprocessorLL
    public final void synpred358_CPreprocessorLL_fragment() throws RecognitionException {   
        // resources/Clang/CPreprocessorLL.g:516:80: ( RPAREN )
        // resources/Clang/CPreprocessorLL.g:516:80: RPAREN
        {
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred358_CPreprocessorLL4074); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred358_CPreprocessorLL

    // Delegated rules

    public final boolean synpred306_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred306_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred299_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred299_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred87_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred87_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred301_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred301_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred41_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred56_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred56_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred68_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred68_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred318_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred318_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred188_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred188_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred69_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred69_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred329_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred329_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred60_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred60_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred316_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred316_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred334_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred334_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred40_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred291_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred291_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred319_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred319_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred358_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred358_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred277_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred277_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred313_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred313_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred355_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred355_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred321_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred321_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred74_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred74_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred112_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred112_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred280_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred280_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred288_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred288_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred103_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred314_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred314_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred272_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred272_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred276_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred276_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred270_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred270_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred326_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred326_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred255_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred255_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred285_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred285_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred116_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred116_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred263_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred263_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred274_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred274_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred262_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred262_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred307_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred307_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred327_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred327_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred42_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred42_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred296_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred296_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred76_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred76_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred287_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred287_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred290_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred290_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred268_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred268_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred70_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred330_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred330_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred48_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred48_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred289_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred289_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred354_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred354_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred302_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred302_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred67_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred67_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred356_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred356_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred275_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred275_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred315_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred315_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred298_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred298_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred308_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred308_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred283_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred283_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred271_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred271_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred63_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred63_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred281_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred281_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred62_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred62_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred265_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred265_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred273_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred273_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred279_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred279_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred328_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred328_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred282_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred282_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred278_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred278_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred351_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred351_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred335_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred335_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred320_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred320_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred26_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred293_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred293_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred309_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred309_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred49_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred49_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred54_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred54_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred284_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred284_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred300_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred300_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred292_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred292_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred80_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred80_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred61_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred61_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred295_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred295_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred267_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred267_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred83_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred83_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred312_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred312_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred91_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred91_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred286_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred286_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred79_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred79_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred75_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred75_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred357_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred357_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred264_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred264_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred325_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred325_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred348_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred348_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred28_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred269_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred269_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred261_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred261_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred310_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred310_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred322_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred322_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred95_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred95_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred294_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred294_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred338_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred338_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred352_CPreprocessorLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred352_CPreprocessorLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA78 dfa78 = new DFA78(this);
    protected DFA79 dfa79 = new DFA79(this);
    protected DFA80 dfa80 = new DFA80(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA102 dfa102 = new DFA102(this);
    protected DFA105 dfa105 = new DFA105(this);
    protected DFA109 dfa109 = new DFA109(this);
    protected DFA187 dfa187 = new DFA187(this);
    static final String DFA2_eotS =
        "\13\uffff";
    static final String DFA2_eofS =
        "\13\uffff";
    static final String DFA2_minS =
        "\1\4\2\6\10\uffff";
    static final String DFA2_maxS =
        "\1\4\2\116\10\uffff";
    static final String DFA2_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10";
    static final String DFA2_specialS =
        "\13\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1",
            "\1\3\1\4\1\uffff\1\4\1\5\1\6\1\7\1\10\3\11\73\uffff\1\12\1"+
            "\2\1\12",
            "\1\3\1\4\1\uffff\1\4\1\5\1\6\1\7\1\10\3\11\73\uffff\1\12\1"+
            "\2\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "317:1: preprocessor_directive returns [PreprocessorDirective value] : (e1= artifact_include | e2= definition | e3= line | e4= pp_error | e5= pp_warning | e6= pp_pragma | e7= conditional | e8= null_directive );";
        }
    }
    static final String DFA16_eotS =
        "\7\uffff";
    static final String DFA16_eofS =
        "\7\uffff";
    static final String DFA16_minS =
        "\1\4\2\6\2\64\2\uffff";
    static final String DFA16_maxS =
        "\1\4\2\115\2\123\2\uffff";
    static final String DFA16_acceptS =
        "\5\uffff\1\1\1\2";
    static final String DFA16_specialS =
        "\7\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\1",
            "\1\3\106\uffff\1\2",
            "\1\3\106\uffff\1\2",
            "\1\5\30\uffff\1\4\5\uffff\1\6",
            "\1\5\30\uffff\1\4\5\uffff\1\6",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "327:1: artifact_include returns [IncludeDirective value] : (e0= HASH ( ( WS )* ) INCLUDE ( ( WS )* ) LESSER_THAN ( ( WS )* ) e1= filename_lib ( ( WS )* ) GREATER_THAN ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | HASH ( ( WS )* ) INCLUDE ( ( WS )* ) e2= STRING_LITERAL ( ( WS )* ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE );";
        }
    }
    static final String DFA43_eotS =
        "\11\uffff";
    static final String DFA43_eofS =
        "\11\uffff";
    static final String DFA43_minS =
        "\1\4\2\7\1\115\1\uffff\1\115\1\33\2\uffff";
    static final String DFA43_maxS =
        "\1\4\3\115\1\uffff\1\121\1\116\2\uffff";
    static final String DFA43_acceptS =
        "\4\uffff\1\3\2\uffff\1\2\1\1";
    static final String DFA43_specialS =
        "\11\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\1",
            "\1\3\1\uffff\1\4\103\uffff\1\2",
            "\1\3\1\uffff\1\4\103\uffff\1\2",
            "\1\5",
            "",
            "\1\5\3\uffff\1\6",
            "\1\7\60\uffff\3\10",
            "",
            ""
    };

    static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
    static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
    static final char[] DFA43_min = DFA.unpackEncodedStringToUnsignedChars(DFA43_minS);
    static final char[] DFA43_max = DFA.unpackEncodedStringToUnsignedChars(DFA43_maxS);
    static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
    static final short[] DFA43_special = DFA.unpackEncodedString(DFA43_specialS);
    static final short[][] DFA43_transition;

    static {
        int numStates = DFA43_transitionS.length;
        DFA43_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA43_transition[i] = DFA.unpackEncodedString(DFA43_transitionS[i]);
        }
    }

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = DFA43_eot;
            this.eof = DFA43_eof;
            this.min = DFA43_min;
            this.max = DFA43_max;
            this.accept = DFA43_accept;
            this.special = DFA43_special;
            this.transition = DFA43_transition;
        }
        public String getDescription() {
            return "336:1: definition returns [Definition value] : ( HASH ( ( WS )* ) DEFINE ( ( WS )+ ) e1= ID ( ( ( WS )+ ) e6= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | HASH ( ( WS )* ) DEFINE ( ( WS )+ ) id1= ID LPAREN ( ( WS )* ) (e2= ID ( ( WS )* ) ( COMMA ( ( WS )* ) e3= ID )* )? ( ( WS )* ) RPAREN ( ( ( WS )+ ) e4= token_sequence )? ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE | ( HASH ( ( WS )* ) UNDEF ( ( WS )+ ) e5= ID ( ( WS )* ) ) ( ( WS )* ) ( COMMENT )* ( ( WS )* ) NEWLINE );";
        }
    }
    static final String DFA78_eotS =
        "\41\uffff";
    static final String DFA78_eofS =
        "\41\uffff";
    static final String DFA78_minS =
        "\1\4\1\6\1\uffff\1\6\1\115\2\uffff\3\10\1\33\1\10\2\33\1\115\1"+
        "\27\1\33\1\115\1\27\1\115\2\27\1\114\1\0\1\115\10\27";
    static final String DFA78_maxS =
        "\1\123\1\116\1\uffff\1\116\1\115\2\uffff\3\123\1\121\1\123\3\121"+
        "\1\116\2\121\1\116\1\121\3\116\1\0\1\121\10\116";
    static final String DFA78_acceptS =
        "\2\uffff\1\1\2\uffff\1\3\1\2\32\uffff";
    static final String DFA78_specialS =
        "\27\uffff\1\0\11\uffff}>";
    static final String[] DFA78_transitionS = {
            "\1\1\1\uffff\6\2\1\uffff\2\2\3\uffff\1\2\1\uffff\56\2\1\uffff"+
            "\2\2\1\uffff\1\2\2\uffff\1\2\1\uffff\5\2\1\uffff\3\2",
            "\2\6\1\uffff\5\6\1\4\2\6\3\5\70\uffff\1\6\1\3\1\6",
            "",
            "\2\6\1\uffff\5\6\1\4\2\6\3\5\70\uffff\1\6\1\3\1\6",
            "\1\7",
            "",
            "",
            "\1\12\16\uffff\2\6\2\uffff\1\6\7\uffff\1\10\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\11\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\14\16\uffff\2\6\2\uffff\1\6\7\uffff\1\6\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\13\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\12\16\uffff\2\6\2\uffff\1\6\7\uffff\1\10\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\11\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\16\61\uffff\1\15\3\uffff\1\17",
            "\1\14\16\uffff\2\6\2\uffff\1\6\7\uffff\1\6\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\13\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\21\61\uffff\1\20\3\uffff\1\22",
            "\1\16\61\uffff\1\15\3\uffff\1\17",
            "\1\23\3\uffff\1\24",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\25\1\26",
            "\1\21\61\uffff\1\20\3\uffff\1\22",
            "\1\30\3\uffff\1\31",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\32\1\26",
            "\1\23\3\uffff\1\24",
            "\2\6\3\uffff\1\34\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\33\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\25\1\26",
            "\1\27\1\6\1\26",
            "\1\uffff",
            "\1\30\3\uffff\1\31",
            "\2\6\3\uffff\1\36\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\35\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\32\1\26",
            "\2\6\3\uffff\1\34\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\33\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\37\1\26",
            "\2\6\3\uffff\1\36\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\35\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\40\1\26",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\37\1\26",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\40\1\26"
    };

    static final short[] DFA78_eot = DFA.unpackEncodedString(DFA78_eotS);
    static final short[] DFA78_eof = DFA.unpackEncodedString(DFA78_eofS);
    static final char[] DFA78_min = DFA.unpackEncodedStringToUnsignedChars(DFA78_minS);
    static final char[] DFA78_max = DFA.unpackEncodedStringToUnsignedChars(DFA78_maxS);
    static final short[] DFA78_accept = DFA.unpackEncodedString(DFA78_acceptS);
    static final short[] DFA78_special = DFA.unpackEncodedString(DFA78_specialS);
    static final short[][] DFA78_transition;

    static {
        int numStates = DFA78_transitionS.length;
        DFA78_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA78_transition[i] = DFA.unpackEncodedString(DFA78_transitionS[i]);
        }
    }

    class DFA78 extends DFA {

        public DFA78(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 78;
            this.eot = DFA78_eot;
            this.eof = DFA78_eof;
            this.min = DFA78_min;
            this.max = DFA78_max;
            this.accept = DFA78_accept;
            this.special = DFA78_special;
            this.transition = DFA78_transition;
        }
        public String getDescription() {
            return "()* loopback of 363:5: (e2= program_code | e5= preprocessor_directive )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA78_23 = input.LA(1);

                         
                        int index78_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred87_CPreprocessorLL()) ) {s = 6;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index78_23);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 78, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA79_eotS =
        "\5\uffff";
    static final String DFA79_eofS =
        "\5\uffff";
    static final String DFA79_minS =
        "\1\4\2\16\2\uffff";
    static final String DFA79_maxS =
        "\1\4\2\115\2\uffff";
    static final String DFA79_acceptS =
        "\3\uffff\1\2\1\1";
    static final String DFA79_specialS =
        "\5\uffff}>";
    static final String[] DFA79_transitionS = {
            "\1\1",
            "\1\4\2\uffff\1\4\2\3\71\uffff\1\2",
            "\1\4\2\uffff\1\4\2\3\71\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA79_eot = DFA.unpackEncodedString(DFA79_eotS);
    static final short[] DFA79_eof = DFA.unpackEncodedString(DFA79_eofS);
    static final char[] DFA79_min = DFA.unpackEncodedStringToUnsignedChars(DFA79_minS);
    static final char[] DFA79_max = DFA.unpackEncodedStringToUnsignedChars(DFA79_maxS);
    static final short[] DFA79_accept = DFA.unpackEncodedString(DFA79_acceptS);
    static final short[] DFA79_special = DFA.unpackEncodedString(DFA79_specialS);
    static final short[][] DFA79_transition;

    static {
        int numStates = DFA79_transitionS.length;
        DFA79_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA79_transition[i] = DFA.unpackEncodedString(DFA79_transitionS[i]);
        }
    }

    class DFA79 extends DFA {

        public DFA79(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 79;
            this.eot = DFA79_eot;
            this.eof = DFA79_eof;
            this.min = DFA79_min;
            this.max = DFA79_max;
            this.accept = DFA79_accept;
            this.special = DFA79_special;
            this.transition = DFA79_transition;
        }
        public String getDescription() {
            return "()* loopback of 366:4: (e3= elif_part )*";
        }
    }
    static final String DFA80_eotS =
        "\5\uffff";
    static final String DFA80_eofS =
        "\5\uffff";
    static final String DFA80_minS =
        "\1\4\2\22\2\uffff";
    static final String DFA80_maxS =
        "\1\4\2\115\2\uffff";
    static final String DFA80_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA80_specialS =
        "\5\uffff}>";
    static final String[] DFA80_transitionS = {
            "\1\1",
            "\1\3\1\4\71\uffff\1\2",
            "\1\3\1\4\71\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA80_eot = DFA.unpackEncodedString(DFA80_eotS);
    static final short[] DFA80_eof = DFA.unpackEncodedString(DFA80_eofS);
    static final char[] DFA80_min = DFA.unpackEncodedStringToUnsignedChars(DFA80_minS);
    static final char[] DFA80_max = DFA.unpackEncodedStringToUnsignedChars(DFA80_maxS);
    static final short[] DFA80_accept = DFA.unpackEncodedString(DFA80_acceptS);
    static final short[] DFA80_special = DFA.unpackEncodedString(DFA80_specialS);
    static final short[][] DFA80_transition;

    static {
        int numStates = DFA80_transitionS.length;
        DFA80_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA80_transition[i] = DFA.unpackEncodedString(DFA80_transitionS[i]);
        }
    }

    class DFA80 extends DFA {

        public DFA80(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 80;
            this.eot = DFA80_eot;
            this.eof = DFA80_eof;
            this.min = DFA80_min;
            this.max = DFA80_max;
            this.accept = DFA80_accept;
            this.special = DFA80_special;
            this.transition = DFA80_transition;
        }
        public String getDescription() {
            return "367:4: (e4= else_part )?";
        }
    }
    static final String DFA91_eotS =
        "\6\uffff";
    static final String DFA91_eofS =
        "\6\uffff";
    static final String DFA91_minS =
        "\1\4\2\16\3\uffff";
    static final String DFA91_maxS =
        "\1\4\2\115\3\uffff";
    static final String DFA91_acceptS =
        "\3\uffff\1\1\1\2\1\3";
    static final String DFA91_specialS =
        "\6\uffff}>";
    static final String[] DFA91_transitionS = {
            "\1\1",
            "\1\3\1\4\1\5\74\uffff\1\2",
            "\1\3\1\4\1\5\74\uffff\1\2",
            "",
            "",
            ""
    };

    static final short[] DFA91_eot = DFA.unpackEncodedString(DFA91_eotS);
    static final short[] DFA91_eof = DFA.unpackEncodedString(DFA91_eofS);
    static final char[] DFA91_min = DFA.unpackEncodedStringToUnsignedChars(DFA91_minS);
    static final char[] DFA91_max = DFA.unpackEncodedStringToUnsignedChars(DFA91_maxS);
    static final short[] DFA91_accept = DFA.unpackEncodedString(DFA91_acceptS);
    static final short[] DFA91_special = DFA.unpackEncodedString(DFA91_specialS);
    static final short[][] DFA91_transition;

    static {
        int numStates = DFA91_transitionS.length;
        DFA91_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA91_transition[i] = DFA.unpackEncodedString(DFA91_transitionS[i]);
        }
    }

    class DFA91 extends DFA {

        public DFA91(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 91;
            this.eot = DFA91_eot;
            this.eof = DFA91_eof;
            this.min = DFA91_min;
            this.max = DFA91_max;
            this.accept = DFA91_accept;
            this.special = DFA91_special;
            this.transition = DFA91_transition;
        }
        public String getDescription() {
            return "371:1: ifline returns [IfLine value] : ( (e0= HASH ( ( WS )* ) IF ( ( WS )+ ) e1= const_expr ) | (h1= HASH ( ( WS )* ) IFDEF ( ( WS )+ ) e2= ID ) | (h2= HASH ( ( WS )* ) IFNDEF ( ( WS )+ ) e3= ID ) );";
        }
    }
    static final String DFA102_eotS =
        "\5\uffff";
    static final String DFA102_eofS =
        "\5\uffff";
    static final String DFA102_minS =
        "\1\4\2\16\2\uffff";
    static final String DFA102_maxS =
        "\1\4\2\115\2\uffff";
    static final String DFA102_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA102_specialS =
        "\5\uffff}>";
    static final String[] DFA102_transitionS = {
            "\1\1",
            "\1\3\2\uffff\1\4\73\uffff\1\2",
            "\1\3\2\uffff\1\4\73\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA102_eot = DFA.unpackEncodedString(DFA102_eotS);
    static final short[] DFA102_eof = DFA.unpackEncodedString(DFA102_eofS);
    static final char[] DFA102_min = DFA.unpackEncodedStringToUnsignedChars(DFA102_minS);
    static final char[] DFA102_max = DFA.unpackEncodedStringToUnsignedChars(DFA102_maxS);
    static final short[] DFA102_accept = DFA.unpackEncodedString(DFA102_acceptS);
    static final short[] DFA102_special = DFA.unpackEncodedString(DFA102_specialS);
    static final short[][] DFA102_transition;

    static {
        int numStates = DFA102_transitionS.length;
        DFA102_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA102_transition[i] = DFA.unpackEncodedString(DFA102_transitionS[i]);
        }
    }

    class DFA102 extends DFA {

        public DFA102(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 102;
            this.eot = DFA102_eot;
            this.eof = DFA102_eof;
            this.min = DFA102_min;
            this.max = DFA102_max;
            this.accept = DFA102_accept;
            this.special = DFA102_special;
            this.transition = DFA102_transition;
        }
        public String getDescription() {
            return "376:1: elif returns [Elif value] : ( (e1= HASH ( ( WS )* ) IF ( ( WS )+ ) (not= NOT )? ( ( WS )* ) def= DEFINED ( ( WS )* ) ( ( LPAREN ( ( WS )* ) id1= ID ( ( WS )* ) RPAREN ) | (id2= ID ) ) ) | e2= HASH ( ( WS )* ) ELIF ( ( WS )+ ) e3= const_expr );";
        }
    }
    static final String DFA105_eotS =
        "\41\uffff";
    static final String DFA105_eofS =
        "\1\2\40\uffff";
    static final String DFA105_minS =
        "\1\4\1\6\2\uffff\1\6\1\115\1\uffff\3\10\1\33\1\10\2\33\1\115\1"+
        "\27\1\33\1\115\1\27\1\115\2\27\1\114\1\0\1\115\10\27";
    static final String DFA105_maxS =
        "\1\123\1\116\2\uffff\1\116\1\115\1\uffff\3\123\1\121\1\123\3\121"+
        "\1\116\2\121\1\116\1\121\3\116\1\0\1\121\10\116";
    static final String DFA105_acceptS =
        "\2\uffff\1\3\1\1\2\uffff\1\2\32\uffff";
    static final String DFA105_specialS =
        "\27\uffff\1\0\11\uffff}>";
    static final String[] DFA105_transitionS = {
            "\1\1\1\uffff\6\3\1\uffff\2\3\3\uffff\1\3\1\uffff\56\3\1\uffff"+
            "\2\3\1\uffff\1\3\2\uffff\1\3\1\uffff\5\3\1\uffff\3\3",
            "\2\6\1\uffff\5\6\1\5\2\6\3\2\70\uffff\1\6\1\4\1\6",
            "",
            "",
            "\2\6\1\uffff\5\6\1\5\2\6\3\2\70\uffff\1\6\1\4\1\6",
            "\1\7",
            "",
            "\1\12\16\uffff\2\6\2\uffff\1\6\7\uffff\1\10\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\11\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\14\16\uffff\2\6\2\uffff\1\6\7\uffff\1\6\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\13\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\12\16\uffff\2\6\2\uffff\1\6\7\uffff\1\10\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\11\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\16\61\uffff\1\15\3\uffff\1\17",
            "\1\14\16\uffff\2\6\2\uffff\1\6\7\uffff\1\6\26\uffff\1\6\10"+
            "\uffff\2\6\1\uffff\1\6\4\uffff\1\6\1\uffff\1\13\3\uffff\1\6"+
            "\1\uffff\1\6",
            "\1\21\61\uffff\1\20\3\uffff\1\22",
            "\1\16\61\uffff\1\15\3\uffff\1\17",
            "\1\23\3\uffff\1\24",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\25\1\26",
            "\1\21\61\uffff\1\20\3\uffff\1\22",
            "\1\30\3\uffff\1\31",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\32\1\26",
            "\1\23\3\uffff\1\24",
            "\2\6\3\uffff\1\34\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\33\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\25\1\26",
            "\1\27\1\6\1\26",
            "\1\uffff",
            "\1\30\3\uffff\1\31",
            "\2\6\3\uffff\1\36\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\35\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\32\1\26",
            "\2\6\3\uffff\1\34\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\33\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\37\1\26",
            "\2\6\3\uffff\1\36\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\6\1\35\1\6",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\40\1\26",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\37\1\26",
            "\2\6\3\uffff\1\6\4\uffff\2\6\1\uffff\2\6\1\uffff\1\6\14\uffff"+
            "\6\6\1\uffff\6\6\13\uffff\1\27\1\40\1\26"
    };

    static final short[] DFA105_eot = DFA.unpackEncodedString(DFA105_eotS);
    static final short[] DFA105_eof = DFA.unpackEncodedString(DFA105_eofS);
    static final char[] DFA105_min = DFA.unpackEncodedStringToUnsignedChars(DFA105_minS);
    static final char[] DFA105_max = DFA.unpackEncodedStringToUnsignedChars(DFA105_maxS);
    static final short[] DFA105_accept = DFA.unpackEncodedString(DFA105_acceptS);
    static final short[] DFA105_special = DFA.unpackEncodedString(DFA105_specialS);
    static final short[][] DFA105_transition;

    static {
        int numStates = DFA105_transitionS.length;
        DFA105_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA105_transition[i] = DFA.unpackEncodedString(DFA105_transitionS[i]);
        }
    }

    class DFA105 extends DFA {

        public DFA105(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 105;
            this.eot = DFA105_eot;
            this.eof = DFA105_eof;
            this.min = DFA105_min;
            this.max = DFA105_max;
            this.accept = DFA105_accept;
            this.special = DFA105_special;
            this.transition = DFA105_transition;
        }
        public String getDescription() {
            return "()* loopback of 385:5: (e2= program_code | e3= preprocessor_directive )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA105_23 = input.LA(1);

                         
                        int index105_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred116_CPreprocessorLL()) ) {s = 6;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index105_23);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 105, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA109_eotS =
        "\6\uffff";
    static final String DFA109_eofS =
        "\1\2\5\uffff";
    static final String DFA109_minS =
        "\1\4\1\6\2\uffff\1\6\1\uffff";
    static final String DFA109_maxS =
        "\1\123\1\116\2\uffff\1\116\1\uffff";
    static final String DFA109_acceptS =
        "\2\uffff\1\3\1\1\1\uffff\1\2";
    static final String DFA109_specialS =
        "\6\uffff}>";
    static final String[] DFA109_transitionS = {
            "\1\1\1\uffff\6\3\1\uffff\2\3\3\uffff\1\3\1\uffff\56\3\1\uffff"+
            "\2\3\1\uffff\1\3\2\uffff\1\3\1\uffff\5\3\1\uffff\3\3",
            "\2\5\1\uffff\10\5\2\uffff\1\2\70\uffff\1\5\1\4\1\5",
            "",
            "",
            "\2\5\1\uffff\10\5\2\uffff\1\2\70\uffff\1\5\1\4\1\5",
            ""
    };

    static final short[] DFA109_eot = DFA.unpackEncodedString(DFA109_eotS);
    static final short[] DFA109_eof = DFA.unpackEncodedString(DFA109_eofS);
    static final char[] DFA109_min = DFA.unpackEncodedStringToUnsignedChars(DFA109_minS);
    static final char[] DFA109_max = DFA.unpackEncodedStringToUnsignedChars(DFA109_maxS);
    static final short[] DFA109_accept = DFA.unpackEncodedString(DFA109_acceptS);
    static final short[] DFA109_special = DFA.unpackEncodedString(DFA109_specialS);
    static final short[][] DFA109_transition;

    static {
        int numStates = DFA109_transitionS.length;
        DFA109_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA109_transition[i] = DFA.unpackEncodedString(DFA109_transitionS[i]);
        }
    }

    class DFA109 extends DFA {

        public DFA109(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 109;
            this.eot = DFA109_eot;
            this.eof = DFA109_eof;
            this.min = DFA109_min;
            this.max = DFA109_max;
            this.accept = DFA109_accept;
            this.special = DFA109_special;
            this.transition = DFA109_transition;
        }
        public String getDescription() {
            return "()* loopback of 391:8: (e1= program_code | e2= preprocessor_directive )*";
        }
    }
    static final String DFA187_eotS =
        "\11\uffff";
    static final String DFA187_eofS =
        "\1\uffff\1\10\4\uffff\1\10\2\uffff";
    static final String DFA187_minS =
        "\1\10\1\25\1\10\3\uffff\1\25\2\uffff";
    static final String DFA187_maxS =
        "\1\123\1\116\1\121\3\uffff\1\116\2\uffff";
    static final String DFA187_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\uffff\1\1\1\2";
    static final String DFA187_specialS =
        "\11\uffff}>";
    static final String[] DFA187_transitionS = {
            "\1\5\22\uffff\1\2\7\uffff\1\5\37\uffff\2\3\1\uffff\1\3\4\uffff"+
            "\1\3\1\uffff\1\5\3\uffff\1\1\1\uffff\1\4",
            "\1\10\1\uffff\3\10\1\uffff\1\7\1\10\4\uffff\2\10\1\uffff\2"+
            "\10\1\uffff\1\10\14\uffff\6\10\1\uffff\6\10\13\uffff\1\10\1"+
            "\6\1\10",
            "\1\5\22\uffff\1\2\7\uffff\1\5\51\uffff\1\5\3\uffff\1\10",
            "",
            "",
            "",
            "\1\10\1\uffff\3\10\1\uffff\1\7\1\10\4\uffff\2\10\1\uffff\2"+
            "\10\1\uffff\1\10\14\uffff\6\10\1\uffff\6\10\13\uffff\1\10\1"+
            "\6\1\10",
            "",
            ""
    };

    static final short[] DFA187_eot = DFA.unpackEncodedString(DFA187_eotS);
    static final short[] DFA187_eof = DFA.unpackEncodedString(DFA187_eofS);
    static final char[] DFA187_min = DFA.unpackEncodedStringToUnsignedChars(DFA187_minS);
    static final char[] DFA187_max = DFA.unpackEncodedStringToUnsignedChars(DFA187_maxS);
    static final short[] DFA187_accept = DFA.unpackEncodedString(DFA187_acceptS);
    static final short[] DFA187_special = DFA.unpackEncodedString(DFA187_specialS);
    static final short[][] DFA187_transition;

    static {
        int numStates = DFA187_transitionS.length;
        DFA187_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA187_transition[i] = DFA.unpackEncodedString(DFA187_transitionS[i]);
        }
    }

    class DFA187 extends DFA {

        public DFA187(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 187;
            this.eot = DFA187_eot;
            this.eof = DFA187_eof;
            this.min = DFA187_min;
            this.max = DFA187_max;
            this.accept = DFA187_accept;
            this.special = DFA187_special;
            this.transition = DFA187_transition;
        }
        public String getDescription() {
            return "497:1: primary_expr returns [PrimaryExpr value] : (fc= func_call_expr | ( LPAREN )* e= ID ( RPAREN )* | e1= constant | e2= STRING_LITERAL | d1= defined_expr );";
        }
    }
 

    public static final BitSet FOLLOW_preprocessor_directive_in_translation_unit1102 = new BitSet(new long[]{0xFFFFFFFFFFF46FD2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_program_code_in_translation_unit1113 = new BitSet(new long[]{0xFFFFFFFFFFF46FD2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_artifact_include_in_preprocessor_directive1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_definition_in_preprocessor_directive1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_line_in_preprocessor_directive1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pp_error_in_preprocessor_directive1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pp_warning_in_preprocessor_directive1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pp_pragma_in_preprocessor_directive1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_preprocessor_directive1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_directive_in_preprocessor_directive1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_artifact_include1235 = new BitSet(new long[]{0x0000000000000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1238 = new BitSet(new long[]{0x0000000000000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_INCLUDE_in_artifact_include1242 = new BitSet(new long[]{0x0010000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1246 = new BitSet(new long[]{0x0010000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LESSER_THAN_in_artifact_include1254 = new BitSet(new long[]{0x0000000000000000L,0x0000000000062000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000062000L});
    public static final BitSet FOLLOW_filename_lib_in_artifact_include1265 = new BitSet(new long[]{0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1269 = new BitSet(new long[]{0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_GREATER_THAN_in_artifact_include1281 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_artifact_include1289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_artifact_include1297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_artifact_include1305 = new BitSet(new long[]{0x0000000000000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1308 = new BitSet(new long[]{0x0000000000000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_INCLUDE_in_artifact_include1312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000082000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1316 = new BitSet(new long[]{0x0000000000000000L,0x0000000000082000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_artifact_include1322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1326 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_artifact_include1339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_artifact_include1343 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_artifact_include1347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_definition1361 = new BitSet(new long[]{0x0000000000000080L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1364 = new BitSet(new long[]{0x0000000000000080L,0x0000000000002000L});
    public static final BitSet FOLLOW_DEFINE_in_definition1368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_definition1378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1393 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AEA5BL});
    public static final BitSet FOLLOW_token_sequence_in_definition1399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_definition1417 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1421 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_definition1425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_definition1431 = new BitSet(new long[]{0x0000000000000080L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1435 = new BitSet(new long[]{0x0000000000000080L,0x0000000000002000L});
    public static final BitSet FOLLOW_DEFINE_in_definition1439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1443 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_definition1449 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_LPAREN_in_definition1451 = new BitSet(new long[]{0x0000000010000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_WS_in_definition1455 = new BitSet(new long[]{0x0000000010000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_definition1471 = new BitSet(new long[]{0x0000000010200000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1477 = new BitSet(new long[]{0x0000000010200000L,0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_definition1482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_WS_in_definition1486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_definition1492 = new BitSet(new long[]{0x0000000010200000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1502 = new BitSet(new long[]{0x0000000010000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_definition1506 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1517 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AEA5BL});
    public static final BitSet FOLLOW_token_sequence_in_definition1524 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_definition1537 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1541 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_definition1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_definition1554 = new BitSet(new long[]{0x0000000000000200L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1558 = new BitSet(new long[]{0x0000000000000200L,0x0000000000002000L});
    public static final BitSet FOLLOW_UNDEF_in_definition1562 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_definition1566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_definition1572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_definition1590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_definition1594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_definition1598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_line1613 = new BitSet(new long[]{0x0000000000000400L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_line1617 = new BitSet(new long[]{0x0000000000000400L,0x0000000000002000L});
    public static final BitSet FOLLOW_LINE_in_line1621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_line1625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002008L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_line1631 = new BitSet(new long[]{0x0000000000000000L,0x0000000000087000L});
    public static final BitSet FOLLOW_WS_in_line1635 = new BitSet(new long[]{0x0000000000000000L,0x0000000000087000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_line1644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_line1652 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_line1656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_line1660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_line1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_pp_pragma1679 = new BitSet(new long[]{0x0000000000002000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_pp_pragma1682 = new BitSet(new long[]{0x0000000000002000L,0x0000000000002000L});
    public static final BitSet FOLLOW_PRAGMA_in_pp_pragma1686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_pp_pragma1690 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AFA5BL});
    public static final BitSet FOLLOW_token_sequence_in_pp_pragma1698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_pragma1706 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_pragma1713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_pp_pragma1717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_pragma1721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_pp_pragma1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_pp_error1739 = new BitSet(new long[]{0x0000000000000800L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_pp_error1742 = new BitSet(new long[]{0x0000000000000800L,0x0000000000002000L});
    public static final BitSet FOLLOW_ERROR_in_pp_error1746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_pp_error1749 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AFA5BL});
    public static final BitSet FOLLOW_token_sequence_in_pp_error1758 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_error1766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_error1773 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_pp_error1777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_error1781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_pp_error1785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_pp_warning1801 = new BitSet(new long[]{0x0000000000001000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_pp_warning1804 = new BitSet(new long[]{0x0000000000001000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WARNING_in_pp_warning1808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_pp_warning1811 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AFA5BL});
    public static final BitSet FOLLOW_token_sequence_in_pp_warning1820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_warning1828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_pp_warning1832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_pp_warning1836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_pp_warning1840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_null_directive1857 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_null_directive1860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_null_directive1868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_null_directive1872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_null_directive1876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_null_directive1880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifline_in_conditional1898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_conditional1903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_conditional1907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_conditional1911 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_conditional1915 = new BitSet(new long[]{0xFFFFFFFFFFF46FD0L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_program_code_in_conditional1925 = new BitSet(new long[]{0xFFFFFFFFFFF46FD0L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_preprocessor_directive_in_conditional1937 = new BitSet(new long[]{0xFFFFFFFFFFF46FD0L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_elif_part_in_conditional1956 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_else_part_in_conditional1969 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_conditional1986 = new BitSet(new long[]{0x0000000000080000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_conditional1989 = new BitSet(new long[]{0x0000000000080000L,0x0000000000002000L});
    public static final BitSet FOLLOW_ENDIF_in_conditional1993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_conditional1996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_conditional2000 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_conditional2004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_NEWLINE_in_conditional2008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_ifline2031 = new BitSet(new long[]{0x0000000000004000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_ifline2035 = new BitSet(new long[]{0x0000000000004000L,0x0000000000002000L});
    public static final BitSet FOLLOW_IF_in_ifline2039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_ifline2042 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_ifline2048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_ifline2060 = new BitSet(new long[]{0x0000000000008000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_ifline2063 = new BitSet(new long[]{0x0000000000008000L,0x0000000000002000L});
    public static final BitSet FOLLOW_IFDEF_in_ifline2067 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_ifline2071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_ifline2077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_ifline2088 = new BitSet(new long[]{0x0000000000010000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_ifline2091 = new BitSet(new long[]{0x0000000000010000L,0x0000000000002000L});
    public static final BitSet FOLLOW_IFNDEF_in_ifline2095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_ifline2098 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_ifline2104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_elif2124 = new BitSet(new long[]{0x0000000000004000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_elif2128 = new BitSet(new long[]{0x0000000000004000L,0x0000000000002000L});
    public static final BitSet FOLLOW_IF_in_elif2132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_elif2136 = new BitSet(new long[]{0x0000000800000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_NOT_in_elif2143 = new BitSet(new long[]{0x0000000000000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_elif2148 = new BitSet(new long[]{0x0000000000000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_DEFINED_in_elif2154 = new BitSet(new long[]{0x0000000008000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_WS_in_elif2157 = new BitSet(new long[]{0x0000000008000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_LPAREN_in_elif2168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_WS_in_elif2171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_elif2177 = new BitSet(new long[]{0x0000000010000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_elif2180 = new BitSet(new long[]{0x0000000010000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_elif2184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_elif2191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_elif2215 = new BitSet(new long[]{0x0000000000020000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_elif2218 = new BitSet(new long[]{0x0000000000020000L,0x0000000000002000L});
    public static final BitSet FOLLOW_ELIF_in_elif2222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_elif2225 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_elif2231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elif_in_elif_part2251 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_elif_part2256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_elif_part2260 = new BitSet(new long[]{0x0000000000000000L,0x0000000000005000L});
    public static final BitSet FOLLOW_NEWLINE_in_elif_part2263 = new BitSet(new long[]{0xFFFFFFFFFFF46FD0L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_program_code_in_elif_part2272 = new BitSet(new long[]{0xFFFFFFFFFFF46FD2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_preprocessor_directive_in_elif_part2283 = new BitSet(new long[]{0xFFFFFFFFFFF46FD2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_HASH_in_else_part2310 = new BitSet(new long[]{0x0000000000040000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_else_part2313 = new BitSet(new long[]{0x0000000000040000L,0x0000000000002000L});
    public static final BitSet FOLLOW_ELSE_in_else_part2317 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_WS_in_else_part2320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007000L});
    public static final BitSet FOLLOW_COMMENT_in_else_part2324 = new BitSet(new long[]{0x0000000000000000L,0x0000000000005000L});
    public static final BitSet FOLLOW_NEWLINE_in_else_part2327 = new BitSet(new long[]{0xFFFFFFFFFFF46FD0L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_program_code_in_else_part2341 = new BitSet(new long[]{0xFFFFFFFFFFF46FD2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_preprocessor_directive_in_else_part2355 = new BitSet(new long[]{0xFFFFFFFFFFF46FD2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_set_in_token_sequence2387 = new BitSet(new long[]{0xFFFFFFFFFFF46FF2L,0x00000000000AEA5BL});
    public static final BitSet FOLLOW_set_in_program_code2696 = new BitSet(new long[]{0xFFFFFFFFFFF46FC2L,0x00000000000EFA5BL});
    public static final BitSet FOLLOW_set_in_filename_lib3006 = new BitSet(new long[]{0x8000000004000002L});
    public static final BitSet FOLLOW_DOT_in_filename_lib3018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ID_in_filename_lib3022 = new BitSet(new long[]{0x8000000004000002L});
    public static final BitSet FOLLOW_DIVIDE_in_filename_lib3032 = new BitSet(new long[]{0x0000000000000000L,0x0000000000060000L});
    public static final BitSet FOLLOW_set_in_filename_lib3036 = new BitSet(new long[]{0x8000000004000002L});
    public static final BitSet FOLLOW_DOT_in_filename_lib3048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ID_in_filename_lib3052 = new BitSet(new long[]{0x8000000004000002L});
    public static final BitSet FOLLOW_LPAREN_in_const_expr3077 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_conditional_expression_in_const_expr3082 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_const_expr3086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_conditional_expression3101 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_logical_or_expr_in_conditional_expression3106 = new BitSet(new long[]{0x0000000410000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_conditional_expression3113 = new BitSet(new long[]{0x0000000410000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_QUESTION_in_conditional_expression3118 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_conditional_expression3121 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_conditional_expression3128 = new BitSet(new long[]{0x0000000002000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_conditional_expression3137 = new BitSet(new long[]{0x0000000002000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_conditional_expression3141 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_conditional_expression3144 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_conditional_expression_in_conditional_expression3151 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_conditional_expression3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_logical_or_expr3172 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_logical_and_expr_in_logical_or_expr3177 = new BitSet(new long[]{0x1000000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_logical_or_expr3185 = new BitSet(new long[]{0x1000000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_OR_in_logical_or_expr3190 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_logical_or_expr3193 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_logical_and_expr_in_logical_or_expr3199 = new BitSet(new long[]{0x1000000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_logical_or_expr3205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_logical_and_expr3220 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_inclusive_or_expr_in_logical_and_expr3225 = new BitSet(new long[]{0x2000000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_logical_and_expr3234 = new BitSet(new long[]{0x2000000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_AND_in_logical_and_expr3239 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_logical_and_expr3242 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_inclusive_or_expr_in_logical_and_expr3248 = new BitSet(new long[]{0x2000000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_logical_and_expr3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_inclusive_or_expr3269 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_exclusive_or_expr_in_inclusive_or_expr3274 = new BitSet(new long[]{0x0000000210000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_inclusive_or_expr3283 = new BitSet(new long[]{0x0000000210000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_PIPE_in_inclusive_or_expr3288 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_inclusive_or_expr3291 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_exclusive_or_expr_in_inclusive_or_expr3297 = new BitSet(new long[]{0x0000000210000002L});
    public static final BitSet FOLLOW_RPAREN_in_inclusive_or_expr3306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_exclusive_or_expr3321 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_and_expr_in_exclusive_or_expr3326 = new BitSet(new long[]{0x0800000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_exclusive_or_expr3334 = new BitSet(new long[]{0x0800000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_CARET_in_exclusive_or_expr3341 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_exclusive_or_expr3345 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_and_expr_in_exclusive_or_expr3351 = new BitSet(new long[]{0x0800000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_exclusive_or_expr3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_and_expr3372 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_equality_expr_in_and_expr3377 = new BitSet(new long[]{0x0000008010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_and_expr3386 = new BitSet(new long[]{0x0000008010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_AMPERSAND_in_and_expr3394 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_and_expr3398 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_equality_expr_in_and_expr3404 = new BitSet(new long[]{0x0000008010000002L});
    public static final BitSet FOLLOW_RPAREN_in_and_expr3410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_equality_expr3425 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_relational_expr_in_equality_expr3430 = new BitSet(new long[]{0x0000003010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_equality_expr3439 = new BitSet(new long[]{0x0000003010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_EQUALS_in_equality_expr3447 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_NOT_EQUALS_in_equality_expr3453 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_equality_expr3458 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_relational_expr_in_equality_expr3468 = new BitSet(new long[]{0x0000003010000002L});
    public static final BitSet FOLLOW_RPAREN_in_equality_expr3475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_relational_expr3490 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_shift_expr_in_relational_expr3495 = new BitSet(new long[]{0x00F0000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_relational_expr3504 = new BitSet(new long[]{0x00F0000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_LESSER_THAN_in_relational_expr3513 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_GREATER_THAN_in_relational_expr3519 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_LESSER_THAN_OR_EQUAL_TO_in_relational_expr3525 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_GREATER_THAN_OR_EQUAL_TO_in_relational_expr3531 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_relational_expr3535 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_shift_expr_in_relational_expr3544 = new BitSet(new long[]{0x00F0000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_relational_expr3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_shift_expr3565 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_additive_expr_in_shift_expr3570 = new BitSet(new long[]{0x0300000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_shift_expr3579 = new BitSet(new long[]{0x0300000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_LEFT_SHIFT_in_shift_expr3587 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_RIGHT_SHIFT_in_shift_expr3593 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_shift_expr3597 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_additive_expr_in_shift_expr3607 = new BitSet(new long[]{0x0300000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_shift_expr3613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_additive_expr3628 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr3633 = new BitSet(new long[]{0x0000000011800002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_additive_expr3641 = new BitSet(new long[]{0x0000000011800002L,0x0000000000002000L});
    public static final BitSet FOLLOW_PLUS_in_additive_expr3650 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_MINUS_in_additive_expr3657 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_additive_expr3661 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr3669 = new BitSet(new long[]{0x0000000011800002L});
    public static final BitSet FOLLOW_RPAREN_in_additive_expr3675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_multiplicative_expr3690 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_unary_expr_in_multiplicative_expr3695 = new BitSet(new long[]{0xC000000010000002L,0x0000000000002001L});
    public static final BitSet FOLLOW_WS_in_multiplicative_expr3703 = new BitSet(new long[]{0xC000000010000002L,0x0000000000002001L});
    public static final BitSet FOLLOW_STAR_in_multiplicative_expr3712 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_DIVIDE_in_multiplicative_expr3718 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_MODULO_in_multiplicative_expr3724 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_multiplicative_expr3729 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_unary_expr_in_multiplicative_expr3737 = new BitSet(new long[]{0xC000000010000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_multiplicative_expr3743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_unary_expr3758 = new BitSet(new long[]{0x0000000808000100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_primary_expr_in_unary_expr3763 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_unary_expr3768 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_PLUS_in_unary_expr3781 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_MINUS_in_unary_expr3793 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_TILDE_in_unary_expr3805 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_NOT_in_unary_expr3817 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_unary_expr3827 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_multiplicative_expr_in_unary_expr3833 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_unary_expr3837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_func_call_expr_in_primary_expr3855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary_expr3863 = new BitSet(new long[]{0x0000000008000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ID_in_primary_expr3868 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_RPAREN_in_primary_expr3872 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_constant_in_primary_expr3880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_primary_expr3889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defined_expr_in_primary_expr3902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_constant3920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OCTAL_LITERAL_in_constant3930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LITERAL_in_constant3940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_constant3950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_defined_expr3966 = new BitSet(new long[]{0x0000000808000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_NOT_in_defined_expr3971 = new BitSet(new long[]{0x0000000000000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_defined_expr3976 = new BitSet(new long[]{0x0000000000000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_DEFINED_in_defined_expr3982 = new BitSet(new long[]{0x0000000008000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_WS_in_defined_expr3985 = new BitSet(new long[]{0x0000000008000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_LPAREN_in_defined_expr3989 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_WS_in_defined_expr3993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000022000L});
    public static final BitSet FOLLOW_ID_in_defined_expr3999 = new BitSet(new long[]{0x0000000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_defined_expr4002 = new BitSet(new long[]{0x0000000010000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_defined_expr4006 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ID_in_func_call_expr4027 = new BitSet(new long[]{0x0000000008000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_func_call_expr4032 = new BitSet(new long[]{0x0000000008000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_func_call_expr4036 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_func_call_expr4040 = new BitSet(new long[]{0x0000000010200002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_func_call_expr4048 = new BitSet(new long[]{0x0000000010200002L,0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_func_call_expr4053 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_func_call_expr4057 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_func_call_expr4063 = new BitSet(new long[]{0x0000000010200002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_func_call_expr4068 = new BitSet(new long[]{0x0000000010200002L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_func_call_expr4074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred14_CPreprocessorLL1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred20_CPreprocessorLL1326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred21_CPreprocessorLL1335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred26_CPreprocessorLL1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred27_CPreprocessorLL1393 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AEA5BL});
    public static final BitSet FOLLOW_token_sequence_in_synpred27_CPreprocessorLL1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred28_CPreprocessorLL1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred34_CPreprocessorLL1455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred35_CPreprocessorLL1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred40_CPreprocessorLL1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred41_CPreprocessorLL1517 = new BitSet(new long[]{0xFFFFFFFFFFF46FF0L,0x00000000000AEA5BL});
    public static final BitSet FOLLOW_token_sequence_in_synpred41_CPreprocessorLL1524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred42_CPreprocessorLL1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred48_CPreprocessorLL1576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred49_CPreprocessorLL1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred54_CPreprocessorLL1635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred56_CPreprocessorLL1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred60_CPreprocessorLL1690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_token_sequence_in_synpred61_CPreprocessorLL1698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred62_CPreprocessorLL1706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred63_CPreprocessorLL1713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred67_CPreprocessorLL1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_token_sequence_in_synpred68_CPreprocessorLL1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred69_CPreprocessorLL1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred70_CPreprocessorLL1773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred74_CPreprocessorLL1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_token_sequence_in_synpred75_CPreprocessorLL1820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred76_CPreprocessorLL1828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred79_CPreprocessorLL1860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred80_CPreprocessorLL1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred83_CPreprocessorLL1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_preprocessor_directive_in_synpred87_CPreprocessorLL1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred91_CPreprocessorLL1996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred95_CPreprocessorLL2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred103_CPreprocessorLL2136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred112_CPreprocessorLL2225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_preprocessor_directive_in_synpred116_CPreprocessorLL2283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred188_CPreprocessorLL2387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred255_CPreprocessorLL2696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred261_CPreprocessorLL3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred262_CPreprocessorLL3086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred263_CPreprocessorLL3101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred264_CPreprocessorLL3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred265_CPreprocessorLL3121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred267_CPreprocessorLL3144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_synpred268_CPreprocessorLL3118 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred268_CPreprocessorLL3121 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_synpred268_CPreprocessorLL3128 = new BitSet(new long[]{0x0000000002000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_synpred268_CPreprocessorLL3137 = new BitSet(new long[]{0x0000000002000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_synpred268_CPreprocessorLL3141 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred268_CPreprocessorLL3144 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_conditional_expression_in_synpred268_CPreprocessorLL3151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred269_CPreprocessorLL3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred270_CPreprocessorLL3172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred271_CPreprocessorLL3185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred272_CPreprocessorLL3193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_synpred273_CPreprocessorLL3190 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred273_CPreprocessorLL3193 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_logical_and_expr_in_synpred273_CPreprocessorLL3199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred274_CPreprocessorLL3205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred275_CPreprocessorLL3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred276_CPreprocessorLL3234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred277_CPreprocessorLL3242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_synpred278_CPreprocessorLL3239 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred278_CPreprocessorLL3242 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_inclusive_or_expr_in_synpred278_CPreprocessorLL3248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred279_CPreprocessorLL3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred280_CPreprocessorLL3269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred281_CPreprocessorLL3283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred282_CPreprocessorLL3291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIPE_in_synpred283_CPreprocessorLL3288 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred283_CPreprocessorLL3291 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_exclusive_or_expr_in_synpred283_CPreprocessorLL3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred284_CPreprocessorLL3306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred285_CPreprocessorLL3321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred286_CPreprocessorLL3334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred287_CPreprocessorLL3345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARET_in_synpred288_CPreprocessorLL3341 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred288_CPreprocessorLL3345 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_and_expr_in_synpred288_CPreprocessorLL3351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred289_CPreprocessorLL3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred290_CPreprocessorLL3372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred291_CPreprocessorLL3386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred292_CPreprocessorLL3398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPERSAND_in_synpred293_CPreprocessorLL3394 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred293_CPreprocessorLL3398 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_equality_expr_in_synpred293_CPreprocessorLL3404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred294_CPreprocessorLL3410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred295_CPreprocessorLL3425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred296_CPreprocessorLL3439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred298_CPreprocessorLL3458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_synpred299_CPreprocessorLL3447 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_NOT_EQUALS_in_synpred299_CPreprocessorLL3453 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred299_CPreprocessorLL3458 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_relational_expr_in_synpred299_CPreprocessorLL3468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred300_CPreprocessorLL3475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred301_CPreprocessorLL3490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred302_CPreprocessorLL3504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred306_CPreprocessorLL3535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSER_THAN_in_synpred307_CPreprocessorLL3513 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_GREATER_THAN_in_synpred307_CPreprocessorLL3519 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_LESSER_THAN_OR_EQUAL_TO_in_synpred307_CPreprocessorLL3525 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_GREATER_THAN_OR_EQUAL_TO_in_synpred307_CPreprocessorLL3531 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred307_CPreprocessorLL3535 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_shift_expr_in_synpred307_CPreprocessorLL3544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred308_CPreprocessorLL3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred309_CPreprocessorLL3565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred310_CPreprocessorLL3579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred312_CPreprocessorLL3597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SHIFT_in_synpred313_CPreprocessorLL3587 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_RIGHT_SHIFT_in_synpred313_CPreprocessorLL3593 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred313_CPreprocessorLL3597 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_additive_expr_in_synpred313_CPreprocessorLL3607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred314_CPreprocessorLL3613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred315_CPreprocessorLL3628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred316_CPreprocessorLL3641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred318_CPreprocessorLL3661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_synpred319_CPreprocessorLL3650 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_MINUS_in_synpred319_CPreprocessorLL3657 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred319_CPreprocessorLL3661 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_multiplicative_expr_in_synpred319_CPreprocessorLL3669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred320_CPreprocessorLL3675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred321_CPreprocessorLL3690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred322_CPreprocessorLL3703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred325_CPreprocessorLL3729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_synpred326_CPreprocessorLL3712 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_DIVIDE_in_synpred326_CPreprocessorLL3718 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_MODULO_in_synpred326_CPreprocessorLL3724 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred326_CPreprocessorLL3729 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_unary_expr_in_synpred326_CPreprocessorLL3737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred327_CPreprocessorLL3743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred328_CPreprocessorLL3758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred329_CPreprocessorLL3768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred330_CPreprocessorLL3758 = new BitSet(new long[]{0x0000000808000100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_primary_expr_in_synpred330_CPreprocessorLL3763 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_synpred330_CPreprocessorLL3768 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_synpred334_CPreprocessorLL3827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred335_CPreprocessorLL3837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred338_CPreprocessorLL3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred348_CPreprocessorLL3985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred351_CPreprocessorLL4002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred352_CPreprocessorLL4006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred354_CPreprocessorLL4048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred355_CPreprocessorLL4057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_synpred356_CPreprocessorLL4068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred357_CPreprocessorLL4053 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_WS_in_synpred357_CPreprocessorLL4057 = new BitSet(new long[]{0x0400000809800100L,0x00000000000A2858L});
    public static final BitSet FOLLOW_const_expr_in_synpred357_CPreprocessorLL4063 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WS_in_synpred357_CPreprocessorLL4068 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred358_CPreprocessorLL4074 = new BitSet(new long[]{0x0000000000000002L});

}