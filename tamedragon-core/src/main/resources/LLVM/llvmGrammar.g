grammar llvmGrammar;

options { language=Java;
	output=AST;
    backtrack=true;
    memoize=true;
}
         
                 
@header{
package com.compilervision.common.llvmir.utils;
import com.compilervision.common.llvmir.irdata.*;

import com.compilervision.common.llvmir.instructions.*;
import com.compilervision.common.llvmir.types.*;
import com.compilervision.common.llvmir.types.exceptions.*;
import com.compilervision.common.llvmir.math.*;

import com.compilervision.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import com.compilervision.common.llvmir.semanticanalysis.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
}

@lexer::header{ package com.compilervision.common.llvmir.utils; 
                import com.compilervision.common.llvmir.types.*;
                import com.compilervision.common.llvmir.semanticanalysis.*;
               }
 
@members {

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
}			 	      

// TERMINALS

fragment
XOR : 'xor';
PHI : 'phi';

UNREACHABLE : 'unreachable' ;
TRUE : 'true';
FALSE : 'false';
ALIGN : ' align ';
LINKAGE_TYPE : 'private'|'common'|'extern'|'internal'|'external';
RETURN_ATTR : 'noalias';
ZEROINITIALIZER : 'zeroinitializer';
CAST_TYPE :	'trunc'	| 'zext' | 'sext' | 'fptrunc' | 'fpext'	| 'fptoui' | 'fptosi' | 'uitofp' | 'sitofp' |'ptrtoint' | 'inttoptr' | 'bitcast';
ARGUMENT_ATTRIBUTE :'byval'| 'nest'| 'structret'|'nocapture';
PARAMETER_ATTRIBUTE : 'zeroext' | 'signext' | 'inreg' | 'byval' | 'sret' | 'nocapture' | 'nest';
FUNCTION_ATTRIBUTE : 'nounwind' | 'uwtable'| 'noreturn' | 'readonly' | 'ssp'| 'optsize';
CALLING_CONV : 'ccc' | 'fastcc' | 'coldcc' | 'cc 10' | 'cc 11';
CONDITION : 'eq' | 'ne' | 'ugt' | 'uge' | 'ult' | 'ule' | 'sgt' | 'sge' | 'slt' |'sle' |'olt' |'ogt'| 'oeq'| 'one'| 'oge' ;
NULL_CHAR : '\\00';
BIN_OPR_STR : ' add ' | 'fadd' | 'sub' | 'fsub' |'mul' | 'fmul' |'udiv'|'sdiv'|'fdiv'| 'urem'|'srem'|'frem' | ' xor ' | 'shl' | 'shr' | 'lshr'|'rshr'|'ashr' |'and' | 'or';
ATOMIC_ORDERING : 'unordered'|'monotonic'|'acquire'|'release'|'acq_rel'|'seq_cst';
string : ID NULL_CHAR;
PRIMITIVE_DATA_TYPE : ( 'i1' | 'i8' | 'i16' | 'i32' | 'i64' | 'float' | 'double' | 'void'  
					 | 'label' |'f32' |'f64'|'f80'|'v64'|'v128'|'a0'|'n8');
prefix : GLOBAL_PREFIX |LOCAL_PREFIX;
EQUAL : '=';
COMMA : ',';
GLOBAL_PREFIX : '@';
LOCAL_PREFIX : '%';
STAR : '*';
START_PARANTHESIS : '(';
END_PARANTHESIS : ')';
START_CURLY : '{';
END_CURLY : '}';
START_ANGULAR : '<';
END_ANGULAR : '>';
START_SQUARE_BR : '[';
END_SQUARE_BR : ']';
DOT  : '.';
MUL_OPERATOR : ' x ';
NUMBER  : ('+'|'-')? '0'..'9'+ ;
DECIMAL_LITERAL : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

fragment
OCTAL_LITERAL : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HEX_LITERAL : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix
	:	('u'|'U')? ('l'|'L')
	|	('u'|'U')  ('l'|'L')?
	;

FLOATING_LITERAL
    :  ('+'|'-')?  ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ Exponent? FloatTypeSuffix
	|   HEX_LITERAL
	;

fragment
Exponent :	 ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

STRING_LITERAL :	 '"' ( EscapeSequence | ~('\\'|'"') )* '"' ;

fragment
EscapeSequence :   '\\' ('a'|'b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    		   |   OctalEscape 
    		   ;	

fragment
OctalEscape :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    		|   '\\' ('0'..'7') ('0'..'7')
    		|   '\\' ('0'..'7')
    ; 	

fragment
LETTER
	:(	'$'
	|	'A'..'Z'
	|	'a'..'z'
	|	'_' )+
	;

ID :LETTER (LETTER|'0'..'9'|'.')* ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ { $channel = HIDDEN; };

LINE_COMMENT : ';' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};

// NON-TERMINALS

llvm : llvm_element;

llvm_element: target_machine_info? (function_def 
									| global_var_list 
									| function_declaration )+ ;

target_machine_info : target_datalayout;

target_datalayout : e1='target datalayout' EQUAL target_Data_ID 
        {
          setTargetData($target_Data_ID.text, $e1.line, $e1.pos);
        };
		
target_Data_ID :   ' "' ID '-p:'  NUMBER ':' NUMBER ':' NUMBER '-' ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '-')+  
						('S32"' | ( PRIMITIVE_DATA_TYPE ':' NUMBER ':' NUMBER '"'));

global_var_list : global_var_list_element; 

global_var_list_element :  global_var ;

function_declaration : e0='declare' LINKAGE_TYPE? RETURN_ATTR? (data_type) GLOBAL_PREFIX e1=ID START_PARANTHESIS
          parameter_list? END_PARANTHESIS function_attr_list
	{
           setFunction($LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text, $function_attr_list.text, false, 
           				$e0.line, $e0.pos);
    };

parameter_list : (e1=data_type e4=ARGUMENT_ATTRIBUTE?)
	{
		addArgument($e1.text, null, $e4.text, false);
    } parameters* ;
                    
parameters :(COMMA (e1=data_type e4=ARGUMENT_ATTRIBUTE?)*  (var='...')? )
           {
                boolean hasEllipses = false;
                if($var.text != null){
                	hasEllipses = true;
                }
                
                addArgument($e1.text,null,$e4.text,hasEllipses);
                };

function_def: e0='define' LINKAGE_TYPE? RETURN_ATTR?(data_type) GLOBAL_PREFIX e1=ID START_PARANTHESIS
					argument_list* END_PARANTHESIS
					function_attr_list ( START_CURLY 
									  basic_blocks [$LINKAGE_TYPE.text, $RETURN_ATTR.text, $data_type.text, $e1.text,
									  $function_attr_list.text, $e0.line, $e0.pos] 
						              END_CURLY )?;
										
function_attr_list : FUNCTION_ATTRIBUTE*;

argument_list :(e1=data_type e2=result e3=ARGUMENT_ATTRIBUTE?) 
			   {						 
			   		addArgument($e1.text,$e2.text,$e3.text,false);
			   } argument* ;
						 
argument :(COMMA e1=data_type e2=result e3=ARGUMENT_ATTRIBUTE?)
         {
		     addArgument($e1.text, $e2.text, $e3.text, false);
		 };
					
basic_blocks [String linkage_type,String return_attr,String data_type,String name, String function_attr, int line, int pos]
		@init{
			setFunction(linkage_type,return_attr,data_type,name, function_attr,true, line, pos);	
		}: instruction_set* ;

instruction_set  : binary_instruction 
				 | memory_rel_instruction 
				 | terminator_instruction 
				 | other_instruction 
				 | cast_instruction ;

binary_instruction : num=result EQUAL oprstr=BIN_OPR_STR flag=('nsw'|'nuw'|'exact')? typ=data_type o1=result COMMA o2=result						
				   {				
				   		setBinaryOperator($num.text,$typ.text,$o1.text,$o2.text,$oprstr.text, $flag.text, $oprstr.line, $oprstr.pos);	
				   }; 				

memory_rel_instruction :  load | alloca | store | getelementptr ;
				
alloca : res=result e=EQUAL ' alloca ' d=data_type  (COMMA (data_type arrayLength=result COMMA)? ALIGN alignV=NUMBER)? 
	   {									
	   		setAllocaInstr($res.text,$d.text,$arrayLength.text, $alignV.text, $e.line, 0);					           					
	   };
	   	
load : res=result e0=EQUAL 'load' data_type e=result (COMMA ALIGN NUMBER)?
	 {							
	 	setLoadInstr($res.text,$e.text,$data_type.text, $e0.line, 0);
	 };
				
store : a='store' atomic=ATOMIC_ORDERING? isVolatile='volatile'? a3=data_type a1=result COMMA
		data_type  a2=result  (COMMA ALIGN NUMBER)*
	  {
	  		setStoreInstr($a1.text, $a2.text, $a3.text, $atomic.text, $isVolatile.text, $a.line, $a.pos);
	  };
			
getelementptr : id=result e=EQUAL getelementptr_rhs[$id.text, $e.line];
			
getelementptr_rhs[String name, int line] : 'getelementptr'inb='inbounds'? START_PARANTHESIS? ptr = ptr_type 
											element=element_name COMMA off= offset END_PARANTHESIS?
				    					 {	    		
											setGetElementPtr(name,$element.text,$ptr.text, $off.text,$inb.text, line, 0);
				    					 };
				     
element_name:  (LOCAL_PREFIX (ID | NUMBER)+ )|(GLOBAL_PREFIX '.'?(ID | NUMBER)+ |data_type )|'undef' ;  

offset : (GLOBAL_PREFIX ID COMMA)? ( data_type LOCAL_PREFIX? (NUMBER | ID))(COMMA data_type LOCAL_PREFIX? (NUMBER | ID) )*;

terminator_instruction : ret | br | switchInstr |unreachable ;
  
ret :  r='ret' d=data_type (e1=result)?
    {
  		setRetInstr($d.text, $e1.text, $r.line, $r.pos);
   	};

br : ( b='br' data_type ( cond = result) COMMA 'label' LOCAL_PREFIX ifT=NUMBER COMMA 'label' LOCAL_PREFIX ifF=NUMBER )
   {
   		setBranchInstr($ifT.text, $ifF.text, $cond.text, $b.line, $b.pos);
   } 
   | ( b='br' 'label'  LOCAL_PREFIX ifT=(NUMBER |ID) )
   {
   		setBranchInstr($ifT.text, null, null, $b.line, $b.pos);			    	
   };
  				
switchInstr : s='switch' data_type e1=result COMMA 'label' e2=result START_SQUARE_BR 
			  e=cases_list[$e1.text, $e2.text, $s.line] END_SQUARE_BR;
			  
cases_list [String switchOn,String defaultBB, int line]
		   @init{	
			   setSwitchInstr(switchOn,defaultBB, line, 0);							
		    }: cases*;		
							
cases :	(data_type e1=(NUMBER | ID) COMMA 'label' e2 = result)
  	  {		
  	  		String typeStr = $data_type.text;
  			String condition = $e1.text;
  			String target = $e2.text;
  			setSwitchCase(typeStr,condition,target, $e1.line, $e1.pos);
  	  };
  						
unreachable : UNREACHABLE
  		    {
  		    	UnreachableInstrData instrData = new UnreachableInstrData();
  		        list.add(instrData);
  		    };			
	
other_instruction : cmp 
 				   | phi 
 				   | call 
 				   | select;
 
cmp :id1= result e=EQUAL (compr = 'icmp'|'fcmp') pre=CONDITION typestr=data_type
     			      o1 = result  COMMA  o2 = result
  	{   
  		String compare = $compr.text == null? "fcmp" : "icmp";
  		createAndSetIcmpData($id1.text, compare, $pre.text, $typestr.text, $o1.text, $o2.text, $e.line, 0);			
  	};

phi : num=result e=EQUAL PHI typeStr=data_type incoming=incoming_list
	{
		setPhiNode($num.text,$typeStr.text,$incoming.text, $e.line, 0);
	}; 

incoming_list : START_SQUARE_BR( 'true'|'false'|result|'undef') COMMA result END_SQUARE_BR
				(COMMA START_SQUARE_BR( 'true'|'false'|result|'undef' ) COMMA result END_SQUARE_BR)+;
						
call : (name=result	EQUAL)? tail='tail'? c='call' callingc=CALLING_CONV? parameterAttr=PARAMETER_ATTRIBUTE? 
		typeStr=data_type func_name START_PARANTHESIS para=parameter END_PARANTHESIS functionAttr=FUNCTION_ATTRIBUTE?
	 {
	 	setCallInstr($name.text, $callingc.text, $tail.text, $para.text, $typeStr.text, $func_name.text, 
	 				 $parameterAttr.text,$functionAttr.text, $c.line, 0);
	 };

func_name :(prefix(NUMBER | ID));
		    
parameter : ((data_type ((prefix?(NUMBER | FLOATING_LITERAL | ID))|truefalse)
			(COMMA data_type ((prefix?(NUMBER | FLOATING_LITERAL | ID))|truefalse))*))? 
			| func_type;	

select : name=result EQUAL t='select' typeStr=data_type condValue=result COMMA 
		 typeStr1=data_type valueStr1=result COMMA 
		 typeStr2=data_type valueStr2=result
       {
       		setSelectInstr($name.text,$typeStr.text, $condValue.text, $typeStr1.text, $valueStr1.text,
       						$typeStr2.text, $valueStr2.text, $t.line, 0);
       };
		
cast_instruction : name = result e=EQUAL cast_instr_rhs [$name.text, $e.line];

cast_instr_rhs[String result, int line] : type=CAST_TYPE START_PARANTHESIS? source=data_type 
										  value1=result 'to' target=data_type END_PARANTHESIS? 
										{
											setCastInstr(result, $type.text, $source.text, 
											$value1.text, $target.text, line, 0);
										};

global_var :global_array | structure_decl | structure_obj |string_constant | global_variable ;

global_array :  e=result e0=EQUAL e1=LINKAGE_TYPE? ('global')? (unnamed='unnamed_addr')? 
				e2='constant'? typeSt = array_data_type?  initial=list_of_initial_values COMMA ALIGN align=NUMBER			
			{	
				setGlobalVariable($e.text, $e1.text, $typeSt.text, $initial.text, $e2.text, 
								  $align.text, $unnamed.text, "array", $e0.line, 0);
			};
	
array_data_type : (data_type );
	 
list_of_initial_values	: (e0=START_SQUARE_BR(data_type (array_initial_value| getelementptr_rhs[null, $e0.line])) 
						  (COMMA data_type (array_initial_value| getelementptr_rhs[null, $e0.line]))* END_SQUARE_BR ) 
						  | ZEROINITIALIZER 
						  | ID STRING_LITERAL; 	

array_initial_value : (NUMBER |STRING_LITERAL) | list_of_initial_values;

global_variable : e=result  e0=EQUAL (e1 = LINKAGE_TYPE)?  ('global')? (e2='constant')?  
				  typeSt=data_type (d=global_variable_initial_value COMMA ALIGN align= NUMBER)? 
      			{   
      				setGlobalVariable($e.text, $e1.text, $typeSt.text, $d.ini, $e2.text, $align.text, null,
      				"global_variable", $e0.line, 0);
      			};
      
global_variable_initial_value returns [String ini] : (initial=NUMBER 
												 | cast_instr_rhs[null, $initial.line] 
												 | getelementptr_rhs[null, $initial.line]) 
  												 {
  												 	$ini = $initial.text;
  												 };
	
structure_obj : e=result eq=EQUAL e1=LINKAGE_TYPE? 'global'? (e2='constant')? LOCAL_PREFIX typeSt=ID  
				(zeroinitial=ZEROINITIALIZER | init =struct_init_value) COMMA ALIGN align=NUMBER
			  {
			  	String initial = (zeroinitial != null)?	$zeroinitial.text : $init.text;
				setGlobalVariable($e.text, $e1.text, $typeSt.text, initial, $e2.text, $align.text, null, "object", $eq.line, 0);
			  };
			  
struct_init_value : START_CURLY (data_type |(LOCAL_PREFIX ID))
					(NUMBER |LETTER |ID STRING_LITERAL |'undef' |array_initial_value)
					(COMMA ((data_type)|(LOCAL_PREFIX ID))( NUMBER|LETTER|ID STRING_LITERAL|'undef'|array_initial_value))* END_CURLY; 		

string_constant :  e=string_name eq=EQUAL e1=LINKAGE_TYPE? unnamed='unnamed_addr' e2='constant' typeSt=data_type ID 
				   initial=STRING_LITERAL  (COMMA ALIGN align=NUMBER)?    
        		{       
        			setGlobalVariable($e.text, $e1.text, $typeSt.text, $initial.text, $e2.text, $align.text, 
        							  $unnamed.text,"string_constant", $eq.line, 0);
        		};
        		
string_name : '@' '.'?ID NUMBER? ('.'ID)? ;

structure_decl : LOCAL_PREFIX name1=ID EQUAL 'type {' selement=struct_element END_CURLY
			   {					
					setStruct($name1.text, $selement.text, $name1.line, $name1.pos);
			   };
			   
struct_element	: (data_type |(LOCAL_PREFIX struct_name)) (COMMA ((data_type)|(LOCAL_PREFIX struct_name)))*;

struct_name :  ID DOT ID ;

result : prefix? (NUMBER | ID | FLOATING_LITERAL) | truefalse | 'undef';

truefalse : TRUE|FALSE;

data_type : (ptr_type 
			| e=PRIMITIVE_DATA_TYPE {updateDataTypeLineAndPos($e.line, $e.pos);}
			| derived_data_type   
			);

derived_data_type : (agr_type | func_type);

agr_type : (multiDim_array_type | struct_type | multiDim_vector_type);

multiDim_vector_type : e=START_ANGULAR {updateDataTypeLineAndPos($e.line, $e.pos);} NUMBER MUL_OPERATOR vector_type END_ANGULAR;

vector_type : e=START_ANGULAR {updateDataTypeLineAndPos($e.line, $e.pos);} NUMBER MUL_OPERATOR PRIMITIVE_DATA_TYPE END_ANGULAR;

multiDim_array_type : e=START_SQUARE_BR {updateDataTypeLineAndPos($e.line, $e.pos);} NUMBER MUL_OPERATOR (array_type)END_SQUARE_BR;

array_type : (e=START_SQUARE_BR {updateDataTypeLineAndPos($e.line, $e.pos);} NUMBER MUL_OPERATOR data_type END_SQUARE_BR) |data_type;

struct_type : (e=LOCAL_PREFIX ID {updateDataTypeLineAndPos($e.line, $e.pos);});

func_type : e1=PRIMITIVE_DATA_TYPE? 
			e2=START_PARANTHESIS {updateDataTypeLineAndPos($e2.line, $e2.pos);} (ptr_type |PRIMITIVE_DATA_TYPE | agr_type) 
			(COMMA  (( ptr_type | PRIMITIVE_DATA_TYPE | agr_type  )* | ('...')))?END_PARANTHESIS;
		
function_data : (START_SQUARE_BR NUMBER MUL_OPERATOR data_type END_SQUARE_BR) | data_type;

ptr_type :(e=PRIMITIVE_DATA_TYPE {updateDataTypeLineAndPos($e.line, $e.pos);}
		 | derived_data_type) STAR+;

value : prefix? NUMBER;
