package br.inf.safetech.helper;

public class StatusInfo {

	private String type;
	private String message;

	public StatusInfo(String type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	
}
