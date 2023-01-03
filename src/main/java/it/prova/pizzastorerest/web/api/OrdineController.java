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

import it.prova.pizzastorerest.dto.ordine.OrdineDTO;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.service.ordine.OrdineService;
import it.prova.pizzastorerest.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastorerest.web.api.exception.IdNullForUpdateException;
import it.prova.pizzastorerest.web.api.exception.OrdineNotFoundException;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {
	
	@Autowired
	private OrdineService ordineService;
	
	public List<OrdineDTO> listAll(){
		return OrdineDTO.createListDTOFromModel(ordineService.listAll());
	}
	
	@GetMapping("/{id}")
	public OrdineDTO findById(@PathVariable(required = true) Long id) {
		Ordine ordineDaCaricare = ordineService.caricaSingoloElemento(id);
		if(ordineDaCaricare == null)
			throw new OrdineNotFoundException("ordine non trovato");
		
		return OrdineDTO.builDTOFromModel(ordineDaCaricare);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdineDTO insert(@Valid @RequestBody OrdineDTO ordine) {
		if(ordine.getId() != null)
			throw new IdNotNullForInsertException("impossibile aggiungere un record se il cmapo id e' stato valorizzato");
		
		ordineService.inserisciNuovo(ordine.buildeModelFromDTO());
		
		return ordine;
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody OrdineDTO ordine) {
		if(ordine.getId() == null)
			throw new IdNullForUpdateException("impossibile aggiornare un record se non si valorizza il campo id prima");
		
		Ordine ordineDaAggiornareOrdine = ordineService.caricaSingoloElemento(ordine.getId());
		if(ordineDaAggiornareOrdine == null)
			throw new OrdineNotFoundException("ordine non trovato");
		
		ordineService.aggiorna(ordine.buildeModelFromDTO());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		if(id == null)
			throw new OrdineNotFoundException("ordine non trovato");
		
		Ordine ordineDaEliminare = ordineService.caricaSingoloElemento(id);
		if(ordineDaEliminare == null)
			throw new OrdineNotFoundException("ordine non trovato");
		
		ordineService.rimuovi(id);
	}
}
