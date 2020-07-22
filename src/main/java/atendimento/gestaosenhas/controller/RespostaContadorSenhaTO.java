package atendimento.gestaosenhas.controller;

import java.io.Serializable;
import java.util.List;

public class RespostaContadorSenhaTO implements Serializable {

	private static final long serialVersionUID = -7662769965808798801L;

	protected List<ContadorSenhaTO> contadores;
	protected String error;
	
	public RespostaContadorSenhaTO() {
	}

	public List<ContadorSenhaTO> getContadores() {
		return contadores;
	}

	public void setContadores(List<ContadorSenhaTO> contadores) {
		this.contadores = contadores;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
