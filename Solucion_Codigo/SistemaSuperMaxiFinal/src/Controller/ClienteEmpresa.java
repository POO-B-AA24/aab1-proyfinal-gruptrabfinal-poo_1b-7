package Controller;
public class ClienteEmpresa extends Cliente {

    public ClienteEmpresa(String nombre, int ruc_cedula, String direccion, int telefono) {
        super(nombre, ruc_cedula, direccion, telefono);
    }

    @Override
    public void calcularDescuento() {
        // Lógica para calcular el descuento para clientes de empresa
        descuento = 0.10; // Ejemplo: 10% de descuento
    }
}
