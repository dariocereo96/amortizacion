package com.cmc.evaluacion.commons;

public class Utilitario {
	public static double redondear(double valor){
		return Math.round(valor*100.0)/100.0;
	}
	
	public static void main(String[] args) {
		System.out.println(redondear(12.939393));
	}
}
