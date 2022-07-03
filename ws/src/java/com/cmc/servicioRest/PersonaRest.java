package com.cmc.servicioRest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Consulta;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ManejadorArchivos;
import com.cmc.rest.servicios.ServicioPersonas;*/

@Path("/personas")
public class PersonaRest {

	/*@Path("/cambiar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Persona modificar(Persona persona) {
		System.out.println(persona);
		try {
			ServicioPersonas.actualizar(persona);
		} catch (ValidationException e) {
			System.out.println("Error en la validacion");
		}
		return persona;
	}

	@Path("/cambio")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response cambiar(Persona persona) {
		System.out.println(persona);
		try {
			ServicioPersonas.actualizar(persona);
			return Response.status(200).entity(persona).build();
		} catch (ValidationException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
	}

	@Path("/lista")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonas() {
		
		ManejadorArchivos manejadorArchivos = new ManejadorArchivos("miArchivo.txt");

		try {
			ArrayList<Persona> personas = manejadorArchivos.leer();
			System.out.println(personas);
			return Response.status(200).entity(personas).build();
		} catch (ArchivoException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
	}
	
	@Path("/consulta")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorCedula(Consulta consulta){
		
		Persona persona = ServicioPersonas.buscarPorCedula(consulta.getCedula());
		
		if(persona!=null){
			return Response.status(200).entity(persona).build();
		}else{
			return Response.status(200).entity("No se encontro ningun registro").build();
		}
	}
	
	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response guardarPersona(Persona persona) {
		System.out.println(persona);
		try {
			ServicioPersonas.guardarPersona(persona);
			return Response.status(200).entity(persona).build();
		} catch (ArchivoException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}*/
	
	
}
