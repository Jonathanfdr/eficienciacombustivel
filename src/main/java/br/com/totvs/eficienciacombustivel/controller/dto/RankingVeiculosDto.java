package br.com.totvs.eficienciacombustivel.controller.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.totvs.eficienciacombustivel.controller.form.RankingVeiculosForm;
import br.com.totvs.eficienciacombustivel.model.Veiculo;
import lombok.Data;

@Data
public class RankingVeiculosDto {

	private String nome;
	private String marca;
	private String modelo;
	private Integer ano;
	private Double quantidadeTotalCombustivel;
	private Double precoTotalCombustivel;
	
	public RankingVeiculosDto(Veiculo veiculo,RankingVeiculosForm rankingVeiculosForm) {
		this.nome = veiculo.getNome();
		this.marca = veiculo.getModelo().getMarca().getNome();
		this.modelo = veiculo.getModelo().getNome();
		this.ano = veiculo.getAnoFabricacao();
		double litrosCidade = rankingVeiculosForm.getDistanciaPercorridaCidade()/veiculo.getConsumoCidade();
		double litrosRodovia = rankingVeiculosForm.getDistanciaPercorridaRodovia()/veiculo.getConsumoRodovia();
		this.quantidadeTotalCombustivel = litrosCidade+litrosRodovia;
		this.precoTotalCombustivel = this.quantidadeTotalCombustivel * rankingVeiculosForm.getPrecoCombustivel();
		
		formataValores();
	}

	public static List<RankingVeiculosDto> lista(List<Veiculo> veiculos, RankingVeiculosForm rankingVeiculosForm) {
		return veiculos.stream()
			   .map(veiculo-> new RankingVeiculosDto(veiculo, rankingVeiculosForm))
			   .sorted(Comparator.comparingDouble(RankingVeiculosDto::getQuantidadeTotalCombustivel))
			   .collect(Collectors.toList());
	}
	
	private void formataValores() {
		quantidadeTotalCombustivel = (double) (Math.round(quantidadeTotalCombustivel*100.0)/100.0);
		precoTotalCombustivel = (double) (Math.round(precoTotalCombustivel*100.0)/100.0);
	}
	
}
