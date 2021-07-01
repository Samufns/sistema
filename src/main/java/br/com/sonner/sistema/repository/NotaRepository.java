package br.com.sonner.sistema.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sonner.sistema.modelo.Nota;


public interface NotaRepository extends JpaRepository<Nota, Integer>{

	Nota findByid(Integer idNota);


}
