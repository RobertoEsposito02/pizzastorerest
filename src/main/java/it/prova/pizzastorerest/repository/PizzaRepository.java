package it.prova.pizzastorerest.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>{

}
