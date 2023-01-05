package it.prova.pizzastorerest.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, CustomClienteRepository{
	
	@Query("from Cliente c left join fetch c.ordini o where c.id = :id")
	Optional<Cliente> findByIdEager(Long id);
	
	@Query("from Cliente c where c.attivo = 1")
	List<Cliente> listAll();
}
