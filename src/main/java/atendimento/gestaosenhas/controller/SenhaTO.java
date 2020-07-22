package atendimento.gestaosenhas.controller;

public class SenhaTO {

	protected String tipoSenha;
	protected Integer numero;
	
	public SenhaTO() {
	}

	public String getTipoSenha() {
		return tipoSenha;
	}

	public void setTipoSenha(String tipoSenha) {
		this.tipoSenha = tipoSenha;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
