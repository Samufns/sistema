package br.com.sonner.sistema.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class NotaItem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer numero;
	private BigDecimal quantidade;
	@OneToOne
	private Nota nota;
	@OneToOne
	private Produto produto;
	
	public NotaItem() {
	}
	
	public NotaItem(Integer numero, BigDecimal quantidade, Nota nota, Produto produto) {
		this.numero = numero;
		this.quantidade = quantidade;
		this.nota = nota;
		this.produto = produto;
	}

	public NotaItem(NotaItem notaItem) {
		this.id = notaItem.getId();
		this.numero = notaItem.getNumero();
		this.quantidade = notaItem.getQuantidade();
		this.nota = notaItem.getNota();
		this.produto = notaItem.getProduto();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	

}
