package br.com.sonner.sistema.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sonner.sistema.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Cliente findByid(Integer idCliente);


}
