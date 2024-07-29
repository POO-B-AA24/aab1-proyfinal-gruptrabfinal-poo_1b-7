
package Controller;

import java.time.LocalDate;

public class ProductoVestimenta extends Producto{

    public ProductoVestimenta(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock) {
        super(nombreProducto, fechaCaducidad, precio, precioPromo, stock);
    }
    
    
    
    public void calcularDescuento(){
       
        precio *= 0.95;
        precioPromo *= 0.95;
    }
}
