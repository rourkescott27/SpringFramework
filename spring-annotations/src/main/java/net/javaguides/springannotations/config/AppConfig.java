package net.javaguides.springannotations.config;

import net.javaguides.springannotations.controller.PizzaController;
import net.javaguides.springannotations.service.NonVegPizza;
import net.javaguides.springannotations.service.Pizza;
import net.javaguides.springannotations.service.VegPizza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Pizza vegPizza () {
        return new VegPizza();
    }

    @Bean
    public Pizza nonVegPizza () {
        return new NonVegPizza();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PizzaController pizzaController () {
        return new PizzaController(nonVegPizza());
    }
}
