package com.umg.lrperezc.service;

import com.umg.lrperezc.dto.CreateOrderDTO;
import com.umg.lrperezc.dto.ItemDTO;
import com.umg.lrperezc.dto.OrderResponseDTO;
import com.umg.lrperezc.exceptions.OrderExceptions.OrderCreationException;
import com.umg.lrperezc.exceptions.OrderExceptions.OrderNotFoundException;
import com.umg.lrperezc.model.Client;
import com.umg.lrperezc.model.Order;
import com.umg.lrperezc.model.OrderItem;
import com.umg.lrperezc.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    public OrderResponseDTO create(@Valid CreateOrderDTO dto) {
        try {
            String normalizedStatus = normalizeStatus(dto.status());
            if (!isValidStatus(normalizedStatus)) {
                throw new OrderCreationException("Estado inválido, use 'pagado' o 'pendiente'");
            }
            if (dto.items() == null || dto.items().isEmpty()) {
                throw new OrderCreationException("Debe proporcionar al menos un item");
            }

            Client client = clientService.findClientById(dto.clientId());

            Order order = new Order();
            order.setClient(client);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(normalizedStatus);
            order.setNotes(dto.notes());

            List<OrderItem> items = dto.items().stream()
                    .map(i -> new OrderItem(i.title(), i.price()))
                    .collect(Collectors.toList());
            order.setItems(items);

            BigDecimal total = items.stream()
                    .map(OrderItem::getPrice)
                    .filter(p -> p != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(total);

            Order saved = orderRepository.save(order);
            return toResponse(saved, true);
        } catch (OrderCreationException e) {
            throw e;
        } catch (Exception e) {
            throw new OrderCreationException("No se pudo crear el pedido", e);
        }
    }

    public List<OrderResponseDTO> listAll() {
        return orderRepository.findAll().stream()
                .map(o -> toResponse(o, false))
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido con id " + id + " no encontrado"));
        return toResponse(order, true);
    }

    private boolean isValidStatus(String status) {
        if (status == null) return false;
        return status.equals("pagado") || status.equals("pendiente");
    }

    private String normalizeStatus(String status) {
        return status == null ? null : status.toLowerCase(Locale.ROOT).trim();
    }

    private OrderResponseDTO toResponse(Order order, boolean recomputeTotal) {
        BigDecimal total = recomputeTotal ? order.getItems().stream()
                .map(OrderItem::getPrice)
                .filter(p -> p != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add) : order.getTotalAmount();

        List<ItemDTO> itemDTOs = order.getItems().stream()
                .map(i -> new ItemDTO(i.getTitle(), i.getPrice()))
                .collect(Collectors.toList());

        return new OrderResponseDTO(
                order.getId(),
                order.getClient().getId(),
                order.getStatus(),
                order.getOrderDate(),
                order.getNotes(),
                itemDTOs,
                total
        );
    }
}
