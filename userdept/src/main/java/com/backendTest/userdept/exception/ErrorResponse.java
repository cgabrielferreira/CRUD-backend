package com.backendTest.userdept.exception;

public class ErrorResponse {
	private String mensagem;
	private int status;

	public ErrorResponse(String mensagem, int status) {
		this.mensagem = mensagem;
		this.status = status;
	}

	// Getters e setters
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
