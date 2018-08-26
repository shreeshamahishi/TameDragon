package org.tamedragon.common.llvmir.types;

public class TLSModel {
	enum Model {
	      GeneralDynamic,
	      LocalDynamic,
	      InitialExec,
	      LocalExec
	    }
	public static Model getTLSModel(GlobalValue gV, Reloc.Model reloc){
		  boolean isLocal = gV.hasLocalLinkage();
		  boolean isDeclaration = gV.isDeclaration();
		  // FIXME: what should we do for protected and internal visibility?
		  // For variables, is internal different from hidden?
		  boolean isHidden = gV.hasHiddenVisibility();

		  if(reloc == reloc.PIC_) {
		    if (isLocal || isHidden)
		      return TLSModel.Model.LocalDynamic;
		    else
		      return TLSModel.Model.GeneralDynamic;
		  } else {
		    if (!isDeclaration || isHidden)
		      return TLSModel.Model.LocalExec;
		    else
		      return TLSModel.Model.InitialExec;
		  }
	}
}
