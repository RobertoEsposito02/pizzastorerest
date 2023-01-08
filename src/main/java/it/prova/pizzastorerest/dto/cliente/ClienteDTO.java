package it.prova.pizzastorerest.dto.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorerest.model.Cliente;
import it.prova.pizzastorerest.model.Ordine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ClienteDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String inidirizzo;
	private boolean attivo;
	
	@Builder.Default
	private List<Ordine> ordini = new ArrayList<>();
	
	public Cliente buildModelFromDto() {
		Cliente result = Cliente.builder()
				.id(id)
				.nome(nome)
				.cognome(cognome)
				.inidirizzo(inidirizzo)
				.attivo(attivo)
				.build();
		
		if(ordini.size() > 0)
			result.setOrdini(ordini);
		
		return result;
	}
	
	public static ClienteDTO buildDTOFromModel(Cliente clienteModel) {
		ClienteDTO result = ClienteDTO.builder()
				.id(clienteModel.getId())
				.nome(clienteModel.getNome())
				.cognome(clienteModel.getCognome())
				.inidirizzo(clienteModel.getInidirizzo())
				.attivo(clienteModel.isAttivo())
				.build();
		
		if(clienteModel.getOrdini().size() > 0)
			result.setOrdini(clienteModel.getOrdini());
		
		return result;
	}
	
	public static List<ClienteDTO> buildDTOListFromModel(List<Cliente> listaModelClienti) {
		return listaModelClienti.stream().map(cliente -> {
			return ClienteDTO.buildDTOFromModel(cliente);
		}).collect(Collectors.toList());
	}
	
	public static List<Cliente> buildModelListFromDTO(List<ClienteDTO> listaDTOClienti){
		return listaDTOClienti.stream().map(cliente -> {
			return cliente.buildModelFromDto();
		}).collect(Collectors.toList());
	}
}
