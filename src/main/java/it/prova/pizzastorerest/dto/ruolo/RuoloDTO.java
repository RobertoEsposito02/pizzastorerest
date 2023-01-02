package it.prova.pizzastorerest.dto.ruolo;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorerest.model.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RuoloDTO {
	private Long id;
	private String descrizione;
	private String codice;

	public Ruolo buildModelFromDTO() {
		Ruolo result = Ruolo.builder()
				.id(id)
				.codice(codice)
				.descrizione(descrizione)
				.build();
		
		return result;
	}
	
	public static RuoloDTO buildRuoloDTOFromModel(Ruolo ruoloModel) {
		return new RuoloDTO(ruoloModel.getId(), ruoloModel.getDescrizione(), ruoloModel.getCodice());
	}
	
	public static List<Ruolo> createModelListFromDTO(List<RuoloDTO> listDTORuoli){
		return listDTORuoli.stream().map(ruolo -> {
			return ruolo.buildModelFromDTO();
		}).collect(Collectors.toList());
	}
	
	public static List<RuoloDTO> createRuoloDTOListFromModelList(List<Ruolo> modelListInput) {
		return modelListInput.stream().map(ruoloEntity -> {
			return RuoloDTO.buildRuoloDTOFromModel(ruoloEntity);
		}).collect(Collectors.toList());
	}
}
