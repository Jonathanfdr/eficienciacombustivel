package br.com.totvs.eficienciacombustivel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.totvs.eficienciacombustivel.model.Modelo;

public interface ModeloRepository  extends JpaRepository<Modelo, Long>{
	
	Optional<Modelo> findByNome(String nome);
	Optional<Modelo> findByNomeAndIdNot(String nome, Long id);
	
}
