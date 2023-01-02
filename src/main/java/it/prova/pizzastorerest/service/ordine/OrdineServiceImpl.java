package it.prova.pizzastorerest.service.ordine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.repository.ordine.OrdineRepository;

@Service
public class OrdineServiceImpl implements OrdineService{

	@Autowired
	private OrdineRepository ordineRepository;
	
	@Override
	public List<Ordine> listAll() {
		return (List<Ordine>) ordineRepository.findAll();
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) {
		return ordineRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		ordineRepository.deleteById(idToRemove);
	}

}
