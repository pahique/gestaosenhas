package atendimento.gestaosenhas.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import atendimento.gestaosenhas.model.ContadorSenha;

public interface ContadorSenhaRepository extends CrudRepository<ContadorSenha, Integer> {

	@Query("SELECT e FROM ContadorSenha e where e.codigoTipoSenha = :codigo")
	public ContadorSenha findByTipoSenha(@Param("codigo") Integer codigoTipoSenha);
}
