package br.com.totvs.eficienciacombustivel.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.totvs.eficienciacombustivel.model.Marca;
import lombok.Data;

@Data
public class MarcaDto {

	private Long id;
	private String nome;
	
	public MarcaDto(Marca modelo) {
		this.id = modelo.getId();
		this.nome = modelo.getNome();
	}
	
	public static List<MarcaDto> converter(List<Marca> marcas) {
		return marcas.stream().map(MarcaDto::new).collect(Collectors.toList());
	}
}
