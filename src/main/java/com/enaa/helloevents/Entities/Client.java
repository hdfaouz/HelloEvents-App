package com.enaa.helloevents.Entities;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.List;


@Entity
public class Client extends User {


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Reservation> reservations;





}

