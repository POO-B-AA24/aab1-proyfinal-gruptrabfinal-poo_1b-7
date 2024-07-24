package Controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreProducto;
    private LocalDate fechaCaducidad;
    private double precio;
    private double precioPromo;
    private int stock;
    private CategoriaProducto categoria;

    public Producto(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock, CategoriaProducto categoria) {
        this.nombreProducto = nombreProducto;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
        this.precioPromo = precioPromo;
        this.stock = stock;
        this.categoria = categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPrecioPromocional() {
        return precioPromo;
    }

    public int getStock() {
        return stock;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void reducirStock(int cantidad) {
        if (cantidad <= stock) {
            this.stock -= cantidad;
        } else {
            System.out.println("Cantidad a reducir excede el stock disponible.");
        }
    }

    public boolean estaEnPromocion() {
        long diasParaCaducar = ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad);
        return stock > 100 || diasParaCaducar < 7;
    }

    public double getPrecioFinal() {
        return estaEnPromocion() ? precioPromo : precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombreProducto=" + nombreProducto + ", fechaCaducidad=" + fechaCaducidad + ", precio=" + precio + ", precioPromo=" + precioPromo + ", stock=" + stock + ", categoria=" + categoria + '}';
    }

    
}


    
