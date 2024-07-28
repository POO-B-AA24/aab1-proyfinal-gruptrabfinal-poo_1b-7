package Model;

import Controller.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Factura implements Serializable {
    public static final long serialVersionUID = 1L;

    public Cliente cliente;
    public List<Producto> productos;
    public double total;
    public double totalImpuestos;

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
        this.totalImpuestos = 0.0;
    }

    public void agregarProducto(Producto producto, boolean aplicarPromocion) { //aqui llega lo del main para asiganarle la promocion 
        double precioFinal = aplicarPromocion ? producto.getPrecioPromocional() : producto.getPrecio(); // la validacion de si es que tiene o no el producto promocion 
        productos.add(producto);
        total += precioFinal;
        totalImpuestos += precioFinal * 0.15;
    }

    public double getTotal() {
        return total;
    }

    public double getTotalImpuestos() {
        return totalImpuestos;
    }

    public double getTotalConDescuento() {
        double descuento = cliente.calcularDescuento();
        return total - (total * descuento);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "cliente=" + cliente +
                ", productos=" + productos +
                ", total=" + total +
                ", total con descuento=" + getTotalConDescuento() +
                '}';
    }
}