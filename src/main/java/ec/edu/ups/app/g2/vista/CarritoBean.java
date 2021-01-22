package ec.edu.ups.app.g2.vista;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.app.g2.modelo.Factura;
import ec.edu.ups.app.g2.modelo.Persona;
import ec.edu.ups.app.g2.modelo.Carrito;
import ec.edu.ups.app.g2.negocio.CarritoNegocio;
import ec.edu.ups.app.g2.negocio.FacturaNegocio;

@ManagedBean
@ApplicationScoped
public class CarritoBean {
	
	@Inject
	private CarritoNegocio carritoON;
	@Inject 
	private FacturaNegocio facON;
	
	
	private String cedula;
	
	private String mensaje;
	private Persona persona;
	private Carrito producto;
	private Factura factura;
	
	
	public void init() {
		persona=new Persona();
		producto=new Carrito();
		factura= new Factura();
		
		cedula="";
		mensaje= "";
		
		
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public Carrito getProducto() {
		return producto;
	}


	public void setProducto(Carrito producto) {
		this.producto = producto;
	}


	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public String comprar(Persona persona) {
		System.out.println(persona);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
	
	}
	
	
	

}
