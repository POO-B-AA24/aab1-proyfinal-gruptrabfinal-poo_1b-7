
package Controller;

import java.time.LocalDate;

public class ProductoAlimentacion extends Producto {

    public ProductoAlimentacion(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock) {
        super(nombreProducto, fechaCaducidad, precio, precioPromo, stock);
    }

    
    
    public void calcularDescuento(){
        precio *= 0.90;
        precioPromo *= 0.90;
    }
    
}
