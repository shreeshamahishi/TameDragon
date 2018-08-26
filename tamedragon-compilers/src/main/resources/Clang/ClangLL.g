grammar ClangLL;

options {
    backtrack=true;
    memoize=true;
    k=1;
}

@header{
package com.compilervision.compilers.clang.abssyntree;
import java.util.HashSet;
import java.util.Stack;
}

@lexer::header{ package com.compilervision.compilers.clang.abssyntree;} 

@members{
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
       				else if("\%".equals(symbol))
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
}

HASH: '#';
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
TYPEOF : ('typeof' | '_typeof_' | '_typeof');
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
QUESTION :	'?';
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

translation_unit returns [TranslationUnit value] : 
		(e=extern_declaration {$value = addExternDecToTransUnit($value, $e.value);})+
	;
extern_declaration returns [ExternDeclaration value]  options {k=1;} 
	: (e1=declaration_specifiers? e2=declarator declaration* '{' )=> e3=function_definition {$value = $e3.value; setLineNumAndPos($value, $e3.value);}
	| (e4=declaration  {$value = $e4.value; setLineNumAndPos($value, $e4.value);})
	| (e5=include_directive {$value = $e5.value; setLineNumAndPos($value, $e5.value);})
	;
include_directive returns [IncludeDirective value] :
	e0=HASH e1=filename_lib HASH 
		{$value = new IncludeDirective($e0.line, $e1.value, IncludeDirective.LIB);} 
	| 
		HASH e2=STRING_LITERAL HASH
		{$value = new IncludeDirective($e2.line, $e2.text, IncludeDirective.LOCAL);}
	;
filename_lib returns [FileNameLib value] :
	  e=ID {$value = new FileNameLib($e.line, $e.text);} ((d1=DOT e1=ID) {if($d1.text != null && $e1.text != null) $value.addToFileName("." +$e1.text);})* (d=DIVIDE e3=ID {if($e3.text != null) $value.addToFileName("/" +$e3.text);} ((d2=DOT e2=ID){if($d2.text != null && $e2.text != null) $value.addToFileName("." +$e2.text);})*)*
	;
function_definition returns [FunctionDef value]:	
			(e1=declaration_specifiers {$value = new FunctionDef(); $value.setDeclSpecifiers($e1.value); setLineNumAndPos($value, $e1.value);})? 
			e2=declarator {if($value == null) $value = new FunctionDef(); $value.setDeclarator($e2.value);setLineNumAndPos($value, $e2.value);}
			e3=compound_statement {$value.setCompoundStmt($e3.value);}
	;
declaration returns [Declaration value]
	:	e1=declaration_specifiers {$value = new Declaration(); $value.setDeclSpecifiers($e1.value); setLineNumAndPos($value, $e1.value); }
		(e2=init_declarator_list {$value.setInitDeclaratorList($e2.value); })? SEMICOLON
	|   TYPEDEF (e3=declaration_specifiers { $value = new Declaration(); $value.setTypeDefDeclaration(true); $value.setDeclSpecifiers($e3.value); setLineNumAndPos($value, $e3.value);})? 
			e4=init_declarator_list {$value.setInitDeclaratorList($e4.value); } SEMICOLON
	;
declaration_specifiers returns [DeclarationSpecifiers value]
	: (e1=storage_class_specifier {$value = addUnitToDeclSpecs($e1.value, $value); }
	| e2=type_specifier  {$value = addUnitToDeclSpecs($e2.value, $value);}
	| e3= type_qualifier {$value = addUnitToDeclSpecs($e3.value, $value);})+ 
	;
storage_class_specifier returns [StorageClassSpecifiers value]
	:	e1=AUTO {$value = new StorageClassSpecifiers($e1.line, $e1.pos, StorageClassSpecifiers.AUTO);}
	|	e2=REGISTER {$value = new StorageClassSpecifiers($e2.line, $e2.pos, StorageClassSpecifiers.REGISTER); }
	|	e3=EXTERN {$value = new StorageClassSpecifiers($e3.line, $e3.pos, StorageClassSpecifiers.EXTERN);}
	|	e4=STATIC {$value = new StorageClassSpecifiers($e4.line, $e4.pos, StorageClassSpecifiers.STATIC);}
	// |	e5=TYPEDEF {$value = new StorageClassSpecifiers($e5.line, $e5.pos, StorageClassSpecifiers.TYPEDEF);}
	;
type_specifier returns [TypeSpecifier value]
	:	e1=VOID {$value = new TypeSpecifier($e1.line, $e1.pos, TypeSpecifier.VOID); }
	|	e2=CHAR {$value = new TypeSpecifier($e2.line, $e2.pos, TypeSpecifier.CHAR); }
	|	e3=SHORT {$value = new TypeSpecifier($e3.line, $e3.pos, TypeSpecifier.SHORT); }
	|	e4=INT {$value = new TypeSpecifier($e4.line, $e4.pos, TypeSpecifier.INT); }
	|	e5=LONG {$value = new TypeSpecifier($e5.line, $e5.pos, TypeSpecifier.LONG); }
	|	e6=FLOAT {$value = new TypeSpecifier($e6.line, $e6.pos, TypeSpecifier.FLOAT); }
	|	e7=DOUBLE {$value = new TypeSpecifier($e7.line, $e7.pos, TypeSpecifier.DOUBLE); }
	|	e8=SIGNED {$value = new TypeSpecifier($e8.line, $e8.pos, TypeSpecifier.SIGNED); }
	|	e9=UNSIGNED {$value = new TypeSpecifier($e9.line, $e9.pos, TypeSpecifier.UNSIGNED); }
	|	e10=struct_or_union_spec {$value = $e10.value; setLineNumAndPos($value, $e10.value); }
	|	e11=enum_spec {$value = $e11.value; setLineNumAndPos($value, $e11.value);}
	|   e12=typedef_name {$value = $e12.value; setLineNumAndPos($value, $e12.value);}
	|	TYPEOF e13=typeof {$value = $e13.value; setLineNumAndPos($value, $e13.value);}
	;
typeof returns [TypeOf value]
	:	e1=LPAREN  (e2=type_name {$value = new TypeOf($e1.line, $e1.pos);$value.setTypeName($e2.value);} | e3=expression {$value = new TypeOf($e1.line, $e1.pos);$value.setExpression($e3.value);})  RPAREN
	;
typedef_name returns [TypeDefName value]
	:  {shouldGetTypedefName(input.LT(1).getText())}?  e=ID {$value = new TypeDefName($e.line, $e.pos, $e.text);}
	;
type_qualifier	returns [TypeQualifier value]
	:	e1=CONST {$value = new TypeQualifier($e1.line, $e1.pos, TypeQualifier.CONST);}
	|	e2=VOLATILE {$value = new TypeQualifier($e2.line, $e2.pos,TypeQualifier.VOLATILE);}
	;
struct_or_union_spec returns [StructOrUnionSpecifier value]
	:	e1=struct_or_union {$value = new StructOrUnionSpecifier(); 
			$value.setStructOrUnion($e1.value); $value.setType(TypeSpecifier.STRUCT_OR_UNION_SPEC); setLineNumAndPos($value, $e1.value); }
		(e2=ID {$value.setName($e2.text); } )? 
		 LCURLY e3=struct_decl_list {$value.setStructDecList($e3.value); } RCURLY
	|	e4= struct_or_union {$value = new StructOrUnionSpecifier();  
			$value.setStructOrUnion($e4.value); $value.setType(TypeSpecifier.STRUCT_OR_UNION_SPEC); setLineNumAndPos($value, $e4.value);}
		 (e5=ID {$value.setName($e5.text);} )
	;
struct_or_union returns [StructOrUnion value]
	:	e1=UNION {$value = new StructOrUnion(StructOrUnion.UNION); setLineNumAndPos($value, $e1.line, $e1.pos);}
	|	e2=STRUCT {$value = new StructOrUnion(StructOrUnion.STRUCT); setLineNumAndPos($value, $e2.line, $e2.pos);}
	;
struct_decl_list returns [StructDeclarationList value ]
	:	(e1=struct_declaration {$value = (StructDeclarationList)addNodeToCollection($e1.value , $value);})+
	;
init_declarator_list returns [InitDeclaratorList value]
	:	e1=init_declarator {$value = (InitDeclaratorList)addNodeToCollection( $e1.value, $value);}
			(COMMA 	e2=init_declarator {$value = (InitDeclaratorList)addNodeToCollection($e2.value, $value);})*
	;
init_declarator returns [InitDeclarator value]
	:	e1=declarator {$value = new InitDeclarator($e1.value, null); setLineNumAndPos($value, $e1.value);} 
			( (WS*) ASSIGN (WS*) e2=initializer {$value.setInitializer($e2.value);} )?	
	;
struct_declaration returns [StructDeclaration value]
	:	e1=specifier_qualifier_list  {$value = new StructDeclaration(); $value.setSpecifierQualifierList($e1.value);}
		(e2=struct_declarator_list {$value.setStructDeclaratorList($e2.value); setLineNumAndPos($value, $e2.value); })*	SEMICOLON
	;
specifier_qualifier_list returns [SpecifierQualifierList value]
	:	(e1=type_specifier {$value = addTypeSpecOrTypeQualToSpecQualList($e1.value, null,  $value); setLineNumAndPos($value, $e1.value);}
	| 	e2=type_qualifier {$value = addTypeSpecOrTypeQualToSpecQualList(null, $e2.value, $value); setLineNumAndPos($value, $e2.value);}) +
	;
struct_declarator_list returns [StructDeclaratorList value]
	:	e1=struct_declarator {$value= (StructDeclaratorList)addNodeToCollection($e1.value, $value); }
		(COMMA e2=struct_declarator {$value= (StructDeclaratorList) addNodeToCollection($e2.value, $value);})*
	;
struct_declarator returns [StructDeclarator value]
	:	e1=declarator {$value = new StructDeclarator($e1.value, null); setLineNumAndPos($value, $e1.value);} 
	    (COLON e2=constant_expression { $value.setBitFieldValue($e2.value);} )?
		|	COLON (e3=constant_expression {$value = new StructDeclarator(null, $e3.value);})?  
	;
enum_spec returns [EnumSpecifier value]
	:	 e1=ENUM (ID )? 
		LCURLY e2=enumarator_list {$value = new EnumSpecifier($ID.text, $e2.value); setLineNumAndPos($value, $e1.line, $e1.pos);} RCURLY
	| 	e3=ENUM ID {$value = new EnumSpecifier($ID.text, null); setLineNumAndPos($value, $e3.line, $e3.pos);}
	;
enumarator_list returns [EnumList value]
	:	e1=enumerator {$value = (EnumList)addNodeToCollection($e1.value, $value);}
			 (COMMA e2=enumerator {$value = (EnumList)addNodeToCollection($e2.value, $value);})*
	;
enumerator returns [Enumerator value] 
	:	e1=ID {$value = new Enumerator($e1.text, null); setLineNumAndPos($value, $e1.line, $e1.pos);} 
			(ASSIGN e2=constant_expression {$value.setInitValue($e2.value);})?	
	;
declarator returns [Declarator value]
	:	(e1=pointer {$value = new Declarator(null, $e1.value); setLineNumAndPos($value, $e1.value);} ) ? 
		e2=direct_declarator {if($value == null) $value = new Declarator();
				$value.setDirectDeclarator($e2.value); setLineNumAndPos($value, $e2.value);}
	| e3=pointer {$value = new Declarator(null, $e3.value); setLineNumAndPos($value, $e3.value);}
	;
direct_declarator returns [DirectDeclarator value]
	:	(e=ID {$value = new DirectDeclarator(); $value.setId($e.text); 
				$value.setType(DirectDeclarator.ID); setLineNumAndPos($value, $e.line, $e.pos);
			  }
			| LPAREN e1=declarator {$value = new DirectDeclarator(); $value.setDeclarator($e1.value);
			 $value.setType(DirectDeclarator.DECLR); setLineNumAndPos($value, $e1.value);} RPAREN )
		 ( v1=LBRACK (e2=expression)? RBRACK { 
		 	
		 	 if($e2.value != null){
		 	 	addToDirectDeclarator($value, DirectDeclarator.ARRAY, $e2.value, null, null, $v1.line, $v1.pos);
		 	 }
		 	 else
		 	 	addToDirectDeclarator($value, DirectDeclarator.EMPTY_ARRAY, null, null, null, $v1.line, $v1.pos);
		 	 e2 = null;
		 	 }  
		 | v2=LPAREN (e3=parameter_type_list)? RPAREN  {  
		 	
		 	if($e3.value != null)
		 	 	addToDirectDeclarator($value, DirectDeclarator.FUNC, null, $e3.value, null, $v2.line, $v2.pos);
		 	 else
		 	 	addToDirectDeclarator($value, DirectDeclarator.NO_ARG_FUNC, null, null, null, $v2.line, $v2.pos);
		 	} 
		 | v3=LPAREN (e4=identifier_list)? RPAREN  { 		 	 	
		 	if($e4.value != null)
		 	 	addToDirectDeclarator($value, DirectDeclarator.ID_LIST_FUNC, null, null, $e4.value, $v3.line, $v3.pos);
		 	 else
		 	 	addToDirectDeclarator($value, DirectDeclarator.NO_ARG_FUNC, null, null, null, $v3.line, $v3.pos);
		 	 }
		 )*
	;
pointer returns [Pointer value]
	: s1=STAR e1=type_qualifier_list {$value = new Pointer(); $value.setTypeQualifierList($e1.value); setLineNumAndPos($value, $s1.line, $s1.pos); } (e2=pointer {$value.setPointer($e2.value);})?
	| s2=STAR e2=pointer {$value = new Pointer($e2.value);  setLineNumAndPos($value, $s2.line, $s2.pos);}
	| s3=STAR {$value = new Pointer();  setLineNumAndPos($value, $s3.line, $s3.pos);}
	;
type_qualifier_list returns [TypeQualifierList value]
	:	(e1=type_qualifier {$value = (TypeQualifierList)addNodeToCollection($e1.value, $value); })+
	;
parameter_type_list returns [ParamTypeList value]
	:	{parsingParamDecl = true;} e1=parameter_list {$value = new ParamTypeList($e1.value); setLineNumAndPos($value, $e1.value);} 
			(COMMA ELLIPSES {$value.setHasEllipses(true);})? {parsingParamDecl = false;}
	;
parameter_list returns [ParamList value]
	: {parsingParamDecl = true;}	e1 = param_declaration {$value =  (ParamList)addNodeToCollection($e1.value, $value); } 
		( COMMA e2=param_declaration {$value = (ParamList) addNodeToCollection($e2.value, $value);})* {parsingParamDecl = false;}
	;
param_declaration returns [ParamDeclaration value]
	:	e=declaration_specifiers {$value = new ParamDeclaration(); 
			$value.setDeclarationSpecifiers($e.value); setLineNumAndPos($value, $e.value);} 
		(e1=declarator {$value.setDeclarator($e1.value);} 
	     | e2=abstract_declarator {$value.setAbstractDeclarator($e2.value);})*	
	;
identifier_list returns [IdentifierList value]
	: e1=ID {$value = (IdentifierList)addIdToIdentifierList($e1.text, $value, $e1.line, $e1.pos);} 
	 (COMMA e2=ID {$value = (IdentifierList)addIdToIdentifierList($e2.text, $value, $e2.line, $e2.pos);})*
	;	
initializer returns [Initializer value]
	:	e1= assignment_expression {$value = new Initializer(); $value.setInitExpr($e1.value); setLineNumAndPos($value, $e1.value);}
	|   LPAREN? e3=compound_statement {$value = new Initializer(); $value.setCompoundStatement($e3.value); setLineNumAndPos($value, $e3.value);} RPAREN?
	|	LCURLY e2=initializer_list {$value = new Initializer(); $value.setInitializerList($e2.value); setLineNumAndPos($value, $e2.value);}COMMA? RCURLY
	;
initializer_list returns [InitializerList value]
	:	e1 = initializer {$value =  (InitializerList)addNodeToCollection($e1.value,$value); } 
		(COMMA e2=initializer {$value = (InitializerList)addNodeToCollection($e2.value, $value);})*	
	;
type_name returns [TypeName value]
	:	e1= specifier_qualifier_list {$value = new TypeName(); 
			$value.setSpecifierQualifierList($e1.value); setLineNumAndPos($value, $e1.value);} (WS*)
		(e2=abstract_declarator {$value.setAbstractDeclarator($e2.value);})?
	;
abstract_declarator returns [AbstractDeclarator value]	
	: 	e1=pointer {$value = new AbstractDeclarator($e1.value, null);}(e2=direct_abstract_declarator  {$value.setDirectAbstractDeclarator($e2.value); setLineNumAndPos($value, $e2.value);})?
	|	e3=direct_abstract_declarator  {$value = new AbstractDeclarator(null, $e3.value); setLineNumAndPos($value, $e3.value);}	
	;
direct_abstract_declarator returns [DirectAbstractDeclarator value]
	:	((a1=LCURLY e1=abstract_declarator {$value = new DirectAbstractDeclarator(); $value.setAbstractDeclarator($e1.value); setLineNumAndPos($value, $a1.line, $a1.pos);} RCURLY)
		| (a2=LBRACK (e2=constant_expression { $value = new DirectAbstractDeclarator(); addToAbstractDirectDeclarator($e2.value, null, $value); setLineNumAndPos($value, $a2.line, $a2.pos);})? RBRACK
	    | a3=LPAREN (e3=parameter_type_list { $value = new DirectAbstractDeclarator(); addToAbstractDirectDeclarator(null, $e3.value, $value); setLineNumAndPos($value, $a3.line, $a3.pos);})? RPAREN)
		 )
		 
		 ( LBRACK (e2=constant_expression { addToAbstractDirectDeclarator($e2.value, null, $value);})? RBRACK | 
		 LPAREN (e3=parameter_type_list { addToAbstractDirectDeclarator(null, $e3.value, $value);})? RPAREN )*
	;
statement returns [Statement value]
	:	e1=labeled_statement {$value = $e1.value; setLineNumAndPos($value, $e1.value);}
	|	e2=selection_statement {$value = $e2.value; setLineNumAndPos($value, $e2.value);}
	|	e3=jump_statement {$value = $e3.value; setLineNumAndPos($value, $e3.value);}
	|	e4=expr_statement {$value = $e4.value; setLineNumAndPos($value, $e4.value);}
	|	e5=compound_statement {$value = $e5.value; setLineNumAndPos($value, $e5.value);}
	|	e6=iteration_statement {$value = $e6.value; setLineNumAndPos($value, $e6.value);}
	|	e7=include_statement {$value = $e7.value; setLineNumAndPos($value, $e7.value);}
	;
labeled_statement returns [LabeledStatement value]
	:	e=ID COLON e1=statement {$value = new LabeledStatement($e.text, null, $e1.value); setLineNumAndPos($value, $e.line, $e.pos);}
	|	a1=CASE e2=constant_expression COLON e3=statement {$value = new LabeledStatement(null, $e2.value, $e3.value); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	a2=DEFAULT COLON e4=statement {$value = new LabeledStatement(null, null, $e4.value); setLineNumAndPos($value, $a2.line, $a2.pos);}
	;
expr_statement  returns [ExprStatement value]
	:	a1=SEMICOLON {$value = new ExprStatement(null); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	e1=expression  SEMICOLON {$value = new ExprStatement($e1.value); setLineNumAndPos($value, $e1.value);} 
	;
compound_statement returns [CompoundStatement value]
	:	a1=LCURLY {$value = new CompoundStatement(); setLineNumAndPos($value, $a1.line, $a1.pos);} e1=block_item_list {$value.setBlockItemList($e1.value);} RCURLY
	;
block_item_list returns [BlockItemList value]
	: (e1=block_item {$value = (BlockItemList)addNodeToCollection($e1.value, $value);})*
	;
block_item returns [BlockItem value]
	: (e1=declaration {$value = new BlockItem(); setLineNumAndPos($value, $e1.value); $value.setDeclaration($e1.value);}) | (e2=statement){$value = new BlockItem(); setLineNumAndPos($value, $e2.value); $value.setStatement($e2.value);}
	;
selection_statement returns [SelectionStatement value] 	
	:	a1=IF LPAREN e1=expression {$value = new SelectionStatement(); $value.setIfExpr($e1.value);} RPAREN
			 e2=statement {$value.setIfStmt($e2.value); setLineNumAndPos($value, $a1.line, $a1.pos);}
			(options {k=1; backtrack=false;}:ELSE  e3=statement {$value.setElseStmt($e3.value);} )?
	|	a2=SWITCH LPAREN e1=expression {$value = new SelectionStatement(); $value.setSwitchExpr($e1.value);}
			 RPAREN e2=statement {$value.setSwitchStmt($e2.value); setLineNumAndPos($value, $a2.line, $a2.pos); }
	;
iteration_statement returns [IterationStatement value]
	:	a1=WHILE LPAREN e1=expression{$value = new IterationStatement(); $value.setWhileExpr($e1.value);} RPAREN 
			e2=statement {$value.setWhileStmt($e2.value); $value.setIterationType(IterationStatement.WHILE); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	a2=DO e3=statement {$value = new IterationStatement(); $value.setWhileStmt($e3.value); $value.setIterationType(IterationStatement.DO_WHILE);}
		 a4=WHILE LPAREN e4=expression{$value.setWhileExpr($e4.value); setLineNumAndPos($value, $a4.line, $a4.pos);} RPAREN SEMICOLON
	|	a3=FOR LPAREN {$value = new IterationStatement(); $value.setIterationType(IterationStatement.FOR); } 
		((e5=expression {$value.setForInitExpr($e5.value);})|(e9=declaration {$value.setForDeclr($e9.value);}))? SEMICOLON? 
		(e6=expression{$value.setForCondExpr($e6.value);})? SEMICOLON (e7=expression{$value.setForIncrExpr($e7.value);})? 
		RPAREN e8=statement {$value.setForStmt($e8.value); setLineNumAndPos($value, $a3.line, $a3.pos);}
	;
jump_statement returns [JumpStatement value]
	: a1=GOTO e=ID SEMICOLON {$value = new JumpStatement(JumpStatement.GOTO, $e.text, null); setLineNumAndPos($value, $a1.line, $a1.pos);}
	| a2=CONTINUE {$value = new JumpStatement(JumpStatement.CONTINUE, null, null); setLineNumAndPos($value, $a2.line, $a2.pos);} SEMICOLON
	| a3=BREAK {$value = new JumpStatement(JumpStatement.BREAK, null, null); setLineNumAndPos($value, $a3.line, $a3.pos);} SEMICOLON
	| a4=RETURN {$value = new JumpStatement(JumpStatement.RETURN, null, null); setLineNumAndPos($value, $a4.line, $a4.pos); } (e1=expression {setReturnExprToJumpStmt($value, $e1.value); })? SEMICOLON
	;
expression returns [RootExpr value]
	:	e1=assignment_expression {$value = addAssignmentExpToExpr($e1.value, $value);} 
		(COMMA e2=assignment_expression {$value = addAssignmentExpToExpr($e2.value, $value);})*
	;
include_statement returns [IncludeStatement value]:
	e0=HASH LESSER_THAN e1=filename_lib GREATER_THAN HASH 
		{$value = new IncludeStatement($e0.line, $e1.value, IncludeStatement.LIB);} 
	| 
		HASH e2=STRING_LITERAL HASH
		{$value = new IncludeStatement($e2.line, $e2.text, IncludeStatement.LOCAL);}
	;
assignment_expression returns [AssignmentExpr value]
	:	e1=unary_expr {$value = new AssignmentExpr(); $value.setUnaryExpr($e1.value);}
		 e2=assignment_operator {$value.setAssignmentOperator($e2.value);} (WS*)
		 e3=assignment_expression {$value.setAssignmentExpr($e3.value); setLineNumAndPos($value, $e1.value);}
		| e4=conditional_expression {$value = new AssignmentExpr(); $value.setConditionalExpr($e4.value); setLineNumAndPos($value, $e4.value);}
	;
assignment_operator returns [AssignmentOperator value]
	:	a1=ASSIGN {$value = new AssignmentOperator(AssignmentOperator.ASSIGN); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	a2=MULTIPLY_ASSIGN  {$value = new AssignmentOperator(AssignmentOperator.MULTIPLY_ASSIGN); setLineNumAndPos($value, $a2.line, $a2.pos);}
	|	a3=DIVIDE_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.DIVIDE_ASSIGN); setLineNumAndPos($value, $a3.line, $a3.pos);}
	|	a4=ADD_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.ADD_ASSIGN); setLineNumAndPos($value, $a4.line, $a4.pos);}
	|	a5=MINUS_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.MINUS_ASSIGN); setLineNumAndPos($value, $a5.line, $a5.pos);}
	|	a6=MODULO_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.MODULO_ASSIGN); setLineNumAndPos($value, $a6.line, $a6.pos);}
	|	a7=BITWISE_AND_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.BITWISE_AND_ASSIGN); setLineNumAndPos($value, $a7.line, $a7.pos);}
	|	a8=BITWISE_XOR_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.BITWISE_XOR_ASSIGN); setLineNumAndPos($value, $a8.line, $a8.pos);}
	|	a9=BITWISE_OR_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.BITWISE_OR_ASSIGN); setLineNumAndPos($value, $a9.line, $a9.pos);}
	|	a10=LEFT_SHIFT_ASSIGN {$value = new AssignmentOperator(AssignmentOperator.LEFT_SHIFT_ASSIGN); setLineNumAndPos($value, $a10.line, $a10.pos);}
	|	a11=RIGHT_SHIFT_ASSIGN{$value = new AssignmentOperator(AssignmentOperator.RIGHT_SHIFT_ASSIGN); setLineNumAndPos($value, $a11.line, $a11.pos);}
	;
conditional_expression returns [ConditionalExpr value]
	:	e1=logical_or_expr {$value = new ConditionalExpr(); $value.setLogicalOrExpr($e1.value); setLineNumAndPos($value, $e1.value); }
		(QUESTION e2= expression {$value.setTrueInConditional($e2.value);} 
			COLON e3= conditional_expression {$value.setFalseInConditional($e3.value);})?
	;
constant_expression returns [ConstExpr value]
	:	e=conditional_expression {$value = new ConstExpr(); $value.setCondExpr($e.value); setLineNumAndPos($value, $e.value);}
	;
logical_or_expr returns [LogicalOrExpr value]
	:	e1=logical_and_expr {$value = new LogicalOrExpr(null, $e1.value); setLineNumAndPos($value, $e1.value);} 
		(OR e2=logical_and_expr {$value = new LogicalOrExpr($value, $e2.value);})*
	;
logical_and_expr returns [LogicalAndExpr value]
	:	e1=inclusive_or_expr {$value = new LogicalAndExpr(null, $e1.value); setLineNumAndPos($value, $e1.value);} 
		 (AND e2=inclusive_or_expr {$value = new LogicalAndExpr($value, $e2.value);})*
	;
inclusive_or_expr returns [InclusiveOrExpr value]
	:	e1=exclusive_or_expr {$value = new InclusiveOrExpr(null, $e1.value); setLineNumAndPos($value, $e1.value);}  
		(PIPE e2=exclusive_or_expr {$value = new InclusiveOrExpr($value, $e2.value);})*
	;
exclusive_or_expr returns [ExclusiveOrExpr value]
	:	e1=and_expr {$value = new ExclusiveOrExpr(null, $e1.value); setLineNumAndPos($value, $e1.value);} 
		(CARET e2=and_expr {$value = new ExclusiveOrExpr($value, $e2.value);})*
	;
and_expr returns [AndExpr value]
	:	e1=equality_expr  {$value = new AndExpr(null, $e1.value); setLineNumAndPos($value, $e1.value);} 
		 (AMPERSAND e2=equality_expr {$value = new AndExpr($value, $e2.value);})*
	;
equality_expr returns [EqualityExpr value]
	:	e1=relational_expr {$value = new EqualityExpr(null, $e1.value, -1); setLineNumAndPos($value, $e1.value);} 
		 ((sign=EQUALS | sign=NOT_EQUALS )
		  e2=relational_expr {$value = new EqualityExpr($value, $e2.value, getIdForSymbol($sign.text, $value));} )*
	;
relational_expr returns [RelationalExpr value]
	:	e1=shift_expr 	{$value = new RelationalExpr(null, $e1.value, -1); setLineNumAndPos($value, $e1.value);} 
		(( sign=LESSER_THAN | sign=GREATER_THAN | sign=LESSER_THAN_OR_EQUAL_TO | sign=GREATER_THAN_OR_EQUAL_TO)
		 e2=shift_expr {$value = new RelationalExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
shift_expr returns [ShiftExpr value]
	:	e1=additive_expr {$value = new ShiftExpr(null, $e1.value, -1); setLineNumAndPos($value, $e1.value);} 
		 ((sign=LEFT_SHIFT | sign=RIGHT_SHIFT)
		  e2=additive_expr {$value = new ShiftExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
additive_expr returns [AdditiveExpr value]
	:	e1=multiplicative_expr {$value = new AdditiveExpr(null, $e1.value, -1); setLineNumAndPos($value, $e1.value);} 
		(( sign=PLUS  | sign=MINUS) 
		e2=multiplicative_expr {$value = new AdditiveExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
multiplicative_expr returns [MultiplicativeExpr value]
	:	e1=cast_expr {$value = new MultiplicativeExpr(null, $e1.value, -1); setLineNumAndPos($value, $e1.value);} 
		(( sign=STAR | sign=DIVIDE | sign=MODULO )
		e2=cast_expr {$value = new MultiplicativeExpr($value, $e2.value, getIdForSymbol($sign.text, $value));})*
	;
cast_expr returns [CastExpr value]
	:	e=unary_expr {$value = new CastExpr(); $value.setUnaryExpr($e.value); setLineNumAndPos($value, $e.value); }
	|	a1=LPAREN e1=type_name {$value = new CastExpr(); $value.setTypeName($e1.value);}
		 RPAREN e2=cast_expr  {$value.setCastExpr($e2.value); setLineNumAndPos($value, $a1.line, $a1.pos);}
	;
unary_expr returns [UnaryExpr value]
	:	e=postfix_expr {$value = new UnaryExpr(); $value.setPostfixExpr($e.value); setLineNumAndPos($value, $e.value);}
	|	a1=INCREMENT {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.INCR);} e1=unary_expr {$value.setUnaryExpr($e1.value); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	a2=DECREMENT {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.DECR);} e2=unary_expr{$value.setUnaryExpr($e2.value); setLineNumAndPos($value, $a2.line, $a2.pos);}
	|	(a3=AMPERSAND  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.AMPERSAND); setLineNumAndPos($value, $a3.line, $a3.pos);}
		| 	a4=STAR  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.STAR); setLineNumAndPos($value, $a4.line, $a4.pos);}
		| 	a5=PLUS  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.PLUS); setLineNumAndPos($value, $a5.line, $a5.pos);}
		| 	a6=MINUS  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.MINUS); setLineNumAndPos($value, $a6.line, $a6.pos);}
		| 	a7=TILDE  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.TILDE); setLineNumAndPos($value, $a7.line, $a7.pos);}
		| 	a8=NOT  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.NOT); setLineNumAndPos($value, $a8.line, $a8.pos);}
		) e3=cast_expr {$value.setCastExpr($e3.value);}
	|	a9=SIZEOF  {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.SIZEOF);} e4=unary_expr {$value.setUnaryExpr($e4.value); setLineNumAndPos($value, $a9.line, $a9.pos);}
	|	a10=SIZEOF {$value = new UnaryExpr(); $value.setOperator(UnaryExpr.SIZEOF); }  LPAREN
		 e5=type_name {$value.setTypeName($e5.value); setLineNumAndPos($value, $a10.line, $a10.pos);} RPAREN
	;
postfix_expr returns [PostfixExpr value]
	:	e1=primary_expr {$value = new PostfixExpr(); $value.setPrimaryExpr($e1.value); setLineNumAndPos($value, $e1.value);}
		(( LBRACK e2=expression {addToPostFixExpr($e2.value, null, PostfixExpr.ARRAY_REF, null, $value); } RBRACK 
		| LPAREN (e3=argument_expr_list )? RPAREN  {addToPostFixExpr(null, $e3.value, PostfixExpr.FUNC_CALL, null, $value); }
		| DOT e=ID {addToPostFixExpr(null, null, PostfixExpr.DOT, $e.text, $value); }
		| DEREFERENCE e4=ID {addToPostFixExpr(null, null, PostfixExpr.DEREFERENCE, $e4.text, $value); }
		| INCREMENT {addToPostFixExpr(null, null, PostfixExpr.INCR, null, $value); }
		| DECREMENT {addToPostFixExpr(null, null, PostfixExpr.DECR, null, $value); }
		))*
	;
primary_expr returns [PrimaryExpr value] 
	:	e=ID {$value = new PrimaryExpr(PrimaryExpr.IDENTIFIER_TYPE); $value.setIdentifier($e.text); setLineNumAndPos($value, $e.line, $e.pos); }
	|	e1=constant {$value = new PrimaryExpr(PrimaryExpr.CONSTANT_TYPE); $value.setConstant($e1.value); setLineNumAndPos($value, $e1.value);}
	|	e2=STRING_LITERAL {$value = new PrimaryExpr(PrimaryExpr.STRING_TYPE); $value.setString($e2.text); setLineNumAndPos($value, $e2.line, $e2.pos);}
	|	a1=LPAREN e3=expression {$value = new PrimaryExpr(PrimaryExpr.EXPR_TYPE); $value.setExpr($e3.value); setLineNumAndPos($value, $a1.line, $a1.pos);} RPAREN
	;
argument_expr_list returns [ArgumentExpressionList value]
	:	(e1=assignment_expression {$value = (ArgumentExpressionList)addNodeToCollection($e1.value, $value);})
			(COMMA e2=assignment_expression {$value =  (ArgumentExpressionList) addNodeToCollection($e2.value, $value);})*
	;
constant returns [Constant value]
	:	a1=DECIMAL_LITERAL  {$value = new Constant(); $value.setIntConstant($DECIMAL_LITERAL.text); setLineNumAndPos($value, $a1.line, $a1.pos);}
	|	a2=OCTAL_LITERAL  {$value = new Constant(); $value.setIntConstant($OCTAL_LITERAL.text); setLineNumAndPos($value, $a2.line, $a2.pos);}
	|	a3=HEX_LITERAL  {$value = new Constant(); $value.setIntConstant($HEX_LITERAL.text); setLineNumAndPos($value, $a3.line, $a3.pos);}
	|	a4=FLOATING_LITERAL  {$value = new Constant(); $value.setFloatingConstant($FLOATING_LITERAL.text); setLineNumAndPos($value, $a4.line, $a4.pos);}
	|	a5=CHAR_LITERAL {$value = new Constant(); $value.setCharConstant($CHAR_LITERAL.text); setLineNumAndPos($value, $a5.line, $a5.pos);}
	;