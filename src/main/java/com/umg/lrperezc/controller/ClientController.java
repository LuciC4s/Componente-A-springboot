package com.umg.lrperezc.controller;

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
    public ResponseEntity<String> createClient(){
        return ResponseEntity.ok("ok");
    }


}
