package com.enaa.helloevents.Mapper;

import com.enaa.helloevents.Dto.ClientDto;
import com.enaa.helloevents.Entities.Client;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ClientMap {
    ClientDto toClientDto(Client client);
    Client toCliententity(ClientDto clientDto);
}
