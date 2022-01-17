package br.com.totvs.eficienciacombustivel.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.totvs.eficienciacombustivel.controller.dto.RankingVeiculosDto;
import br.com.totvs.eficienciacombustivel.controller.dto.VeiculoDto;
import br.com.totvs.eficienciacombustivel.controller.form.RankingVeiculosForm;
import br.com.totvs.eficienciacombustivel.controller.form.VeiculoForm;
import br.com.totvs.eficienciacombustivel.model.Modelo;
import br.com.totvs.eficienciacombustivel.model.Veiculo;
import br.com.totvs.eficienciacombustivel.repository.ModeloRepository;
import br.com.totvs.eficienciacombustivel.repository.VeiculoRepository;
import br.com.totvs.eficienciacombustivel.services.interfaces.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Override
	public List<VeiculoDto> listaVeiculos() {
		return VeiculoDto.converter(veiculoRepository.findAll());
	}

	@Override
	public Optional<VeiculoDto> detalhaVeiculo(Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		
		if (veiculo.isEmpty()) 
			return Optional.empty();
		
		return Optional.of(new VeiculoDto(veiculo.get()));
	}

	@Override
	public boolean isNomeUnico(String nome) {
		return veiculoRepository.findByNome(nome).isEmpty();
	}
	
	@Override
	public boolean isIdModeloValido(Long idModelo) {
		return modeloRepository.existsById(idModelo);
	}

	@Override
	@Transactional
	public VeiculoDto insereVeiculo(@Valid VeiculoForm veiculoForm) {
		Optional<Modelo> modelo = modeloRepository.findById(veiculoForm.getIdModelo());
		Veiculo veiculo =  new Veiculo(veiculoForm,modelo.get());
		return new VeiculoDto(veiculoRepository.save(veiculo));
	}
	
	@Override
	public boolean isNomeIdUnico(String nome, Long id) {
		return veiculoRepository.findByNomeAndIdNot(nome, id).isEmpty();
	}

	@Override
	public boolean isIdVeiculoValido(Long id) {
		return veiculoRepository.existsById(id);
	}

	@Override
	@Transactional
	public VeiculoDto alteraVeiculo(Long id, VeiculoForm veiculoForm) {
		Veiculo veiculo = veiculoRepository.findById(id).get();
		Modelo modelo = modeloRepository.findById(veiculoForm.getIdModelo()).get();
		veiculoForm.atualiza(veiculo,modelo);
		return new VeiculoDto(veiculo);
	}

	@Override
	@Transactional
	public void excluiVeiculo(Long id) {
		veiculoRepository.deleteById(id);
	}

	@Override
	public List<RankingVeiculosDto> listaRankingEficiencia(RankingVeiculosForm rankingVeiculosForm) {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		return RankingVeiculosDto.lista(veiculos, rankingVeiculosForm);
	}

}
