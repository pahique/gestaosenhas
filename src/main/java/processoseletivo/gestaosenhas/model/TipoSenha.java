package processoseletivo.gestaosenhas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tipo_senha")
public class TipoSenha implements Serializable {
    
	private static final long serialVersionUID = -8102494373604899187L;
	
	public static final Integer PREFERENCIAL = 1;
	public static final Integer NORMAL = 2;
	
	protected Integer codigo;
    protected String descricao;

    public TipoSenha() {
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
        if (!(o instanceof TipoSenha)) {
            return false;
        }
        TipoSenha tipoSenha = (TipoSenha) o;
        return Objects.equals(codigo, tipoSenha.codigo) && Objects.equals(descricao, tipoSenha.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descricao);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoSenha[");
        sb.append("codigo='").append(getCodigo()).append("', ");
        sb.append("descricao='").append(getDescricao()).append("'");
        sb.append("]");
        return sb.toString();
    }

}
