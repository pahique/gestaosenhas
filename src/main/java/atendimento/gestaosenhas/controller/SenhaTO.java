package atendimento.gestaosenhas.controller;

import java.io.Serializable;

public class SenhaTO implements Serializable {

	private static final long serialVersionUID = -4059745084193838625L;

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
