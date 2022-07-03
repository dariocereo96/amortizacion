package com.cmc.evaluacion.entidades;

public class Cuota {
	private int numero;
	private double cuota, capitalInicio, interes, abonoCapital, saldo;
	private boolean pagado;
	private double pendiente;

	public Cuota(int numero) {
		super();
		this.numero = numero;
		this.pagado = false;
		this.pendiente = this.cuota;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public double getCapitalInicio() {
		return capitalInicio;
	}

	public void setCapitalInicio(double capitalInicio) {
		this.capitalInicio = capitalInicio;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getAbonoCapital() {
		return abonoCapital;
	}

	public void setAbonoCapital(double abonoCapital) {
		this.abonoCapital = abonoCapital;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public double getPendiente() {
		return pendiente;
	}

	public void setPendiente(double pendiente) {
		this.pendiente = pendiente;
	}

	@Override
	public String toString() {
		return numero + "   |  " + cuota + "  |  " + capitalInicio + "  |  "
				+ interes + " | " + abonoCapital + " | " + saldo + " | " + pagado + " | " + pendiente;
	}

}
