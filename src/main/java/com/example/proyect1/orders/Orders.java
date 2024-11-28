package com.example.proyect1.orders;

import java.util.Set;

public class Orders {
}

record LineItem (Integer id, int product, int quantity) {}
record Order(Integer id, Set<LineItem> lineItems) {}
