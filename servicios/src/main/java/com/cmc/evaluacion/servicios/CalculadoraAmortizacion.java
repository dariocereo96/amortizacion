package com.cmc.evaluacion.servicios;

import com.cmc.evaluacion.commons.Utilitario;
import com.cmc.evaluacion.entidades.Cuota;
import com.cmc.evaluacion.entidades.Prestamo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculadoraAmortizacion {

	private static Logger logger = LogManager.getLogger(CalculadoraAmortizacion.class);

	public static double calcularCuota(Prestamo prestamo) {
		// calculo interes mensual
		double interesMensual = (prestamo.getInteres() / 12) / 100;

		// calculo valor cuota
		double valorCuota = (prestamo.getMonto() * interesMensual)
				/ (1 - Math.pow(1 + interesMensual, -prestamo.getPlazo()));
		return valorCuota;

	}

	public static void generarTabla(Prestamo prestamo, String tipoAmortizacion) {
		
		double cuotaFija = calcularCuota(prestamo);
		double abonoCapital = prestamo.getMonto()/prestamo.getPlazo();
				
		// Crear cuotas del prestamo
		for (int i = 0; i < prestamo.getCuotas().length; i++) {
			prestamo.getCuotas()[i] = new Cuota(i + 1);
			if(tipoAmortizacion.equals("FR")){
				prestamo.getCuotas()[i].setCuota(Utilitario.redondear(cuotaFija));
			}else if(tipoAmortizacion.equals("AL")){
				prestamo.getCuotas()[i].setAbonoCapital(Utilitario.redondear(abonoCapital));
			}
		}

		// Llenar las cuotas del prestamo
		for (int i = 0; i < prestamo.getCuotas().length; i++) {
			if (prestamo.getCuotas()[i].getNumero() != 1) {
				if (prestamo.getCuotas()[i].getNumero() != prestamo.getPlazo()) {
					// Verificamos el tipo de amortizacion
					if (tipoAmortizacion.equals("FR")) {
						calcularValoresCuota(prestamo.getInteres(), prestamo.getCuotas()[i],prestamo.getCuotas()[i + 1]);
						logger.info("Cuota creada: " + prestamo.getCuotas()[i]);
					}else if(tipoAmortizacion.equals("AL")){
						calcularValoresCuotaAlemana(prestamo.getInteres(), prestamo.getCuotas()[i],prestamo.getCuotas()[i + 1]);
						logger.info("Cuota creada: " + prestamo.getCuotas()[i]);
					}
				} else {
					// Verificamos el tipo de amortizacion
					if (tipoAmortizacion.equals("FR")) {
						calcularValoresCuota(prestamo.getInteres(), prestamo.getCuotas()[i], null);
						logger.info("Cuota creada: " + prestamo.getCuotas()[i]);
					} else if (tipoAmortizacion.equals("AL")) {
						calcularValoresCuotaAlemana(prestamo.getInteres(), prestamo.getCuotas()[i], null);
					}
				}

			} else {
				prestamo.getCuotas()[i].setCapitalInicio(prestamo.getMonto());
				if (tipoAmortizacion.equals("FR")) {
					calcularValoresCuota(prestamo.getInteres(), prestamo.getCuotas()[i], prestamo.getCuotas()[i + 1]);
					logger.info("Cuota creada: " + prestamo.getCuotas()[i]);
				} else if (tipoAmortizacion.equals("AL")) {
					calcularValoresCuotaAlemana(prestamo.getInteres(), prestamo.getCuotas()[i],prestamo.getCuotas()[i + 1]);
				}

			}

		}

	}

	private static void calcularValoresCuota(double interes, Cuota cuotaActual, Cuota cuotaSiguiente) {
		// Calculamos el interes de la cuota
		double interesValor = Utilitario.redondear(cuotaActual.getCapitalInicio() * ((interes / 12) / 100));
		// Calculamos el abono al capital
		double abonoCapital = Utilitario.redondear(cuotaActual.getCuota() - interesValor);
		// Calculamos el saldo que queda ha pagar
		double saldo = Utilitario.redondear(cuotaActual.getCapitalInicio() - abonoCapital);

		// Asignamos los valores calculados a la cuota
		cuotaActual.setAbonoCapital(abonoCapital);
		cuotaActual.setInteres(interesValor);
		cuotaActual.setSaldo(saldo);
		cuotaActual.setPendiente(cuotaActual.getCuota());

		// Asignamos el capital inicial de la siguiente cuota
		if (cuotaSiguiente != null) {
			cuotaSiguiente.setCapitalInicio(saldo);
		} else {
			if (saldo > 0) {
				double cuotaNueva = cuotaActual.getCuota() + saldo;
				abonoCapital = Utilitario.redondear(cuotaNueva - interesValor);
				saldo = Utilitario.redondear(cuotaActual.getCapitalInicio() - abonoCapital);
				cuotaActual.setCuota(cuotaNueva);
				cuotaActual.setAbonoCapital(abonoCapital);
				cuotaActual.setInteres(interesValor);
				cuotaActual.setSaldo(saldo);
				cuotaActual.setPendiente(cuotaNueva);
			}
		}
	}

	private static void calcularValoresCuotaAlemana(double interes, Cuota cuotaActual, Cuota cuotaSiguiente) {
		// Calculamos el interes de la cuota
		double interesValor = Utilitario.redondear(cuotaActual.getCapitalInicio() * ((interes / 12) / 100));
		// Calculamos el abono al capital
		double cuota = Utilitario.redondear(cuotaActual.getAbonoCapital() + interesValor);
		// Calculamos el saldo que queda ha pagar
		double saldo = Utilitario.redondear(cuotaActual.getCapitalInicio() - cuotaActual.getAbonoCapital());

		// Asignamos los valores calculados a la cuota
		cuotaActual.setCuota(cuota);
		cuotaActual.setInteres(interesValor);
		cuotaActual.setSaldo(saldo);
		
		// Asignamos el capital inicial de la siguiente cuota
		if (cuotaSiguiente != null) {
			cuotaSiguiente.setCapitalInicio(saldo);
		} 
	}

	public static void mostrarTabla(Prestamo prestamo) {
		// Mostrar tabla de amortizacion
		System.out.println("No   Cuota    CapitalInicio   Interes   Capital   Saldo   Pagado   Pendiente");
		for (int i = 0; i < prestamo.getCuotas().length; i++) {
			System.out.println(prestamo.getCuotas()[i]);
		}
	}
}
