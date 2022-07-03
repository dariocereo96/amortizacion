package com.cmc.evaluacion.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.cmc.evaluacion.commons.DuplicatedException;
import com.cmc.evaluacion.commons.NotFoundException;
import com.cmc.evaluacion.commons.TipoAmortizacion;
import com.cmc.evaluacion.entidades.Cliente;
import com.cmc.evaluacion.entidades.Prestamo;

public class Banco {

	HashMap<String, ArrayList<Prestamo>> prestamos;
	ArrayList<Cliente> clientes;

	public Banco() {
		this.prestamos = new HashMap<String, ArrayList<Prestamo>>();
		this.clientes = new ArrayList<Cliente>();
	}

	public HashMap<String, ArrayList<Prestamo>> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(HashMap<String, ArrayList<Prestamo>> prestamos) {
		this.prestamos = prestamos;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente buscarClientes(String cedula) {
		Cliente cli = null;
		for (Cliente cliente : clientes) {
			if (cliente.getCedula().equals(cedula)) {
				cli = cliente;
			}
		}
		return cli;
	}

	public void registrarCliente(Cliente cliente) throws DuplicatedException {
		if ((buscarClientes(cliente.getCedula())) == null) {
			clientes.add(cliente);
		} else {
			throw new DuplicatedException("El cliente ya existe");
		}
	}

	public void asignarPrestamo(String cedula, Prestamo prestamo) throws NotFoundException {
		Cliente cliente = buscarClientes(cedula);
		if (cliente != null) {
			CalculadoraAmortizacion.generarTabla(prestamo, TipoAmortizacion.FRANCESA);
			ArrayList<Prestamo> pre = buscarPrestamos(cedula);
			if (pre != null) {
				pre.add(prestamo);
			} else {
				pre = new ArrayList<Prestamo>();
				pre.add(prestamo);
				prestamos.put(cedula, pre);
			}

		} else {
			throw new NotFoundException("no es cliente del banco");
		}
	}

	public ArrayList<Prestamo> buscarPrestamos(String cedula) {
		return prestamos.get(cedula);
	}

	public Prestamo buscarPrestamo(String codigo) {
		for (ArrayList<Prestamo> pres : prestamos.values()) {
			for (Prestamo p : pres) {
				if (p.getCodigo().equals(codigo)) {
					return p;
				}
			}
		}

		return null;
	}

}
