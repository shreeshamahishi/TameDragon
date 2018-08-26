grammar ClangLL;

options {
    backtrack=true;
    memoize=true;
    k=2;
}

@header{
package com.compilervision.compilers.clang.abssyntree;
}

@lexer::header{ package com.compilervision.compilers.clang.abssyntree;} 

@members{
	private TranslationUnit addExternDecToTransUnit(TranslationUnit tu, ExternDeclaration ed){
    		if(tu == null){ // First time
    			tu = new TranslationUnit();
    			tu.setExternDec(ed);
    			tu.setTranslationUnitNext(null);
    		}
    		else{ // Add ed to the last tu
    			TranslationUnit last = tu;
    			TranslationUnit current = tu;
    			while(true){
    				if(current.getTranslationUnitNext() == null){
    					last = current;
    					break;
    				}
    				current = current.getTranslationUnitNext();
    			}
    			
    			// Create new translation unit and add to end
    			TranslationUnit tuLatest = new TranslationUnit(0, ed, null);
    			last.setTranslationUnitNext(tuLatest);			
    		}
    		
    		return tu;
    	}
    	
    	private IdentifierList addIdToIdentifierList(String id, IdentifierList identifierList){
        		
        		if(identifierList == null){ // First time
    			identifierList = new IdentifierList();
    			identifierList.setName(id);
    			identifierList.setIdentifierListNext(null);
    		}
    		else{ // Add ed to the last tu
    			IdentifierList last = identifierList;
    			IdentifierList current = identifierList;
    			while(true){
    				if(current.getNextInCollection() == null){
    					last = current;
    					break;
    				}
    				current = current.getNextInCollection();
    			}
    			
    			// Create new translation unit and add to end
    			IdentifierList ilLatest = new IdentifierList(id, null);
    			last.setIdentifierListNext(ilLatest);			
    		}
    		
    		return identifierList;
        	}
    	
    	
    	private NodeCollection addNodeToCollection(NodeItem nodeItem, NodeCollection nodeCollection){
        		
        		NodeCollection newList = null;
        		int elementType = nodeItem.getNodeItemType();
        		
        		NodeCollection last = nodeCollection;
        		if(nodeCollection != null){
        			NodeCollection current = nodeCollection;
        			
        			while(true){
						NodeCollection nc = current.getNextInCollection();
        				if(nc == null){
        					last = current;
        					break;
        				}
        				else{
        					current = nc;
        				}
        			}
        		}
        		
        		switch(elementType){
        			case NodeItem.DECLARATION:
        				DeclarationList declrList = new DeclarationList();
        				declrList.setDeclaration((Declaration)nodeItem);
        				declrList.setDeclarationListNext(null);
        				if(last == null) return declrList;
        				newList = declrList;
        				break;
        				
        			case NodeItem.STRUCT_DECLARATION:
        				StructDeclarationList structDeclrList = new StructDeclarationList();
        				structDeclrList.setStructDeclaration((StructDeclaration)nodeItem);
        				structDeclrList.setStructDeclarationListNext(null);
        				if(last == null) return structDeclrList;
        				newList = structDeclrList;
        				break;
        				
        			case NodeItem.INIT_DECLARATOR:
        				InitDeclaratorList initDeclaratorList = new InitDeclaratorList();
        				initDeclaratorList.setInitDeclarator((InitDeclarator)nodeItem);
        				initDeclaratorList.setInitDeclaratorListNext(null);
        				if(last == null) return initDeclaratorList;
        				newList = initDeclaratorList;
        				break;
        				
        			case NodeItem.STRUCT_DECLARATOR:
        				StructDeclaratorList structDeclaratorList = new StructDeclaratorList();
        				structDeclaratorList.setStructDeclarator((StructDeclarator)nodeItem);
        				structDeclaratorList.setStructDeclaratorListNext(null);
        				if(last == null) return structDeclaratorList;
        				newList = structDeclaratorList;
        				break;
        				
        			case NodeItem.ENUMERATOR:
        				EnumList enumList = new EnumList();
        				enumList.setEnumarator((Enumerator)nodeItem);
        				enumList.setEnumList(null);
        				if(last == null) return enumList;
        				newList = enumList;
        				break;
        				
        			case NodeItem.TYPE_QUALIFIER:
        				TypeQualifierList typeQualifierList = new TypeQualifierList();
        				typeQualifierList.setTypeQualifier((TypeQualifier)nodeItem);
        				typeQualifierList.setTypeQualifierList(null);
        				if(last == null) return typeQualifierList;
        				newList = typeQualifierList;
        				break;
        				
        			case NodeItem.PARAM_DECLARATION:
        				ParamList paramList = new ParamList();
        				paramList.setParamDeclaration((ParamDeclaration)nodeItem);
        				paramList.setParamList(null);
        				if(last == null) return paramList;
        				newList = paramList;
        				break;
        				
        			case NodeItem.INITIALIZER:
        				InitializerList initializerList = new InitializerList();
        				initializerList.setInitializer((Initializer)nodeItem);
        				initializerList.setInitializerList(null);
        				if(last == null) return initializerList;
        				newList = initializerList;
        				break;
        				
        			case NodeItem.STATEMENT:
        				StatementList statementList = new StatementList();
        				statementList.setStatement((Statement)nodeItem);
        				statementList.setStatementList(null);
        				if(last == null) return statementList;
        				newList = statementList;
        				break;
        				
        			case NodeItem.ASSIGNMENT_EXPR:
        				ArgumentExpressionList argumentExpressionList = new ArgumentExpressionList();
        				argumentExpressionList.setAssignmentExpr((AssignmentExpr)nodeItem);
        				argumentExpressionList.setArgumentExpressionList(null);
        				if(last == null) return argumentExpressionList;
        				newList = argumentExpressionList;
        				break;
        			
        			default:
        				if(last == null) return null;
        				break;	
        		}
        		
        		// Since nodeCollection is not null, add the new list to the last collection and return
        		// the first nodeCollection
        		last.setDeclarationListNext(newList);
        		return nodeCollection;
        	}
        	
        	private RootExpr addAssignmentExpToExpr(AssignmentExpr assignmentExpr, RootExpr rootExpr){
        		if(rootExpr == null){
        			rootExpr = new RootExpr();
        			rootExpr.setAssignmentExpr(assignmentExpr);
        			rootExpr.setRootExprNext(null);
        			return rootExpr;
        		}
        		else{
        			RootExpr last = rootExpr;
        			RootExpr current = rootExpr;
        			
        			while(true){
        				if(current.getNextInCollection() == null){
        					last = current;
        					break;
        				}
        			}
        			
        			RootExpr newRootExpr = new RootExpr();
        			newRootExpr.setAssignmentExpr(assignmentExpr);
        			newRootExpr.setRootExprNext(null);
        			last.setRootExprNext(newRootExpr);
        			
        			return rootExpr;
        			
        		}
        	}
        	
    	private DeclarationSpecifiers  addUnitToDeclSpecs(DeclSpecUnit declSpecUnit, DeclarationSpecifiers declSpecs){
    	    		
    		int declSpecUnitType = declSpecUnit.getDecSpecType();
    		DeclarationSpecifiers newDeclSpecs = new DeclarationSpecifiers();
    		if(declSpecs == null){
    			declSpecs = newDeclSpecs;
    			declSpecs.setDeclarationSpecifiersNext(null);
    			newDeclSpecs = declSpecs;
    		}
    		else{
    			
    			newDeclSpecs.setDeclarationSpecifiersNext(null);
    			
    			DeclarationSpecifiers last = declSpecs;
    			DeclarationSpecifiers current = declSpecs;
    			
    			while(true){
    				if(current.getDeclarationSpecifiersNext() == null){
    					last = current;
    					break;
    				}
    				current = current.getDeclarationSpecifiersNext();
    			}
    			
    			last.setDeclarationSpecifiersNext(newDeclSpecs);
    		}
    		
    		if(declSpecUnitType == DeclSpecUnit.STORAGE_CLASS_SPECS){
    			newDeclSpecs.setStorageClassSpecifiers((StorageClassSpecifiers)declSpecUnit);
    		}
    		else if(declSpecUnitType == DeclSpecUnit.TYPE_QUALIFIER){
    			newDeclSpecs.setTypeQualifier((TypeQualifier)declSpecUnit);
    		}
    		else if(declSpecUnitType == DeclSpecUnit.TYPE_SPECIFIER){
    			newDeclSpecs.setTypeSpecifier((TypeSpecifier)declSpecUnit);
    		}
    		
    		return declSpecs;
    	}
    	
    	private SpecifierQualifierList addTypeSpecOrTypeQualToSpecQualList(TypeSpecifier typeSpecifier, 
    			TypeQualifier typeQualifier,  SpecifierQualifierList list){
    			
    		//  One of typeSpecifier or typeQualifier must be null
    			
    		if(list == null){
    			list = new SpecifierQualifierList();
    			if(typeSpecifier == null)
    				list.setTypeQualifier(typeQualifier);
    			else
    				list.setTypeSpecifier(typeSpecifier);
    			
    			list.setSpecifierQualifierListNext(null);
    		}
    		else{
    			SpecifierQualifierList last = list;
    			SpecifierQualifierList current = list;
    			while(true){
    				if(current.getSpecifierQualifierListNext() == null){
    					last = current;
    					break;
    				}
    				current = current.getSpecifierQualifierListNext();
    			}
    			
    			SpecifierQualifierList newList = new SpecifierQualifierList();
    			if(typeSpecifier == null)
    				newList.setTypeQualifier(typeQualifier);
    			else
    				newList.setTypeSpecifier(typeSpecifier);
    			newList.setSpecifierQualifierListNext(null);
    			
    			last.setSpecifierQualifierListNext(newList);
    			
    		}
    		
    		return list;
    	}
    	
    	private void addToDirectDeclarator(ConstExpr constExpr, ParamTypeList paramTypeList, DirectDeclarator
    				directDeclarator){
    		DirectDeclarator last = directDeclarator;
    		DirectDeclarator current = directDeclarator;
    		
    		while(true){
    			if(current.getDirectDeclarator() == null){
    				last = current;
    				break;
    			}
    		}
    		
    		DirectDeclarator newDD = new DirectDeclarator();
    		if(constExpr != null){
    			newDD.setArraySizeExpr(constExpr);
    		}
    		if(paramTypeList != null){
    			newDD.setParamTypeList(paramTypeList);
    		}
    		
    		last.setDirectDeclarator(newDD);
    	}
    	
    	private void addToAbstractDirectDeclarator(ConstExpr constExpr, ParamTypeList paramTypeList, DirectAbstractDeclarator
    				abstractDirectDeclarator){
    		DirectAbstractDeclarator last = abstractDirectDeclarator;
    		DirectAbstractDeclarator current = abstractDirectDeclarator;
    		
    		while(true){
    			if(current.getDirectAbstractDeclarator() == null){
    				last = current;
    				break;
    			}
    		}
    		
    		DirectAbstractDeclarator newDD = new DirectAbstractDeclarator();
    		if(constExpr != null){
    			newDD.setArraySizeExpr(constExpr);
    		}
    		if(paramTypeList != null){
    			newDD.setFuncArgs(paramTypeList);
    		}
    		
    		last.setDirectAbstractDeclarator(newDD);
    	
    	}
    	
    	private void addToPostFixExpr(RootExpr arrayExpr, ArgumentExpressionList argumentExpressionList, int operator, 
        				String id, PostfixExpr postFixExpr){
        		PostfixExpr last = postFixExpr;
        		PostfixExpr current = postFixExpr;
        		
        		while(true){
        			if(current.getPostfixExpr() == null){
        				last = current;
        				break;
        			}
        		}
        		
        		PostfixExpr newPfe = new PostfixExpr();
        		if(arrayExpr != null){
        			newPfe.setArrayExpr(arrayExpr);
        		}
        		if(argumentExpressionList != null){
        			newPfe.setArgumentExpressionList(argumentExpressionList);
        		}
        		if(operator != -1){
        			newPfe.setType(operator);
        		}
        		if(id != null){
        			newPfe.setIdentifier(id);
        		}
        		
        		last.setPostfixExpr(newPfe);
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


SEMICOLON :	';';
COMMA	:	',';
ASSIGN	:	'=';
INT	:	'int';
DOUBLE	:	'double';
PLUS	:	'+';
MINUS 	:	'-';
COLON	:	':';
DOT	:	'.';
EXTERN	:	'extern';
AUTO	:	'auto';
STATIC	:	'static';
REGISTER	:'register';
CHAR	:	'char';
SHORT	:	'short';
LONG	:	'long';
FLOAT	:	'float';
SIGNED	:	'signed';
UNSIGNED:	'unsigned';
TYPEDEF	:	'typedef';
UNION	:	'union';
STRUCT	:	'struct';
CONST	:	'const';
VOLATILE:	'volatile';
ENUM	:	'enum';
LPAREN	:	'(';
RPAREN	:	')';
VOID	:	'void';
LCURLY	:	'{';
RCURLY	:	'}';
LBRACK	:	'[';
RBRACK	:	']';
IF	:	'if';
ELSE	:	'else';
CASE	:	'case';
DEFAULT :	'default';
SWITCH 	:	'switch';
WHILE	:	'while';
DO 	:	'do';
FOR	:	'for';
GOTO	:	'goto';
CONTINUE:	'continue';
BREAK	:	'break';
RETURN	:	'return';
PIPE 	:	'|';
NOT	:	'!';
EQUALS	:	'==';
NOT_EQUALS :	'!=';
DEREFERENCE :	'->';
AMPERSAND : '&';
INCREMENT :	'++';
DECREMENT :	'--';
SIZEOF	:	 'sizeof';
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
TILDE	:	'^';
OR	:	'||';
AND	:	'&&';
QUESTION :	'question';
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
NEWLINE	:	'\r'?'\n'{skip();};
WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;} ;
COMMENT :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;} ;
LINE_COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};
ID	:	LETTER (LETTER|'0'..'9')* ;	
STRING_LITERAL :	'"' ( EscapeSequence | ~('\\'|'"') )* '"' ;
fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
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

translation_unit returns [TranslationUnit value] : 
		(e=extern_declaration {$value = addExternDecToTransUnit($value, $e.value);})+
	;
extern_declaration returns [ExternDeclaration value]  options {k=1;} 
	: (e1=declaration_specifiers? e2=declarator declaration* '{' )=> e3=function_definition {$value = $e3.value;}
	| (e4=declaration  {$value = $e4.value;})
	;
//		e1=function_definition {$value = $e1;}
//	|	e2=declaration {$value = $e2;}
//	;
function_definition returns [FunctionDef value]:	
			(e1=declaration_specifiers {$value = new FunctionDef(); $value.setDeclSpecifiers($e1.value);})? 
			e2=declarator {if($value == null) $value = new FunctionDef(); $value.setDeclarator($e2.value);}
			e3=compound_statement {$value.setCompoundStmt($e3.value);}
	;
declaration returns [Declaration value]
	:	e1=declaration_specifiers {$value = new Declaration(); $value.setDeclSpecifiers($e1.value);}
		(e2=init_declarator_list {$value.setInitDeclaratorList($e2.value);})? SEMICOLON
	;
declaration_list returns [DeclarationList value]
	:	(e1=declaration {$value = (DeclarationList)addNodeToCollection($e1.value, $value);} )+
	;
declaration_specifiers returns [DeclarationSpecifiers value]
	: (e1=storage_class_specifier {$value = addUnitToDeclSpecs($e1.value, $value);}
	| e2=type_specifier  {$value = addUnitToDeclSpecs($e2.value, $value);}
	| e3= type_qualifier {$value = addUnitToDeclSpecs($e3.value, $value);})+ 
	;
storage_class_specifier returns [StorageClassSpecifiers value]
	:	AUTO {$value = new StorageClassSpecifiers(StorageClassSpecifiers.AUTO);}
	|	REGISTER {$value = new StorageClassSpecifiers(StorageClassSpecifiers.REGISTER);}
	|	EXTERN {$value = new StorageClassSpecifiers(StorageClassSpecifiers.EXTERN);}
	|	STATIC {$value = new StorageClassSpecifiers(StorageClassSpecifiers.STATIC);}
	|	TYPEDEF {$value = new StorageClassSpecifiers(StorageClassSpecifiers.TYPEDEF);}
	;
type_specifier returns [TypeSpecifier value]
	:	VOID {$value = new TypeSpecifier(TypeSpecifier.VOID); }
	|	CHAR {$value = new TypeSpecifier(TypeSpecifier.CHAR); }
	|	SHORT {$value = new TypeSpecifier(TypeSpecifier.SHORT); }
	|	INT {$value = new TypeSpecifier(TypeSpecifier.INT); }
	|	LONG {$value = new TypeSpecifier(TypeSpecifier.LONG); }
	|	FLOAT {$value = new TypeSpecifier(TypeSpecifier.FLOAT); }
	|	DOUBLE {$value = new TypeSpecifier(TypeSpecifier.DOUBLE); }
	|	SIGNED {$value = new TypeSpecifier(TypeSpecifier.SIGNED); }
	|	UNSIGNED {$value = new TypeSpecifier(TypeSpecifier.UNSIGNED); }
	|	struct_or_union_spec {$value = new TypeSpecifier(TypeSpecifier.STRUCT_OR_UNION_SPEC); }
	|	enum_spec {$value = new TypeSpecifier(TypeSpecifier.ENUM_SPEC); }
	;
type_qualifier	returns [TypeQualifier value]
	:	CONST {$value = new TypeQualifier(TypeQualifier.CONST);}
	|	VOLATILE {$value = new TypeQualifier(TypeQualifier.VOLATILE);}
	;
struct_or_union_spec returns [StructOrUnionSpecifier value]
	:	e1=struct_or_union {$value = new StructOrUnionSpecifier(); $value.setStructOrUnion($e1.value); }
		(e2=ID {$value.setName($e2.text); } )? 
		 LCURLY e3=struct_decl_list {$value.setStructDecList($e3.value); } RCURLY
	|	e1= struct_or_union {$value = new StructOrUnionSpecifier();  $value.setStructOrUnion($e1.value);}
		 (e2=ID {$value.setName($e2.text);} )
	;
struct_or_union returns [StructOrUnion value]
	:	UNION {$value = new StructOrUnion(StructOrUnion.UNION);}
	|	STRUCT {$value = new StructOrUnion(StructOrUnion.UNION);}
	;
struct_decl_list returns [StructDeclarationList value ]
	:	(e1=struct_declaration {$value = (StructDeclarationList)addNodeToCollection($e1.value , $value);})+
	;
init_declarator_list returns [InitDeclaratorList value]
	:	e1=init_declarator {$value = (InitDeclaratorList)addNodeToCollection( $e1.value, $value);}
			(COMMA 	e2=init_declarator {$value = (InitDeclaratorList)addNodeToCollection($e2.value, $value);})*
	;
init_declarator returns [InitDeclarator value]
	:	e1=declarator {$value = new InitDeclarator($e1.value, null);} 
			(ASSIGN e2=initializer {$value.setInitializer($e2.value); } )?	
	;
struct_declaration returns [StructDeclaration value]
	:	e1=specifier_qualifier_list  {$value = new StructDeclaration(); $value.setSpecifierQualifierList($e1.value);}
		e2=struct_declarator_list {$value.setStructDeclaratorList($e2.value);}	SEMICOLON
	;
specifier_qualifier_list returns [SpecifierQualifierList value]
	:	(e1=type_specifier {$value = addTypeSpecOrTypeQualToSpecQualList($e1.value, null,  $value);}| 
			e2=type_qualifier {$value = addTypeSpecOrTypeQualToSpecQualList(null, $e2.value, $value);}) +
	;
struct_declarator_list returns [StructDeclaratorList value]
	:	e1=struct_declarator {$value= (StructDeclaratorList)addNodeToCollection($e1.value, $value);}
		(COMMA e2=struct_declarator {$value= (StructDeclaratorList) addNodeToCollection($e2.value, $value);})*
	;
struct_declarator returns [StructDeclarator value]
	:	e1=declarator {$value = new StructDeclarator($e1.value, null);} (COLON 
			e2=constant_expression { $value.setBitFieldValue($e2.value);} )?
		|	COLON (e3=constant_expression {$value = new StructDeclarator(null, $e3.value);})?  
	;
enum_spec returns [EnumSpecifier value]
	:	 ENUM (ID )? 
		LCURLY e2=enumarator_list {$value = new EnumSpecifier($ID.text, $e2.value);} RCURLY
	| 	ENUM ID {$value = new EnumSpecifier($ID.text, null);}
	;
enumarator_list returns [EnumList value]
	:	e1=enumerator {$value = (EnumList)addNodeToCollection($e1.value, $value);}
			 (COMMA e2=enumerator {$value = (EnumList)addNodeToCollection($e2.value, $value);})*
	;
enumerator returns [Enumerator value] 
	:	e1=ID {$value = new Enumerator($e1.text, null); } 
			(ASSIGN e2=constant_expression {$value.setInitValue($e2.value);})?	
	;
declarator returns [Declarator value]
	:	(e1=pointer {$value = new Declarator(null, $e1.value);}) ? 
		e2=direct_declarator {if($value == null) $value = new Declarator();
				$value.setDirectDeclarator($e2.value);}
	| e3=pointer {$value = new Declarator(null, $e3.value);}
	;
direct_declarator returns [DirectDeclarator value]
	:	(e=ID {$value = new DirectDeclarator(); $value.setId($e.text); $value.setType(DirectDeclarator.ID);}| 
			LPAREN e1=declarator {$value = new DirectDeclarator(); $value.setDeclarator($e1.value);
			 $value.setType(DirectDeclarator.DECLR);} RPAREN )
		 ( LBRACK (e2=constant_expression)? RBRACK { 
		 	DirectDeclarator oldDD = $value;
		 	DirectDeclarator newDD = new DirectDeclarator(); 
		 	if($e2.value != null) newDD.setType(DirectDeclarator.CONST_ARRAY);  
		 	else newDD.setType(DirectDeclarator.EMPTY_ARRAY); 
		 	newDD.setArraySizeExpr($e2.value);
		 	newDD.setDirectDeclarator(oldDD); $value = newDD;}  
		 | LPAREN (e3=parameter_type_list)? RPAREN  {  
		 	DirectDeclarator oldDD = $value;
		 	DirectDeclarator newDD = new DirectDeclarator(); 
		 	newDD.setType(DirectDeclarator.FUNC); 
		 	newDD.setParamTypeList($e3.value);
		 	newDD.setDirectDeclarator(oldDD); $value = newDD; } 
		 | LPAREN (e4=identifier_list)? RPAREN  { 
		 	 	DirectDeclarator oldDD = $value;
		 		DirectDeclarator newDD = new DirectDeclarator(); 
		 		if($e4.value != null) newDD.setType(DirectDeclarator.ID_LIST_FUNC); 
		 		else newDD.setType(DirectDeclarator.NO_ARG_FUNC);  
		 		newDD.setIdentifierList($e4.value);
		 		newDD.setDirectDeclarator(oldDD); $value = newDD; 
		 	 }
		 )*
	;
pointer returns [Pointer value]
	: STAR e1=type_qualifier_list {$value = new Pointer(); $value.setTypeQualifierList($e1.value);} (e2=pointer {$value.setPointer($e2.value);})?
	| STAR e2=pointer {$value = new Pointer($e2.value);}
	| STAR {$value = new Pointer();}
	;
type_qualifier_list returns [TypeQualifierList value]
	:	(e1=type_qualifier {$value = (TypeQualifierList)addNodeToCollection($e1.value, $value); })+
	;
parameter_type_list returns [ParamTypeList value]
	:	e1=parameter_list {$value = new ParamTypeList($e1.value);} (COMMA ELLIPSES {$value.setHasEllipses(true);})?
	;
parameter_list returns [ParamList value]
	:	e1 = param_declaration {$value =  (ParamList)addNodeToCollection($e1.value, $value); } 
		( COMMA e2=param_declaration {$value = (ParamList) addNodeToCollection($e2.value, $value);})*
	;
param_declaration returns [ParamDeclaration value]
	:	e=declaration_specifiers {$value = new ParamDeclaration(); $value.setDeclarationSpecifiers($e.value);} 
		(e1=declarator {$value.setDeclarator($e1.value);} 
	     | e2=abstract_declarator {$value.setAbstractDeclarator($e2.value);})*	
	;
identifier_list returns [IdentifierList value]
	: e1=ID {$value = (IdentifierList)addIdToIdentifierList($e1.text, $value);} 
	 (COMMA e2=ID {$value = (IdentifierList)addIdToIdentifierList($e2.text, $value);})*
	;	
	
initializer returns [Initializer value]
	:	e1= assignment_expression {$value = new Initializer(); $value.setInitExpr($e1.value);}
	|	LCURLY e2=initializer_list {$value = new Initializer(); $value.setInitializerList($e2.value);}COMMA ? RCURLY
	;
initializer_list returns [InitializerList value]
	:	e1 = initializer {$value =  (InitializerList)addNodeToCollection($e1.value,$value); } 
		(COMMA e2=initializer {$value = (InitializerList)addNodeToCollection($e1.value, $value);})*	
	;
type_name returns [TypeName value]
	:	e1= specifier_qualifier_list {$value = new TypeName(); $value.setSpecifierQualifierList($e1.value);}
		(e2=abstract_declarator {$value.setAbstractDeclarator($e2.value);})?
	;
abstract_declarator returns [AbstractDeclarator value]	
	: 	e1=pointer (e2=direct_abstract_declarator  {$value = new AbstractDeclarator($e1.value, $e2.value); })?
	|	e2=direct_abstract_declarator  {$value = new AbstractDeclarator(null, $e2.value);}	
	;
direct_abstract_declarator returns [DirectAbstractDeclarator value]
	:	((LCURLY e1=abstract_declarator {$value = new DirectAbstractDeclarator(); $value.setAbstractDeclarator($e1.value);} RCURLY)
		| (LBRACK (e2=constant_expression { $value = new DirectAbstractDeclarator(); addToAbstractDirectDeclarator($e2.value, null, $value);})? RBRACK | 
		 LPAREN (e3=parameter_type_list { $value = new DirectAbstractDeclarator(); addToAbstractDirectDeclarator(null, $e3.value, $value);})? RPAREN)
		 )
		 
		 ( LBRACK (e2=constant_expression { addToAbstractDirectDeclarator($e2.value, null, $value);})? RBRACK | 
		 LPAREN (e3=parameter_type_list { addToAbstractDirectDeclarator(null, $e3.value, $value);})? RPAREN )*
	;
statement returns [Statement value]
	:	e1=labeled_statement {$value = $e1.value;}
	|	e2=selection_statement {$value = $e2.value;}
	|	e3=jump_statement {$value = $e3.value;}
	|	e4=expr_statement {$value = $e4.value;}
	|	e5=compound_statement {$value = $e5.value;}
	|	e6=iteration_statement {$value = $e6.value;}
	;
labeled_statement returns [LabeledStatement value]
	:	e=ID COLON e1=statement {$value = new LabeledStatement($e.text, null, $e1.value);}
	|	CASE e2=constant_expression COLON e3=statement {$value = new LabeledStatement(null, $e2.value, $e3.value);}
	|	DEFAULT COLON e4=statement {$value = new LabeledStatement(null, null, $e4.value);}
	;
expr_statement  returns [ExprStatement value]
	:	SEMICOLON {$value = new ExprStatement(null);}
	|	e1=expression  SEMICOLON {$value = new ExprStatement($e1.value);} 
	;
compound_statement returns [CompoundStatement value]
	:	LCURLY (e1=declaration_list {$value = new CompoundStatement($e1.value, null); })* 
			(e2=statement_list {if($value == null) $value = new CompoundStatement(null, $e2.value);
			else $value.setStatementList($e2.value);})? RCURLY
	;
statement_list returns [StatementList value]
	:	(e1=statement {$value = (StatementList)addNodeToCollection($e1.value, $value); })+
	;
selection_statement returns [SelectionStatement value] 	
	:	IF LPAREN e1=expression {$value = new SelectionStatement(); $value.setIfExpr($e1.value);} RPAREN
			 e2=statement {$value.setIfStmt($e2.value);}
			(ELSE  e3=statement {$value.setElseStmt($e3.value);} )?
	|	SWITCH LPAREN e1=expression {{$value = new SelectionStatement(); $value.setSwitchExpr($e1.value);}}
			 RPAREN e2=statement {$value.setSwitchStmt($e2.value);}
	;
iteration_statement returns [IterationStatement value]
	:	WHILE LPAREN e1=expression{$value = new IterationStatement(); $value.setWhileExpr($e1.value);} RPAREN 
			e2=statement {$value.setWhileStmt($e2.value); $value.setWhileType(IterationStatement.WHILE);}
	|	DO e3=statement {$value = new IterationStatement(); $value.setWhileStmt($e3.value); $value.setWhileType(IterationStatement.DO_WHILE);}
		 WHILE LPAREN e4=expression{$value.setWhileExpr($e4.value);} RPAREN SEMICOLON
	|	FOR LPAREN {$value = new IterationStatement(); } 
		(e5=expression {$value.setForInitExpr($e5.value);})? SEMICOLON 
		(e6=expression{$value.setForCondExpr($e6.value);})? SEMICOLON (e7=expression{$value.setForIncrExpr($e7.value);})? 
		RPAREN e8=statement {$value.setForStmt($e8.value);}
	;
jump_statement returns [JumpStatement value]
	: GOTO e=ID SEMICOLON {$value = new JumpStatement(JumpStatement.GOTO, $e.text, null);}
	| CONTINUE {$value = new JumpStatement(JumpStatement.CONTINUE, null, null);}
	| BREAK {$value = new JumpStatement(JumpStatement.BREAK, null, null);}
	| RETURN (e1=expression {$value = new JumpStatement(JumpStatement.RETURN, null, $e1.value);})?
	;
expression returns [RootExpr value]
	:	e1=assignment_expression {addAssignmentExpToExpr($e1.value, $value);} 
		(COMMA e2=assignment_expression {addAssignmentExpToExpr($e2.value, $value);} )*
	;
assignment_expression returns [AssignmentExpr value]
	:	e1=unary_expr {$value = new AssignmentExpr(); $value.setUnaryExpr($e1.value);}
		 e2=assignment_operator {$value.setAssignmentOperator($e2.value);} e3=assignment_expression {$value.setAssignmentExpr($e3.value);}
		| e4=conditional_expression {$value = new AssignmentExpr(); $value.setConditionalExpr($e4.value);}
	;
assignment_operator returns [AssignmentOperator value]
	:	ASSIGN {$value = new AssignmentOperator(AssignmentOperator.ASSIGN);}
	|	MULTIPLY_ASSIGN  {$value = new AssignmentOperator(AssignmentOperator.MULTIPLY_ASSIGN);}
	|	DIVIDE_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.DIVIDE_ASSIGN);}
	|	ADD_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.ADD_ASSIGN);}
	|	MINUS_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.MINUS_ASSIGN);}
	|	MODULO_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.MODULO_ASSIGN);}
	|	BITWISE_AND_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.BITWISE_AND_ASSIGN);}
	|	BITWISE_XOR_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.BITWISE_XOR_ASSIGN);}
	|	BITWISE_OR_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.BITWISE_OR_ASSIGN);}
	|	LEFT_SHIFT_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.LEFT_SHIFT_ASSIGN);}
	|	RIGHT_SHIFT_ASSIGN{$value = new AssignmentOperator(AssignmentOperator.RIGHT_SHIFT_ASSIGN);}
	;
conditional_expression returns [ConditionalExpr value]
	:	e1=logical_or_expr {$value = new ConditionalExpr(); $value.setLogicalOrExpr($e1.value);}
		(QUESTION e2= expression {$value.setTrueInConditional($e2.value);} 
			COLON e3= conditional_expression {$value.setFalseInConditional($e3.value);})?
	;
constant_expression returns [ConstExpr value]
	:	e=conditional_expression {$value = new ConstExpr(); $value.setCondExpr($e.value);}
	;
logical_or_expr returns [LogicalOrExpr value]
	:	e1=logical_and_expr {$value = new LogicalOrExpr(null, $e1.value); } 
		(OR e2=logical_and_expr {$value = new LogicalOrExpr($value, $e2.value);})*
	;
logical_and_expr returns [LogicalAndExpr value]
	:	e1=inclusive_or_expr {$value = new LogicalAndExpr(null, $e1.value); } 
		 (AND e2=inclusive_or_expr {$value = new LogicalAndExpr($value, $e2.value);})*
	;
inclusive_or_expr returns [InclusiveOrExpr value]
	:	e1=exclusive_or_expr {$value = new InclusiveOrExpr(null, $e1.value); }  
		(PIPE e2=exclusive_or_expr {$value = new InclusiveOrExpr($value, $e2.value);})*
	;
exclusive_or_expr returns [ExclusiveOrExpr value]
	:	e1=and_expr {$value = new ExclusiveOrExpr(null, $e1.value); } 
		(TILDE e2=and_expr {$value = new ExclusiveOrExpr($value, $e2.value);})*
	;
and_expr returns [AndExpr value]
	:	e1=equality_expr  {$value = new AndExpr(null, $e1.value); } 
		 (AMPERSAND e2=equality_expr {$value = new AndExpr($value, $e2.value);})*
	;
equality_expr returns [EqualityExpr value]
	:	e1=relational_expr {$value = new EqualityExpr(null, $e1.value, -1); } 
		 ((sign=EQUALS | sign=NOT_EQUALS )
		  e2=relational_expr {$value = new EqualityExpr($value, $e2.value, getIdForSymbol($sign.text, $value));} )*
	;
relational_expr returns [RelationalExpr value]
	:	e1=shift_expr 	{$value = new RelationalExpr(null, $e1.value, -1); } 
		(( sign=LESSER_THAN | sign=GREATER_THAN | sign=LESSER_THAN_OR_EQUAL_TO | sign=GREATER_THAN_OR_EQUAL_TO)
		 e2=shift_expr {$value = new RelationalExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
shift_expr returns [ShiftExpr value]
	:	e1=additive_expr {$value = new ShiftExpr(null, $e1.value, -1); } 
		 ((sign=LEFT_SHIFT | sign=RIGHT_SHIFT)
		  e2=additive_expr {$value = new ShiftExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
additive_expr returns [AdditiveExpr value]
	:	e1=multiplicative_expr {$value = new AdditiveExpr(null, $e1.value, -1); } 
		(( sign=PLUS  | sign=MINUS) 
		e2=multiplicative_expr {$value = new AdditiveExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
multiplicative_expr returns [MultiplicativeExpr value]
	:	e1=cast_expr {$value = new MultiplicativeExpr(null, $e1.value, -1); } 
		(( sign=STAR | sign=DIVIDE | sign=MODULO )
		e2=cast_expr {$value = new MultiplicativeExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
cast_expr returns [CastExpr value]
	:	e=unary_expr {$value = new CastExpr(); $value.setUnaryExpr($e.value);}
	|	LPAREN e1=type_name {$value = new CastExpr(); $value.setTypeName($e1.value);}
		 RPAREN e2=cast_expr  {$value.setCastExpr($e2.value);}
	;
unary_expr returns [UnaryExpr value]
	:	e=postfix_expr {$value = new UnaryExpr(); $value.setPostfixExpr($e.value);}
	|	INCREMENT {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.INCR);} e1=unary_expr {$value.setUnaryExpr($e1.value);}
	|	DECREMENT {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.DECR);} e2=unary_expr{$value.setUnaryExpr($e2.value);}
	|	(AMPERSAND  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.AMPERSAND);}
		| 	STAR  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.STAR);}
		| 	PLUS  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.PLUS);}
		| 	MINUS  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.MINUS);}
		| 	TILDE  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.TILDE);}
		| 	NOT  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.NOT);}
		) e3=cast_expr {$value.setCastExpr($e3.value);}
	|	SIZEOF  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.SIZEOF);} e4=unary_expr {$value.setUnaryExpr($e4.value);}
	|	SIZEOF {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.SIZEOF);}  LPAREN
		 e5=type_name {$value.setTypeName($e5.value);} RPAREN
	;
postfix_expr returns [PostfixExpr value]
	:	e1=primary_expr {$value = new PostfixExpr(); $value.setPrimaryExpr($e1.value);}
		(( LBRACK e2=expression {addToPostFixExpr($e2.value, null, -1, null, $value); } RBRACK 
		| LPAREN (e3=argument_expr_list  {addToPostFixExpr(null, $e3.value, -1, null, $value); })? RPAREN
		| DOT e=ID {addToPostFixExpr(null, null, PostfixExpr.DOT, $e.text, $value); }
		| DEREFERENCE e4=ID {addToPostFixExpr(null, null, PostfixExpr.DEREFERENCE, $e4.text, $value); }
		| INCREMENT {addToPostFixExpr(null, null, PostfixExpr.INCR, null, $value); }
		| DECREMENT {addToPostFixExpr(null, null, PostfixExpr.DECR, null, $value); }
		) )*
	;
primary_expr returns [PrimaryExpr value] 
	:	e=ID {$value = new PrimaryExpr(); $value.setIdentifier($e.text);}
	|	e1=constant {$value = new PrimaryExpr(); $value.setConstant($e1.value);}
	|	e2=STRING_LITERAL {$value = new PrimaryExpr(); $value.setString($e2.text);}
	|	LPAREN e3=expression {$value = new PrimaryExpr(); $value.setExpr($e3.value);} RPAREN
	;
argument_expr_list returns [ArgumentExpressionList value]
	:	(e1=assignment_expression {$value = (ArgumentExpressionList)addNodeToCollection($e1.value, $value); })+
	;
constant returns [Constant value]
	:	DECIMAL_LITERAL  {$value = new Constant(); $value.setIntConstant($DECIMAL_LITERAL.text);}
	|	OCTAL_LITERAL  {$value = new Constant(); $value.setIntConstant($OCTAL_LITERAL.text);}
	|	HEX_LITERAL  {$value = new Constant(); $value.setIntConstant($HEX_LITERAL.text);}
	|	FLOATING_LITERAL  {$value = new Constant(); $value.setFloatingConstant($FLOATING_LITERAL.text);}
	|	CHAR_LITERAL {$value = new Constant(); $value.setIntConstant($CHAR_LITERAL.text);}
	;
