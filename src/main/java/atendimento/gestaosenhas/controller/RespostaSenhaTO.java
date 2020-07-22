package atendimento.gestaosenhas.controller;

import java.io.Serializable;

public class RespostaSenhaTO implements Serializable {

	private static final long serialVersionUID = -1081873735274920756L;

	protected SenhaTO senha;
	protected String error;
	
	public RespostaSenhaTO() {	
	}
	
	public SenhaTO getSenha() {
		return senha;
	}
	public void setSenha(SenhaTO senha) {
		this.senha = senha;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
