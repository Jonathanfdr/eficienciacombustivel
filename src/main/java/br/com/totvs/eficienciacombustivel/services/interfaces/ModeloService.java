package br.com.totvs.eficienciacombustivel.services.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.totvs.eficienciacombustivel.controller.dto.ModeloDto;
import br.com.totvs.eficienciacombustivel.controller.form.ModeloForm;

public interface ModeloService {

	List<ModeloDto> listaModelos();
	Optional<ModeloDto> detalhaModelo(Long id);
	boolean isNomeUnico(String nome);
	boolean isIdMarcaValido(Long idMarca);
	ModeloDto insereModelo(ModeloForm modeloForm);
	boolean isNomeIdUnico(String nome, Long id);
	boolean isIdModeloValido(Long id);
	ModeloDto alteraModelo(Long id, ModeloForm modeloForm);
	void excluiModelo(Long id);
	
}
