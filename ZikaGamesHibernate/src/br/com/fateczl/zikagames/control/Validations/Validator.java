package br.com.fateczl.zikagames.control.Validations;

public class Validator {
	private boolean success;
	private String errorMessage;
	
	public Validator() {
		this.success = true;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		this.success = false;
	}	
}
