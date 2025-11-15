package com.umg.lrperezc.controller;

import com.umg.lrperezc.dto.CreateOrderDTO;
import com.umg.lrperezc.dto.OrderResponseDTO;
import com.umg.lrperezc.exceptions.OrderExceptions.OrderCreationException;
import com.umg.lrperezc.exceptions.OrderExceptions.OrderNotFoundException;
import com.umg.lrperezc.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody CreateOrderDTO dto) {
        OrderResponseDTO created = orderService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> list() {
        return ResponseEntity.ok(orderService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/client/{clientId}/pendientes")
    public ResponseEntity<List<OrderResponseDTO>> getPendingByClient(@PathVariable int clientId) {
        return ResponseEntity.ok(orderService.listPendingByClient(clientId));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleNotFound(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(OrderCreationException.class)
    public ResponseEntity<String> handleCreation(OrderCreationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
