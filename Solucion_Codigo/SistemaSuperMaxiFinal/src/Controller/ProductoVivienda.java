
package Controller;

import java.time.LocalDate;

public class ProductoVivienda extends Producto{

    public ProductoVivienda(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock) {
        super(nombreProducto, fechaCaducidad, precio, precioPromo, stock);
    }
    
    public void calcularDescuento(){
        
        precio *= 0.91;
        precioPromo *= 0.91;
    }
}
