package org.tamedragon.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.SequentialType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

//===----------------------------------------------------------------------===//
//
// This file defines target properties related to datatype size/offset/alignment
// information.  It uses lazy annotations to cache information about how
// structure types are laid out and used.
//
// This structure should be created once, filled in if the defaults are not
// correct and then passed around by const&.  None of the members functions
// require modification to the object.
//
//===----------------------------------------------------------------------===//


/// Enum used to categorize the alignment types stored by TargetAlignElem
enum AlignTypeEnum {
	INVALID_ALIGN ('0'),
	INTEGER_ALIGN('i'),               ///< Integer type alignment
	VECTOR_ALIGN('v'),                ///< Vector type alignment
	FLOAT_ALIGN('f'),                 ///< Floating point type alignment
	AGGREGATE_ALIGN('a'),             ///< Aggregate alignment
	STACK_ALIGN('s');                  ///< Stack objects alignment

	private char type;

	private AlignTypeEnum(char c){
		type = c;
	}
	static char getAlignTypeChar(int value){
		switch (value) {
		case 0:
			return 'o';
		case 1:
			return 'i';
		case 2:
			return 'v';
		case 3:
			return 'f';
		case 4:
			return 'a';
		case 5:
			return 's';
		default:
			return ' ';
		}
	}}


/// Target alignment element.
///
/// Stores the alignment data associated with a given alignment type (pointer,
/// integer, vector, float) and type bit width.
///
/// The unusual order of elements in the structure attempts to reduce
/// padding and make the structure slightly more cache friendly.
class TargetAlignElem {
	private AlignTypeEnum alignType;  //< Alignment type (AlignTypeEnum)
	private int abiAlign;       //< ABI alignment for this type/bitw
	private int preferredAlign;      //< Pref. alignment for this type/bitw
	private int typeBitWidth;   //< Type bit width
	
	protected TargetAlignElem(){
		//TODO what do we do here?
		//alignType = 8;
	}

	/// Initializer
	public static TargetAlignElem create(AlignTypeEnum alignType, int abiAlign,
			int preferredAlign, int bitWidth) throws Exception {

		if(abiAlign > preferredAlign){
			throw new Exception("Preferred alignment worse than ABI!");
		}

		TargetAlignElem targetAlignElem = new TargetAlignElem();
		targetAlignElem.alignType = alignType;
		targetAlignElem.abiAlign = abiAlign;
		targetAlignElem.preferredAlign = preferredAlign;
		targetAlignElem.typeBitWidth = bitWidth;
		return targetAlignElem;

	}

	@Override
	public boolean equals(Object object){
		if(!(object instanceof TargetAlignElem)){
			return false;
		}

		TargetAlignElem otherTargetAlignElem = (TargetAlignElem) object;
		return alignType == otherTargetAlignElem.getAlignType() && 
		abiAlign == otherTargetAlignElem.getAbiAlign() 
		&& preferredAlign == otherTargetAlignElem.getPreferredAlign() 
		&& typeBitWidth == otherTargetAlignElem.getTypeBitWidth();

	}

	public AlignTypeEnum getAlignType() {
		return alignType;
	}

	public int getAbiAlign() {
		return abiAlign;
	}

	public int getPreferredAlign() {
		return preferredAlign;
	}

	public int getTypeBitWidth() {
		return typeBitWidth;
	}

	public void setAlignType(AlignTypeEnum alignType) {
		this.alignType = alignType;
	}

	public void setAbiAlign(int abiAlign) {
		this.abiAlign = abiAlign;
	}

	public void setPreferredAlign(int preferredAlign) {
		this.preferredAlign = preferredAlign;
	}

	public void setTypeBitWidth(int typeBitWidth) {
		this.typeBitWidth = typeBitWidth;
	}
}

/// TargetData - This class holds a parsed version of the target data layout
/// string in a module and provides methods for querying it.  The target data
/// layout string is specified *by the target* - a frontend generating LLVM IR
/// is required to generate the right target data for the target being codegen'd
/// to.  If some measure of portability is desired, an empty string may be
/// specified in the module.
public class TargetData {
	private boolean littleEndian;          ///< Defaults to false
	private int pointerMemSize;        ///< Pointer size in bytes
	private int pointerABIAlign;       ///< Pointer ABI alignment
	private int pointerPrefAlign;      ///< Pointer preferred alignment
	private int stackNaturalAlign;     ///< Stack natural alignment

	private List<Short> legalIntWidths; ///< Legal Integers.
	
	private static final Logger LOG = LoggerFactory.getLogger(TargetData.class);

	/// Alignments- Where the primitive type alignment data is stored.
	///
	/// @sa init().
	/// @note Could support multiple size pointer alignments, e.g., 32-bit
	/// pointers vs. 64-bit pointers by extending TargetAlignment, but for now,
	/// we don't.
	private List<TargetAlignElem> alignments;

	/// InvalidAlignmentElem - This member is a signal that a requested alignment
	/// type and bit width were not found in the list.
	public static TargetAlignElem invalidAlignmentElem;

	static{
		try{
			invalidAlignmentElem = 
				TargetAlignElem.create(AlignTypeEnum.INVALID_ALIGN, 0, 0, 0);
		}
		catch (Exception e) {
			// TODO: Log here
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// The StructType -> StructLayout map.
	private  HashMap<StructType, StructLayout> layoutMap;

	// Set/initialize target alignments
	private void setAlignment(AlignTypeEnum alignType, int abiAlign,
			int preferredAlign, int bitWidth) throws Exception {
		if(abiAlign > preferredAlign){
			throw new Exception("Preferred alignment worse than ABI!");
		}

		for (int i = 0, size = alignments.size(); i != size; ++i) {

			TargetAlignElem targetAlignElement = alignments.get(i);

			if (targetAlignElement.getAlignType() == alignType &&
					targetAlignElement.getTypeBitWidth() == bitWidth &&
					targetAlignElement.getAbiAlign() == abiAlign &&
					targetAlignElement.getPreferredAlign() == preferredAlign 
			) {
				// Update the abi, preferred alignments.
				targetAlignElement.setAbiAlign(abiAlign);
				targetAlignElement.setPreferredAlign(preferredAlign);
				return;
			}
		}

		alignments.add(TargetAlignElem.create(alignType, abiAlign, preferredAlign, bitWidth));
	}

	private long getAlignmentInfo(AlignTypeEnum alignType, int bitWidth,
			boolean abiInfo, Type type) throws Exception {
		// Check to see if we have an exact match and remember the best match we see.
		int bestMatchIndex = -1;
		int largestInt = -1;
		for (int i = 0, size = alignments.size(); i != size; i++) {

			TargetAlignElem targetAlignElem = alignments.get(i);


			if (targetAlignElem.getAlignType() == alignType &&
					targetAlignElem.getTypeBitWidth() == bitWidth)
				return abiInfo ? targetAlignElem.getAbiAlign() : targetAlignElem.getPreferredAlign();

				// The best match so far depends on what we're looking for.
				if (alignType == AlignTypeEnum.INTEGER_ALIGN &&
						targetAlignElem.getAlignType() == AlignTypeEnum.INTEGER_ALIGN) {
					// The "best match" for integers is the smallest size that is larger than
					// the BitWidth requested.
					if (targetAlignElem.getTypeBitWidth() > bitWidth && (bestMatchIndex == -1 ||
							targetAlignElem.getTypeBitWidth() < alignments.get(bestMatchIndex).getTypeBitWidth()))
						bestMatchIndex = i;

					// However, if there isn't one that's larger, then we must use the
					// largest one we have (see below)
					if (largestInt == -1 ||
							targetAlignElem.getTypeBitWidth() > alignments.get(largestInt).getTypeBitWidth())
						largestInt = i;
				}
		}

		// Okay, we didn't find an exact solution.  Fall back here depending on what
		// is being looked for.
		if (bestMatchIndex == -1) {
			// If we didn't find an integer alignment, fall back on most conservative.
			if (alignType == AlignTypeEnum.INTEGER_ALIGN) {
				bestMatchIndex = largestInt;
			} 
			else {
				if(alignType != AlignTypeEnum.VECTOR_ALIGN){
					throw new Exception("Unknown alignment type!");
				}

				// By default, use natural alignment for vector types. This is consistent
				// with what clang and llvm-gcc do.

				VectorType vectorType = (VectorType) type;

				long align = getTypeAllocSize(vectorType.getContainedType());
				align *= vectorType.getNumElements();

				// If the alignment is not a power of 2, round up to the next power of 2.
				// This happens for non-power-of-2 length vectors.

				if ((align & (align-1)) > 0)
					align = nextPowerOf2(align);
				return align;
			}
		}

		// Since we got a "best match" index, just return it.
		return abiInfo ? alignments.get(bestMatchIndex).getAbiAlign()
				: alignments.get(bestMatchIndex).getPreferredAlign();
	}


	/// getTypeAllocSize - Return the offset in bytes between successive objects
	/// of the specified type, including alignment padding.  This is the amount
	/// that alloca reserves for this type.  For example, returns 12 or 16 for
	/// x86_fp80, depending on alignment.
	public long getTypeAllocSize(Type type) throws Exception {
		// Round up to the next alignment boundary.
		return roundUpAlignment(getTypeStoreSize(type), getABITypeAlignment(type));
	}

	/// RoundUpAlignment - Round the specified value up to the next alignment
	/// boundary specified by Alignment.  For example, 7 rounded up to an
	/// alignment boundary of 4 is 8.  8 rounded up to the alignment boundary of 4
	/// is 8 because it is already aligned.
	public static long roundUpAlignment(long Val, int alignment) {
		/*if((alignment & (alignment-1)) != 0){
			throw new Exception("Alignment must be power of 2!");
		}

		return (Val + (alignment-1)) & ~Val(alignment-1);
		 */

		//TODO Implement correctly
		return Val;

	}

	/// getTypeStoreSize - Return the maximum number of bytes that may be
	/// overwritten by storing the specified type.  For example, returns 5
	/// for i36 and 10 for x86_fp80.
	public long getTypeStoreSize(Type type) throws Exception {
		return (getTypeSizeInBits(type)+7)/8;
	}


	/// Target pointer alignment
	public int getPointerABIAlignment() { return pointerABIAlign; }

	/// Return target's alignment for stack-based pointers
	public int getPointerPrefAlignment() { return pointerPrefAlign; }

	/// Target pointer size
	public int getPointerSize() { return pointerMemSize; }


	// Internal helper method that returns requested alignment for type.
	private int getAlignment(Type type, boolean abiOrPref) throws Exception {
		int AlignType = -1;

		if(!type.isSized()){
			throw new Exception("Cannot getTypeInfo() on a type that is unsized!");
		}

		TypeID typeID = type.getTypeId();

		switch (typeID) {
		// Early escape for the non-numeric types.
		case LABEL_ID:
		case POINTER_ID:
			return (abiOrPref ? getPointerABIAlignment() : getPointerPrefAlignment());
		case ARRAY_ID:
		{
			ArrayType arrayType = (ArrayType) type;
			return getAlignment(arrayType.getContainedType(), abiOrPref);
		}

		case STRUCT_ID: 
		{
			// Packed structure types always have an ABI alignment of one.
			StructType structType = (StructType) type;
			if (structType.getIsPacked() && abiOrPref)
				return 1;

			// Get the layout annotation... which is lazily created on demand.
			StructLayout layout = getStructLayout(structType);
			int align = (int)getAlignmentInfo(AlignTypeEnum.AGGREGATE_ALIGN, 0, abiOrPref, type);

			return align > layout.getAlignment() ? align : layout.getAlignment();
		}

		case INTEGER_ID:
		case VOID_ID:
			AlignType = AlignTypeEnum.INTEGER_ALIGN.ordinal();
			break;

		case HALF_FLOAT_ID:
		case FLOAT_ID:
		case DOUBLE_ID:
			// PPC_FP128TyID and FP128TyID have different data contents, but the
			// same size and alignment, so they look the same here.
		case PPC_FP128_ID:
		case FP128_ID:
		case X86_FP80_ID:
			AlignType = AlignTypeEnum.FLOAT_ALIGN.ordinal();
			break;

		case X86_MMX_ID:
		case VECTOR_ID:
			AlignType = AlignTypeEnum.VECTOR_ALIGN.ordinal();
			break;

		default:
			throw new Exception("Bad type for getAlignment!!!");
		}

		return (int)getAlignmentInfo(AlignTypeEnum.values()[AlignType],  (int)getTypeSizeInBits(type),
				abiOrPref, type);
	}

	/// getTypeSizeInBits - Return the number of bits necessary to hold the
	/// specified type.  For example, returns 36 for i36 and 80 for x86_fp80.
	public long getTypeSizeInBits(Type type) throws Exception {
		if(!type.isSized()){
			throw new Exception("Cannot getTypeInfo() on a type that is unsized!");
		}

		TypeID typeID = type.getTypeId();

		switch (typeID) {
		case LABEL_ID:
		case POINTER_ID:
			return getPointerSizeInBits();
		case ARRAY_ID:
		{
			ArrayType arrayType = (ArrayType)type;
			return getTypeAllocSizeInBits(arrayType.getContainedType())* arrayType.getNumElements();
		}
		case STRUCT_ID:
		{
			// Get the layout annotation... which is lazily created on demand.
			StructType structType = (StructType) type;
			return getStructLayout(structType).getSizeInBits();
		}
		case INTEGER_ID:
		{
			IntegerType intType = (IntegerType) type;
			return intType.getNumBits();
		}
		case VOID_ID:
			return 8;
		case HALF_FLOAT_ID:
			return 16;
		case FLOAT_ID:
			return 32;
		case DOUBLE_ID:
		case X86_MMX_ID:
			return 64;
		case PPC_FP128_ID:
		case FP128_ID:
			return 128;
			// In memory objects this is always aligned to a higher boundary, but
			// only 80 bits contain information.
		case X86_FP80_ID:
			return 80;

		case VECTOR_ID:
		{
			VectorType vectorType = (VectorType) type;
			return vectorType.getPrimitiveSizeInBits();
		}
		default:
			throw new Exception("TargetData::getTypeSizeInBits(): Unsupported type");
		}
	}

	/// Target pointer size, in bits
	public int getPointerSizeInBits() { 
		return 8* pointerMemSize; 
	}

	/// getTypeAllocSizeInBits - Return the offset in bits between successive
	/// objects of the specified type, including alignment padding; always a
	/// multiple of 8.  This is the amount that alloca reserves for this type.
	/// For example, returns 96 or 128 for x86_fp80, depending on alignment.
	public long getTypeAllocSizeInBits(Type type) throws Exception {
		return 8* getTypeAllocSize(type);
	}

	/// getStructLayout - Return a StructLayout object, indicating the alignment
	/// of the struct, its size, and the offsets of its fields.  Note that this
	/// information is lazily cached.
	public StructLayout getStructLayout(StructType structType) throws Exception{
		if (layoutMap == null){
			layoutMap = new HashMap<StructType, StructLayout>();
		}

		StructLayout structLayout = layoutMap.get(structType);
		if (structLayout != null) {
			return structLayout;
		}

		// Create the struct layout.
		structLayout = new StructLayout(structType, this);
		layoutMap.put(structType, structLayout);

		return structLayout;
	}

	/// Valid alignment predicate.
	///
	/// Predicate that tests a TargetAlignElem reference returned by get() against
	/// InvalidAlignmentElem.
	private boolean validAlignment(final TargetAlignElem align)  {
		return !align.equals(invalidAlignmentElem);
	}

	/// Initialise a TargetData object with default values, ensure that the
	/// target data pass is registered.
	private void init(){

		layoutMap = null;
		littleEndian = false;
		pointerMemSize = 8;
		pointerABIAlign = 8;
		pointerPrefAlign = pointerABIAlign;
		stackNaturalAlign = 0;
		alignments = new ArrayList<TargetAlignElem>();
		legalIntWidths = new ArrayList<Short> ();


		try{
			// Default alignments
			//				setAlignment(AlignTypeEnum.INTEGER_ALIGN,   1,  1, 1);   // i1
			//				setAlignment(AlignTypeEnum.INTEGER_ALIGN,   1,  1, 8);   // i8
			//				setAlignment(AlignTypeEnum.INTEGER_ALIGN,   2,  2, 16);  // i16
			//				setAlignment(AlignTypeEnum.INTEGER_ALIGN,   4,  4, 32);  // i32
			//				setAlignment(AlignTypeEnum.INTEGER_ALIGN,   4,  8, 64);  // i64
			//				setAlignment(AlignTypeEnum.FLOAT_ALIGN,     2,  2, 16);  // half
			//				setAlignment(AlignTypeEnum.FLOAT_ALIGN,     4,  4, 32);  // float
			//				setAlignment(AlignTypeEnum.FLOAT_ALIGN,     8,  8, 64);  // double
			//				setAlignment(AlignTypeEnum.FLOAT_ALIGN,    16, 16, 128); // ppcf128, quad, ...
			//				setAlignment(AlignTypeEnum.VECTOR_ALIGN,    8,  8, 64);  // v2i32, v1i64, ...
			//				setAlignment(AlignTypeEnum.VECTOR_ALIGN,   16, 16, 128); // v16i8, v8i16, v4i32, ...
			//				setAlignment(AlignTypeEnum.AGGREGATE_ALIGN, 0,  8,  0);  // struct
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}


	/// Default constructor.
	public TargetData(){}


	public TargetData(String targetDescription)  {
		String errMsg = parseSpecifier(targetDescription, this);
		if(!("".equals(errMsg))){
			LOG.error("Invalid target data layout string.");
			// TODO : Try and recover with some default values
		}
	}

	public boolean isLittleEndian() { return littleEndian; }
	public boolean isBigEndian() { return littleEndian; }

	private static int getInt(String str) {
		int result = 0;
		try{
			result = Integer.parseInt(str);
		}
		catch(NumberFormatException nfe){}
		return result;
	}

	/// getStringRepresentation - Return the string representation of the
	/// TargetData.  This representation is in the same format accepted by the
	/// string constructor above.
	public String parseSpecifier(String targetDescription, TargetData targetData) {
		if (targetData != null){
			targetData.init();
		}

		if(targetDescription == null){
			return "";
		}

		targetDescription.trim();
		targetDescription = targetDescription.substring(2, targetDescription.length()-1);
		String[] tokens = targetDescription.split("-");

		for(String Token : tokens) {

			if (Token.isEmpty())
				continue;

			String[] Split = Token.split(":");
			String Specifier = Split[0];

			if(Specifier.isEmpty()){
				return "Can't be empty here";
			}

			if(Specifier.equals("E")){
				if (targetData != null){
					targetData.littleEndian = false;
				}
			}
			else if(Specifier.equals("e")){
				if (targetData != null){
					targetData.littleEndian = true;
				}
			}
			else if(Specifier.equals("p")){
				// Pointer size.
				int PointerMemSizeBits = getInt(Split[1]);
				if (PointerMemSizeBits < 0 || PointerMemSizeBits % 8 != 0)
					return "invalid pointer size, must be a positive 8-bit multiple";

				if (targetData != null){
					targetData.pointerMemSize = PointerMemSizeBits / 8;
				}

				// Pointer ABI alignment.
				int pointerABIAlignBits = getInt(Split[2]);
				if (pointerABIAlignBits < 0 || pointerABIAlignBits % 8 != 0) {
					return "invalid pointer ABI alignment,  must be a positive 8-bit multiple";
				}

				if (targetData != null){
					targetData.pointerABIAlign = pointerABIAlignBits / 8;
				}

				// Pointer preferred alignment.
				int pointerPrefAlignBits = getInt(Split[3]);
				if (pointerPrefAlignBits < 0 || pointerPrefAlignBits % 8 != 0) {
					return "invalid pointer preferred alignment,  must be a positive 8-bit multiple";
				}
				if (targetData != null){
					targetData.pointerPrefAlign = pointerPrefAlignBits / 8;
					if (targetData.pointerPrefAlign == 0)
						targetData.pointerPrefAlign = targetData.pointerABIAlign;
				}
			}
			else if(Specifier.startsWith("i") || Specifier.startsWith("v") || Specifier.startsWith("f")
					|| Specifier.startsWith("a") || Specifier.startsWith("s"))  {

				AlignTypeEnum AlignType;
				if(Specifier.startsWith("i")){ 
					AlignType = AlignTypeEnum.INTEGER_ALIGN;
				}
				else if(Specifier.startsWith("v")){ 
					AlignType = AlignTypeEnum.VECTOR_ALIGN;
				}
				else if(Specifier.startsWith("f")){ 
					AlignType = AlignTypeEnum.FLOAT_ALIGN;
				}
				else if(Specifier.startsWith("a")){ 
					AlignType = AlignTypeEnum.AGGREGATE_ALIGN;
				}
				else{
					AlignType = AlignTypeEnum.STACK_ALIGN;
				}

				if(Specifier.length() == 1){
					return "invalid " + Specifier + "-size field,  must be positive";
				}
				String sizeSpec = Specifier.substring(1, Specifier.length());

				int size = getInt(sizeSpec);
				if (size < 0) {
					return "invalid " + Specifier.charAt(0) + "-size field,  must be positive";
				}

				int abiAlignBits = getInt(Split[1]);
				if (abiAlignBits < 0 || abiAlignBits % 8 != 0) {
					return "invalid " + Specifier.charAt(0) +"-abi-alignment field,  must be a positive 8-bit multiple";
				}

				int ABIAlign = abiAlignBits / 8;

				int prefAlignBits = getInt(Split[2]);
				if (prefAlignBits < 0 || prefAlignBits % 8 != 0) {
					return "invalid " + Specifier.charAt(0) +"-preferred-alignment field, must be a positive 8-bit multiple";
				}

				int prefAlign = prefAlignBits / 8;
				if (prefAlign == 0){
					prefAlign = ABIAlign;
				}

				if (targetData != null){
					try{
						targetData.setAlignment(AlignType, ABIAlign, prefAlign, size);
					}
					catch(Exception e){
						// TODO Log here
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
			else if(Specifier.startsWith("S")) { // Stack natural alignment.

				if(Specifier.length() == 1){
					return "invalid natural stack alignment (S-field), must be a positive 8-bit multiple";
				}

				int stackNaturalAlignBits = getInt(Specifier.substring(1, Specifier.length()));
				if (stackNaturalAlignBits < 0 || stackNaturalAlignBits % 8 != 0) {
					return "invalid natural stack alignment (S-field), must be a positive 8-bit multiple";
				}
				if (targetData != null){
					targetData.stackNaturalAlign = stackNaturalAlignBits / 8;
				}
			}
			else if(Specifier.startsWith("n")) { // Native integer types.

				if(Specifier.length() == 1){
					return "invalid native integer size: the size must be a positive integer.";
				}

				Specifier = Specifier.substring(1, Specifier.length());

				int Width = getInt(Specifier);
				if (Width <= 0) {
					return "invalid native integer size, " + Specifier  + "must be a positive integer.";
				}

				if (targetData != null && Width != 0){
					targetData.legalIntWidths.add((short)Width);
				}

				for(int i = 1; i < Split.length; i++){
					Width = getInt(Split[i]);
					if (Width <= 0) {
						return "invalid native integer size, " + Specifier  + "must be a positive integer.";
					}

					if (targetData != null && Width != 0){
						targetData.legalIntWidths.add((short)Width);
					}
				}
			}
		}

		return "";
	}

	/// getStringRepresentation - Return the string representation of the
	/// TargetData.  This representation is in the same format accepted by the
	/// string constructor above.

	public String getStringRepresentation()  {

		StringBuffer OS = new StringBuffer();

		OS.append("\"");
		OS.append(littleEndian ? "e" : "E");
		OS.append("-p:");
		OS.append(pointerMemSize*8 + ":");
		OS.append(pointerABIAlign*8 + ":");
		OS.append(pointerPrefAlign*8);
		//			OS.append("-S");
		//			OS.append(stackNaturalAlign*8);

		for (int i = 0, e = alignments.size(); i != e; i++) {
			TargetAlignElem AI = alignments.get(i);
			OS.append('-');
			int ch = AI.getAlignType().ordinal();
			char chr	= AlignTypeEnum.getAlignTypeChar(ch);
			OS.append(chr);
			//				OS.append((char)AI.getAlignType().ordinal());
			OS.append(AI.getTypeBitWidth());
			OS.append(':');
			OS.append(AI.getAbiAlign()*8);
			OS.append(':');
			OS.append(AI.getPreferredAlign()*8);
		}

		if (!legalIntWidths.isEmpty()) {
			OS.append("-n");
			OS.append((int)legalIntWidths.get(0));

			for (int i = 1, e = legalIntWidths.size(); i != e; i++){
				OS.append(':');
				OS.append((int)legalIntWidths.get(i));
			}
		}
		OS.append("-S");
		OS.append(stackNaturalAlign*8);
		OS.append("\"");
		return OS.toString();
	}

	/// isLegalInteger - This function returns true if the specified type is
	/// known to be a native integer type supported by the CPU.  For example,
	/// i64 is not native on most 32-bit CPUs and i37 is not native on any known
	/// one.  This returns false if the integer width is not legal.
	///
	/// The width is specified in bits.
	///
	public boolean isLegalInteger(int Width) {
		for (int i = 0, e = (int)legalIntWidths.size(); i != e; i++)
			if (legalIntWidths.get(i) == Width)
				return true;
		return false;
	}

	public boolean isIllegalInteger(int Width) {
		return !isLegalInteger(Width);
	}

	/// Returns true if the given alignment exceeds the natural stack alignment.
	public boolean exceedsNaturalStackAlignment(int Align) {
		return (stackNaturalAlign != 0) && (Align > stackNaturalAlign);
	}

	/// fitsInLegalInteger - This function returns true if the specified type fits
	/// in a native integer type supported by the CPU.  For example, if the CPU
	/// only supports i32 as a native integer type, then i27 fits in a legal
	// integer type but i45 does not.
	public boolean fitsInLegalInteger(int Width) {
		for (int i = 0, e = (int)legalIntWidths.size(); i != e; i++)
			if (Width <= legalIntWidths.get(i))
				return true;
		return false;
	}


	/// Size examples:
	///
	/// Type        SizeInBits  StoreSizeInBits  AllocSizeInBits[*]
	/// ----        ----------  ---------------  ---------------
	///  i1            1           8                8
	///  i8            8           8                8
	///  i19          19          24               32
	///  i32          32          32               32
	///  i100        100         104              128
	///  i128        128         128              128
	///  Float        32          32               32
	///  Double       64          64               64
	///  X86_FP80     80          80               96
	///
	/// [*] The alloc size depends on the alignment, and thus on the target.
	///     These values are for x86-32 linux.

	/// getTypeStoreSizeInBits - Return the maximum number of bits that may be
	/// overwritten by storing the specified type; always a multiple of 8.  For
	/// example, returns 40 for i36 and 80 for x86_fp80.
	public long getTypeStoreSizeInBits(Type type) throws Exception {
		return 8*getTypeStoreSize(type);
	}


	/// getABITypeAlignment - Return the minimum ABI-required alignment for the
	/// specified type.
	public int getABITypeAlignment(Type type) throws Exception {
		return getAlignment(type, true);
	}

	/// getABIIntegerTypeAlignment - Return the minimum ABI-required alignment for
	/// an integer type of the specified bitwidth.
	public int getABIIntegerTypeAlignment(int BitWidth) throws Exception {
		return (int)getAlignmentInfo(AlignTypeEnum.INTEGER_ALIGN, BitWidth, true, null);
	}

	/// getCallFrameTypeAlignment - Return the minimum ABI-required alignment
	/// for the specified type when it is part of a call frame.
	public int getCallFrameTypeAlignment(Type type) throws Exception {
		for (int i = 0, e = alignments.size(); i != e; i++)
			if (alignments.get(i).getAlignType() == AlignTypeEnum.STACK_ALIGN)
				return alignments.get(i).getAbiAlign();

		return getABITypeAlignment(type);
	}

	/// getPrefTypeAlignment - Return the preferred stack/global alignment for
	/// the specified type.  This is always at least as good as the ABI alignment.
	public int getPrefTypeAlignment(Type type) throws Exception{
		return getAlignment(type, false);
	}

	/// getPreferredTypeAlignmentShift - Return the preferred alignment for the
	/// specified type, returned as log2 of the value (a shift amount).
	///
	public int getPreferredTypeAlignmentShift(Type type) throws Exception{
		int align = getPrefTypeAlignment(type);
		if((align & (align-1)) != 0){
			throw new Exception("Alignment is not a power of two!");
		}
		return Log2_32(align);
	}

	/// getIntPtrType - Return an int integer type that is the same size or
	/// greater to the host pointer size.
	///

	public IntegerType getIntPtrType(CompilationContext compilationContext) throws TypeCreationException {
		return IntegerType.getIntegerType(compilationContext, getPointerSizeInBits(), true );
	}

	/// getPreferredAlignment - Return the preferred alignment of the specified
	/// global.  This includes an explicitly requested alignment (if the global
	/// has one).
	public int getPreferredAlignment(final GlobalVariable GV) throws Exception{
		Type elemType = GV.getType().getContainedType();
		int alignment = getPrefTypeAlignment(elemType);
		int GVAlignment = GV.getAlignment();
		if (GVAlignment >= alignment) {
			alignment = GVAlignment;
		} else if (GVAlignment != 0) {
			int abiTypeAlignment = getABITypeAlignment(elemType);
			alignment = GVAlignment > abiTypeAlignment ? GVAlignment : abiTypeAlignment;
		}

		if (GV.hasInitializer() && GVAlignment == 0) {
			if (alignment < 16) {
				// If the global is not external, see if it is large.  If so, give it a
				// larger alignment.
				if (getTypeSizeInBits(elemType) > 128)
					alignment = 16;    // 16-byte alignment.
			}
		}
		return alignment;
	}



	/// getPreferredAlignmentLog - Return the preferred alignment of the
	/// specified global, returned in log form.  This includes an explicitly
	/// requested alignment (if the global has one).
	int getPreferredAlignmentLog(final GlobalVariable GV) throws Exception {
		return Log2_32(getPreferredAlignment(GV));
	}

	/// getIndexedOffset - return the offset from the beginning of the type for
	/// the specified indices.  This is used to implement getelementptr.
	///
	public long getIndexedOffset(Type type, List<Value>Indices) throws Exception{
		if(!type.isPointerType()){
			throw new Exception("Illegal argument for getIndexedOffset()");
		}

		long Result = 0;

		PointerType ptrTy = (PointerType) type;

		//TODO Get an iterator for the type 
		//generic_gep_type_iterator<Value* const*> TI = gep_type_begin(ptrTy, Indices);
		List<Type> TI = new ArrayList<Type>(Indices.size());

		for (int CurIDX = 0, EndIDX = Indices.size(); CurIDX != EndIDX;
		CurIDX++) {

			Type currType = TI.get(CurIDX);
			TypeID typeID = currType.getTypeId();

			if (typeID == TypeID.STRUCT_ID) {
				StructType STy  = (StructType) currType;
				if(Indices.get(CurIDX).getType() != Type.getInt32Type(type.getCompilationContext(), false)){
					throw new Exception("Illegal struct idx");
				}

				ConstantInt cstInt = (ConstantInt) Indices.get(CurIDX);

				//TODO Implement zero extension
				//int FieldNo = cstInt.getZeroExt();
				int FieldNo = cstInt.getApInt().getZExtValue().intValue();

				// Get structure layout information...
				StructLayout Layout = getStructLayout(STy);

				// Add in the offset, as calculated by the structure layout info...
				Result += Layout.getElementOffset(FieldNo);

				// Update Ty to refer to current element
				type = STy.getTypeAtIndex(FieldNo);
			} 
			else {
				// Update Ty to refer to current element
				SequentialType seqType = (SequentialType) type;

				type = seqType.getContainedType();

				// Get the array index and the size of each array element.
				ConstantInt cstInt = (ConstantInt) Indices.get(CurIDX);
				//TODO : To get Size extension below?
				long arrayIdx = cstInt.getApInt().getZExtValue().intValue();
				if(arrayIdx != 0){
					Result += (long)arrayIdx * getTypeAllocSize(type);
				}
			}
		}

		return Result;
	}


	/// NextPowerOf2 - Returns the next power of two (in 64-bits)
	/// that is strictly greater than A.  Returns zero on overflow.
	//TODO Move this to a math utility
	public static  long nextPowerOf2(long value) {
		value |= (value >> 1);
		value |= (value >> 2);
		value |= (value >> 4);
		value |= (value >> 8);
		value |= (value >> 16);
		value |= (value >> 32);
		return value + 1;
	}

	public static int Log2_32(int Value) {
		return 31 - countLeadingZeros_32(Value);
	}

	public static int countLeadingZeros_32(int value) {
		int count; // result
		//TODO This is platform specific; make sure we implement this later.
		//		#if __GNUC__ >= 4
		//		  // PowerPC is defined for __builtin_clz(0)
		//		#if !defined(__ppc__) && !defined(__ppc64__)
		//		  if (!Value) return 32;
		//		#endif
		//		  Count = __builtin_clz(Value);
		//		#else
		if (value == 0)
			return 32;
		count = 0;
		// bisection method for count leading zeros
		for (int shift = 32 >> 1; shift != 0; shift >>= 1) {
			int tmp = value >> shift;
			if (tmp != 0) {
				value = tmp;
			} else {
				count |= shift;
			}
		}
		//#endif
		return count;
	}
}