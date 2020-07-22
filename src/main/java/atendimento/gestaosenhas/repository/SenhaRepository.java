package atendimento.gestaosenhas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import atendimento.gestaosenhas.model.Senha;

public interface SenhaRepository extends CrudRepository<Senha, Long> {

	@Query("SELECT e FROM Senha e where e.tipoSenha.codigo = :codigo and e.timestampChamada is null order by e.timestampEmissao")
	public List<Senha> findEmitidasByTipoSenha(@Param("codigo")Integer codigoTipoSenha);

	@Query("SELECT e FROM Senha e where e.timestampChamada = (select max(timestampChamada) from Senha)")
	public Senha getUltimaSenhaChamada();
}
