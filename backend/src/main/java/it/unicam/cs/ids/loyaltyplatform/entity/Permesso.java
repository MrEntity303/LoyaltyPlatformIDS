package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Permessi")
public class Permesso
{
    @Id
    private String nomePermesso;//TODO impostare lunghezza a 30

    public Permesso(String nomePermesso) {
        this.nomePermesso = nomePermesso;
    }

    public Permesso() {

    }


    public String getNomePermesso() {
        return nomePermesso;
    }

    public void setNomePermesso(String nomePermesso) {
        this.nomePermesso = nomePermesso;
    }


}
