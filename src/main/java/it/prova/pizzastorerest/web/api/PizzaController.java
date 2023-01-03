package it.prova.pizzastorerest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastorerest.dto.pizza.PizzaDTO;
import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.service.pizza.PizzaService;
import it.prova.pizzastorerest.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastorerest.web.api.exception.IdNullForUpdateException;
import it.prova.pizzastorerest.web.api.exception.PizzaNotFoundException;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	public List<PizzaDTO> listAll(){
		return PizzaDTO.createListaDTOFromModel(pizzaService.listAll());
	}
	
	
	@GetMapping("/{id}")
	public PizzaDTO findById(@PathVariable(required = true) Long id) {
		if(id == null)
			throw new PizzaNotFoundException("pizza non trovato");
		
		Pizza pizzaDaCaricare = pizzaService.caricaSingoloElemento(id);
		if(pizzaDaCaricare == null)
			throw new PizzaNotFoundException("pizza non trovata");
		
		return PizzaDTO.buildDTOFromModel(pizzaDaCaricare);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PizzaDTO insert(@Valid @RequestBody PizzaDTO pizzaDTO) {
		if(pizzaDTO.getId() != null)
			throw new IdNotNullForInsertException("impossibile inserire un elemento che ha il campo id valorizzato");
		
		pizzaService.inserisciNuovo(pizzaDTO.buildModelFromDTO());
		
		return pizzaDTO;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody PizzaDTO pizzaDTO) {
		if(pizzaDTO.getId() == null)
			throw new IdNullForUpdateException("impossibile aggiornare un record se non si valorizza il campo id");
			
		pizzaService.aggiorna(pizzaDTO.buildModelFromDTO());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		if(id == null)
			throw new PizzaNotFoundException("pizza non trovato");
		
		Pizza pizzaDaEliminare = pizzaService.caricaSingoloElemento(id);
		if(pizzaDaEliminare == null)
			throw new PizzaNotFoundException("pizza non trovata");
		
		pizzaService.rimuovi(id);
	}
	
}
