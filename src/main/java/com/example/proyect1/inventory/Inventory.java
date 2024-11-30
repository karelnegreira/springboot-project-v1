package com.example.proyect1.inventory;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
class Inventory {
    @EventListener
    void on (InventoryUpdatedEvent event) {
        System.out.println("The inventory has been updated");
    }
}
