 package it.prova.pizzastorerest.dto.ordine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;
import it.prova.pizzastorerest.model.Pizza;
import it.prova.pizzastorerest.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrdineDTO {
	private Long id;
	private LocalDate data;
	private boolean closed;
	private String codice;
	private Integer costoTotale;
	
	private Cliente cliente;
	
	@Builder.Default
	private List<Pizza> pizze = new ArrayList<>();
	
	private Utente fattorino;
	
	public Ordine buildeModelFromDTO() {
		Ordine result = Ordine.builder()
				.id(id)
				.data(data)
				.closed(closed)
				.codice(codice)
				.costoTotale(costoTotale)
				.build();
		
		if(cliente != null)
			result.setCliente(cliente);
		if(pizze.size() > 0)
			result.setPizze(pizze);
		if(fattorino != null)
			result.setFattorino(fattorino);
		
		return result;
	}
	
	public static OrdineDTO builDTOFromModel(Ordine ordineModel) {
		OrdineDTO result = OrdineDTO.builder()
				.id(ordineModel.getId())
				.data(ordineModel.getData())
				.codice(ordineModel.getCodice())
				.closed(ordineModel.isClosed())
				.costoTotale(ordineModel.getCostoTotale())
				.build();
		
		if(ordineModel.getCliente() != null)
			result.setCliente(ordineModel.getCliente());
		if(ordineModel.getPizze().size() > 0)
			result.setPizze(ordineModel.getPizze());
		if(ordineModel.getFattorino() != null)
			result.setFattorino(ordineModel.getFattorino());
		
		return result;
	}
	
	public static List<Ordine> createListModelFromDTO(List<OrdineDTO> listDtoOrdine){
		return listDtoOrdine.stream().map(ordine -> {
			return ordine.buildeModelFromDTO();
		}).collect(Collectors.toList());
	}
	
	public static List<OrdineDTO> createListDTOFromModel(List<Ordine> listModel){
		return listModel.stream().map(ordine -> {
			return OrdineDTO.builDTOFromModel(ordine);
		}).collect(Collectors.toList());
	}
}
