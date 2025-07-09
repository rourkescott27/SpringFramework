package net.javaguides.springannotations.controller;


import net.javaguides.springannotations.service.Pizza;
import net.javaguides.springannotations.service.VegPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pizzaDemo")
public class PizzaController {

    private Pizza pizza;

    ///--Constructor Injection--///
    @Autowired
    // Use @Qualifier to specify which Pizza bean to inject when multiple implementations exist
    public PizzaController ( @Qualifier("nonVegPizza") Pizza pizza ) {
        this.pizza = pizza;
    }

    public String getPizza () {
        return pizza.getPizza();
    }
}


/// --Field Injection--///
/// @Autowired private VegPizza vegPizza;

///--Setter Injection--///
/// @Autowired public void setVegPizza ( VegPizza vegPizza ) {
///       this.vegPizza = vegPizza;
///    }