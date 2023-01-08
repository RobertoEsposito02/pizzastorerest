package it.prova.pizzastorerest.service.utente;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.prova.pizzastorerest.model.StatoUtente;
import it.prova.pizzastorerest.model.Utente;
import it.prova.pizzastorerest.repository.utente.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService{

	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		utenteInstance.setDateCreated(new Date());
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteRepository.save(utenteInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		utenteRepository.deleteById(idToRemove);
	}

	@Override
	public Utente findByUsername(String username) {
		return utenteRepository.findByUsername(username).orElse(null);
	}

	@Override
	public void changeUserAbilitation(Long utenteInstanceId) {
		Utente utenteInstance = caricaSingoloElemento(utenteInstanceId);
		if (utenteInstance == null)
			throw new RuntimeException("Elemento non trovato.");

		if (utenteInstance.getStato() == null || utenteInstance.getStato().equals(StatoUtente.CREATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
		else if (utenteInstance.getStato().equals(StatoUtente.ATTIVO))
			utenteInstance.setStato(StatoUtente.DISABILITATO);
		else if (utenteInstance.getStato().equals(StatoUtente.DISABILITATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
	}
	
}
