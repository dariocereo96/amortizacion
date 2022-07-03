package com.cmc.evaluacion.servicios;

import com.cmc.evaluacion.commons.Utilitario;
import com.cmc.evaluacion.entidades.Cuota;

public class TestImpresion {
	public static void main(String[] args) {
		Cuota cuota = new Cuota(1);
		cuota.setCuota(444.24);
		cuota.setCapitalInicio(5000);
		cuota.setInteres(50);
		cuota.setAbonoCapital(Utilitario.redondear(394.24134));
		cuota.setSaldo(Utilitario.redondear(4605.76143));
		System.out.println(cuota);
	}
}
