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

import br.com.sonner.sistema.controller.form.NotaItemForm;
import br.com.sonner.sistema.modelo.NotaItem;
import br.com.sonner.sistema.repository.NotaItemRepository;
import br.com.sonner.sistema.repository.NotaRepository;
import br.com.sonner.sistema.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/sistema/notaItem")
public class NotaItemController {
	
	@Autowired
	NotaItemRepository nir;
	
	@Autowired
	ProdutoRepository pr;
	
	@Autowired
	NotaRepository nr;
	
	@GetMapping
	public List<NotaItem> listaNotas(){
		return nir.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<NotaItem> listaNotaUnica(@PathVariable(value="id") Integer id){
		Optional<NotaItem> notaItem = nir.findById(id);
		if (notaItem.isPresent()) {
			return ResponseEntity.ok(new NotaItem(notaItem.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<NotaItem> salvaNota(@RequestBody NotaItemForm notaItemForm, UriComponentsBuilder uriBuilder ) {
		NotaItem notaItem = notaItemForm.converter(nr,pr);
		nir.save(notaItem);
		
		URI uri = uriBuilder.path("/sistema/nota/{id}").buildAndExpand(notaItem.getId()).toUri();
		return ResponseEntity.created(uri).body(new NotaItem(notaItem));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<NotaItem> deletaNota(@PathVariable Integer id) {
		Optional<NotaItem> optionalNotaItem = nir.findById(id);
		if (optionalNotaItem.isPresent()) {
			nir.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<NotaItem> atualizaNota(@PathVariable Integer id, @RequestBody NotaItemForm notaItemForm) {
		Optional<NotaItem> optionalNota = nir.findById(id);
			if (optionalNota.isPresent()) {
				NotaItem notaItem = notaItemForm.atualizar(id, nir, nr, pr );
				return ResponseEntity.ok(new NotaItem(notaItem));
			}
			return ResponseEntity.notFound().build();
		} 
	}
