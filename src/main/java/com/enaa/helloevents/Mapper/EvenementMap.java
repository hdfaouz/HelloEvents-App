package com.enaa.helloevents.Mapper;

import com.enaa.helloevents.Dto.EvenementDto;
import com.enaa.helloevents.Entities.Evenement;


import java.util.List;


public interface EvenementMap {
    EvenementDto eventToDto(Evenement evenement);
      Evenement dtoToEvent(EvenementDto eventDto);

}


