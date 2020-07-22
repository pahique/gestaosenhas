package atendimento.gestaosenhas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import atendimento.gestaosenhas.dao.ContadorSenhaRepository;
import atendimento.gestaosenhas.dao.SenhaChamadaRepository;
import atendimento.gestaosenhas.dao.SenhaEmitidaRepository;
import atendimento.gestaosenhas.dao.TipoSenhaRepository;
import atendimento.gestaosenhas.model.ContadorSenha;
import atendimento.gestaosenhas.model.SenhaChamada;
import atendimento.gestaosenhas.model.SenhaEmitida;
import atendimento.gestaosenhas.model.TipoSenha;

@Service
public class GestaoSenhasService {
	
    private static final Logger LOGGER = LogManager.getLogger(GestaoSenhasService.class);

    @Autowired
	private SenhaChamadaRepository senhaChamadaRepository;
	
	@Autowired
	private SenhaEmitidaRepository senhaEmitidaRepository;
	
	@Autowired
	private TipoSenhaRepository tipoSenhaRepository;
	
	@Autowired
	private ContadorSenhaRepository contadorSenhaRepository;
	
	
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public SenhaChamada getUltimaSenhaChamada() {
		SenhaChamada ultimaSenha = null;
		try {
			ultimaSenha = senhaChamadaRepository.getUltimaSenha();
		} catch(EmptyResultDataAccessException e) {
			LOGGER.error("Nenhuma senha foi chamada"); 
		}
		return ultimaSenha;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SenhaEmitida gerarNovaSenha(int tipoSenha) {
		SenhaEmitida novaSenha = new SenhaEmitida();
		novaSenha.setTimestamp(new Date());
		Optional<TipoSenha> tipoSenhaObj = tipoSenhaRepository.findById(tipoSenha);
		novaSenha.setTipoSenha(tipoSenhaObj.get());
		ContadorSenha contador = contadorSenhaRepository.findByTipoSenha(tipoSenha);
		novaSenha.setNumero(contador.getNumeroAtual());
		senhaEmitidaRepository.save(novaSenha);
		ContadorSenha novoContador = new ContadorSenha();
		novoContador.setCodigoTipoSenha(contador.getCodigoTipoSenha());
		novoContador.setNumeroAtual(contador.getNumeroAtual() + 1);
		contadorSenhaRepository.save(novoContador);
		return novaSenha;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void reiniciarContagemDeSenhas(int tipoSenha) {
		ContadorSenha contador = contadorSenhaRepository.findByTipoSenha(tipoSenha);
		contador.setNumeroAtual(0);
		contadorSenhaRepository.save(contador);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SenhaChamada chamarProximaSenha() {
		SenhaChamada result = null;
		List<SenhaEmitida> senhasPreferenciais = senhaEmitidaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		if (!senhasPreferenciais.isEmpty()) {
			SenhaEmitida senhaMaisAntiga = senhasPreferenciais.get(0);
			SenhaChamada senhaChamada = new SenhaChamada();
			senhaChamada.setTimestampEmitida(senhaMaisAntiga.getTimestamp());
			senhaChamada.setTimestampChamada(new Date());
			senhaChamada.setNumero(senhaMaisAntiga.getNumero());
			senhaChamada.setTipoSenha(senhaMaisAntiga.getTipoSenha());
			senhaChamadaRepository.save(senhaChamada);
			senhaEmitidaRepository.delete(senhaMaisAntiga);
			result = senhaChamada;
		} else {
			List<SenhaEmitida> senhasNormais = senhaEmitidaRepository.findByTipoSenha(TipoSenha.NORMAL);
			if (!senhasNormais.isEmpty()) {
				SenhaEmitida senhaMaisAntiga = senhasPreferenciais.get(0);
				SenhaChamada senhaChamada = new SenhaChamada();
				senhaChamada.setTimestampEmitida(senhaMaisAntiga.getTimestamp());
				senhaChamada.setTimestampChamada(new Date());
				senhaChamada.setNumero(senhaMaisAntiga.getNumero());
				senhaChamada.setTipoSenha(senhaMaisAntiga.getTipoSenha());
				senhaChamadaRepository.save(senhaChamada);
				senhaEmitidaRepository.delete(senhaMaisAntiga);
				result = senhaChamada;
			}
		}
		return result;
	}
}
