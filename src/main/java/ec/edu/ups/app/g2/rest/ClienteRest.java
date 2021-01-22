package ec.edu.ups.app.g2.rest;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.app.g2.modelo.Factura;
import ec.edu.ups.app.g2.modelo.Carrito;
import ec.edu.ups.app.g2.negocio.CarritoNegocio;
import ec.edu.ups.app.g2.negocio.FacturaNegocio;

@Path("/carrito")
public class ClienteRest implements Serializable{
	
	
	private static final long serialVersionUID=1L;
	@Inject
	private CarritoNegocio con;
	
	@Inject
	private FacturaNegocio facON;
	
	private String buscarProducto;
	private String estado;
	
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("addCarrito")
	public String addCarrito(String parametro) {//:1:0100500719:
		 int idPelicula = Integer.parseInt(parametro.split(":")[2].replaceAll("\"", "").trim());
		String cedula = parametro.split(":")[3];
		System.out.println(parametro + " Llegado a adcarritoServicioRest");
		return gl.addCarrito(idPelicula, cedula);
	}
	

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("removeCarrito")
	public String removeCarrito(String parametro) {
		int idPelicula = Integer.parseInt(parametro.split(":")[2].replaceAll("\"", "").trim());
		String cedula = parametro.split(":")[3];
		for(Carrito carrito : gl.getCarritos()) {
			if(carrito.getCedulaUsuario().compareToIgnoreCase(cedula)==0 &&
				carrito.getPelicula().getCodigoPelicula() == idPelicula	
			   ) {
				if(gl.eliminarCarrito(carrito.getIdCarrito())) 
					return "Se elimino un carrito";
				else
					return "No se elimino el carrito";
			}
		}
		return "No se encontro el carrito";
	}
	
	

	

}
