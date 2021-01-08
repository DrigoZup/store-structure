package br.com.zup.store.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.store.DTO.ClientDto;
import br.com.zup.store.DTO.ResponseDto;
import br.com.zup.store.DTO.UpdateClientDto;
import br.com.zup.store.entity.Client;
import br.com.zup.store.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;
    
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto signUpClient(@RequestBody ClientDto client) {
        return clientService.signUpClient(client);
    }
    
    @GetMapping(path = "/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Client searchClientByCpf(@PathVariable Long cpf) {
        return clientService.searchClientByCpf(cpf);
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Client> listClients() {
        return clientService.listClients();
    }
    
    @PutMapping(path = "/update/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE}) 
    public ResponseDto updateClient(@PathVariable Long cpf, @RequestBody UpdateClientDto client){
        return clientService.updateClient(cpf, client);
    }
    
    @DeleteMapping(path = "/delete/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto deleteClient(@PathVariable Long cpf) {
        return clientService.deleteClient(cpf);
    }
}
