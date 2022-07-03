package com.cmc.evaluacion.servicios;

import com.cmc.evaluacion.commons.TipoAmortizacion;
import com.cmc.evaluacion.entidades.Prestamo;

public class TestCalculadoraAlemana2 {
	public static void main(String[] args) {
		Prestamo prestamo = new Prestamo("A1234",50000, 2, 12);
		CalculadoraAmortizacion.generarTabla(prestamo,TipoAmortizacion.ALEMANA);
		CalculadoraAmortizacion.mostrarTabla(prestamo);
	}
}
