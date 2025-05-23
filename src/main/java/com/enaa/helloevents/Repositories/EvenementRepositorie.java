package com.enaa.helloevents.Repositories;

import com.enaa.helloevents.Entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepositorie extends JpaRepository<Evenement,Long> {
}
