package br.com.sonner.sistema.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.sonner.sistema.modelo.Produto;
import br.com.sonner.sistema.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/sistema/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository pr;
	
	@GetMapping
	public List<Produto> listaProduto(){
		return pr.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Produto> listaProdutoUnico(@PathVariable(value="id") Integer id){
		Optional<Produto> produto = pr.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(new Produto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
		pr.save(produto);
		
		URI uri = uriBuilder.path("/sistema/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new Produto(produto));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Produto> deletaProduto(@PathVariable Integer id) {
		Optional<Produto> optionalProduto = pr.findById(id);
		if (optionalProduto.isPresent()) {
			pr.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Produto> AtualizaProduto(@PathVariable Integer id,@RequestBody Produto produto) {
		Optional<Produto> optionalCliente = pr.findById(id);
			if (optionalCliente.isPresent()) {
				pr.save(produto);
				return ResponseEntity.ok(new Produto(produto));
			}
			return ResponseEntity.notFound().build();
		} 
	}
