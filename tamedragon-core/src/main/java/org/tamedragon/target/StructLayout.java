package org.tamedragon.target;

import java.util.List;

import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;

public class StructLayout {
	private long structSize;
	private int structAlignment;
	private int numElements;

	private List<Long> memberOffsets;  // variable sized array!

	protected StructLayout(StructType structType, final TargetData targetData) throws Exception {
		if(structType.isOpaque()){
			throw new Exception("Cannot get layout of opaque structs");
		}

		structAlignment = 0;
		structSize = 0;
		numElements = structType.getElementSize();

		// Loop over each of the elements, placing them in memory.
		for (int i = 0, e = numElements; i != e; i++) {
			Type Ty = structType.getTypeAtIndex(i);
			int TyAlign = structType.getIsPacked() ? 1 : targetData.getABITypeAlignment(Ty);

			// Add padding if necessary to align the data element properly.
			if ((structSize & (TyAlign-1)) != 0){
				structSize = TargetData.roundUpAlignment(structSize, TyAlign);
			}

			// Keep track of maximum alignment constraint.
			structAlignment = TyAlign > structAlignment ? TyAlign : structAlignment;

			memberOffsets.set(i, structSize);
			structSize += targetData.getTypeAllocSize(Ty); // Consume space for this data item
		}

		// Empty structures have alignment of 1 byte.
		if (structAlignment == 0){ 
			structAlignment = 1;
		}

		// Add padding to the end of the struct so that it could be put in an array
		// and all array elements would be aligned correctly.
		if ((structSize & (structAlignment-1)) != 0)
			structSize = TargetData.roundUpAlignment(structSize, structAlignment);
	}

	public long getSizeInBytes() {
		return structSize;
	}

	public long getSizeInBits() {
		return 8* structSize;
	}

	public int getAlignment() {
		return structAlignment;
	}

	/// getElementContainingOffset - Given a valid byte offset into the structure,
	/// return the structure index that contains it.
	///
	public int getElementContainingOffset(long offset) throws Exception {
		//TODO Implement this
		//		const uint64_t *SI =
		//		    std::upper_bound(&MemberOffsets[0], &MemberOffsets[NumElements], Offset);
		//		  assert(SI != &MemberOffsets[0] && "Offset not in structure type!");
		//		  --SI;
		//		  assert(*SI <= Offset && "upper_bound didn't work");
		//		  assert((SI == &MemberOffsets[0] || *(SI-1) <= Offset) &&
		//		         (SI+1 == &MemberOffsets[NumElements] || *(SI+1) > Offset) &&
		//		         "Upper bound didn't work!");
		//
		//		  // Multiple fields can have the same offset if any of them are zero sized.
		//		  // For example, in { i32, [0 x i32], i32 }, searching for offset 4 will stop
		//		  // at the i32 element, because it is the last element at that offset.  This is
		//		  // the right one to return, because anything after it will have a higher
		//		  // offset, implying that this element is non-empty.
		//		  return SI-&MemberOffsets[0];

		return 0;
	}

	public long getElementOffset(int index) throws Exception{
		if(index >= numElements){
			throw new Exception("Invalid element index!");
		}

		return memberOffsets.get(index);
	}

	public long getElementOffsetInBits(int index)  throws Exception{
		return getElementOffset(index ) * 8;
	}
}
