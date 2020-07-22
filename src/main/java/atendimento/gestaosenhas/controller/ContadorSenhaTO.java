package atendimento.gestaosenhas.controller;

import java.io.Serializable;

public class ContadorSenhaTO implements Serializable {

	private static final long serialVersionUID = 574652607223424424L;

	protected String tipoSenha;
	protected Integer numeroAtual;
	
	public ContadorSenhaTO() {	
	}
	
	public String getTipoSenha() {
		return tipoSenha;
	}
	public void setTipoSenha(String tipoSenha) {
		this.tipoSenha = tipoSenha;
	}
	public Integer getNumeroAtual() {
		return numeroAtual;
	}
	public void setNumeroAtual(Integer numeroAtual) {
		this.numeroAtual = numeroAtual;
	}
	
	
}
