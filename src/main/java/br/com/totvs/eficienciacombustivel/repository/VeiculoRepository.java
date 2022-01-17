package br.com.totvs.eficienciacombustivel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.totvs.eficienciacombustivel.model.Veiculo;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{
	
	Optional<Veiculo> findByNome(String nome);
	Optional<Veiculo> findByNomeAndIdNot(String nome, Long id);
	
}