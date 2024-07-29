
package Controller;

import java.time.LocalDate;

public class ProductoSalud extends Producto {

    public ProductoSalud(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock) {
        super(nombreProducto, fechaCaducidad, precio, precioPromo, stock);
    }
    
    public void calcularDescuento(){
       
        precio *= 0.88;
        precioPromo *= 0.88;
    }
}
