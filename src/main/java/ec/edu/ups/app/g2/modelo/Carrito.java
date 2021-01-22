package ec.edu.ups.app.g2.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Carrito {
 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int codigo; 
 private String producto;
 private int stock;
 private double costo;
 
 
public int getCodigo() {
	return codigo;
}
public void setCodigo(int codigo) {
	this.codigo = codigo;
}
public String getProducto() {
	return producto;
}
public void setProducto(String producto) {
	this.producto = producto;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public double getCosto() {
	return costo;
}
public void setCosto(double costo) {
	this.costo = costo;
}
@Override
public String toString() {
	return "Producto [codigo=" + codigo + ", producto=" + producto + ", stock=" + stock + ", costo=" + costo + "]";
}
 

}
