package atendimento.gestaosenhas.controller;

import java.io.Serializable;

public class ParamTipoSenhaTO implements Serializable {

	private static final long serialVersionUID = 2117974182656403677L;

	protected Integer tipoSenha;

	public ParamTipoSenhaTO() {
	}
	
	public Integer getTipoSenha() {
		return tipoSenha;
	}

	public void setTipoSenha(Integer tipoSenha) {
		this.tipoSenha = tipoSenha;
	}
	
}
