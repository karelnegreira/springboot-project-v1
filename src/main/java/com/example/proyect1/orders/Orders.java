package com.example.proyect1.orders;

import com.example.proyect1.inventory.InventoryUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

public class Orders {
}

@Controller
@ResponseBody
@RequestMapping("/orders")
class OrdersController {
    private final OrderRepository repository;

    private final ApplicationEventPublisher publisher;

    OrdersController(OrderRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping
    Collection<Order> getAll() {
        return repository.findAll();
    }

    @PostMapping
    void create(@RequestBody Order order) {
        var saved = this.repository.save(order);
        System.out.println("Saved [" + saved + " ]");

        saved
                .lineItems()
                .forEach(li -> {
                    publisher.publishEvent(new InventoryUpdatedEvent(
                            li.product(), li.quantity()
                    ));
                });
    }
}
//java 21,
//Object Oriented Programming
//sealed types
//pattern matching
//smart switch
//records
class Loans {
    String DisplayMessageFor (Loan loan) {
        var message = switch (loan) {
            case UnsecuredLoan(var interest) -> "ouch! that " + interest + " is gonna hurt";
            case SecuredLoan s1 -> "Good job, you got a loan";
        };

        return message;
    }
}

sealed interface Loan permits SecuredLoan, UnsecuredLoan {

}

final class SecuredLoan implements Loan {

}

record UnsecuredLoan (float interests) implements Loan {}

interface OrderRepository extends ListCrudRepository<Order, Integer> {}

@Table("orders_line_items")
record LineItem (@Id Integer id, int product, int quantity) {}

@Table("orders")
record Order(@Id Integer id, Set<LineItem> lineItems) {}
