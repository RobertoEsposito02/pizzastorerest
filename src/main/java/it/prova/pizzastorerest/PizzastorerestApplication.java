package it.prova.pizzastorerest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.model.Ruolo;
import it.prova.pizzastorerest.model.Utente;
import it.prova.pizzastorerest.service.cliente.ClienteService;
import it.prova.pizzastorerest.service.ordine.OrdineService;
import it.prova.pizzastorerest.service.pizza.PizzaService;
import it.prova.pizzastorerest.service.ruolo.RuoloService;
import it.prova.pizzastorerest.service.utente.UtenteService;

@SpringBootApplication
public class PizzastorerestApplication implements CommandLineRunner{

	@Autowired
	private RuoloService ruoloService;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private PizzaService pizzaServiceInstance;
	@Autowired
	private OrdineService ordineServiceInstance;
	@Autowired
	private ClienteService clienteServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzastorerestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		ruoloService.inserisciNuovo(Ruolo.builder().codice(Ruolo.ROLE_ADMIN).descrizione("Administrator").build());
		Utente admin = Utente.builder()
				.nome("mario")
				.cognome("rossi")
				.username("admin")
				.password("admin")
				.email("admin@email.com")
				.build();
		admin.getRuoli().add(ruoloService.listAll().get(0));
		utenteServiceInstance.inserisciNuovo(admin);
		
		String[] ingredientiMargherita = {"farina00","pomodoro","fiordilatte","olioextravergine","basilico"};
		pizzaServiceInstance.inserisciNuovo(Pizza.builder()
				.descrizione("margherita")
				.ingredienti(ingredientiMargherita)
				.prezzoBase(5)
				.attivo(true)
				.build());
		
		clienteServiceInstance.inserisciNuovo(Cliente.builder()
				.nome("roberto")
				.cognome("esposito")
				.inidirizzo("indirizzo")
				.attivo(true)
				.build());
		
		ordineServiceInstance.inserisciNuovo(Ordine.builder()
				.data(LocalDate.now())
				.closed(false)
				.codice("codice1")
				.costoTotale(5)
				.cliente(clienteServiceInstance.listAll().get(0))
				.build());   
		
	}

}
