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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Modelo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true) @NotNull
	private String nome;	
	@ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) @NotNull
	private Marca marca;
	
	public Modelo(String nome, Marca marca) {
		this.nome = nome;
		this.marca = marca;
	}
	
}
