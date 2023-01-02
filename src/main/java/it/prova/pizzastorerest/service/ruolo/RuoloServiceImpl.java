package it.prova.pizzastorerest.service.ruolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pizzastorerest.model.Ruolo;
import it.prova.pizzastorerest.repository.ruolo.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService{

	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		ruoloRepository.deleteById(idToRemove);;
	}

}
