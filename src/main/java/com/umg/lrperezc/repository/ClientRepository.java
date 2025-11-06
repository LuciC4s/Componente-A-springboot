package com.umg.lrperezc.repository;

import com.umg.lrperezc.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findClientByEmail(String email);
    Client findClientByUsername(String username);

    Client findClientById(int id);
}
