package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class StorageLocCombinedExpression {

	private Long constValue;
	private List<Pair<ConstantInt, Value>> expression;

	private LLVMIREmitter emitter = LLVMIREmitter.getInstance();

	public StorageLocCombinedExpression(Long constValue, List<Pair<ConstantInt, Value>> expression){
		this.constValue = constValue;
		this.expression = expression;
	}

	public Long getConstValue() {
		return constValue;
	}

	public void setConstValue(Long constValue) {
		this.constValue = constValue;
	}

	public List<Pair<ConstantInt, Value>> getExpression() {
		return expression;
	}

	public void setExpression(List<Pair<ConstantInt, Value>> expression) {
		this.expression = expression;
	}


	public boolean hasTermInExpression(ConstantInt factor, Value value){
		for(Pair<ConstantInt, Value> pair : expression){
			if(pair.getFirst().equals(factor) && pair.getSecond().equals(value)){
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean equals(Object object){
		if(!(object instanceof StorageLocCombinedExpression)){
			return false;
		}

		StorageLocCombinedExpression other = (StorageLocCombinedExpression) object;
		if(!constValue.equals(other.getConstValue())){
			return false;
		}

		if(expression.size() != other.getExpression().size()){
			return false;
		}

		for(Pair<ConstantInt, Value> pair : expression){
			if(!(other.hasTermInExpression(pair.getFirst(), pair.getSecond()))){
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString(){
		String result = "";
		result += constValue.toString();

		if(expression.size() ==  0){
			return result;
		}

		result += " + [";
		int count = 0;
		for(Pair<ConstantInt, Value> pair : expression){

			result += pair.getFirst().getName() + "*" + emitter.getValidName(pair.getSecond());
			if(count < expression.size() -1){
				result += ", ";
			}
		}
		result += "]";

		return result;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public boolean hasNegativeElements() {

		if(constValue < 0){
			return true;
		}

		for(Pair<ConstantInt, Value> pair :expression){
			ConstantInt factor = pair.getFirst();
			if(factor.isNegative()){
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns true if this expression is lexically "greater" than the expression that is
	 * passed as argument.
	 * 
	 * Only lexically equivalent expressions can be compared. If two expressions are not
	 * lexically equivalent, we return conservatively return false. 
	 * 
	 * @param otherExpr
	 * @return
	 */
	public boolean isGreaterThan(StorageLocCombinedExpression otherExpr){

		if(constValue < otherExpr.getConstValue()){
			return false;
		}

		if(expression.size() != otherExpr.getExpression().size()){
			// Cannot say whether this expression is greater than
			// the other expression
			return false;
		}

		List<Pair<ConstantInt, Value>> otherExprList = otherExpr.getExpression();
		for(Pair<ConstantInt, Value> term: expression){
			Long coefficient = new Long(term.getFirst().getApInt().getVal());
			Value value = term.getSecond();

			Long otherCoefficient = null;
			for(Pair<ConstantInt, Value> otherTerm: otherExprList){
				if(value.equals(otherTerm.getSecond())){
					otherCoefficient = new Long(otherTerm.getFirst().getApInt().getVal());
					break;
				}
			}

			if(otherCoefficient == null){
				// Incompatible expressions for comparison; cannot say whether this expression is greater than
				// the other expression
				return false;
			}

			if(coefficient <= otherCoefficient){
				return false;
			}
		}

		return true;
	}

	public static Long getFactor(StorageLocCombinedExpression combinedExpr, StorageLocCombinedExpression otherCombinedExpr) {
		Long quotient = null;

		Long constValueExpr = combinedExpr.getConstValue();
		Long constValueOtherExpr = otherCombinedExpr.getConstValue();

		if(constValueOtherExpr == 0){
			// Cannot divide by zero
			return quotient;
		}

		if(constValueExpr == 0){
			// Factor cannot be zero
			return null;
		}

		if(constValueExpr % constValueOtherExpr != 0){
			// Factor must evaluate to a integer
			return null;
		}

		List<Pair<ConstantInt, Value>>  expr = combinedExpr.getExpression();
		List<Pair<ConstantInt, Value>>  otherExpr = otherCombinedExpr.getExpression();

		if(expr.size() != otherExpr.size()){
			// Cannot find the factor for this
			return null;
		}

		quotient = constValueExpr / constValueOtherExpr;

		for(Pair<ConstantInt, Value> pair : expr){

			Value value = pair.getSecond();
			Long otherFactor = null;

			for(Pair<ConstantInt, Value> otherPair : otherExpr){
				Value otherValue = otherPair.getSecond();
				if(value.equals(otherValue)){
					otherFactor = Long.parseLong(otherPair.getFirst().getApInt().getVal());
					break;
				}
			}

			if(otherFactor == null){
				// Mismatch in expression
				return null;
			}

			Long factor = Long.parseLong(pair.getFirst().getApInt().getVal());
			if(factor % otherFactor != 0){
				// Not a factor
				return null;
			}

			if(quotient != (factor / otherFactor)){
				// Not a factor
				return null;
			}
		}

		// Found the factor!
		return quotient;
	}

	/**
	 * Returns a new storage location that is scaled by the amount that is passed in.
	 * 
	 * Scaling is the result of multiplying each factor in the expression and the constant
	 * value with the factor that is passed in.
	 * 
	 * @param count
	 */
	public StorageLocCombinedExpression scaleBy(int count) {

		List<Pair<ConstantInt, Value>> newExpression = new ArrayList<Pair<ConstantInt,Value>>();

		for(Pair<ConstantInt, Value> term : expression){
			ConstantInt factor = term.getFirst();

			try{
				ConstantInt constInt = new ConstantInt((IntegerType)factor.getType(), 
						new APInt(factor.getApInt().getNumBits(), "" + count, factor.getApInt().isSigned()));

				ConstantInt result = (ConstantInt)factor.multiply(constInt);
				
				Pair<ConstantInt, Value> updatedPair = new Pair<ConstantInt, Value>(result, term.getSecond());
				newExpression.add(updatedPair);

			}
			catch(Exception e){
				e.printStackTrace();
				System.exit(-1);
			}
		}

		Long newConstValue = count * constValue;
		
		return new StorageLocCombinedExpression(newConstValue, newExpression);

	}

}
