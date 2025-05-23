package com.enaa.helloevents.Service;

import com.enaa.helloevents.Dto.ClientDto;
import com.enaa.helloevents.Entities.Client;
import com.enaa.helloevents.Mapper.ClientMap;
import com.enaa.helloevents.Repositories.ClientRepositorie;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


import java.util.List;


@Service

public class ClientService {

    private final ClientRepositorie clientRepository;
    private final ClientMap clientMap;

    public ClientService(ClientRepositorie clientRepository , ClientMap clientMap) {
        this.clientRepository = clientRepository;
        this.clientMap = clientMap;
    }

    public ClientDto SaveClient(ClientDto clientDto){
        return clientMap.toClientDto(clientRepository.save(clientMap.toCliententity(clientDto)));
    }

    public List<ClientDto> getallClients(){
        return clientRepository.findAll().stream()
                .map(client ->clientMap.toClientDto(client)).toList();
    }

    public ClientDto getClientByid(Long id){
        return clientRepository.findById(id)
                .map(client -> clientMap.toClientDto(client))
                .orElseThrow(()-> new RuntimeException("Client not found"));
    }

    public ClientDto modifierClient(Long id ,ClientDto clientDto){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());

        return clientMap.toClientDto(clientRepository.save(client));



    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }





}
