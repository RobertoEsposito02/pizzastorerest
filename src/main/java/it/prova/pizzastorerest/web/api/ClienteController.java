package it.prova.pizzastorerest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastorerest.dto.cliente.ClienteDTO;
import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.service.cliente.ClienteService;
import it.prova.pizzastorerest.web.api.exception.ClienteNotFoundException;
import it.prova.pizzastorerest.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastorerest.web.api.exception.IdNullForUpdateException;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<ClienteDTO> listAll(){
		return ClienteDTO.buildDTOListFromModel(clienteService.listAll());
	}
	
	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable(required = true) Long id) {
		Cliente clienteDaCaricare = clienteService.caricaSingoloElementoEager(id);
		if(clienteDaCaricare == null)
			throw new ClienteNotFoundException("cliente non trovato");
		
		return ClienteDTO.buildDTOFromModel(clienteDaCaricare);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO insert(@Valid @RequestBody ClienteDTO cliente) {
		if(cliente.getId() != null)
			throw new IdNotNullForInsertException("impossibile aggiungere un record se il campo id e' stato valorizzato");
		
		clienteService.inserisciNuovo(cliente.buildModelFromDto());
		
		return cliente;
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody ClienteDTO clienteDTO) {
		if(clienteDTO.getId() == null)
			throw new IdNullForUpdateException("impossibile aggiornare un record se non si valorizza il campo id");
		
		Cliente clienteDaAggiornare = clienteService.caricaSingoloElemento(clienteDTO.getId());
		if(clienteDaAggiornare == null)
			throw new ClienteNotFoundException("cliente non trovato");
		
		clienteService.aggiorna(clienteDTO.buildModelFromDto());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		if(id == null)
			throw new ClienteNotFoundException("cliente non trovato");
		
		Cliente clienteDaEliminare = clienteService.caricaSingoloElemento(id);
		if(clienteDaEliminare == null)
			throw new ClienteNotFoundException("cliente non trovato");
		
		clienteService.rimuovi(id);
	}
	
	@GetMapping("/search")
	public List<ClienteDTO> findByExample(@RequestBody ClienteDTO cliente){
		return ClienteDTO.buildDTOListFromModel(clienteService.findByExample(cliente.buildModelFromDto()));
	}
}
