package br.com.totvs.eficienciacombustivel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.totvs.eficienciacombustivel.controller.dto.ModeloDto;
import br.com.totvs.eficienciacombustivel.controller.form.ModeloForm;
import br.com.totvs.eficienciacombustivel.services.interfaces.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

	@Autowired
	private ModeloService modeloService;
	
	@GetMapping
	public List<ModeloDto> lista(){
		return modeloService.listaModelos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ModeloDto> detalhar(@PathVariable Long id) {
		Optional<ModeloDto> modeloDto = modeloService.detalhaModelo(id);
		
		if (modeloDto.isPresent())
			return ResponseEntity.ok(modeloDto.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ModeloDto> cadastrar(@RequestBody @Valid ModeloForm modeloForm, UriComponentsBuilder uriBuilder) {
		if(!modeloService.isNomeUnico(modeloForm.getNome()))
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		if(!modeloService.isIdMarcaValido(modeloForm.getIdMarca()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		ModeloDto modeloDto = modeloService.insereModelo(modeloForm);
		URI uri = uriBuilder.path("/modelo/{id}").buildAndExpand(modeloDto.getId()).toUri();
		return ResponseEntity.created(uri).body(modeloDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ModeloDto> atualizar(@PathVariable Long id, @RequestBody @Valid ModeloForm modeloForm) {
		if(!modeloService.isIdModeloValido(id))
			return ResponseEntity.notFound().build();
		
		if(!modeloService.isNomeIdUnico(modeloForm.getNome(),id)) 
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		if(!modeloService.isIdMarcaValido(modeloForm.getIdMarca()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return ResponseEntity.ok(modeloService.alteraModelo(id,modeloForm));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if(!modeloService.isIdModeloValido(id))
			return ResponseEntity.notFound().build();

		modeloService.excluiModelo(id);
		return ResponseEntity.ok().build();
	}
	
}