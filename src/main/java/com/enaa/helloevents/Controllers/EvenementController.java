package com.enaa.helloevents.Controllers;

import com.enaa.helloevents.Dto.EvenementDto;
import com.enaa.helloevents.Service.EvenementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EvenementController {

    public final EvenementService eventService;

    public EvenementController(EvenementService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EvenementDto addEvent(@RequestBody EvenementDto eventDto) {
        return eventService.AddEvent(eventDto);
    }
    @GetMapping
    public List<EvenementDto> getAllEvents(){
        return eventService.getAllEvents();
    }
    @GetMapping("/{id}")
    public EvenementDto getEventById(@PathVariable Long id ) {
        return  eventService.getEventById(id);
    }
    @PutMapping("/{id}")
    public EvenementDto updatEvent(@PathVariable Long id ,@RequestBody EvenementDto eventDto) {
        return eventService.updateEvent(id,eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id ) {
        eventService.deleteEvent(id);
    }

}
