package com.enaa.helloevents.Service;

import com.enaa.helloevents.Dto.ReservationDto;
import com.enaa.helloevents.Entities.Reservation;
import com.enaa.helloevents.Mapper.ReservationMap;
import com.enaa.helloevents.Repositories.ClientRepositorie;
import com.enaa.helloevents.Repositories.EvenementRepositorie;
import com.enaa.helloevents.Repositories.ReservationRepositorie;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepositorie reservationRepository;
    private final ClientRepositorie clientRepository;
    private final EvenementRepositorie eventRepository;
    private ReservationMap reservationMapper;

    public ReservationService(ReservationRepositorie reservationRepository, ClientRepositorie clientRepository, EvenementRepositorie eventRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.eventRepository = eventRepository;
    }


    @Transactional
    public ReservationDto Resrever(ReservationDto reservatioDto) {
        var client = clientRepository.findById(reservatioDto.getClientid())
                .orElseThrow(() -> new RuntimeException("Client not found with id :" + reservatioDto.getClientid()));
        var event = eventRepository.findById(reservatioDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found with id :" + reservatioDto.getEventId()));

        var reservation = new Reservation();
        reservation.setClient(client);
        reservation.setEvenement(event);

        var savedReservation = reservationRepository.save(reservation);

        return reservationMapper.reservationToDto(savedReservation);


    }




    public List<ReservationDto> getAllReservation() {
        return reservationRepository.findAll().stream()
                .map(res -> reservationMapper.reservationToDto(res))
                .toList();
    }

    public ReservationDto getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(ress -> reservationMapper.reservationToDto(ress))
                .orElseThrow(() -> new RuntimeException("Reservation Not Found"));
    }
}







