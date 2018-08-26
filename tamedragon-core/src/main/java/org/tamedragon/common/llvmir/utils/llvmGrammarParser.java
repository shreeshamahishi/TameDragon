// $ANTLR 3.3 Nov 30, 2010 12:50:56 Resources/LLVM/llvmGrammar.g 2014-07-15 15:17:42

package org.tamedragon.common.llvmir.utils;
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
import org.tamedragon.common.llvmir.instructions.*;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.irdata.*;
import org.tamedragon.common.llvmir.math.*;
import org.tamedragon.common.llvmir.semanticanalysis.*;
import org.tamedragon.common.llvmir.types.*;
import org.tamedragon.common.llvmir.types.exceptions.*;

public class llvmGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "XOR", "PHI", "UNREACHABLE", "TRUE", "FALSE", "ALIGN", "LINKAGE_TYPE", "RETURN_ATTR", "ZEROINITIALIZER", "CAST_TYPE", "ARGUMENT_ATTRIBUTE", "PARAMETER_ATTRIBUTE", "FUNCTION_ATTRIBUTE", "CALLING_CONV", "CONDITION", "NULL_CHAR", "BIN_OPR_STR", "ATOMIC_ORDERING", "ID", "PRIMITIVE_DATA_TYPE", "GLOBAL_PREFIX", "LOCAL_PREFIX", "EQUAL", "COMMA", "STAR", "START_PARANTHESIS", "END_PARANTHESIS", "START_CURLY", "END_CURLY", "START_ANGULAR", "END_ANGULAR", "START_SQUARE_BR", "END_SQUARE_BR", "DOT", "MUL_OPERATOR", "NUMBER", "IntegerTypeSuffix", "DECIMAL_LITERAL", "OCTAL_LITERAL", "HexDigit", "HEX_LITERAL", "Exponent", "FloatTypeSuffix", "FLOATING_LITERAL", "EscapeSequence", "STRING_LITERAL", "OctalEscape", "LETTER", "WHITESPACE", "LINE_COMMENT", "'target datalayout'", "' \"'", "'-p:'", "':'", "'-'", "'S32\"'", "'\"'", "'declare'", "'...'", "'define'", "'nsw'", "'nuw'", "'exact'", "' alloca '", "'load'", "'store'", "'volatile'", "'getelementptr'", "'inbounds'", "'undef'", "'ret'", "'br'", "'label'", "'switch'", "'icmp'", "'fcmp'", "'tail'", "'call'", "'select'", "'to'", "'global'", "'unnamed_addr'", "'constant'", "'type {'"
    };
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


        public llvmGrammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public llvmGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[237+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return llvmGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "Resources/LLVM/llvmGrammar.g"; }



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

    	private void setLoadInstr(String id, String pointerName, String typeStr, int lineNum, int position) {	
    		LoadInstrData load =new LoadInstrData();
    		load.setPointerName(pointerName);
    		load.setResult(id);
    		load.setTypeStr(typeStr);
    		load.setLineNum(lineNum);
    		load.setPosition(position);
    	    list.add(load);
    	}
    	
    	private void setGetElementPtr(String name, String elementName, String ptrType, String offset, 
    								  String inbound, int lineNum, int position) {
    		GetElementPtrData data = new GetElementPtrData();
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
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string"
    // Resources/LLVM/llvmGrammar.g:357:1: string : ID NULL_CHAR ;
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
            // Resources/LLVM/llvmGrammar.g:357:8: ( ID NULL_CHAR )
            // Resources/LLVM/llvmGrammar.g:357:10: ID NULL_CHAR
            {
            root_0 = (Object)adaptor.nil();

            ID1=(Token)match(input,ID,FOLLOW_ID_in_string471); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID1_tree = (Object)adaptor.create(ID1);
            adaptor.addChild(root_0, ID1_tree);
            }
            NULL_CHAR2=(Token)match(input,NULL_CHAR,FOLLOW_NULL_CHAR_in_string473); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 1, string_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "string"

    public static class prefix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "prefix"
    // Resources/LLVM/llvmGrammar.g:360:1: prefix : ( GLOBAL_PREFIX | LOCAL_PREFIX );
    public final llvmGrammarParser.prefix_return prefix() throws RecognitionException {
        llvmGrammarParser.prefix_return retval = new llvmGrammarParser.prefix_return();
        retval.start = input.LT(1);
        int prefix_StartIndex = input.index();
        Object root_0 = null;

        Token set3=null;

        Object set3_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:360:8: ( GLOBAL_PREFIX | LOCAL_PREFIX )
            // Resources/LLVM/llvmGrammar.g:
            {
            root_0 = (Object)adaptor.nil();

            set3=(Token)input.LT(1);
            if ( (input.LA(1)>=GLOBAL_PREFIX && input.LA(1)<=LOCAL_PREFIX) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set3));
                state.errorRecovery=false;state.failed=false;
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
            if ( state.backtracking>0 ) { memoize(input, 2, prefix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "prefix"

    public static class llvm_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "llvm"
    // Resources/LLVM/llvmGrammar.g:437:1: llvm : llvm_element ;
    public final llvmGrammarParser.llvm_return llvm() throws RecognitionException {
        llvmGrammarParser.llvm_return retval = new llvmGrammarParser.llvm_return();
        retval.start = input.LT(1);
        int llvm_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.llvm_element_return llvm_element4 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:437:6: ( llvm_element )
            // Resources/LLVM/llvmGrammar.g:437:8: llvm_element
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_llvm_element_in_llvm1252);
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
            if ( state.backtracking>0 ) { memoize(input, 3, llvm_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "llvm"

    public static class llvm_element_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "llvm_element"
    // Resources/LLVM/llvmGrammar.g:439:1: llvm_element : ( target_machine_info )? ( function_def | global_var_list | function_declaration )+ ;
    public final llvmGrammarParser.llvm_element_return llvm_element() throws RecognitionException {
        llvmGrammarParser.llvm_element_return retval = new llvmGrammarParser.llvm_element_return();
        retval.start = input.LT(1);
        int llvm_element_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.target_machine_info_return target_machine_info5 = null;

        llvmGrammarParser.function_def_return function_def6 = null;

        llvmGrammarParser.global_var_list_return global_var_list7 = null;

        llvmGrammarParser.function_declaration_return function_declaration8 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:439:13: ( ( target_machine_info )? ( function_def | global_var_list | function_declaration )+ )
            // Resources/LLVM/llvmGrammar.g:439:15: ( target_machine_info )? ( function_def | global_var_list | function_declaration )+
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:439:15: ( target_machine_info )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==54) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: target_machine_info
                    {
                    pushFollow(FOLLOW_target_machine_info_in_llvm_element1259);
                    target_machine_info5=target_machine_info();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, target_machine_info5.getTree());

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:439:36: ( function_def | global_var_list | function_declaration )+
            int cnt2=0;
            loop2:
            do {
                int alt2=4;
                switch ( input.LA(1) ) {
                case 63:
                    {
                    alt2=1;
                    }
                    break;
                case TRUE:
                case FALSE:
                case ID:
                case GLOBAL_PREFIX:
                case LOCAL_PREFIX:
                case NUMBER:
                case FLOATING_LITERAL:
                case 73:
                    {
                    alt2=2;
                    }
                    break;
                case 61:
                    {
                    alt2=3;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:439:37: function_def
            	    {
            	    pushFollow(FOLLOW_function_def_in_llvm_element1263);
            	    function_def6=function_def();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, function_def6.getTree());

            	    }
            	    break;
            	case 2 :
            	    // Resources/LLVM/llvmGrammar.g:440:12: global_var_list
            	    {
            	    pushFollow(FOLLOW_global_var_list_in_llvm_element1277);
            	    global_var_list7=global_var_list();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, global_var_list7.getTree());

            	    }
            	    break;
            	case 3 :
            	    // Resources/LLVM/llvmGrammar.g:441:12: function_declaration
            	    {
            	    pushFollow(FOLLOW_function_declaration_in_llvm_element1291);
            	    function_declaration8=function_declaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, function_declaration8.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 4, llvm_element_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "llvm_element"

    public static class target_machine_info_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "target_machine_info"
    // Resources/LLVM/llvmGrammar.g:443:1: target_machine_info : target_datalayout ;
    public final llvmGrammarParser.target_machine_info_return target_machine_info() throws RecognitionException {
        llvmGrammarParser.target_machine_info_return retval = new llvmGrammarParser.target_machine_info_return();
        retval.start = input.LT(1);
        int target_machine_info_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.target_datalayout_return target_datalayout9 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:443:21: ( target_datalayout )
            // Resources/LLVM/llvmGrammar.g:443:23: target_datalayout
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_target_datalayout_in_target_machine_info1303);
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
            if ( state.backtracking>0 ) { memoize(input, 5, target_machine_info_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "target_machine_info"

    public static class target_datalayout_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "target_datalayout"
    // Resources/LLVM/llvmGrammar.g:445:1: target_datalayout : e1= 'target datalayout' EQUAL target_Data_ID ;
    public final llvmGrammarParser.target_datalayout_return target_datalayout() throws RecognitionException {
        llvmGrammarParser.target_datalayout_return retval = new llvmGrammarParser.target_datalayout_return();
        retval.start = input.LT(1);
        int target_datalayout_StartIndex = input.index();
        Object root_0 = null;

        Token e1=null;
        Token EQUAL10=null;
        llvmGrammarParser.target_Data_ID_return target_Data_ID11 = null;


        Object e1_tree=null;
        Object EQUAL10_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:445:19: (e1= 'target datalayout' EQUAL target_Data_ID )
            // Resources/LLVM/llvmGrammar.g:445:21: e1= 'target datalayout' EQUAL target_Data_ID
            {
            root_0 = (Object)adaptor.nil();

            e1=(Token)match(input,54,FOLLOW_54_in_target_datalayout1313); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e1_tree = (Object)adaptor.create(e1);
            adaptor.addChild(root_0, e1_tree);
            }
            EQUAL10=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_target_datalayout1315); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EQUAL10_tree = (Object)adaptor.create(EQUAL10);
            adaptor.addChild(root_0, EQUAL10_tree);
            }
            pushFollow(FOLLOW_target_Data_ID_in_target_datalayout1317);
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
            if ( state.backtracking>0 ) { memoize(input, 6, target_datalayout_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "target_datalayout"

    public static class target_Data_ID_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "target_Data_ID"
    // Resources/LLVM/llvmGrammar.g:450:1: target_Data_ID : ' \"' ID '-p:' NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+ ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) ) ;
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
            // Resources/LLVM/llvmGrammar.g:450:16: ( ' \"' ID '-p:' NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+ ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) ) )
            // Resources/LLVM/llvmGrammar.g:450:20: ' \"' ID '-p:' NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+ ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) )
            {
            root_0 = (Object)adaptor.nil();

            string_literal12=(Token)match(input,55,FOLLOW_55_in_target_Data_ID1340); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal12_tree = (Object)adaptor.create(string_literal12);
            adaptor.addChild(root_0, string_literal12_tree);
            }
            ID13=(Token)match(input,ID,FOLLOW_ID_in_target_Data_ID1342); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID13_tree = (Object)adaptor.create(ID13);
            adaptor.addChild(root_0, ID13_tree);
            }
            string_literal14=(Token)match(input,56,FOLLOW_56_in_target_Data_ID1344); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal14_tree = (Object)adaptor.create(string_literal14);
            adaptor.addChild(root_0, string_literal14_tree);
            }
            NUMBER15=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1347); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER15_tree = (Object)adaptor.create(NUMBER15);
            adaptor.addChild(root_0, NUMBER15_tree);
            }
            char_literal16=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1349); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal16_tree = (Object)adaptor.create(char_literal16);
            adaptor.addChild(root_0, char_literal16_tree);
            }
            NUMBER17=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1351); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER17_tree = (Object)adaptor.create(NUMBER17);
            adaptor.addChild(root_0, NUMBER17_tree);
            }
            char_literal18=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1353); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal18_tree = (Object)adaptor.create(char_literal18);
            adaptor.addChild(root_0, char_literal18_tree);
            }
            NUMBER19=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1355); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER19_tree = (Object)adaptor.create(NUMBER19);
            adaptor.addChild(root_0, NUMBER19_tree);
            }
            char_literal20=(Token)match(input,58,FOLLOW_58_in_target_Data_ID1357); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal20_tree = (Object)adaptor.create(char_literal20);
            adaptor.addChild(root_0, char_literal20_tree);
            }
            // Resources/LLVM/llvmGrammar.g:450:68: ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==PRIMITIVE_DATA_TYPE) ) {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==57) ) {
                        int LA3_3 = input.LA(3);

                        if ( (LA3_3==NUMBER) ) {
                            int LA3_4 = input.LA(4);

                            if ( (LA3_4==57) ) {
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
            	    // Resources/LLVM/llvmGrammar.g:450:70: PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-'
            	    {
            	    PRIMITIVE_DATA_TYPE21=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1361); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    PRIMITIVE_DATA_TYPE21_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE21);
            	    adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE21_tree);
            	    }
            	    char_literal22=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1363); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal22_tree = (Object)adaptor.create(char_literal22);
            	    adaptor.addChild(root_0, char_literal22_tree);
            	    }
            	    NUMBER23=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1365); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NUMBER23_tree = (Object)adaptor.create(NUMBER23);
            	    adaptor.addChild(root_0, NUMBER23_tree);
            	    }
            	    char_literal24=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1367); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal24_tree = (Object)adaptor.create(char_literal24);
            	    adaptor.addChild(root_0, char_literal24_tree);
            	    }
            	    NUMBER25=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1369); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NUMBER25_tree = (Object)adaptor.create(NUMBER25);
            	    adaptor.addChild(root_0, NUMBER25_tree);
            	    }
            	    char_literal26=(Token)match(input,58,FOLLOW_58_in_target_Data_ID1371); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal26_tree = (Object)adaptor.create(char_literal26);
            	    adaptor.addChild(root_0, char_literal26_tree);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // Resources/LLVM/llvmGrammar.g:451:7: ( 'S32\"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==59) ) {
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
                    // Resources/LLVM/llvmGrammar.g:451:8: 'S32\"'
                    {
                    string_literal27=(Token)match(input,59,FOLLOW_59_in_target_Data_ID1384); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal27_tree = (Object)adaptor.create(string_literal27);
                    adaptor.addChild(root_0, string_literal27_tree);
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:451:17: ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' )
                    {
                    // Resources/LLVM/llvmGrammar.g:451:17: ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"' )
                    // Resources/LLVM/llvmGrammar.g:451:19: PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '\"'
                    {
                    PRIMITIVE_DATA_TYPE28=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1390); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PRIMITIVE_DATA_TYPE28_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE28);
                    adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE28_tree);
                    }
                    char_literal29=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1392); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal29_tree = (Object)adaptor.create(char_literal29);
                    adaptor.addChild(root_0, char_literal29_tree);
                    }
                    NUMBER30=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1394); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER30_tree = (Object)adaptor.create(NUMBER30);
                    adaptor.addChild(root_0, NUMBER30_tree);
                    }
                    char_literal31=(Token)match(input,57,FOLLOW_57_in_target_Data_ID1396); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal31_tree = (Object)adaptor.create(char_literal31);
                    adaptor.addChild(root_0, char_literal31_tree);
                    }
                    NUMBER32=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_target_Data_ID1398); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER32_tree = (Object)adaptor.create(NUMBER32);
                    adaptor.addChild(root_0, NUMBER32_tree);
                    }
                    char_literal33=(Token)match(input,60,FOLLOW_60_in_target_Data_ID1400); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 7, target_Data_ID_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "target_Data_ID"

    public static class global_var_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_var_list"
    // Resources/LLVM/llvmGrammar.g:453:1: global_var_list : global_var_list_element ;
    public final llvmGrammarParser.global_var_list_return global_var_list() throws RecognitionException {
        llvmGrammarParser.global_var_list_return retval = new llvmGrammarParser.global_var_list_return();
        retval.start = input.LT(1);
        int global_var_list_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.global_var_list_element_return global_var_list_element34 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:453:17: ( global_var_list_element )
            // Resources/LLVM/llvmGrammar.g:453:19: global_var_list_element
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_global_var_list_element_in_global_var_list1410);
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
            if ( state.backtracking>0 ) { memoize(input, 8, global_var_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_var_list"

    public static class global_var_list_element_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_var_list_element"
    // Resources/LLVM/llvmGrammar.g:455:1: global_var_list_element : global_var ;
    public final llvmGrammarParser.global_var_list_element_return global_var_list_element() throws RecognitionException {
        llvmGrammarParser.global_var_list_element_return retval = new llvmGrammarParser.global_var_list_element_return();
        retval.start = input.LT(1);
        int global_var_list_element_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.global_var_return global_var35 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:455:25: ( global_var )
            // Resources/LLVM/llvmGrammar.g:455:28: global_var
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_global_var_in_global_var_list_element1420);
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
            if ( state.backtracking>0 ) { memoize(input, 9, global_var_list_element_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_var_list_element"

    public static class function_declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_declaration"
    // Resources/LLVM/llvmGrammar.g:457:1: function_declaration : e0= 'declare' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( parameter_list )? END_PARANTHESIS function_attr_list ;
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
        llvmGrammarParser.data_type_return data_type38 = null;

        llvmGrammarParser.parameter_list_return parameter_list41 = null;

        llvmGrammarParser.function_attr_list_return function_attr_list43 = null;


        Object e0_tree=null;
        Object e1_tree=null;
        Object LINKAGE_TYPE36_tree=null;
        Object RETURN_ATTR37_tree=null;
        Object GLOBAL_PREFIX39_tree=null;
        Object START_PARANTHESIS40_tree=null;
        Object END_PARANTHESIS42_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:457:22: (e0= 'declare' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( parameter_list )? END_PARANTHESIS function_attr_list )
            // Resources/LLVM/llvmGrammar.g:457:24: e0= 'declare' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( parameter_list )? END_PARANTHESIS function_attr_list
            {
            root_0 = (Object)adaptor.nil();

            e0=(Token)match(input,61,FOLLOW_61_in_function_declaration1431); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e0_tree = (Object)adaptor.create(e0);
            adaptor.addChild(root_0, e0_tree);
            }
            // Resources/LLVM/llvmGrammar.g:457:37: ( LINKAGE_TYPE )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LINKAGE_TYPE) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: LINKAGE_TYPE
                    {
                    LINKAGE_TYPE36=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_function_declaration1433); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LINKAGE_TYPE36_tree = (Object)adaptor.create(LINKAGE_TYPE36);
                    adaptor.addChild(root_0, LINKAGE_TYPE36_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:457:51: ( RETURN_ATTR )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RETURN_ATTR) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: RETURN_ATTR
                    {
                    RETURN_ATTR37=(Token)match(input,RETURN_ATTR,FOLLOW_RETURN_ATTR_in_function_declaration1436); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RETURN_ATTR37_tree = (Object)adaptor.create(RETURN_ATTR37);
                    adaptor.addChild(root_0, RETURN_ATTR37_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:457:64: ( data_type )
            // Resources/LLVM/llvmGrammar.g:457:65: data_type
            {
            pushFollow(FOLLOW_data_type_in_function_declaration1440);
            data_type38=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type38.getTree());

            }

            GLOBAL_PREFIX39=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_function_declaration1443); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            GLOBAL_PREFIX39_tree = (Object)adaptor.create(GLOBAL_PREFIX39);
            adaptor.addChild(root_0, GLOBAL_PREFIX39_tree);
            }
            e1=(Token)match(input,ID,FOLLOW_ID_in_function_declaration1447); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e1_tree = (Object)adaptor.create(e1);
            adaptor.addChild(root_0, e1_tree);
            }
            START_PARANTHESIS40=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_function_declaration1449); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            START_PARANTHESIS40_tree = (Object)adaptor.create(START_PARANTHESIS40);
            adaptor.addChild(root_0, START_PARANTHESIS40_tree);
            }
            // Resources/LLVM/llvmGrammar.g:458:11: ( parameter_list )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==PRIMITIVE_DATA_TYPE||LA7_0==LOCAL_PREFIX||LA7_0==START_PARANTHESIS||LA7_0==START_ANGULAR||LA7_0==START_SQUARE_BR) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: parameter_list
                    {
                    pushFollow(FOLLOW_parameter_list_in_function_declaration1461);
                    parameter_list41=parameter_list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameter_list41.getTree());

                    }
                    break;

            }

            END_PARANTHESIS42=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_function_declaration1464); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_PARANTHESIS42_tree = (Object)adaptor.create(END_PARANTHESIS42);
            adaptor.addChild(root_0, END_PARANTHESIS42_tree);
            }
            pushFollow(FOLLOW_function_attr_list_in_function_declaration1466);
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
            if ( state.backtracking>0 ) { memoize(input, 10, function_declaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "function_declaration"

    public static class parameter_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter_list"
    // Resources/LLVM/llvmGrammar.g:464:1: parameter_list : (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? ) ( parameters )* ;
    public final llvmGrammarParser.parameter_list_return parameter_list() throws RecognitionException {
        llvmGrammarParser.parameter_list_return retval = new llvmGrammarParser.parameter_list_return();
        retval.start = input.LT(1);
        int parameter_list_StartIndex = input.index();
        Object root_0 = null;

        Token e4=null;
        llvmGrammarParser.data_type_return e1 = null;

        llvmGrammarParser.parameters_return parameters44 = null;


        Object e4_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:464:16: ( (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? ) ( parameters )* )
            // Resources/LLVM/llvmGrammar.g:464:18: (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? ) ( parameters )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:464:18: (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )
            // Resources/LLVM/llvmGrammar.g:464:19: e1= data_type (e4= ARGUMENT_ATTRIBUTE )?
            {
            pushFollow(FOLLOW_data_type_in_parameter_list1480);
            e1=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());
            // Resources/LLVM/llvmGrammar.g:464:34: (e4= ARGUMENT_ATTRIBUTE )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ARGUMENT_ATTRIBUTE) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e4= ARGUMENT_ATTRIBUTE
                    {
                    e4=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_parameter_list1484); if (state.failed) return retval;
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
            // Resources/LLVM/llvmGrammar.g:467:7: ( parameters )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==COMMA) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: parameters
            	    {
            	    pushFollow(FOLLOW_parameters_in_parameter_list1491);
            	    parameters44=parameters();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameters44.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 11, parameter_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parameter_list"

    public static class parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // Resources/LLVM/llvmGrammar.g:469:1: parameters : ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? ) ;
    public final llvmGrammarParser.parameters_return parameters() throws RecognitionException {
        llvmGrammarParser.parameters_return retval = new llvmGrammarParser.parameters_return();
        retval.start = input.LT(1);
        int parameters_StartIndex = input.index();
        Object root_0 = null;

        Token e4=null;
        Token var=null;
        Token COMMA45=null;
        llvmGrammarParser.data_type_return e1 = null;


        Object e4_tree=null;
        Object var_tree=null;
        Object COMMA45_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:469:12: ( ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? ) )
            // Resources/LLVM/llvmGrammar.g:469:13: ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:469:13: ( COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )? )
            // Resources/LLVM/llvmGrammar.g:469:14: COMMA (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )* (var= '...' )?
            {
            COMMA45=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters1521); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA45_tree = (Object)adaptor.create(COMMA45);
            adaptor.addChild(root_0, COMMA45_tree);
            }
            // Resources/LLVM/llvmGrammar.g:469:20: (e1= data_type (e4= ARGUMENT_ATTRIBUTE )? )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==PRIMITIVE_DATA_TYPE||LA11_0==LOCAL_PREFIX||LA11_0==START_PARANTHESIS||LA11_0==START_ANGULAR||LA11_0==START_SQUARE_BR) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:469:21: e1= data_type (e4= ARGUMENT_ATTRIBUTE )?
            	    {
            	    pushFollow(FOLLOW_data_type_in_parameters1526);
            	    e1=data_type();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());
            	    // Resources/LLVM/llvmGrammar.g:469:36: (e4= ARGUMENT_ATTRIBUTE )?
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==ARGUMENT_ATTRIBUTE) ) {
            	        alt10=1;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // Resources/LLVM/llvmGrammar.g:0:0: e4= ARGUMENT_ATTRIBUTE
            	            {
            	            e4=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_parameters1530); if (state.failed) return retval;
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
            } while (true);

            // Resources/LLVM/llvmGrammar.g:469:60: (var= '...' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==62) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:469:61: var= '...'
                    {
                    var=(Token)match(input,62,FOLLOW_62_in_parameters1539); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 12, parameters_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class function_def_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_def"
    // Resources/LLVM/llvmGrammar.g:479:1: function_def : e0= 'define' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( argument_list )* END_PARANTHESIS function_attr_list ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )? ;
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
        llvmGrammarParser.data_type_return data_type48 = null;

        llvmGrammarParser.argument_list_return argument_list51 = null;

        llvmGrammarParser.function_attr_list_return function_attr_list53 = null;

        llvmGrammarParser.basic_blocks_return basic_blocks55 = null;


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
            // Resources/LLVM/llvmGrammar.g:479:13: (e0= 'define' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( argument_list )* END_PARANTHESIS function_attr_list ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )? )
            // Resources/LLVM/llvmGrammar.g:479:15: e0= 'define' ( LINKAGE_TYPE )? ( RETURN_ATTR )? ( data_type ) GLOBAL_PREFIX e1= ID START_PARANTHESIS ( argument_list )* END_PARANTHESIS function_attr_list ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )?
            {
            root_0 = (Object)adaptor.nil();

            e0=(Token)match(input,63,FOLLOW_63_in_function_def1565); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e0_tree = (Object)adaptor.create(e0);
            adaptor.addChild(root_0, e0_tree);
            }
            // Resources/LLVM/llvmGrammar.g:479:27: ( LINKAGE_TYPE )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LINKAGE_TYPE) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: LINKAGE_TYPE
                    {
                    LINKAGE_TYPE46=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_function_def1567); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LINKAGE_TYPE46_tree = (Object)adaptor.create(LINKAGE_TYPE46);
                    adaptor.addChild(root_0, LINKAGE_TYPE46_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:479:41: ( RETURN_ATTR )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RETURN_ATTR) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: RETURN_ATTR
                    {
                    RETURN_ATTR47=(Token)match(input,RETURN_ATTR,FOLLOW_RETURN_ATTR_in_function_def1570); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RETURN_ATTR47_tree = (Object)adaptor.create(RETURN_ATTR47);
                    adaptor.addChild(root_0, RETURN_ATTR47_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:479:53: ( data_type )
            // Resources/LLVM/llvmGrammar.g:479:54: data_type
            {
            pushFollow(FOLLOW_data_type_in_function_def1573);
            data_type48=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type48.getTree());

            }

            GLOBAL_PREFIX49=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_function_def1576); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            GLOBAL_PREFIX49_tree = (Object)adaptor.create(GLOBAL_PREFIX49);
            adaptor.addChild(root_0, GLOBAL_PREFIX49_tree);
            }
            e1=(Token)match(input,ID,FOLLOW_ID_in_function_def1580); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e1_tree = (Object)adaptor.create(e1);
            adaptor.addChild(root_0, e1_tree);
            }
            START_PARANTHESIS50=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_function_def1582); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            START_PARANTHESIS50_tree = (Object)adaptor.create(START_PARANTHESIS50);
            adaptor.addChild(root_0, START_PARANTHESIS50_tree);
            }
            // Resources/LLVM/llvmGrammar.g:480:6: ( argument_list )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==PRIMITIVE_DATA_TYPE||LA15_0==LOCAL_PREFIX||LA15_0==START_PARANTHESIS||LA15_0==START_ANGULAR||LA15_0==START_SQUARE_BR) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: argument_list
            	    {
            	    pushFollow(FOLLOW_argument_list_in_function_def1589);
            	    argument_list51=argument_list();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument_list51.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            END_PARANTHESIS52=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_function_def1592); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_PARANTHESIS52_tree = (Object)adaptor.create(END_PARANTHESIS52);
            adaptor.addChild(root_0, END_PARANTHESIS52_tree);
            }
            pushFollow(FOLLOW_function_attr_list_in_function_def1599);
            function_attr_list53=function_attr_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, function_attr_list53.getTree());
            // Resources/LLVM/llvmGrammar.g:481:25: ( START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==START_CURLY) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:481:27: START_CURLY basic_blocks[$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,\r\n\t\t\t\t\t\t\t\t\t $function_attr_list.text, $e0.line, $e0.pos] END_CURLY
                    {
                    START_CURLY54=(Token)match(input,START_CURLY,FOLLOW_START_CURLY_in_function_def1603); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    START_CURLY54_tree = (Object)adaptor.create(START_CURLY54);
                    adaptor.addChild(root_0, START_CURLY54_tree);
                    }
                    pushFollow(FOLLOW_basic_blocks_in_function_def1617);
                    basic_blocks55=basic_blocks((LINKAGE_TYPE46!=null?LINKAGE_TYPE46.getText():null), (RETURN_ATTR47!=null?RETURN_ATTR47.getText():null), (data_type48!=null?input.toString(data_type48.start,data_type48.stop):null), (e1!=null?e1.getText():null), (function_attr_list53!=null?input.toString(function_attr_list53.start,function_attr_list53.stop):null), (e0!=null?e0.getLine():0), (e0!=null?e0.getCharPositionInLine():0));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, basic_blocks55.getTree());
                    END_CURLY56=(Token)match(input,END_CURLY,FOLLOW_END_CURLY_in_function_def1642); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 13, function_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "function_def"

    public static class function_attr_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_attr_list"
    // Resources/LLVM/llvmGrammar.g:486:1: function_attr_list : ( FUNCTION_ATTRIBUTE )* ;
    public final llvmGrammarParser.function_attr_list_return function_attr_list() throws RecognitionException {
        llvmGrammarParser.function_attr_list_return retval = new llvmGrammarParser.function_attr_list_return();
        retval.start = input.LT(1);
        int function_attr_list_StartIndex = input.index();
        Object root_0 = null;

        Token FUNCTION_ATTRIBUTE57=null;

        Object FUNCTION_ATTRIBUTE57_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:486:20: ( ( FUNCTION_ATTRIBUTE )* )
            // Resources/LLVM/llvmGrammar.g:486:22: ( FUNCTION_ATTRIBUTE )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:486:22: ( FUNCTION_ATTRIBUTE )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==FUNCTION_ATTRIBUTE) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: FUNCTION_ATTRIBUTE
            	    {
            	    FUNCTION_ATTRIBUTE57=(Token)match(input,FUNCTION_ATTRIBUTE,FOLLOW_FUNCTION_ATTRIBUTE_in_function_attr_list1663); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    FUNCTION_ATTRIBUTE57_tree = (Object)adaptor.create(FUNCTION_ATTRIBUTE57);
            	    adaptor.addChild(root_0, FUNCTION_ATTRIBUTE57_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 14, function_attr_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "function_attr_list"

    public static class argument_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "argument_list"
    // Resources/LLVM/llvmGrammar.g:488:1: argument_list : (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ( argument )* ;
    public final llvmGrammarParser.argument_list_return argument_list() throws RecognitionException {
        llvmGrammarParser.argument_list_return retval = new llvmGrammarParser.argument_list_return();
        retval.start = input.LT(1);
        int argument_list_StartIndex = input.index();
        Object root_0 = null;

        Token e3=null;
        llvmGrammarParser.data_type_return e1 = null;

        llvmGrammarParser.result_return e2 = null;

        llvmGrammarParser.argument_return argument58 = null;


        Object e3_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:488:15: ( (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ( argument )* )
            // Resources/LLVM/llvmGrammar.g:488:16: (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ( argument )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:488:16: (e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? )
            // Resources/LLVM/llvmGrammar.g:488:17: e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )?
            {
            pushFollow(FOLLOW_data_type_in_argument_list1674);
            e1=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());
            pushFollow(FOLLOW_result_in_argument_list1678);
            e2=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
            // Resources/LLVM/llvmGrammar.g:488:42: (e3= ARGUMENT_ATTRIBUTE )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==ARGUMENT_ATTRIBUTE) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e3= ARGUMENT_ATTRIBUTE
                    {
                    e3=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_argument_list1682); if (state.failed) return retval;
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
            // Resources/LLVM/llvmGrammar.g:491:9: ( argument )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==COMMA) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: argument
            	    {
            	    pushFollow(FOLLOW_argument_in_argument_list1695);
            	    argument58=argument();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument58.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 15, argument_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "argument_list"

    public static class argument_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "argument"
    // Resources/LLVM/llvmGrammar.g:493:1: argument : ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) ;
    public final llvmGrammarParser.argument_return argument() throws RecognitionException {
        llvmGrammarParser.argument_return retval = new llvmGrammarParser.argument_return();
        retval.start = input.LT(1);
        int argument_StartIndex = input.index();
        Object root_0 = null;

        Token e3=null;
        Token COMMA59=null;
        llvmGrammarParser.data_type_return e1 = null;

        llvmGrammarParser.result_return e2 = null;


        Object e3_tree=null;
        Object COMMA59_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:493:10: ( ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? ) )
            // Resources/LLVM/llvmGrammar.g:493:11: ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:493:11: ( COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )? )
            // Resources/LLVM/llvmGrammar.g:493:12: COMMA e1= data_type e2= result (e3= ARGUMENT_ATTRIBUTE )?
            {
            COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_argument1712); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA59_tree = (Object)adaptor.create(COMMA59);
            adaptor.addChild(root_0, COMMA59_tree);
            }
            pushFollow(FOLLOW_data_type_in_argument1716);
            e1=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());
            pushFollow(FOLLOW_result_in_argument1720);
            e2=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
            // Resources/LLVM/llvmGrammar.g:493:43: (e3= ARGUMENT_ATTRIBUTE )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ARGUMENT_ATTRIBUTE) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e3= ARGUMENT_ATTRIBUTE
                    {
                    e3=(Token)match(input,ARGUMENT_ATTRIBUTE,FOLLOW_ARGUMENT_ATTRIBUTE_in_argument1724); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 16, argument_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "argument"

    public static class basic_blocks_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "basic_blocks"
    // Resources/LLVM/llvmGrammar.g:498:1: basic_blocks[String linkage_type,String return_attr,String data_type,String name, String function_attr, int line, int pos] : ( instruction_set )* ;
    public final llvmGrammarParser.basic_blocks_return basic_blocks(String linkage_type, String return_attr, String data_type, String name, String function_attr, int line, int pos) throws RecognitionException {
        llvmGrammarParser.basic_blocks_return retval = new llvmGrammarParser.basic_blocks_return();
        retval.start = input.LT(1);
        int basic_blocks_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.instruction_set_return instruction_set60 = null;




        			setFunction(linkage_type,return_attr,data_type,name, function_attr,true, line, pos);	
        		
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:501:4: ( ( instruction_set )* )
            // Resources/LLVM/llvmGrammar.g:501:6: ( instruction_set )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:501:6: ( instruction_set )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=UNREACHABLE && LA21_0<=FALSE)||LA21_0==ID||(LA21_0>=GLOBAL_PREFIX && LA21_0<=LOCAL_PREFIX)||LA21_0==NUMBER||LA21_0==FLOATING_LITERAL||LA21_0==69||(LA21_0>=73 && LA21_0<=75)||LA21_0==77||(LA21_0>=80 && LA21_0<=81)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: instruction_set
            	    {
            	    pushFollow(FOLLOW_instruction_set_in_basic_blocks1757);
            	    instruction_set60=instruction_set();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, instruction_set60.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 17, basic_blocks_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "basic_blocks"

    public static class instruction_set_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "instruction_set"
    // Resources/LLVM/llvmGrammar.g:503:1: instruction_set : ( binary_instruction | memory_rel_instruction | terminator_instruction | other_instruction | cast_instruction );
    public final llvmGrammarParser.instruction_set_return instruction_set() throws RecognitionException {
        llvmGrammarParser.instruction_set_return retval = new llvmGrammarParser.instruction_set_return();
        retval.start = input.LT(1);
        int instruction_set_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.binary_instruction_return binary_instruction61 = null;

        llvmGrammarParser.memory_rel_instruction_return memory_rel_instruction62 = null;

        llvmGrammarParser.terminator_instruction_return terminator_instruction63 = null;

        llvmGrammarParser.other_instruction_return other_instruction64 = null;

        llvmGrammarParser.cast_instruction_return cast_instruction65 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:503:18: ( binary_instruction | memory_rel_instruction | terminator_instruction | other_instruction | cast_instruction )
            int alt22=5;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:503:20: binary_instruction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_binary_instruction_in_instruction_set1768);
                    binary_instruction61=binary_instruction();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, binary_instruction61.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:504:8: memory_rel_instruction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_memory_rel_instruction_in_instruction_set1778);
                    memory_rel_instruction62=memory_rel_instruction();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, memory_rel_instruction62.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:505:8: terminator_instruction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_terminator_instruction_in_instruction_set1788);
                    terminator_instruction63=terminator_instruction();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, terminator_instruction63.getTree());

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:506:8: other_instruction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_other_instruction_in_instruction_set1798);
                    other_instruction64=other_instruction();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, other_instruction64.getTree());

                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:507:8: cast_instruction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_cast_instruction_in_instruction_set1808);
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
            if ( state.backtracking>0 ) { memoize(input, 18, instruction_set_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "instruction_set"

    public static class binary_instruction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "binary_instruction"
    // Resources/LLVM/llvmGrammar.g:509:1: binary_instruction : num= result EQUAL oprstr= BIN_OPR_STR (flag= ( 'nsw' | 'nuw' | 'exact' ) )? typ= data_type o1= result COMMA o2= result ;
    public final llvmGrammarParser.binary_instruction_return binary_instruction() throws RecognitionException {
        llvmGrammarParser.binary_instruction_return retval = new llvmGrammarParser.binary_instruction_return();
        retval.start = input.LT(1);
        int binary_instruction_StartIndex = input.index();
        Object root_0 = null;

        Token oprstr=null;
        Token flag=null;
        Token EQUAL66=null;
        Token COMMA67=null;
        llvmGrammarParser.result_return num = null;

        llvmGrammarParser.data_type_return typ = null;

        llvmGrammarParser.result_return o1 = null;

        llvmGrammarParser.result_return o2 = null;


        Object oprstr_tree=null;
        Object flag_tree=null;
        Object EQUAL66_tree=null;
        Object COMMA67_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:509:20: (num= result EQUAL oprstr= BIN_OPR_STR (flag= ( 'nsw' | 'nuw' | 'exact' ) )? typ= data_type o1= result COMMA o2= result )
            // Resources/LLVM/llvmGrammar.g:509:22: num= result EQUAL oprstr= BIN_OPR_STR (flag= ( 'nsw' | 'nuw' | 'exact' ) )? typ= data_type o1= result COMMA o2= result
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_binary_instruction1819);
            num=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, num.getTree());
            EQUAL66=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_binary_instruction1821); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EQUAL66_tree = (Object)adaptor.create(EQUAL66);
            adaptor.addChild(root_0, EQUAL66_tree);
            }
            oprstr=(Token)match(input,BIN_OPR_STR,FOLLOW_BIN_OPR_STR_in_binary_instruction1825); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            oprstr_tree = (Object)adaptor.create(oprstr);
            adaptor.addChild(root_0, oprstr_tree);
            }
            // Resources/LLVM/llvmGrammar.g:509:62: (flag= ( 'nsw' | 'nuw' | 'exact' ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=64 && LA23_0<=66)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: flag= ( 'nsw' | 'nuw' | 'exact' )
                    {
                    flag=(Token)input.LT(1);
                    if ( (input.LA(1)>=64 && input.LA(1)<=66) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(flag));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_data_type_in_binary_instruction1840);
            typ=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typ.getTree());
            pushFollow(FOLLOW_result_in_binary_instruction1844);
            o1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, o1.getTree());
            COMMA67=(Token)match(input,COMMA,FOLLOW_COMMA_in_binary_instruction1846); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA67_tree = (Object)adaptor.create(COMMA67);
            adaptor.addChild(root_0, COMMA67_tree);
            }
            pushFollow(FOLLOW_result_in_binary_instruction1850);
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
            if ( state.backtracking>0 ) { memoize(input, 19, binary_instruction_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "binary_instruction"

    public static class memory_rel_instruction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "memory_rel_instruction"
    // Resources/LLVM/llvmGrammar.g:514:1: memory_rel_instruction : ( load | alloca | store | getelementptr );
    public final llvmGrammarParser.memory_rel_instruction_return memory_rel_instruction() throws RecognitionException {
        llvmGrammarParser.memory_rel_instruction_return retval = new llvmGrammarParser.memory_rel_instruction_return();
        retval.start = input.LT(1);
        int memory_rel_instruction_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.load_return load68 = null;

        llvmGrammarParser.alloca_return alloca69 = null;

        llvmGrammarParser.store_return store70 = null;

        llvmGrammarParser.getelementptr_return getelementptr71 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:514:24: ( load | alloca | store | getelementptr )
            int alt24=4;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:514:27: load
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_load_in_memory_rel_instruction1879);
                    load68=load();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, load68.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:514:34: alloca
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_alloca_in_memory_rel_instruction1883);
                    alloca69=alloca();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, alloca69.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:514:43: store
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_store_in_memory_rel_instruction1887);
                    store70=store();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, store70.getTree());

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:514:51: getelementptr
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_getelementptr_in_memory_rel_instruction1891);
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
            if ( state.backtracking>0 ) { memoize(input, 20, memory_rel_instruction_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "memory_rel_instruction"

    public static class alloca_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alloca"
    // Resources/LLVM/llvmGrammar.g:516:1: alloca : res= result e= EQUAL ' alloca ' d= data_type ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )? ;
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
        llvmGrammarParser.result_return res = null;

        llvmGrammarParser.data_type_return d = null;

        llvmGrammarParser.result_return arrayLength = null;

        llvmGrammarParser.data_type_return data_type74 = null;


        Object e_tree=null;
        Object alignV_tree=null;
        Object string_literal72_tree=null;
        Object COMMA73_tree=null;
        Object COMMA75_tree=null;
        Object ALIGN76_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:516:8: (res= result e= EQUAL ' alloca ' d= data_type ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )? )
            // Resources/LLVM/llvmGrammar.g:516:10: res= result e= EQUAL ' alloca ' d= data_type ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_alloca1906);
            res=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, res.getTree());
            e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_alloca1910); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            string_literal72=(Token)match(input,67,FOLLOW_67_in_alloca1912); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal72_tree = (Object)adaptor.create(string_literal72);
            adaptor.addChild(root_0, string_literal72_tree);
            }
            pushFollow(FOLLOW_data_type_in_alloca1916);
            d=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            // Resources/LLVM/llvmGrammar.g:516:53: ( COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==COMMA) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:516:54: COMMA ( data_type arrayLength= result COMMA )? ALIGN alignV= NUMBER
                    {
                    COMMA73=(Token)match(input,COMMA,FOLLOW_COMMA_in_alloca1920); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA73_tree = (Object)adaptor.create(COMMA73);
                    adaptor.addChild(root_0, COMMA73_tree);
                    }
                    // Resources/LLVM/llvmGrammar.g:516:60: ( data_type arrayLength= result COMMA )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==PRIMITIVE_DATA_TYPE||LA25_0==LOCAL_PREFIX||LA25_0==START_PARANTHESIS||LA25_0==START_ANGULAR||LA25_0==START_SQUARE_BR) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:516:61: data_type arrayLength= result COMMA
                            {
                            pushFollow(FOLLOW_data_type_in_alloca1923);
                            data_type74=data_type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type74.getTree());
                            pushFollow(FOLLOW_result_in_alloca1927);
                            arrayLength=result();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayLength.getTree());
                            COMMA75=(Token)match(input,COMMA,FOLLOW_COMMA_in_alloca1929); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA75_tree = (Object)adaptor.create(COMMA75);
                            adaptor.addChild(root_0, COMMA75_tree);
                            }

                            }
                            break;

                    }

                    ALIGN76=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_alloca1933); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALIGN76_tree = (Object)adaptor.create(ALIGN76);
                    adaptor.addChild(root_0, ALIGN76_tree);
                    }
                    alignV=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_alloca1937); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 21, alloca_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "alloca"

    public static class load_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "load"
    // Resources/LLVM/llvmGrammar.g:521:1: load : res= result e0= EQUAL 'load' data_type e= result ( COMMA ALIGN NUMBER )? ;
    public final llvmGrammarParser.load_return load() throws RecognitionException {
        llvmGrammarParser.load_return retval = new llvmGrammarParser.load_return();
        retval.start = input.LT(1);
        int load_StartIndex = input.index();
        Object root_0 = null;

        Token e0=null;
        Token string_literal77=null;
        Token COMMA79=null;
        Token ALIGN80=null;
        Token NUMBER81=null;
        llvmGrammarParser.result_return res = null;

        llvmGrammarParser.result_return e = null;

        llvmGrammarParser.data_type_return data_type78 = null;


        Object e0_tree=null;
        Object string_literal77_tree=null;
        Object COMMA79_tree=null;
        Object ALIGN80_tree=null;
        Object NUMBER81_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:521:6: (res= result e0= EQUAL 'load' data_type e= result ( COMMA ALIGN NUMBER )? )
            // Resources/LLVM/llvmGrammar.g:521:8: res= result e0= EQUAL 'load' data_type e= result ( COMMA ALIGN NUMBER )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_load1961);
            res=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, res.getTree());
            e0=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_load1965); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e0_tree = (Object)adaptor.create(e0);
            adaptor.addChild(root_0, e0_tree);
            }
            string_literal77=(Token)match(input,68,FOLLOW_68_in_load1967); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal77_tree = (Object)adaptor.create(string_literal77);
            adaptor.addChild(root_0, string_literal77_tree);
            }
            pushFollow(FOLLOW_data_type_in_load1969);
            data_type78=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type78.getTree());
            pushFollow(FOLLOW_result_in_load1973);
            e=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            // Resources/LLVM/llvmGrammar.g:521:54: ( COMMA ALIGN NUMBER )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==COMMA) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:521:55: COMMA ALIGN NUMBER
                    {
                    COMMA79=(Token)match(input,COMMA,FOLLOW_COMMA_in_load1976); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA79_tree = (Object)adaptor.create(COMMA79);
                    adaptor.addChild(root_0, COMMA79_tree);
                    }
                    ALIGN80=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_load1978); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALIGN80_tree = (Object)adaptor.create(ALIGN80);
                    adaptor.addChild(root_0, ALIGN80_tree);
                    }
                    NUMBER81=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_load1980); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER81_tree = (Object)adaptor.create(NUMBER81);
                    adaptor.addChild(root_0, NUMBER81_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              							
              	 	setLoadInstr((res!=null?input.toString(res.start,res.stop):null),(e!=null?input.toString(e.start,e.stop):null),(data_type78!=null?input.toString(data_type78.start,data_type78.stop):null), (e0!=null?e0.getLine():0), 0);
              	 
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
            if ( state.backtracking>0 ) { memoize(input, 22, load_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "load"

    public static class store_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "store"
    // Resources/LLVM/llvmGrammar.g:526:1: store : a= 'store' (atomic= ATOMIC_ORDERING )? (isVolatile= 'volatile' )? a3= data_type a1= result COMMA data_type a2= result ( COMMA ALIGN NUMBER )* ;
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
        llvmGrammarParser.data_type_return a3 = null;

        llvmGrammarParser.result_return a1 = null;

        llvmGrammarParser.result_return a2 = null;

        llvmGrammarParser.data_type_return data_type83 = null;


        Object a_tree=null;
        Object atomic_tree=null;
        Object isVolatile_tree=null;
        Object COMMA82_tree=null;
        Object COMMA84_tree=null;
        Object ALIGN85_tree=null;
        Object NUMBER86_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:526:7: (a= 'store' (atomic= ATOMIC_ORDERING )? (isVolatile= 'volatile' )? a3= data_type a1= result COMMA data_type a2= result ( COMMA ALIGN NUMBER )* )
            // Resources/LLVM/llvmGrammar.g:526:9: a= 'store' (atomic= ATOMIC_ORDERING )? (isVolatile= 'volatile' )? a3= data_type a1= result COMMA data_type a2= result ( COMMA ALIGN NUMBER )*
            {
            root_0 = (Object)adaptor.nil();

            a=(Token)match(input,69,FOLLOW_69_in_store2000); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            a_tree = (Object)adaptor.create(a);
            adaptor.addChild(root_0, a_tree);
            }
            // Resources/LLVM/llvmGrammar.g:526:25: (atomic= ATOMIC_ORDERING )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ATOMIC_ORDERING) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: atomic= ATOMIC_ORDERING
                    {
                    atomic=(Token)match(input,ATOMIC_ORDERING,FOLLOW_ATOMIC_ORDERING_in_store2004); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    atomic_tree = (Object)adaptor.create(atomic);
                    adaptor.addChild(root_0, atomic_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:526:53: (isVolatile= 'volatile' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==70) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: isVolatile= 'volatile'
                    {
                    isVolatile=(Token)match(input,70,FOLLOW_70_in_store2009); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    isVolatile_tree = (Object)adaptor.create(isVolatile);
                    adaptor.addChild(root_0, isVolatile_tree);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_data_type_in_store2014);
            a3=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, a3.getTree());
            pushFollow(FOLLOW_result_in_store2018);
            a1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, a1.getTree());
            COMMA82=(Token)match(input,COMMA,FOLLOW_COMMA_in_store2020); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA82_tree = (Object)adaptor.create(COMMA82);
            adaptor.addChild(root_0, COMMA82_tree);
            }
            pushFollow(FOLLOW_data_type_in_store2024);
            data_type83=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type83.getTree());
            pushFollow(FOLLOW_result_in_store2029);
            a2=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, a2.getTree());
            // Resources/LLVM/llvmGrammar.g:527:25: ( COMMA ALIGN NUMBER )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==COMMA) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:527:26: COMMA ALIGN NUMBER
            	    {
            	    COMMA84=(Token)match(input,COMMA,FOLLOW_COMMA_in_store2033); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA84_tree = (Object)adaptor.create(COMMA84);
            	    adaptor.addChild(root_0, COMMA84_tree);
            	    }
            	    ALIGN85=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_store2035); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ALIGN85_tree = (Object)adaptor.create(ALIGN85);
            	    adaptor.addChild(root_0, ALIGN85_tree);
            	    }
            	    NUMBER86=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_store2037); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NUMBER86_tree = (Object)adaptor.create(NUMBER86);
            	    adaptor.addChild(root_0, NUMBER86_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

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
            if ( state.backtracking>0 ) { memoize(input, 23, store_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "store"

    public static class getelementptr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "getelementptr"
    // Resources/LLVM/llvmGrammar.g:532:1: getelementptr : id= result e= EQUAL getelementptr_rhs[$id.text, $e.line] ;
    public final llvmGrammarParser.getelementptr_return getelementptr() throws RecognitionException {
        llvmGrammarParser.getelementptr_return retval = new llvmGrammarParser.getelementptr_return();
        retval.start = input.LT(1);
        int getelementptr_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        llvmGrammarParser.result_return id = null;

        llvmGrammarParser.getelementptr_rhs_return getelementptr_rhs87 = null;


        Object e_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:532:15: (id= result e= EQUAL getelementptr_rhs[$id.text, $e.line] )
            // Resources/LLVM/llvmGrammar.g:532:17: id= result e= EQUAL getelementptr_rhs[$id.text, $e.line]
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_getelementptr2057);
            id=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, id.getTree());
            e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_getelementptr2061); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            pushFollow(FOLLOW_getelementptr_rhs_in_getelementptr2063);
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
            if ( state.backtracking>0 ) { memoize(input, 24, getelementptr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "getelementptr"

    public static class getelementptr_rhs_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "getelementptr_rhs"
    // Resources/LLVM/llvmGrammar.g:534:1: getelementptr_rhs[String name, int line] : 'getelementptr' (inb= 'inbounds' )? ( START_PARANTHESIS )? ptr= ptr_type element= element_name COMMA off= offset ( END_PARANTHESIS )? ;
    public final llvmGrammarParser.getelementptr_rhs_return getelementptr_rhs(String name, int line) throws RecognitionException {
        llvmGrammarParser.getelementptr_rhs_return retval = new llvmGrammarParser.getelementptr_rhs_return();
        retval.start = input.LT(1);
        int getelementptr_rhs_StartIndex = input.index();
        Object root_0 = null;

        Token inb=null;
        Token string_literal88=null;
        Token START_PARANTHESIS89=null;
        Token COMMA90=null;
        Token END_PARANTHESIS91=null;
        llvmGrammarParser.ptr_type_return ptr = null;

        llvmGrammarParser.element_name_return element = null;

        llvmGrammarParser.offset_return off = null;


        Object inb_tree=null;
        Object string_literal88_tree=null;
        Object START_PARANTHESIS89_tree=null;
        Object COMMA90_tree=null;
        Object END_PARANTHESIS91_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:534:42: ( 'getelementptr' (inb= 'inbounds' )? ( START_PARANTHESIS )? ptr= ptr_type element= element_name COMMA off= offset ( END_PARANTHESIS )? )
            // Resources/LLVM/llvmGrammar.g:534:44: 'getelementptr' (inb= 'inbounds' )? ( START_PARANTHESIS )? ptr= ptr_type element= element_name COMMA off= offset ( END_PARANTHESIS )?
            {
            root_0 = (Object)adaptor.nil();

            string_literal88=(Token)match(input,71,FOLLOW_71_in_getelementptr_rhs2076); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal88_tree = (Object)adaptor.create(string_literal88);
            adaptor.addChild(root_0, string_literal88_tree);
            }
            // Resources/LLVM/llvmGrammar.g:534:62: (inb= 'inbounds' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==72) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: inb= 'inbounds'
                    {
                    inb=(Token)match(input,72,FOLLOW_72_in_getelementptr_rhs2079); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    inb_tree = (Object)adaptor.create(inb);
                    adaptor.addChild(root_0, inb_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:534:75: ( START_PARANTHESIS )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==START_PARANTHESIS) ) {
                int LA32_1 = input.LA(2);

                if ( (synpred43_llvmGrammar()) ) {
                    alt32=1;
                }
            }
            switch (alt32) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: START_PARANTHESIS
                    {
                    START_PARANTHESIS89=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_getelementptr_rhs2082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    START_PARANTHESIS89_tree = (Object)adaptor.create(START_PARANTHESIS89);
                    adaptor.addChild(root_0, START_PARANTHESIS89_tree);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_ptr_type_in_getelementptr_rhs2089);
            ptr=ptr_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr.getTree());
            pushFollow(FOLLOW_element_name_in_getelementptr_rhs2105);
            element=element_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, element.getTree());
            COMMA90=(Token)match(input,COMMA,FOLLOW_COMMA_in_getelementptr_rhs2107); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA90_tree = (Object)adaptor.create(COMMA90);
            adaptor.addChild(root_0, COMMA90_tree);
            }
            pushFollow(FOLLOW_offset_in_getelementptr_rhs2112);
            off=offset();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, off.getTree());
            // Resources/LLVM/llvmGrammar.g:535:51: ( END_PARANTHESIS )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==END_PARANTHESIS) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: END_PARANTHESIS
                    {
                    END_PARANTHESIS91=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_getelementptr_rhs2114); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END_PARANTHESIS91_tree = (Object)adaptor.create(END_PARANTHESIS91);
                    adaptor.addChild(root_0, END_PARANTHESIS91_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	    		
              											setGetElementPtr(name,(element!=null?input.toString(element.start,element.stop):null),(ptr!=null?input.toString(ptr.start,ptr.stop):null), (off!=null?input.toString(off.start,off.stop):null),(inb!=null?inb.getText():null), line, 0);
              				    					 
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
            if ( state.backtracking>0 ) { memoize(input, 25, getelementptr_rhs_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "getelementptr_rhs"

    public static class element_name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element_name"
    // Resources/LLVM/llvmGrammar.g:540:1: element_name : ( ( LOCAL_PREFIX ( ID | NUMBER )+ ) | ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type ) | 'undef' );
    public final llvmGrammarParser.element_name_return element_name() throws RecognitionException {
        llvmGrammarParser.element_name_return retval = new llvmGrammarParser.element_name_return();
        retval.start = input.LT(1);
        int element_name_StartIndex = input.index();
        Object root_0 = null;

        Token LOCAL_PREFIX92=null;
        Token set93=null;
        Token GLOBAL_PREFIX94=null;
        Token char_literal95=null;
        Token set96=null;
        Token string_literal98=null;
        llvmGrammarParser.data_type_return data_type97 = null;


        Object LOCAL_PREFIX92_tree=null;
        Object set93_tree=null;
        Object GLOBAL_PREFIX94_tree=null;
        Object char_literal95_tree=null;
        Object set96_tree=null;
        Object string_literal98_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:540:13: ( ( LOCAL_PREFIX ( ID | NUMBER )+ ) | ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type ) | 'undef' )
            int alt38=3;
            switch ( input.LA(1) ) {
            case LOCAL_PREFIX:
                {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==ID) ) {
                    int LA38_4 = input.LA(3);

                    if ( (synpred47_llvmGrammar()) ) {
                        alt38=1;
                    }
                    else if ( (synpred52_llvmGrammar()) ) {
                        alt38=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA38_1==NUMBER) ) {
                    alt38=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;
                }
                }
                break;
            case PRIMITIVE_DATA_TYPE:
            case GLOBAL_PREFIX:
            case START_PARANTHESIS:
            case START_ANGULAR:
            case START_SQUARE_BR:
                {
                alt38=2;
                }
                break;
            case 73:
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
                    // Resources/LLVM/llvmGrammar.g:540:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:540:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
                    // Resources/LLVM/llvmGrammar.g:540:17: LOCAL_PREFIX ( ID | NUMBER )+
                    {
                    LOCAL_PREFIX92=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_element_name2149); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX92_tree = (Object)adaptor.create(LOCAL_PREFIX92);
                    adaptor.addChild(root_0, LOCAL_PREFIX92_tree);
                    }
                    // Resources/LLVM/llvmGrammar.g:540:30: ( ID | NUMBER )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==ID||LA34_0==NUMBER) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:
                    	    {
                    	    set93=(Token)input.LT(1);
                    	    if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                    	        input.consume();
                    	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set93));
                    	        state.errorRecovery=false;state.failed=false;
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
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:540:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:540:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==GLOBAL_PREFIX) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==PRIMITIVE_DATA_TYPE||LA37_0==LOCAL_PREFIX||LA37_0==START_PARANTHESIS||LA37_0==START_ANGULAR||LA37_0==START_SQUARE_BR) ) {
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
                            // Resources/LLVM/llvmGrammar.g:540:48: GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+
                            {
                            GLOBAL_PREFIX94=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_element_name2163); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            GLOBAL_PREFIX94_tree = (Object)adaptor.create(GLOBAL_PREFIX94);
                            adaptor.addChild(root_0, GLOBAL_PREFIX94_tree);
                            }
                            // Resources/LLVM/llvmGrammar.g:540:62: ( '.' )?
                            int alt35=2;
                            int LA35_0 = input.LA(1);

                            if ( (LA35_0==DOT) ) {
                                alt35=1;
                            }
                            switch (alt35) {
                                case 1 :
                                    // Resources/LLVM/llvmGrammar.g:0:0: '.'
                                    {
                                    char_literal95=(Token)match(input,DOT,FOLLOW_DOT_in_element_name2165); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    char_literal95_tree = (Object)adaptor.create(char_literal95);
                                    adaptor.addChild(root_0, char_literal95_tree);
                                    }

                                    }
                                    break;

                            }

                            // Resources/LLVM/llvmGrammar.g:540:66: ( ID | NUMBER )+
                            int cnt36=0;
                            loop36:
                            do {
                                int alt36=2;
                                int LA36_0 = input.LA(1);

                                if ( (LA36_0==ID||LA36_0==NUMBER) ) {
                                    alt36=1;
                                }


                                switch (alt36) {
                            	case 1 :
                            	    // Resources/LLVM/llvmGrammar.g:
                            	    {
                            	    set96=(Token)input.LT(1);
                            	    if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                            	        input.consume();
                            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set96));
                            	        state.errorRecovery=false;state.failed=false;
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
                                        EarlyExitException eee =
                                            new EarlyExitException(36, input);
                                        throw eee;
                                }
                                cnt36++;
                            } while (true);


                            }
                            break;
                        case 2 :
                            // Resources/LLVM/llvmGrammar.g:540:82: data_type
                            {
                            pushFollow(FOLLOW_data_type_in_element_name2177);
                            data_type97=data_type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type97.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:540:94: 'undef'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal98=(Token)match(input,73,FOLLOW_73_in_element_name2181); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal98_tree = (Object)adaptor.create(string_literal98);
                    adaptor.addChild(root_0, string_literal98_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 26, element_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "element_name"

    public static class offset_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "offset"
    // Resources/LLVM/llvmGrammar.g:542:1: offset : ( GLOBAL_PREFIX ID COMMA )? ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) ) ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )* ;
    public final llvmGrammarParser.offset_return offset() throws RecognitionException {
        llvmGrammarParser.offset_return retval = new llvmGrammarParser.offset_return();
        retval.start = input.LT(1);
        int offset_StartIndex = input.index();
        Object root_0 = null;

        Token GLOBAL_PREFIX99=null;
        Token ID100=null;
        Token COMMA101=null;
        Token LOCAL_PREFIX103=null;
        Token set104=null;
        Token COMMA105=null;
        Token LOCAL_PREFIX107=null;
        Token set108=null;
        llvmGrammarParser.data_type_return data_type102 = null;

        llvmGrammarParser.data_type_return data_type106 = null;


        Object GLOBAL_PREFIX99_tree=null;
        Object ID100_tree=null;
        Object COMMA101_tree=null;
        Object LOCAL_PREFIX103_tree=null;
        Object set104_tree=null;
        Object COMMA105_tree=null;
        Object LOCAL_PREFIX107_tree=null;
        Object set108_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:542:8: ( ( GLOBAL_PREFIX ID COMMA )? ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) ) ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )* )
            // Resources/LLVM/llvmGrammar.g:542:10: ( GLOBAL_PREFIX ID COMMA )? ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) ) ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:542:10: ( GLOBAL_PREFIX ID COMMA )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==GLOBAL_PREFIX) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:542:11: GLOBAL_PREFIX ID COMMA
                    {
                    GLOBAL_PREFIX99=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_offset2193); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GLOBAL_PREFIX99_tree = (Object)adaptor.create(GLOBAL_PREFIX99);
                    adaptor.addChild(root_0, GLOBAL_PREFIX99_tree);
                    }
                    ID100=(Token)match(input,ID,FOLLOW_ID_in_offset2195); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID100_tree = (Object)adaptor.create(ID100);
                    adaptor.addChild(root_0, ID100_tree);
                    }
                    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_offset2197); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA101_tree = (Object)adaptor.create(COMMA101);
                    adaptor.addChild(root_0, COMMA101_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:542:36: ( data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )
            // Resources/LLVM/llvmGrammar.g:542:38: data_type ( LOCAL_PREFIX )? ( NUMBER | ID )
            {
            pushFollow(FOLLOW_data_type_in_offset2203);
            data_type102=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type102.getTree());
            // Resources/LLVM/llvmGrammar.g:542:48: ( LOCAL_PREFIX )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==LOCAL_PREFIX) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: LOCAL_PREFIX
                    {
                    LOCAL_PREFIX103=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_offset2205); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX103_tree = (Object)adaptor.create(LOCAL_PREFIX103);
                    adaptor.addChild(root_0, LOCAL_PREFIX103_tree);
                    }

                    }
                    break;

            }

            set104=(Token)input.LT(1);
            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set104));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            // Resources/LLVM/llvmGrammar.g:542:76: ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )*
            loop42:
            do {
                int alt42=2;
                alt42 = dfa42.predict(input);
                switch (alt42) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:542:77: COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID )
            	    {
            	    COMMA105=(Token)match(input,COMMA,FOLLOW_COMMA_in_offset2217); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA105_tree = (Object)adaptor.create(COMMA105);
            	    adaptor.addChild(root_0, COMMA105_tree);
            	    }
            	    pushFollow(FOLLOW_data_type_in_offset2219);
            	    data_type106=data_type();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type106.getTree());
            	    // Resources/LLVM/llvmGrammar.g:542:93: ( LOCAL_PREFIX )?
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==LOCAL_PREFIX) ) {
            	        alt41=1;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // Resources/LLVM/llvmGrammar.g:0:0: LOCAL_PREFIX
            	            {
            	            LOCAL_PREFIX107=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_offset2221); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            LOCAL_PREFIX107_tree = (Object)adaptor.create(LOCAL_PREFIX107);
            	            adaptor.addChild(root_0, LOCAL_PREFIX107_tree);
            	            }

            	            }
            	            break;

            	    }

            	    set108=(Token)input.LT(1);
            	    if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set108));
            	        state.errorRecovery=false;state.failed=false;
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
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 27, offset_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "offset"

    public static class terminator_instruction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "terminator_instruction"
    // Resources/LLVM/llvmGrammar.g:544:1: terminator_instruction : ( ret | br | switchInstr | unreachable );
    public final llvmGrammarParser.terminator_instruction_return terminator_instruction() throws RecognitionException {
        llvmGrammarParser.terminator_instruction_return retval = new llvmGrammarParser.terminator_instruction_return();
        retval.start = input.LT(1);
        int terminator_instruction_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.ret_return ret109 = null;

        llvmGrammarParser.br_return br110 = null;

        llvmGrammarParser.switchInstr_return switchInstr111 = null;

        llvmGrammarParser.unreachable_return unreachable112 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:544:24: ( ret | br | switchInstr | unreachable )
            int alt43=4;
            switch ( input.LA(1) ) {
            case 74:
                {
                alt43=1;
                }
                break;
            case 75:
                {
                alt43=2;
                }
                break;
            case 77:
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
                    // Resources/LLVM/llvmGrammar.g:544:26: ret
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ret_in_terminator_instruction2241);
                    ret109=ret();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ret109.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:544:32: br
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_br_in_terminator_instruction2245);
                    br110=br();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, br110.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:544:37: switchInstr
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_switchInstr_in_terminator_instruction2249);
                    switchInstr111=switchInstr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchInstr111.getTree());

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:544:50: unreachable
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_unreachable_in_terminator_instruction2252);
                    unreachable112=unreachable();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unreachable112.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 28, terminator_instruction_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "terminator_instruction"

    public static class ret_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ret"
    // Resources/LLVM/llvmGrammar.g:546:1: ret : r= 'ret' d= data_type (e1= result )? ;
    public final llvmGrammarParser.ret_return ret() throws RecognitionException {
        llvmGrammarParser.ret_return retval = new llvmGrammarParser.ret_return();
        retval.start = input.LT(1);
        int ret_StartIndex = input.index();
        Object root_0 = null;

        Token r=null;
        llvmGrammarParser.data_type_return d = null;

        llvmGrammarParser.result_return e1 = null;


        Object r_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:546:5: (r= 'ret' d= data_type (e1= result )? )
            // Resources/LLVM/llvmGrammar.g:546:8: r= 'ret' d= data_type (e1= result )?
            {
            root_0 = (Object)adaptor.nil();

            r=(Token)match(input,74,FOLLOW_74_in_ret2266); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            r_tree = (Object)adaptor.create(r);
            adaptor.addChild(root_0, r_tree);
            }
            pushFollow(FOLLOW_data_type_in_ret2270);
            d=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            // Resources/LLVM/llvmGrammar.g:546:28: (e1= result )?
            int alt44=2;
            switch ( input.LA(1) ) {
                case GLOBAL_PREFIX:
                case LOCAL_PREFIX:
                    {
                    int LA44_1 = input.LA(2);

                    if ( (LA44_1==ID||LA44_1==NUMBER||LA44_1==FLOATING_LITERAL) ) {
                        int LA44_2 = input.LA(3);

                        if ( (LA44_2==EOF||(LA44_2>=UNREACHABLE && LA44_2<=FALSE)||LA44_2==ID||(LA44_2>=GLOBAL_PREFIX && LA44_2<=LOCAL_PREFIX)||LA44_2==END_CURLY||LA44_2==NUMBER||LA44_2==FLOATING_LITERAL||LA44_2==69||(LA44_2>=73 && LA44_2<=75)||LA44_2==77||(LA44_2>=80 && LA44_2<=81)) ) {
                            alt44=1;
                        }
                    }
                    }
                    break;
                case ID:
                case NUMBER:
                case FLOATING_LITERAL:
                    {
                    int LA44_2 = input.LA(2);

                    if ( (LA44_2==EOF||(LA44_2>=UNREACHABLE && LA44_2<=FALSE)||LA44_2==ID||(LA44_2>=GLOBAL_PREFIX && LA44_2<=LOCAL_PREFIX)||LA44_2==END_CURLY||LA44_2==NUMBER||LA44_2==FLOATING_LITERAL||LA44_2==69||(LA44_2>=73 && LA44_2<=75)||LA44_2==77||(LA44_2>=80 && LA44_2<=81)) ) {
                        alt44=1;
                    }
                    }
                    break;
                case TRUE:
                case FALSE:
                    {
                    int LA44_3 = input.LA(2);

                    if ( (LA44_3==EOF||(LA44_3>=UNREACHABLE && LA44_3<=FALSE)||LA44_3==ID||(LA44_3>=GLOBAL_PREFIX && LA44_3<=LOCAL_PREFIX)||LA44_3==END_CURLY||LA44_3==NUMBER||LA44_3==FLOATING_LITERAL||LA44_3==69||(LA44_3>=73 && LA44_3<=75)||LA44_3==77||(LA44_3>=80 && LA44_3<=81)) ) {
                        alt44=1;
                    }
                    }
                    break;
                case 73:
                    {
                    int LA44_4 = input.LA(2);

                    if ( (LA44_4==EOF||(LA44_4>=UNREACHABLE && LA44_4<=FALSE)||LA44_4==ID||(LA44_4>=GLOBAL_PREFIX && LA44_4<=LOCAL_PREFIX)||LA44_4==END_CURLY||LA44_4==NUMBER||LA44_4==FLOATING_LITERAL||LA44_4==69||(LA44_4>=73 && LA44_4<=75)||LA44_4==77||(LA44_4>=80 && LA44_4<=81)) ) {
                        alt44=1;
                    }
                    }
                    break;
            }

            switch (alt44) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:546:29: e1= result
                    {
                    pushFollow(FOLLOW_result_in_ret2275);
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
            if ( state.backtracking>0 ) { memoize(input, 29, ret_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ret"

    public static class br_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "br"
    // Resources/LLVM/llvmGrammar.g:551:1: br : ( (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER ) | (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) ) );
    public final llvmGrammarParser.br_return br() throws RecognitionException {
        llvmGrammarParser.br_return retval = new llvmGrammarParser.br_return();
        retval.start = input.LT(1);
        int br_StartIndex = input.index();
        Object root_0 = null;

        Token b=null;
        Token ifT=null;
        Token ifF=null;
        Token COMMA114=null;
        Token string_literal115=null;
        Token LOCAL_PREFIX116=null;
        Token COMMA117=null;
        Token string_literal118=null;
        Token LOCAL_PREFIX119=null;
        Token string_literal120=null;
        Token LOCAL_PREFIX121=null;
        llvmGrammarParser.result_return cond = null;

        llvmGrammarParser.data_type_return data_type113 = null;


        Object b_tree=null;
        Object ifT_tree=null;
        Object ifF_tree=null;
        Object COMMA114_tree=null;
        Object string_literal115_tree=null;
        Object LOCAL_PREFIX116_tree=null;
        Object COMMA117_tree=null;
        Object string_literal118_tree=null;
        Object LOCAL_PREFIX119_tree=null;
        Object string_literal120_tree=null;
        Object LOCAL_PREFIX121_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:551:4: ( (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER ) | (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==75) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==76) ) {
                    alt45=2;
                }
                else if ( (LA45_1==PRIMITIVE_DATA_TYPE||LA45_1==LOCAL_PREFIX||LA45_1==START_PARANTHESIS||LA45_1==START_ANGULAR||LA45_1==START_SQUARE_BR) ) {
                    alt45=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:551:6: (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:551:6: (b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER )
                    // Resources/LLVM/llvmGrammar.g:551:8: b= 'br' data_type (cond= result ) COMMA 'label' LOCAL_PREFIX ifT= NUMBER COMMA 'label' LOCAL_PREFIX ifF= NUMBER
                    {
                    b=(Token)match(input,75,FOLLOW_75_in_br2295); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    b_tree = (Object)adaptor.create(b);
                    adaptor.addChild(root_0, b_tree);
                    }
                    pushFollow(FOLLOW_data_type_in_br2297);
                    data_type113=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type113.getTree());
                    // Resources/LLVM/llvmGrammar.g:551:25: (cond= result )
                    // Resources/LLVM/llvmGrammar.g:551:27: cond= result
                    {
                    pushFollow(FOLLOW_result_in_br2305);
                    cond=result();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cond.getTree());

                    }

                    COMMA114=(Token)match(input,COMMA,FOLLOW_COMMA_in_br2308); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA114_tree = (Object)adaptor.create(COMMA114);
                    adaptor.addChild(root_0, COMMA114_tree);
                    }
                    string_literal115=(Token)match(input,76,FOLLOW_76_in_br2310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal115_tree = (Object)adaptor.create(string_literal115);
                    adaptor.addChild(root_0, string_literal115_tree);
                    }
                    LOCAL_PREFIX116=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_br2312); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX116_tree = (Object)adaptor.create(LOCAL_PREFIX116);
                    adaptor.addChild(root_0, LOCAL_PREFIX116_tree);
                    }
                    ifT=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_br2316); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ifT_tree = (Object)adaptor.create(ifT);
                    adaptor.addChild(root_0, ifT_tree);
                    }
                    COMMA117=(Token)match(input,COMMA,FOLLOW_COMMA_in_br2318); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA117_tree = (Object)adaptor.create(COMMA117);
                    adaptor.addChild(root_0, COMMA117_tree);
                    }
                    string_literal118=(Token)match(input,76,FOLLOW_76_in_br2320); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal118_tree = (Object)adaptor.create(string_literal118);
                    adaptor.addChild(root_0, string_literal118_tree);
                    }
                    LOCAL_PREFIX119=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_br2322); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX119_tree = (Object)adaptor.create(LOCAL_PREFIX119);
                    adaptor.addChild(root_0, LOCAL_PREFIX119_tree);
                    }
                    ifF=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_br2326); if (state.failed) return retval;
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
                    // Resources/LLVM/llvmGrammar.g:555:6: (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:555:6: (b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID ) )
                    // Resources/LLVM/llvmGrammar.g:555:8: b= 'br' 'label' LOCAL_PREFIX ifT= ( NUMBER | ID )
                    {
                    b=(Token)match(input,75,FOLLOW_75_in_br2345); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    b_tree = (Object)adaptor.create(b);
                    adaptor.addChild(root_0, b_tree);
                    }
                    string_literal120=(Token)match(input,76,FOLLOW_76_in_br2347); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal120_tree = (Object)adaptor.create(string_literal120);
                    adaptor.addChild(root_0, string_literal120_tree);
                    }
                    LOCAL_PREFIX121=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_br2350); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX121_tree = (Object)adaptor.create(LOCAL_PREFIX121);
                    adaptor.addChild(root_0, LOCAL_PREFIX121_tree);
                    }
                    ifT=(Token)input.LT(1);
                    if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(ifT));
                        state.errorRecovery=false;state.failed=false;
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
            if ( state.backtracking>0 ) { memoize(input, 30, br_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "br"

    public static class switchInstr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "switchInstr"
    // Resources/LLVM/llvmGrammar.g:560:1: switchInstr : s= 'switch' data_type e1= result COMMA 'label' e2= result START_SQUARE_BR e= cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR ;
    public final llvmGrammarParser.switchInstr_return switchInstr() throws RecognitionException {
        llvmGrammarParser.switchInstr_return retval = new llvmGrammarParser.switchInstr_return();
        retval.start = input.LT(1);
        int switchInstr_StartIndex = input.index();
        Object root_0 = null;

        Token s=null;
        Token COMMA123=null;
        Token string_literal124=null;
        Token START_SQUARE_BR125=null;
        Token END_SQUARE_BR126=null;
        llvmGrammarParser.result_return e1 = null;

        llvmGrammarParser.result_return e2 = null;

        llvmGrammarParser.cases_list_return e = null;

        llvmGrammarParser.data_type_return data_type122 = null;


        Object s_tree=null;
        Object COMMA123_tree=null;
        Object string_literal124_tree=null;
        Object START_SQUARE_BR125_tree=null;
        Object END_SQUARE_BR126_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:560:13: (s= 'switch' data_type e1= result COMMA 'label' e2= result START_SQUARE_BR e= cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR )
            // Resources/LLVM/llvmGrammar.g:560:15: s= 'switch' data_type e1= result COMMA 'label' e2= result START_SQUARE_BR e= cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR
            {
            root_0 = (Object)adaptor.nil();

            s=(Token)match(input,77,FOLLOW_77_in_switchInstr2382); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            s_tree = (Object)adaptor.create(s);
            adaptor.addChild(root_0, s_tree);
            }
            pushFollow(FOLLOW_data_type_in_switchInstr2384);
            data_type122=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type122.getTree());
            pushFollow(FOLLOW_result_in_switchInstr2388);
            e1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());
            COMMA123=(Token)match(input,COMMA,FOLLOW_COMMA_in_switchInstr2390); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA123_tree = (Object)adaptor.create(COMMA123);
            adaptor.addChild(root_0, COMMA123_tree);
            }
            string_literal124=(Token)match(input,76,FOLLOW_76_in_switchInstr2392); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal124_tree = (Object)adaptor.create(string_literal124);
            adaptor.addChild(root_0, string_literal124_tree);
            }
            pushFollow(FOLLOW_result_in_switchInstr2396);
            e2=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
            START_SQUARE_BR125=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_switchInstr2398); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            START_SQUARE_BR125_tree = (Object)adaptor.create(START_SQUARE_BR125);
            adaptor.addChild(root_0, START_SQUARE_BR125_tree);
            }
            pushFollow(FOLLOW_cases_list_in_switchInstr2408);
            e=cases_list((e1!=null?input.toString(e1.start,e1.stop):null), (e2!=null?input.toString(e2.start,e2.stop):null), (s!=null?s.getLine():0));

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            END_SQUARE_BR126=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_switchInstr2411); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_SQUARE_BR126_tree = (Object)adaptor.create(END_SQUARE_BR126);
            adaptor.addChild(root_0, END_SQUARE_BR126_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 31, switchInstr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "switchInstr"

    public static class cases_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cases_list"
    // Resources/LLVM/llvmGrammar.g:563:1: cases_list[String switchOn,String defaultBB, int line] : ( cases )* ;
    public final llvmGrammarParser.cases_list_return cases_list(String switchOn, String defaultBB, int line) throws RecognitionException {
        llvmGrammarParser.cases_list_return retval = new llvmGrammarParser.cases_list_return();
        retval.start = input.LT(1);
        int cases_list_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.cases_return cases127 = null;



        	
        			   setSwitchInstr(switchOn,defaultBB, line, 0);							
        		    
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:566:8: ( ( cases )* )
            // Resources/LLVM/llvmGrammar.g:566:10: ( cases )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:566:10: ( cases )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==PRIMITIVE_DATA_TYPE||LA46_0==LOCAL_PREFIX||LA46_0==START_PARANTHESIS||LA46_0==START_ANGULAR||LA46_0==START_SQUARE_BR) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: cases
            	    {
            	    pushFollow(FOLLOW_cases_in_cases_list2434);
            	    cases127=cases();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, cases127.getTree());

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 32, cases_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "cases_list"

    public static class cases_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cases"
    // Resources/LLVM/llvmGrammar.g:568:1: cases : ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result ) ;
    public final llvmGrammarParser.cases_return cases() throws RecognitionException {
        llvmGrammarParser.cases_return retval = new llvmGrammarParser.cases_return();
        retval.start = input.LT(1);
        int cases_StartIndex = input.index();
        Object root_0 = null;

        Token e1=null;
        Token COMMA129=null;
        Token string_literal130=null;
        llvmGrammarParser.result_return e2 = null;

        llvmGrammarParser.data_type_return data_type128 = null;


        Object e1_tree=null;
        Object COMMA129_tree=null;
        Object string_literal130_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:568:7: ( ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result ) )
            // Resources/LLVM/llvmGrammar.g:568:9: ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:568:9: ( data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result )
            // Resources/LLVM/llvmGrammar.g:568:10: data_type e1= ( NUMBER | ID ) COMMA 'label' e2= result
            {
            pushFollow(FOLLOW_data_type_in_cases2453);
            data_type128=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type128.getTree());
            e1=(Token)input.LT(1);
            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(e1));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            COMMA129=(Token)match(input,COMMA,FOLLOW_COMMA_in_cases2465); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA129_tree = (Object)adaptor.create(COMMA129);
            adaptor.addChild(root_0, COMMA129_tree);
            }
            string_literal130=(Token)match(input,76,FOLLOW_76_in_cases2467); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal130_tree = (Object)adaptor.create(string_literal130);
            adaptor.addChild(root_0, string_literal130_tree);
            }
            pushFollow(FOLLOW_result_in_cases2473);
            e2=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());

            }

            if ( state.backtracking==0 ) {
              		
                	  		String typeStr = (data_type128!=null?input.toString(data_type128.start,data_type128.stop):null);
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
            if ( state.backtracking>0 ) { memoize(input, 33, cases_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "cases"

    public static class unreachable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unreachable"
    // Resources/LLVM/llvmGrammar.g:576:1: unreachable : UNREACHABLE ;
    public final llvmGrammarParser.unreachable_return unreachable() throws RecognitionException {
        llvmGrammarParser.unreachable_return retval = new llvmGrammarParser.unreachable_return();
        retval.start = input.LT(1);
        int unreachable_StartIndex = input.index();
        Object root_0 = null;

        Token UNREACHABLE131=null;

        Object UNREACHABLE131_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:576:13: ( UNREACHABLE )
            // Resources/LLVM/llvmGrammar.g:576:15: UNREACHABLE
            {
            root_0 = (Object)adaptor.nil();

            UNREACHABLE131=(Token)match(input,UNREACHABLE,FOLLOW_UNREACHABLE_in_unreachable2497); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            UNREACHABLE131_tree = (Object)adaptor.create(UNREACHABLE131);
            adaptor.addChild(root_0, UNREACHABLE131_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 34, unreachable_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "unreachable"

    public static class other_instruction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "other_instruction"
    // Resources/LLVM/llvmGrammar.g:582:1: other_instruction : ( cmp | phi | call | select );
    public final llvmGrammarParser.other_instruction_return other_instruction() throws RecognitionException {
        llvmGrammarParser.other_instruction_return retval = new llvmGrammarParser.other_instruction_return();
        retval.start = input.LT(1);
        int other_instruction_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.cmp_return cmp132 = null;

        llvmGrammarParser.phi_return phi133 = null;

        llvmGrammarParser.call_return call134 = null;

        llvmGrammarParser.select_return select135 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:582:19: ( cmp | phi | call | select )
            int alt47=4;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:582:21: cmp
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_cmp_in_other_instruction2519);
                    cmp132=cmp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cmp132.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:583:11: phi
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_phi_in_other_instruction2532);
                    phi133=phi();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, phi133.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:584:11: call
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_call_in_other_instruction2545);
                    call134=call();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, call134.getTree());

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:585:11: select
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_select_in_other_instruction2558);
                    select135=select();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, select135.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 35, other_instruction_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "other_instruction"

    public static class cmp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cmp"
    // Resources/LLVM/llvmGrammar.g:587:1: cmp : id1= result e= EQUAL (compr= 'icmp' | 'fcmp' ) pre= CONDITION typestr= data_type o1= result COMMA o2= result ;
    public final llvmGrammarParser.cmp_return cmp() throws RecognitionException {
        llvmGrammarParser.cmp_return retval = new llvmGrammarParser.cmp_return();
        retval.start = input.LT(1);
        int cmp_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token compr=null;
        Token pre=null;
        Token string_literal136=null;
        Token COMMA137=null;
        llvmGrammarParser.result_return id1 = null;

        llvmGrammarParser.data_type_return typestr = null;

        llvmGrammarParser.result_return o1 = null;

        llvmGrammarParser.result_return o2 = null;


        Object e_tree=null;
        Object compr_tree=null;
        Object pre_tree=null;
        Object string_literal136_tree=null;
        Object COMMA137_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:587:5: (id1= result e= EQUAL (compr= 'icmp' | 'fcmp' ) pre= CONDITION typestr= data_type o1= result COMMA o2= result )
            // Resources/LLVM/llvmGrammar.g:587:6: id1= result e= EQUAL (compr= 'icmp' | 'fcmp' ) pre= CONDITION typestr= data_type o1= result COMMA o2= result
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_cmp2569);
            id1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, id1.getTree());
            e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_cmp2573); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            // Resources/LLVM/llvmGrammar.g:587:26: (compr= 'icmp' | 'fcmp' )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==78) ) {
                alt48=1;
            }
            else if ( (LA48_0==79) ) {
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
                    // Resources/LLVM/llvmGrammar.g:587:27: compr= 'icmp'
                    {
                    compr=(Token)match(input,78,FOLLOW_78_in_cmp2580); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    compr_tree = (Object)adaptor.create(compr);
                    adaptor.addChild(root_0, compr_tree);
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:587:42: 'fcmp'
                    {
                    string_literal136=(Token)match(input,79,FOLLOW_79_in_cmp2582); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal136_tree = (Object)adaptor.create(string_literal136);
                    adaptor.addChild(root_0, string_literal136_tree);
                    }

                    }
                    break;

            }

            pre=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_cmp2587); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            pre_tree = (Object)adaptor.create(pre);
            adaptor.addChild(root_0, pre_tree);
            }
            pushFollow(FOLLOW_data_type_in_cmp2591);
            typestr=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typestr.getTree());
            pushFollow(FOLLOW_result_in_cmp2611);
            o1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, o1.getTree());
            COMMA137=(Token)match(input,COMMA,FOLLOW_COMMA_in_cmp2614); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA137_tree = (Object)adaptor.create(COMMA137);
            adaptor.addChild(root_0, COMMA137_tree);
            }
            pushFollow(FOLLOW_result_in_cmp2621);
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
            if ( state.backtracking>0 ) { memoize(input, 36, cmp_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "cmp"

    public static class phi_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "phi"
    // Resources/LLVM/llvmGrammar.g:594:1: phi : num= result e= EQUAL PHI typeStr= data_type incoming= incoming_list ;
    public final llvmGrammarParser.phi_return phi() throws RecognitionException {
        llvmGrammarParser.phi_return retval = new llvmGrammarParser.phi_return();
        retval.start = input.LT(1);
        int phi_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token PHI138=null;
        llvmGrammarParser.result_return num = null;

        llvmGrammarParser.data_type_return typeStr = null;

        llvmGrammarParser.incoming_list_return incoming = null;


        Object e_tree=null;
        Object PHI138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:594:5: (num= result e= EQUAL PHI typeStr= data_type incoming= incoming_list )
            // Resources/LLVM/llvmGrammar.g:594:7: num= result e= EQUAL PHI typeStr= data_type incoming= incoming_list
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_phi2636);
            num=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, num.getTree());
            e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_phi2640); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            PHI138=(Token)match(input,PHI,FOLLOW_PHI_in_phi2642); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PHI138_tree = (Object)adaptor.create(PHI138);
            adaptor.addChild(root_0, PHI138_tree);
            }
            pushFollow(FOLLOW_data_type_in_phi2646);
            typeStr=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr.getTree());
            pushFollow(FOLLOW_incoming_list_in_phi2650);
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
            if ( state.backtracking>0 ) { memoize(input, 37, phi_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "phi"

    public static class incoming_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "incoming_list"
    // Resources/LLVM/llvmGrammar.g:599:1: incoming_list : START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+ ;
    public final llvmGrammarParser.incoming_list_return incoming_list() throws RecognitionException {
        llvmGrammarParser.incoming_list_return retval = new llvmGrammarParser.incoming_list_return();
        retval.start = input.LT(1);
        int incoming_list_StartIndex = input.index();
        Object root_0 = null;

        Token START_SQUARE_BR139=null;
        Token string_literal140=null;
        Token string_literal141=null;
        Token string_literal143=null;
        Token COMMA144=null;
        Token END_SQUARE_BR146=null;
        Token COMMA147=null;
        Token START_SQUARE_BR148=null;
        Token string_literal149=null;
        Token string_literal150=null;
        Token string_literal152=null;
        Token COMMA153=null;
        Token END_SQUARE_BR155=null;
        llvmGrammarParser.result_return result142 = null;

        llvmGrammarParser.result_return result145 = null;

        llvmGrammarParser.result_return result151 = null;

        llvmGrammarParser.result_return result154 = null;


        Object START_SQUARE_BR139_tree=null;
        Object string_literal140_tree=null;
        Object string_literal141_tree=null;
        Object string_literal143_tree=null;
        Object COMMA144_tree=null;
        Object END_SQUARE_BR146_tree=null;
        Object COMMA147_tree=null;
        Object START_SQUARE_BR148_tree=null;
        Object string_literal149_tree=null;
        Object string_literal150_tree=null;
        Object string_literal152_tree=null;
        Object COMMA153_tree=null;
        Object END_SQUARE_BR155_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:599:15: ( START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+ )
            // Resources/LLVM/llvmGrammar.g:599:17: START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+
            {
            root_0 = (Object)adaptor.nil();

            START_SQUARE_BR139=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_incoming_list2662); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            START_SQUARE_BR139_tree = (Object)adaptor.create(START_SQUARE_BR139);
            adaptor.addChild(root_0, START_SQUARE_BR139_tree);
            }
            // Resources/LLVM/llvmGrammar.g:599:32: ( 'true' | 'false' | result | 'undef' )
            int alt49=4;
            switch ( input.LA(1) ) {
            case TRUE:
                {
                int LA49_1 = input.LA(2);

                if ( (synpred71_llvmGrammar()) ) {
                    alt49=1;
                }
                else if ( (synpred73_llvmGrammar()) ) {
                    alt49=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 1, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA49_2 = input.LA(2);

                if ( (synpred72_llvmGrammar()) ) {
                    alt49=2;
                }
                else if ( (synpred73_llvmGrammar()) ) {
                    alt49=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 2, input);

                    throw nvae;
                }
                }
                break;
            case ID:
            case GLOBAL_PREFIX:
            case LOCAL_PREFIX:
            case NUMBER:
            case FLOATING_LITERAL:
                {
                alt49=3;
                }
                break;
            case 73:
                {
                int LA49_4 = input.LA(2);

                if ( (synpred73_llvmGrammar()) ) {
                    alt49=3;
                }
                else if ( (true) ) {
                    alt49=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 4, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:599:34: 'true'
                    {
                    string_literal140=(Token)match(input,TRUE,FOLLOW_TRUE_in_incoming_list2665); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal140_tree = (Object)adaptor.create(string_literal140);
                    adaptor.addChild(root_0, string_literal140_tree);
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:599:41: 'false'
                    {
                    string_literal141=(Token)match(input,FALSE,FOLLOW_FALSE_in_incoming_list2667); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal141_tree = (Object)adaptor.create(string_literal141);
                    adaptor.addChild(root_0, string_literal141_tree);
                    }

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:599:49: result
                    {
                    pushFollow(FOLLOW_result_in_incoming_list2669);
                    result142=result();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, result142.getTree());

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:599:56: 'undef'
                    {
                    string_literal143=(Token)match(input,73,FOLLOW_73_in_incoming_list2671); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal143_tree = (Object)adaptor.create(string_literal143);
                    adaptor.addChild(root_0, string_literal143_tree);
                    }

                    }
                    break;

            }

            COMMA144=(Token)match(input,COMMA,FOLLOW_COMMA_in_incoming_list2674); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA144_tree = (Object)adaptor.create(COMMA144);
            adaptor.addChild(root_0, COMMA144_tree);
            }
            pushFollow(FOLLOW_result_in_incoming_list2676);
            result145=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, result145.getTree());
            END_SQUARE_BR146=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_incoming_list2678); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_SQUARE_BR146_tree = (Object)adaptor.create(END_SQUARE_BR146);
            adaptor.addChild(root_0, END_SQUARE_BR146_tree);
            }
            // Resources/LLVM/llvmGrammar.g:600:5: ( COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:600:6: COMMA START_SQUARE_BR ( 'true' | 'false' | result | 'undef' ) COMMA result END_SQUARE_BR
            	    {
            	    COMMA147=(Token)match(input,COMMA,FOLLOW_COMMA_in_incoming_list2685); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA147_tree = (Object)adaptor.create(COMMA147);
            	    adaptor.addChild(root_0, COMMA147_tree);
            	    }
            	    START_SQUARE_BR148=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_incoming_list2687); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    START_SQUARE_BR148_tree = (Object)adaptor.create(START_SQUARE_BR148);
            	    adaptor.addChild(root_0, START_SQUARE_BR148_tree);
            	    }
            	    // Resources/LLVM/llvmGrammar.g:600:27: ( 'true' | 'false' | result | 'undef' )
            	    int alt50=4;
            	    switch ( input.LA(1) ) {
            	    case TRUE:
            	        {
            	        int LA50_1 = input.LA(2);

            	        if ( (synpred74_llvmGrammar()) ) {
            	            alt50=1;
            	        }
            	        else if ( (synpred76_llvmGrammar()) ) {
            	            alt50=3;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 50, 1, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case FALSE:
            	        {
            	        int LA50_2 = input.LA(2);

            	        if ( (synpred75_llvmGrammar()) ) {
            	            alt50=2;
            	        }
            	        else if ( (synpred76_llvmGrammar()) ) {
            	            alt50=3;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 50, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case ID:
            	    case GLOBAL_PREFIX:
            	    case LOCAL_PREFIX:
            	    case NUMBER:
            	    case FLOATING_LITERAL:
            	        {
            	        alt50=3;
            	        }
            	        break;
            	    case 73:
            	        {
            	        int LA50_4 = input.LA(2);

            	        if ( (synpred76_llvmGrammar()) ) {
            	            alt50=3;
            	        }
            	        else if ( (true) ) {
            	            alt50=4;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 50, 4, input);

            	            throw nvae;
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
            	            // Resources/LLVM/llvmGrammar.g:600:29: 'true'
            	            {
            	            string_literal149=(Token)match(input,TRUE,FOLLOW_TRUE_in_incoming_list2690); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal149_tree = (Object)adaptor.create(string_literal149);
            	            adaptor.addChild(root_0, string_literal149_tree);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Resources/LLVM/llvmGrammar.g:600:36: 'false'
            	            {
            	            string_literal150=(Token)match(input,FALSE,FOLLOW_FALSE_in_incoming_list2692); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal150_tree = (Object)adaptor.create(string_literal150);
            	            adaptor.addChild(root_0, string_literal150_tree);
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // Resources/LLVM/llvmGrammar.g:600:44: result
            	            {
            	            pushFollow(FOLLOW_result_in_incoming_list2694);
            	            result151=result();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, result151.getTree());

            	            }
            	            break;
            	        case 4 :
            	            // Resources/LLVM/llvmGrammar.g:600:51: 'undef'
            	            {
            	            string_literal152=(Token)match(input,73,FOLLOW_73_in_incoming_list2696); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal152_tree = (Object)adaptor.create(string_literal152);
            	            adaptor.addChild(root_0, string_literal152_tree);
            	            }

            	            }
            	            break;

            	    }

            	    COMMA153=(Token)match(input,COMMA,FOLLOW_COMMA_in_incoming_list2700); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA153_tree = (Object)adaptor.create(COMMA153);
            	    adaptor.addChild(root_0, COMMA153_tree);
            	    }
            	    pushFollow(FOLLOW_result_in_incoming_list2702);
            	    result154=result();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, result154.getTree());
            	    END_SQUARE_BR155=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_incoming_list2704); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    END_SQUARE_BR155_tree = (Object)adaptor.create(END_SQUARE_BR155);
            	    adaptor.addChild(root_0, END_SQUARE_BR155_tree);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt51 >= 1 ) break loop51;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(51, input);
                        throw eee;
                }
                cnt51++;
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 38, incoming_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "incoming_list"

    public static class call_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "call"
    // Resources/LLVM/llvmGrammar.g:602:1: call : (name= result EQUAL )? (tail= 'tail' )? c= 'call' (callingc= CALLING_CONV )? (parameterAttr= PARAMETER_ATTRIBUTE )? typeStr= data_type func_name START_PARANTHESIS para= parameter END_PARANTHESIS (functionAttr= FUNCTION_ATTRIBUTE )? ;
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
        Token EQUAL156=null;
        Token START_PARANTHESIS158=null;
        Token END_PARANTHESIS159=null;
        llvmGrammarParser.result_return name = null;

        llvmGrammarParser.data_type_return typeStr = null;

        llvmGrammarParser.parameter_return para = null;

        llvmGrammarParser.func_name_return func_name157 = null;


        Object tail_tree=null;
        Object c_tree=null;
        Object callingc_tree=null;
        Object parameterAttr_tree=null;
        Object functionAttr_tree=null;
        Object EQUAL156_tree=null;
        Object START_PARANTHESIS158_tree=null;
        Object END_PARANTHESIS159_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:602:6: ( (name= result EQUAL )? (tail= 'tail' )? c= 'call' (callingc= CALLING_CONV )? (parameterAttr= PARAMETER_ATTRIBUTE )? typeStr= data_type func_name START_PARANTHESIS para= parameter END_PARANTHESIS (functionAttr= FUNCTION_ATTRIBUTE )? )
            // Resources/LLVM/llvmGrammar.g:602:8: (name= result EQUAL )? (tail= 'tail' )? c= 'call' (callingc= CALLING_CONV )? (parameterAttr= PARAMETER_ATTRIBUTE )? typeStr= data_type func_name START_PARANTHESIS para= parameter END_PARANTHESIS (functionAttr= FUNCTION_ATTRIBUTE )?
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:602:8: (name= result EQUAL )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=TRUE && LA52_0<=FALSE)||LA52_0==ID||(LA52_0>=GLOBAL_PREFIX && LA52_0<=LOCAL_PREFIX)||LA52_0==NUMBER||LA52_0==FLOATING_LITERAL||LA52_0==73) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:602:9: name= result EQUAL
                    {
                    pushFollow(FOLLOW_result_in_call2723);
                    name=result();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());
                    EQUAL156=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_call2725); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EQUAL156_tree = (Object)adaptor.create(EQUAL156);
                    adaptor.addChild(root_0, EQUAL156_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:602:33: (tail= 'tail' )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==80) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: tail= 'tail'
                    {
                    tail=(Token)match(input,80,FOLLOW_80_in_call2731); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    tail_tree = (Object)adaptor.create(tail);
                    adaptor.addChild(root_0, tail_tree);
                    }

                    }
                    break;

            }

            c=(Token)match(input,81,FOLLOW_81_in_call2736); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            c_tree = (Object)adaptor.create(c);
            adaptor.addChild(root_0, c_tree);
            }
            // Resources/LLVM/llvmGrammar.g:602:59: (callingc= CALLING_CONV )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==CALLING_CONV) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: callingc= CALLING_CONV
                    {
                    callingc=(Token)match(input,CALLING_CONV,FOLLOW_CALLING_CONV_in_call2740); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    callingc_tree = (Object)adaptor.create(callingc);
                    adaptor.addChild(root_0, callingc_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:602:87: (parameterAttr= PARAMETER_ATTRIBUTE )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==PARAMETER_ATTRIBUTE) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: parameterAttr= PARAMETER_ATTRIBUTE
                    {
                    parameterAttr=(Token)match(input,PARAMETER_ATTRIBUTE,FOLLOW_PARAMETER_ATTRIBUTE_in_call2745); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    parameterAttr_tree = (Object)adaptor.create(parameterAttr);
                    adaptor.addChild(root_0, parameterAttr_tree);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_data_type_in_call2753);
            typeStr=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr.getTree());
            pushFollow(FOLLOW_func_name_in_call2755);
            func_name157=func_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, func_name157.getTree());
            START_PARANTHESIS158=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_call2757); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            START_PARANTHESIS158_tree = (Object)adaptor.create(START_PARANTHESIS158);
            adaptor.addChild(root_0, START_PARANTHESIS158_tree);
            }
            pushFollow(FOLLOW_parameter_in_call2761);
            para=parameter();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, para.getTree());
            END_PARANTHESIS159=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_call2763); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_PARANTHESIS159_tree = (Object)adaptor.create(END_PARANTHESIS159);
            adaptor.addChild(root_0, END_PARANTHESIS159_tree);
            }
            // Resources/LLVM/llvmGrammar.g:603:92: (functionAttr= FUNCTION_ATTRIBUTE )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==FUNCTION_ATTRIBUTE) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: functionAttr= FUNCTION_ATTRIBUTE
                    {
                    functionAttr=(Token)match(input,FUNCTION_ATTRIBUTE,FOLLOW_FUNCTION_ATTRIBUTE_in_call2767); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    functionAttr_tree = (Object)adaptor.create(functionAttr);
                    adaptor.addChild(root_0, functionAttr_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

              	 	setCallInstr((name!=null?input.toString(name.start,name.stop):null), (callingc!=null?callingc.getText():null), (tail!=null?tail.getText():null), (para!=null?input.toString(para.start,para.stop):null), (typeStr!=null?input.toString(typeStr.start,typeStr.stop):null), (func_name157!=null?input.toString(func_name157.start,func_name157.stop):null), 
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
            if ( state.backtracking>0 ) { memoize(input, 39, call_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "call"

    public static class func_name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "func_name"
    // Resources/LLVM/llvmGrammar.g:609:1: func_name : ( prefix ( NUMBER | ID ) ) ;
    public final llvmGrammarParser.func_name_return func_name() throws RecognitionException {
        llvmGrammarParser.func_name_return retval = new llvmGrammarParser.func_name_return();
        retval.start = input.LT(1);
        int func_name_StartIndex = input.index();
        Object root_0 = null;

        Token set161=null;
        llvmGrammarParser.prefix_return prefix160 = null;


        Object set161_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:609:11: ( ( prefix ( NUMBER | ID ) ) )
            // Resources/LLVM/llvmGrammar.g:609:12: ( prefix ( NUMBER | ID ) )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:609:12: ( prefix ( NUMBER | ID ) )
            // Resources/LLVM/llvmGrammar.g:609:13: prefix ( NUMBER | ID )
            {
            pushFollow(FOLLOW_prefix_in_func_name2780);
            prefix160=prefix();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix160.getTree());
            set161=(Token)input.LT(1);
            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set161));
                state.errorRecovery=false;state.failed=false;
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
            if ( state.backtracking>0 ) { memoize(input, 40, func_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "func_name"

    public static class parameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter"
    // Resources/LLVM/llvmGrammar.g:611:1: parameter : ( ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )? | func_type );
    public final llvmGrammarParser.parameter_return parameter() throws RecognitionException {
        llvmGrammarParser.parameter_return retval = new llvmGrammarParser.parameter_return();
        retval.start = input.LT(1);
        int parameter_StartIndex = input.index();
        Object root_0 = null;

        Token set164=null;
        Token COMMA166=null;
        Token set169=null;
        llvmGrammarParser.data_type_return data_type162 = null;

        llvmGrammarParser.prefix_return prefix163 = null;

        llvmGrammarParser.truefalse_return truefalse165 = null;

        llvmGrammarParser.data_type_return data_type167 = null;

        llvmGrammarParser.prefix_return prefix168 = null;

        llvmGrammarParser.truefalse_return truefalse170 = null;

        llvmGrammarParser.func_type_return func_type171 = null;


        Object set164_tree=null;
        Object COMMA166_tree=null;
        Object set169_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:611:11: ( ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )? | func_type )
            int alt63=2;
            switch ( input.LA(1) ) {
            case PRIMITIVE_DATA_TYPE:
                {
                int LA63_1 = input.LA(2);

                if ( (synpred94_llvmGrammar()) ) {
                    alt63=1;
                }
                else if ( (true) ) {
                    alt63=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
                }
                break;
            case LOCAL_PREFIX:
            case END_PARANTHESIS:
            case START_ANGULAR:
            case START_SQUARE_BR:
                {
                alt63=1;
                }
                break;
            case START_PARANTHESIS:
                {
                int LA63_5 = input.LA(2);

                if ( (synpred94_llvmGrammar()) ) {
                    alt63=1;
                }
                else if ( (true) ) {
                    alt63=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 5, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:611:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:611:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==PRIMITIVE_DATA_TYPE||LA62_0==LOCAL_PREFIX||LA62_0==START_PARANTHESIS||LA62_0==START_ANGULAR||LA62_0==START_SQUARE_BR) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:611:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
                            {
                            // Resources/LLVM/llvmGrammar.g:611:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
                            // Resources/LLVM/llvmGrammar.g:611:15: data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
                            {
                            pushFollow(FOLLOW_data_type_in_parameter2804);
                            data_type162=data_type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type162.getTree());
                            // Resources/LLVM/llvmGrammar.g:611:25: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
                            int alt58=2;
                            int LA58_0 = input.LA(1);

                            if ( (LA58_0==ID||(LA58_0>=GLOBAL_PREFIX && LA58_0<=LOCAL_PREFIX)||LA58_0==NUMBER||LA58_0==FLOATING_LITERAL) ) {
                                alt58=1;
                            }
                            else if ( ((LA58_0>=TRUE && LA58_0<=FALSE)) ) {
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
                                    // Resources/LLVM/llvmGrammar.g:611:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                                    {
                                    // Resources/LLVM/llvmGrammar.g:611:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                                    // Resources/LLVM/llvmGrammar.g:611:27: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
                                    {
                                    // Resources/LLVM/llvmGrammar.g:611:27: ( prefix )?
                                    int alt57=2;
                                    int LA57_0 = input.LA(1);

                                    if ( ((LA57_0>=GLOBAL_PREFIX && LA57_0<=LOCAL_PREFIX)) ) {
                                        alt57=1;
                                    }
                                    switch (alt57) {
                                        case 1 :
                                            // Resources/LLVM/llvmGrammar.g:0:0: prefix
                                            {
                                            pushFollow(FOLLOW_prefix_in_parameter2808);
                                            prefix163=prefix();

                                            state._fsp--;
                                            if (state.failed) return retval;
                                            if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix163.getTree());

                                            }
                                            break;

                                    }

                                    set164=(Token)input.LT(1);
                                    if ( input.LA(1)==ID||input.LA(1)==NUMBER||input.LA(1)==FLOATING_LITERAL ) {
                                        input.consume();
                                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set164));
                                        state.errorRecovery=false;state.failed=false;
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
                                    // Resources/LLVM/llvmGrammar.g:611:68: truefalse
                                    {
                                    pushFollow(FOLLOW_truefalse_in_parameter2823);
                                    truefalse165=truefalse();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, truefalse165.getTree());

                                    }
                                    break;

                            }

                            // Resources/LLVM/llvmGrammar.g:612:4: ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
                            loop61:
                            do {
                                int alt61=2;
                                int LA61_0 = input.LA(1);

                                if ( (LA61_0==COMMA) ) {
                                    alt61=1;
                                }


                                switch (alt61) {
                            	case 1 :
                            	    // Resources/LLVM/llvmGrammar.g:612:5: COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
                            	    {
                            	    COMMA166=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameter2830); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    COMMA166_tree = (Object)adaptor.create(COMMA166);
                            	    adaptor.addChild(root_0, COMMA166_tree);
                            	    }
                            	    pushFollow(FOLLOW_data_type_in_parameter2832);
                            	    data_type167=data_type();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type167.getTree());
                            	    // Resources/LLVM/llvmGrammar.g:612:21: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
                            	    int alt60=2;
                            	    int LA60_0 = input.LA(1);

                            	    if ( (LA60_0==ID||(LA60_0>=GLOBAL_PREFIX && LA60_0<=LOCAL_PREFIX)||LA60_0==NUMBER||LA60_0==FLOATING_LITERAL) ) {
                            	        alt60=1;
                            	    }
                            	    else if ( ((LA60_0>=TRUE && LA60_0<=FALSE)) ) {
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
                            	            // Resources/LLVM/llvmGrammar.g:612:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                            	            {
                            	            // Resources/LLVM/llvmGrammar.g:612:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                            	            // Resources/LLVM/llvmGrammar.g:612:23: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
                            	            {
                            	            // Resources/LLVM/llvmGrammar.g:612:23: ( prefix )?
                            	            int alt59=2;
                            	            int LA59_0 = input.LA(1);

                            	            if ( ((LA59_0>=GLOBAL_PREFIX && LA59_0<=LOCAL_PREFIX)) ) {
                            	                alt59=1;
                            	            }
                            	            switch (alt59) {
                            	                case 1 :
                            	                    // Resources/LLVM/llvmGrammar.g:0:0: prefix
                            	                    {
                            	                    pushFollow(FOLLOW_prefix_in_parameter2836);
                            	                    prefix168=prefix();

                            	                    state._fsp--;
                            	                    if (state.failed) return retval;
                            	                    if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix168.getTree());

                            	                    }
                            	                    break;

                            	            }

                            	            set169=(Token)input.LT(1);
                            	            if ( input.LA(1)==ID||input.LA(1)==NUMBER||input.LA(1)==FLOATING_LITERAL ) {
                            	                input.consume();
                            	                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set169));
                            	                state.errorRecovery=false;state.failed=false;
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
                            	            // Resources/LLVM/llvmGrammar.g:612:64: truefalse
                            	            {
                            	            pushFollow(FOLLOW_truefalse_in_parameter2851);
                            	            truefalse170=truefalse();

                            	            state._fsp--;
                            	            if (state.failed) return retval;
                            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, truefalse170.getTree());

                            	            }
                            	            break;

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop61;
                                }
                            } while (true);


                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:613:6: func_type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_func_type_in_parameter2865);
                    func_type171=func_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, func_type171.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 41, parameter_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parameter"

    public static class select_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "select"
    // Resources/LLVM/llvmGrammar.g:615:1: select : name= result EQUAL t= 'select' typeStr= data_type condValue= result COMMA typeStr1= data_type valueStr1= result COMMA typeStr2= data_type valueStr2= result ;
    public final llvmGrammarParser.select_return select() throws RecognitionException {
        llvmGrammarParser.select_return retval = new llvmGrammarParser.select_return();
        retval.start = input.LT(1);
        int select_StartIndex = input.index();
        Object root_0 = null;

        Token t=null;
        Token EQUAL172=null;
        Token COMMA173=null;
        Token COMMA174=null;
        llvmGrammarParser.result_return name = null;

        llvmGrammarParser.data_type_return typeStr = null;

        llvmGrammarParser.result_return condValue = null;

        llvmGrammarParser.data_type_return typeStr1 = null;

        llvmGrammarParser.result_return valueStr1 = null;

        llvmGrammarParser.data_type_return typeStr2 = null;

        llvmGrammarParser.result_return valueStr2 = null;


        Object t_tree=null;
        Object EQUAL172_tree=null;
        Object COMMA173_tree=null;
        Object COMMA174_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:615:8: (name= result EQUAL t= 'select' typeStr= data_type condValue= result COMMA typeStr1= data_type valueStr1= result COMMA typeStr2= data_type valueStr2= result )
            // Resources/LLVM/llvmGrammar.g:615:10: name= result EQUAL t= 'select' typeStr= data_type condValue= result COMMA typeStr1= data_type valueStr1= result COMMA typeStr2= data_type valueStr2= result
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_select2876);
            name=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());
            EQUAL172=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_select2878); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EQUAL172_tree = (Object)adaptor.create(EQUAL172);
            adaptor.addChild(root_0, EQUAL172_tree);
            }
            t=(Token)match(input,82,FOLLOW_82_in_select2882); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            t_tree = (Object)adaptor.create(t);
            adaptor.addChild(root_0, t_tree);
            }
            pushFollow(FOLLOW_data_type_in_select2886);
            typeStr=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr.getTree());
            pushFollow(FOLLOW_result_in_select2890);
            condValue=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condValue.getTree());
            COMMA173=(Token)match(input,COMMA,FOLLOW_COMMA_in_select2892); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA173_tree = (Object)adaptor.create(COMMA173);
            adaptor.addChild(root_0, COMMA173_tree);
            }
            pushFollow(FOLLOW_data_type_in_select2900);
            typeStr1=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr1.getTree());
            pushFollow(FOLLOW_result_in_select2904);
            valueStr1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, valueStr1.getTree());
            COMMA174=(Token)match(input,COMMA,FOLLOW_COMMA_in_select2906); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA174_tree = (Object)adaptor.create(COMMA174);
            adaptor.addChild(root_0, COMMA174_tree);
            }
            pushFollow(FOLLOW_data_type_in_select2914);
            typeStr2=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeStr2.getTree());
            pushFollow(FOLLOW_result_in_select2918);
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
            if ( state.backtracking>0 ) { memoize(input, 42, select_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "select"

    public static class cast_instruction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cast_instruction"
    // Resources/LLVM/llvmGrammar.g:623:1: cast_instruction : name= result e= EQUAL cast_instr_rhs[$name.text, $e.line] ;
    public final llvmGrammarParser.cast_instruction_return cast_instruction() throws RecognitionException {
        llvmGrammarParser.cast_instruction_return retval = new llvmGrammarParser.cast_instruction_return();
        retval.start = input.LT(1);
        int cast_instruction_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        llvmGrammarParser.result_return name = null;

        llvmGrammarParser.cast_instr_rhs_return cast_instr_rhs175 = null;


        Object e_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:623:18: (name= result e= EQUAL cast_instr_rhs[$name.text, $e.line] )
            // Resources/LLVM/llvmGrammar.g:623:20: name= result e= EQUAL cast_instr_rhs[$name.text, $e.line]
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_cast_instruction2941);
            name=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());
            e=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_cast_instruction2945); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            pushFollow(FOLLOW_cast_instr_rhs_in_cast_instruction2947);
            cast_instr_rhs175=cast_instr_rhs((name!=null?input.toString(name.start,name.stop):null), (e!=null?e.getLine():0));

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, cast_instr_rhs175.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 43, cast_instruction_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "cast_instruction"

    public static class cast_instr_rhs_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cast_instr_rhs"
    // Resources/LLVM/llvmGrammar.g:625:1: cast_instr_rhs[String result, int line] : type= CAST_TYPE ( START_PARANTHESIS )? source= data_type value1= result 'to' target= data_type ( END_PARANTHESIS )? ;
    public final llvmGrammarParser.cast_instr_rhs_return cast_instr_rhs(String result, int line) throws RecognitionException {
        llvmGrammarParser.cast_instr_rhs_return retval = new llvmGrammarParser.cast_instr_rhs_return();
        retval.start = input.LT(1);
        int cast_instr_rhs_StartIndex = input.index();
        Object root_0 = null;

        Token type=null;
        Token START_PARANTHESIS176=null;
        Token string_literal177=null;
        Token END_PARANTHESIS178=null;
        llvmGrammarParser.data_type_return source = null;

        llvmGrammarParser.result_return value1 = null;

        llvmGrammarParser.data_type_return target = null;


        Object type_tree=null;
        Object START_PARANTHESIS176_tree=null;
        Object string_literal177_tree=null;
        Object END_PARANTHESIS178_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:625:41: (type= CAST_TYPE ( START_PARANTHESIS )? source= data_type value1= result 'to' target= data_type ( END_PARANTHESIS )? )
            // Resources/LLVM/llvmGrammar.g:625:43: type= CAST_TYPE ( START_PARANTHESIS )? source= data_type value1= result 'to' target= data_type ( END_PARANTHESIS )?
            {
            root_0 = (Object)adaptor.nil();

            type=(Token)match(input,CAST_TYPE,FOLLOW_CAST_TYPE_in_cast_instr_rhs2960); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            type_tree = (Object)adaptor.create(type);
            adaptor.addChild(root_0, type_tree);
            }
            // Resources/LLVM/llvmGrammar.g:625:58: ( START_PARANTHESIS )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==START_PARANTHESIS) ) {
                int LA64_1 = input.LA(2);

                if ( (synpred95_llvmGrammar()) ) {
                    alt64=1;
                }
            }
            switch (alt64) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: START_PARANTHESIS
                    {
                    START_PARANTHESIS176=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_cast_instr_rhs2962); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    START_PARANTHESIS176_tree = (Object)adaptor.create(START_PARANTHESIS176);
                    adaptor.addChild(root_0, START_PARANTHESIS176_tree);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_data_type_in_cast_instr_rhs2967);
            source=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, source.getTree());
            pushFollow(FOLLOW_result_in_cast_instr_rhs2984);
            value1=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, value1.getTree());
            string_literal177=(Token)match(input,83,FOLLOW_83_in_cast_instr_rhs2986); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal177_tree = (Object)adaptor.create(string_literal177);
            adaptor.addChild(root_0, string_literal177_tree);
            }
            pushFollow(FOLLOW_data_type_in_cast_instr_rhs2990);
            target=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, target.getTree());
            // Resources/LLVM/llvmGrammar.g:626:49: ( END_PARANTHESIS )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==END_PARANTHESIS) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: END_PARANTHESIS
                    {
                    END_PARANTHESIS178=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_cast_instr_rhs2992); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END_PARANTHESIS178_tree = (Object)adaptor.create(END_PARANTHESIS178);
                    adaptor.addChild(root_0, END_PARANTHESIS178_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 44, cast_instr_rhs_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "cast_instr_rhs"

    public static class global_var_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_var"
    // Resources/LLVM/llvmGrammar.g:632:1: global_var : ( global_array | structure_decl | structure_obj | string_constant | global_variable );
    public final llvmGrammarParser.global_var_return global_var() throws RecognitionException {
        llvmGrammarParser.global_var_return retval = new llvmGrammarParser.global_var_return();
        retval.start = input.LT(1);
        int global_var_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.global_array_return global_array179 = null;

        llvmGrammarParser.structure_decl_return structure_decl180 = null;

        llvmGrammarParser.structure_obj_return structure_obj181 = null;

        llvmGrammarParser.string_constant_return string_constant182 = null;

        llvmGrammarParser.global_variable_return global_variable183 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:632:12: ( global_array | structure_decl | structure_obj | string_constant | global_variable )
            int alt66=5;
            alt66 = dfa66.predict(input);
            switch (alt66) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:632:13: global_array
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_global_array_in_global_var3013);
                    global_array179=global_array();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, global_array179.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:632:28: structure_decl
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_structure_decl_in_global_var3017);
                    structure_decl180=structure_decl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, structure_decl180.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:632:45: structure_obj
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_structure_obj_in_global_var3021);
                    structure_obj181=structure_obj();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, structure_obj181.getTree());

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:632:60: string_constant
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_string_constant_in_global_var3024);
                    string_constant182=string_constant();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, string_constant182.getTree());

                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:632:78: global_variable
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_global_variable_in_global_var3028);
                    global_variable183=global_variable();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, global_variable183.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 45, global_var_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_var"

    public static class global_array_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_array"
    // Resources/LLVM/llvmGrammar.g:634:1: global_array : e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (unnamed= 'unnamed_addr' )? (e2= 'constant' )? (typeSt= array_data_type )? initial= list_of_initial_values COMMA ALIGN align= NUMBER ;
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
        Token string_literal184=null;
        Token COMMA185=null;
        Token ALIGN186=null;
        llvmGrammarParser.result_return e = null;

        llvmGrammarParser.array_data_type_return typeSt = null;

        llvmGrammarParser.list_of_initial_values_return initial = null;


        Object e0_tree=null;
        Object e1_tree=null;
        Object unnamed_tree=null;
        Object e2_tree=null;
        Object align_tree=null;
        Object string_literal184_tree=null;
        Object COMMA185_tree=null;
        Object ALIGN186_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:634:14: (e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (unnamed= 'unnamed_addr' )? (e2= 'constant' )? (typeSt= array_data_type )? initial= list_of_initial_values COMMA ALIGN align= NUMBER )
            // Resources/LLVM/llvmGrammar.g:634:17: e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (unnamed= 'unnamed_addr' )? (e2= 'constant' )? (typeSt= array_data_type )? initial= list_of_initial_values COMMA ALIGN align= NUMBER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_global_array3040);
            e=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            e0=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_global_array3044); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e0_tree = (Object)adaptor.create(e0);
            adaptor.addChild(root_0, e0_tree);
            }
            // Resources/LLVM/llvmGrammar.g:634:37: (e1= LINKAGE_TYPE )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==LINKAGE_TYPE) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e1= LINKAGE_TYPE
                    {
                    e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_global_array3048); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e1_tree = (Object)adaptor.create(e1);
                    adaptor.addChild(root_0, e1_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:634:52: ( 'global' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==84) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:634:53: 'global'
                    {
                    string_literal184=(Token)match(input,84,FOLLOW_84_in_global_array3052); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal184_tree = (Object)adaptor.create(string_literal184);
                    adaptor.addChild(root_0, string_literal184_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:634:64: (unnamed= 'unnamed_addr' )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==85) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:634:65: unnamed= 'unnamed_addr'
                    {
                    unnamed=(Token)match(input,85,FOLLOW_85_in_global_array3059); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    unnamed_tree = (Object)adaptor.create(unnamed);
                    adaptor.addChild(root_0, unnamed_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:635:7: (e2= 'constant' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==86) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e2= 'constant'
                    {
                    e2=(Token)match(input,86,FOLLOW_86_in_global_array3070); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e2_tree = (Object)adaptor.create(e2);
                    adaptor.addChild(root_0, e2_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:635:27: (typeSt= array_data_type )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==PRIMITIVE_DATA_TYPE||LA71_0==LOCAL_PREFIX||LA71_0==START_PARANTHESIS||LA71_0==START_ANGULAR) ) {
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
                    // Resources/LLVM/llvmGrammar.g:0:0: typeSt= array_data_type
                    {
                    pushFollow(FOLLOW_array_data_type_in_global_array3077);
                    typeSt=array_data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSt.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_list_of_initial_values_in_global_array3083);
            initial=list_of_initial_values();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, initial.getTree());
            COMMA185=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_array3085); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA185_tree = (Object)adaptor.create(COMMA185);
            adaptor.addChild(root_0, COMMA185_tree);
            }
            ALIGN186=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_global_array3087); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ALIGN186_tree = (Object)adaptor.create(ALIGN186);
            adaptor.addChild(root_0, ALIGN186_tree);
            }
            align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_global_array3091); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 46, global_array_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_array"

    public static class array_data_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "array_data_type"
    // Resources/LLVM/llvmGrammar.g:641:1: array_data_type : ( data_type ) ;
    public final llvmGrammarParser.array_data_type_return array_data_type() throws RecognitionException {
        llvmGrammarParser.array_data_type_return retval = new llvmGrammarParser.array_data_type_return();
        retval.start = input.LT(1);
        int array_data_type_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.data_type_return data_type187 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:641:17: ( ( data_type ) )
            // Resources/LLVM/llvmGrammar.g:641:19: ( data_type )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:641:19: ( data_type )
            // Resources/LLVM/llvmGrammar.g:641:20: data_type
            {
            pushFollow(FOLLOW_data_type_in_array_data_type3109);
            data_type187=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type187.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 47, array_data_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "array_data_type"

    public static class list_of_initial_values_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_of_initial_values"
    // Resources/LLVM/llvmGrammar.g:643:1: list_of_initial_values : ( (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR ) | ZEROINITIALIZER | ID STRING_LITERAL );
    public final llvmGrammarParser.list_of_initial_values_return list_of_initial_values() throws RecognitionException {
        llvmGrammarParser.list_of_initial_values_return retval = new llvmGrammarParser.list_of_initial_values_return();
        retval.start = input.LT(1);
        int list_of_initial_values_StartIndex = input.index();
        Object root_0 = null;

        Token e0=null;
        Token COMMA191=null;
        Token END_SQUARE_BR195=null;
        Token ZEROINITIALIZER196=null;
        Token ID197=null;
        Token STRING_LITERAL198=null;
        llvmGrammarParser.data_type_return data_type188 = null;

        llvmGrammarParser.array_initial_value_return array_initial_value189 = null;

        llvmGrammarParser.getelementptr_rhs_return getelementptr_rhs190 = null;

        llvmGrammarParser.data_type_return data_type192 = null;

        llvmGrammarParser.array_initial_value_return array_initial_value193 = null;

        llvmGrammarParser.getelementptr_rhs_return getelementptr_rhs194 = null;


        Object e0_tree=null;
        Object COMMA191_tree=null;
        Object END_SQUARE_BR195_tree=null;
        Object ZEROINITIALIZER196_tree=null;
        Object ID197_tree=null;
        Object STRING_LITERAL198_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:643:24: ( (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR ) | ZEROINITIALIZER | ID STRING_LITERAL )
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
                    // Resources/LLVM/llvmGrammar.g:643:26: (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:643:26: (e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR )
                    // Resources/LLVM/llvmGrammar.g:643:27: e0= START_SQUARE_BR ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) ) ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )* END_SQUARE_BR
                    {
                    e0=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_list_of_initial_values3124); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e0_tree = (Object)adaptor.create(e0);
                    adaptor.addChild(root_0, e0_tree);
                    }
                    // Resources/LLVM/llvmGrammar.g:643:45: ( data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )
                    // Resources/LLVM/llvmGrammar.g:643:46: data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] )
                    {
                    pushFollow(FOLLOW_data_type_in_list_of_initial_values3126);
                    data_type188=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type188.getTree());
                    // Resources/LLVM/llvmGrammar.g:643:56: ( array_initial_value | getelementptr_rhs[null, $e0.line] )
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==ZEROINITIALIZER||LA72_0==ID||LA72_0==START_SQUARE_BR||LA72_0==NUMBER||LA72_0==STRING_LITERAL) ) {
                        alt72=1;
                    }
                    else if ( (LA72_0==71) ) {
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
                            // Resources/LLVM/llvmGrammar.g:643:57: array_initial_value
                            {
                            pushFollow(FOLLOW_array_initial_value_in_list_of_initial_values3129);
                            array_initial_value189=array_initial_value();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value189.getTree());

                            }
                            break;
                        case 2 :
                            // Resources/LLVM/llvmGrammar.g:643:78: getelementptr_rhs[null, $e0.line]
                            {
                            pushFollow(FOLLOW_getelementptr_rhs_in_list_of_initial_values3132);
                            getelementptr_rhs190=getelementptr_rhs(null, (e0!=null?e0.getLine():0));

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs190.getTree());

                            }
                            break;

                    }


                    }

                    // Resources/LLVM/llvmGrammar.g:644:9: ( COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==COMMA) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // Resources/LLVM/llvmGrammar.g:644:10: COMMA data_type ( array_initial_value | getelementptr_rhs[null, $e0.line] )
                    	    {
                    	    COMMA191=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_initial_values3147); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA191_tree = (Object)adaptor.create(COMMA191);
                    	    adaptor.addChild(root_0, COMMA191_tree);
                    	    }
                    	    pushFollow(FOLLOW_data_type_in_list_of_initial_values3149);
                    	    data_type192=data_type();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type192.getTree());
                    	    // Resources/LLVM/llvmGrammar.g:644:26: ( array_initial_value | getelementptr_rhs[null, $e0.line] )
                    	    int alt73=2;
                    	    int LA73_0 = input.LA(1);

                    	    if ( (LA73_0==ZEROINITIALIZER||LA73_0==ID||LA73_0==START_SQUARE_BR||LA73_0==NUMBER||LA73_0==STRING_LITERAL) ) {
                    	        alt73=1;
                    	    }
                    	    else if ( (LA73_0==71) ) {
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
                    	            // Resources/LLVM/llvmGrammar.g:644:27: array_initial_value
                    	            {
                    	            pushFollow(FOLLOW_array_initial_value_in_list_of_initial_values3152);
                    	            array_initial_value193=array_initial_value();

                    	            state._fsp--;
                    	            if (state.failed) return retval;
                    	            if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value193.getTree());

                    	            }
                    	            break;
                    	        case 2 :
                    	            // Resources/LLVM/llvmGrammar.g:644:48: getelementptr_rhs[null, $e0.line]
                    	            {
                    	            pushFollow(FOLLOW_getelementptr_rhs_in_list_of_initial_values3155);
                    	            getelementptr_rhs194=getelementptr_rhs(null, (e0!=null?e0.getLine():0));

                    	            state._fsp--;
                    	            if (state.failed) return retval;
                    	            if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs194.getTree());

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    END_SQUARE_BR195=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_list_of_initial_values3161); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END_SQUARE_BR195_tree = (Object)adaptor.create(END_SQUARE_BR195);
                    adaptor.addChild(root_0, END_SQUARE_BR195_tree);
                    }

                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:645:11: ZEROINITIALIZER
                    {
                    root_0 = (Object)adaptor.nil();

                    ZEROINITIALIZER196=(Token)match(input,ZEROINITIALIZER,FOLLOW_ZEROINITIALIZER_in_list_of_initial_values3176); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ZEROINITIALIZER196_tree = (Object)adaptor.create(ZEROINITIALIZER196);
                    adaptor.addChild(root_0, ZEROINITIALIZER196_tree);
                    }

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:646:11: ID STRING_LITERAL
                    {
                    root_0 = (Object)adaptor.nil();

                    ID197=(Token)match(input,ID,FOLLOW_ID_in_list_of_initial_values3189); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID197_tree = (Object)adaptor.create(ID197);
                    adaptor.addChild(root_0, ID197_tree);
                    }
                    STRING_LITERAL198=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_list_of_initial_values3191); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL198_tree = (Object)adaptor.create(STRING_LITERAL198);
                    adaptor.addChild(root_0, STRING_LITERAL198_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 48, list_of_initial_values_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "list_of_initial_values"

    public static class array_initial_value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "array_initial_value"
    // Resources/LLVM/llvmGrammar.g:648:1: array_initial_value : ( ( NUMBER | STRING_LITERAL ) | list_of_initial_values );
    public final llvmGrammarParser.array_initial_value_return array_initial_value() throws RecognitionException {
        llvmGrammarParser.array_initial_value_return retval = new llvmGrammarParser.array_initial_value_return();
        retval.start = input.LT(1);
        int array_initial_value_StartIndex = input.index();
        Object root_0 = null;

        Token set199=null;
        llvmGrammarParser.list_of_initial_values_return list_of_initial_values200 = null;


        Object set199_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:648:21: ( ( NUMBER | STRING_LITERAL ) | list_of_initial_values )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==NUMBER||LA76_0==STRING_LITERAL) ) {
                alt76=1;
            }
            else if ( (LA76_0==ZEROINITIALIZER||LA76_0==ID||LA76_0==START_SQUARE_BR) ) {
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
                    // Resources/LLVM/llvmGrammar.g:648:23: ( NUMBER | STRING_LITERAL )
                    {
                    root_0 = (Object)adaptor.nil();

                    set199=(Token)input.LT(1);
                    if ( input.LA(1)==NUMBER||input.LA(1)==STRING_LITERAL ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set199));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:648:50: list_of_initial_values
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_list_of_initial_values_in_array_initial_value3210);
                    list_of_initial_values200=list_of_initial_values();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_of_initial_values200.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 49, array_initial_value_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "array_initial_value"

    public static class global_variable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_variable"
    // Resources/LLVM/llvmGrammar.g:650:1: global_variable : e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? typeSt= data_type (d= global_variable_initial_value COMMA ALIGN align= NUMBER )? ;
    public final llvmGrammarParser.global_variable_return global_variable() throws RecognitionException {
        llvmGrammarParser.global_variable_return retval = new llvmGrammarParser.global_variable_return();
        retval.start = input.LT(1);
        int global_variable_StartIndex = input.index();
        Object root_0 = null;

        Token e0=null;
        Token e1=null;
        Token e2=null;
        Token align=null;
        Token string_literal201=null;
        Token COMMA202=null;
        Token ALIGN203=null;
        llvmGrammarParser.result_return e = null;

        llvmGrammarParser.data_type_return typeSt = null;

        llvmGrammarParser.global_variable_initial_value_return d = null;


        Object e0_tree=null;
        Object e1_tree=null;
        Object e2_tree=null;
        Object align_tree=null;
        Object string_literal201_tree=null;
        Object COMMA202_tree=null;
        Object ALIGN203_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:650:17: (e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? typeSt= data_type (d= global_variable_initial_value COMMA ALIGN align= NUMBER )? )
            // Resources/LLVM/llvmGrammar.g:650:19: e= result e0= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? typeSt= data_type (d= global_variable_initial_value COMMA ALIGN align= NUMBER )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_global_variable3220);
            e=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            e0=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_global_variable3225); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e0_tree = (Object)adaptor.create(e0);
            adaptor.addChild(root_0, e0_tree);
            }
            // Resources/LLVM/llvmGrammar.g:650:38: (e1= LINKAGE_TYPE )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==LINKAGE_TYPE) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:650:39: e1= LINKAGE_TYPE
                    {
                    e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_global_variable3232); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e1_tree = (Object)adaptor.create(e1);
                    adaptor.addChild(root_0, e1_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:650:60: ( 'global' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==84) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:650:61: 'global'
                    {
                    string_literal201=(Token)match(input,84,FOLLOW_84_in_global_variable3238); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal201_tree = (Object)adaptor.create(string_literal201);
                    adaptor.addChild(root_0, string_literal201_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:650:72: (e2= 'constant' )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==86) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:650:73: e2= 'constant'
                    {
                    e2=(Token)match(input,86,FOLLOW_86_in_global_variable3245); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e2_tree = (Object)adaptor.create(e2);
                    adaptor.addChild(root_0, e2_tree);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_data_type_in_global_variable3259);
            typeSt=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSt.getTree());
            // Resources/LLVM/llvmGrammar.g:651:24: (d= global_variable_initial_value COMMA ALIGN align= NUMBER )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==NUMBER) ) {
                int LA80_1 = input.LA(2);

                if ( (LA80_1==COMMA) ) {
                    alt80=1;
                }
            }
            else if ( (LA80_0==CAST_TYPE||LA80_0==71) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:651:25: d= global_variable_initial_value COMMA ALIGN align= NUMBER
                    {
                    pushFollow(FOLLOW_global_variable_initial_value_in_global_variable3264);
                    d=global_variable_initial_value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
                    COMMA202=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_variable3266); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA202_tree = (Object)adaptor.create(COMMA202);
                    adaptor.addChild(root_0, COMMA202_tree);
                    }
                    ALIGN203=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_global_variable3268); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALIGN203_tree = (Object)adaptor.create(ALIGN203);
                    adaptor.addChild(root_0, ALIGN203_tree);
                    }
                    align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_global_variable3273); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    align_tree = (Object)adaptor.create(align);
                    adaptor.addChild(root_0, align_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
                 
                    				setGlobalVariable((e!=null?input.toString(e.start,e.stop):null), (e1!=null?e1.getText():null), (typeSt!=null?input.toString(typeSt.start,typeSt.stop):null), (d!=null?d.ini:null), (e2!=null?e2.getText():null), (align!=null?align.getText():null), null,
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
            if ( state.backtracking>0 ) { memoize(input, 50, global_variable_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_variable"

    public static class global_variable_initial_value_return extends ParserRuleReturnScope {
        public String ini;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_variable_initial_value"
    // Resources/LLVM/llvmGrammar.g:657:1: global_variable_initial_value returns [String ini] : (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] ) ;
    public final llvmGrammarParser.global_variable_initial_value_return global_variable_initial_value() throws RecognitionException {
        llvmGrammarParser.global_variable_initial_value_return retval = new llvmGrammarParser.global_variable_initial_value_return();
        retval.start = input.LT(1);
        int global_variable_initial_value_StartIndex = input.index();
        Object root_0 = null;

        Token initial=null;
        llvmGrammarParser.cast_instr_rhs_return cast_instr_rhs204 = null;

        llvmGrammarParser.getelementptr_rhs_return getelementptr_rhs205 = null;


        Object initial_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:657:52: ( (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] ) )
            // Resources/LLVM/llvmGrammar.g:657:54: (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:657:54: (initial= NUMBER | cast_instr_rhs[null, $initial.line] | getelementptr_rhs[null, $initial.line] )
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
            case 71:
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
                    // Resources/LLVM/llvmGrammar.g:657:55: initial= NUMBER
                    {
                    initial=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_global_variable_initial_value3308); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    initial_tree = (Object)adaptor.create(initial);
                    adaptor.addChild(root_0, initial_tree);
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:658:16: cast_instr_rhs[null, $initial.line]
                    {
                    pushFollow(FOLLOW_cast_instr_rhs_in_global_variable_initial_value3326);
                    cast_instr_rhs204=cast_instr_rhs(null, (initial!=null?initial.getLine():0));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cast_instr_rhs204.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:659:16: getelementptr_rhs[null, $initial.line]
                    {
                    pushFollow(FOLLOW_getelementptr_rhs_in_global_variable_initial_value3345);
                    getelementptr_rhs205=getelementptr_rhs(null, (initial!=null?initial.getLine():0));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, getelementptr_rhs205.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 51, global_variable_initial_value_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_variable_initial_value"

    public static class structure_obj_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structure_obj"
    // Resources/LLVM/llvmGrammar.g:664:1: structure_obj : e= result eq= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? LOCAL_PREFIX typeSt= ID (zeroinitial= ZEROINITIALIZER | init= struct_init_value ) COMMA ALIGN align= NUMBER ;
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
        Token string_literal206=null;
        Token LOCAL_PREFIX207=null;
        Token COMMA208=null;
        Token ALIGN209=null;
        llvmGrammarParser.result_return e = null;

        llvmGrammarParser.struct_init_value_return init = null;


        Object eq_tree=null;
        Object e1_tree=null;
        Object e2_tree=null;
        Object typeSt_tree=null;
        Object zeroinitial_tree=null;
        Object align_tree=null;
        Object string_literal206_tree=null;
        Object LOCAL_PREFIX207_tree=null;
        Object COMMA208_tree=null;
        Object ALIGN209_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:664:15: (e= result eq= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? LOCAL_PREFIX typeSt= ID (zeroinitial= ZEROINITIALIZER | init= struct_init_value ) COMMA ALIGN align= NUMBER )
            // Resources/LLVM/llvmGrammar.g:664:17: e= result eq= EQUAL (e1= LINKAGE_TYPE )? ( 'global' )? (e2= 'constant' )? LOCAL_PREFIX typeSt= ID (zeroinitial= ZEROINITIALIZER | init= struct_init_value ) COMMA ALIGN align= NUMBER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_result_in_structure_obj3376);
            e=result();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            eq=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_structure_obj3380); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            eq_tree = (Object)adaptor.create(eq);
            adaptor.addChild(root_0, eq_tree);
            }
            // Resources/LLVM/llvmGrammar.g:664:37: (e1= LINKAGE_TYPE )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==LINKAGE_TYPE) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e1= LINKAGE_TYPE
                    {
                    e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_structure_obj3384); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e1_tree = (Object)adaptor.create(e1);
                    adaptor.addChild(root_0, e1_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:664:52: ( 'global' )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==84) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: 'global'
                    {
                    string_literal206=(Token)match(input,84,FOLLOW_84_in_structure_obj3387); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal206_tree = (Object)adaptor.create(string_literal206);
                    adaptor.addChild(root_0, string_literal206_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:664:62: (e2= 'constant' )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==86) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:664:63: e2= 'constant'
                    {
                    e2=(Token)match(input,86,FOLLOW_86_in_structure_obj3393); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e2_tree = (Object)adaptor.create(e2);
                    adaptor.addChild(root_0, e2_tree);
                    }

                    }
                    break;

            }

            LOCAL_PREFIX207=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_structure_obj3397); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LOCAL_PREFIX207_tree = (Object)adaptor.create(LOCAL_PREFIX207);
            adaptor.addChild(root_0, LOCAL_PREFIX207_tree);
            }
            typeSt=(Token)match(input,ID,FOLLOW_ID_in_structure_obj3401); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            typeSt_tree = (Object)adaptor.create(typeSt);
            adaptor.addChild(root_0, typeSt_tree);
            }
            // Resources/LLVM/llvmGrammar.g:665:5: (zeroinitial= ZEROINITIALIZER | init= struct_init_value )
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
                    // Resources/LLVM/llvmGrammar.g:665:6: zeroinitial= ZEROINITIALIZER
                    {
                    zeroinitial=(Token)match(input,ZEROINITIALIZER,FOLLOW_ZEROINITIALIZER_in_structure_obj3412); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    zeroinitial_tree = (Object)adaptor.create(zeroinitial);
                    adaptor.addChild(root_0, zeroinitial_tree);
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:665:36: init= struct_init_value
                    {
                    pushFollow(FOLLOW_struct_init_value_in_structure_obj3419);
                    init=struct_init_value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, init.getTree());

                    }
                    break;

            }

            COMMA208=(Token)match(input,COMMA,FOLLOW_COMMA_in_structure_obj3422); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA208_tree = (Object)adaptor.create(COMMA208);
            adaptor.addChild(root_0, COMMA208_tree);
            }
            ALIGN209=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_structure_obj3424); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ALIGN209_tree = (Object)adaptor.create(ALIGN209);
            adaptor.addChild(root_0, ALIGN209_tree);
            }
            align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_structure_obj3428); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 52, structure_obj_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "structure_obj"

    public static class struct_init_value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "struct_init_value"
    // Resources/LLVM/llvmGrammar.g:671:1: struct_init_value : START_CURLY ( data_type | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )* END_CURLY ;
    public final llvmGrammarParser.struct_init_value_return struct_init_value() throws RecognitionException {
        llvmGrammarParser.struct_init_value_return retval = new llvmGrammarParser.struct_init_value_return();
        retval.start = input.LT(1);
        int struct_init_value_StartIndex = input.index();
        Object root_0 = null;

        Token START_CURLY210=null;
        Token LOCAL_PREFIX212=null;
        Token ID213=null;
        Token NUMBER214=null;
        Token LETTER215=null;
        Token ID216=null;
        Token STRING_LITERAL217=null;
        Token string_literal218=null;
        Token COMMA220=null;
        Token LOCAL_PREFIX222=null;
        Token ID223=null;
        Token NUMBER224=null;
        Token LETTER225=null;
        Token ID226=null;
        Token STRING_LITERAL227=null;
        Token string_literal228=null;
        Token END_CURLY230=null;
        llvmGrammarParser.data_type_return data_type211 = null;

        llvmGrammarParser.array_initial_value_return array_initial_value219 = null;

        llvmGrammarParser.data_type_return data_type221 = null;

        llvmGrammarParser.array_initial_value_return array_initial_value229 = null;


        Object START_CURLY210_tree=null;
        Object LOCAL_PREFIX212_tree=null;
        Object ID213_tree=null;
        Object NUMBER214_tree=null;
        Object LETTER215_tree=null;
        Object ID216_tree=null;
        Object STRING_LITERAL217_tree=null;
        Object string_literal218_tree=null;
        Object COMMA220_tree=null;
        Object LOCAL_PREFIX222_tree=null;
        Object ID223_tree=null;
        Object NUMBER224_tree=null;
        Object LETTER225_tree=null;
        Object ID226_tree=null;
        Object STRING_LITERAL227_tree=null;
        Object string_literal228_tree=null;
        Object END_CURLY230_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:671:19: ( START_CURLY ( data_type | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )* END_CURLY )
            // Resources/LLVM/llvmGrammar.g:671:21: START_CURLY ( data_type | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )* END_CURLY
            {
            root_0 = (Object)adaptor.nil();

            START_CURLY210=(Token)match(input,START_CURLY,FOLLOW_START_CURLY_in_struct_init_value3448); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            START_CURLY210_tree = (Object)adaptor.create(START_CURLY210);
            adaptor.addChild(root_0, START_CURLY210_tree);
            }
            // Resources/LLVM/llvmGrammar.g:671:33: ( data_type | ( LOCAL_PREFIX ID ) )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==PRIMITIVE_DATA_TYPE||LA86_0==START_PARANTHESIS||LA86_0==START_ANGULAR||LA86_0==START_SQUARE_BR) ) {
                alt86=1;
            }
            else if ( (LA86_0==LOCAL_PREFIX) ) {
                int LA86_2 = input.LA(2);

                if ( (LA86_2==ID) ) {
                    int LA86_3 = input.LA(3);

                    if ( (synpred123_llvmGrammar()) ) {
                        alt86=1;
                    }
                    else if ( (true) ) {
                        alt86=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 86, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 2, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:671:34: data_type
                    {
                    pushFollow(FOLLOW_data_type_in_struct_init_value3451);
                    data_type211=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type211.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:671:45: ( LOCAL_PREFIX ID )
                    {
                    // Resources/LLVM/llvmGrammar.g:671:45: ( LOCAL_PREFIX ID )
                    // Resources/LLVM/llvmGrammar.g:671:46: LOCAL_PREFIX ID
                    {
                    LOCAL_PREFIX212=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_init_value3455); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX212_tree = (Object)adaptor.create(LOCAL_PREFIX212);
                    adaptor.addChild(root_0, LOCAL_PREFIX212_tree);
                    }
                    ID213=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3457); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID213_tree = (Object)adaptor.create(ID213);
                    adaptor.addChild(root_0, ID213_tree);
                    }

                    }


                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:672:6: ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value )
            int alt87=5;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                int LA87_1 = input.LA(2);

                if ( (synpred124_llvmGrammar()) ) {
                    alt87=1;
                }
                else if ( (true) ) {
                    alt87=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 87, 1, input);

                    throw nvae;
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

                    if ( (synpred126_llvmGrammar()) ) {
                        alt87=3;
                    }
                    else if ( (true) ) {
                        alt87=5;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 87, 7, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 87, 3, input);

                    throw nvae;
                }
                }
                break;
            case 73:
                {
                alt87=4;
                }
                break;
            case ZEROINITIALIZER:
            case START_SQUARE_BR:
            case STRING_LITERAL:
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
                    // Resources/LLVM/llvmGrammar.g:672:7: NUMBER
                    {
                    NUMBER214=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_struct_init_value3467); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER214_tree = (Object)adaptor.create(NUMBER214);
                    adaptor.addChild(root_0, NUMBER214_tree);
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:672:15: LETTER
                    {
                    LETTER215=(Token)match(input,LETTER,FOLLOW_LETTER_in_struct_init_value3470); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LETTER215_tree = (Object)adaptor.create(LETTER215);
                    adaptor.addChild(root_0, LETTER215_tree);
                    }

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:672:23: ID STRING_LITERAL
                    {
                    ID216=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3473); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID216_tree = (Object)adaptor.create(ID216);
                    adaptor.addChild(root_0, ID216_tree);
                    }
                    STRING_LITERAL217=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_struct_init_value3475); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL217_tree = (Object)adaptor.create(STRING_LITERAL217);
                    adaptor.addChild(root_0, STRING_LITERAL217_tree);
                    }

                    }
                    break;
                case 4 :
                    // Resources/LLVM/llvmGrammar.g:672:42: 'undef'
                    {
                    string_literal218=(Token)match(input,73,FOLLOW_73_in_struct_init_value3478); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal218_tree = (Object)adaptor.create(string_literal218);
                    adaptor.addChild(root_0, string_literal218_tree);
                    }

                    }
                    break;
                case 5 :
                    // Resources/LLVM/llvmGrammar.g:672:51: array_initial_value
                    {
                    pushFollow(FOLLOW_array_initial_value_in_struct_init_value3481);
                    array_initial_value219=array_initial_value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value219.getTree());

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:673:6: ( COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value ) )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==COMMA) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:673:7: COMMA ( ( data_type ) | ( LOCAL_PREFIX ID ) ) ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value )
            	    {
            	    COMMA220=(Token)match(input,COMMA,FOLLOW_COMMA_in_struct_init_value3490); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA220_tree = (Object)adaptor.create(COMMA220);
            	    adaptor.addChild(root_0, COMMA220_tree);
            	    }
            	    // Resources/LLVM/llvmGrammar.g:673:13: ( ( data_type ) | ( LOCAL_PREFIX ID ) )
            	    int alt88=2;
            	    int LA88_0 = input.LA(1);

            	    if ( (LA88_0==PRIMITIVE_DATA_TYPE||LA88_0==START_PARANTHESIS||LA88_0==START_ANGULAR||LA88_0==START_SQUARE_BR) ) {
            	        alt88=1;
            	    }
            	    else if ( (LA88_0==LOCAL_PREFIX) ) {
            	        int LA88_2 = input.LA(2);

            	        if ( (LA88_2==ID) ) {
            	            int LA88_3 = input.LA(3);

            	            if ( (synpred128_llvmGrammar()) ) {
            	                alt88=1;
            	            }
            	            else if ( (true) ) {
            	                alt88=2;
            	            }
            	            else {
            	                if (state.backtracking>0) {state.failed=true; return retval;}
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 88, 3, input);

            	                throw nvae;
            	            }
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 88, 2, input);

            	            throw nvae;
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
            	            // Resources/LLVM/llvmGrammar.g:673:14: ( data_type )
            	            {
            	            // Resources/LLVM/llvmGrammar.g:673:14: ( data_type )
            	            // Resources/LLVM/llvmGrammar.g:673:15: data_type
            	            {
            	            pushFollow(FOLLOW_data_type_in_struct_init_value3494);
            	            data_type221=data_type();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type221.getTree());

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // Resources/LLVM/llvmGrammar.g:673:26: ( LOCAL_PREFIX ID )
            	            {
            	            // Resources/LLVM/llvmGrammar.g:673:26: ( LOCAL_PREFIX ID )
            	            // Resources/LLVM/llvmGrammar.g:673:27: LOCAL_PREFIX ID
            	            {
            	            LOCAL_PREFIX222=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_init_value3498); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            LOCAL_PREFIX222_tree = (Object)adaptor.create(LOCAL_PREFIX222);
            	            adaptor.addChild(root_0, LOCAL_PREFIX222_tree);
            	            }
            	            ID223=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3500); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            ID223_tree = (Object)adaptor.create(ID223);
            	            adaptor.addChild(root_0, ID223_tree);
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // Resources/LLVM/llvmGrammar.g:673:44: ( NUMBER | LETTER | ID STRING_LITERAL | 'undef' | array_initial_value )
            	    int alt89=5;
            	    switch ( input.LA(1) ) {
            	    case NUMBER:
            	        {
            	        int LA89_1 = input.LA(2);

            	        if ( (synpred129_llvmGrammar()) ) {
            	            alt89=1;
            	        }
            	        else if ( (true) ) {
            	            alt89=5;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 89, 1, input);

            	            throw nvae;
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

            	            if ( (synpred131_llvmGrammar()) ) {
            	                alt89=3;
            	            }
            	            else if ( (true) ) {
            	                alt89=5;
            	            }
            	            else {
            	                if (state.backtracking>0) {state.failed=true; return retval;}
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 89, 7, input);

            	                throw nvae;
            	            }
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 89, 3, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case 73:
            	        {
            	        alt89=4;
            	        }
            	        break;
            	    case ZEROINITIALIZER:
            	    case START_SQUARE_BR:
            	    case STRING_LITERAL:
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
            	            // Resources/LLVM/llvmGrammar.g:673:46: NUMBER
            	            {
            	            NUMBER224=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_struct_init_value3505); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            NUMBER224_tree = (Object)adaptor.create(NUMBER224);
            	            adaptor.addChild(root_0, NUMBER224_tree);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Resources/LLVM/llvmGrammar.g:673:53: LETTER
            	            {
            	            LETTER225=(Token)match(input,LETTER,FOLLOW_LETTER_in_struct_init_value3507); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            LETTER225_tree = (Object)adaptor.create(LETTER225);
            	            adaptor.addChild(root_0, LETTER225_tree);
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // Resources/LLVM/llvmGrammar.g:673:60: ID STRING_LITERAL
            	            {
            	            ID226=(Token)match(input,ID,FOLLOW_ID_in_struct_init_value3509); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            ID226_tree = (Object)adaptor.create(ID226);
            	            adaptor.addChild(root_0, ID226_tree);
            	            }
            	            STRING_LITERAL227=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_struct_init_value3511); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            STRING_LITERAL227_tree = (Object)adaptor.create(STRING_LITERAL227);
            	            adaptor.addChild(root_0, STRING_LITERAL227_tree);
            	            }

            	            }
            	            break;
            	        case 4 :
            	            // Resources/LLVM/llvmGrammar.g:673:78: 'undef'
            	            {
            	            string_literal228=(Token)match(input,73,FOLLOW_73_in_struct_init_value3513); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal228_tree = (Object)adaptor.create(string_literal228);
            	            adaptor.addChild(root_0, string_literal228_tree);
            	            }

            	            }
            	            break;
            	        case 5 :
            	            // Resources/LLVM/llvmGrammar.g:673:86: array_initial_value
            	            {
            	            pushFollow(FOLLOW_array_initial_value_in_struct_init_value3515);
            	            array_initial_value229=array_initial_value();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, array_initial_value229.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            END_CURLY230=(Token)match(input,END_CURLY,FOLLOW_END_CURLY_in_struct_init_value3520); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_CURLY230_tree = (Object)adaptor.create(END_CURLY230);
            adaptor.addChild(root_0, END_CURLY230_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 53, struct_init_value_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "struct_init_value"

    public static class string_constant_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string_constant"
    // Resources/LLVM/llvmGrammar.g:675:1: string_constant : e= string_name eq= EQUAL (e1= LINKAGE_TYPE )? unnamed= 'unnamed_addr' e2= 'constant' typeSt= data_type ID initial= STRING_LITERAL ( COMMA ALIGN align= NUMBER )? ;
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
        Token ID231=null;
        Token COMMA232=null;
        Token ALIGN233=null;
        llvmGrammarParser.string_name_return e = null;

        llvmGrammarParser.data_type_return typeSt = null;


        Object eq_tree=null;
        Object e1_tree=null;
        Object unnamed_tree=null;
        Object e2_tree=null;
        Object initial_tree=null;
        Object align_tree=null;
        Object ID231_tree=null;
        Object COMMA232_tree=null;
        Object ALIGN233_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:675:17: (e= string_name eq= EQUAL (e1= LINKAGE_TYPE )? unnamed= 'unnamed_addr' e2= 'constant' typeSt= data_type ID initial= STRING_LITERAL ( COMMA ALIGN align= NUMBER )? )
            // Resources/LLVM/llvmGrammar.g:675:20: e= string_name eq= EQUAL (e1= LINKAGE_TYPE )? unnamed= 'unnamed_addr' e2= 'constant' typeSt= data_type ID initial= STRING_LITERAL ( COMMA ALIGN align= NUMBER )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_string_name_in_string_constant3534);
            e=string_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            eq=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_string_constant3538); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            eq_tree = (Object)adaptor.create(eq);
            adaptor.addChild(root_0, eq_tree);
            }
            // Resources/LLVM/llvmGrammar.g:675:45: (e1= LINKAGE_TYPE )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==LINKAGE_TYPE) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e1= LINKAGE_TYPE
                    {
                    e1=(Token)match(input,LINKAGE_TYPE,FOLLOW_LINKAGE_TYPE_in_string_constant3542); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e1_tree = (Object)adaptor.create(e1);
                    adaptor.addChild(root_0, e1_tree);
                    }

                    }
                    break;

            }

            unnamed=(Token)match(input,85,FOLLOW_85_in_string_constant3547); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            unnamed_tree = (Object)adaptor.create(unnamed);
            adaptor.addChild(root_0, unnamed_tree);
            }
            e2=(Token)match(input,86,FOLLOW_86_in_string_constant3551); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e2_tree = (Object)adaptor.create(e2);
            adaptor.addChild(root_0, e2_tree);
            }
            pushFollow(FOLLOW_data_type_in_string_constant3555);
            typeSt=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSt.getTree());
            ID231=(Token)match(input,ID,FOLLOW_ID_in_string_constant3557); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID231_tree = (Object)adaptor.create(ID231);
            adaptor.addChild(root_0, ID231_tree);
            }
            initial=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string_constant3569); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            initial_tree = (Object)adaptor.create(initial);
            adaptor.addChild(root_0, initial_tree);
            }
            // Resources/LLVM/llvmGrammar.g:676:32: ( COMMA ALIGN align= NUMBER )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==COMMA) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:676:33: COMMA ALIGN align= NUMBER
                    {
                    COMMA232=(Token)match(input,COMMA,FOLLOW_COMMA_in_string_constant3573); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA232_tree = (Object)adaptor.create(COMMA232);
                    adaptor.addChild(root_0, COMMA232_tree);
                    }
                    ALIGN233=(Token)match(input,ALIGN,FOLLOW_ALIGN_in_string_constant3575); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALIGN233_tree = (Object)adaptor.create(ALIGN233);
                    adaptor.addChild(root_0, ALIGN233_tree);
                    }
                    align=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_string_constant3579); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 54, string_constant_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "string_constant"

    public static class string_name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string_name"
    // Resources/LLVM/llvmGrammar.g:682:1: string_name : '@' ( '.' )? ID ( NUMBER )? ( '.' ID )? ;
    public final llvmGrammarParser.string_name_return string_name() throws RecognitionException {
        llvmGrammarParser.string_name_return retval = new llvmGrammarParser.string_name_return();
        retval.start = input.LT(1);
        int string_name_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal234=null;
        Token char_literal235=null;
        Token ID236=null;
        Token NUMBER237=null;
        Token char_literal238=null;
        Token ID239=null;

        Object char_literal234_tree=null;
        Object char_literal235_tree=null;
        Object ID236_tree=null;
        Object NUMBER237_tree=null;
        Object char_literal238_tree=null;
        Object ID239_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:682:13: ( '@' ( '.' )? ID ( NUMBER )? ( '.' ID )? )
            // Resources/LLVM/llvmGrammar.g:682:15: '@' ( '.' )? ID ( NUMBER )? ( '.' ID )?
            {
            root_0 = (Object)adaptor.nil();

            char_literal234=(Token)match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_string_name3615); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal234_tree = (Object)adaptor.create(char_literal234);
            adaptor.addChild(root_0, char_literal234_tree);
            }
            // Resources/LLVM/llvmGrammar.g:682:19: ( '.' )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==DOT) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: '.'
                    {
                    char_literal235=(Token)match(input,DOT,FOLLOW_DOT_in_string_name3617); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal235_tree = (Object)adaptor.create(char_literal235);
                    adaptor.addChild(root_0, char_literal235_tree);
                    }

                    }
                    break;

            }

            ID236=(Token)match(input,ID,FOLLOW_ID_in_string_name3619); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID236_tree = (Object)adaptor.create(ID236);
            adaptor.addChild(root_0, ID236_tree);
            }
            // Resources/LLVM/llvmGrammar.g:682:26: ( NUMBER )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==NUMBER) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: NUMBER
                    {
                    NUMBER237=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_string_name3621); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER237_tree = (Object)adaptor.create(NUMBER237);
                    adaptor.addChild(root_0, NUMBER237_tree);
                    }

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:682:34: ( '.' ID )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==DOT) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:682:35: '.' ID
                    {
                    char_literal238=(Token)match(input,DOT,FOLLOW_DOT_in_string_name3625); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal238_tree = (Object)adaptor.create(char_literal238);
                    adaptor.addChild(root_0, char_literal238_tree);
                    }
                    ID239=(Token)match(input,ID,FOLLOW_ID_in_string_name3626); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID239_tree = (Object)adaptor.create(ID239);
                    adaptor.addChild(root_0, ID239_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 55, string_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "string_name"

    public static class structure_decl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structure_decl"
    // Resources/LLVM/llvmGrammar.g:684:1: structure_decl : LOCAL_PREFIX name1= ID EQUAL 'type {' selement= struct_element END_CURLY ;
    public final llvmGrammarParser.structure_decl_return structure_decl() throws RecognitionException {
        llvmGrammarParser.structure_decl_return retval = new llvmGrammarParser.structure_decl_return();
        retval.start = input.LT(1);
        int structure_decl_StartIndex = input.index();
        Object root_0 = null;

        Token name1=null;
        Token LOCAL_PREFIX240=null;
        Token EQUAL241=null;
        Token string_literal242=null;
        Token END_CURLY243=null;
        llvmGrammarParser.struct_element_return selement = null;


        Object name1_tree=null;
        Object LOCAL_PREFIX240_tree=null;
        Object EQUAL241_tree=null;
        Object string_literal242_tree=null;
        Object END_CURLY243_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:684:16: ( LOCAL_PREFIX name1= ID EQUAL 'type {' selement= struct_element END_CURLY )
            // Resources/LLVM/llvmGrammar.g:684:18: LOCAL_PREFIX name1= ID EQUAL 'type {' selement= struct_element END_CURLY
            {
            root_0 = (Object)adaptor.nil();

            LOCAL_PREFIX240=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_structure_decl3637); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LOCAL_PREFIX240_tree = (Object)adaptor.create(LOCAL_PREFIX240);
            adaptor.addChild(root_0, LOCAL_PREFIX240_tree);
            }
            name1=(Token)match(input,ID,FOLLOW_ID_in_structure_decl3641); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name1_tree = (Object)adaptor.create(name1);
            adaptor.addChild(root_0, name1_tree);
            }
            EQUAL241=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_structure_decl3643); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EQUAL241_tree = (Object)adaptor.create(EQUAL241);
            adaptor.addChild(root_0, EQUAL241_tree);
            }
            string_literal242=(Token)match(input,87,FOLLOW_87_in_structure_decl3645); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal242_tree = (Object)adaptor.create(string_literal242);
            adaptor.addChild(root_0, string_literal242_tree);
            }
            pushFollow(FOLLOW_struct_element_in_structure_decl3649);
            selement=struct_element();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, selement.getTree());
            END_CURLY243=(Token)match(input,END_CURLY,FOLLOW_END_CURLY_in_structure_decl3651); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_CURLY243_tree = (Object)adaptor.create(END_CURLY243);
            adaptor.addChild(root_0, END_CURLY243_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 56, structure_decl_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "structure_decl"

    public static class struct_element_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "struct_element"
    // Resources/LLVM/llvmGrammar.g:689:1: struct_element : ( data_type | ( LOCAL_PREFIX struct_name ) ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )* ;
    public final llvmGrammarParser.struct_element_return struct_element() throws RecognitionException {
        llvmGrammarParser.struct_element_return retval = new llvmGrammarParser.struct_element_return();
        retval.start = input.LT(1);
        int struct_element_StartIndex = input.index();
        Object root_0 = null;

        Token LOCAL_PREFIX245=null;
        Token COMMA247=null;
        Token LOCAL_PREFIX249=null;
        llvmGrammarParser.data_type_return data_type244 = null;

        llvmGrammarParser.struct_name_return struct_name246 = null;

        llvmGrammarParser.data_type_return data_type248 = null;

        llvmGrammarParser.struct_name_return struct_name250 = null;


        Object LOCAL_PREFIX245_tree=null;
        Object COMMA247_tree=null;
        Object LOCAL_PREFIX249_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:689:16: ( ( data_type | ( LOCAL_PREFIX struct_name ) ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )* )
            // Resources/LLVM/llvmGrammar.g:689:18: ( data_type | ( LOCAL_PREFIX struct_name ) ) ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )*
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:689:18: ( data_type | ( LOCAL_PREFIX struct_name ) )
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==PRIMITIVE_DATA_TYPE||LA96_0==START_PARANTHESIS||LA96_0==START_ANGULAR||LA96_0==START_SQUARE_BR) ) {
                alt96=1;
            }
            else if ( (LA96_0==LOCAL_PREFIX) ) {
                int LA96_2 = input.LA(2);

                if ( (LA96_2==ID) ) {
                    int LA96_3 = input.LA(3);

                    if ( (LA96_3==DOT) ) {
                        alt96=2;
                    }
                    else if ( ((LA96_3>=COMMA && LA96_3<=STAR)||LA96_3==END_CURLY) ) {
                        alt96=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 96, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 96, 2, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:689:19: data_type
                    {
                    pushFollow(FOLLOW_data_type_in_struct_element3674);
                    data_type244=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type244.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:689:30: ( LOCAL_PREFIX struct_name )
                    {
                    // Resources/LLVM/llvmGrammar.g:689:30: ( LOCAL_PREFIX struct_name )
                    // Resources/LLVM/llvmGrammar.g:689:31: LOCAL_PREFIX struct_name
                    {
                    LOCAL_PREFIX245=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_element3678); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOCAL_PREFIX245_tree = (Object)adaptor.create(LOCAL_PREFIX245);
                    adaptor.addChild(root_0, LOCAL_PREFIX245_tree);
                    }
                    pushFollow(FOLLOW_struct_name_in_struct_element3680);
                    struct_name246=struct_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, struct_name246.getTree());

                    }


                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:689:58: ( COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==COMMA) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:689:59: COMMA ( ( data_type ) | ( LOCAL_PREFIX struct_name ) )
            	    {
            	    COMMA247=(Token)match(input,COMMA,FOLLOW_COMMA_in_struct_element3685); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA247_tree = (Object)adaptor.create(COMMA247);
            	    adaptor.addChild(root_0, COMMA247_tree);
            	    }
            	    // Resources/LLVM/llvmGrammar.g:689:65: ( ( data_type ) | ( LOCAL_PREFIX struct_name ) )
            	    int alt97=2;
            	    int LA97_0 = input.LA(1);

            	    if ( (LA97_0==PRIMITIVE_DATA_TYPE||LA97_0==START_PARANTHESIS||LA97_0==START_ANGULAR||LA97_0==START_SQUARE_BR) ) {
            	        alt97=1;
            	    }
            	    else if ( (LA97_0==LOCAL_PREFIX) ) {
            	        int LA97_2 = input.LA(2);

            	        if ( (LA97_2==ID) ) {
            	            int LA97_3 = input.LA(3);

            	            if ( (LA97_3==DOT) ) {
            	                alt97=2;
            	            }
            	            else if ( ((LA97_3>=COMMA && LA97_3<=STAR)||LA97_3==END_CURLY) ) {
            	                alt97=1;
            	            }
            	            else {
            	                if (state.backtracking>0) {state.failed=true; return retval;}
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 97, 3, input);

            	                throw nvae;
            	            }
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 97, 2, input);

            	            throw nvae;
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
            	            // Resources/LLVM/llvmGrammar.g:689:66: ( data_type )
            	            {
            	            // Resources/LLVM/llvmGrammar.g:689:66: ( data_type )
            	            // Resources/LLVM/llvmGrammar.g:689:67: data_type
            	            {
            	            pushFollow(FOLLOW_data_type_in_struct_element3689);
            	            data_type248=data_type();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type248.getTree());

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // Resources/LLVM/llvmGrammar.g:689:78: ( LOCAL_PREFIX struct_name )
            	            {
            	            // Resources/LLVM/llvmGrammar.g:689:78: ( LOCAL_PREFIX struct_name )
            	            // Resources/LLVM/llvmGrammar.g:689:79: LOCAL_PREFIX struct_name
            	            {
            	            LOCAL_PREFIX249=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_element3693); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            LOCAL_PREFIX249_tree = (Object)adaptor.create(LOCAL_PREFIX249);
            	            adaptor.addChild(root_0, LOCAL_PREFIX249_tree);
            	            }
            	            pushFollow(FOLLOW_struct_name_in_struct_element3695);
            	            struct_name250=struct_name();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, struct_name250.getTree());

            	            }


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 57, struct_element_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "struct_element"

    public static class struct_name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "struct_name"
    // Resources/LLVM/llvmGrammar.g:691:1: struct_name : ID DOT ID ;
    public final llvmGrammarParser.struct_name_return struct_name() throws RecognitionException {
        llvmGrammarParser.struct_name_return retval = new llvmGrammarParser.struct_name_return();
        retval.start = input.LT(1);
        int struct_name_StartIndex = input.index();
        Object root_0 = null;

        Token ID251=null;
        Token DOT252=null;
        Token ID253=null;

        Object ID251_tree=null;
        Object DOT252_tree=null;
        Object ID253_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:691:13: ( ID DOT ID )
            // Resources/LLVM/llvmGrammar.g:691:16: ID DOT ID
            {
            root_0 = (Object)adaptor.nil();

            ID251=(Token)match(input,ID,FOLLOW_ID_in_struct_name3708); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID251_tree = (Object)adaptor.create(ID251);
            adaptor.addChild(root_0, ID251_tree);
            }
            DOT252=(Token)match(input,DOT,FOLLOW_DOT_in_struct_name3710); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DOT252_tree = (Object)adaptor.create(DOT252);
            adaptor.addChild(root_0, DOT252_tree);
            }
            ID253=(Token)match(input,ID,FOLLOW_ID_in_struct_name3712); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID253_tree = (Object)adaptor.create(ID253);
            adaptor.addChild(root_0, ID253_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 58, struct_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "struct_name"

    public static class result_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "result"
    // Resources/LLVM/llvmGrammar.g:693:1: result : ( ( prefix )? ( NUMBER | ID | FLOATING_LITERAL ) | truefalse | 'undef' );
    public final llvmGrammarParser.result_return result() throws RecognitionException {
        llvmGrammarParser.result_return retval = new llvmGrammarParser.result_return();
        retval.start = input.LT(1);
        int result_StartIndex = input.index();
        Object root_0 = null;

        Token set255=null;
        Token string_literal257=null;
        llvmGrammarParser.prefix_return prefix254 = null;

        llvmGrammarParser.truefalse_return truefalse256 = null;


        Object set255_tree=null;
        Object string_literal257_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:693:8: ( ( prefix )? ( NUMBER | ID | FLOATING_LITERAL ) | truefalse | 'undef' )
            int alt100=3;
            switch ( input.LA(1) ) {
            case ID:
            case GLOBAL_PREFIX:
            case LOCAL_PREFIX:
            case NUMBER:
            case FLOATING_LITERAL:
                {
                alt100=1;
                }
                break;
            case TRUE:
            case FALSE:
                {
                alt100=2;
                }
                break;
            case 73:
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
                    // Resources/LLVM/llvmGrammar.g:693:10: ( prefix )? ( NUMBER | ID | FLOATING_LITERAL )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:693:10: ( prefix )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( ((LA99_0>=GLOBAL_PREFIX && LA99_0<=LOCAL_PREFIX)) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // Resources/LLVM/llvmGrammar.g:0:0: prefix
                            {
                            pushFollow(FOLLOW_prefix_in_result3721);
                            prefix254=prefix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix254.getTree());

                            }
                            break;

                    }

                    set255=(Token)input.LT(1);
                    if ( input.LA(1)==ID||input.LA(1)==NUMBER||input.LA(1)==FLOATING_LITERAL ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set255));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:693:53: truefalse
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_truefalse_in_result3738);
                    truefalse256=truefalse();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, truefalse256.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:693:65: 'undef'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal257=(Token)match(input,73,FOLLOW_73_in_result3742); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal257_tree = (Object)adaptor.create(string_literal257);
                    adaptor.addChild(root_0, string_literal257_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 59, result_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "result"

    public static class truefalse_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "truefalse"
    // Resources/LLVM/llvmGrammar.g:695:1: truefalse : ( TRUE | FALSE );
    public final llvmGrammarParser.truefalse_return truefalse() throws RecognitionException {
        llvmGrammarParser.truefalse_return retval = new llvmGrammarParser.truefalse_return();
        retval.start = input.LT(1);
        int truefalse_StartIndex = input.index();
        Object root_0 = null;

        Token set258=null;

        Object set258_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:695:11: ( TRUE | FALSE )
            // Resources/LLVM/llvmGrammar.g:
            {
            root_0 = (Object)adaptor.nil();

            set258=(Token)input.LT(1);
            if ( (input.LA(1)>=TRUE && input.LA(1)<=FALSE) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set258));
                state.errorRecovery=false;state.failed=false;
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
            if ( state.backtracking>0 ) { memoize(input, 60, truefalse_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "truefalse"

    public static class data_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "data_type"
    // Resources/LLVM/llvmGrammar.g:697:1: data_type : ( ptr_type | e= PRIMITIVE_DATA_TYPE | derived_data_type ) ;
    public final llvmGrammarParser.data_type_return data_type() throws RecognitionException {
        llvmGrammarParser.data_type_return retval = new llvmGrammarParser.data_type_return();
        retval.start = input.LT(1);
        int data_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        llvmGrammarParser.ptr_type_return ptr_type259 = null;

        llvmGrammarParser.derived_data_type_return derived_data_type260 = null;


        Object e_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:697:11: ( ( ptr_type | e= PRIMITIVE_DATA_TYPE | derived_data_type ) )
            // Resources/LLVM/llvmGrammar.g:697:13: ( ptr_type | e= PRIMITIVE_DATA_TYPE | derived_data_type )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:697:13: ( ptr_type | e= PRIMITIVE_DATA_TYPE | derived_data_type )
            int alt101=3;
            switch ( input.LA(1) ) {
            case PRIMITIVE_DATA_TYPE:
                {
                int LA101_1 = input.LA(2);

                if ( (synpred148_llvmGrammar()) ) {
                    alt101=1;
                }
                else if ( (synpred149_llvmGrammar()) ) {
                    alt101=2;
                }
                else if ( (true) ) {
                    alt101=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 1, input);

                    throw nvae;
                }
                }
                break;
            case START_SQUARE_BR:
                {
                int LA101_2 = input.LA(2);

                if ( (synpred148_llvmGrammar()) ) {
                    alt101=1;
                }
                else if ( (true) ) {
                    alt101=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 2, input);

                    throw nvae;
                }
                }
                break;
            case LOCAL_PREFIX:
                {
                int LA101_3 = input.LA(2);

                if ( (synpred148_llvmGrammar()) ) {
                    alt101=1;
                }
                else if ( (true) ) {
                    alt101=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 3, input);

                    throw nvae;
                }
                }
                break;
            case START_ANGULAR:
                {
                int LA101_4 = input.LA(2);

                if ( (synpred148_llvmGrammar()) ) {
                    alt101=1;
                }
                else if ( (true) ) {
                    alt101=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 4, input);

                    throw nvae;
                }
                }
                break;
            case START_PARANTHESIS:
                {
                int LA101_5 = input.LA(2);

                if ( (synpred148_llvmGrammar()) ) {
                    alt101=1;
                }
                else if ( (true) ) {
                    alt101=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 5, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:697:14: ptr_type
                    {
                    pushFollow(FOLLOW_ptr_type_in_data_type3761);
                    ptr_type259=ptr_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr_type259.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:698:6: e= PRIMITIVE_DATA_TYPE
                    {
                    e=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_data_type3771); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e_tree = (Object)adaptor.create(e);
                    adaptor.addChild(root_0, e_tree);
                    }
                    if ( state.backtracking==0 ) {
                      updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:699:6: derived_data_type
                    {
                    pushFollow(FOLLOW_derived_data_type_in_data_type3780);
                    derived_data_type260=derived_data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, derived_data_type260.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 61, data_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "data_type"

    public static class derived_data_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "derived_data_type"
    // Resources/LLVM/llvmGrammar.g:702:1: derived_data_type : ( agr_type | func_type ) ;
    public final llvmGrammarParser.derived_data_type_return derived_data_type() throws RecognitionException {
        llvmGrammarParser.derived_data_type_return retval = new llvmGrammarParser.derived_data_type_return();
        retval.start = input.LT(1);
        int derived_data_type_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.agr_type_return agr_type261 = null;

        llvmGrammarParser.func_type_return func_type262 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:702:19: ( ( agr_type | func_type ) )
            // Resources/LLVM/llvmGrammar.g:702:21: ( agr_type | func_type )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:702:21: ( agr_type | func_type )
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
                    // Resources/LLVM/llvmGrammar.g:702:22: agr_type
                    {
                    pushFollow(FOLLOW_agr_type_in_derived_data_type3797);
                    agr_type261=agr_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, agr_type261.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:702:33: func_type
                    {
                    pushFollow(FOLLOW_func_type_in_derived_data_type3801);
                    func_type262=func_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, func_type262.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 62, derived_data_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "derived_data_type"

    public static class agr_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "agr_type"
    // Resources/LLVM/llvmGrammar.g:704:1: agr_type : ( multiDim_array_type | struct_type | multiDim_vector_type ) ;
    public final llvmGrammarParser.agr_type_return agr_type() throws RecognitionException {
        llvmGrammarParser.agr_type_return retval = new llvmGrammarParser.agr_type_return();
        retval.start = input.LT(1);
        int agr_type_StartIndex = input.index();
        Object root_0 = null;

        llvmGrammarParser.multiDim_array_type_return multiDim_array_type263 = null;

        llvmGrammarParser.struct_type_return struct_type264 = null;

        llvmGrammarParser.multiDim_vector_type_return multiDim_vector_type265 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:704:10: ( ( multiDim_array_type | struct_type | multiDim_vector_type ) )
            // Resources/LLVM/llvmGrammar.g:704:12: ( multiDim_array_type | struct_type | multiDim_vector_type )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:704:12: ( multiDim_array_type | struct_type | multiDim_vector_type )
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
                    // Resources/LLVM/llvmGrammar.g:704:13: multiDim_array_type
                    {
                    pushFollow(FOLLOW_multiDim_array_type_in_agr_type3811);
                    multiDim_array_type263=multiDim_array_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiDim_array_type263.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:704:35: struct_type
                    {
                    pushFollow(FOLLOW_struct_type_in_agr_type3815);
                    struct_type264=struct_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, struct_type264.getTree());

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:704:49: multiDim_vector_type
                    {
                    pushFollow(FOLLOW_multiDim_vector_type_in_agr_type3819);
                    multiDim_vector_type265=multiDim_vector_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiDim_vector_type265.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 63, agr_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "agr_type"

    public static class multiDim_vector_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiDim_vector_type"
    // Resources/LLVM/llvmGrammar.g:706:1: multiDim_vector_type : e= START_ANGULAR NUMBER MUL_OPERATOR vector_type END_ANGULAR ;
    public final llvmGrammarParser.multiDim_vector_type_return multiDim_vector_type() throws RecognitionException {
        llvmGrammarParser.multiDim_vector_type_return retval = new llvmGrammarParser.multiDim_vector_type_return();
        retval.start = input.LT(1);
        int multiDim_vector_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token NUMBER266=null;
        Token MUL_OPERATOR267=null;
        Token END_ANGULAR269=null;
        llvmGrammarParser.vector_type_return vector_type268 = null;


        Object e_tree=null;
        Object NUMBER266_tree=null;
        Object MUL_OPERATOR267_tree=null;
        Object END_ANGULAR269_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:706:22: (e= START_ANGULAR NUMBER MUL_OPERATOR vector_type END_ANGULAR )
            // Resources/LLVM/llvmGrammar.g:706:24: e= START_ANGULAR NUMBER MUL_OPERATOR vector_type END_ANGULAR
            {
            root_0 = (Object)adaptor.nil();

            e=(Token)match(input,START_ANGULAR,FOLLOW_START_ANGULAR_in_multiDim_vector_type3830); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            if ( state.backtracking==0 ) {
              updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
            }
            NUMBER266=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_multiDim_vector_type3834); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER266_tree = (Object)adaptor.create(NUMBER266);
            adaptor.addChild(root_0, NUMBER266_tree);
            }
            MUL_OPERATOR267=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_multiDim_vector_type3836); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MUL_OPERATOR267_tree = (Object)adaptor.create(MUL_OPERATOR267);
            adaptor.addChild(root_0, MUL_OPERATOR267_tree);
            }
            pushFollow(FOLLOW_vector_type_in_multiDim_vector_type3838);
            vector_type268=vector_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, vector_type268.getTree());
            END_ANGULAR269=(Token)match(input,END_ANGULAR,FOLLOW_END_ANGULAR_in_multiDim_vector_type3840); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_ANGULAR269_tree = (Object)adaptor.create(END_ANGULAR269);
            adaptor.addChild(root_0, END_ANGULAR269_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 64, multiDim_vector_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "multiDim_vector_type"

    public static class vector_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "vector_type"
    // Resources/LLVM/llvmGrammar.g:708:1: vector_type : e= START_ANGULAR NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR ;
    public final llvmGrammarParser.vector_type_return vector_type() throws RecognitionException {
        llvmGrammarParser.vector_type_return retval = new llvmGrammarParser.vector_type_return();
        retval.start = input.LT(1);
        int vector_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token NUMBER270=null;
        Token MUL_OPERATOR271=null;
        Token PRIMITIVE_DATA_TYPE272=null;
        Token END_ANGULAR273=null;

        Object e_tree=null;
        Object NUMBER270_tree=null;
        Object MUL_OPERATOR271_tree=null;
        Object PRIMITIVE_DATA_TYPE272_tree=null;
        Object END_ANGULAR273_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:708:13: (e= START_ANGULAR NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR )
            // Resources/LLVM/llvmGrammar.g:708:15: e= START_ANGULAR NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR
            {
            root_0 = (Object)adaptor.nil();

            e=(Token)match(input,START_ANGULAR,FOLLOW_START_ANGULAR_in_vector_type3850); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            if ( state.backtracking==0 ) {
              updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
            }
            NUMBER270=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vector_type3854); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER270_tree = (Object)adaptor.create(NUMBER270);
            adaptor.addChild(root_0, NUMBER270_tree);
            }
            MUL_OPERATOR271=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_vector_type3856); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MUL_OPERATOR271_tree = (Object)adaptor.create(MUL_OPERATOR271);
            adaptor.addChild(root_0, MUL_OPERATOR271_tree);
            }
            PRIMITIVE_DATA_TYPE272=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_vector_type3858); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PRIMITIVE_DATA_TYPE272_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE272);
            adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE272_tree);
            }
            END_ANGULAR273=(Token)match(input,END_ANGULAR,FOLLOW_END_ANGULAR_in_vector_type3860); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_ANGULAR273_tree = (Object)adaptor.create(END_ANGULAR273);
            adaptor.addChild(root_0, END_ANGULAR273_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 65, vector_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "vector_type"

    public static class multiDim_array_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiDim_array_type"
    // Resources/LLVM/llvmGrammar.g:710:1: multiDim_array_type : e= START_SQUARE_BR NUMBER MUL_OPERATOR ( array_type ) END_SQUARE_BR ;
    public final llvmGrammarParser.multiDim_array_type_return multiDim_array_type() throws RecognitionException {
        llvmGrammarParser.multiDim_array_type_return retval = new llvmGrammarParser.multiDim_array_type_return();
        retval.start = input.LT(1);
        int multiDim_array_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token NUMBER274=null;
        Token MUL_OPERATOR275=null;
        Token END_SQUARE_BR277=null;
        llvmGrammarParser.array_type_return array_type276 = null;


        Object e_tree=null;
        Object NUMBER274_tree=null;
        Object MUL_OPERATOR275_tree=null;
        Object END_SQUARE_BR277_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:710:21: (e= START_SQUARE_BR NUMBER MUL_OPERATOR ( array_type ) END_SQUARE_BR )
            // Resources/LLVM/llvmGrammar.g:710:23: e= START_SQUARE_BR NUMBER MUL_OPERATOR ( array_type ) END_SQUARE_BR
            {
            root_0 = (Object)adaptor.nil();

            e=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_multiDim_array_type3870); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            if ( state.backtracking==0 ) {
              updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
            }
            NUMBER274=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_multiDim_array_type3874); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER274_tree = (Object)adaptor.create(NUMBER274);
            adaptor.addChild(root_0, NUMBER274_tree);
            }
            MUL_OPERATOR275=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_multiDim_array_type3876); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MUL_OPERATOR275_tree = (Object)adaptor.create(MUL_OPERATOR275);
            adaptor.addChild(root_0, MUL_OPERATOR275_tree);
            }
            // Resources/LLVM/llvmGrammar.g:710:106: ( array_type )
            // Resources/LLVM/llvmGrammar.g:710:107: array_type
            {
            pushFollow(FOLLOW_array_type_in_multiDim_array_type3879);
            array_type276=array_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, array_type276.getTree());

            }

            END_SQUARE_BR277=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_multiDim_array_type3881); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_SQUARE_BR277_tree = (Object)adaptor.create(END_SQUARE_BR277);
            adaptor.addChild(root_0, END_SQUARE_BR277_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 66, multiDim_array_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "multiDim_array_type"

    public static class array_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "array_type"
    // Resources/LLVM/llvmGrammar.g:712:1: array_type : ( (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type );
    public final llvmGrammarParser.array_type_return array_type() throws RecognitionException {
        llvmGrammarParser.array_type_return retval = new llvmGrammarParser.array_type_return();
        retval.start = input.LT(1);
        int array_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token NUMBER278=null;
        Token MUL_OPERATOR279=null;
        Token END_SQUARE_BR281=null;
        llvmGrammarParser.data_type_return data_type280 = null;

        llvmGrammarParser.data_type_return data_type282 = null;


        Object e_tree=null;
        Object NUMBER278_tree=null;
        Object MUL_OPERATOR279_tree=null;
        Object END_SQUARE_BR281_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:712:12: ( (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type )
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==START_SQUARE_BR) ) {
                int LA104_1 = input.LA(2);

                if ( (synpred153_llvmGrammar()) ) {
                    alt104=1;
                }
                else if ( (true) ) {
                    alt104=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA104_0==PRIMITIVE_DATA_TYPE||LA104_0==LOCAL_PREFIX||LA104_0==START_PARANTHESIS||LA104_0==START_ANGULAR) ) {
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
                    // Resources/LLVM/llvmGrammar.g:712:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:712:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
                    // Resources/LLVM/llvmGrammar.g:712:15: e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
                    {
                    e=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_array_type3892); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e_tree = (Object)adaptor.create(e);
                    adaptor.addChild(root_0, e_tree);
                    }
                    if ( state.backtracking==0 ) {
                      updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
                    }
                    NUMBER278=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_array_type3896); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER278_tree = (Object)adaptor.create(NUMBER278);
                    adaptor.addChild(root_0, NUMBER278_tree);
                    }
                    MUL_OPERATOR279=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_array_type3898); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MUL_OPERATOR279_tree = (Object)adaptor.create(MUL_OPERATOR279);
                    adaptor.addChild(root_0, MUL_OPERATOR279_tree);
                    }
                    pushFollow(FOLLOW_data_type_in_array_type3900);
                    data_type280=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type280.getTree());
                    END_SQUARE_BR281=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_array_type3902); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END_SQUARE_BR281_tree = (Object)adaptor.create(END_SQUARE_BR281);
                    adaptor.addChild(root_0, END_SQUARE_BR281_tree);
                    }

                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:712:124: data_type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_data_type_in_array_type3906);
                    data_type282=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type282.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 67, array_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "array_type"

    public static class struct_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "struct_type"
    // Resources/LLVM/llvmGrammar.g:714:1: struct_type : (e= LOCAL_PREFIX ID ) ;
    public final llvmGrammarParser.struct_type_return struct_type() throws RecognitionException {
        llvmGrammarParser.struct_type_return retval = new llvmGrammarParser.struct_type_return();
        retval.start = input.LT(1);
        int struct_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token ID283=null;

        Object e_tree=null;
        Object ID283_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:714:13: ( (e= LOCAL_PREFIX ID ) )
            // Resources/LLVM/llvmGrammar.g:714:15: (e= LOCAL_PREFIX ID )
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:714:15: (e= LOCAL_PREFIX ID )
            // Resources/LLVM/llvmGrammar.g:714:16: e= LOCAL_PREFIX ID
            {
            e=(Token)match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_struct_type3917); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e_tree = (Object)adaptor.create(e);
            adaptor.addChild(root_0, e_tree);
            }
            ID283=(Token)match(input,ID,FOLLOW_ID_in_struct_type3919); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID283_tree = (Object)adaptor.create(ID283);
            adaptor.addChild(root_0, ID283_tree);
            }
            if ( state.backtracking==0 ) {
              updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
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
            if ( state.backtracking>0 ) { memoize(input, 68, struct_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "struct_type"

    public static class func_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "func_type"
    // Resources/LLVM/llvmGrammar.g:716:1: func_type : (e1= PRIMITIVE_DATA_TYPE )? e2= START_PARANTHESIS ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type ) ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )? END_PARANTHESIS ;
    public final llvmGrammarParser.func_type_return func_type() throws RecognitionException {
        llvmGrammarParser.func_type_return retval = new llvmGrammarParser.func_type_return();
        retval.start = input.LT(1);
        int func_type_StartIndex = input.index();
        Object root_0 = null;

        Token e1=null;
        Token e2=null;
        Token PRIMITIVE_DATA_TYPE285=null;
        Token COMMA287=null;
        Token PRIMITIVE_DATA_TYPE289=null;
        Token string_literal291=null;
        Token END_PARANTHESIS292=null;
        llvmGrammarParser.ptr_type_return ptr_type284 = null;

        llvmGrammarParser.agr_type_return agr_type286 = null;

        llvmGrammarParser.ptr_type_return ptr_type288 = null;

        llvmGrammarParser.agr_type_return agr_type290 = null;


        Object e1_tree=null;
        Object e2_tree=null;
        Object PRIMITIVE_DATA_TYPE285_tree=null;
        Object COMMA287_tree=null;
        Object PRIMITIVE_DATA_TYPE289_tree=null;
        Object string_literal291_tree=null;
        Object END_PARANTHESIS292_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:716:11: ( (e1= PRIMITIVE_DATA_TYPE )? e2= START_PARANTHESIS ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type ) ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )? END_PARANTHESIS )
            // Resources/LLVM/llvmGrammar.g:716:13: (e1= PRIMITIVE_DATA_TYPE )? e2= START_PARANTHESIS ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type ) ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )? END_PARANTHESIS
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:716:15: (e1= PRIMITIVE_DATA_TYPE )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==PRIMITIVE_DATA_TYPE) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: e1= PRIMITIVE_DATA_TYPE
                    {
                    e1=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3932); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e1_tree = (Object)adaptor.create(e1);
                    adaptor.addChild(root_0, e1_tree);
                    }

                    }
                    break;

            }

            e2=(Token)match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_func_type3941); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            e2_tree = (Object)adaptor.create(e2);
            adaptor.addChild(root_0, e2_tree);
            }
            if ( state.backtracking==0 ) {
              updateDataTypeLineAndPos((e2!=null?e2.getLine():0), (e2!=null?e2.getCharPositionInLine():0));
            }
            // Resources/LLVM/llvmGrammar.g:717:72: ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )
            int alt106=3;
            switch ( input.LA(1) ) {
            case PRIMITIVE_DATA_TYPE:
                {
                int LA106_1 = input.LA(2);

                if ( (synpred155_llvmGrammar()) ) {
                    alt106=1;
                }
                else if ( (synpred156_llvmGrammar()) ) {
                    alt106=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 106, 1, input);

                    throw nvae;
                }
                }
                break;
            case START_SQUARE_BR:
                {
                int LA106_2 = input.LA(2);

                if ( (synpred155_llvmGrammar()) ) {
                    alt106=1;
                }
                else if ( (true) ) {
                    alt106=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 106, 2, input);

                    throw nvae;
                }
                }
                break;
            case LOCAL_PREFIX:
                {
                int LA106_3 = input.LA(2);

                if ( (synpred155_llvmGrammar()) ) {
                    alt106=1;
                }
                else if ( (true) ) {
                    alt106=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 106, 3, input);

                    throw nvae;
                }
                }
                break;
            case START_ANGULAR:
                {
                int LA106_4 = input.LA(2);

                if ( (synpred155_llvmGrammar()) ) {
                    alt106=1;
                }
                else if ( (true) ) {
                    alt106=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 106, 4, input);

                    throw nvae;
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
                    // Resources/LLVM/llvmGrammar.g:717:73: ptr_type
                    {
                    pushFollow(FOLLOW_ptr_type_in_func_type3946);
                    ptr_type284=ptr_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr_type284.getTree());

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:717:83: PRIMITIVE_DATA_TYPE
                    {
                    PRIMITIVE_DATA_TYPE285=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3949); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PRIMITIVE_DATA_TYPE285_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE285);
                    adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE285_tree);
                    }

                    }
                    break;
                case 3 :
                    // Resources/LLVM/llvmGrammar.g:717:105: agr_type
                    {
                    pushFollow(FOLLOW_agr_type_in_func_type3953);
                    agr_type286=agr_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, agr_type286.getTree());

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:718:4: ( COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==COMMA) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:718:5: COMMA ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) )
                    {
                    COMMA287=(Token)match(input,COMMA,FOLLOW_COMMA_in_func_type3961); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA287_tree = (Object)adaptor.create(COMMA287);
                    adaptor.addChild(root_0, COMMA287_tree);
                    }
                    // Resources/LLVM/llvmGrammar.g:718:12: ( ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )* | ( '...' ) )
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==PRIMITIVE_DATA_TYPE||LA108_0==LOCAL_PREFIX||(LA108_0>=START_PARANTHESIS && LA108_0<=END_PARANTHESIS)||LA108_0==START_ANGULAR||LA108_0==START_SQUARE_BR) ) {
                        alt108=1;
                    }
                    else if ( (LA108_0==62) ) {
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
                            // Resources/LLVM/llvmGrammar.g:718:13: ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )*
                            {
                            // Resources/LLVM/llvmGrammar.g:718:13: ( ptr_type | PRIMITIVE_DATA_TYPE | agr_type )*
                            loop107:
                            do {
                                int alt107=4;
                                switch ( input.LA(1) ) {
                                case PRIMITIVE_DATA_TYPE:
                                    {
                                    int LA107_2 = input.LA(2);

                                    if ( (synpred157_llvmGrammar()) ) {
                                        alt107=1;
                                    }
                                    else if ( (synpred158_llvmGrammar()) ) {
                                        alt107=2;
                                    }


                                    }
                                    break;
                                case START_SQUARE_BR:
                                    {
                                    int LA107_3 = input.LA(2);

                                    if ( (synpred157_llvmGrammar()) ) {
                                        alt107=1;
                                    }
                                    else if ( (synpred159_llvmGrammar()) ) {
                                        alt107=3;
                                    }


                                    }
                                    break;
                                case LOCAL_PREFIX:
                                    {
                                    int LA107_4 = input.LA(2);

                                    if ( (synpred157_llvmGrammar()) ) {
                                        alt107=1;
                                    }
                                    else if ( (synpred159_llvmGrammar()) ) {
                                        alt107=3;
                                    }


                                    }
                                    break;
                                case START_ANGULAR:
                                    {
                                    int LA107_5 = input.LA(2);

                                    if ( (synpred157_llvmGrammar()) ) {
                                        alt107=1;
                                    }
                                    else if ( (synpred159_llvmGrammar()) ) {
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
                            	    // Resources/LLVM/llvmGrammar.g:718:15: ptr_type
                            	    {
                            	    pushFollow(FOLLOW_ptr_type_in_func_type3967);
                            	    ptr_type288=ptr_type();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, ptr_type288.getTree());

                            	    }
                            	    break;
                            	case 2 :
                            	    // Resources/LLVM/llvmGrammar.g:718:26: PRIMITIVE_DATA_TYPE
                            	    {
                            	    PRIMITIVE_DATA_TYPE289=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3971); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    PRIMITIVE_DATA_TYPE289_tree = (Object)adaptor.create(PRIMITIVE_DATA_TYPE289);
                            	    adaptor.addChild(root_0, PRIMITIVE_DATA_TYPE289_tree);
                            	    }

                            	    }
                            	    break;
                            	case 3 :
                            	    // Resources/LLVM/llvmGrammar.g:718:48: agr_type
                            	    {
                            	    pushFollow(FOLLOW_agr_type_in_func_type3975);
                            	    agr_type290=agr_type();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, agr_type290.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop107;
                                }
                            } while (true);


                            }
                            break;
                        case 2 :
                            // Resources/LLVM/llvmGrammar.g:718:63: ( '...' )
                            {
                            // Resources/LLVM/llvmGrammar.g:718:63: ( '...' )
                            // Resources/LLVM/llvmGrammar.g:718:64: '...'
                            {
                            string_literal291=(Token)match(input,62,FOLLOW_62_in_func_type3984); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal291_tree = (Object)adaptor.create(string_literal291);
                            adaptor.addChild(root_0, string_literal291_tree);
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            END_PARANTHESIS292=(Token)match(input,END_PARANTHESIS,FOLLOW_END_PARANTHESIS_in_func_type3989); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END_PARANTHESIS292_tree = (Object)adaptor.create(END_PARANTHESIS292);
            adaptor.addChild(root_0, END_PARANTHESIS292_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 69, func_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "func_type"

    public static class function_data_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_data"
    // Resources/LLVM/llvmGrammar.g:720:1: function_data : ( ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type );
    public final llvmGrammarParser.function_data_return function_data() throws RecognitionException {
        llvmGrammarParser.function_data_return retval = new llvmGrammarParser.function_data_return();
        retval.start = input.LT(1);
        int function_data_StartIndex = input.index();
        Object root_0 = null;

        Token START_SQUARE_BR293=null;
        Token NUMBER294=null;
        Token MUL_OPERATOR295=null;
        Token END_SQUARE_BR297=null;
        llvmGrammarParser.data_type_return data_type296 = null;

        llvmGrammarParser.data_type_return data_type298 = null;


        Object START_SQUARE_BR293_tree=null;
        Object NUMBER294_tree=null;
        Object MUL_OPERATOR295_tree=null;
        Object END_SQUARE_BR297_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:720:15: ( ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) | data_type )
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==START_SQUARE_BR) ) {
                int LA110_1 = input.LA(2);

                if ( (synpred162_llvmGrammar()) ) {
                    alt110=1;
                }
                else if ( (true) ) {
                    alt110=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 110, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA110_0==PRIMITIVE_DATA_TYPE||LA110_0==LOCAL_PREFIX||LA110_0==START_PARANTHESIS||LA110_0==START_ANGULAR) ) {
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
                    // Resources/LLVM/llvmGrammar.g:720:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
                    {
                    root_0 = (Object)adaptor.nil();

                    // Resources/LLVM/llvmGrammar.g:720:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
                    // Resources/LLVM/llvmGrammar.g:720:18: START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
                    {
                    START_SQUARE_BR293=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_function_data4000); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    START_SQUARE_BR293_tree = (Object)adaptor.create(START_SQUARE_BR293);
                    adaptor.addChild(root_0, START_SQUARE_BR293_tree);
                    }
                    NUMBER294=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_function_data4002); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER294_tree = (Object)adaptor.create(NUMBER294);
                    adaptor.addChild(root_0, NUMBER294_tree);
                    }
                    MUL_OPERATOR295=(Token)match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_function_data4004); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MUL_OPERATOR295_tree = (Object)adaptor.create(MUL_OPERATOR295);
                    adaptor.addChild(root_0, MUL_OPERATOR295_tree);
                    }
                    pushFollow(FOLLOW_data_type_in_function_data4006);
                    data_type296=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type296.getTree());
                    END_SQUARE_BR297=(Token)match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_function_data4008); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END_SQUARE_BR297_tree = (Object)adaptor.create(END_SQUARE_BR297);
                    adaptor.addChild(root_0, END_SQUARE_BR297_tree);
                    }

                    }


                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:720:81: data_type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_data_type_in_function_data4013);
                    data_type298=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type298.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 70, function_data_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "function_data"

    public static class ptr_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ptr_type"
    // Resources/LLVM/llvmGrammar.g:722:1: ptr_type : (e= PRIMITIVE_DATA_TYPE | derived_data_type ) ( STAR )+ ;
    public final llvmGrammarParser.ptr_type_return ptr_type() throws RecognitionException {
        llvmGrammarParser.ptr_type_return retval = new llvmGrammarParser.ptr_type_return();
        retval.start = input.LT(1);
        int ptr_type_StartIndex = input.index();
        Object root_0 = null;

        Token e=null;
        Token STAR300=null;
        llvmGrammarParser.derived_data_type_return derived_data_type299 = null;


        Object e_tree=null;
        Object STAR300_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:722:10: ( (e= PRIMITIVE_DATA_TYPE | derived_data_type ) ( STAR )+ )
            // Resources/LLVM/llvmGrammar.g:722:11: (e= PRIMITIVE_DATA_TYPE | derived_data_type ) ( STAR )+
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:722:11: (e= PRIMITIVE_DATA_TYPE | derived_data_type )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 111, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA111_0==LOCAL_PREFIX||LA111_0==START_PARANTHESIS||LA111_0==START_ANGULAR||LA111_0==START_SQUARE_BR) ) {
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
                    // Resources/LLVM/llvmGrammar.g:722:12: e= PRIMITIVE_DATA_TYPE
                    {
                    e=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_ptr_type4023); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    e_tree = (Object)adaptor.create(e);
                    adaptor.addChild(root_0, e_tree);
                    }
                    if ( state.backtracking==0 ) {
                      updateDataTypeLineAndPos((e!=null?e.getLine():0), (e!=null?e.getCharPositionInLine():0));
                    }

                    }
                    break;
                case 2 :
                    // Resources/LLVM/llvmGrammar.g:723:6: derived_data_type
                    {
                    pushFollow(FOLLOW_derived_data_type_in_ptr_type4032);
                    derived_data_type299=derived_data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, derived_data_type299.getTree());

                    }
                    break;

            }

            // Resources/LLVM/llvmGrammar.g:723:25: ( STAR )+
            int cnt112=0;
            loop112:
            do {
                int alt112=2;
                int LA112_0 = input.LA(1);

                if ( (LA112_0==STAR) ) {
                    alt112=1;
                }


                switch (alt112) {
            	case 1 :
            	    // Resources/LLVM/llvmGrammar.g:0:0: STAR
            	    {
            	    STAR300=(Token)match(input,STAR,FOLLOW_STAR_in_ptr_type4035); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    STAR300_tree = (Object)adaptor.create(STAR300);
            	    adaptor.addChild(root_0, STAR300_tree);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt112 >= 1 ) break loop112;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(112, input);
                        throw eee;
                }
                cnt112++;
            } while (true);


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
            if ( state.backtracking>0 ) { memoize(input, 71, ptr_type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ptr_type"

    public static class value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "value"
    // Resources/LLVM/llvmGrammar.g:725:1: value : ( prefix )? NUMBER ;
    public final llvmGrammarParser.value_return value() throws RecognitionException {
        llvmGrammarParser.value_return retval = new llvmGrammarParser.value_return();
        retval.start = input.LT(1);
        int value_StartIndex = input.index();
        Object root_0 = null;

        Token NUMBER302=null;
        llvmGrammarParser.prefix_return prefix301 = null;


        Object NUMBER302_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }
            // Resources/LLVM/llvmGrammar.g:725:7: ( ( prefix )? NUMBER )
            // Resources/LLVM/llvmGrammar.g:725:9: ( prefix )? NUMBER
            {
            root_0 = (Object)adaptor.nil();

            // Resources/LLVM/llvmGrammar.g:725:9: ( prefix )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( ((LA113_0>=GLOBAL_PREFIX && LA113_0<=LOCAL_PREFIX)) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // Resources/LLVM/llvmGrammar.g:0:0: prefix
                    {
                    pushFollow(FOLLOW_prefix_in_value4044);
                    prefix301=prefix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, prefix301.getTree());

                    }
                    break;

            }

            NUMBER302=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value4047); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER302_tree = (Object)adaptor.create(NUMBER302);
            adaptor.addChild(root_0, NUMBER302_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 72, value_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "value"

    // $ANTLR start synpred43_llvmGrammar
    public final void synpred43_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:534:75: ( START_PARANTHESIS )
        // Resources/LLVM/llvmGrammar.g:534:75: START_PARANTHESIS
        {
        match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_synpred43_llvmGrammar2082); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_llvmGrammar

    // $ANTLR start synpred47_llvmGrammar
    public final void synpred47_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:540:16: ( ( LOCAL_PREFIX ( ID | NUMBER )+ ) )
        // Resources/LLVM/llvmGrammar.g:540:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
        {
        // Resources/LLVM/llvmGrammar.g:540:16: ( LOCAL_PREFIX ( ID | NUMBER )+ )
        // Resources/LLVM/llvmGrammar.g:540:17: LOCAL_PREFIX ( ID | NUMBER )+
        {
        match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_synpred47_llvmGrammar2149); if (state.failed) return ;
        // Resources/LLVM/llvmGrammar.g:540:30: ( ID | NUMBER )+
        int cnt116=0;
        loop116:
        do {
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==ID||LA116_0==NUMBER) ) {
                alt116=1;
            }


            switch (alt116) {
        	case 1 :
        	    // Resources/LLVM/llvmGrammar.g:
        	    {
        	    if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
        	        input.consume();
        	        state.errorRecovery=false;state.failed=false;
        	    }
        	    else {
        	        if (state.backtracking>0) {state.failed=true; return ;}
        	        MismatchedSetException mse = new MismatchedSetException(null,input);
        	        throw mse;
        	    }


        	    }
        	    break;

        	default :
        	    if ( cnt116 >= 1 ) break loop116;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(116, input);
                    throw eee;
            }
            cnt116++;
        } while (true);


        }


        }
    }
    // $ANTLR end synpred47_llvmGrammar

    // $ANTLR start synpred52_llvmGrammar
    public final void synpred52_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:540:47: ( ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type ) )
        // Resources/LLVM/llvmGrammar.g:540:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
        {
        // Resources/LLVM/llvmGrammar.g:540:47: ( GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+ | data_type )
        int alt121=2;
        int LA121_0 = input.LA(1);

        if ( (LA121_0==GLOBAL_PREFIX) ) {
            alt121=1;
        }
        else if ( (LA121_0==PRIMITIVE_DATA_TYPE||LA121_0==LOCAL_PREFIX||LA121_0==START_PARANTHESIS||LA121_0==START_ANGULAR||LA121_0==START_SQUARE_BR) ) {
            alt121=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 121, 0, input);

            throw nvae;
        }
        switch (alt121) {
            case 1 :
                // Resources/LLVM/llvmGrammar.g:540:48: GLOBAL_PREFIX ( '.' )? ( ID | NUMBER )+
                {
                match(input,GLOBAL_PREFIX,FOLLOW_GLOBAL_PREFIX_in_synpred52_llvmGrammar2163); if (state.failed) return ;
                // Resources/LLVM/llvmGrammar.g:540:62: ( '.' )?
                int alt119=2;
                int LA119_0 = input.LA(1);

                if ( (LA119_0==DOT) ) {
                    alt119=1;
                }
                switch (alt119) {
                    case 1 :
                        // Resources/LLVM/llvmGrammar.g:0:0: '.'
                        {
                        match(input,DOT,FOLLOW_DOT_in_synpred52_llvmGrammar2165); if (state.failed) return ;

                        }
                        break;

                }

                // Resources/LLVM/llvmGrammar.g:540:66: ( ID | NUMBER )+
                int cnt120=0;
                loop120:
                do {
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==ID||LA120_0==NUMBER) ) {
                        alt120=1;
                    }


                    switch (alt120) {
                	case 1 :
                	    // Resources/LLVM/llvmGrammar.g:
                	    {
                	    if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                	        input.consume();
                	        state.errorRecovery=false;state.failed=false;
                	    }
                	    else {
                	        if (state.backtracking>0) {state.failed=true; return ;}
                	        MismatchedSetException mse = new MismatchedSetException(null,input);
                	        throw mse;
                	    }


                	    }
                	    break;

                	default :
                	    if ( cnt120 >= 1 ) break loop120;
                	    if (state.backtracking>0) {state.failed=true; return ;}
                            EarlyExitException eee =
                                new EarlyExitException(120, input);
                            throw eee;
                    }
                    cnt120++;
                } while (true);


                }
                break;
            case 2 :
                // Resources/LLVM/llvmGrammar.g:540:82: data_type
                {
                pushFollow(FOLLOW_data_type_in_synpred52_llvmGrammar2177);
                data_type();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred52_llvmGrammar

    // $ANTLR start synpred58_llvmGrammar
    public final void synpred58_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:542:77: ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )
        // Resources/LLVM/llvmGrammar.g:542:77: COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID )
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred58_llvmGrammar2217); if (state.failed) return ;
        pushFollow(FOLLOW_data_type_in_synpred58_llvmGrammar2219);
        data_type();

        state._fsp--;
        if (state.failed) return ;
        // Resources/LLVM/llvmGrammar.g:542:93: ( LOCAL_PREFIX )?
        int alt122=2;
        int LA122_0 = input.LA(1);

        if ( (LA122_0==LOCAL_PREFIX) ) {
            alt122=1;
        }
        switch (alt122) {
            case 1 :
                // Resources/LLVM/llvmGrammar.g:0:0: LOCAL_PREFIX
                {
                match(input,LOCAL_PREFIX,FOLLOW_LOCAL_PREFIX_in_synpred58_llvmGrammar2221); if (state.failed) return ;

                }
                break;

        }

        if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
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
    // $ANTLR end synpred58_llvmGrammar

    // $ANTLR start synpred71_llvmGrammar
    public final void synpred71_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:599:34: ( 'true' )
        // Resources/LLVM/llvmGrammar.g:599:34: 'true'
        {
        match(input,TRUE,FOLLOW_TRUE_in_synpred71_llvmGrammar2665); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred71_llvmGrammar

    // $ANTLR start synpred72_llvmGrammar
    public final void synpred72_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:599:41: ( 'false' )
        // Resources/LLVM/llvmGrammar.g:599:41: 'false'
        {
        match(input,FALSE,FOLLOW_FALSE_in_synpred72_llvmGrammar2667); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred72_llvmGrammar

    // $ANTLR start synpred73_llvmGrammar
    public final void synpred73_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:599:49: ( result )
        // Resources/LLVM/llvmGrammar.g:599:49: result
        {
        pushFollow(FOLLOW_result_in_synpred73_llvmGrammar2669);
        result();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred73_llvmGrammar

    // $ANTLR start synpred74_llvmGrammar
    public final void synpred74_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:600:29: ( 'true' )
        // Resources/LLVM/llvmGrammar.g:600:29: 'true'
        {
        match(input,TRUE,FOLLOW_TRUE_in_synpred74_llvmGrammar2690); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred74_llvmGrammar

    // $ANTLR start synpred75_llvmGrammar
    public final void synpred75_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:600:36: ( 'false' )
        // Resources/LLVM/llvmGrammar.g:600:36: 'false'
        {
        match(input,FALSE,FOLLOW_FALSE_in_synpred75_llvmGrammar2692); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred75_llvmGrammar

    // $ANTLR start synpred76_llvmGrammar
    public final void synpred76_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:600:44: ( result )
        // Resources/LLVM/llvmGrammar.g:600:44: result
        {
        pushFollow(FOLLOW_result_in_synpred76_llvmGrammar2694);
        result();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred76_llvmGrammar

    // $ANTLR start synpred94_llvmGrammar
    public final void synpred94_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:611:13: ( ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )? )
        // Resources/LLVM/llvmGrammar.g:611:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
        {
        // Resources/LLVM/llvmGrammar.g:611:13: ( ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* ) )?
        int alt138=2;
        int LA138_0 = input.LA(1);

        if ( (LA138_0==PRIMITIVE_DATA_TYPE||LA138_0==LOCAL_PREFIX||LA138_0==START_PARANTHESIS||LA138_0==START_ANGULAR||LA138_0==START_SQUARE_BR) ) {
            alt138=1;
        }
        switch (alt138) {
            case 1 :
                // Resources/LLVM/llvmGrammar.g:611:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
                {
                // Resources/LLVM/llvmGrammar.g:611:14: ( data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )* )
                // Resources/LLVM/llvmGrammar.g:611:15: data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
                {
                pushFollow(FOLLOW_data_type_in_synpred94_llvmGrammar2804);
                data_type();

                state._fsp--;
                if (state.failed) return ;
                // Resources/LLVM/llvmGrammar.g:611:25: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==ID||(LA134_0>=GLOBAL_PREFIX && LA134_0<=LOCAL_PREFIX)||LA134_0==NUMBER||LA134_0==FLOATING_LITERAL) ) {
                    alt134=1;
                }
                else if ( ((LA134_0>=TRUE && LA134_0<=FALSE)) ) {
                    alt134=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 134, 0, input);

                    throw nvae;
                }
                switch (alt134) {
                    case 1 :
                        // Resources/LLVM/llvmGrammar.g:611:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                        {
                        // Resources/LLVM/llvmGrammar.g:611:26: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                        // Resources/LLVM/llvmGrammar.g:611:27: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
                        {
                        // Resources/LLVM/llvmGrammar.g:611:27: ( prefix )?
                        int alt133=2;
                        int LA133_0 = input.LA(1);

                        if ( ((LA133_0>=GLOBAL_PREFIX && LA133_0<=LOCAL_PREFIX)) ) {
                            alt133=1;
                        }
                        switch (alt133) {
                            case 1 :
                                // Resources/LLVM/llvmGrammar.g:0:0: prefix
                                {
                                pushFollow(FOLLOW_prefix_in_synpred94_llvmGrammar2808);
                                prefix();

                                state._fsp--;
                                if (state.failed) return ;

                                }
                                break;

                        }

                        if ( input.LA(1)==ID||input.LA(1)==NUMBER||input.LA(1)==FLOATING_LITERAL ) {
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
                        break;
                    case 2 :
                        // Resources/LLVM/llvmGrammar.g:611:68: truefalse
                        {
                        pushFollow(FOLLOW_truefalse_in_synpred94_llvmGrammar2823);
                        truefalse();

                        state._fsp--;
                        if (state.failed) return ;

                        }
                        break;

                }

                // Resources/LLVM/llvmGrammar.g:612:4: ( COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse ) )*
                loop137:
                do {
                    int alt137=2;
                    int LA137_0 = input.LA(1);

                    if ( (LA137_0==COMMA) ) {
                        alt137=1;
                    }


                    switch (alt137) {
                	case 1 :
                	    // Resources/LLVM/llvmGrammar.g:612:5: COMMA data_type ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
                	    {
                	    match(input,COMMA,FOLLOW_COMMA_in_synpred94_llvmGrammar2830); if (state.failed) return ;
                	    pushFollow(FOLLOW_data_type_in_synpred94_llvmGrammar2832);
                	    data_type();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    // Resources/LLVM/llvmGrammar.g:612:21: ( ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) ) | truefalse )
                	    int alt136=2;
                	    int LA136_0 = input.LA(1);

                	    if ( (LA136_0==ID||(LA136_0>=GLOBAL_PREFIX && LA136_0<=LOCAL_PREFIX)||LA136_0==NUMBER||LA136_0==FLOATING_LITERAL) ) {
                	        alt136=1;
                	    }
                	    else if ( ((LA136_0>=TRUE && LA136_0<=FALSE)) ) {
                	        alt136=2;
                	    }
                	    else {
                	        if (state.backtracking>0) {state.failed=true; return ;}
                	        NoViableAltException nvae =
                	            new NoViableAltException("", 136, 0, input);

                	        throw nvae;
                	    }
                	    switch (alt136) {
                	        case 1 :
                	            // Resources/LLVM/llvmGrammar.g:612:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                	            {
                	            // Resources/LLVM/llvmGrammar.g:612:22: ( ( prefix )? ( NUMBER | FLOATING_LITERAL | ID ) )
                	            // Resources/LLVM/llvmGrammar.g:612:23: ( prefix )? ( NUMBER | FLOATING_LITERAL | ID )
                	            {
                	            // Resources/LLVM/llvmGrammar.g:612:23: ( prefix )?
                	            int alt135=2;
                	            int LA135_0 = input.LA(1);

                	            if ( ((LA135_0>=GLOBAL_PREFIX && LA135_0<=LOCAL_PREFIX)) ) {
                	                alt135=1;
                	            }
                	            switch (alt135) {
                	                case 1 :
                	                    // Resources/LLVM/llvmGrammar.g:0:0: prefix
                	                    {
                	                    pushFollow(FOLLOW_prefix_in_synpred94_llvmGrammar2836);
                	                    prefix();

                	                    state._fsp--;
                	                    if (state.failed) return ;

                	                    }
                	                    break;

                	            }

                	            if ( input.LA(1)==ID||input.LA(1)==NUMBER||input.LA(1)==FLOATING_LITERAL ) {
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
                	            break;
                	        case 2 :
                	            // Resources/LLVM/llvmGrammar.g:612:64: truefalse
                	            {
                	            pushFollow(FOLLOW_truefalse_in_synpred94_llvmGrammar2851);
                	            truefalse();

                	            state._fsp--;
                	            if (state.failed) return ;

                	            }
                	            break;

                	    }


                	    }
                	    break;

                	default :
                	    break loop137;
                    }
                } while (true);


                }


                }
                break;

        }


        }
    }
    // $ANTLR end synpred94_llvmGrammar

    // $ANTLR start synpred95_llvmGrammar
    public final void synpred95_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:625:58: ( START_PARANTHESIS )
        // Resources/LLVM/llvmGrammar.g:625:58: START_PARANTHESIS
        {
        match(input,START_PARANTHESIS,FOLLOW_START_PARANTHESIS_in_synpred95_llvmGrammar2962); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred95_llvmGrammar

    // $ANTLR start synpred97_llvmGrammar
    public final void synpred97_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:632:13: ( global_array )
        // Resources/LLVM/llvmGrammar.g:632:13: global_array
        {
        pushFollow(FOLLOW_global_array_in_synpred97_llvmGrammar3013);
        global_array();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred97_llvmGrammar

    // $ANTLR start synpred98_llvmGrammar
    public final void synpred98_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:632:28: ( structure_decl )
        // Resources/LLVM/llvmGrammar.g:632:28: structure_decl
        {
        pushFollow(FOLLOW_structure_decl_in_synpred98_llvmGrammar3017);
        structure_decl();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred98_llvmGrammar

    // $ANTLR start synpred99_llvmGrammar
    public final void synpred99_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:632:45: ( structure_obj )
        // Resources/LLVM/llvmGrammar.g:632:45: structure_obj
        {
        pushFollow(FOLLOW_structure_obj_in_synpred99_llvmGrammar3021);
        structure_obj();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred99_llvmGrammar

    // $ANTLR start synpred100_llvmGrammar
    public final void synpred100_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:632:60: ( string_constant )
        // Resources/LLVM/llvmGrammar.g:632:60: string_constant
        {
        pushFollow(FOLLOW_string_constant_in_synpred100_llvmGrammar3024);
        string_constant();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred100_llvmGrammar

    // $ANTLR start synpred123_llvmGrammar
    public final void synpred123_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:671:34: ( data_type )
        // Resources/LLVM/llvmGrammar.g:671:34: data_type
        {
        pushFollow(FOLLOW_data_type_in_synpred123_llvmGrammar3451);
        data_type();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred123_llvmGrammar

    // $ANTLR start synpred124_llvmGrammar
    public final void synpred124_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:672:7: ( NUMBER )
        // Resources/LLVM/llvmGrammar.g:672:7: NUMBER
        {
        match(input,NUMBER,FOLLOW_NUMBER_in_synpred124_llvmGrammar3467); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred124_llvmGrammar

    // $ANTLR start synpred126_llvmGrammar
    public final void synpred126_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:672:23: ( ID STRING_LITERAL )
        // Resources/LLVM/llvmGrammar.g:672:23: ID STRING_LITERAL
        {
        match(input,ID,FOLLOW_ID_in_synpred126_llvmGrammar3473); if (state.failed) return ;
        match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_synpred126_llvmGrammar3475); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred126_llvmGrammar

    // $ANTLR start synpred128_llvmGrammar
    public final void synpred128_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:673:14: ( ( data_type ) )
        // Resources/LLVM/llvmGrammar.g:673:14: ( data_type )
        {
        // Resources/LLVM/llvmGrammar.g:673:14: ( data_type )
        // Resources/LLVM/llvmGrammar.g:673:15: data_type
        {
        pushFollow(FOLLOW_data_type_in_synpred128_llvmGrammar3494);
        data_type();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred128_llvmGrammar

    // $ANTLR start synpred129_llvmGrammar
    public final void synpred129_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:673:46: ( NUMBER )
        // Resources/LLVM/llvmGrammar.g:673:46: NUMBER
        {
        match(input,NUMBER,FOLLOW_NUMBER_in_synpred129_llvmGrammar3505); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred129_llvmGrammar

    // $ANTLR start synpred131_llvmGrammar
    public final void synpred131_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:673:60: ( ID STRING_LITERAL )
        // Resources/LLVM/llvmGrammar.g:673:60: ID STRING_LITERAL
        {
        match(input,ID,FOLLOW_ID_in_synpred131_llvmGrammar3509); if (state.failed) return ;
        match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_synpred131_llvmGrammar3511); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred131_llvmGrammar

    // $ANTLR start synpred148_llvmGrammar
    public final void synpred148_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:697:14: ( ptr_type )
        // Resources/LLVM/llvmGrammar.g:697:14: ptr_type
        {
        pushFollow(FOLLOW_ptr_type_in_synpred148_llvmGrammar3761);
        ptr_type();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred148_llvmGrammar

    // $ANTLR start synpred149_llvmGrammar
    public final void synpred149_llvmGrammar_fragment() throws RecognitionException {   
        Token e=null;

        // Resources/LLVM/llvmGrammar.g:698:6: (e= PRIMITIVE_DATA_TYPE )
        // Resources/LLVM/llvmGrammar.g:698:6: e= PRIMITIVE_DATA_TYPE
        {
        e=(Token)match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred149_llvmGrammar3771); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred149_llvmGrammar

    // $ANTLR start synpred153_llvmGrammar
    public final void synpred153_llvmGrammar_fragment() throws RecognitionException {   
        Token e=null;

        // Resources/LLVM/llvmGrammar.g:712:14: ( (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) )
        // Resources/LLVM/llvmGrammar.g:712:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
        {
        // Resources/LLVM/llvmGrammar.g:712:14: (e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
        // Resources/LLVM/llvmGrammar.g:712:15: e= START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
        {
        e=(Token)match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_synpred153_llvmGrammar3892); if (state.failed) return ;
        match(input,NUMBER,FOLLOW_NUMBER_in_synpred153_llvmGrammar3896); if (state.failed) return ;
        match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_synpred153_llvmGrammar3898); if (state.failed) return ;
        pushFollow(FOLLOW_data_type_in_synpred153_llvmGrammar3900);
        data_type();

        state._fsp--;
        if (state.failed) return ;
        match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_synpred153_llvmGrammar3902); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred153_llvmGrammar

    // $ANTLR start synpred155_llvmGrammar
    public final void synpred155_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:717:73: ( ptr_type )
        // Resources/LLVM/llvmGrammar.g:717:73: ptr_type
        {
        pushFollow(FOLLOW_ptr_type_in_synpred155_llvmGrammar3946);
        ptr_type();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred155_llvmGrammar

    // $ANTLR start synpred156_llvmGrammar
    public final void synpred156_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:717:83: ( PRIMITIVE_DATA_TYPE )
        // Resources/LLVM/llvmGrammar.g:717:83: PRIMITIVE_DATA_TYPE
        {
        match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred156_llvmGrammar3949); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred156_llvmGrammar

    // $ANTLR start synpred157_llvmGrammar
    public final void synpred157_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:718:15: ( ptr_type )
        // Resources/LLVM/llvmGrammar.g:718:15: ptr_type
        {
        pushFollow(FOLLOW_ptr_type_in_synpred157_llvmGrammar3967);
        ptr_type();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred157_llvmGrammar

    // $ANTLR start synpred158_llvmGrammar
    public final void synpred158_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:718:26: ( PRIMITIVE_DATA_TYPE )
        // Resources/LLVM/llvmGrammar.g:718:26: PRIMITIVE_DATA_TYPE
        {
        match(input,PRIMITIVE_DATA_TYPE,FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred158_llvmGrammar3971); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred158_llvmGrammar

    // $ANTLR start synpred159_llvmGrammar
    public final void synpred159_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:718:48: ( agr_type )
        // Resources/LLVM/llvmGrammar.g:718:48: agr_type
        {
        pushFollow(FOLLOW_agr_type_in_synpred159_llvmGrammar3975);
        agr_type();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred159_llvmGrammar

    // $ANTLR start synpred162_llvmGrammar
    public final void synpred162_llvmGrammar_fragment() throws RecognitionException {   
        // Resources/LLVM/llvmGrammar.g:720:17: ( ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR ) )
        // Resources/LLVM/llvmGrammar.g:720:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
        {
        // Resources/LLVM/llvmGrammar.g:720:17: ( START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR )
        // Resources/LLVM/llvmGrammar.g:720:18: START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR
        {
        match(input,START_SQUARE_BR,FOLLOW_START_SQUARE_BR_in_synpred162_llvmGrammar4000); if (state.failed) return ;
        match(input,NUMBER,FOLLOW_NUMBER_in_synpred162_llvmGrammar4002); if (state.failed) return ;
        match(input,MUL_OPERATOR,FOLLOW_MUL_OPERATOR_in_synpred162_llvmGrammar4004); if (state.failed) return ;
        pushFollow(FOLLOW_data_type_in_synpred162_llvmGrammar4006);
        data_type();

        state._fsp--;
        if (state.failed) return ;
        match(input,END_SQUARE_BR,FOLLOW_END_SQUARE_BR_in_synpred162_llvmGrammar4008); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred162_llvmGrammar

    // Delegated rules

    public final boolean synpred74_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred74_llvmGrammar_fragment(); // can never throw exception
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
    public final boolean synpred149_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred149_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred158_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred158_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred76_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred76_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred98_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred98_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred131_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred131_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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
    public final boolean synpred124_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred124_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred100_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred100_llvmGrammar_fragment(); // can never throw exception
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
    public final boolean synpred75_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred75_llvmGrammar_fragment(); // can never throw exception
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
    public final boolean synpred43_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred47_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred47_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred52_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred52_llvmGrammar_fragment(); // can never throw exception
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
    public final boolean synpred129_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred129_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred58_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred58_llvmGrammar_fragment(); // can never throw exception
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
    public final boolean synpred157_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred157_llvmGrammar_fragment(); // can never throw exception
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
    public final boolean synpred99_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred162_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred162_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred148_llvmGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred148_llvmGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA22 dfa22 = new DFA22(this);
    protected DFA24 dfa24 = new DFA24(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA66 dfa66 = new DFA66(this);
    static final String DFA22_eotS =
        "\13\uffff";
    static final String DFA22_eofS =
        "\13\uffff";
    static final String DFA22_minS =
        "\1\6\1\26\3\32\3\uffff\1\5\2\uffff";
    static final String DFA22_maxS =
        "\1\121\1\57\3\32\3\uffff\1\122\2\uffff";
    static final String DFA22_acceptS =
        "\5\uffff\1\2\1\3\1\4\1\uffff\1\1\1\5";
    static final String DFA22_specialS =
        "\13\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\6\2\3\15\uffff\1\2\1\uffff\2\1\15\uffff\1\2\7\uffff\1\2"+
            "\25\uffff\1\5\3\uffff\1\4\2\6\1\uffff\1\6\2\uffff\2\7",
            "\1\2\20\uffff\1\2\7\uffff\1\2",
            "\1\10",
            "\1\10",
            "\1\10",
            "",
            "",
            "",
            "\1\7\7\uffff\1\12\6\uffff\1\11\56\uffff\2\5\2\uffff\1\5\6"+
            "\uffff\5\7",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "503:1: instruction_set : ( binary_instruction | memory_rel_instruction | terminator_instruction | other_instruction | cast_instruction );";
        }
    }
    static final String DFA24_eotS =
        "\12\uffff";
    static final String DFA24_eofS =
        "\12\uffff";
    static final String DFA24_minS =
        "\1\7\1\26\3\32\1\uffff\1\103\3\uffff";
    static final String DFA24_maxS =
        "\1\111\1\57\3\32\1\uffff\1\107\3\uffff";
    static final String DFA24_acceptS =
        "\5\uffff\1\3\1\uffff\1\1\1\2\1\4";
    static final String DFA24_specialS =
        "\12\uffff}>";
    static final String[] DFA24_transitionS = {
            "\2\3\15\uffff\1\2\1\uffff\2\1\15\uffff\1\2\7\uffff\1\2\25\uffff"+
            "\1\5\3\uffff\1\4",
            "\1\2\20\uffff\1\2\7\uffff\1\2",
            "\1\6",
            "\1\6",
            "\1\6",
            "",
            "\1\10\1\7\2\uffff\1\11",
            "",
            "",
            ""
    };

    static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
    static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
    static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
    static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
    static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
    static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
    static final short[][] DFA24_transition;

    static {
        int numStates = DFA24_transitionS.length;
        DFA24_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
        }
    }

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = DFA24_eot;
            this.eof = DFA24_eof;
            this.min = DFA24_min;
            this.max = DFA24_max;
            this.accept = DFA24_accept;
            this.special = DFA24_special;
            this.transition = DFA24_transition;
        }
        public String getDescription() {
            return "514:1: memory_rel_instruction : ( load | alloca | store | getelementptr );";
        }
    }
    static final String DFA42_eotS =
        "\22\uffff";
    static final String DFA42_eofS =
        "\1\1\21\uffff";
    static final String DFA42_minS =
        "\1\6\16\uffff\1\0\2\uffff";
    static final String DFA42_maxS =
        "\1\121\16\uffff\1\0\2\uffff";
    static final String DFA42_acceptS =
        "\1\uffff\1\2\17\uffff\1\1";
    static final String DFA42_specialS =
        "\17\uffff\1\0\2\uffff}>";
    static final String[] DFA42_transitionS = {
            "\3\1\15\uffff\1\1\1\uffff\2\1\1\uffff\1\17\2\uffff\1\1\1\uffff"+
            "\1\1\3\uffff\1\1\2\uffff\1\1\7\uffff\1\1\25\uffff\1\1\3\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
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
            ""
    };

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "()* loopback of 542:76: ( COMMA data_type ( LOCAL_PREFIX )? ( NUMBER | ID ) )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_15 = input.LA(1);

                         
                        int index42_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_llvmGrammar()) ) {s = 17;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index42_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA47_eotS =
        "\12\uffff";
    static final String DFA47_eofS =
        "\12\uffff";
    static final String DFA47_minS =
        "\1\7\1\26\3\32\1\uffff\1\5\3\uffff";
    static final String DFA47_maxS =
        "\1\121\1\57\3\32\1\uffff\1\122\3\uffff";
    static final String DFA47_acceptS =
        "\5\uffff\1\3\1\uffff\1\2\1\4\1\1";
    static final String DFA47_specialS =
        "\12\uffff}>";
    static final String[] DFA47_transitionS = {
            "\2\3\15\uffff\1\2\1\uffff\2\1\15\uffff\1\2\7\uffff\1\2\31\uffff"+
            "\1\4\6\uffff\2\5",
            "\1\2\20\uffff\1\2\7\uffff\1\2",
            "\1\6",
            "\1\6",
            "\1\6",
            "",
            "\1\7\110\uffff\2\11\2\5\1\10",
            "",
            "",
            ""
    };

    static final short[] DFA47_eot = DFA.unpackEncodedString(DFA47_eotS);
    static final short[] DFA47_eof = DFA.unpackEncodedString(DFA47_eofS);
    static final char[] DFA47_min = DFA.unpackEncodedStringToUnsignedChars(DFA47_minS);
    static final char[] DFA47_max = DFA.unpackEncodedStringToUnsignedChars(DFA47_maxS);
    static final short[] DFA47_accept = DFA.unpackEncodedString(DFA47_acceptS);
    static final short[] DFA47_special = DFA.unpackEncodedString(DFA47_specialS);
    static final short[][] DFA47_transition;

    static {
        int numStates = DFA47_transitionS.length;
        DFA47_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA47_transition[i] = DFA.unpackEncodedString(DFA47_transitionS[i]);
        }
    }

    class DFA47 extends DFA {

        public DFA47(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 47;
            this.eot = DFA47_eot;
            this.eof = DFA47_eof;
            this.min = DFA47_min;
            this.max = DFA47_max;
            this.accept = DFA47_accept;
            this.special = DFA47_special;
            this.transition = DFA47_transition;
        }
        public String getDescription() {
            return "582:1: other_instruction : ( cmp | phi | call | select );";
        }
    }
    static final String DFA66_eotS =
        "\13\uffff";
    static final String DFA66_eofS =
        "\13\uffff";
    static final String DFA66_minS =
        "\1\7\5\0\5\uffff";
    static final String DFA66_maxS =
        "\1\111\5\0\5\uffff";
    static final String DFA66_acceptS =
        "\6\uffff\1\1\1\2\1\3\1\5\1\4";
    static final String DFA66_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\5\uffff}>";
    static final String[] DFA66_transitionS = {
            "\2\3\15\uffff\1\2\1\uffff\1\5\1\1\15\uffff\1\2\7\uffff\1\2"+
            "\31\uffff\1\4",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA66_eot = DFA.unpackEncodedString(DFA66_eotS);
    static final short[] DFA66_eof = DFA.unpackEncodedString(DFA66_eofS);
    static final char[] DFA66_min = DFA.unpackEncodedStringToUnsignedChars(DFA66_minS);
    static final char[] DFA66_max = DFA.unpackEncodedStringToUnsignedChars(DFA66_maxS);
    static final short[] DFA66_accept = DFA.unpackEncodedString(DFA66_acceptS);
    static final short[] DFA66_special = DFA.unpackEncodedString(DFA66_specialS);
    static final short[][] DFA66_transition;

    static {
        int numStates = DFA66_transitionS.length;
        DFA66_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA66_transition[i] = DFA.unpackEncodedString(DFA66_transitionS[i]);
        }
    }

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = DFA66_eot;
            this.eof = DFA66_eof;
            this.min = DFA66_min;
            this.max = DFA66_max;
            this.accept = DFA66_accept;
            this.special = DFA66_special;
            this.transition = DFA66_transition;
        }
        public String getDescription() {
            return "632:1: global_var : ( global_array | structure_decl | structure_obj | string_constant | global_variable );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA66_1 = input.LA(1);

                         
                        int index66_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_llvmGrammar()) ) {s = 6;}

                        else if ( (synpred98_llvmGrammar()) ) {s = 7;}

                        else if ( (synpred99_llvmGrammar()) ) {s = 8;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index66_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA66_2 = input.LA(1);

                         
                        int index66_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_llvmGrammar()) ) {s = 6;}

                        else if ( (synpred99_llvmGrammar()) ) {s = 8;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index66_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA66_3 = input.LA(1);

                         
                        int index66_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_llvmGrammar()) ) {s = 6;}

                        else if ( (synpred99_llvmGrammar()) ) {s = 8;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index66_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA66_4 = input.LA(1);

                         
                        int index66_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_llvmGrammar()) ) {s = 6;}

                        else if ( (synpred99_llvmGrammar()) ) {s = 8;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index66_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA66_5 = input.LA(1);

                         
                        int index66_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_llvmGrammar()) ) {s = 6;}

                        else if ( (synpred99_llvmGrammar()) ) {s = 8;}

                        else if ( (synpred100_llvmGrammar()) ) {s = 10;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index66_5);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 66, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ID_in_string471 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_NULL_CHAR_in_string473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_prefix0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_llvm_element_in_llvm1252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_target_machine_info_in_llvm_element1259 = new BitSet(new long[]{0xA000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_function_def_in_llvm_element1263 = new BitSet(new long[]{0xA000808003400182L,0x0000000000000200L});
    public static final BitSet FOLLOW_global_var_list_in_llvm_element1277 = new BitSet(new long[]{0xA000808003400182L,0x0000000000000200L});
    public static final BitSet FOLLOW_function_declaration_in_llvm_element1291 = new BitSet(new long[]{0xA000808003400182L,0x0000000000000200L});
    public static final BitSet FOLLOW_target_datalayout_in_target_machine_info1303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_target_datalayout1313 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_target_datalayout1315 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_target_Data_ID_in_target_datalayout1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_target_Data_ID1340 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_target_Data_ID1342 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_target_Data_ID1344 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1347 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_target_Data_ID1349 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1351 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_target_Data_ID1353 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1355 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_target_Data_ID1357 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1361 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_target_Data_ID1363 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1365 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_target_Data_ID1367 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1369 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_target_Data_ID1371 = new BitSet(new long[]{0x0800000000800000L});
    public static final BitSet FOLLOW_59_in_target_Data_ID1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_target_Data_ID1390 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_target_Data_ID1392 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1394 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_target_Data_ID1396 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_target_Data_ID1398 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_target_Data_ID1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_var_list_element_in_global_var_list1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_var_in_global_var_list_element1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_function_declaration1431 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_LINKAGE_TYPE_in_function_declaration1433 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_RETURN_ATTR_in_function_declaration1436 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_function_declaration1440 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_GLOBAL_PREFIX_in_function_declaration1443 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_function_declaration1447 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_function_declaration1449 = new BitSet(new long[]{0x0000000A62800C00L});
    public static final BitSet FOLLOW_parameter_list_in_function_declaration1461 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_END_PARANTHESIS_in_function_declaration1464 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_function_attr_list_in_function_declaration1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_parameter_list1480 = new BitSet(new long[]{0x0000000008004002L});
    public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_parameter_list1484 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_parameters_in_parameter_list1491 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_parameters1521 = new BitSet(new long[]{0x4000000A22800C02L});
    public static final BitSet FOLLOW_data_type_in_parameters1526 = new BitSet(new long[]{0x4000000A22804C02L});
    public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_parameters1530 = new BitSet(new long[]{0x4000000A22800C02L});
    public static final BitSet FOLLOW_62_in_parameters1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_function_def1565 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_LINKAGE_TYPE_in_function_def1567 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_RETURN_ATTR_in_function_def1570 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_function_def1573 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_GLOBAL_PREFIX_in_function_def1576 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_function_def1580 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_function_def1582 = new BitSet(new long[]{0x0000000A62800C00L});
    public static final BitSet FOLLOW_argument_list_in_function_def1589 = new BitSet(new long[]{0x0000000A62800C00L});
    public static final BitSet FOLLOW_END_PARANTHESIS_in_function_def1592 = new BitSet(new long[]{0x0000000080010000L});
    public static final BitSet FOLLOW_function_attr_list_in_function_def1599 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_START_CURLY_in_function_def1603 = new BitSet(new long[]{0x00008081034001C0L,0x0000000000032E20L});
    public static final BitSet FOLLOW_basic_blocks_in_function_def1617 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_END_CURLY_in_function_def1642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_ATTRIBUTE_in_function_attr_list1663 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_data_type_in_argument_list1674 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_argument_list1678 = new BitSet(new long[]{0x0000000008004002L});
    public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_argument_list1682 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_argument_in_argument_list1695 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_argument1712 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_argument1716 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_argument1720 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_ARGUMENT_ATTRIBUTE_in_argument1724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instruction_set_in_basic_blocks1757 = new BitSet(new long[]{0x00008080034001C2L,0x0000000000032E20L});
    public static final BitSet FOLLOW_binary_instruction_in_instruction_set1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memory_rel_instruction_in_instruction_set1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_terminator_instruction_in_instruction_set1788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_other_instruction_in_instruction_set1798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_instruction_in_instruction_set1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_binary_instruction1819 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_binary_instruction1821 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_BIN_OPR_STR_in_binary_instruction1825 = new BitSet(new long[]{0x0000000A22800C00L,0x0000000000000007L});
    public static final BitSet FOLLOW_set_in_binary_instruction1829 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_binary_instruction1840 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_binary_instruction1844 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_binary_instruction1846 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_binary_instruction1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_load_in_memory_rel_instruction1879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alloca_in_memory_rel_instruction1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_store_in_memory_rel_instruction1887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_getelementptr_in_memory_rel_instruction1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_alloca1906 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_alloca1910 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_alloca1912 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_alloca1916 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_alloca1920 = new BitSet(new long[]{0x0000000A22800E00L});
    public static final BitSet FOLLOW_data_type_in_alloca1923 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_alloca1927 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_alloca1929 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_alloca1933 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_alloca1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_load1961 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_load1965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_load1967 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_load1969 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_load1973 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_load1976 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_load1978 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_load1980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_store2000 = new BitSet(new long[]{0x0000000A22A00C00L,0x0000000000000040L});
    public static final BitSet FOLLOW_ATOMIC_ORDERING_in_store2004 = new BitSet(new long[]{0x0000000A22800C00L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_store2009 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_store2014 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_store2018 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_store2020 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_store2024 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_store2029 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_store2033 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_store2035 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_store2037 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_result_in_getelementptr2057 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_getelementptr2061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_getelementptr_rhs_in_getelementptr2063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_getelementptr_rhs2076 = new BitSet(new long[]{0x0000000A22800000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_getelementptr_rhs2079 = new BitSet(new long[]{0x0000000A22800000L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_getelementptr_rhs2082 = new BitSet(new long[]{0x0000000A22800000L});
    public static final BitSet FOLLOW_ptr_type_in_getelementptr_rhs2089 = new BitSet(new long[]{0x0000000A23800C00L,0x0000000000000200L});
    public static final BitSet FOLLOW_element_name_in_getelementptr_rhs2105 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_getelementptr_rhs2107 = new BitSet(new long[]{0x0000000A23800C00L});
    public static final BitSet FOLLOW_offset_in_getelementptr_rhs2112 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_END_PARANTHESIS_in_getelementptr_rhs2114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_element_name2149 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_element_name2151 = new BitSet(new long[]{0x0000008000400002L});
    public static final BitSet FOLLOW_GLOBAL_PREFIX_in_element_name2163 = new BitSet(new long[]{0x000000A000400000L});
    public static final BitSet FOLLOW_DOT_in_element_name2165 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_element_name2167 = new BitSet(new long[]{0x0000008000400002L});
    public static final BitSet FOLLOW_data_type_in_element_name2177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_element_name2181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GLOBAL_PREFIX_in_offset2193 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_offset2195 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_offset2197 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_offset2203 = new BitSet(new long[]{0x0000008002400000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_offset2205 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_offset2208 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_offset2217 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_offset2219 = new BitSet(new long[]{0x0000008002400000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_offset2221 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_offset2224 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_ret_in_terminator_instruction2241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_br_in_terminator_instruction2245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchInstr_in_terminator_instruction2249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unreachable_in_terminator_instruction2252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ret2266 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_ret2270 = new BitSet(new long[]{0x0000808003400182L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_ret2275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_br2295 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_br2297 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_br2305 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_br2308 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_br2310 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_br2312 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_br2316 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_br2318 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_br2320 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_br2322 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_br2326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_br2345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_br2347 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_br2350 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_br2354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_switchInstr2382 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_switchInstr2384 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_switchInstr2388 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_switchInstr2390 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_switchInstr2392 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_switchInstr2396 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_switchInstr2398 = new BitSet(new long[]{0x0000001A22800C00L});
    public static final BitSet FOLLOW_cases_list_in_switchInstr2408 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_switchInstr2411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cases_in_cases_list2434 = new BitSet(new long[]{0x0000000A22800C02L});
    public static final BitSet FOLLOW_data_type_in_cases2453 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_cases2457 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_cases2465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_cases2467 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_cases2473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNREACHABLE_in_unreachable2497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cmp_in_other_instruction2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_phi_in_other_instruction2532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_call_in_other_instruction2545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_other_instruction2558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_cmp2569 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_cmp2573 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_78_in_cmp2580 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_79_in_cmp2582 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_CONDITION_in_cmp2587 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_cmp2591 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_cmp2611 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_cmp2614 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_cmp2621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_phi2636 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_phi2640 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_PHI_in_phi2642 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_phi2646 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_incoming_list_in_phi2650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_incoming_list2662 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_TRUE_in_incoming_list2665 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_FALSE_in_incoming_list2667 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_result_in_incoming_list2669 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_73_in_incoming_list2671 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_incoming_list2674 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_incoming_list2676 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_incoming_list2678 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_incoming_list2685 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_incoming_list2687 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_TRUE_in_incoming_list2690 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_FALSE_in_incoming_list2692 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_result_in_incoming_list2694 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_73_in_incoming_list2696 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_incoming_list2700 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_incoming_list2702 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_incoming_list2704 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_result_in_call2723 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_call2725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_80_in_call2731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_call2736 = new BitSet(new long[]{0x0000000A22828C00L});
    public static final BitSet FOLLOW_CALLING_CONV_in_call2740 = new BitSet(new long[]{0x0000000A22808C00L});
    public static final BitSet FOLLOW_PARAMETER_ATTRIBUTE_in_call2745 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_call2753 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_func_name_in_call2755 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_call2757 = new BitSet(new long[]{0x0000000A62800C00L});
    public static final BitSet FOLLOW_parameter_in_call2761 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_END_PARANTHESIS_in_call2763 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_FUNCTION_ATTRIBUTE_in_call2767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefix_in_func_name2780 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_func_name2781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_parameter2804 = new BitSet(new long[]{0x0000808003400180L});
    public static final BitSet FOLLOW_prefix_in_parameter2808 = new BitSet(new long[]{0x0000808000400000L});
    public static final BitSet FOLLOW_set_in_parameter2810 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_truefalse_in_parameter2823 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_parameter2830 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_parameter2832 = new BitSet(new long[]{0x0000808003400180L});
    public static final BitSet FOLLOW_prefix_in_parameter2836 = new BitSet(new long[]{0x0000808000400000L});
    public static final BitSet FOLLOW_set_in_parameter2838 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_truefalse_in_parameter2851 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_func_type_in_parameter2865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_select2876 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_select2878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_select2882 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_select2886 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_select2890 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_select2892 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_select2900 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_select2904 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_select2906 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_select2914 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_select2918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_cast_instruction2941 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_cast_instruction2945 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_cast_instr_rhs_in_cast_instruction2947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_TYPE_in_cast_instr_rhs2960 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_cast_instr_rhs2962 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_cast_instr_rhs2967 = new BitSet(new long[]{0x0000808003400180L,0x0000000000000200L});
    public static final BitSet FOLLOW_result_in_cast_instr_rhs2984 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_cast_instr_rhs2986 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_cast_instr_rhs2990 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_END_PARANTHESIS_in_cast_instr_rhs2992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_array_in_global_var3013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structure_decl_in_global_var3017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structure_obj_in_global_var3021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_constant_in_global_var3024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_variable_in_global_var3028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_global_array3040 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_global_array3044 = new BitSet(new long[]{0x0000000A22C01C00L,0x0000000000700000L});
    public static final BitSet FOLLOW_LINKAGE_TYPE_in_global_array3048 = new BitSet(new long[]{0x0000000A22C01C00L,0x0000000000700000L});
    public static final BitSet FOLLOW_84_in_global_array3052 = new BitSet(new long[]{0x0000000A22C01C00L,0x0000000000700000L});
    public static final BitSet FOLLOW_85_in_global_array3059 = new BitSet(new long[]{0x0000000A22C01C00L,0x0000000000700000L});
    public static final BitSet FOLLOW_86_in_global_array3070 = new BitSet(new long[]{0x0000000A22C01C00L,0x0000000000700000L});
    public static final BitSet FOLLOW_array_data_type_in_global_array3077 = new BitSet(new long[]{0x0000000A22C01C00L,0x0000000000700000L});
    public static final BitSet FOLLOW_list_of_initial_values_in_global_array3083 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_global_array3085 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_global_array3087 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_global_array3091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_array_data_type3109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_list_of_initial_values3124 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_list_of_initial_values3126 = new BitSet(new long[]{0x0002008A22C01C00L,0x0000000000700080L});
    public static final BitSet FOLLOW_array_initial_value_in_list_of_initial_values3129 = new BitSet(new long[]{0x0000001008000000L});
    public static final BitSet FOLLOW_getelementptr_rhs_in_list_of_initial_values3132 = new BitSet(new long[]{0x0000001008000000L});
    public static final BitSet FOLLOW_COMMA_in_list_of_initial_values3147 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_list_of_initial_values3149 = new BitSet(new long[]{0x0002008A22C01C00L,0x0000000000700080L});
    public static final BitSet FOLLOW_array_initial_value_in_list_of_initial_values3152 = new BitSet(new long[]{0x0000001008000000L});
    public static final BitSet FOLLOW_getelementptr_rhs_in_list_of_initial_values3155 = new BitSet(new long[]{0x0000001008000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_list_of_initial_values3161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ZEROINITIALIZER_in_list_of_initial_values3176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_list_of_initial_values3189 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_list_of_initial_values3191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_array_initial_value3201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_of_initial_values_in_array_initial_value3210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_global_variable3220 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_global_variable3225 = new BitSet(new long[]{0x0000000A22800C00L,0x0000000000500000L});
    public static final BitSet FOLLOW_LINKAGE_TYPE_in_global_variable3232 = new BitSet(new long[]{0x0000000A22800C00L,0x0000000000500000L});
    public static final BitSet FOLLOW_84_in_global_variable3238 = new BitSet(new long[]{0x0000000A22800C00L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_global_variable3245 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_global_variable3259 = new BitSet(new long[]{0x0000008000002002L,0x0000000000000080L});
    public static final BitSet FOLLOW_global_variable_initial_value_in_global_variable3264 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_global_variable3266 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_global_variable3268 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_global_variable3273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_global_variable_initial_value3308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_instr_rhs_in_global_variable_initial_value3326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_getelementptr_rhs_in_global_variable_initial_value3345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_structure_obj3376 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_structure_obj3380 = new BitSet(new long[]{0x0000000002000400L,0x0000000000500000L});
    public static final BitSet FOLLOW_LINKAGE_TYPE_in_structure_obj3384 = new BitSet(new long[]{0x0000000002000000L,0x0000000000500000L});
    public static final BitSet FOLLOW_84_in_structure_obj3387 = new BitSet(new long[]{0x0000000002000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_structure_obj3393 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_structure_obj3397 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_structure_obj3401 = new BitSet(new long[]{0x0000000080001000L});
    public static final BitSet FOLLOW_ZEROINITIALIZER_in_structure_obj3412 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_struct_init_value_in_structure_obj3419 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_structure_obj3422 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_structure_obj3424 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_structure_obj3428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_CURLY_in_struct_init_value3448 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_struct_init_value3451 = new BitSet(new long[]{0x000A008A22C01C00L,0x0000000000700200L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_init_value3455 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_struct_init_value3457 = new BitSet(new long[]{0x000A008A22C01C00L,0x0000000000700200L});
    public static final BitSet FOLLOW_NUMBER_in_struct_init_value3467 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_LETTER_in_struct_init_value3470 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_ID_in_struct_init_value3473 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_struct_init_value3475 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_73_in_struct_init_value3478 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_array_initial_value_in_struct_init_value3481 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_COMMA_in_struct_init_value3490 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_struct_init_value3494 = new BitSet(new long[]{0x000A008A22C01C00L,0x0000000000700200L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_init_value3498 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_struct_init_value3500 = new BitSet(new long[]{0x000A008A22C01C00L,0x0000000000700200L});
    public static final BitSet FOLLOW_NUMBER_in_struct_init_value3505 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_LETTER_in_struct_init_value3507 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_ID_in_struct_init_value3509 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_struct_init_value3511 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_73_in_struct_init_value3513 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_array_initial_value_in_struct_init_value3515 = new BitSet(new long[]{0x0000000108000000L});
    public static final BitSet FOLLOW_END_CURLY_in_struct_init_value3520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_name_in_string_constant3534 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_string_constant3538 = new BitSet(new long[]{0x0000000000000400L,0x0000000000200000L});
    public static final BitSet FOLLOW_LINKAGE_TYPE_in_string_constant3542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_string_constant3547 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_string_constant3551 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_string_constant3555 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_string_constant3557 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string_constant3569 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_string_constant3573 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ALIGN_in_string_constant3575 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_string_constant3579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GLOBAL_PREFIX_in_string_name3615 = new BitSet(new long[]{0x0000002000400000L});
    public static final BitSet FOLLOW_DOT_in_string_name3617 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_string_name3619 = new BitSet(new long[]{0x000000A000000002L});
    public static final BitSet FOLLOW_NUMBER_in_string_name3621 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_DOT_in_string_name3625 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_string_name3626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_structure_decl3637 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_structure_decl3641 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUAL_in_structure_decl3643 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_structure_decl3645 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_struct_element_in_structure_decl3649 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_END_CURLY_in_structure_decl3651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_struct_element3674 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_element3678 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_struct_name_in_struct_element3680 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_struct_element3685 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_struct_element3689 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_element3693 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_struct_name_in_struct_element3695 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_ID_in_struct_name3708 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_DOT_in_struct_name3710 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_struct_name3712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefix_in_result3721 = new BitSet(new long[]{0x0000808000400000L});
    public static final BitSet FOLLOW_set_in_result3724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_truefalse_in_result3738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_result3742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_truefalse0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ptr_type_in_data_type3761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_data_type3771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_derived_data_type_in_data_type3780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_agr_type_in_derived_data_type3797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_func_type_in_derived_data_type3801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiDim_array_type_in_agr_type3811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_struct_type_in_agr_type3815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiDim_vector_type_in_agr_type3819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_ANGULAR_in_multiDim_vector_type3830 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_multiDim_vector_type3834 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_multiDim_vector_type3836 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_vector_type_in_multiDim_vector_type3838 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_ANGULAR_in_multiDim_vector_type3840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_ANGULAR_in_vector_type3850 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_vector_type3854 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_vector_type3856 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_vector_type3858 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_ANGULAR_in_vector_type3860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_multiDim_array_type3870 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_multiDim_array_type3874 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_multiDim_array_type3876 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_array_type_in_multiDim_array_type3879 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_multiDim_array_type3881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_array_type3892 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_array_type3896 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_array_type3898 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_array_type3900 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_array_type3902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_array_type3906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_struct_type3917 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_struct_type3919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3932 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_func_type3941 = new BitSet(new long[]{0x0000000A22800000L});
    public static final BitSet FOLLOW_ptr_type_in_func_type3946 = new BitSet(new long[]{0x0000000048000000L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3949 = new BitSet(new long[]{0x0000000048000000L});
    public static final BitSet FOLLOW_agr_type_in_func_type3953 = new BitSet(new long[]{0x0000000048000000L});
    public static final BitSet FOLLOW_COMMA_in_func_type3961 = new BitSet(new long[]{0x4000000A62800000L});
    public static final BitSet FOLLOW_ptr_type_in_func_type3967 = new BitSet(new long[]{0x0000000A62800000L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_func_type3971 = new BitSet(new long[]{0x0000000A62800000L});
    public static final BitSet FOLLOW_agr_type_in_func_type3975 = new BitSet(new long[]{0x0000000A62800000L});
    public static final BitSet FOLLOW_62_in_func_type3984 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_END_PARANTHESIS_in_func_type3989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_function_data4000 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_function_data4002 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_function_data4004 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_function_data4006 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_function_data4008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_function_data4013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_ptr_type4023 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_derived_data_type_in_ptr_type4032 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_STAR_in_ptr_type4035 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_prefix_in_value4044 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_value4047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_synpred43_llvmGrammar2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_synpred47_llvmGrammar2149 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_synpred47_llvmGrammar2151 = new BitSet(new long[]{0x0000008000400002L});
    public static final BitSet FOLLOW_GLOBAL_PREFIX_in_synpred52_llvmGrammar2163 = new BitSet(new long[]{0x000000A000400000L});
    public static final BitSet FOLLOW_DOT_in_synpred52_llvmGrammar2165 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_synpred52_llvmGrammar2167 = new BitSet(new long[]{0x0000008000400002L});
    public static final BitSet FOLLOW_data_type_in_synpred52_llvmGrammar2177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred58_llvmGrammar2217 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_synpred58_llvmGrammar2219 = new BitSet(new long[]{0x0000008002400000L});
    public static final BitSet FOLLOW_LOCAL_PREFIX_in_synpred58_llvmGrammar2221 = new BitSet(new long[]{0x0000008000400000L});
    public static final BitSet FOLLOW_set_in_synpred58_llvmGrammar2224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_synpred71_llvmGrammar2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_synpred72_llvmGrammar2667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_synpred73_llvmGrammar2669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_synpred74_llvmGrammar2690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_synpred75_llvmGrammar2692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_synpred76_llvmGrammar2694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_synpred94_llvmGrammar2804 = new BitSet(new long[]{0x0000808003400180L});
    public static final BitSet FOLLOW_prefix_in_synpred94_llvmGrammar2808 = new BitSet(new long[]{0x0000808000400000L});
    public static final BitSet FOLLOW_set_in_synpred94_llvmGrammar2810 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_truefalse_in_synpred94_llvmGrammar2823 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred94_llvmGrammar2830 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_synpred94_llvmGrammar2832 = new BitSet(new long[]{0x0000808003400180L});
    public static final BitSet FOLLOW_prefix_in_synpred94_llvmGrammar2836 = new BitSet(new long[]{0x0000808000400000L});
    public static final BitSet FOLLOW_set_in_synpred94_llvmGrammar2838 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_truefalse_in_synpred94_llvmGrammar2851 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_START_PARANTHESIS_in_synpred95_llvmGrammar2962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_array_in_synpred97_llvmGrammar3013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structure_decl_in_synpred98_llvmGrammar3017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structure_obj_in_synpred99_llvmGrammar3021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_constant_in_synpred100_llvmGrammar3024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_synpred123_llvmGrammar3451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_synpred124_llvmGrammar3467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred126_llvmGrammar3473 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_synpred126_llvmGrammar3475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_synpred128_llvmGrammar3494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_synpred129_llvmGrammar3505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred131_llvmGrammar3509 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_synpred131_llvmGrammar3511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ptr_type_in_synpred148_llvmGrammar3761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred149_llvmGrammar3771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_synpred153_llvmGrammar3892 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_synpred153_llvmGrammar3896 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_synpred153_llvmGrammar3898 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_synpred153_llvmGrammar3900 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_synpred153_llvmGrammar3902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ptr_type_in_synpred155_llvmGrammar3946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred156_llvmGrammar3949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ptr_type_in_synpred157_llvmGrammar3967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIMITIVE_DATA_TYPE_in_synpred158_llvmGrammar3971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_agr_type_in_synpred159_llvmGrammar3975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_SQUARE_BR_in_synpred162_llvmGrammar4000 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NUMBER_in_synpred162_llvmGrammar4002 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_MUL_OPERATOR_in_synpred162_llvmGrammar4004 = new BitSet(new long[]{0x0000000A22800C00L});
    public static final BitSet FOLLOW_data_type_in_synpred162_llvmGrammar4006 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_END_SQUARE_BR_in_synpred162_llvmGrammar4008 = new BitSet(new long[]{0x0000000000000002L});

}