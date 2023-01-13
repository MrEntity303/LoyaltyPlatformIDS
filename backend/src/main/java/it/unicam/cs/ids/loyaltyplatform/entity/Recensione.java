package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import primaryKeys.PKRecensione;

@Entity
@Table(name = "Recensioni")
@IdClass(PKRecensione.class)
public class Recensione {      //TODO aggiungere foreight keys

    @Id
    private final Integer qualeConsumatore;
    @Id
    private final Integer qualeAzienda;
    @Column(nullable = false)
    private Integer valutazione;
    private String commento;

    public Recensione() {

        this.qualeConsumatore = 0;
        this.qualeAzienda = 0;
    }

    public Recensione(Integer qualeConsumatore, Integer qualeAzienda, Integer valutazione, String commento) {
        this.qualeConsumatore = qualeConsumatore;
        this.qualeAzienda = qualeAzienda;
        this.valutazione = valutazione;
        this.commento = commento;
    }

    public Integer getQualeConsumatore() {
        return qualeConsumatore;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public Integer getValutazione() {
        return valutazione;
    }

    public void setValutazione(Integer valutazione) {
        this.valutazione = valutazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }
}