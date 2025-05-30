package com.enaa.helloevents.Mapper;

import com.enaa.helloevents.Dto.ReservationDto;
import com.enaa.helloevents.Entities.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMap {

    ReservationDto reservationToDto(Reservation reservation);
    Reservation DtoToReservation(ReservationDto dto);
}


