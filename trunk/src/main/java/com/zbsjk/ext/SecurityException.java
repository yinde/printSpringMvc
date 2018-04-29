package com.zbsjk.ext;

public class SecurityException extends RuntimeException {
	
	private String messageException;
	
	private String filed;
	
	public String getMessageException() {
		return messageException;
	}

	public void setMessageException(String messageException) {
		this.messageException = messageException;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

    private SecurityException(String message) {
        super(message);
    }

    public SecurityException(String field,String message){
    	this(message);
    	this.filed=field;
    	this.messageException=message;
    }
    
    private SecurityException() {
    }
}
