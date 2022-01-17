package br.com.totvs.eficienciacombustivel.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.totvs.eficienciacombustivel.model.Veiculo;
import lombok.Data;

@Data
public class VeiculoDto {

	private Long id;
	private String nome;
	private String modelo;
	private String marca;
	private Integer anoFabricacao;
	private Double consumoCidade;
	private Double consumoRodovia;
	
	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.nome = veiculo.getNome();
		this.modelo = veiculo.getModelo().getNome();
		this.marca = veiculo.getModelo().getMarca().getNome();
		this.anoFabricacao = veiculo.getAnoFabricacao();
		this.consumoCidade = veiculo.getConsumoCidade();
		this.consumoRodovia = veiculo.getConsumoRodovia();
	}
	
	public static List<VeiculoDto> converter(List<Veiculo> veiculos) {
		return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}
	
}
