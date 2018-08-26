grammar CPreprocessorLL;

options {
    backtrack=true;
    memoize=true;
}

@header{
package com.compilervision.compilers.clang.preprocessor;
import java.util.HashSet;
}

@lexer::header{ package com.compilervision.compilers.clang.preprocessor;} 

@lexer::members{
		public void skipIfRequired(){
			CurrentProductionSingleton cps = CurrentProductionSingleton.getInstance();
			if(!cps.wsRequired()) 
				skip();			
		} 
	} 

@members{		
		
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
       				else if("\%".equals(symbol))
        				id = MultiplicativeExpr.MODULO;
        			else
        				id = -1;
        		}
        		else
        			id = -1; // Invalid
        		
        		return id;
        	}		
}

HASH: '#';
DOUBLE_HASH: '##';
INCLUDE: 'include';
DEFINE: 'define';
DEFINED: 'defined';
UNDEF: 'undef';
LINE: 'line';
ERROR: 'error';
WARNING:'warning';
PRAGMA: 'pragma';
IF: 'if';
IFDEF: 'ifdef';
IFNDEF: 'ifndef';
ELIF: 'elif';
ELSE: 'else';
ENDIF: 'endif';
SEMICOLON :	';';
COMMA	:	',';
ASSIGN	:	'=';
PLUS	:	'+';
MINUS 	:	'-';
COLON	:	':';
DOT	:	'.';
LPAREN	:	'(';
RPAREN	:	')';
LCURLY	:	'{';
RCURLY	:	'}';
LBRACK	:	'[';
RBRACK	:	']';
PIPE 	:	'|';
QUESTION:   '?';
NOT	:	'!';
EQUALS	:	'==';
NOT_EQUALS :	'!=';
DEREFERENCE :	'->';
AMPERSAND : '&';
INCREMENT :	'++';
DECREMENT :	'--';
MULTIPLY_ASSIGN : '*=';
DIVIDE_ASSIGN :	'/=';
ADD_ASSIGN	: '+=';	
MINUS_ASSIGN	: '-=';
MODULO_ASSIGN	: '%=';
BITWISE_AND_ASSIGN : '&=';
BITWISE_XOR_ASSIGN : '^=';
BITWISE_OR_ASSIGN : '|=';	
LEFT_SHIFT_ASSIGN : '<<=';
RIGHT_SHIFT_ASSIGN : '>>=';
LESSER_THAN 	:	'<';
GREATER_THAN 	:	'>';
LESSER_THAN_OR_EQUAL_TO 	:	'<=';
GREATER_THAN_OR_EQUAL_TO 	:	'>=';
LEFT_SHIFT 	:	'<<';
RIGHT_SHIFT 	:	'>>';
TILDE	:	'~';
CARET : '^';
OR	:	'||';
AND	:	'&&';
STAR	:	'*';
DIVIDE	:	'/';
MODULO	:	'%';
ELLIPSES:	'...';
DECIMAL_LITERAL : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;
OCTAL_LITERAL : '0' ('0'..'7')+ IntegerTypeSuffix? ;
HEX_LITERAL : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;
fragment
 HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;
fragment
IntegerTypeSuffix
	:	('u'|'U')? ('l'|'L')
	|	('u'|'U')  ('l'|'L')?
	;
FLOATING_LITERAL
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ Exponent? FloatTypeSuffix
	;
fragment
Exponent :	 ('e'|'E') ('+'|'-')? ('0'..'9')+ ;
fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;
CHAR_LITERAL    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\'';    
NEWLINE	:	'\r'?'\n' ;
WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') ;
COMMENT :   '/*' ( options {greedy=false;} : . )* '*/' ;
LINE_COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' ;
ID	:	LETTER (LETTER|'0'..'9')* ;	
FILENAME : LETTER (LETTER|'0'..'9'|MINUS)* ;
STRING_LITERAL :	'"' ( EscapeSequence | ~('\\'|'"') )* '"' ;
fragment
EscapeSequence
    :   '\\' ('a'|'b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   OctalEscape ;	
fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ; 	
fragment
LETTER
	:	'$'
	|	'A'..'Z'
	|	'a'..'z'
	|	'_'
	;
	
translation_unit returns [PreprocessorSegments value] : 
		( e1=preprocessor_directive  { $value = addUnitToPreprocessorSegments($value, $e1.value);}
		| e2=program_code { $value = addUnitToPreprocessorSegments($value, $e2.value);})+
	;
preprocessor_directive returns [PreprocessorDirective value] : 
	  e1=artifact_include { $value = $e1.value;} 
	 | e2=definition { $value = $e2.value;} 
	 | e3=line { $value = $e3.value;} 
	 | e4=pp_error {  $value = $e4.value;} 
	 | e5=pp_warning {  $value = $e5.value;} 
	 | e6=pp_pragma { $value = $e6.value;} 
	 | e7=conditional {$value = $e7.value;} 
	 | e8= null_directive {$value = $e8.value;} 
	;
artifact_include returns [IncludeDirective value]:	
		 e0=HASH (WS*) INCLUDE  (WS*) 	
		LESSER_THAN  (WS*)  e1=filename_lib  (WS*) 
		{$value = new IncludeDirective($e0.line, $e1.value, IncludeDirective.LIB);} 
		GREATER_THAN  (WS*) COMMENT* (WS*) NEWLINE
	| 
		HASH (WS*) INCLUDE  (WS*) e2=STRING_LITERAL  (WS*)
		{$value = new IncludeDirective($e2.line, $e2.text, IncludeDirective.LOCAL);} (WS*) COMMENT* (WS*) NEWLINE
	;
definition returns [Definition value] :
	HASH (WS*) DEFINE  (WS+) e1=ID   {$value = new Definition($e1.line, $e1.text, Definition.ID_DEFINITION);} 			
			((WS+) e6=token_sequence)?  
			  {$value.setReplacementTokenSequence($e6.value);} (WS*) COMMENT* (WS*) NEWLINE
	| 	HASH  (WS*) DEFINE  (WS+) id1=ID LPAREN  (WS*) {$value = new Definition($id1.line, $id1.text, Definition.MACRO_DEFINITION);} 
	     (e2=ID {$value.addToMacroList($e2.text);}  (WS*) (COMMA  (WS*) e3=ID {$value.addToMacroList($e3.text);})*)?  (WS*) RPAREN
	      ((WS+)  e4=token_sequence)?   {$value.setReplacementTokenSequence($e4.value);} (WS*) COMMENT* (WS*) NEWLINE
    | (HASH  (WS*) UNDEF  (WS+) e5=ID  (WS*) {$value = new Definition($e5.line, $e5.text, Definition.UNDEFINITION);} ) 	(WS*) COMMENT* (WS*) NEWLINE
	;
line returns [Line value] :
	(HASH  (WS*) LINE  (WS+) e1=DECIMAL_LITERAL  (WS*) {$value = new Line($e1.line, $e1.text);} (e2=STRING_LITERAL{$value.setFileName($e2.text);})? ) (WS*) COMMENT* (WS*) NEWLINE
	;
pp_pragma returns [Pragma value] :
	(HASH (WS*) PRAGMA  (WS+) {$value = new Pragma();}(e1=token_sequence {$value.setTokenSequence($e1.value);})?  (WS*) ) (WS*) COMMENT* (WS*) NEWLINE
	;
pp_error returns [Error value]:
	(HASH (WS*) ERROR (WS+) {$value = new Error();} (e1=token_sequence {$value.setTokenSequence($e1.value);})?  (WS*) ) (WS*) COMMENT* (WS*) NEWLINE
	;
/* This is a GCC Extension of C language */
pp_warning returns [Warning value]:
	(HASH (WS*) WARNING (WS+) {$value = new Warning();} (e1=token_sequence {$value.setTokenSequence($e1.value);})?  (WS*) COMMENT* (WS*) NEWLINE) 
	;
null_directive returns [NullDirective value] :
	 HASH (WS*)  {$value = new NullDirective();} (WS*) COMMENT* (WS*) NEWLINE
	;
conditional returns [Conditional value] :
		(e1=ifline {$value = new Conditional($e1.value.getLineNum(), $e1.value);} (WS*) COMMENT* (WS*) NEWLINE
				( e2=program_code { $value.addPreprocessorUnit($e2.value);}
				| e5=preprocessor_directive  { $value.addPreprocessorUnit($e5.value);}	
				)*
			(e3=elif_part {$value.addElifPart($e3.value);})* 
			(e4=else_part {$value.setElsePart($e4.value);}
		 	 )? 
		endcond=HASH (WS*) ENDIF (WS*) COMMENT* (WS*) NEWLINE {$value.setEndConditionalLineNum($endcond.line);}) 
	;
ifline returns [IfLine value] :
	  (e0=HASH  (WS*) IF (WS+) e1=const_expr  { $value = new IfLine($e0.line, IfLine.CONST_EXPR, $e1.value);})
	| (h1=HASH (WS*) IFDEF  (WS+) e2=ID { $value = new IfLine($h1.line, IfLine.IFDEF, $e2.text);})
	| (h2=HASH (WS*) IFNDEF (WS+) e3=ID { $value = new IfLine($h2.line, IfLine.IFNDEF, $e3.text);})
	;
elif returns [Elif value] :
	(e1=HASH  (WS*) IF  (WS+) (not=NOT)? (WS*) def=DEFINED (WS*) 
				((LPAREN (WS*) id1=ID (WS*) RPAREN) |(id2=ID))  
				{$value = constructElifLine($e1.line, $not.text, $id1.text, $id2.text);})
				
	 | e2=HASH (WS*) ELIF (WS+) e3=const_expr  { $value = new Elif($e2.line, IfLine.CONST_EXPR, $e3.value);}
	;
elif_part returns [ElifPart value] :
	 e1=elif {$value = new ElifPart($e1.value.getLineNum(), $e1.value);} (WS*) COMMENT* NEWLINE
	 		(e2=program_code {$value.addPreprocessorUnit($e2.value);}
	 		|e3=preprocessor_directive {$value.addPreprocessorUnit($e3.value);}
	 		)*
	;
else_part returns [ElsePart value] :
	 	e0=HASH (WS*) ELSE (WS*) COMMENT* NEWLINE { $value = new ElsePart($e0.line);}
	 					(e1=program_code {$value.addPreprocessorUnit($e1.value);}
	 					|e2=preprocessor_directive {$value.addPreprocessorUnit($e2.value);}
	 					) *
	;
token_sequence returns [TokenSequence value]:	 
	 (e1=(IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN	
		| RPAREN | LCURLY | RCURLY	| LBRACK | RBRACK | PIPE | NOT | EQUALS	| NOT_EQUALS | DOUBLE_HASH | HASH | DEREFERENCE | AMPERSAND | INCREMENT
		| DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN 
		| BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN 
		| LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE	| CARET | OR | AND | STAR
		| DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL 	
		| WS | QUESTION | COMMENT | LINE_COMMENT  | ID	| STRING_LITERAL | INCLUDE | DEFINE
		| DEFINED |UNDEF | LINE | ERROR | PRAGMA){ $value = addToken($value, $e1.line, $e1.text);}
	  )+
	;
program_code returns [ProgramCode value]:
	 (e1=(IF | ELSE | SEMICOLON | COMMA | ASSIGN | PLUS | MINUS | COLON | DOT | LPAREN	
		| RPAREN | LCURLY | RCURLY	| LBRACK | RBRACK | PIPE | NOT | EQUALS	| NOT_EQUALS | DEREFERENCE | AMPERSAND | INCREMENT
		| DECREMENT | MULTIPLY_ASSIGN | DIVIDE_ASSIGN | ADD_ASSIGN | MINUS_ASSIGN | MODULO_ASSIGN | BITWISE_AND_ASSIGN 
		| BITWISE_XOR_ASSIGN | BITWISE_OR_ASSIGN | LEFT_SHIFT_ASSIGN | RIGHT_SHIFT_ASSIGN | LESSER_THAN | GREATER_THAN 
		| LESSER_THAN_OR_EQUAL_TO | GREATER_THAN_OR_EQUAL_TO | LEFT_SHIFT | RIGHT_SHIFT | TILDE	| CARET | OR | AND | STAR
		| DIVIDE | MODULO | ELLIPSES | DECIMAL_LITERAL | OCTAL_LITERAL | HEX_LITERAL | FLOATING_LITERAL | CHAR_LITERAL | NEWLINE	
		| WS | COMMENT | LINE_COMMENT  | QUESTION | ID	| STRING_LITERAL | INCLUDE | DEFINE
		| DEFINED |UNDEF | LINE | ERROR | PRAGMA | FILENAME) { $value = addToken($value, $e1.line, $e1.text);}
	 )+
		;
filename_lib returns [FileNameLib value] :
	  e=(ID|FILENAME) {$value = new FileNameLib($e.line, $e.text);} ((d1=DOT e1=ID){if($d1.text != null && $e1.text != null) $value.addToFileName("." +$e1.text);})*  (d=DIVIDE e3=(ID|FILENAME) {if($e3.text != null) $value.addToFileName("/" +$e3.text);} ((d2=DOT e2=ID){if($d2.text != null && $e2.text != null) $value.addToFileName("." +$e2.text);})*)*
	;
// TODO : Is it the first or the second? For now assume the second
//const_expr returns [ConstExpr value]
//	:	e1=conditional_expression {$value = addConditionalExpToConstExpr($e1.value, $value);} 
//		(COMMA e2=conditional_expression {$value = addConditionalExpToConstExpr($e2.value, $value);} )*
//	;
const_expr returns [ConstExpr value]
	:	LPAREN? e=conditional_expression {$value = new ConstExpr(); $value.setConditionalExpr($e.value); 
			setLineNumAndPos($value, $e.value.getLineNum(), $e.value.getPos());} RPAREN?
	;
conditional_expression returns [ConditionalExpr value]
	:	LPAREN? e1=logical_or_expr {$value = new ConditionalExpr(); $value.setLogicalOrExpr($e1.value); 
			setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());}
		(WS*) (QUESTION (WS*) e2= const_expr {$value.setTrueInConditional($e2.value);} 
			(WS*) COLON (WS*) e3= conditional_expression {$value.setFalseInConditional($e3.value);})? RPAREN?
	;
logical_or_expr returns [LogicalOrExpr value]
	:	LPAREN? e1=logical_and_expr {$value = new LogicalOrExpr(null, $e1.value); 
				setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		(WS*) (OR (WS*) e2=logical_and_expr {$value = new LogicalOrExpr($value, $e2.value);})* RPAREN?
	;
logical_and_expr returns [LogicalAndExpr value]
	:	LPAREN? e1=inclusive_or_expr {$value = new LogicalAndExpr(null, $e1.value); 
					setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		 (WS*) (AND (WS*) e2=inclusive_or_expr {$value = new LogicalAndExpr($value, $e2.value);})* RPAREN?
	;
inclusive_or_expr returns [InclusiveOrExpr value]
	:	LPAREN? e1=exclusive_or_expr {$value = new InclusiveOrExpr(null, $e1.value); setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());}  
		(WS*) (PIPE (WS*) e2=exclusive_or_expr 
		{$value = new InclusiveOrExpr($value, $e2.value);})* RPAREN?
	;
exclusive_or_expr returns [ExclusiveOrExpr value]
	:	LPAREN? e1=and_expr {$value = new ExclusiveOrExpr(null, $e1.value); setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		(WS*)((sign=CARET) (WS*) e2=and_expr {$value = new ExclusiveOrExpr($value, $e2.value);})* RPAREN?
	;
and_expr returns [AndExpr value]
	:	LPAREN? e1=equality_expr  {$value = new AndExpr(null, $e1.value); setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		(WS*) ((sign=AMPERSAND) (WS*) e2=equality_expr {$value = new AndExpr($value, $e2.value);})* RPAREN?
	;
equality_expr returns [EqualityExpr value]
	:	LPAREN? e1=relational_expr {$value = new EqualityExpr(null, $e1.value, -1); 
					setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		 (WS*) ((sign=EQUALS | sign=NOT_EQUALS ) (WS*)
		  e2=relational_expr {$value = new EqualityExpr($value, $e2.value, getIdForSymbol($sign.text, $value));} )* RPAREN?
	;
relational_expr returns [RelationalExpr value]
	:	LPAREN? e1=shift_expr 	{$value = new RelationalExpr(null, $e1.value, -1); 
					setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		(WS*) (( sign=LESSER_THAN | sign=GREATER_THAN | sign=LESSER_THAN_OR_EQUAL_TO | sign=GREATER_THAN_OR_EQUAL_TO) (WS*)
		 e2=shift_expr {$value = new RelationalExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})* RPAREN?
	;
shift_expr returns [ShiftExpr value]
	:	LPAREN? e1=additive_expr {$value = new ShiftExpr(null, $e1.value, -1); 
			setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		 (WS*) ((sign=LEFT_SHIFT | sign=RIGHT_SHIFT) (WS*)
		  e2=additive_expr {$value = new ShiftExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})* RPAREN?
	;
additive_expr returns [AdditiveExpr value]
	:	LPAREN? e1=multiplicative_expr {$value = new AdditiveExpr(null, $e1.value, -1); 
				setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		(WS*) (( sign=PLUS  | sign=MINUS) (WS*)
		e2=multiplicative_expr {$value = new AdditiveExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})* RPAREN?
	;
multiplicative_expr returns [MultiplicativeExpr value]
	:	LPAREN? e1=unary_expr {$value = new MultiplicativeExpr( $e1.value, null, -1);
			 setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());} 
		(WS*) (( sign=STAR | sign=DIVIDE | sign=MODULO ) (WS*)
		e2=unary_expr {$value = new MultiplicativeExpr($e2.value, $value, getIdForSymbol($sign.text, $value));})* RPAREN?
	;
unary_expr returns [UnaryExpr value]
	:	LPAREN? e=primary_expr {$value = new UnaryExpr(); $value.setPrimaryExpr($e.value); 
					setLineNumAndPos($value, $e.value.getLineNum(), $e.value.getPos());} (WS*) 
	|	( 	a1=PLUS  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.PLUS); setLineNumAndPos($value, $a1.line, $a1.pos);}
		| 	a2=MINUS  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.MINUS); setLineNumAndPos($value, $a2.line, $a2.pos);}
		| 	a3=TILDE  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.TILDE); setLineNumAndPos($value, $a3.line, $a3.pos);}
		| 	a4=NOT  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.NOT); setLineNumAndPos($value, $a4.line, $a4.pos);}
		) (WS*) e3=multiplicative_expr {$value.setMultiplicativeExpr($e3.value);} RPAREN?
	;
primary_expr returns [PrimaryExpr value] 
	:	fc=func_call_expr {$value = new PrimaryExpr($fc.value);} 
	|	LPAREN* e=ID {$value = new PrimaryExpr(PrimaryExpr.IDENTIFIER_TYPE); $value.setIdentifier($e.text); setLineNumAndPos($value, $e.line, $e.pos); } RPAREN*
	|	e1=constant {$value = new PrimaryExpr(PrimaryExpr.CONSTANT_TYPE); $value.setConstant($e1.value); setLineNumAndPos($value, $e1.value.getLineNum(), $e1.value.getPos());}
	|	e2=STRING_LITERAL {$value = new PrimaryExpr(PrimaryExpr.STRING_TYPE); $value.setString($e2.text); setLineNumAndPos($value, $e2.line, $e2.pos);}
	|   d1 = defined_expr {$value = new PrimaryExpr($d1.value);}
	;
constant returns [Constant value]
	:	a1=DECIMAL_LITERAL  {$value = new Constant(); $value.setIntConstant($DECIMAL_LITERAL.text); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	a2=OCTAL_LITERAL  {$value = new Constant(); $value.setIntConstant($OCTAL_LITERAL.text); setLineNumAndPos($value, $a2.line, $a2.pos);}
	|	a3=HEX_LITERAL  {$value = new Constant(); $value.setIntConstant($HEX_LITERAL.text); setLineNumAndPos($value, $a3.line, $a3.pos);}
	|	a5=CHAR_LITERAL {$value = new Constant(); $value.setCharConstant($CHAR_LITERAL.text); setLineNumAndPos($value, $a5.line, $a5.pos);}
	;
defined_expr returns [Defined value] :
	LPAREN*(not=NOT)? (WS*) def=DEFINED (WS*) LPAREN? (WS*) id1=ID (WS*) RPAREN*
	{$value = constructDefined($not.text, $id1.text);} 
	;
func_call_expr returns [FuncCallExpr value]
	: f=ID {$value = new FuncCallExpr($f.text);} (WS*) LPAREN e3=const_expr {$value.addParameters($e3.value);} 
		(WS*) (COMMA  (WS*) e4=const_expr {$value.addParameters($e4.value);} (WS*))* RPAREN? 
	;