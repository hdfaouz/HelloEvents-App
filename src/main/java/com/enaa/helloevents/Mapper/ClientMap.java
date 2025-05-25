package com.enaa.helloevents.Mapper;

import com.enaa.helloevents.Dto.ClientDto;
import com.enaa.helloevents.Entities.Client;


import java.util.List;



public interface ClientMap {
    ClientDto toClientDto(Client client);
    Client toCliententity(ClientDto clientDto);
}


