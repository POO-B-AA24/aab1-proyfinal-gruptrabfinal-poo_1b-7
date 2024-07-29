package Model;

import Controller.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "factura.dat";

    public Cliente cliente;
    public List<Producto> productos;
    public double total;
    public double totalImpuestos;
    public double totalDescuento;

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
        this.totalImpuestos = 0.0;
        this.totalDescuento = 0.0;
        cliente.calcularDescuento();  // Calcula el descuento inicial del cliente
    }

    public void agregarProducto(Producto producto) {
        producto.calcularDescuento();  // Calcula el descuento específico del producto
        double precioFinal = producto.getPrecioFinal();
        productos.add(producto);
        total += precioFinal;
        totalDescuento += precioFinal * (1 - cliente.descuento);  // Aplica el descuento del cliente
        totalImpuestos += precioFinal * 0.15; 
    }
    
    public Factura cargarFactura(){
        Factura factura = null;
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            factura = (Factura) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return factura;
    }
    
    public void guardarFactura() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return String.format("Factura {\n" +
                             "  Cliente: %s,\n" +
                             "  Productos: %s,\n" +
                             "  Total: %.2f,\n" +
                             "  Total Impuestos: %.2f\n" +
                             '}', cliente, productos, total, totalImpuestos);
    }
}


