package it.prova.pizzastorerest.service.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pizzastorerest.model.Utente;
import it.prova.pizzastorerest.repository.utente.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService{

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public List<Utente> listAll() {
		return (List<Utente>) utenteRepository.findAll();
	}

	@Override
	public Utente caricaSingoloElemento(Long id) {
		return utenteRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);
	}

	@Override
	public void inserisciNuovo(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		utenteRepository.deleteById(idToRemove);
	}
	
}
