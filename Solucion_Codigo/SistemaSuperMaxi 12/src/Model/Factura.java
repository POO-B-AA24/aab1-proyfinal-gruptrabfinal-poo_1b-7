package Model;

import Controller.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    private Cliente cliente;
    private List<Producto> productos;
    private double total;
    private double totalImpuestos;

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
        this.totalImpuestos = 0.0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecioFinal();
        totalImpuestos += producto.getPrecioFinal() * 0.15; 
    }

    public double getTotal() {
        return total;
    }

    public double getTotalImpuestos() {
        return totalImpuestos;
    }
    public double getTotalConDescuento() {
        double descuento = cliente.calcularDescuento();
        total = total - (total * descuento);
        return total;
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

