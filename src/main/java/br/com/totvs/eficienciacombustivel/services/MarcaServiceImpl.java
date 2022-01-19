package br.com.totvs.eficienciacombustivel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.totvs.eficienciacombustivel.controller.dto.MarcaDto;
import br.com.totvs.eficienciacombustivel.controller.form.MarcaForm;
import br.com.totvs.eficienciacombustivel.model.Marca;
import br.com.totvs.eficienciacombustivel.repository.MarcaRepository;
import br.com.totvs.eficienciacombustivel.services.interfaces.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService{

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public List<MarcaDto> listaMarcas() {
		return MarcaDto.converter(marcaRepository.findAll());
	}

	@Override
	public Optional<MarcaDto> detalhaMarca(Long id) {
		Optional<Marca> marca = marcaRepository.findById(id);
		
		if (!marca.isPresent()) 
			return Optional.empty();
		
		return Optional.of(new MarcaDto(marca.get()));
	}
	
	@Override
	public boolean isNomeUnico(String nome) {
		return !marcaRepository.findByNome(nome).isPresent();
	}

	@Override
	@Transactional
	public MarcaDto insereMarca(MarcaForm marcaForm) {
		Marca marca =  new Marca(marcaForm.getNome());
		return new MarcaDto(marcaRepository.save(marca));
	}

	@Override
	public boolean isNomeIdUnico(String nome, Long id) {
		return !marcaRepository.findByNomeAndIdNot(nome, id).isPresent();
	}

	@Override
	public boolean isIdMarcaValido(Long id) {
		return marcaRepository.existsById(id);
	}
	
	@Override
	@Transactional
	public MarcaDto alteraMarca(Long id, MarcaForm marcaForm) {
		Marca marca = marcaRepository.findById(id).get();
		marcaForm.atualiza(marca);
		return new MarcaDto(marca);
	}

	@Override
	@Transactional
	public void excluiMarca(Long id) {
		marcaRepository.deleteById(id);
	}

}
