package br.com.totvs.eficienciacombustivel.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.totvs.eficienciacombustivel.model.Marca;
import lombok.Data;

@Data
public class MarcaForm {

	@NotNull @NotEmpty @Length(min = 2,max=45)
	private String nome; 
	
	public void atualiza(Marca marca) {
		marca.setNome(nome);
	}
}
