package br.com.sonner.sistema.controller.form;

import java.math.BigDecimal;

import br.com.sonner.sistema.modelo.Nota;
import br.com.sonner.sistema.modelo.NotaItem;
import br.com.sonner.sistema.modelo.Produto;
import br.com.sonner.sistema.repository.NotaItemRepository;
import br.com.sonner.sistema.repository.NotaRepository;
import br.com.sonner.sistema.repository.ProdutoRepository;

public class NotaItemForm {
		
	private Integer numero;
	private BigDecimal quantidade;
	private Integer idNota;
	private Integer idProduto;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getIdNota() {
		return idNota;
	}
	public void setIdNota(Integer idNota) {
		this.idNota = idNota;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
		
	public NotaItem converter(NotaRepository nr, ProdutoRepository pr) {
		Nota nota = nr.findByid(idNota);
		Produto produto = pr.findByid(idProduto);
		return new NotaItem(numero, quantidade, nota, produto);
	}
	public NotaItem atualizar(Integer id, NotaItemRepository nir,  NotaRepository nr, ProdutoRepository pr) {
		NotaItem notaItem = nir.getById(id);
		Nota nota = nr.findByid(idNota);
		Produto produto = pr.findByid(idProduto);
		notaItem.setNumero(numero);
		notaItem.setQuantidade(quantidade);
		notaItem.setNota(nota);
		notaItem.setProduto(produto);
		nir.save(notaItem);
		return notaItem;
	}
	
}
