package atendimento.gestaosenhas.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import atendimento.gestaosenhas.model.SenhaChamada;
import atendimento.gestaosenhas.model.SenhaEmitida;
import atendimento.gestaosenhas.service.GestaoSenhasService;

@RestController
@RequestMapping("/api")
public class GestaoSenhasController {

    private static final Logger LOGGER = LogManager.getLogger(GestaoSenhasController.class);

    @Autowired
    GestaoSenhasService service;
    
	@GetMapping("/senha/chamada")
	public ResultadoSenhaTO getUltimaSenhaChamada() {
		LOGGER.debug("getUltimaSenhaChamada()");
		ResultadoSenhaTO result = new ResultadoSenhaTO();
		SenhaChamada senha = service.getUltimaSenhaChamada();
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
	public ResultadoSenhaTO gerarNovaSenha(@RequestBody Integer tipoSenha) {
		ResultadoSenhaTO result = new ResultadoSenhaTO();
		if (tipoSenha == null) {
			result.setError("O parametro 'tipoSenha' é obrigatorio");
		} else {
			SenhaEmitida senha = service.gerarNovaSenha(tipoSenha);
			if (senha != null) {
				SenhaTO s = new SenhaTO();
				s.setTipoSenha(senha.getTipoSenha().getSigla());
				s.setNumero(senha.getNumero());
				result.setSenha(s);
			} else {
				result.setError("Erro ao gerar nova senha");
			}
		}
		return result;
	}
}
