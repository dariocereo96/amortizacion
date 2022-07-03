package com.cmc.servicioRest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cmc.evaluacion.commons.TipoAmortizacion;
import com.cmc.evaluacion.entidades.Cuota;
import com.cmc.evaluacion.entidades.Prestamo;
import com.cmc.evaluacion.entidades.PrestamoDTO;
import com.cmc.evaluacion.servicios.CalculadoraAmortizacion;

@Path("/prestamos")
public class ServicioBancario {
	
	@Path("/simulacion")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response simularPrestamo(PrestamoDTO prestamoDTO){
		Prestamo prestamo = new Prestamo("A1235",prestamoDTO.getMonto(),prestamoDTO.getInteres(),prestamoDTO.getPlazo());
		
		if(prestamoDTO.getTipo().equals("FR")){
			CalculadoraAmortizacion.generarTabla(prestamo,TipoAmortizacion.FRANCESA);
		}else if(prestamoDTO.getTipo().equals("AL")){
			CalculadoraAmortizacion.generarTabla(prestamo,TipoAmortizacion.ALEMANA);
		}
		
		double totalIntereses=0;
		for (Cuota cuota : prestamo.getCuotas()) {
			totalIntereses += cuota.getInteres();
		}
		
		System.out.println(totalIntereses);
		
		CalculadoraAmortizacion.mostrarTabla(prestamo);
		return Response.status(200).entity(prestamo.getCuotas()).build();
	}
}
