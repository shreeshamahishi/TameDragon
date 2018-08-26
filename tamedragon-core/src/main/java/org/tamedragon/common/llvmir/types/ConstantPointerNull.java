package org.tamedragon.common.llvmir.types;

/**
 * ConstantPointerNull - a constant pointer value that points to null
 * @author ipsg
 *
 */
public class ConstantPointerNull extends Constant {
	
	protected ConstantPointerNull(PointerType pointerType){
		super(pointerType, null);
	}

	  /// get() - Static factory methods - Return objects of the specified value

	/*public static ConstantPointerNull get(PointerType *T){
		ConstantPointerNull *&Entry = Ty->getContext().pImpl->CPNConstants[Ty];
		  if (Entry == 0)
		    Entry = new ConstantPointerNull(Ty);
		  
		  return Entry;
	}

	  /// getType - Specialize the getType() method to always return an PointerType,
	  /// which reduces the amount of casting needed in parts of the compiler.
	  ///
	  public PointerType getType() {
	    return reinterpret_cast<PointerType*>(Value::getType());
	  }

	  /// Methods for support type inquiry through isa, cast, and dyn_cast:
	  static  bool classof(const ConstantPointerNull *) { return true; }
	  static bool classof(const Value *V) {
	    return V->getValueID() == ConstantPointerNullVal;
	  }

		// destroyConstant - Remove the constant from the constant table...
		//
		void ConstantPointerNull::destroyConstant() {
		  getContext().pImpl->CPNConstants.erase(getType());
		  // Free the constant and any dangling references to it.
		  destroyConstantImpl();
		}
	*/

}
