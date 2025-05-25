package com.enaa.helloevents.Service;

import com.enaa.helloevents.Dto.EvenementDto;
import com.enaa.helloevents.Entities.Evenement;
import com.enaa.helloevents.Mapper.EvenementMap;
import com.enaa.helloevents.Repositories.EvenementRepositorie;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EvenementService {

    private final EvenementRepositorie eventRepository;
    private EvenementMap eventMapper;

    public EvenementService(EvenementRepositorie eventRepository, EvenementMap evenementMap) {
        this.eventRepository = eventRepository;
        this.eventMapper = evenementMap;
    }



    public EvenementDto AddEvent(EvenementDto eventDto) {
        return eventMapper.eventToDto(eventRepository.save(eventMapper.dtoToEvent(eventDto)));
    }

    public List<EvenementDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(event ->eventMapper.eventToDto(event))
                .toList();
    }

    public EvenementDto getEventById(Long id){
        return eventRepository.findById(id)
                .map(evenement -> eventMapper.eventToDto(evenement))
                .orElseThrow(()->new RuntimeException("event not found"));
    }

   /* public EvenementDto updateEvent(Long id, EvenementDto eventDto) {
        Evenement ev = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evenement non trouvé avec l'id " + id));

        ev.setEventname(eventDto.getEventname());
        ev.setDescription(eventDto.getDescription());


        return eventMapper.eventToDto(eventRepository.save(ev));
    }*/


    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}

