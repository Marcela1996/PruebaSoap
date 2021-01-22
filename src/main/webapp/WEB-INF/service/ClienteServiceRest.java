package ec.edu.ups.app.g2.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.app.g2.modelo.Acceso;
import ec.edu.ups.app.g2.modelo.Cajero;
import ec.edu.ups.app.g2.modelo.Cliente;
import ec.edu.ups.app.g2.negocio.CajeroON;
import ec.edu.ups.app.g2.negocio.ClienteON;
import ec.edu.ups.app.g2.negocio.CreditoON;
import ec.edu.ups.app.g2.negocio.GestionAdministradorON;
import ec.edu.ups.app.g2.utils.Respuesta;



@Path("/users")
public class ClienteServiceRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ClienteON con;
	@Inject
	private CajeroON caon;
	@Inject
	private CreditoON crediON;
	@Inject
	private GestionAdministradorON adminON;

	Date myDate = new Date();

	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	private String buscarTipo;
	private String estadoAcceso;

	@POST
	@Path("/transferencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta transferencia2(TransferenciaTemporal t) {
		Respuesta r = new Respuesta();
		try {
			r.setCodigo(1);
			r.setMensaje(con.transferencia(t));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			r.setCodigo(0);
			r.setMensaje(e.getMessage());
		}
		return r;

	}

	@POST
	@Path("/Retiro")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta retiro(@QueryParam("cajero") String cajero, @QueryParam("cliente") String cliente,
			@QueryParam("monto") Double monto) {
		Respuesta r = new Respuesta();
		try {
			r.setCodigo(1);
			r.setMensaje(caon.retiro(cajero, cliente, monto));
		} catch (Exception e) {
			r.setCodigo(0);
			r.setMensaje("Error " + e.getMessage());
			e.printStackTrace();
		}

		return r;
	}

	@POST
	@Path("/Deposito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta deposito(@QueryParam("cajero") String cajero, @QueryParam("cliente") String cliente,
			@QueryParam("monto") Double monto, @QueryParam("depositante") String depositante) {
		Respuesta r = new Respuesta();
		try {
			r.setCodigo(1);
			r.setMensaje(caon.depositosC(cajero, cliente, monto, depositante));
		} catch (Exception e) {
			r.setCodigo(0);
			r.setMensaje("Error " + e.getMessage());
			e.printStackTrace();
		}
		return r;

	}

	@GET
	@Path("/Login")
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta login(@QueryParam("correo") String correo, @QueryParam("clave") String clave) throws Exception {

		boolean client = false;
		Acceso acceso = new Acceso();
		List<Acceso> accesos = new ArrayList<Acceso>();
		Cliente cliente;
		String mensaje;
		Respuesta r = new Respuesta();
		try {

			if (caon.loginC(correo, clave).getTipo().equalsIgnoreCase("cajero")) {
				client = true;
				Cajero cajero = caon.loginC(correo, clave);
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",
				// client);
				mensaje = "Ingreso Exitoso";
				r.setCodigo(0);
				r.setMensaje(cajero.getCedula());
				return r;
			} // Fin if (caon.loginC(this.correo,
				// this.clave).getTipo().equalsIgnoreCase("cajero"))

		} catch (Exception e) {
			e.printStackTrace();

			if (con.buscarCorreo(correo) != null) {
				if (con.loginC(correo, clave) != null) {
					client = true;
					cliente = con.loginC(correo, clave);
					// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",
					// client);

					con.enviarCorreo(correo, "Acceso a la cuenta", "Acceso correcto a la cuenta");
					acceso.setClave(clave);
					acceso.setEstado("Correcto");
					acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
					acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
					acceso.setCliente(cliente);
					accesos.add(acceso);
					cliente.setAccesos(accesos);
					con.editar(cliente);

					acceso = new Acceso();
					accesos.clear();

					this.fechaHasta = LocalDate.now();
					this.fechaDesde = con.restarFecha(this.fechaHasta);
					this.buscarTipo = "Todos";
					this.estadoAcceso = "Todos";
					this.buscarTipo = "Todos";
					mensaje = "Ingreso Exitoso";
					r.setCodigo(1);
					r.setMensaje(cliente.getCuenta().getNumero());
					return r;
				} else {
					mensaje = "ERROR. Usuario Incorrecto";
					r.setCodigo(99);
					r.setMensaje(mensaje);
					con.enviarCorreo(correo, "Acceso a la cuenta",
							"Su intento ha sido fallido, con contraseña: " + clave);
					cliente = con.loginC(correo, clave);
					acceso.setClave(clave);
					acceso.setEstado("Fallido");
					acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
					acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
					acceso.setCliente(cliente);
					accesos.add(acceso);
					cliente.setAccesos(accesos);
					con.editar(cliente);

				} // Fin if (con.loginC(this.correo, this.clave) != null)
			} else {
				mensaje = "Error";
				r.setCodigo(99);
				r.setMensaje(mensaje);
			} // Fin if (con.buscarCorreo(this.correo) != null)
		} // FIn try-catch

		return null;
	}// Fin metodo login

	@GET
	@Path("/BuscarCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteTemporal buscarCliente(@QueryParam("cedula") String cedula) throws Exception {
		ClienteTemporal c = new ClienteTemporal();
		try {
			c.setCodigo(con.buscar(cedula).getCodigo());
			c.setCedula(con.buscar(cedula).getCedula());
			c.setNombre(con.buscar(cedula).getNombre());
			c.setCorreo(con.buscar(cedula).getCorreo());
			c.setCuenta(con.buscar(cedula).getCuenta().getNumero());

			return c;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta getModels(@PathParam("id") String id) {
		Respuesta r = new Respuesta();
		System.out.println("-----------------------------------------------------------");
		r.setCodigo(1);
		r.setMensaje(id);
		return r;
	}

	@GET
	@Path("listarCliente")
	@Produces("application/json")
	public List<Cliente> listar() throws Exception {
	System.out.println("-----------------------------------------------------------");
			return con.listar();
		
	}

}