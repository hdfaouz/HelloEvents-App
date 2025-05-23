package com.enaa.helloevents.Repositories;

import com.enaa.helloevents.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepositorie extends JpaRepository<Reservation,Long> {
}
