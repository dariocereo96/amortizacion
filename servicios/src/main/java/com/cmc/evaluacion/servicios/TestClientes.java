package com.cmc.evaluacion.servicios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cmc.evaluacion.commons.DuplicatedException;
import com.cmc.evaluacion.entidades.Cliente;

public class TestClientes {
	@Test
	public void testPrimerCliente() {
		Banco b=new Banco();
		try {
			b.registrarCliente(new Cliente("1714616123", "Camilo", "Cuesta"));
		} catch (DuplicatedException e) {
			e.printStackTrace();
		}
		assertEquals(1, b.getClientes().size());
	}
	
	@Test
	public void testClienteDuplicado() {
		Banco b=new Banco();
		try {
			b.registrarCliente(new Cliente("1714616123", "Camilo", "Cuesta"));
		} catch (DuplicatedException e) {
			e.printStackTrace();
			assertEquals("el cliente ya existe", e.getMessage());
		}
		assertEquals(1, b.getClientes().size());
	}
	
	@Test
	public void testVariosClientes() {
		Banco b=new Banco();
		try {
			b.registrarCliente(new Cliente("1714616123", "Camilo", "Cuesta"));
			b.registrarCliente(new Cliente("0514616123", "Ramiro", "Cuesta"));
			b.registrarCliente(new Cliente("1714616123", "Vero", "Cuesta"));
		} catch (DuplicatedException e) {
			e.printStackTrace();
			assertEquals("El cliente ya existe", e.getMessage());
		}
		assertEquals(2, b.getClientes().size());
	}
	
}
