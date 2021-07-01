package br.com.sonner.sistema.controller.form;

import java.sql.Date;


import br.com.sonner.sistema.modelo.Cliente;
import br.com.sonner.sistema.modelo.Nota;
import br.com.sonner.sistema.repository.ClienteRepository;
import br.com.sonner.sistema.repository.NotaRepository;


public class NotaForm {
	
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
	public Nota atualizar(Integer id, NotaRepository nr, ClienteRepository cr) {
		Nota nota = nr.getById(id);
		Cliente cliente = cr.findByid(idCliente);
		nota.setDataCompra(dataCompra);
		nota.setCliente(cliente);
		nr.save(nota);
		
		return nota;
	}

	
}
