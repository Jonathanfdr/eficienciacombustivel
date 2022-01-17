package br.com.totvs.eficienciacombustivel.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.totvs.eficienciacombustivel.model.Modelo;
import br.com.totvs.eficienciacombustivel.model.Veiculo;
import lombok.Data;

@Data
public class VeiculoForm {
	
	@NotNull @NotEmpty @Length(min = 2,max=45)
	private String nome;
	@NotNull
	private Long idModelo;
	private Integer anoFabricacao;
	@NotNull @Positive
	private Double consumoCidade;
	@NotNull @Positive
	private Double consumoRodovia;
	
	public void atualiza(Veiculo veiculo, Modelo modelo) {
		veiculo.setNome(nome);
		veiculo.setAnoFabricacao(anoFabricacao);
		veiculo.setConsumoCidade(consumoCidade);
		veiculo.setConsumoRodovia(consumoRodovia);
		veiculo.setModelo(modelo);
	}
	
}
