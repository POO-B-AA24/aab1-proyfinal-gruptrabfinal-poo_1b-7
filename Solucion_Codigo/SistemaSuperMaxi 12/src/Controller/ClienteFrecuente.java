package Controller;
public class ClienteFrecuente extends Cliente {
    private int puntosAcumulados;

    public ClienteFrecuente(String nombre, String rucCedula, String direccion, String telefono, int puntosAcumulados) {
        super(nombre, rucCedula, direccion, telefono);
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public double calcularDescuento() {
        return puntosAcumulados * 0.01; 
    }

    @Override
    public String toString() {
        return super.toString() + ", Puntos acumulados: " + puntosAcumulados;
    }
}
