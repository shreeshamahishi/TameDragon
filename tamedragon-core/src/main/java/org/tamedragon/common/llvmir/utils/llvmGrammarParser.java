// $ANTLR 3.5.2 llvmGrammar.g 2020-02-03 11:59:32

package org.tamedragon.common.llvmir.utils;
import org.tamedragon.common.llvmir.irdata.*;

import org.tamedragon.common.llvmir.instructions.*;
import org.tamedragon.common.llvmir.types.*;
import org.tamedragon.common.llvmir.types.exceptions.*;
import org.tamedragon.common.llvmir.math.*;

import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.semanticanalysis.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class llvmGrammarParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALIGN", "ARGUMENT_ATTRIBUTE", 
		"ATOMIC_ORDERING", "BIN_OPR_FLAG", "BIN_OPR_STR", "CALLING_CONV", "CAST_TYPE", 
		"COMMA", "CONDITION", "DECIMAL_LITERAL", "DOT", "END_ANGULAR", "END_CURLY", 
		"END_PARANTHESIS", "END_SQUARE_BR", "EQUAL", "EscapeSequence", "Exponent", 
		"FALSE", "FLOATING_LITERAL", "FUNCTION_ATTRIBUTE", "FloatTypeSuffix", 
		"GLOBAL_PREFIX", "HEX_LITERAL", "HexDigit", "ID", "IntegerTypeSuffix", 
		"LETTER", "LINE_COMMENT", "LINKAGE_TYPE", "LOCAL_PREFIX", "MUL_OPERATOR", 
		"NULL_CHAR", "NUMBER", "OCTAL_LITERAL", "OctalEscape", "PARAMETER_ATTRIBUTE", 
		"PHI", "PRIMITIVE_DATA_TYPE", "RETURN_ATTR", "STAR", "START_ANGULAR", 
		"START_CURLY", "START_PARANTHESIS", "START_SQUARE_BR", "STRING_LITERAL", 
		"TRUE", "UNREACHABLE", "WHITESPACE", "XOR", "ZEROINITIALIZER", "' \"'", 
		"' alloca '", "'\"'", "'-'", "'-p:'", "'...'", "':'", "'S32\"'", "'br'", 
		"'call'", "'constant'", "'declare'", "'define'", "'fcmp'", "'getelementptr'", 
		"'global'", "'icmp'", "'inbounds'", "'label'", "'load'", "'ret'", "'select'", 
		"'store'", "'switch'", "'tail'", "'target datalayout'", "'to'", "'type {'", 
		"'undef'", "'unnamed_addr'", "'volatile'"
	};
	public static final int EOF=-1;
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
	public static final int ALIGN=4;
	public static final int ARGUMENT_ATTRIBUTE=5;
	public static final int ATOMIC_ORDERING=6;
	public static final int BIN_OPR_FLAG=7;
	public static final int BIN_OPR_STR=8;
	public static final int CALLING_CONV=9;
	public static final int CAST_TYPE=10;
	public static final int COMMA=11;
	public static final int CONDITION=12;
	public static final int DECIMAL_LITERAL=13;
	public static final int DOT=14;
	public static final int END_ANGULAR=15;
	public static final int END_CURLY=16;
	public static final int END_PARANTHESIS=17;
	public static final int END_SQUARE_BR=18;
	public static final int EQUAL=19;
	public static final int EscapeSequence=20;
	public static final int Exponent=21;
	public static final int FALSE=22;
	public static final int FLOATING_LITERAL=23;
	public static final int FUNCTION_ATTRIBUTE=24;
	public static final int FloatTypeSuffix=25;
	public static final int GLOBAL_PREFIX=26;
	public static final int HEX_LITERAL=27;
	public static final int HexDigit=28;
	public static final int ID=29;
	public static final int IntegerTypeSuffix=30;
	public static final int LETTER=31;
	public static final int LINE_COMMENT=32;
	public static final int LINKAGE_TYPE=33;
	public static final int LOCAL_PREFIX=34;
	public static final int MUL_OPERATOR=35;
	public static final int NULL_CHAR=36;
	public static final int NUMBER=37;
	public static final int OCTAL_LITERAL=38;
	public static final int OctalEscape=39;
	public static final int PARAMETER_ATTRIBUTE=40;
	public static final int PHI=41;
	public static final int PRIMITIVE_DATA_TYPE=42;
	public static final int RETURN_ATTR=43;
	public static final int STAR=44;
	public static final int START_ANGULAR=45;
	public static final int START_CURLY=46;
	public static final int START_PARANTHESIS=47;
	public static final int START_SQUARE_BR=48;
	public static final int STRING_LITERAL=49;
	public static final int TRUE=50;
	public static final int UNREACHABLE=51;
	public static final int WHITESPACE=52;
	public static final int XOR=53;
	public static final int ZEROINITIALIZER=54;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public llvmGrammarParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public llvmGrammarParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[234+1];


	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return llvmGrammarParser.tokenNames; }
	@Override public String getGrammarFileName() { return "llvmGrammar.g"; }



		private int dataTypeLine = 0;
		private int dataTypePos = 0;

		 private List <ValueData> list = new ArrayList<ValueData>();
		 
		 // Argument data for a function are added to this list.
		private List<ArgumentData> argumentDataList = new ArrayList<ArgumentData>();
			 	    
		private List <ValueData> ptrDataList = new ArrayList<ValueData>();
		private CastInstrData castData = null;
		private GetElementPtrData getElementPtrData = null;	      
	  
	  	public List <ValueData> getList() {
	    	return list;
	  	}
		   
		private void addArgument(String typeStr, String name, String attributeList, boolean hasEllipses) {
			ArgumentData argument = new ArgumentData();
			argument.setResult(name);
		    argument.setTypeStr(typeStr);
		    argument.setAttributs(attributeList);
		    argument.setHasEllipses(hasEllipses);
			argument.setLineNum(dataTypeLine);
			argument.setPosition(dataTypePos);
		 	argumentDataList.add(argument);
		}
		
		private void updateDataTypeLineAndPos(int line, int pos){
			dataTypeLine = line;
			dataTypePos = pos;
		}
		
		private void setFunction(String linkageType, String returnAttr, String dataType, String name,
					String functionAttr, boolean isDefine, int lineNum, int position) {
			FunctionData function = new FunctionData();
			function.setLinkageType(linkageType);
			function.setReturnAttribute(returnAttr);
			function.setTypeStr(dataType);
			function.setFunctionName(name);
			function.setArgumentList(argumentDataList);
			function.setFunctionAttribute(functionAttr);
			function.setDefine(isDefine);
		    list.add(function);
		    
			function.setLineNum(lineNum);
			function.setPosition(position);
			
		    // Reset the argument data list
		    argumentDataList = new ArrayList<ArgumentData>();
		}

		private void setBinaryOperator(String result, String typeStr, String firstOpr, String secondOpr, String binOpIdStr, 
					String flag, int lineNum, int position) {            
	              BinaryOprData binaryOpr = new BinaryOprData();
	              BinaryOperatorID binOprId = BinaryOperator.getBinOpIDFromString(binOpIdStr.trim());
	              binaryOpr.setResult(result);              
	              binaryOpr.setTypeStr(typeStr);
	              binaryOpr.setBinOpratorID(binOprId);
	              binaryOpr.setFirstOperand(firstOpr);
	              binaryOpr.setSecondOperand(secondOpr);
				  binaryOpr.setFlag(flag);              
	              binaryOpr.setLineNum(lineNum);
				  binaryOpr.setPosition(position);
	              list.add(binaryOpr);
		}
	  
		private void setAllocaInstr(String name, String typeStr, String arrayLength, String alignValue, int lineNum, int position) {
			AllocaInstrData alloca = new AllocaInstrData();							
			alloca.setResult(name);
			alloca.setTypeStr(typeStr);
			alloca.setArrayLength(arrayLength);	
			alloca.setAlign(alignValue);
			alloca.setLineNum(lineNum);
			alloca.setPosition(position);
			list.add(alloca);			
		}

		private void setStoreInstr(String arg1, String arg2, String typeStr, String atomic, String isVolatile, int lineNum, int position) {
			StoreInstrData store = new StoreInstrData(); 
			store.setFirstOperand(arg1);
			store.setSecondOperand(arg2);
			store.setTypeStr(typeStr);
			store.setLineNum(lineNum);
			store.setPosition(position);
			store.setAtomicOrdering(atomic);
			store.setIsVolatile(isVolatile);
		   
		    list.add(store);
		}

		private void setLoadInstr(String pointeeTypeStr, String id, String pointerName, String typeStr, int lineNum, int position) {	
			LoadInstrData load = new LoadInstrData();
			load.setPointeeTypeStr(pointeeTypeStr);
			load.setPointerName(pointerName);
			load.setResult(id);
			load.setTypeStr(typeStr);
			load.setLineNum(lineNum);
			load.setPosition(position);
		    list.add(load);
		}
		
		private void setGetElementPtr(String pointeeTypeStr, String name, String elementName, String ptrType, String offset, 
									  String inbound, int lineNum, int position) {
			GetElementPtrData data = new GetElementPtrData();
			data.setPointeeTypeStr(pointeeTypeStr);
			data.setResult(name);
			data.setElementName(elementName);
			data.setPtrType(ptrType);
			data.setListVsIndexStr(offset);
			data.setIsInBound(inbound);
			data.setLineNum(lineNum);
			data.setPosition(position);
			if(name == null){
				ptrDataList.add(data);
			}
			else{
				list.add(data); 		
			}	
		}
		
		private void setRetInstr(String returnDataType, String returndata, int lineNum, int position){
			ReturnInstrData data = new ReturnInstrData();
			data.setReturnType(returnDataType);
			data.setReturnData(returndata);
			data.setLineNum(lineNum);
			data.setPosition(position);
			list.add(data);
		}	
		
		private void setBranchInstr(String ifTrueStr, String ifFalseStr, String condition, int lineNum, int position){
		   BranchInstrData branch = new BranchInstrData();
		   branch.setIfTrue(ifTrueStr);
		   branch.setIfFalse(ifFalseStr);
		   branch.setCondition(condition);
		   branch.setLineNum(lineNum);
		   branch.setPosition(position);
		   
		   list.add(branch);
		}
		
		private void setSwitchInstr(String switchOn, String defaultBB, int lineNum, int position) {
			SwitchInstrData data = new SwitchInstrData();
			data.setDefaultBB(defaultBB);
			data.setSwitchOn(switchOn);
			data.setLineNum(lineNum);
			data.setPosition(position);
			
			list.add(data);		
		}	
		
		private void setSwitchCase(String typeStr, String condition, String target, int lineNum, int position) {
	          SwitchCasesData casesData = new SwitchCasesData();
	          casesData.setTypeStr(typeStr);
	          casesData.setCondition(condition);
	          casesData.setTarget(target);		  
			  casesData.setLineNum(lineNum);
			  casesData.setPosition(position);
	          
			  list.add(casesData);
		} 
		
		private void setGlobalVariable(String name, String isInitial, String typeStr, String initialValue, 
									   String isConstant, String alignStr, 
									   String unnamed, String type, int lineNum, int position) {
			
			GlobalVariableData variableData = new GlobalVariableData();
			boolean isUnnamed = false;
			variableData.setResult(name);
			variableData.setTypeStr(typeStr);
			variableData.setInitialValue(initialValue);
			variableData.setLinkageType(isInitial);
			variableData.setIsConstant(isConstant);
			variableData.setAlign(alignStr);
			variableData.setVariableType(type);
			if(unnamed != null){
				isUnnamed= true;
			}
			variableData.setUnnamed(isUnnamed);
	        variableData.setCastData(castData);
	        variableData.setPtrData(getElementPtrData);
	        variableData.setPtrDataList(ptrDataList);
			variableData.setLineNum(lineNum);
	        variableData.setPosition(position);
			
			// Reset the list of pointer data
	         ptrDataList = new ArrayList<ValueData>();
	         castData = null;
					
			list.add(variableData);		
		}	

		private void setStruct(String name, String elements, int lineNum, int position) {
			StructureData data = new StructureData();
			data.setResult(name);
			data.setStructElements(elements);

			data.setLineNum(lineNum);
	        data.setPosition(position);
			
			list.add(data);
		}	
		
		private void setPhiNode(String name, String typeStr, String incoming, int lineNum, int position) {
			PhiNodeData phidata = new PhiNodeData();
			phidata.setResult(name);
			phidata.setTypeStr(typeStr);
			phidata.setIncomingValueAndBBPairs(incoming);

			phidata.setLineNum(lineNum);
	        phidata.setPosition(position);
			
			list.add(phidata);
		}
		
		private void setCallInstr(String name, String callingConv, String isTail, String parameter, String typeStr, 
					String functionName, String parameterAttr, String functionAttr, int lineNum, int position) {
			CallInstrData data = new CallInstrData();
			data.setResult(name);
			data.setCallingConv(callingConv);
			data.setIsVarArguments(isTail);
			data.setFunctionParameters(parameter);
			data.setTypeStr(typeStr);
			data.setFunctionName(functionName);
			data.setParameterAttr(parameterAttr);
			data.setFunctionAttributes(functionAttr);
			
			data.setLineNum(lineNum);
	        data.setPosition(position);
			
			list.add(data);
		}	
		
		private void setCastInstr(String name, String type, String source, String value, String target, int lineNum, int position) {
			CastInstrData data = new CastInstrData();
			data.setResult(name);
			data.setTypeStr(type);
			data.setSource(source);
			data.setValue(value);
			data.setTarget(target);
			
			data.setLineNum(lineNum);
	        data.setPosition(position);
					
			if(name == null){
				castData = data;
				castData.setLineNum(lineNum);
				castData.setPosition(position);		
			}
			else{
				list.add(data);
			}
		}	
		
		private void createAndSetIcmpData(String result, String compare, String predicate, String typeStr, String firstOpr,
				String secondOpr, int lineNum, int position) {
			CmpData data =  new CmpData();
			data.setResult(result);
			data.setCmpType(compare);
			data.setPredicate(predicate);
			data.setTypeStr(typeStr);
			data.setFirstOprand(firstOpr);
			data.setSecondOprand(secondOpr);
			
			data.setLineNum(lineNum);
	        data.setPosition(position);
			
			list.add(data);
		}
				
		private void setTargetData(String targetDataString, int lineNum, int position) {
			DataLayout data = new DataLayout();
			data.setTargetDataLayout(targetDataString);
		
			data.setLineNum(lineNum);
			data.setPosition(position);
		
			list.add(data);
		}

		private void setSelectInstr(String result, String typeStr, String condValueStr, 
						String type1, String value1, String type2, String value2, int lineNum, int position){
			SelectInstrData selectInstrData = new SelectInstrData();
	    		selectInstrData.setResult(result);
	    		selectInstrData.setTypeStr(typeStr);
	    		selectInstrData.setConditionalValue(condValueStr);
	    		selectInstrData.setFirstType(type1);
	    		selectInstrData.setFirstValue(value1);
	    		selectInstrData.setSecondType(type2);
	    		selectInstrData.setSecondValue(value2);
	    		selectInstrData.setLineNum(lineNum);
	    		
	    		selectInstrData.setLineNum(lineNum);
	    		selectInstrData.setPosition(position);
	    		
	    		list.add(selectInstrData);
		}		


	public static class string_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "string"
	// llvmGrammar.g:360:1: string : ID NULL_CHAR ;
	public final llvmGrammarParser.string_return string() throws RecognitionException {
		llvmGrammarParser.string_return retval = new llvmGrammarParser.string_return();
		retval.start = input.LT(1);
		int string_StartIndex = input.index();

		Object root_0 = null;

		Token ID1=null;
		Token NULL_CHAR2=null;

		Object ID1_tree=null;
		Object NULL_CHAR2_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

			// llvmGrammar.g:360:8: ( ID NULL_CHAR )
			// llvmGrammar.g:360:10: ID NULL_CHAR
			{
			root_0 = (Object)adaptor.nil();


			ID1=(Token)match(input,ID,FOLLOW_ID_in_string487); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID1_tree = (Object)adaptor.create(ID1);
			adaptor.addChild(root_0, ID1_tree);
			}

			NULL_CHAR2=(Token)match(input,NULL_CHAR,FOLLOW_NULL_CHAR_in_string489); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NULL_CHAR2_tree = (Object)adaptor.create(NULL_CHAR2);
			adaptor.addChild(root_0, NULL_CHAR2_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 1, string_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "string"


	public static class prefix_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prefix"
	// llvmGrammar.g:363:1: prefix : ( GLOBAL_PREFIX | LOCAL_PREFIX );
	public final llvmGrammarParser.prefix_return prefix() throws RecognitionException {
		llvmGrammarParser.prefix_return retval = new llvmGrammarParser.prefix_return();
		retval.start = input.LT(1);
		int prefix_StartIndex = input.index();

		Object root_0 = null;

		Token set3=null;

		Object set3_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

			// llvmGrammar.g:363:8: ( GLOBAL_PREFIX | LOCAL_PREFIX )
			// llvmGrammar.g:
			{
			root_0 = (Object)adaptor.nil();


			set3=input.LT(1);
			if ( input.LA(1)==GLOBAL_PREFIX||input.LA(1)==LOCAL_PREFIX ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set3));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 2, prefix_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "prefix"


	public static class llvm_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "llvm"
	// llvmGrammar.g:440:1: llvm : llvm_element ;
	public final llvmGrammarParser.llvm_return llvm() throws RecognitionException {
		llvmGrammarParser.llvm_return retval = new llvmGrammarParser.llvm_return();
		retval.start = input.LT(1);
		int llvm_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope llvm_element4 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

			// llvmGrammar.g:440:6: ( llvm_element )
			// llvmGrammar.g:440:8: llvm_element
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_llvm_element_in_llvm1268);
			llvm_element4=llvm_element();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, llvm_element4.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 3, llvm_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "llvm"


	public static class llvm_element_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "llvm_element"
	// llvmGrammar.g:442:1: llvm_element : ( target_machine_info )? ( function_def | global_var_list | function_declaration )+ ;
	public final llvmGrammarParser.llvm_element_return llvm_element() throws RecognitionException {
		llvmGrammarParser.llvm_element_return retval = new llvmGrammarParser.llvm_element_return();
		retval.start = input.LT(1);
		int llvm_element_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope target_machine_info5 =null;
		ParserRuleReturnScope function_def6 =null;
		ParserRuleReturnScope global_var_list7 =null;
		ParserRuleReturnScope function_declaration8 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

			// llvmGrammar.g:442:13: ( ( target_machine_info )? ( function_def | global_var_list | function_declaration )+ )
			// llvmGrammar.g:442:15: ( target_machine_info )? ( function_def | global_var_list | function_declaration )+
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:442:15: ( target_machine_info )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==80) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// llvmGrammar.g:442:15: target_machine_info
					{
					pushFollow(FOLLOW_target_machine_info_in_llvm_element1275);
					target_machine_info5=target_machine_info();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, target_machine_info5.getTree());

					}
					break;

			}

			// llvmGrammar.g:442:36: ( function_def | global_var_list | function_declaration )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=4;
				switch ( input.LA(1) ) {
				case 67:
					{
					alt2=1;
					}
					break;
				case FALSE:
				case FLOATING_LITERAL:
				case GLOBAL_PREFIX:
				case ID:
				case LOCAL_PREFIX:
				case NUMBER:
				case TRUE:
				case 83:
					{
					alt2=2;
					}
					break;
				case 66:
					{
					alt2=3;
					}
					break;
				}
				switch (alt2) {
				case 1 :
					// llvmGrammar.g:442:37: function_def
					{
					pushFollow(FOLLOW_function_def_in_llvm_element1279);
					function_def6=function_def();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, function_def6.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:443:12: global_var_list
					{
					pushFollow(FOLLOW_global_var_list_in_llvm_element1293);
					global_var_list7=global_var_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, global_var_list7.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:444:12: function_declaration
					{
					pushFollow(FOLLOW_function_declaration_in_llvm_element1307);
					function_declaration8=function_declaration();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, function_declaration8.getTree());

					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 4, llvm_element_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "llvm_element"


	public static class target_machine_info_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "target_machine_info"
	// llvmGrammar.g:446:1: target_machine_info : target_datalayout ;
	public final llvmGrammarParser.target_machine_info_return target_machine_info() throws RecognitionException {
		llvmGrammarParser.target_machine_info_return retval = new llvmGrammarParser.target_machine_info_return();
		retval.start = input.LT(1);
		int target_machine_info_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope target_datalayout9 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

			// llvmGrammar.g:446:21: ( target_datalayout )
			// llvmGrammar.g:446:23: target_datalayout
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_target_datalayout_in_target_machine_info1319);
			target_datalayout9=target_datalayout();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, target_datalayout9.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 5, target_machine_info_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "target_machine_info"


	public static class target_datalayout_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "target_datalayout"
	// llvmGrammar.g:448:1: target_datalayout : e1= 'target datalayout' EQUAL target_Data_ID ;
	public final llvmGrammarParser.target_datalayout_return target_datalayout() throws RecognitionException {
		llvmGrammarParser.target_datalayout_return retval = new llvmGrammarParser.target_datalayout_return();
		retval.start = input.LT(1);
		int target_datalayout_StartIndex = input.index();

		Object root_0 = null;

		Token e1=null;
		Token EQUAL10=null;
		ParserRuleReturnScope target_Data_ID11 =null;

		Object e1_tree=null;
		Object EQUAL10_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

			// llvmGrammar.g:448:19: (e1= 'target datalayout' EQUAL target_Data_ID )
			// llvmGrammar.g:448:21: e1= 'target datalayout' EQUAL target_Data_ID
			{
			root_0 = (Object)adaptor.nil();


			e1=(Token)match(input,80,FOLLOW_80_in_target_datalayout1329); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e1_tree = (Object)adaptor.create(e1);
			adaptor.addChild(root_0, e1_tree);
			}

			EQUAL10=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_target_datalayout1331); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EQUAL10_tree = (Object)adaptor.create(EQUAL10);
			adaptor.addChild(root_0, EQUAL10_tree);
			}

			pushFollow(FOLLOW_target_Data_ID_in_target_datalayout1333);
			target_Data_ID11=target_Data_ID();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, target_Data_ID11.getTree());

			if ( state.backtracking==0 ) {
			          setTargetData((target_Data_ID11!=null?input.toString(target_Data_ID11.start,target_Data_ID11.stop):null), (e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));
			        }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 6, target_datalayout_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "target_datalayout"


	public static class target_Data_ID_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "target_Data_ID"
	// llvmGrammar.g:453:1: target_Data_ID : ' \"' ID '-p:' NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+ ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) ) ;
	public final llvmGrammarParser.target_Data_ID_return target_Data_ID() throws RecognitionException {
		llvmGrammarParser.target_Data_ID_return retval = new llvmGrammarParser.target_Data_ID_return();
		retval.start = input.LT(1);
		int target_Data_ID_StartIndex = input.index();

		Object root_0 = null;

		Token string_literal12=null;
		Token ID13=null;
		Token string_literal14=null;
		Token NUMBER15=null;
		Token char_literal16=null;
		Token NUMBER17=null;
		Token char_literal18=null;
		Token NUMBER19=null;
		Token char_literal20=null;
		Token PRIMITIVE_DATA_TYPE21=null;
		Token char_literal22=null;
		Token NUMBER23=null;
		Token char_literal24=null;
		Token NUMBER25=null;
		Token char_literal26=null;
		Token string_literal27=null;
		Token PRIMITIVE_DATA_TYPE28=null;
		Token char_literal29=null;
		Token NUMBER30=null;
		Token char_literal31=null;
		Token NUMBER32=null;
		Token char_literal33=null;

		Object string_literal12_tree=null;
		Object ID13_tree=null;
		Object string_literal14_tree=null;
		Object NUMBER15_tree=null;
		Object char_literal16_tree=null;
		Object NUMBER17_tree=null;
		Object char_literal18_tree=null;
		Object NUMBER19_tree=null;
		Object char_literal20_tree=null;
		Object PRIMITIVE_DATA_TYPE21_tree=null;
		Object char_literal22_tree=null;
		Object NUMBER23_tree=null;
		Object char_literal24_tree=null;
		Object NUMBER25_tree=null;
		Object char_literal26_tree=null;
		Object string_literal27_tree=null;
		Object PRIMITIVE_DATA_TYPE28_tree=null;
		Object char_literal29_tree=null;
		Object NUMBER30_tree=null;
		Object char_literal31_tree=null;
		Object NUMBER32_tree=null;
		Object char_literal33_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

			// llvmGrammar.g:453:16: ( ' \"' ID '-p:' NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+ ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) ) )
			// llvmGrammar.g:453:20: ' \"' ID '-p:' NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+ ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) )
			{
			root_0 = (Object)adaptor.nil();


			string_literal12=(Token)match(input,55,FOLLOW_55_in_target_Data_ID1356); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal12_tree = (Object)adaptor.create(string_literal12);
			adaptor.addChild(root_0, string_literal12_tree);
			}

			ID13=(Token)match(input,ID,FOLLOW_ID_in_target_Data_ID1358); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID13_tree = (Object)adaptor.create(ID13);
			adaptor.addChild(root_0, ID13_tree);
			}

			string_literal14=(Token)match(input,59,FOLLOW_59_in_target_Data_ID1360); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal14_tree = (Object)adaptor.create(string_literal14);
			adaptor.addChild(root_0, string_literal14_tree);
			}

			NUMBER15=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1363); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER15_tree = (Object)adaptor.create(NUMBER15);
			adaptor.addChild(root_0, NUMBER15_tree);
			}

			char_literal16=(Token)match(input,61,FOLLOW_61_in_target_Data_ID1365); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal16_tree = (Object)adaptor.create(char_literal16);
			adaptor.addChild(root_0, char_literal16_tree);
			}

			NUMBER17=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1367); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER17_tree = (Object)adaptor.create(NUMBER17);
			adaptor.addChild(root_0, NUMBER17_tree);
			}

			char_literal18=(Token)match(input,61,FOLLOW_61_in_target_Data_ID1369); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal18_tree = (Object)adaptor.create(char_literal18);
			adaptor.addChild(root_0, char_literal18_tree);
			}

			NUMBER19=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1371); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER19_tree = (Object)adaptor.create(NUMBER19);
			adaptor.addChild(root_0, NUMBER19_tree);
			}

			char_literal20=(Token)match(input,58,FOLLOW_58_in_target_Data_ID1373); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal20_tree = (Object)adaptor.create(char_literal20);
			adaptor.addChild(root_0, char_literal20_tree);
			}

			// llvmGrammar.g:453:68: ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==PRIMITIVE_DATA_TYPE) ) {
					int LA3_2 = input.LA(2);
					if ( (LA3_2==61) ) {
						int LA3_3 = input.LA(3);
						if ( (LA3_3==NUMBER) ) {
							int LA3_4 = input.LA(4);
							if ( (LA3_4==61) ) {
								int LA3_5 = input.LA(5);
								if ( (LA3_5==NUMBER) ) {
									int LA3_6 = input.LA(6);
									if ( (LA3_6==58) ) {
										alt3=1;
									}

								}

							}

						}

					}

				}

				switch (alt3) {
				case 1 :
					// llvmGrammar.g:453:70: PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-'
					{
					PRIMITIVE_DATA_TYPE21=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1377); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PRIMITIVE_DATA_TYPE21_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE21);
					adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE21_tree);
					}

					char_literal22=(Token)match(input,61,FOLLOW_61_in_target_Data_ID1379); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal22_tree = (Object)adaptor.create(char_literal22);
					adaptor.addChild(root_0, char_literal22_tree);
					}

					NUMBER23=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1381); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER23_tree = (Object)adaptor.create(NUMBER23);
					adaptor.addChild(root_0, NUMBER23_tree);
					}

					char_literal24=(Token)match(input,61,FOLLOW_61_in_target_Data_ID1383); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal24_tree = (Object)adaptor.create(char_literal24);
					adaptor.addChild(root_0, char_literal24_tree);
					}

					NUMBER25=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1385); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER25_tree = (Object)adaptor.create(NUMBER25);
					adaptor.addChild(root_0, NUMBER25_tree);
					}

					char_literal26=(Token)match(input,58,FOLLOW_58_in_target_Data_ID1387); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal26_tree = (Object)adaptor.create(char_literal26);
					adaptor.addChild(root_0, char_literal26_tree);
					}

					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			// llvmGrammar.g:454:7: ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==62) ) {
				alt4=1;
			}
			else if ( (LA4_0==PRIMITIVE_DATA_TYPE) ) {
				alt4=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// llvmGrammar.g:454:8: 'S32\"'
					{
					string_literal27=(Token)match(input,62,FOLLOW_62_in_target_Data_ID1400); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal27_tree = (Object)adaptor.create(string_literal27);
					adaptor.addChild(root_0, string_literal27_tree);
					}

					}
					break;
				case 2 :
					// llvmGrammar.g:454:17: ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' )
					{
					// llvmGrammar.g:454:17: ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' )
					// llvmGrammar.g:454:19: PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"'
					{
					PRIMITIVE_DATA_TYPE28=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1406); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PRIMITIVE_DATA_TYPE28_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE28);
					adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE28_tree);
					}

					char_literal29=(Token)match(input,61,FOLLOW_61_in_target_Data_ID1408); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal29_tree = (Object)adaptor.create(char_literal29);
					adaptor.addChild(root_0, char_literal29_tree);
					}

					NUMBER30=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1410); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER30_tree = (Object)adaptor.create(NUMBER30);
					adaptor.addChild(root_0, NUMBER30_tree);
					}

					char_literal31=(Token)match(input,61,FOLLOW_61_in_target_Data_ID1412); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal31_tree = (Object)adaptor.create(char_literal31);
					adaptor.addChild(root_0, char_literal31_tree);
					}

					NUMBER32=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1414); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER32_tree = (Object)adaptor.create(NUMBER32);
					adaptor.addChild(root_0, NUMBER32_tree);
					}

					char_literal33=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1416); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal33_tree = (Object)adaptor.create(char_literal33);
					adaptor.addChild(root_0, char_literal33_tree);
					}

					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 7, target_Data_ID_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "target_Data_ID"


	public static class global_var_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "global_var_list"
	// llvmGrammar.g:456:1: global_var_list : global_var_list_element ;
	public final llvmGrammarParser.global_var_list_return global_var_list() throws RecognitionException {
		llvmGrammarParser.global_var_list_return retval = new llvmGrammarParser.global_var_list_return();
		retval.start = input.LT(1);
		int global_var_list_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope global_var_list_element34 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

			// llvmGrammar.g:456:17: ( global_var_list_element )
			// llvmGrammar.g:456:19: global_var_list_element
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_global_var_list_element_in_global_var_list1426);
			global_var_list_element34=global_var_list_element();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, global_var_list_element34.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 8, global_var_list_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "global_var_list"


	public static class global_var_list_element_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "global_var_list_element"
	// llvmGrammar.g:458:1: global_var_list_element : global_var ;
	public final llvmGrammarParser.global_var_list_element_return global_var_list_element() throws RecognitionException {
		llvmGrammarParser.global_var_list_element_return retval = new llvmGrammarParser.global_var_list_element_return();
		retval.start = input.LT(1);
		int global_var_list_element_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope global_var35 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

			// llvmGrammar.g:458:25: ( global_var )
			// llvmGrammar.g:458:28: global_var
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_global_var_in_global_var_list_element1436);
			global_var35=global_var();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, global_var35.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 9, global_var_list_element_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "global_var_list_element"


	public static class function_declaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_declaration"
	// llvmGrammar.g:460:1: function_declaration : e0= 'declare' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( parameter_list )? END_PARANTHESIS function_attr_list ;
	public final llvmGrammarParser.function_declaration_return function_declaration() throws RecognitionException {
		llvmGrammarParser.function_declaration_return retval = new llvmGrammarParser.function_declaration_return();
		retval.start = input.LT(1);
		int function_declaration_StartIndex = input.index();

		Object root_0 = null;

		Token e0=null;
		Token e1=null;
		Token LINKAGE_TYPE36=null;
		Token RETURN_ATTR37=null;
		Token GLOBAL_PREFIX39=null;
		Token START_PARANTHESIS40=null;
		Token END_PARANTHESIS42=null;
		ParserRuleReturnScope data_type38 =null;
		ParserRuleReturnScope parameter_list41 =null;
		ParserRuleReturnScope function_attr_list43 =null;

		Object e0_tree=null;
		Object e1_tree=null;
		Object LINKAGE_TYPE36_tree=null;
		Object RETURN_ATTR37_tree=null;
		Object GLOBAL_PREFIX39_tree=null;
		Object START_PARANTHESIS40_tree=null;
		Object END_PARANTHESIS42_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

			// llvmGrammar.g:460:22: (e0= 'declare' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( parameter_list )? END_PARANTHESIS function_attr_list )
			// llvmGrammar.g:460:24: e0= 'declare' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( parameter_list )? END_PARANTHESIS function_attr_list
			{
			root_0 = (Object)adaptor.nil();


			e0=(Token)match(input,66,FOLLOW_66_in_function_declaration1447); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e0_tree = (Object)adaptor.create(e0);
			adaptor.addChild(root_0, e0_tree);
			}

			// llvmGrammar.g:460:37: ( LINKAGE_TYPE )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==LINKAGE_TYPE) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// llvmGrammar.g:460:37: LINKAGE_TYPE
					{
					LINKAGE_TYPE36=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_function_declaration1449); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LINKAGE_TYPE36_tree = (Object)adaptor.create(LINKAGE_TYPE36);
					adaptor.addChild(root_0, LINKAGE_TYPE36_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:460:51: ( RETURN_ATTR )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==RETURN_ATTR) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// llvmGrammar.g:460:51: RETURN_ATTR
					{
					RETURN_ATTR37=(Token)match(input,RETURN_ATTR,FOLLOW_RETURN_ATTR_in_function_declaration1452); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RETURN_ATTR37_tree = (Object)adaptor.create(RETURN_ATTR37);
					adaptor.addChild(root_0, RETURN_ATTR37_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:460:64: ( data_type )
			// llvmGrammar.g:460:65: data_type
			{
			pushFollow(FOLLOW_data_type_in_function_declaration1456);
			data_type38=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type38.getTree());

			}

			GLOBAL_PREFIX39=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_function_declaration1459); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GLOBAL_PREFIX39_tree = (Object)adaptor.create(GLOBAL_PREFIX39);
			adaptor.addChild(root_0, GLOBAL_PREFIX39_tree);
			}

			e1=(Token)match(input,ID,FOLLOW_ID_in_function_declaration1463); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e1_tree = (Object)adaptor.create(e1);
			adaptor.addChild(root_0, e1_tree);
			}

			START_PARANTHESIS40=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_function_declaration1465); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			START_PARANTHESIS40_tree = (Object)adaptor.create(START_PARANTHESIS40);
			adaptor.addChild(root_0, START_PARANTHESIS40_tree);
			}

			// llvmGrammar.g:461:11: ( parameter_list )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==LOCAL_PREFIX||LA7_0==PRIMITIVE_DATA_TYPE||LA7_0==START_ANGULAR||(LA7_0 >= START_PARANTHESIS && LA7_0 <= START_SQUARE_BR)) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// llvmGrammar.g:461:11: parameter_list
					{
					pushFollow(FOLLOW_parameter_list_in_function_declaration1477);
					parameter_list41=parameter_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, parameter_list41.getTree());

					}
					break;

			}

			END_PARANTHESIS42=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_function_declaration1480); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_PARANTHESIS42_tree = (Object)adaptor.create(END_PARANTHESIS42);
			adaptor.addChild(root_0, END_PARANTHESIS42_tree);
			}

			pushFollow(FOLLOW_function_attr_list_in_function_declaration1482);
			function_attr_list43=function_attr_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, function_attr_list43.getTree());

			if ( state.backtracking==0 ) {
			           setFunction((LINKAGE_TYPE36!=null?LINKAGE_TYPE36.getText():null), (RETURN_ATTR37!=null?RETURN_ATTR37.getText():null), (data_type38!=null?input.toString(data_type38.start,data_type38.stop):null), (e1!=null?e1.getText():null), (function_attr_list43!=null?input.toString(function_attr_list43.start,function_attr_list43.stop):null), false, 
			           				(e0!=null?e0.getLine():0), (e0!=null?e0.getCharPositionInLine():0));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 10, function_declaration_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "function_declaration"


	public static class parameter_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "parameter_list"
	// llvmGrammar.g:467:1: parameter_list : (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? ) ( parameters )* ;
	public final llvmGrammarParser.parameter_list_return parameter_list() throws RecognitionException {
		llvmGrammarParser.parameter_list_return retval = new llvmGrammarParser.parameter_list_return();
		retval.start = input.LT(1);
		int parameter_list_StartIndex = input.index();

		Object root_0 = null;

		Token e4=null;
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope parameters44 =null;

		Object e4_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

			// llvmGrammar.g:467:16: ( (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? ) ( parameters )* )
			// llvmGrammar.g:467:18: (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? ) ( parameters )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:467:18: (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )
			// llvmGrammar.g:467:19: e1= data_type (e4= ARGUMENT_ATTRIBUTE )?
			{
			pushFollow(FOLLOW_data_type_in_parameter_list1496);
			e1=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());

			// llvmGrammar.g:467:34: (e4= ARGUMENT_ATTRIBUTE )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==ARGUMENT_ATTRIBUTE) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// llvmGrammar.g:467:34: e4= ARGUMENT_ATTRIBUTE
					{
					e4=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_parameter_list1500); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e4_tree = (Object)adaptor.create(e4);
					adaptor.addChild(root_0, e4_tree);
					}

					}
					break;

			}

			}

			if ( state.backtracking==0 ) {
					addArgument((e1!=null?input.toString(e1.start,e1.stop):null), null, (e4!=null?e4.getText():null), false);
			    }
			// llvmGrammar.g:470:7: ( parameters )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==COMMA) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// llvmGrammar.g:470:7: parameters
					{
					pushFollow(FOLLOW_parameters_in_parameter_list1507);
					parameters44=parameters();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, parameters44.getTree());

					}
					break;

				default :
					break loop9;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 11, parameter_list_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "parameter_list"


	public static class parameters_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "parameters"
	// llvmGrammar.g:472:1: parameters : ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? ) ;
	public final llvmGrammarParser.parameters_return parameters() throws RecognitionException {
		llvmGrammarParser.parameters_return retval = new llvmGrammarParser.parameters_return();
		retval.start = input.LT(1);
		int parameters_StartIndex = input.index();

		Object root_0 = null;

		Token e4=null;
		Token var=null;
		Token COMMA45=null;
		ParserRuleReturnScope e1 =null;

		Object e4_tree=null;
		Object var_tree=null;
		Object COMMA45_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

			// llvmGrammar.g:472:12: ( ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? ) )
			// llvmGrammar.g:472:13: ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:472:13: ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? )
			// llvmGrammar.g:472:14: COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )?
			{
			COMMA45=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters1537); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA45_tree = (Object)adaptor.create(COMMA45);
			adaptor.addChild(root_0, COMMA45_tree);
			}

			// llvmGrammar.g:472:20: (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==LOCAL_PREFIX||LA11_0==PRIMITIVE_DATA_TYPE||LA11_0==START_ANGULAR||(LA11_0 >= START_PARANTHESIS && LA11_0 <= START_SQUARE_BR)) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// llvmGrammar.g:472:21: e1= data_type (e4= ARGUMENT_ATTRIBUTE )?
					{
					pushFollow(FOLLOW_data_type_in_parameters1542);
					e1=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());

					// llvmGrammar.g:472:36: (e4= ARGUMENT_ATTRIBUTE )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==ARGUMENT_ATTRIBUTE) ) {
						alt10=1;
					}
					switch (alt10) {
						case 1 :
							// llvmGrammar.g:472:36: e4= ARGUMENT_ATTRIBUTE
							{
							e4=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_parameters1546); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							e4_tree = (Object)adaptor.create(e4);
							adaptor.addChild(root_0, e4_tree);
							}

							}
							break;

					}

					}
					break;

				default :
					break loop11;
				}
			}

			// llvmGrammar.g:472:60: (var= '...' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==60) ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// llvmGrammar.g:472:61: var= '...'
					{
					var=(Token)match(input,60,FOLLOW_60_in_parameters1555); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);
					}

					}
					break;

			}

			}

			if ( state.backtracking==0 ) {
			                boolean hasEllipses = false;
			                if((var!=null?var.getText():null) != null){
			                	hasEllipses = true;
			                }
			                
			                addArgument((e1!=null?input.toString(e1.start,e1.stop):null),null,(e4!=null?e4.getText():null),hasEllipses);
			                }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 12, parameters_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "parameters"


	public static class function_def_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_def"
	// llvmGrammar.g:482:1: function_def : e0= 'define' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( argument_list )* END_PARANTHESIS function_attr_list ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )? ;
	public final llvmGrammarParser.function_def_return function_def() throws RecognitionException {
		llvmGrammarParser.function_def_return retval = new llvmGrammarParser.function_def_return();
		retval.start = input.LT(1);
		int function_def_StartIndex = input.index();

		Object root_0 = null;

		Token e0=null;
		Token e1=null;
		Token LINKAGE_TYPE46=null;
		Token RETURN_ATTR47=null;
		Token GLOBAL_PREFIX49=null;
		Token START_PARANTHESIS50=null;
		Token END_PARANTHESIS52=null;
		Token START_CURLY54=null;
		Token END_CURLY56=null;
		ParserRuleReturnScope data_type48 =null;
		ParserRuleReturnScope argument_list51 =null;
		ParserRuleReturnScope function_attr_list53 =null;
		ParserRuleReturnScope basic_blocks55 =null;

		Object e0_tree=null;
		Object e1_tree=null;
		Object LINKAGE_TYPE46_tree=null;
		Object RETURN_ATTR47_tree=null;
		Object GLOBAL_PREFIX49_tree=null;
		Object START_PARANTHESIS50_tree=null;
		Object END_PARANTHESIS52_tree=null;
		Object START_CURLY54_tree=null;
		Object END_CURLY56_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

			// llvmGrammar.g:482:13: (e0= 'define' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( argument_list )* END_PARANTHESIS function_attr_list ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )? )
			// llvmGrammar.g:482:15: e0= 'define' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( argument_list )* END_PARANTHESIS function_attr_list ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )?
			{
			root_0 = (Object)adaptor.nil();


			e0=(Token)match(input,67,FOLLOW_67_in_function_def1581); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e0_tree = (Object)adaptor.create(e0);
			adaptor.addChild(root_0, e0_tree);
			}

			// llvmGrammar.g:482:27: ( LINKAGE_TYPE )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==LINKAGE_TYPE) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// llvmGrammar.g:482:27: LINKAGE_TYPE
					{
					LINKAGE_TYPE46=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_function_def1583); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LINKAGE_TYPE46_tree = (Object)adaptor.create(LINKAGE_TYPE46);
					adaptor.addChild(root_0, LINKAGE_TYPE46_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:482:41: ( RETURN_ATTR )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==RETURN_ATTR) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// llvmGrammar.g:482:41: RETURN_ATTR
					{
					RETURN_ATTR47=(Token)match(input,RETURN_ATTR,FOLLOW_RETURN_ATTR_in_function_def1586); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RETURN_ATTR47_tree = (Object)adaptor.create(RETURN_ATTR47);
					adaptor.addChild(root_0, RETURN_ATTR47_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:482:53: ( data_type )
			// llvmGrammar.g:482:54: data_type
			{
			pushFollow(FOLLOW_data_type_in_function_def1589);
			data_type48=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type48.getTree());

			}

			GLOBAL_PREFIX49=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_function_def1592); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GLOBAL_PREFIX49_tree = (Object)adaptor.create(GLOBAL_PREFIX49);
			adaptor.addChild(root_0, GLOBAL_PREFIX49_tree);
			}

			e1=(Token)match(input,ID,FOLLOW_ID_in_function_def1596); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e1_tree = (Object)adaptor.create(e1);
			adaptor.addChild(root_0, e1_tree);
			}

			START_PARANTHESIS50=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_function_def1598); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			START_PARANTHESIS50_tree = (Object)adaptor.create(START_PARANTHESIS50);
			adaptor.addChild(root_0, START_PARANTHESIS50_tree);
			}

			// llvmGrammar.g:483:6: ( argument_list )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==LOCAL_PREFIX||LA15_0==PRIMITIVE_DATA_TYPE||LA15_0==START_ANGULAR||(LA15_0 >= START_PARANTHESIS && LA15_0 <= START_SQUARE_BR)) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// llvmGrammar.g:483:6: argument_list
					{
					pushFollow(FOLLOW_argument_list_in_function_def1605);
					argument_list51=argument_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, argument_list51.getTree());

					}
					break;

				default :
					break loop15;
				}
			}

			END_PARANTHESIS52=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_function_def1608); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_PARANTHESIS52_tree = (Object)adaptor.create(END_PARANTHESIS52);
			adaptor.addChild(root_0, END_PARANTHESIS52_tree);
			}

			pushFollow(FOLLOW_function_attr_list_in_function_def1615);
			function_attr_list53=function_attr_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, function_attr_list53.getTree());

			// llvmGrammar.g:484:25: ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==START_CURLY) ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// llvmGrammar.g:484:27: START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY
					{
					START_CURLY54=(Token)match(input,START_CURLY,FOLLOW_START_CURLY_in_function_def1619); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					START_CURLY54_tree = (Object)adaptor.create(START_CURLY54);
					adaptor.addChild(root_0, START_CURLY54_tree);
					}

					pushFollow(FOLLOW_basic_blocks_in_function_def1633);
					basic_blocks55=basic_blocks((LINKAGE_TYPE46!=null?LINKAGE_TYPE46.getText():null), (RETURN_ATTR47!=null?RETURN_ATTR47.getText():null), (data_type48!=null?input.toString(data_type48.start,data_type48.stop):null), (e1!=null?e1.getText():null), (function_attr_list53!=null?input.toString(function_attr_list53.start,function_attr_list53.stop):null), (e0!=null?e0.getLine():0), (e0!=null?e0.getCharPositionInLine():0));
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, basic_blocks55.getTree());

					END_CURLY56=(Token)match(input,END_CURLY,FOLLOW_END_CURLY_in_function_def1658); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_CURLY56_tree = (Object)adaptor.create(END_CURLY56);
					adaptor.addChild(root_0, END_CURLY56_tree);
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 13, function_def_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "function_def"


	public static class function_attr_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_attr_list"
	// llvmGrammar.g:489:1: function_attr_list : ( FUNCTION_ATTRIBUTE )* ;
	public final llvmGrammarParser.function_attr_list_return function_attr_list() throws RecognitionException {
		llvmGrammarParser.function_attr_list_return retval = new llvmGrammarParser.function_attr_list_return();
		retval.start = input.LT(1);
		int function_attr_list_StartIndex = input.index();

		Object root_0 = null;

		Token FUNCTION_ATTRIBUTE57=null;

		Object FUNCTION_ATTRIBUTE57_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

			// llvmGrammar.g:489:20: ( ( FUNCTION_ATTRIBUTE )* )
			// llvmGrammar.g:489:22: ( FUNCTION_ATTRIBUTE )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:489:22: ( FUNCTION_ATTRIBUTE )*
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==FUNCTION_ATTRIBUTE) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// llvmGrammar.g:489:22: FUNCTION_ATTRIBUTE
					{
					FUNCTION_ATTRIBUTE57=(Token)match(input,FUNCTION_ATTRIBUTE,FOLLOW_FUNCTION_ATTRIBUTE_in_function_attr_list1679); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FUNCTION_ATTRIBUTE57_tree = (Object)adaptor.create(FUNCTION_ATTRIBUTE57);
					adaptor.addChild(root_0, FUNCTION_ATTRIBUTE57_tree);
					}

					}
					break;

				default :
					break loop17;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 14, function_attr_list_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "function_attr_list"


	public static class argument_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "argument_list"
	// llvmGrammar.g:491:1: argument_list : (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ( argument )* ;
	public final llvmGrammarParser.argument_list_return argument_list() throws RecognitionException {
		llvmGrammarParser.argument_list_return retval = new llvmGrammarParser.argument_list_return();
		retval.start = input.LT(1);
		int argument_list_StartIndex = input.index();

		Object root_0 = null;

		Token e3=null;
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope e2 =null;
		ParserRuleReturnScope argument58 =null;

		Object e3_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

			// llvmGrammar.g:491:15: ( (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ( argument )* )
			// llvmGrammar.g:491:16: (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ( argument )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:491:16: (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? )
			// llvmGrammar.g:491:17: e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )?
			{
			pushFollow(FOLLOW_data_type_in_argument_list1690);
			e1=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());

			pushFollow(FOLLOW_result_in_argument_list1694);
			e2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());

			// llvmGrammar.g:491:42: (e3= ARGUMENT_ATTRIBUTE )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==ARGUMENT_ATTRIBUTE) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// llvmGrammar.g:491:42: e3= ARGUMENT_ATTRIBUTE
					{
					e3=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_argument_list1698); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e3_tree = (Object)adaptor.create(e3);
					adaptor.addChild(root_0, e3_tree);
					}

					}
					break;

			}

			}

			if ( state.backtracking==0 ) {						 
						   		addArgument((e1!=null?input.toString(e1.start,e1.stop):null),(e2!=null?input.toString(e2.start,e2.stop):null),(e3!=null?e3.getText():null),false);
						   }
			// llvmGrammar.g:494:9: ( argument )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==COMMA) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// llvmGrammar.g:494:9: argument
					{
					pushFollow(FOLLOW_argument_in_argument_list1711);
					argument58=argument();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, argument58.getTree());

					}
					break;

				default :
					break loop19;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 15, argument_list_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "argument_list"


	public static class argument_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "argument"
	// llvmGrammar.g:496:1: argument : ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ;
	public final llvmGrammarParser.argument_return argument() throws RecognitionException {
		llvmGrammarParser.argument_return retval = new llvmGrammarParser.argument_return();
		retval.start = input.LT(1);
		int argument_StartIndex = input.index();

		Object root_0 = null;

		Token e3=null;
		Token COMMA59=null;
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope e2 =null;

		Object e3_tree=null;
		Object COMMA59_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

			// llvmGrammar.g:496:10: ( ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) )
			// llvmGrammar.g:496:11: ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:496:11: ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? )
			// llvmGrammar.g:496:12: COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )?
			{
			COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_argument1728); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA59_tree = (Object)adaptor.create(COMMA59);
			adaptor.addChild(root_0, COMMA59_tree);
			}

			pushFollow(FOLLOW_data_type_in_argument1732);
			e1=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());

			pushFollow(FOLLOW_result_in_argument1736);
			e2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());

			// llvmGrammar.g:496:43: (e3= ARGUMENT_ATTRIBUTE )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==ARGUMENT_ATTRIBUTE) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// llvmGrammar.g:496:43: e3= ARGUMENT_ATTRIBUTE
					{
					e3=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_argument1740); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e3_tree = (Object)adaptor.create(e3);
					adaptor.addChild(root_0, e3_tree);
					}

					}
					break;

			}

			}

			if ( state.backtracking==0 ) {
					     addArgument((e1!=null?input.toString(e1.start,e1.stop):null), (e2!=null?input.toString(e2.start,e2.stop):null), (e3!=null?e3.getText():null), false);
					 }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 16, argument_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "argument"


	public static class basic_blocks_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "basic_blocks"
	// llvmGrammar.g:501:1: basic_blocks[String linkage_type,String return_attr,String data_type,String name, String function_attr, int line, int pos] : ( instruction_set )* ;
	public final llvmGrammarParser.basic_blocks_return basic_blocks(String linkage_type, String return_attr, String data_type, String name, String function_attr, int line, int pos) throws RecognitionException {
		llvmGrammarParser.basic_blocks_return retval = new llvmGrammarParser.basic_blocks_return();
		retval.start = input.LT(1);
		int basic_blocks_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope instruction_set60 =null;



					setFunction(linkage_type,return_attr,data_type,name, function_attr,true, line, pos);	
				
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

			// llvmGrammar.g:504:4: ( ( instruction_set )* )
			// llvmGrammar.g:504:6: ( instruction_set )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:504:6: ( instruction_set )*
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( ((LA21_0 >= FALSE && LA21_0 <= FLOATING_LITERAL)||LA21_0==GLOBAL_PREFIX||LA21_0==ID||LA21_0==LOCAL_PREFIX||LA21_0==NUMBER||(LA21_0 >= TRUE && LA21_0 <= UNREACHABLE)||(LA21_0 >= 63 && LA21_0 <= 64)||LA21_0==75||(LA21_0 >= 77 && LA21_0 <= 79)||LA21_0==83) ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// llvmGrammar.g:504:6: instruction_set
					{
					pushFollow(FOLLOW_instruction_set_in_basic_blocks1773);
					instruction_set60=instruction_set();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, instruction_set60.getTree());

					}
					break;

				default :
					break loop21;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 17, basic_blocks_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "basic_blocks"


	public static class instruction_set_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "instruction_set"
	// llvmGrammar.g:506:1: instruction_set : ( binary_instruction | memory_rel_instruction | terminator_instruction | other_instruction | cast_instruction );
	public final llvmGrammarParser.instruction_set_return instruction_set() throws RecognitionException {
		llvmGrammarParser.instruction_set_return retval = new llvmGrammarParser.instruction_set_return();
		retval.start = input.LT(1);
		int instruction_set_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope binary_instruction61 =null;
		ParserRuleReturnScope memory_rel_instruction62 =null;
		ParserRuleReturnScope terminator_instruction63 =null;
		ParserRuleReturnScope other_instruction64 =null;
		ParserRuleReturnScope cast_instruction65 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

			// llvmGrammar.g:506:18: ( binary_instruction | memory_rel_instruction | terminator_instruction | other_instruction | cast_instruction )
			int alt22=5;
			switch ( input.LA(1) ) {
			case GLOBAL_PREFIX:
			case LOCAL_PREFIX:
				{
				int LA22_1 = input.LA(2);
				if ( (LA22_1==FLOATING_LITERAL||LA22_1==ID||LA22_1==NUMBER) ) {
					int LA22_2 = input.LA(3);
					if ( (LA22_2==EQUAL) ) {
						switch ( input.LA(4) ) {
						case BIN_OPR_STR:
							{
							alt22=1;
							}
							break;
						case 56:
						case 69:
						case 74:
							{
							alt22=2;
							}
							break;
						case PHI:
						case 64:
						case 68:
						case 71:
						case 76:
						case 79:
							{
							alt22=4;
							}
							break;
						case CAST_TYPE:
							{
							alt22=5;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 22, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 22, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FLOATING_LITERAL:
			case ID:
			case NUMBER:
				{
				int LA22_2 = input.LA(2);
				if ( (LA22_2==EQUAL) ) {
					switch ( input.LA(3) ) {
					case BIN_OPR_STR:
						{
						alt22=1;
						}
						break;
					case 56:
					case 69:
					case 74:
						{
						alt22=2;
						}
						break;
					case PHI:
					case 64:
					case 68:
					case 71:
					case 76:
					case 79:
						{
						alt22=4;
						}
						break;
					case CAST_TYPE:
						{
						alt22=5;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 22, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FALSE:
			case TRUE:
				{
				int LA22_3 = input.LA(2);
				if ( (LA22_3==EQUAL) ) {
					switch ( input.LA(3) ) {
					case BIN_OPR_STR:
						{
						alt22=1;
						}
						break;
					case 56:
					case 69:
					case 74:
						{
						alt22=2;
						}
						break;
					case PHI:
					case 64:
					case 68:
					case 71:
					case 76:
					case 79:
						{
						alt22=4;
						}
						break;
					case CAST_TYPE:
						{
						alt22=5;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 22, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 83:
				{
				int LA22_4 = input.LA(2);
				if ( (LA22_4==EQUAL) ) {
					switch ( input.LA(3) ) {
					case BIN_OPR_STR:
						{
						alt22=1;
						}
						break;
					case 56:
					case 69:
					case 74:
						{
						alt22=2;
						}
						break;
					case PHI:
					case 64:
					case 68:
					case 71:
					case 76:
					case 79:
						{
						alt22=4;
						}
						break;
					case CAST_TYPE:
						{
						alt22=5;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 22, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 77:
				{
				alt22=2;
				}
				break;
			case UNREACHABLE:
			case 63:
			case 75:
			case 78:
				{
				alt22=3;
				}
				break;
			case 64:
			case 79:
				{
				alt22=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// llvmGrammar.g:506:20: binary_instruction
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_binary_instruction_in_instruction_set1784);
					binary_instruction61=binary_instruction();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, binary_instruction61.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:507:8: memory_rel_instruction
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_memory_rel_instruction_in_instruction_set1794);
					memory_rel_instruction62=memory_rel_instruction();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, memory_rel_instruction62.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:508:8: terminator_instruction
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_terminator_instruction_in_instruction_set1804);
					terminator_instruction63=terminator_instruction();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, terminator_instruction63.getTree());

					}
					break;
				case 4 :
					// llvmGrammar.g:509:8: other_instruction
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_other_instruction_in_instruction_set1814);
					other_instruction64=other_instruction();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, other_instruction64.getTree());

					}
					break;
				case 5 :
					// llvmGrammar.g:510:8: cast_instruction
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_cast_instruction_in_instruction_set1824);
					cast_instruction65=cast_instruction();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cast_instruction65.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 18, instruction_set_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "instruction_set"


	public static class binary_instruction_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "binary_instruction"
	// llvmGrammar.g:512:1: binary_instruction : num= result EQUAL oprstr= BIN_OPR_STR (flag= BIN_OPR_FLAG )? typ= data_type o1= result COMMA o2= result ;
	public final llvmGrammarParser.binary_instruction_return binary_instruction() throws RecognitionException {
		llvmGrammarParser.binary_instruction_return retval = new llvmGrammarParser.binary_instruction_return();
		retval.start = input.LT(1);
		int binary_instruction_StartIndex = input.index();

		Object root_0 = null;

		Token oprstr=null;
		Token flag=null;
		Token EQUAL66=null;
		Token COMMA67=null;
		ParserRuleReturnScope num =null;
		ParserRuleReturnScope typ =null;
		ParserRuleReturnScope o1 =null;
		ParserRuleReturnScope o2 =null;

		Object oprstr_tree=null;
		Object flag_tree=null;
		Object EQUAL66_tree=null;
		Object COMMA67_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

			// llvmGrammar.g:512:20: (num= result EQUAL oprstr= BIN_OPR_STR (flag= BIN_OPR_FLAG )? typ= data_type o1= result COMMA o2= result )
			// llvmGrammar.g:512:22: num= result EQUAL oprstr= BIN_OPR_STR (flag= BIN_OPR_FLAG )? typ= data_type o1= result COMMA o2= result
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_binary_instruction1835);
			num=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, num.getTree());

			EQUAL66=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_binary_instruction1837); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EQUAL66_tree = (Object)adaptor.create(EQUAL66);
			adaptor.addChild(root_0, EQUAL66_tree);
			}

			oprstr=(Token)match(input,BIN_OPR_STR,FOLLOW_BIN_OPR_STR_in_binary_instruction1841); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			oprstr_tree = (Object)adaptor.create(oprstr);
			adaptor.addChild(root_0, oprstr_tree);
			}

			// llvmGrammar.g:512:62: (flag= BIN_OPR_FLAG )?
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==BIN_OPR_FLAG) ) {
				alt23=1;
			}
			switch (alt23) {
				case 1 :
					// llvmGrammar.g:512:62: flag= BIN_OPR_FLAG
					{
					flag=(Token)match(input,BIN_OPR_FLAG,FOLLOW_BIN_OPR_FLAG_in_binary_instruction1845); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					flag_tree = (Object)adaptor.create(flag);
					adaptor.addChild(root_0, flag_tree);
					}

					}
					break;

			}

			pushFollow(FOLLOW_data_type_in_binary_instruction1850);
			typ=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typ.getTree());

			pushFollow(FOLLOW_result_in_binary_instruction1854);
			o1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, o1.getTree());

			COMMA67=(Token)match(input,COMMA,FOLLOW_COMMA_in_binary_instruction1856); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA67_tree = (Object)adaptor.create(COMMA67);
			adaptor.addChild(root_0, COMMA67_tree);
			}

			pushFollow(FOLLOW_result_in_binary_instruction1860);
			o2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, o2.getTree());

			if ( state.backtracking==0 ) {				
							   		setBinaryOperator((num!=null?input.toString(num.start,num.stop):null),(typ!=null?input.toString(typ.start,typ.stop):null),(o1!=null?input.toString(o1.start,o1.stop):null),(o2!=null?input.toString(o2.start,o2.stop):null),(oprstr!=null?oprstr.getText():null), (flag!=null?flag.getText():null), (oprstr!=null?oprstr.getLine():0), (oprstr!=null?oprstr.getCharPositionInLine():0));	
							   }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 19, binary_instruction_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "binary_instruction"


	public static class memory_rel_instruction_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "memory_rel_instruction"
	// llvmGrammar.g:517:1: memory_rel_instruction : ( load | alloca | store | getelementptr );
	public final llvmGrammarParser.memory_rel_instruction_return memory_rel_instruction() throws RecognitionException {
		llvmGrammarParser.memory_rel_instruction_return retval = new llvmGrammarParser.memory_rel_instruction_return();
		retval.start = input.LT(1);
		int memory_rel_instruction_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope load68 =null;
		ParserRuleReturnScope alloca69 =null;
		ParserRuleReturnScope store70 =null;
		ParserRuleReturnScope getelementptr71 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

			// llvmGrammar.g:517:24: ( load | alloca | store | getelementptr )
			int alt24=4;
			switch ( input.LA(1) ) {
			case GLOBAL_PREFIX:
			case LOCAL_PREFIX:
				{
				int LA24_1 = input.LA(2);
				if ( (LA24_1==FLOATING_LITERAL||LA24_1==ID||LA24_1==NUMBER) ) {
					int LA24_2 = input.LA(3);
					if ( (LA24_2==EQUAL) ) {
						switch ( input.LA(4) ) {
						case 74:
							{
							alt24=1;
							}
							break;
						case 56:
							{
							alt24=2;
							}
							break;
						case 69:
							{
							alt24=4;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 24, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FLOATING_LITERAL:
			case ID:
			case NUMBER:
				{
				int LA24_2 = input.LA(2);
				if ( (LA24_2==EQUAL) ) {
					switch ( input.LA(3) ) {
					case 74:
						{
						alt24=1;
						}
						break;
					case 56:
						{
						alt24=2;
						}
						break;
					case 69:
						{
						alt24=4;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FALSE:
			case TRUE:
				{
				int LA24_3 = input.LA(2);
				if ( (LA24_3==EQUAL) ) {
					switch ( input.LA(3) ) {
					case 74:
						{
						alt24=1;
						}
						break;
					case 56:
						{
						alt24=2;
						}
						break;
					case 69:
						{
						alt24=4;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 83:
				{
				int LA24_4 = input.LA(2);
				if ( (LA24_4==EQUAL) ) {
					switch ( input.LA(3) ) {
					case 74:
						{
						alt24=1;
						}
						break;
					case 56:
						{
						alt24=2;
						}
						break;
					case 69:
						{
						alt24=4;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 77:
				{
				alt24=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}
			switch (alt24) {
				case 1 :
					// llvmGrammar.g:517:27: load
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_load_in_memory_rel_instruction1889);
					load68=load();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, load68.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:517:34: alloca
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_alloca_in_memory_rel_instruction1893);
					alloca69=alloca();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, alloca69.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:517:43: store
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_store_in_memory_rel_instruction1897);
					store70=store();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, store70.getTree());

					}
					break;
				case 4 :
					// llvmGrammar.g:517:51: getelementptr
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_getelementptr_in_memory_rel_instruction1901);
					getelementptr71=getelementptr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr71.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 20, memory_rel_instruction_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "memory_rel_instruction"


	public static class alloca_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "alloca"
	// llvmGrammar.g:519:1: alloca : res= result e= EQUAL ' alloca ' d= data_type ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )? ;
	public final llvmGrammarParser.alloca_return alloca() throws RecognitionException {
		llvmGrammarParser.alloca_return retval = new llvmGrammarParser.alloca_return();
		retval.start = input.LT(1);
		int alloca_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token alignV=null;
		Token string_literal72=null;
		Token COMMA73=null;
		Token COMMA75=null;
		Token ALIGN76=null;
		ParserRuleReturnScope res =null;
		ParserRuleReturnScope d =null;
		ParserRuleReturnScope arrayLength =null;
		ParserRuleReturnScope data_type74 =null;

		Object e_tree=null;
		Object alignV_tree=null;
		Object string_literal72_tree=null;
		Object COMMA73_tree=null;
		Object COMMA75_tree=null;
		Object ALIGN76_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

			// llvmGrammar.g:519:8: (res= result e= EQUAL ' alloca ' d= data_type ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )? )
			// llvmGrammar.g:519:10: res= result e= EQUAL ' alloca ' d= data_type ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_alloca1916);
			res=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, res.getTree());

			e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_alloca1920); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			string_literal72=(Token)match(input,56,FOLLOW_56_in_alloca1922); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal72_tree = (Object)adaptor.create(string_literal72);
			adaptor.addChild(root_0, string_literal72_tree);
			}

			pushFollow(FOLLOW_data_type_in_alloca1926);
			d=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

			// llvmGrammar.g:519:53: ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==COMMA) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// llvmGrammar.g:519:54: COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER
					{
					COMMA73=(Token)match(input,COMMA,FOLLOW_COMMA_in_alloca1930); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA73_tree = (Object)adaptor.create(COMMA73);
					adaptor.addChild(root_0, COMMA73_tree);
					}

					// llvmGrammar.g:519:60: ( data_type arrayLength= result COMMA )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==LOCAL_PREFIX||LA25_0==PRIMITIVE_DATA_TYPE||LA25_0==START_ANGULAR||(LA25_0 >= START_PARANTHESIS && LA25_0 <= START_SQUARE_BR)) ) {
						alt25=1;
					}
					switch (alt25) {
						case 1 :
							// llvmGrammar.g:519:61: data_type arrayLength= result COMMA
							{
							pushFollow(FOLLOW_data_type_in_alloca1933);
							data_type74=data_type();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type74.getTree());

							pushFollow(FOLLOW_result_in_alloca1937);
							arrayLength=result();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayLength.getTree());

							COMMA75=(Token)match(input,COMMA,FOLLOW_COMMA_in_alloca1939); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA75_tree = (Object)adaptor.create(COMMA75);
							adaptor.addChild(root_0, COMMA75_tree);
							}

							}
							break;

					}

					ALIGN76=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_alloca1943); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALIGN76_tree = (Object)adaptor.create(ALIGN76);
					adaptor.addChild(root_0, ALIGN76_tree);
					}

					alignV=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_alloca1947); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					alignV_tree = (Object)adaptor.create(alignV);
					adaptor.addChild(root_0, alignV_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {									
				   		setAllocaInstr((res!=null?input.toString(res.start,res.stop):null),(d!=null?input.toString(d.start,d.stop):null),(arrayLength!=null?input.toString(arrayLength.start,arrayLength.stop):null), (alignV!=null?alignV.getText():null), (e!=null?e.getLine():0), 0);					           					
				   }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 21, alloca_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "alloca"


	public static class load_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "load"
	// llvmGrammar.g:524:1: load : res= result e0= EQUAL 'load' pointee= data_type COMMA ptr= data_type loc= result ( COMMA ALIGN NUMBER )? ;
	public final llvmGrammarParser.load_return load() throws RecognitionException {
		llvmGrammarParser.load_return retval = new llvmGrammarParser.load_return();
		retval.start = input.LT(1);
		int load_StartIndex = input.index();

		Object root_0 = null;

		Token e0=null;
		Token string_literal77=null;
		Token COMMA78=null;
		Token COMMA79=null;
		Token ALIGN80=null;
		Token NUMBER81=null;
		ParserRuleReturnScope res =null;
		ParserRuleReturnScope pointee =null;
		ParserRuleReturnScope ptr =null;
		ParserRuleReturnScope loc =null;

		Object e0_tree=null;
		Object string_literal77_tree=null;
		Object COMMA78_tree=null;
		Object COMMA79_tree=null;
		Object ALIGN80_tree=null;
		Object NUMBER81_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

			// llvmGrammar.g:524:6: (res= result e0= EQUAL 'load' pointee= data_type COMMA ptr= data_type loc= result ( COMMA ALIGN NUMBER )? )
			// llvmGrammar.g:524:8: res= result e0= EQUAL 'load' pointee= data_type COMMA ptr= data_type loc= result ( COMMA ALIGN NUMBER )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_load1971);
			res=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, res.getTree());

			e0=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_load1975); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e0_tree = (Object)adaptor.create(e0);
			adaptor.addChild(root_0, e0_tree);
			}

			string_literal77=(Token)match(input,74,FOLLOW_74_in_load1977); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal77_tree = (Object)adaptor.create(string_literal77);
			adaptor.addChild(root_0, string_literal77_tree);
			}

			pushFollow(FOLLOW_data_type_in_load1981);
			pointee=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, pointee.getTree());

			COMMA78=(Token)match(input,COMMA,FOLLOW_COMMA_in_load1983); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA78_tree = (Object)adaptor.create(COMMA78);
			adaptor.addChild(root_0, COMMA78_tree);
			}

			pushFollow(FOLLOW_data_type_in_load1987);
			ptr=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr.getTree());

			pushFollow(FOLLOW_result_in_load1991);
			loc=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, loc.getTree());

			// llvmGrammar.g:524:84: ( COMMA ALIGN NUMBER )?
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==COMMA) ) {
				alt27=1;
			}
			switch (alt27) {
				case 1 :
					// llvmGrammar.g:524:85: COMMA ALIGN NUMBER
					{
					COMMA79=(Token)match(input,COMMA,FOLLOW_COMMA_in_load1994); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA79_tree = (Object)adaptor.create(COMMA79);
					adaptor.addChild(root_0, COMMA79_tree);
					}

					ALIGN80=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_load1996); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALIGN80_tree = (Object)adaptor.create(ALIGN80);
					adaptor.addChild(root_0, ALIGN80_tree);
					}

					NUMBER81=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_load1998); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER81_tree = (Object)adaptor.create(NUMBER81);
					adaptor.addChild(root_0, NUMBER81_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {							
				 	setLoadInstr((pointee!=null?input.toString(pointee.start,pointee.stop):null), (res!=null?input.toString(res.start,res.stop):null),(loc!=null?input.toString(loc.start,loc.stop):null),(ptr!=null?input.toString(ptr.start,ptr.stop):null), (e0!=null?e0.getLine():0), 0);
				 }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 22, load_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "load"


	public static class store_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "store"
	// llvmGrammar.g:529:1: store : a= 'store' (atomic= ATOMIC_ORDERING )? (isVolatile= 'volatile' )? a3= data_type a1= result COMMA data_type a2= result ( COMMA ALIGN NUMBER )* ;
	public final llvmGrammarParser.store_return store() throws RecognitionException {
		llvmGrammarParser.store_return retval = new llvmGrammarParser.store_return();
		retval.start = input.LT(1);
		int store_StartIndex = input.index();

		Object root_0 = null;

		Token a=null;
		Token atomic=null;
		Token isVolatile=null;
		Token COMMA82=null;
		Token COMMA84=null;
		Token ALIGN85=null;
		Token NUMBER86=null;
		ParserRuleReturnScope a3 =null;
		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;
		ParserRuleReturnScope data_type83 =null;

		Object a_tree=null;
		Object atomic_tree=null;
		Object isVolatile_tree=null;
		Object COMMA82_tree=null;
		Object COMMA84_tree=null;
		Object ALIGN85_tree=null;
		Object NUMBER86_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

			// llvmGrammar.g:529:7: (a= 'store' (atomic= ATOMIC_ORDERING )? (isVolatile= 'volatile' )? a3= data_type a1= result COMMA data_type a2= result ( COMMA ALIGN NUMBER )* )
			// llvmGrammar.g:529:9: a= 'store' (atomic= ATOMIC_ORDERING )? (isVolatile= 'volatile' )? a3= data_type a1= result COMMA data_type a2= result ( COMMA ALIGN NUMBER )*
			{
			root_0 = (Object)adaptor.nil();


			a=(Token)match(input,77,FOLLOW_77_in_store2018); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			a_tree = (Object)adaptor.create(a);
			adaptor.addChild(root_0, a_tree);
			}

			// llvmGrammar.g:529:25: (atomic= ATOMIC_ORDERING )?
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==ATOMIC_ORDERING) ) {
				alt28=1;
			}
			switch (alt28) {
				case 1 :
					// llvmGrammar.g:529:25: atomic= ATOMIC_ORDERING
					{
					atomic=(Token)match(input,ATOMIC_ORDERING,FOLLOW_ATOMIC_ORDERING_in_store2022); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					atomic_tree = (Object)adaptor.create(atomic);
					adaptor.addChild(root_0, atomic_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:529:53: (isVolatile= 'volatile' )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==85) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// llvmGrammar.g:529:53: isVolatile= 'volatile'
					{
					isVolatile=(Token)match(input,85,FOLLOW_85_in_store2027); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					isVolatile_tree = (Object)adaptor.create(isVolatile);
					adaptor.addChild(root_0, isVolatile_tree);
					}

					}
					break;

			}

			pushFollow(FOLLOW_data_type_in_store2032);
			a3=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, a3.getTree());

			pushFollow(FOLLOW_result_in_store2036);
			a1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, a1.getTree());

			COMMA82=(Token)match(input,COMMA,FOLLOW_COMMA_in_store2038); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA82_tree = (Object)adaptor.create(COMMA82);
			adaptor.addChild(root_0, COMMA82_tree);
			}

			pushFollow(FOLLOW_data_type_in_store2042);
			data_type83=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type83.getTree());

			pushFollow(FOLLOW_result_in_store2047);
			a2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, a2.getTree());

			// llvmGrammar.g:530:25: ( COMMA ALIGN NUMBER )*
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==COMMA) ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// llvmGrammar.g:530:26: COMMA ALIGN NUMBER
					{
					COMMA84=(Token)match(input,COMMA,FOLLOW_COMMA_in_store2051); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA84_tree = (Object)adaptor.create(COMMA84);
					adaptor.addChild(root_0, COMMA84_tree);
					}

					ALIGN85=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_store2053); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALIGN85_tree = (Object)adaptor.create(ALIGN85);
					adaptor.addChild(root_0, ALIGN85_tree);
					}

					NUMBER86=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_store2055); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER86_tree = (Object)adaptor.create(NUMBER86);
					adaptor.addChild(root_0, NUMBER86_tree);
					}

					}
					break;

				default :
					break loop30;
				}
			}

			if ( state.backtracking==0 ) {
				  		setStoreInstr((a1!=null?input.toString(a1.start,a1.stop):null), (a2!=null?input.toString(a2.start,a2.stop):null), (a3!=null?input.toString(a3.start,a3.stop):null), (atomic!=null?atomic.getText():null), (isVolatile!=null?isVolatile.getText():null), (a!=null?a.getLine():0), (a!=null?a.getCharPositionInLine():0));
				  }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 23, store_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "store"


	public static class getelementptr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "getelementptr"
	// llvmGrammar.g:535:1: getelementptr : id= result e= EQUAL getelementptr_rhs[$id.text, $e.line] ;
	public final llvmGrammarParser.getelementptr_return getelementptr() throws RecognitionException {
		llvmGrammarParser.getelementptr_return retval = new llvmGrammarParser.getelementptr_return();
		retval.start = input.LT(1);
		int getelementptr_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		ParserRuleReturnScope id =null;
		ParserRuleReturnScope getelementptr_rhs87 =null;

		Object e_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

			// llvmGrammar.g:535:15: (id= result e= EQUAL getelementptr_rhs[$id.text, $e.line] )
			// llvmGrammar.g:535:17: id= result e= EQUAL getelementptr_rhs[$id.text, $e.line]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_getelementptr2075);
			id=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, id.getTree());

			e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_getelementptr2079); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			pushFollow(FOLLOW_getelementptr_rhs_in_getelementptr2081);
			getelementptr_rhs87=getelementptr_rhs((id!=null?input.toString(id.start,id.stop):null), (e!=null?e.getLine():0));
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs87.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 24, getelementptr_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "getelementptr"


	public static class getelementptr_rhs_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "getelementptr_rhs"
	// llvmGrammar.g:537:1: getelementptr_rhs[String name, int line] : 'getelementptr' (inb= 'inbounds' )? ( START_PARANTHESIS )? pointee= data_type COMMA ptr= ptr_type element= element_name COMMA off= offset ( END_PARANTHESIS )? ;
	public final llvmGrammarParser.getelementptr_rhs_return getelementptr_rhs(String name, int line) throws RecognitionException {
		llvmGrammarParser.getelementptr_rhs_return retval = new llvmGrammarParser.getelementptr_rhs_return();
		retval.start = input.LT(1);
		int getelementptr_rhs_StartIndex = input.index();

		Object root_0 = null;

		Token inb=null;
		Token string_literal88=null;
		Token START_PARANTHESIS89=null;
		Token COMMA90=null;
		Token COMMA91=null;
		Token END_PARANTHESIS92=null;
		ParserRuleReturnScope pointee =null;
		ParserRuleReturnScope ptr =null;
		ParserRuleReturnScope element =null;
		ParserRuleReturnScope off =null;

		Object inb_tree=null;
		Object string_literal88_tree=null;
		Object START_PARANTHESIS89_tree=null;
		Object COMMA90_tree=null;
		Object COMMA91_tree=null;
		Object END_PARANTHESIS92_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }

			// llvmGrammar.g:537:42: ( 'getelementptr' (inb= 'inbounds' )? ( START_PARANTHESIS )? pointee= data_type COMMA ptr= ptr_type element= element_name COMMA off= offset ( END_PARANTHESIS )? )
			// llvmGrammar.g:537:44: 'getelementptr' (inb= 'inbounds' )? ( START_PARANTHESIS )? pointee= data_type COMMA ptr= ptr_type element= element_name COMMA off= offset ( END_PARANTHESIS )?
			{
			root_0 = (Object)adaptor.nil();


			string_literal88=(Token)match(input,69,FOLLOW_69_in_getelementptr_rhs2094); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal88_tree = (Object)adaptor.create(string_literal88);
			adaptor.addChild(root_0, string_literal88_tree);
			}

			// llvmGrammar.g:537:62: (inb= 'inbounds' )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==72) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// llvmGrammar.g:537:62: inb= 'inbounds'
					{
					inb=(Token)match(input,72,FOLLOW_72_in_getelementptr_rhs2097); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					inb_tree = (Object)adaptor.create(inb);
					adaptor.addChild(root_0, inb_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:537:75: ( START_PARANTHESIS )?
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==START_PARANTHESIS) ) {
				int LA32_1 = input.LA(2);
				if ( (synpred40_llvmGrammar()) ) {
					alt32=1;
				}
			}
			switch (alt32) {
				case 1 :
					// llvmGrammar.g:537:75: START_PARANTHESIS
					{
					START_PARANTHESIS89=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_getelementptr_rhs2100); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					START_PARANTHESIS89_tree = (Object)adaptor.create(START_PARANTHESIS89);
					adaptor.addChild(root_0, START_PARANTHESIS89_tree);
					}

					}
					break;

			}

			pushFollow(FOLLOW_data_type_in_getelementptr_rhs2105);
			pointee=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, pointee.getTree());

			COMMA90=(Token)match(input,COMMA,FOLLOW_COMMA_in_getelementptr_rhs2107); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA90_tree = (Object)adaptor.create(COMMA90);
			adaptor.addChild(root_0, COMMA90_tree);
			}

			pushFollow(FOLLOW_ptr_type_in_getelementptr_rhs2113);
			ptr=ptr_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr.getTree());

			pushFollow(FOLLOW_element_name_in_getelementptr_rhs2129);
			element=element_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, element.getTree());

			COMMA91=(Token)match(input,COMMA,FOLLOW_COMMA_in_getelementptr_rhs2131); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA91_tree = (Object)adaptor.create(COMMA91);
			adaptor.addChild(root_0, COMMA91_tree);
			}

			pushFollow(FOLLOW_offset_in_getelementptr_rhs2136);
			off=offset();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, off.getTree());

			// llvmGrammar.g:538:51: ( END_PARANTHESIS )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==END_PARANTHESIS) ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// llvmGrammar.g:538:51: END_PARANTHESIS
					{
					END_PARANTHESIS92=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_getelementptr_rhs2138); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_PARANTHESIS92_tree = (Object)adaptor.create(END_PARANTHESIS92);
					adaptor.addChild(root_0, END_PARANTHESIS92_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {	    		
														setGetElementPtr((pointee!=null?input.toString(pointee.start,pointee.stop):null), name,(element!=null?input.toString(element.start,element.stop):null),(ptr!=null?input.toString(ptr.start,ptr.stop):null), (off!=null?input.toString(off.start,off.stop):null),(inb!=null?inb.getText():null), line, 0);
							    					 }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 25, getelementptr_rhs_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "getelementptr_rhs"


	public static class element_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "element_name"
	// llvmGrammar.g:543:1: element_name : ( ( LOCAL_PREFIX ( ID | NUMBER )+ ) | ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type ) | 'undef' );
	public final llvmGrammarParser.element_name_return element_name() throws RecognitionException {
		llvmGrammarParser.element_name_return retval = new llvmGrammarParser.element_name_return();
		retval.start = input.LT(1);
		int element_name_StartIndex = input.index();

		Object root_0 = null;

		Token LOCAL_PREFIX93=null;
		Token set94=null;
		Token GLOBAL_PREFIX95=null;
		Token char_literal96=null;
		Token set97=null;
		Token string_literal99=null;
		ParserRuleReturnScope data_type98 =null;

		Object LOCAL_PREFIX93_tree=null;
		Object set94_tree=null;
		Object GLOBAL_PREFIX95_tree=null;
		Object char_literal96_tree=null;
		Object set97_tree=null;
		Object string_literal99_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }

			// llvmGrammar.g:543:13: ( ( LOCAL_PREFIX ( ID | NUMBER )+ ) | ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type ) | 'undef' )
			int alt38=3;
			switch ( input.LA(1) ) {
			case LOCAL_PREFIX:
				{
				int LA38_1 = input.LA(2);
				if ( (LA38_1==ID) ) {
					int LA38_4 = input.LA(3);
					if ( (synpred44_llvmGrammar()) ) {
						alt38=1;
					}
					else if ( (synpred49_llvmGrammar()) ) {
						alt38=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 38, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA38_1==NUMBER) ) {
					alt38=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 38, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case GLOBAL_PREFIX:
			case PRIMITIVE_DATA_TYPE:
			case START_ANGULAR:
			case START_PARANTHESIS:
			case START_SQUARE_BR:
				{
				alt38=2;
				}
				break;
			case 83:
				{
				alt38=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}
			switch (alt38) {
				case 1 :
					// llvmGrammar.g:543:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:543:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
					// llvmGrammar.g:543:17: LOCAL_PREFIX ( ID | NUMBER )+
					{
					LOCAL_PREFIX93=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_element_name2173); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX93_tree = (Object)adaptor.create(LOCAL_PREFIX93);
					adaptor.addChild(root_0, LOCAL_PREFIX93_tree);
					}

					// llvmGrammar.g:543:30: ( ID | NUMBER )+
					int cnt34=0;
					loop34:
					while (true) {
						int alt34=2;
						int LA34_0 = input.LA(1);
						if ( (LA34_0==ID||LA34_0==NUMBER) ) {
							alt34=1;
						}

						switch (alt34) {
						case 1 :
							// llvmGrammar.g:
							{
							set94=input.LT(1);
							if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
								input.consume();
								if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set94));
								state.errorRecovery=false;
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt34 >= 1 ) break loop34;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(34, input);
							throw eee;
						}
						cnt34++;
					}

					}

					}
					break;
				case 2 :
					// llvmGrammar.g:543:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:543:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==GLOBAL_PREFIX) ) {
						alt37=1;
					}
					else if ( (LA37_0==LOCAL_PREFIX||LA37_0==PRIMITIVE_DATA_TYPE||LA37_0==START_ANGULAR||(LA37_0 >= START_PARANTHESIS && LA37_0 <= START_SQUARE_BR)) ) {
						alt37=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 37, 0, input);
						throw nvae;
					}

					switch (alt37) {
						case 1 :
							// llvmGrammar.g:543:48: GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+
							{
							GLOBAL_PREFIX95=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_element_name2187); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							GLOBAL_PREFIX95_tree = (Object)adaptor.create(GLOBAL_PREFIX95);
							adaptor.addChild(root_0, GLOBAL_PREFIX95_tree);
							}

							// llvmGrammar.g:543:62: ( '.' )?
							int alt35=2;
							int LA35_0 = input.LA(1);
							if ( (LA35_0==DOT) ) {
								alt35=1;
							}
							switch (alt35) {
								case 1 :
									// llvmGrammar.g:543:62: '.'
									{
									char_literal96=(Token)match(input,DOT,FOLLOW_DOT_in_element_name2189); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									char_literal96_tree = (Object)adaptor.create(char_literal96);
									adaptor.addChild(root_0, char_literal96_tree);
									}

									}
									break;

							}

							// llvmGrammar.g:543:66: ( ID | NUMBER )+
							int cnt36=0;
							loop36:
							while (true) {
								int alt36=2;
								int LA36_0 = input.LA(1);
								if ( (LA36_0==ID||LA36_0==NUMBER) ) {
									alt36=1;
								}

								switch (alt36) {
								case 1 :
									// llvmGrammar.g:
									{
									set97=input.LT(1);
									if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
										input.consume();
										if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set97));
										state.errorRecovery=false;
										state.failed=false;
									}
									else {
										if (state.backtracking>0) {state.failed=true; return retval;}
										MismatchedSetException mse = new MismatchedSetException(null,input);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt36 >= 1 ) break loop36;
									if (state.backtracking>0) {state.failed=true; return retval;}
									EarlyExitException eee = new EarlyExitException(36, input);
									throw eee;
								}
								cnt36++;
							}

							}
							break;
						case 2 :
							// llvmGrammar.g:543:82: data_type
							{
							pushFollow(FOLLOW_data_type_in_element_name2201);
							data_type98=data_type();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type98.getTree());

							}
							break;

					}

					}
					break;
				case 3 :
					// llvmGrammar.g:543:94: 'undef'
					{
					root_0 = (Object)adaptor.nil();


					string_literal99=(Token)match(input,83,FOLLOW_83_in_element_name2205); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal99_tree = (Object)adaptor.create(string_literal99);
					adaptor.addChild(root_0, string_literal99_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 26, element_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "element_name"


	public static class offset_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "offset"
	// llvmGrammar.g:545:1: offset : ( GLOBAL_PREFIX ID COMMA )? ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) ) ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )* ;
	public final llvmGrammarParser.offset_return offset() throws RecognitionException {
		llvmGrammarParser.offset_return retval = new llvmGrammarParser.offset_return();
		retval.start = input.LT(1);
		int offset_StartIndex = input.index();

		Object root_0 = null;

		Token GLOBAL_PREFIX100=null;
		Token ID101=null;
		Token COMMA102=null;
		Token LOCAL_PREFIX104=null;
		Token set105=null;
		Token COMMA106=null;
		Token LOCAL_PREFIX108=null;
		Token set109=null;
		ParserRuleReturnScope data_type103 =null;
		ParserRuleReturnScope data_type107 =null;

		Object GLOBAL_PREFIX100_tree=null;
		Object ID101_tree=null;
		Object COMMA102_tree=null;
		Object LOCAL_PREFIX104_tree=null;
		Object set105_tree=null;
		Object COMMA106_tree=null;
		Object LOCAL_PREFIX108_tree=null;
		Object set109_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }

			// llvmGrammar.g:545:8: ( ( GLOBAL_PREFIX ID COMMA )? ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) ) ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )* )
			// llvmGrammar.g:545:10: ( GLOBAL_PREFIX ID COMMA )? ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) ) ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:545:10: ( GLOBAL_PREFIX ID COMMA )?
			int alt39=2;
			int LA39_0 = input.LA(1);
			if ( (LA39_0==GLOBAL_PREFIX) ) {
				alt39=1;
			}
			switch (alt39) {
				case 1 :
					// llvmGrammar.g:545:11: GLOBAL_PREFIX ID COMMA
					{
					GLOBAL_PREFIX100=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_offset2217); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GLOBAL_PREFIX100_tree = (Object)adaptor.create(GLOBAL_PREFIX100);
					adaptor.addChild(root_0, GLOBAL_PREFIX100_tree);
					}

					ID101=(Token)match(input,ID,FOLLOW_ID_in_offset2219); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID101_tree = (Object)adaptor.create(ID101);
					adaptor.addChild(root_0, ID101_tree);
					}

					COMMA102=(Token)match(input,COMMA,FOLLOW_COMMA_in_offset2221); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA102_tree = (Object)adaptor.create(COMMA102);
					adaptor.addChild(root_0, COMMA102_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:545:36: ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )
			// llvmGrammar.g:545:38: data_type ( LOCAL_PREFIX )? ( NUMBER | ID )
			{
			pushFollow(FOLLOW_data_type_in_offset2227);
			data_type103=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type103.getTree());

			// llvmGrammar.g:545:48: ( LOCAL_PREFIX )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==LOCAL_PREFIX) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// llvmGrammar.g:545:48: LOCAL_PREFIX
					{
					LOCAL_PREFIX104=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_offset2229); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX104_tree = (Object)adaptor.create(LOCAL_PREFIX104);
					adaptor.addChild(root_0, LOCAL_PREFIX104_tree);
					}

					}
					break;

			}

			set105=input.LT(1);
			if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set105));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			// llvmGrammar.g:545:76: ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )*
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0==COMMA) ) {
					int LA42_15 = input.LA(2);
					if ( (synpred55_llvmGrammar()) ) {
						alt42=1;
					}

				}

				switch (alt42) {
				case 1 :
					// llvmGrammar.g:545:77: COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID )
					{
					COMMA106=(Token)match(input,COMMA,FOLLOW_COMMA_in_offset2241); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA106_tree = (Object)adaptor.create(COMMA106);
					adaptor.addChild(root_0, COMMA106_tree);
					}

					pushFollow(FOLLOW_data_type_in_offset2243);
					data_type107=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type107.getTree());

					// llvmGrammar.g:545:93: ( LOCAL_PREFIX )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==LOCAL_PREFIX) ) {
						alt41=1;
					}
					switch (alt41) {
						case 1 :
							// llvmGrammar.g:545:93: LOCAL_PREFIX
							{
							LOCAL_PREFIX108=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_offset2245); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							LOCAL_PREFIX108_tree = (Object)adaptor.create(LOCAL_PREFIX108);
							adaptor.addChild(root_0, LOCAL_PREFIX108_tree);
							}

							}
							break;

					}

					set109=input.LT(1);
					if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
						input.consume();
						if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set109));
						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

				default :
					break loop42;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 27, offset_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "offset"


	public static class terminator_instruction_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "terminator_instruction"
	// llvmGrammar.g:547:1: terminator_instruction : ( ret | br | switchInstr | unreachable );
	public final llvmGrammarParser.terminator_instruction_return terminator_instruction() throws RecognitionException {
		llvmGrammarParser.terminator_instruction_return retval = new llvmGrammarParser.terminator_instruction_return();
		retval.start = input.LT(1);
		int terminator_instruction_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope ret110 =null;
		ParserRuleReturnScope br111 =null;
		ParserRuleReturnScope switchInstr112 =null;
		ParserRuleReturnScope unreachable113 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }

			// llvmGrammar.g:547:24: ( ret | br | switchInstr | unreachable )
			int alt43=4;
			switch ( input.LA(1) ) {
			case 75:
				{
				alt43=1;
				}
				break;
			case 63:
				{
				alt43=2;
				}
				break;
			case 78:
				{
				alt43=3;
				}
				break;
			case UNREACHABLE:
				{
				alt43=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}
			switch (alt43) {
				case 1 :
					// llvmGrammar.g:547:26: ret
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_ret_in_terminator_instruction2265);
					ret110=ret();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, ret110.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:547:32: br
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_br_in_terminator_instruction2269);
					br111=br();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, br111.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:547:37: switchInstr
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_switchInstr_in_terminator_instruction2273);
					switchInstr112=switchInstr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, switchInstr112.getTree());

					}
					break;
				case 4 :
					// llvmGrammar.g:547:50: unreachable
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_unreachable_in_terminator_instruction2276);
					unreachable113=unreachable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, unreachable113.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 28, terminator_instruction_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "terminator_instruction"


	public static class ret_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ret"
	// llvmGrammar.g:549:1: ret : r= 'ret' d= data_type (e1= result )? ;
	public final llvmGrammarParser.ret_return ret() throws RecognitionException {
		llvmGrammarParser.ret_return retval = new llvmGrammarParser.ret_return();
		retval.start = input.LT(1);
		int ret_StartIndex = input.index();

		Object root_0 = null;

		Token r=null;
		ParserRuleReturnScope d =null;
		ParserRuleReturnScope e1 =null;

		Object r_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }

			// llvmGrammar.g:549:5: (r= 'ret' d= data_type (e1= result )? )
			// llvmGrammar.g:549:8: r= 'ret' d= data_type (e1= result )?
			{
			root_0 = (Object)adaptor.nil();


			r=(Token)match(input,75,FOLLOW_75_in_ret2290); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			r_tree = (Object)adaptor.create(r);
			adaptor.addChild(root_0, r_tree);
			}

			pushFollow(FOLLOW_data_type_in_ret2294);
			d=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

			// llvmGrammar.g:549:28: (e1= result )?
			int alt44=2;
			switch ( input.LA(1) ) {
				case GLOBAL_PREFIX:
				case LOCAL_PREFIX:
					{
					int LA44_1 = input.LA(2);
					if ( (LA44_1==FLOATING_LITERAL||LA44_1==ID||LA44_1==NUMBER) ) {
						int LA44_2 = input.LA(3);
						if ( (LA44_2==EOF||LA44_2==END_CURLY||(LA44_2 >= FALSE && LA44_2 <= FLOATING_LITERAL)||LA44_2==GLOBAL_PREFIX||LA44_2==ID||LA44_2==LOCAL_PREFIX||LA44_2==NUMBER||(LA44_2 >= TRUE && LA44_2 <= UNREACHABLE)||(LA44_2 >= 63 && LA44_2 <= 64)||LA44_2==75||(LA44_2 >= 77 && LA44_2 <= 79)||LA44_2==83) ) {
							alt44=1;
						}
					}
					}
					break;
				case FLOATING_LITERAL:
				case ID:
				case NUMBER:
					{
					int LA44_2 = input.LA(2);
					if ( (LA44_2==EOF||LA44_2==END_CURLY||(LA44_2 >= FALSE && LA44_2 <= FLOATING_LITERAL)||LA44_2==GLOBAL_PREFIX||LA44_2==ID||LA44_2==LOCAL_PREFIX||LA44_2==NUMBER||(LA44_2 >= TRUE && LA44_2 <= UNREACHABLE)||(LA44_2 >= 63 && LA44_2 <= 64)||LA44_2==75||(LA44_2 >= 77 && LA44_2 <= 79)||LA44_2==83) ) {
						alt44=1;
					}
					}
					break;
				case FALSE:
				case TRUE:
					{
					int LA44_3 = input.LA(2);
					if ( (LA44_3==EOF||LA44_3==END_CURLY||(LA44_3 >= FALSE && LA44_3 <= FLOATING_LITERAL)||LA44_3==GLOBAL_PREFIX||LA44_3==ID||LA44_3==LOCAL_PREFIX||LA44_3==NUMBER||(LA44_3 >= TRUE && LA44_3 <= UNREACHABLE)||(LA44_3 >= 63 && LA44_3 <= 64)||LA44_3==75||(LA44_3 >= 77 && LA44_3 <= 79)||LA44_3==83) ) {
						alt44=1;
					}
					}
					break;
				case 83:
					{
					int LA44_4 = input.LA(2);
					if ( (LA44_4==EOF||LA44_4==END_CURLY||(LA44_4 >= FALSE && LA44_4 <= FLOATING_LITERAL)||LA44_4==GLOBAL_PREFIX||LA44_4==ID||LA44_4==LOCAL_PREFIX||LA44_4==NUMBER||(LA44_4 >= TRUE && LA44_4 <= UNREACHABLE)||(LA44_4 >= 63 && LA44_4 <= 64)||LA44_4==75||(LA44_4 >= 77 && LA44_4 <= 79)||LA44_4==83) ) {
						alt44=1;
					}
					}
					break;
			}
			switch (alt44) {
				case 1 :
					// llvmGrammar.g:549:29: e1= result
					{
					pushFollow(FOLLOW_result_in_ret2299);
					e1=result();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			  		setRetInstr((d!=null?input.toString(d.start,d.stop):null), (e1!=null?input.toString(e1.start,e1.stop):null), (r!=null?r.getLine():0), (r!=null?r.getCharPositionInLine():0));
			   	}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 29, ret_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "ret"


	public static class br_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "br"
	// llvmGrammar.g:554:1: br : ( (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER ) | (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) ) );
	public final llvmGrammarParser.br_return br() throws RecognitionException {
		llvmGrammarParser.br_return retval = new llvmGrammarParser.br_return();
		retval.start = input.LT(1);
		int br_StartIndex = input.index();

		Object root_0 = null;

		Token b=null;
		Token ifT=null;
		Token ifF=null;
		Token COMMA115=null;
		Token string_literal116=null;
		Token LOCAL_PREFIX117=null;
		Token COMMA118=null;
		Token string_literal119=null;
		Token LOCAL_PREFIX120=null;
		Token string_literal121=null;
		Token LOCAL_PREFIX122=null;
		ParserRuleReturnScope cond =null;
		ParserRuleReturnScope data_type114 =null;

		Object b_tree=null;
		Object ifT_tree=null;
		Object ifF_tree=null;
		Object COMMA115_tree=null;
		Object string_literal116_tree=null;
		Object LOCAL_PREFIX117_tree=null;
		Object COMMA118_tree=null;
		Object string_literal119_tree=null;
		Object LOCAL_PREFIX120_tree=null;
		Object string_literal121_tree=null;
		Object LOCAL_PREFIX122_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }

			// llvmGrammar.g:554:4: ( (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER ) | (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) ) )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==63) ) {
				int LA45_1 = input.LA(2);
				if ( (LA45_1==73) ) {
					alt45=2;
				}
				else if ( (LA45_1==LOCAL_PREFIX||LA45_1==PRIMITIVE_DATA_TYPE||LA45_1==START_ANGULAR||(LA45_1 >= START_PARANTHESIS && LA45_1 <= START_SQUARE_BR)) ) {
					alt45=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 45, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// llvmGrammar.g:554:6: (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:554:6: (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER )
					// llvmGrammar.g:554:8: b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER
					{
					b=(Token)match(input,63,FOLLOW_63_in_br2319); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					b_tree = (Object)adaptor.create(b);
					adaptor.addChild(root_0, b_tree);
					}

					pushFollow(FOLLOW_data_type_in_br2321);
					data_type114=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type114.getTree());

					// llvmGrammar.g:554:25: (cond= result )
					// llvmGrammar.g:554:27: cond= result
					{
					pushFollow(FOLLOW_result_in_br2329);
					cond=result();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cond.getTree());

					}

					COMMA115=(Token)match(input,COMMA,FOLLOW_COMMA_in_br2332); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA115_tree = (Object)adaptor.create(COMMA115);
					adaptor.addChild(root_0, COMMA115_tree);
					}

					string_literal116=(Token)match(input,73,FOLLOW_73_in_br2334); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal116_tree = (Object)adaptor.create(string_literal116);
					adaptor.addChild(root_0, string_literal116_tree);
					}

					LOCAL_PREFIX117=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_br2336); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX117_tree = (Object)adaptor.create(LOCAL_PREFIX117);
					adaptor.addChild(root_0, LOCAL_PREFIX117_tree);
					}

					ifT=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_br2340); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ifT_tree = (Object)adaptor.create(ifT);
					adaptor.addChild(root_0, ifT_tree);
					}

					COMMA118=(Token)match(input,COMMA,FOLLOW_COMMA_in_br2342); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA118_tree = (Object)adaptor.create(COMMA118);
					adaptor.addChild(root_0, COMMA118_tree);
					}

					string_literal119=(Token)match(input,73,FOLLOW_73_in_br2344); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal119_tree = (Object)adaptor.create(string_literal119);
					adaptor.addChild(root_0, string_literal119_tree);
					}

					LOCAL_PREFIX120=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_br2346); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX120_tree = (Object)adaptor.create(LOCAL_PREFIX120);
					adaptor.addChild(root_0, LOCAL_PREFIX120_tree);
					}

					ifF=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_br2350); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ifF_tree = (Object)adaptor.create(ifF);
					adaptor.addChild(root_0, ifF_tree);
					}

					}

					if ( state.backtracking==0 ) {
					   		setBranchInstr((ifT!=null?ifT.getText():null), (ifF!=null?ifF.getText():null), (cond!=null?input.toString(cond.start,cond.stop):null), (b!=null?b.getLine():0), (b!=null?b.getCharPositionInLine():0));
					   }
					}
					break;
				case 2 :
					// llvmGrammar.g:558:6: (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:558:6: (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) )
					// llvmGrammar.g:558:8: b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID )
					{
					b=(Token)match(input,63,FOLLOW_63_in_br2369); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					b_tree = (Object)adaptor.create(b);
					adaptor.addChild(root_0, b_tree);
					}

					string_literal121=(Token)match(input,73,FOLLOW_73_in_br2371); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal121_tree = (Object)adaptor.create(string_literal121);
					adaptor.addChild(root_0, string_literal121_tree);
					}

					LOCAL_PREFIX122=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_br2374); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX122_tree = (Object)adaptor.create(LOCAL_PREFIX122);
					adaptor.addChild(root_0, LOCAL_PREFIX122_tree);
					}

					ifT=input.LT(1);
					if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
						input.consume();
						if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(ifT));
						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}

					if ( state.backtracking==0 ) {
					   		setBranchInstr((ifT!=null?ifT.getText():null), null, null, (b!=null?b.getLine():0), (b!=null?b.getCharPositionInLine():0));			    	
					   }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 30, br_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "br"


	public static class switchInstr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "switchInstr"
	// llvmGrammar.g:563:1: switchInstr : s= 'switch' data_type e1= result COMMA 'label' e2= result START_SQUARE_BR e= cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR ;
	public final llvmGrammarParser.switchInstr_return switchInstr() throws RecognitionException {
		llvmGrammarParser.switchInstr_return retval = new llvmGrammarParser.switchInstr_return();
		retval.start = input.LT(1);
		int switchInstr_StartIndex = input.index();

		Object root_0 = null;

		Token s=null;
		Token COMMA124=null;
		Token string_literal125=null;
		Token START_SQUARE_BR126=null;
		Token END_SQUARE_BR127=null;
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope e2 =null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope data_type123 =null;

		Object s_tree=null;
		Object COMMA124_tree=null;
		Object string_literal125_tree=null;
		Object START_SQUARE_BR126_tree=null;
		Object END_SQUARE_BR127_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }

			// llvmGrammar.g:563:13: (s= 'switch' data_type e1= result COMMA 'label' e2= result START_SQUARE_BR e= cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR )
			// llvmGrammar.g:563:15: s= 'switch' data_type e1= result COMMA 'label' e2= result START_SQUARE_BR e= cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR
			{
			root_0 = (Object)adaptor.nil();


			s=(Token)match(input,78,FOLLOW_78_in_switchInstr2406); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			s_tree = (Object)adaptor.create(s);
			adaptor.addChild(root_0, s_tree);
			}

			pushFollow(FOLLOW_data_type_in_switchInstr2408);
			data_type123=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type123.getTree());

			pushFollow(FOLLOW_result_in_switchInstr2412);
			e1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());

			COMMA124=(Token)match(input,COMMA,FOLLOW_COMMA_in_switchInstr2414); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA124_tree = (Object)adaptor.create(COMMA124);
			adaptor.addChild(root_0, COMMA124_tree);
			}

			string_literal125=(Token)match(input,73,FOLLOW_73_in_switchInstr2416); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal125_tree = (Object)adaptor.create(string_literal125);
			adaptor.addChild(root_0, string_literal125_tree);
			}

			pushFollow(FOLLOW_result_in_switchInstr2420);
			e2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());

			START_SQUARE_BR126=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_switchInstr2422); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			START_SQUARE_BR126_tree = (Object)adaptor.create(START_SQUARE_BR126);
			adaptor.addChild(root_0, START_SQUARE_BR126_tree);
			}

			pushFollow(FOLLOW_cases_list_in_switchInstr2432);
			e=cases_list((e1!=null?input.toString(e1.start,e1.stop):null), (e2!=null?input.toString(e2.start,e2.stop):null), (s!=null?s.getLine():0));
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

			END_SQUARE_BR127=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_switchInstr2435); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_SQUARE_BR127_tree = (Object)adaptor.create(END_SQUARE_BR127);
			adaptor.addChild(root_0, END_SQUARE_BR127_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 31, switchInstr_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "switchInstr"


	public static class cases_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cases_list"
	// llvmGrammar.g:566:1: cases_list[String switchOn,String defaultBB, int line] : ( cases )* ;
	public final llvmGrammarParser.cases_list_return cases_list(String switchOn, String defaultBB, int line) throws RecognitionException {
		llvmGrammarParser.cases_list_return retval = new llvmGrammarParser.cases_list_return();
		retval.start = input.LT(1);
		int cases_list_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope cases128 =null;


			
					   setSwitchInstr(switchOn,defaultBB, line, 0);							
				    
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }

			// llvmGrammar.g:569:8: ( ( cases )* )
			// llvmGrammar.g:569:10: ( cases )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:569:10: ( cases )*
			loop46:
			while (true) {
				int alt46=2;
				int LA46_0 = input.LA(1);
				if ( (LA46_0==LOCAL_PREFIX||LA46_0==PRIMITIVE_DATA_TYPE||LA46_0==START_ANGULAR||(LA46_0 >= START_PARANTHESIS && LA46_0 <= START_SQUARE_BR)) ) {
					alt46=1;
				}

				switch (alt46) {
				case 1 :
					// llvmGrammar.g:569:10: cases
					{
					pushFollow(FOLLOW_cases_in_cases_list2458);
					cases128=cases();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cases128.getTree());

					}
					break;

				default :
					break loop46;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 32, cases_list_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cases_list"


	public static class cases_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cases"
	// llvmGrammar.g:571:1: cases : ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result ) ;
	public final llvmGrammarParser.cases_return cases() throws RecognitionException {
		llvmGrammarParser.cases_return retval = new llvmGrammarParser.cases_return();
		retval.start = input.LT(1);
		int cases_StartIndex = input.index();

		Object root_0 = null;

		Token e1=null;
		Token COMMA130=null;
		Token string_literal131=null;
		ParserRuleReturnScope e2 =null;
		ParserRuleReturnScope data_type129 =null;

		Object e1_tree=null;
		Object COMMA130_tree=null;
		Object string_literal131_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }

			// llvmGrammar.g:571:7: ( ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result ) )
			// llvmGrammar.g:571:9: ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:571:9: ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result )
			// llvmGrammar.g:571:10: data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result
			{
			pushFollow(FOLLOW_data_type_in_cases2477);
			data_type129=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type129.getTree());

			e1=input.LT(1);
			if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(e1));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			COMMA130=(Token)match(input,COMMA,FOLLOW_COMMA_in_cases2489); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA130_tree = (Object)adaptor.create(COMMA130);
			adaptor.addChild(root_0, COMMA130_tree);
			}

			string_literal131=(Token)match(input,73,FOLLOW_73_in_cases2491); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal131_tree = (Object)adaptor.create(string_literal131);
			adaptor.addChild(root_0, string_literal131_tree);
			}

			pushFollow(FOLLOW_result_in_cases2497);
			e2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());

			}

			if ( state.backtracking==0 ) {		
			  	  		String typeStr = (data_type129!=null?input.toString(data_type129.start,data_type129.stop):null);
			  			String condition = (e1!=null?e1.getText():null);
			  			String target = (e2!=null?input.toString(e2.start,e2.stop):null);
			  			setSwitchCase(typeStr,condition,target, (e1!=null?e1.getLine():0), (e1!=null?e1.getCharPositionInLine():0));
			  	  }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 33, cases_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cases"


	public static class unreachable_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "unreachable"
	// llvmGrammar.g:579:1: unreachable : UNREACHABLE ;
	public final llvmGrammarParser.unreachable_return unreachable() throws RecognitionException {
		llvmGrammarParser.unreachable_return retval = new llvmGrammarParser.unreachable_return();
		retval.start = input.LT(1);
		int unreachable_StartIndex = input.index();

		Object root_0 = null;

		Token UNREACHABLE132=null;

		Object UNREACHABLE132_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }

			// llvmGrammar.g:579:13: ( UNREACHABLE )
			// llvmGrammar.g:579:15: UNREACHABLE
			{
			root_0 = (Object)adaptor.nil();


			UNREACHABLE132=(Token)match(input,UNREACHABLE,FOLLOW_UNREACHABLE_in_unreachable2521); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			UNREACHABLE132_tree = (Object)adaptor.create(UNREACHABLE132);
			adaptor.addChild(root_0, UNREACHABLE132_tree);
			}

			if ( state.backtracking==0 ) {
			  		    	UnreachableInstrData instrData = new UnreachableInstrData();
			  		        list.add(instrData);
			  		    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 34, unreachable_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "unreachable"


	public static class other_instruction_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "other_instruction"
	// llvmGrammar.g:585:1: other_instruction : ( cmp | phi | call | select );
	public final llvmGrammarParser.other_instruction_return other_instruction() throws RecognitionException {
		llvmGrammarParser.other_instruction_return retval = new llvmGrammarParser.other_instruction_return();
		retval.start = input.LT(1);
		int other_instruction_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope cmp133 =null;
		ParserRuleReturnScope phi134 =null;
		ParserRuleReturnScope call135 =null;
		ParserRuleReturnScope select136 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }

			// llvmGrammar.g:585:19: ( cmp | phi | call | select )
			int alt47=4;
			switch ( input.LA(1) ) {
			case GLOBAL_PREFIX:
			case LOCAL_PREFIX:
				{
				int LA47_1 = input.LA(2);
				if ( (LA47_1==FLOATING_LITERAL||LA47_1==ID||LA47_1==NUMBER) ) {
					int LA47_2 = input.LA(3);
					if ( (LA47_2==EQUAL) ) {
						switch ( input.LA(4) ) {
						case PHI:
							{
							alt47=2;
							}
							break;
						case 76:
							{
							alt47=4;
							}
							break;
						case 68:
						case 71:
							{
							alt47=1;
							}
							break;
						case 64:
						case 79:
							{
							alt47=3;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 47, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 47, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 47, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FLOATING_LITERAL:
			case ID:
			case NUMBER:
				{
				int LA47_2 = input.LA(2);
				if ( (LA47_2==EQUAL) ) {
					switch ( input.LA(3) ) {
					case PHI:
						{
						alt47=2;
						}
						break;
					case 76:
						{
						alt47=4;
						}
						break;
					case 68:
					case 71:
						{
						alt47=1;
						}
						break;
					case 64:
					case 79:
						{
						alt47=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 47, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 47, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FALSE:
			case TRUE:
				{
				int LA47_3 = input.LA(2);
				if ( (LA47_3==EQUAL) ) {
					switch ( input.LA(3) ) {
					case PHI:
						{
						alt47=2;
						}
						break;
					case 76:
						{
						alt47=4;
						}
						break;
					case 68:
					case 71:
						{
						alt47=1;
						}
						break;
					case 64:
					case 79:
						{
						alt47=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 47, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 47, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 83:
				{
				int LA47_4 = input.LA(2);
				if ( (LA47_4==EQUAL) ) {
					switch ( input.LA(3) ) {
					case PHI:
						{
						alt47=2;
						}
						break;
					case 76:
						{
						alt47=4;
						}
						break;
					case 68:
					case 71:
						{
						alt47=1;
						}
						break;
					case 64:
					case 79:
						{
						alt47=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 47, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 47, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 64:
			case 79:
				{
				alt47=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}
			switch (alt47) {
				case 1 :
					// llvmGrammar.g:585:21: cmp
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_cmp_in_other_instruction2543);
					cmp133=cmp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cmp133.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:586:11: phi
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_phi_in_other_instruction2556);
					phi134=phi();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, phi134.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:587:11: call
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_call_in_other_instruction2569);
					call135=call();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, call135.getTree());

					}
					break;
				case 4 :
					// llvmGrammar.g:588:11: select
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_select_in_other_instruction2582);
					select136=select();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, select136.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 35, other_instruction_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "other_instruction"


	public static class cmp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cmp"
	// llvmGrammar.g:590:1: cmp : id1= result e= EQUAL (compr= 'icmp' | 'fcmp' ) pre= CONDITION typestr= data_type o1= result COMMA o2= result ;
	public final llvmGrammarParser.cmp_return cmp() throws RecognitionException {
		llvmGrammarParser.cmp_return retval = new llvmGrammarParser.cmp_return();
		retval.start = input.LT(1);
		int cmp_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token compr=null;
		Token pre=null;
		Token string_literal137=null;
		Token COMMA138=null;
		ParserRuleReturnScope id1 =null;
		ParserRuleReturnScope typestr =null;
		ParserRuleReturnScope o1 =null;
		ParserRuleReturnScope o2 =null;

		Object e_tree=null;
		Object compr_tree=null;
		Object pre_tree=null;
		Object string_literal137_tree=null;
		Object COMMA138_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }

			// llvmGrammar.g:590:5: (id1= result e= EQUAL (compr= 'icmp' | 'fcmp' ) pre= CONDITION typestr= data_type o1= result COMMA o2= result )
			// llvmGrammar.g:590:6: id1= result e= EQUAL (compr= 'icmp' | 'fcmp' ) pre= CONDITION typestr= data_type o1= result COMMA o2= result
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_cmp2593);
			id1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, id1.getTree());

			e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_cmp2597); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			// llvmGrammar.g:590:26: (compr= 'icmp' | 'fcmp' )
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==71) ) {
				alt48=1;
			}
			else if ( (LA48_0==68) ) {
				alt48=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}

			switch (alt48) {
				case 1 :
					// llvmGrammar.g:590:27: compr= 'icmp'
					{
					compr=(Token)match(input,71,FOLLOW_71_in_cmp2604); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					compr_tree = (Object)adaptor.create(compr);
					adaptor.addChild(root_0, compr_tree);
					}

					}
					break;
				case 2 :
					// llvmGrammar.g:590:42: 'fcmp'
					{
					string_literal137=(Token)match(input,68,FOLLOW_68_in_cmp2606); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal137_tree = (Object)adaptor.create(string_literal137);
					adaptor.addChild(root_0, string_literal137_tree);
					}

					}
					break;

			}

			pre=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_cmp2611); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			pre_tree = (Object)adaptor.create(pre);
			adaptor.addChild(root_0, pre_tree);
			}

			pushFollow(FOLLOW_data_type_in_cmp2615);
			typestr=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typestr.getTree());

			pushFollow(FOLLOW_result_in_cmp2635);
			o1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, o1.getTree());

			COMMA138=(Token)match(input,COMMA,FOLLOW_COMMA_in_cmp2638); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA138_tree = (Object)adaptor.create(COMMA138);
			adaptor.addChild(root_0, COMMA138_tree);
			}

			pushFollow(FOLLOW_result_in_cmp2645);
			o2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, o2.getTree());

			if ( state.backtracking==0 ) {   
			  		String compare = (compr!=null?compr.getText():null) == null? "fcmp" : "icmp";
			  		createAndSetIcmpData((id1!=null?input.toString(id1.start,id1.stop):null), compare, (pre!=null?pre.getText():null), (typestr!=null?input.toString(typestr.start,typestr.stop):null), (o1!=null?input.toString(o1.start,o1.stop):null), (o2!=null?input.toString(o2.start,o2.stop):null), (e!=null?e.getLine():0), 0);			
			  	}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 36, cmp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cmp"


	public static class phi_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "phi"
	// llvmGrammar.g:597:1: phi : num= result e= EQUAL PHI typeStr= data_type incoming= incoming_list ;
	public final llvmGrammarParser.phi_return phi() throws RecognitionException {
		llvmGrammarParser.phi_return retval = new llvmGrammarParser.phi_return();
		retval.start = input.LT(1);
		int phi_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token PHI139=null;
		ParserRuleReturnScope num =null;
		ParserRuleReturnScope typeStr =null;
		ParserRuleReturnScope incoming =null;

		Object e_tree=null;
		Object PHI139_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }

			// llvmGrammar.g:597:5: (num= result e= EQUAL PHI typeStr= data_type incoming= incoming_list )
			// llvmGrammar.g:597:7: num= result e= EQUAL PHI typeStr= data_type incoming= incoming_list
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_phi2660);
			num=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, num.getTree());

			e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_phi2664); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			PHI139=(Token)match(input,PHI,FOLLOW_PHI_in_phi2666); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PHI139_tree = (Object)adaptor.create(PHI139);
			adaptor.addChild(root_0, PHI139_tree);
			}

			pushFollow(FOLLOW_data_type_in_phi2670);
			typeStr=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr.getTree());

			pushFollow(FOLLOW_incoming_list_in_phi2674);
			incoming=incoming_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, incoming.getTree());

			if ( state.backtracking==0 ) {
					setPhiNode((num!=null?input.toString(num.start,num.stop):null),(typeStr!=null?input.toString(typeStr.start,typeStr.stop):null),(incoming!=null?input.toString(incoming.start,incoming.stop):null), (e!=null?e.getLine():0), 0);
				}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 37, phi_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "phi"


	public static class incoming_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "incoming_list"
	// llvmGrammar.g:602:1: incoming_list : START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+ ;
	public final llvmGrammarParser.incoming_list_return incoming_list() throws RecognitionException {
		llvmGrammarParser.incoming_list_return retval = new llvmGrammarParser.incoming_list_return();
		retval.start = input.LT(1);
		int incoming_list_StartIndex = input.index();

		Object root_0 = null;

		Token START_SQUARE_BR140=null;
		Token string_literal141=null;
		Token string_literal142=null;
		Token string_literal144=null;
		Token COMMA145=null;
		Token END_SQUARE_BR147=null;
		Token COMMA148=null;
		Token START_SQUARE_BR149=null;
		Token string_literal150=null;
		Token string_literal151=null;
		Token string_literal153=null;
		Token COMMA154=null;
		Token END_SQUARE_BR156=null;
		ParserRuleReturnScope result143 =null;
		ParserRuleReturnScope result146 =null;
		ParserRuleReturnScope result152 =null;
		ParserRuleReturnScope result155 =null;

		Object START_SQUARE_BR140_tree=null;
		Object string_literal141_tree=null;
		Object string_literal142_tree=null;
		Object string_literal144_tree=null;
		Object COMMA145_tree=null;
		Object END_SQUARE_BR147_tree=null;
		Object COMMA148_tree=null;
		Object START_SQUARE_BR149_tree=null;
		Object string_literal150_tree=null;
		Object string_literal151_tree=null;
		Object string_literal153_tree=null;
		Object COMMA154_tree=null;
		Object END_SQUARE_BR156_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }

			// llvmGrammar.g:602:15: ( START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+ )
			// llvmGrammar.g:602:17: START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+
			{
			root_0 = (Object)adaptor.nil();


			START_SQUARE_BR140=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_incoming_list2686); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			START_SQUARE_BR140_tree = (Object)adaptor.create(START_SQUARE_BR140);
			adaptor.addChild(root_0, START_SQUARE_BR140_tree);
			}

			// llvmGrammar.g:602:32: ( 'true' | 'false' | result | 'undef' )
			int alt49=4;
			switch ( input.LA(1) ) {
			case TRUE:
				{
				int LA49_1 = input.LA(2);
				if ( (synpred68_llvmGrammar()) ) {
					alt49=1;
				}
				else if ( (synpred70_llvmGrammar()) ) {
					alt49=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 49, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FALSE:
				{
				int LA49_2 = input.LA(2);
				if ( (synpred69_llvmGrammar()) ) {
					alt49=2;
				}
				else if ( (synpred70_llvmGrammar()) ) {
					alt49=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 49, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FLOATING_LITERAL:
			case GLOBAL_PREFIX:
			case ID:
			case LOCAL_PREFIX:
			case NUMBER:
				{
				alt49=3;
				}
				break;
			case 83:
				{
				int LA49_4 = input.LA(2);
				if ( (synpred70_llvmGrammar()) ) {
					alt49=3;
				}
				else if ( (true) ) {
					alt49=4;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 49, 0, input);
				throw nvae;
			}
			switch (alt49) {
				case 1 :
					// llvmGrammar.g:602:34: 'true'
					{
					string_literal141=(Token)match(input,TRUE,FOLLOW_TRUE_in_incoming_list2689); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal141_tree = (Object)adaptor.create(string_literal141);
					adaptor.addChild(root_0, string_literal141_tree);
					}

					}
					break;
				case 2 :
					// llvmGrammar.g:602:41: 'false'
					{
					string_literal142=(Token)match(input,FALSE,FOLLOW_FALSE_in_incoming_list2691); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal142_tree = (Object)adaptor.create(string_literal142);
					adaptor.addChild(root_0, string_literal142_tree);
					}

					}
					break;
				case 3 :
					// llvmGrammar.g:602:49: result
					{
					pushFollow(FOLLOW_result_in_incoming_list2693);
					result143=result();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, result143.getTree());

					}
					break;
				case 4 :
					// llvmGrammar.g:602:56: 'undef'
					{
					string_literal144=(Token)match(input,83,FOLLOW_83_in_incoming_list2695); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal144_tree = (Object)adaptor.create(string_literal144);
					adaptor.addChild(root_0, string_literal144_tree);
					}

					}
					break;

			}

			COMMA145=(Token)match(input,COMMA,FOLLOW_COMMA_in_incoming_list2698); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA145_tree = (Object)adaptor.create(COMMA145);
			adaptor.addChild(root_0, COMMA145_tree);
			}

			pushFollow(FOLLOW_result_in_incoming_list2700);
			result146=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, result146.getTree());

			END_SQUARE_BR147=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_incoming_list2702); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_SQUARE_BR147_tree = (Object)adaptor.create(END_SQUARE_BR147);
			adaptor.addChild(root_0, END_SQUARE_BR147_tree);
			}

			// llvmGrammar.g:603:5: ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+
			int cnt51=0;
			loop51:
			while (true) {
				int alt51=2;
				int LA51_0 = input.LA(1);
				if ( (LA51_0==COMMA) ) {
					alt51=1;
				}

				switch (alt51) {
				case 1 :
					// llvmGrammar.g:603:6: COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR
					{
					COMMA148=(Token)match(input,COMMA,FOLLOW_COMMA_in_incoming_list2709); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA148_tree = (Object)adaptor.create(COMMA148);
					adaptor.addChild(root_0, COMMA148_tree);
					}

					START_SQUARE_BR149=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_incoming_list2711); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					START_SQUARE_BR149_tree = (Object)adaptor.create(START_SQUARE_BR149);
					adaptor.addChild(root_0, START_SQUARE_BR149_tree);
					}

					// llvmGrammar.g:603:27: ( 'true' | 'false' | result | 'undef' )
					int alt50=4;
					switch ( input.LA(1) ) {
					case TRUE:
						{
						int LA50_1 = input.LA(2);
						if ( (synpred71_llvmGrammar()) ) {
							alt50=1;
						}
						else if ( (synpred73_llvmGrammar()) ) {
							alt50=3;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 50, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case FALSE:
						{
						int LA50_2 = input.LA(2);
						if ( (synpred72_llvmGrammar()) ) {
							alt50=2;
						}
						else if ( (synpred73_llvmGrammar()) ) {
							alt50=3;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 50, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case FLOATING_LITERAL:
					case GLOBAL_PREFIX:
					case ID:
					case LOCAL_PREFIX:
					case NUMBER:
						{
						alt50=3;
						}
						break;
					case 83:
						{
						int LA50_4 = input.LA(2);
						if ( (synpred73_llvmGrammar()) ) {
							alt50=3;
						}
						else if ( (true) ) {
							alt50=4;
						}

						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 50, 0, input);
						throw nvae;
					}
					switch (alt50) {
						case 1 :
							// llvmGrammar.g:603:29: 'true'
							{
							string_literal150=(Token)match(input,TRUE,FOLLOW_TRUE_in_incoming_list2714); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							string_literal150_tree = (Object)adaptor.create(string_literal150);
							adaptor.addChild(root_0, string_literal150_tree);
							}

							}
							break;
						case 2 :
							// llvmGrammar.g:603:36: 'false'
							{
							string_literal151=(Token)match(input,FALSE,FOLLOW_FALSE_in_incoming_list2716); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							string_literal151_tree = (Object)adaptor.create(string_literal151);
							adaptor.addChild(root_0, string_literal151_tree);
							}

							}
							break;
						case 3 :
							// llvmGrammar.g:603:44: result
							{
							pushFollow(FOLLOW_result_in_incoming_list2718);
							result152=result();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, result152.getTree());

							}
							break;
						case 4 :
							// llvmGrammar.g:603:51: 'undef'
							{
							string_literal153=(Token)match(input,83,FOLLOW_83_in_incoming_list2720); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							string_literal153_tree = (Object)adaptor.create(string_literal153);
							adaptor.addChild(root_0, string_literal153_tree);
							}

							}
							break;

					}

					COMMA154=(Token)match(input,COMMA,FOLLOW_COMMA_in_incoming_list2724); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA154_tree = (Object)adaptor.create(COMMA154);
					adaptor.addChild(root_0, COMMA154_tree);
					}

					pushFollow(FOLLOW_result_in_incoming_list2726);
					result155=result();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, result155.getTree());

					END_SQUARE_BR156=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_incoming_list2728); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_SQUARE_BR156_tree = (Object)adaptor.create(END_SQUARE_BR156);
					adaptor.addChild(root_0, END_SQUARE_BR156_tree);
					}

					}
					break;

				default :
					if ( cnt51 >= 1 ) break loop51;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(51, input);
					throw eee;
				}
				cnt51++;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 38, incoming_list_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "incoming_list"


	public static class call_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "call"
	// llvmGrammar.g:605:1: call : (name= result EQUAL )? (tail= 'tail' )? c= 'call' (callingc= CALLING_CONV )? (parameterAttr= PARAMETER_ATTRIBUTE )? typeStr= data_type func_name START_PARANTHESIS para= parameter END_PARANTHESIS (functionAttr= FUNCTION_ATTRIBUTE )? ;
	public final llvmGrammarParser.call_return call() throws RecognitionException {
		llvmGrammarParser.call_return retval = new llvmGrammarParser.call_return();
		retval.start = input.LT(1);
		int call_StartIndex = input.index();

		Object root_0 = null;

		Token tail=null;
		Token c=null;
		Token callingc=null;
		Token parameterAttr=null;
		Token functionAttr=null;
		Token EQUAL157=null;
		Token START_PARANTHESIS159=null;
		Token END_PARANTHESIS160=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope typeStr =null;
		ParserRuleReturnScope para =null;
		ParserRuleReturnScope func_name158 =null;

		Object tail_tree=null;
		Object c_tree=null;
		Object callingc_tree=null;
		Object parameterAttr_tree=null;
		Object functionAttr_tree=null;
		Object EQUAL157_tree=null;
		Object START_PARANTHESIS159_tree=null;
		Object END_PARANTHESIS160_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

			// llvmGrammar.g:605:6: ( (name= result EQUAL )? (tail= 'tail' )? c= 'call' (callingc= CALLING_CONV )? (parameterAttr= PARAMETER_ATTRIBUTE )? typeStr= data_type func_name START_PARANTHESIS para= parameter END_PARANTHESIS (functionAttr= FUNCTION_ATTRIBUTE )? )
			// llvmGrammar.g:605:8: (name= result EQUAL )? (tail= 'tail' )? c= 'call' (callingc= CALLING_CONV )? (parameterAttr= PARAMETER_ATTRIBUTE )? typeStr= data_type func_name START_PARANTHESIS para= parameter END_PARANTHESIS (functionAttr= FUNCTION_ATTRIBUTE )?
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:605:8: (name= result EQUAL )?
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( ((LA52_0 >= FALSE && LA52_0 <= FLOATING_LITERAL)||LA52_0==GLOBAL_PREFIX||LA52_0==ID||LA52_0==LOCAL_PREFIX||LA52_0==NUMBER||LA52_0==TRUE||LA52_0==83) ) {
				alt52=1;
			}
			switch (alt52) {
				case 1 :
					// llvmGrammar.g:605:9: name= result EQUAL
					{
					pushFollow(FOLLOW_result_in_call2747);
					name=result();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());

					EQUAL157=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_call2749); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EQUAL157_tree = (Object)adaptor.create(EQUAL157);
					adaptor.addChild(root_0, EQUAL157_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:605:33: (tail= 'tail' )?
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==79) ) {
				alt53=1;
			}
			switch (alt53) {
				case 1 :
					// llvmGrammar.g:605:33: tail= 'tail'
					{
					tail=(Token)match(input,79,FOLLOW_79_in_call2755); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					tail_tree = (Object)adaptor.create(tail);
					adaptor.addChild(root_0, tail_tree);
					}

					}
					break;

			}

			c=(Token)match(input,64,FOLLOW_64_in_call2760); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			c_tree = (Object)adaptor.create(c);
			adaptor.addChild(root_0, c_tree);
			}

			// llvmGrammar.g:605:59: (callingc= CALLING_CONV )?
			int alt54=2;
			int LA54_0 = input.LA(1);
			if ( (LA54_0==CALLING_CONV) ) {
				alt54=1;
			}
			switch (alt54) {
				case 1 :
					// llvmGrammar.g:605:59: callingc= CALLING_CONV
					{
					callingc=(Token)match(input,CALLING_CONV,FOLLOW_CALLING_CONV_in_call2764); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					callingc_tree = (Object)adaptor.create(callingc);
					adaptor.addChild(root_0, callingc_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:605:87: (parameterAttr= PARAMETER_ATTRIBUTE )?
			int alt55=2;
			int LA55_0 = input.LA(1);
			if ( (LA55_0==PARAMETER_ATTRIBUTE) ) {
				alt55=1;
			}
			switch (alt55) {
				case 1 :
					// llvmGrammar.g:605:87: parameterAttr= PARAMETER_ATTRIBUTE
					{
					parameterAttr=(Token)match(input,PARAMETER_ATTRIBUTE,FOLLOW_PARAMETER_ATTRIBUTE_in_call2769); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					parameterAttr_tree = (Object)adaptor.create(parameterAttr);
					adaptor.addChild(root_0, parameterAttr_tree);
					}

					}
					break;

			}

			pushFollow(FOLLOW_data_type_in_call2777);
			typeStr=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr.getTree());

			pushFollow(FOLLOW_func_name_in_call2779);
			func_name158=func_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, func_name158.getTree());

			START_PARANTHESIS159=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_call2781); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			START_PARANTHESIS159_tree = (Object)adaptor.create(START_PARANTHESIS159);
			adaptor.addChild(root_0, START_PARANTHESIS159_tree);
			}

			pushFollow(FOLLOW_parameter_in_call2785);
			para=parameter();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, para.getTree());

			END_PARANTHESIS160=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_call2787); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_PARANTHESIS160_tree = (Object)adaptor.create(END_PARANTHESIS160);
			adaptor.addChild(root_0, END_PARANTHESIS160_tree);
			}

			// llvmGrammar.g:606:92: (functionAttr= FUNCTION_ATTRIBUTE )?
			int alt56=2;
			int LA56_0 = input.LA(1);
			if ( (LA56_0==FUNCTION_ATTRIBUTE) ) {
				alt56=1;
			}
			switch (alt56) {
				case 1 :
					// llvmGrammar.g:606:92: functionAttr= FUNCTION_ATTRIBUTE
					{
					functionAttr=(Token)match(input,FUNCTION_ATTRIBUTE,FOLLOW_FUNCTION_ATTRIBUTE_in_call2791); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					functionAttr_tree = (Object)adaptor.create(functionAttr);
					adaptor.addChild(root_0, functionAttr_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
				 	setCallInstr((name!=null?input.toString(name.start,name.stop):null), (callingc!=null?callingc.getText():null), (tail!=null?tail.getText():null), (para!=null?input.toString(para.start,para.stop):null), (typeStr!=null?input.toString(typeStr.start,typeStr.stop):null), (func_name158!=null?input.toString(func_name158.start,func_name158.stop):null), 
				 				 (parameterAttr!=null?parameterAttr.getText():null),(functionAttr!=null?functionAttr.getText():null), (c!=null?c.getLine():0), 0);
				 }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 39, call_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "call"


	public static class func_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "func_name"
	// llvmGrammar.g:612:1: func_name : ( prefix ( NUMBER | ID ) ) ;
	public final llvmGrammarParser.func_name_return func_name() throws RecognitionException {
		llvmGrammarParser.func_name_return retval = new llvmGrammarParser.func_name_return();
		retval.start = input.LT(1);
		int func_name_StartIndex = input.index();

		Object root_0 = null;

		Token set162=null;
		ParserRuleReturnScope prefix161 =null;

		Object set162_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

			// llvmGrammar.g:612:11: ( ( prefix ( NUMBER | ID ) ) )
			// llvmGrammar.g:612:12: ( prefix ( NUMBER | ID ) )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:612:12: ( prefix ( NUMBER | ID ) )
			// llvmGrammar.g:612:13: prefix ( NUMBER | ID )
			{
			pushFollow(FOLLOW_prefix_in_func_name2804);
			prefix161=prefix();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix161.getTree());

			set162=input.LT(1);
			if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set162));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 40, func_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "func_name"


	public static class parameter_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "parameter"
	// llvmGrammar.g:614:1: parameter : ( ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )? | func_type );
	public final llvmGrammarParser.parameter_return parameter() throws RecognitionException {
		llvmGrammarParser.parameter_return retval = new llvmGrammarParser.parameter_return();
		retval.start = input.LT(1);
		int parameter_StartIndex = input.index();

		Object root_0 = null;

		Token set165=null;
		Token COMMA167=null;
		Token set170=null;
		ParserRuleReturnScope data_type163 =null;
		ParserRuleReturnScope prefix164 =null;
		ParserRuleReturnScope truefalse166 =null;
		ParserRuleReturnScope data_type168 =null;
		ParserRuleReturnScope prefix169 =null;
		ParserRuleReturnScope truefalse171 =null;
		ParserRuleReturnScope func_type172 =null;

		Object set165_tree=null;
		Object COMMA167_tree=null;
		Object set170_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

			// llvmGrammar.g:614:11: ( ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )? | func_type )
			int alt63=2;
			switch ( input.LA(1) ) {
			case PRIMITIVE_DATA_TYPE:
				{
				int LA63_1 = input.LA(2);
				if ( (synpred91_llvmGrammar()) ) {
					alt63=1;
				}
				else if ( (true) ) {
					alt63=2;
				}

				}
				break;
			case END_PARANTHESIS:
			case LOCAL_PREFIX:
			case START_ANGULAR:
			case START_SQUARE_BR:
				{
				alt63=1;
				}
				break;
			case START_PARANTHESIS:
				{
				int LA63_5 = input.LA(2);
				if ( (synpred91_llvmGrammar()) ) {
					alt63=1;
				}
				else if ( (true) ) {
					alt63=2;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 63, 0, input);
				throw nvae;
			}
			switch (alt63) {
				case 1 :
					// llvmGrammar.g:614:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:614:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
					int alt62=2;
					int LA62_0 = input.LA(1);
					if ( (LA62_0==LOCAL_PREFIX||LA62_0==PRIMITIVE_DATA_TYPE||LA62_0==START_ANGULAR||(LA62_0 >= START_PARANTHESIS && LA62_0 <= START_SQUARE_BR)) ) {
						alt62=1;
					}
					switch (alt62) {
						case 1 :
							// llvmGrammar.g:614:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
							{
							// llvmGrammar.g:614:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
							// llvmGrammar.g:614:15: data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
							{
							pushFollow(FOLLOW_data_type_in_parameter2828);
							data_type163=data_type();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type163.getTree());

							// llvmGrammar.g:614:25: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
							int alt58=2;
							int LA58_0 = input.LA(1);
							if ( (LA58_0==FLOATING_LITERAL||LA58_0==GLOBAL_PREFIX||LA58_0==ID||LA58_0==LOCAL_PREFIX||LA58_0==NUMBER) ) {
								alt58=1;
							}
							else if ( (LA58_0==FALSE||LA58_0==TRUE) ) {
								alt58=2;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								NoViableAltException nvae =
									new NoViableAltException("", 58, 0, input);
								throw nvae;
							}

							switch (alt58) {
								case 1 :
									// llvmGrammar.g:614:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
									{
									// llvmGrammar.g:614:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
									// llvmGrammar.g:614:27: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
									{
									// llvmGrammar.g:614:27: ( prefix )?
									int alt57=2;
									int LA57_0 = input.LA(1);
									if ( (LA57_0==GLOBAL_PREFIX||LA57_0==LOCAL_PREFIX) ) {
										alt57=1;
									}
									switch (alt57) {
										case 1 :
											// llvmGrammar.g:614:27: prefix
											{
											pushFollow(FOLLOW_prefix_in_parameter2832);
											prefix164=prefix();
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix164.getTree());

											}
											break;

									}

									set165=input.LT(1);
									if ( input.LA(1)==FLOATING_LITERAL||input.LA(1)==ID||input.LA(1)==NUMBER ) {
										input.consume();
										if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set165));
										state.errorRecovery=false;
										state.failed=false;
									}
									else {
										if (state.backtracking>0) {state.failed=true; return retval;}
										MismatchedSetException mse = new MismatchedSetException(null,input);
										throw mse;
									}
									}

									}
									break;
								case 2 :
									// llvmGrammar.g:614:68: truefalse
									{
									pushFollow(FOLLOW_truefalse_in_parameter2847);
									truefalse166=truefalse();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, truefalse166.getTree());

									}
									break;

							}

							// llvmGrammar.g:615:4: ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
							loop61:
							while (true) {
								int alt61=2;
								int LA61_0 = input.LA(1);
								if ( (LA61_0==COMMA) ) {
									alt61=1;
								}

								switch (alt61) {
								case 1 :
									// llvmGrammar.g:615:5: COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
									{
									COMMA167=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameter2854); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA167_tree = (Object)adaptor.create(COMMA167);
									adaptor.addChild(root_0, COMMA167_tree);
									}

									pushFollow(FOLLOW_data_type_in_parameter2856);
									data_type168=data_type();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type168.getTree());

									// llvmGrammar.g:615:21: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
									int alt60=2;
									int LA60_0 = input.LA(1);
									if ( (LA60_0==FLOATING_LITERAL||LA60_0==GLOBAL_PREFIX||LA60_0==ID||LA60_0==LOCAL_PREFIX||LA60_0==NUMBER) ) {
										alt60=1;
									}
									else if ( (LA60_0==FALSE||LA60_0==TRUE) ) {
										alt60=2;
									}

									else {
										if (state.backtracking>0) {state.failed=true; return retval;}
										NoViableAltException nvae =
											new NoViableAltException("", 60, 0, input);
										throw nvae;
									}

									switch (alt60) {
										case 1 :
											// llvmGrammar.g:615:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
											{
											// llvmGrammar.g:615:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
											// llvmGrammar.g:615:23: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
											{
											// llvmGrammar.g:615:23: ( prefix )?
											int alt59=2;
											int LA59_0 = input.LA(1);
											if ( (LA59_0==GLOBAL_PREFIX||LA59_0==LOCAL_PREFIX) ) {
												alt59=1;
											}
											switch (alt59) {
												case 1 :
													// llvmGrammar.g:615:23: prefix
													{
													pushFollow(FOLLOW_prefix_in_parameter2860);
													prefix169=prefix();
													state._fsp--;
													if (state.failed) return retval;
													if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix169.getTree());

													}
													break;

											}

											set170=input.LT(1);
											if ( input.LA(1)==FLOATING_LITERAL||input.LA(1)==ID||input.LA(1)==NUMBER ) {
												input.consume();
												if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set170));
												state.errorRecovery=false;
												state.failed=false;
											}
											else {
												if (state.backtracking>0) {state.failed=true; return retval;}
												MismatchedSetException mse = new MismatchedSetException(null,input);
												throw mse;
											}
											}

											}
											break;
										case 2 :
											// llvmGrammar.g:615:64: truefalse
											{
											pushFollow(FOLLOW_truefalse_in_parameter2875);
											truefalse171=truefalse();
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, truefalse171.getTree());

											}
											break;

									}

									}
									break;

								default :
									break loop61;
								}
							}

							}

							}
							break;

					}

					}
					break;
				case 2 :
					// llvmGrammar.g:616:6: func_type
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_func_type_in_parameter2889);
					func_type172=func_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, func_type172.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 41, parameter_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "parameter"


	public static class select_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "select"
	// llvmGrammar.g:618:1: select : name= result EQUAL t= 'select' typeStr= data_type condValue= result COMMA typeStr1= data_type valueStr1= result COMMA typeStr2= data_type valueStr2= result ;
	public final llvmGrammarParser.select_return select() throws RecognitionException {
		llvmGrammarParser.select_return retval = new llvmGrammarParser.select_return();
		retval.start = input.LT(1);
		int select_StartIndex = input.index();

		Object root_0 = null;

		Token t=null;
		Token EQUAL173=null;
		Token COMMA174=null;
		Token COMMA175=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope typeStr =null;
		ParserRuleReturnScope condValue =null;
		ParserRuleReturnScope typeStr1 =null;
		ParserRuleReturnScope valueStr1 =null;
		ParserRuleReturnScope typeStr2 =null;
		ParserRuleReturnScope valueStr2 =null;

		Object t_tree=null;
		Object EQUAL173_tree=null;
		Object COMMA174_tree=null;
		Object COMMA175_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

			// llvmGrammar.g:618:8: (name= result EQUAL t= 'select' typeStr= data_type condValue= result COMMA typeStr1= data_type valueStr1= result COMMA typeStr2= data_type valueStr2= result )
			// llvmGrammar.g:618:10: name= result EQUAL t= 'select' typeStr= data_type condValue= result COMMA typeStr1= data_type valueStr1= result COMMA typeStr2= data_type valueStr2= result
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_select2900);
			name=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());

			EQUAL173=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_select2902); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EQUAL173_tree = (Object)adaptor.create(EQUAL173);
			adaptor.addChild(root_0, EQUAL173_tree);
			}

			t=(Token)match(input,76,FOLLOW_76_in_select2906); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			t_tree = (Object)adaptor.create(t);
			adaptor.addChild(root_0, t_tree);
			}

			pushFollow(FOLLOW_data_type_in_select2910);
			typeStr=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr.getTree());

			pushFollow(FOLLOW_result_in_select2914);
			condValue=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, condValue.getTree());

			COMMA174=(Token)match(input,COMMA,FOLLOW_COMMA_in_select2916); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA174_tree = (Object)adaptor.create(COMMA174);
			adaptor.addChild(root_0, COMMA174_tree);
			}

			pushFollow(FOLLOW_data_type_in_select2924);
			typeStr1=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr1.getTree());

			pushFollow(FOLLOW_result_in_select2928);
			valueStr1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, valueStr1.getTree());

			COMMA175=(Token)match(input,COMMA,FOLLOW_COMMA_in_select2930); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA175_tree = (Object)adaptor.create(COMMA175);
			adaptor.addChild(root_0, COMMA175_tree);
			}

			pushFollow(FOLLOW_data_type_in_select2938);
			typeStr2=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr2.getTree());

			pushFollow(FOLLOW_result_in_select2942);
			valueStr2=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, valueStr2.getTree());

			if ( state.backtracking==0 ) {
			       		setSelectInstr((name!=null?input.toString(name.start,name.stop):null),(typeStr!=null?input.toString(typeStr.start,typeStr.stop):null), (condValue!=null?input.toString(condValue.start,condValue.stop):null), (typeStr1!=null?input.toString(typeStr1.start,typeStr1.stop):null), (valueStr1!=null?input.toString(valueStr1.start,valueStr1.stop):null),
			       						(typeStr2!=null?input.toString(typeStr2.start,typeStr2.stop):null), (valueStr2!=null?input.toString(valueStr2.start,valueStr2.stop):null), (t!=null?t.getLine():0), 0);
			       }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 42, select_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "select"


	public static class cast_instruction_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cast_instruction"
	// llvmGrammar.g:626:1: cast_instruction : name= result e= EQUAL cast_instr_rhs[$name.text, $e.line] ;
	public final llvmGrammarParser.cast_instruction_return cast_instruction() throws RecognitionException {
		llvmGrammarParser.cast_instruction_return retval = new llvmGrammarParser.cast_instruction_return();
		retval.start = input.LT(1);
		int cast_instruction_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope cast_instr_rhs176 =null;

		Object e_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

			// llvmGrammar.g:626:18: (name= result e= EQUAL cast_instr_rhs[$name.text, $e.line] )
			// llvmGrammar.g:626:20: name= result e= EQUAL cast_instr_rhs[$name.text, $e.line]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_cast_instruction2965);
			name=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());

			e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_cast_instruction2969); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			pushFollow(FOLLOW_cast_instr_rhs_in_cast_instruction2971);
			cast_instr_rhs176=cast_instr_rhs((name!=null?input.toString(name.start,name.stop):null), (e!=null?e.getLine():0));
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, cast_instr_rhs176.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 43, cast_instruction_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cast_instruction"


	public static class cast_instr_rhs_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cast_instr_rhs"
	// llvmGrammar.g:628:1: cast_instr_rhs[String result, int line] : type= CAST_TYPE ( START_PARANTHESIS )? source= data_type value1= result 'to' target= data_type ( END_PARANTHESIS )? ;
	public final llvmGrammarParser.cast_instr_rhs_return cast_instr_rhs(String result, int line) throws RecognitionException {
		llvmGrammarParser.cast_instr_rhs_return retval = new llvmGrammarParser.cast_instr_rhs_return();
		retval.start = input.LT(1);
		int cast_instr_rhs_StartIndex = input.index();

		Object root_0 = null;

		Token type=null;
		Token START_PARANTHESIS177=null;
		Token string_literal178=null;
		Token END_PARANTHESIS179=null;
		ParserRuleReturnScope source =null;
		ParserRuleReturnScope value1 =null;
		ParserRuleReturnScope target =null;

		Object type_tree=null;
		Object START_PARANTHESIS177_tree=null;
		Object string_literal178_tree=null;
		Object END_PARANTHESIS179_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

			// llvmGrammar.g:628:41: (type= CAST_TYPE ( START_PARANTHESIS )? source= data_type value1= result 'to' target= data_type ( END_PARANTHESIS )? )
			// llvmGrammar.g:628:43: type= CAST_TYPE ( START_PARANTHESIS )? source= data_type value1= result 'to' target= data_type ( END_PARANTHESIS )?
			{
			root_0 = (Object)adaptor.nil();


			type=(Token)match(input,CAST_TYPE,FOLLOW_CAST_TYPE_in_cast_instr_rhs2984); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			type_tree = (Object)adaptor.create(type);
			adaptor.addChild(root_0, type_tree);
			}

			// llvmGrammar.g:628:58: ( START_PARANTHESIS )?
			int alt64=2;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==START_PARANTHESIS) ) {
				int LA64_1 = input.LA(2);
				if ( (synpred92_llvmGrammar()) ) {
					alt64=1;
				}
			}
			switch (alt64) {
				case 1 :
					// llvmGrammar.g:628:58: START_PARANTHESIS
					{
					START_PARANTHESIS177=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_cast_instr_rhs2986); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					START_PARANTHESIS177_tree = (Object)adaptor.create(START_PARANTHESIS177);
					adaptor.addChild(root_0, START_PARANTHESIS177_tree);
					}

					}
					break;

			}

			pushFollow(FOLLOW_data_type_in_cast_instr_rhs2991);
			source=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, source.getTree());

			pushFollow(FOLLOW_result_in_cast_instr_rhs3008);
			value1=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, value1.getTree());

			string_literal178=(Token)match(input,81,FOLLOW_81_in_cast_instr_rhs3010); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal178_tree = (Object)adaptor.create(string_literal178);
			adaptor.addChild(root_0, string_literal178_tree);
			}

			pushFollow(FOLLOW_data_type_in_cast_instr_rhs3014);
			target=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, target.getTree());

			// llvmGrammar.g:629:49: ( END_PARANTHESIS )?
			int alt65=2;
			int LA65_0 = input.LA(1);
			if ( (LA65_0==END_PARANTHESIS) ) {
				alt65=1;
			}
			switch (alt65) {
				case 1 :
					// llvmGrammar.g:629:49: END_PARANTHESIS
					{
					END_PARANTHESIS179=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_cast_instr_rhs3016); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_PARANTHESIS179_tree = (Object)adaptor.create(END_PARANTHESIS179);
					adaptor.addChild(root_0, END_PARANTHESIS179_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
														setCastInstr(result, (type!=null?type.getText():null), (source!=null?input.toString(source.start,source.stop):null), 
														(value1!=null?input.toString(value1.start,value1.stop):null), (target!=null?input.toString(target.start,target.stop):null), line, 0);
													}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 44, cast_instr_rhs_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cast_instr_rhs"


	public static class global_var_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "global_var"
	// llvmGrammar.g:635:1: global_var : ( global_array | structure_decl | structure_obj | string_constant | global_variable );
	public final llvmGrammarParser.global_var_return global_var() throws RecognitionException {
		llvmGrammarParser.global_var_return retval = new llvmGrammarParser.global_var_return();
		retval.start = input.LT(1);
		int global_var_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope global_array180 =null;
		ParserRuleReturnScope structure_decl181 =null;
		ParserRuleReturnScope structure_obj182 =null;
		ParserRuleReturnScope string_constant183 =null;
		ParserRuleReturnScope global_variable184 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

			// llvmGrammar.g:635:12: ( global_array | structure_decl | structure_obj | string_constant | global_variable )
			int alt66=5;
			switch ( input.LA(1) ) {
			case LOCAL_PREFIX:
				{
				int LA66_1 = input.LA(2);
				if ( (synpred94_llvmGrammar()) ) {
					alt66=1;
				}
				else if ( (synpred95_llvmGrammar()) ) {
					alt66=2;
				}
				else if ( (synpred96_llvmGrammar()) ) {
					alt66=3;
				}
				else if ( (true) ) {
					alt66=5;
				}

				}
				break;
			case FLOATING_LITERAL:
			case ID:
			case NUMBER:
				{
				int LA66_2 = input.LA(2);
				if ( (synpred94_llvmGrammar()) ) {
					alt66=1;
				}
				else if ( (synpred96_llvmGrammar()) ) {
					alt66=3;
				}
				else if ( (true) ) {
					alt66=5;
				}

				}
				break;
			case FALSE:
			case TRUE:
				{
				int LA66_3 = input.LA(2);
				if ( (synpred94_llvmGrammar()) ) {
					alt66=1;
				}
				else if ( (synpred96_llvmGrammar()) ) {
					alt66=3;
				}
				else if ( (true) ) {
					alt66=5;
				}

				}
				break;
			case 83:
				{
				int LA66_4 = input.LA(2);
				if ( (synpred94_llvmGrammar()) ) {
					alt66=1;
				}
				else if ( (synpred96_llvmGrammar()) ) {
					alt66=3;
				}
				else if ( (true) ) {
					alt66=5;
				}

				}
				break;
			case GLOBAL_PREFIX:
				{
				int LA66_5 = input.LA(2);
				if ( (synpred94_llvmGrammar()) ) {
					alt66=1;
				}
				else if ( (synpred96_llvmGrammar()) ) {
					alt66=3;
				}
				else if ( (synpred97_llvmGrammar()) ) {
					alt66=4;
				}
				else if ( (true) ) {
					alt66=5;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 66, 0, input);
				throw nvae;
			}
			switch (alt66) {
				case 1 :
					// llvmGrammar.g:635:13: global_array
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_global_array_in_global_var3037);
					global_array180=global_array();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, global_array180.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:635:28: structure_decl
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_structure_decl_in_global_var3041);
					structure_decl181=structure_decl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, structure_decl181.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:635:45: structure_obj
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_structure_obj_in_global_var3045);
					structure_obj182=structure_obj();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, structure_obj182.getTree());

					}
					break;
				case 4 :
					// llvmGrammar.g:635:60: string_constant
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_string_constant_in_global_var3048);
					string_constant183=string_constant();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, string_constant183.getTree());

					}
					break;
				case 5 :
					// llvmGrammar.g:635:78: global_variable
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_global_variable_in_global_var3052);
					global_variable184=global_variable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, global_variable184.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 45, global_var_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "global_var"


	public static class global_array_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "global_array"
	// llvmGrammar.g:637:1: global_array : e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (unnamed= 'unnamed_addr' )? (e2= 'constant' )? (typeSt= array_data_type )? initial= list_of_initial_values COMMA ALIGN align= NUMBER ;
	public final llvmGrammarParser.global_array_return global_array() throws RecognitionException {
		llvmGrammarParser.global_array_return retval = new llvmGrammarParser.global_array_return();
		retval.start = input.LT(1);
		int global_array_StartIndex = input.index();

		Object root_0 = null;

		Token e0=null;
		Token e1=null;
		Token unnamed=null;
		Token e2=null;
		Token align=null;
		Token string_literal185=null;
		Token COMMA186=null;
		Token ALIGN187=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope typeSt =null;
		ParserRuleReturnScope initial =null;

		Object e0_tree=null;
		Object e1_tree=null;
		Object unnamed_tree=null;
		Object e2_tree=null;
		Object align_tree=null;
		Object string_literal185_tree=null;
		Object COMMA186_tree=null;
		Object ALIGN187_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }

			// llvmGrammar.g:637:14: (e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (unnamed= 'unnamed_addr' )? (e2= 'constant' )? (typeSt= array_data_type )? initial= list_of_initial_values COMMA ALIGN align= NUMBER )
			// llvmGrammar.g:637:17: e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (unnamed= 'unnamed_addr' )? (e2= 'constant' )? (typeSt= array_data_type )? initial= list_of_initial_values COMMA ALIGN align= NUMBER
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_global_array3064);
			e=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

			e0=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_global_array3068); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e0_tree = (Object)adaptor.create(e0);
			adaptor.addChild(root_0, e0_tree);
			}

			// llvmGrammar.g:637:37: (e1= LINKAGE_TYPE )?
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==LINKAGE_TYPE) ) {
				alt67=1;
			}
			switch (alt67) {
				case 1 :
					// llvmGrammar.g:637:37: e1= LINKAGE_TYPE
					{
					e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_global_array3072); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e1_tree = (Object)adaptor.create(e1);
					adaptor.addChild(root_0, e1_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:637:52: ( 'global' )?
			int alt68=2;
			int LA68_0 = input.LA(1);
			if ( (LA68_0==70) ) {
				alt68=1;
			}
			switch (alt68) {
				case 1 :
					// llvmGrammar.g:637:53: 'global'
					{
					string_literal185=(Token)match(input,70,FOLLOW_70_in_global_array3076); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal185_tree = (Object)adaptor.create(string_literal185);
					adaptor.addChild(root_0, string_literal185_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:637:64: (unnamed= 'unnamed_addr' )?
			int alt69=2;
			int LA69_0 = input.LA(1);
			if ( (LA69_0==84) ) {
				alt69=1;
			}
			switch (alt69) {
				case 1 :
					// llvmGrammar.g:637:65: unnamed= 'unnamed_addr'
					{
					unnamed=(Token)match(input,84,FOLLOW_84_in_global_array3083); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					unnamed_tree = (Object)adaptor.create(unnamed);
					adaptor.addChild(root_0, unnamed_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:638:7: (e2= 'constant' )?
			int alt70=2;
			int LA70_0 = input.LA(1);
			if ( (LA70_0==65) ) {
				alt70=1;
			}
			switch (alt70) {
				case 1 :
					// llvmGrammar.g:638:7: e2= 'constant'
					{
					e2=(Token)match(input,65,FOLLOW_65_in_global_array3094); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e2_tree = (Object)adaptor.create(e2);
					adaptor.addChild(root_0, e2_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:638:27: (typeSt= array_data_type )?
			int alt71=2;
			int LA71_0 = input.LA(1);
			if ( (LA71_0==LOCAL_PREFIX||LA71_0==PRIMITIVE_DATA_TYPE||LA71_0==START_ANGULAR||LA71_0==START_PARANTHESIS) ) {
				alt71=1;
			}
			else if ( (LA71_0==START_SQUARE_BR) ) {
				int LA71_2 = input.LA(2);
				if ( (LA71_2==NUMBER) ) {
					alt71=1;
				}
			}
			switch (alt71) {
				case 1 :
					// llvmGrammar.g:638:27: typeSt= array_data_type
					{
					pushFollow(FOLLOW_array_data_type_in_global_array3101);
					typeSt=array_data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSt.getTree());

					}
					break;

			}

			pushFollow(FOLLOW_list_of_initial_values_in_global_array3107);
			initial=list_of_initial_values();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, initial.getTree());

			COMMA186=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_array3109); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA186_tree = (Object)adaptor.create(COMMA186);
			adaptor.addChild(root_0, COMMA186_tree);
			}

			ALIGN187=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_global_array3111); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ALIGN187_tree = (Object)adaptor.create(ALIGN187);
			adaptor.addChild(root_0, ALIGN187_tree);
			}

			align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_global_array3115); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			align_tree = (Object)adaptor.create(align);
			adaptor.addChild(root_0, align_tree);
			}

			if ( state.backtracking==0 ) {	
							setGlobalVariable((e!=null?input.toString(e.start,e.stop):null), (e1!=null?e1.getText():null), (typeSt!=null?input.toString(typeSt.start,typeSt.stop):null), (initial!=null?input.toString(initial.start,initial.stop):null), (e2!=null?e2.getText():null), 
											  (align!=null?align.getText():null), (unnamed!=null?unnamed.getText():null), "array", (e0!=null?e0.getLine():0), 0);
						}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 46, global_array_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "global_array"


	public static class array_data_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "array_data_type"
	// llvmGrammar.g:644:1: array_data_type : ( data_type ) ;
	public final llvmGrammarParser.array_data_type_return array_data_type() throws RecognitionException {
		llvmGrammarParser.array_data_type_return retval = new llvmGrammarParser.array_data_type_return();
		retval.start = input.LT(1);
		int array_data_type_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope data_type188 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }

			// llvmGrammar.g:644:17: ( ( data_type ) )
			// llvmGrammar.g:644:19: ( data_type )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:644:19: ( data_type )
			// llvmGrammar.g:644:20: data_type
			{
			pushFollow(FOLLOW_data_type_in_array_data_type3133);
			data_type188=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type188.getTree());

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 47, array_data_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "array_data_type"


	public static class list_of_initial_values_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list_of_initial_values"
	// llvmGrammar.g:646:1: list_of_initial_values : ( (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR ) | ZEROINITIALIZER | ID STRING_LITERAL );
	public final llvmGrammarParser.list_of_initial_values_return list_of_initial_values() throws RecognitionException {
		llvmGrammarParser.list_of_initial_values_return retval = new llvmGrammarParser.list_of_initial_values_return();
		retval.start = input.LT(1);
		int list_of_initial_values_StartIndex = input.index();

		Object root_0 = null;

		Token e0=null;
		Token COMMA192=null;
		Token END_SQUARE_BR196=null;
		Token ZEROINITIALIZER197=null;
		Token ID198=null;
		Token STRING_LITERAL199=null;
		ParserRuleReturnScope data_type189 =null;
		ParserRuleReturnScope array_initial_value190 =null;
		ParserRuleReturnScope getelementptr_rhs191 =null;
		ParserRuleReturnScope data_type193 =null;
		ParserRuleReturnScope array_initial_value194 =null;
		ParserRuleReturnScope getelementptr_rhs195 =null;

		Object e0_tree=null;
		Object COMMA192_tree=null;
		Object END_SQUARE_BR196_tree=null;
		Object ZEROINITIALIZER197_tree=null;
		Object ID198_tree=null;
		Object STRING_LITERAL199_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }

			// llvmGrammar.g:646:24: ( (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR ) | ZEROINITIALIZER | ID STRING_LITERAL )
			int alt75=3;
			switch ( input.LA(1) ) {
			case START_SQUARE_BR:
				{
				alt75=1;
				}
				break;
			case ZEROINITIALIZER:
				{
				alt75=2;
				}
				break;
			case ID:
				{
				alt75=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 75, 0, input);
				throw nvae;
			}
			switch (alt75) {
				case 1 :
					// llvmGrammar.g:646:26: (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:646:26: (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR )
					// llvmGrammar.g:646:27: e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR
					{
					e0=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_list_of_initial_values3148); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e0_tree = (Object)adaptor.create(e0);
					adaptor.addChild(root_0, e0_tree);
					}

					// llvmGrammar.g:646:45: ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )
					// llvmGrammar.g:646:46: data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] )
					{
					pushFollow(FOLLOW_data_type_in_list_of_initial_values3150);
					data_type189=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type189.getTree());

					// llvmGrammar.g:646:56: ( array_initial_value | getelementptr_rhs[null, $e0.line] )
					int alt72=2;
					int LA72_0 = input.LA(1);
					if ( (LA72_0==ID||LA72_0==NUMBER||(LA72_0 >= START_SQUARE_BR && LA72_0 <= STRING_LITERAL)||LA72_0==ZEROINITIALIZER) ) {
						alt72=1;
					}
					else if ( (LA72_0==69) ) {
						alt72=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 72, 0, input);
						throw nvae;
					}

					switch (alt72) {
						case 1 :
							// llvmGrammar.g:646:57: array_initial_value
							{
							pushFollow(FOLLOW_array_initial_value_in_list_of_initial_values3153);
							array_initial_value190=array_initial_value();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value190.getTree());

							}
							break;
						case 2 :
							// llvmGrammar.g:646:78: getelementptr_rhs[null, $e0.line]
							{
							pushFollow(FOLLOW_getelementptr_rhs_in_list_of_initial_values3156);
							getelementptr_rhs191=getelementptr_rhs(null, (e0!=null?e0.getLine():0));
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs191.getTree());

							}
							break;

					}

					}

					// llvmGrammar.g:647:9: ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )*
					loop74:
					while (true) {
						int alt74=2;
						int LA74_0 = input.LA(1);
						if ( (LA74_0==COMMA) ) {
							alt74=1;
						}

						switch (alt74) {
						case 1 :
							// llvmGrammar.g:647:10: COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] )
							{
							COMMA192=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_initial_values3171); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA192_tree = (Object)adaptor.create(COMMA192);
							adaptor.addChild(root_0, COMMA192_tree);
							}

							pushFollow(FOLLOW_data_type_in_list_of_initial_values3173);
							data_type193=data_type();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type193.getTree());

							// llvmGrammar.g:647:26: ( array_initial_value | getelementptr_rhs[null, $e0.line] )
							int alt73=2;
							int LA73_0 = input.LA(1);
							if ( (LA73_0==ID||LA73_0==NUMBER||(LA73_0 >= START_SQUARE_BR && LA73_0 <= STRING_LITERAL)||LA73_0==ZEROINITIALIZER) ) {
								alt73=1;
							}
							else if ( (LA73_0==69) ) {
								alt73=2;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								NoViableAltException nvae =
									new NoViableAltException("", 73, 0, input);
								throw nvae;
							}

							switch (alt73) {
								case 1 :
									// llvmGrammar.g:647:27: array_initial_value
									{
									pushFollow(FOLLOW_array_initial_value_in_list_of_initial_values3176);
									array_initial_value194=array_initial_value();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value194.getTree());

									}
									break;
								case 2 :
									// llvmGrammar.g:647:48: getelementptr_rhs[null, $e0.line]
									{
									pushFollow(FOLLOW_getelementptr_rhs_in_list_of_initial_values3179);
									getelementptr_rhs195=getelementptr_rhs(null, (e0!=null?e0.getLine():0));
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs195.getTree());

									}
									break;

							}

							}
							break;

						default :
							break loop74;
						}
					}

					END_SQUARE_BR196=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_list_of_initial_values3185); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_SQUARE_BR196_tree = (Object)adaptor.create(END_SQUARE_BR196);
					adaptor.addChild(root_0, END_SQUARE_BR196_tree);
					}

					}

					}
					break;
				case 2 :
					// llvmGrammar.g:648:11: ZEROINITIALIZER
					{
					root_0 = (Object)adaptor.nil();


					ZEROINITIALIZER197=(Token)match(input,ZEROINITIALIZER,FOLLOW_ZEROINITIALIZER_in_list_of_initial_values3200); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ZEROINITIALIZER197_tree = (Object)adaptor.create(ZEROINITIALIZER197);
					adaptor.addChild(root_0, ZEROINITIALIZER197_tree);
					}

					}
					break;
				case 3 :
					// llvmGrammar.g:649:11: ID STRING_LITERAL
					{
					root_0 = (Object)adaptor.nil();


					ID198=(Token)match(input,ID,FOLLOW_ID_in_list_of_initial_values3213); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID198_tree = (Object)adaptor.create(ID198);
					adaptor.addChild(root_0, ID198_tree);
					}

					STRING_LITERAL199=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_list_of_initial_values3215); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STRING_LITERAL199_tree = (Object)adaptor.create(STRING_LITERAL199);
					adaptor.addChild(root_0, STRING_LITERAL199_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 48, list_of_initial_values_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "list_of_initial_values"


	public static class array_initial_value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "array_initial_value"
	// llvmGrammar.g:651:1: array_initial_value : ( ( NUMBER | STRING_LITERAL ) | list_of_initial_values );
	public final llvmGrammarParser.array_initial_value_return array_initial_value() throws RecognitionException {
		llvmGrammarParser.array_initial_value_return retval = new llvmGrammarParser.array_initial_value_return();
		retval.start = input.LT(1);
		int array_initial_value_StartIndex = input.index();

		Object root_0 = null;

		Token set200=null;
		ParserRuleReturnScope list_of_initial_values201 =null;

		Object set200_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }

			// llvmGrammar.g:651:21: ( ( NUMBER | STRING_LITERAL ) | list_of_initial_values )
			int alt76=2;
			int LA76_0 = input.LA(1);
			if ( (LA76_0==NUMBER||LA76_0==STRING_LITERAL) ) {
				alt76=1;
			}
			else if ( (LA76_0==ID||LA76_0==START_SQUARE_BR||LA76_0==ZEROINITIALIZER) ) {
				alt76=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 76, 0, input);
				throw nvae;
			}

			switch (alt76) {
				case 1 :
					// llvmGrammar.g:651:23: ( NUMBER | STRING_LITERAL )
					{
					root_0 = (Object)adaptor.nil();


					set200=input.LT(1);
					if ( input.LA(1)==NUMBER||input.LA(1)==STRING_LITERAL ) {
						input.consume();
						if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set200));
						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 2 :
					// llvmGrammar.g:651:50: list_of_initial_values
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_list_of_initial_values_in_array_initial_value3234);
					list_of_initial_values201=list_of_initial_values();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, list_of_initial_values201.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 49, array_initial_value_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "array_initial_value"


	public static class global_variable_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "global_variable"
	// llvmGrammar.g:653:1: global_variable : e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? typeSt= data_type (d= global_variable_initial_value COMMA ALIGN align= NUMBER )? ;
	public final llvmGrammarParser.global_variable_return global_variable() throws RecognitionException {
		llvmGrammarParser.global_variable_return retval = new llvmGrammarParser.global_variable_return();
		retval.start = input.LT(1);
		int global_variable_StartIndex = input.index();

		Object root_0 = null;

		Token e0=null;
		Token e1=null;
		Token e2=null;
		Token align=null;
		Token string_literal202=null;
		Token COMMA203=null;
		Token ALIGN204=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope typeSt =null;
		ParserRuleReturnScope d =null;

		Object e0_tree=null;
		Object e1_tree=null;
		Object e2_tree=null;
		Object align_tree=null;
		Object string_literal202_tree=null;
		Object COMMA203_tree=null;
		Object ALIGN204_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }

			// llvmGrammar.g:653:17: (e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? typeSt= data_type (d= global_variable_initial_value COMMA ALIGN align= NUMBER )? )
			// llvmGrammar.g:653:19: e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? typeSt= data_type (d= global_variable_initial_value COMMA ALIGN align= NUMBER )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_global_variable3244);
			e=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

			e0=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_global_variable3249); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e0_tree = (Object)adaptor.create(e0);
			adaptor.addChild(root_0, e0_tree);
			}

			// llvmGrammar.g:653:38: (e1= LINKAGE_TYPE )?
			int alt77=2;
			int LA77_0 = input.LA(1);
			if ( (LA77_0==LINKAGE_TYPE) ) {
				alt77=1;
			}
			switch (alt77) {
				case 1 :
					// llvmGrammar.g:653:39: e1= LINKAGE_TYPE
					{
					e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_global_variable3256); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e1_tree = (Object)adaptor.create(e1);
					adaptor.addChild(root_0, e1_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:653:60: ( 'global' )?
			int alt78=2;
			int LA78_0 = input.LA(1);
			if ( (LA78_0==70) ) {
				alt78=1;
			}
			switch (alt78) {
				case 1 :
					// llvmGrammar.g:653:61: 'global'
					{
					string_literal202=(Token)match(input,70,FOLLOW_70_in_global_variable3262); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal202_tree = (Object)adaptor.create(string_literal202);
					adaptor.addChild(root_0, string_literal202_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:653:72: (e2= 'constant' )?
			int alt79=2;
			int LA79_0 = input.LA(1);
			if ( (LA79_0==65) ) {
				alt79=1;
			}
			switch (alt79) {
				case 1 :
					// llvmGrammar.g:653:73: e2= 'constant'
					{
					e2=(Token)match(input,65,FOLLOW_65_in_global_variable3269); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e2_tree = (Object)adaptor.create(e2);
					adaptor.addChild(root_0, e2_tree);
					}

					}
					break;

			}

			pushFollow(FOLLOW_data_type_in_global_variable3283);
			typeSt=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSt.getTree());

			// llvmGrammar.g:654:24: (d= global_variable_initial_value COMMA ALIGN align= NUMBER )?
			int alt80=2;
			int LA80_0 = input.LA(1);
			if ( (LA80_0==NUMBER) ) {
				int LA80_1 = input.LA(2);
				if ( (LA80_1==COMMA) ) {
					alt80=1;
				}
			}
			else if ( (LA80_0==CAST_TYPE||LA80_0==69) ) {
				alt80=1;
			}
			switch (alt80) {
				case 1 :
					// llvmGrammar.g:654:25: d= global_variable_initial_value COMMA ALIGN align= NUMBER
					{
					pushFollow(FOLLOW_global_variable_initial_value_in_global_variable3288);
					d=global_variable_initial_value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

					COMMA203=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_variable3290); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA203_tree = (Object)adaptor.create(COMMA203);
					adaptor.addChild(root_0, COMMA203_tree);
					}

					ALIGN204=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_global_variable3292); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALIGN204_tree = (Object)adaptor.create(ALIGN204);
					adaptor.addChild(root_0, ALIGN204_tree);
					}

					align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_global_variable3297); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					align_tree = (Object)adaptor.create(align);
					adaptor.addChild(root_0, align_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {   
			      				setGlobalVariable((e!=null?input.toString(e.start,e.stop):null), (e1!=null?e1.getText():null), (typeSt!=null?input.toString(typeSt.start,typeSt.stop):null), (d!=null?((llvmGrammarParser.global_variable_initial_value_return)d).ini:null), (e2!=null?e2.getText():null), (align!=null?align.getText():null), null,
			      				"global_variable", (e0!=null?e0.getLine():0), 0);
			      			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 50, global_variable_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "global_variable"


	public static class global_variable_initial_value_return extends ParserRuleReturnScope {
		public String ini;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "global_variable_initial_value"
	// llvmGrammar.g:660:1: global_variable_initial_value returns [String ini] : (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] ) ;
	public final llvmGrammarParser.global_variable_initial_value_return global_variable_initial_value() throws RecognitionException {
		llvmGrammarParser.global_variable_initial_value_return retval = new llvmGrammarParser.global_variable_initial_value_return();
		retval.start = input.LT(1);
		int global_variable_initial_value_StartIndex = input.index();

		Object root_0 = null;

		Token initial=null;
		ParserRuleReturnScope cast_instr_rhs205 =null;
		ParserRuleReturnScope getelementptr_rhs206 =null;

		Object initial_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }

			// llvmGrammar.g:660:52: ( (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] ) )
			// llvmGrammar.g:660:54: (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:660:54: (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] )
			int alt81=3;
			switch ( input.LA(1) ) {
			case NUMBER:
				{
				alt81=1;
				}
				break;
			case CAST_TYPE:
				{
				alt81=2;
				}
				break;
			case 69:
				{
				alt81=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 81, 0, input);
				throw nvae;
			}
			switch (alt81) {
				case 1 :
					// llvmGrammar.g:660:55: initial= NUMBER
					{
					initial=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_global_variable_initial_value3332); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					initial_tree = (Object)adaptor.create(initial);
					adaptor.addChild(root_0, initial_tree);
					}

					}
					break;
				case 2 :
					// llvmGrammar.g:661:16: cast_instr_rhs[null, $initial.line]
					{
					pushFollow(FOLLOW_cast_instr_rhs_in_global_variable_initial_value3350);
					cast_instr_rhs205=cast_instr_rhs(null, (initial!=null?initial.getLine():0));
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cast_instr_rhs205.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:662:16: getelementptr_rhs[null, $initial.line]
					{
					pushFollow(FOLLOW_getelementptr_rhs_in_global_variable_initial_value3369);
					getelementptr_rhs206=getelementptr_rhs(null, (initial!=null?initial.getLine():0));
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs206.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			  												 	retval.ini = (initial!=null?initial.getText():null);
			  												 }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 51, global_variable_initial_value_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "global_variable_initial_value"


	public static class structure_obj_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "structure_obj"
	// llvmGrammar.g:667:1: structure_obj : e= result eq= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? LOCAL_PREFIX typeSt= ID (zeroinitial= ZEROINITIALIZER |init= struct_init_value ) COMMA ALIGN align= NUMBER ;
	public final llvmGrammarParser.structure_obj_return structure_obj() throws RecognitionException {
		llvmGrammarParser.structure_obj_return retval = new llvmGrammarParser.structure_obj_return();
		retval.start = input.LT(1);
		int structure_obj_StartIndex = input.index();

		Object root_0 = null;

		Token eq=null;
		Token e1=null;
		Token e2=null;
		Token typeSt=null;
		Token zeroinitial=null;
		Token align=null;
		Token string_literal207=null;
		Token LOCAL_PREFIX208=null;
		Token COMMA209=null;
		Token ALIGN210=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope init =null;

		Object eq_tree=null;
		Object e1_tree=null;
		Object e2_tree=null;
		Object typeSt_tree=null;
		Object zeroinitial_tree=null;
		Object align_tree=null;
		Object string_literal207_tree=null;
		Object LOCAL_PREFIX208_tree=null;
		Object COMMA209_tree=null;
		Object ALIGN210_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }

			// llvmGrammar.g:667:15: (e= result eq= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? LOCAL_PREFIX typeSt= ID (zeroinitial= ZEROINITIALIZER |init= struct_init_value ) COMMA ALIGN align= NUMBER )
			// llvmGrammar.g:667:17: e= result eq= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? LOCAL_PREFIX typeSt= ID (zeroinitial= ZEROINITIALIZER |init= struct_init_value ) COMMA ALIGN align= NUMBER
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_result_in_structure_obj3400);
			e=result();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

			eq=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_structure_obj3404); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			eq_tree = (Object)adaptor.create(eq);
			adaptor.addChild(root_0, eq_tree);
			}

			// llvmGrammar.g:667:37: (e1= LINKAGE_TYPE )?
			int alt82=2;
			int LA82_0 = input.LA(1);
			if ( (LA82_0==LINKAGE_TYPE) ) {
				alt82=1;
			}
			switch (alt82) {
				case 1 :
					// llvmGrammar.g:667:37: e1= LINKAGE_TYPE
					{
					e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_structure_obj3408); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e1_tree = (Object)adaptor.create(e1);
					adaptor.addChild(root_0, e1_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:667:52: ( 'global' )?
			int alt83=2;
			int LA83_0 = input.LA(1);
			if ( (LA83_0==70) ) {
				alt83=1;
			}
			switch (alt83) {
				case 1 :
					// llvmGrammar.g:667:52: 'global'
					{
					string_literal207=(Token)match(input,70,FOLLOW_70_in_structure_obj3411); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal207_tree = (Object)adaptor.create(string_literal207);
					adaptor.addChild(root_0, string_literal207_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:667:62: (e2= 'constant' )?
			int alt84=2;
			int LA84_0 = input.LA(1);
			if ( (LA84_0==65) ) {
				alt84=1;
			}
			switch (alt84) {
				case 1 :
					// llvmGrammar.g:667:63: e2= 'constant'
					{
					e2=(Token)match(input,65,FOLLOW_65_in_structure_obj3417); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e2_tree = (Object)adaptor.create(e2);
					adaptor.addChild(root_0, e2_tree);
					}

					}
					break;

			}

			LOCAL_PREFIX208=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_structure_obj3421); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			LOCAL_PREFIX208_tree = (Object)adaptor.create(LOCAL_PREFIX208);
			adaptor.addChild(root_0, LOCAL_PREFIX208_tree);
			}

			typeSt=(Token)match(input,ID,FOLLOW_ID_in_structure_obj3425); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			typeSt_tree = (Object)adaptor.create(typeSt);
			adaptor.addChild(root_0, typeSt_tree);
			}

			// llvmGrammar.g:668:5: (zeroinitial= ZEROINITIALIZER |init= struct_init_value )
			int alt85=2;
			int LA85_0 = input.LA(1);
			if ( (LA85_0==ZEROINITIALIZER) ) {
				alt85=1;
			}
			else if ( (LA85_0==START_CURLY) ) {
				alt85=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 85, 0, input);
				throw nvae;
			}

			switch (alt85) {
				case 1 :
					// llvmGrammar.g:668:6: zeroinitial= ZEROINITIALIZER
					{
					zeroinitial=(Token)match(input,ZEROINITIALIZER,FOLLOW_ZEROINITIALIZER_in_structure_obj3436); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					zeroinitial_tree = (Object)adaptor.create(zeroinitial);
					adaptor.addChild(root_0, zeroinitial_tree);
					}

					}
					break;
				case 2 :
					// llvmGrammar.g:668:36: init= struct_init_value
					{
					pushFollow(FOLLOW_struct_init_value_in_structure_obj3443);
					init=struct_init_value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, init.getTree());

					}
					break;

			}

			COMMA209=(Token)match(input,COMMA,FOLLOW_COMMA_in_structure_obj3446); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COMMA209_tree = (Object)adaptor.create(COMMA209);
			adaptor.addChild(root_0, COMMA209_tree);
			}

			ALIGN210=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_structure_obj3448); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ALIGN210_tree = (Object)adaptor.create(ALIGN210);
			adaptor.addChild(root_0, ALIGN210_tree);
			}

			align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_structure_obj3452); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			align_tree = (Object)adaptor.create(align);
			adaptor.addChild(root_0, align_tree);
			}

			if ( state.backtracking==0 ) {
						  	String initial = (zeroinitial != null)?	(zeroinitial!=null?zeroinitial.getText():null) : (init!=null?input.toString(init.start,init.stop):null);
							setGlobalVariable((e!=null?input.toString(e.start,e.stop):null), (e1!=null?e1.getText():null), (typeSt!=null?typeSt.getText():null), initial, (e2!=null?e2.getText():null), (align!=null?align.getText():null), null, "object", (eq!=null?eq.getLine():0), 0);
						  }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 52, structure_obj_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "structure_obj"


	public static class struct_init_value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "struct_init_value"
	// llvmGrammar.g:674:1: struct_init_value : START_CURLY ( data_type | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )* END_CURLY ;
	public final llvmGrammarParser.struct_init_value_return struct_init_value() throws RecognitionException {
		llvmGrammarParser.struct_init_value_return retval = new llvmGrammarParser.struct_init_value_return();
		retval.start = input.LT(1);
		int struct_init_value_StartIndex = input.index();

		Object root_0 = null;

		Token START_CURLY211=null;
		Token LOCAL_PREFIX213=null;
		Token ID214=null;
		Token NUMBER215=null;
		Token LETTER216=null;
		Token ID217=null;
		Token STRING_LITERAL218=null;
		Token string_literal219=null;
		Token COMMA221=null;
		Token LOCAL_PREFIX223=null;
		Token ID224=null;
		Token NUMBER225=null;
		Token LETTER226=null;
		Token ID227=null;
		Token STRING_LITERAL228=null;
		Token string_literal229=null;
		Token END_CURLY231=null;
		ParserRuleReturnScope data_type212 =null;
		ParserRuleReturnScope array_initial_value220 =null;
		ParserRuleReturnScope data_type222 =null;
		ParserRuleReturnScope array_initial_value230 =null;

		Object START_CURLY211_tree=null;
		Object LOCAL_PREFIX213_tree=null;
		Object ID214_tree=null;
		Object NUMBER215_tree=null;
		Object LETTER216_tree=null;
		Object ID217_tree=null;
		Object STRING_LITERAL218_tree=null;
		Object string_literal219_tree=null;
		Object COMMA221_tree=null;
		Object LOCAL_PREFIX223_tree=null;
		Object ID224_tree=null;
		Object NUMBER225_tree=null;
		Object LETTER226_tree=null;
		Object ID227_tree=null;
		Object STRING_LITERAL228_tree=null;
		Object string_literal229_tree=null;
		Object END_CURLY231_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }

			// llvmGrammar.g:674:19: ( START_CURLY ( data_type | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )* END_CURLY )
			// llvmGrammar.g:674:21: START_CURLY ( data_type | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )* END_CURLY
			{
			root_0 = (Object)adaptor.nil();


			START_CURLY211=(Token)match(input,START_CURLY,FOLLOW_START_CURLY_in_struct_init_value3472); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			START_CURLY211_tree = (Object)adaptor.create(START_CURLY211);
			adaptor.addChild(root_0, START_CURLY211_tree);
			}

			// llvmGrammar.g:674:33: ( data_type | ( LOCAL_PREFIX ID ) )
			int alt86=2;
			int LA86_0 = input.LA(1);
			if ( (LA86_0==PRIMITIVE_DATA_TYPE||LA86_0==START_ANGULAR||(LA86_0 >= START_PARANTHESIS && LA86_0 <= START_SQUARE_BR)) ) {
				alt86=1;
			}
			else if ( (LA86_0==LOCAL_PREFIX) ) {
				int LA86_2 = input.LA(2);
				if ( (LA86_2==ID) ) {
					int LA86_3 = input.LA(3);
					if ( (synpred120_llvmGrammar()) ) {
						alt86=1;
					}
					else if ( (true) ) {
						alt86=2;
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 86, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 86, 0, input);
				throw nvae;
			}

			switch (alt86) {
				case 1 :
					// llvmGrammar.g:674:34: data_type
					{
					pushFollow(FOLLOW_data_type_in_struct_init_value3475);
					data_type212=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type212.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:674:45: ( LOCAL_PREFIX ID )
					{
					// llvmGrammar.g:674:45: ( LOCAL_PREFIX ID )
					// llvmGrammar.g:674:46: LOCAL_PREFIX ID
					{
					LOCAL_PREFIX213=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_init_value3479); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX213_tree = (Object)adaptor.create(LOCAL_PREFIX213);
					adaptor.addChild(root_0, LOCAL_PREFIX213_tree);
					}

					ID214=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3481); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID214_tree = (Object)adaptor.create(ID214);
					adaptor.addChild(root_0, ID214_tree);
					}

					}

					}
					break;

			}

			// llvmGrammar.g:675:6: ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value )
			int alt87=5;
			switch ( input.LA(1) ) {
			case NUMBER:
				{
				int LA87_1 = input.LA(2);
				if ( (synpred121_llvmGrammar()) ) {
					alt87=1;
				}
				else if ( (true) ) {
					alt87=5;
				}

				}
				break;
			case LETTER:
				{
				alt87=2;
				}
				break;
			case ID:
				{
				int LA87_3 = input.LA(2);
				if ( (LA87_3==STRING_LITERAL) ) {
					int LA87_7 = input.LA(3);
					if ( (synpred123_llvmGrammar()) ) {
						alt87=3;
					}
					else if ( (true) ) {
						alt87=5;
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 87, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 83:
				{
				alt87=4;
				}
				break;
			case START_SQUARE_BR:
			case STRING_LITERAL:
			case ZEROINITIALIZER:
				{
				alt87=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 87, 0, input);
				throw nvae;
			}
			switch (alt87) {
				case 1 :
					// llvmGrammar.g:675:7: NUMBER
					{
					NUMBER215=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_struct_init_value3491); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER215_tree = (Object)adaptor.create(NUMBER215);
					adaptor.addChild(root_0, NUMBER215_tree);
					}

					}
					break;
				case 2 :
					// llvmGrammar.g:675:15: LETTER
					{
					LETTER216=(Token)match(input,LETTER,FOLLOW_LETTER_in_struct_init_value3494); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LETTER216_tree = (Object)adaptor.create(LETTER216);
					adaptor.addChild(root_0, LETTER216_tree);
					}

					}
					break;
				case 3 :
					// llvmGrammar.g:675:23: ID STRING_LITERAL
					{
					ID217=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3497); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID217_tree = (Object)adaptor.create(ID217);
					adaptor.addChild(root_0, ID217_tree);
					}

					STRING_LITERAL218=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_struct_init_value3499); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STRING_LITERAL218_tree = (Object)adaptor.create(STRING_LITERAL218);
					adaptor.addChild(root_0, STRING_LITERAL218_tree);
					}

					}
					break;
				case 4 :
					// llvmGrammar.g:675:42: 'undef'
					{
					string_literal219=(Token)match(input,83,FOLLOW_83_in_struct_init_value3502); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal219_tree = (Object)adaptor.create(string_literal219);
					adaptor.addChild(root_0, string_literal219_tree);
					}

					}
					break;
				case 5 :
					// llvmGrammar.g:675:51: array_initial_value
					{
					pushFollow(FOLLOW_array_initial_value_in_struct_init_value3505);
					array_initial_value220=array_initial_value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value220.getTree());

					}
					break;

			}

			// llvmGrammar.g:676:6: ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )*
			loop90:
			while (true) {
				int alt90=2;
				int LA90_0 = input.LA(1);
				if ( (LA90_0==COMMA) ) {
					alt90=1;
				}

				switch (alt90) {
				case 1 :
					// llvmGrammar.g:676:7: COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value )
					{
					COMMA221=(Token)match(input,COMMA,FOLLOW_COMMA_in_struct_init_value3514); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA221_tree = (Object)adaptor.create(COMMA221);
					adaptor.addChild(root_0, COMMA221_tree);
					}

					// llvmGrammar.g:676:13: ( ( data_type ) | ( LOCAL_PREFIX ID ) )
					int alt88=2;
					int LA88_0 = input.LA(1);
					if ( (LA88_0==PRIMITIVE_DATA_TYPE||LA88_0==START_ANGULAR||(LA88_0 >= START_PARANTHESIS && LA88_0 <= START_SQUARE_BR)) ) {
						alt88=1;
					}
					else if ( (LA88_0==LOCAL_PREFIX) ) {
						int LA88_2 = input.LA(2);
						if ( (LA88_2==ID) ) {
							int LA88_3 = input.LA(3);
							if ( (synpred125_llvmGrammar()) ) {
								alt88=1;
							}
							else if ( (true) ) {
								alt88=2;
							}

						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 88, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 88, 0, input);
						throw nvae;
					}

					switch (alt88) {
						case 1 :
							// llvmGrammar.g:676:14: ( data_type )
							{
							// llvmGrammar.g:676:14: ( data_type )
							// llvmGrammar.g:676:15: data_type
							{
							pushFollow(FOLLOW_data_type_in_struct_init_value3518);
							data_type222=data_type();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type222.getTree());

							}

							}
							break;
						case 2 :
							// llvmGrammar.g:676:26: ( LOCAL_PREFIX ID )
							{
							// llvmGrammar.g:676:26: ( LOCAL_PREFIX ID )
							// llvmGrammar.g:676:27: LOCAL_PREFIX ID
							{
							LOCAL_PREFIX223=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_init_value3522); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							LOCAL_PREFIX223_tree = (Object)adaptor.create(LOCAL_PREFIX223);
							adaptor.addChild(root_0, LOCAL_PREFIX223_tree);
							}

							ID224=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3524); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							ID224_tree = (Object)adaptor.create(ID224);
							adaptor.addChild(root_0, ID224_tree);
							}

							}

							}
							break;

					}

					// llvmGrammar.g:676:44: ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value )
					int alt89=5;
					switch ( input.LA(1) ) {
					case NUMBER:
						{
						int LA89_1 = input.LA(2);
						if ( (synpred126_llvmGrammar()) ) {
							alt89=1;
						}
						else if ( (true) ) {
							alt89=5;
						}

						}
						break;
					case LETTER:
						{
						alt89=2;
						}
						break;
					case ID:
						{
						int LA89_3 = input.LA(2);
						if ( (LA89_3==STRING_LITERAL) ) {
							int LA89_7 = input.LA(3);
							if ( (synpred128_llvmGrammar()) ) {
								alt89=3;
							}
							else if ( (true) ) {
								alt89=5;
							}

						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 89, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 83:
						{
						alt89=4;
						}
						break;
					case START_SQUARE_BR:
					case STRING_LITERAL:
					case ZEROINITIALIZER:
						{
						alt89=5;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 89, 0, input);
						throw nvae;
					}
					switch (alt89) {
						case 1 :
							// llvmGrammar.g:676:46: NUMBER
							{
							NUMBER225=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_struct_init_value3529); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NUMBER225_tree = (Object)adaptor.create(NUMBER225);
							adaptor.addChild(root_0, NUMBER225_tree);
							}

							}
							break;
						case 2 :
							// llvmGrammar.g:676:53: LETTER
							{
							LETTER226=(Token)match(input,LETTER,FOLLOW_LETTER_in_struct_init_value3531); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							LETTER226_tree = (Object)adaptor.create(LETTER226);
							adaptor.addChild(root_0, LETTER226_tree);
							}

							}
							break;
						case 3 :
							// llvmGrammar.g:676:60: ID STRING_LITERAL
							{
							ID227=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3533); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							ID227_tree = (Object)adaptor.create(ID227);
							adaptor.addChild(root_0, ID227_tree);
							}

							STRING_LITERAL228=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_struct_init_value3535); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							STRING_LITERAL228_tree = (Object)adaptor.create(STRING_LITERAL228);
							adaptor.addChild(root_0, STRING_LITERAL228_tree);
							}

							}
							break;
						case 4 :
							// llvmGrammar.g:676:78: 'undef'
							{
							string_literal229=(Token)match(input,83,FOLLOW_83_in_struct_init_value3537); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							string_literal229_tree = (Object)adaptor.create(string_literal229);
							adaptor.addChild(root_0, string_literal229_tree);
							}

							}
							break;
						case 5 :
							// llvmGrammar.g:676:86: array_initial_value
							{
							pushFollow(FOLLOW_array_initial_value_in_struct_init_value3539);
							array_initial_value230=array_initial_value();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value230.getTree());

							}
							break;

					}

					}
					break;

				default :
					break loop90;
				}
			}

			END_CURLY231=(Token)match(input,END_CURLY,FOLLOW_END_CURLY_in_struct_init_value3544); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_CURLY231_tree = (Object)adaptor.create(END_CURLY231);
			adaptor.addChild(root_0, END_CURLY231_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 53, struct_init_value_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "struct_init_value"


	public static class string_constant_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "string_constant"
	// llvmGrammar.g:678:1: string_constant : e= string_name eq= EQUAL (e1= LINKAGE_TYPE )? unnamed= 'unnamed_addr' e2= 'constant' typeSt= data_type ID initial= STRING_LITERAL ( COMMA ALIGN align= NUMBER )? ;
	public final llvmGrammarParser.string_constant_return string_constant() throws RecognitionException {
		llvmGrammarParser.string_constant_return retval = new llvmGrammarParser.string_constant_return();
		retval.start = input.LT(1);
		int string_constant_StartIndex = input.index();

		Object root_0 = null;

		Token eq=null;
		Token e1=null;
		Token unnamed=null;
		Token e2=null;
		Token initial=null;
		Token align=null;
		Token ID232=null;
		Token COMMA233=null;
		Token ALIGN234=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope typeSt =null;

		Object eq_tree=null;
		Object e1_tree=null;
		Object unnamed_tree=null;
		Object e2_tree=null;
		Object initial_tree=null;
		Object align_tree=null;
		Object ID232_tree=null;
		Object COMMA233_tree=null;
		Object ALIGN234_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }

			// llvmGrammar.g:678:17: (e= string_name eq= EQUAL (e1= LINKAGE_TYPE )? unnamed= 'unnamed_addr' e2= 'constant' typeSt= data_type ID initial= STRING_LITERAL ( COMMA ALIGN align= NUMBER )? )
			// llvmGrammar.g:678:20: e= string_name eq= EQUAL (e1= LINKAGE_TYPE )? unnamed= 'unnamed_addr' e2= 'constant' typeSt= data_type ID initial= STRING_LITERAL ( COMMA ALIGN align= NUMBER )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_string_name_in_string_constant3558);
			e=string_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

			eq=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_string_constant3562); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			eq_tree = (Object)adaptor.create(eq);
			adaptor.addChild(root_0, eq_tree);
			}

			// llvmGrammar.g:678:45: (e1= LINKAGE_TYPE )?
			int alt91=2;
			int LA91_0 = input.LA(1);
			if ( (LA91_0==LINKAGE_TYPE) ) {
				alt91=1;
			}
			switch (alt91) {
				case 1 :
					// llvmGrammar.g:678:45: e1= LINKAGE_TYPE
					{
					e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_string_constant3566); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e1_tree = (Object)adaptor.create(e1);
					adaptor.addChild(root_0, e1_tree);
					}

					}
					break;

			}

			unnamed=(Token)match(input,84,FOLLOW_84_in_string_constant3571); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			unnamed_tree = (Object)adaptor.create(unnamed);
			adaptor.addChild(root_0, unnamed_tree);
			}

			e2=(Token)match(input,65,FOLLOW_65_in_string_constant3575); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e2_tree = (Object)adaptor.create(e2);
			adaptor.addChild(root_0, e2_tree);
			}

			pushFollow(FOLLOW_data_type_in_string_constant3579);
			typeSt=data_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSt.getTree());

			ID232=(Token)match(input,ID,FOLLOW_ID_in_string_constant3581); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID232_tree = (Object)adaptor.create(ID232);
			adaptor.addChild(root_0, ID232_tree);
			}

			initial=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string_constant3593); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			initial_tree = (Object)adaptor.create(initial);
			adaptor.addChild(root_0, initial_tree);
			}

			// llvmGrammar.g:679:32: ( COMMA ALIGN align= NUMBER )?
			int alt92=2;
			int LA92_0 = input.LA(1);
			if ( (LA92_0==COMMA) ) {
				alt92=1;
			}
			switch (alt92) {
				case 1 :
					// llvmGrammar.g:679:33: COMMA ALIGN align= NUMBER
					{
					COMMA233=(Token)match(input,COMMA,FOLLOW_COMMA_in_string_constant3597); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA233_tree = (Object)adaptor.create(COMMA233);
					adaptor.addChild(root_0, COMMA233_tree);
					}

					ALIGN234=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_string_constant3599); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALIGN234_tree = (Object)adaptor.create(ALIGN234);
					adaptor.addChild(root_0, ALIGN234_tree);
					}

					align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_string_constant3603); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					align_tree = (Object)adaptor.create(align);
					adaptor.addChild(root_0, align_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {       
			        			setGlobalVariable((e!=null?input.toString(e.start,e.stop):null), (e1!=null?e1.getText():null), (typeSt!=null?input.toString(typeSt.start,typeSt.stop):null), (initial!=null?initial.getText():null), (e2!=null?e2.getText():null), (align!=null?align.getText():null), 
			        							  (unnamed!=null?unnamed.getText():null),"string_constant", (eq!=null?eq.getLine():0), 0);
			        		}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 54, string_constant_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "string_constant"


	public static class string_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "string_name"
	// llvmGrammar.g:685:1: string_name : '@' ( '.' )? ID ( NUMBER )? ( '.' ID )? ;
	public final llvmGrammarParser.string_name_return string_name() throws RecognitionException {
		llvmGrammarParser.string_name_return retval = new llvmGrammarParser.string_name_return();
		retval.start = input.LT(1);
		int string_name_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal235=null;
		Token char_literal236=null;
		Token ID237=null;
		Token NUMBER238=null;
		Token char_literal239=null;
		Token ID240=null;

		Object char_literal235_tree=null;
		Object char_literal236_tree=null;
		Object ID237_tree=null;
		Object NUMBER238_tree=null;
		Object char_literal239_tree=null;
		Object ID240_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }

			// llvmGrammar.g:685:13: ( '@' ( '.' )? ID ( NUMBER )? ( '.' ID )? )
			// llvmGrammar.g:685:15: '@' ( '.' )? ID ( NUMBER )? ( '.' ID )?
			{
			root_0 = (Object)adaptor.nil();


			char_literal235=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_string_name3639); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal235_tree = (Object)adaptor.create(char_literal235);
			adaptor.addChild(root_0, char_literal235_tree);
			}

			// llvmGrammar.g:685:19: ( '.' )?
			int alt93=2;
			int LA93_0 = input.LA(1);
			if ( (LA93_0==DOT) ) {
				alt93=1;
			}
			switch (alt93) {
				case 1 :
					// llvmGrammar.g:685:19: '.'
					{
					char_literal236=(Token)match(input,DOT,FOLLOW_DOT_in_string_name3641); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal236_tree = (Object)adaptor.create(char_literal236);
					adaptor.addChild(root_0, char_literal236_tree);
					}

					}
					break;

			}

			ID237=(Token)match(input,ID,FOLLOW_ID_in_string_name3643); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID237_tree = (Object)adaptor.create(ID237);
			adaptor.addChild(root_0, ID237_tree);
			}

			// llvmGrammar.g:685:26: ( NUMBER )?
			int alt94=2;
			int LA94_0 = input.LA(1);
			if ( (LA94_0==NUMBER) ) {
				alt94=1;
			}
			switch (alt94) {
				case 1 :
					// llvmGrammar.g:685:26: NUMBER
					{
					NUMBER238=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_string_name3645); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER238_tree = (Object)adaptor.create(NUMBER238);
					adaptor.addChild(root_0, NUMBER238_tree);
					}

					}
					break;

			}

			// llvmGrammar.g:685:34: ( '.' ID )?
			int alt95=2;
			int LA95_0 = input.LA(1);
			if ( (LA95_0==DOT) ) {
				alt95=1;
			}
			switch (alt95) {
				case 1 :
					// llvmGrammar.g:685:35: '.' ID
					{
					char_literal239=(Token)match(input,DOT,FOLLOW_DOT_in_string_name3649); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal239_tree = (Object)adaptor.create(char_literal239);
					adaptor.addChild(root_0, char_literal239_tree);
					}

					ID240=(Token)match(input,ID,FOLLOW_ID_in_string_name3650); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID240_tree = (Object)adaptor.create(ID240);
					adaptor.addChild(root_0, ID240_tree);
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 55, string_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "string_name"


	public static class structure_decl_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "structure_decl"
	// llvmGrammar.g:687:1: structure_decl : LOCAL_PREFIX name1= ID EQUAL 'type {' selement= struct_element END_CURLY ;
	public final llvmGrammarParser.structure_decl_return structure_decl() throws RecognitionException {
		llvmGrammarParser.structure_decl_return retval = new llvmGrammarParser.structure_decl_return();
		retval.start = input.LT(1);
		int structure_decl_StartIndex = input.index();

		Object root_0 = null;

		Token name1=null;
		Token LOCAL_PREFIX241=null;
		Token EQUAL242=null;
		Token string_literal243=null;
		Token END_CURLY244=null;
		ParserRuleReturnScope selement =null;

		Object name1_tree=null;
		Object LOCAL_PREFIX241_tree=null;
		Object EQUAL242_tree=null;
		Object string_literal243_tree=null;
		Object END_CURLY244_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }

			// llvmGrammar.g:687:16: ( LOCAL_PREFIX name1= ID EQUAL 'type {' selement= struct_element END_CURLY )
			// llvmGrammar.g:687:18: LOCAL_PREFIX name1= ID EQUAL 'type {' selement= struct_element END_CURLY
			{
			root_0 = (Object)adaptor.nil();


			LOCAL_PREFIX241=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_structure_decl3661); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			LOCAL_PREFIX241_tree = (Object)adaptor.create(LOCAL_PREFIX241);
			adaptor.addChild(root_0, LOCAL_PREFIX241_tree);
			}

			name1=(Token)match(input,ID,FOLLOW_ID_in_structure_decl3665); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			name1_tree = (Object)adaptor.create(name1);
			adaptor.addChild(root_0, name1_tree);
			}

			EQUAL242=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_structure_decl3667); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EQUAL242_tree = (Object)adaptor.create(EQUAL242);
			adaptor.addChild(root_0, EQUAL242_tree);
			}

			string_literal243=(Token)match(input,82,FOLLOW_82_in_structure_decl3669); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal243_tree = (Object)adaptor.create(string_literal243);
			adaptor.addChild(root_0, string_literal243_tree);
			}

			pushFollow(FOLLOW_struct_element_in_structure_decl3673);
			selement=struct_element();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, selement.getTree());

			END_CURLY244=(Token)match(input,END_CURLY,FOLLOW_END_CURLY_in_structure_decl3675); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_CURLY244_tree = (Object)adaptor.create(END_CURLY244);
			adaptor.addChild(root_0, END_CURLY244_tree);
			}

			if ( state.backtracking==0 ) {					
								setStruct((name1!=null?name1.getText():null), (selement!=null?input.toString(selement.start,selement.stop):null), (name1!=null?name1.getLine():0), (name1!=null?name1.getCharPositionInLine():0));
						   }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 56, structure_decl_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "structure_decl"


	public static class struct_element_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "struct_element"
	// llvmGrammar.g:692:1: struct_element : ( data_type | ( LOCAL_PREFIX struct_name ) ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )* ;
	public final llvmGrammarParser.struct_element_return struct_element() throws RecognitionException {
		llvmGrammarParser.struct_element_return retval = new llvmGrammarParser.struct_element_return();
		retval.start = input.LT(1);
		int struct_element_StartIndex = input.index();

		Object root_0 = null;

		Token LOCAL_PREFIX246=null;
		Token COMMA248=null;
		Token LOCAL_PREFIX250=null;
		ParserRuleReturnScope data_type245 =null;
		ParserRuleReturnScope struct_name247 =null;
		ParserRuleReturnScope data_type249 =null;
		ParserRuleReturnScope struct_name251 =null;

		Object LOCAL_PREFIX246_tree=null;
		Object COMMA248_tree=null;
		Object LOCAL_PREFIX250_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }

			// llvmGrammar.g:692:16: ( ( data_type | ( LOCAL_PREFIX struct_name ) ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )* )
			// llvmGrammar.g:692:18: ( data_type | ( LOCAL_PREFIX struct_name ) ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )*
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:692:18: ( data_type | ( LOCAL_PREFIX struct_name ) )
			int alt96=2;
			int LA96_0 = input.LA(1);
			if ( (LA96_0==PRIMITIVE_DATA_TYPE||LA96_0==START_ANGULAR||(LA96_0 >= START_PARANTHESIS && LA96_0 <= START_SQUARE_BR)) ) {
				alt96=1;
			}
			else if ( (LA96_0==LOCAL_PREFIX) ) {
				int LA96_2 = input.LA(2);
				if ( (LA96_2==ID) ) {
					int LA96_3 = input.LA(3);
					if ( (LA96_3==DOT) ) {
						alt96=2;
					}
					else if ( (LA96_3==COMMA||LA96_3==END_CURLY||LA96_3==STAR) ) {
						alt96=1;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 96, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 96, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 96, 0, input);
				throw nvae;
			}

			switch (alt96) {
				case 1 :
					// llvmGrammar.g:692:19: data_type
					{
					pushFollow(FOLLOW_data_type_in_struct_element3698);
					data_type245=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type245.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:692:30: ( LOCAL_PREFIX struct_name )
					{
					// llvmGrammar.g:692:30: ( LOCAL_PREFIX struct_name )
					// llvmGrammar.g:692:31: LOCAL_PREFIX struct_name
					{
					LOCAL_PREFIX246=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_element3702); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOCAL_PREFIX246_tree = (Object)adaptor.create(LOCAL_PREFIX246);
					adaptor.addChild(root_0, LOCAL_PREFIX246_tree);
					}

					pushFollow(FOLLOW_struct_name_in_struct_element3704);
					struct_name247=struct_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, struct_name247.getTree());

					}

					}
					break;

			}

			// llvmGrammar.g:692:58: ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )*
			loop98:
			while (true) {
				int alt98=2;
				int LA98_0 = input.LA(1);
				if ( (LA98_0==COMMA) ) {
					alt98=1;
				}

				switch (alt98) {
				case 1 :
					// llvmGrammar.g:692:59: COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) )
					{
					COMMA248=(Token)match(input,COMMA,FOLLOW_COMMA_in_struct_element3709); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA248_tree = (Object)adaptor.create(COMMA248);
					adaptor.addChild(root_0, COMMA248_tree);
					}

					// llvmGrammar.g:692:65: ( ( data_type ) | ( LOCAL_PREFIX struct_name ) )
					int alt97=2;
					int LA97_0 = input.LA(1);
					if ( (LA97_0==PRIMITIVE_DATA_TYPE||LA97_0==START_ANGULAR||(LA97_0 >= START_PARANTHESIS && LA97_0 <= START_SQUARE_BR)) ) {
						alt97=1;
					}
					else if ( (LA97_0==LOCAL_PREFIX) ) {
						int LA97_2 = input.LA(2);
						if ( (LA97_2==ID) ) {
							int LA97_3 = input.LA(3);
							if ( (LA97_3==DOT) ) {
								alt97=2;
							}
							else if ( (LA97_3==COMMA||LA97_3==END_CURLY||LA97_3==STAR) ) {
								alt97=1;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 97, 3, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 97, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 97, 0, input);
						throw nvae;
					}

					switch (alt97) {
						case 1 :
							// llvmGrammar.g:692:66: ( data_type )
							{
							// llvmGrammar.g:692:66: ( data_type )
							// llvmGrammar.g:692:67: data_type
							{
							pushFollow(FOLLOW_data_type_in_struct_element3713);
							data_type249=data_type();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type249.getTree());

							}

							}
							break;
						case 2 :
							// llvmGrammar.g:692:78: ( LOCAL_PREFIX struct_name )
							{
							// llvmGrammar.g:692:78: ( LOCAL_PREFIX struct_name )
							// llvmGrammar.g:692:79: LOCAL_PREFIX struct_name
							{
							LOCAL_PREFIX250=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_element3717); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							LOCAL_PREFIX250_tree = (Object)adaptor.create(LOCAL_PREFIX250);
							adaptor.addChild(root_0, LOCAL_PREFIX250_tree);
							}

							pushFollow(FOLLOW_struct_name_in_struct_element3719);
							struct_name251=struct_name();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, struct_name251.getTree());

							}

							}
							break;

					}

					}
					break;

				default :
					break loop98;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 57, struct_element_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "struct_element"


	public static class struct_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "struct_name"
	// llvmGrammar.g:694:1: struct_name : ID DOT ID ;
	public final llvmGrammarParser.struct_name_return struct_name() throws RecognitionException {
		llvmGrammarParser.struct_name_return retval = new llvmGrammarParser.struct_name_return();
		retval.start = input.LT(1);
		int struct_name_StartIndex = input.index();

		Object root_0 = null;

		Token ID252=null;
		Token DOT253=null;
		Token ID254=null;

		Object ID252_tree=null;
		Object DOT253_tree=null;
		Object ID254_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }

			// llvmGrammar.g:694:13: ( ID DOT ID )
			// llvmGrammar.g:694:16: ID DOT ID
			{
			root_0 = (Object)adaptor.nil();


			ID252=(Token)match(input,ID,FOLLOW_ID_in_struct_name3732); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID252_tree = (Object)adaptor.create(ID252);
			adaptor.addChild(root_0, ID252_tree);
			}

			DOT253=(Token)match(input,DOT,FOLLOW_DOT_in_struct_name3734); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DOT253_tree = (Object)adaptor.create(DOT253);
			adaptor.addChild(root_0, DOT253_tree);
			}

			ID254=(Token)match(input,ID,FOLLOW_ID_in_struct_name3736); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID254_tree = (Object)adaptor.create(ID254);
			adaptor.addChild(root_0, ID254_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 58, struct_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "struct_name"


	public static class result_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "result"
	// llvmGrammar.g:696:1: result : ( ( prefix )? ( NUMBER | ID | FLOATING_LITERAL ) | truefalse | 'undef' );
	public final llvmGrammarParser.result_return result() throws RecognitionException {
		llvmGrammarParser.result_return retval = new llvmGrammarParser.result_return();
		retval.start = input.LT(1);
		int result_StartIndex = input.index();

		Object root_0 = null;

		Token set256=null;
		Token string_literal258=null;
		ParserRuleReturnScope prefix255 =null;
		ParserRuleReturnScope truefalse257 =null;

		Object set256_tree=null;
		Object string_literal258_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }

			// llvmGrammar.g:696:8: ( ( prefix )? ( NUMBER | ID | FLOATING_LITERAL ) | truefalse | 'undef' )
			int alt100=3;
			switch ( input.LA(1) ) {
			case FLOATING_LITERAL:
			case GLOBAL_PREFIX:
			case ID:
			case LOCAL_PREFIX:
			case NUMBER:
				{
				alt100=1;
				}
				break;
			case FALSE:
			case TRUE:
				{
				alt100=2;
				}
				break;
			case 83:
				{
				alt100=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 100, 0, input);
				throw nvae;
			}
			switch (alt100) {
				case 1 :
					// llvmGrammar.g:696:10: ( prefix )? ( NUMBER | ID | FLOATING_LITERAL )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:696:10: ( prefix )?
					int alt99=2;
					int LA99_0 = input.LA(1);
					if ( (LA99_0==GLOBAL_PREFIX||LA99_0==LOCAL_PREFIX) ) {
						alt99=1;
					}
					switch (alt99) {
						case 1 :
							// llvmGrammar.g:696:10: prefix
							{
							pushFollow(FOLLOW_prefix_in_result3745);
							prefix255=prefix();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix255.getTree());

							}
							break;

					}

					set256=input.LT(1);
					if ( input.LA(1)==FLOATING_LITERAL||input.LA(1)==ID||input.LA(1)==NUMBER ) {
						input.consume();
						if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set256));
						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 2 :
					// llvmGrammar.g:696:53: truefalse
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_truefalse_in_result3762);
					truefalse257=truefalse();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, truefalse257.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:696:65: 'undef'
					{
					root_0 = (Object)adaptor.nil();


					string_literal258=(Token)match(input,83,FOLLOW_83_in_result3766); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal258_tree = (Object)adaptor.create(string_literal258);
					adaptor.addChild(root_0, string_literal258_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 59, result_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "result"


	public static class truefalse_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "truefalse"
	// llvmGrammar.g:698:1: truefalse : ( TRUE | FALSE );
	public final llvmGrammarParser.truefalse_return truefalse() throws RecognitionException {
		llvmGrammarParser.truefalse_return retval = new llvmGrammarParser.truefalse_return();
		retval.start = input.LT(1);
		int truefalse_StartIndex = input.index();

		Object root_0 = null;

		Token set259=null;

		Object set259_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }

			// llvmGrammar.g:698:11: ( TRUE | FALSE )
			// llvmGrammar.g:
			{
			root_0 = (Object)adaptor.nil();


			set259=input.LT(1);
			if ( input.LA(1)==FALSE||input.LA(1)==TRUE ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set259));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 60, truefalse_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "truefalse"


	public static class data_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "data_type"
	// llvmGrammar.g:700:1: data_type : ( ptr_type |e= PRIMITIVE_DATA_TYPE | derived_data_type ) ;
	public final llvmGrammarParser.data_type_return data_type() throws RecognitionException {
		llvmGrammarParser.data_type_return retval = new llvmGrammarParser.data_type_return();
		retval.start = input.LT(1);
		int data_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		ParserRuleReturnScope ptr_type260 =null;
		ParserRuleReturnScope derived_data_type261 =null;

		Object e_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }

			// llvmGrammar.g:700:11: ( ( ptr_type |e= PRIMITIVE_DATA_TYPE | derived_data_type ) )
			// llvmGrammar.g:700:13: ( ptr_type |e= PRIMITIVE_DATA_TYPE | derived_data_type )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:700:13: ( ptr_type |e= PRIMITIVE_DATA_TYPE | derived_data_type )
			int alt101=3;
			switch ( input.LA(1) ) {
			case PRIMITIVE_DATA_TYPE:
				{
				int LA101_1 = input.LA(2);
				if ( (synpred145_llvmGrammar()) ) {
					alt101=1;
				}
				else if ( (synpred146_llvmGrammar()) ) {
					alt101=2;
				}
				else if ( (true) ) {
					alt101=3;
				}

				}
				break;
			case START_SQUARE_BR:
				{
				int LA101_2 = input.LA(2);
				if ( (synpred145_llvmGrammar()) ) {
					alt101=1;
				}
				else if ( (true) ) {
					alt101=3;
				}

				}
				break;
			case LOCAL_PREFIX:
				{
				int LA101_3 = input.LA(2);
				if ( (synpred145_llvmGrammar()) ) {
					alt101=1;
				}
				else if ( (true) ) {
					alt101=3;
				}

				}
				break;
			case START_ANGULAR:
				{
				int LA101_4 = input.LA(2);
				if ( (synpred145_llvmGrammar()) ) {
					alt101=1;
				}
				else if ( (true) ) {
					alt101=3;
				}

				}
				break;
			case START_PARANTHESIS:
				{
				int LA101_5 = input.LA(2);
				if ( (synpred145_llvmGrammar()) ) {
					alt101=1;
				}
				else if ( (true) ) {
					alt101=3;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 101, 0, input);
				throw nvae;
			}
			switch (alt101) {
				case 1 :
					// llvmGrammar.g:700:14: ptr_type
					{
					pushFollow(FOLLOW_ptr_type_in_data_type3785);
					ptr_type260=ptr_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr_type260.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:701:6: e= PRIMITIVE_DATA_TYPE
					{
					e=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_data_type3795); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e_tree = (Object)adaptor.create(e);
					adaptor.addChild(root_0, e_tree);
					}

					if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
					}
					break;
				case 3 :
					// llvmGrammar.g:702:6: derived_data_type
					{
					pushFollow(FOLLOW_derived_data_type_in_data_type3804);
					derived_data_type261=derived_data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, derived_data_type261.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 61, data_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "data_type"


	public static class derived_data_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "derived_data_type"
	// llvmGrammar.g:705:1: derived_data_type : ( agr_type | func_type ) ;
	public final llvmGrammarParser.derived_data_type_return derived_data_type() throws RecognitionException {
		llvmGrammarParser.derived_data_type_return retval = new llvmGrammarParser.derived_data_type_return();
		retval.start = input.LT(1);
		int derived_data_type_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope agr_type262 =null;
		ParserRuleReturnScope func_type263 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }

			// llvmGrammar.g:705:19: ( ( agr_type | func_type ) )
			// llvmGrammar.g:705:21: ( agr_type | func_type )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:705:21: ( agr_type | func_type )
			int alt102=2;
			int LA102_0 = input.LA(1);
			if ( (LA102_0==LOCAL_PREFIX||LA102_0==START_ANGULAR||LA102_0==START_SQUARE_BR) ) {
				alt102=1;
			}
			else if ( (LA102_0==PRIMITIVE_DATA_TYPE||LA102_0==START_PARANTHESIS) ) {
				alt102=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 102, 0, input);
				throw nvae;
			}

			switch (alt102) {
				case 1 :
					// llvmGrammar.g:705:22: agr_type
					{
					pushFollow(FOLLOW_agr_type_in_derived_data_type3821);
					agr_type262=agr_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, agr_type262.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:705:33: func_type
					{
					pushFollow(FOLLOW_func_type_in_derived_data_type3825);
					func_type263=func_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, func_type263.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 62, derived_data_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "derived_data_type"


	public static class agr_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "agr_type"
	// llvmGrammar.g:707:1: agr_type : ( multiDim_array_type | struct_type | multiDim_vector_type ) ;
	public final llvmGrammarParser.agr_type_return agr_type() throws RecognitionException {
		llvmGrammarParser.agr_type_return retval = new llvmGrammarParser.agr_type_return();
		retval.start = input.LT(1);
		int agr_type_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope multiDim_array_type264 =null;
		ParserRuleReturnScope struct_type265 =null;
		ParserRuleReturnScope multiDim_vector_type266 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }

			// llvmGrammar.g:707:10: ( ( multiDim_array_type | struct_type | multiDim_vector_type ) )
			// llvmGrammar.g:707:12: ( multiDim_array_type | struct_type | multiDim_vector_type )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:707:12: ( multiDim_array_type | struct_type | multiDim_vector_type )
			int alt103=3;
			switch ( input.LA(1) ) {
			case START_SQUARE_BR:
				{
				alt103=1;
				}
				break;
			case LOCAL_PREFIX:
				{
				alt103=2;
				}
				break;
			case START_ANGULAR:
				{
				alt103=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 103, 0, input);
				throw nvae;
			}
			switch (alt103) {
				case 1 :
					// llvmGrammar.g:707:13: multiDim_array_type
					{
					pushFollow(FOLLOW_multiDim_array_type_in_agr_type3835);
					multiDim_array_type264=multiDim_array_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, multiDim_array_type264.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:707:35: struct_type
					{
					pushFollow(FOLLOW_struct_type_in_agr_type3839);
					struct_type265=struct_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, struct_type265.getTree());

					}
					break;
				case 3 :
					// llvmGrammar.g:707:49: multiDim_vector_type
					{
					pushFollow(FOLLOW_multiDim_vector_type_in_agr_type3843);
					multiDim_vector_type266=multiDim_vector_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, multiDim_vector_type266.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 63, agr_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "agr_type"


	public static class multiDim_vector_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multiDim_vector_type"
	// llvmGrammar.g:709:1: multiDim_vector_type : e= START_ANGULAR NUMBER MUL_OPERATOR vector_type END_ANGULAR ;
	public final llvmGrammarParser.multiDim_vector_type_return multiDim_vector_type() throws RecognitionException {
		llvmGrammarParser.multiDim_vector_type_return retval = new llvmGrammarParser.multiDim_vector_type_return();
		retval.start = input.LT(1);
		int multiDim_vector_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token NUMBER267=null;
		Token MUL_OPERATOR268=null;
		Token END_ANGULAR270=null;
		ParserRuleReturnScope vector_type269 =null;

		Object e_tree=null;
		Object NUMBER267_tree=null;
		Object MUL_OPERATOR268_tree=null;
		Object END_ANGULAR270_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }

			// llvmGrammar.g:709:22: (e= START_ANGULAR NUMBER MUL_OPERATOR vector_type END_ANGULAR )
			// llvmGrammar.g:709:24: e= START_ANGULAR NUMBER MUL_OPERATOR vector_type END_ANGULAR
			{
			root_0 = (Object)adaptor.nil();


			e=(Token)match(input,START_ANGULAR,FOLLOW_START_ANGULAR_in_multiDim_vector_type3854); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
			NUMBER267=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_multiDim_vector_type3858); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER267_tree = (Object)adaptor.create(NUMBER267);
			adaptor.addChild(root_0, NUMBER267_tree);
			}

			MUL_OPERATOR268=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_multiDim_vector_type3860); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			MUL_OPERATOR268_tree = (Object)adaptor.create(MUL_OPERATOR268);
			adaptor.addChild(root_0, MUL_OPERATOR268_tree);
			}

			pushFollow(FOLLOW_vector_type_in_multiDim_vector_type3862);
			vector_type269=vector_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, vector_type269.getTree());

			END_ANGULAR270=(Token)match(input,END_ANGULAR,FOLLOW_END_ANGULAR_in_multiDim_vector_type3864); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_ANGULAR270_tree = (Object)adaptor.create(END_ANGULAR270);
			adaptor.addChild(root_0, END_ANGULAR270_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 64, multiDim_vector_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "multiDim_vector_type"


	public static class vector_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "vector_type"
	// llvmGrammar.g:711:1: vector_type : e= START_ANGULAR NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR ;
	public final llvmGrammarParser.vector_type_return vector_type() throws RecognitionException {
		llvmGrammarParser.vector_type_return retval = new llvmGrammarParser.vector_type_return();
		retval.start = input.LT(1);
		int vector_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token NUMBER271=null;
		Token MUL_OPERATOR272=null;
		Token PRIMITIVE_DATA_TYPE273=null;
		Token END_ANGULAR274=null;

		Object e_tree=null;
		Object NUMBER271_tree=null;
		Object MUL_OPERATOR272_tree=null;
		Object PRIMITIVE_DATA_TYPE273_tree=null;
		Object END_ANGULAR274_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }

			// llvmGrammar.g:711:13: (e= START_ANGULAR NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR )
			// llvmGrammar.g:711:15: e= START_ANGULAR NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR
			{
			root_0 = (Object)adaptor.nil();


			e=(Token)match(input,START_ANGULAR,FOLLOW_START_ANGULAR_in_vector_type3874); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
			NUMBER271=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vector_type3878); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER271_tree = (Object)adaptor.create(NUMBER271);
			adaptor.addChild(root_0, NUMBER271_tree);
			}

			MUL_OPERATOR272=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_vector_type3880); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			MUL_OPERATOR272_tree = (Object)adaptor.create(MUL_OPERATOR272);
			adaptor.addChild(root_0, MUL_OPERATOR272_tree);
			}

			PRIMITIVE_DATA_TYPE273=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_vector_type3882); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PRIMITIVE_DATA_TYPE273_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE273);
			adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE273_tree);
			}

			END_ANGULAR274=(Token)match(input,END_ANGULAR,FOLLOW_END_ANGULAR_in_vector_type3884); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_ANGULAR274_tree = (Object)adaptor.create(END_ANGULAR274);
			adaptor.addChild(root_0, END_ANGULAR274_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 65, vector_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "vector_type"


	public static class multiDim_array_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multiDim_array_type"
	// llvmGrammar.g:713:1: multiDim_array_type : e= START_SQUARE_BR NUMBER MUL_OPERATOR ( array_type ) END_SQUARE_BR ;
	public final llvmGrammarParser.multiDim_array_type_return multiDim_array_type() throws RecognitionException {
		llvmGrammarParser.multiDim_array_type_return retval = new llvmGrammarParser.multiDim_array_type_return();
		retval.start = input.LT(1);
		int multiDim_array_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token NUMBER275=null;
		Token MUL_OPERATOR276=null;
		Token END_SQUARE_BR278=null;
		ParserRuleReturnScope array_type277 =null;

		Object e_tree=null;
		Object NUMBER275_tree=null;
		Object MUL_OPERATOR276_tree=null;
		Object END_SQUARE_BR278_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }

			// llvmGrammar.g:713:21: (e= START_SQUARE_BR NUMBER MUL_OPERATOR ( array_type ) END_SQUARE_BR )
			// llvmGrammar.g:713:23: e= START_SQUARE_BR NUMBER MUL_OPERATOR ( array_type ) END_SQUARE_BR
			{
			root_0 = (Object)adaptor.nil();


			e=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_multiDim_array_type3894); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
			NUMBER275=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_multiDim_array_type3898); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER275_tree = (Object)adaptor.create(NUMBER275);
			adaptor.addChild(root_0, NUMBER275_tree);
			}

			MUL_OPERATOR276=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_multiDim_array_type3900); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			MUL_OPERATOR276_tree = (Object)adaptor.create(MUL_OPERATOR276);
			adaptor.addChild(root_0, MUL_OPERATOR276_tree);
			}

			// llvmGrammar.g:713:106: ( array_type )
			// llvmGrammar.g:713:107: array_type
			{
			pushFollow(FOLLOW_array_type_in_multiDim_array_type3903);
			array_type277=array_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, array_type277.getTree());

			}

			END_SQUARE_BR278=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_multiDim_array_type3905); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_SQUARE_BR278_tree = (Object)adaptor.create(END_SQUARE_BR278);
			adaptor.addChild(root_0, END_SQUARE_BR278_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 66, multiDim_array_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "multiDim_array_type"


	public static class array_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "array_type"
	// llvmGrammar.g:715:1: array_type : ( (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type );
	public final llvmGrammarParser.array_type_return array_type() throws RecognitionException {
		llvmGrammarParser.array_type_return retval = new llvmGrammarParser.array_type_return();
		retval.start = input.LT(1);
		int array_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token NUMBER279=null;
		Token MUL_OPERATOR280=null;
		Token END_SQUARE_BR282=null;
		ParserRuleReturnScope data_type281 =null;
		ParserRuleReturnScope data_type283 =null;

		Object e_tree=null;
		Object NUMBER279_tree=null;
		Object MUL_OPERATOR280_tree=null;
		Object END_SQUARE_BR282_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }

			// llvmGrammar.g:715:12: ( (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type )
			int alt104=2;
			int LA104_0 = input.LA(1);
			if ( (LA104_0==START_SQUARE_BR) ) {
				int LA104_1 = input.LA(2);
				if ( (synpred150_llvmGrammar()) ) {
					alt104=1;
				}
				else if ( (true) ) {
					alt104=2;
				}

			}
			else if ( (LA104_0==LOCAL_PREFIX||LA104_0==PRIMITIVE_DATA_TYPE||LA104_0==START_ANGULAR||LA104_0==START_PARANTHESIS) ) {
				alt104=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 104, 0, input);
				throw nvae;
			}

			switch (alt104) {
				case 1 :
					// llvmGrammar.g:715:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:715:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
					// llvmGrammar.g:715:15: e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
					{
					e=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_array_type3916); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e_tree = (Object)adaptor.create(e);
					adaptor.addChild(root_0, e_tree);
					}

					if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
					NUMBER279=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_array_type3920); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER279_tree = (Object)adaptor.create(NUMBER279);
					adaptor.addChild(root_0, NUMBER279_tree);
					}

					MUL_OPERATOR280=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_array_type3922); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MUL_OPERATOR280_tree = (Object)adaptor.create(MUL_OPERATOR280);
					adaptor.addChild(root_0, MUL_OPERATOR280_tree);
					}

					pushFollow(FOLLOW_data_type_in_array_type3924);
					data_type281=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type281.getTree());

					END_SQUARE_BR282=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_array_type3926); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_SQUARE_BR282_tree = (Object)adaptor.create(END_SQUARE_BR282);
					adaptor.addChild(root_0, END_SQUARE_BR282_tree);
					}

					}

					}
					break;
				case 2 :
					// llvmGrammar.g:715:124: data_type
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_data_type_in_array_type3930);
					data_type283=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type283.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 67, array_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "array_type"


	public static class struct_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "struct_type"
	// llvmGrammar.g:717:1: struct_type : (e= LOCAL_PREFIX ID ) ;
	public final llvmGrammarParser.struct_type_return struct_type() throws RecognitionException {
		llvmGrammarParser.struct_type_return retval = new llvmGrammarParser.struct_type_return();
		retval.start = input.LT(1);
		int struct_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token ID284=null;

		Object e_tree=null;
		Object ID284_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }

			// llvmGrammar.g:717:13: ( (e= LOCAL_PREFIX ID ) )
			// llvmGrammar.g:717:15: (e= LOCAL_PREFIX ID )
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:717:15: (e= LOCAL_PREFIX ID )
			// llvmGrammar.g:717:16: e= LOCAL_PREFIX ID
			{
			e=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_type3941); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e_tree = (Object)adaptor.create(e);
			adaptor.addChild(root_0, e_tree);
			}

			ID284=(Token)match(input,ID,FOLLOW_ID_in_struct_type3943); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID284_tree = (Object)adaptor.create(ID284);
			adaptor.addChild(root_0, ID284_tree);
			}

			if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 68, struct_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "struct_type"


	public static class func_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "func_type"
	// llvmGrammar.g:719:1: func_type : (e1= PRIMITIVE_DATA_TYPE )? e2= START_PARANTHESIS ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type ) ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )? END_PARANTHESIS ;
	public final llvmGrammarParser.func_type_return func_type() throws RecognitionException {
		llvmGrammarParser.func_type_return retval = new llvmGrammarParser.func_type_return();
		retval.start = input.LT(1);
		int func_type_StartIndex = input.index();

		Object root_0 = null;

		Token e1=null;
		Token e2=null;
		Token PRIMITIVE_DATA_TYPE286=null;
		Token COMMA288=null;
		Token PRIMITIVE_DATA_TYPE290=null;
		Token string_literal292=null;
		Token END_PARANTHESIS293=null;
		ParserRuleReturnScope ptr_type285 =null;
		ParserRuleReturnScope agr_type287 =null;
		ParserRuleReturnScope ptr_type289 =null;
		ParserRuleReturnScope agr_type291 =null;

		Object e1_tree=null;
		Object e2_tree=null;
		Object PRIMITIVE_DATA_TYPE286_tree=null;
		Object COMMA288_tree=null;
		Object PRIMITIVE_DATA_TYPE290_tree=null;
		Object string_literal292_tree=null;
		Object END_PARANTHESIS293_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }

			// llvmGrammar.g:719:11: ( (e1= PRIMITIVE_DATA_TYPE )? e2= START_PARANTHESIS ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type ) ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )? END_PARANTHESIS )
			// llvmGrammar.g:719:13: (e1= PRIMITIVE_DATA_TYPE )? e2= START_PARANTHESIS ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type ) ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )? END_PARANTHESIS
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:719:15: (e1= PRIMITIVE_DATA_TYPE )?
			int alt105=2;
			int LA105_0 = input.LA(1);
			if ( (LA105_0==PRIMITIVE_DATA_TYPE) ) {
				alt105=1;
			}
			switch (alt105) {
				case 1 :
					// llvmGrammar.g:719:15: e1= PRIMITIVE_DATA_TYPE
					{
					e1=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3956); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e1_tree = (Object)adaptor.create(e1);
					adaptor.addChild(root_0, e1_tree);
					}

					}
					break;

			}

			e2=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_func_type3965); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			e2_tree = (Object)adaptor.create(e2);
			adaptor.addChild(root_0, e2_tree);
			}

			if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0));}
			// llvmGrammar.g:720:72: ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )
			int alt106=3;
			switch ( input.LA(1) ) {
			case PRIMITIVE_DATA_TYPE:
				{
				int LA106_1 = input.LA(2);
				if ( (synpred152_llvmGrammar()) ) {
					alt106=1;
				}
				else if ( (synpred153_llvmGrammar()) ) {
					alt106=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 106, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case START_SQUARE_BR:
				{
				int LA106_2 = input.LA(2);
				if ( (synpred152_llvmGrammar()) ) {
					alt106=1;
				}
				else if ( (true) ) {
					alt106=3;
				}

				}
				break;
			case LOCAL_PREFIX:
				{
				int LA106_3 = input.LA(2);
				if ( (synpred152_llvmGrammar()) ) {
					alt106=1;
				}
				else if ( (true) ) {
					alt106=3;
				}

				}
				break;
			case START_ANGULAR:
				{
				int LA106_4 = input.LA(2);
				if ( (synpred152_llvmGrammar()) ) {
					alt106=1;
				}
				else if ( (true) ) {
					alt106=3;
				}

				}
				break;
			case START_PARANTHESIS:
				{
				alt106=1;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 106, 0, input);
				throw nvae;
			}
			switch (alt106) {
				case 1 :
					// llvmGrammar.g:720:73: ptr_type
					{
					pushFollow(FOLLOW_ptr_type_in_func_type3970);
					ptr_type285=ptr_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr_type285.getTree());

					}
					break;
				case 2 :
					// llvmGrammar.g:720:83: PRIMITIVE_DATA_TYPE
					{
					PRIMITIVE_DATA_TYPE286=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3973); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PRIMITIVE_DATA_TYPE286_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE286);
					adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE286_tree);
					}

					}
					break;
				case 3 :
					// llvmGrammar.g:720:105: agr_type
					{
					pushFollow(FOLLOW_agr_type_in_func_type3977);
					agr_type287=agr_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, agr_type287.getTree());

					}
					break;

			}

			// llvmGrammar.g:721:4: ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )?
			int alt109=2;
			int LA109_0 = input.LA(1);
			if ( (LA109_0==COMMA) ) {
				alt109=1;
			}
			switch (alt109) {
				case 1 :
					// llvmGrammar.g:721:5: COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) )
					{
					COMMA288=(Token)match(input,COMMA,FOLLOW_COMMA_in_func_type3985); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA288_tree = (Object)adaptor.create(COMMA288);
					adaptor.addChild(root_0, COMMA288_tree);
					}

					// llvmGrammar.g:721:12: ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) )
					int alt108=2;
					int LA108_0 = input.LA(1);
					if ( (LA108_0==END_PARANTHESIS||LA108_0==LOCAL_PREFIX||LA108_0==PRIMITIVE_DATA_TYPE||LA108_0==START_ANGULAR||(LA108_0 >= START_PARANTHESIS && LA108_0 <= START_SQUARE_BR)) ) {
						alt108=1;
					}
					else if ( (LA108_0==60) ) {
						alt108=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 108, 0, input);
						throw nvae;
					}

					switch (alt108) {
						case 1 :
							// llvmGrammar.g:721:13: ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )*
							{
							// llvmGrammar.g:721:13: ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )*
							loop107:
							while (true) {
								int alt107=4;
								switch ( input.LA(1) ) {
								case PRIMITIVE_DATA_TYPE:
									{
									int LA107_2 = input.LA(2);
									if ( (synpred154_llvmGrammar()) ) {
										alt107=1;
									}
									else if ( (synpred155_llvmGrammar()) ) {
										alt107=2;
									}

									}
									break;
								case START_SQUARE_BR:
									{
									int LA107_3 = input.LA(2);
									if ( (synpred154_llvmGrammar()) ) {
										alt107=1;
									}
									else if ( (synpred156_llvmGrammar()) ) {
										alt107=3;
									}

									}
									break;
								case LOCAL_PREFIX:
									{
									int LA107_4 = input.LA(2);
									if ( (synpred154_llvmGrammar()) ) {
										alt107=1;
									}
									else if ( (synpred156_llvmGrammar()) ) {
										alt107=3;
									}

									}
									break;
								case START_ANGULAR:
									{
									int LA107_5 = input.LA(2);
									if ( (synpred154_llvmGrammar()) ) {
										alt107=1;
									}
									else if ( (synpred156_llvmGrammar()) ) {
										alt107=3;
									}

									}
									break;
								case START_PARANTHESIS:
									{
									alt107=1;
									}
									break;
								}
								switch (alt107) {
								case 1 :
									// llvmGrammar.g:721:15: ptr_type
									{
									pushFollow(FOLLOW_ptr_type_in_func_type3991);
									ptr_type289=ptr_type();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr_type289.getTree());

									}
									break;
								case 2 :
									// llvmGrammar.g:721:26: PRIMITIVE_DATA_TYPE
									{
									PRIMITIVE_DATA_TYPE290=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3995); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									PRIMITIVE_DATA_TYPE290_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE290);
									adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE290_tree);
									}

									}
									break;
								case 3 :
									// llvmGrammar.g:721:48: agr_type
									{
									pushFollow(FOLLOW_agr_type_in_func_type3999);
									agr_type291=agr_type();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, agr_type291.getTree());

									}
									break;

								default :
									break loop107;
								}
							}

							}
							break;
						case 2 :
							// llvmGrammar.g:721:63: ( '...' )
							{
							// llvmGrammar.g:721:63: ( '...' )
							// llvmGrammar.g:721:64: '...'
							{
							string_literal292=(Token)match(input,60,FOLLOW_60_in_func_type4008); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							string_literal292_tree = (Object)adaptor.create(string_literal292);
							adaptor.addChild(root_0, string_literal292_tree);
							}

							}

							}
							break;

					}

					}
					break;

			}

			END_PARANTHESIS293=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_func_type4013); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			END_PARANTHESIS293_tree = (Object)adaptor.create(END_PARANTHESIS293);
			adaptor.addChild(root_0, END_PARANTHESIS293_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 69, func_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "func_type"


	public static class function_data_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_data"
	// llvmGrammar.g:723:1: function_data : ( ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type );
	public final llvmGrammarParser.function_data_return function_data() throws RecognitionException {
		llvmGrammarParser.function_data_return retval = new llvmGrammarParser.function_data_return();
		retval.start = input.LT(1);
		int function_data_StartIndex = input.index();

		Object root_0 = null;

		Token START_SQUARE_BR294=null;
		Token NUMBER295=null;
		Token MUL_OPERATOR296=null;
		Token END_SQUARE_BR298=null;
		ParserRuleReturnScope data_type297 =null;
		ParserRuleReturnScope data_type299 =null;

		Object START_SQUARE_BR294_tree=null;
		Object NUMBER295_tree=null;
		Object MUL_OPERATOR296_tree=null;
		Object END_SQUARE_BR298_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }

			// llvmGrammar.g:723:15: ( ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type )
			int alt110=2;
			int LA110_0 = input.LA(1);
			if ( (LA110_0==START_SQUARE_BR) ) {
				int LA110_1 = input.LA(2);
				if ( (synpred159_llvmGrammar()) ) {
					alt110=1;
				}
				else if ( (true) ) {
					alt110=2;
				}

			}
			else if ( (LA110_0==LOCAL_PREFIX||LA110_0==PRIMITIVE_DATA_TYPE||LA110_0==START_ANGULAR||LA110_0==START_PARANTHESIS) ) {
				alt110=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 110, 0, input);
				throw nvae;
			}

			switch (alt110) {
				case 1 :
					// llvmGrammar.g:723:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
					{
					root_0 = (Object)adaptor.nil();


					// llvmGrammar.g:723:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
					// llvmGrammar.g:723:18: START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
					{
					START_SQUARE_BR294=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_function_data4024); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					START_SQUARE_BR294_tree = (Object)adaptor.create(START_SQUARE_BR294);
					adaptor.addChild(root_0, START_SQUARE_BR294_tree);
					}

					NUMBER295=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_function_data4026); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER295_tree = (Object)adaptor.create(NUMBER295);
					adaptor.addChild(root_0, NUMBER295_tree);
					}

					MUL_OPERATOR296=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_function_data4028); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MUL_OPERATOR296_tree = (Object)adaptor.create(MUL_OPERATOR296);
					adaptor.addChild(root_0, MUL_OPERATOR296_tree);
					}

					pushFollow(FOLLOW_data_type_in_function_data4030);
					data_type297=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type297.getTree());

					END_SQUARE_BR298=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_function_data4032); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					END_SQUARE_BR298_tree = (Object)adaptor.create(END_SQUARE_BR298);
					adaptor.addChild(root_0, END_SQUARE_BR298_tree);
					}

					}

					}
					break;
				case 2 :
					// llvmGrammar.g:723:81: data_type
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_data_type_in_function_data4037);
					data_type299=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type299.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 70, function_data_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "function_data"


	public static class ptr_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ptr_type"
	// llvmGrammar.g:725:1: ptr_type : (e= PRIMITIVE_DATA_TYPE | derived_data_type ) ( STAR )+ ;
	public final llvmGrammarParser.ptr_type_return ptr_type() throws RecognitionException {
		llvmGrammarParser.ptr_type_return retval = new llvmGrammarParser.ptr_type_return();
		retval.start = input.LT(1);
		int ptr_type_StartIndex = input.index();

		Object root_0 = null;

		Token e=null;
		Token STAR301=null;
		ParserRuleReturnScope derived_data_type300 =null;

		Object e_tree=null;
		Object STAR301_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }

			// llvmGrammar.g:725:10: ( (e= PRIMITIVE_DATA_TYPE | derived_data_type ) ( STAR )+ )
			// llvmGrammar.g:725:11: (e= PRIMITIVE_DATA_TYPE | derived_data_type ) ( STAR )+
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:725:11: (e= PRIMITIVE_DATA_TYPE | derived_data_type )
			int alt111=2;
			int LA111_0 = input.LA(1);
			if ( (LA111_0==PRIMITIVE_DATA_TYPE) ) {
				int LA111_1 = input.LA(2);
				if ( (LA111_1==STAR) ) {
					alt111=1;
				}
				else if ( (LA111_1==START_PARANTHESIS) ) {
					alt111=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 111, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA111_0==LOCAL_PREFIX||LA111_0==START_ANGULAR||(LA111_0 >= START_PARANTHESIS && LA111_0 <= START_SQUARE_BR)) ) {
				alt111=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 111, 0, input);
				throw nvae;
			}

			switch (alt111) {
				case 1 :
					// llvmGrammar.g:725:12: e= PRIMITIVE_DATA_TYPE
					{
					e=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_ptr_type4047); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					e_tree = (Object)adaptor.create(e);
					adaptor.addChild(root_0, e_tree);
					}

					if ( state.backtracking==0 ) {updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));}
					}
					break;
				case 2 :
					// llvmGrammar.g:726:6: derived_data_type
					{
					pushFollow(FOLLOW_derived_data_type_in_ptr_type4056);
					derived_data_type300=derived_data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, derived_data_type300.getTree());

					}
					break;

			}

			// llvmGrammar.g:726:25: ( STAR )+
			int cnt112=0;
			loop112:
			while (true) {
				int alt112=2;
				int LA112_0 = input.LA(1);
				if ( (LA112_0==STAR) ) {
					alt112=1;
				}

				switch (alt112) {
				case 1 :
					// llvmGrammar.g:726:25: STAR
					{
					STAR301=(Token)match(input,STAR,FOLLOW_STAR_in_ptr_type4059); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR301_tree = (Object)adaptor.create(STAR301);
					adaptor.addChild(root_0, STAR301_tree);
					}

					}
					break;

				default :
					if ( cnt112 >= 1 ) break loop112;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(112, input);
					throw eee;
				}
				cnt112++;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 71, ptr_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "ptr_type"


	public static class value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "value"
	// llvmGrammar.g:728:1: value : ( prefix )? NUMBER ;
	public final llvmGrammarParser.value_return value() throws RecognitionException {
		llvmGrammarParser.value_return retval = new llvmGrammarParser.value_return();
		retval.start = input.LT(1);
		int value_StartIndex = input.index();

		Object root_0 = null;

		Token NUMBER303=null;
		ParserRuleReturnScope prefix302 =null;

		Object NUMBER303_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }

			// llvmGrammar.g:728:7: ( ( prefix )? NUMBER )
			// llvmGrammar.g:728:9: ( prefix )? NUMBER
			{
			root_0 = (Object)adaptor.nil();


			// llvmGrammar.g:728:9: ( prefix )?
			int alt113=2;
			int LA113_0 = input.LA(1);
			if ( (LA113_0==GLOBAL_PREFIX||LA113_0==LOCAL_PREFIX) ) {
				alt113=1;
			}
			switch (alt113) {
				case 1 :
					// llvmGrammar.g:728:9: prefix
					{
					pushFollow(FOLLOW_prefix_in_value4068);
					prefix302=prefix();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix302.getTree());

					}
					break;

			}

			NUMBER303=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value4071); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NUMBER303_tree = (Object)adaptor.create(NUMBER303);
			adaptor.addChild(root_0, NUMBER303_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 72, value_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "value"

	// $ANTLR start synpred40_llvmGrammar
	public final void synpred40_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:537:75: ( START_PARANTHESIS )
		// llvmGrammar.g:537:75: START_PARANTHESIS
		{
		match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_synpred40_llvmGrammar2100); if (state.failed) return;

		}

	}
	// $ANTLR end synpred40_llvmGrammar

	// $ANTLR start synpred44_llvmGrammar
	public final void synpred44_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:543:16: ( ( LOCAL_PREFIX ( ID | NUMBER )+ ) )
		// llvmGrammar.g:543:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
		{
		// llvmGrammar.g:543:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
		// llvmGrammar.g:543:17: LOCAL_PREFIX ( ID | NUMBER )+
		{
		match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_synpred44_llvmGrammar2173); if (state.failed) return;

		// llvmGrammar.g:543:30: ( ID | NUMBER )+
		int cnt116=0;
		loop116:
		while (true) {
			int alt116=2;
			int LA116_0 = input.LA(1);
			if ( (LA116_0==ID||LA116_0==NUMBER) ) {
				alt116=1;
			}

			switch (alt116) {
			case 1 :
				// llvmGrammar.g:
				{
				if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
					input.consume();
					state.errorRecovery=false;
					state.failed=false;
				}
				else {
					if (state.backtracking>0) {state.failed=true; return;}
					MismatchedSetException mse = new MismatchedSetException(null,input);
					throw mse;
				}
				}
				break;

			default :
				if ( cnt116 >= 1 ) break loop116;
				if (state.backtracking>0) {state.failed=true; return;}
				EarlyExitException eee = new EarlyExitException(116, input);
				throw eee;
			}
			cnt116++;
		}

		}

		}

	}
	// $ANTLR end synpred44_llvmGrammar

	// $ANTLR start synpred49_llvmGrammar
	public final void synpred49_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:543:47: ( ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type ) )
		// llvmGrammar.g:543:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
		{
		// llvmGrammar.g:543:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
		int alt121=2;
		int LA121_0 = input.LA(1);
		if ( (LA121_0==GLOBAL_PREFIX) ) {
			alt121=1;
		}
		else if ( (LA121_0==LOCAL_PREFIX||LA121_0==PRIMITIVE_DATA_TYPE||LA121_0==START_ANGULAR||(LA121_0 >= START_PARANTHESIS && LA121_0 <= START_SQUARE_BR)) ) {
			alt121=2;
		}

		else {
			if (state.backtracking>0) {state.failed=true; return;}
			NoViableAltException nvae =
				new NoViableAltException("", 121, 0, input);
			throw nvae;
		}

		switch (alt121) {
			case 1 :
				// llvmGrammar.g:543:48: GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+
				{
				match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_synpred49_llvmGrammar2187); if (state.failed) return;

				// llvmGrammar.g:543:62: ( '.' )?
				int alt119=2;
				int LA119_0 = input.LA(1);
				if ( (LA119_0==DOT) ) {
					alt119=1;
				}
				switch (alt119) {
					case 1 :
						// llvmGrammar.g:543:62: '.'
						{
						match(input,DOT,FOLLOW_DOT_in_synpred49_llvmGrammar2189); if (state.failed) return;

						}
						break;

				}

				// llvmGrammar.g:543:66: ( ID | NUMBER )+
				int cnt120=0;
				loop120:
				while (true) {
					int alt120=2;
					int LA120_0 = input.LA(1);
					if ( (LA120_0==ID||LA120_0==NUMBER) ) {
						alt120=1;
					}

					switch (alt120) {
					case 1 :
						// llvmGrammar.g:
						{
						if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
							input.consume();
							state.errorRecovery=false;
							state.failed=false;
						}
						else {
							if (state.backtracking>0) {state.failed=true; return;}
							MismatchedSetException mse = new MismatchedSetException(null,input);
							throw mse;
						}
						}
						break;

					default :
						if ( cnt120 >= 1 ) break loop120;
						if (state.backtracking>0) {state.failed=true; return;}
						EarlyExitException eee = new EarlyExitException(120, input);
						throw eee;
					}
					cnt120++;
				}

				}
				break;
			case 2 :
				// llvmGrammar.g:543:82: data_type
				{
				pushFollow(FOLLOW_data_type_in_synpred49_llvmGrammar2201);
				data_type();
				state._fsp--;
				if (state.failed) return;

				}
				break;

		}

		}

	}
	// $ANTLR end synpred49_llvmGrammar

	// $ANTLR start synpred55_llvmGrammar
	public final void synpred55_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:545:77: ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )
		// llvmGrammar.g:545:77: COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID )
		{
		match(input,COMMA,FOLLOW_COMMA_in_synpred55_llvmGrammar2241); if (state.failed) return;

		pushFollow(FOLLOW_data_type_in_synpred55_llvmGrammar2243);
		data_type();
		state._fsp--;
		if (state.failed) return;

		// llvmGrammar.g:545:93: ( LOCAL_PREFIX )?
		int alt122=2;
		int LA122_0 = input.LA(1);
		if ( (LA122_0==LOCAL_PREFIX) ) {
			alt122=1;
		}
		switch (alt122) {
			case 1 :
				// llvmGrammar.g:545:93: LOCAL_PREFIX
				{
				match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_synpred55_llvmGrammar2245); if (state.failed) return;

				}
				break;

		}

		if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
			input.consume();
			state.errorRecovery=false;
			state.failed=false;
		}
		else {
			if (state.backtracking>0) {state.failed=true; return;}
			MismatchedSetException mse = new MismatchedSetException(null,input);
			throw mse;
		}
		}

	}
	// $ANTLR end synpred55_llvmGrammar

	// $ANTLR start synpred68_llvmGrammar
	public final void synpred68_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:602:34: ( 'true' )
		// llvmGrammar.g:602:34: 'true'
		{
		match(input,TRUE,FOLLOW_TRUE_in_synpred68_llvmGrammar2689); if (state.failed) return;

		}

	}
	// $ANTLR end synpred68_llvmGrammar

	// $ANTLR start synpred69_llvmGrammar
	public final void synpred69_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:602:41: ( 'false' )
		// llvmGrammar.g:602:41: 'false'
		{
		match(input,FALSE,FOLLOW_FALSE_in_synpred69_llvmGrammar2691); if (state.failed) return;

		}

	}
	// $ANTLR end synpred69_llvmGrammar

	// $ANTLR start synpred70_llvmGrammar
	public final void synpred70_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:602:49: ( result )
		// llvmGrammar.g:602:49: result
		{
		pushFollow(FOLLOW_result_in_synpred70_llvmGrammar2693);
		result();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred70_llvmGrammar

	// $ANTLR start synpred71_llvmGrammar
	public final void synpred71_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:603:29: ( 'true' )
		// llvmGrammar.g:603:29: 'true'
		{
		match(input,TRUE,FOLLOW_TRUE_in_synpred71_llvmGrammar2714); if (state.failed) return;

		}

	}
	// $ANTLR end synpred71_llvmGrammar

	// $ANTLR start synpred72_llvmGrammar
	public final void synpred72_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:603:36: ( 'false' )
		// llvmGrammar.g:603:36: 'false'
		{
		match(input,FALSE,FOLLOW_FALSE_in_synpred72_llvmGrammar2716); if (state.failed) return;

		}

	}
	// $ANTLR end synpred72_llvmGrammar

	// $ANTLR start synpred73_llvmGrammar
	public final void synpred73_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:603:44: ( result )
		// llvmGrammar.g:603:44: result
		{
		pushFollow(FOLLOW_result_in_synpred73_llvmGrammar2718);
		result();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred73_llvmGrammar

	// $ANTLR start synpred91_llvmGrammar
	public final void synpred91_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:614:13: ( ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )? )
		// llvmGrammar.g:614:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
		{
		// llvmGrammar.g:614:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
		int alt138=2;
		int LA138_0 = input.LA(1);
		if ( (LA138_0==LOCAL_PREFIX||LA138_0==PRIMITIVE_DATA_TYPE||LA138_0==START_ANGULAR||(LA138_0 >= START_PARANTHESIS && LA138_0 <= START_SQUARE_BR)) ) {
			alt138=1;
		}
		switch (alt138) {
			case 1 :
				// llvmGrammar.g:614:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
				{
				// llvmGrammar.g:614:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
				// llvmGrammar.g:614:15: data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
				{
				pushFollow(FOLLOW_data_type_in_synpred91_llvmGrammar2828);
				data_type();
				state._fsp--;
				if (state.failed) return;

				// llvmGrammar.g:614:25: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
				int alt134=2;
				int LA134_0 = input.LA(1);
				if ( (LA134_0==FLOATING_LITERAL||LA134_0==GLOBAL_PREFIX||LA134_0==ID||LA134_0==LOCAL_PREFIX||LA134_0==NUMBER) ) {
					alt134=1;
				}
				else if ( (LA134_0==FALSE||LA134_0==TRUE) ) {
					alt134=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return;}
					NoViableAltException nvae =
						new NoViableAltException("", 134, 0, input);
					throw nvae;
				}

				switch (alt134) {
					case 1 :
						// llvmGrammar.g:614:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
						{
						// llvmGrammar.g:614:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
						// llvmGrammar.g:614:27: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
						{
						// llvmGrammar.g:614:27: ( prefix )?
						int alt133=2;
						int LA133_0 = input.LA(1);
						if ( (LA133_0==GLOBAL_PREFIX||LA133_0==LOCAL_PREFIX) ) {
							alt133=1;
						}
						switch (alt133) {
							case 1 :
								// llvmGrammar.g:614:27: prefix
								{
								pushFollow(FOLLOW_prefix_in_synpred91_llvmGrammar2832);
								prefix();
								state._fsp--;
								if (state.failed) return;

								}
								break;

						}

						if ( input.LA(1)==FLOATING_LITERAL||input.LA(1)==ID||input.LA(1)==NUMBER ) {
							input.consume();
							state.errorRecovery=false;
							state.failed=false;
						}
						else {
							if (state.backtracking>0) {state.failed=true; return;}
							MismatchedSetException mse = new MismatchedSetException(null,input);
							throw mse;
						}
						}

						}
						break;
					case 2 :
						// llvmGrammar.g:614:68: truefalse
						{
						pushFollow(FOLLOW_truefalse_in_synpred91_llvmGrammar2847);
						truefalse();
						state._fsp--;
						if (state.failed) return;

						}
						break;

				}

				// llvmGrammar.g:615:4: ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
				loop137:
				while (true) {
					int alt137=2;
					int LA137_0 = input.LA(1);
					if ( (LA137_0==COMMA) ) {
						alt137=1;
					}

					switch (alt137) {
					case 1 :
						// llvmGrammar.g:615:5: COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
						{
						match(input,COMMA,FOLLOW_COMMA_in_synpred91_llvmGrammar2854); if (state.failed) return;

						pushFollow(FOLLOW_data_type_in_synpred91_llvmGrammar2856);
						data_type();
						state._fsp--;
						if (state.failed) return;

						// llvmGrammar.g:615:21: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
						int alt136=2;
						int LA136_0 = input.LA(1);
						if ( (LA136_0==FLOATING_LITERAL||LA136_0==GLOBAL_PREFIX||LA136_0==ID||LA136_0==LOCAL_PREFIX||LA136_0==NUMBER) ) {
							alt136=1;
						}
						else if ( (LA136_0==FALSE||LA136_0==TRUE) ) {
							alt136=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return;}
							NoViableAltException nvae =
								new NoViableAltException("", 136, 0, input);
							throw nvae;
						}

						switch (alt136) {
							case 1 :
								// llvmGrammar.g:615:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
								{
								// llvmGrammar.g:615:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
								// llvmGrammar.g:615:23: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
								{
								// llvmGrammar.g:615:23: ( prefix )?
								int alt135=2;
								int LA135_0 = input.LA(1);
								if ( (LA135_0==GLOBAL_PREFIX||LA135_0==LOCAL_PREFIX) ) {
									alt135=1;
								}
								switch (alt135) {
									case 1 :
										// llvmGrammar.g:615:23: prefix
										{
										pushFollow(FOLLOW_prefix_in_synpred91_llvmGrammar2860);
										prefix();
										state._fsp--;
										if (state.failed) return;

										}
										break;

								}

								if ( input.LA(1)==FLOATING_LITERAL||input.LA(1)==ID||input.LA(1)==NUMBER ) {
									input.consume();
									state.errorRecovery=false;
									state.failed=false;
								}
								else {
									if (state.backtracking>0) {state.failed=true; return;}
									MismatchedSetException mse = new MismatchedSetException(null,input);
									throw mse;
								}
								}

								}
								break;
							case 2 :
								// llvmGrammar.g:615:64: truefalse
								{
								pushFollow(FOLLOW_truefalse_in_synpred91_llvmGrammar2875);
								truefalse();
								state._fsp--;
								if (state.failed) return;

								}
								break;

						}

						}
						break;

					default :
						break loop137;
					}
				}

				}

				}
				break;

		}

		}

	}
	// $ANTLR end synpred91_llvmGrammar

	// $ANTLR start synpred92_llvmGrammar
	public final void synpred92_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:628:58: ( START_PARANTHESIS )
		// llvmGrammar.g:628:58: START_PARANTHESIS
		{
		match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_synpred92_llvmGrammar2986); if (state.failed) return;

		}

	}
	// $ANTLR end synpred92_llvmGrammar

	// $ANTLR start synpred94_llvmGrammar
	public final void synpred94_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:635:13: ( global_array )
		// llvmGrammar.g:635:13: global_array
		{
		pushFollow(FOLLOW_global_array_in_synpred94_llvmGrammar3037);
		global_array();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred94_llvmGrammar

	// $ANTLR start synpred95_llvmGrammar
	public final void synpred95_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:635:28: ( structure_decl )
		// llvmGrammar.g:635:28: structure_decl
		{
		pushFollow(FOLLOW_structure_decl_in_synpred95_llvmGrammar3041);
		structure_decl();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred95_llvmGrammar

	// $ANTLR start synpred96_llvmGrammar
	public final void synpred96_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:635:45: ( structure_obj )
		// llvmGrammar.g:635:45: structure_obj
		{
		pushFollow(FOLLOW_structure_obj_in_synpred96_llvmGrammar3045);
		structure_obj();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred96_llvmGrammar

	// $ANTLR start synpred97_llvmGrammar
	public final void synpred97_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:635:60: ( string_constant )
		// llvmGrammar.g:635:60: string_constant
		{
		pushFollow(FOLLOW_string_constant_in_synpred97_llvmGrammar3048);
		string_constant();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred97_llvmGrammar

	// $ANTLR start synpred120_llvmGrammar
	public final void synpred120_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:674:34: ( data_type )
		// llvmGrammar.g:674:34: data_type
		{
		pushFollow(FOLLOW_data_type_in_synpred120_llvmGrammar3475);
		data_type();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred120_llvmGrammar

	// $ANTLR start synpred121_llvmGrammar
	public final void synpred121_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:675:7: ( NUMBER )
		// llvmGrammar.g:675:7: NUMBER
		{
		match(input,NUMBER,FOLLOW_NUMBER_in_synpred121_llvmGrammar3491); if (state.failed) return;

		}

	}
	// $ANTLR end synpred121_llvmGrammar

	// $ANTLR start synpred123_llvmGrammar
	public final void synpred123_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:675:23: ( ID STRING_LITERAL )
		// llvmGrammar.g:675:23: ID STRING_LITERAL
		{
		match(input,ID,FOLLOW_ID_in_synpred123_llvmGrammar3497); if (state.failed) return;

		match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_synpred123_llvmGrammar3499); if (state.failed) return;

		}

	}
	// $ANTLR end synpred123_llvmGrammar

	// $ANTLR start synpred125_llvmGrammar
	public final void synpred125_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:676:14: ( ( data_type ) )
		// llvmGrammar.g:676:14: ( data_type )
		{
		// llvmGrammar.g:676:14: ( data_type )
		// llvmGrammar.g:676:15: data_type
		{
		pushFollow(FOLLOW_data_type_in_synpred125_llvmGrammar3518);
		data_type();
		state._fsp--;
		if (state.failed) return;

		}

		}

	}
	// $ANTLR end synpred125_llvmGrammar

	// $ANTLR start synpred126_llvmGrammar
	public final void synpred126_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:676:46: ( NUMBER )
		// llvmGrammar.g:676:46: NUMBER
		{
		match(input,NUMBER,FOLLOW_NUMBER_in_synpred126_llvmGrammar3529); if (state.failed) return;

		}

	}
	// $ANTLR end synpred126_llvmGrammar

	// $ANTLR start synpred128_llvmGrammar
	public final void synpred128_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:676:60: ( ID STRING_LITERAL )
		// llvmGrammar.g:676:60: ID STRING_LITERAL
		{
		match(input,ID,FOLLOW_ID_in_synpred128_llvmGrammar3533); if (state.failed) return;

		match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_synpred128_llvmGrammar3535); if (state.failed) return;

		}

	}
	// $ANTLR end synpred128_llvmGrammar

	// $ANTLR start synpred145_llvmGrammar
	public final void synpred145_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:700:14: ( ptr_type )
		// llvmGrammar.g:700:14: ptr_type
		{
		pushFollow(FOLLOW_ptr_type_in_synpred145_llvmGrammar3785);
		ptr_type();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred145_llvmGrammar

	// $ANTLR start synpred146_llvmGrammar
	public final void synpred146_llvmGrammar_fragment() throws RecognitionException {
		Token e=null;


		// llvmGrammar.g:701:6: (e= PRIMITIVE_DATA_TYPE )
		// llvmGrammar.g:701:6: e= PRIMITIVE_DATA_TYPE
		{
		e=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred146_llvmGrammar3795); if (state.failed) return;

		}

	}
	// $ANTLR end synpred146_llvmGrammar

	// $ANTLR start synpred150_llvmGrammar
	public final void synpred150_llvmGrammar_fragment() throws RecognitionException {
		Token e=null;


		// llvmGrammar.g:715:14: ( (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) )
		// llvmGrammar.g:715:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
		{
		// llvmGrammar.g:715:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
		// llvmGrammar.g:715:15: e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
		{
		e=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_synpred150_llvmGrammar3916); if (state.failed) return;

		match(input,NUMBER,FOLLOW_NUMBER_in_synpred150_llvmGrammar3920); if (state.failed) return;

		match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_synpred150_llvmGrammar3922); if (state.failed) return;

		pushFollow(FOLLOW_data_type_in_synpred150_llvmGrammar3924);
		data_type();
		state._fsp--;
		if (state.failed) return;

		match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_synpred150_llvmGrammar3926); if (state.failed) return;

		}

		}

	}
	// $ANTLR end synpred150_llvmGrammar

	// $ANTLR start synpred152_llvmGrammar
	public final void synpred152_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:720:73: ( ptr_type )
		// llvmGrammar.g:720:73: ptr_type
		{
		pushFollow(FOLLOW_ptr_type_in_synpred152_llvmGrammar3970);
		ptr_type();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred152_llvmGrammar

	// $ANTLR start synpred153_llvmGrammar
	public final void synpred153_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:720:83: ( PRIMITIVE_DATA_TYPE )
		// llvmGrammar.g:720:83: PRIMITIVE_DATA_TYPE
		{
		match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred153_llvmGrammar3973); if (state.failed) return;

		}

	}
	// $ANTLR end synpred153_llvmGrammar

	// $ANTLR start synpred154_llvmGrammar
	public final void synpred154_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:721:15: ( ptr_type )
		// llvmGrammar.g:721:15: ptr_type
		{
		pushFollow(FOLLOW_ptr_type_in_synpred154_llvmGrammar3991);
		ptr_type();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred154_llvmGrammar

	// $ANTLR start synpred155_llvmGrammar
	public final void synpred155_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:721:26: ( PRIMITIVE_DATA_TYPE )
		// llvmGrammar.g:721:26: PRIMITIVE_DATA_TYPE
		{
		match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred155_llvmGrammar3995); if (state.failed) return;

		}

	}
	// $ANTLR end synpred155_llvmGrammar

	// $ANTLR start synpred156_llvmGrammar
	public final void synpred156_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:721:48: ( agr_type )
		// llvmGrammar.g:721:48: agr_type
		{
		pushFollow(FOLLOW_agr_type_in_synpred156_llvmGrammar3999);
		agr_type();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred156_llvmGrammar

	// $ANTLR start synpred159_llvmGrammar
	public final void synpred159_llvmGrammar_fragment() throws RecognitionException {
		// llvmGrammar.g:723:17: ( ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) )
		// llvmGrammar.g:723:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
		{
		// llvmGrammar.g:723:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
		// llvmGrammar.g:723:18: START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
		{
		match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_synpred159_llvmGrammar4024); if (state.failed) return;

		match(input,NUMBER,FOLLOW_NUMBER_in_synpred159_llvmGrammar4026); if (state.failed) return;

		match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_synpred159_llvmGrammar4028); if (state.failed) return;

		pushFollow(FOLLOW_data_type_in_synpred159_llvmGrammar4030);
		data_type();
		state._fsp--;
		if (state.failed) return;

		match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_synpred159_llvmGrammar4032); if (state.failed) return;

		}

		}

	}
	// $ANTLR end synpred159_llvmGrammar

	// Delegated rules

	public final boolean synpred128_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred128_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred159_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred159_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred126_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred126_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred49_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred49_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred69_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred69_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred145_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred145_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred156_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred156_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred123_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred123_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred125_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred125_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred68_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred68_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred146_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred146_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred44_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred44_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred97_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred97_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred120_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred120_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred152_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred152_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred55_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred55_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred96_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred96_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred121_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred121_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred95_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred95_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred150_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred150_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred154_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred154_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred73_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred73_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred155_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred155_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred153_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred153_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred70_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred70_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred91_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred91_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred40_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred40_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred72_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred72_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred92_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred92_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred94_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred94_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred71_llvmGrammar() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred71_llvmGrammar_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_ID_in_string487 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_NULL_CHAR_in_string489 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_llvm_element_in_llvm1268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_target_machine_info_in_llvm_element1275 = new BitSet(new long[]{0x0004002424C00000L,0x000000000008000CL});
	public static final BitSet FOLLOW_function_def_in_llvm_element1279 = new BitSet(new long[]{0x0004002424C00002L,0x000000000008000CL});
	public static final BitSet FOLLOW_global_var_list_in_llvm_element1293 = new BitSet(new long[]{0x0004002424C00002L,0x000000000008000CL});
	public static final BitSet FOLLOW_function_declaration_in_llvm_element1307 = new BitSet(new long[]{0x0004002424C00002L,0x000000000008000CL});
	public static final BitSet FOLLOW_target_datalayout_in_target_machine_info1319 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_target_datalayout1329 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_target_datalayout1331 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_target_Data_ID_in_target_datalayout1333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_target_Data_ID1356 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_target_Data_ID1358 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_target_Data_ID1360 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1363 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_target_Data_ID1365 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1367 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_target_Data_ID1369 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1371 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_target_Data_ID1373 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1377 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_target_Data_ID1379 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1381 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_target_Data_ID1383 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1385 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_target_Data_ID1387 = new BitSet(new long[]{0x4000040000000000L});
	public static final BitSet FOLLOW_62_in_target_Data_ID1400 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1406 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_target_Data_ID1408 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1410 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_target_Data_ID1412 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1414 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_target_Data_ID1416 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_global_var_list_element_in_global_var_list1426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_global_var_in_global_var_list_element1436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_function_declaration1447 = new BitSet(new long[]{0x0001AC0600000000L});
	public static final BitSet FOLLOW_LINKAGE_TYPE_in_function_declaration1449 = new BitSet(new long[]{0x0001AC0400000000L});
	public static final BitSet FOLLOW_RETURN_ATTR_in_function_declaration1452 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_function_declaration1456 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_GLOBAL_PREFIX_in_function_declaration1459 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_function_declaration1463 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_function_declaration1465 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_parameter_list_in_function_declaration1477 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_END_PARANTHESIS_in_function_declaration1480 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_function_attr_list_in_function_declaration1482 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_parameter_list1496 = new BitSet(new long[]{0x0000000000000822L});
	public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_parameter_list1500 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_parameters_in_parameter_list1507 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_parameters1537 = new BitSet(new long[]{0x1001A40400000002L});
	public static final BitSet FOLLOW_data_type_in_parameters1542 = new BitSet(new long[]{0x1001A40400000022L});
	public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_parameters1546 = new BitSet(new long[]{0x1001A40400000002L});
	public static final BitSet FOLLOW_60_in_parameters1555 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_function_def1581 = new BitSet(new long[]{0x0001AC0600000000L});
	public static final BitSet FOLLOW_LINKAGE_TYPE_in_function_def1583 = new BitSet(new long[]{0x0001AC0400000000L});
	public static final BitSet FOLLOW_RETURN_ATTR_in_function_def1586 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_function_def1589 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_GLOBAL_PREFIX_in_function_def1592 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_function_def1596 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_function_def1598 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_argument_list_in_function_def1605 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_END_PARANTHESIS_in_function_def1608 = new BitSet(new long[]{0x0000400001000000L});
	public static final BitSet FOLLOW_function_attr_list_in_function_def1615 = new BitSet(new long[]{0x0000400000000002L});
	public static final BitSet FOLLOW_START_CURLY_in_function_def1619 = new BitSet(new long[]{0x800C002424C10000L,0x000000000008E801L});
	public static final BitSet FOLLOW_basic_blocks_in_function_def1633 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_END_CURLY_in_function_def1658 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNCTION_ATTRIBUTE_in_function_attr_list1679 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_data_type_in_argument_list1690 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_argument_list1694 = new BitSet(new long[]{0x0000000000000822L});
	public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_argument_list1698 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_argument_in_argument_list1711 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_argument1728 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_argument1732 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_argument1736 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_argument1740 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_set_in_basic_blocks1773 = new BitSet(new long[]{0x800C002424C00002L,0x000000000008E801L});
	public static final BitSet FOLLOW_binary_instruction_in_instruction_set1784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_memory_rel_instruction_in_instruction_set1794 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_terminator_instruction_in_instruction_set1804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_other_instruction_in_instruction_set1814 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cast_instruction_in_instruction_set1824 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_binary_instruction1835 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_binary_instruction1837 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_BIN_OPR_STR_in_binary_instruction1841 = new BitSet(new long[]{0x0001A40400000080L});
	public static final BitSet FOLLOW_BIN_OPR_FLAG_in_binary_instruction1845 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_binary_instruction1850 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_binary_instruction1854 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_binary_instruction1856 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_binary_instruction1860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_load_in_memory_rel_instruction1889 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alloca_in_memory_rel_instruction1893 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_store_in_memory_rel_instruction1897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_getelementptr_in_memory_rel_instruction1901 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_alloca1916 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_alloca1920 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_alloca1922 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_alloca1926 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_alloca1930 = new BitSet(new long[]{0x0001A40400000010L});
	public static final BitSet FOLLOW_data_type_in_alloca1933 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_alloca1937 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_alloca1939 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_alloca1943 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_alloca1947 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_load1971 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_load1975 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_load1977 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_load1981 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_load1983 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_load1987 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_load1991 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_load1994 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_load1996 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_load1998 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_store2018 = new BitSet(new long[]{0x0001A40400000040L,0x0000000000200000L});
	public static final BitSet FOLLOW_ATOMIC_ORDERING_in_store2022 = new BitSet(new long[]{0x0001A40400000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_store2027 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_store2032 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_store2036 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_store2038 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_store2042 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_store2047 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_store2051 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_store2053 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_store2055 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_result_in_getelementptr2075 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_getelementptr2079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_getelementptr_rhs_in_getelementptr2081 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_getelementptr_rhs2094 = new BitSet(new long[]{0x0001A40400000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_getelementptr_rhs2097 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_getelementptr_rhs2100 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_getelementptr_rhs2105 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_getelementptr_rhs2107 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_ptr_type_in_getelementptr_rhs2113 = new BitSet(new long[]{0x0001A40404000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_element_name_in_getelementptr_rhs2129 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_getelementptr_rhs2131 = new BitSet(new long[]{0x0001A40404000000L});
	public static final BitSet FOLLOW_offset_in_getelementptr_rhs2136 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_END_PARANTHESIS_in_getelementptr_rhs2138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_element_name2173 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_GLOBAL_PREFIX_in_element_name2187 = new BitSet(new long[]{0x0000002020004000L});
	public static final BitSet FOLLOW_DOT_in_element_name2189 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_data_type_in_element_name2201 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_element_name2205 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GLOBAL_PREFIX_in_offset2217 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_offset2219 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_offset2221 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_offset2227 = new BitSet(new long[]{0x0000002420000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_offset2229 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_set_in_offset2232 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_offset2241 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_offset2243 = new BitSet(new long[]{0x0000002420000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_offset2245 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_set_in_offset2248 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ret_in_terminator_instruction2265 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_br_in_terminator_instruction2269 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_switchInstr_in_terminator_instruction2273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreachable_in_terminator_instruction2276 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_ret2290 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_ret2294 = new BitSet(new long[]{0x0004002424C00002L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_ret2299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_br2319 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_br2321 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_br2329 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_br2332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_br2334 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_br2336 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_br2340 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_br2342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_br2344 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_br2346 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_br2350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_br2369 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_br2371 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_br2374 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_set_in_br2378 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_switchInstr2406 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_switchInstr2408 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_switchInstr2412 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_switchInstr2414 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_switchInstr2416 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_switchInstr2420 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_switchInstr2422 = new BitSet(new long[]{0x0001A40400040000L});
	public static final BitSet FOLLOW_cases_list_in_switchInstr2432 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_switchInstr2435 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cases_in_cases_list2458 = new BitSet(new long[]{0x0001A40400000002L});
	public static final BitSet FOLLOW_data_type_in_cases2477 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_set_in_cases2481 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_cases2489 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_cases2491 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_cases2497 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UNREACHABLE_in_unreachable2521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cmp_in_other_instruction2543 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_phi_in_other_instruction2556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_call_in_other_instruction2569 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_in_other_instruction2582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_cmp2593 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_cmp2597 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000090L});
	public static final BitSet FOLLOW_71_in_cmp2604 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_68_in_cmp2606 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_CONDITION_in_cmp2611 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_cmp2615 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_cmp2635 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_cmp2638 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_cmp2645 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_phi2660 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_phi2664 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_PHI_in_phi2666 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_phi2670 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_incoming_list_in_phi2674 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_incoming_list2686 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_TRUE_in_incoming_list2689 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_FALSE_in_incoming_list2691 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_result_in_incoming_list2693 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_83_in_incoming_list2695 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_incoming_list2698 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_incoming_list2700 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_incoming_list2702 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_incoming_list2709 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_incoming_list2711 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_TRUE_in_incoming_list2714 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_FALSE_in_incoming_list2716 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_result_in_incoming_list2718 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_83_in_incoming_list2720 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_incoming_list2724 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_incoming_list2726 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_incoming_list2728 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_result_in_call2747 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_call2749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008001L});
	public static final BitSet FOLLOW_79_in_call2755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_call2760 = new BitSet(new long[]{0x0001A50400000200L});
	public static final BitSet FOLLOW_CALLING_CONV_in_call2764 = new BitSet(new long[]{0x0001A50400000000L});
	public static final BitSet FOLLOW_PARAMETER_ATTRIBUTE_in_call2769 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_call2777 = new BitSet(new long[]{0x0000000404000000L});
	public static final BitSet FOLLOW_func_name_in_call2779 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_call2781 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_parameter_in_call2785 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_END_PARANTHESIS_in_call2787 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_FUNCTION_ATTRIBUTE_in_call2791 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prefix_in_func_name2804 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_set_in_func_name2805 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_parameter2828 = new BitSet(new long[]{0x0004002424C00000L});
	public static final BitSet FOLLOW_prefix_in_parameter2832 = new BitSet(new long[]{0x0000002020800000L});
	public static final BitSet FOLLOW_set_in_parameter2834 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_truefalse_in_parameter2847 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_parameter2854 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_parameter2856 = new BitSet(new long[]{0x0004002424C00000L});
	public static final BitSet FOLLOW_prefix_in_parameter2860 = new BitSet(new long[]{0x0000002020800000L});
	public static final BitSet FOLLOW_set_in_parameter2862 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_truefalse_in_parameter2875 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_func_type_in_parameter2889 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_select2900 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_select2902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_select2906 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_select2910 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_select2914 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_select2916 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_select2924 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_select2928 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_select2930 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_select2938 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_select2942 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_cast_instruction2965 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_cast_instruction2969 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_cast_instr_rhs_in_cast_instruction2971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CAST_TYPE_in_cast_instr_rhs2984 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_cast_instr_rhs2986 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_cast_instr_rhs2991 = new BitSet(new long[]{0x0004002424C00000L,0x0000000000080000L});
	public static final BitSet FOLLOW_result_in_cast_instr_rhs3008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_cast_instr_rhs3010 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_cast_instr_rhs3014 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_END_PARANTHESIS_in_cast_instr_rhs3016 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_global_array_in_global_var3037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_structure_decl_in_global_var3041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_structure_obj_in_global_var3045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_constant_in_global_var3048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_global_variable_in_global_var3052 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_global_array3064 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_global_array3068 = new BitSet(new long[]{0x0041A40620000000L,0x0000000000100042L});
	public static final BitSet FOLLOW_LINKAGE_TYPE_in_global_array3072 = new BitSet(new long[]{0x0041A40420000000L,0x0000000000100042L});
	public static final BitSet FOLLOW_70_in_global_array3076 = new BitSet(new long[]{0x0041A40420000000L,0x0000000000100002L});
	public static final BitSet FOLLOW_84_in_global_array3083 = new BitSet(new long[]{0x0041A40420000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_global_array3094 = new BitSet(new long[]{0x0041A40420000000L});
	public static final BitSet FOLLOW_array_data_type_in_global_array3101 = new BitSet(new long[]{0x0041000020000000L});
	public static final BitSet FOLLOW_list_of_initial_values_in_global_array3107 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_global_array3109 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_global_array3111 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_global_array3115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_array_data_type3133 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_list_of_initial_values3148 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_list_of_initial_values3150 = new BitSet(new long[]{0x0043002020000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_array_initial_value_in_list_of_initial_values3153 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_getelementptr_rhs_in_list_of_initial_values3156 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_COMMA_in_list_of_initial_values3171 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_list_of_initial_values3173 = new BitSet(new long[]{0x0043002020000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_array_initial_value_in_list_of_initial_values3176 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_getelementptr_rhs_in_list_of_initial_values3179 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_list_of_initial_values3185 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ZEROINITIALIZER_in_list_of_initial_values3200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_list_of_initial_values3213 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_list_of_initial_values3215 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_array_initial_value3225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_list_of_initial_values_in_array_initial_value3234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_global_variable3244 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_global_variable3249 = new BitSet(new long[]{0x0001A40600000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_LINKAGE_TYPE_in_global_variable3256 = new BitSet(new long[]{0x0001A40400000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_70_in_global_variable3262 = new BitSet(new long[]{0x0001A40400000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_global_variable3269 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_global_variable3283 = new BitSet(new long[]{0x0000002000000402L,0x0000000000000020L});
	public static final BitSet FOLLOW_global_variable_initial_value_in_global_variable3288 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_global_variable3290 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_global_variable3292 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_global_variable3297 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_global_variable_initial_value3332 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cast_instr_rhs_in_global_variable_initial_value3350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_getelementptr_rhs_in_global_variable_initial_value3369 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_structure_obj3400 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_structure_obj3404 = new BitSet(new long[]{0x0000000600000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_LINKAGE_TYPE_in_structure_obj3408 = new BitSet(new long[]{0x0000000400000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_70_in_structure_obj3411 = new BitSet(new long[]{0x0000000400000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_structure_obj3417 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_structure_obj3421 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_structure_obj3425 = new BitSet(new long[]{0x0040400000000000L});
	public static final BitSet FOLLOW_ZEROINITIALIZER_in_structure_obj3436 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_struct_init_value_in_structure_obj3443 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COMMA_in_structure_obj3446 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_structure_obj3448 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_structure_obj3452 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_CURLY_in_struct_init_value3472 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_struct_init_value3475 = new BitSet(new long[]{0x00430020A0000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_init_value3479 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_struct_init_value3481 = new BitSet(new long[]{0x00430020A0000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_NUMBER_in_struct_init_value3491 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_LETTER_in_struct_init_value3494 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_ID_in_struct_init_value3497 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_struct_init_value3499 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_83_in_struct_init_value3502 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_array_initial_value_in_struct_init_value3505 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_COMMA_in_struct_init_value3514 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_struct_init_value3518 = new BitSet(new long[]{0x00430020A0000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_init_value3522 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_struct_init_value3524 = new BitSet(new long[]{0x00430020A0000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_NUMBER_in_struct_init_value3529 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_LETTER_in_struct_init_value3531 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_ID_in_struct_init_value3533 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_struct_init_value3535 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_83_in_struct_init_value3537 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_array_initial_value_in_struct_init_value3539 = new BitSet(new long[]{0x0000000000010800L});
	public static final BitSet FOLLOW_END_CURLY_in_struct_init_value3544 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_name_in_string_constant3558 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_string_constant3562 = new BitSet(new long[]{0x0000000200000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_LINKAGE_TYPE_in_string_constant3566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_string_constant3571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_string_constant3575 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_string_constant3579 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_string_constant3581 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_string_constant3593 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_string_constant3597 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ALIGN_in_string_constant3599 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_string_constant3603 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GLOBAL_PREFIX_in_string_name3639 = new BitSet(new long[]{0x0000000020004000L});
	public static final BitSet FOLLOW_DOT_in_string_name3641 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_string_name3643 = new BitSet(new long[]{0x0000002000004002L});
	public static final BitSet FOLLOW_NUMBER_in_string_name3645 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_DOT_in_string_name3649 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_string_name3650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_structure_decl3661 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_structure_decl3665 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_EQUAL_in_structure_decl3667 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_structure_decl3669 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_struct_element_in_structure_decl3673 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_END_CURLY_in_structure_decl3675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_struct_element3698 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_element3702 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_struct_name_in_struct_element3704 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_struct_element3709 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_struct_element3713 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_element3717 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_struct_name_in_struct_element3719 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ID_in_struct_name3732 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DOT_in_struct_name3734 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_struct_name3736 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prefix_in_result3745 = new BitSet(new long[]{0x0000002020800000L});
	public static final BitSet FOLLOW_set_in_result3748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_truefalse_in_result3762 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_result3766 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ptr_type_in_data_type3785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_data_type3795 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_derived_data_type_in_data_type3804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_agr_type_in_derived_data_type3821 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_func_type_in_derived_data_type3825 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multiDim_array_type_in_agr_type3835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_struct_type_in_agr_type3839 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multiDim_vector_type_in_agr_type3843 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_ANGULAR_in_multiDim_vector_type3854 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_multiDim_vector_type3858 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_multiDim_vector_type3860 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_vector_type_in_multiDim_vector_type3862 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_END_ANGULAR_in_multiDim_vector_type3864 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_ANGULAR_in_vector_type3874 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_vector_type3878 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_vector_type3880 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_vector_type3882 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_END_ANGULAR_in_vector_type3884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_multiDim_array_type3894 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_multiDim_array_type3898 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_multiDim_array_type3900 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_array_type_in_multiDim_array_type3903 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_multiDim_array_type3905 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_array_type3916 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_array_type3920 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_array_type3922 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_array_type3924 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_array_type3926 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_array_type3930 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_type3941 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_ID_in_struct_type3943 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3956 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_func_type3965 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_ptr_type_in_func_type3970 = new BitSet(new long[]{0x0000000000020800L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3973 = new BitSet(new long[]{0x0000000000020800L});
	public static final BitSet FOLLOW_agr_type_in_func_type3977 = new BitSet(new long[]{0x0000000000020800L});
	public static final BitSet FOLLOW_COMMA_in_func_type3985 = new BitSet(new long[]{0x1001A40400020000L});
	public static final BitSet FOLLOW_ptr_type_in_func_type3991 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3995 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_agr_type_in_func_type3999 = new BitSet(new long[]{0x0001A40400020000L});
	public static final BitSet FOLLOW_60_in_func_type4008 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_END_PARANTHESIS_in_func_type4013 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_function_data4024 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_function_data4026 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_function_data4028 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_function_data4030 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_function_data4032 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_function_data4037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_ptr_type4047 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_derived_data_type_in_ptr_type4056 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_STAR_in_ptr_type4059 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_prefix_in_value4068 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_value4071 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_synpred40_llvmGrammar2100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_synpred44_llvmGrammar2173 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_GLOBAL_PREFIX_in_synpred49_llvmGrammar2187 = new BitSet(new long[]{0x0000002020004000L});
	public static final BitSet FOLLOW_DOT_in_synpred49_llvmGrammar2189 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_data_type_in_synpred49_llvmGrammar2201 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_synpred55_llvmGrammar2241 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_synpred55_llvmGrammar2243 = new BitSet(new long[]{0x0000002420000000L});
	public static final BitSet FOLLOW_LOCAL_PREFIX_in_synpred55_llvmGrammar2245 = new BitSet(new long[]{0x0000002020000000L});
	public static final BitSet FOLLOW_set_in_synpred55_llvmGrammar2248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_synpred68_llvmGrammar2689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_synpred69_llvmGrammar2691 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_synpred70_llvmGrammar2693 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_synpred71_llvmGrammar2714 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_synpred72_llvmGrammar2716 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_synpred73_llvmGrammar2718 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_synpred91_llvmGrammar2828 = new BitSet(new long[]{0x0004002424C00000L});
	public static final BitSet FOLLOW_prefix_in_synpred91_llvmGrammar2832 = new BitSet(new long[]{0x0000002020800000L});
	public static final BitSet FOLLOW_set_in_synpred91_llvmGrammar2834 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_truefalse_in_synpred91_llvmGrammar2847 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_COMMA_in_synpred91_llvmGrammar2854 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_synpred91_llvmGrammar2856 = new BitSet(new long[]{0x0004002424C00000L});
	public static final BitSet FOLLOW_prefix_in_synpred91_llvmGrammar2860 = new BitSet(new long[]{0x0000002020800000L});
	public static final BitSet FOLLOW_set_in_synpred91_llvmGrammar2862 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_truefalse_in_synpred91_llvmGrammar2875 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_START_PARANTHESIS_in_synpred92_llvmGrammar2986 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_global_array_in_synpred94_llvmGrammar3037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_structure_decl_in_synpred95_llvmGrammar3041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_structure_obj_in_synpred96_llvmGrammar3045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_constant_in_synpred97_llvmGrammar3048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_synpred120_llvmGrammar3475 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_synpred121_llvmGrammar3491 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_synpred123_llvmGrammar3497 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_synpred123_llvmGrammar3499 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_type_in_synpred125_llvmGrammar3518 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_synpred126_llvmGrammar3529 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_synpred128_llvmGrammar3533 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_synpred128_llvmGrammar3535 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ptr_type_in_synpred145_llvmGrammar3785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred146_llvmGrammar3795 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_synpred150_llvmGrammar3916 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_synpred150_llvmGrammar3920 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_synpred150_llvmGrammar3922 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_synpred150_llvmGrammar3924 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_synpred150_llvmGrammar3926 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ptr_type_in_synpred152_llvmGrammar3970 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred153_llvmGrammar3973 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ptr_type_in_synpred154_llvmGrammar3991 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred155_llvmGrammar3995 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_agr_type_in_synpred156_llvmGrammar3999 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_START_SQUARE_BR_in_synpred159_llvmGrammar4024 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NUMBER_in_synpred159_llvmGrammar4026 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_MUL_OPERATOR_in_synpred159_llvmGrammar4028 = new BitSet(new long[]{0x0001A40400000000L});
	public static final BitSet FOLLOW_data_type_in_synpred159_llvmGrammar4030 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_END_SQUARE_BR_in_synpred159_llvmGrammar4032 = new BitSet(new long[]{0x0000000000000002L});
}
