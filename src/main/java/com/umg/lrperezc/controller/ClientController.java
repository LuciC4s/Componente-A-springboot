package com.umg.lrperezc.controller;

import com.umg.lrperezc.dto.CreateClientDTO;
import com.umg.lrperezc.model.Client;
import com.umg.lrperezc.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id){
        return ResponseEntity.ok().body(clientService.findClientById(id));
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody CreateClientDTO createClientDTO){
        return ResponseEntity.ok().body(clientService.createClient(createClientDTO));
    }


}
