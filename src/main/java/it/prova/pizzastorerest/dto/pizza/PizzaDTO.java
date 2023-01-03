package it.prova.pizzastorerest.dto.pizza;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorerest.model.Pizza;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PizzaDTO {
	private Long id;
	private String descrizione;
	private String[] ingredienti;
	private Integer prezzoBase;
	private boolean attivo;
	
	public Pizza buildModelFromDTO() {
		Pizza result = Pizza.builder()
				.id(id)
				.descrizione(descrizione)
				.ingredienti(ingredienti)
				.prezzoBase(prezzoBase)
				.attivo(attivo)
				.build();
		
		return result;
	}
	
	public static PizzaDTO buildDTOFromModel(Pizza pizzaModel) {
		PizzaDTO result = PizzaDTO.builder()
				.id(pizzaModel.getId())
				.descrizione(pizzaModel.getDescrizione())
				.ingredienti(pizzaModel.getIngredienti())
				.prezzoBase(pizzaModel.getPrezzoBase())
				.attivo(pizzaModel.isAttivo())
				.build();
	
		return result;
	}
	
	public static List<Pizza> createListaModelFromDTO(List<PizzaDTO> listaDTO){
		return listaDTO.stream().map(pizza -> {
			return pizza.buildModelFromDTO();
		}).collect(Collectors.toList());
	}
	
	public static List<PizzaDTO> createListaDTOFromModel(List<Pizza> listaModel){
		return listaModel.stream().map(pizza -> {
			return PizzaDTO.buildDTOFromModel(pizza);
		}).collect(Collectors.toList());
	}
}
