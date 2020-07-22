package atendimento.gestaosenhas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import atendimento.gestaosenhas.model.ContadorSenha;
import atendimento.gestaosenhas.model.Senha;
import atendimento.gestaosenhas.model.TipoSenha;
import atendimento.gestaosenhas.repository.ContadorSenhaRepository;
import atendimento.gestaosenhas.repository.SenhaRepository;
import atendimento.gestaosenhas.repository.TipoSenhaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestaoSenhasServiceTest {

	@Autowired
	protected GestaoSenhasService service;
	
	@Autowired
	protected TipoSenhaRepository tipoSenhaRepository;
	
	@Autowired
	protected ContadorSenhaRepository contadorSenhaRepository;

	@Autowired
	protected SenhaRepository senhaRepository;
	
	@After
	public void tearDown() {
		senhaRepository.deleteAll();
	}
	
	@Test
	public void getUltimaSenhaChamada() throws Exception {
		Optional<TipoSenha> preferencial = tipoSenhaRepository.findById(TipoSenha.PREFERENCIAL);
		Optional<TipoSenha> normal = tipoSenhaRepository.findById(TipoSenha.NORMAL);
		
		Senha senha1 = new Senha();
		senha1.setTipoSenha(normal.get());
		senha1.setNumero(1);
		senha1.setTimestampEmissao(new Date());
		senha1.setTimestampChamada(new Date());
		senhaRepository.save(senha1);
		
		Senha senha2 = new Senha();
		senha2.setTipoSenha(preferencial.get());
		senha2.setNumero(2);
		senha2.setTimestampEmissao(new Date());
		senha2.setTimestampChamada(new Date());
		senhaRepository.save(senha2);
		
		Senha senha3 = new Senha();
		senha3.setTipoSenha(preferencial.get());
		senha3.setNumero(3);
		senha3.setTimestampEmissao(new Date());
		senhaRepository.save(senha3);
		
		Senha senha = service.getUltimaSenhaChamada();
		assertEquals(senha2.getNumero(), senha.getNumero());
	}
	
	@Test
	public void getUltimaSenhaChamada_inexistente() throws Exception {
		Senha senha = service.getUltimaSenhaChamada();
		assertNull(senha);
	}
	
	@Test
	public void gerarNovaSenha() throws Exception {
		ContadorSenha contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		Integer numeroAntes = contadorPreferencial.getNumeroAtual();
		
		Senha senha = service.gerarNovaSenha(TipoSenha.PREFERENCIAL);
		assertEquals(TipoSenha.PREFERENCIAL, senha.getTipoSenha().getCodigo());
		assertEquals(new Integer(numeroAntes+1), senha.getNumero());
		assertNotNull(senha.getTimestampEmissao());
		assertNull(senha.getTimestampChamada());
		
		contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		Integer numeroDepois = contadorPreferencial.getNumeroAtual();
		assertEquals(numeroDepois, new Integer(numeroAntes+1));
	}
	
	@Test(expected = TipoSenhaInvalidoException.class)
	public void gerarNovaSenha_tipoInvalido() throws Exception {
		service.gerarNovaSenha(3);
	}
	
	@Test
	public void reiniciarContagemDeSenhas() throws Exception {
		ContadorSenha contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		ContadorSenha contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		contadorPreferencial.setNumeroAtual(5);
		contadorSenhaRepository.save(contadorPreferencial);

		contadorNormal.setNumeroAtual(7);
		contadorSenhaRepository.save(contadorNormal);
		
		service.reiniciarContagemDeSenhas();
		
		contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		assertEquals(new Integer(0), contadorPreferencial.getNumeroAtual());
		assertEquals(new Integer(0), contadorNormal.getNumeroAtual());
	}
	
	@Test
	public void reiniciarContagemDeSenhas_preferencial() throws Exception {
		ContadorSenha contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		ContadorSenha contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		contadorPreferencial.setNumeroAtual(5);
		contadorSenhaRepository.save(contadorPreferencial);

		contadorNormal.setNumeroAtual(7);
		contadorSenhaRepository.save(contadorNormal);
		
		service.reiniciarContagemDeSenhas(TipoSenha.PREFERENCIAL);
		
		contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		assertEquals(new Integer(0), contadorPreferencial.getNumeroAtual());
		assertEquals(new Integer(7), contadorNormal.getNumeroAtual());
	}
	
	@Test
	public void reiniciarContagemDeSenhas_normal() throws Exception {
		ContadorSenha contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		ContadorSenha contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		contadorPreferencial.setNumeroAtual(5);
		contadorSenhaRepository.save(contadorPreferencial);

		contadorNormal.setNumeroAtual(7);
		contadorSenhaRepository.save(contadorNormal);
		
		service.reiniciarContagemDeSenhas(TipoSenha.NORMAL);
		
		contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		assertEquals(new Integer(5), contadorPreferencial.getNumeroAtual());
		assertEquals(new Integer(0), contadorNormal.getNumeroAtual());
	}
	
	@Test
	public void getContadoresSenha() throws Exception {
		ContadorSenha contadorPreferencial = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		ContadorSenha contadorNormal = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		
		contadorPreferencial.setNumeroAtual(5);
		contadorSenhaRepository.save(contadorPreferencial);

		contadorNormal.setNumeroAtual(7);
		contadorSenhaRepository.save(contadorNormal);
		
		Iterable<ContadorSenha> contadores = service.getContadoresSenha();
		Iterator<ContadorSenha> iter = contadores.iterator();
		ContadorSenha contador1 = iter.next();
		ContadorSenha contador2 = iter.next();
		if (contador1.getCodigoTipoSenha().equals(TipoSenha.PREFERENCIAL)) {
			assertEquals(contadorPreferencial.getNumeroAtual(), contador1.getNumeroAtual());
			assertEquals(contadorNormal.getNumeroAtual(), contador2.getNumeroAtual());
		} else {
			assertEquals(contadorNormal.getNumeroAtual(), contador1.getNumeroAtual());
			assertEquals(contadorPreferencial.getNumeroAtual(), contador2.getNumeroAtual());
		}
	}
	
	@Test
	public void chamarProximaSenha_preferencial() throws Exception {
		Optional<TipoSenha> preferencial = tipoSenhaRepository.findById(TipoSenha.PREFERENCIAL);
		Optional<TipoSenha> normal = tipoSenhaRepository.findById(TipoSenha.NORMAL);
		
		Senha senha1 = new Senha();
		senha1.setTipoSenha(normal.get());
		senha1.setNumero(1);
		senha1.setTimestampEmissao(new Date());
		senha1.setTimestampChamada(new Date());
		senhaRepository.save(senha1);
		
		Senha senha2 = new Senha();
		senha2.setTipoSenha(normal.get());
		senha2.setNumero(2);
		senha2.setTimestampEmissao(new Date());
		senhaRepository.save(senha2);
		
		Senha senha3 = new Senha();
		senha3.setTipoSenha(preferencial.get());
		senha3.setNumero(3);
		senha3.setTimestampEmissao(new Date());
		senhaRepository.save(senha3);

		Senha proxima = service.chamarProximaSenha();
		assertEquals(senha3.getNumero(), proxima.getNumero());
	}
	
	@Test
	public void chamarProximaSenha_normal() throws Exception {
		Optional<TipoSenha> preferencial = tipoSenhaRepository.findById(TipoSenha.PREFERENCIAL);
		Optional<TipoSenha> normal = tipoSenhaRepository.findById(TipoSenha.NORMAL);
		
		Senha senha1 = new Senha();
		senha1.setTipoSenha(normal.get());
		senha1.setNumero(1);
		senha1.setTimestampEmissao(new Date());
		senha1.setTimestampChamada(new Date());
		senhaRepository.save(senha1);
		
		Senha senha2 = new Senha();
		senha2.setTipoSenha(normal.get());
		senha2.setNumero(2);
		senha2.setTimestampEmissao(new Date());
		senhaRepository.save(senha2);
		
		Senha senha3 = new Senha();
		senha3.setTipoSenha(preferencial.get());
		senha3.setNumero(3);
		senha3.setTimestampEmissao(new Date());
		senha3.setTimestampChamada(new Date());
		senhaRepository.save(senha3);

		Senha proxima = service.chamarProximaSenha();
		assertEquals(senha2.getNumero(), proxima.getNumero());
	}
}
