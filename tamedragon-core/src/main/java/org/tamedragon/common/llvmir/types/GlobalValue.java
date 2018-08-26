package org.tamedragon.common.llvmir.types;

import java.util.List;

public class GlobalValue extends Constant {

	public enum LinkageTypes { 
		ExternalLinkage("external"),	///< Externally visible function
		AvailableExternallyLinkage("available_externally"), ///< Available for inspection, not emission.
		LinkOnceAnyLinkage("linkonce"), ///< Keep one copy of function when linking (inline)
		LinkOnceODRLinkage("linkonce_odr"), ///< Same, but only replaced by something equivalent.
		WeakAnyLinkage("weak"),     ///< Keep one copy of named function when linking (weak)
		WeakODRLinkage("weak_odr"),     ///< Same, but only replaced by something equivalent.
		AppendingLinkage("appending"),   ///< Special purpose, only applies to global arrays
		InternalLinkage("internal"),    ///< Rename collisions when linking (static functions).
		PrivateLinkage("private"),     ///< Like Internal, but omit from symbol table.
		LinkerPrivateLinkage("linker_private"), ///< Like Private, but linker removes.
		LinkerPrivateWeakLinkage("linker_private_weak"), ///< Like LinkerPrivate, but weak.
		LinkerPrivateWeakDefAutoLinkage("linker_private_weak_auto"), ///< Like LinkerPrivateWeak, but possibly hidden.
		DLLImportLinkage("dllimport"),   ///< Function to be imported from DLL
		DLLExportLinkage("dllexport"),   ///< Function to be accessible from DLL.
		ExternalWeakLinkage("extern_weak"),///< ExternalWeak linkage description.
		CommonLinkage("common") ;      ///< Tentative definitions.

		private String strRepresentation;

		LinkageTypes(String strRepresentation) {
			this.strRepresentation = strRepresentation;
		}

		public String getStrRepresentation(){
			return strRepresentation;
		}
	}

	public enum  VisibilityTypes { 
		DefaultVisibility,  ///< The GV is visible
		HiddenVisibility,       ///< The GV is hidden
		ProtectedVisibility     ///< The GV is protected
	}

	protected Module parent;
	private LinkageTypes linkage = LinkageTypes.WeakAnyLinkage;   // The linkage of this global
	private VisibilityTypes visibility = VisibilityTypes.ProtectedVisibility;    // The visibility style of this global
	private int alignment = 16;    // Alignment of this symbol, must be power of two
	private boolean unnamedAddr;   // This value's address is not significant
	private String section;        // Section to emit this into, empty mean default

	public GlobalValue(Type type, List<? extends Value> operandList, LinkageTypes linkage,String name){
		super(type, operandList);
		this.linkage = linkage;
		this.alignment = 0;
		setName(name);
	}

	public GlobalValue(Module parent, Type type, List<? extends Value> operandList, LinkageTypes linkage,String name){
		super(type, operandList);
		this.linkage = linkage;
		this.alignment = 0;
		this.unnamedAddr = true;
		this.parent = parent;
		setName(name);
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int align){
		this.alignment = align;
	}

	public boolean hasUnnamedAddr() {
		return unnamedAddr; 
	}

	public void setUnnamedAddr(boolean val) {
		unnamedAddr = val; 
	}

	VisibilityTypes getVisibility() {
		return visibility; 
	}

	boolean hasDefaultVisibility() {
		return visibility == VisibilityTypes.DefaultVisibility; 
	}

	boolean hasHiddenVisibility() {
		return visibility == VisibilityTypes.HiddenVisibility; 
	}

	boolean hasProtectedVisibility() {
		return visibility == VisibilityTypes.ProtectedVisibility;
	}

	void setVisibility(VisibilityTypes v) {
		visibility = v; 
	}

	public boolean hasSection() {
		return section.length() != 0; 
	}

	public String getSection() {
		return section; 
	}

	public void setSection(String s) {
		section = s; 
	}

	@Override
	public PointerType getType() {
		return (PointerType)super.getType();
	}

	public static LinkageTypes getLinkOnceLinkage(boolean isODR) {
		return isODR ? LinkageTypes.LinkOnceODRLinkage : LinkageTypes.LinkOnceAnyLinkage;
	}

	public static LinkageTypes getWeakLinkage(boolean isODR) {
		return isODR ? LinkageTypes.WeakODRLinkage : LinkageTypes.WeakAnyLinkage;
	}

	public static boolean isExternalLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.ExternalLinkage;
	}

	public static boolean isAvailableExternallyLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.AvailableExternallyLinkage;
	}

	public static boolean isLinkOnceLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.LinkOnceAnyLinkage || Linkage == LinkageTypes.LinkOnceODRLinkage;
	}

	public static boolean isWeakLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.WeakAnyLinkage || Linkage == LinkageTypes.WeakODRLinkage;
	}

	public static boolean isAppendingLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.AppendingLinkage;
	}

	public static boolean isInternalLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.InternalLinkage;
	}

	public static boolean isPrivateLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.PrivateLinkage;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public static boolean isLinkerPrivateLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.LinkerPrivateLinkage;
	}

	public static boolean isLinkerPrivateWeakLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.LinkerPrivateWeakLinkage;
	}

	public static boolean isLinkerPrivateWeakDefAutoLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.LinkerPrivateWeakDefAutoLinkage;
	}

	public static boolean isLocalLinkage(LinkageTypes Linkage) {
		return isInternalLinkage(Linkage) || isPrivateLinkage(Linkage) ||
				isLinkerPrivateLinkage(Linkage) || isLinkerPrivateWeakLinkage(Linkage) ;
	}

	public static boolean isDLLImportLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.DLLImportLinkage;
	}

	public static boolean isDLLExportLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.DLLExportLinkage;
	}

	public static boolean isExternalWeakLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.ExternalWeakLinkage;
	}

	public static boolean isCommonLinkage(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.CommonLinkage;
	}

	/**
	 * Whether the definition of this global may be replaced by something non-equivalent at link time.  For example, if a function has
	 * weak linkage then the code defining it may be replaced by different code.
	 * @param Linkage
	 * @return
	 */
	public static boolean mayBeOverridden(LinkageTypes Linkage) {
		return Linkage == LinkageTypes.WeakAnyLinkage ||
				Linkage == LinkageTypes.LinkOnceAnyLinkage ||
				Linkage == LinkageTypes.CommonLinkage ||
				Linkage == LinkageTypes.ExternalWeakLinkage ||
				Linkage == LinkageTypes.LinkerPrivateWeakLinkage ||
				Linkage == LinkageTypes.LinkerPrivateWeakDefAutoLinkage;
	}

	/**
	 * Whether the definition of this global may be replaced at link time. 
	 * NB: Using this method outside of the code generators is almost
	 * always a mistake: when working at the IR level use mayBeOverridden instead
	 * as it knows about ODR semantics.
	 * @param Linkage
	 * @return
	 */
	public static boolean isWeakForLinker(LinkageTypes Linkage)  {
		return Linkage == LinkageTypes.AvailableExternallyLinkage ||
				Linkage == LinkageTypes.WeakAnyLinkage ||
				Linkage == LinkageTypes.WeakODRLinkage ||
				Linkage == LinkageTypes.LinkOnceAnyLinkage ||
				Linkage == LinkageTypes.LinkOnceODRLinkage ||
				Linkage == LinkageTypes.CommonLinkage ||
				Linkage == LinkageTypes.ExternalWeakLinkage ||
				Linkage == LinkageTypes.LinkerPrivateWeakLinkage ||
				Linkage == LinkageTypes.LinkerPrivateWeakDefAutoLinkage;
	}

	public boolean hasExternalLinkage()  {
		return isExternalLinkage(linkage); 
	}

	public boolean hasAvailableExternallyLinkage()  {
		return isAvailableExternallyLinkage(linkage);
	}

	public boolean hasLinkOnceLinkage()  {
		return isLinkOnceLinkage(linkage);
	}

	public boolean hasWeakLinkage()  {
		return isWeakLinkage(linkage);
	}

	public boolean hasAppendingLinkage()  {
		return isAppendingLinkage(linkage); 
	}

	public boolean hasInternalLinkage()  { 
		return isInternalLinkage(linkage); 
	}

	public boolean hasPrivateLinkage()  {
		return isPrivateLinkage(linkage); 
	}

	public boolean hasLinkerPrivateLinkage()  {
		return isLinkerPrivateLinkage(linkage); 
	}

	public boolean hasLinkerPrivateWeakLinkage()  {
		return isLinkerPrivateWeakLinkage(linkage);
	}

	public boolean hasLinkerPrivateWeakDefAutoLinkage()  {
		return isLinkerPrivateWeakDefAutoLinkage(linkage);
	}

	public boolean hasLocalLinkage()  {
		return isLocalLinkage(linkage); 
	}

	public boolean hasDLLImportLinkage()  {
		return isDLLImportLinkage(linkage); 
	}

	public boolean hasDLLExportLinkage()  { 
		return isDLLExportLinkage(linkage); 
	}

	public boolean hasExternalWeakLinkage()  { 
		return isExternalWeakLinkage(linkage);
	}

	public boolean hasCommonLinkage()  {
		return isCommonLinkage(linkage);
	}

	public void setLinkage(LinkageTypes LT) {
		linkage = LT;
	}

	public LinkageTypes getLinkage() {
		return linkage; 
	}

	public boolean mayBeOverridden() { 
		return mayBeOverridden(linkage); 
	}

	public boolean isWeakForLinker() { 
		return isWeakForLinker(linkage); 
	}

	/**
	 * copy all additional attributes (those not needed to create a GlobalValue) from the GlobalValue Src to this one.
	 * @param src
	 */
	public void copyAttributesFrom(GlobalValue src) {
		setAlignment(src.getAlignment());
		setSection(src.getSection());
		setVisibility(src.getVisibility());
		setUnnamedAddr(src.hasUnnamedAddr());
	}

	/**
	 * Return true if the primary definition of this global value is outside of the current translation unit.
	 * @return
	 */
	public boolean isDeclaration(){
		if(getNumOperands() == 1)
			return false;
		return true;
	}

	public boolean hasDefinitiveInitializer() {
		return hasInitializer() &&
				// The initializer of a global variable with weak linkage may change at
				// link time.
				!mayBeOverridden();
	}

	public boolean hasInitializer(){
		return !isDeclaration();
	}

	/**
	 * Materialization is used to construct functions only as they're needed. This
	 * is useful to reduce memory usage in LLVM or parsing work done by the
	 * BitcodeReader to load the Module.
	 * isMaterializable - If this function's Module is being lazily streamed in
	 * functions from disk or some other source, this method can be used to check
	 * to see if the function has been read in yet or not.
	 * @return
	 */
	public boolean isMaterializable(){
		// TODO
		return true;
	}

	/**
	 * Returns true if this function was loaded from a GVMaterializer that's
	 * still attached to its Module and that knows how to dematerialize the function.
	 * @return
	 */
	public boolean isDematerializable(){
		// TODO
		return true;
	}

	/**
	 * make sure this GlobalValue is fully read. If the module is corrupt,
	 * this returns true and fills in the optional string with information
	 * about the problem.  If successful, this returns false.
	 * @param errInfo
	 * @return
	 */
	public boolean Materialize(String errInfo){
		// TODO
		return true;
	}

	/**
	 * If this GlobalValue is read in, and if the GVMaterializer supports it,
	 * release the memory for the function, and set it up to be materialized lazily.
	 * If !isDematerializable(), this method is a noop.
	 */
	public void Dematerialize(){
		// TODO
	}

	public void destroyConstant(){
		// TODO
	}

	/**
	 * This method unlinks 'this' from the containing module and deletes it.
	 */
	public void eraseFromParent(){
		// TODO
	}
}
