package it.prova.pizzastorerest.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.repository.cliente.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente caricaSingoloElemento(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);
	}

	@Override
	public void inserisciNuovo(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		clienteRepository.deleteById(idToRemove);
	}

}
