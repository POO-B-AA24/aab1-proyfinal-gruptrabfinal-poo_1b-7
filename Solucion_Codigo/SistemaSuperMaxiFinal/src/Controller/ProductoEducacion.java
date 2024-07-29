
package Controller;

import java.time.LocalDate;

public class ProductoEducacion extends Producto {

    public ProductoEducacion(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock) {
        super(nombreProducto, fechaCaducidad, precio, precioPromo, stock);
    }
    
    public void calcularDescuento(){
        
        precio *= 0.84;
        precioPromo *= 0.84;
    }
}
