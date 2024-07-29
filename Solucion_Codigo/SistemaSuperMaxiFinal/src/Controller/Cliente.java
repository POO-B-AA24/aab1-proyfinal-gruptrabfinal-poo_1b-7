package Controller;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    public String nombre;
    public int ruc_cedula;
    public String direccion;
    public int telefono;
    public double descuento;

    public Cliente(String nombre, int ruc_cedula, String direccion, int telefono) {
        this.nombre = nombre;
        this.ruc_cedula = ruc_cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descuento =  0.0;
    }

    public abstract void calcularDescuento();

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", ruc_cedula=" + ruc_cedula + ", direccion=" + direccion + ", telefono=" + telefono + ", descuento=" + descuento + '}';
    }
}
