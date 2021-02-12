package com.gutotech.loteriasapi.controller.exception;

public class ErrorResponse {
	private int status;
	private String error;
	private String message;
	private long timestamp;
	private String path;

	public ErrorResponse(int status, String error, String message, long timestamp, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = timestamp;
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
