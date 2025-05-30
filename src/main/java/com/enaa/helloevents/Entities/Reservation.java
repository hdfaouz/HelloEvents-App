package com.enaa.helloevents.Entities;

import jakarta.persistence.*;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idreservation;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Evenement evenement;

    public Reservation() {
    }

    public Reservation(Long idreservation, Client client, Evenement evenement) {
        this.idreservation = idreservation;
        this.client = client;
        this.evenement = evenement;
    }

    public void setIdreservation(Long idreservation) {
        this.idreservation = idreservation;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Long getIdreservation() {
        return idreservation;
    }

    public Client getClient() {
        return client;
    }

    public Evenement getEvenement() {
        return evenement;
    }
}


