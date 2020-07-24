package atendimento.gestaosenhas.util;

public class Formatter {

	
	public static String formatSenha(String siglaTipoSenha, Integer numero) {
		if (siglaTipoSenha == null) {
			siglaTipoSenha = "";
		}
		if (numero == null) {
			numero = 0;
		}
		return String.format("%1s%04d", siglaTipoSenha, numero);
	}
}
