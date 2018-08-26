/**
* This class is the abstraction for a scanner for the tiger language.
*/
package com.compilervision.compilers.clang.lexer;

import java_cup.runtime.*;
import com.compilervision.compilers.clang.abssyntree.*;

%%
%public
%class ClangLRLexer
%unicode
%cupsym sym
%cup
%line
%column
%function next_token
%type java_cup.runtime.Symbol
%{
	StringBuffer string = new StringBuffer();
	private Symbol symbol(int type)
	{
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
	
	private Double getDouble(String str)
	{
		return new Double(str);  // to be modified
	}
	
	private Integer getInt(String str)
	{
		return new Integer(str); // to be modified
	}
%}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

IntegerLiteral = (0 | [1-9][0-9]* | 0[1-7]+ | 0[xX][a-fA-F0-9]+) ([uU][lL]|[Ll][uU]|u|U|l|L)?
FloatingPointLiteral = ([0-9]*\.[0-9]+ ((e|E)(\+|-)?[0-9]+)?) ((f|F)(l|L)|(l|L)(f|F)|f|F|l|L)?
//FloatingPointLiteralSuff = {FloatingPointLiteral} | {FloatingPointLiteral} (f|F)(l|L)|(l|L)(f|F)|f|F|l|L
Identifier = [_a-zA-Z] [_a-zA-Z0-9]*
CharLiteral = '[A-Za-z0-9]' | '[~`!@#$%\^&\*()_\-\+={}\|\\\[\]:;\'<,>\.\?/]' | '\\[0-7]' | '\\[0-7][0-7]' | '\\[0-7][0-7][0-7]' | '\\(x|X)[0-9a-zA-Z]* | '\\[a|b|f|n|r|t|v|\\|\?|\'|\"]'
%state STRING
%state COMMENT

%%
<YYINITIAL> "auto" { return symbol(sym.AUTO); }
<YYINITIAL> "break" { return symbol(sym.BREAK); }
<YYINITIAL> "case" { return symbol(sym.CASE); }
<YYINITIAL> "char" { return symbol(sym.CHAR); }
<YYINITIAL> "const" { return symbol(sym.CONST); }
<YYINITIAL> "continue" { return symbol(sym.CONTINUE); }
<YYINITIAL> "default" { return symbol(sym.DEFAULT); }
<YYINITIAL> "do" { return symbol(sym.DO); }
<YYINITIAL> "double" { return symbol(sym.DOUBLE); }
<YYINITIAL> "else" { return symbol(sym.ELSE); }
<YYINITIAL> "enum" { return symbol(sym.ENUM); }
<YYINITIAL> "extern" { return symbol(sym.EXTERN); }
<YYINITIAL> "float" { return symbol(sym.FLOAT); }
<YYINITIAL> "for" { return symbol(sym.FOR); }
<YYINITIAL> "goto" { return symbol(sym.GOTO); }
<YYINITIAL> "if" { return symbol(sym.IF); }
<YYINITIAL> "int" { return symbol(sym.INT); }
<YYINITIAL> "long" { return symbol(sym.LONG); }
<YYINITIAL> "register" { return symbol(sym.REGISTER); }
<YYINITIAL> "return" { return symbol(sym.RETURN); }
<YYINITIAL> "short" { return symbol(sym.SHORT); }
<YYINITIAL> "signed" { return symbol(sym.SIGNED); }
<YYINITIAL> "sizeof" { return symbol(sym.SIZEOF); }
<YYINITIAL> "static" { return symbol(sym.STATIC); }
<YYINITIAL> "struct" { return symbol(sym.STRUCT); }
<YYINITIAL> "switch" { return symbol(sym.SWITCH); }
<YYINITIAL> "typedef" { return symbol(sym.TYPEDEF); }
<YYINITIAL> "union" { return symbol(sym.UNION); }
<YYINITIAL> "unsigned" { return symbol(sym.UNSIGNED); }
<YYINITIAL> "void" { return symbol(sym.VOID); }
<YYINITIAL> "volatile" { return symbol(sym.VOLATILE); }
<YYINITIAL> "while" { return symbol(sym.WHILE); }
<YYINITIAL> "asm" { return symbol(sym.ASM); }
<YYINITIAL> "{" { return symbol(sym.LBRACE); }
<YYINITIAL> "}" { return symbol(sym.RBRACE); }
<YYINITIAL> ":" { return symbol(sym.COLON); }
<YYINITIAL> "." { return symbol(sym.DOT); }
<YYINITIAL> "," { return symbol(sym.COMMA); }
<YYINITIAL> ";" { return symbol(sym.SEMICOLON); }
<YYINITIAL> "(" { return symbol(sym.LPAREN); }
<YYINITIAL> ")" { return symbol(sym.RPAREN); }
<YYINITIAL> "[" { return symbol(sym.LBRACK); }
<YYINITIAL> "]" { return symbol(sym.RBRACK); }
<YYINITIAL> "->" { return symbol(sym.POINTER_MEM_ACCESS); }
<YYINITIAL> "!" { return symbol(sym.NOT); }
<YYINITIAL> "~" { return symbol(sym.ONES_COMPLEMENT); }
<YYINITIAL> "++" { return symbol(sym.INCREMENT);}
<YYINITIAL> "--" { return symbol(sym.DECREMENT);}
<YYINITIAL> "+" { return symbol(sym.PLUS);}
<YYINITIAL> "-" { return symbol(sym.MINUS);}
<YYINITIAL> "*" { return symbol(sym.STAR);}
<YYINITIAL> "&" { return symbol(sym.BITWISE_AND);}
<YYINITIAL> "/" { return symbol(sym.DIVIDE);}
<YYINITIAL> "%" { return symbol(sym.MODULO);}
<YYINITIAL> "<<" { return symbol(sym.LEFT_SHIFT);}
<YYINITIAL> ">>" { return symbol(sym.RIGHT_SHIFT);}
<YYINITIAL> "<" { return symbol(sym.LT);}
<YYINITIAL> ">" { return symbol(sym.GT);}
<YYINITIAL> "<=" { return symbol(sym.LTE);}
<YYINITIAL> ">=" { return symbol(sym.GTE);}
<YYINITIAL> "==" { return symbol(sym.EQUALS);}
<YYINITIAL> "!=" { return symbol(sym.NOT_EQUALS);}
<YYINITIAL> "^" { return symbol(sym.BITWISE_XOR);}
<YYINITIAL> "|" { return symbol(sym.BITWISE_OR);}
<YYINITIAL> "&&" { return symbol(sym.AND);}
<YYINITIAL> "||" { return symbol(sym.OR);}
<YYINITIAL> "?" { return symbol(sym.QUESTION);}
<YYINITIAL> "=" { return symbol(sym.ASSIGN);}
<YYINITIAL> "+=" { return symbol(sym.ADD_ASSIGN);}
<YYINITIAL> "-=" { return symbol(sym.MINUS_ASSIGN);}
<YYINITIAL> "*=" { return symbol(sym.MULTIPLY_ASSIGN);}
<YYINITIAL> "/=" { return symbol(sym.DIVIDE_ASSIGN);}
<YYINITIAL> "%=" { return symbol(sym.MODULO_ASSIGN);}
<YYINITIAL> "&=" { return symbol(sym.BITWISE_AND_ASSIGN);}
<YYINITIAL> "^=" { return symbol(sym.BITWISE_XOR_ASSIGN);}
<YYINITIAL> "|=" { return symbol(sym.BITWISE_OR_ASSIGN);}
<YYINITIAL> "<<=" { return symbol(sym.LEFT_SHIFT_ASSIGN);}
<YYINITIAL> ">>=" { return symbol(sym.RIGHT_SHIFT_ASSIGN);}
<YYINITIAL> {Identifier}  { return symbol(sym.IDENTIFIER, yytext()); }
<YYINITIAL> {CharLiteral} {return symbol(sym.CHAR_LITERAL, yytext());}
<YYINITIAL> {FloatingPointLiteral} {return symbol(sym.FLOATING_POINT_LITERAL, getDouble(yytext()));}
<YYINITIAL> {IntegerLiteral} { return symbol(sym.INTEGER_LITERAL, getInt(yytext())) ; }
<YYINITIAL> \" { string.setLength(0); yybegin(STRING); }
<YYINITIAL> "/*" {yybegin(COMMENT);}
<YYINITIAL> {WhiteSpace} { /* ignore */ }

<COMMENT> [^*/] {/* ignore */} 
<COMMENT> "*/" {yybegin(YYINITIAL);}
<COMMENT> .|\n { throw new Error("Illegal character in comment state <"+ yytext()+">"); }
<STRING> 
{
	\" 		{ yybegin(YYINITIAL);
			return symbol(sym.STRING_LITERAL, string.toString()); }
	[^\n\r\"\\]+ { string.append(yytext());}
	\\t 		{ string.append("\t"); }
	\\n 		{ string.append("\n"); }
	\\r 		{ string.append("\r"); }
	\\\" 		{ string.append("\""); }
	\\ 			{ string.append("\\"); }
}

/* error fallback */
.|\n { throw new Error("Illegal character <"+ yytext()+">"); }