package br.com.sonner.sistema.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity 
public class Nota {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dataCompra;
	@ManyToOne
	private Cliente cliente;
	
	public Nota() {
	}
	
	public Nota(Date dataCompra, Cliente cliente) {
		this.dataCompra = dataCompra;
		this.cliente = cliente;
	}

	public Nota(Nota nota) {
		this.id = nota.getId();
		this.dataCompra = nota.getDataCompra();
		this.cliente = nota.getCliente();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente idCliente) {
		this.cliente = idCliente;
	}
	
	

}
