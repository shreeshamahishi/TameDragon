package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.List;

public class ConstantVector extends Constant {

	private List<Constant> constValues;
	
	public ConstantVector(VectorType vectorType, List<Constant> constValues) {
		// TODO Fix this
		super(vectorType, constValues);
		this.constValues = new ArrayList<>(constValues);
	}

	public void destroyConstantImpl() {
		// TODO Implement this
		// getType()->getContext().pImpl->VectorConstants.remove(this);
	}

	public Value handleOperandChangeImpl(Value From, Value To) {
		// TODO Implement this later
		return null;
	}

	// ConstantVector accessors
	public static Constant get(List<Constant> constantsList) {
		Constant cst = getImpl(constantsList);
		if (cst != null) {
			return cst;
		}

		Type typeOfFirstElement = constantsList.get(0).getType();
		VectorType vectorType = new VectorType(typeOfFirstElement.getCompilationContext(), typeOfFirstElement, constantsList.size());

		// TODO Create only if required as below:
		// return Ty->getContext().pImpl->VectorConstants.getOrCreate(Ty, V);

		// TODO ... and remove this
		return new ConstantVector(vectorType, constantsList);

	}

	private static Constant getImpl(List<Constant> V) {
		// TODO Implement this
		return null;
	}

	/// Return a ConstantVector with the specified constant in each element.
	public static Constant getSplat(long numElements, Constant constantVal) {
		// If this splat is compatible with ConstantDataVector, use it instead of ConstantVector.
		if (constantVal.getValueTypeID() == ValueTypeID.CONST_FP || constantVal.getValueTypeID() == ValueTypeID.CONST_INT ) {
			// TODO Implement ConstantDataSequential
			// && ConstantDataSequential.isElementTypeCompatible(V.getType())) {
			// TODO Implement ConstantDataVector::getSplat(NumElts, V)
			// return ConstantDataVector::getSplat(NumElts, V);
		}


		List<Constant> elements = new ArrayList<>((int)numElements);
		for(int i = 0; i < numElements; i++) {
			elements.add(constantVal);
		}
		
		return get(elements);
	}

	/* If all elements of the vector constant have the same value, return that
	   value. Otherwise, return null. Ignore undefined elements by setting
	   allowUndefs to true.
	 */
	public Constant getSplatValue(boolean allowUndefs){
		// Check out first element.
		Constant firstElement = constValues.get(0);
		// Then make sure all remaining elements point to the same value.

		for (int I = 1; I < constValues.size(); ++I) {
			Constant constVal = constValues.get(I);
			if (constVal == firstElement)
				continue;

			// Strict mode: any mismatch is not a splat.
			if (!allowUndefs) {
				return null;
			}

			// Allow undefs mode: ignore undefined elements.
			if (constVal == UndefValue.createOrGet(firstElement.getType())) {
				continue;
			}

			// If we do not have a defined element yet, use the current operand.
			if (constVal == UndefValue.createOrGet(firstElement.getType())) {
				firstElement = constVal;
			}

			if (constVal != firstElement) {
				return null;
			}
		}
		
		return firstElement;
	}	
	
	public Constant getAggregateElement(int index) {
		return constValues.get(index);
	}
	
	public Type getContainedType() {
		return constValues.get(0).getType();
	}
}
