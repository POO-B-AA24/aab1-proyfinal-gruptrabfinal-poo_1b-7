package Controller;
public class Cliente {
    public String nombre;
    public String ruc_cedula;
    public String direccion;
    public String telefono;

    public Cliente(String nombre, String ruc_cedula, String direccion, String telefono) {
        this.nombre = nombre;
        this.ruc_cedula = ruc_cedula;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", ruc_cedula=" + ruc_cedula + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
    
    
}
