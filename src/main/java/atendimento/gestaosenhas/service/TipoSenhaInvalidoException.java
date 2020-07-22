package atendimento.gestaosenhas.service;

public class TipoSenhaInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 8879166401231919634L;
	
	protected Integer codigoTipoSenha;
	
	public TipoSenhaInvalidoException(Integer codigoTipoSenha) {
		super();
		this.codigoTipoSenha = codigoTipoSenha;
	}
}
