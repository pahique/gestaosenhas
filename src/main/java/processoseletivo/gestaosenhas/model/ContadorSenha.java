package processoseletivo.gestaosenhas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="contador_senha")
public class ContadorSenha implements Serializable {
    
	private static final long serialVersionUID = 1949357947956348320L;
	
	protected TipoSenha tipoSenha;
	protected Integer numeroAtual;

    public ContadorSenha() {
    }

    public TipoSenha getTipoSenha() {
        return this.tipoSenha;
    }

    public void setTipoSenha(TipoSenha tipoSenha) {
        this.tipoSenha = tipoSenha;
    }

    public Integer getNumeroAtual() {
        return this.numeroAtual;
    }

    public void setNumeroAtual(Integer numeroAtual) {
        this.numeroAtual = numeroAtual;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ContadorSenha)) {
            return false;
        }
        ContadorSenha contadorSenha = (ContadorSenha) o;
        return Objects.equals(tipoSenha, contadorSenha.tipoSenha) && Objects.equals(numeroAtual, contadorSenha.numeroAtual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoSenha, numeroAtual);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ContadorSenha[");
        sb.append("tipoSenha='").append(getTipoSenha()).append("'").append(", ");
        sb.append("numeroAtual='").append(getNumeroAtual()).append("'");
        sb.append("]");
        return sb.toString();
    }

    
}