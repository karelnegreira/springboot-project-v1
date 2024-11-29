package com.example.proyect1.orders;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    void create(@RequestBody Order order) {
        var saved = this.repository.save(order);
        System.out.println("Saved [" + saved + " ]");
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
