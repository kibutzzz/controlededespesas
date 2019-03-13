package br.inf.safetech.helper;

public class StatusInfo {

	private StatusType type;
	private String message;
	

	public StatusInfo(StatusType tipo, String message) {
		super();
		this.type = tipo;
		this.message = message;
	}

	public StatusType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
	
	

	
}
