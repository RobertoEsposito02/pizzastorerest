package it.prova.pizzastorerest.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.repository.pizza.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService{

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	public List<Pizza> listAll() {
		return (List<Pizza>) pizzaRepository.findAll();
	}

	@Override
	public Pizza caricaSingoloElemento(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
	}

	@Override
	public void inserisciNuovo(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		pizzaRepository.deleteById(idToRemove);
	}

	@Override
	public List<Pizza> findByExample(Pizza example) {
		return pizzaRepository.findByExample(example);
	}
	
}
