package it.prova.pizzastorerest.service.cliente;

import java.util.List;

import it.prova.pizzastorerest.model.Cliente;

public interface ClienteService {
	public List<Cliente> listAll();

	public Cliente caricaSingoloElemento(Long id);

	public void aggiorna(Cliente clienteInstance);

	public void inserisciNuovo(Cliente clienteInstance);

	public void rimuovi(Long idToRemove);

	public Cliente caricaSingoloElementoEager(Long id);
	
	public List<Cliente> findByExample(Cliente example);
}
