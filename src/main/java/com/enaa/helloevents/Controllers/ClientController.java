package com.enaa.helloevents.Controllers;

import com.enaa.helloevents.Dto.ClientDto;
import com.enaa.helloevents.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("client")
public class ClientController {

    public ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getallClients(){
        return  clientService.getallClients();
    }
    @PostMapping
    public ClientDto addClient(@RequestBody ClientDto clientDto){
        return clientService.SaveClient(clientDto);
    }
    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id){
        return clientService.getClientByid(id);
    }
    @PutMapping("/{id}")
    public ClientDto updateClient(@PathVariable Long id , @RequestBody ClientDto clientDto){
        return clientService.modifierClient(id,clientDto);
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
