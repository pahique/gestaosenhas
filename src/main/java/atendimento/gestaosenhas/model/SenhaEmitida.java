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
@Table(name="senha_emitida")
public class SenhaEmitida implements Serializable {

	private static final long serialVersionUID = 5113596782245043734L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	protected Date timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "codigo_tipo_senha", referencedColumnName = "codigo")
	protected TipoSenha tipoSenha;
    
	protected Integer numero;

	
    public SenhaEmitida() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SenhaEmitida)) {
            return false;
        }
        SenhaEmitida senhaEmitida = (SenhaEmitida) o;
        return Objects.equals(tipoSenha, senhaEmitida.tipoSenha) && Objects.equals(numero, senhaEmitida.numero)
        		&& Objects.equals(timestamp, senhaEmitida.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoSenha, numero, timestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SenhaEmitida[");
        sb.append("tipoSenha='").append(getTipoSenha()).append("'").append(", ");
        sb.append("numero='").append(getNumero()).append("'").append(", ");
        sb.append("timestamp='").append(getTimestamp()).append("'");
        sb.append("]");
        return sb.toString();
    }

}