package br.com.zup.store.service;

import static org.springframework.beans.BeanUtils.copyProperties;
import static br.com.zup.store.constants.ConstantsResponses.CLIENT_ALREADY_REGISTERED;
import static br.com.zup.store.constants.ConstantsResponses.CLIENT_NOT_FOUND;
import static br.com.zup.store.constants.ConstantsResponses.CLIENT_SUCCESSFULLY_REGISTERED;
import static br.com.zup.store.constants.ConstantsResponses.CLIENT_SUCCESSFULLY_UPDATED;
import static br.com.zup.store.constants.ConstantsResponses.CLIENT_SUCCESSFULLY_DELETED;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.store.DTO.ClientDto;
import br.com.zup.store.DTO.ResponseDto;
import br.com.zup.store.DTO.UpdateClientDto;
import br.com.zup.store.entity.Client;
import br.com.zup.store.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;


    public ResponseDto signUpClient(ClientDto client) {
        
        boolean existingClient = clientRepository.existsByCpf(client.getCpf());
        if (existingClient) {
            return new ResponseDto(CLIENT_ALREADY_REGISTERED);
        }
        
        Client clientRegistered = new Client();
        copyProperties(client, clientRegistered);
        
        clientRepository.save(clientRegistered);
        
        return new ResponseDto(CLIENT_SUCCESSFULLY_REGISTERED);
    }

    public Client searchClientByCpf(Long cpf) {
        return clientRepository.findByCpf(cpf).orElse(null);
    }

    public List<Client> listClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public ResponseDto updateClient(Long cpf, UpdateClientDto client) {
        
        Optional<Client> searchedClient = clientRepository.findByCpf(cpf);
        
        if (searchedClient.isEmpty()) {
            return new ResponseDto(CLIENT_NOT_FOUND);
        }
        Client updatedClient = searchedClient.get();
        copyProperties(client, updatedClient);
        
        clientRepository.save(updatedClient);
        
        return new ResponseDto(CLIENT_SUCCESSFULLY_UPDATED);
    }

    public ResponseDto deleteClient(Long cpf) {
        
        Optional<Client> searchedClient = clientRepository.findByCpf(cpf);
        
        if (searchedClient.isEmpty()) {
            return new ResponseDto(CLIENT_NOT_FOUND);
        }
        
        Client client = searchedClient.get();
        clientRepository.delete(client);
        
        return new ResponseDto(CLIENT_SUCCESSFULLY_DELETED);
    }

}
