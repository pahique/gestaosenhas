package atendimento.gestaosenhas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_usuario")
public class TipoUsuario implements Serializable {
    
	private static final long serialVersionUID = -7576343007274887792L;
	
	@Id
	protected Integer codigo;
	
    protected String descricao;

    
    public TipoUsuario() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario tipoUsuario = (TipoUsuario) o;
        return Objects.equals(codigo, tipoUsuario.codigo) && Objects.equals(descricao, tipoUsuario.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descricao);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoUsuario[");
        sb.append("codigo='").append(getCodigo()).append("', ");
        sb.append("descricao='").append(getDescricao()).append("'");
        sb.append("]");
        return sb.toString();
    }
}