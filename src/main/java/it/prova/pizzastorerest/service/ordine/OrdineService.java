package it.prova.pizzastorerest.service.ordine;

import java.util.List;

import it.prova.pizzastorerest.model.Ordine;

public interface OrdineService{
	public List<Ordine> listAll();

	public Ordine caricaSingoloElemento(Long id);

	public void aggiorna(Ordine ordineInstance);

	public void inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Long idToRemove);
}
