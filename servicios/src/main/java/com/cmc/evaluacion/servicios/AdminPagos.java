package com.cmc.evaluacion.servicios;

import com.cmc.evaluacion.commons.Utilitario;
import com.cmc.evaluacion.entidades.Cuota;
import com.cmc.evaluacion.entidades.Prestamo;

public class AdminPagos {
	public void pagar(Prestamo prestamo, double monto) {

		double saldo = monto;

		for (Cuota cuota : prestamo.getCuotas()) {
			if (!cuota.isPagado()) {
				if (saldo >= cuota.getPendiente()) {
					saldo = Utilitario.redondear(saldo - cuota.getPendiente());
					cuota.setPagado(true);
					cuota.setPendiente(0);
				} else if (saldo < cuota.getPendiente() && saldo != 0) {
					cuota.setPendiente(Utilitario.redondear(cuota.getPendiente() - saldo));
					break;
				} else {
					break;
				}
			}
		}

	}
}
