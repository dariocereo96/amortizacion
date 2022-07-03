package com.cmc.evaluacion.servicios;

import com.cmc.evaluacion.commons.DuplicatedException;
import com.cmc.evaluacion.commons.NotFoundException;
import com.cmc.evaluacion.entidades.Cliente;
import com.cmc.evaluacion.entidades.Prestamo;

public class TestPagos {
	public static void main(String[] args) {
		
		Banco banco = new Banco();
		try {
			banco.registrarCliente(new Cliente("1207340264", "Camilo", "Cuesta"));
			banco.asignarPrestamo("1207340264", new Prestamo("A12345",5000, 12, 12));
			
			AdminPagos pagos = new AdminPagos();
			Prestamo prestamo = banco.buscarPrestamo("A12345");
			
			pagos.pagar(prestamo,4000);
			pagos.pagar(prestamo,300);
			pagos.pagar(prestamo, 142.50);
			pagos.pagar(prestamo,0.14);
			pagos.pagar(prestamo,44);
			pagos.pagar(prestamo, 400);
			pagos.pagar(prestamo, 0.29);
			pagos.pagar(prestamo, 200);
			pagos.pagar(prestamo, 200);
			pagos.pagar(prestamo, 35);
			pagos.pagar(prestamo, 4.5);
			pagos.pagar(prestamo, 4.49);
			pagos.pagar(prestamo, 0.01);
	
		
			
			CalculadoraAmortizacion.mostrarTabla(prestamo);
			
			
		} catch (DuplicatedException e) {
			System.out.println(e);
		} catch (NotFoundException e) {
			System.out.println(e);
		}
	}
}
