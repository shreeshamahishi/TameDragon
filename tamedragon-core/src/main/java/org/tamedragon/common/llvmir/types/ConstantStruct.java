package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class ConstantStruct extends Constant {
	StructType structType;
	List<Constant> val;
	protected ConstantStruct(StructType structType, List<Constant> val) {
		super(structType, val);
		this.structType = structType;
		this.val = val;
		setName(structType.getName());
	}

	public static Constant get(StructType structType, List<Constant> val){
		// Create a ConstantAggregateZero value if all elements are zeros.
		for (int i = 0, e = val.size(); i != e; ++i)
			if(val.get(i)!= null)
				try {
					return structType.getCompilationContext().constantsTy.getOrCreate(structType, val);
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(-1);
				}

				//		  assert((ST->isOpaque() || ST->getNumElements() == V.size()) &&
				//		         "Incorrect # elements specified to ConstantStruct::get");
				//		  return ConstantAggregateZero::get(ST);
				try {
					return structType.getCompilationContext().constantsTy.getOrCreate(structType, val);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				return null;
	}

	public static Constant get(StructType structType, Constant ... constants){
		// Create a ConstantAggregateZero value if all elements are zeros.
		for (int i = 0, e = constants.length; i != e; ++i)
			if (constants[i] != null)
				try {
					return structType.getCompilationContext().constantsTy.getOrCreate(structType, Arrays.asList(constants));
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(-1);
				}

				//		  assert((ST->isOpaque() || ST->getNumElements() == V.size()) &&
				//		         "Incorrect # elements specified to ConstantStruct::get");
				//		  return ConstantAggregateZero::get(ST);
				return null;

	}

	/**
	 * getAnon - Return an anonymous struct that has the specified
	     elements.  If the struct is possibly empty, then you must specify a
	     context.
	 * @param V
	 * @param Packed
	 * @return
	 */
	public static Constant getAnon(List<Constant> V, boolean Packed, boolean isUnion) throws Exception{
		return get(getTypeForElements(V, Packed, isUnion), V);
	}

	/**
	 * getTypeForElements - Return an anonymous struct type to use for a constant
	     with the specified set of elements.  The list must not be empty.
	 * @param V
	 * @param Packed
	 * @return
	 */
	public static StructType getTypeForElements(List<Constant> V, boolean Packed, boolean isUnion) throws Exception{
		if(V.isEmpty()){
			throw new Exception("ConstantStruct's getTypeForElements cannot be called on empty list");
		}
		return getTypeForElements(V.get(0).getType().getCompilationContext(), V, Packed, isUnion);
	}

	/**
	 * getTypeForElements - Return an anonymous struct type to use for a constant
	     with the specified set of elements.  The list must not be empty.
	 * @param compilationContext
	 * @param V
	 * @param Packed
	 * @return
	 */
	public static StructType getTypeForElements(CompilationContext compilationContext, List<Constant> V, boolean Packed, boolean isUnion) {
		List<Type> EltTypes = new ArrayList<Type>();
		for (int i = 0, e = V.size(); i != e; ++i)
			EltTypes.add(V.get(i).getType());

		StructType structType = null;
		try {
			structType = Type.getStructType(compilationContext, Packed, "", isUnion, EltTypes.toArray(new Type[EltTypes.size()]));
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return structType;
	}

	@Override
	public String toString() {
		String name = "%" + getName();
		String str = name + " { ";
		for(int i = 0; i < val.size(); i++){
			str += (i < (val.size() -1))? val.get(i).toString() + ", " : val.get(i).toString();
		}
		
		// For Union we have to add extra i8 Array if possible.
		int nosOfMembers = structType.getElementSize();
		if(structType.isUnion() && nosOfMembers == 2){
			ArrayType arrayType = (ArrayType)structType.getTypeAtIndex(1);
			str += ", " + arrayType.toString() + " undef";
		}
		
		str += " }";
		return str;
	}

	public List<Constant> getVal() {
		return val;
	}

	/**
	 * class ConstantStruct : public Constant {

protected:

public:
  /// Transparently provide more efficient getOperand methods.
  DECLARE_TRANSPARENT_OPERAND_ACCESSORS(Constant);

  /// getType() specialization - Reduce amount of casting...
  ///
  inline StructType *getType() const {
    return reinterpret_cast<StructType*>(Value::getType());
  }

  virtual void destroyConstant();
  virtual void replaceUsesOfWithOnConstant(Value *From, Value *To, Use *U);

  /// Methods for support type inquiry through isa, cast, and dyn_cast:
  static inline bool classof(const ConstantStruct *) { return true; }
  static bool classof(const Value *V) {
    return V->getValueID() == ConstantStructVal;
  }
};
	 */
}
