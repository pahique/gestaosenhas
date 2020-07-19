package processoseletivo.gestaosenhas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import processoseletivo.gestaosenhas.dao.ContadorSenhaDao;
import processoseletivo.gestaosenhas.dao.SenhaChamadaDao;
import processoseletivo.gestaosenhas.dao.SenhaEmitidaDao;
import processoseletivo.gestaosenhas.dao.TipoSenhaDao;
import processoseletivo.gestaosenhas.model.ContadorSenha;
import processoseletivo.gestaosenhas.model.SenhaChamada;
import processoseletivo.gestaosenhas.model.SenhaEmitida;
import processoseletivo.gestaosenhas.model.TipoSenha;

@Service
public class GestaoSenhasService {
	
	@Autowired
	private SenhaChamadaDao senhaChamadaDao;
	
	@Autowired
	private SenhaEmitidaDao senhaEmitidaDao;
	
	@Autowired
	private TipoSenhaDao tipoSenhaDao;
	
	@Autowired
	private ContadorSenhaDao contadorSenhaDao;
	
	
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public SenhaChamada getUltimaSenhaChamada() {
		SenhaChamada ultimaSenha = senhaChamadaDao.getUltimaSenha();
		return ultimaSenha;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SenhaEmitida gerarNovaSenha(int tipoSenha) {
		SenhaEmitida novaSenha = new SenhaEmitida();
		novaSenha.setTimestamp(new Date());
		TipoSenha tipoSenhaObj = tipoSenhaDao.get(tipoSenha);
		novaSenha.setTipoSenha(tipoSenhaObj);
		ContadorSenha contador = contadorSenhaDao.findByTipoSenha(tipoSenha);
		novaSenha.setNumero(contador.getNumeroAtual());
		senhaEmitidaDao.save(novaSenha);
		ContadorSenha novoContador = new ContadorSenha();
		novoContador.setTipoSenha(contador.getTipoSenha());
		novoContador.setNumeroAtual(contador.getNumeroAtual() + 1);
		contadorSenhaDao.update(novoContador);
		return novaSenha;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void reiniciarContagemDeSenhas(int tipoSenha) {
		ContadorSenha contador = contadorSenhaDao.findByTipoSenha(tipoSenha);
		contador.setNumeroAtual(0);
		contadorSenhaDao.update(contador);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public SenhaChamada chamarProximaSenha() {
		SenhaChamada result = null;
		List<SenhaEmitida> senhasPreferenciais = senhaEmitidaDao.findByTipoSenha(TipoSenha.PREFERENCIAL);
		if (!senhasPreferenciais.isEmpty()) {
			SenhaEmitida senhaMaisAntiga = senhasPreferenciais.get(0);
			SenhaChamada senhaChamada = new SenhaChamada();
			senhaChamada.setTimestampEmitida(senhaMaisAntiga.getTimestamp());
			senhaChamada.setTimestampChamada(new Date());
			senhaChamada.setNumero(senhaMaisAntiga.getNumero());
			senhaChamada.setTipoSenha(senhaMaisAntiga.getTipoSenha());
			senhaChamadaDao.save(senhaChamada);
			senhaEmitidaDao.delete(senhaMaisAntiga);
			result = senhaChamada;
		} else {
			List<SenhaEmitida> senhasNormais = senhaEmitidaDao.findByTipoSenha(TipoSenha.NORMAL);
			if (!senhasNormais.isEmpty()) {
				SenhaEmitida senhaMaisAntiga = senhasPreferenciais.get(0);
				SenhaChamada senhaChamada = new SenhaChamada();
				senhaChamada.setTimestampEmitida(senhaMaisAntiga.getTimestamp());
				senhaChamada.setTimestampChamada(new Date());
				senhaChamada.setNumero(senhaMaisAntiga.getNumero());
				senhaChamada.setTipoSenha(senhaMaisAntiga.getTipoSenha());
				senhaChamadaDao.save(senhaChamada);
				senhaEmitidaDao.delete(senhaMaisAntiga);
				result = senhaChamada;
			}
		}
		return result;
	}
}
