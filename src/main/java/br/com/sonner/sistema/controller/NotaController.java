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

import br.com.sonner.sistema.controller.form.NotaForm;
import br.com.sonner.sistema.modelo.Nota;
import br.com.sonner.sistema.repository.ClienteRepository;
import br.com.sonner.sistema.repository.NotaRepository;

@RestController
@RequestMapping(value="/sistema/nota")
public class NotaController {
	
	@Autowired
	NotaRepository nr;
	@Autowired
	ClienteRepository cr;
	
	@GetMapping
	public List<Nota> listaTodasNotas(){
		return nr.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Nota> listaNotaUnica(@PathVariable(value="id") Integer id){
		Optional<Nota> nota = nr.findById(id);
		if (nota.isPresent()) {
			return ResponseEntity.ok(new Nota(nota.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Nota> salvaNota(@RequestBody NotaForm notaForm, UriComponentsBuilder uriBuilder ) {
		Nota nota = notaForm.converter(cr);
		nr.save(nota);
		URI uri = uriBuilder.path("/sistema/nota/{id}").buildAndExpand(nota.getId()).toUri();
		return ResponseEntity.created(uri).body(new Nota(nota));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Nota> deletaNota(@PathVariable Integer id ) {
		Optional<Nota> optionalNota = nr.findById(id);
		if (optionalNota.isPresent()) {
			nr.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Nota> atualizaNota(@PathVariable Integer id, @RequestBody NotaForm notaForm) {
		Optional<Nota> optionalNota = nr.findById(id);
			if (optionalNota.isPresent()) {
				Nota nota = notaForm.atualizar(id, nr, cr);
				return ResponseEntity.ok(new Nota(nota));
			}
			return ResponseEntity.notFound().build();
		} 
	}
