package br.com.totvs.eficienciacombustivel.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.totvs.eficienciacombustivel.model.Modelo;
import lombok.Data;

@Data
public class ModeloDto {

	private Long id;
	private String nome;
	private String marca;
	
	public ModeloDto(Modelo modelo) {
		this.id = modelo.getId();
		this.nome = modelo.getNome();
		this.marca = modelo.getMarca().getNome();
	}
	
	public static List<ModeloDto> converter(List<Modelo> modelos) {
		return modelos.stream().map(ModeloDto::new).collect(Collectors.toList());
	}
	
}
