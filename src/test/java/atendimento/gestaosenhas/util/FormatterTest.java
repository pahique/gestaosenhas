package atendimento.gestaosenhas.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FormatterTest {

	@Test
	public void formatSenha() throws Exception {
		String siglaTipoSenha = "P";
		Integer numero = 45;
		
		String resultado = Formatter.formatSenha(siglaTipoSenha,  numero);
		Assert.assertEquals("P0045", resultado);
		
		siglaTipoSenha = null;
		numero = null;
		resultado = Formatter.formatSenha(siglaTipoSenha,  numero);
		Assert.assertEquals(" 0000", resultado);		
	}
	
}
