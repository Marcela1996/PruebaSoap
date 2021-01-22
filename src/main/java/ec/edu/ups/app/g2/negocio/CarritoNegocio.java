package ec.edu.ups.app.g2.negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.app.g2.dao.PersonaDAO;
import ec.edu.ups.app.g2.dao.ProductDAO;
import ec.edu.ups.app.g2.modelo.Persona;
import ec.edu.ups.app.g2.modelo.Carrito;



@Stateless
public class CarritoNegocio {
	
	@Inject
	private ProductDAO  productoDAO;
	@Inject
	private PersonaDAO  personaDAO;
	
	public boolean guardar(Carrito carrito) throws Exception {
		return productoDAO.insert(carrito);

	}
	
	public boolean 	ingresar(Persona persona) {	
	
			return personaDAO.guardar(persona);
					
					
				
}
	}

	
	
	

