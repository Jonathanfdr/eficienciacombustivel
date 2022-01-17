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

import br.com.totvs.eficienciacombustivel.controller.dto.MarcaDto;
import br.com.totvs.eficienciacombustivel.controller.form.MarcaForm;
import br.com.totvs.eficienciacombustivel.services.interfaces.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	public List<MarcaDto> lista(){
		return marcaService.listaMarcas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MarcaDto> detalhar(@PathVariable Long id) {
		Optional<MarcaDto> marcaDto = marcaService.detalhaMarca(id);
		
		if (marcaDto.isPresent())
			return ResponseEntity.ok(marcaDto.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<MarcaDto> cadastrar(@RequestBody @Valid MarcaForm marcaForm, UriComponentsBuilder uriBuilder) {
		
		if(!marcaService.isNomeUnico(marcaForm.getNome()))
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		MarcaDto marcaDto = marcaService.insereMarca(marcaForm);
		URI uri = uriBuilder.path("/modelo/{id}").buildAndExpand(marcaDto.getId()).toUri();
		return ResponseEntity.created(uri).body(marcaDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MarcaDto> atualizar(@PathVariable Long id, @RequestBody @Valid MarcaForm marcaForm) {
		if(!marcaService.isIdMarcaValido(id))
			return ResponseEntity.notFound().build();
		
		if(!marcaService.isNomeIdUnico(marcaForm.getNome(),id)) 
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		return ResponseEntity.ok(marcaService.alteraMarca(id,marcaForm));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if(!marcaService.isIdMarcaValido(id))
			return ResponseEntity.notFound().build();

		marcaService.excluiMarca(id);
		return ResponseEntity.ok().build();
	}
	
}