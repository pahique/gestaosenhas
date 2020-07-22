package atendimento.gestaosenhas.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import atendimento.gestaosenhas.model.SenhaChamada;

public interface SenhaChamadaRepository extends CrudRepository<SenhaChamada, Long> {

	@Query("SELECT e FROM SenhaChamada e where e.timestampChamada = (select max(timestampChamada) from SenhaChamada)")
	public SenhaChamada getUltimaSenha();
}
