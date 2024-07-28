package Controller;
public class ClienteEmpresa extends Cliente {

    private String nombreEmpresa;

    public ClienteEmpresa(String nombreEmpresa, String nombre, String rucCedula, String direccion, String telefono) {
        super(nombre, rucCedula, direccion, telefono);
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    @Override
    public double calcularDescuento() {
        return 15.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nombre de la empresa: " + nombreEmpresa;
    }
}
