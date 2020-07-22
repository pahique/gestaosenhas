package atendimento.gestaosenhas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contador_senha")
public class ContadorSenha implements Serializable {
    
	private static final long serialVersionUID = 1949357947956348320L;
	
	@Id
	protected Integer codigoTipoSenha;
	
	protected Integer numeroAtual;

	
    public ContadorSenha() {
    }

    public Integer getCodigoTipoSenha() {
        return this.codigoTipoSenha;
    }

    public void setCodigoTipoSenha(Integer codigoTipoSenha) {
        this.codigoTipoSenha = codigoTipoSenha;
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
        return Objects.equals(codigoTipoSenha, contadorSenha.codigoTipoSenha) && Objects.equals(numeroAtual, contadorSenha.numeroAtual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoTipoSenha, numeroAtual);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ContadorSenha[");
        sb.append("codigoTipoSenha='").append(getCodigoTipoSenha()).append("'").append(", ");
        sb.append("numeroAtual='").append(getNumeroAtual()).append("'");
        sb.append("]");
        return sb.toString();
    }

    
}