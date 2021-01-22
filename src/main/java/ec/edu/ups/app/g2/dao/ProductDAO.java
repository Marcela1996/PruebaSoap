package ec.edu.ups.app.g2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.g2.modelo.Carrito;

@Stateless
public class ProductDAO {
	

	@Inject
	private EntityManager em;

	public boolean insert(Carrito carrito) {
		try {
			em.persist(carrito);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean actualizar(Carrito carrito) {
		try{
			em.merge(carrito);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	
	}

	public Carrito find(int id ) { 
		return em.find(Carrito.class, id);
	}

	public List<Carrito> getCarritos(){
		return em.createQuery("SELECT c from Carrito c", Carrito.class).getResultList();
	}

	public boolean remove(int codigo ) { 
		try {
			//Producto carrito = find(idCarrito);
			//System.out.println("carrito encontrado"+carrito);
		return em.createNativeQuery("DELETE FROM carrito where codigo = "+codigo).executeUpdate() != 0;
			 
		 
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public int getLastIdCarrito() {
		String jpql = "Select MAX(e.idCarrito) from Carrito e";
		Query q = em.createQuery(jpql, Integer.class);
		return (int) q.getSingleResult();
	}
	
	public List<Carrito> getCarritoXCedula (String cedula) {
		List<Carrito> lista = new ArrayList()<Carrito>();
		
		for(Carrito carrito: getCarritos()) {
			if(carrito.getCedula().compareTo(cedula)==0) {
				lista.add(carrito);
			}
		}
		return  lista ;
	}
	
}
