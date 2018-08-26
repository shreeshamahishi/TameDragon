package org.tamedragon.common.llvmir.types;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ConstantAggregateZero extends Constant{

	static Set<ConstantAggregateZero> constantAggregateZeroPool = new HashSet<ConstantAggregateZero>(); 

	private ConstantAggregateZero(Type type) {
		super(type, null);
		this.valueTypeID = ValueTypeID.CONST_AGGREGATE_ZERO;
	}


	public static ConstantAggregateZero get(Type type){
		if(constantAggregateZeroPool.isEmpty()){
			ConstantAggregateZero cAZ = new ConstantAggregateZero(type);
			constantAggregateZeroPool.add(cAZ);
			return cAZ;
		}
		else{
			Iterator<ConstantAggregateZero> iterator = constantAggregateZeroPool.iterator();
			while(iterator.hasNext()){
				ConstantAggregateZero cAZ = iterator.next();
				if(cAZ.getType() == type)
					return cAZ;
			}
			ConstantAggregateZero cAZ = new ConstantAggregateZero(type);
			constantAggregateZeroPool.add(cAZ);
			return cAZ;
		}
	}

	//	public void destroyConstant();

	/// getSequentialElement - If this CAZ has array or vector type, return a zero
	/// with the right element type.
	public Constant getSequentialElement() throws InstantiationException{
		Type type = getType();
		CompilationContext compilationContext = getContext();
		if(type.isArrayType() || type.isVectorType()){
			Type containedType = null;
			if(type.isArrayType()){
				ArrayType arrayType = (ArrayType)type;
				containedType = arrayType.getContainedType();
			}
			if(type.isVectorType()){
				VectorType vectorType = (VectorType)type;
				containedType = vectorType.getContainedType();
			}
			if(containedType.isPrimitiveType())
				return getConstantZeroForPrimitiveType(containedType, compilationContext);
			else
				return get(containedType);
		}
		
		return null;
	}

	private Constant getConstantZeroForPrimitiveType(Type type, CompilationContext compilationContext) throws InstantiationException {
		if(type.isInt16Type())
			return ConstantInt.create(Type.getInt16Type(compilationContext, false), 0, false);
		else if(type.isInt1Type())
			return ConstantInt.create(Type.getInt1Type(compilationContext, false), 0, false);
		else if(type.isInt32Type())
			return ConstantInt.create(Type.getInt32Type(compilationContext, false), 0, false);
		else if(type.isInt64Type())
			return ConstantInt.create(Type.getInt64Type(compilationContext, false), 0, false);
		else if(type.isInt8Type())
			return ConstantInt.create(Type.getInt8Type(compilationContext, false), 0, false);
		else if(type.isFloatType() || type.isFP128Type() || type.isDoubleType()){
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, "0.0");
			return new ConstantFP(type, apFloat );
		}
		return null;
	}


	/// getStructElement - If this CAZ has struct type, return a zero with the
	/// right element type for the specified element.
	public Constant getStructElement(int elt) throws InstantiationException {
		CompilationContext compilationContext = getContext();
		if(getType().isStructType()){
			StructType structType = (StructType)getType();
			Type memberType = structType.getTypeAtIndex(elt);
			if(memberType.isPrimitiveType())
				return getConstantZeroForPrimitiveType(memberType, compilationContext);
			else
				return get(memberType);
		}
		return null;
	}

	/// getElementValue - Return a zero of the right value for the specified GEP
	/// index.
//	public Constant getElementValue(Constant c){
//		
//	}

	/// getElementValue - Return a zero of the right value for the specified GEP
	/// index.
//	public Constant getElementValue(int idx){
//		
//	}
	
	@Override
	public String toString() {
		return getType().toString() + " " + "zeroinitializer";
	}
}
