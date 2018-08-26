// $ANTLR 3.3 Nov 30, 2010 12:50:56 resources/Clang/ClangLL.g 2014-07-10 11:47:33

package org.tamedragon.compilers.clang.abssyntree;
import java.util.HashSet;
import java.util.Stack;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class ClangLLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "HASH", "SEMICOLON", "COMMA", "ASSIGN", "INT", "DOUBLE", "PLUS", "MINUS", "COLON", "DOT", "EXTERN", "AUTO", "STATIC", "REGISTER", "CHAR", "SHORT", "LONG", "FLOAT", "SIGNED", "UNSIGNED", "TYPEDEF", "UNION", "STRUCT", "CONST", "VOLATILE", "ENUM", "LPAREN", "RPAREN", "VOID", "LCURLY", "RCURLY", "LBRACK", "RBRACK", "IF", "ELSE", "CASE", "DEFAULT", "SWITCH", "WHILE", "DO", "FOR", "GOTO", "CONTINUE", "BREAK", "RETURN", "PIPE", "NOT", "EQUALS", "NOT_EQUALS", "DEREFERENCE", "AMPERSAND", "INCREMENT", "DECREMENT", "SIZEOF", "TYPEOF", "MULTIPLY_ASSIGN", "DIVIDE_ASSIGN", "ADD_ASSIGN", "MINUS_ASSIGN", "MODULO_ASSIGN", "BITWISE_AND_ASSIGN", "BITWISE_XOR_ASSIGN", "BITWISE_OR_ASSIGN", "LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", "LESSER_THAN", "GREATER_THAN", "LESSER_THAN_OR_EQUAL_TO", "GREATER_THAN_OR_EQUAL_TO", "LEFT_SHIFT", "RIGHT_SHIFT", "TILDE", "CARET", "OR", "AND", "QUESTION", "STAR", "DIVIDE", "MODULO", "ELLIPSES", "IntegerTypeSuffix", "DECIMAL_LITERAL", "OCTAL_LITERAL", "HexDigit", "HEX_LITERAL", "Exponent", "FloatTypeSuffix", "FLOATING_LITERAL", "EscapeSequence", "CHAR_LITERAL", "NEWLINE", "WS", "COMMENT", "LINE_COMMENT", "LETTER", "ID", "STRING_LITERAL", "OctalEscape"
    };
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


        public ClangLLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ClangLLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[238+1];
             
             
        }
        

    public String[] getTokenNames() { return ClangLLParser.tokenNames; }
    public String getGrammarFileName() { return "resources/Clang/ClangLL.g"; }


    	private HashSet<String> ids = new HashSet<String>();
    	private HashSet<String> typeNames = new HashSet<String>();
    	private Stack<String> errors = new Stack<String>();
    	
    	private boolean parsingParamDecl = false;
    	
    	private boolean shouldGetTypedefName(String name){
    		try{
    				String nextToken = input.LT(2).getText();
    		
    		if(!parsingParamDecl){
    			if(nextToken.equals(";") || nextToken.equals("=") || nextToken.equals("(")
    					|| nextToken.equals(",") || nextToken.equals("[")){
    				return false;
    				}
    			return true;
    		}
    		else{
    			if(nextToken.equals(";") || nextToken.equals("=") || nextToken.equals("(")
    					|| nextToken.equals(",") || nextToken.equals("[") || nextToken.equals(")")){
    				return false;
    				}
    			return true;
    		}
    			}
    			catch(Exception e){
    				return false;
    			}
    	}
    	
    	private TranslationUnit addExternDecToTransUnit(TranslationUnit tu, ExternDeclaration ed){
        		try{
    				if(tu == null){ // First time
        			tu = new TranslationUnit();
        			tu.setExternDec(ed);
        			tu.setLineNum(ed.getLineNum());
        			tu.setPos(ed.getPos()); 
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
        			TranslationUnit tuLatest = new TranslationUnit(ed, null);
        			last.setTranslationUnitNext(tuLatest);			
        		}

        		return tu;
    			}
    			catch(Exception e){
    				return tu;
    			}
        		
        	}
        	
        	private IdentifierList addIdToIdentifierList(String id, IdentifierList identifierList,
        										int lineNum, int pos){
            		try{
    				if(identifierList == null){ // First time
        			identifierList = new IdentifierList();
        			identifierList.setName(id);
        			identifierList.setLineNum(lineNum);
        			identifierList.setPos(pos); 
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
    			catch(Exception e){
    				return identifierList;
    			}
            	
            	}
        	
        	
        	private NodeCollection addNodeToCollection(NodeItem nodeItem, NodeCollection nodeCollection){
            	NodeCollection newList = null;
            		try{
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
            				
            			case NodeItem.STRUCT_DECLARATION:
            				StructDeclarationList structDeclrList = new StructDeclarationList();
            				structDeclrList.setStructDeclaration((StructDeclaration)nodeItem);
            				structDeclrList.setStructDeclarationListNext(null);
            				structDeclrList.setLineNum(((StructDeclaration)nodeItem).getLineNum());
            				structDeclrList.setPos(((StructDeclaration)nodeItem).getPos());
            				if(last == null) {        					
            					return structDeclrList;
            				}
            				newList = structDeclrList;
            				break;
            				
            			case NodeItem.INIT_DECLARATOR:
            				InitDeclaratorList initDeclaratorList = new InitDeclaratorList();
            				initDeclaratorList.setInitDeclarator((InitDeclarator)nodeItem);
            				initDeclaratorList.setInitDeclaratorListNext(null);
            				initDeclaratorList.setLineNum(((InitDeclarator)nodeItem).getLineNum());
            				initDeclaratorList.setPos(((InitDeclarator)nodeItem).getPos());
            				if(last == null){        					
            					return initDeclaratorList;
            				}
            				newList = initDeclaratorList;
            				break;
            				
            			case NodeItem.STRUCT_DECLARATOR:
            				StructDeclaratorList structDeclaratorList = new StructDeclaratorList();
            				structDeclaratorList.setStructDeclarator((StructDeclarator)nodeItem);
            				structDeclaratorList.setLineNum(((StructDeclarator)nodeItem).getLineNum());
            				structDeclaratorList.setPos(((StructDeclarator)nodeItem).getPos());
            				structDeclaratorList.setStructDeclaratorListNext(null);
            				if(last == null){        					
            					return structDeclaratorList;
            				}
            				newList = structDeclaratorList;
            				break;
            				
            			case NodeItem.ENUMERATOR:
            				EnumList enumList = new EnumList();
            				enumList.setEnumarator((Enumerator)nodeItem);
            				enumList.setEnumList(null);
            				enumList.setLineNum(((Enumerator)nodeItem).getLineNum());
            				enumList.setPos(((Enumerator)nodeItem).getPos());
            				if(last == null){        					
            					return enumList;
            				}
            				newList = enumList;
            				break;
            				
            			case NodeItem.TYPE_QUALIFIER:
            				TypeQualifierList typeQualifierList = new TypeQualifierList();
            				typeQualifierList.setTypeQualifier((TypeQualifier)nodeItem);
            				typeQualifierList.setTypeQualifierList(null);
            				typeQualifierList.setLineNum(((TypeQualifier)nodeItem).getLineNum());
            				typeQualifierList.setPos(((TypeQualifier)nodeItem).getPos());
            				if(last == null){        					
            					return typeQualifierList;
            				}
            				newList = typeQualifierList;
            				break;
            				
            			case NodeItem.PARAM_DECLARATION:
            				ParamList paramList = new ParamList();
            				paramList.setLineNum(((ParamDeclaration)nodeItem).getLineNum());
            				paramList.setPos(((ParamDeclaration)nodeItem).getPos());
            				paramList.setParamDeclaration((ParamDeclaration)nodeItem);
            				paramList.setParamList(null);
            				if(last == null){        					
            					return paramList;
            				}
            				newList = paramList;
            				break;
            				
            			case NodeItem.INITIALIZER:
            				InitializerList initializerList = new InitializerList();
            				initializerList.setInitializer((Initializer)nodeItem);
            				initializerList.setInitializerList(null);
            				initializerList.setLineNum(((Initializer)nodeItem).getLineNum());
            				initializerList.setPos(((Initializer)nodeItem).getPos());
            				if(last == null){        					
            					return initializerList;
            				}
            				newList = initializerList;
            				break;
            				
            			case NodeItem.ASSIGNMENT_EXPR:
            				ArgumentExpressionList argumentExpressionList = new ArgumentExpressionList();
            				argumentExpressionList.setAssignmentExpr((AssignmentExpr)nodeItem);
            				argumentExpressionList.setArgumentExpressionList(null);
            				argumentExpressionList.setLineNum(((AssignmentExpr)nodeItem).getLineNum());
            				argumentExpressionList.setPos(((AssignmentExpr)nodeItem).getPos());
            				if(last == null) {        					
            					return argumentExpressionList;
            				}
            				newList = argumentExpressionList;
            				break;
            				
            			case NodeItem.BLOCK_ITEM:
            				BlockItemList blockItemList = new BlockItemList();
            				blockItemList.setBlockItem((BlockItem)nodeItem);
            				blockItemList.setBlockItemList(null);
            				blockItemList.setLineNum(((BlockItem)nodeItem).getLineNum());
            				blockItemList.setPos(((BlockItem)nodeItem).getPos());
            				if(last == null) {        					
            					return blockItemList;
            				}
            				newList = blockItemList;
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
    			catch(Exception e){
    				return newList;
    			}
            	}
            	
            		private RootExpr addAssignmentExpToExpr(AssignmentExpr assignmentExpr, RootExpr rootExpr){
                		
                		try{
    				if(rootExpr == null){
                			rootExpr = new RootExpr();
                			rootExpr.setAssignmentExpr(assignmentExpr);
                			rootExpr.setLineNum(assignmentExpr.getLineNum());
                			rootExpr.setPos(assignmentExpr.getPos());
                			rootExpr.setRootExprNext(null);
                			return rootExpr;
                		}
                		else{
                			RootExpr last = rootExpr;
                			RootExpr current = rootExpr;
                			
                			while(true){
                				if(current.getRootExprNext() == null){
                					last = current;
                					break;
                				}
                				
                				current = current.getRootExprNext();
                			}
                			
                			RootExpr newRootExpr = new RootExpr();
                			newRootExpr.setAssignmentExpr(assignmentExpr);
                			newRootExpr.setRootExprNext(null);
                			last.setRootExprNext(newRootExpr);
                			
                			return rootExpr;
                			
                		}
    			}
    			catch(Exception e){
    				return null;
    			}
       	}
                	
            	
        	private DeclarationSpecifiers  addUnitToDeclSpecs(DeclSpecUnit declSpecUnit, DeclarationSpecifiers declSpecs){
        	    		
        		try{
    				int declSpecUnitType = declSpecUnit.getDecSpecType();
        		DeclarationSpecifiers newDeclSpecs = new DeclarationSpecifiers();
        		if(declSpecs == null){
        			declSpecs = newDeclSpecs;
        			int declSpecType = declSpecUnit.getDecSpecType();
        			if(declSpecType == declSpecUnit.STORAGE_CLASS_SPECS){
    	    			declSpecs.setLineNum(((StorageClassSpecifiers)declSpecUnit).getLineNum());
    	    			declSpecs.setPos(((StorageClassSpecifiers)declSpecUnit).getPos());
    	    		}
    	    		else if(declSpecType == declSpecUnit.TYPE_QUALIFIER){
    	    			declSpecs.setLineNum(((TypeQualifier)declSpecUnit).getLineNum());
    	    			declSpecs.setPos(((TypeQualifier)declSpecUnit).getPos());
    	    		}
    	    		else{  // Must be type specifier
    	    			declSpecs.setLineNum(((TypeSpecifier)declSpecUnit).getLineNum());
    	    			declSpecs.setPos(((TypeSpecifier)declSpecUnit).getPos());
    	    		}
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
    			catch(Exception e){
    				return null;
    			}
        	}
        	
        	private SpecifierQualifierList addTypeSpecOrTypeQualToSpecQualList(TypeSpecifier typeSpecifier, 
        			TypeQualifier typeQualifier,  SpecifierQualifierList list){
        		try{
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
    			catch(Exception e){
    				return null;
    			}	
        		
        	}
        	
        	private void addToDirectDeclarator(DirectDeclarator directDeclarator, int type, RootExpr rootExpr, 
        			ParamTypeList parameterTypeList, IdentifierList identifierList, int lineNum, int pos ){
        		try{
    				DirectDeclarator last = directDeclarator;
        		DirectDeclarator current = directDeclarator;
        		
        		while(true){
        			if(current.getDirectDeclarator() == null){
        				last = current;
        				break;
        			}
        			else
        				current = current.getDirectDeclarator();
        		}
        		
        		DirectDeclarator newDD = new DirectDeclarator(null, null, null, rootExpr,  parameterTypeList, type);
        		
        		last.setDirectDeclarator(newDD);
    			}
    			catch(Exception e){
    			}
        	}
        	
        	
        	private void addToAbstractDirectDeclarator(ConstExpr constExpr, ParamTypeList paramTypeList, DirectAbstractDeclarator
        				abstractDirectDeclarator){
        		try{
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
    			catch(Exception e){
    			}
        	}
        	
        	private void addToPostFixExpr(RootExpr arrayExpr, ArgumentExpressionList argumentExpressionList, int operator, 
            				String id, PostfixExpr postFixExpr){
            		try{
    				PostfixExpr last = postFixExpr;
            		PostfixExpr current = postFixExpr;
            		
            		while(true){
            			if(current.getPostfixExpr() == null){
            				last = current;
            				break;
            			}
            			else
                			current = current.getPostfixExpr();
            		}
            		
            		PostfixExpr newPfe = new PostfixExpr();
            		newPfe.setType(operator);
            		newPfe.setLineNum(last.getLineNum());
            		newPfe.setPos(last.getPos());
            		
            		if(arrayExpr != null){
            			newPfe.setArrayExpr(arrayExpr);
            		}
            		if(argumentExpressionList != null){
            			newPfe.setArgumentExpressionList(argumentExpressionList);
            		}
            		
            		if(id != null){
            			newPfe.setIdentifier(id);
            		}
            		
            		last.setPostfixExpr(newPfe);
    			}
    			catch(Exception e){
    			}
            	}
            	
            	private int getIdForSymbol(String symbol, Object obj){
            	
            		try{
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
    			catch(Exception e){
    				return -1;
    			}
            	
            		
            	}
            	
            	private void setLineNumAndPos(FunctionDef functionDef, DeclarationSpecifiers declSpecifiers){
            		try{
    				functionDef.setLineNum(declSpecifiers.getLineNum());
            		functionDef.setPos(declSpecifiers.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	 private void setLineNumAndPos(FunctionDef functionDef, Declarator declarator){
            		try{
    				functionDef.setLineNum(declarator.getLineNum());
            		functionDef.setPos(declarator.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(ExternDeclaration externDeclaration, FunctionDef functionDef){
            		try{
    				externDeclaration.setLineNum(functionDef.getLineNum());
            		externDeclaration.setPos(functionDef.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(ExternDeclaration externDeclaration, Declaration declaration){
            		try{
    				externDeclaration.setLineNum(declaration.getLineNum());
            		externDeclaration.setPos(declaration.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(ExternDeclaration externDeclaration, IncludeDirective includeDirective){
            		try{
    				externDeclaration.setLineNum(includeDirective.getLineNum());
            		externDeclaration.setPos(includeDirective.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(Declaration declaration, DeclarationSpecifiers declarationSpecifiers){
            		try{
    				declaration.setLineNum(declarationSpecifiers.getLineNum());
            		declaration.setPos(declarationSpecifiers.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(TypeSpecifier typeSpecifier, StructOrUnionSpecifier structOrUnionSpecifier){
            		try{
    				typeSpecifier.setLineNum(structOrUnionSpecifier.getLineNum());
            		typeSpecifier.setPos(structOrUnionSpecifier.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(TypeSpecifier typeSpecifier, EnumSpecifier enumSpecifier){
            		try{
    				typeSpecifier.setLineNum(enumSpecifier.getLineNum());
            		typeSpecifier.setPos(enumSpecifier.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(TypeSpecifier typeSpecifier, TypeOf typeOf){
            		try{
    				typeSpecifier.setLineNum(typeOf.getLineNum());
            		typeSpecifier.setPos(typeOf.getPos());
    			}
    			catch(Exception e){
    			}
            	}
            	
            	private void setLineNumAndPos(BlockItem blockItem, Declaration declaration){
            		try{
    				blockItem.setLineNum(declaration.getLineNum());
            		blockItem.setPos(declaration.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
            	
            	private void setLineNumAndPos(BlockItem blockItem, Statement statement){
            		try{
    				blockItem.setLineNum(statement.getLineNum());
            		blockItem.setPos(statement.getPos());
    			}
    			catch(Exception e){
    			}
            		
            	}
     
    		private void setLineNumAndPos(StructOrUnionSpecifier structOrUnionSpecifier, StructOrUnion structOrUnion){
    			try{
    				structOrUnionSpecifier.setLineNum(structOrUnion.getLineNum()); structOrUnionSpecifier.setPos(structOrUnion.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}

    		private void setLineNumAndPos(StructOrUnion structOrUnion, int num, int pos){
    			try{
    				structOrUnion.setLineNum(num); structOrUnion.setPos(pos);
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(InitDeclarator initDeclarator, Declarator declarator){
    			try{
    				initDeclarator.setLineNum(declarator.getLineNum()); initDeclarator.setPos(declarator.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(StructDeclaration structDeclaration, StructDeclaratorList structDeclaratorList){
    			try{
    				structDeclaration.setLineNum(structDeclaratorList.getLineNum()); structDeclaration.setPos(structDeclaratorList.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(StructDeclaratorList structDeclaratorList, StructDeclarator structDeclarator){
    			try{
    				structDeclaratorList.setLineNum(structDeclarator.getLineNum()); structDeclaratorList.setPos(structDeclarator.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(SpecifierQualifierList specifierQualifierList, TypeSpecifier typeSpecifier ){
    			try{
    				specifierQualifierList.setLineNum(typeSpecifier.getLineNum()); specifierQualifierList.setPos(typeSpecifier.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(SpecifierQualifierList specifierQualifierList, TypeQualifier typeQualifier ){
    			try{
    				specifierQualifierList.setLineNum(typeQualifier.getLineNum()); specifierQualifierList.setPos(typeQualifier.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(StructDeclarator structDeclarator, Declarator declarator ){
    			try{
    				structDeclarator.setLineNum(declarator.getLineNum()); structDeclarator.setPos(declarator.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(EnumSpecifier enumSpecifier, int line, int pos){
    			try{
    				enumSpecifier.setLineNum(line); enumSpecifier.setPos(pos);
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(Enumerator enumerator, int line, int pos){
    			try{
    				enumerator.setLineNum(line); enumerator.setPos(pos);
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(Declarator declarator, Pointer pointer){
    			try{
    				declarator.setLineNum(pointer.getLineNum()); declarator.setPos(pointer.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(Declarator declarator, DirectDeclarator directDeclarator ){
    			try{
    				declarator.setLineNum(directDeclarator.getLineNum()); declarator.setPos(directDeclarator.getPos());
    			}
    			catch(Exception e){
    			}
    			
    		}
    		
    		private void setLineNumAndPos(DirectDeclarator directDeclarator, int line, int pos ){
    			try{
    				directDeclarator.setLineNum(line); directDeclarator.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(DirectDeclarator directDeclarator, Declarator declarator ){
    			try{
    				directDeclarator.setLineNum(declarator.getLineNum()); directDeclarator.setPos(declarator.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(Pointer pointer, int line, int pos ){
    			try{
    				pointer.setLineNum(line); pointer.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ParamTypeList paramTypeList, ParamList paramList){
    			try{
    				paramTypeList.setLineNum(paramList.getLineNum()); paramTypeList.setPos(paramList.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ParamDeclaration paramDeclaration, DeclarationSpecifiers declarationSpecifiers){
    			try{
    				paramDeclaration.setLineNum(declarationSpecifiers.getLineNum()); paramDeclaration.setPos(declarationSpecifiers.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(TypeSpecifier typeSpec, TypeDefName tName){
    			try{
    				typeSpec.setLineNum(tName.getLineNum()); typeSpec.setPos(tName.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(Initializer initializer, AssignmentExpr assignmentExpr){
    			try{
    				initializer.setLineNum(assignmentExpr.getLineNum()); initializer.setPos(assignmentExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(Initializer initializer, InitializerList initializerList){
    			try{
    				initializer.setLineNum(initializerList.getLineNum()); initializer.setPos(initializerList.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(Initializer initializer, CompoundStatement compoundStatement){
    			try{
    				initializer.setLineNum(compoundStatement.getLineNum()); initializer.setPos(compoundStatement.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(TypeName typeName, SpecifierQualifierList specifierQualifierList ){
    			try{
    				typeName.setLineNum(specifierQualifierList.getLineNum()); typeName.setPos(specifierQualifierList.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(AbstractDeclarator abstractDeclarator, DirectAbstractDeclarator directAbstractDeclarator){
    			try{
    				abstractDeclarator.setLineNum(directAbstractDeclarator.getLineNum()); abstractDeclarator.setPos(directAbstractDeclarator.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(DirectAbstractDeclarator directAbstractDeclarator, int line, int pos){
    			try{
    				directAbstractDeclarator.setLineNum(line); directAbstractDeclarator.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(Statement parent, Statement child){
    			try{
    				parent.setLineNum(child.getLineNum()); parent.setPos(child.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(LabeledStatement labeledStatement, int line, int pos){
    			try{
    				labeledStatement.setLineNum(line); labeledStatement.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ExprStatement exprStatement, int line, int pos){
    			try{
    				exprStatement.setLineNum(line); exprStatement.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ExprStatement exprStatement, RootExpr rootExpr){
    			try{
    				exprStatement.setLineNum(rootExpr.getLineNum()); exprStatement.setPos(rootExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(CompoundStatement compoundStatement, int line, int pos){
    			try{
    				compoundStatement.setLineNum(line); compoundStatement.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(SelectionStatement selectionStatement, int line, int pos){
    			try{
    				selectionStatement.setLineNum(line); selectionStatement.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(IterationStatement iterationStatement, int line, int pos){
    			try{
    				iterationStatement.setLineNum(line); iterationStatement.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(JumpStatement jumpStatement, int line, int pos){
    			try{
    				jumpStatement.setLineNum(line); jumpStatement.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setReturnExprToJumpStmt(JumpStatement jumpStatement, RootExpr returnExpr){
    			try{
    				jumpStatement.setReturnExpr(returnExpr);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(AssignmentExpr assignmentExpr, UnaryExpr unaryExpr){
    			try{
    				assignmentExpr.setLineNum(unaryExpr.getLineNum()); assignmentExpr.setPos(unaryExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(AssignmentExpr assignmentExpr, ConditionalExpr conditionalExpr){
    			try{
    				assignmentExpr.setLineNum(conditionalExpr.getLineNum()); assignmentExpr.setPos(conditionalExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(AssignmentOperator assignmentOperator, int line, int pos){
    			try{
    				assignmentOperator.setLineNum(line); assignmentOperator.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ConditionalExpr conditionalExpr, LogicalOrExpr logicalOrExpr){
    			try{
    				conditionalExpr.setLineNum(logicalOrExpr.getLineNum()); conditionalExpr.setPos(logicalOrExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ConstExpr constExpr, ConditionalExpr conditionalExpr){
    			try{
    				constExpr.setLineNum(conditionalExpr.getLineNum()); constExpr.setPos(conditionalExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(LogicalOrExpr logicalOrExpr, LogicalAndExpr logicalAndExpr){
    			try{
    				logicalOrExpr.setLineNum(logicalAndExpr.getLineNum()); logicalOrExpr.setPos(logicalAndExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(LogicalAndExpr logicalAndExpr, InclusiveOrExpr inclusiveOrExpr){
    			try{
    				logicalAndExpr.setLineNum(inclusiveOrExpr.getLineNum()); logicalAndExpr.setPos(inclusiveOrExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(InclusiveOrExpr inclusiveOrExpr, ExclusiveOrExpr exclusiveOrExpr){
    			try{
    				inclusiveOrExpr.setLineNum(exclusiveOrExpr.getLineNum()); inclusiveOrExpr.setPos(exclusiveOrExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ExclusiveOrExpr exclusiveOrExpr, AndExpr andExpr){
    			try{
    				exclusiveOrExpr.setLineNum(andExpr.getLineNum()); exclusiveOrExpr.setPos(andExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(AndExpr andExpr, EqualityExpr equalityExpr){
    			try{
    				andExpr.setLineNum(equalityExpr.getLineNum()); andExpr.setPos(equalityExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(EqualityExpr equalityExpr, RelationalExpr relationalExpr){
    			try{
    				equalityExpr.setLineNum(relationalExpr.getLineNum()); equalityExpr.setPos(relationalExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(RelationalExpr relationalExpr, ShiftExpr shiftExpr){
    			try{
    				relationalExpr.setLineNum(shiftExpr.getLineNum()); relationalExpr.setPos(shiftExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(ShiftExpr shiftExpr, AdditiveExpr additiveExpr){
    			try{
    				shiftExpr.setLineNum(additiveExpr.getLineNum()); shiftExpr.setPos(additiveExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(AdditiveExpr additiveExpr, MultiplicativeExpr multiplicativeExpr){
    			try{
    				additiveExpr.setLineNum(multiplicativeExpr.getLineNum()); additiveExpr.setPos(multiplicativeExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(MultiplicativeExpr multiplicativeExpr,  CastExpr castExpr){
    			try{
    				multiplicativeExpr.setLineNum(castExpr.getLineNum()); multiplicativeExpr.setPos(castExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(CastExpr castExpr,  UnaryExpr unaryExpr){
    			try{
    				castExpr.setLineNum(unaryExpr.getLineNum()); castExpr.setPos(unaryExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(CastExpr castExpr,  int line, int pos){
    			try{
    				castExpr.setLineNum(line); castExpr.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(UnaryExpr unaryExpr,  PostfixExpr postfixExpr){
    			try{
    				unaryExpr.setLineNum(postfixExpr.getLineNum()); unaryExpr.setPos(postfixExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(UnaryExpr unaryExpr,  int line, int pos){
    			try{
    				unaryExpr.setLineNum(line); unaryExpr.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}

    		private void setLineNumAndPos(PostfixExpr postfixExpr,  PrimaryExpr primaryExpr){
    			try{
    				postfixExpr.setLineNum(primaryExpr.getLineNum()); postfixExpr.setPos(primaryExpr.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(PrimaryExpr primaryExpr,  Constant constant){
    			try{
    				primaryExpr.setLineNum(constant.getLineNum()); primaryExpr.setPos(constant.getPos());
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(PrimaryExpr primaryExpr,  int line, int pos){
    			try{
    				primaryExpr.setLineNum(line); primaryExpr.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		private void setLineNumAndPos(Constant constant,  int line, int pos){
    			try{
    				constant.setLineNum(line); constant.setPos(pos);
    			}
    			catch(Exception e){
    			}
    		}
    		
    		public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
            	String hdr = getErrorHeader(e);
            	String msg = getErrorMessage(e, tokenNames);
            	errors.push(hdr + "  " + msg);
            }
            
            public Stack<String> getErrorMsgs(){
            	return errors;
            }			  



    // $ANTLR start "translation_unit"
    // resources/Clang/ClangLL.g:1259:1: translation_unit returns [TranslationUnit value] : (e= extern_declaration )+ ;
    public final TranslationUnit translation_unit() throws RecognitionException {
        TranslationUnit value = null;
        int translation_unit_StartIndex = input.index();
        ExternDeclaration e = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return value; }
            // resources/Clang/ClangLL.g:1259:50: ( (e= extern_declaration )+ )
            // resources/Clang/ClangLL.g:1260:3: (e= extern_declaration )+
            {
            // resources/Clang/ClangLL.g:1260:3: (e= extern_declaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                alt1 = dfa1.predict(input);
                switch (alt1) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1260:4: e= extern_declaration
            	    {
            	    pushFollow(FOLLOW_extern_declaration_in_translation_unit1236);
            	    e=extern_declaration();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addExternDecToTransUnit(value, e);
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


    // $ANTLR start "extern_declaration"
    // resources/Clang/ClangLL.g:1262:1: extern_declaration returns [ExternDeclaration value] options {k=1; } : ( ( (e1= declaration_specifiers )? e2= declarator ( declaration )* '{' )=>e3= function_definition | (e4= declaration ) | (e5= include_directive ) );
    public final ExternDeclaration extern_declaration() throws RecognitionException {
        ExternDeclaration value = null;
        int extern_declaration_StartIndex = input.index();
        FunctionDef e3 = null;

        Declaration e4 = null;

        IncludeDirective e5 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return value; }
            // resources/Clang/ClangLL.g:1263:2: ( ( (e1= declaration_specifiers )? e2= declarator ( declaration )* '{' )=>e3= function_definition | (e4= declaration ) | (e5= include_directive ) )
            int alt2=3;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // resources/Clang/ClangLL.g:1263:4: ( (e1= declaration_specifiers )? e2= declarator ( declaration )* '{' )=>e3= function_definition
                    {
                    pushFollow(FOLLOW_function_definition_in_extern_declaration1283);
                    e3=function_definition();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e3; setLineNumAndPos(value, e3);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1264:4: (e4= declaration )
                    {
                    // resources/Clang/ClangLL.g:1264:4: (e4= declaration )
                    // resources/Clang/ClangLL.g:1264:5: e4= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_extern_declaration1293);
                    e4=declaration();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e4; setLineNumAndPos(value, e4);
                    }

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1265:4: (e5= include_directive )
                    {
                    // resources/Clang/ClangLL.g:1265:4: (e5= include_directive )
                    // resources/Clang/ClangLL.g:1265:5: e5= include_directive
                    {
                    pushFollow(FOLLOW_include_directive_in_extern_declaration1305);
                    e5=include_directive();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e5; setLineNumAndPos(value, e5);
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
            if ( state.backtracking>0 ) { memoize(input, 2, extern_declaration_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "extern_declaration"


    // $ANTLR start "include_directive"
    // resources/Clang/ClangLL.g:1267:1: include_directive returns [IncludeDirective value] : (e0= HASH e1= filename_lib HASH | HASH e2= STRING_LITERAL HASH );
    public final IncludeDirective include_directive() throws RecognitionException {
        IncludeDirective value = null;
        int include_directive_StartIndex = input.index();
        Token e0=null;
        Token e2=null;
        FileNameLib e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return value; }
            // resources/Clang/ClangLL.g:1267:52: (e0= HASH e1= filename_lib HASH | HASH e2= STRING_LITERAL HASH )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==HASH) ) {
                int LA3_1 = input.LA(2);

                if ( (synpred6_ClangLL()) ) {
                    alt3=1;
                }
                else if ( (true) ) {
                    alt3=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // resources/Clang/ClangLL.g:1268:2: e0= HASH e1= filename_lib HASH
                    {
                    e0=(Token)match(input,HASH,FOLLOW_HASH_in_include_directive1324); if (state.failed) return value;
                    pushFollow(FOLLOW_filename_lib_in_include_directive1328);
                    e1=filename_lib();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,HASH,FOLLOW_HASH_in_include_directive1330); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IncludeDirective((e0!=null?e0.getLine():0), e1, IncludeDirective.LIB);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1271:3: HASH e2= STRING_LITERAL HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_include_directive1344); if (state.failed) return value;
                    e2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_include_directive1348); if (state.failed) return value;
                    match(input,HASH,FOLLOW_HASH_in_include_directive1350); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IncludeDirective((e2!=null?e2.getLine():0), (e2!=null?e2.getText():null), IncludeDirective.LOCAL);
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
            if ( state.backtracking>0 ) { memoize(input, 3, include_directive_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "include_directive"


    // $ANTLR start "filename_lib"
    // resources/Clang/ClangLL.g:1274:1: filename_lib returns [FileNameLib value] : e= ID ( (d1= DOT e1= ID ) )* (d= DIVIDE e3= ID ( (d2= DOT e2= ID ) )* )* ;
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return value; }
            // resources/Clang/ClangLL.g:1274:42: (e= ID ( (d1= DOT e1= ID ) )* (d= DIVIDE e3= ID ( (d2= DOT e2= ID ) )* )* )
            // resources/Clang/ClangLL.g:1275:4: e= ID ( (d1= DOT e1= ID ) )* (d= DIVIDE e3= ID ( (d2= DOT e2= ID ) )* )*
            {
            e=(Token)match(input,ID,FOLLOW_ID_in_filename_lib1372); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new FileNameLib((e!=null?e.getLine():0), (e!=null?e.getText():null));
            }
            // resources/Clang/ClangLL.g:1275:55: ( (d1= DOT e1= ID ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==DOT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1275:56: (d1= DOT e1= ID )
            	    {
            	    // resources/Clang/ClangLL.g:1275:56: (d1= DOT e1= ID )
            	    // resources/Clang/ClangLL.g:1275:57: d1= DOT e1= ID
            	    {
            	    d1=(Token)match(input,DOT,FOLLOW_DOT_in_filename_lib1380); if (state.failed) return value;
            	    e1=(Token)match(input,ID,FOLLOW_ID_in_filename_lib1384); if (state.failed) return value;

            	    }

            	    if ( state.backtracking==0 ) {
            	      if((d1!=null?d1.getText():null) != null && (e1!=null?e1.getText():null) != null) value.addToFileName("." +(e1!=null?e1.getText():null));
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // resources/Clang/ClangLL.g:1275:153: (d= DIVIDE e3= ID ( (d2= DOT e2= ID ) )* )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==DIVIDE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1275:154: d= DIVIDE e3= ID ( (d2= DOT e2= ID ) )*
            	    {
            	    d=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_filename_lib1394); if (state.failed) return value;
            	    e3=(Token)match(input,ID,FOLLOW_ID_in_filename_lib1398); if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      if((e3!=null?e3.getText():null) != null) value.addToFileName("/" +(e3!=null?e3.getText():null));
            	    }
            	    // resources/Clang/ClangLL.g:1275:229: ( (d2= DOT e2= ID ) )*
            	    loop5:
            	    do {
            	        int alt5=2;
            	        int LA5_0 = input.LA(1);

            	        if ( (LA5_0==DOT) ) {
            	            alt5=1;
            	        }


            	        switch (alt5) {
            	    	case 1 :
            	    	    // resources/Clang/ClangLL.g:1275:230: (d2= DOT e2= ID )
            	    	    {
            	    	    // resources/Clang/ClangLL.g:1275:230: (d2= DOT e2= ID )
            	    	    // resources/Clang/ClangLL.g:1275:231: d2= DOT e2= ID
            	    	    {
            	    	    d2=(Token)match(input,DOT,FOLLOW_DOT_in_filename_lib1406); if (state.failed) return value;
            	    	    e2=(Token)match(input,ID,FOLLOW_ID_in_filename_lib1410); if (state.failed) return value;

            	    	    }

            	    	    if ( state.backtracking==0 ) {
            	    	      if((d2!=null?d2.getText():null) != null && (e2!=null?e2.getText():null) != null) value.addToFileName("." +(e2!=null?e2.getText():null));
            	    	    }

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop5;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, filename_lib_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "filename_lib"


    // $ANTLR start "function_definition"
    // resources/Clang/ClangLL.g:1277:1: function_definition returns [FunctionDef value] : (e1= declaration_specifiers )? e2= declarator e3= compound_statement ;
    public final FunctionDef function_definition() throws RecognitionException {
        FunctionDef value = null;
        int function_definition_StartIndex = input.index();
        DeclarationSpecifiers e1 = null;

        Declarator e2 = null;

        CompoundStatement e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return value; }
            // resources/Clang/ClangLL.g:1277:48: ( (e1= declaration_specifiers )? e2= declarator e3= compound_statement )
            // resources/Clang/ClangLL.g:1278:4: (e1= declaration_specifiers )? e2= declarator e3= compound_statement
            {
            // resources/Clang/ClangLL.g:1278:4: (e1= declaration_specifiers )?
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // resources/Clang/ClangLL.g:1278:5: e1= declaration_specifiers
                    {
                    pushFollow(FOLLOW_declaration_specifiers_in_function_definition1435);
                    e1=declaration_specifiers();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new FunctionDef(); value.setDeclSpecifiers(e1); setLineNumAndPos(value, e1);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_declarator_in_function_definition1447);
            e2=declarator();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              if(value == null) value = new FunctionDef(); value.setDeclarator(e2);setLineNumAndPos(value, e2);
            }
            pushFollow(FOLLOW_compound_statement_in_function_definition1456);
            e3=compound_statement();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value.setCompoundStmt(e3);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, function_definition_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "function_definition"


    // $ANTLR start "declaration"
    // resources/Clang/ClangLL.g:1282:1: declaration returns [Declaration value] : (e1= declaration_specifiers (e2= init_declarator_list )? SEMICOLON | TYPEDEF (e3= declaration_specifiers )? e4= init_declarator_list SEMICOLON );
    public final Declaration declaration() throws RecognitionException {
        Declaration value = null;
        int declaration_StartIndex = input.index();
        DeclarationSpecifiers e1 = null;

        InitDeclaratorList e2 = null;

        DeclarationSpecifiers e3 = null;

        InitDeclaratorList e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return value; }
            // resources/Clang/ClangLL.g:1283:2: (e1= declaration_specifiers (e2= init_declarator_list )? SEMICOLON | TYPEDEF (e3= declaration_specifiers )? e4= init_declarator_list SEMICOLON )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // resources/Clang/ClangLL.g:1283:4: e1= declaration_specifiers (e2= init_declarator_list )? SEMICOLON
                    {
                    pushFollow(FOLLOW_declaration_specifiers_in_declaration1474);
                    e1=declaration_specifiers();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Declaration(); value.setDeclSpecifiers(e1); setLineNumAndPos(value, e1); 
                    }
                    // resources/Clang/ClangLL.g:1284:3: (e2= init_declarator_list )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==LPAREN||LA8_0==STAR||LA8_0==ID) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1284:4: e2= init_declarator_list
                            {
                            pushFollow(FOLLOW_init_declarator_list_in_declaration1483);
                            e2=init_declarator_list();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setInitDeclaratorList(e2); 
                            }

                            }
                            break;

                    }

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declaration1489); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1285:6: TYPEDEF (e3= declaration_specifiers )? e4= init_declarator_list SEMICOLON
                    {
                    match(input,TYPEDEF,FOLLOW_TYPEDEF_in_declaration1496); if (state.failed) return value;
                    // resources/Clang/ClangLL.g:1285:14: (e3= declaration_specifiers )?
                    int alt9=2;
                    alt9 = dfa9.predict(input);
                    switch (alt9) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1285:15: e3= declaration_specifiers
                            {
                            pushFollow(FOLLOW_declaration_specifiers_in_declaration1501);
                            e3=declaration_specifiers();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new Declaration(); value.setTypeDefDeclaration(true); value.setDeclSpecifiers(e3); setLineNumAndPos(value, e3);
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_init_declarator_list_in_declaration1513);
                    e4=init_declarator_list();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setInitDeclaratorList(e4); 
                    }
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declaration1517); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, declaration_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "declaration"


    // $ANTLR start "declaration_specifiers"
    // resources/Clang/ClangLL.g:1288:1: declaration_specifiers returns [DeclarationSpecifiers value] : (e1= storage_class_specifier | e2= type_specifier | e3= type_qualifier )+ ;
    public final DeclarationSpecifiers declaration_specifiers() throws RecognitionException {
        DeclarationSpecifiers value = null;
        int declaration_specifiers_StartIndex = input.index();
        StorageClassSpecifiers e1 = null;

        TypeSpecifier e2 = null;

        TypeQualifier e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return value; }
            // resources/Clang/ClangLL.g:1289:2: ( (e1= storage_class_specifier | e2= type_specifier | e3= type_qualifier )+ )
            // resources/Clang/ClangLL.g:1289:4: (e1= storage_class_specifier | e2= type_specifier | e3= type_qualifier )+
            {
            // resources/Clang/ClangLL.g:1289:4: (e1= storage_class_specifier | e2= type_specifier | e3= type_qualifier )+
            int cnt11=0;
            loop11:
            do {
                int alt11=4;
                alt11 = dfa11.predict(input);
                switch (alt11) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1289:5: e1= storage_class_specifier
            	    {
            	    pushFollow(FOLLOW_storage_class_specifier_in_declaration_specifiers1534);
            	    e1=storage_class_specifier();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addUnitToDeclSpecs(e1, value); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/ClangLL.g:1290:4: e2= type_specifier
            	    {
            	    pushFollow(FOLLOW_type_specifier_in_declaration_specifiers1543);
            	    e2=type_specifier();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addUnitToDeclSpecs(e2, value);
            	    }

            	    }
            	    break;
            	case 3 :
            	    // resources/Clang/ClangLL.g:1291:4: e3= type_qualifier
            	    {
            	    pushFollow(FOLLOW_type_qualifier_in_declaration_specifiers1554);
            	    e3=type_qualifier();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addUnitToDeclSpecs(e3, value);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, declaration_specifiers_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "declaration_specifiers"


    // $ANTLR start "storage_class_specifier"
    // resources/Clang/ClangLL.g:1293:1: storage_class_specifier returns [StorageClassSpecifiers value] : (e1= AUTO | e2= REGISTER | e3= EXTERN | e4= STATIC );
    public final StorageClassSpecifiers storage_class_specifier() throws RecognitionException {
        StorageClassSpecifiers value = null;
        int storage_class_specifier_StartIndex = input.index();
        Token e1=null;
        Token e2=null;
        Token e3=null;
        Token e4=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return value; }
            // resources/Clang/ClangLL.g:1294:2: (e1= AUTO | e2= REGISTER | e3= EXTERN | e4= STATIC )
            int alt12=4;
            switch ( input.LA(1) ) {
            case AUTO:
                {
                alt12=1;
                }
                break;
            case REGISTER:
                {
                alt12=2;
                }
                break;
            case EXTERN:
                {
                alt12=3;
                }
                break;
            case STATIC:
                {
                alt12=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // resources/Clang/ClangLL.g:1294:4: e1= AUTO
                    {
                    e1=(Token)match(input,AUTO,FOLLOW_AUTO_in_storage_class_specifier1575); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StorageClassSpecifiers((e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0), StorageClassSpecifiers.AUTO);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1295:4: e2= REGISTER
                    {
                    e2=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_storage_class_specifier1584); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StorageClassSpecifiers((e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0), StorageClassSpecifiers.REGISTER); 
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1296:4: e3= EXTERN
                    {
                    e3=(Token)match(input,EXTERN,FOLLOW_EXTERN_in_storage_class_specifier1593); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StorageClassSpecifiers((e3!=null?e3.getLine():0), (e3!=null?e3.getCharPositionInLine():0), StorageClassSpecifiers.EXTERN);
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1297:4: e4= STATIC
                    {
                    e4=(Token)match(input,STATIC,FOLLOW_STATIC_in_storage_class_specifier1602); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StorageClassSpecifiers((e4!=null?e4.getLine():0), (e4!=null?e4.getCharPositionInLine():0), StorageClassSpecifiers.STATIC);
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
            if ( state.backtracking>0 ) { memoize(input, 8, storage_class_specifier_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "storage_class_specifier"


    // $ANTLR start "type_specifier"
    // resources/Clang/ClangLL.g:1300:1: type_specifier returns [TypeSpecifier value] : (e1= VOID | e2= CHAR | e3= SHORT | e4= INT | e5= LONG | e6= FLOAT | e7= DOUBLE | e8= SIGNED | e9= UNSIGNED | e10= struct_or_union_spec | e11= enum_spec | e12= typedef_name | TYPEOF e13= typeof );
    public final TypeSpecifier type_specifier() throws RecognitionException {
        TypeSpecifier value = null;
        int type_specifier_StartIndex = input.index();
        Token e1=null;
        Token e2=null;
        Token e3=null;
        Token e4=null;
        Token e5=null;
        Token e6=null;
        Token e7=null;
        Token e8=null;
        Token e9=null;
        StructOrUnionSpecifier e10 = null;

        EnumSpecifier e11 = null;

        TypeDefName e12 = null;

        TypeOf e13 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return value; }
            // resources/Clang/ClangLL.g:1301:2: (e1= VOID | e2= CHAR | e3= SHORT | e4= INT | e5= LONG | e6= FLOAT | e7= DOUBLE | e8= SIGNED | e9= UNSIGNED | e10= struct_or_union_spec | e11= enum_spec | e12= typedef_name | TYPEOF e13= typeof )
            int alt13=13;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // resources/Clang/ClangLL.g:1301:4: e1= VOID
                    {
                    e1=(Token)match(input,VOID,FOLLOW_VOID_in_type_specifier1622); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0), TypeSpecifier.VOID); 
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1302:4: e2= CHAR
                    {
                    e2=(Token)match(input,CHAR,FOLLOW_CHAR_in_type_specifier1631); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0), TypeSpecifier.CHAR); 
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1303:4: e3= SHORT
                    {
                    e3=(Token)match(input,SHORT,FOLLOW_SHORT_in_type_specifier1640); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e3!=null?e3.getLine():0), (e3!=null?e3.getCharPositionInLine():0), TypeSpecifier.SHORT); 
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1304:4: e4= INT
                    {
                    e4=(Token)match(input,INT,FOLLOW_INT_in_type_specifier1649); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e4!=null?e4.getLine():0), (e4!=null?e4.getCharPositionInLine():0), TypeSpecifier.INT); 
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/ClangLL.g:1305:4: e5= LONG
                    {
                    e5=(Token)match(input,LONG,FOLLOW_LONG_in_type_specifier1658); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e5!=null?e5.getLine():0), (e5!=null?e5.getCharPositionInLine():0), TypeSpecifier.LONG); 
                    }

                    }
                    break;
                case 6 :
                    // resources/Clang/ClangLL.g:1306:4: e6= FLOAT
                    {
                    e6=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_type_specifier1667); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e6!=null?e6.getLine():0), (e6!=null?e6.getCharPositionInLine():0), TypeSpecifier.FLOAT); 
                    }

                    }
                    break;
                case 7 :
                    // resources/Clang/ClangLL.g:1307:4: e7= DOUBLE
                    {
                    e7=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_type_specifier1676); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e7!=null?e7.getLine():0), (e7!=null?e7.getCharPositionInLine():0), TypeSpecifier.DOUBLE); 
                    }

                    }
                    break;
                case 8 :
                    // resources/Clang/ClangLL.g:1308:4: e8= SIGNED
                    {
                    e8=(Token)match(input,SIGNED,FOLLOW_SIGNED_in_type_specifier1685); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e8!=null?e8.getLine():0), (e8!=null?e8.getCharPositionInLine():0), TypeSpecifier.SIGNED); 
                    }

                    }
                    break;
                case 9 :
                    // resources/Clang/ClangLL.g:1309:4: e9= UNSIGNED
                    {
                    e9=(Token)match(input,UNSIGNED,FOLLOW_UNSIGNED_in_type_specifier1694); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeSpecifier((e9!=null?e9.getLine():0), (e9!=null?e9.getCharPositionInLine():0), TypeSpecifier.UNSIGNED); 
                    }

                    }
                    break;
                case 10 :
                    // resources/Clang/ClangLL.g:1310:4: e10= struct_or_union_spec
                    {
                    pushFollow(FOLLOW_struct_or_union_spec_in_type_specifier1703);
                    e10=struct_or_union_spec();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e10; setLineNumAndPos(value, e10); 
                    }

                    }
                    break;
                case 11 :
                    // resources/Clang/ClangLL.g:1311:4: e11= enum_spec
                    {
                    pushFollow(FOLLOW_enum_spec_in_type_specifier1712);
                    e11=enum_spec();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e11; setLineNumAndPos(value, e11);
                    }

                    }
                    break;
                case 12 :
                    // resources/Clang/ClangLL.g:1312:6: e12= typedef_name
                    {
                    pushFollow(FOLLOW_typedef_name_in_type_specifier1723);
                    e12=typedef_name();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e12; setLineNumAndPos(value, e12);
                    }

                    }
                    break;
                case 13 :
                    // resources/Clang/ClangLL.g:1313:4: TYPEOF e13= typeof
                    {
                    match(input,TYPEOF,FOLLOW_TYPEOF_in_type_specifier1730); if (state.failed) return value;
                    pushFollow(FOLLOW_typeof_in_type_specifier1734);
                    e13=typeof();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e13; setLineNumAndPos(value, e13);
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
            if ( state.backtracking>0 ) { memoize(input, 9, type_specifier_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "type_specifier"


    // $ANTLR start "typeof"
    // resources/Clang/ClangLL.g:1315:1: typeof returns [TypeOf value] : e1= LPAREN (e2= type_name | e3= expression ) RPAREN ;
    public final TypeOf typeof() throws RecognitionException {
        TypeOf value = null;
        int typeof_StartIndex = input.index();
        Token e1=null;
        TypeName e2 = null;

        RootExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return value; }
            // resources/Clang/ClangLL.g:1316:2: (e1= LPAREN (e2= type_name | e3= expression ) RPAREN )
            // resources/Clang/ClangLL.g:1316:4: e1= LPAREN (e2= type_name | e3= expression ) RPAREN
            {
            e1=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_typeof1752); if (state.failed) return value;
            // resources/Clang/ClangLL.g:1316:15: (e2= type_name | e3= expression )
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // resources/Clang/ClangLL.g:1316:16: e2= type_name
                    {
                    pushFollow(FOLLOW_type_name_in_typeof1758);
                    e2=type_name();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeOf((e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));value.setTypeName(e2);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1316:103: e3= expression
                    {
                    pushFollow(FOLLOW_expression_in_typeof1766);
                    e3=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeOf((e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));value.setExpression(e3);
                    }

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_typeof1772); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, typeof_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "typeof"


    // $ANTLR start "typedef_name"
    // resources/Clang/ClangLL.g:1318:1: typedef_name returns [TypeDefName value] : {...}?e= ID ;
    public final TypeDefName typedef_name() throws RecognitionException {
        TypeDefName value = null;
        int typedef_name_StartIndex = input.index();
        Token e=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return value; }
            // resources/Clang/ClangLL.g:1319:2: ({...}?e= ID )
            // resources/Clang/ClangLL.g:1319:5: {...}?e= ID
            {
            if ( !((shouldGetTypedefName(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return value;}
                throw new FailedPredicateException(input, "typedef_name", "shouldGetTypedefName(input.LT(1).getText())");
            }
            e=(Token)match(input,ID,FOLLOW_ID_in_typedef_name1792); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new TypeDefName((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0), (e!=null?e.getText():null));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, typedef_name_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "typedef_name"


    // $ANTLR start "type_qualifier"
    // resources/Clang/ClangLL.g:1321:1: type_qualifier returns [TypeQualifier value] : (e1= CONST | e2= VOLATILE );
    public final TypeQualifier type_qualifier() throws RecognitionException {
        TypeQualifier value = null;
        int type_qualifier_StartIndex = input.index();
        Token e1=null;
        Token e2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return value; }
            // resources/Clang/ClangLL.g:1322:2: (e1= CONST | e2= VOLATILE )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==CONST) ) {
                alt15=1;
            }
            else if ( (LA15_0==VOLATILE) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // resources/Clang/ClangLL.g:1322:4: e1= CONST
                    {
                    e1=(Token)match(input,CONST,FOLLOW_CONST_in_type_qualifier1810); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeQualifier((e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0), TypeQualifier.CONST);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1323:4: e2= VOLATILE
                    {
                    e2=(Token)match(input,VOLATILE,FOLLOW_VOLATILE_in_type_qualifier1819); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new TypeQualifier((e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0),TypeQualifier.VOLATILE);
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
            if ( state.backtracking>0 ) { memoize(input, 12, type_qualifier_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "type_qualifier"


    // $ANTLR start "struct_or_union_spec"
    // resources/Clang/ClangLL.g:1325:1: struct_or_union_spec returns [StructOrUnionSpecifier value] : (e1= struct_or_union (e2= ID )? LCURLY e3= struct_decl_list RCURLY | e4= struct_or_union (e5= ID ) );
    public final StructOrUnionSpecifier struct_or_union_spec() throws RecognitionException {
        StructOrUnionSpecifier value = null;
        int struct_or_union_spec_StartIndex = input.index();
        Token e2=null;
        Token e5=null;
        StructOrUnion e1 = null;

        StructDeclarationList e3 = null;

        StructOrUnion e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return value; }
            // resources/Clang/ClangLL.g:1326:2: (e1= struct_or_union (e2= ID )? LCURLY e3= struct_decl_list RCURLY | e4= struct_or_union (e5= ID ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=UNION && LA17_0<=STRUCT)) ) {
                int LA17_1 = input.LA(2);

                if ( (synpred35_ClangLL()) ) {
                    alt17=1;
                }
                else if ( (true) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // resources/Clang/ClangLL.g:1326:4: e1= struct_or_union (e2= ID )? LCURLY e3= struct_decl_list RCURLY
                    {
                    pushFollow(FOLLOW_struct_or_union_in_struct_or_union_spec1837);
                    e1=struct_or_union();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StructOrUnionSpecifier(); 
                      			value.setStructOrUnion(e1); value.setType(TypeSpecifier.STRUCT_OR_UNION_SPEC); setLineNumAndPos(value, e1); 
                    }
                    // resources/Clang/ClangLL.g:1328:3: (e2= ID )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ID) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1328:4: e2= ID
                            {
                            e2=(Token)match(input,ID,FOLLOW_ID_in_struct_or_union_spec1846); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setName((e2!=null?e2.getText():null)); 
                            }

                            }
                            break;

                    }

                    match(input,LCURLY,FOLLOW_LCURLY_in_struct_or_union_spec1857); if (state.failed) return value;
                    pushFollow(FOLLOW_struct_decl_list_in_struct_or_union_spec1861);
                    e3=struct_decl_list();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setStructDecList(e3); 
                    }
                    match(input,RCURLY,FOLLOW_RCURLY_in_struct_or_union_spec1865); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1330:4: e4= struct_or_union (e5= ID )
                    {
                    pushFollow(FOLLOW_struct_or_union_in_struct_or_union_spec1873);
                    e4=struct_or_union();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StructOrUnionSpecifier();  
                      			value.setStructOrUnion(e4); value.setType(TypeSpecifier.STRUCT_OR_UNION_SPEC); setLineNumAndPos(value, e4);
                    }
                    // resources/Clang/ClangLL.g:1332:4: (e5= ID )
                    // resources/Clang/ClangLL.g:1332:5: e5= ID
                    {
                    e5=(Token)match(input,ID,FOLLOW_ID_in_struct_or_union_spec1883); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setName((e5!=null?e5.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 13, struct_or_union_spec_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "struct_or_union_spec"


    // $ANTLR start "struct_or_union"
    // resources/Clang/ClangLL.g:1334:1: struct_or_union returns [StructOrUnion value] : (e1= UNION | e2= STRUCT );
    public final StructOrUnion struct_or_union() throws RecognitionException {
        StructOrUnion value = null;
        int struct_or_union_StartIndex = input.index();
        Token e1=null;
        Token e2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return value; }
            // resources/Clang/ClangLL.g:1335:2: (e1= UNION | e2= STRUCT )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==UNION) ) {
                alt18=1;
            }
            else if ( (LA18_0==STRUCT) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // resources/Clang/ClangLL.g:1335:4: e1= UNION
                    {
                    e1=(Token)match(input,UNION,FOLLOW_UNION_in_struct_or_union1903); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StructOrUnion(StructOrUnion.UNION); setLineNumAndPos(value, (e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1336:4: e2= STRUCT
                    {
                    e2=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_struct_or_union1912); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StructOrUnion(StructOrUnion.STRUCT); setLineNumAndPos(value, (e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 14, struct_or_union_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "struct_or_union"


    // $ANTLR start "struct_decl_list"
    // resources/Clang/ClangLL.g:1338:1: struct_decl_list returns [StructDeclarationList value ] : (e1= struct_declaration )+ ;
    public final StructDeclarationList struct_decl_list() throws RecognitionException {
        StructDeclarationList value = null;
        int struct_decl_list_StartIndex = input.index();
        StructDeclaration e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return value; }
            // resources/Clang/ClangLL.g:1339:2: ( (e1= struct_declaration )+ )
            // resources/Clang/ClangLL.g:1339:4: (e1= struct_declaration )+
            {
            // resources/Clang/ClangLL.g:1339:4: (e1= struct_declaration )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                alt19 = dfa19.predict(input);
                switch (alt19) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1339:5: e1= struct_declaration
            	    {
            	    pushFollow(FOLLOW_struct_declaration_in_struct_decl_list1931);
            	    e1=struct_declaration();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (StructDeclarationList)addNodeToCollection(e1 , value);
            	    }

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, struct_decl_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "struct_decl_list"


    // $ANTLR start "init_declarator_list"
    // resources/Clang/ClangLL.g:1341:1: init_declarator_list returns [InitDeclaratorList value] : e1= init_declarator ( COMMA e2= init_declarator )* ;
    public final InitDeclaratorList init_declarator_list() throws RecognitionException {
        InitDeclaratorList value = null;
        int init_declarator_list_StartIndex = input.index();
        InitDeclarator e1 = null;

        InitDeclarator e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return value; }
            // resources/Clang/ClangLL.g:1342:2: (e1= init_declarator ( COMMA e2= init_declarator )* )
            // resources/Clang/ClangLL.g:1342:4: e1= init_declarator ( COMMA e2= init_declarator )*
            {
            pushFollow(FOLLOW_init_declarator_in_init_declarator_list1951);
            e1=init_declarator();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = (InitDeclaratorList)addNodeToCollection( e1, value);
            }
            // resources/Clang/ClangLL.g:1343:4: ( COMMA e2= init_declarator )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==COMMA) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1343:5: COMMA e2= init_declarator
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_init_declarator_list1959); if (state.failed) return value;
            	    pushFollow(FOLLOW_init_declarator_in_init_declarator_list1964);
            	    e2=init_declarator();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (InitDeclaratorList)addNodeToCollection(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, init_declarator_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "init_declarator_list"


    // $ANTLR start "init_declarator"
    // resources/Clang/ClangLL.g:1345:1: init_declarator returns [InitDeclarator value] : e1= declarator ( ( ( WS )* ) ASSIGN ( ( WS )* ) e2= initializer )? ;
    public final InitDeclarator init_declarator() throws RecognitionException {
        InitDeclarator value = null;
        int init_declarator_StartIndex = input.index();
        Declarator e1 = null;

        Initializer e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return value; }
            // resources/Clang/ClangLL.g:1346:2: (e1= declarator ( ( ( WS )* ) ASSIGN ( ( WS )* ) e2= initializer )? )
            // resources/Clang/ClangLL.g:1346:4: e1= declarator ( ( ( WS )* ) ASSIGN ( ( WS )* ) e2= initializer )?
            {
            pushFollow(FOLLOW_declarator_in_init_declarator1984);
            e1=declarator();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new InitDeclarator(e1, null); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1347:4: ( ( ( WS )* ) ASSIGN ( ( WS )* ) e2= initializer )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==ASSIGN||LA23_0==WS) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // resources/Clang/ClangLL.g:1347:6: ( ( WS )* ) ASSIGN ( ( WS )* ) e2= initializer
                    {
                    // resources/Clang/ClangLL.g:1347:6: ( ( WS )* )
                    // resources/Clang/ClangLL.g:1347:7: ( WS )*
                    {
                    // resources/Clang/ClangLL.g:1347:7: ( WS )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==WS) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_init_declarator1995); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }

                    match(input,ASSIGN,FOLLOW_ASSIGN_in_init_declarator1999); if (state.failed) return value;
                    // resources/Clang/ClangLL.g:1347:19: ( ( WS )* )
                    // resources/Clang/ClangLL.g:1347:20: ( WS )*
                    {
                    // resources/Clang/ClangLL.g:1347:20: ( WS )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==WS) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_init_declarator2002); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }

                    pushFollow(FOLLOW_initializer_in_init_declarator2008);
                    e2=initializer();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setInitializer(e2);
                    }

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
            if ( state.backtracking>0 ) { memoize(input, 17, init_declarator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "init_declarator"


    // $ANTLR start "struct_declaration"
    // resources/Clang/ClangLL.g:1349:1: struct_declaration returns [StructDeclaration value] : e1= specifier_qualifier_list (e2= struct_declarator_list )* SEMICOLON ;
    public final StructDeclaration struct_declaration() throws RecognitionException {
        StructDeclaration value = null;
        int struct_declaration_StartIndex = input.index();
        SpecifierQualifierList e1 = null;

        StructDeclaratorList e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return value; }
            // resources/Clang/ClangLL.g:1350:2: (e1= specifier_qualifier_list (e2= struct_declarator_list )* SEMICOLON )
            // resources/Clang/ClangLL.g:1350:4: e1= specifier_qualifier_list (e2= struct_declarator_list )* SEMICOLON
            {
            pushFollow(FOLLOW_specifier_qualifier_list_in_struct_declaration2030);
            e1=specifier_qualifier_list();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new StructDeclaration(); value.setSpecifierQualifierList(e1);
            }
            // resources/Clang/ClangLL.g:1351:3: (e2= struct_declarator_list )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COLON||LA24_0==LPAREN||LA24_0==STAR||LA24_0==ID) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1351:4: e2= struct_declarator_list
            	    {
            	    pushFollow(FOLLOW_struct_declarator_list_in_struct_declaration2040);
            	    e2=struct_declarator_list();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.setStructDeclaratorList(e2); setLineNumAndPos(value, e2); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_struct_declaration2046); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 18, struct_declaration_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "struct_declaration"


    // $ANTLR start "specifier_qualifier_list"
    // resources/Clang/ClangLL.g:1353:1: specifier_qualifier_list returns [SpecifierQualifierList value] : (e1= type_specifier | e2= type_qualifier )+ ;
    public final SpecifierQualifierList specifier_qualifier_list() throws RecognitionException {
        SpecifierQualifierList value = null;
        int specifier_qualifier_list_StartIndex = input.index();
        TypeSpecifier e1 = null;

        TypeQualifier e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return value; }
            // resources/Clang/ClangLL.g:1354:2: ( (e1= type_specifier | e2= type_qualifier )+ )
            // resources/Clang/ClangLL.g:1354:4: (e1= type_specifier | e2= type_qualifier )+
            {
            // resources/Clang/ClangLL.g:1354:4: (e1= type_specifier | e2= type_qualifier )+
            int cnt25=0;
            loop25:
            do {
                int alt25=3;
                alt25 = dfa25.predict(input);
                switch (alt25) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1354:5: e1= type_specifier
            	    {
            	    pushFollow(FOLLOW_type_specifier_in_specifier_qualifier_list2063);
            	    e1=type_specifier();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addTypeSpecOrTypeQualToSpecQualList(e1, null,  value); setLineNumAndPos(value, e1);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/ClangLL.g:1355:5: e2= type_qualifier
            	    {
            	    pushFollow(FOLLOW_type_qualifier_in_specifier_qualifier_list2073);
            	    e2=type_qualifier();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addTypeSpecOrTypeQualToSpecQualList(null, e2, value); setLineNumAndPos(value, e2);
            	    }

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 19, specifier_qualifier_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "specifier_qualifier_list"


    // $ANTLR start "struct_declarator_list"
    // resources/Clang/ClangLL.g:1357:1: struct_declarator_list returns [StructDeclaratorList value] : e1= struct_declarator ( COMMA e2= struct_declarator )* ;
    public final StructDeclaratorList struct_declarator_list() throws RecognitionException {
        StructDeclaratorList value = null;
        int struct_declarator_list_StartIndex = input.index();
        StructDeclarator e1 = null;

        StructDeclarator e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return value; }
            // resources/Clang/ClangLL.g:1358:2: (e1= struct_declarator ( COMMA e2= struct_declarator )* )
            // resources/Clang/ClangLL.g:1358:4: e1= struct_declarator ( COMMA e2= struct_declarator )*
            {
            pushFollow(FOLLOW_struct_declarator_in_struct_declarator_list2094);
            e1=struct_declarator();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = (StructDeclaratorList)addNodeToCollection(e1, value); 
            }
            // resources/Clang/ClangLL.g:1359:3: ( COMMA e2= struct_declarator )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1359:4: COMMA e2= struct_declarator
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_struct_declarator_list2101); if (state.failed) return value;
            	    pushFollow(FOLLOW_struct_declarator_in_struct_declarator_list2105);
            	    e2=struct_declarator();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (StructDeclaratorList) addNodeToCollection(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 20, struct_declarator_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "struct_declarator_list"


    // $ANTLR start "struct_declarator"
    // resources/Clang/ClangLL.g:1361:1: struct_declarator returns [StructDeclarator value] : (e1= declarator ( COLON e2= constant_expression )? | COLON (e3= constant_expression )? );
    public final StructDeclarator struct_declarator() throws RecognitionException {
        StructDeclarator value = null;
        int struct_declarator_StartIndex = input.index();
        Declarator e1 = null;

        ConstExpr e2 = null;

        ConstExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return value; }
            // resources/Clang/ClangLL.g:1362:2: (e1= declarator ( COLON e2= constant_expression )? | COLON (e3= constant_expression )? )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LPAREN||LA29_0==STAR||LA29_0==ID) ) {
                alt29=1;
            }
            else if ( (LA29_0==COLON) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // resources/Clang/ClangLL.g:1362:4: e1= declarator ( COLON e2= constant_expression )?
                    {
                    pushFollow(FOLLOW_declarator_in_struct_declarator2125);
                    e1=declarator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new StructDeclarator(e1, null); setLineNumAndPos(value, e1);
                    }
                    // resources/Clang/ClangLL.g:1363:6: ( COLON e2= constant_expression )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==COLON) ) {
                        int LA27_1 = input.LA(2);

                        if ( (synpred46_ClangLL()) ) {
                            alt27=1;
                        }
                    }
                    switch (alt27) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1363:7: COLON e2= constant_expression
                            {
                            match(input,COLON,FOLLOW_COLON_in_struct_declarator2136); if (state.failed) return value;
                            pushFollow(FOLLOW_constant_expression_in_struct_declarator2140);
                            e2=constant_expression();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value.setBitFieldValue(e2);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1364:5: COLON (e3= constant_expression )?
                    {
                    match(input,COLON,FOLLOW_COLON_in_struct_declarator2151); if (state.failed) return value;
                    // resources/Clang/ClangLL.g:1364:11: (e3= constant_expression )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==LPAREN||LA28_0==STAR||LA28_0==ID) ) {
                        int LA28_1 = input.LA(2);

                        if ( (synpred48_ClangLL()) ) {
                            alt28=1;
                        }
                    }
                    else if ( ((LA28_0>=PLUS && LA28_0<=MINUS)||LA28_0==NOT||(LA28_0>=AMPERSAND && LA28_0<=SIZEOF)||LA28_0==TILDE||(LA28_0>=DECIMAL_LITERAL && LA28_0<=OCTAL_LITERAL)||LA28_0==HEX_LITERAL||LA28_0==FLOATING_LITERAL||LA28_0==CHAR_LITERAL||LA28_0==STRING_LITERAL) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1364:12: e3= constant_expression
                            {
                            pushFollow(FOLLOW_constant_expression_in_struct_declarator2156);
                            e3=constant_expression();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new StructDeclarator(null, e3);
                            }

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
            if ( state.backtracking>0 ) { memoize(input, 21, struct_declarator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "struct_declarator"


    // $ANTLR start "enum_spec"
    // resources/Clang/ClangLL.g:1366:1: enum_spec returns [EnumSpecifier value] : (e1= ENUM ( ID )? LCURLY e2= enumarator_list RCURLY | e3= ENUM ID );
    public final EnumSpecifier enum_spec() throws RecognitionException {
        EnumSpecifier value = null;
        int enum_spec_StartIndex = input.index();
        Token e1=null;
        Token e3=null;
        Token ID1=null;
        Token ID2=null;
        EnumList e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return value; }
            // resources/Clang/ClangLL.g:1367:2: (e1= ENUM ( ID )? LCURLY e2= enumarator_list RCURLY | e3= ENUM ID )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==ENUM) ) {
                int LA31_1 = input.LA(2);

                if ( (synpred50_ClangLL()) ) {
                    alt31=1;
                }
                else if ( (true) ) {
                    alt31=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // resources/Clang/ClangLL.g:1367:5: e1= ENUM ( ID )? LCURLY e2= enumarator_list RCURLY
                    {
                    e1=(Token)match(input,ENUM,FOLLOW_ENUM_in_enum_spec2179); if (state.failed) return value;
                    // resources/Clang/ClangLL.g:1367:13: ( ID )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==ID) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1367:14: ID
                            {
                            ID1=(Token)match(input,ID,FOLLOW_ID_in_enum_spec2182); if (state.failed) return value;

                            }
                            break;

                    }

                    match(input,LCURLY,FOLLOW_LCURLY_in_enum_spec2190); if (state.failed) return value;
                    pushFollow(FOLLOW_enumarator_list_in_enum_spec2194);
                    e2=enumarator_list();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new EnumSpecifier((ID1!=null?ID1.getText():null), e2); setLineNumAndPos(value, (e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));
                    }
                    match(input,RCURLY,FOLLOW_RCURLY_in_enum_spec2198); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1369:5: e3= ENUM ID
                    {
                    e3=(Token)match(input,ENUM,FOLLOW_ENUM_in_enum_spec2206); if (state.failed) return value;
                    ID2=(Token)match(input,ID,FOLLOW_ID_in_enum_spec2208); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new EnumSpecifier((ID2!=null?ID2.getText():null), null); setLineNumAndPos(value, (e3!=null?e3.getLine():0), (e3!=null?e3.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 22, enum_spec_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "enum_spec"


    // $ANTLR start "enumarator_list"
    // resources/Clang/ClangLL.g:1371:1: enumarator_list returns [EnumList value] : e1= enumerator ( COMMA e2= enumerator )* ;
    public final EnumList enumarator_list() throws RecognitionException {
        EnumList value = null;
        int enumarator_list_StartIndex = input.index();
        Enumerator e1 = null;

        Enumerator e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return value; }
            // resources/Clang/ClangLL.g:1372:2: (e1= enumerator ( COMMA e2= enumerator )* )
            // resources/Clang/ClangLL.g:1372:4: e1= enumerator ( COMMA e2= enumerator )*
            {
            pushFollow(FOLLOW_enumerator_in_enumarator_list2226);
            e1=enumerator();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = (EnumList)addNodeToCollection(e1, value);
            }
            // resources/Clang/ClangLL.g:1373:5: ( COMMA e2= enumerator )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==COMMA) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1373:6: COMMA e2= enumerator
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_enumarator_list2235); if (state.failed) return value;
            	    pushFollow(FOLLOW_enumerator_in_enumarator_list2239);
            	    e2=enumerator();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (EnumList)addNodeToCollection(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 23, enumarator_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "enumarator_list"


    // $ANTLR start "enumerator"
    // resources/Clang/ClangLL.g:1375:1: enumerator returns [Enumerator value] : e1= ID ( ASSIGN e2= constant_expression )? ;
    public final Enumerator enumerator() throws RecognitionException {
        Enumerator value = null;
        int enumerator_StartIndex = input.index();
        Token e1=null;
        ConstExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return value; }
            // resources/Clang/ClangLL.g:1376:2: (e1= ID ( ASSIGN e2= constant_expression )? )
            // resources/Clang/ClangLL.g:1376:4: e1= ID ( ASSIGN e2= constant_expression )?
            {
            e1=(Token)match(input,ID,FOLLOW_ID_in_enumerator2260); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new Enumerator((e1!=null?e1.getText():null), null); setLineNumAndPos(value, (e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));
            }
            // resources/Clang/ClangLL.g:1377:4: ( ASSIGN e2= constant_expression )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==ASSIGN) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // resources/Clang/ClangLL.g:1377:5: ASSIGN e2= constant_expression
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_enumerator2269); if (state.failed) return value;
                    pushFollow(FOLLOW_constant_expression_in_enumerator2273);
                    e2=constant_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setInitValue(e2);
                    }

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
            if ( state.backtracking>0 ) { memoize(input, 24, enumerator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "enumerator"


    // $ANTLR start "declarator"
    // resources/Clang/ClangLL.g:1379:1: declarator returns [Declarator value] : ( (e1= pointer )? e2= direct_declarator | e3= pointer );
    public final Declarator declarator() throws RecognitionException {
        Declarator value = null;
        int declarator_StartIndex = input.index();
        Pointer e1 = null;

        DirectDeclarator e2 = null;

        Pointer e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return value; }
            // resources/Clang/ClangLL.g:1380:2: ( (e1= pointer )? e2= direct_declarator | e3= pointer )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==STAR) ) {
                int LA35_1 = input.LA(2);

                if ( (synpred54_ClangLL()) ) {
                    alt35=1;
                }
                else if ( (true) ) {
                    alt35=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA35_0==LPAREN||LA35_0==ID) ) {
                alt35=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // resources/Clang/ClangLL.g:1380:4: (e1= pointer )? e2= direct_declarator
                    {
                    // resources/Clang/ClangLL.g:1380:4: (e1= pointer )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==STAR) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1380:5: e1= pointer
                            {
                            pushFollow(FOLLOW_pointer_in_declarator2295);
                            e1=pointer();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new Declarator(null, e1); setLineNumAndPos(value, e1);
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_direct_declarator_in_declarator2308);
                    e2=direct_declarator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      if(value == null) value = new Declarator();
                      				value.setDirectDeclarator(e2); setLineNumAndPos(value, e2);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1383:4: e3= pointer
                    {
                    pushFollow(FOLLOW_pointer_in_declarator2317);
                    e3=pointer();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Declarator(null, e3); setLineNumAndPos(value, e3);
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
            if ( state.backtracking>0 ) { memoize(input, 25, declarator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "declarator"


    // $ANTLR start "direct_declarator"
    // resources/Clang/ClangLL.g:1385:1: direct_declarator returns [DirectDeclarator value] : (e= ID | LPAREN e1= declarator RPAREN ) (v1= LBRACK (e2= expression )? RBRACK | v2= LPAREN (e3= parameter_type_list )? RPAREN | v3= LPAREN (e4= identifier_list )? RPAREN )* ;
    public final DirectDeclarator direct_declarator() throws RecognitionException {
        DirectDeclarator value = null;
        int direct_declarator_StartIndex = input.index();
        Token e=null;
        Token v1=null;
        Token v2=null;
        Token v3=null;
        Declarator e1 = null;

        RootExpr e2 = null;

        ParamTypeList e3 = null;

        IdentifierList e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return value; }
            // resources/Clang/ClangLL.g:1386:2: ( (e= ID | LPAREN e1= declarator RPAREN ) (v1= LBRACK (e2= expression )? RBRACK | v2= LPAREN (e3= parameter_type_list )? RPAREN | v3= LPAREN (e4= identifier_list )? RPAREN )* )
            // resources/Clang/ClangLL.g:1386:4: (e= ID | LPAREN e1= declarator RPAREN ) (v1= LBRACK (e2= expression )? RBRACK | v2= LPAREN (e3= parameter_type_list )? RPAREN | v3= LPAREN (e4= identifier_list )? RPAREN )*
            {
            // resources/Clang/ClangLL.g:1386:4: (e= ID | LPAREN e1= declarator RPAREN )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==ID) ) {
                alt36=1;
            }
            else if ( (LA36_0==LPAREN) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // resources/Clang/ClangLL.g:1386:5: e= ID
                    {
                    e=(Token)match(input,ID,FOLLOW_ID_in_direct_declarator2336); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new DirectDeclarator(); value.setId((e!=null?e.getText():null)); 
                      				value.setType(DirectDeclarator.ID); setLineNumAndPos(value, (e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
                      			  
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1389:6: LPAREN e1= declarator RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_direct_declarator2345); if (state.failed) return value;
                    pushFollow(FOLLOW_declarator_in_direct_declarator2349);
                    e1=declarator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new DirectDeclarator(); value.setDeclarator(e1);
                      			 value.setType(DirectDeclarator.DECLR); setLineNumAndPos(value, e1);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_direct_declarator2353); if (state.failed) return value;

                    }
                    break;

            }

            // resources/Clang/ClangLL.g:1391:4: (v1= LBRACK (e2= expression )? RBRACK | v2= LPAREN (e3= parameter_type_list )? RPAREN | v3= LPAREN (e4= identifier_list )? RPAREN )*
            loop40:
            do {
                int alt40=4;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==LBRACK) ) {
                    int LA40_1 = input.LA(2);

                    if ( (synpred57_ClangLL()) ) {
                        alt40=1;
                    }


                }
                else if ( (LA40_0==LPAREN) ) {
                    int LA40_4 = input.LA(2);

                    if ( (synpred59_ClangLL()) ) {
                        alt40=2;
                    }
                    else if ( (synpred61_ClangLL()) ) {
                        alt40=3;
                    }


                }


                switch (alt40) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1391:6: v1= LBRACK (e2= expression )? RBRACK
            	    {
            	    v1=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_direct_declarator2364); if (state.failed) return value;
            	    // resources/Clang/ClangLL.g:1391:16: (e2= expression )?
            	    int alt37=2;
            	    int LA37_0 = input.LA(1);

            	    if ( ((LA37_0>=PLUS && LA37_0<=MINUS)||LA37_0==LPAREN||LA37_0==NOT||(LA37_0>=AMPERSAND && LA37_0<=SIZEOF)||LA37_0==TILDE||LA37_0==STAR||(LA37_0>=DECIMAL_LITERAL && LA37_0<=OCTAL_LITERAL)||LA37_0==HEX_LITERAL||LA37_0==FLOATING_LITERAL||LA37_0==CHAR_LITERAL||(LA37_0>=ID && LA37_0<=STRING_LITERAL)) ) {
            	        alt37=1;
            	    }
            	    switch (alt37) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1391:17: e2= expression
            	            {
            	            pushFollow(FOLLOW_expression_in_direct_declarator2369);
            	            e2=expression();

            	            state._fsp--;
            	            if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    match(input,RBRACK,FOLLOW_RBRACK_in_direct_declarator2373); if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       
            	      		 	
            	      		 	 if(e2 != null){
            	      		 	 	addToDirectDeclarator(value, DirectDeclarator.ARRAY, e2, null, null, (v1!=null?v1.getLine():0), (v1!=null?v1.getCharPositionInLine():0));
            	      		 	 }
            	      		 	 else
            	      		 	 	addToDirectDeclarator(value, DirectDeclarator.EMPTY_ARRAY, null, null, null, (v1!=null?v1.getLine():0), (v1!=null?v1.getCharPositionInLine():0));
            	      		 	 e2 = null;
            	      		 	 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/ClangLL.g:1400:6: v2= LPAREN (e3= parameter_type_list )? RPAREN
            	    {
            	    v2=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_direct_declarator2386); if (state.failed) return value;
            	    // resources/Clang/ClangLL.g:1400:16: (e3= parameter_type_list )?
            	    int alt38=2;
            	    alt38 = dfa38.predict(input);
            	    switch (alt38) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1400:17: e3= parameter_type_list
            	            {
            	            pushFollow(FOLLOW_parameter_type_list_in_direct_declarator2391);
            	            e3=parameter_type_list();

            	            state._fsp--;
            	            if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    match(input,RPAREN,FOLLOW_RPAREN_in_direct_declarator2395); if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	        
            	      		 	
            	      		 	if(e3 != null)
            	      		 	 	addToDirectDeclarator(value, DirectDeclarator.FUNC, null, e3, null, (v2!=null?v2.getLine():0), (v2!=null?v2.getCharPositionInLine():0));
            	      		 	 else
            	      		 	 	addToDirectDeclarator(value, DirectDeclarator.NO_ARG_FUNC, null, null, null, (v2!=null?v2.getLine():0), (v2!=null?v2.getCharPositionInLine():0));
            	      		 	
            	    }

            	    }
            	    break;
            	case 3 :
            	    // resources/Clang/ClangLL.g:1407:6: v3= LPAREN (e4= identifier_list )? RPAREN
            	    {
            	    v3=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_direct_declarator2408); if (state.failed) return value;
            	    // resources/Clang/ClangLL.g:1407:16: (e4= identifier_list )?
            	    int alt39=2;
            	    int LA39_0 = input.LA(1);

            	    if ( (LA39_0==ID) ) {
            	        alt39=1;
            	    }
            	    switch (alt39) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1407:17: e4= identifier_list
            	            {
            	            pushFollow(FOLLOW_identifier_list_in_direct_declarator2413);
            	            e4=identifier_list();

            	            state._fsp--;
            	            if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    match(input,RPAREN,FOLLOW_RPAREN_in_direct_declarator2417); if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       		 	 	
            	      		 	if(e4 != null)
            	      		 	 	addToDirectDeclarator(value, DirectDeclarator.ID_LIST_FUNC, null, null, e4, (v3!=null?v3.getLine():0), (v3!=null?v3.getCharPositionInLine():0));
            	      		 	 else
            	      		 	 	addToDirectDeclarator(value, DirectDeclarator.NO_ARG_FUNC, null, null, null, (v3!=null?v3.getLine():0), (v3!=null?v3.getCharPositionInLine():0));
            	      		 	 
            	    }

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 26, direct_declarator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "direct_declarator"


    // $ANTLR start "pointer"
    // resources/Clang/ClangLL.g:1415:1: pointer returns [Pointer value] : (s1= STAR e1= type_qualifier_list (e2= pointer )? | s2= STAR e2= pointer | s3= STAR );
    public final Pointer pointer() throws RecognitionException {
        Pointer value = null;
        int pointer_StartIndex = input.index();
        Token s1=null;
        Token s2=null;
        Token s3=null;
        TypeQualifierList e1 = null;

        Pointer e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return value; }
            // resources/Clang/ClangLL.g:1416:2: (s1= STAR e1= type_qualifier_list (e2= pointer )? | s2= STAR e2= pointer | s3= STAR )
            int alt42=3;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==STAR) ) {
                int LA42_1 = input.LA(2);

                if ( (synpred63_ClangLL()) ) {
                    alt42=1;
                }
                else if ( (synpred64_ClangLL()) ) {
                    alt42=2;
                }
                else if ( (true) ) {
                    alt42=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // resources/Clang/ClangLL.g:1416:4: s1= STAR e1= type_qualifier_list (e2= pointer )?
                    {
                    s1=(Token)match(input,STAR,FOLLOW_STAR_in_pointer2442); if (state.failed) return value;
                    pushFollow(FOLLOW_type_qualifier_list_in_pointer2446);
                    e1=type_qualifier_list();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Pointer(); value.setTypeQualifierList(e1); setLineNumAndPos(value, (s1!=null?s1.getLine():0), (s1!=null?s1.getCharPositionInLine():0)); 
                    }
                    // resources/Clang/ClangLL.g:1416:147: (e2= pointer )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==STAR) ) {
                        int LA41_1 = input.LA(2);

                        if ( (synpred62_ClangLL()) ) {
                            alt41=1;
                        }
                    }
                    switch (alt41) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1416:148: e2= pointer
                            {
                            pushFollow(FOLLOW_pointer_in_pointer2453);
                            e2=pointer();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setPointer(e2);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1417:4: s2= STAR e2= pointer
                    {
                    s2=(Token)match(input,STAR,FOLLOW_STAR_in_pointer2464); if (state.failed) return value;
                    pushFollow(FOLLOW_pointer_in_pointer2468);
                    e2=pointer();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Pointer(e2);  setLineNumAndPos(value, (s2!=null?s2.getLine():0), (s2!=null?s2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1418:4: s3= STAR
                    {
                    s3=(Token)match(input,STAR,FOLLOW_STAR_in_pointer2477); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Pointer();  setLineNumAndPos(value, (s3!=null?s3.getLine():0), (s3!=null?s3.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 27, pointer_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "pointer"


    // $ANTLR start "type_qualifier_list"
    // resources/Clang/ClangLL.g:1420:1: type_qualifier_list returns [TypeQualifierList value] : (e1= type_qualifier )+ ;
    public final TypeQualifierList type_qualifier_list() throws RecognitionException {
        TypeQualifierList value = null;
        int type_qualifier_list_StartIndex = input.index();
        TypeQualifier e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return value; }
            // resources/Clang/ClangLL.g:1421:2: ( (e1= type_qualifier )+ )
            // resources/Clang/ClangLL.g:1421:4: (e1= type_qualifier )+
            {
            // resources/Clang/ClangLL.g:1421:4: (e1= type_qualifier )+
            int cnt43=0;
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>=CONST && LA43_0<=VOLATILE)) ) {
                    int LA43_1 = input.LA(2);

                    if ( (synpred65_ClangLL()) ) {
                        alt43=1;
                    }


                }


                switch (alt43) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1421:5: e1= type_qualifier
            	    {
            	    pushFollow(FOLLOW_type_qualifier_in_type_qualifier_list2496);
            	    e1=type_qualifier();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (TypeQualifierList)addNodeToCollection(e1, value); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt43 >= 1 ) break loop43;
            	    if (state.backtracking>0) {state.failed=true; return value;}
                        EarlyExitException eee =
                            new EarlyExitException(43, input);
                        throw eee;
                }
                cnt43++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 28, type_qualifier_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "type_qualifier_list"


    // $ANTLR start "parameter_type_list"
    // resources/Clang/ClangLL.g:1423:1: parameter_type_list returns [ParamTypeList value] : e1= parameter_list ( COMMA ELLIPSES )? ;
    public final ParamTypeList parameter_type_list() throws RecognitionException {
        ParamTypeList value = null;
        int parameter_type_list_StartIndex = input.index();
        ParamList e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return value; }
            // resources/Clang/ClangLL.g:1424:2: (e1= parameter_list ( COMMA ELLIPSES )? )
            // resources/Clang/ClangLL.g:1424:4: e1= parameter_list ( COMMA ELLIPSES )?
            {
            if ( state.backtracking==0 ) {
              parsingParamDecl = true;
            }
            pushFollow(FOLLOW_parameter_list_in_parameter_type_list2518);
            e1=parameter_list();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ParamTypeList(e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1425:4: ( COMMA ELLIPSES )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==COMMA) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // resources/Clang/ClangLL.g:1425:5: COMMA ELLIPSES
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_parameter_type_list2527); if (state.failed) return value;
                    match(input,ELLIPSES,FOLLOW_ELLIPSES_in_parameter_type_list2529); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setHasEllipses(true);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              parsingParamDecl = false;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 29, parameter_type_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "parameter_type_list"


    // $ANTLR start "parameter_list"
    // resources/Clang/ClangLL.g:1427:1: parameter_list returns [ParamList value] : e1= param_declaration ( COMMA e2= param_declaration )* ;
    public final ParamList parameter_list() throws RecognitionException {
        ParamList value = null;
        int parameter_list_StartIndex = input.index();
        ParamDeclaration e1 = null;

        ParamDeclaration e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return value; }
            // resources/Clang/ClangLL.g:1428:2: (e1= param_declaration ( COMMA e2= param_declaration )* )
            // resources/Clang/ClangLL.g:1428:4: e1= param_declaration ( COMMA e2= param_declaration )*
            {
            if ( state.backtracking==0 ) {
              parsingParamDecl = true;
            }
            pushFollow(FOLLOW_param_declaration_in_parameter_list2555);
            e1=param_declaration();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value =  (ParamList)addNodeToCollection(e1, value); 
            }
            // resources/Clang/ClangLL.g:1429:3: ( COMMA e2= param_declaration )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==COMMA) ) {
                    int LA45_1 = input.LA(2);

                    if ( (synpred67_ClangLL()) ) {
                        alt45=1;
                    }


                }


                switch (alt45) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1429:5: COMMA e2= param_declaration
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_parameter_list2564); if (state.failed) return value;
            	    pushFollow(FOLLOW_param_declaration_in_parameter_list2568);
            	    e2=param_declaration();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (ParamList) addNodeToCollection(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              parsingParamDecl = false;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 30, parameter_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "parameter_list"


    // $ANTLR start "param_declaration"
    // resources/Clang/ClangLL.g:1431:1: param_declaration returns [ParamDeclaration value] : e= declaration_specifiers (e1= declarator | e2= abstract_declarator )* ;
    public final ParamDeclaration param_declaration() throws RecognitionException {
        ParamDeclaration value = null;
        int param_declaration_StartIndex = input.index();
        DeclarationSpecifiers e = null;

        Declarator e1 = null;

        AbstractDeclarator e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return value; }
            // resources/Clang/ClangLL.g:1432:2: (e= declaration_specifiers (e1= declarator | e2= abstract_declarator )* )
            // resources/Clang/ClangLL.g:1432:4: e= declaration_specifiers (e1= declarator | e2= abstract_declarator )*
            {
            pushFollow(FOLLOW_declaration_specifiers_in_param_declaration2590);
            e=declaration_specifiers();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ParamDeclaration(); 
              			value.setDeclarationSpecifiers(e); setLineNumAndPos(value, e);
            }
            // resources/Clang/ClangLL.g:1434:3: (e1= declarator | e2= abstract_declarator )*
            loop46:
            do {
                int alt46=3;
                switch ( input.LA(1) ) {
                case LPAREN:
                case STAR:
                    {
                    int LA46_1 = input.LA(2);

                    if ( (synpred68_ClangLL()) ) {
                        alt46=1;
                    }
                    else if ( (synpred69_ClangLL()) ) {
                        alt46=2;
                    }


                    }
                    break;
                case ID:
                    {
                    alt46=1;
                    }
                    break;
                case LCURLY:
                case LBRACK:
                    {
                    alt46=2;
                    }
                    break;

                }

                switch (alt46) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1434:4: e1= declarator
            	    {
            	    pushFollow(FOLLOW_declarator_in_param_declaration2600);
            	    e1=declarator();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.setDeclarator(e1);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/ClangLL.g:1435:9: e2= abstract_declarator
            	    {
            	    pushFollow(FOLLOW_abstract_declarator_in_param_declaration2615);
            	    e2=abstract_declarator();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value.setAbstractDeclarator(e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 31, param_declaration_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "param_declaration"


    // $ANTLR start "identifier_list"
    // resources/Clang/ClangLL.g:1437:1: identifier_list returns [IdentifierList value] : e1= ID ( COMMA e2= ID )* ;
    public final IdentifierList identifier_list() throws RecognitionException {
        IdentifierList value = null;
        int identifier_list_StartIndex = input.index();
        Token e1=null;
        Token e2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return value; }
            // resources/Clang/ClangLL.g:1438:2: (e1= ID ( COMMA e2= ID )* )
            // resources/Clang/ClangLL.g:1438:4: e1= ID ( COMMA e2= ID )*
            {
            e1=(Token)match(input,ID,FOLLOW_ID_in_identifier_list2636); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = (IdentifierList)addIdToIdentifierList((e1!=null?e1.getText():null), value, (e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));
            }
            // resources/Clang/ClangLL.g:1439:3: ( COMMA e2= ID )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==COMMA) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1439:4: COMMA e2= ID
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_identifier_list2644); if (state.failed) return value;
            	    e2=(Token)match(input,ID,FOLLOW_ID_in_identifier_list2648); if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (IdentifierList)addIdToIdentifierList((e2!=null?e2.getText():null), value, (e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0));
            	    }

            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 32, identifier_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "identifier_list"


    // $ANTLR start "initializer"
    // resources/Clang/ClangLL.g:1441:1: initializer returns [Initializer value] : (e1= assignment_expression | ( LPAREN )? e3= compound_statement ( RPAREN )? | LCURLY e2= initializer_list ( COMMA )? RCURLY );
    public final Initializer initializer() throws RecognitionException {
        Initializer value = null;
        int initializer_StartIndex = input.index();
        AssignmentExpr e1 = null;

        CompoundStatement e3 = null;

        InitializerList e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return value; }
            // resources/Clang/ClangLL.g:1442:2: (e1= assignment_expression | ( LPAREN )? e3= compound_statement ( RPAREN )? | LCURLY e2= initializer_list ( COMMA )? RCURLY )
            int alt51=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                int LA51_1 = input.LA(2);

                if ( (synpred71_ClangLL()) ) {
                    alt51=1;
                }
                else if ( (synpred74_ClangLL()) ) {
                    alt51=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 51, 1, input);

                    throw nvae;
                }
                }
                break;
            case PLUS:
            case MINUS:
            case NOT:
            case AMPERSAND:
            case INCREMENT:
            case DECREMENT:
            case SIZEOF:
            case TILDE:
            case STAR:
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case HEX_LITERAL:
            case FLOATING_LITERAL:
            case CHAR_LITERAL:
            case ID:
            case STRING_LITERAL:
                {
                alt51=1;
                }
                break;
            case LCURLY:
                {
                int LA51_5 = input.LA(2);

                if ( (synpred74_ClangLL()) ) {
                    alt51=2;
                }
                else if ( (true) ) {
                    alt51=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 51, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // resources/Clang/ClangLL.g:1442:4: e1= assignment_expression
                    {
                    pushFollow(FOLLOW_assignment_expression_in_initializer2670);
                    e1=assignment_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Initializer(); value.setInitExpr(e1); setLineNumAndPos(value, e1);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1443:6: ( LPAREN )? e3= compound_statement ( RPAREN )?
                    {
                    // resources/Clang/ClangLL.g:1443:6: ( LPAREN )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==LPAREN) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // resources/Clang/ClangLL.g:0:0: LPAREN
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_initializer2679); if (state.failed) return value;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_compound_statement_in_initializer2684);
                    e3=compound_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Initializer(); value.setCompoundStatement(e3); setLineNumAndPos(value, e3);
                    }
                    // resources/Clang/ClangLL.g:1443:143: ( RPAREN )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==RPAREN) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // resources/Clang/ClangLL.g:0:0: RPAREN
                            {
                            match(input,RPAREN,FOLLOW_RPAREN_in_initializer2688); if (state.failed) return value;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1444:4: LCURLY e2= initializer_list ( COMMA )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_initializer2694); if (state.failed) return value;
                    pushFollow(FOLLOW_initializer_list_in_initializer2698);
                    e2=initializer_list();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Initializer(); value.setInitializerList(e2); setLineNumAndPos(value, e2);
                    }
                    // resources/Clang/ClangLL.g:1444:135: ( COMMA )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==COMMA) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // resources/Clang/ClangLL.g:0:0: COMMA
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_initializer2701); if (state.failed) return value;

                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_initializer2704); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 33, initializer_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "initializer"


    // $ANTLR start "initializer_list"
    // resources/Clang/ClangLL.g:1446:1: initializer_list returns [InitializerList value] : e1= initializer ( COMMA e2= initializer )* ;
    public final InitializerList initializer_list() throws RecognitionException {
        InitializerList value = null;
        int initializer_list_StartIndex = input.index();
        Initializer e1 = null;

        Initializer e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return value; }
            // resources/Clang/ClangLL.g:1447:2: (e1= initializer ( COMMA e2= initializer )* )
            // resources/Clang/ClangLL.g:1447:4: e1= initializer ( COMMA e2= initializer )*
            {
            pushFollow(FOLLOW_initializer_in_initializer_list2722);
            e1=initializer();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value =  (InitializerList)addNodeToCollection(e1,value); 
            }
            // resources/Clang/ClangLL.g:1448:3: ( COMMA e2= initializer )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==COMMA) ) {
                    int LA52_1 = input.LA(2);

                    if ( (synpred76_ClangLL()) ) {
                        alt52=1;
                    }


                }


                switch (alt52) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1448:4: COMMA e2= initializer
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_initializer_list2730); if (state.failed) return value;
            	    pushFollow(FOLLOW_initializer_in_initializer_list2734);
            	    e2=initializer();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (InitializerList)addNodeToCollection(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 34, initializer_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "initializer_list"


    // $ANTLR start "type_name"
    // resources/Clang/ClangLL.g:1450:1: type_name returns [TypeName value] : e1= specifier_qualifier_list ( ( WS )* ) (e2= abstract_declarator )? ;
    public final TypeName type_name() throws RecognitionException {
        TypeName value = null;
        int type_name_StartIndex = input.index();
        SpecifierQualifierList e1 = null;

        AbstractDeclarator e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return value; }
            // resources/Clang/ClangLL.g:1451:2: (e1= specifier_qualifier_list ( ( WS )* ) (e2= abstract_declarator )? )
            // resources/Clang/ClangLL.g:1451:4: e1= specifier_qualifier_list ( ( WS )* ) (e2= abstract_declarator )?
            {
            pushFollow(FOLLOW_specifier_qualifier_list_in_type_name2756);
            e1=specifier_qualifier_list();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new TypeName(); 
              			value.setSpecifierQualifierList(e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1452:87: ( ( WS )* )
            // resources/Clang/ClangLL.g:1452:88: ( WS )*
            {
            // resources/Clang/ClangLL.g:1452:88: ( WS )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==WS) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:0:0: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_type_name2761); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);


            }

            // resources/Clang/ClangLL.g:1453:3: (e2= abstract_declarator )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==LPAREN||LA54_0==LCURLY||LA54_0==LBRACK||LA54_0==STAR) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // resources/Clang/ClangLL.g:1453:4: e2= abstract_declarator
                    {
                    pushFollow(FOLLOW_abstract_declarator_in_type_name2770);
                    e2=abstract_declarator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setAbstractDeclarator(e2);
                    }

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
            if ( state.backtracking>0 ) { memoize(input, 35, type_name_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "type_name"


    // $ANTLR start "abstract_declarator"
    // resources/Clang/ClangLL.g:1455:1: abstract_declarator returns [AbstractDeclarator value] : (e1= pointer (e2= direct_abstract_declarator )? | e3= direct_abstract_declarator );
    public final AbstractDeclarator abstract_declarator() throws RecognitionException {
        AbstractDeclarator value = null;
        int abstract_declarator_StartIndex = input.index();
        Pointer e1 = null;

        DirectAbstractDeclarator e2 = null;

        DirectAbstractDeclarator e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return value; }
            // resources/Clang/ClangLL.g:1456:2: (e1= pointer (e2= direct_abstract_declarator )? | e3= direct_abstract_declarator )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==STAR) ) {
                alt56=1;
            }
            else if ( (LA56_0==LPAREN||LA56_0==LCURLY||LA56_0==LBRACK) ) {
                alt56=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // resources/Clang/ClangLL.g:1456:5: e1= pointer (e2= direct_abstract_declarator )?
                    {
                    pushFollow(FOLLOW_pointer_in_abstract_declarator2792);
                    e1=pointer();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AbstractDeclarator(e1, null);
                    }
                    // resources/Clang/ClangLL.g:1456:67: (e2= direct_abstract_declarator )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==LPAREN||LA55_0==LCURLY||LA55_0==LBRACK) ) {
                        int LA55_1 = input.LA(2);

                        if ( (synpred79_ClangLL()) ) {
                            alt55=1;
                        }
                    }
                    switch (alt55) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1456:68: e2= direct_abstract_declarator
                            {
                            pushFollow(FOLLOW_direct_abstract_declarator_in_abstract_declarator2798);
                            e2=direct_abstract_declarator();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setDirectAbstractDeclarator(e2); setLineNumAndPos(value, e2);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1457:4: e3= direct_abstract_declarator
                    {
                    pushFollow(FOLLOW_direct_abstract_declarator_in_abstract_declarator2810);
                    e3=direct_abstract_declarator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AbstractDeclarator(null, e3); setLineNumAndPos(value, e3);
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
            if ( state.backtracking>0 ) { memoize(input, 36, abstract_declarator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "abstract_declarator"


    // $ANTLR start "direct_abstract_declarator"
    // resources/Clang/ClangLL.g:1459:1: direct_abstract_declarator returns [DirectAbstractDeclarator value] : ( (a1= LCURLY e1= abstract_declarator RCURLY ) | (a2= LBRACK (e2= constant_expression )? RBRACK | a3= LPAREN (e3= parameter_type_list )? RPAREN ) ) ( LBRACK (e2= constant_expression )? RBRACK | LPAREN (e3= parameter_type_list )? RPAREN )* ;
    public final DirectAbstractDeclarator direct_abstract_declarator() throws RecognitionException {
        DirectAbstractDeclarator value = null;
        int direct_abstract_declarator_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a3=null;
        AbstractDeclarator e1 = null;

        ConstExpr e2 = null;

        ParamTypeList e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return value; }
            // resources/Clang/ClangLL.g:1460:2: ( ( (a1= LCURLY e1= abstract_declarator RCURLY ) | (a2= LBRACK (e2= constant_expression )? RBRACK | a3= LPAREN (e3= parameter_type_list )? RPAREN ) ) ( LBRACK (e2= constant_expression )? RBRACK | LPAREN (e3= parameter_type_list )? RPAREN )* )
            // resources/Clang/ClangLL.g:1460:4: ( (a1= LCURLY e1= abstract_declarator RCURLY ) | (a2= LBRACK (e2= constant_expression )? RBRACK | a3= LPAREN (e3= parameter_type_list )? RPAREN ) ) ( LBRACK (e2= constant_expression )? RBRACK | LPAREN (e3= parameter_type_list )? RPAREN )*
            {
            // resources/Clang/ClangLL.g:1460:4: ( (a1= LCURLY e1= abstract_declarator RCURLY ) | (a2= LBRACK (e2= constant_expression )? RBRACK | a3= LPAREN (e3= parameter_type_list )? RPAREN ) )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==LCURLY) ) {
                alt60=1;
            }
            else if ( (LA60_0==LPAREN||LA60_0==LBRACK) ) {
                alt60=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // resources/Clang/ClangLL.g:1460:5: (a1= LCURLY e1= abstract_declarator RCURLY )
                    {
                    // resources/Clang/ClangLL.g:1460:5: (a1= LCURLY e1= abstract_declarator RCURLY )
                    // resources/Clang/ClangLL.g:1460:6: a1= LCURLY e1= abstract_declarator RCURLY
                    {
                    a1=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_direct_abstract_declarator2832); if (state.failed) return value;
                    pushFollow(FOLLOW_abstract_declarator_in_direct_abstract_declarator2836);
                    e1=abstract_declarator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new DirectAbstractDeclarator(); value.setAbstractDeclarator(e1); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }
                    match(input,RCURLY,FOLLOW_RCURLY_in_direct_abstract_declarator2840); if (state.failed) return value;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1461:5: (a2= LBRACK (e2= constant_expression )? RBRACK | a3= LPAREN (e3= parameter_type_list )? RPAREN )
                    {
                    // resources/Clang/ClangLL.g:1461:5: (a2= LBRACK (e2= constant_expression )? RBRACK | a3= LPAREN (e3= parameter_type_list )? RPAREN )
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==LBRACK) ) {
                        alt59=1;
                    }
                    else if ( (LA59_0==LPAREN) ) {
                        alt59=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 59, 0, input);

                        throw nvae;
                    }
                    switch (alt59) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1461:6: a2= LBRACK (e2= constant_expression )? RBRACK
                            {
                            a2=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_direct_abstract_declarator2850); if (state.failed) return value;
                            // resources/Clang/ClangLL.g:1461:16: (e2= constant_expression )?
                            int alt57=2;
                            int LA57_0 = input.LA(1);

                            if ( ((LA57_0>=PLUS && LA57_0<=MINUS)||LA57_0==LPAREN||LA57_0==NOT||(LA57_0>=AMPERSAND && LA57_0<=SIZEOF)||LA57_0==TILDE||LA57_0==STAR||(LA57_0>=DECIMAL_LITERAL && LA57_0<=OCTAL_LITERAL)||LA57_0==HEX_LITERAL||LA57_0==FLOATING_LITERAL||LA57_0==CHAR_LITERAL||(LA57_0>=ID && LA57_0<=STRING_LITERAL)) ) {
                                alt57=1;
                            }
                            switch (alt57) {
                                case 1 :
                                    // resources/Clang/ClangLL.g:1461:17: e2= constant_expression
                                    {
                                    pushFollow(FOLLOW_constant_expression_in_direct_abstract_declarator2855);
                                    e2=constant_expression();

                                    state._fsp--;
                                    if (state.failed) return value;
                                    if ( state.backtracking==0 ) {
                                       value = new DirectAbstractDeclarator(); addToAbstractDirectDeclarator(e2, null, value); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                                    }

                                    }
                                    break;

                            }

                            match(input,RBRACK,FOLLOW_RBRACK_in_direct_abstract_declarator2861); if (state.failed) return value;

                            }
                            break;
                        case 2 :
                            // resources/Clang/ClangLL.g:1462:8: a3= LPAREN (e3= parameter_type_list )? RPAREN
                            {
                            a3=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_direct_abstract_declarator2872); if (state.failed) return value;
                            // resources/Clang/ClangLL.g:1462:18: (e3= parameter_type_list )?
                            int alt58=2;
                            alt58 = dfa58.predict(input);
                            switch (alt58) {
                                case 1 :
                                    // resources/Clang/ClangLL.g:1462:19: e3= parameter_type_list
                                    {
                                    pushFollow(FOLLOW_parameter_type_list_in_direct_abstract_declarator2877);
                                    e3=parameter_type_list();

                                    state._fsp--;
                                    if (state.failed) return value;
                                    if ( state.backtracking==0 ) {
                                       value = new DirectAbstractDeclarator(); addToAbstractDirectDeclarator(null, e3, value); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                                    }

                                    }
                                    break;

                            }

                            match(input,RPAREN,FOLLOW_RPAREN_in_direct_abstract_declarator2883); if (state.failed) return value;

                            }
                            break;

                    }


                    }
                    break;

            }

            // resources/Clang/ClangLL.g:1465:4: ( LBRACK (e2= constant_expression )? RBRACK | LPAREN (e3= parameter_type_list )? RPAREN )*
            loop63:
            do {
                int alt63=3;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==LBRACK) ) {
                    int LA63_1 = input.LA(2);

                    if ( (synpred86_ClangLL()) ) {
                        alt63=1;
                    }


                }
                else if ( (LA63_0==LPAREN) ) {
                    int LA63_4 = input.LA(2);

                    if ( (synpred88_ClangLL()) ) {
                        alt63=2;
                    }


                }


                switch (alt63) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1465:6: LBRACK (e2= constant_expression )? RBRACK
            	    {
            	    match(input,LBRACK,FOLLOW_LBRACK_in_direct_abstract_declarator2900); if (state.failed) return value;
            	    // resources/Clang/ClangLL.g:1465:13: (e2= constant_expression )?
            	    int alt61=2;
            	    int LA61_0 = input.LA(1);

            	    if ( ((LA61_0>=PLUS && LA61_0<=MINUS)||LA61_0==LPAREN||LA61_0==NOT||(LA61_0>=AMPERSAND && LA61_0<=SIZEOF)||LA61_0==TILDE||LA61_0==STAR||(LA61_0>=DECIMAL_LITERAL && LA61_0<=OCTAL_LITERAL)||LA61_0==HEX_LITERAL||LA61_0==FLOATING_LITERAL||LA61_0==CHAR_LITERAL||(LA61_0>=ID && LA61_0<=STRING_LITERAL)) ) {
            	        alt61=1;
            	    }
            	    switch (alt61) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1465:14: e2= constant_expression
            	            {
            	            pushFollow(FOLLOW_constant_expression_in_direct_abstract_declarator2905);
            	            e2=constant_expression();

            	            state._fsp--;
            	            if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	               addToAbstractDirectDeclarator(e2, null, value);
            	            }

            	            }
            	            break;

            	    }

            	    match(input,RBRACK,FOLLOW_RBRACK_in_direct_abstract_declarator2911); if (state.failed) return value;

            	    }
            	    break;
            	case 2 :
            	    // resources/Clang/ClangLL.g:1466:4: LPAREN (e3= parameter_type_list )? RPAREN
            	    {
            	    match(input,LPAREN,FOLLOW_LPAREN_in_direct_abstract_declarator2919); if (state.failed) return value;
            	    // resources/Clang/ClangLL.g:1466:11: (e3= parameter_type_list )?
            	    int alt62=2;
            	    alt62 = dfa62.predict(input);
            	    switch (alt62) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1466:12: e3= parameter_type_list
            	            {
            	            pushFollow(FOLLOW_parameter_type_list_in_direct_abstract_declarator2924);
            	            e3=parameter_type_list();

            	            state._fsp--;
            	            if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	               addToAbstractDirectDeclarator(null, e3, value);
            	            }

            	            }
            	            break;

            	    }

            	    match(input,RPAREN,FOLLOW_RPAREN_in_direct_abstract_declarator2930); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 37, direct_abstract_declarator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "direct_abstract_declarator"


    // $ANTLR start "statement"
    // resources/Clang/ClangLL.g:1468:1: statement returns [Statement value] : (e1= labeled_statement | e2= selection_statement | e3= jump_statement | e4= expr_statement | e5= compound_statement | e6= iteration_statement | e7= include_statement );
    public final Statement statement() throws RecognitionException {
        Statement value = null;
        int statement_StartIndex = input.index();
        LabeledStatement e1 = null;

        SelectionStatement e2 = null;

        JumpStatement e3 = null;

        ExprStatement e4 = null;

        CompoundStatement e5 = null;

        IterationStatement e6 = null;

        IncludeStatement e7 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return value; }
            // resources/Clang/ClangLL.g:1469:2: (e1= labeled_statement | e2= selection_statement | e3= jump_statement | e4= expr_statement | e5= compound_statement | e6= iteration_statement | e7= include_statement )
            int alt64=7;
            alt64 = dfa64.predict(input);
            switch (alt64) {
                case 1 :
                    // resources/Clang/ClangLL.g:1469:4: e1= labeled_statement
                    {
                    pushFollow(FOLLOW_labeled_statement_in_statement2949);
                    e1=labeled_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e1; setLineNumAndPos(value, e1);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1470:4: e2= selection_statement
                    {
                    pushFollow(FOLLOW_selection_statement_in_statement2958);
                    e2=selection_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e2; setLineNumAndPos(value, e2);
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1471:4: e3= jump_statement
                    {
                    pushFollow(FOLLOW_jump_statement_in_statement2967);
                    e3=jump_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e3; setLineNumAndPos(value, e3);
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1472:4: e4= expr_statement
                    {
                    pushFollow(FOLLOW_expr_statement_in_statement2976);
                    e4=expr_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e4; setLineNumAndPos(value, e4);
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/ClangLL.g:1473:4: e5= compound_statement
                    {
                    pushFollow(FOLLOW_compound_statement_in_statement2985);
                    e5=compound_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e5; setLineNumAndPos(value, e5);
                    }

                    }
                    break;
                case 6 :
                    // resources/Clang/ClangLL.g:1474:4: e6= iteration_statement
                    {
                    pushFollow(FOLLOW_iteration_statement_in_statement2994);
                    e6=iteration_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e6; setLineNumAndPos(value, e6);
                    }

                    }
                    break;
                case 7 :
                    // resources/Clang/ClangLL.g:1475:4: e7= include_statement
                    {
                    pushFollow(FOLLOW_include_statement_in_statement3003);
                    e7=include_statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = e7; setLineNumAndPos(value, e7);
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
            if ( state.backtracking>0 ) { memoize(input, 38, statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "statement"


    // $ANTLR start "labeled_statement"
    // resources/Clang/ClangLL.g:1477:1: labeled_statement returns [LabeledStatement value] : (e= ID COLON e1= statement | a1= CASE e2= constant_expression COLON e3= statement | a2= DEFAULT COLON e4= statement );
    public final LabeledStatement labeled_statement() throws RecognitionException {
        LabeledStatement value = null;
        int labeled_statement_StartIndex = input.index();
        Token e=null;
        Token a1=null;
        Token a2=null;
        Statement e1 = null;

        ConstExpr e2 = null;

        Statement e3 = null;

        Statement e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return value; }
            // resources/Clang/ClangLL.g:1478:2: (e= ID COLON e1= statement | a1= CASE e2= constant_expression COLON e3= statement | a2= DEFAULT COLON e4= statement )
            int alt65=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt65=1;
                }
                break;
            case CASE:
                {
                alt65=2;
                }
                break;
            case DEFAULT:
                {
                alt65=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // resources/Clang/ClangLL.g:1478:4: e= ID COLON e1= statement
                    {
                    e=(Token)match(input,ID,FOLLOW_ID_in_labeled_statement3021); if (state.failed) return value;
                    match(input,COLON,FOLLOW_COLON_in_labeled_statement3023); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_labeled_statement3027);
                    e1=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new LabeledStatement((e!=null?e.getText():null), null, e1); setLineNumAndPos(value, (e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1479:4: a1= CASE e2= constant_expression COLON e3= statement
                    {
                    a1=(Token)match(input,CASE,FOLLOW_CASE_in_labeled_statement3036); if (state.failed) return value;
                    pushFollow(FOLLOW_constant_expression_in_labeled_statement3040);
                    e2=constant_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,COLON,FOLLOW_COLON_in_labeled_statement3042); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_labeled_statement3046);
                    e3=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new LabeledStatement(null, e2, e3); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1480:4: a2= DEFAULT COLON e4= statement
                    {
                    a2=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_labeled_statement3055); if (state.failed) return value;
                    match(input,COLON,FOLLOW_COLON_in_labeled_statement3057); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_labeled_statement3061);
                    e4=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new LabeledStatement(null, null, e4); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 39, labeled_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "labeled_statement"


    // $ANTLR start "expr_statement"
    // resources/Clang/ClangLL.g:1482:1: expr_statement returns [ExprStatement value] : (a1= SEMICOLON | e1= expression SEMICOLON );
    public final ExprStatement expr_statement() throws RecognitionException {
        ExprStatement value = null;
        int expr_statement_StartIndex = input.index();
        Token a1=null;
        RootExpr e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return value; }
            // resources/Clang/ClangLL.g:1483:2: (a1= SEMICOLON | e1= expression SEMICOLON )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==SEMICOLON) ) {
                alt66=1;
            }
            else if ( ((LA66_0>=PLUS && LA66_0<=MINUS)||LA66_0==LPAREN||LA66_0==NOT||(LA66_0>=AMPERSAND && LA66_0<=SIZEOF)||LA66_0==TILDE||LA66_0==STAR||(LA66_0>=DECIMAL_LITERAL && LA66_0<=OCTAL_LITERAL)||LA66_0==HEX_LITERAL||LA66_0==FLOATING_LITERAL||LA66_0==CHAR_LITERAL||(LA66_0>=ID && LA66_0<=STRING_LITERAL)) ) {
                alt66=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // resources/Clang/ClangLL.g:1483:4: a1= SEMICOLON
                    {
                    a1=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_expr_statement3080); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new ExprStatement(null); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1484:4: e1= expression SEMICOLON
                    {
                    pushFollow(FOLLOW_expression_in_expr_statement3089);
                    e1=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_expr_statement3092); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new ExprStatement(e1); setLineNumAndPos(value, e1);
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
            if ( state.backtracking>0 ) { memoize(input, 40, expr_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "expr_statement"


    // $ANTLR start "compound_statement"
    // resources/Clang/ClangLL.g:1486:1: compound_statement returns [CompoundStatement value] : a1= LCURLY e1= block_item_list RCURLY ;
    public final CompoundStatement compound_statement() throws RecognitionException {
        CompoundStatement value = null;
        int compound_statement_StartIndex = input.index();
        Token a1=null;
        BlockItemList e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return value; }
            // resources/Clang/ClangLL.g:1487:2: (a1= LCURLY e1= block_item_list RCURLY )
            // resources/Clang/ClangLL.g:1487:4: a1= LCURLY e1= block_item_list RCURLY
            {
            a1=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_compound_statement3111); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new CompoundStatement(); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
            }
            pushFollow(FOLLOW_block_item_list_in_compound_statement3117);
            e1=block_item_list();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value.setBlockItemList(e1);
            }
            match(input,RCURLY,FOLLOW_RCURLY_in_compound_statement3121); if (state.failed) return value;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 41, compound_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "compound_statement"


    // $ANTLR start "block_item_list"
    // resources/Clang/ClangLL.g:1489:1: block_item_list returns [BlockItemList value] : (e1= block_item )* ;
    public final BlockItemList block_item_list() throws RecognitionException {
        BlockItemList value = null;
        int block_item_list_StartIndex = input.index();
        BlockItem e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return value; }
            // resources/Clang/ClangLL.g:1490:2: ( (e1= block_item )* )
            // resources/Clang/ClangLL.g:1490:4: (e1= block_item )*
            {
            // resources/Clang/ClangLL.g:1490:4: (e1= block_item )*
            loop67:
            do {
                int alt67=2;
                alt67 = dfa67.predict(input);
                switch (alt67) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1490:5: e1= block_item
            	    {
            	    pushFollow(FOLLOW_block_item_in_block_item_list3138);
            	    e1=block_item();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = (BlockItemList)addNodeToCollection(e1, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop67;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 42, block_item_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "block_item_list"


    // $ANTLR start "block_item"
    // resources/Clang/ClangLL.g:1492:1: block_item returns [BlockItem value] : ( (e1= declaration ) | (e2= statement ) );
    public final BlockItem block_item() throws RecognitionException {
        BlockItem value = null;
        int block_item_StartIndex = input.index();
        Declaration e1 = null;

        Statement e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return value; }
            // resources/Clang/ClangLL.g:1493:2: ( (e1= declaration ) | (e2= statement ) )
            int alt68=2;
            alt68 = dfa68.predict(input);
            switch (alt68) {
                case 1 :
                    // resources/Clang/ClangLL.g:1493:4: (e1= declaration )
                    {
                    // resources/Clang/ClangLL.g:1493:4: (e1= declaration )
                    // resources/Clang/ClangLL.g:1493:5: e1= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_block_item3159);
                    e1=declaration();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new BlockItem(); setLineNumAndPos(value, e1); value.setDeclaration(e1);
                    }

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1493:122: (e2= statement )
                    {
                    // resources/Clang/ClangLL.g:1493:122: (e2= statement )
                    // resources/Clang/ClangLL.g:1493:123: e2= statement
                    {
                    pushFollow(FOLLOW_statement_in_block_item3169);
                    e2=statement();

                    state._fsp--;
                    if (state.failed) return value;

                    }

                    if ( state.backtracking==0 ) {
                      value = new BlockItem(); setLineNumAndPos(value, e2); value.setStatement(e2);
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
            if ( state.backtracking>0 ) { memoize(input, 43, block_item_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "block_item"


    // $ANTLR start "selection_statement"
    // resources/Clang/ClangLL.g:1495:1: selection_statement returns [SelectionStatement value] : (a1= IF LPAREN e1= expression RPAREN e2= statement ( options {k=1; backtrack=false; } : ELSE e3= statement )? | a2= SWITCH LPAREN e1= expression RPAREN e2= statement );
    public final SelectionStatement selection_statement() throws RecognitionException {
        SelectionStatement value = null;
        int selection_statement_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        RootExpr e1 = null;

        Statement e2 = null;

        Statement e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return value; }
            // resources/Clang/ClangLL.g:1496:2: (a1= IF LPAREN e1= expression RPAREN e2= statement ( options {k=1; backtrack=false; } : ELSE e3= statement )? | a2= SWITCH LPAREN e1= expression RPAREN e2= statement )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==IF) ) {
                alt70=1;
            }
            else if ( (LA70_0==SWITCH) ) {
                alt70=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // resources/Clang/ClangLL.g:1496:4: a1= IF LPAREN e1= expression RPAREN e2= statement ( options {k=1; backtrack=false; } : ELSE e3= statement )?
                    {
                    a1=(Token)match(input,IF,FOLLOW_IF_in_selection_statement3189); if (state.failed) return value;
                    match(input,LPAREN,FOLLOW_LPAREN_in_selection_statement3191); if (state.failed) return value;
                    pushFollow(FOLLOW_expression_in_selection_statement3195);
                    e1=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new SelectionStatement(); value.setIfExpr(e1);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_selection_statement3199); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_selection_statement3207);
                    e2=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setIfStmt(e2); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }
                    // resources/Clang/ClangLL.g:1498:4: ( options {k=1; backtrack=false; } : ELSE e3= statement )?
                    int alt69=2;
                    int LA69_0 = input.LA(1);

                    if ( (LA69_0==ELSE) ) {
                        int LA69_1 = input.LA(2);

                        if ( (true) ) {
                            alt69=1;
                        }
                    }
                    switch (alt69) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1498:37: ELSE e3= statement
                            {
                            match(input,ELSE,FOLLOW_ELSE_in_selection_statement3227); if (state.failed) return value;
                            pushFollow(FOLLOW_statement_in_selection_statement3232);
                            e3=statement();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setElseStmt(e3);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1499:4: a2= SWITCH LPAREN e1= expression RPAREN e2= statement
                    {
                    a2=(Token)match(input,SWITCH,FOLLOW_SWITCH_in_selection_statement3244); if (state.failed) return value;
                    match(input,LPAREN,FOLLOW_LPAREN_in_selection_statement3246); if (state.failed) return value;
                    pushFollow(FOLLOW_expression_in_selection_statement3250);
                    e1=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new SelectionStatement(); value.setSwitchExpr(e1);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_selection_statement3258); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_selection_statement3262);
                    e2=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setSwitchStmt(e2); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0)); 
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
            if ( state.backtracking>0 ) { memoize(input, 44, selection_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "selection_statement"


    // $ANTLR start "iteration_statement"
    // resources/Clang/ClangLL.g:1502:1: iteration_statement returns [IterationStatement value] : (a1= WHILE LPAREN e1= expression RPAREN e2= statement | a2= DO e3= statement a4= WHILE LPAREN e4= expression RPAREN SEMICOLON | a3= FOR LPAREN ( (e5= expression ) | (e9= declaration ) )? ( SEMICOLON )? (e6= expression )? SEMICOLON (e7= expression )? RPAREN e8= statement );
    public final IterationStatement iteration_statement() throws RecognitionException {
        IterationStatement value = null;
        int iteration_statement_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a4=null;
        Token a3=null;
        RootExpr e1 = null;

        Statement e2 = null;

        Statement e3 = null;

        RootExpr e4 = null;

        RootExpr e5 = null;

        Declaration e9 = null;

        RootExpr e6 = null;

        RootExpr e7 = null;

        Statement e8 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return value; }
            // resources/Clang/ClangLL.g:1503:2: (a1= WHILE LPAREN e1= expression RPAREN e2= statement | a2= DO e3= statement a4= WHILE LPAREN e4= expression RPAREN SEMICOLON | a3= FOR LPAREN ( (e5= expression ) | (e9= declaration ) )? ( SEMICOLON )? (e6= expression )? SEMICOLON (e7= expression )? RPAREN e8= statement )
            int alt75=3;
            switch ( input.LA(1) ) {
            case WHILE:
                {
                alt75=1;
                }
                break;
            case DO:
                {
                alt75=2;
                }
                break;
            case FOR:
                {
                alt75=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }

            switch (alt75) {
                case 1 :
                    // resources/Clang/ClangLL.g:1503:4: a1= WHILE LPAREN e1= expression RPAREN e2= statement
                    {
                    a1=(Token)match(input,WHILE,FOLLOW_WHILE_in_iteration_statement3280); if (state.failed) return value;
                    match(input,LPAREN,FOLLOW_LPAREN_in_iteration_statement3282); if (state.failed) return value;
                    pushFollow(FOLLOW_expression_in_iteration_statement3286);
                    e1=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IterationStatement(); value.setWhileExpr(e1);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_iteration_statement3289); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_iteration_statement3297);
                    e2=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setWhileStmt(e2); value.setIterationType(IterationStatement.WHILE); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1505:4: a2= DO e3= statement a4= WHILE LPAREN e4= expression RPAREN SEMICOLON
                    {
                    a2=(Token)match(input,DO,FOLLOW_DO_in_iteration_statement3306); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_iteration_statement3310);
                    e3=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IterationStatement(); value.setWhileStmt(e3); value.setIterationType(IterationStatement.DO_WHILE);
                    }
                    a4=(Token)match(input,WHILE,FOLLOW_WHILE_in_iteration_statement3319); if (state.failed) return value;
                    match(input,LPAREN,FOLLOW_LPAREN_in_iteration_statement3321); if (state.failed) return value;
                    pushFollow(FOLLOW_expression_in_iteration_statement3325);
                    e4=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setWhileExpr(e4); setLineNumAndPos(value, (a4!=null?a4.getLine():0), (a4!=null?a4.getCharPositionInLine():0));
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_iteration_statement3328); if (state.failed) return value;
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_iteration_statement3330); if (state.failed) return value;

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1507:4: a3= FOR LPAREN ( (e5= expression ) | (e9= declaration ) )? ( SEMICOLON )? (e6= expression )? SEMICOLON (e7= expression )? RPAREN e8= statement
                    {
                    a3=(Token)match(input,FOR,FOLLOW_FOR_in_iteration_statement3337); if (state.failed) return value;
                    match(input,LPAREN,FOLLOW_LPAREN_in_iteration_statement3339); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IterationStatement(); value.setIterationType(IterationStatement.FOR); 
                    }
                    // resources/Clang/ClangLL.g:1508:3: ( (e5= expression ) | (e9= declaration ) )?
                    int alt71=3;
                    alt71 = dfa71.predict(input);
                    switch (alt71) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1508:4: (e5= expression )
                            {
                            // resources/Clang/ClangLL.g:1508:4: (e5= expression )
                            // resources/Clang/ClangLL.g:1508:5: e5= expression
                            {
                            pushFollow(FOLLOW_expression_in_iteration_statement3350);
                            e5=expression();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setForInitExpr(e5);
                            }

                            }


                            }
                            break;
                        case 2 :
                            // resources/Clang/ClangLL.g:1508:56: (e9= declaration )
                            {
                            // resources/Clang/ClangLL.g:1508:56: (e9= declaration )
                            // resources/Clang/ClangLL.g:1508:57: e9= declaration
                            {
                            pushFollow(FOLLOW_declaration_in_iteration_statement3358);
                            e9=declaration();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setForDeclr(e9);
                            }

                            }


                            }
                            break;

                    }

                    // resources/Clang/ClangLL.g:1508:108: ( SEMICOLON )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==SEMICOLON) ) {
                        int LA72_1 = input.LA(2);

                        if ( (synpred105_ClangLL()) ) {
                            alt72=1;
                        }
                    }
                    switch (alt72) {
                        case 1 :
                            // resources/Clang/ClangLL.g:0:0: SEMICOLON
                            {
                            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_iteration_statement3365); if (state.failed) return value;

                            }
                            break;

                    }

                    // resources/Clang/ClangLL.g:1509:3: (e6= expression )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( ((LA73_0>=PLUS && LA73_0<=MINUS)||LA73_0==LPAREN||LA73_0==NOT||(LA73_0>=AMPERSAND && LA73_0<=SIZEOF)||LA73_0==TILDE||LA73_0==STAR||(LA73_0>=DECIMAL_LITERAL && LA73_0<=OCTAL_LITERAL)||LA73_0==HEX_LITERAL||LA73_0==FLOATING_LITERAL||LA73_0==CHAR_LITERAL||(LA73_0>=ID && LA73_0<=STRING_LITERAL)) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1509:4: e6= expression
                            {
                            pushFollow(FOLLOW_expression_in_iteration_statement3374);
                            e6=expression();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setForCondExpr(e6);
                            }

                            }
                            break;

                    }

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_iteration_statement3379); if (state.failed) return value;
                    // resources/Clang/ClangLL.g:1509:65: (e7= expression )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( ((LA74_0>=PLUS && LA74_0<=MINUS)||LA74_0==LPAREN||LA74_0==NOT||(LA74_0>=AMPERSAND && LA74_0<=SIZEOF)||LA74_0==TILDE||LA74_0==STAR||(LA74_0>=DECIMAL_LITERAL && LA74_0<=OCTAL_LITERAL)||LA74_0==HEX_LITERAL||LA74_0==FLOATING_LITERAL||LA74_0==CHAR_LITERAL||(LA74_0>=ID && LA74_0<=STRING_LITERAL)) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1509:66: e7= expression
                            {
                            pushFollow(FOLLOW_expression_in_iteration_statement3384);
                            e7=expression();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value.setForIncrExpr(e7);
                            }

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_iteration_statement3392); if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_iteration_statement3396);
                    e8=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setForStmt(e8); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 45, iteration_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "iteration_statement"


    // $ANTLR start "jump_statement"
    // resources/Clang/ClangLL.g:1512:1: jump_statement returns [JumpStatement value] : (a1= GOTO e= ID SEMICOLON | a2= CONTINUE SEMICOLON | a3= BREAK SEMICOLON | a4= RETURN (e1= expression )? SEMICOLON );
    public final JumpStatement jump_statement() throws RecognitionException {
        JumpStatement value = null;
        int jump_statement_StartIndex = input.index();
        Token a1=null;
        Token e=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        RootExpr e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return value; }
            // resources/Clang/ClangLL.g:1513:2: (a1= GOTO e= ID SEMICOLON | a2= CONTINUE SEMICOLON | a3= BREAK SEMICOLON | a4= RETURN (e1= expression )? SEMICOLON )
            int alt77=4;
            switch ( input.LA(1) ) {
            case GOTO:
                {
                alt77=1;
                }
                break;
            case CONTINUE:
                {
                alt77=2;
                }
                break;
            case BREAK:
                {
                alt77=3;
                }
                break;
            case RETURN:
                {
                alt77=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }

            switch (alt77) {
                case 1 :
                    // resources/Clang/ClangLL.g:1513:4: a1= GOTO e= ID SEMICOLON
                    {
                    a1=(Token)match(input,GOTO,FOLLOW_GOTO_in_jump_statement3414); if (state.failed) return value;
                    e=(Token)match(input,ID,FOLLOW_ID_in_jump_statement3418); if (state.failed) return value;
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jump_statement3420); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new JumpStatement(JumpStatement.GOTO, (e!=null?e.getText():null), null); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1514:4: a2= CONTINUE SEMICOLON
                    {
                    a2=(Token)match(input,CONTINUE,FOLLOW_CONTINUE_in_jump_statement3429); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new JumpStatement(JumpStatement.CONTINUE, null, null); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                    }
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jump_statement3433); if (state.failed) return value;

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1515:4: a3= BREAK SEMICOLON
                    {
                    a3=(Token)match(input,BREAK,FOLLOW_BREAK_in_jump_statement3440); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new JumpStatement(JumpStatement.BREAK, null, null); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                    }
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jump_statement3444); if (state.failed) return value;

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1516:4: a4= RETURN (e1= expression )? SEMICOLON
                    {
                    a4=(Token)match(input,RETURN,FOLLOW_RETURN_in_jump_statement3451); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new JumpStatement(JumpStatement.RETURN, null, null); setLineNumAndPos(value, (a4!=null?a4.getLine():0), (a4!=null?a4.getCharPositionInLine():0)); 
                    }
                    // resources/Clang/ClangLL.g:1516:124: (e1= expression )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( ((LA76_0>=PLUS && LA76_0<=MINUS)||LA76_0==LPAREN||LA76_0==NOT||(LA76_0>=AMPERSAND && LA76_0<=SIZEOF)||LA76_0==TILDE||LA76_0==STAR||(LA76_0>=DECIMAL_LITERAL && LA76_0<=OCTAL_LITERAL)||LA76_0==HEX_LITERAL||LA76_0==FLOATING_LITERAL||LA76_0==CHAR_LITERAL||(LA76_0>=ID && LA76_0<=STRING_LITERAL)) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1516:125: e1= expression
                            {
                            pushFollow(FOLLOW_expression_in_jump_statement3458);
                            e1=expression();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              setReturnExprToJumpStmt(value, e1); 
                            }

                            }
                            break;

                    }

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jump_statement3464); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 46, jump_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "jump_statement"


    // $ANTLR start "expression"
    // resources/Clang/ClangLL.g:1518:1: expression returns [RootExpr value] : e1= assignment_expression ( COMMA e2= assignment_expression )* ;
    public final RootExpr expression() throws RecognitionException {
        RootExpr value = null;
        int expression_StartIndex = input.index();
        AssignmentExpr e1 = null;

        AssignmentExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return value; }
            // resources/Clang/ClangLL.g:1519:2: (e1= assignment_expression ( COMMA e2= assignment_expression )* )
            // resources/Clang/ClangLL.g:1519:4: e1= assignment_expression ( COMMA e2= assignment_expression )*
            {
            pushFollow(FOLLOW_assignment_expression_in_expression3480);
            e1=assignment_expression();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = addAssignmentExpToExpr(e1, value);
            }
            // resources/Clang/ClangLL.g:1520:3: ( COMMA e2= assignment_expression )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==COMMA) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1520:4: COMMA e2= assignment_expression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_expression3488); if (state.failed) return value;
            	    pushFollow(FOLLOW_assignment_expression_in_expression3492);
            	    e2=assignment_expression();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = addAssignmentExpToExpr(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 47, expression_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "expression"


    // $ANTLR start "include_statement"
    // resources/Clang/ClangLL.g:1522:1: include_statement returns [IncludeStatement value] : (e0= HASH LESSER_THAN e1= filename_lib GREATER_THAN HASH | HASH e2= STRING_LITERAL HASH );
    public final IncludeStatement include_statement() throws RecognitionException {
        IncludeStatement value = null;
        int include_statement_StartIndex = input.index();
        Token e0=null;
        Token e2=null;
        FileNameLib e1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return value; }
            // resources/Clang/ClangLL.g:1522:51: (e0= HASH LESSER_THAN e1= filename_lib GREATER_THAN HASH | HASH e2= STRING_LITERAL HASH )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==HASH) ) {
                int LA79_1 = input.LA(2);

                if ( (synpred113_ClangLL()) ) {
                    alt79=1;
                }
                else if ( (true) ) {
                    alt79=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 79, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // resources/Clang/ClangLL.g:1523:2: e0= HASH LESSER_THAN e1= filename_lib GREATER_THAN HASH
                    {
                    e0=(Token)match(input,HASH,FOLLOW_HASH_in_include_statement3511); if (state.failed) return value;
                    match(input,LESSER_THAN,FOLLOW_LESSER_THAN_in_include_statement3513); if (state.failed) return value;
                    pushFollow(FOLLOW_filename_lib_in_include_statement3517);
                    e1=filename_lib();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_include_statement3519); if (state.failed) return value;
                    match(input,HASH,FOLLOW_HASH_in_include_statement3521); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IncludeStatement((e0!=null?e0.getLine():0), e1, IncludeStatement.LIB);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1526:3: HASH e2= STRING_LITERAL HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_include_statement3535); if (state.failed) return value;
                    e2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_include_statement3539); if (state.failed) return value;
                    match(input,HASH,FOLLOW_HASH_in_include_statement3541); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new IncludeStatement((e2!=null?e2.getLine():0), (e2!=null?e2.getText():null), IncludeStatement.LOCAL);
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
            if ( state.backtracking>0 ) { memoize(input, 48, include_statement_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "include_statement"


    // $ANTLR start "assignment_expression"
    // resources/Clang/ClangLL.g:1529:1: assignment_expression returns [AssignmentExpr value] : (e1= unary_expr e2= assignment_operator ( ( WS )* ) e3= assignment_expression | e4= conditional_expression );
    public final AssignmentExpr assignment_expression() throws RecognitionException {
        AssignmentExpr value = null;
        int assignment_expression_StartIndex = input.index();
        UnaryExpr e1 = null;

        AssignmentOperator e2 = null;

        AssignmentExpr e3 = null;

        ConditionalExpr e4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return value; }
            // resources/Clang/ClangLL.g:1530:2: (e1= unary_expr e2= assignment_operator ( ( WS )* ) e3= assignment_expression | e4= conditional_expression )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( ((LA81_0>=PLUS && LA81_0<=MINUS)||LA81_0==LPAREN||LA81_0==NOT||(LA81_0>=AMPERSAND && LA81_0<=SIZEOF)||LA81_0==TILDE||LA81_0==STAR||(LA81_0>=DECIMAL_LITERAL && LA81_0<=OCTAL_LITERAL)||LA81_0==HEX_LITERAL||LA81_0==FLOATING_LITERAL||LA81_0==CHAR_LITERAL||(LA81_0>=ID && LA81_0<=STRING_LITERAL)) ) {
                int LA81_1 = input.LA(2);

                if ( (synpred115_ClangLL()) ) {
                    alt81=1;
                }
                else if ( (true) ) {
                    alt81=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // resources/Clang/ClangLL.g:1530:4: e1= unary_expr e2= assignment_operator ( ( WS )* ) e3= assignment_expression
                    {
                    pushFollow(FOLLOW_unary_expr_in_assignment_expression3561);
                    e1=unary_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentExpr(); value.setUnaryExpr(e1);
                    }
                    pushFollow(FOLLOW_assignment_operator_in_assignment_expression3570);
                    e2=assignment_operator();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setAssignmentOperator(e2);
                    }
                    // resources/Clang/ClangLL.g:1531:70: ( ( WS )* )
                    // resources/Clang/ClangLL.g:1531:71: ( WS )*
                    {
                    // resources/Clang/ClangLL.g:1531:71: ( WS )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==WS) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // resources/Clang/ClangLL.g:0:0: WS
                    	    {
                    	    match(input,WS,FOLLOW_WS_in_assignment_expression3575); if (state.failed) return value;

                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);


                    }

                    pushFollow(FOLLOW_assignment_expression_in_assignment_expression3584);
                    e3=assignment_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setAssignmentExpr(e3); setLineNumAndPos(value, e1);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1533:5: e4= conditional_expression
                    {
                    pushFollow(FOLLOW_conditional_expression_in_assignment_expression3594);
                    e4=conditional_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentExpr(); value.setConditionalExpr(e4); setLineNumAndPos(value, e4);
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
            if ( state.backtracking>0 ) { memoize(input, 49, assignment_expression_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "assignment_expression"


    // $ANTLR start "assignment_operator"
    // resources/Clang/ClangLL.g:1535:1: assignment_operator returns [AssignmentOperator value] : (a1= ASSIGN | a2= MULTIPLY_ASSIGN | a3= DIVIDE_ASSIGN | a4= ADD_ASSIGN | a5= MINUS_ASSIGN | a6= MODULO_ASSIGN | a7= BITWISE_AND_ASSIGN | a8= BITWISE_XOR_ASSIGN | a9= BITWISE_OR_ASSIGN | a10= LEFT_SHIFT_ASSIGN | a11= RIGHT_SHIFT_ASSIGN );
    public final AssignmentOperator assignment_operator() throws RecognitionException {
        AssignmentOperator value = null;
        int assignment_operator_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;
        Token a6=null;
        Token a7=null;
        Token a8=null;
        Token a9=null;
        Token a10=null;
        Token a11=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return value; }
            // resources/Clang/ClangLL.g:1536:2: (a1= ASSIGN | a2= MULTIPLY_ASSIGN | a3= DIVIDE_ASSIGN | a4= ADD_ASSIGN | a5= MINUS_ASSIGN | a6= MODULO_ASSIGN | a7= BITWISE_AND_ASSIGN | a8= BITWISE_XOR_ASSIGN | a9= BITWISE_OR_ASSIGN | a10= LEFT_SHIFT_ASSIGN | a11= RIGHT_SHIFT_ASSIGN )
            int alt82=11;
            alt82 = dfa82.predict(input);
            switch (alt82) {
                case 1 :
                    // resources/Clang/ClangLL.g:1536:4: a1= ASSIGN
                    {
                    a1=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment_operator3612); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.ASSIGN); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1537:4: a2= MULTIPLY_ASSIGN
                    {
                    a2=(Token)match(input,MULTIPLY_ASSIGN,FOLLOW_MULTIPLY_ASSIGN_in_assignment_operator3621); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.MULTIPLY_ASSIGN); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1538:4: a3= DIVIDE_ASSIGN
                    {
                    a3=(Token)match(input,DIVIDE_ASSIGN,FOLLOW_DIVIDE_ASSIGN_in_assignment_operator3631); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.DIVIDE_ASSIGN); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1539:4: a4= ADD_ASSIGN
                    {
                    a4=(Token)match(input,ADD_ASSIGN,FOLLOW_ADD_ASSIGN_in_assignment_operator3640); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.ADD_ASSIGN); setLineNumAndPos(value, (a4!=null?a4.getLine():0), (a4!=null?a4.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/ClangLL.g:1540:4: a5= MINUS_ASSIGN
                    {
                    a5=(Token)match(input,MINUS_ASSIGN,FOLLOW_MINUS_ASSIGN_in_assignment_operator3649); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.MINUS_ASSIGN); setLineNumAndPos(value, (a5!=null?a5.getLine():0), (a5!=null?a5.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 6 :
                    // resources/Clang/ClangLL.g:1541:4: a6= MODULO_ASSIGN
                    {
                    a6=(Token)match(input,MODULO_ASSIGN,FOLLOW_MODULO_ASSIGN_in_assignment_operator3658); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.MODULO_ASSIGN); setLineNumAndPos(value, (a6!=null?a6.getLine():0), (a6!=null?a6.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 7 :
                    // resources/Clang/ClangLL.g:1542:4: a7= BITWISE_AND_ASSIGN
                    {
                    a7=(Token)match(input,BITWISE_AND_ASSIGN,FOLLOW_BITWISE_AND_ASSIGN_in_assignment_operator3667); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.BITWISE_AND_ASSIGN); setLineNumAndPos(value, (a7!=null?a7.getLine():0), (a7!=null?a7.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 8 :
                    // resources/Clang/ClangLL.g:1543:4: a8= BITWISE_XOR_ASSIGN
                    {
                    a8=(Token)match(input,BITWISE_XOR_ASSIGN,FOLLOW_BITWISE_XOR_ASSIGN_in_assignment_operator3676); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.BITWISE_XOR_ASSIGN); setLineNumAndPos(value, (a8!=null?a8.getLine():0), (a8!=null?a8.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 9 :
                    // resources/Clang/ClangLL.g:1544:4: a9= BITWISE_OR_ASSIGN
                    {
                    a9=(Token)match(input,BITWISE_OR_ASSIGN,FOLLOW_BITWISE_OR_ASSIGN_in_assignment_operator3685); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.BITWISE_OR_ASSIGN); setLineNumAndPos(value, (a9!=null?a9.getLine():0), (a9!=null?a9.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 10 :
                    // resources/Clang/ClangLL.g:1545:4: a10= LEFT_SHIFT_ASSIGN
                    {
                    a10=(Token)match(input,LEFT_SHIFT_ASSIGN,FOLLOW_LEFT_SHIFT_ASSIGN_in_assignment_operator3694); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.LEFT_SHIFT_ASSIGN); setLineNumAndPos(value, (a10!=null?a10.getLine():0), (a10!=null?a10.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 11 :
                    // resources/Clang/ClangLL.g:1546:4: a11= RIGHT_SHIFT_ASSIGN
                    {
                    a11=(Token)match(input,RIGHT_SHIFT_ASSIGN,FOLLOW_RIGHT_SHIFT_ASSIGN_in_assignment_operator3703); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new AssignmentOperator(AssignmentOperator.RIGHT_SHIFT_ASSIGN); setLineNumAndPos(value, (a11!=null?a11.getLine():0), (a11!=null?a11.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 50, assignment_operator_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "assignment_operator"


    // $ANTLR start "conditional_expression"
    // resources/Clang/ClangLL.g:1548:1: conditional_expression returns [ConditionalExpr value] : e1= logical_or_expr ( QUESTION e2= expression COLON e3= conditional_expression )? ;
    public final ConditionalExpr conditional_expression() throws RecognitionException {
        ConditionalExpr value = null;
        int conditional_expression_StartIndex = input.index();
        LogicalOrExpr e1 = null;

        RootExpr e2 = null;

        ConditionalExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return value; }
            // resources/Clang/ClangLL.g:1549:2: (e1= logical_or_expr ( QUESTION e2= expression COLON e3= conditional_expression )? )
            // resources/Clang/ClangLL.g:1549:4: e1= logical_or_expr ( QUESTION e2= expression COLON e3= conditional_expression )?
            {
            pushFollow(FOLLOW_logical_or_expr_in_conditional_expression3720);
            e1=logical_or_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ConditionalExpr(); value.setLogicalOrExpr(e1); setLineNumAndPos(value, e1); 
            }
            // resources/Clang/ClangLL.g:1550:3: ( QUESTION e2= expression COLON e3= conditional_expression )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==QUESTION) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // resources/Clang/ClangLL.g:1550:4: QUESTION e2= expression COLON e3= conditional_expression
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_conditional_expression3727); if (state.failed) return value;
                    pushFollow(FOLLOW_expression_in_conditional_expression3732);
                    e2=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setTrueInConditional(e2);
                    }
                    match(input,COLON,FOLLOW_COLON_in_conditional_expression3740); if (state.failed) return value;
                    pushFollow(FOLLOW_conditional_expression_in_conditional_expression3745);
                    e3=conditional_expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setFalseInConditional(e3);
                    }

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
            if ( state.backtracking>0 ) { memoize(input, 51, conditional_expression_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "conditional_expression"


    // $ANTLR start "constant_expression"
    // resources/Clang/ClangLL.g:1553:1: constant_expression returns [ConstExpr value] : e= conditional_expression ;
    public final ConstExpr constant_expression() throws RecognitionException {
        ConstExpr value = null;
        int constant_expression_StartIndex = input.index();
        ConditionalExpr e = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return value; }
            // resources/Clang/ClangLL.g:1554:2: (e= conditional_expression )
            // resources/Clang/ClangLL.g:1554:4: e= conditional_expression
            {
            pushFollow(FOLLOW_conditional_expression_in_constant_expression3765);
            e=conditional_expression();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ConstExpr(); value.setCondExpr(e); setLineNumAndPos(value, e);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 52, constant_expression_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "constant_expression"


    // $ANTLR start "logical_or_expr"
    // resources/Clang/ClangLL.g:1556:1: logical_or_expr returns [LogicalOrExpr value] : e1= logical_and_expr ( OR e2= logical_and_expr )* ;
    public final LogicalOrExpr logical_or_expr() throws RecognitionException {
        LogicalOrExpr value = null;
        int logical_or_expr_StartIndex = input.index();
        LogicalAndExpr e1 = null;

        LogicalAndExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return value; }
            // resources/Clang/ClangLL.g:1557:2: (e1= logical_and_expr ( OR e2= logical_and_expr )* )
            // resources/Clang/ClangLL.g:1557:4: e1= logical_and_expr ( OR e2= logical_and_expr )*
            {
            pushFollow(FOLLOW_logical_and_expr_in_logical_or_expr3783);
            e1=logical_and_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new LogicalOrExpr(null, e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1558:3: ( OR e2= logical_and_expr )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==OR) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1558:4: OR e2= logical_and_expr
            	    {
            	    match(input,OR,FOLLOW_OR_in_logical_or_expr3791); if (state.failed) return value;
            	    pushFollow(FOLLOW_logical_and_expr_in_logical_or_expr3795);
            	    e2=logical_and_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new LogicalOrExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 53, logical_or_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "logical_or_expr"


    // $ANTLR start "logical_and_expr"
    // resources/Clang/ClangLL.g:1560:1: logical_and_expr returns [LogicalAndExpr value] : e1= inclusive_or_expr ( AND e2= inclusive_or_expr )* ;
    public final LogicalAndExpr logical_and_expr() throws RecognitionException {
        LogicalAndExpr value = null;
        int logical_and_expr_StartIndex = input.index();
        InclusiveOrExpr e1 = null;

        InclusiveOrExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return value; }
            // resources/Clang/ClangLL.g:1561:2: (e1= inclusive_or_expr ( AND e2= inclusive_or_expr )* )
            // resources/Clang/ClangLL.g:1561:4: e1= inclusive_or_expr ( AND e2= inclusive_or_expr )*
            {
            pushFollow(FOLLOW_inclusive_or_expr_in_logical_and_expr3815);
            e1=inclusive_or_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new LogicalAndExpr(null, e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1562:4: ( AND e2= inclusive_or_expr )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==AND) ) {
                    alt85=1;
                }


                switch (alt85) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1562:5: AND e2= inclusive_or_expr
            	    {
            	    match(input,AND,FOLLOW_AND_in_logical_and_expr3824); if (state.failed) return value;
            	    pushFollow(FOLLOW_inclusive_or_expr_in_logical_and_expr3828);
            	    e2=inclusive_or_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new LogicalAndExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop85;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 54, logical_and_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "logical_and_expr"


    // $ANTLR start "inclusive_or_expr"
    // resources/Clang/ClangLL.g:1564:1: inclusive_or_expr returns [InclusiveOrExpr value] : e1= exclusive_or_expr ( PIPE e2= exclusive_or_expr )* ;
    public final InclusiveOrExpr inclusive_or_expr() throws RecognitionException {
        InclusiveOrExpr value = null;
        int inclusive_or_expr_StartIndex = input.index();
        ExclusiveOrExpr e1 = null;

        ExclusiveOrExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return value; }
            // resources/Clang/ClangLL.g:1565:2: (e1= exclusive_or_expr ( PIPE e2= exclusive_or_expr )* )
            // resources/Clang/ClangLL.g:1565:4: e1= exclusive_or_expr ( PIPE e2= exclusive_or_expr )*
            {
            pushFollow(FOLLOW_exclusive_or_expr_in_inclusive_or_expr3848);
            e1=exclusive_or_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new InclusiveOrExpr(null, e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1566:3: ( PIPE e2= exclusive_or_expr )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==PIPE) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1566:4: PIPE e2= exclusive_or_expr
            	    {
            	    match(input,PIPE,FOLLOW_PIPE_in_inclusive_or_expr3857); if (state.failed) return value;
            	    pushFollow(FOLLOW_exclusive_or_expr_in_inclusive_or_expr3861);
            	    e2=exclusive_or_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new InclusiveOrExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 55, inclusive_or_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "inclusive_or_expr"


    // $ANTLR start "exclusive_or_expr"
    // resources/Clang/ClangLL.g:1568:1: exclusive_or_expr returns [ExclusiveOrExpr value] : e1= and_expr ( CARET e2= and_expr )* ;
    public final ExclusiveOrExpr exclusive_or_expr() throws RecognitionException {
        ExclusiveOrExpr value = null;
        int exclusive_or_expr_StartIndex = input.index();
        AndExpr e1 = null;

        AndExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return value; }
            // resources/Clang/ClangLL.g:1569:2: (e1= and_expr ( CARET e2= and_expr )* )
            // resources/Clang/ClangLL.g:1569:4: e1= and_expr ( CARET e2= and_expr )*
            {
            pushFollow(FOLLOW_and_expr_in_exclusive_or_expr3881);
            e1=and_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ExclusiveOrExpr(null, e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1570:3: ( CARET e2= and_expr )*
            loop87:
            do {
                int alt87=2;
                int LA87_0 = input.LA(1);

                if ( (LA87_0==CARET) ) {
                    alt87=1;
                }


                switch (alt87) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1570:4: CARET e2= and_expr
            	    {
            	    match(input,CARET,FOLLOW_CARET_in_exclusive_or_expr3889); if (state.failed) return value;
            	    pushFollow(FOLLOW_and_expr_in_exclusive_or_expr3893);
            	    e2=and_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new ExclusiveOrExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop87;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 56, exclusive_or_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "exclusive_or_expr"


    // $ANTLR start "and_expr"
    // resources/Clang/ClangLL.g:1572:1: and_expr returns [AndExpr value] : e1= equality_expr ( AMPERSAND e2= equality_expr )* ;
    public final AndExpr and_expr() throws RecognitionException {
        AndExpr value = null;
        int and_expr_StartIndex = input.index();
        EqualityExpr e1 = null;

        EqualityExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return value; }
            // resources/Clang/ClangLL.g:1573:2: (e1= equality_expr ( AMPERSAND e2= equality_expr )* )
            // resources/Clang/ClangLL.g:1573:4: e1= equality_expr ( AMPERSAND e2= equality_expr )*
            {
            pushFollow(FOLLOW_equality_expr_in_and_expr3913);
            e1=equality_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new AndExpr(null, e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1574:4: ( AMPERSAND e2= equality_expr )*
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0==AMPERSAND) ) {
                    int LA88_1 = input.LA(2);

                    if ( (synpred131_ClangLL()) ) {
                        alt88=1;
                    }


                }


                switch (alt88) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1574:5: AMPERSAND e2= equality_expr
            	    {
            	    match(input,AMPERSAND,FOLLOW_AMPERSAND_in_and_expr3923); if (state.failed) return value;
            	    pushFollow(FOLLOW_equality_expr_in_and_expr3927);
            	    e2=equality_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new AndExpr(value, e2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop88;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 57, and_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "and_expr"


    // $ANTLR start "equality_expr"
    // resources/Clang/ClangLL.g:1576:1: equality_expr returns [EqualityExpr value] : e1= relational_expr ( (sign= EQUALS | sign= NOT_EQUALS ) e2= relational_expr )* ;
    public final EqualityExpr equality_expr() throws RecognitionException {
        EqualityExpr value = null;
        int equality_expr_StartIndex = input.index();
        Token sign=null;
        RelationalExpr e1 = null;

        RelationalExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return value; }
            // resources/Clang/ClangLL.g:1577:2: (e1= relational_expr ( (sign= EQUALS | sign= NOT_EQUALS ) e2= relational_expr )* )
            // resources/Clang/ClangLL.g:1577:4: e1= relational_expr ( (sign= EQUALS | sign= NOT_EQUALS ) e2= relational_expr )*
            {
            pushFollow(FOLLOW_relational_expr_in_equality_expr3947);
            e1=relational_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new EqualityExpr(null, e1, -1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1578:4: ( (sign= EQUALS | sign= NOT_EQUALS ) e2= relational_expr )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( ((LA90_0>=EQUALS && LA90_0<=NOT_EQUALS)) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1578:5: (sign= EQUALS | sign= NOT_EQUALS ) e2= relational_expr
            	    {
            	    // resources/Clang/ClangLL.g:1578:5: (sign= EQUALS | sign= NOT_EQUALS )
            	    int alt89=2;
            	    int LA89_0 = input.LA(1);

            	    if ( (LA89_0==EQUALS) ) {
            	        alt89=1;
            	    }
            	    else if ( (LA89_0==NOT_EQUALS) ) {
            	        alt89=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 89, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt89) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1578:6: sign= EQUALS
            	            {
            	            sign=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_equality_expr3959); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/ClangLL.g:1578:20: sign= NOT_EQUALS
            	            {
            	            sign=(Token)match(input,NOT_EQUALS,FOLLOW_NOT_EQUALS_in_equality_expr3965); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_relational_expr_in_equality_expr3975);
            	    e2=relational_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new EqualityExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 58, equality_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "equality_expr"


    // $ANTLR start "relational_expr"
    // resources/Clang/ClangLL.g:1581:1: relational_expr returns [RelationalExpr value] : e1= shift_expr ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) e2= shift_expr )* ;
    public final RelationalExpr relational_expr() throws RecognitionException {
        RelationalExpr value = null;
        int relational_expr_StartIndex = input.index();
        Token sign=null;
        ShiftExpr e1 = null;

        ShiftExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return value; }
            // resources/Clang/ClangLL.g:1582:2: (e1= shift_expr ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) e2= shift_expr )* )
            // resources/Clang/ClangLL.g:1582:4: e1= shift_expr ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) e2= shift_expr )*
            {
            pushFollow(FOLLOW_shift_expr_in_relational_expr3996);
            e1=shift_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new RelationalExpr(null, e1, -1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1583:3: ( (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) e2= shift_expr )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( ((LA92_0>=LESSER_THAN && LA92_0<=GREATER_THAN_OR_EQUAL_TO)) ) {
                    alt92=1;
                }


                switch (alt92) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1583:4: (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO ) e2= shift_expr
            	    {
            	    // resources/Clang/ClangLL.g:1583:4: (sign= LESSER_THAN | sign= GREATER_THAN | sign= LESSER_THAN_OR_EQUAL_TO | sign= GREATER_THAN_OR_EQUAL_TO )
            	    int alt91=4;
            	    switch ( input.LA(1) ) {
            	    case LESSER_THAN:
            	        {
            	        alt91=1;
            	        }
            	        break;
            	    case GREATER_THAN:
            	        {
            	        alt91=2;
            	        }
            	        break;
            	    case LESSER_THAN_OR_EQUAL_TO:
            	        {
            	        alt91=3;
            	        }
            	        break;
            	    case GREATER_THAN_OR_EQUAL_TO:
            	        {
            	        alt91=4;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 91, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt91) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1583:6: sign= LESSER_THAN
            	            {
            	            sign=(Token)match(input,LESSER_THAN,FOLLOW_LESSER_THAN_in_relational_expr4009); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/ClangLL.g:1583:25: sign= GREATER_THAN
            	            {
            	            sign=(Token)match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_relational_expr4015); if (state.failed) return value;

            	            }
            	            break;
            	        case 3 :
            	            // resources/Clang/ClangLL.g:1583:45: sign= LESSER_THAN_OR_EQUAL_TO
            	            {
            	            sign=(Token)match(input,LESSER_THAN_OR_EQUAL_TO,FOLLOW_LESSER_THAN_OR_EQUAL_TO_in_relational_expr4021); if (state.failed) return value;

            	            }
            	            break;
            	        case 4 :
            	            // resources/Clang/ClangLL.g:1583:76: sign= GREATER_THAN_OR_EQUAL_TO
            	            {
            	            sign=(Token)match(input,GREATER_THAN_OR_EQUAL_TO,FOLLOW_GREATER_THAN_OR_EQUAL_TO_in_relational_expr4027); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_shift_expr_in_relational_expr4035);
            	    e2=shift_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new RelationalExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 59, relational_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "relational_expr"


    // $ANTLR start "shift_expr"
    // resources/Clang/ClangLL.g:1586:1: shift_expr returns [ShiftExpr value] : e1= additive_expr ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) e2= additive_expr )* ;
    public final ShiftExpr shift_expr() throws RecognitionException {
        ShiftExpr value = null;
        int shift_expr_StartIndex = input.index();
        Token sign=null;
        AdditiveExpr e1 = null;

        AdditiveExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return value; }
            // resources/Clang/ClangLL.g:1587:2: (e1= additive_expr ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) e2= additive_expr )* )
            // resources/Clang/ClangLL.g:1587:4: e1= additive_expr ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) e2= additive_expr )*
            {
            pushFollow(FOLLOW_additive_expr_in_shift_expr4055);
            e1=additive_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ShiftExpr(null, e1, -1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1588:4: ( (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) e2= additive_expr )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( ((LA94_0>=LEFT_SHIFT && LA94_0<=RIGHT_SHIFT)) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1588:5: (sign= LEFT_SHIFT | sign= RIGHT_SHIFT ) e2= additive_expr
            	    {
            	    // resources/Clang/ClangLL.g:1588:5: (sign= LEFT_SHIFT | sign= RIGHT_SHIFT )
            	    int alt93=2;
            	    int LA93_0 = input.LA(1);

            	    if ( (LA93_0==LEFT_SHIFT) ) {
            	        alt93=1;
            	    }
            	    else if ( (LA93_0==RIGHT_SHIFT) ) {
            	        alt93=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 93, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt93) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1588:6: sign= LEFT_SHIFT
            	            {
            	            sign=(Token)match(input,LEFT_SHIFT,FOLLOW_LEFT_SHIFT_in_shift_expr4067); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/ClangLL.g:1588:24: sign= RIGHT_SHIFT
            	            {
            	            sign=(Token)match(input,RIGHT_SHIFT,FOLLOW_RIGHT_SHIFT_in_shift_expr4073); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_additive_expr_in_shift_expr4082);
            	    e2=additive_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new ShiftExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop94;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 60, shift_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "shift_expr"


    // $ANTLR start "additive_expr"
    // resources/Clang/ClangLL.g:1591:1: additive_expr returns [AdditiveExpr value] : e1= multiplicative_expr ( (sign= PLUS | sign= MINUS ) e2= multiplicative_expr )* ;
    public final AdditiveExpr additive_expr() throws RecognitionException {
        AdditiveExpr value = null;
        int additive_expr_StartIndex = input.index();
        Token sign=null;
        MultiplicativeExpr e1 = null;

        MultiplicativeExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return value; }
            // resources/Clang/ClangLL.g:1592:2: (e1= multiplicative_expr ( (sign= PLUS | sign= MINUS ) e2= multiplicative_expr )* )
            // resources/Clang/ClangLL.g:1592:4: e1= multiplicative_expr ( (sign= PLUS | sign= MINUS ) e2= multiplicative_expr )*
            {
            pushFollow(FOLLOW_multiplicative_expr_in_additive_expr4102);
            e1=multiplicative_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new AdditiveExpr(null, e1, -1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1593:3: ( (sign= PLUS | sign= MINUS ) e2= multiplicative_expr )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( ((LA96_0>=PLUS && LA96_0<=MINUS)) ) {
                    int LA96_1 = input.LA(2);

                    if ( (synpred141_ClangLL()) ) {
                        alt96=1;
                    }


                }


                switch (alt96) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1593:4: (sign= PLUS | sign= MINUS ) e2= multiplicative_expr
            	    {
            	    // resources/Clang/ClangLL.g:1593:4: (sign= PLUS | sign= MINUS )
            	    int alt95=2;
            	    int LA95_0 = input.LA(1);

            	    if ( (LA95_0==PLUS) ) {
            	        alt95=1;
            	    }
            	    else if ( (LA95_0==MINUS) ) {
            	        alt95=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 95, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt95) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1593:6: sign= PLUS
            	            {
            	            sign=(Token)match(input,PLUS,FOLLOW_PLUS_in_additive_expr4114); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/ClangLL.g:1593:19: sign= MINUS
            	            {
            	            sign=(Token)match(input,MINUS,FOLLOW_MINUS_in_additive_expr4121); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiplicative_expr_in_additive_expr4129);
            	    e2=multiplicative_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new AdditiveExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop96;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 61, additive_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "additive_expr"


    // $ANTLR start "multiplicative_expr"
    // resources/Clang/ClangLL.g:1596:1: multiplicative_expr returns [MultiplicativeExpr value] : e1= cast_expr ( (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr )* ;
    public final MultiplicativeExpr multiplicative_expr() throws RecognitionException {
        MultiplicativeExpr value = null;
        int multiplicative_expr_StartIndex = input.index();
        Token sign=null;
        CastExpr e1 = null;

        CastExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return value; }
            // resources/Clang/ClangLL.g:1597:2: (e1= cast_expr ( (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr )* )
            // resources/Clang/ClangLL.g:1597:4: e1= cast_expr ( (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr )*
            {
            pushFollow(FOLLOW_cast_expr_in_multiplicative_expr4149);
            e1=cast_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new MultiplicativeExpr(null, e1, -1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1598:3: ( (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==STAR) ) {
                    int LA98_1 = input.LA(2);

                    if ( (synpred144_ClangLL()) ) {
                        alt98=1;
                    }


                }
                else if ( ((LA98_0>=DIVIDE && LA98_0<=MODULO)) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1598:4: (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr
            	    {
            	    // resources/Clang/ClangLL.g:1598:4: (sign= STAR | sign= DIVIDE | sign= MODULO )
            	    int alt97=3;
            	    switch ( input.LA(1) ) {
            	    case STAR:
            	        {
            	        alt97=1;
            	        }
            	        break;
            	    case DIVIDE:
            	        {
            	        alt97=2;
            	        }
            	        break;
            	    case MODULO:
            	        {
            	        alt97=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 97, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt97) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1598:6: sign= STAR
            	            {
            	            sign=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicative_expr4161); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/ClangLL.g:1598:18: sign= DIVIDE
            	            {
            	            sign=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_multiplicative_expr4167); if (state.failed) return value;

            	            }
            	            break;
            	        case 3 :
            	            // resources/Clang/ClangLL.g:1598:32: sign= MODULO
            	            {
            	            sign=(Token)match(input,MODULO,FOLLOW_MODULO_in_multiplicative_expr4173); if (state.failed) return value;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_cast_expr_in_multiplicative_expr4181);
            	    e2=cast_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value = new MultiplicativeExpr(value, e2, getIdForSymbol((sign!=null?sign.getText():null), value));
            	    }

            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 62, multiplicative_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "multiplicative_expr"


    // $ANTLR start "cast_expr"
    // resources/Clang/ClangLL.g:1601:1: cast_expr returns [CastExpr value] : (e= unary_expr | a1= LPAREN e1= type_name RPAREN e2= cast_expr );
    public final CastExpr cast_expr() throws RecognitionException {
        CastExpr value = null;
        int cast_expr_StartIndex = input.index();
        Token a1=null;
        UnaryExpr e = null;

        TypeName e1 = null;

        CastExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return value; }
            // resources/Clang/ClangLL.g:1602:2: (e= unary_expr | a1= LPAREN e1= type_name RPAREN e2= cast_expr )
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==LPAREN) ) {
                int LA99_1 = input.LA(2);

                if ( (synpred145_ClangLL()) ) {
                    alt99=1;
                }
                else if ( (true) ) {
                    alt99=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 99, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA99_0>=PLUS && LA99_0<=MINUS)||LA99_0==NOT||(LA99_0>=AMPERSAND && LA99_0<=SIZEOF)||LA99_0==TILDE||LA99_0==STAR||(LA99_0>=DECIMAL_LITERAL && LA99_0<=OCTAL_LITERAL)||LA99_0==HEX_LITERAL||LA99_0==FLOATING_LITERAL||LA99_0==CHAR_LITERAL||(LA99_0>=ID && LA99_0<=STRING_LITERAL)) ) {
                alt99=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;
            }
            switch (alt99) {
                case 1 :
                    // resources/Clang/ClangLL.g:1602:4: e= unary_expr
                    {
                    pushFollow(FOLLOW_unary_expr_in_cast_expr4201);
                    e=unary_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new CastExpr(); value.setUnaryExpr(e); setLineNumAndPos(value, e); 
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1603:4: a1= LPAREN e1= type_name RPAREN e2= cast_expr
                    {
                    a1=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_cast_expr4210); if (state.failed) return value;
                    pushFollow(FOLLOW_type_name_in_cast_expr4214);
                    e1=type_name();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new CastExpr(); value.setTypeName(e1);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_cast_expr4221); if (state.failed) return value;
                    pushFollow(FOLLOW_cast_expr_in_cast_expr4225);
                    e2=cast_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setCastExpr(e2); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 63, cast_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "cast_expr"


    // $ANTLR start "unary_expr"
    // resources/Clang/ClangLL.g:1606:1: unary_expr returns [UnaryExpr value] : (e= postfix_expr | a1= INCREMENT e1= unary_expr | a2= DECREMENT e2= unary_expr | (a3= AMPERSAND | a4= STAR | a5= PLUS | a6= MINUS | a7= TILDE | a8= NOT ) e3= cast_expr | a9= SIZEOF e4= unary_expr | a10= SIZEOF LPAREN e5= type_name RPAREN );
    public final UnaryExpr unary_expr() throws RecognitionException {
        UnaryExpr value = null;
        int unary_expr_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;
        Token a6=null;
        Token a7=null;
        Token a8=null;
        Token a9=null;
        Token a10=null;
        PostfixExpr e = null;

        UnaryExpr e1 = null;

        UnaryExpr e2 = null;

        CastExpr e3 = null;

        UnaryExpr e4 = null;

        TypeName e5 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return value; }
            // resources/Clang/ClangLL.g:1607:2: (e= postfix_expr | a1= INCREMENT e1= unary_expr | a2= DECREMENT e2= unary_expr | (a3= AMPERSAND | a4= STAR | a5= PLUS | a6= MINUS | a7= TILDE | a8= NOT ) e3= cast_expr | a9= SIZEOF e4= unary_expr | a10= SIZEOF LPAREN e5= type_name RPAREN )
            int alt101=6;
            switch ( input.LA(1) ) {
            case LPAREN:
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case HEX_LITERAL:
            case FLOATING_LITERAL:
            case CHAR_LITERAL:
            case ID:
            case STRING_LITERAL:
                {
                alt101=1;
                }
                break;
            case INCREMENT:
                {
                alt101=2;
                }
                break;
            case DECREMENT:
                {
                alt101=3;
                }
                break;
            case PLUS:
            case MINUS:
            case NOT:
            case AMPERSAND:
            case TILDE:
            case STAR:
                {
                alt101=4;
                }
                break;
            case SIZEOF:
                {
                int LA101_5 = input.LA(2);

                if ( (synpred155_ClangLL()) ) {
                    alt101=5;
                }
                else if ( (true) ) {
                    alt101=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }

            switch (alt101) {
                case 1 :
                    // resources/Clang/ClangLL.g:1607:4: e= postfix_expr
                    {
                    pushFollow(FOLLOW_postfix_expr_in_unary_expr4244);
                    e=postfix_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new UnaryExpr(); value.setPostfixExpr(e); setLineNumAndPos(value, e);
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1608:4: a1= INCREMENT e1= unary_expr
                    {
                    a1=(Token)match(input,INCREMENT,FOLLOW_INCREMENT_in_unary_expr4253); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new UnaryExpr(); value.setOperator(UnaryExpr.INCR);
                    }
                    pushFollow(FOLLOW_unary_expr_in_unary_expr4259);
                    e1=unary_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setUnaryExpr(e1); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1609:4: a2= DECREMENT e2= unary_expr
                    {
                    a2=(Token)match(input,DECREMENT,FOLLOW_DECREMENT_in_unary_expr4268); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new UnaryExpr(); value.setOperator(UnaryExpr.DECR);
                    }
                    pushFollow(FOLLOW_unary_expr_in_unary_expr4274);
                    e2=unary_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setUnaryExpr(e2); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1610:4: (a3= AMPERSAND | a4= STAR | a5= PLUS | a6= MINUS | a7= TILDE | a8= NOT ) e3= cast_expr
                    {
                    // resources/Clang/ClangLL.g:1610:4: (a3= AMPERSAND | a4= STAR | a5= PLUS | a6= MINUS | a7= TILDE | a8= NOT )
                    int alt100=6;
                    switch ( input.LA(1) ) {
                    case AMPERSAND:
                        {
                        alt100=1;
                        }
                        break;
                    case STAR:
                        {
                        alt100=2;
                        }
                        break;
                    case PLUS:
                        {
                        alt100=3;
                        }
                        break;
                    case MINUS:
                        {
                        alt100=4;
                        }
                        break;
                    case TILDE:
                        {
                        alt100=5;
                        }
                        break;
                    case NOT:
                        {
                        alt100=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 100, 0, input);

                        throw nvae;
                    }

                    switch (alt100) {
                        case 1 :
                            // resources/Clang/ClangLL.g:1610:5: a3= AMPERSAND
                            {
                            a3=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_unary_expr4283); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.AMPERSAND); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 2 :
                            // resources/Clang/ClangLL.g:1611:6: a4= STAR
                            {
                            a4=(Token)match(input,STAR,FOLLOW_STAR_in_unary_expr4295); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.STAR); setLineNumAndPos(value, (a4!=null?a4.getLine():0), (a4!=null?a4.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 3 :
                            // resources/Clang/ClangLL.g:1612:6: a5= PLUS
                            {
                            a5=(Token)match(input,PLUS,FOLLOW_PLUS_in_unary_expr4307); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.PLUS); setLineNumAndPos(value, (a5!=null?a5.getLine():0), (a5!=null?a5.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 4 :
                            // resources/Clang/ClangLL.g:1613:6: a6= MINUS
                            {
                            a6=(Token)match(input,MINUS,FOLLOW_MINUS_in_unary_expr4319); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.MINUS); setLineNumAndPos(value, (a6!=null?a6.getLine():0), (a6!=null?a6.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 5 :
                            // resources/Clang/ClangLL.g:1614:6: a7= TILDE
                            {
                            a7=(Token)match(input,TILDE,FOLLOW_TILDE_in_unary_expr4331); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.TILDE); setLineNumAndPos(value, (a7!=null?a7.getLine():0), (a7!=null?a7.getCharPositionInLine():0));
                            }

                            }
                            break;
                        case 6 :
                            // resources/Clang/ClangLL.g:1615:6: a8= NOT
                            {
                            a8=(Token)match(input,NOT,FOLLOW_NOT_in_unary_expr4343); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                              value = new UnaryExpr(); value.setOperator(UnaryExpr.NOT); setLineNumAndPos(value, (a8!=null?a8.getLine():0), (a8!=null?a8.getCharPositionInLine():0));
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_cast_expr_in_unary_expr4354);
                    e3=cast_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setCastExpr(e3);
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/ClangLL.g:1617:4: a9= SIZEOF e4= unary_expr
                    {
                    a9=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_unary_expr4363); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new UnaryExpr(); value.setOperator(UnaryExpr.SIZEOF);
                    }
                    pushFollow(FOLLOW_unary_expr_in_unary_expr4370);
                    e4=unary_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setUnaryExpr(e4); setLineNumAndPos(value, (a9!=null?a9.getLine():0), (a9!=null?a9.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 6 :
                    // resources/Clang/ClangLL.g:1618:4: a10= SIZEOF LPAREN e5= type_name RPAREN
                    {
                    a10=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_unary_expr4379); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new UnaryExpr(); value.setOperator(UnaryExpr.SIZEOF); 
                    }
                    match(input,LPAREN,FOLLOW_LPAREN_in_unary_expr4384); if (state.failed) return value;
                    pushFollow(FOLLOW_type_name_in_unary_expr4391);
                    e5=type_name();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value.setTypeName(e5); setLineNumAndPos(value, (a10!=null?a10.getLine():0), (a10!=null?a10.getCharPositionInLine():0));
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_unary_expr4395); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 64, unary_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "unary_expr"


    // $ANTLR start "postfix_expr"
    // resources/Clang/ClangLL.g:1621:1: postfix_expr returns [PostfixExpr value] : e1= primary_expr ( ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT ) )* ;
    public final PostfixExpr postfix_expr() throws RecognitionException {
        PostfixExpr value = null;
        int postfix_expr_StartIndex = input.index();
        Token e=null;
        Token e4=null;
        PrimaryExpr e1 = null;

        RootExpr e2 = null;

        ArgumentExpressionList e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return value; }
            // resources/Clang/ClangLL.g:1622:2: (e1= primary_expr ( ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT ) )* )
            // resources/Clang/ClangLL.g:1622:4: e1= primary_expr ( ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT ) )*
            {
            pushFollow(FOLLOW_primary_expr_in_postfix_expr4411);
            e1=primary_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new PostfixExpr(); value.setPrimaryExpr(e1); setLineNumAndPos(value, e1);
            }
            // resources/Clang/ClangLL.g:1623:3: ( ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT ) )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==LPAREN||(LA104_0>=INCREMENT && LA104_0<=DECREMENT)) ) {
                    int LA104_1 = input.LA(2);

                    if ( (synpred162_ClangLL()) ) {
                        alt104=1;
                    }


                }
                else if ( (LA104_0==DOT||LA104_0==LBRACK||LA104_0==DEREFERENCE) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1623:4: ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT )
            	    {
            	    // resources/Clang/ClangLL.g:1623:4: ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT )
            	    int alt103=6;
            	    switch ( input.LA(1) ) {
            	    case LBRACK:
            	        {
            	        alt103=1;
            	        }
            	        break;
            	    case LPAREN:
            	        {
            	        alt103=2;
            	        }
            	        break;
            	    case DOT:
            	        {
            	        alt103=3;
            	        }
            	        break;
            	    case DEREFERENCE:
            	        {
            	        alt103=4;
            	        }
            	        break;
            	    case INCREMENT:
            	        {
            	        alt103=5;
            	        }
            	        break;
            	    case DECREMENT:
            	        {
            	        alt103=6;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return value;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 103, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt103) {
            	        case 1 :
            	            // resources/Clang/ClangLL.g:1623:6: LBRACK e2= expression RBRACK
            	            {
            	            match(input,LBRACK,FOLLOW_LBRACK_in_postfix_expr4420); if (state.failed) return value;
            	            pushFollow(FOLLOW_expression_in_postfix_expr4424);
            	            e2=expression();

            	            state._fsp--;
            	            if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	              addToPostFixExpr(e2, null, PostfixExpr.ARRAY_REF, null, value); 
            	            }
            	            match(input,RBRACK,FOLLOW_RBRACK_in_postfix_expr4428); if (state.failed) return value;

            	            }
            	            break;
            	        case 2 :
            	            // resources/Clang/ClangLL.g:1624:5: LPAREN (e3= argument_expr_list )? RPAREN
            	            {
            	            match(input,LPAREN,FOLLOW_LPAREN_in_postfix_expr4435); if (state.failed) return value;
            	            // resources/Clang/ClangLL.g:1624:12: (e3= argument_expr_list )?
            	            int alt102=2;
            	            int LA102_0 = input.LA(1);

            	            if ( ((LA102_0>=PLUS && LA102_0<=MINUS)||LA102_0==LPAREN||LA102_0==NOT||(LA102_0>=AMPERSAND && LA102_0<=SIZEOF)||LA102_0==TILDE||LA102_0==STAR||(LA102_0>=DECIMAL_LITERAL && LA102_0<=OCTAL_LITERAL)||LA102_0==HEX_LITERAL||LA102_0==FLOATING_LITERAL||LA102_0==CHAR_LITERAL||(LA102_0>=ID && LA102_0<=STRING_LITERAL)) ) {
            	                alt102=1;
            	            }
            	            switch (alt102) {
            	                case 1 :
            	                    // resources/Clang/ClangLL.g:1624:13: e3= argument_expr_list
            	                    {
            	                    pushFollow(FOLLOW_argument_expr_list_in_postfix_expr4440);
            	                    e3=argument_expr_list();

            	                    state._fsp--;
            	                    if (state.failed) return value;

            	                    }
            	                    break;

            	            }

            	            match(input,RPAREN,FOLLOW_RPAREN_in_postfix_expr4445); if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	              addToPostFixExpr(null, e3, PostfixExpr.FUNC_CALL, null, value); 
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // resources/Clang/ClangLL.g:1625:5: DOT e= ID
            	            {
            	            match(input,DOT,FOLLOW_DOT_in_postfix_expr4454); if (state.failed) return value;
            	            e=(Token)match(input,ID,FOLLOW_ID_in_postfix_expr4458); if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	              addToPostFixExpr(null, null, PostfixExpr.DOT, (e!=null?e.getText():null), value); 
            	            }

            	            }
            	            break;
            	        case 4 :
            	            // resources/Clang/ClangLL.g:1626:5: DEREFERENCE e4= ID
            	            {
            	            match(input,DEREFERENCE,FOLLOW_DEREFERENCE_in_postfix_expr4466); if (state.failed) return value;
            	            e4=(Token)match(input,ID,FOLLOW_ID_in_postfix_expr4470); if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	              addToPostFixExpr(null, null, PostfixExpr.DEREFERENCE, (e4!=null?e4.getText():null), value); 
            	            }

            	            }
            	            break;
            	        case 5 :
            	            // resources/Clang/ClangLL.g:1627:5: INCREMENT
            	            {
            	            match(input,INCREMENT,FOLLOW_INCREMENT_in_postfix_expr4478); if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	              addToPostFixExpr(null, null, PostfixExpr.INCR, null, value); 
            	            }

            	            }
            	            break;
            	        case 6 :
            	            // resources/Clang/ClangLL.g:1628:5: DECREMENT
            	            {
            	            match(input,DECREMENT,FOLLOW_DECREMENT_in_postfix_expr4486); if (state.failed) return value;
            	            if ( state.backtracking==0 ) {
            	              addToPostFixExpr(null, null, PostfixExpr.DECR, null, value); 
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 65, postfix_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "postfix_expr"


    // $ANTLR start "primary_expr"
    // resources/Clang/ClangLL.g:1631:1: primary_expr returns [PrimaryExpr value] : (e= ID | e1= constant | e2= STRING_LITERAL | a1= LPAREN e3= expression RPAREN );
    public final PrimaryExpr primary_expr() throws RecognitionException {
        PrimaryExpr value = null;
        int primary_expr_StartIndex = input.index();
        Token e=null;
        Token e2=null;
        Token a1=null;
        Constant e1 = null;

        RootExpr e3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return value; }
            // resources/Clang/ClangLL.g:1632:2: (e= ID | e1= constant | e2= STRING_LITERAL | a1= LPAREN e3= expression RPAREN )
            int alt105=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt105=1;
                }
                break;
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case HEX_LITERAL:
            case FLOATING_LITERAL:
            case CHAR_LITERAL:
                {
                alt105=2;
                }
                break;
            case STRING_LITERAL:
                {
                alt105=3;
                }
                break;
            case LPAREN:
                {
                alt105=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;
            }

            switch (alt105) {
                case 1 :
                    // resources/Clang/ClangLL.g:1632:4: e= ID
                    {
                    e=(Token)match(input,ID,FOLLOW_ID_in_primary_expr4511); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.IDENTIFIER_TYPE); value.setIdentifier((e!=null?e.getText():null)); setLineNumAndPos(value, (e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0)); 
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1633:4: e1= constant
                    {
                    pushFollow(FOLLOW_constant_in_primary_expr4520);
                    e1=constant();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.CONSTANT_TYPE); value.setConstant(e1); setLineNumAndPos(value, e1);
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1634:4: e2= STRING_LITERAL
                    {
                    e2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_primary_expr4529); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.STRING_TYPE); value.setString((e2!=null?e2.getText():null)); setLineNumAndPos(value, (e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1635:4: a1= LPAREN e3= expression RPAREN
                    {
                    a1=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_primary_expr4538); if (state.failed) return value;
                    pushFollow(FOLLOW_expression_in_primary_expr4542);
                    e3=expression();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new PrimaryExpr(PrimaryExpr.EXPR_TYPE); value.setExpr(e3); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_primary_expr4546); if (state.failed) return value;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 66, primary_expr_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "primary_expr"


    // $ANTLR start "argument_expr_list"
    // resources/Clang/ClangLL.g:1637:1: argument_expr_list returns [ArgumentExpressionList value] : (e1= assignment_expression ) ( COMMA e2= assignment_expression )* ;
    public final ArgumentExpressionList argument_expr_list() throws RecognitionException {
        ArgumentExpressionList value = null;
        int argument_expr_list_StartIndex = input.index();
        AssignmentExpr e1 = null;

        AssignmentExpr e2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return value; }
            // resources/Clang/ClangLL.g:1638:2: ( (e1= assignment_expression ) ( COMMA e2= assignment_expression )* )
            // resources/Clang/ClangLL.g:1638:4: (e1= assignment_expression ) ( COMMA e2= assignment_expression )*
            {
            // resources/Clang/ClangLL.g:1638:4: (e1= assignment_expression )
            // resources/Clang/ClangLL.g:1638:5: e1= assignment_expression
            {
            pushFollow(FOLLOW_assignment_expression_in_argument_expr_list4563);
            e1=assignment_expression();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = (ArgumentExpressionList)addNodeToCollection(e1, value);
            }

            }

            // resources/Clang/ClangLL.g:1639:4: ( COMMA e2= assignment_expression )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==COMMA) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // resources/Clang/ClangLL.g:1639:5: COMMA e2= assignment_expression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_argument_expr_list4572); if (state.failed) return value;
            	    pushFollow(FOLLOW_assignment_expression_in_argument_expr_list4576);
            	    e2=assignment_expression();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	      value =  (ArgumentExpressionList) addNodeToCollection(e2, value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 67, argument_expr_list_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "argument_expr_list"


    // $ANTLR start "constant"
    // resources/Clang/ClangLL.g:1641:1: constant returns [Constant value] : (a1= DECIMAL_LITERAL | a2= OCTAL_LITERAL | a3= HEX_LITERAL | a4= FLOATING_LITERAL | a5= CHAR_LITERAL );
    public final Constant constant() throws RecognitionException {
        Constant value = null;
        int constant_StartIndex = input.index();
        Token a1=null;
        Token a2=null;
        Token a3=null;
        Token a4=null;
        Token a5=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return value; }
            // resources/Clang/ClangLL.g:1642:2: (a1= DECIMAL_LITERAL | a2= OCTAL_LITERAL | a3= HEX_LITERAL | a4= FLOATING_LITERAL | a5= CHAR_LITERAL )
            int alt107=5;
            switch ( input.LA(1) ) {
            case DECIMAL_LITERAL:
                {
                alt107=1;
                }
                break;
            case OCTAL_LITERAL:
                {
                alt107=2;
                }
                break;
            case HEX_LITERAL:
                {
                alt107=3;
                }
                break;
            case FLOATING_LITERAL:
                {
                alt107=4;
                }
                break;
            case CHAR_LITERAL:
                {
                alt107=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }

            switch (alt107) {
                case 1 :
                    // resources/Clang/ClangLL.g:1642:4: a1= DECIMAL_LITERAL
                    {
                    a1=(Token)match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_constant4596); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setIntConstant((a1!=null?a1.getText():null)); setLineNumAndPos(value, (a1!=null?a1.getLine():0), (a1!=null?a1.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // resources/Clang/ClangLL.g:1643:4: a2= OCTAL_LITERAL
                    {
                    a2=(Token)match(input,OCTAL_LITERAL,FOLLOW_OCTAL_LITERAL_in_constant4606); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setIntConstant((a2!=null?a2.getText():null)); setLineNumAndPos(value, (a2!=null?a2.getLine():0), (a2!=null?a2.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // resources/Clang/ClangLL.g:1644:4: a3= HEX_LITERAL
                    {
                    a3=(Token)match(input,HEX_LITERAL,FOLLOW_HEX_LITERAL_in_constant4616); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setIntConstant((a3!=null?a3.getText():null)); setLineNumAndPos(value, (a3!=null?a3.getLine():0), (a3!=null?a3.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 4 :
                    // resources/Clang/ClangLL.g:1645:4: a4= FLOATING_LITERAL
                    {
                    a4=(Token)match(input,FLOATING_LITERAL,FOLLOW_FLOATING_LITERAL_in_constant4626); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new Constant(); value.setFloatingConstant((a4!=null?a4.getText():null)); setLineNumAndPos(value, (a4!=null?a4.getLine():0), (a4!=null?a4.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 5 :
                    // resources/Clang/ClangLL.g:1646:4: a5= CHAR_LITERAL
                    {
                    a5=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_constant4636); if (state.failed) return value;
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
            if ( state.backtracking>0 ) { memoize(input, 68, constant_StartIndex); }
        }
        return value;
    }
    // $ANTLR end "constant"

    // $ANTLR start synpred2_ClangLL
    public final void synpred2_ClangLL_fragment() throws RecognitionException {   
        DeclarationSpecifiers e1 = null;


        // resources/Clang/ClangLL.g:1263:7: (e1= declaration_specifiers )
        // resources/Clang/ClangLL.g:1263:7: e1= declaration_specifiers
        {
        pushFollow(FOLLOW_declaration_specifiers_in_synpred2_ClangLL1266);
        e1=declaration_specifiers();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_ClangLL

    // $ANTLR start synpred4_ClangLL
    public final void synpred4_ClangLL_fragment() throws RecognitionException {   
        DeclarationSpecifiers e1 = null;

        Declarator e2 = null;


        // resources/Clang/ClangLL.g:1263:4: ( (e1= declaration_specifiers )? e2= declarator ( declaration )* '{' )
        // resources/Clang/ClangLL.g:1263:5: (e1= declaration_specifiers )? e2= declarator ( declaration )* '{'
        {
        // resources/Clang/ClangLL.g:1263:7: (e1= declaration_specifiers )?
        int alt108=2;
        alt108 = dfa108.predict(input);
        switch (alt108) {
            case 1 :
                // resources/Clang/ClangLL.g:0:0: e1= declaration_specifiers
                {
                pushFollow(FOLLOW_declaration_specifiers_in_synpred4_ClangLL1266);
                e1=declaration_specifiers();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_declarator_in_synpred4_ClangLL1271);
        e2=declarator();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/ClangLL.g:1263:46: ( declaration )*
        loop109:
        do {
            int alt109=2;
            alt109 = dfa109.predict(input);
            switch (alt109) {
        	case 1 :
        	    // resources/Clang/ClangLL.g:0:0: declaration
        	    {
        	    pushFollow(FOLLOW_declaration_in_synpred4_ClangLL1273);
        	    declaration();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop109;
            }
        } while (true);

        match(input,LCURLY,FOLLOW_LCURLY_in_synpred4_ClangLL1276); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_ClangLL

    // $ANTLR start synpred5_ClangLL
    public final void synpred5_ClangLL_fragment() throws RecognitionException {   
        Declaration e4 = null;


        // resources/Clang/ClangLL.g:1264:4: ( (e4= declaration ) )
        // resources/Clang/ClangLL.g:1264:4: (e4= declaration )
        {
        // resources/Clang/ClangLL.g:1264:4: (e4= declaration )
        // resources/Clang/ClangLL.g:1264:5: e4= declaration
        {
        pushFollow(FOLLOW_declaration_in_synpred5_ClangLL1293);
        e4=declaration();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred5_ClangLL

    // $ANTLR start synpred6_ClangLL
    public final void synpred6_ClangLL_fragment() throws RecognitionException {   
        Token e0=null;
        FileNameLib e1 = null;


        // resources/Clang/ClangLL.g:1268:2: (e0= HASH e1= filename_lib HASH )
        // resources/Clang/ClangLL.g:1268:2: e0= HASH e1= filename_lib HASH
        {
        e0=(Token)match(input,HASH,FOLLOW_HASH_in_synpred6_ClangLL1324); if (state.failed) return ;
        pushFollow(FOLLOW_filename_lib_in_synpred6_ClangLL1328);
        e1=filename_lib();

        state._fsp--;
        if (state.failed) return ;
        match(input,HASH,FOLLOW_HASH_in_synpred6_ClangLL1330); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_ClangLL

    // $ANTLR start synpred10_ClangLL
    public final void synpred10_ClangLL_fragment() throws RecognitionException {   
        DeclarationSpecifiers e1 = null;


        // resources/Clang/ClangLL.g:1278:5: (e1= declaration_specifiers )
        // resources/Clang/ClangLL.g:1278:5: e1= declaration_specifiers
        {
        pushFollow(FOLLOW_declaration_specifiers_in_synpred10_ClangLL1435);
        e1=declaration_specifiers();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_ClangLL

    // $ANTLR start synpred13_ClangLL
    public final void synpred13_ClangLL_fragment() throws RecognitionException {   
        DeclarationSpecifiers e3 = null;


        // resources/Clang/ClangLL.g:1285:15: (e3= declaration_specifiers )
        // resources/Clang/ClangLL.g:1285:15: e3= declaration_specifiers
        {
        pushFollow(FOLLOW_declaration_specifiers_in_synpred13_ClangLL1501);
        e3=declaration_specifiers();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_ClangLL

    // $ANTLR start synpred15_ClangLL
    public final void synpred15_ClangLL_fragment() throws RecognitionException {   
        TypeSpecifier e2 = null;


        // resources/Clang/ClangLL.g:1290:4: (e2= type_specifier )
        // resources/Clang/ClangLL.g:1290:4: e2= type_specifier
        {
        pushFollow(FOLLOW_type_specifier_in_synpred15_ClangLL1543);
        e2=type_specifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_ClangLL

    // $ANTLR start synpred32_ClangLL
    public final void synpred32_ClangLL_fragment() throws RecognitionException {   
        TypeName e2 = null;


        // resources/Clang/ClangLL.g:1316:16: (e2= type_name )
        // resources/Clang/ClangLL.g:1316:16: e2= type_name
        {
        pushFollow(FOLLOW_type_name_in_synpred32_ClangLL1758);
        e2=type_name();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred32_ClangLL

    // $ANTLR start synpred35_ClangLL
    public final void synpred35_ClangLL_fragment() throws RecognitionException {   
        Token e2=null;
        StructOrUnion e1 = null;

        StructDeclarationList e3 = null;


        // resources/Clang/ClangLL.g:1326:4: (e1= struct_or_union (e2= ID )? LCURLY e3= struct_decl_list RCURLY )
        // resources/Clang/ClangLL.g:1326:4: e1= struct_or_union (e2= ID )? LCURLY e3= struct_decl_list RCURLY
        {
        pushFollow(FOLLOW_struct_or_union_in_synpred35_ClangLL1837);
        e1=struct_or_union();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/ClangLL.g:1328:3: (e2= ID )?
        int alt112=2;
        int LA112_0 = input.LA(1);

        if ( (LA112_0==ID) ) {
            alt112=1;
        }
        switch (alt112) {
            case 1 :
                // resources/Clang/ClangLL.g:1328:4: e2= ID
                {
                e2=(Token)match(input,ID,FOLLOW_ID_in_synpred35_ClangLL1846); if (state.failed) return ;

                }
                break;

        }

        match(input,LCURLY,FOLLOW_LCURLY_in_synpred35_ClangLL1857); if (state.failed) return ;
        pushFollow(FOLLOW_struct_decl_list_in_synpred35_ClangLL1861);
        e3=struct_decl_list();

        state._fsp--;
        if (state.failed) return ;
        match(input,RCURLY,FOLLOW_RCURLY_in_synpred35_ClangLL1865); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_ClangLL

    // $ANTLR start synpred43_ClangLL
    public final void synpred43_ClangLL_fragment() throws RecognitionException {   
        TypeSpecifier e1 = null;


        // resources/Clang/ClangLL.g:1354:5: (e1= type_specifier )
        // resources/Clang/ClangLL.g:1354:5: e1= type_specifier
        {
        pushFollow(FOLLOW_type_specifier_in_synpred43_ClangLL2063);
        e1=type_specifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_ClangLL

    // $ANTLR start synpred46_ClangLL
    public final void synpred46_ClangLL_fragment() throws RecognitionException {   
        ConstExpr e2 = null;


        // resources/Clang/ClangLL.g:1363:7: ( COLON e2= constant_expression )
        // resources/Clang/ClangLL.g:1363:7: COLON e2= constant_expression
        {
        match(input,COLON,FOLLOW_COLON_in_synpred46_ClangLL2136); if (state.failed) return ;
        pushFollow(FOLLOW_constant_expression_in_synpred46_ClangLL2140);
        e2=constant_expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred46_ClangLL

    // $ANTLR start synpred48_ClangLL
    public final void synpred48_ClangLL_fragment() throws RecognitionException {   
        ConstExpr e3 = null;


        // resources/Clang/ClangLL.g:1364:12: (e3= constant_expression )
        // resources/Clang/ClangLL.g:1364:12: e3= constant_expression
        {
        pushFollow(FOLLOW_constant_expression_in_synpred48_ClangLL2156);
        e3=constant_expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_ClangLL

    // $ANTLR start synpred50_ClangLL
    public final void synpred50_ClangLL_fragment() throws RecognitionException {   
        Token e1=null;
        EnumList e2 = null;


        // resources/Clang/ClangLL.g:1367:5: (e1= ENUM ( ID )? LCURLY e2= enumarator_list RCURLY )
        // resources/Clang/ClangLL.g:1367:5: e1= ENUM ( ID )? LCURLY e2= enumarator_list RCURLY
        {
        e1=(Token)match(input,ENUM,FOLLOW_ENUM_in_synpred50_ClangLL2179); if (state.failed) return ;
        // resources/Clang/ClangLL.g:1367:13: ( ID )?
        int alt116=2;
        int LA116_0 = input.LA(1);

        if ( (LA116_0==ID) ) {
            alt116=1;
        }
        switch (alt116) {
            case 1 :
                // resources/Clang/ClangLL.g:1367:14: ID
                {
                match(input,ID,FOLLOW_ID_in_synpred50_ClangLL2182); if (state.failed) return ;

                }
                break;

        }

        match(input,LCURLY,FOLLOW_LCURLY_in_synpred50_ClangLL2190); if (state.failed) return ;
        pushFollow(FOLLOW_enumarator_list_in_synpred50_ClangLL2194);
        e2=enumarator_list();

        state._fsp--;
        if (state.failed) return ;
        match(input,RCURLY,FOLLOW_RCURLY_in_synpred50_ClangLL2198); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred50_ClangLL

    // $ANTLR start synpred54_ClangLL
    public final void synpred54_ClangLL_fragment() throws RecognitionException {   
        Pointer e1 = null;

        DirectDeclarator e2 = null;


        // resources/Clang/ClangLL.g:1380:4: ( (e1= pointer )? e2= direct_declarator )
        // resources/Clang/ClangLL.g:1380:4: (e1= pointer )? e2= direct_declarator
        {
        // resources/Clang/ClangLL.g:1380:4: (e1= pointer )?
        int alt117=2;
        int LA117_0 = input.LA(1);

        if ( (LA117_0==STAR) ) {
            alt117=1;
        }
        switch (alt117) {
            case 1 :
                // resources/Clang/ClangLL.g:1380:5: e1= pointer
                {
                pushFollow(FOLLOW_pointer_in_synpred54_ClangLL2295);
                e1=pointer();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_direct_declarator_in_synpred54_ClangLL2308);
        e2=direct_declarator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred54_ClangLL

    // $ANTLR start synpred57_ClangLL
    public final void synpred57_ClangLL_fragment() throws RecognitionException {   
        Token v1=null;
        RootExpr e2 = null;


        // resources/Clang/ClangLL.g:1391:6: (v1= LBRACK (e2= expression )? RBRACK )
        // resources/Clang/ClangLL.g:1391:6: v1= LBRACK (e2= expression )? RBRACK
        {
        v1=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_synpred57_ClangLL2364); if (state.failed) return ;
        // resources/Clang/ClangLL.g:1391:16: (e2= expression )?
        int alt118=2;
        int LA118_0 = input.LA(1);

        if ( ((LA118_0>=PLUS && LA118_0<=MINUS)||LA118_0==LPAREN||LA118_0==NOT||(LA118_0>=AMPERSAND && LA118_0<=SIZEOF)||LA118_0==TILDE||LA118_0==STAR||(LA118_0>=DECIMAL_LITERAL && LA118_0<=OCTAL_LITERAL)||LA118_0==HEX_LITERAL||LA118_0==FLOATING_LITERAL||LA118_0==CHAR_LITERAL||(LA118_0>=ID && LA118_0<=STRING_LITERAL)) ) {
            alt118=1;
        }
        switch (alt118) {
            case 1 :
                // resources/Clang/ClangLL.g:1391:17: e2= expression
                {
                pushFollow(FOLLOW_expression_in_synpred57_ClangLL2369);
                e2=expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RBRACK,FOLLOW_RBRACK_in_synpred57_ClangLL2373); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred57_ClangLL

    // $ANTLR start synpred59_ClangLL
    public final void synpred59_ClangLL_fragment() throws RecognitionException {   
        Token v2=null;
        ParamTypeList e3 = null;


        // resources/Clang/ClangLL.g:1400:6: (v2= LPAREN (e3= parameter_type_list )? RPAREN )
        // resources/Clang/ClangLL.g:1400:6: v2= LPAREN (e3= parameter_type_list )? RPAREN
        {
        v2=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_synpred59_ClangLL2386); if (state.failed) return ;
        // resources/Clang/ClangLL.g:1400:16: (e3= parameter_type_list )?
        int alt119=2;
        alt119 = dfa119.predict(input);
        switch (alt119) {
            case 1 :
                // resources/Clang/ClangLL.g:1400:17: e3= parameter_type_list
                {
                pushFollow(FOLLOW_parameter_type_list_in_synpred59_ClangLL2391);
                e3=parameter_type_list();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred59_ClangLL2395); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred59_ClangLL

    // $ANTLR start synpred61_ClangLL
    public final void synpred61_ClangLL_fragment() throws RecognitionException {   
        Token v3=null;
        IdentifierList e4 = null;


        // resources/Clang/ClangLL.g:1407:6: (v3= LPAREN (e4= identifier_list )? RPAREN )
        // resources/Clang/ClangLL.g:1407:6: v3= LPAREN (e4= identifier_list )? RPAREN
        {
        v3=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_synpred61_ClangLL2408); if (state.failed) return ;
        // resources/Clang/ClangLL.g:1407:16: (e4= identifier_list )?
        int alt120=2;
        int LA120_0 = input.LA(1);

        if ( (LA120_0==ID) ) {
            alt120=1;
        }
        switch (alt120) {
            case 1 :
                // resources/Clang/ClangLL.g:1407:17: e4= identifier_list
                {
                pushFollow(FOLLOW_identifier_list_in_synpred61_ClangLL2413);
                e4=identifier_list();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred61_ClangLL2417); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred61_ClangLL

    // $ANTLR start synpred62_ClangLL
    public final void synpred62_ClangLL_fragment() throws RecognitionException {   
        Pointer e2 = null;


        // resources/Clang/ClangLL.g:1416:148: (e2= pointer )
        // resources/Clang/ClangLL.g:1416:148: e2= pointer
        {
        pushFollow(FOLLOW_pointer_in_synpred62_ClangLL2453);
        e2=pointer();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred62_ClangLL

    // $ANTLR start synpred63_ClangLL
    public final void synpred63_ClangLL_fragment() throws RecognitionException {   
        Token s1=null;
        TypeQualifierList e1 = null;

        Pointer e2 = null;


        // resources/Clang/ClangLL.g:1416:4: (s1= STAR e1= type_qualifier_list (e2= pointer )? )
        // resources/Clang/ClangLL.g:1416:4: s1= STAR e1= type_qualifier_list (e2= pointer )?
        {
        s1=(Token)match(input,STAR,FOLLOW_STAR_in_synpred63_ClangLL2442); if (state.failed) return ;
        pushFollow(FOLLOW_type_qualifier_list_in_synpred63_ClangLL2446);
        e1=type_qualifier_list();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/ClangLL.g:1416:147: (e2= pointer )?
        int alt121=2;
        int LA121_0 = input.LA(1);

        if ( (LA121_0==STAR) ) {
            alt121=1;
        }
        switch (alt121) {
            case 1 :
                // resources/Clang/ClangLL.g:1416:148: e2= pointer
                {
                pushFollow(FOLLOW_pointer_in_synpred63_ClangLL2453);
                e2=pointer();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred63_ClangLL

    // $ANTLR start synpred64_ClangLL
    public final void synpred64_ClangLL_fragment() throws RecognitionException {   
        Token s2=null;
        Pointer e2 = null;


        // resources/Clang/ClangLL.g:1417:4: (s2= STAR e2= pointer )
        // resources/Clang/ClangLL.g:1417:4: s2= STAR e2= pointer
        {
        s2=(Token)match(input,STAR,FOLLOW_STAR_in_synpred64_ClangLL2464); if (state.failed) return ;
        pushFollow(FOLLOW_pointer_in_synpred64_ClangLL2468);
        e2=pointer();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred64_ClangLL

    // $ANTLR start synpred65_ClangLL
    public final void synpred65_ClangLL_fragment() throws RecognitionException {   
        TypeQualifier e1 = null;


        // resources/Clang/ClangLL.g:1421:5: (e1= type_qualifier )
        // resources/Clang/ClangLL.g:1421:5: e1= type_qualifier
        {
        pushFollow(FOLLOW_type_qualifier_in_synpred65_ClangLL2496);
        e1=type_qualifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred65_ClangLL

    // $ANTLR start synpred67_ClangLL
    public final void synpred67_ClangLL_fragment() throws RecognitionException {   
        ParamDeclaration e2 = null;


        // resources/Clang/ClangLL.g:1429:5: ( COMMA e2= param_declaration )
        // resources/Clang/ClangLL.g:1429:5: COMMA e2= param_declaration
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred67_ClangLL2564); if (state.failed) return ;
        pushFollow(FOLLOW_param_declaration_in_synpred67_ClangLL2568);
        e2=param_declaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred67_ClangLL

    // $ANTLR start synpred68_ClangLL
    public final void synpred68_ClangLL_fragment() throws RecognitionException {   
        Declarator e1 = null;


        // resources/Clang/ClangLL.g:1434:4: (e1= declarator )
        // resources/Clang/ClangLL.g:1434:4: e1= declarator
        {
        pushFollow(FOLLOW_declarator_in_synpred68_ClangLL2600);
        e1=declarator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred68_ClangLL

    // $ANTLR start synpred69_ClangLL
    public final void synpred69_ClangLL_fragment() throws RecognitionException {   
        AbstractDeclarator e2 = null;


        // resources/Clang/ClangLL.g:1435:9: (e2= abstract_declarator )
        // resources/Clang/ClangLL.g:1435:9: e2= abstract_declarator
        {
        pushFollow(FOLLOW_abstract_declarator_in_synpred69_ClangLL2615);
        e2=abstract_declarator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred69_ClangLL

    // $ANTLR start synpred71_ClangLL
    public final void synpred71_ClangLL_fragment() throws RecognitionException {   
        AssignmentExpr e1 = null;


        // resources/Clang/ClangLL.g:1442:4: (e1= assignment_expression )
        // resources/Clang/ClangLL.g:1442:4: e1= assignment_expression
        {
        pushFollow(FOLLOW_assignment_expression_in_synpred71_ClangLL2670);
        e1=assignment_expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred71_ClangLL

    // $ANTLR start synpred74_ClangLL
    public final void synpred74_ClangLL_fragment() throws RecognitionException {   
        CompoundStatement e3 = null;


        // resources/Clang/ClangLL.g:1443:6: ( ( LPAREN )? e3= compound_statement ( RPAREN )? )
        // resources/Clang/ClangLL.g:1443:6: ( LPAREN )? e3= compound_statement ( RPAREN )?
        {
        // resources/Clang/ClangLL.g:1443:6: ( LPAREN )?
        int alt122=2;
        int LA122_0 = input.LA(1);

        if ( (LA122_0==LPAREN) ) {
            alt122=1;
        }
        switch (alt122) {
            case 1 :
                // resources/Clang/ClangLL.g:0:0: LPAREN
                {
                match(input,LPAREN,FOLLOW_LPAREN_in_synpred74_ClangLL2679); if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_compound_statement_in_synpred74_ClangLL2684);
        e3=compound_statement();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/ClangLL.g:1443:143: ( RPAREN )?
        int alt123=2;
        int LA123_0 = input.LA(1);

        if ( (LA123_0==RPAREN) ) {
            alt123=1;
        }
        switch (alt123) {
            case 1 :
                // resources/Clang/ClangLL.g:0:0: RPAREN
                {
                match(input,RPAREN,FOLLOW_RPAREN_in_synpred74_ClangLL2688); if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred74_ClangLL

    // $ANTLR start synpred76_ClangLL
    public final void synpred76_ClangLL_fragment() throws RecognitionException {   
        Initializer e2 = null;


        // resources/Clang/ClangLL.g:1448:4: ( COMMA e2= initializer )
        // resources/Clang/ClangLL.g:1448:4: COMMA e2= initializer
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred76_ClangLL2730); if (state.failed) return ;
        pushFollow(FOLLOW_initializer_in_synpred76_ClangLL2734);
        e2=initializer();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred76_ClangLL

    // $ANTLR start synpred79_ClangLL
    public final void synpred79_ClangLL_fragment() throws RecognitionException {   
        DirectAbstractDeclarator e2 = null;


        // resources/Clang/ClangLL.g:1456:68: (e2= direct_abstract_declarator )
        // resources/Clang/ClangLL.g:1456:68: e2= direct_abstract_declarator
        {
        pushFollow(FOLLOW_direct_abstract_declarator_in_synpred79_ClangLL2798);
        e2=direct_abstract_declarator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred79_ClangLL

    // $ANTLR start synpred86_ClangLL
    public final void synpred86_ClangLL_fragment() throws RecognitionException {   
        ConstExpr e2 = null;


        // resources/Clang/ClangLL.g:1465:6: ( LBRACK (e2= constant_expression )? RBRACK )
        // resources/Clang/ClangLL.g:1465:6: LBRACK (e2= constant_expression )? RBRACK
        {
        match(input,LBRACK,FOLLOW_LBRACK_in_synpred86_ClangLL2900); if (state.failed) return ;
        // resources/Clang/ClangLL.g:1465:13: (e2= constant_expression )?
        int alt126=2;
        int LA126_0 = input.LA(1);

        if ( ((LA126_0>=PLUS && LA126_0<=MINUS)||LA126_0==LPAREN||LA126_0==NOT||(LA126_0>=AMPERSAND && LA126_0<=SIZEOF)||LA126_0==TILDE||LA126_0==STAR||(LA126_0>=DECIMAL_LITERAL && LA126_0<=OCTAL_LITERAL)||LA126_0==HEX_LITERAL||LA126_0==FLOATING_LITERAL||LA126_0==CHAR_LITERAL||(LA126_0>=ID && LA126_0<=STRING_LITERAL)) ) {
            alt126=1;
        }
        switch (alt126) {
            case 1 :
                // resources/Clang/ClangLL.g:1465:14: e2= constant_expression
                {
                pushFollow(FOLLOW_constant_expression_in_synpred86_ClangLL2905);
                e2=constant_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RBRACK,FOLLOW_RBRACK_in_synpred86_ClangLL2911); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred86_ClangLL

    // $ANTLR start synpred88_ClangLL
    public final void synpred88_ClangLL_fragment() throws RecognitionException {   
        ParamTypeList e3 = null;


        // resources/Clang/ClangLL.g:1466:4: ( LPAREN (e3= parameter_type_list )? RPAREN )
        // resources/Clang/ClangLL.g:1466:4: LPAREN (e3= parameter_type_list )? RPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred88_ClangLL2919); if (state.failed) return ;
        // resources/Clang/ClangLL.g:1466:11: (e3= parameter_type_list )?
        int alt127=2;
        alt127 = dfa127.predict(input);
        switch (alt127) {
            case 1 :
                // resources/Clang/ClangLL.g:1466:12: e3= parameter_type_list
                {
                pushFollow(FOLLOW_parameter_type_list_in_synpred88_ClangLL2924);
                e3=parameter_type_list();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred88_ClangLL2930); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred88_ClangLL

    // $ANTLR start synpred89_ClangLL
    public final void synpred89_ClangLL_fragment() throws RecognitionException {   
        LabeledStatement e1 = null;


        // resources/Clang/ClangLL.g:1469:4: (e1= labeled_statement )
        // resources/Clang/ClangLL.g:1469:4: e1= labeled_statement
        {
        pushFollow(FOLLOW_labeled_statement_in_synpred89_ClangLL2949);
        e1=labeled_statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred89_ClangLL

    // $ANTLR start synpred92_ClangLL
    public final void synpred92_ClangLL_fragment() throws RecognitionException {   
        ExprStatement e4 = null;


        // resources/Clang/ClangLL.g:1472:4: (e4= expr_statement )
        // resources/Clang/ClangLL.g:1472:4: e4= expr_statement
        {
        pushFollow(FOLLOW_expr_statement_in_synpred92_ClangLL2976);
        e4=expr_statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred92_ClangLL

    // $ANTLR start synpred99_ClangLL
    public final void synpred99_ClangLL_fragment() throws RecognitionException {   
        Declaration e1 = null;


        // resources/Clang/ClangLL.g:1493:4: ( (e1= declaration ) )
        // resources/Clang/ClangLL.g:1493:4: (e1= declaration )
        {
        // resources/Clang/ClangLL.g:1493:4: (e1= declaration )
        // resources/Clang/ClangLL.g:1493:5: e1= declaration
        {
        pushFollow(FOLLOW_declaration_in_synpred99_ClangLL3159);
        e1=declaration();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred99_ClangLL

    // $ANTLR start synpred103_ClangLL
    public final void synpred103_ClangLL_fragment() throws RecognitionException {   
        RootExpr e5 = null;


        // resources/Clang/ClangLL.g:1508:4: ( (e5= expression ) )
        // resources/Clang/ClangLL.g:1508:4: (e5= expression )
        {
        // resources/Clang/ClangLL.g:1508:4: (e5= expression )
        // resources/Clang/ClangLL.g:1508:5: e5= expression
        {
        pushFollow(FOLLOW_expression_in_synpred103_ClangLL3350);
        e5=expression();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred103_ClangLL

    // $ANTLR start synpred104_ClangLL
    public final void synpred104_ClangLL_fragment() throws RecognitionException {   
        Declaration e9 = null;


        // resources/Clang/ClangLL.g:1508:56: ( (e9= declaration ) )
        // resources/Clang/ClangLL.g:1508:56: (e9= declaration )
        {
        // resources/Clang/ClangLL.g:1508:56: (e9= declaration )
        // resources/Clang/ClangLL.g:1508:57: e9= declaration
        {
        pushFollow(FOLLOW_declaration_in_synpred104_ClangLL3358);
        e9=declaration();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred104_ClangLL

    // $ANTLR start synpred105_ClangLL
    public final void synpred105_ClangLL_fragment() throws RecognitionException {   
        // resources/Clang/ClangLL.g:1508:108: ( SEMICOLON )
        // resources/Clang/ClangLL.g:1508:108: SEMICOLON
        {
        match(input,SEMICOLON,FOLLOW_SEMICOLON_in_synpred105_ClangLL3365); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred105_ClangLL

    // $ANTLR start synpred113_ClangLL
    public final void synpred113_ClangLL_fragment() throws RecognitionException {   
        Token e0=null;
        FileNameLib e1 = null;


        // resources/Clang/ClangLL.g:1523:2: (e0= HASH LESSER_THAN e1= filename_lib GREATER_THAN HASH )
        // resources/Clang/ClangLL.g:1523:2: e0= HASH LESSER_THAN e1= filename_lib GREATER_THAN HASH
        {
        e0=(Token)match(input,HASH,FOLLOW_HASH_in_synpred113_ClangLL3511); if (state.failed) return ;
        match(input,LESSER_THAN,FOLLOW_LESSER_THAN_in_synpred113_ClangLL3513); if (state.failed) return ;
        pushFollow(FOLLOW_filename_lib_in_synpred113_ClangLL3517);
        e1=filename_lib();

        state._fsp--;
        if (state.failed) return ;
        match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_synpred113_ClangLL3519); if (state.failed) return ;
        match(input,HASH,FOLLOW_HASH_in_synpred113_ClangLL3521); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred113_ClangLL

    // $ANTLR start synpred115_ClangLL
    public final void synpred115_ClangLL_fragment() throws RecognitionException {   
        UnaryExpr e1 = null;

        AssignmentOperator e2 = null;

        AssignmentExpr e3 = null;


        // resources/Clang/ClangLL.g:1530:4: (e1= unary_expr e2= assignment_operator ( ( WS )* ) e3= assignment_expression )
        // resources/Clang/ClangLL.g:1530:4: e1= unary_expr e2= assignment_operator ( ( WS )* ) e3= assignment_expression
        {
        pushFollow(FOLLOW_unary_expr_in_synpred115_ClangLL3561);
        e1=unary_expr();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_assignment_operator_in_synpred115_ClangLL3570);
        e2=assignment_operator();

        state._fsp--;
        if (state.failed) return ;
        // resources/Clang/ClangLL.g:1531:70: ( ( WS )* )
        // resources/Clang/ClangLL.g:1531:71: ( WS )*
        {
        // resources/Clang/ClangLL.g:1531:71: ( WS )*
        loop129:
        do {
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==WS) ) {
                alt129=1;
            }


            switch (alt129) {
        	case 1 :
        	    // resources/Clang/ClangLL.g:0:0: WS
        	    {
        	    match(input,WS,FOLLOW_WS_in_synpred115_ClangLL3575); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop129;
            }
        } while (true);


        }

        pushFollow(FOLLOW_assignment_expression_in_synpred115_ClangLL3584);
        e3=assignment_expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred115_ClangLL

    // $ANTLR start synpred131_ClangLL
    public final void synpred131_ClangLL_fragment() throws RecognitionException {   
        EqualityExpr e2 = null;


        // resources/Clang/ClangLL.g:1574:5: ( AMPERSAND e2= equality_expr )
        // resources/Clang/ClangLL.g:1574:5: AMPERSAND e2= equality_expr
        {
        match(input,AMPERSAND,FOLLOW_AMPERSAND_in_synpred131_ClangLL3923); if (state.failed) return ;
        pushFollow(FOLLOW_equality_expr_in_synpred131_ClangLL3927);
        e2=equality_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred131_ClangLL

    // $ANTLR start synpred141_ClangLL
    public final void synpred141_ClangLL_fragment() throws RecognitionException {   
        Token sign=null;
        MultiplicativeExpr e2 = null;


        // resources/Clang/ClangLL.g:1593:4: ( (sign= PLUS | sign= MINUS ) e2= multiplicative_expr )
        // resources/Clang/ClangLL.g:1593:4: (sign= PLUS | sign= MINUS ) e2= multiplicative_expr
        {
        // resources/Clang/ClangLL.g:1593:4: (sign= PLUS | sign= MINUS )
        int alt133=2;
        int LA133_0 = input.LA(1);

        if ( (LA133_0==PLUS) ) {
            alt133=1;
        }
        else if ( (LA133_0==MINUS) ) {
            alt133=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 133, 0, input);

            throw nvae;
        }
        switch (alt133) {
            case 1 :
                // resources/Clang/ClangLL.g:1593:6: sign= PLUS
                {
                sign=(Token)match(input,PLUS,FOLLOW_PLUS_in_synpred141_ClangLL4114); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/ClangLL.g:1593:19: sign= MINUS
                {
                sign=(Token)match(input,MINUS,FOLLOW_MINUS_in_synpred141_ClangLL4121); if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_multiplicative_expr_in_synpred141_ClangLL4129);
        e2=multiplicative_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred141_ClangLL

    // $ANTLR start synpred144_ClangLL
    public final void synpred144_ClangLL_fragment() throws RecognitionException {   
        Token sign=null;
        CastExpr e2 = null;


        // resources/Clang/ClangLL.g:1598:4: ( (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr )
        // resources/Clang/ClangLL.g:1598:4: (sign= STAR | sign= DIVIDE | sign= MODULO ) e2= cast_expr
        {
        // resources/Clang/ClangLL.g:1598:4: (sign= STAR | sign= DIVIDE | sign= MODULO )
        int alt134=3;
        switch ( input.LA(1) ) {
        case STAR:
            {
            alt134=1;
            }
            break;
        case DIVIDE:
            {
            alt134=2;
            }
            break;
        case MODULO:
            {
            alt134=3;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 134, 0, input);

            throw nvae;
        }

        switch (alt134) {
            case 1 :
                // resources/Clang/ClangLL.g:1598:6: sign= STAR
                {
                sign=(Token)match(input,STAR,FOLLOW_STAR_in_synpred144_ClangLL4161); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/ClangLL.g:1598:18: sign= DIVIDE
                {
                sign=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_synpred144_ClangLL4167); if (state.failed) return ;

                }
                break;
            case 3 :
                // resources/Clang/ClangLL.g:1598:32: sign= MODULO
                {
                sign=(Token)match(input,MODULO,FOLLOW_MODULO_in_synpred144_ClangLL4173); if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_cast_expr_in_synpred144_ClangLL4181);
        e2=cast_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred144_ClangLL

    // $ANTLR start synpred145_ClangLL
    public final void synpred145_ClangLL_fragment() throws RecognitionException {   
        UnaryExpr e = null;


        // resources/Clang/ClangLL.g:1602:4: (e= unary_expr )
        // resources/Clang/ClangLL.g:1602:4: e= unary_expr
        {
        pushFollow(FOLLOW_unary_expr_in_synpred145_ClangLL4201);
        e=unary_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred145_ClangLL

    // $ANTLR start synpred155_ClangLL
    public final void synpred155_ClangLL_fragment() throws RecognitionException {   
        Token a9=null;
        UnaryExpr e4 = null;


        // resources/Clang/ClangLL.g:1617:4: (a9= SIZEOF e4= unary_expr )
        // resources/Clang/ClangLL.g:1617:4: a9= SIZEOF e4= unary_expr
        {
        a9=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_synpred155_ClangLL4363); if (state.failed) return ;
        pushFollow(FOLLOW_unary_expr_in_synpred155_ClangLL4370);
        e4=unary_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred155_ClangLL

    // $ANTLR start synpred162_ClangLL
    public final void synpred162_ClangLL_fragment() throws RecognitionException {   
        Token e=null;
        Token e4=null;
        RootExpr e2 = null;

        ArgumentExpressionList e3 = null;


        // resources/Clang/ClangLL.g:1623:4: ( ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT ) )
        // resources/Clang/ClangLL.g:1623:4: ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT )
        {
        // resources/Clang/ClangLL.g:1623:4: ( LBRACK e2= expression RBRACK | LPAREN (e3= argument_expr_list )? RPAREN | DOT e= ID | DEREFERENCE e4= ID | INCREMENT | DECREMENT )
        int alt138=6;
        switch ( input.LA(1) ) {
        case LBRACK:
            {
            alt138=1;
            }
            break;
        case LPAREN:
            {
            alt138=2;
            }
            break;
        case DOT:
            {
            alt138=3;
            }
            break;
        case DEREFERENCE:
            {
            alt138=4;
            }
            break;
        case INCREMENT:
            {
            alt138=5;
            }
            break;
        case DECREMENT:
            {
            alt138=6;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 138, 0, input);

            throw nvae;
        }

        switch (alt138) {
            case 1 :
                // resources/Clang/ClangLL.g:1623:6: LBRACK e2= expression RBRACK
                {
                match(input,LBRACK,FOLLOW_LBRACK_in_synpred162_ClangLL4420); if (state.failed) return ;
                pushFollow(FOLLOW_expression_in_synpred162_ClangLL4424);
                e2=expression();

                state._fsp--;
                if (state.failed) return ;
                match(input,RBRACK,FOLLOW_RBRACK_in_synpred162_ClangLL4428); if (state.failed) return ;

                }
                break;
            case 2 :
                // resources/Clang/ClangLL.g:1624:5: LPAREN (e3= argument_expr_list )? RPAREN
                {
                match(input,LPAREN,FOLLOW_LPAREN_in_synpred162_ClangLL4435); if (state.failed) return ;
                // resources/Clang/ClangLL.g:1624:12: (e3= argument_expr_list )?
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( ((LA137_0>=PLUS && LA137_0<=MINUS)||LA137_0==LPAREN||LA137_0==NOT||(LA137_0>=AMPERSAND && LA137_0<=SIZEOF)||LA137_0==TILDE||LA137_0==STAR||(LA137_0>=DECIMAL_LITERAL && LA137_0<=OCTAL_LITERAL)||LA137_0==HEX_LITERAL||LA137_0==FLOATING_LITERAL||LA137_0==CHAR_LITERAL||(LA137_0>=ID && LA137_0<=STRING_LITERAL)) ) {
                    alt137=1;
                }
                switch (alt137) {
                    case 1 :
                        // resources/Clang/ClangLL.g:1624:13: e3= argument_expr_list
                        {
                        pushFollow(FOLLOW_argument_expr_list_in_synpred162_ClangLL4440);
                        e3=argument_expr_list();

                        state._fsp--;
                        if (state.failed) return ;

                        }
                        break;

                }

                match(input,RPAREN,FOLLOW_RPAREN_in_synpred162_ClangLL4445); if (state.failed) return ;

                }
                break;
            case 3 :
                // resources/Clang/ClangLL.g:1625:5: DOT e= ID
                {
                match(input,DOT,FOLLOW_DOT_in_synpred162_ClangLL4454); if (state.failed) return ;
                e=(Token)match(input,ID,FOLLOW_ID_in_synpred162_ClangLL4458); if (state.failed) return ;

                }
                break;
            case 4 :
                // resources/Clang/ClangLL.g:1626:5: DEREFERENCE e4= ID
                {
                match(input,DEREFERENCE,FOLLOW_DEREFERENCE_in_synpred162_ClangLL4466); if (state.failed) return ;
                e4=(Token)match(input,ID,FOLLOW_ID_in_synpred162_ClangLL4470); if (state.failed) return ;

                }
                break;
            case 5 :
                // resources/Clang/ClangLL.g:1627:5: INCREMENT
                {
                match(input,INCREMENT,FOLLOW_INCREMENT_in_synpred162_ClangLL4478); if (state.failed) return ;

                }
                break;
            case 6 :
                // resources/Clang/ClangLL.g:1628:5: DECREMENT
                {
                match(input,DECREMENT,FOLLOW_DECREMENT_in_synpred162_ClangLL4486); if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred162_ClangLL

    // Delegated rules

    public final boolean synpred50_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred50_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred57_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred57_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred69_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred69_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred141_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred141_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred115_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred115_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred46_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred46_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred113_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred113_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred131_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred131_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred104_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred104_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred65_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred65_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred64_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred64_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred86_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred86_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred79_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred79_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred54_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred54_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred74_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred74_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred62_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred62_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred67_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred67_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred71_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred71_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred103_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred89_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred89_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred99_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred76_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred76_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred63_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred63_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred144_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred144_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred155_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred155_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred92_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred92_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred61_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred61_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred43_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred162_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred162_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred88_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred88_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred145_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred145_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred68_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred68_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred59_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred59_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred48_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred48_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred105_ClangLL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred105_ClangLL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA2 dfa2 = new DFA2(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA19 dfa19 = new DFA19(this);
    protected DFA25 dfa25 = new DFA25(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA62 dfa62 = new DFA62(this);
    protected DFA64 dfa64 = new DFA64(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA68 dfa68 = new DFA68(this);
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA82 dfa82 = new DFA82(this);
    protected DFA108 dfa108 = new DFA108(this);
    protected DFA109 dfa109 = new DFA109(this);
    protected DFA119 dfa119 = new DFA119(this);
    protected DFA127 dfa127 = new DFA127(this);
    static final String DFA1_eotS =
        "\32\uffff";
    static final String DFA1_eofS =
        "\1\1\31\uffff";
    static final String DFA1_minS =
        "\1\4\31\uffff";
    static final String DFA1_maxS =
        "\1\143\31\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\2\1\1\27\uffff";
    static final String DFA1_specialS =
        "\32\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\2\3\uffff\2\2\4\uffff\21\2\1\uffff\1\2\31\uffff\1\2\25\uffff"+
            "\1\2\22\uffff\1\2",
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
            return "()+ loopback of 1260:3: (e= extern_declaration )+";
        }
    }
    static final String DFA2_eotS =
        "\31\uffff";
    static final String DFA2_eofS =
        "\31\uffff";
    static final String DFA2_minS =
        "\1\4\24\0\4\uffff";
    static final String DFA2_maxS =
        "\1\143\24\0\4\uffff";
    static final String DFA2_acceptS =
        "\25\uffff\2\1\1\2\1\3";
    static final String DFA2_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
        "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\4\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\30\3\uffff\1\10\1\13\4\uffff\1\3\1\1\1\4\1\2\1\6\1\7\1\11"+
            "\1\12\1\14\1\15\1\27\1\16\1\17\1\23\1\24\1\20\1\26\1\uffff\1"+
            "\5\31\uffff\1\22\25\uffff\1\25\22\uffff\1\21",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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
            return "1262:1: extern_declaration returns [ExternDeclaration value] options {k=1; } : ( ( (e1= declaration_specifiers )? e2= declarator ( declaration )* '{' )=>e3= function_definition | (e4= declaration ) | (e5= include_directive ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_0 = input.LA(1);

                         
                        int index2_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA2_0==AUTO) ) {s = 1;}

                        else if ( (LA2_0==REGISTER) ) {s = 2;}

                        else if ( (LA2_0==EXTERN) ) {s = 3;}

                        else if ( (LA2_0==STATIC) ) {s = 4;}

                        else if ( (LA2_0==VOID) ) {s = 5;}

                        else if ( (LA2_0==CHAR) ) {s = 6;}

                        else if ( (LA2_0==SHORT) ) {s = 7;}

                        else if ( (LA2_0==INT) ) {s = 8;}

                        else if ( (LA2_0==LONG) ) {s = 9;}

                        else if ( (LA2_0==FLOAT) ) {s = 10;}

                        else if ( (LA2_0==DOUBLE) ) {s = 11;}

                        else if ( (LA2_0==SIGNED) ) {s = 12;}

                        else if ( (LA2_0==UNSIGNED) ) {s = 13;}

                        else if ( (LA2_0==UNION) ) {s = 14;}

                        else if ( (LA2_0==STRUCT) ) {s = 15;}

                        else if ( (LA2_0==ENUM) ) {s = 16;}

                        else if ( (LA2_0==ID) ) {s = 17;}

                        else if ( (LA2_0==TYPEOF) ) {s = 18;}

                        else if ( (LA2_0==CONST) ) {s = 19;}

                        else if ( (LA2_0==VOLATILE) ) {s = 20;}

                        else if ( (LA2_0==STAR) && (synpred4_ClangLL())) {s = 21;}

                        else if ( (LA2_0==LPAREN) && (synpred4_ClangLL())) {s = 22;}

                        else if ( (LA2_0==TYPEDEF) ) {s = 23;}

                        else if ( (LA2_0==HASH) ) {s = 24;}

                         
                        input.seek(index2_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA2_1 = input.LA(1);

                         
                        int index2_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA2_2 = input.LA(1);

                         
                        int index2_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA2_3 = input.LA(1);

                         
                        int index2_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA2_4 = input.LA(1);

                         
                        int index2_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA2_5 = input.LA(1);

                         
                        int index2_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA2_6 = input.LA(1);

                         
                        int index2_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA2_7 = input.LA(1);

                         
                        int index2_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_7);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA2_8 = input.LA(1);

                         
                        int index2_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_8);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA2_9 = input.LA(1);

                         
                        int index2_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA2_10 = input.LA(1);

                         
                        int index2_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_10);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA2_11 = input.LA(1);

                         
                        int index2_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_11);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA2_12 = input.LA(1);

                         
                        int index2_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA2_13 = input.LA(1);

                         
                        int index2_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_13);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA2_14 = input.LA(1);

                         
                        int index2_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_14);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA2_15 = input.LA(1);

                         
                        int index2_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_15);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA2_16 = input.LA(1);

                         
                        int index2_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_16);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA2_17 = input.LA(1);

                         
                        int index2_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred4_ClangLL()||(synpred4_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText()))))) ) {s = 22;}

                        else if ( ((synpred5_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 23;}

                         
                        input.seek(index2_17);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA2_18 = input.LA(1);

                         
                        int index2_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_18);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA2_19 = input.LA(1);

                         
                        int index2_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_19);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA2_20 = input.LA(1);

                         
                        int index2_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_ClangLL()) ) {s = 22;}

                        else if ( (synpred5_ClangLL()) ) {s = 23;}

                         
                        input.seek(index2_20);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA7_eotS =
        "\27\uffff";
    static final String DFA7_eofS =
        "\27\uffff";
    static final String DFA7_minS =
        "\1\10\20\uffff\1\0\5\uffff";
    static final String DFA7_maxS =
        "\1\143\20\uffff\1\0\5\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\23\uffff\1\2\1\uffff";
    static final String DFA7_specialS =
        "\21\uffff\1\0\5\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\25\1\uffff\1\1\31\uffff\1\1"+
            "\25\uffff\1\25\22\uffff\1\21",
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
            "\1\uffff",
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
            return "1278:4: (e1= declaration_specifiers )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_17 = input.LA(1);

                         
                        int index7_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred10_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index7_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA10_eotS =
        "\26\uffff";
    static final String DFA10_eofS =
        "\26\uffff";
    static final String DFA10_minS =
        "\1\10\25\uffff";
    static final String DFA10_maxS =
        "\1\143\25\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\23\uffff\1\2";
    static final String DFA10_specialS =
        "\26\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\1\4\uffff\12\1\1\25\5\1\2\uffff\1\1\31\uffff\1\1\50\uffff"+
            "\1\1",
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
            return "1282:1: declaration returns [Declaration value] : (e1= declaration_specifiers (e2= init_declarator_list )? SEMICOLON | TYPEDEF (e3= declaration_specifiers )? e4= init_declarator_list SEMICOLON );";
        }
    }
    static final String DFA9_eotS =
        "\27\uffff";
    static final String DFA9_eofS =
        "\27\uffff";
    static final String DFA9_minS =
        "\1\10\20\uffff\1\0\5\uffff";
    static final String DFA9_maxS =
        "\1\143\20\uffff\1\0\5\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\1\23\uffff\1\2\1\uffff";
    static final String DFA9_specialS =
        "\21\uffff\1\0\5\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\25\1\uffff\1\1\31\uffff\1\1"+
            "\25\uffff\1\25\22\uffff\1\21",
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
            "\1\uffff",
            "",
            "",
            "",
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
            return "1285:14: (e3= declaration_specifiers )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_17 = input.LA(1);

                         
                        int index9_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred13_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index9_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA11_eotS =
        "\35\uffff";
    static final String DFA11_eofS =
        "\1\1\34\uffff";
    static final String DFA11_minS =
        "\1\5\1\uffff\1\0\32\uffff";
    static final String DFA11_maxS =
        "\1\143\1\uffff\1\0\32\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\4\10\uffff\1\1\3\uffff\1\2\14\uffff\1\3\1\uffff";
    static final String DFA11_specialS =
        "\2\uffff\1\0\32\uffff}>";
    static final String[] DFA11_transitionS = {
            "\2\1\1\uffff\2\16\4\uffff\4\12\6\16\1\uffff\2\16\2\33\1\16"+
            "\2\1\1\16\1\1\1\uffff\1\1\26\uffff\1\16\25\uffff\1\1\22\uffff"+
            "\1\2",
            "",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1289:4: (e1= storage_class_specifier | e2= type_specifier | e3= type_qualifier )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_2 = input.LA(1);

                         
                        int index11_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred15_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 14;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index11_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA13_eotS =
        "\17\uffff";
    static final String DFA13_eofS =
        "\17\uffff";
    static final String DFA13_minS =
        "\1\10\16\uffff";
    static final String DFA13_maxS =
        "\1\143\16\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\13"+
        "\1\14\1\15";
    static final String DFA13_specialS =
        "\17\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\4\1\7\10\uffff\1\2\1\3\1\5\1\6\1\10\1\11\1\uffff\2\12\2"+
            "\uffff\1\14\2\uffff\1\1\31\uffff\1\16\50\uffff\1\15",
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
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1300:1: type_specifier returns [TypeSpecifier value] : (e1= VOID | e2= CHAR | e3= SHORT | e4= INT | e5= LONG | e6= FLOAT | e7= DOUBLE | e8= SIGNED | e9= UNSIGNED | e10= struct_or_union_spec | e11= enum_spec | e12= typedef_name | TYPEOF e13= typeof );";
        }
    }
    static final String DFA14_eotS =
        "\41\uffff";
    static final String DFA14_eofS =
        "\41\uffff";
    static final String DFA14_minS =
        "\1\10\14\uffff\1\0\23\uffff";
    static final String DFA14_maxS =
        "\1\144\14\uffff\1\0\23\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\1\17\uffff\1\2\17\uffff";
    static final String DFA14_specialS =
        "\15\uffff\1\0\23\uffff}>";
    static final String[] DFA14_transitionS = {
            "\2\1\2\21\6\uffff\6\1\1\uffff\5\1\1\21\1\uffff\1\1\21\uffff"+
            "\1\21\3\uffff\4\21\1\1\20\uffff\1\21\4\uffff\1\21\4\uffff\2"+
            "\21\1\uffff\1\21\2\uffff\1\21\1\uffff\1\21\5\uffff\1\15\1\21",
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
            "\1\uffff",
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
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "1316:15: (e2= type_name | e3= expression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_13 = input.LA(1);

                         
                        int index14_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred32_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index14_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA19_eotS =
        "\22\uffff";
    static final String DFA19_eofS =
        "\22\uffff";
    static final String DFA19_minS =
        "\1\10\21\uffff";
    static final String DFA19_maxS =
        "\1\143\21\uffff";
    static final String DFA19_acceptS =
        "\1\uffff\1\2\1\1\17\uffff";
    static final String DFA19_specialS =
        "\22\uffff}>";
    static final String[] DFA19_transitionS = {
            "\2\2\10\uffff\6\2\1\uffff\5\2\2\uffff\1\2\1\uffff\1\1\27\uffff"+
            "\1\2\50\uffff\1\2",
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
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1339:4: (e1= struct_declaration )+";
        }
    }
    static final String DFA25_eotS =
        "\32\uffff";
    static final String DFA25_eofS =
        "\1\1\31\uffff";
    static final String DFA25_minS =
        "\1\5\1\uffff\1\0\27\uffff";
    static final String DFA25_maxS =
        "\1\143\1\uffff\1\0\27\uffff";
    static final String DFA25_acceptS =
        "\1\uffff\1\3\11\uffff\1\1\14\uffff\1\2\1\uffff";
    static final String DFA25_specialS =
        "\2\uffff\1\0\27\uffff}>";
    static final String[] DFA25_transitionS = {
            "\1\1\2\uffff\2\13\2\uffff\1\1\5\uffff\6\13\1\uffff\2\13\2\30"+
            "\1\13\2\1\1\13\1\1\1\uffff\1\1\26\uffff\1\13\25\uffff\1\1\16"+
            "\uffff\1\1\3\uffff\1\2",
            "",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1354:4: (e1= type_specifier | e2= type_qualifier )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA25_2 = input.LA(1);

                         
                        int index25_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred43_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 11;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index25_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 25, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA38_eotS =
        "\26\uffff";
    static final String DFA38_eofS =
        "\26\uffff";
    static final String DFA38_minS =
        "\1\10\25\uffff";
    static final String DFA38_maxS =
        "\1\143\25\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\1\23\uffff\1\2";
    static final String DFA38_specialS =
        "\26\uffff}>";
    static final String[] DFA38_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\uffff\1\25\1\1\31\uffff\1\1"+
            "\50\uffff\1\1",
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
            ""
    };

    static final short[] DFA38_eot = DFA.unpackEncodedString(DFA38_eotS);
    static final short[] DFA38_eof = DFA.unpackEncodedString(DFA38_eofS);
    static final char[] DFA38_min = DFA.unpackEncodedStringToUnsignedChars(DFA38_minS);
    static final char[] DFA38_max = DFA.unpackEncodedStringToUnsignedChars(DFA38_maxS);
    static final short[] DFA38_accept = DFA.unpackEncodedString(DFA38_acceptS);
    static final short[] DFA38_special = DFA.unpackEncodedString(DFA38_specialS);
    static final short[][] DFA38_transition;

    static {
        int numStates = DFA38_transitionS.length;
        DFA38_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
        }
    }

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = DFA38_eot;
            this.eof = DFA38_eof;
            this.min = DFA38_min;
            this.max = DFA38_max;
            this.accept = DFA38_accept;
            this.special = DFA38_special;
            this.transition = DFA38_transition;
        }
        public String getDescription() {
            return "1400:16: (e3= parameter_type_list )?";
        }
    }
    static final String DFA58_eotS =
        "\26\uffff";
    static final String DFA58_eofS =
        "\26\uffff";
    static final String DFA58_minS =
        "\1\10\25\uffff";
    static final String DFA58_maxS =
        "\1\143\25\uffff";
    static final String DFA58_acceptS =
        "\1\uffff\1\1\23\uffff\1\2";
    static final String DFA58_specialS =
        "\26\uffff}>";
    static final String[] DFA58_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\uffff\1\25\1\1\31\uffff\1\1"+
            "\50\uffff\1\1",
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
            ""
    };

    static final short[] DFA58_eot = DFA.unpackEncodedString(DFA58_eotS);
    static final short[] DFA58_eof = DFA.unpackEncodedString(DFA58_eofS);
    static final char[] DFA58_min = DFA.unpackEncodedStringToUnsignedChars(DFA58_minS);
    static final char[] DFA58_max = DFA.unpackEncodedStringToUnsignedChars(DFA58_maxS);
    static final short[] DFA58_accept = DFA.unpackEncodedString(DFA58_acceptS);
    static final short[] DFA58_special = DFA.unpackEncodedString(DFA58_specialS);
    static final short[][] DFA58_transition;

    static {
        int numStates = DFA58_transitionS.length;
        DFA58_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA58_transition[i] = DFA.unpackEncodedString(DFA58_transitionS[i]);
        }
    }

    class DFA58 extends DFA {

        public DFA58(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 58;
            this.eot = DFA58_eot;
            this.eof = DFA58_eof;
            this.min = DFA58_min;
            this.max = DFA58_max;
            this.accept = DFA58_accept;
            this.special = DFA58_special;
            this.transition = DFA58_transition;
        }
        public String getDescription() {
            return "1462:18: (e3= parameter_type_list )?";
        }
    }
    static final String DFA62_eotS =
        "\26\uffff";
    static final String DFA62_eofS =
        "\26\uffff";
    static final String DFA62_minS =
        "\1\10\25\uffff";
    static final String DFA62_maxS =
        "\1\143\25\uffff";
    static final String DFA62_acceptS =
        "\1\uffff\1\1\23\uffff\1\2";
    static final String DFA62_specialS =
        "\26\uffff}>";
    static final String[] DFA62_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\uffff\1\25\1\1\31\uffff\1\1"+
            "\50\uffff\1\1",
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
            ""
    };

    static final short[] DFA62_eot = DFA.unpackEncodedString(DFA62_eotS);
    static final short[] DFA62_eof = DFA.unpackEncodedString(DFA62_eofS);
    static final char[] DFA62_min = DFA.unpackEncodedStringToUnsignedChars(DFA62_minS);
    static final char[] DFA62_max = DFA.unpackEncodedStringToUnsignedChars(DFA62_maxS);
    static final short[] DFA62_accept = DFA.unpackEncodedString(DFA62_acceptS);
    static final short[] DFA62_special = DFA.unpackEncodedString(DFA62_specialS);
    static final short[][] DFA62_transition;

    static {
        int numStates = DFA62_transitionS.length;
        DFA62_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA62_transition[i] = DFA.unpackEncodedString(DFA62_transitionS[i]);
        }
    }

    class DFA62 extends DFA {

        public DFA62(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 62;
            this.eot = DFA62_eot;
            this.eof = DFA62_eof;
            this.min = DFA62_min;
            this.max = DFA62_max;
            this.accept = DFA62_accept;
            this.special = DFA62_special;
            this.transition = DFA62_transition;
        }
        public String getDescription() {
            return "1466:11: (e3= parameter_type_list )?";
        }
    }
    static final String DFA64_eotS =
        "\13\uffff";
    static final String DFA64_eofS =
        "\13\uffff";
    static final String DFA64_minS =
        "\1\4\1\0\11\uffff";
    static final String DFA64_maxS =
        "\1\144\1\0\11\uffff";
    static final String DFA64_acceptS =
        "\2\uffff\1\1\1\4\1\2\1\3\1\1\1\4\1\5\1\6\1\7";
    static final String DFA64_specialS =
        "\1\uffff\1\0\11\uffff}>";
    static final String[] DFA64_transitionS = {
            "\1\12\1\7\4\uffff\2\7\22\uffff\1\7\2\uffff\1\10\3\uffff\1\4"+
            "\1\uffff\2\6\1\4\3\11\4\5\1\uffff\1\7\3\uffff\4\7\21\uffff\1"+
            "\7\4\uffff\1\7\4\uffff\2\7\1\uffff\1\7\2\uffff\1\7\1\uffff\1"+
            "\7\5\uffff\1\1\1\7",
            "\1\uffff",
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

    static final short[] DFA64_eot = DFA.unpackEncodedString(DFA64_eotS);
    static final short[] DFA64_eof = DFA.unpackEncodedString(DFA64_eofS);
    static final char[] DFA64_min = DFA.unpackEncodedStringToUnsignedChars(DFA64_minS);
    static final char[] DFA64_max = DFA.unpackEncodedStringToUnsignedChars(DFA64_maxS);
    static final short[] DFA64_accept = DFA.unpackEncodedString(DFA64_acceptS);
    static final short[] DFA64_special = DFA.unpackEncodedString(DFA64_specialS);
    static final short[][] DFA64_transition;

    static {
        int numStates = DFA64_transitionS.length;
        DFA64_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA64_transition[i] = DFA.unpackEncodedString(DFA64_transitionS[i]);
        }
    }

    class DFA64 extends DFA {

        public DFA64(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 64;
            this.eot = DFA64_eot;
            this.eof = DFA64_eof;
            this.min = DFA64_min;
            this.max = DFA64_max;
            this.accept = DFA64_accept;
            this.special = DFA64_special;
            this.transition = DFA64_transition;
        }
        public String getDescription() {
            return "1468:1: statement returns [Statement value] : (e1= labeled_statement | e2= selection_statement | e3= jump_statement | e4= expr_statement | e5= compound_statement | e6= iteration_statement | e7= include_statement );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA64_1 = input.LA(1);

                         
                        int index64_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred89_ClangLL()) ) {s = 2;}

                        else if ( (synpred92_ClangLL()) ) {s = 3;}

                         
                        input.seek(index64_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 64, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA67_eotS =
        "\65\uffff";
    static final String DFA67_eofS =
        "\65\uffff";
    static final String DFA67_minS =
        "\1\4\64\uffff";
    static final String DFA67_maxS =
        "\1\144\64\uffff";
    static final String DFA67_acceptS =
        "\1\uffff\1\2\1\1\62\uffff";
    static final String DFA67_specialS =
        "\65\uffff}>";
    static final String[] DFA67_transitionS = {
            "\2\2\2\uffff\4\2\2\uffff\21\2\1\uffff\2\2\1\1\2\uffff\1\2\1"+
            "\uffff\12\2\1\uffff\1\2\3\uffff\5\2\20\uffff\1\2\4\uffff\1\2"+
            "\4\uffff\2\2\1\uffff\1\2\2\uffff\1\2\1\uffff\1\2\5\uffff\2\2",
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
            ""
    };

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "()* loopback of 1490:4: (e1= block_item )*";
        }
    }
    static final String DFA68_eotS =
        "\64\uffff";
    static final String DFA68_eofS =
        "\64\uffff";
    static final String DFA68_minS =
        "\1\4\20\uffff\1\0\42\uffff";
    static final String DFA68_maxS =
        "\1\144\20\uffff\1\0\42\uffff";
    static final String DFA68_acceptS =
        "\1\uffff\1\1\24\uffff\1\2\35\uffff";
    static final String DFA68_specialS =
        "\21\uffff\1\0\42\uffff}>";
    static final String[] DFA68_transitionS = {
            "\2\26\2\uffff\2\1\2\26\2\uffff\20\1\1\26\1\uffff\1\1\1\26\3"+
            "\uffff\1\26\1\uffff\12\26\1\uffff\1\26\3\uffff\4\26\1\1\20\uffff"+
            "\1\26\4\uffff\1\26\4\uffff\2\26\1\uffff\1\26\2\uffff\1\26\1"+
            "\uffff\1\26\5\uffff\1\21\1\26",
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
            "\1\uffff",
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
            "",
            "",
            "",
            ""
    };

    static final short[] DFA68_eot = DFA.unpackEncodedString(DFA68_eotS);
    static final short[] DFA68_eof = DFA.unpackEncodedString(DFA68_eofS);
    static final char[] DFA68_min = DFA.unpackEncodedStringToUnsignedChars(DFA68_minS);
    static final char[] DFA68_max = DFA.unpackEncodedStringToUnsignedChars(DFA68_maxS);
    static final short[] DFA68_accept = DFA.unpackEncodedString(DFA68_acceptS);
    static final short[] DFA68_special = DFA.unpackEncodedString(DFA68_specialS);
    static final short[][] DFA68_transition;

    static {
        int numStates = DFA68_transitionS.length;
        DFA68_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA68_transition[i] = DFA.unpackEncodedString(DFA68_transitionS[i]);
        }
    }

    class DFA68 extends DFA {

        public DFA68(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 68;
            this.eot = DFA68_eot;
            this.eof = DFA68_eof;
            this.min = DFA68_min;
            this.max = DFA68_max;
            this.accept = DFA68_accept;
            this.special = DFA68_special;
            this.transition = DFA68_transition;
        }
        public String getDescription() {
            return "1492:1: block_item returns [BlockItem value] : ( (e1= declaration ) | (e2= statement ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA68_17 = input.LA(1);

                         
                        int index68_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred99_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index68_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 68, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA71_eotS =
        "\50\uffff";
    static final String DFA71_eofS =
        "\50\uffff";
    static final String DFA71_minS =
        "\1\5\21\0\26\uffff";
    static final String DFA71_maxS =
        "\1\144\21\0\26\uffff";
    static final String DFA71_acceptS =
        "\22\uffff\1\2\23\uffff\1\3\1\1";
    static final String DFA71_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\1\20\26\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\46\2\uffff\2\22\1\15\1\16\2\uffff\20\22\1\10\1\uffff\1\22"+
            "\21\uffff\1\20\3\uffff\1\13\1\11\1\12\1\21\1\22\20\uffff\1\17"+
            "\4\uffff\1\14\4\uffff\1\2\1\3\1\uffff\1\4\2\uffff\1\5\1\uffff"+
            "\1\6\5\uffff\1\1\1\7",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "1508:3: ( (e5= expression ) | (e9= declaration ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA71_1 = input.LA(1);

                         
                        int index71_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( ((synpred104_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 18;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA71_2 = input.LA(1);

                         
                        int index71_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA71_3 = input.LA(1);

                         
                        int index71_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA71_4 = input.LA(1);

                         
                        int index71_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA71_5 = input.LA(1);

                         
                        int index71_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA71_6 = input.LA(1);

                         
                        int index71_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA71_7 = input.LA(1);

                         
                        int index71_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA71_8 = input.LA(1);

                         
                        int index71_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA71_9 = input.LA(1);

                         
                        int index71_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA71_10 = input.LA(1);

                         
                        int index71_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA71_11 = input.LA(1);

                         
                        int index71_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA71_12 = input.LA(1);

                         
                        int index71_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA71_13 = input.LA(1);

                         
                        int index71_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA71_14 = input.LA(1);

                         
                        int index71_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA71_15 = input.LA(1);

                         
                        int index71_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA71_16 = input.LA(1);

                         
                        int index71_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA71_17 = input.LA(1);

                         
                        int index71_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_ClangLL()) ) {s = 39;}

                        else if ( (true) ) {s = 38;}

                         
                        input.seek(index71_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 71, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA82_eotS =
        "\14\uffff";
    static final String DFA82_eofS =
        "\14\uffff";
    static final String DFA82_minS =
        "\1\7\13\uffff";
    static final String DFA82_maxS =
        "\1\104\13\uffff";
    static final String DFA82_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13";
    static final String DFA82_specialS =
        "\14\uffff}>";
    static final String[] DFA82_transitionS = {
            "\1\1\63\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13",
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

    static final short[] DFA82_eot = DFA.unpackEncodedString(DFA82_eotS);
    static final short[] DFA82_eof = DFA.unpackEncodedString(DFA82_eofS);
    static final char[] DFA82_min = DFA.unpackEncodedStringToUnsignedChars(DFA82_minS);
    static final char[] DFA82_max = DFA.unpackEncodedStringToUnsignedChars(DFA82_maxS);
    static final short[] DFA82_accept = DFA.unpackEncodedString(DFA82_acceptS);
    static final short[] DFA82_special = DFA.unpackEncodedString(DFA82_specialS);
    static final short[][] DFA82_transition;

    static {
        int numStates = DFA82_transitionS.length;
        DFA82_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA82_transition[i] = DFA.unpackEncodedString(DFA82_transitionS[i]);
        }
    }

    class DFA82 extends DFA {

        public DFA82(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 82;
            this.eot = DFA82_eot;
            this.eof = DFA82_eof;
            this.min = DFA82_min;
            this.max = DFA82_max;
            this.accept = DFA82_accept;
            this.special = DFA82_special;
            this.transition = DFA82_transition;
        }
        public String getDescription() {
            return "1535:1: assignment_operator returns [AssignmentOperator value] : (a1= ASSIGN | a2= MULTIPLY_ASSIGN | a3= DIVIDE_ASSIGN | a4= ADD_ASSIGN | a5= MINUS_ASSIGN | a6= MODULO_ASSIGN | a7= BITWISE_AND_ASSIGN | a8= BITWISE_XOR_ASSIGN | a9= BITWISE_OR_ASSIGN | a10= LEFT_SHIFT_ASSIGN | a11= RIGHT_SHIFT_ASSIGN );";
        }
    }
    static final String DFA108_eotS =
        "\27\uffff";
    static final String DFA108_eofS =
        "\27\uffff";
    static final String DFA108_minS =
        "\1\10\20\uffff\1\0\5\uffff";
    static final String DFA108_maxS =
        "\1\143\20\uffff\1\0\5\uffff";
    static final String DFA108_acceptS =
        "\1\uffff\1\1\23\uffff\1\2\1\uffff";
    static final String DFA108_specialS =
        "\21\uffff\1\0\5\uffff}>";
    static final String[] DFA108_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\25\1\uffff\1\1\31\uffff\1\1"+
            "\25\uffff\1\25\22\uffff\1\21",
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
            "\1\uffff",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA108_eot = DFA.unpackEncodedString(DFA108_eotS);
    static final short[] DFA108_eof = DFA.unpackEncodedString(DFA108_eofS);
    static final char[] DFA108_min = DFA.unpackEncodedStringToUnsignedChars(DFA108_minS);
    static final char[] DFA108_max = DFA.unpackEncodedStringToUnsignedChars(DFA108_maxS);
    static final short[] DFA108_accept = DFA.unpackEncodedString(DFA108_acceptS);
    static final short[] DFA108_special = DFA.unpackEncodedString(DFA108_specialS);
    static final short[][] DFA108_transition;

    static {
        int numStates = DFA108_transitionS.length;
        DFA108_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA108_transition[i] = DFA.unpackEncodedString(DFA108_transitionS[i]);
        }
    }

    class DFA108 extends DFA {

        public DFA108(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 108;
            this.eot = DFA108_eot;
            this.eof = DFA108_eof;
            this.min = DFA108_min;
            this.max = DFA108_max;
            this.accept = DFA108_accept;
            this.special = DFA108_special;
            this.transition = DFA108_transition;
        }
        public String getDescription() {
            return "1263:7: (e1= declaration_specifiers )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA108_17 = input.LA(1);

                         
                        int index108_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred2_ClangLL()&&(shouldGetTypedefName(input.LT(1).getText())))) ) {s = 1;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index108_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 108, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA109_eotS =
        "\27\uffff";
    static final String DFA109_eofS =
        "\27\uffff";
    static final String DFA109_minS =
        "\1\10\26\uffff";
    static final String DFA109_maxS =
        "\1\143\26\uffff";
    static final String DFA109_acceptS =
        "\1\uffff\1\2\1\1\24\uffff";
    static final String DFA109_specialS =
        "\27\uffff}>";
    static final String[] DFA109_transitionS = {
            "\2\2\4\uffff\20\2\2\uffff\1\2\1\1\30\uffff\1\2\50\uffff\1\2",
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
            return "()* loopback of 1263:46: ( declaration )*";
        }
    }
    static final String DFA119_eotS =
        "\26\uffff";
    static final String DFA119_eofS =
        "\26\uffff";
    static final String DFA119_minS =
        "\1\10\25\uffff";
    static final String DFA119_maxS =
        "\1\143\25\uffff";
    static final String DFA119_acceptS =
        "\1\uffff\1\1\23\uffff\1\2";
    static final String DFA119_specialS =
        "\26\uffff}>";
    static final String[] DFA119_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\uffff\1\25\1\1\31\uffff\1\1"+
            "\50\uffff\1\1",
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
            ""
    };

    static final short[] DFA119_eot = DFA.unpackEncodedString(DFA119_eotS);
    static final short[] DFA119_eof = DFA.unpackEncodedString(DFA119_eofS);
    static final char[] DFA119_min = DFA.unpackEncodedStringToUnsignedChars(DFA119_minS);
    static final char[] DFA119_max = DFA.unpackEncodedStringToUnsignedChars(DFA119_maxS);
    static final short[] DFA119_accept = DFA.unpackEncodedString(DFA119_acceptS);
    static final short[] DFA119_special = DFA.unpackEncodedString(DFA119_specialS);
    static final short[][] DFA119_transition;

    static {
        int numStates = DFA119_transitionS.length;
        DFA119_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA119_transition[i] = DFA.unpackEncodedString(DFA119_transitionS[i]);
        }
    }

    class DFA119 extends DFA {

        public DFA119(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 119;
            this.eot = DFA119_eot;
            this.eof = DFA119_eof;
            this.min = DFA119_min;
            this.max = DFA119_max;
            this.accept = DFA119_accept;
            this.special = DFA119_special;
            this.transition = DFA119_transition;
        }
        public String getDescription() {
            return "1400:16: (e3= parameter_type_list )?";
        }
    }
    static final String DFA127_eotS =
        "\26\uffff";
    static final String DFA127_eofS =
        "\26\uffff";
    static final String DFA127_minS =
        "\1\10\25\uffff";
    static final String DFA127_maxS =
        "\1\143\25\uffff";
    static final String DFA127_acceptS =
        "\1\uffff\1\1\23\uffff\1\2";
    static final String DFA127_specialS =
        "\26\uffff}>";
    static final String[] DFA127_transitionS = {
            "\2\1\4\uffff\12\1\1\uffff\5\1\1\uffff\1\25\1\1\31\uffff\1\1"+
            "\50\uffff\1\1",
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
            ""
    };

    static final short[] DFA127_eot = DFA.unpackEncodedString(DFA127_eotS);
    static final short[] DFA127_eof = DFA.unpackEncodedString(DFA127_eofS);
    static final char[] DFA127_min = DFA.unpackEncodedStringToUnsignedChars(DFA127_minS);
    static final char[] DFA127_max = DFA.unpackEncodedStringToUnsignedChars(DFA127_maxS);
    static final short[] DFA127_accept = DFA.unpackEncodedString(DFA127_acceptS);
    static final short[] DFA127_special = DFA.unpackEncodedString(DFA127_specialS);
    static final short[][] DFA127_transition;

    static {
        int numStates = DFA127_transitionS.length;
        DFA127_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA127_transition[i] = DFA.unpackEncodedString(DFA127_transitionS[i]);
        }
    }

    class DFA127 extends DFA {

        public DFA127(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 127;
            this.eot = DFA127_eot;
            this.eof = DFA127_eof;
            this.min = DFA127_min;
            this.max = DFA127_max;
            this.accept = DFA127_accept;
            this.special = DFA127_special;
            this.transition = DFA127_transition;
        }
        public String getDescription() {
            return "1466:11: (e3= parameter_type_list )?";
        }
    }
 

    public static final BitSet FOLLOW_extern_declaration_in_translation_unit1236 = new BitSet(new long[]{0x040000017FFFC312L,0x0000000800010000L});
    public static final BitSet FOLLOW_function_definition_in_extern_declaration1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_extern_declaration1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_directive_in_extern_declaration1305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_include_directive1324 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_filename_lib_in_include_directive1328 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_include_directive1330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_include_directive1344 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_directive1348 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_include_directive1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_filename_lib1372 = new BitSet(new long[]{0x0000000000002002L,0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_filename_lib1380 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_filename_lib1384 = new BitSet(new long[]{0x0000000000002002L,0x0000000000020000L});
    public static final BitSet FOLLOW_DIVIDE_in_filename_lib1394 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_filename_lib1398 = new BitSet(new long[]{0x0000000000002002L,0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_filename_lib1406 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_filename_lib1410 = new BitSet(new long[]{0x0000000000002002L,0x0000000000020000L});
    public static final BitSet FOLLOW_declaration_specifiers_in_function_definition1435 = new BitSet(new long[]{0x040000017EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_declarator_in_function_definition1447 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_compound_statement_in_function_definition1456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_specifiers_in_declaration1474 = new BitSet(new long[]{0x040000017EFFC320L,0x0000000800010000L});
    public static final BitSet FOLLOW_init_declarator_list_in_declaration1483 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_declaration1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPEDEF_in_declaration1496 = new BitSet(new long[]{0x040000017EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_declaration_specifiers_in_declaration1501 = new BitSet(new long[]{0x040000017EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_init_declarator_list_in_declaration1513 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_declaration1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_storage_class_specifier_in_declaration_specifiers1534 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_specifier_in_declaration_specifiers1543 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_qualifier_in_declaration_specifiers1554 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_AUTO_in_storage_class_specifier1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGISTER_in_storage_class_specifier1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTERN_in_storage_class_specifier1593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_storage_class_specifier1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_type_specifier1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_type_specifier1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_type_specifier1640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type_specifier1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_type_specifier1658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_type_specifier1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_type_specifier1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIGNED_in_type_specifier1685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNSIGNED_in_type_specifier1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_struct_or_union_spec_in_type_specifier1703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_spec_in_type_specifier1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedef_name_in_type_specifier1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPEOF_in_type_specifier1730 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_typeof_in_type_specifier1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_typeof1752 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_type_name_in_typeof1758 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_expression_in_typeof1766 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_typeof1772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typedef_name1792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_type_qualifier1810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOLATILE_in_type_qualifier1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_struct_or_union_in_struct_or_union_spec1837 = new BitSet(new long[]{0x0000000200000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_struct_or_union_spec1846 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCURLY_in_struct_or_union_spec1857 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_struct_decl_list_in_struct_or_union_spec1861 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_struct_or_union_spec1865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_struct_or_union_in_struct_or_union_spec1873 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_struct_or_union_spec1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNION_in_struct_or_union1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_struct_or_union1912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_struct_declaration_in_struct_decl_list1931 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_init_declarator_in_init_declarator_list1951 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_init_declarator_list1959 = new BitSet(new long[]{0x040000017EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_init_declarator_in_init_declarator_list1964 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_declarator_in_init_declarator1984 = new BitSet(new long[]{0x0000000000000082L,0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_init_declarator1995 = new BitSet(new long[]{0x0000000000000080L,0x0000000080000000L});
    public static final BitSet FOLLOW_ASSIGN_in_init_declarator1999 = new BitSet(new long[]{0x07C400037EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_WS_in_init_declarator2002 = new BitSet(new long[]{0x07C400037EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_initializer_in_init_declarator2008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_specifier_qualifier_list_in_struct_declaration2030 = new BitSet(new long[]{0x040000017EFFD320L,0x0000000800010000L});
    public static final BitSet FOLLOW_struct_declarator_list_in_struct_declaration2040 = new BitSet(new long[]{0x040000017EFFD320L,0x0000000800010000L});
    public static final BitSet FOLLOW_SEMICOLON_in_struct_declaration2046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specifier_in_specifier_qualifier_list2063 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_qualifier_in_specifier_qualifier_list2073 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_struct_declarator_in_struct_declarator_list2094 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_struct_declarator_list2101 = new BitSet(new long[]{0x040000017EFFD300L,0x0000000800010000L});
    public static final BitSet FOLLOW_struct_declarator_in_struct_declarator_list2105 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_declarator_in_struct_declarator2125 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COLON_in_struct_declarator2136 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_struct_declarator2140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_struct_declarator2151 = new BitSet(new long[]{0x07C400017EFFCF02L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_struct_declarator2156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enum_spec2179 = new BitSet(new long[]{0x0000000200000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_enum_spec2182 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCURLY_in_enum_spec2190 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_enumarator_list_in_enum_spec2194 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_enum_spec2198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enum_spec2206 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_enum_spec2208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumerator_in_enumarator_list2226 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_enumarator_list2235 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_enumerator_in_enumarator_list2239 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_ID_in_enumerator2260 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_ASSIGN_in_enumerator2269 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_enumerator2273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_declarator2295 = new BitSet(new long[]{0x0000000040000000L,0x0000000800010000L});
    public static final BitSet FOLLOW_direct_declarator_in_declarator2308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_declarator2317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_direct_declarator2336 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LPAREN_in_direct_declarator2345 = new BitSet(new long[]{0x040000017EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_declarator_in_direct_declarator2349 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_direct_declarator2353 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LBRACK_in_direct_declarator2364 = new BitSet(new long[]{0x07C400117EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_direct_declarator2369 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_direct_declarator2373 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LPAREN_in_direct_declarator2386 = new BitSet(new long[]{0x04000001BEFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_parameter_type_list_in_direct_declarator2391 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_direct_declarator2395 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LPAREN_in_direct_declarator2408 = new BitSet(new long[]{0x0000000080000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_identifier_list_in_direct_declarator2413 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_direct_declarator2417 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_STAR_in_pointer2442 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_qualifier_list_in_pointer2446 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_pointer_in_pointer2453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_pointer2464 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_pointer_in_pointer2468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_pointer2477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_qualifier_in_type_qualifier_list2496 = new BitSet(new long[]{0x040000013EFFC302L,0x0000000800000000L});
    public static final BitSet FOLLOW_parameter_list_in_parameter_type_list2518 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_parameter_type_list2527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_ELLIPSES_in_parameter_type_list2529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_param_declaration_in_parameter_list2555 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_parameter_list2564 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_param_declaration_in_parameter_list2568 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_declaration_specifiers_in_param_declaration2590 = new BitSet(new long[]{0x0400000B7EFFC302L,0x0000000800010000L});
    public static final BitSet FOLLOW_declarator_in_param_declaration2600 = new BitSet(new long[]{0x0400000B7EFFC302L,0x0000000800010000L});
    public static final BitSet FOLLOW_abstract_declarator_in_param_declaration2615 = new BitSet(new long[]{0x0400000B7EFFC302L,0x0000000800010000L});
    public static final BitSet FOLLOW_ID_in_identifier_list2636 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_identifier_list2644 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_identifier_list2648 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_assignment_expression_in_initializer2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_initializer2679 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_compound_statement_in_initializer2684 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_RPAREN_in_initializer2688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_initializer2694 = new BitSet(new long[]{0x07C400037EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_initializer_list_in_initializer2698 = new BitSet(new long[]{0x0000000400000040L});
    public static final BitSet FOLLOW_COMMA_in_initializer2701 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_initializer2704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_initializer_in_initializer_list2722 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_initializer_list2730 = new BitSet(new long[]{0x07C400037EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_initializer_in_initializer_list2734 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_specifier_qualifier_list_in_type_name2756 = new BitSet(new long[]{0x0400000B7EFFC302L,0x0000000880010000L});
    public static final BitSet FOLLOW_WS_in_type_name2761 = new BitSet(new long[]{0x0400000B7EFFC302L,0x0000000880010000L});
    public static final BitSet FOLLOW_abstract_declarator_in_type_name2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_abstract_declarator2792 = new BitSet(new long[]{0x0400000B7EFFC302L,0x0000000800010000L});
    public static final BitSet FOLLOW_direct_abstract_declarator_in_abstract_declarator2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_direct_abstract_declarator_in_abstract_declarator2810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_direct_abstract_declarator2832 = new BitSet(new long[]{0x0400000B7EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_abstract_declarator_in_direct_abstract_declarator2836 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_direct_abstract_declarator2840 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LBRACK_in_direct_abstract_declarator2850 = new BitSet(new long[]{0x07C400117EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_direct_abstract_declarator2855 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_direct_abstract_declarator2861 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LPAREN_in_direct_abstract_declarator2872 = new BitSet(new long[]{0x04000001BEFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_parameter_type_list_in_direct_abstract_declarator2877 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_direct_abstract_declarator2883 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LBRACK_in_direct_abstract_declarator2900 = new BitSet(new long[]{0x07C400117EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_direct_abstract_declarator2905 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_direct_abstract_declarator2911 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_LPAREN_in_direct_abstract_declarator2919 = new BitSet(new long[]{0x04000001BEFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_parameter_type_list_in_direct_abstract_declarator2924 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_direct_abstract_declarator2930 = new BitSet(new long[]{0x0000000840000002L});
    public static final BitSet FOLLOW_labeled_statement_in_statement2949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_statement_in_statement2958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jump_statement_in_statement2967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_statement_in_statement2976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_statement_in_statement2985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iteration_statement_in_statement2994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_statement_in_statement3003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_labeled_statement3021 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_labeled_statement3023 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_labeled_statement3027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_labeled_statement3036 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_labeled_statement3040 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_labeled_statement3042 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_labeled_statement3046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_labeled_statement3055 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_labeled_statement3057 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_labeled_statement3061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_expr_statement3080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expr_statement3089 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_expr_statement3092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_compound_statement3111 = new BitSet(new long[]{0x07C5FFA77FFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_block_item_list_in_compound_statement3117 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_compound_statement3121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_item_in_block_item_list3138 = new BitSet(new long[]{0x07C5FFA37FFFCF32L,0x0000001829610800L});
    public static final BitSet FOLLOW_declaration_in_block_item3159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block_item3169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_selection_statement3189 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_LPAREN_in_selection_statement3191 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_selection_statement3195 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_selection_statement3199 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_selection_statement3207 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_ELSE_in_selection_statement3227 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_selection_statement3232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_selection_statement3244 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_LPAREN_in_selection_statement3246 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_selection_statement3250 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_selection_statement3258 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_selection_statement3262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_iteration_statement3280 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_LPAREN_in_iteration_statement3282 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_iteration_statement3286 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_iteration_statement3289 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_iteration_statement3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_iteration_statement3306 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_iteration_statement3310 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_WHILE_in_iteration_statement3319 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_LPAREN_in_iteration_statement3321 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_iteration_statement3325 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_iteration_statement3328 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_iteration_statement3330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_iteration_statement3337 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_LPAREN_in_iteration_statement3339 = new BitSet(new long[]{0x07C400017FFFCF20L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_iteration_statement3350 = new BitSet(new long[]{0x07C400017EFFCF20L,0x0000001829610800L});
    public static final BitSet FOLLOW_declaration_in_iteration_statement3358 = new BitSet(new long[]{0x07C400017EFFCF20L,0x0000001829610800L});
    public static final BitSet FOLLOW_SEMICOLON_in_iteration_statement3365 = new BitSet(new long[]{0x07C400017EFFCF20L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_iteration_statement3374 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_iteration_statement3379 = new BitSet(new long[]{0x07C40001FEFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_iteration_statement3384 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_iteration_statement3392 = new BitSet(new long[]{0x07C5FFA37EFFCF30L,0x0000001829610800L});
    public static final BitSet FOLLOW_statement_in_iteration_statement3396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GOTO_in_jump_statement3414 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_jump_statement3418 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_jump_statement3420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_jump_statement3429 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_jump_statement3433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_jump_statement3440 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_jump_statement3444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_jump_statement3451 = new BitSet(new long[]{0x07C400017EFFCF20L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_jump_statement3458 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_SEMICOLON_in_jump_statement3464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_expression_in_expression3480 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_expression3488 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_assignment_expression_in_expression3492 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_HASH_in_include_statement3511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_LESSER_THAN_in_include_statement3513 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_filename_lib_in_include_statement3517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_GREATER_THAN_in_include_statement3519 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_include_statement3521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_include_statement3535 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_statement3539 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_include_statement3541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_expr_in_assignment_expression3561 = new BitSet(new long[]{0xF800000000000080L,0x000000000000001FL});
    public static final BitSet FOLLOW_assignment_operator_in_assignment_expression3570 = new BitSet(new long[]{0x07C400017EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_WS_in_assignment_expression3575 = new BitSet(new long[]{0x07C400017EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_assignment_expression_in_assignment_expression3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_expression_in_assignment_expression3594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assignment_operator3612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULTIPLY_ASSIGN_in_assignment_operator3621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_ASSIGN_in_assignment_operator3631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_ASSIGN_in_assignment_operator3640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_ASSIGN_in_assignment_operator3649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODULO_ASSIGN_in_assignment_operator3658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITWISE_AND_ASSIGN_in_assignment_operator3667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITWISE_XOR_ASSIGN_in_assignment_operator3676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITWISE_OR_ASSIGN_in_assignment_operator3685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SHIFT_ASSIGN_in_assignment_operator3694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHT_SHIFT_ASSIGN_in_assignment_operator3703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logical_or_expr_in_conditional_expression3720 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_QUESTION_in_conditional_expression3727 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_conditional_expression3732 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_conditional_expression3740 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_conditional_expression_in_conditional_expression3745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_expression_in_constant_expression3765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logical_and_expr_in_logical_or_expr3783 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_OR_in_logical_or_expr3791 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_logical_and_expr_in_logical_or_expr3795 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_inclusive_or_expr_in_logical_and_expr3815 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_AND_in_logical_and_expr3824 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_inclusive_or_expr_in_logical_and_expr3828 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_exclusive_or_expr_in_inclusive_or_expr3848 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_PIPE_in_inclusive_or_expr3857 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_exclusive_or_expr_in_inclusive_or_expr3861 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_and_expr_in_exclusive_or_expr3881 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_CARET_in_exclusive_or_expr3889 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_and_expr_in_exclusive_or_expr3893 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_equality_expr_in_and_expr3913 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_AMPERSAND_in_and_expr3923 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_equality_expr_in_and_expr3927 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_relational_expr_in_equality_expr3947 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_equality_expr3959 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_NOT_EQUALS_in_equality_expr3965 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_relational_expr_in_equality_expr3975 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_shift_expr_in_relational_expr3996 = new BitSet(new long[]{0x0000000000000002L,0x00000000000001E0L});
    public static final BitSet FOLLOW_LESSER_THAN_in_relational_expr4009 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_GREATER_THAN_in_relational_expr4015 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_LESSER_THAN_OR_EQUAL_TO_in_relational_expr4021 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_GREATER_THAN_OR_EQUAL_TO_in_relational_expr4027 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_shift_expr_in_relational_expr4035 = new BitSet(new long[]{0x0000000000000002L,0x00000000000001E0L});
    public static final BitSet FOLLOW_additive_expr_in_shift_expr4055 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000600L});
    public static final BitSet FOLLOW_LEFT_SHIFT_in_shift_expr4067 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_RIGHT_SHIFT_in_shift_expr4073 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_additive_expr_in_shift_expr4082 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000600L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr4102 = new BitSet(new long[]{0x0000000000000C02L});
    public static final BitSet FOLLOW_PLUS_in_additive_expr4114 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_MINUS_in_additive_expr4121 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr4129 = new BitSet(new long[]{0x0000000000000C02L});
    public static final BitSet FOLLOW_cast_expr_in_multiplicative_expr4149 = new BitSet(new long[]{0x0000000000000002L,0x0000000000070000L});
    public static final BitSet FOLLOW_STAR_in_multiplicative_expr4161 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_DIVIDE_in_multiplicative_expr4167 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_MODULO_in_multiplicative_expr4173 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_cast_expr_in_multiplicative_expr4181 = new BitSet(new long[]{0x0000000000000002L,0x0000000000070000L});
    public static final BitSet FOLLOW_unary_expr_in_cast_expr4201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_cast_expr4210 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_name_in_cast_expr4214 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_cast_expr4221 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_cast_expr_in_cast_expr4225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfix_expr_in_unary_expr4244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCREMENT_in_unary_expr4253 = new BitSet(new long[]{0x03C4000040000C00L,0x0000001829610800L});
    public static final BitSet FOLLOW_unary_expr_in_unary_expr4259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECREMENT_in_unary_expr4268 = new BitSet(new long[]{0x03C4000040000C00L,0x0000001829610800L});
    public static final BitSet FOLLOW_unary_expr_in_unary_expr4274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPERSAND_in_unary_expr4283 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_STAR_in_unary_expr4295 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_PLUS_in_unary_expr4307 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_MINUS_in_unary_expr4319 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_TILDE_in_unary_expr4331 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_NOT_in_unary_expr4343 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_cast_expr_in_unary_expr4354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZEOF_in_unary_expr4363 = new BitSet(new long[]{0x03C4000040000C00L,0x0000001829610800L});
    public static final BitSet FOLLOW_unary_expr_in_unary_expr4370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZEOF_in_unary_expr4379 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_LPAREN_in_unary_expr4384 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_name_in_unary_expr4391 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_unary_expr4395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_expr_in_postfix_expr4411 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_LBRACK_in_postfix_expr4420 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_postfix_expr4424 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_postfix_expr4428 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_LPAREN_in_postfix_expr4435 = new BitSet(new long[]{0x07C40001FEFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_argument_expr_list_in_postfix_expr4440 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_postfix_expr4445 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_DOT_in_postfix_expr4454 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_postfix_expr4458 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_DEREFERENCE_in_postfix_expr4466 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_postfix_expr4470 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_INCREMENT_in_postfix_expr4478 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_DECREMENT_in_postfix_expr4486 = new BitSet(new long[]{0x01A0000840002002L});
    public static final BitSet FOLLOW_ID_in_primary_expr4511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_primary_expr4520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_primary_expr4529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary_expr4538 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_primary_expr4542 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_primary_expr4546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_expression_in_argument_expr_list4563 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_argument_expr_list4572 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_assignment_expression_in_argument_expr_list4576 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_constant4596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OCTAL_LITERAL_in_constant4606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LITERAL_in_constant4616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATING_LITERAL_in_constant4626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_constant4636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_specifiers_in_synpred2_ClangLL1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_specifiers_in_synpred4_ClangLL1266 = new BitSet(new long[]{0x040000017EFFC300L,0x0000000800010000L});
    public static final BitSet FOLLOW_declarator_in_synpred4_ClangLL1271 = new BitSet(new long[]{0x040000033FFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_declaration_in_synpred4_ClangLL1273 = new BitSet(new long[]{0x040000033FFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_LCURLY_in_synpred4_ClangLL1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_synpred5_ClangLL1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_synpred6_ClangLL1324 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_filename_lib_in_synpred6_ClangLL1328 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_synpred6_ClangLL1330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_specifiers_in_synpred10_ClangLL1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_specifiers_in_synpred13_ClangLL1501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specifier_in_synpred15_ClangLL1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_name_in_synpred32_ClangLL1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_struct_or_union_in_synpred35_ClangLL1837 = new BitSet(new long[]{0x0000000200000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_synpred35_ClangLL1846 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCURLY_in_synpred35_ClangLL1857 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_struct_decl_list_in_synpred35_ClangLL1861 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_synpred35_ClangLL1865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specifier_in_synpred43_ClangLL2063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_synpred46_ClangLL2136 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_synpred46_ClangLL2140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_expression_in_synpred48_ClangLL2156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_synpred50_ClangLL2179 = new BitSet(new long[]{0x0000000200000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_synpred50_ClangLL2182 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCURLY_in_synpred50_ClangLL2190 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_enumarator_list_in_synpred50_ClangLL2194 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_synpred50_ClangLL2198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_synpred54_ClangLL2295 = new BitSet(new long[]{0x0000000040000000L,0x0000000800010000L});
    public static final BitSet FOLLOW_direct_declarator_in_synpred54_ClangLL2308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_synpred57_ClangLL2364 = new BitSet(new long[]{0x07C400117EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_synpred57_ClangLL2369 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_synpred57_ClangLL2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred59_ClangLL2386 = new BitSet(new long[]{0x04000001BEFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_parameter_type_list_in_synpred59_ClangLL2391 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred59_ClangLL2395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred61_ClangLL2408 = new BitSet(new long[]{0x0000000080000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_identifier_list_in_synpred61_ClangLL2413 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred61_ClangLL2417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_synpred62_ClangLL2453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_synpred63_ClangLL2442 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_type_qualifier_list_in_synpred63_ClangLL2446 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_pointer_in_synpred63_ClangLL2453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_synpred64_ClangLL2464 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_pointer_in_synpred64_ClangLL2468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_qualifier_in_synpred65_ClangLL2496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred67_ClangLL2564 = new BitSet(new long[]{0x040000013EFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_param_declaration_in_synpred67_ClangLL2568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarator_in_synpred68_ClangLL2600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstract_declarator_in_synpred69_ClangLL2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_expression_in_synpred71_ClangLL2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred74_ClangLL2679 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_compound_statement_in_synpred74_ClangLL2684 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_RPAREN_in_synpred74_ClangLL2688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred76_ClangLL2730 = new BitSet(new long[]{0x07C400037EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_initializer_in_synpred76_ClangLL2734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_direct_abstract_declarator_in_synpred79_ClangLL2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_synpred86_ClangLL2900 = new BitSet(new long[]{0x07C400117EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_constant_expression_in_synpred86_ClangLL2905 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_synpred86_ClangLL2911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred88_ClangLL2919 = new BitSet(new long[]{0x04000001BEFFC300L,0x0000000800000000L});
    public static final BitSet FOLLOW_parameter_type_list_in_synpred88_ClangLL2924 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred88_ClangLL2930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_labeled_statement_in_synpred89_ClangLL2949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_statement_in_synpred92_ClangLL2976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_synpred99_ClangLL3159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred103_ClangLL3350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_synpred104_ClangLL3358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_synpred105_ClangLL3365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_synpred113_ClangLL3511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_LESSER_THAN_in_synpred113_ClangLL3513 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_filename_lib_in_synpred113_ClangLL3517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_GREATER_THAN_in_synpred113_ClangLL3519 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_HASH_in_synpred113_ClangLL3521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_expr_in_synpred115_ClangLL3561 = new BitSet(new long[]{0xF800000000000080L,0x000000000000001FL});
    public static final BitSet FOLLOW_assignment_operator_in_synpred115_ClangLL3570 = new BitSet(new long[]{0x07C400017EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_WS_in_synpred115_ClangLL3575 = new BitSet(new long[]{0x07C400017EFFCF00L,0x00000018A9610800L});
    public static final BitSet FOLLOW_assignment_expression_in_synpred115_ClangLL3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPERSAND_in_synpred131_ClangLL3923 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_equality_expr_in_synpred131_ClangLL3927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_synpred141_ClangLL4114 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_MINUS_in_synpred141_ClangLL4121 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_multiplicative_expr_in_synpred141_ClangLL4129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_synpred144_ClangLL4161 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_DIVIDE_in_synpred144_ClangLL4167 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_MODULO_in_synpred144_ClangLL4173 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_cast_expr_in_synpred144_ClangLL4181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_expr_in_synpred145_ClangLL4201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZEOF_in_synpred155_ClangLL4363 = new BitSet(new long[]{0x03C4000040000C00L,0x0000001829610800L});
    public static final BitSet FOLLOW_unary_expr_in_synpred155_ClangLL4370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_synpred162_ClangLL4420 = new BitSet(new long[]{0x07C400017EFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_expression_in_synpred162_ClangLL4424 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACK_in_synpred162_ClangLL4428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred162_ClangLL4435 = new BitSet(new long[]{0x07C40001FEFFCF00L,0x0000001829610800L});
    public static final BitSet FOLLOW_argument_expr_list_in_synpred162_ClangLL4440 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred162_ClangLL4445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred162_ClangLL4454 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_synpred162_ClangLL4458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEREFERENCE_in_synpred162_ClangLL4466 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ID_in_synpred162_ClangLL4470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCREMENT_in_synpred162_ClangLL4478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECREMENT_in_synpred162_ClangLL4486 = new BitSet(new long[]{0x0000000000000002L});

}