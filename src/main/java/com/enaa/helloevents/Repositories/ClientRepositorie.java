package com.enaa.helloevents.Repositories;
import com.enaa.helloevents.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositorie extends JpaRepository<Client, Long> {

}
