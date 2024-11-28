package com.example.proyect1.orders;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Set;

public class Orders {
}

interface OrderRepository extends ListCrudRepository<Order, Integer> {}

record LineItem (@Id Integer id, int product, int quantity) {}
record Order(@Id Integer id, Set<LineItem> lineItems) {}
