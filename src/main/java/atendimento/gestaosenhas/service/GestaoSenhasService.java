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

import atendimento.gestaosenhas.model.ContadorSenha;
import atendimento.gestaosenhas.model.Senha;
import atendimento.gestaosenhas.model.TipoSenha;
import atendimento.gestaosenhas.repository.ContadorSenhaRepository;
import atendimento.gestaosenhas.repository.SenhaRepository;
import atendimento.gestaosenhas.repository.TipoSenhaRepository;

@Service
public class GestaoSenhasService {
	
    private static final Logger LOGGER = LogManager.getLogger(GestaoSenhasService.class);

    @Autowired
	private SenhaRepository senhaRepository;
	
	@Autowired
	private TipoSenhaRepository tipoSenhaRepository;
	
	@Autowired
	private ContadorSenhaRepository contadorSenhaRepository;
	
	
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Senha getUltimaSenhaChamada() {
		Senha ultimaSenha = null;
		try {
			ultimaSenha = senhaRepository.getUltimaSenhaChamada();
		} catch(EmptyResultDataAccessException e) {
			LOGGER.error("Nenhuma senha foi chamada"); 
		}
		return ultimaSenha;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Senha gerarNovaSenha(int tipoSenha) {
		Senha novaSenha = new Senha();
		novaSenha.setTimestampEmissao(new Date());
		Optional<TipoSenha> tipoSenhaObj = tipoSenhaRepository.findById(tipoSenha);
		if (tipoSenhaObj.isPresent()) {
			novaSenha.setTipoSenha(tipoSenhaObj.get());
			ContadorSenha contador = contadorSenhaRepository.findByTipoSenha(tipoSenha);
			novaSenha.setNumero(contador.getNumeroAtual() + 1);
			senhaRepository.save(novaSenha);
			ContadorSenha novoContador = new ContadorSenha();
			novoContador.setCodigoTipoSenha(contador.getCodigoTipoSenha());
			novoContador.setNumeroAtual(contador.getNumeroAtual() + 1);
			contadorSenhaRepository.save(novoContador);
		} else {
			LOGGER.error(String.format("Tipo de senha inválido: %d", tipoSenha));
			throw new TipoSenhaInvalidoException(tipoSenha);
		}
		return novaSenha;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void reiniciarContagemDeSenhas(int tipoSenha) {
		ContadorSenha contador = contadorSenhaRepository.findByTipoSenha(tipoSenha);
		contador.setNumeroAtual(0);
		contadorSenhaRepository.save(contador);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void reiniciarContagemDeSenhas() {
		Iterable<ContadorSenha> contadores = contadorSenhaRepository.findAll();
		for (ContadorSenha contador : contadores) {
			contador.setNumeroAtual(0);
		}
		contadorSenhaRepository.saveAll(contadores);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Iterable<ContadorSenha> getContadoresSenha() {
		return contadorSenhaRepository.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Senha chamarProximaSenha() {
		Senha result = null;
		List<Senha> senhasPreferenciais = senhaRepository.findEmitidasByTipoSenha(TipoSenha.PREFERENCIAL);
		if (!senhasPreferenciais.isEmpty()) {
			Senha senhaMaisAntiga = senhasPreferenciais.get(0);
			senhaMaisAntiga.setTimestampChamada(new Date());
			senhaRepository.save(senhaMaisAntiga);
			result = senhaMaisAntiga;
		} else {
			List<Senha> senhasNormais = senhaRepository.findEmitidasByTipoSenha(TipoSenha.NORMAL);
			if (!senhasNormais.isEmpty()) {
				Senha senhaMaisAntiga = senhasNormais.get(0);
				senhaMaisAntiga.setTimestampChamada(new Date());
				senhaRepository.save(senhaMaisAntiga);
				result = senhaMaisAntiga;
			}
		}
		return result;
	}
}
