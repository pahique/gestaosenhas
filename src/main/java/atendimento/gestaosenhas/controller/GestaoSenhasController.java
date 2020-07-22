package atendimento.gestaosenhas.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import atendimento.gestaosenhas.model.ContadorSenha;
import atendimento.gestaosenhas.model.Senha;
import atendimento.gestaosenhas.service.GestaoSenhasService;
import atendimento.gestaosenhas.service.TipoSenhaInvalidoException;

@RestController
@RequestMapping("/api")
public class GestaoSenhasController {

    private static final Logger LOGGER = LogManager.getLogger(GestaoSenhasController.class);

    @Autowired
    GestaoSenhasService service;
    
	@GetMapping("/senha/ultima")
	public RespostaSenhaTO getUltimaSenhaChamada() {
		LOGGER.debug("getUltimaSenhaChamada()");
		RespostaSenhaTO result = new RespostaSenhaTO();
		Senha senha = service.getUltimaSenhaChamada();
		if (senha != null) {
			SenhaTO s = new SenhaTO();
			s.setTipoSenha(senha.getTipoSenha().getSigla());
			s.setNumero(senha.getNumero());
			result.setSenha(s);
		} else {
			SenhaTO s = new SenhaTO();
			s.setTipoSenha("");
			s.setNumero(0);
			result.setSenha(s);
			result.setError("Nenhuma senha foi chamada");
		}
		return result;
	}
	
	@PostMapping("/senha/nova")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RespostaSenhaTO gerarNovaSenha(@RequestBody ParamTipoSenhaTO param) {
		RespostaSenhaTO result = new RespostaSenhaTO();
		if (param == null || param.getTipoSenha() == null) {
			result.setError("O parâmetro 'tipoSenha' é obrigatorio");
		} else {
			try {
				Senha senha = service.gerarNovaSenha(param.getTipoSenha());
				if (senha != null) {
					SenhaTO s = new SenhaTO();
					s.setTipoSenha(senha.getTipoSenha().getSigla());
					s.setNumero(senha.getNumero());
					result.setSenha(s);
				} else {
					result.setError("Erro ao gerar nova senha");
				}
			} catch(TipoSenhaInvalidoException e) {
				result.setError(String.format("Parâmetro 'tipoSenha' inválido: %d", param.getTipoSenha()));
			} catch(Exception e) {
				result.setError("Erro ao gerar nova senha");
			}
		}
		return result;
	}
	
	@PostMapping("/senha/proxima")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RespostaSenhaTO chamarProximaSenha() {
		RespostaSenhaTO result = new RespostaSenhaTO();
		try {
			Senha senha = service.chamarProximaSenha();
			if (senha != null) {
				SenhaTO s = new SenhaTO();
				s.setTipoSenha(senha.getTipoSenha().getSigla());
				s.setNumero(senha.getNumero());
				result.setSenha(s);
			} else {
				result.setError("Nenhuma senha a ser chamada");
			}
		} catch(Exception e) {
			result.setError("Erro ao chamar próxima senha");
		}
		return result;
	}
	
	@DeleteMapping("/senha/reset")
	@ResponseStatus(code = HttpStatus.OK)
	public RespostaContadorSenhaTO reiniciarContagemSenhas(@RequestBody ParamTipoSenhaTO param) {
		RespostaContadorSenhaTO result = new RespostaContadorSenhaTO();
		try {
			if (param == null || param.getTipoSenha() == null) {
				service.reiniciarContagemDeSenhas();
			} else {
				service.reiniciarContagemDeSenhas(param.getTipoSenha());
			}
			Iterable<ContadorSenha> contadores = service.getContadoresSenha();
			List<ContadorSenhaTO> lista = new ArrayList<ContadorSenhaTO>();
			for (ContadorSenha contador : contadores) {
				ContadorSenhaTO c = new ContadorSenhaTO();
				c.setTipoSenha(contador.getCodigoTipoSenha().toString()); //TODO - resolver mapeamento!!!
				c.setNumeroAtual(contador.getNumeroAtual());
				lista.add(c);
			}
			result.setContadores(lista);
		} catch(Exception e) {
			result.setError("Erro ao zerar contagem de senhas");
		}
		return result;
	}
	
}
