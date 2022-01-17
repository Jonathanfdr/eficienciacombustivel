package br.com.totvs.eficienciacombustivel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import br.com.totvs.eficienciacombustivel.controller.form.VeiculoForm;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Veiculo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull @Column(unique=true)
	private String nome;
	@ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) @NotNull
	private Modelo modelo;
	@Nullable
	private Integer anoFabricacao;
	@NotNull
	private Double consumoCidade;
	@NotNull
	private Double consumoRodovia;
	
	public Veiculo(VeiculoForm veiculoForm, Modelo modelo) {
		this.nome = veiculoForm.getNome();
		this.anoFabricacao = veiculoForm.getAnoFabricacao();
		this.consumoCidade = veiculoForm.getConsumoCidade();
		this.consumoRodovia = veiculoForm.getConsumoRodovia();
		this.modelo = modelo;
	}
	
}
