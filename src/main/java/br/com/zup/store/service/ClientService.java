package br.com.zup.store.service;

import java.util.List;
import br.com.zup.store.DTO.ClientDto;
import br.com.zup.store.DTO.ResponseDto;
import br.com.zup.store.DTO.UpdateClientDto;
import br.com.zup.store.entity.Client;

public interface ClientService {

    public ResponseDto signUpClient(ClientDto client);
    
    public Client searchClientByCpf(Long cpf);
    
    public List<Client> listClients();
    
    public ResponseDto updateClient(Long cpf, UpdateClientDto client);
    
    public ResponseDto deleteClient(Long cpf);
}
