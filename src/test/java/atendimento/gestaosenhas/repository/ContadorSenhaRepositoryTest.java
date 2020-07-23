package atendimento.gestaosenhas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import atendimento.gestaosenhas.model.ContadorSenha;
import atendimento.gestaosenhas.model.TipoSenha;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContadorSenhaRepositoryTest {

	@Autowired
	TestEntityManager em;

	@Autowired
	ContadorSenhaRepository contadorSenhaRepository;
	
	@Test
	public void findByTipoSenha() throws Exception {
	
		ContadorSenha contador1 = new ContadorSenha();
		contador1.setCodigoTipoSenha(TipoSenha.PREFERENCIAL);
		contador1.setNumeroAtual(10);
		em.merge(contador1);
		
		ContadorSenha contador2 = new ContadorSenha();
		contador2.setCodigoTipoSenha(TipoSenha.NORMAL);
		contador2.setNumeroAtual(5);
		em.merge(contador2);
		
		em.flush();

		ContadorSenha result1 = contadorSenhaRepository.findByTipoSenha(TipoSenha.PREFERENCIAL);
		assertEquals(result1.getNumeroAtual(), contador1.getNumeroAtual());

		ContadorSenha result2 = contadorSenhaRepository.findByTipoSenha(TipoSenha.NORMAL);
		assertEquals(result2.getNumeroAtual(), contador2.getNumeroAtual());
	}
}
