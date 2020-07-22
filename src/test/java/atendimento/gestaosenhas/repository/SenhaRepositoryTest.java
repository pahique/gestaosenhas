package atendimento.gestaosenhas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import atendimento.gestaosenhas.model.Senha;
import atendimento.gestaosenhas.model.TipoSenha;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SenhaRepositoryTest {

	@Autowired
	protected TestEntityManager em;

	@Autowired
	protected SenhaRepository senhaRepository;
	
	@Test
	public void findEmitidasByTipoSenha() throws Exception {
		TipoSenha preferencial = em.find(TipoSenha.class, TipoSenha.PREFERENCIAL);
		TipoSenha normal = em.find(TipoSenha.class, TipoSenha.NORMAL);
		
		Senha senha1 = new Senha();
		senha1.setTipoSenha(normal);
		senha1.setTimestampEmissao(new Date());
		senha1.setNumero(5);
		em.persist(senha1);
		
		Senha senha2 = new Senha();
		senha2.setTipoSenha(preferencial);
		senha2.setTimestampEmissao(new Date());
		senha2.setTimestampChamada(new Date());
		senha2.setNumero(6);
		em.persist(senha2);
		
		Senha senha3 = new Senha();
		senha3.setTipoSenha(preferencial);
		senha3.setTimestampEmissao(new Date());
		senha3.setTimestampChamada(new Date());
		senha3.setNumero(7);
		em.persist(senha3);
		
		Senha senha4 = new Senha();
		senha4.setTipoSenha(normal);
		senha4.setTimestampEmissao(new Date());
		senha4.setNumero(8);
		em.persist(senha4);

		Senha senha5 = new Senha();
		senha5.setTipoSenha(preferencial);
		senha5.setTimestampEmissao(new Date());
		senha5.setNumero(9);
		em.persist(senha5);
		
		em.flush();
		
		List<Senha> senhasEmitidasPref = senhaRepository.findEmitidasByTipoSenha(TipoSenha.PREFERENCIAL);
		assertEquals(1, senhasEmitidasPref.size());
		assertEquals(senha5.getNumero(), senhasEmitidasPref.get(0).getNumero());

		List<Senha> senhasEmitidasNorm = senhaRepository.findEmitidasByTipoSenha(TipoSenha.NORMAL);
		assertEquals(2, senhasEmitidasNorm.size());
		assertEquals(senha1.getNumero(), senhasEmitidasNorm.get(0).getNumero());
		assertEquals(senha4.getNumero(), senhasEmitidasNorm.get(1).getNumero());
	}	
	
	@Test
	public void getUltimaSenhaChamada() throws Exception {
		TipoSenha preferencial = em.find(TipoSenha.class, TipoSenha.PREFERENCIAL);
		TipoSenha normal = em.find(TipoSenha.class, TipoSenha.NORMAL);
		
		Senha senha1 = new Senha();
		senha1.setTipoSenha(normal);
		senha1.setTimestampEmissao(new Date());
		senha1.setNumero(5);
		em.persist(senha1);
		
		Senha senha2 = new Senha();
		senha2.setTipoSenha(preferencial);
		senha2.setTimestampEmissao(new Date());
		senha2.setTimestampChamada(new Date());
		senha2.setNumero(6);
		em.persist(senha2);
		
		Senha senha3 = new Senha();
		senha3.setTipoSenha(preferencial);
		senha3.setTimestampEmissao(new Date());
		senha3.setTimestampChamada(new Date());
		senha3.setNumero(7);
		em.persist(senha3);
		
		Senha senha4 = new Senha();
		senha4.setTipoSenha(normal);
		senha4.setTimestampEmissao(new Date());
		senha4.setNumero(8);
		em.persist(senha4);

		Senha senha5 = new Senha();
		senha5.setTipoSenha(preferencial);
		senha5.setTimestampEmissao(new Date());
		senha5.setNumero(9);
		em.persist(senha5);
		
		em.flush();
		
		Senha ultimaSenha = senhaRepository.getUltimaSenhaChamada();
		assertEquals(senha3.getNumero(), ultimaSenha.getNumero());
	}
}
