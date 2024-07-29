package Controller;

public class ClienteFrecuente extends Cliente {
    public int puntosAcumulados;

    public ClienteFrecuente(String nombre, int ruc_cedula, String direccion, int telefono) {
        super(nombre, ruc_cedula, direccion, telefono);
        this.puntosAcumulados = 0;
    }

    public void acumularPuntos(int puntos) {
        this.puntosAcumulados += puntos;
    }

    public void calcularDescuento() {
        // Lógica para calcular el descuento basado en los puntos acumulados
        descuento = puntosAcumulados * 0.01; // Ejemplo: 1% de descuento por cada 100 puntos
    }
}
