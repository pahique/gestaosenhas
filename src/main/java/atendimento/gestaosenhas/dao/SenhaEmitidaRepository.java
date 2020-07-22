package atendimento.gestaosenhas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import atendimento.gestaosenhas.model.SenhaEmitida;

public interface SenhaEmitidaRepository extends CrudRepository<SenhaEmitida, Long> {

	@Query("SELECT e FROM SenhaEmitida e where e.tipoSenha.codigo = :codigo")
	public List<SenhaEmitida> findByTipoSenha(@Param("codigo")Integer tipoSenha);
}
