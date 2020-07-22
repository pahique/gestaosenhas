package atendimento.gestaosenhas.dao;

import org.springframework.data.repository.CrudRepository;

import atendimento.gestaosenhas.model.TipoUsuario;

public interface TipoUsuarioRepository extends CrudRepository<TipoUsuario, Integer> {

}
