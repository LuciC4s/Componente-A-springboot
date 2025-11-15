package com.umg.lrperezc.service;

import com.umg.lrperezc.dto.CreateClientDTO;
import com.umg.lrperezc.model.Client;
import com.umg.lrperezc.repository.ClientRepository;
import com.umg.lrperezc.exceptions.ClientExceptions.ClientCreationException;
import com.umg.lrperezc.exceptions.ClientExceptions.ClientNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    ClientRepository clientRepository;

    public Client findClientByEmail(String email){
        return  clientRepository.findClientByEmail(email);
    }

    public Client findClientByUsername(String username) {
        return  clientRepository.findClientByUsername(username);
    }

    public Client findClientById(int id){
        Client found = clientRepository.findClientById(id);
        if (found == null) {
            throw new ClientNotFoundException("Client with id " + id + " not found");
        }
        return found;
    }

    public Client createClient(@Valid @RequestBody CreateClientDTO createClientDTO){
        Client existing = clientRepository.findClientByEmail(createClientDTO.email());
        if (existing != null) {
            throw new ClientCreationException("Client with email " + createClientDTO.email() + " already exists");
        }
        try {
            Client client = new Client();
            client.setFirstName(createClientDTO.firstName());
            client.setLastName(createClientDTO.lastName());
            client.setEmail(createClientDTO.email());
            client.setUsername(createClientDTO.username());
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new ClientCreationException("Client could not be created", e);
        }
    }

    public List<Client> getAllClients(){
        List<Client> allClients = clientRepository.findAll();
        return allClients;
    }
}
