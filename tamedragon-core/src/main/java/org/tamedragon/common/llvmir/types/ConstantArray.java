package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

/**
 * Constant Array Declarations
 * @author ipsg
 *
 */
public class ConstantArray extends Constant {

	private List<Constant> val;
	private ArrayType arrayType;

	public ConstantArray(ArrayType arrayType, List<Constant> val) {
		super(arrayType, val);
		this.arrayType = arrayType;
		this.val = val;
	}

	/**
	 * ConstantArray accessors
	 * @param arrayType
	 * @param values
	 * @return
	 */
	public static Constant get(ArrayType arrayType, List<Constant> values) throws Exception{
		for (int i = 0, e = values.size(); i != e; ++i) {
			Type valueType = values.get(i).getType();
			Type containedType = arrayType.getContainedType();

			if(valueType != containedType) { 
				Type containedType2 = null;
				Type containedType3 = null;
				if(valueType.isArrayType() && containedType.isPointerType()){
					ArrayType arrayType2 = (ArrayType)valueType;
					PointerType pointerType = (PointerType)containedType;
					containedType2 = arrayType2.getContainedType();
					containedType3 = pointerType.getContainedType();
				}
				else if(valueType.isArrayType() && containedType.isArrayType()){
					ArrayType arrayType2 = (ArrayType)valueType;
					ArrayType arrayType3 = (ArrayType)containedType;
					containedType2 = arrayType2.getContainedType();
					containedType3 = arrayType3.getContainedType();
				}
				if(containedType2 == null || containedType3 == null || containedType2 != containedType3)
					throw new Exception("Wrong type in array element initializer");
			}

		}
		CompilationContext pImpl = arrayType.getCompilationContext();
		// If this is an all-zero array, return a ConstantAggregateZero object
		if (!values.isEmpty()) {
			Constant C = values.get(0);
			if (!C.isNullValue())
				return pImpl.constantsTy.getOrCreate(arrayType, values);

			for (int i = 1, e = values.size(); i != e; ++i)
				if (values.get(i) != C)
					return pImpl.constantsTy.getOrCreate(arrayType, values);
		}
		return null;
		// return ConstantAggregateZero::get(Ty);
	}

	/**
	 *   This method constructs a ConstantArray and initializes it with a text
	     string. The default behavior (AddNull==true) causes a null terminator to
	     be placed at the end of the array. This effectively increases the length
	     of the array by one (you've been warned).  However, in some situations 
	     this is not desired so if AddNull==false then the string is copied without
	     null termination.
	 * @return
	 */
	public static Constant get(String initializer, boolean addNull, CompilationContext compilationContext){
		List<Constant> elementVals = new ArrayList<Constant>();
		IntegerType integerType = Type.getInt8Type(compilationContext, false);
		int length = initializer.length();

		for(int i = 0; i < length; i++){
			char ch = initializer.charAt(i);

			if(i < (length - 1)){
				char temp = initializer.charAt(i + 1);
				if(ch == '\\' && temp == 'n'){
					ch = '\n';
					i++;
				}
				if(ch == '\\' && temp == 't'){
					ch = '\t';
					i++;
				}
			}

			int v = (int)ch;
			APInt val = new APInt(8, ULong.valueOf(v), false);
			ConstantInt constantInt = null;
			try {
				constantInt = new ConstantInt(integerType, val);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			elementVals.add(constantInt);
		}

		if(addNull){
			APInt val = new APInt(8, ULong.valueOf("000"), false);
			ConstantInt constantInt = null;
			try {
				constantInt = new ConstantInt(integerType, val);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			elementVals.add(constantInt);
		}

		ArrayType arrayType = null;
		try {
			arrayType = Type.getArrayType(compilationContext, integerType, elementVals.size());
		} catch (TypeCreationException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}

		Constant constant = null;
		try {
			constant = get(arrayType, elementVals);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return constant;
	}

	/**
	 *   isString - This method returns true if the array is an array of i8 and
	     the elements of the array are all ConstantInt's.
	 * @return boolean
	 */
	public boolean isString() {
		// Check the element type for i8...
		if (!((ArrayType)getType()).getContainedType().isInt8Type())
			return false;
		// Check the elements to make sure they are all integers, not constant
		// expressions.
		for (int i = 0, e = getNumOperands(); i != e; ++i)
			if (!(getOperand(i) instanceof ConstantInt))
				return false;
		return true;
	}

	/**
	 * isCString - This method returns true if the array is a string (see
	   isString) and it ends in a null byte \\0 and does not contains any other
	   null bytes except its terminator.
	 * @return boolean
	 */
	public boolean isCString() {
		// Check the element type for i8...
		if (!((ArrayType)getType()).getContainedType().isInt8Type())
			return false;

		// Last element must be a null.
		if (!((Constant)getOperand(getNumOperands()-1)).isNullValue())
			return false;
		// Other elements must be non-null integers.
		for (int i = 0, e = getNumOperands()-1; i != e; ++i) {
			if (!(getOperand(i) instanceof ConstantInt))
				return false;
			if (((Constant)getOperand(i)).isNullValue())
				return false;
		}
		return true;
	}

	/**
	 * getAsString - If this array is isString(), then this method converts the
	   array to an String and returns it.  Otherwise, it asserts out.
	 * @return String
	 */
	public String getAsString() {
		assert(isString()) : "Not a string!";
		return convertToString(getNumOperands());
	}

	/**
	 * getAsCString - If this array is isCString(), then this method converts the
	   array (without the trailing null byte) to an std::string and returns it.
	   Otherwise, it asserts out.
	 * @return
	 */
	public String getAsCString() {
		assert(isCString()) : "Not a string!";
		return convertToString(getNumOperands() - 1);
	}

	private String convertToString(int numOperands) {
		char[] chArr = new char[numOperands];
		for(int i = 0; i < numOperands; i++){
			ConstantInt constantInt = (ConstantInt)getOperand(i);
			APInt apInt = constantInt.getApInt();
			int val = Integer.valueOf(apInt.toString());
			char ch =(char)val;
			chArr[i] = ch;
		}
		return new String(chArr);
	}

	public List<Constant> getVal() {
		return val;
	}

	public ArrayType getArrayType() {
		return arrayType;
	}

	@Override
	public String toString() {
		String str = "";
		str = str + getArrayType().toString() + " ";
		if(isString() || isCString()){
			String s = getAsString();
			if(s.contains("\n"))
				s = s.replaceAll("\n", "\\\\0A");
			if(s.contains("\0"))
				s = s.replaceAll("\0", "\\\\00");
			if(s.contains("\t"))
				s = s.replaceAll("\t", "\\\\09");
			str = str + "c\"" + s + "\"";
		}
		else{
			str = str + getVal().toString();
		}
		return str;
	}

	/**
	 * 
  friend struct ConstantCreator<ConstantArray, ArrayType,
                                    std::vector<Constant*> >;
  ConstantArray(const ConstantArray &);      // DO NOT IMPLEMENT

public:

  /// Transparently provide more efficient getOperand methods.
  DECLARE_TRANSPARENT_OPERAND_ACCESSORS(Constant);

  /// getType - Specialize the getType() method to always return an ArrayType,
  /// which reduces the amount of casting needed in parts of the compiler.
  ///
  inline ArrayType *getType() const {
    return reinterpret_cast<ArrayType*>(Value::getType());
  }

  virtual void destroyConstant();
  virtual void replaceUsesOfWithOnConstant(Value *From, Value *To, Use *U);
	 */
	public boolean hasZeroInitializer(){
		boolean hasZeroInitializer = true;
		for(Constant constant : val){
			if(constant instanceof ConstantInt){
				ConstantInt constantInt = (ConstantInt)constant;
				APInt apInt = constantInt.getApInt();
				if(! apInt.isNullValue()){
					hasZeroInitializer = false;
					break;
				}
			}
			else{
				hasZeroInitializer = false;
				break;
			}
		}
		return hasZeroInitializer;
	}
}
