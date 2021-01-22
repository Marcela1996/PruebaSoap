package ec.edu.ups.app.g2.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import ec.edu.ups.app.g2.modelo.Factura;
import ec.edu.ups.app.g2.modelo.Persona;

@Stateless
public class FacturaDAO {
	
	private EntityManager em;
	
		
	public boolean guardar(Factura factura) throws Exception {

		try {
			em.persist(factura);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
		return true;
	
	
	
	
	

}
	}
