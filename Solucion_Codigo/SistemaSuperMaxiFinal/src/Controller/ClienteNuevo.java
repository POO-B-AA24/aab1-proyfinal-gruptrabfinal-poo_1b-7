
package Controller;

public class ClienteNuevo extends Cliente {

    public ClienteNuevo(String nombre, int ruc_cedula, String direccion, int telefono) {
        super(nombre, ruc_cedula, direccion, telefono);
    }

    @Override
    public void calcularDescuento() {
        // Lógica para calcular el descuento para clientes nuevos
        descuento = 0.05; // Ejemplo: 5% de descuento
    }
}
