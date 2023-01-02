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

import it.prova.pizzastorerest.dto.utente.UtenteDTO;
import it.prova.pizzastorerest.model.Utente;
import it.prova.pizzastorerest.service.utente.UtenteService;
import it.prova.pizzastorerest.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastorerest.web.api.exception.IdNullForUpdateException;
import it.prova.pizzastorerest.web.api.exception.UtenteNotFoundException;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	// questa mi serve solo per capire se solo ADMIN vi ha accesso
	@GetMapping("/testSoloAdmin")
	public String test() {
		return "OK";
	}
//
//	@GetMapping(value = "/userInfo")
//	public ResponseEntity<UtenteInfoJWTResponseDTO> getUserInfo() {
//
//		// se sono qui significa che sono autenticato quindi devo estrarre le info dal
//		// contesto
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//		// estraggo le info dal principal
//		Utente utenteLoggato = utenteService.findByUsername(username);
//		List<String> ruoli = utenteLoggato.getRuoli().stream().map(item -> item.getCodice())
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(new UtenteInfoJWTResponseDTO(utenteLoggato.getDateCreated(),utenteLoggato.getNome(), utenteLoggato.getCognome(),
//				utenteLoggato.getUsername(), utenteLoggato.getEmail(), ruoli));
//	}
	
	@GetMapping
	public List<UtenteDTO> listAll(){
		return UtenteDTO.createUtenteListDTOFromModel(utenteService.listAll());
	}
	
	@GetMapping("/{id}")
	public UtenteDTO findById(@PathVariable(required = true) Long id) {
		Utente utenteDaCaricare = utenteService.caricaSingoloElemento(id);
		if(utenteDaCaricare == null)
			throw new UtenteNotFoundException("utente non trovato");
		
		return UtenteDTO.buildUtenteDTOFromModel(utenteDaCaricare);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void inserisci(@Valid @RequestBody UtenteDTO utente) {
		if(utente.getId() != null)
			throw new IdNotNullForInsertException("impossibile aggiungere un record se contenente id");
		
		utenteService.inserisciNuovo(utente.buildUtenteModel(true));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody UtenteDTO utente) {
		if(utente.getId() == null)	
			throw new IdNullForUpdateException("impossibile aggiornare un record se non si inserisce l'id");
		
		utenteService.aggiorna(utente.buildUtenteModel(true));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Utente utenteDaEliminare = utenteService.caricaSingoloElemento(id);
		if(utenteDaEliminare == null)
			throw new UtenteNotFoundException("utente non trovato");
		
		utenteService.rimuovi(id);
	}
	
}