package br.com.totvs.eficienciacombustivel.services.interfaces;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.totvs.eficienciacombustivel.controller.dto.RankingVeiculosDto;
import br.com.totvs.eficienciacombustivel.controller.dto.VeiculoDto;
import br.com.totvs.eficienciacombustivel.controller.form.RankingVeiculosForm;
import br.com.totvs.eficienciacombustivel.controller.form.VeiculoForm;

public interface VeiculoService {

	List<VeiculoDto> listaVeiculos();
	Optional<VeiculoDto> detalhaVeiculo(Long id);
	boolean isNomeUnico(String nome);
	boolean isIdModeloValido(Long idModelo);
	VeiculoDto insereVeiculo(VeiculoForm veiculoForm);
	boolean isNomeIdUnico(String nome, Long id);
	boolean isIdVeiculoValido(Long id);
	VeiculoDto alteraVeiculo(Long id, VeiculoForm veiculoForm);
	void excluiVeiculo(Long id);
	List<RankingVeiculosDto> listaRankingEficiencia(@Valid RankingVeiculosForm rankingVeiculosForm);
	
}
