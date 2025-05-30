package com.enaa.helloevents.Controllers;


import com.enaa.helloevents.Dto.ReservationDto;
import com.enaa.helloevents.Service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
    public ReservationService resrvationService;


    @PostMapping
    public ReservationDto addReservation(@RequestBody  ReservationDto reservatioDto){
        return resrvationService.Resrever(reservatioDto);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations(){
        return resrvationService.getAllReservation();
    }

    @GetMapping("/{id}")
    public ReservationDto getreservationById(@PathVariable  Long id ){
        return resrvationService.getReservationById(id);
    }



}
