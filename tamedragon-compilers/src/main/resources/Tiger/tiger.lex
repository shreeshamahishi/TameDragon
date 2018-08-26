/**
* This class is the abstraction for a scanner for the tiger language.
*/
package org.codetranslators.compiler.tiger.lexer;

import java_cup.runtime.*;

%%
%public
%class Lexer
%unicode
%cupsym TigerSym
%cup
%line
%column
%function next_token
%type java_cup.runtime.Symbol
%{
	int numOpenComment;
	StringBuffer string = new StringBuffer();
	private Symbol symbol(int type)
	{
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
	
	public int getLine()
	{
		return yyline;
	}
	
	public int getColumn()
	{
		return yycolumn;
	}
	
%}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

Identifier = [_a-zA-Z] [_a-zA-Z0-9]*
DecIntegerLiteral = 0 | [1-9][0-9]*
%state STRING
%state COMMENT

%%
<YYINITIAL> "type" { return symbol(TigerSym.TYPE); }
<YYINITIAL> "array" { return symbol(TigerSym.ARRAY); }
<YYINITIAL> "of" { return symbol(TigerSym.OF); }
<YYINITIAL> "var" { return symbol(TigerSym.VAR); }
<YYINITIAL> "nil" { return symbol(TigerSym.NIL); }
<YYINITIAL> "function" { return symbol(TigerSym.FUNCTION); }
<YYINITIAL> "let" { return symbol(TigerSym.LET); }
<YYINITIAL> "in" { return symbol(TigerSym.IN); }
<YYINITIAL> "end" { return symbol(TigerSym.END); }
<YYINITIAL> "-" { return symbol(TigerSym.MINUS); }
<YYINITIAL> "+" { return symbol(TigerSym.PLUS); }
<YYINITIAL> "*" { return symbol(TigerSym.MULTIPLY); }
<YYINITIAL> "/" { return symbol(TigerSym.DIVIDE); }
<YYINITIAL> "=" { return symbol(TigerSym.EQUAL); }
<YYINITIAL> "<>" { return symbol(TigerSym.NEQ); }
<YYINITIAL> ">" { return symbol(TigerSym.GT); }
<YYINITIAL> "<" { return symbol(TigerSym.LT); }
<YYINITIAL> ">=" { return symbol(TigerSym.GTE); }
<YYINITIAL> "<=" { return symbol(TigerSym.LTE); }
<YYINITIAL> "&" { return symbol(TigerSym.AND); }
<YYINITIAL> "|" { return symbol(TigerSym.OR); }
<YYINITIAL> ":=" { return symbol(TigerSym.ASSIGN); }
<YYINITIAL> "if" { return symbol(TigerSym.IF); }
<YYINITIAL> "then" { return symbol(TigerSym.THEN); }
<YYINITIAL> "else" { return symbol(TigerSym.ELSE); }
<YYINITIAL> "while" { return symbol(TigerSym.WHILE); }
<YYINITIAL> "for" { return symbol(TigerSym.FOR); }
<YYINITIAL> "do" { return symbol(TigerSym.DO); }
<YYINITIAL> "break" { return symbol(TigerSym.BREAK); }
<YYINITIAL> "(" { return symbol(TigerSym.LPAREN); }
<YYINITIAL> ")" { return symbol(TigerSym.RPAREN); }
<YYINITIAL> "[" { return symbol(TigerSym.LBRACK); }
<YYINITIAL> "]" { return symbol(TigerSym.RBRACK); }
<YYINITIAL> "{" { return symbol(TigerSym.LBRACE); }
<YYINITIAL> "}" { return symbol(TigerSym.RBRACE); }
<YYINITIAL> ":" { return symbol(TigerSym.COLON); }
<YYINITIAL> "int" { return symbol(TigerSym.INT, yytext()); }
<YYINITIAL> "to" { return symbol(TigerSym.TO); }
<YYINITIAL> "." { return symbol(TigerSym.DOT); }
<YYINITIAL> "," { return symbol(TigerSym.COMMA); }
<YYINITIAL> "string" { return symbol(TigerSym.STRING, yytext()); }
<YYINITIAL> ";" { return symbol(TigerSym.SEMICOLON); }
<YYINITIAL> {Identifier} { return symbol(TigerSym.IDENTIFIER, yytext()); }
<YYINITIAL> {DecIntegerLiteral} { return symbol(TigerSym.INTEGER_LITERAL, new Integer(yytext())); }
<YYINITIAL> \" { string.setLength(0); yybegin(STRING); }
<YYINITIAL> "/*" {yybegin(COMMENT); numOpenComment = 1;}
<YYINITIAL> {WhiteSpace} { /* ignore */ }

<COMMENT> "/*" {numOpenComment++;}
<COMMENT> [^*/] {/* ignore */} 
<COMMENT> "*/" {numOpenComment--; if(numOpenComment == 0) yybegin(YYINITIAL);}
<COMMENT> .|\n { throw new Error("Illegal character in comment state <"+ yytext()+">"); }
<STRING> 
{
	\" 		{ yybegin(YYINITIAL);
			return symbol(TigerSym.STRING_LITERAL, string.toString()); }
	[^\n\r\"\\]+ { string.append(yytext());}
	\\t 		{ string.append("\t"); }
	\\n 		{ string.append("\n"); }
	\\r 		{ string.append("\r"); }
	\\\" 		{ string.append("\""); }
	\\ 			{ string.append("\\"); }
}

/* error fallback */
.|\n { throw new Error("Illegal character <"+ yytext()+">"); }