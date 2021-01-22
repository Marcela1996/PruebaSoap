package ec.edu.ups.app.g2.dao;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import ec.edu.ups.app.g2.modelo.Persona;


@Stateless
public class PersonaDAO {
	
	private EntityManager em;
	
	public boolean guardar(Persona persona) throws Exception {

		try {
			em.persist(persona);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
		return true;
	
	}

}
