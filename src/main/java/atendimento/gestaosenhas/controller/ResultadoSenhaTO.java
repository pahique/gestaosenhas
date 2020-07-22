package atendimento.gestaosenhas.controller;

public class ResultadoSenhaTO {

	protected SenhaTO senha;
	protected String error;
	
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
