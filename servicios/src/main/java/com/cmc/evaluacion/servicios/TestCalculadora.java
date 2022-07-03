package com.cmc.evaluacion.servicios;

import com.cmc.evaluacion.commons.TipoAmortizacion;
import com.cmc.evaluacion.entidades.Prestamo;

public class TestCalculadora {
	public static void main(String[] args) {
		Prestamo prestamo = new Prestamo("A123",5000, 12, 12);
		CalculadoraAmortizacion.generarTabla(prestamo,TipoAmortizacion.FRANCESA);
		CalculadoraAmortizacion.mostrarTabla(prestamo);
	}
}
