package processoseletivo.gestaosenhas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="senha_chamada")
public class SenhaChamada implements Serializable {

	private static final long serialVersionUID = 7988989705795023613L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected Date timestampEmitida;
	protected Date timestampChamada;
	protected TipoSenha tipoSenha;
    protected Integer numero;
    // protected Integer numeroGuiche;

    public SenhaChamada() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestampEmitida() {
		return timestampEmitida;
	}

	public void setTimestampEmitida(Date timestampEmitida) {
		this.timestampEmitida = timestampEmitida;
	}

	public Date getTimestampChamada() {
		return timestampChamada;
	}

	public void setTimestampChamada(Date timestampChamada) {
		this.timestampChamada = timestampChamada;
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
        if (!(o instanceof SenhaChamada)) {
            return false;
        }
        SenhaChamada senhaChamada = (SenhaChamada) o;
        return Objects.equals(tipoSenha, senhaChamada.tipoSenha) 
        		&& Objects.equals(numero, senhaChamada.numero)
        		&& Objects.equals(timestampEmitida, senhaChamada.timestampEmitida)
        		&& Objects.equals(timestampChamada, senhaChamada.timestampChamada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoSenha, numero, timestampEmitida, timestampChamada);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SenhaChamada[");
        sb.append("tipoSenha='").append(getTipoSenha()).append("'").append(", ");
        sb.append("numero='").append(getNumero()).append("'").append(", ");
        sb.append("timestampEmitida").append(getTimestampEmitida()).append("'").append(", ");
        sb.append("timestampChamada='").append(getTimestampChamada()).append("'");
        sb.append("]");
        return sb.toString();
    }

}