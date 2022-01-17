package br.com.totvs.eficienciacombustivel.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.totvs.eficienciacombustivel.model.Marca;
import br.com.totvs.eficienciacombustivel.model.Modelo;
import lombok.Data;

@Data
public class ModeloForm {

	@NotNull @NotEmpty @Length(min = 2,max=45)
	private String nome;
	@NotNull
	private Long idMarca;
	
	public void atualiza(Modelo modelo, Marca marca) {
		modelo.setNome(nome);
		modelo.setMarca(marca);		
	}

}
