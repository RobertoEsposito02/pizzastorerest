package it.prova.pizzastorerest.service.pizza;

import java.util.List;

import it.prova.pizzastorerest.model.Pizza;

public interface PizzaService{
	public List<Pizza> listAll();

	public Pizza caricaSingoloElemento(Long id);

	public void aggiorna(Pizza pizzaInstance);

	public void inserisciNuovo(Pizza pizzaInstance);

	public void rimuovi(Long idToRemove);
}
