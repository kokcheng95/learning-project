package com.example.test.exception.handler;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4646309095420034543L;

	private String target;
	private String fieldName;
	private String fieldVal;

	public EmployeeNotFoundException(Long id, String target) {
		super("Could not find employee id => " + id);
		this.target = target;
	}

	public EmployeeNotFoundException(String fieldName,String fieldVal, String target) {
		super("Could not find employee "+ fieldName+"=>"  + fieldVal);
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}
}