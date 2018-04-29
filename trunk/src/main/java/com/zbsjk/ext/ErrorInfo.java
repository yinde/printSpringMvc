package com.zbsjk.ext;

public class ErrorInfo {  
    
    public static final Integer OK = 0;  
    public static final Integer ERROR = -1;  
      
    private String msg;  
    private String field;
      
    public ErrorInfo() {  
          
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}  
      
    
      
      
}  