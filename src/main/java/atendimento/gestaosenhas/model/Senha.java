package atendimento.gestaosenhas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="senha")
public class Senha implements Serializable {

	private static final long serialVersionUID = 7988989705795023613L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "codigo_tipo_senha", referencedColumnName = "codigo")
	protected TipoSenha tipoSenha;
    
	protected Integer numero;

	protected Date timestampEmissao;
	
	protected Date timestampChamada;
	
    // protected Integer numeroGuiche;

    public Senha() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public TipoSenha getTipoSenha() {
        return this.tipoSenha;
    }

    public void setTipoSenha(TipoSenha tipoSenha) {
        this.tipoSenha = tipoSenha;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

	public Date getTimestampEmissao() {
		return timestampEmissao;
	}

	public void setTimestampEmissao(Date timestampEmissao) {
		this.timestampEmissao = timestampEmissao;
	}

	public Date getTimestampChamada() {
		return timestampChamada;
	}

	public void setTimestampChamada(Date timestampChamada) {
		this.timestampChamada = timestampChamada;
	}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Senha)) {
            return false;
        }
        Senha senhaChamada = (Senha) o;
        return Objects.equals(tipoSenha, senhaChamada.tipoSenha) 
        		&& Objects.equals(numero, senhaChamada.numero)
        		&& Objects.equals(timestampEmissao, senhaChamada.timestampEmissao)
        		&& Objects.equals(timestampChamada, senhaChamada.timestampChamada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoSenha, numero, timestampEmissao, timestampChamada);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SenhaChamada[");
        sb.append("tipoSenha='").append(getTipoSenha()).append("'").append(", ");
        sb.append("numero='").append(getNumero()).append("'").append(", ");
        sb.append("timestampEmissao").append(getTimestampEmissao()).append("'").append(", ");
        sb.append("timestampChamada='").append(getTimestampChamada()).append("'");
        sb.append("]");
        return sb.toString();
    }

}