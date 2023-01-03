package it.prova.pizzastorerest.repository.ordine;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorerest.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository{
	
	@Query("from Ordine o left join fetch o.pizze p where o.id = :id")
	Optional<Ordine> findByIdConPizze(Long id);
}
