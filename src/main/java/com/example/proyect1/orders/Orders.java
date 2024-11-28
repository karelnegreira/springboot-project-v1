package com.example.proyect1.orders;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

public class Orders {
}

@Controller
@ResponseBody
class OrdersController {
    private final OrderRepository repository;

    OrdersController(OrderRepository repo) {
        this.repository = repo;
    }
}

interface OrderRepository extends ListCrudRepository<Order, Integer> {}

@Table("orders_line_items")
record LineItem (@Id Integer id, int product, int quantity) {}
record Order(@Id Integer id, Set<LineItem> lineItems) {}
