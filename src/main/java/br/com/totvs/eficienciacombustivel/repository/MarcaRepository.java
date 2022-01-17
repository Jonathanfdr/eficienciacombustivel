package br.com.totvs.eficienciacombustivel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.totvs.eficienciacombustivel.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
	
	Optional<Marca> findByNome(String nome);
	Optional<Marca> findByNomeAndIdNot(String nome, Long id);
	
}
