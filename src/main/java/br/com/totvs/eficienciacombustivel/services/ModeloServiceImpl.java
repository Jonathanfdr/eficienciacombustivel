package br.com.totvs.eficienciacombustivel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.totvs.eficienciacombustivel.controller.dto.ModeloDto;
import br.com.totvs.eficienciacombustivel.controller.form.ModeloForm;
import br.com.totvs.eficienciacombustivel.model.Marca;
import br.com.totvs.eficienciacombustivel.model.Modelo;
import br.com.totvs.eficienciacombustivel.repository.MarcaRepository;
import br.com.totvs.eficienciacombustivel.repository.ModeloRepository;
import br.com.totvs.eficienciacombustivel.services.interfaces.ModeloService;

@Service
public class ModeloServiceImpl implements ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public List<ModeloDto> listaModelos() {
		return ModeloDto.converter(modeloRepository.findAll());
	}

	@Override
	public Optional<ModeloDto> detalhaModelo(Long id) {
		Optional<Modelo> modelo = modeloRepository.findById(id);
		
		if (modelo.isEmpty()) 
			return Optional.empty();
		
		return Optional.of(new ModeloDto(modelo.get()));
	}
	
	@Override
	public boolean isNomeUnico(String nome) {
		return modeloRepository.findByNome(nome).isEmpty();
	}

	@Override
	public boolean isIdMarcaValido(Long idMarca) {
		return marcaRepository.existsById(idMarca);
	}

	@Override
	@Transactional
	public ModeloDto insereModelo(ModeloForm modeloForm) {
		Optional<Marca> marca = marcaRepository.findById(modeloForm.getIdMarca());
		Modelo modelo =  new Modelo(modeloForm.getNome(),marca.get());
		return new ModeloDto(modeloRepository.save(modelo));
	}

	@Override
	public boolean isNomeIdUnico(String nome, Long id) {
		return modeloRepository.findByNomeAndIdNot(nome, id).isEmpty();
	}

	@Override
	public boolean isIdModeloValido(Long id) {
		return modeloRepository.existsById(id);
	}

	@Override
	@Transactional
	public ModeloDto alteraModelo(Long id, ModeloForm modeloForm) {
		Modelo modelo = modeloRepository.findById(id).get();
		Marca marca = marcaRepository.findById(modeloForm.getIdMarca()).get();
		modeloForm.atualiza(modelo,marca);
		return new ModeloDto(modelo);
	}

	@Override
	@Transactional
	public void excluiModelo(Long id) {
		modeloRepository.deleteById(id);
	}

}
