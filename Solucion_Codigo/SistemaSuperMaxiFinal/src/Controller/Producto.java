package Controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    public String nombreProducto;
    public LocalDate fechaCaducidad;
    public double precio;
    public double precioPromo;
    public int stock;

    public Producto(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock) {
        this.nombreProducto = nombreProducto;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
        this.precioPromo = precioPromo;
        this.stock = stock;
    }

    public abstract void calcularDescuento();

    public void reducirStock(int cantidad) {
        if (cantidad <= stock) {
            this.stock -= cantidad;
        } else {
            System.out.println("Cantidad a reducir excede el stock disponible.");
        }
    }

    public boolean estaEnPromocion() {
        long diasParaCaducar = ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad);
        return stock < 100 || diasParaCaducar < 7;
    }

    public double getPrecioFinal() {
        return estaEnPromocion() ? precioPromo : precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombreProducto=" + nombreProducto + ", fechaCaducidad=" + fechaCaducidad + ", precio=" + precio + ", precioPromo=" + precioPromo + ", stock=" + stock + '}';
    }
} 
