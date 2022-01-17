package br.com.totvs.eficienciacombustivel.services.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.totvs.eficienciacombustivel.controller.dto.MarcaDto;
import br.com.totvs.eficienciacombustivel.controller.form.MarcaForm;

public interface MarcaService {

	List<MarcaDto> listaMarcas();
	Optional<MarcaDto> detalhaMarca(Long id);
	boolean isNomeUnico(String nome);
	MarcaDto insereMarca(MarcaForm marcaForm);
	boolean isNomeIdUnico(String nome, Long id);
	boolean isIdMarcaValido(Long id);
	MarcaDto alteraMarca(Long id, MarcaForm marcaForm);
	void excluiMarca(Long id);
	
}
