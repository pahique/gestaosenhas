package atendimento.gestaosenhas.dao;

import org.springframework.data.repository.CrudRepository;

import atendimento.gestaosenhas.model.TipoSenha;

public interface TipoSenhaRepository extends CrudRepository<TipoSenha, Integer> {

}
