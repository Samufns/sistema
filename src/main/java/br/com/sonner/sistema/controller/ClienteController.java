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

import br.com.sonner.sistema.modelo.Cliente;
import br.com.sonner.sistema.repository.ClienteRepository;

@RestController
@RequestMapping(value="/sistema/cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepository cr;
	
	@GetMapping
	public List<Cliente> listaCliente(){
		return cr.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> listaClienteUnico(@PathVariable Integer id){
		Optional<Cliente> cliente = cr.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new Cliente(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> salvaCliente(@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {
		cr.save(cliente);
		
		URI uri = uriBuilder.path("/sistema/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new Cliente(cliente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Cliente> deletaCliente(@PathVariable Integer id, @RequestBody Cliente cliente ) {
		Optional<Cliente> optionalCliente = cr.findById(id);
		if (optionalCliente.isPresent()) {
			cr.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cliente> AtualizaCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
		Optional<Cliente> optionalCliente = cr.findById(id);
			if (optionalCliente.isPresent()) {
				cr.save(cliente);
				return ResponseEntity.ok(new Cliente(cliente));
			}
			return ResponseEntity.notFound().build();
		} 
	}
