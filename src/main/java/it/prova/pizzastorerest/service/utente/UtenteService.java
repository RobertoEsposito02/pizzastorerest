package it.prova.pizzastorerest.service.utente;

import java.util.List;

import it.prova.pizzastorerest.model.Utente;

public interface UtenteService {
	public List<Utente> listAll();

	public Utente caricaSingoloElemento(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Long idToRemove);
}
