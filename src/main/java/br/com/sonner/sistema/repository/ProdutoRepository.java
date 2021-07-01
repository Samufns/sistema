package br.com.sonner.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sonner.sistema.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	Produto findByid(Integer idProduto);

}
