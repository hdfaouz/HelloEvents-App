package com.enaa.helloevents.Service;

import com.enaa.helloevents.Dto.EvenementDto;
import com.enaa.helloevents.Entities.Evenement;
import com.enaa.helloevents.Mapper.EvenementMap;
import com.enaa.helloevents.Repositories.EvenementRepositorie;
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

    public EvenementService(EvenementRepositorie eventRepository) {
        this.eventRepository = eventRepository;
    }



}
