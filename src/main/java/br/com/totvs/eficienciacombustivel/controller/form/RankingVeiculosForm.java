package br.com.totvs.eficienciacombustivel.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class RankingVeiculosForm {

	@NotNull @Positive
	private Long precoCombustivel;
	@NotNull @Positive
	private Long distanciaPercorridaCidade;
	@NotNull @Positive
	private Long distanciaPercorridaRodovia;
	
}
