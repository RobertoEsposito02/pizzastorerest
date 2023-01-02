package it.prova.pizzastorerest.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
