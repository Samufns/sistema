package br.com.sonner.sistema.controller.form;

import java.sql.Date;


import br.com.sonner.sistema.modelo.Cliente;
import br.com.sonner.sistema.modelo.Nota;
import br.com.sonner.sistema.repository.ClienteRepository;


public class NotaAtualizaForm {
	
	private Date dataCompra;
	private	Integer idCliente;
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Nota converter(ClienteRepository cr) {
		Cliente cliente = cr.findByid(idCliente);
		return new Nota(dataCompra, cliente);
	}
	

	
}
