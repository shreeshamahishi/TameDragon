package org.tamedragon.common.llvmir.instructions.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BaseCoreException extends Exception {

	// Common prepositions in error messages
	public static final String TO = " to ";
	public static final String FROM = " from ";
	
	protected static final String UNKNOWN_ERROR = "UNKNOWN ERROR"; 
	
	// Generic error messages for all terminator instructions
	public static final String INVALID_INDEX_FOR_SUCCESSOR = "Invalid successor index for ";
	public static final String SWITCH_ON_TYPE_MUST_BE_I32_OR_I64 = "The switch on must of type i32 or i64 only.";
	public static final String INVALID_INDEX_FOR_PREDECESSOR = "Invalid predecessor index for ";
	
	// Generic error messages for phi node instruction
	public static final String NO_SUCH_BASIC_BLOCK = "The basic block is not part of the phi node instruction.";
	
	// Generic messages for instructions
	public static final String CANNOT_REMOVE_INSTRUCTION_FROM_LIVE_BLOCK = "The instruction cannot be removed from a block that is considered dead, but is actually live.";
	
	private String errorMessage;
	
	private List<String> variablePartsOfMessage;
	
	protected final List<String> staticMessagesList = new ArrayList<String>();
	
	protected void init(List<String> staticMessagList, String ...messageParts){
		staticMessagesList.add(INVALID_INDEX_FOR_SUCCESSOR);
		staticMessagesList.add(SWITCH_ON_TYPE_MUST_BE_I32_OR_I64);
		staticMessagesList.add(INVALID_INDEX_FOR_PREDECESSOR);
		staticMessagesList.add(NO_SUCH_BASIC_BLOCK);
		
		this.staticMessagesList.addAll(staticMessagList);
		
		// Initialize the list that holds the variable parts of the message.
		variablePartsOfMessage = new ArrayList<String>();
		
		errorMessage = "";
		for(String msgPart : messageParts){
			errorMessage += msgPart;
			
			// Any message part that does not match any of the statically defined 
			// message is a variable part of the message
			if(!staticMessagesList.contains(msgPart))
				variablePartsOfMessage.add(msgPart);
			
		}
		
		// If the number of variable parts of the message are the same as the number
		// of message parts that were passed, this is not a valid message; default the
		// error message to "unknown error".
		if(variablePartsOfMessage.size() == messageParts.length)
			errorMessage = UNKNOWN_ERROR;
	}
	
	public BaseCoreException(List<String> staticMessagList, String ...messageParts){
		init(staticMessagList, messageParts);
	}
	
	public BaseCoreException(List<String> staticMessagList, Exception cause, String ...messageParts){
		super(cause);
		init(staticMessagList, messageParts);
	}
	
	public int getNumVariablePartsOfMessage(){
		return variablePartsOfMessage.size();
	}
	
	public String getVariableMessagePart(int index){
		if(index < 0 || index >= variablePartsOfMessage.size())
			return null;
		
		return variablePartsOfMessage.get(index);
	}
	
	public String getMessage(){
		return errorMessage;
	}
}
