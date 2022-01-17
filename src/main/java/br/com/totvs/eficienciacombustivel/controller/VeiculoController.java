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

import br.com.totvs.eficienciacombustivel.controller.dto.RankingVeiculosDto;
import br.com.totvs.eficienciacombustivel.controller.dto.VeiculoDto;
import br.com.totvs.eficienciacombustivel.controller.form.RankingVeiculosForm;
import br.com.totvs.eficienciacombustivel.controller.form.VeiculoForm;
import br.com.totvs.eficienciacombustivel.services.interfaces.VeiculoService;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public List<VeiculoDto> lista(){
		return veiculoService.listaVeiculos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoDto> detalhar(@PathVariable Long id) {
		Optional<VeiculoDto> veiculoDto = veiculoService.detalhaVeiculo(id);
		
		if (veiculoDto.isPresent())
			return ResponseEntity.ok(veiculoDto.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<VeiculoDto> cadastrar(@RequestBody @Valid VeiculoForm veiculoForm, UriComponentsBuilder uriBuilder) {
		if(!veiculoService.isNomeUnico(veiculoForm.getNome()))
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		if(!veiculoService.isIdModeloValido(veiculoForm.getIdModelo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		VeiculoDto veiculoDto = veiculoService.insereVeiculo(veiculoForm);
		URI uri = uriBuilder.path("/veiculo/{id}").buildAndExpand(veiculoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(veiculoDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VeiculoDto> atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoForm veiculoForm) {
		if(!veiculoService.isIdVeiculoValido(id))
			return ResponseEntity.notFound().build();
		
		if(!veiculoService.isNomeIdUnico(veiculoForm.getNome(),id)) 
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		if(!veiculoService.isIdModeloValido(veiculoForm.getIdModelo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return ResponseEntity.ok(veiculoService.alteraVeiculo(id,veiculoForm));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if(!veiculoService.isIdVeiculoValido(id))
			return ResponseEntity.notFound().build();

		veiculoService.excluiVeiculo(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/rankingEficiencia")
	public List<RankingVeiculosDto> ranking(@RequestBody @Valid RankingVeiculosForm rankingVeiculosForm) {
		
		return veiculoService.listaRankingEficiencia(rankingVeiculosForm);
	}
}
