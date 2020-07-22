package atendimento.gestaosenhas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_senha")
public class TipoSenha implements Serializable {
    
	private static final long serialVersionUID = -8102494373604899187L;
	
	public static final Integer PREFERENCIAL = 1;
	public static final Integer NORMAL = 2;
	
	@Id
	protected Integer codigo;	
	
	protected String sigla;
    
	protected String descricao;

	
    public TipoSenha() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
        return Objects.equals(codigo, tipoSenha.codigo) 
        		&& Objects.equals(sigla, tipoSenha.sigla)
        		&& Objects.equals(descricao, tipoSenha.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, sigla, descricao);
    }

    @Override
    public String toString() {
        return String.format("TipoSenha[codigo=%d,sigla=%s,descricao=%s", getCodigo(), getSigla(), getDescricao());
    }

}
