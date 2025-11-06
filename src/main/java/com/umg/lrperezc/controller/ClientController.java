package com.umg.lrperezc.controller;

import com.umg.lrperezc.dto.CreateClientDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {


    @GetMapping("{id}")
    public ResponseEntity<String> getClient(@PathVariable String id){
        return ResponseEntity.ok().body(String.format("Client with id '%s'",id));
    }

    @PostMapping
    public ResponseEntity<String> createClient(@Valid @RequestBody CreateClientDTO createClientDTO){
        return ResponseEntity.ok("ok");
    }


}
