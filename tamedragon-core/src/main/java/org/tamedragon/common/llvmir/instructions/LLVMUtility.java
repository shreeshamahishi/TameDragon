package org.tamedragon.common.llvmir.instructions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

public class LLVMUtility {
	
	public static boolean checkIfNumberIsPowerOfTwo(long align2) {
		double align = align2;
		align = log2(align);
		int temp = (int) align;
		double temp2 = temp;
		if (temp2 != align)
			return false;
		else
			return true;
	}

	public static boolean isPowerOf2_32(int number){
		if(number <= 0)
			return false;

		if((number & (number -1)) == 0)
			return true;

		return false;

	}

	private static double logb(double a, double b) {
		return Math.log(a) / Math.log(b);
	}

	private static double log2(double a) {
		return logb(a, 2);
	}

	public static int binaryToDecimal(String binStr){
		char chArr[] = binStr.toCharArray();
		int retVal = 0;
		for(int i = 0; i < chArr.length; i++){
			char ch = chArr[i];
			if(ch == '1'){
				int toPower = chArr.length - (i +1);
				retVal += Math.pow(2, toPower);
			}
		}
		return retVal;
	}

	public static int getParamAttributeAsIntegerValue(Properties properties, String paramAttrAsString){
		String propertyAsStr = properties.getProperty(paramAttrAsString);

		if(propertyAsStr == null)
			return 0;

		return Integer.parseInt(properties.getProperty(paramAttrAsString));
	}
	
	public static boolean isDebugMode(Properties properties){
		
		if(properties == null){
			return true;
		}
		
		String debugProperty = properties.getProperty("debugMode");
		if(debugProperty != null && debugProperty.equalsIgnoreCase("Y") || debugProperty.equalsIgnoreCase("Yes")){
			return true;
		}
		
		return false;
	}

	public static int addAttrs(int retAttributeList, int retAttrs) throws InstructionUpdateException {
		return retAttributeList |= retAttrs;
	}

	public static String getAttributeAsString(Integer attrValue){
		String attrAsString = "";
		
		if(attrValue == null)
			return attrAsString;
		
		List<Integer> attrList = Attributes.getAttributesAsList(attrValue);
		Collections.sort(attrList);
		for(int i = 0; i < attrList.size(); i++){
			int attr = attrList.get(i).intValue();
			if(i < (attrList.size()-1))
				attrAsString += Attributes.getAttributeAsString(attr) + ","; 
			else
				attrAsString += Attributes.getAttributeAsString(attr); 
		}
		return attrAsString;
	}
	
	public static int getSizeForType(Properties properties, Type t) {
		int align = 0;
		String sizeStr = null;

		if (t.isInt1Type())
			sizeStr = properties.getProperty("i1Type");

		else if(t.isInt8Type())
			sizeStr = properties.getProperty("i8Type");

		else if (t.isInt16Type())
			sizeStr = properties.getProperty("i16Type");

		else if(t.isHalfType())
			sizeStr = properties.getProperty("HalfType");

		else if (t.isInt32Type()) 
			sizeStr = properties.getProperty("i32Type");

		else if(t.isFloatType())
			sizeStr = properties.getProperty("floatType");

		else if (t.isDerivedType()) {

			if(t.isPointerType()){
				String wordSize = properties.getProperty("wordsize");
				if(wordSize.equals("32"))
					sizeStr = "4";
				else if(wordSize.equals("64"))
					sizeStr = "8";
			}

			else if(t.isStructType()){
				StructType structType = (StructType)t;
				
				// For Union Type
				int nosOfMembers = structType.getElementSize();
				int maxSize = 0;
				if(structType.isUnion()){
					for(int i = 0; i < nosOfMembers; i++){
						Type type = structType.getTypeAtIndex(i);
						int size = getSizeForType(properties, type);
						if(maxSize < size)
							maxSize = size;
					}
					return maxSize;
				}
				else{
					// For StructType
					int totalSize = 0;
					for(int i = 0; i < nosOfMembers; i++){
						Type type = structType.getTypeAtIndex(i);
						int size = getSizeForType(properties, type);
						if(maxSize < size)
							maxSize = size;
						totalSize += size;
					}
					return totalSize;
				}
			}

			else if(t.isArrayType()){
				ArrayType arrayType = (ArrayType)t;
				Integer nosOfelements = new Long(arrayType.getNumElements()).intValue();
				Type containedType = arrayType.getContainedType();
				return nosOfelements * getSizeForType(properties, containedType);
			}

			else if(t.isVectorType()){
				//TODO
			}

			else if(t.isFunctionType()){
				//TODO
			}
		}
		else if (t.isDoubleType())
			sizeStr = properties.getProperty("doubleType");

		else if (t.isInt64Type())
			sizeStr = properties.getProperty("i64Type");

		else if (t.isFP128Type())
			sizeStr = properties.getProperty("FP128Type");

		else if (t.isPPC_FP128Type())
			sizeStr = properties.getProperty("PPC_FP128Type");

		else if (t.isX86_FP80Type())
			sizeStr = properties.getProperty("X86_MMXType");

		else if (t.isX86_MMXType())
			sizeStr = properties.getProperty("X86_MMXType");

		if(sizeStr != null)
			align = Integer.parseInt(sizeStr);
		
		return align;
	}
	
	public static int getPriorityForType(Properties properties,Type t) {
		int align = 0;
		String alignStr = null;
		
		if (t.isInt1Type())
			alignStr = properties.getProperty("i1TypePriority");

		else if(t.isInt8Type())
			alignStr = properties.getProperty("i8TypePriority");

		else if (t.isInt16Type())
			alignStr = properties.getProperty("i16TypePriority");

		else if(t.isHalfType())
			alignStr = properties.getProperty("HalfTypePriority");

		else if (t.isInt32Type()) 
			alignStr = properties.getProperty("i32TypePriority");

		else if(t.isFloatType())
			alignStr = properties.getProperty("floatTypePriority");

		else if (t.isDerivedType()) {

			if(t.isPointerType())
				alignStr = properties.getProperty("pointerTypePriority");

			else if(t.isStructType()){
				StructType structType = (StructType)t;
				
				// For Union Type
				int nosOfMembers = structType.getElementSize();
				int maxSize = 0;
				if(structType.isUnion()){
					for(int i = 0; i < nosOfMembers; i++){
						Type type = structType.getTypeAtIndex(i);
						int size = getSizeForType(properties, type);
						if(maxSize < size)
							maxSize = size;
					}
					return maxSize;
				}
				else{
					// For StructType
					for(int i = 0; i < nosOfMembers; i++){
						Type type = structType.getTypeAtIndex(i);
						int size = getSizeForType(properties, type);
						maxSize += size;
					}
					return maxSize;
				}
			}

			else if(t.isArrayType()){
				ArrayType arrayType = (ArrayType)t;
				Integer nosOfelements = new Long(arrayType.getNumElements()).intValue();
				Type containedType = arrayType.getContainedType();
				return nosOfelements * getSizeForType(properties, containedType);
			}

			else if(t.isVectorType()){
				//TODO
			}

			else if(t.isFunctionType()){
				//TODO
			}
		}
		else if (t.isDoubleType())
			alignStr = properties.getProperty("doubleTypePriority");

		else if (t.isInt64Type())
			alignStr = properties.getProperty("i64TypePriority");

		else if (t.isFP128Type())
			alignStr = properties.getProperty("FP128TypePriority");

		else if (t.isPPC_FP128Type())
			alignStr = properties.getProperty("PPC_FP128TypePriority");

		else if (t.isX86_FP80Type())
			alignStr = properties.getProperty("X86_FP80TypePriority");

		else if (t.isX86_MMXType())
			alignStr = properties.getProperty("X86_MMXTypePriority");

		if(alignStr != null)
			align = Integer.parseInt(alignStr);

		return align;
	}
	/**
	 * RoundUpAlignment - Round the specified value up to the next alignment
	 * boundary specified by Alignment.  For example, 7 rounded up to an
	 * alignment boundary of 4 is 8.  8 rounded up to the alignment boundary of 4
	 * is 8 because it is already aligned.
	 * @param value
	 * @param alignment
	 * @return
	 */
	public static int roundUpAlignment(int value, int alignment){
		if(!isPowerOf2_32(alignment)){
			try{
			throw new Exception("Alignment must be power of 2!");
			}
			catch(Exception e){
				System.exit(-1);
			}
		}
		return (value + (alignment-1)) & ~(alignment-1);
	}
	
	public static int getWordSize(Properties properties){
		String wordSize = properties.getProperty("wordsize");
		return Integer.parseInt(wordSize);
	}
	
	/*public static void changeIncludePath(Properties properties, String newpath, String comments){
		properties.put("includePath", newpath);
		FileWriter writer = null;
		try{
		writer = new FileWriter(COMPILER_SETTINGS_PATH);
		properties.store(writer, comments);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
			try{
				if(writer != null){
					writer.close();
					writer = null;
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}*/
	
	public static String getOriginalIncludePath(Properties properties){
		return properties.getProperty("includePath");
	}
	
	public static boolean compareBinaryExpr(BinaryOperator binaryOperator, Instruction instructn) {
		BinaryOperatorID binaryOperatorID = binaryOperator.getOpCode();
		if(instructn instanceof BinaryOperator){
			BinaryOperator aebBinaryOperator = (BinaryOperator) instructn;
			BinaryOperatorID aebBinaryOperatorID = aebBinaryOperator.getOpCode();
			Value aebBinaryOperatorFirstOp = aebBinaryOperator.getOperand(0);
			Value aebBinaryOperatorSecondOp = aebBinaryOperator.getOperand(1);
			Value binaryOperatorFirstOp = binaryOperator.getOperand(0);
			Value binaryOperatorSecondOp = binaryOperator.getOperand(1);

			// match current instruction's expression against those
			// in AEB, including commutativity
			if(binaryOperatorID == aebBinaryOperatorID && ((binaryOperator.isCommutative() && binaryOperatorFirstOp.equals(aebBinaryOperatorSecondOp) && binaryOperatorSecondOp.equals(aebBinaryOperatorFirstOp)) || (binaryOperatorFirstOp.equals(aebBinaryOperatorFirstOp) && binaryOperatorSecondOp.equals(aebBinaryOperatorSecondOp)))){
				return true;
			}
		}
		return false;
	}
	
	public static void removePhiNodeIfIncomingValuesAreSame(PhiNode phiNode) {
		// If PhiNode has same incoming values then remove this PhiNode and replace it with one of the incoming values
		int noOfPredessors = phiNode.getNumPredecessors();
		Value previousPredessor = null;
		boolean hasSamePredessor = false;
		for(int num=0; num<noOfPredessors; num++){
			try {
				if(num == 0){
					previousPredessor = phiNode.getIncomingValue(num);
					continue;
				}
				Value currentPredessor = phiNode.getIncomingValue(num);
				if(previousPredessor != currentPredessor){
					hasSamePredessor = false;
					break;
				}
				else
					hasSamePredessor = true;

			} catch (InstructionDetailAccessException e) {
				e.printStackTrace();
			}
		}
		if(hasSamePredessor){
			phiNode.getParent().removeInstruction(phiNode);
			phiNode.replaceAllUsesOfThisWith(previousPredessor);
		}
	}

	private static boolean compareBranchInstr(BranchInst instruction, BranchInst commonInstruction) throws InstructionUpdateException, InstructionDetailAccessException {
		boolean isSame  = false;
		int numOfOprnds1 = instruction.getNumOperands();
		int numOfOprnds2 = commonInstruction.getNumOperands();
		
		if(numOfOprnds1 == numOfOprnds2){
			for(int i = 0; i < numOfOprnds1; i++){
				Value oprnd1 = instruction.getOperand(i);
				Value oprnd2 = commonInstruction.getOperand(i);
				
				if(oprnd1 instanceof Instruction && oprnd2 instanceof Instruction){
					isSame = compareInstructions((Instruction)oprnd1, (Instruction)oprnd2);
					if(!isSame)
						return false;
				}
				else if(oprnd1 == oprnd2){
					return true;
				}
				else
					isSame = false;
			}
		}
		
		return isSame;
	}

	public static boolean compareInstructions(Instruction oprnd1,	Instruction oprnd2) 
					throws InstructionUpdateException, InstructionDetailAccessException {
		boolean isSame = false;
		if(oprnd1 instanceof BranchInst && oprnd2 instanceof BranchInst)
			isSame = compareBranchInstr((BranchInst)oprnd1, (BranchInst)oprnd2);
		else if(oprnd1 instanceof BinaryOperator && oprnd2 instanceof BinaryOperator)
			isSame = compareBinaryExpr((BinaryOperator)oprnd1, (BinaryOperator)oprnd2);
		else if(oprnd1 instanceof CmpInst && oprnd2 instanceof CmpInst)
			isSame = compareCmpExpr((CmpInst)oprnd1, (CmpInst)oprnd2);
		
//		if(isSame){
//			int numOfUsers = oprnd1.getNumUses();
//			// replace the all users of this instruction with instruction2
//			for(int j = 0; j < numOfUsers; j++){
//				Value user = oprnd1.getUserAt(j);
//				
//				if(user instanceof PhiNode){
//					PhiNode phiNode = (PhiNode)user;
//					BasicBlock incomingBlk = phiNode.getIncomingBlock(oprnd1);
//					int index = phiNode.getBasicBlockIndex(incomingBlk);
//					BasicBlock parentBB = oprnd2.getParent();
//					CFG cfg = parentBB.getParent().getCfg();
//					List<BasicBlock> sucessors = cfg.getSuccessors(parentBB);
//					BasicBlock sucessor = (BasicBlock) sucessors.get(0);
//					while(sucessor != phiNode.getParent()){
//						parentBB = sucessor;
//						sucessor = cfg.getSuccessors(sucessor).get(0);
//					}
//					phiNode.setIncomingValueAndBasicBlock(index, oprnd2, parentBB);
//				}
//			}
//		}
		
		return isSame;
	}

	private static boolean compareCmpExpr(CmpInst oprnd1, CmpInst oprnd2) throws InstructionUpdateException, InstructionDetailAccessException {
		Predicate predicate1 = oprnd1.getPredicate();
		Predicate predicate2 = oprnd1.getPredicate();
		
		if(predicate1 == predicate2){
			Value firstOprnd1 = oprnd1.getOperand(0);
			Value firstOprnd2 = oprnd2.getOperand(0);
			Value secondOprnd1 = oprnd1.getOperand(1);
			Value secondOprnd2 = oprnd2.getOperand(1);
			
			if(firstOprnd1 instanceof Instruction && firstOprnd2 instanceof Instruction){
				boolean isFirstOpSame = compareInstructions((Instruction)firstOprnd1, (Instruction)firstOprnd2);
				if(isFirstOpSame){
					if(secondOprnd1 instanceof Instruction && secondOprnd2 instanceof Instruction){
						boolean isSecondOprndSame = compareInstructions((Instruction)secondOprnd1, (Instruction)secondOprnd2);
						if(isSecondOprndSame)
							return true;
						else
							return false;
					}
					else if(secondOprnd1 == secondOprnd2)
						return true;
					else
						return false;
				}
				else
					return false;
			}
			else if(firstOprnd1.equals(firstOprnd2)){
				if(secondOprnd1 instanceof Instruction && secondOprnd2 instanceof Instruction){
					boolean isSecondOprndSame = compareInstructions((Instruction)secondOprnd1, (Instruction)secondOprnd2);
					if(isSecondOprndSame)
						return true;
					else
						return false;
				}
				else if(secondOprnd1.equals(secondOprnd2))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}

	public static String convertIntoLLVMFloatingPointValue(String val) {
		
		
		 // This is the starting double value
        double doubleInput = Double.parseDouble(val);
        
        // Convert the starting value to the equivalent value in a long
        long doubleAsLong = Double.doubleToRawLongBits( doubleInput );

        // Convert the long to a String
        String doubleAsString = Long.toHexString( doubleAsLong );
        String binStr  = new BigInteger(doubleAsString, 16).toString(2);
        
        while(binStr.length() < 64){
        	binStr = "0"+binStr;
        }
        
        char charArr[] = binStr.toCharArray();
        // make lowest 28 bits as zeros
        for(int i = 63; i > 35; i--)
        	charArr[i] = '0';
        
        binStr = new String(charArr);
        
        String hexStr  = new BigInteger(binStr, 2).toString(16);
        hexStr = hexStr.toUpperCase();
        hexStr = "0x" + hexStr;
		return hexStr;
	}

	public static Properties getDefaultProperties() {
		Properties properties = new Properties();
		properties.setProperty("target", "MIPS");
		properties.setProperty("nocapture","2097152");
		properties.setProperty("uwtable","1073741824");
		properties.setProperty("i8arrayAlignment","1");
		properties.setProperty("MultiDimensionalArrayAlignment","16");
		properties.setProperty("replaceTrigraphSequences","YES");
		properties.setProperty("readonly","1024");
		properties.setProperty("inreg","8");
		properties.setProperty("noinline","2048");
		properties.setProperty("X86_MMXType","16");
		properties.setProperty("noimplicitfloat","8388608");
		properties.setProperty("readnone","512");
		properties.setProperty("wordsize","32");
		properties.setProperty("includePath","E:/CompilerVision/trunk/Compilers/resources/include");
		properties.setProperty("naked","16777216");
		properties.setProperty("noalias","64");
		properties.setProperty("returns_twice","536870912");
		properties.setProperty("i64Alignment","8");
		properties.setProperty("signext","2");
		properties.setProperty("floatType","4");
		properties.setProperty("pointerType","8");
		properties.setProperty("FP128Alignment","16");
		properties.setProperty("byval","128");
		properties.setProperty("doubleType","8");
		properties.setProperty("projectPath","E:/CompilerVision/trunk/Compilers/resources/projectPath");
		properties.setProperty("i1Alignment","1");
		properties.setProperty("doubleAlignment","8");
		properties.setProperty("sspreq","32784");
		properties.setProperty("optsize","8196");
		properties.setProperty("FP128Type","16");
		properties.setProperty("alwaysinline","4096");
		properties.setProperty("X86_MMXAlignment","16");
		properties.setProperty("HalfType","8");
		properties.setProperty("i16Alignment","2");
		properties.setProperty("i32Alignment","4");
		properties.setProperty("nonlazybind","2147483648");
		properties.setProperty("structureAlignment","4");
		properties.setProperty("i16Type","2");
		properties.setProperty("i1Type","1");
		properties.setProperty("PPC_FP128Type","16");
		properties.setProperty("nest","256");
		properties.setProperty("zeroext","1");
		properties.setProperty("i8Type","1");
		properties.setProperty("floatAlignment","4");
		properties.setProperty("i64Type","8");
		properties.setProperty("i32Type","4");
		properties.setProperty("HalfTypeAlignment","8");
		properties.setProperty("noreturn","4");
		properties.setProperty("sret","16");
		properties.setProperty("i8Alignment","1");
		properties.setProperty("noredzone","4194304");
		properties.setProperty("pointerAlignment","8");
		properties.setProperty("newLine","\r\n");
		properties.setProperty("nounwind","32");
		properties.setProperty("inlinehint","33554432");
		properties.setProperty("arrayAlignment","4");
		properties.setProperty("ssp","16392");
		properties.setProperty("PPC_FP128Alignment","16");
		properties.setProperty("debugMode","YES");
		return properties;
	}
}
